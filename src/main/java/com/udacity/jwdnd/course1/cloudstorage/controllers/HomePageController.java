package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomePageController {
    @Autowired
    private NotesService notesService;
    @Autowired
    private FileService fileService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private EncryptionService encryptionService;

//    public HomePageController(NotesService notesService, FileService fileService,
//                              CredentialService credentialService,
//                              EncryptionService encryptionService) {
//        this.notesService = notesService;
//        this.fileService = fileService;
//        this.credentialService= credentialService;
//        this.encryptionService = encryptionService;
//    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication,
                              @ModelAttribute("newFile")FileForm newFile,
                              @ModelAttribute("newNote") NoteForm newNote,
                              @ModelAttribute("newCredential") CredentialForm credential,
                              Model model) {
        model.addAttribute("notes", notesService.getAllNotes(authentication.getName()));
        model.addAttribute("files", fileService.getAllFiles(authentication.getName()));
        model.addAttribute("credentials",
                credentialService.getAllCredentials(authentication.getName()));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

}
