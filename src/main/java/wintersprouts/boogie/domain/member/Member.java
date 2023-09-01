package wintersprouts.boogie.domain.member;


import lombok.*;
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

    @Column(name = "MEMBER_ACCOUNT", nullable = false)
    private Long account;

    @OneToOne(mappedBy = "donationPublisher")
    private Donation donation;

    /**
     * 내가 기부한 기부글 목록
     */
    @OneToMany(mappedBy = "member")
    private List<Donator> donated = new ArrayList<>();


    @Builder
    public Member(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
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
