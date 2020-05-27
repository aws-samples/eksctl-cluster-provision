## Create Amazon EKS Cluster Step by Step

- A role dos Workers nodes vai ser criada pelo eksctl, porém a criação também é possível via console (https://docs.aws.amazon.com/eks/latest/userguide/worker_node_IAM_role.html)

- Criar o cluster de EKS rodando o seguinte comando 
```shell
eksctl create cluster -f cluster-template.yaml
```
- Realizando o update de sua Kubeconfig localmente - aws eks --region YOUR_REGION update-kubeconfig --name YOUR_CLUSTER_NAME

- Para que o Cluster Autoscaler funcione de maneira correta é necessário adicionar um policy na role criada pelo eksctl que permita com que o AutoScaler escale as máquinas baseado nas requisições dos deployments.(https://aws.amazon.com/premiumsupport/knowledge-center/eks-cluster-autoscaler-setup/)

- Ir até a pasta manifests/00-aws-auth-nodes e seguir as instruções do README.

- Ir até a pasta manifests/08-cluster-autoscaling seguir as intruções do README e ajustar a ROLE.

- Aplicar os manifestos dos componentes adicionais do cluster de Kubernetes (Os outros manifestos podem ser aplicados para utilização de componentes adicionais se preferir)
```shell
kubectl apply -f manifests/02-kube2iam
kubectl apply -f manifests/04-metric-server
kubectl apply -f manifests/08-cluster-autoscaling
```

- Adicionar as seguintes Tags nas suas subnets públicas e privadas para poder provisionar services (LoadBalancers) externos e internos.
```
Private Subnets - kubernetes.io/role/internal-elb: 1
Public Subnets - kubernetes.io/role/elb: 1
```

## Realizar update do cluster após alterar alguma configuração

- Dry Run
```shell
eksctl update cluster --config-file=config.yaml
```

- Aplicar
```shell
eksctl update cluster --config-file=cluster.yaml --approve
```

## Referências

LoadBalancer Service Annotations - https://gist.github.com/mgoodness/1a2926f3b02d8e8149c224d25cc57dc1