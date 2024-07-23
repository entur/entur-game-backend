variable "env" {
  description = "Environment. dev|tst|prd."
}

variable "db_name" {
  description = "Database name"
}

variable "generation" {
  description = "Infra generation"
  default     = 1
}
