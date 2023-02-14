package study.nosql.mongodb.business.common.mongodb;

public interface MongoDBCommandService<T, E> {

    T save(Long aggregationId, T t);

    void update(Long aggregationId, E t);

    void delete(Long aggregationId, E t);
}
