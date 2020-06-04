from aws_cdk import core
import aws_cdk.aws_ec2 as ec2


# Infraestructure definition using CDK

class VpcStack(core.Stack):

    def __init__(self, scope: core.Construct, id: str, **kwargs):
        super().__init__(scope, id, **kwargs)
        self.vpc_info = None
        self.create_vpc()


    def create_vpc(self):
        # Uses self here that car re-use Later
        self.vpc_info = ec2.Vpc(self, "VPC",
            max_azs=2,
            cidr="10.10.0.0/16",
            # configuration will create 3 groups in 2 AZs = 6 subnets.
            subnet_configuration=[ec2.SubnetConfiguration(
                subnet_type=ec2.SubnetType.PUBLIC,
                name="Public",
                cidr_mask=24
            ), ec2.SubnetConfiguration(
                subnet_type=ec2.SubnetType.PRIVATE,
                name="Private",
                cidr_mask=24
            )
            ],
            # nat_gateway_provider=ec2.NatProvider.gateway(),
            nat_gateways=2,
        )
        
        private_subnets = self.vpc_info.select_subnets(
            subnet_type=ec2.SubnetType.PRIVATE
        )

        public_subnets = self.vpc_info.select_subnets(
            subnet_type=ec2.SubnetType.PUBLIC
        )
    
        core.CfnOutput(self, "Region", value="us-east-1")
        core.CfnOutput(self, "VpcID", value=self.vpc_info.vpc_id)
        core.CfnOutput(self, "VpcCidr", value="10.10.0.0/16")
        core.CfnOutput(self, "PrivateSubnetsIds", value=str(private_subnets.subnet_ids))
        core.CfnOutput(self, "PublicSubnetsIds", value=str(public_subnets.subnet_ids))
        core.CfnOutput(self, "AvailabilityZones", value=str(public_subnets.availability_zones))

        

        

