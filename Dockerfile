FROM openjdk:8
ADD target/orderProductPayment-0.0.1-SNAPSHOT.jar ordermanagement.jar
ENTRYPOINT ["java","-jar","ordermanagement.jar"]