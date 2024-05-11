package br.com.wagnermorais.fron_gestao_vagas.modules.company;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.dto.CreateCandidateDTO;
import br.com.wagnermorais.fron_gestao_vagas.modules.company.dto.CreateCompanyDTO;
import br.com.wagnermorais.fron_gestao_vagas.modules.company.dto.CreateJobsDTO;
import br.com.wagnermorais.fron_gestao_vagas.modules.company.service.CreateCompanyService;
import br.com.wagnermorais.fron_gestao_vagas.modules.company.service.CreateJobService;
import br.com.wagnermorais.fron_gestao_vagas.modules.company.service.ListAllJobsCompanyService;
import br.com.wagnermorais.fron_gestao_vagas.modules.company.service.LoginCompanyService;
import br.com.wagnermorais.fron_gestao_vagas.utils.FormatErrorMessagem;
import jakarta.servlet.http.HttpSession;




@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyService createCompanyService;

    @Autowired
    private LoginCompanyService loginCompanyService;

    @Autowired
    private CreateJobService createJobService;

    @Autowired
    private ListAllJobsCompanyService listAllJobsCompanyService;
    
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("company", new CreateCandidateDTO());
        return "company/create";
    }


    @PostMapping("/create")
    public String save(Model model , CreateCompanyDTO companyDTO){
       try {
        model.addAttribute("company", new CreateCandidateDTO());
        return this.createCompanyService.execute(companyDTO);

       } catch (HttpClientErrorException e) {
         model.addAttribute("error_message", FormatErrorMessagem.formatErrorMessagem(e.getResponseBodyAsString()));
         model.addAttribute("company", companyDTO);
       }

       return "company/create";
    }

    @GetMapping("/login")
    public String login(){
      return "company/login";
    }


    @PostMapping("/singIn")
    public String singIn(RedirectAttributes redirectAttributes, HttpSession session ,String username , String password){
        
        try {
            var token = this.loginCompanyService.execute(username, password);
            System.out.println(token);
            var grants = token.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+ role.toString().toUpperCase())).toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, grants);
            auth.setDetails(token.getAcess_token());

            SecurityContextHolder.getContext().setAuthentication(auth);
            SecurityContext securityContext =  SecurityContextHolder.getContext();
            session.setAttribute("SPRING_SECURITY_CONTEXT" , securityContext);
            session.setAttribute("token", token);
            return "redirect:/company/jobs";
        
        } catch (HttpClientErrorException e ) {
            redirectAttributes.addFlashAttribute("error_message" , "Usuario/senha incorreto");
            return "redirect:/company/jobs";
        }
        
    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    public String jobs(Model model){
        model.addAttribute("jobs", new CreateJobsDTO());
        return "/company/jobs";
    }


    @PostMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    public String createJobs(CreateJobsDTO createJobsDTO){
       
       var result = this.createJobService.execute(createJobsDTO, getToken());
       System.out.println(result);

       return "redirect:/company/jobs/list";
    }

    @GetMapping("/jobs/list")
    @PreAuthorize("hasRole('COMPANY')")
    public String list(Model model){
        
        var result = this.listAllJobsCompanyService.execute(getToken());
        model.addAttribute("jobs", result);
        System.out.println(result);
        return "/company/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContext securityContext =  SecurityContextHolder.getContext();
        session.setAttribute("SPRING_SECURITY_CONTEXT" , securityContext);
        session.setAttribute("token", null);

        return "redirect:/company/login";
    }

    private String getToken(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        return authentication.getDetails().toString();
    }

    

}
