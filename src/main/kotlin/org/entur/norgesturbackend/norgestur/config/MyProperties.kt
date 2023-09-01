package org.entur.norgesturbackend.norgestur.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "spring")
class MyProperties {
    var secret: String? = null
    var datasource: DataSourceProperties? = null
}

class DataSourceProperties {
    var url: String? = null
    var username: String? = null
    var password: String? = null
}