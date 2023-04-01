package personal.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import personal.project.domain.dto.OpenGraphDto;
import personal.project.domain.dto.UploadDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id @GeneratedValue
    @Column(name = "PROJECT_ID")
    private Long id;

    private Integer category;
    private String content;
    private String title;
    private String image;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;

    //opengraph
    private String linkTitle;
    private String linkUrl;
    private String linkImage;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Project(UploadDto uploadDto) {
        this.category = uploadDto.getCategory();
        this.content = uploadDto.getContent();
        this.title = uploadDto.getTitle();
        this.image = uploadDto.getImage();
        this.linkTitle = uploadDto.getLinkTitle();
        this.linkUrl = uploadDto.getLinkUrl();
        this.linkImage = uploadDto.getLinkImage();
        this.likeCount = 0;
        this.commentCount = 0;
        this.viewCount = 0;
    }

    //project member 양방향 연관관계
    public void addMember(Member member) {
        this.member = member;
        member.getProjects().add(this);
    }
}
