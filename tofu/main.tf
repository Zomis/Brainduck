# TODO: https://kulbhushanparashar.medium.com/efficiently-sync-local-files-to-aws-s3-bucket-with-terraform-9bcc67d4aa34
locals {
	region = "eu-central-1"
}

terraform {
	backend "s3" {
		region = local.region
	}
}

resource "aws_s3_bucket" "frontend" {
  bucket = "brainduck"

  tags = {
    project = "Brainduck"
  }
}

data "aws_caller_identity" "current" {}
data "aws_partition" "current" {}
data "aws_region" "current" {}
