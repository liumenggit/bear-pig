/*
 * Copyright (c) 2020 bear4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.wan7.bear.auth.config;

import cc.wan7.bear.auth.support.CustomeOAuth2AccessTokenGenerator;
import cc.wan7.bear.auth.support.core.CustomeOAuth2TokenCustomizer;
import cc.wan7.bear.auth.support.core.FormIdentityLoginConfigurer;
import cc.wan7.bear.auth.support.core.BearDaoAuthenticationProvider;
import cc.wan7.bear.auth.support.handler.BearAuthenticationFailureEventHandler;
import cc.wan7.bear.auth.support.handler.BearAuthenticationSuccessEventHandler;
import cc.wan7.bear.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import cc.wan7.bear.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import cc.wan7.bear.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationConverter;
import cc.wan7.bear.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationProvider;
import cc.wan7.bear.common.core.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;

/**
 * @author lengleng
 * @date 2022/5/27
 *
 * ?????????????????????
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfiguration {

	private final OAuth2AuthorizationService authorizationService;

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

		http.apply(authorizationServerConfigurer.tokenEndpoint((tokenEndpoint) -> {// ???????????????????????????
			tokenEndpoint.accessTokenRequestConverter(accessTokenRequestConverter()) // ??????????????????????????????Converter
					.accessTokenResponseHandler(new BearAuthenticationSuccessEventHandler()) // ?????????????????????
					.errorResponseHandler(new BearAuthenticationFailureEventHandler());// ?????????????????????
		}).clientAuthentication(oAuth2ClientAuthenticationConfigurer -> // ????????????????????????
		oAuth2ClientAuthenticationConfigurer.errorResponseHandler(new BearAuthenticationFailureEventHandler()))// ???????????????????????????
				.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint// ????????????????????????confirm??????
						.consentPage(SecurityConstants.CUSTOM_CONSENT_PAGE_URI)));

		RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
		DefaultSecurityFilterChain securityFilterChain = http.requestMatcher(endpointsMatcher)
				.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
				.apply(authorizationServerConfigurer.authorizationService(authorizationService)// redis??????token?????????
						.authorizationServerSettings(AuthorizationServerSettings.builder()
								.issuer(SecurityConstants.PROJECT_LICENSE).build()))
				// ????????????????????????????????????
				.and().apply(new FormIdentityLoginConfigurer()).and().build();

		// ?????????????????????????????????
		addCustomOAuth2GrantAuthenticationProvider(http);
		return securityFilterChain;
	}

	/**
	 * ???????????????????????? </br>
	 * client:username:uuid
	 * @return OAuth2TokenGenerator
	 */
	@Bean
	public OAuth2TokenGenerator oAuth2TokenGenerator() {
		CustomeOAuth2AccessTokenGenerator accessTokenGenerator = new CustomeOAuth2AccessTokenGenerator();
		// ??????Token ????????????????????????
		accessTokenGenerator.setAccessTokenCustomizer(new CustomeOAuth2TokenCustomizer());
		return new DelegatingOAuth2TokenGenerator(accessTokenGenerator, new OAuth2RefreshTokenGenerator());
	}

	/**
	 * request -> xToken ?????????????????????
	 * @return DelegatingAuthenticationConverter
	 */
	private AuthenticationConverter accessTokenRequestConverter() {
		return new DelegatingAuthenticationConverter(Arrays.asList(
				new OAuth2ResourceOwnerPasswordAuthenticationConverter(),
				new OAuth2ResourceOwnerSmsAuthenticationConverter(), new OAuth2RefreshTokenAuthenticationConverter(),
				new OAuth2ClientCredentialsAuthenticationConverter(),
				new OAuth2AuthorizationCodeAuthenticationConverter(),
				new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
	}

	/**
	 * ?????????????????????????????????
	 *
	 * 1. ???????????? </br>
	 * 2. ???????????? </br>
	 *
	 */
	@SuppressWarnings("unchecked")
	private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);

		OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider = new OAuth2ResourceOwnerPasswordAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		OAuth2ResourceOwnerSmsAuthenticationProvider resourceOwnerSmsAuthenticationProvider = new OAuth2ResourceOwnerSmsAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		// ?????? UsernamePasswordAuthenticationToken
		http.authenticationProvider(new BearDaoAuthenticationProvider());
		// ?????? OAuth2ResourceOwnerPasswordAuthenticationToken
		http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
		// ?????? OAuth2ResourceOwnerSmsAuthenticationToken
		http.authenticationProvider(resourceOwnerSmsAuthenticationProvider);
	}

}
