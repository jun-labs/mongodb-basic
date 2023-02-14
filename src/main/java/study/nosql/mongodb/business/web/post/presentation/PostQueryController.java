package study.nosql.mongodb.business.web.post.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.nosql.mongodb.business.web.post.presentation.dto.response.PostResponse;
import study.nosql.mongodb.business.web.post.facade.PostQueryFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/query/posts")
public class PostQueryController {

    private final PostQueryFacade postQueryFacade;

    @GetMapping("{postId}")
    public ResponseEntity<PostResponse> findPostById(@PathVariable Long postId) {
        PostResponse response = postQueryFacade.findPostById(postId);
        return ResponseEntity.ok(response);
    }
}
