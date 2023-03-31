package personal.project.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.project.domain.entity.Member;
import personal.project.domain.entity.Project;
import personal.project.web.repository.MemberRepository;
import personal.project.web.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    /**모든 프로젝트 조회*/
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    /**프로젝트 조회*/
    public Optional<Project> findProject(Long id) {
        return projectRepository.findById(id);
    }

    /**프로젝트 저장*/
    public void save(Project project) {
        projectRepository.save(project);
    }
}



