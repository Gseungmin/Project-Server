package personal.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;
    private String content;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    public Comment(String content) {
        this.content = content;
    }

    public void addMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }

    public void addProject(Project project) {
        this.project = project;
        project.getComments().add(this);
    }
}
