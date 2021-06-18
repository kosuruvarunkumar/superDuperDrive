package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomePageController {
    private NotesService notesService;

    public HomePageController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication,
                              @ModelAttribute Files file,
                              @ModelAttribute("newNote") NoteForm newNote,
                              @ModelAttribute Credentials credential, Model model) {
        model.addAttribute("notes", notesService.getAllNotes(authentication.getName()));
        return "home";
    }

}
