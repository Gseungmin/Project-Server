package personal.project.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.domain.entity.Likes;
import personal.project.domain.entity.Project;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByMemIdAndProId(Long memberId, Long projectId);
}
