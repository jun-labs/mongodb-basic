package study.nosql.mongodb.business.domain.category.infrastructure.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import study.nosql.mongodb.business.domain.category.entity.ConcreteCategory;

import java.util.Optional;

import static study.nosql.mongodb.domain.category.entity.QCategoryGroup.categoryGroup;
import static study.nosql.mongodb.domain.category.entity.QConcreteCategory.concreteCategory;
import static study.nosql.mongodb.domain.category.entity.QSubCategory.subCategory;

@Repository
public class CategoryQueryDSLQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CategoryQueryDSLQueryRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<ConcreteCategory> findConcreteCategoryByPostId(Long concreteCategoryId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(concreteCategory)
                        .join(concreteCategory.subCategory, subCategory)
                        .fetchJoin()
                        .join(subCategory.categoryGroup, categoryGroup)
                        .fetchJoin()
                        .where(concreteCategory.concreteCategoryId.eq(concreteCategoryId))
                        .fetchOne()
        );
    }
}
