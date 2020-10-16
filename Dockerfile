FROM java:8u111-jre
MAINTAINER Develon 787246976@qq.com
WORKDIR /project
ADD target/*.jar /project/app.jar
EXPOSE 8443