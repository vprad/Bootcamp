FROM openjdk:11
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/OnlineExaminationSystem-0.0.1-SNAPSHOT-plain.jar onlineexaminationsystem.jar
EXPOSE 8081
ENTRYPOINT exec java $JAVA_OPTS -jar onlineexaminationsystem.jar
#For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar developercommunityapp.jar