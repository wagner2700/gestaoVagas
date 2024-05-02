package br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.service.ApplyJobService;
import br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.service.CandidateService;
import br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.service.FindJobsService;
import br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.service.ProfileCandidateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private FindJobsService findJobsService;

    @Autowired
    private ApplyJobService ApplyJobService;
    
    @GetMapping("/login")
    public String login(){
        return "candidate/login";
    }

    @PostMapping("/singIn")
    public String singIn(RedirectAttributes redirectAttributes, HttpSession session ,String username , String password){
        
        try {
            var token = this.candidateService.login(username, password);
            var grants = token.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+ role.toString().toUpperCase())).toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, grants);
            auth.setDetails(token.getAcess_token());

            SecurityContextHolder.getContext().setAuthentication(auth);
            SecurityContext securityContext =  SecurityContextHolder.getContext();
            session.setAttribute("SPRING_SECURITY_CONTEXT" , securityContext);
            session.setAttribute("token", token);
            return "redirect:/candidate/profile";
        
        } catch (HttpClientErrorException e ) {
            redirectAttributes.addFlashAttribute("error_message" , "Usuario/senha incorreto");
            return "redirect:/candidate/login";
        }
        
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String profile(Model model){

        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

            var user = this.profileCandidateService.execute(authentication.getDetails().toString());

            model.addAttribute("user", user);
        
        return "candidate/profile";
        } catch (HttpClientErrorException e) {
            return "redirect:/candidate/login";
        }
    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String jobs(Model model, String filter){
        
       try {
            if (filter != null) {
                //
                
                var jobs = this.findJobsService.execute( getToken() ,filter );
                System.out.println( jobs);
                model.addAttribute("jobs", jobs);
            }
       } catch (HttpClientErrorException e) {
        return "redirect:/candidate/login";
       }
        return "candidate/jobs";
    }


    @PostMapping("/jobs/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String applyJob(@RequestParam("jobId") UUID jobId){
        
        this.ApplyJobService.execute(getToken(),jobId);

        return "redirect:/candidate/jobs";

    }

    private String getToken(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        return authentication.getDetails().toString();
    }

}
