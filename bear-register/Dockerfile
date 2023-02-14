FROM moxm/java:1.8-full

RUN mkdir -p /bear-register

WORKDIR /bear-register

ARG JAR_FILE=target/bear-register.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8848

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD sleep 30; java $JAVA_OPTS -jar app.jar
