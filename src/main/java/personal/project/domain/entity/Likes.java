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
public class Likes {

    @Id @GeneratedValue
    @Column(name = "LIKES_ID")
    private Long id;
    private Long memId;
    private Long proId;

    public Likes(Long memberId, Long projectId) {
        this.memId = memberId;
        this.proId = projectId;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    public void addMember(Member member) {
        this.member = member;
        member.getLikes().add(this);
    }

    public void addProject(Project project) {
        this.project = project;
        project.setLikeCount(project.getLikeCount()+1);
        project.getLikes().add(this);
    }

    public void deleteMember(Member member) {
        this.member = null;
        this.memId = null;
        member.getLikes().remove(this);
    }

    public void deleteProject(Project project) {
        this.project = null;
        this.proId = null;
        project.setLikeCount(project.getLikeCount()-1);
        project.getLikes().remove(this);
    }
}
