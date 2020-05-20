## Create Amazon EKS Cluster Step by Step

- Criar Amazon EKS service role no console do IAM (https://docs.aws.amazon.com/eks/latest/userguide/getting-started-console.html#role-create)

- A role dos Workers nodes vai ser criada pelo eksctl, porém a criação também é possível via console (https://docs.aws.amazon.com/eks/latest/userguide/worker_node_IAM_role.html)

- Criar o cluster de EKS rodando o seguinte comando 
```shell
eksctl create cluster -f cluster-template.yaml
```
- Realizando o update de sua Kubeconfig localmente - aws eks --region YOUR_REGION update-kubeconfig --name YOUR_CLUSTER_NAME

- Para que o Cluster Autoscaler funcione de maneira correta é necessário adicionar um policy na role criada pelo eksctl que permita com que o AutoScaler escale as máquinas baseado nas requisições dos deployments. (https://github.com/jtblin/kube2iam)

- Aplicar os manifestos dos componentes adicionais do cluster de Kubernets
```shell
kubectl apply -f manifests/
```

- Adicionar as seguintes Tags nas suas subnets públicas e privadas para poder provisionar services externos e internos.
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