package personal.project.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.project.domain.entity.Comment;
import personal.project.domain.entity.Project;
import personal.project.web.repository.CommentRepository;
import personal.project.web.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    /**프로젝트의 모든 댓글 조회*/
    public List<Comment> findAll(Long projectId) {

        List<Comment> all = commentRepository.findAll();
        List<Comment> collect = all.stream().
                filter(comment -> comment.getProject().getId().equals(projectId)).collect(Collectors.toList());
        return collect;
    }

    /**댓글 저장*/
    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}



