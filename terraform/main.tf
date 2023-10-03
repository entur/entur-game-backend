# Terraform configuration for agrmbe
module "init" {
  source      = "github.com/entur/terraform-google-init//modules/init?ref=v0.3.0"
  app_id      = "agrmbe"
  environment = var.env
}

# https://github.com/entur/terraform-google-sql-db/tree/master/modules/postgresql#inputs
module "postgres" {
  source           = "github.com/entur/terraform-google-sql-db//modules/postgresql?ref=v1.7.1"
  init             = module.init
  generation       = var.generation
  database_version = "POSTGRES_14"
  databases        = [var.db_name]
}
