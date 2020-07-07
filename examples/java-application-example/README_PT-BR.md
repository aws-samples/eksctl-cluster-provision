# Java Application Example

## Pré-Requisitos

- [Repositório ECR criado na conta](https://docs.aws.amazon.com/pt_br/AmazonECR/latest/userguide/repository-create.html) com o nome java-application-example.
- Bucket que será utilizado para listar os objetos pela nossa aplicação.
- Role com permissão de acesso ao bucket S3, como estamos utilizando o kube2iam é necessário alterar a Trust Relationship, consultar (https://github.com/jtblin/kube2iam)

## Realizando o Deploy

### Buildando e enviado a imagem para o ECR

- Realizar Login no repositório do ECR (Comando de login do ECR precisa do aws cli v2)

```shell
aws ecr get-login-password --region REGION | docker login --username AWS --password-stdin ACCOUNT_ID.dkr.ecr.REGION.amazonaws.com
```

- Na raiz desta pasta executar o comando a seguir para buildar o container.

```shell
docker build -t java-application-example .
```

- Realizar o tagging da imagem buildada localmente para preparar para envia-la para o repositório

```shell
docker tag java-application-example:latest ACCOUNT_ID.dkr.ecr.REGION.amazonaws.com/java-application-example:latest
```

- Realizar o Push da imagem para o repositório ECR

```shell
docker push ACCOUNT_ID.dkr.ecr.REGION.amazonaws.com/java-application-example:latest
```

### Alterando manifestos para realizar deploy da aplicação no EKS

**kubernetes/01-configmap.yaml**

```yaml
BUCKET_NAME: Seu bucket para utilizar na PoC
REGION_NAME: Regiao onde esta seu bucket
```

**kubernetes/02-deployment.yaml**

```yaml
  metadata:
      annotations:
        iam.amazonaws.com/role: <Role com permissao de acesso ao S3>
  ...
  - image: <URL da imagem que subimos para o ECR>
```

### Aplicando manifestos

- Antes de aplicar é necessário criar o namespace para nossa aplicação, nessa demonstração estamos utilizando o namespace prd, então execute o seguinte comando:

```shell
kubectl create namespace prd
```

- Então depois de tudo alterado e configurado execute o seguinte comando

```shell
kubectl apply -f kubernetes/
```

- Assim a sua aplicação será provisionada dentro do cluster no namespace PRD.

### Testando a aplicação

- Precisamos do endpoint do balanceador público provisionado pelo Kubernetes, para isso execute o seguinte comando

```shell
kubectl get svc -nprd | awk '{print $4}' | grep -vi external
```

- Acesse o endpoint resultado do comando acima no path **/api/listarObjetos/**

- Ele exibira a lista de objetos presentes no seu Bucket utilizando as permissões da role criada acessando sua conta na AWS de maneira segura
