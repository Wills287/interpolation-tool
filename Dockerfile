FROM adoptopenjdk/openjdk11:alpine-jre

ENV APPLICATION=interpolation-tool

WORKDIR /home/

ADD target/$APPLICATION.jar /home/$APPLICATION.jar

EXPOSE 8080

CMD java -jar /home/$APPLICATION.jar
