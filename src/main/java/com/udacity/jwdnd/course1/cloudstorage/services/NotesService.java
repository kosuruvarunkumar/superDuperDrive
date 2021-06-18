package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private NotesMapper notesMapper;
    private UserMapper userMapper;

    public NotesService(NotesMapper notesMapper, UserMapper userMapper) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
    }

    public List<Notes> getAllNotes(String userName) {
        Integer userid = getUserId(userName);
        return notesMapper.getAllNotesOfAUser(userid);
    }

    public Integer addNotes(NoteForm note, String userName) {
        Integer userid = getUserId(userName);
        return notesMapper.addNote(new Notes(null, note.getNoteTitle(),
                note.getNoteDescription(), userid));
    }

    private Integer getUserId(String userName) {
        return userMapper.getUser(userName).getId();
    }

    public Integer updateNotes(NoteForm newNote) {
        return notesMapper.updateNote(newNote);
    }

    public void deleteNote(Integer noteId) {
        notesMapper.deleteNote(noteId);
    }

}
