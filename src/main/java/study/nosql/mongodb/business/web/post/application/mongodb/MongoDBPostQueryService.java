package study.nosql.mongodb.business.web.post.application.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.nosql.mongodb.common.documents.post.document.PostDocument;
import study.nosql.mongodb.common.documents.post.infrastructure.command.PostDocumentRepository;
import study.nosql.mongodb.business.domain.common.field.Deleted;

@Service
@RequiredArgsConstructor
public class MongoDBPostQueryService {

    private final PostDocumentRepository mongoTemplate;

    @Transactional(readOnly = true)
    public PostDocument findPostDocumentByPostId(Long postId) {
        return mongoTemplate.findPostDocumentByPostId(
                        postId,
                        Deleted.FALSE
                )
                .orElseGet(() -> null);
    }
}
