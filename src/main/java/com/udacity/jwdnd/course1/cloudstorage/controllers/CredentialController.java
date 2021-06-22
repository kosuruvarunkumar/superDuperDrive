package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {
    CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credentials")
    public String addCredential(@ModelAttribute("newCredential") CredentialForm credential,
                                Model model, Authentication authentication) {
        String username = authentication.getName();
        Integer credentialId;
        if(credential.getId().isEmpty()){
            credentialId = credentialService.addCredential(username, credential);
        } else {
            credentialId = credentialService.updateCredential(credential, username);
        }
        if(credentialId < 0) {
            model.addAttribute("error","Unable to process the request. ");
        } else {
            model.addAttribute("result","success");
        }
        return "result";
    }

    @GetMapping("/delete-credential/{id}")
    public String deleteCredential(Authentication authentication,
                                   @PathVariable Integer id,
                                   Model model) {
        credentialService.deleteCredential(id);
        model.addAttribute("result","success");
        return "result";
    }
}
