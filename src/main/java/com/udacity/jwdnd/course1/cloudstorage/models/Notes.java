package com.udacity.jwdnd.course1.cloudstorage.models;

public class Notes {
    private Integer id;
    private String noteTitle;
    private String notesDescription;
    private Integer userid;

    public Notes(Integer id, String noteTitle, String notesDescription, Integer userid) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.notesDescription = notesDescription;
        this.userid = userid;
    }

    public Notes(String title, String description) {
        this.noteTitle = title;
        this.notesDescription = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNotesDescription() {
        return notesDescription;
    }

    public void setNotesDescription(String notesDescription) {
        this.notesDescription = notesDescription;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
