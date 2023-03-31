package personal.project.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import personal.project.domain.dto.UploadDto;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    /**프로젝트 업로드*/
    @PostMapping("/upload/project")
    public ResponseEntity upload(@RequestBody UploadDto uploadDto) {
        return ResponseEntity.ok().build();
    }
}



