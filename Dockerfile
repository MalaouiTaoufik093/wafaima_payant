FROM openjdk:8
EXPOSE 8080
ADD target/wafaima_payant.jar target/wafaima_payant.jar
ENTRYPOINT ["java","-jar","/target/wafaima_payant.jar"]



