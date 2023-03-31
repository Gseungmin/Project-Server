package personal.project.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import personal.project.domain.dto.ReturnProjectDto;
import personal.project.domain.dto.ReturnProjectDtos;
import personal.project.domain.dto.UploadDto;
import personal.project.domain.entity.Member;
import personal.project.domain.entity.Project;
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

    /**프로젝트 업로드*/
    @PostMapping("/upload/project")
    public ResponseEntity upload(@RequestBody UploadDto uploadDto, Authentication authentication) {

        Optional<Member> findMember = memberService.findMember(authentication.getName());
        Member member = findMember.get();

        Project project = new Project(uploadDto);
        project.addMember(member);
        projectService.save(project);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ReturnProjectDtos search(@RequestParam(value = "query") String query) {
        List<Project> all = projectService.findAll();
        List<ReturnProjectDto> collect = all.stream().map(project -> new ReturnProjectDto(project)).collect(Collectors.toList());
        ReturnProjectDtos returnProjectDtos = new ReturnProjectDtos(collect);
        return returnProjectDtos;
    }
}



