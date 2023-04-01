package personal.project.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.domain.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
