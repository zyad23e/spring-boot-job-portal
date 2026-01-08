package com.zyad.platform.jobportal.services;

import com.zyad.platform.jobportal.entity.RecruiterProfile;
import com.zyad.platform.jobportal.entity.Users;
import com.zyad.platform.jobportal.repository.RecruiterProfileRepository;
import com.zyad.platform.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository, UsersRepository usersRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.usersRepository = usersRepository;
    }

    // we use Optional because the data might not exist. instead of hoping its not null, we deal with it on purpose.
    public Optional<RecruiterProfile> getOne(Integer id){
        return recruiterProfileRepository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }

    public RecruiterProfile getCurrentRecruiterProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found."));
            Optional<RecruiterProfile> recruiterProfile = getOne(users.getUserId());
            return recruiterProfile.orElse(null);
        } else {
            return null;
        }
    }
}
