## Create Amazon EKS Cluster Step by Step

- Create Amazon EKS service role in IAM console (https://docs.aws.amazon.com/eks/latest/userguide/getting-started-console.html#role-create)

- Create a IAM Role for your managed workers nodes in AWS (https://docs.aws.amazon.com/eks/latest/userguide/worker_node_IAM_role.html)

- Criar o cluster de EKS rodando o seguinte comando 
```shell
eksctl create cluster -f cluster.yaml
```
- Realizando o update de sua Kubeconfig localmente - aws eks --region YOUR_REGION update-kubeconfig --name YOUR_CLUSTER_NAME

- Aplicar os manifestos dos componentes adicionais do cluster de Kubernets
```shell
kubectl apply -f manifests/
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