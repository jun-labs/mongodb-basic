package study.nosql.mongodb.configuration.database;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class MongoDBInitializationConfiguration {

    private Set<String> collections = new HashSet<>();

    @Autowired
    private MongoTemplate mongoTemplate;

    void extractCollections() {
        this.collections = new HashSet<>();
        collections.addAll(mongoTemplate.getCollectionNames());
    }

    @BeforeEach
    void beforeEach() {
        extractCollections();
        for (String collection : collections) {
            log.debug("Remove all documents from [{}] collection.", collection);
            mongoTemplate.dropCollection(collection);
        }
    }

    public void clearCollections() {
        beforeEach();
    }
}
