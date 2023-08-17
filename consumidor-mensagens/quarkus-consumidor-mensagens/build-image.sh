quarkus build
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/vflorent/consumidor-mensagens-jvm .
docker push quay.io/vflorent/consumidor-mensagens-jvm