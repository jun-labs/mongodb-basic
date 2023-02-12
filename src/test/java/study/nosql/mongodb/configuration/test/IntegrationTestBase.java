package study.nosql.mongodb.configuration.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import study.nosql.mongodb.configuration.database.MongoDBInitializationConfiguration;
import study.nosql.mongodb.configuration.database.RDBInitializationConfiguration;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    @Autowired
    private RDBInitializationConfiguration RDBInitializationConfiguration;

    @Autowired
    private MongoDBInitializationConfiguration mongoDBInitializationConfiguration;

    @AfterEach
    void afterEach() {
        RDBInitializationConfiguration.truncateAllEntity();
        mongoDBInitializationConfiguration.clearCollections();
    }
}
