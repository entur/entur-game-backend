FROM gcr.io/distroless/java17-debian12:nonroot

LABEL org.opencontainers.image.authors="teampartner@entur.org"
EXPOSE 9010

COPY build/libs/entur-game-backend*.jar /app/entur-game-backend.jar
COPY src/main/kubernetes/ /config

WORKDIR /app
CMD ["entur-game-backend.jar"]

