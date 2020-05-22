# EKS Cluster Demo

O objetivo desse repositório é demonstrar a utilização do [eksclt](https://eksctl.io) para provisionar um cluster EKS em alta disponibilidade dentro de uma rede VPC já existente em sua conta na AWS

## Pré-Requisitos

* [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
* [eksctl](https://docs.aws.amazon.com/eks/latest/userguide/eksctl.html#installing-eksctl)
* [aws-cli](https://docs.aws.amazon.com/pt_br/cli/latest/userguide/cli-chap-install.html)
* VPC previamente configurada, pode ser encontrada nesse [repositório](https://github.com/BRCentralSA/aws-brazil-edu-series/blob/master/utils/vpc-template.yaml), necessário minimo de duas Zonas de disponibilidade e 4 subnets, 2 públicas e 2 privadas
* Credenciais de acesso a AWS previamente configuradas em **~/.aws/credentials** (https://docs.aws.amazon.com/pt_br/sdk-for-java/v1/developer-guide/setup-credentials.html)
* [cookiecutter](https://cookiecutter.readthedocs.io/en/1.7.0/index.html) para gerar o cluster.yaml necessário para criar o cluster utilizando o eksclt

## Criando seu primeiro cluster

* Criar Amazon EKS service role no console do IAM (https://docs.aws.amazon.com/eks/latest/userguide/getting-started-console.html#role-create), essa role será utilizada posteeriormente.

* Execute o seguinte comando com o cookiecutter para gerar as configurações do seu cluster EKS:
```shell
cookiecutter eks_configs
```

* As seguintes perguntas serão exibidas, após preenche-las uma pasta será criada na raiz do repositório com o nome que você definiu para o cluster, entre nela e siga os passos do README.md.

```
cluster_name [Nome do cluster, ex: poc-cluster]: poc-cluster-test
region [Nome da reigião, ex: us-east-1]: us-east-1
vpc_id [ID da VPC da sua conta, ex: vpc-00000000]: vpc-00000000
vpc_cidr [CIDR da VPC, ex: 10.0.0.0/16]: 10.2.0.0/16
availability_zone_1 [A primeira zona de disponibilidade, ex: us-east-1a]: us-east-1a
availability_zone_2 [A segunda zona de disponibilidade, ex: us-east-1b]: us-east-1b
subnet_priv_1a [O ID da primeira subnet privada, ex: subnet-00000000]: subnet-0000000
subnet_priv_1a_cidr [O CIDR da primeira subnet privada, ex: 10.0.0.0/24]: 10.2.2.0/24
subnet_priv_1b [O ID da segunda subnet privada, ex: subnet-00000000]: subnet-0000000
subnet_priv_1b_cidr [O CIDR da segunda subnet privada: 10.1.0.0/24]: 10.2.3.0/24
subnet_pub_1a [O ID da primeira subnet publica, ex: subnet-00000000]: subnet-000000
subnet_pub_1a_cidr [O CIDR da primeira subnet publica, ex: 10.2.0.0/24]: 10.2.0.0/24
subnet_pub_1b [O ID da segunda subnet publica, ex: subnet-00000000]: subnet-000000
subnet_pub_1b_cidr [O CIDR da segunda subnet publica: 10.3.0.0/24]: 10.2.1.0/24
eks_service_role [A Role criada anteriormente]: 
```




## Arquitetura do cluster que será provisionado

<p align="center"> 
<img src="images/cluster_diagram.png">
</p>


## Exemplos

A pasta **examples/cluster-creation** foi criada para facilitar o entendimento do que será gerado pelo cookiecutter com os valores previamente preenchidos.

## Aplicação Exemplo

Foi desenvolvida uma aplicação Java para podermos testar o nosso cluster provisionado anteriormente, essa aplicação realiza a chamada para a API da AWS utilizando uma role com permissões (O permissionamento é feito através do Kube2Iam) onde lista o conteudo de um bucket.

Siga as instruções do [README](examples/java-application-example/README.md) para provisiona-la no cluster

## Referências

https://github.com/weaveworks/eksctl
https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html
https://github.com/jtblin/kube2iam
https://docs.aws.amazon.com/eks/latest/userguide/cluster-autoscaler.html