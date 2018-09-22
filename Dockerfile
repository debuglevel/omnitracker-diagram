FROM openjdk:8-jdk-alpine AS builder
COPY . /src/
WORKDIR /src/
RUN ./gradlew build


FROM haskell:7.8
RUN cabal update && \
    cabal install erd
RUN apt-get update && \
    apt-get install -y graphviz openjdk-7-jdk

WORKDIR /app
COPY --from=builder /src/build/libs/*-all.jar /app/omnitracker2erd.jar
ADD docker/run.sh .

CMD ["/app/run.sh"]