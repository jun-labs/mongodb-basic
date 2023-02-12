package study.nosql.mongodb.configuration.annotation;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static study.nosql.mongodb.configuration.annotation.InsertData.CATEGORY_SQL_SCRIPT_PATH;
import static study.nosql.mongodb.configuration.annotation.InsertData.MEMBER_SQL_SCRIPT_PATH;
import static study.nosql.mongodb.configuration.annotation.InsertData.POST_SQL_SCRIPT_PATH;
import static study.nosql.mongodb.configuration.annotation.InsertData.REVIEW_SQL_SCRIPT_PATH;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = MEMBER_SQL_SCRIPT_PATH),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = CATEGORY_SQL_SCRIPT_PATH),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = POST_SQL_SCRIPT_PATH),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = REVIEW_SQL_SCRIPT_PATH)
})
public @interface InsertData {
    String MEMBER_SQL_SCRIPT_PATH = "classpath:/sql/member.sql";
    String POST_SQL_SCRIPT_PATH = "classpath:/sql/post.sql";
    String CATEGORY_SQL_SCRIPT_PATH = "classpath:/sql/category.sql";
    String REVIEW_SQL_SCRIPT_PATH = "classpath:/sql/review.sql";
}
