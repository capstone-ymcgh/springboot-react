package com.capstone.capstone_project.security.jwt;


import com.capstone.capstone_project.common.exception.ResourceNotFoundException;
import com.capstone.capstone_project.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * DaoAuthenticationProvider 구현
 */
@Service
public class CustomMemberDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.memberRepo.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("MemberEntity", "Member Email : ", username));
    }
}