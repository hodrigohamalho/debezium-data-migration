quarkus build
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/vflorent/gerador-mensagens-jvm .
docker push quay.io/vflorent/gerador-mensagens-jvm