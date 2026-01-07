package com.zyad.platform.jobportal.controller;

import com.zyad.platform.jobportal.entity.RecruiterProfile;
import com.zyad.platform.jobportal.entity.Users;
import com.zyad.platform.jobportal.repository.UsersRepository;
import com.zyad.platform.jobportal.services.RecruiterProfileService;
import com.zyad.platform.jobportal.util.FileUploadUtil;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;


import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {

    private final UsersRepository usersRepository;
    private final RecruiterProfileService recruiterProfileService;

    public RecruiterProfileController(UsersRepository usersRepository, RecruiterProfileService recruiterProfileService) {
        this.usersRepository = usersRepository;
        this.recruiterProfileService = recruiterProfileService;
    }

    @GetMapping("/")
    public String recruiterProfile(Model model){
        // SecurityContext is what holds the security info, SecurityContextHolder is how we retrieve it.
        // SecurityContextHolder contains the security context for the CURRENT request, which is the CURRENTLY logged in user. has no user data inside it.
        //                                                   .getContext -> gives you the data container itself
        //                                                                .getAuthentication -> gives all info about the current user.
        //             authentication now stores a reference to the authentication object (user details)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // only run the profile-loading logic if the user is actually logged in. makes sure if the "/recruiter-profile" endpoint is hit without a logged in user it doesnt work
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();

            // find current logged in user by email and store all the users info in users variable, if email not found then throw an exception.
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not find user."));
            Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getOne(users.getUserId());

            if (!recruiterProfile.isEmpty()){
                model.addAttribute("profile", recruiterProfile.get());
            }
        }
        return "recruiter_profile";
    }

    @PostMapping("/addNew")
    public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image")MultipartFile multipartFile, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not find user."));
            recruiterProfile.setUserId(users);
            recruiterProfile.setUserAccountId(users.getUserId());
        }
        // model.addAttribute -> adds the recruiterProfile object to the Spring MVC model. makes it available to the view under the name "profile".
        model.addAttribute("profile", recruiterProfile);
        String fileName = "";
        // only run the image-handling code if the user selected a file.
        if (!multipartFile.getOriginalFilename().equals("")){
            fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            recruiterProfile.setProfilePhoto(fileName);
        }
        RecruiterProfile savedUser = recruiterProfileService.addNew(recruiterProfile);
        String uploadDir = "photos/recruiter/" + savedUser.getUserAccountId();
        try {
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dashboard/";

    }
}
