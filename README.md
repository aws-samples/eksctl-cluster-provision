# EKS Cluster Demo

O objetivo desse repositório é demonstrar a utilização do [eksclt](https://eksctl.io) para provisionar um cluster EKS em alta disponibilidade dentro de uma rede VPC já existente em sua conta na AWS

## Pré-Requisitos

* kubectl
* [eksctl](https://docs.aws.amazon.com/eks/latest/userguide/eksctl.html#installing-eksctl)
* aws-cli
* VPC previamente configurada, pode ser encontrada nesse [repositório](https://github.com/BRCentralSA/aws-brazil-edu-series/blob/master/utils/vpc-template.yaml), necessário minimo de duas Zonas de disponibilidade e 4 subnets, 2 públicas e 2 privadas
* Credenciais de acesso a AWS previamente configuradas em **~/.aws/credentials**
* [cookiecutter](https://cookiecutter.readthedocs.io/en/1.7.0/index.html) para gerar o cluster.yaml necessário para criar o cluster utilizando o eksclt

## Criando seu primeiro cluster

* Execute o seguinte comando com o cookiecutter para gerar as configurações do seu cluster EKS:
```shell
cookiecutter eks_configs
```

* Preencha as perguntas que serão realizadas pelo cookiecutter, no final uma pasta com o nome que você definiu para o seu cluster será criada na raiz, entre nela e siga os passos do README.md

## Exemplos

A pasta **examples/cluster-creation** foi criada para facilitar o entendimento do que será gerado pelo cookiecutter com os valores previamente preenchidos.

## Referências

eksctl - https://github.com/weaveworks/eksctl