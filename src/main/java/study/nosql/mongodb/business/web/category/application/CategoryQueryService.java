package study.nosql.mongodb.business.web.category.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.nosql.mongodb.business.domain.category.entity.ConcreteCategory;
import study.nosql.mongodb.business.domain.category.infrastructure.query.CategoryQueryDSLQueryRepository;

@Service
@RequiredArgsConstructor
public class CategoryQueryService {

    private final CategoryQueryDSLQueryRepository categoryQueryDSLQueryRepository;

    @Transactional(readOnly = true)
    public ConcreteCategory findConcreteCategoryByPostId(Long concreteCategoryId) {
        return categoryQueryDSLQueryRepository.findConcreteCategoryByPostId(concreteCategoryId).orElseThrow();
    }
}
