package personal.project.domain.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import personal.project.domain.dto.OpenGraphDto;

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

    //opengraph
    private String linkTitle;
    private String linkUrl;
    private String linkImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
