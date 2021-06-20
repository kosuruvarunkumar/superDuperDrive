package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class FileController {
    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/files")
    public String addFile(@ModelAttribute("newFile")FileForm newFile,
                          Authentication authentication, Model model) throws IOException {
        String userName = authentication.getName();
        MultipartFile multipartFile = newFile.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        boolean filePresent = fileService.isFilePresent(userName, fileName);
        if(!filePresent) {
            int fileId = fileService.addFile(multipartFile, userName);
            model.addAttribute("result","success");
        } else {
            model.addAttribute("error","You are trying to add duplicate file");
        }

        return "result";
    }

    @GetMapping("/delete-file/{fileName}")
    public String deleteFile(@PathVariable String fileName, Authentication authentication,
                             Model model) {
        fileService.deleteFile(fileName);
        model.addAttribute("result","success");
        return "result";
    }

    @GetMapping(value = "/get-file/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getFile(@PathVariable String fileName) {
        return fileService.getFile(fileName);
    }
}
