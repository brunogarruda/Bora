
FROM openjdk:8-jdk-alpine
VOLUME /eventos
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/eventos.jar eventos.jar
RUN sh -c 'touch eventos.jar'
EXPOSE 8087
#ENTRYPOINT exec java $JAVA_OPTS -jar eventos.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar eventos.jar
