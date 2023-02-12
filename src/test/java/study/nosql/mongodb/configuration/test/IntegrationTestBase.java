package study.nosql.mongodb.configuration.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import study.nosql.mongodb.configuration.database.DatabaseInitialization;

import java.util.Set;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    @Autowired
    private DatabaseInitialization databaseInitialization;

    @Autowired
    private MongoTemplate mongoTemplate;

    @AfterEach
    void afterEach() {
        databaseInitialization.truncateAllEntity();
        Set<String> collections = mongoTemplate.getCollectionNames();
        for (String collection : collections) {
            log.debug("Remove all documents from [{}] collection.", collection);
            mongoTemplate.dropCollection(collection);
        }
    }
}
