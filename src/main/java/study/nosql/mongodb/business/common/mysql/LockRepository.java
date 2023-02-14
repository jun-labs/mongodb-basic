package study.nosql.mongodb.business.common.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.nosql.mongodb.business.domain.post.entity.Post;

public interface LockRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT get_lock(:postId, 1)", nativeQuery = true)
    void getLockByPostId(@Param("postId") String postId);

    @Query(value = "SELECT release_lock(:postId)", nativeQuery = true)
    void releaseLockByPostId(@Param("postId") String postId);
}
