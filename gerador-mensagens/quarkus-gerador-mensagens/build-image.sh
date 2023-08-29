quarkus build
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/hodrigohamalho/gerador-mensagens-jvm .
docker push quay.io/hodrigohamalho/gerador-mensagens-jvm
