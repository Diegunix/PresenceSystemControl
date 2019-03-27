FROM java:8-jdk-alpine

COPY ./target/presenceSystem-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch presenceSystem-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","presenceSystem-0.0.1-SNAPSHOT.jar"]  