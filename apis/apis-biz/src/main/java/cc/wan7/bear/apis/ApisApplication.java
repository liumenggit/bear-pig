package cc.wan7.bear.apis;

import cc.wan7.bear.common.feign.annotation.EnableBearFeignClients;
import cc.wan7.bear.common.security.annotation.EnableBearResourceServer;
import cc.wan7.bear.common.swagger.annotation.EnableBearDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
* @author pig archetype
* <p>
* 项目启动类
*/
@EnableBearDoc
@EnableBearFeignClients
@EnableBearResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class ApisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApisApplication.class, args);
    }

}
