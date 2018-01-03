package com.iqmsoft.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import javax.sql.DataSource


@RestController
class DbStatusController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/")
    DBInfo showDbStatus() {
        def connection = dataSource.connection
        try {
            def metaData = connection.metaData
            def dbInfo = new DBInfo(url: metaData.getURL(), productName: metaData.databaseProductName, productVersion: metaData.databaseProductVersion)
            return dbInfo;
        }
        finally {
            connection.close()
        }
    }
}
