package personal.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String nickname;
    private String email;
    private String password;
    private String Type;
    private String profileImage;
    private String introduction;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Project> projects = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Likes> likes = new ArrayList<>();

    public Member(String nickname, String email, String password, String type) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.Type = type;
    }

    public Member(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.Type = type;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
