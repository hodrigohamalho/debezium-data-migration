quarkus build
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/vflorent/soap-mock-jvm .
docker push quay.io/vflorent/soap-mock-jvm