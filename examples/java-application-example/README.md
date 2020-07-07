# Java Application Example

## Prerequisites

- [ECR repository created in the account](https://docs.aws.amazon.com/pt_br/AmazonECR/latest/userguide/repository-create.html) with the name java-application-example.
- Bucket that will be used to list objects by our application.
- Role with permission to access the S3 bucket, as we are using kube2iam it is necessary to change the Trust Relationship, consult (https://github.com/jtblin/kube2iam)

## Deploying

### Building and sending the image to the ECR

- Login to the ECR repository (ECR login command needs aws cli v2)

```shell
aws ecr get-login-password --region REGION | docker login --username AWS --password-stdin ACCOUNT_ID.dkr.ecr.REGION.amazonaws.com
```

- At the root of this folder, execute the following command to build the container.

```shell
docker build -t java-application-example .
```

- Tagging the image built locally to prepare to send it to the repository

```shell
docker tag java-application-example:latest ACCOUNT_ID.dkr.ecr.REGION.amazonaws.com/java-application-example:latest
```

- Push the image to the ECR repository

```shell
docker push ACCOUNT_ID.dkr.ecr.REGION.amazonaws.com/java-application-example:latest
```

### Changing manifests to deploy the application on EKS

**kubernetes/01-configmap.yaml**

```yaml
BUCKET_NAME: Your bucket to use on PoC
REGION_NAME: Region where your bucket is
```

**kubernetes/02-deployment.yaml**

```yaml
  metadata:
      annotations:
        iam.amazonaws.com/role: <Role with permission to access S3>
  ...
  - image: <URL of the image we upload to the ECR>
```

### Applying manifests

- Before applying it is necessary to create the namespace for our application, in this demonstration we are using the namespace prd, so execute the following command:

```shell
kubectl create namespace prd
```

- Then after everything is changed and configured, execute the following command

```shell
kubectl apply -f kubernetes/
```

- So your application will be provisioned within the cluster in the PRD namespace.

### Testing the application

- We need the endpoint of the public load balancer provided by Kubernetes, to do this run the following command

```shell
kubectl get svc -nprd | awk '{print $4}' | grep -vi external
```

- Access the endpoint result of the above command in path **/api/listarObjetos/**

- It will display the list of objects present in the Bucket using the permissions of the role created before
