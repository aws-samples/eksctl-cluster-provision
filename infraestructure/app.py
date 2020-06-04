#!/usr/bin/env python3

from aws_cdk import core

from infraestructure.vpc_stack import VpcStack
from infraestructure.iam_stack import IamStack


# Constants, default region
region_name = "us-east-1"
env_US = core.Environment(region=region_name)
app = core.App()

vpc_stack = VpcStack(app, "vpc")
iam_stack = IamStack(app, "iam-stack")

app.synth()
