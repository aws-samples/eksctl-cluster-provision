# AWS Auth Nodes
{% raw %}
Quando você cria um cluster do Amazon EKS, o usuário ou a função da entidade do IAM, como um usuário federado que cria o cluster, recebe automaticamente sistema: permissões mestres na configuração do RBAC do cluster. Para conceder a usuários ou funções adicionais da AWS a capacidade de interagir com seu cluster, edite o ConfigMap do aws-auth no Kubernetes.

Para conseguir o YAML execute o seguinte comando:

```shell
kubectl describe configmap -n kube-system aws-auth
```

Ele retornará um YAML onde você conseguira realizar a adição de usuários e roles para administrar o cluster

Exemplo:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: aws-auth
  namespace: kube-system
data:
  mapRoles: |
    - rolearn: <ARN of instance role (not instance profile)>
      username: system:node:{{EC2PrivateDNSName}}
      groups:
        - system:bootstrappers
        - system:nodes
  mapUsers: |
    - userarn: IAM_USER_ARN
      username: MY_USER
      groups:
        - system:masters

```
{% endraw %}