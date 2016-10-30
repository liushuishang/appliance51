FROM frolvlad/alpine-oraclejdk8:slim
ENV LC_ALL en_US.UTF-8
ENV TZ 'Asia/Shanghai'
VOLUME /tmp
ADD appliance51-admin-0.0.1-SNAPSHOT.jar admin.jar
EXPOSE 8080 8069
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin.jar"]