package personal.project.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.domain.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
