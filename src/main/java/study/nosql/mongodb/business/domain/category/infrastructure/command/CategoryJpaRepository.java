package study.nosql.mongodb.business.domain.category.infrastructure.command;

import org.springframework.data.jpa.repository.JpaRepository;
import study.nosql.mongodb.business.domain.category.entity.CategoryGroup;

public interface CategoryJpaRepository extends JpaRepository<CategoryGroup, Long> {
}
