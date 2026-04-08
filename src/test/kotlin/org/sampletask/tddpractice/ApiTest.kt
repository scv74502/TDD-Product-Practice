package org.sampletask.tddpractice

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class ApiTest {
    @Autowired
    protected lateinit var databaseCleanup: DatabaseCleanup

    @LocalServerPort
    private val port: Int = 0

    @BeforeEach
    fun setUp() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port
            databaseCleanup.afterPropertiesSet()
        }

        databaseCleanup.execute()
    }
}
