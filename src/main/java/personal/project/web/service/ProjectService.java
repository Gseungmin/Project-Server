package personal.project.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.project.domain.dto.SearchCondition;
import personal.project.domain.dto.get.ReturnProjectDto;
import personal.project.domain.entity.Member;
import personal.project.domain.entity.Project;
import personal.project.web.repository.MemberRepository;
import personal.project.web.repository.ProjectRepository;
import personal.project.web.repository.SearchRepository;
import personal.project.web.repository.SearchRepositoryImpl;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final SearchRepository searchRepository;

    /**모든 프로젝트 조회*/
    public List<ReturnProjectDto> findAll(String query, Integer sort) {
        if (query == "") {
            query = null;
        }
        if (sort == 0) {
            sort = 0;
        }
        return searchRepository.searchByCondition(new SearchCondition(query, sort));
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



