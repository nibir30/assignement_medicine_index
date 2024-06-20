FROM openjdk:17-alpine
ENV SPRING_PROFILES_ACTIVE=prod
COPY target/medicine_index*.jar /opt/app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app/app.jar"]

#FROM openjdk:17-alpine
#COPY ./target/doclive*.jar app.jar
#
#ENTRYPOINT ["java","-jar","/app.jar"]
