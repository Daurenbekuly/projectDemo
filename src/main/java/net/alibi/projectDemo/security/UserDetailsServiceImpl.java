package net.alibi.projectDemo.security;

import lombok.Data;
import net.alibi.projectDemo.model.User;
import net.alibi.projectDemo.repository.ScholarRepository;
import net.alibi.projectDemo.repository.TeacherRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Data
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final TeacherRepository teacherRepository;
    private final ScholarRepository scholarRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = teacherRepository.findByEmail(email)
                .orElseGet(() -> scholarRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist")));
        return SecurityUser.fromUser(user);
    }
}
