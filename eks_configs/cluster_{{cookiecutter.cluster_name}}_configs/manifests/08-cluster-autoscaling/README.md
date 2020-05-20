# Cluster AutoScaler

- Para que o Cluster Autoscaler funcione de maneira correta é necessário adicionar um policy na role criada pelo eksctl que permita com que o AutoScaler escale as máquinas baseado nas requisições dos deployments. (https://github.com/jtblin/kube2iam)

Substituir no YAML 

```yaml
annotations:
    iam.amazonaws.com/role: arn:aws:iam::00000:role/eksctl-{{cookiecutter.cluster_name}}-nodegroup # Substituir com a sua role se estiver usando o Kube2iam
```

Pela role criada pelo eksctl conforme descrito acima