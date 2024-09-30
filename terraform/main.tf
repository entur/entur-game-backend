# Terraform configuration for agrmbe
module "init" {
  source      = "github.com/entur/terraform-google-init//modules/init?ref=v1.0.0"
  app_id      = "gamebckend"
  environment = var.env
}

# https://github.com/entur/terraform-google-sql-db/tree/master/modules/postgresql#inputs
module "postgres" {
  source           = "github.com/entur/terraform-google-sql-db//modules/postgresql?ref=v1.7.5"
  init             = module.init
  generation       = var.generation
  database_version = "POSTGRES_14"
  databases        = [var.db_name]
}
