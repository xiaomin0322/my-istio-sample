FROM uquote/jdk8-tomee1.7.3-plume

MAINTAINER Simon Meng <menghot@gmail.com>

WORKDIR /

ARG JAR_FILE
ADD target/${JAR_FILE} /work/app.jar
ADD run.sh /run.sh
RUN chmod +x /run.sh

#ENV JAVA_OPTS=""

# Instead run app directly, Run app by a wrapped script will bring some benefits
# 1. able to accept extenal enviroment variables
# 2. won't extends the app stop time

#ENTRYPOINT ["/run.sh"]

#java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /work/app.jar

EXPOSE 8081


#ENTRYPOINT ["java","-Duser.timezone=GMT+08","$JAVA_OPTS","-Djava.security.egd=file:/dev/./urandom"]

ENTRYPOINT ["java","-Duser.timezone=GMT+08","-Djava.security.egd=file:/dev/./urandom"]
CMD ["-jar","/work/app.jar"]

