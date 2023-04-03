package personal.project.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import personal.project.domain.dto.get.*;
import personal.project.domain.dto.post.CommentDto;
import personal.project.domain.dto.post.UploadDto;
import personal.project.domain.entity.Comment;
import personal.project.domain.entity.Likes;
import personal.project.domain.entity.Member;
import personal.project.domain.entity.Project;
import personal.project.web.service.CommentService;
import personal.project.web.service.LikeService;
import personal.project.web.service.MemberService;
import personal.project.web.service.ProjectService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final MemberService memberService;
    private final ProjectService projectService;
    private final CommentService commentService;
    private final LikeService likeService;

    //프로젝트 업로드
    @PostMapping("/upload/project")
    public ResponseEntity upload(@RequestBody UploadDto uploadDto, Authentication authentication) {

        Optional<Member> findMember = memberService.findMember(authentication.getName());
        Member member = findMember.get();

        Project project = new Project(uploadDto);
        project.addMember(member);
        projectService.save(project);

        return ResponseEntity.ok().build();
    }

    //프로젝트 목록 조회
    @GetMapping("/search")
    public ReturnProjectDtos search(@RequestParam(value = "query") String query,
                                    @RequestParam(value = "sort") Integer sort) {

        List<ReturnProjectDto> all = projectService.findAll(query, sort);
        ReturnProjectDtos returnProjectDtos = new ReturnProjectDtos(all);
        return returnProjectDtos;
    }

    //프로젝트 조회
    @GetMapping("/project/{projectId}")
    public ReturnProjectDetailDto getProject(@PathVariable("projectId") Long projectId, Authentication authentication) {

        Optional<Project> findProject = projectService.findProject(projectId);
        Project project = findProject.get();

        Optional<Member> findAuthMember = memberService.findMember(authentication.getName());
        Member authMember = findAuthMember.get(); //토큰 멤버 정보
        Optional<Member> findWriterMember = memberService.findMember(project.getMember().getEmail());
        Member writerMember = findWriterMember.get(); //작성자 멤버 정보

        //반환 객체 생성
        ReturnProjectDetailDto projectDto = new ReturnProjectDetailDto(project);

        //좋아요 체크
        Optional<Likes> likes = likeService.findLikes(authMember.getId(), projectId);
        if (likes.isPresent()) { //좋아요가 있는 경우
            projectDto.setIsLiked(true);
        } else {
            projectDto.setIsLiked(false);
        }

        //토큰 멤버와 작성자가 같은 경우
        if (authMember.getId().equals(writerMember.getId())) {
            projectDto.setIsMy(true); //true 리턴
        }
        return projectDto;
    }

    //댓글 업로드
    @PostMapping("/upload/comment")
    public ResponseEntity uploadComment(@RequestBody CommentDto commentDto, Authentication authentication) {

        Optional<Member> findMember = memberService.findMember(authentication.getName());
        Member member = findMember.get();
        Optional<Project> findProject = projectService.findProject(commentDto.getProjectId());
        Project project = findProject.get();

        Comment comment = new Comment(commentDto.getContent());

        //양방향 연관관계 설정
        comment.addProject(project);
        comment.addMember(member);
        project.setCommentCount(project.getCommentCount()+1);

        commentService.save(comment);

        return ResponseEntity.ok().build();
    }

    //프로젝트 댓글 목록 조회
    @GetMapping("/comments/{projectId}")
    public ReturnCommentDtos getComments(@PathVariable("projectId") Long projectId) {
        List<Comment> all = commentService.findAll(projectId);
        List<ReturnCommentDto> collect = all.stream().map(comment -> new ReturnCommentDto(comment)).collect(Collectors.toList());
        ReturnCommentDtos returnCommentDtos = new ReturnCommentDtos(collect);
        return returnCommentDtos;
    }

    //댓글 업로드
    @PostMapping("/likes/{projectId}")
    public ReturnLikeDto likes(@PathVariable("projectId") Long projectId, Authentication authentication) {

        Optional<Member> findMember = memberService.findMember(authentication.getName());
        Member member = findMember.get();
        Optional<Project> findProject = projectService.findProject(projectId);
        Project project = findProject.get();

        Optional<Likes> likes = likeService.findLikes(member.getId(), projectId);

        if (likes.isPresent()) { //좋아요가 있는 경우
            likes.get().deleteMember(member); //연관관계 제거
            likes.get().deleteProject(project); //연관관계 제거
            likeService.save(likes.get());
            return new ReturnLikeDto(false);
        }

        Likes like = new Likes(member.getId(), projectId);
        like.addMember(member);
        like.addProject(project);
        likeService.save(like);
        return new ReturnLikeDto(true);
    }
}



