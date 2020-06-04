from aws_cdk import core
import aws_cdk.aws_iam as iam


# Infraestructure definition using CDK

class IamStack(core.Stack):

    def __init__(self, scope: core.Construct, id: str, **kwargs):
        super().__init__(scope, id, **kwargs)
        self.create_iam_roles()


    def create_iam_roles(self):
        # Creating Amazon EKS Cluster Role
        managed_policy_name = "AmazonEKSClusterPolicy"

        iam.Role(self, "eksClusterRole", assumed_by=iam.ServicePrincipal("eks.amazonaws.com"), 
            managed_policies=[managed_policy_name])
        # Uses self here that car re-use Later

        # core.CfnOutput(self, "Eks", value="us-east-1")
        

        

        

