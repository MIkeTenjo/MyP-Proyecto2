FROM gradle:8.6-jdk21 AS build
COPY --chown=gradle:gradle ./home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
CMD ["gradle", "test", "--no-daemon"]
