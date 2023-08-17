quarkus build
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/vflorent/consumidor-respostas-jvm .
docker push quay.io/vflorent/consumidor-respostas-jvm