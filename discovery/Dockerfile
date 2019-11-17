
FROM openjdk:8-jdk-alpine
VOLUME /discovery
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/discovery.jar discovery.jar
RUN sh -c 'touch discovery.jar'
EXPOSE 8761
#ENTRYPOINT exec java $JAVA_OPTS -jar discovery.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar discovery.jar
