## Create Amazon EKS Cluster Step by Step

- Create Amazon EKS service role in IAM console (https://docs.aws.amazon.com/eks/latest/userguide/getting-started-console.html#role-create)
- Create your Kube Config, running the follow command - aws eks --region YOUR_REGION update-kubeconfig --name YOUR_CLUSTER_NAME
- Test your configuration (kubectl get svc)
- Create a IAM Role for your managed workers nodes in AWS (Choose Ec2 annd attach the following policies  AmazonEKSWorkerNodePolicy,  AmazonEKS_CNI_Policy,  AmazonEC2ContainerRegistryReadOnly) Name - NodeInstanceRole (https://docs.aws.amazon.com/eks/latest/userguide/worker_node_IAM_role.html)
- Criar Managed node group