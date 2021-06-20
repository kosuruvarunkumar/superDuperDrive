package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.*;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;

@Controller
public class HomePageController {
    private NotesService notesService;
    private FileService fileService;

    public HomePageController(NotesService notesService, FileService fileService) {
        this.notesService = notesService;
        this.fileService = fileService;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication,
                              @ModelAttribute("newFile")FileForm newFile,
                              @ModelAttribute("newNote") NoteForm newNote,
                              @ModelAttribute Credentials credential, Model model) {
        model.addAttribute("notes", notesService.getAllNotes(authentication.getName()));
        model.addAttribute("files", fileService.getAllFilesOfAUser(authentication.getName()));
        return "home";
    }

}
