package study.nosql.mongodb.business.web.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.nosql.mongodb.business.domain.post.entity.Post;
import study.nosql.mongodb.business.domain.post.infrastructure.command.PostJpaRepository;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostJpaRepository postJpaRepository;

    public Post findPostById(Long postId) {
        return postJpaRepository.findById(postId).orElseThrow();
    }
}
