quarkus build
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/hodrigohamalho/soap-mock-jvm .
docker push quay.io/hodrigohamalho/soap-mock-jvm