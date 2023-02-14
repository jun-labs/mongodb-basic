package study.nosql.mongodb.business.web.post.application.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.nosql.mongodb.business.common.mongodb.MongoDBCommandService;
import study.nosql.mongodb.business.domain.post.entity.Post;
import study.nosql.mongodb.common.documents.post.document.PostDocument;

@Service
@RequiredArgsConstructor
public class MongoDBPostCommandService implements MongoDBCommandService<PostDocument, Post> {

    private final MongoTemplate mongoTemplate;
    private final MongoDBPostQueryService mongoDBPostQueryService;

    @Override
    @Transactional
    public PostDocument save(Long postId, PostDocument postDocument) {
        mongoTemplate.save(postDocument);
        return postDocument;
    }

    @Override
    public void update(Long aggregationId, Post post) {
        PostDocument findPostDocument = mongoDBPostQueryService.findPostDocumentByPostId(aggregationId);
        findPostDocument.updatePost(post);
        mongoTemplate.save(findPostDocument);
    }

    @Override
    public void delete(Long aggregationId, Post post) {

    }
}
