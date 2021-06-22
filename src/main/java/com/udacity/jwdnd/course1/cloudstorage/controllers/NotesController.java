package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotesController {
    NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("/notes")
    public String addNotes(Authentication authentication,
                           @ModelAttribute("newNote") NoteForm newNote,
            Model model) {
        String error = null;
        Integer noteId;
        if(newNote.getNoteId().isEmpty()){
            noteId = notesService.addNotes(newNote, authentication.getName());
        } else {
            noteId = notesService.updateNotes(newNote);
        }
        if(noteId == null) {
            model.addAttribute("error", "Error in processing the request");
        } else {
            model.addAttribute("notes", notesService.getAllNotes(authentication.getName()));
            model.addAttribute("result", "success");
        }
        return "result";
    }

    @GetMapping("/delete-note/{id}")
    public String deleteNote(Authentication authentication, @PathVariable Integer id, Model model) {
        notesService.deleteNote(id);
        model.addAttribute("result","success");
        return "result";
    }

}
