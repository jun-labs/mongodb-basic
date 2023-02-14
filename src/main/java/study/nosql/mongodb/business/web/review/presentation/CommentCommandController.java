package study.nosql.mongodb.business.web.review.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.business.web.review.facade.ReviewCommandFacade;
import study.nosql.mongodb.business.web.review.presentation.dto.request.ReviewWriteRequest;
import study.nosql.mongodb.business.web.review.presentation.dto.response.ReviewWriteResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class CommentCommandController {

    private final ReviewCommandFacade reviewCommandFacade;

    @PostMapping("/{postId}")
    public ResponseEntity<ReviewWriteResponse> writeReview(@RequestBody ReviewWriteRequest reviewWriteRequest,
                                                           @PathVariable Long postId) {
        Review review = reviewCommandFacade.writeReview(
                reviewWriteRequest.getMemberId(),
                postId,
                reviewWriteRequest.getContent()
        );

        return ResponseEntity.ok(ReviewWriteResponse.of(review));
    }

}
