package study.nosql.mongodb.business.web.category.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.nosql.mongodb.business.domain.category.entity.ConcreteCategory;
import study.nosql.mongodb.business.domain.category.infrastructure.query.CategoryQueryDSLQueryRepository;
import study.nosql.mongodb.business.domain.common.exception.BusinessException;

import static study.nosql.mongodb.business.domain.common.exception.domain.category.CategoryExceptionType.CATEGORY_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class CategoryQueryService {

    private final CategoryQueryDSLQueryRepository categoryQueryDSLQueryRepository;

    @Transactional(readOnly = true)
    public ConcreteCategory findConcreteCategoryByPostId(Long concreteCategoryId) {
        return categoryQueryDSLQueryRepository.findConcreteCategoryByPostId(concreteCategoryId)
                .orElseThrow(()-> BusinessException.of(CATEGORY_NOT_FOUND_EXCEPTION));
    }
}
