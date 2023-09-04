package wintersprouts.boogie.domain.member;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donator.Donator;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER")
@ToString
@DynamicInsert //insert 시 null인 필드 제외
public class Member implements UserDetails {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "MEMBER_EMAIL", updatable = false, unique = true, nullable = false)
    private String email;

    @Column(name = "MEMBER_PW", nullable = false)
    private String password;

    @Column(name = "MEMBER_NAME", nullable = false)
    private String name;

    @Column(name = "MEMBER_NICKNAME", unique = true)
    private String nickname;

    @Column(name = "MEMBER_ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Setter
    @Column(name = "MEMBER_ACCOUNT", nullable = false)
    @ColumnDefault("0") //@DynamicInsert 덕분에 insert 시 null인 필드 제외되고 default 0으로 설정해둬서, 결론적으로 DB에 default인 0이 들어가게 됨
    private Long account;

    @OneToMany(mappedBy = "donationPublisher")
    private List<Donation> donation = new ArrayList<>();

    /**
     * 내가 기부한 기부글 목록
     */
    @OneToMany(mappedBy = "member")
    private List<Donator> donated = new ArrayList<>();

    @Builder
    public Member(String email, String password, String name, String nickname, Role role, Long account) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.role = role;
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.getValue()));
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
