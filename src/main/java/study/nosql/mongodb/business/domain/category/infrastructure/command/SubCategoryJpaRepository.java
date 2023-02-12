package study.nosql.mongodb.business.domain.category.infrastructure.command;

import org.springframework.data.jpa.repository.JpaRepository;
import study.nosql.mongodb.business.domain.category.entity.SubCategory;

public interface SubCategoryJpaRepository extends JpaRepository<SubCategory, Long> {
}
