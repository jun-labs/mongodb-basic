package study.nosql.mongodb.configuration.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RDBInitializationConfiguration {

    private static final String SET_FOREIGN_KEY_CHECKS_FALSE = "SET FOREIGN_KEY_CHECKS = 0";
    private static final String SET_FOREIGN_KEY_CHECKS_TRUE = "SET FOREIGN_KEY_CHECKS = 1";

    private final EntityManager entityManager;
    private Set<String> tableNames;

    @PostConstruct
    public void afterPropertiesSet() {
        Metamodel metamodel = entityManager.getMetamodel();
        tableNames = metamodel.getEntities().stream()
                .filter(isEntityTypeAndNotNull())
                .map(changeToLowerCase())
                .collect(Collectors.toUnmodifiableSet());
    }

    private Predicate<EntityType<?>> isEntityTypeAndNotNull() {
        return entityType -> entityType.getJavaType().getAnnotation(Entity.class) != null;
    }

    private Function<EntityType<?>, String> changeToLowerCase() {
        return entityType -> entityType.getName().toUpperCase();
    }

    @Transactional
    public void truncateAllEntity() {
        entityManager.flush();
        entityManager.clear();

        entityManager.createNativeQuery(SET_FOREIGN_KEY_CHECKS_FALSE).executeUpdate();

        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }

        entityManager.createNativeQuery(SET_FOREIGN_KEY_CHECKS_TRUE).executeUpdate();
    }
}
