package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotesTests extends CloudStorageApplicationTests {
    @Test
    public void deleteTest() {
        String nTitle = "Note to test delete operation";
        String nDescription = "Note to test delete operation";
        HPage hPage = signUpAndLogin();
        saveNote(nTitle, nDescription, hPage);
        hPage.nToNotesTab();
        hPage = new HPage(driver);
        Assertions.assertFalse(hPage.noNotes(driver));
        deleteNote(hPage);
        Assertions.assertTrue(hPage.noNotes(driver));
    }

    private void deleteNote(HPage hPage) {
        hPage.dNote();
        RPage rPage = new RPage(driver);
        rPage.clickOk();
    }

    @Test
    public void saveAndViewNote() {
        String nTitle = "New Note";
        String nDescription = "New note is added";
        HPage hPage = signUpAndLogin();
        saveNote(nTitle, nDescription, hPage);
        hPage.nToNotesTab();
        hPage = new HPage(driver);
        Notes note = hPage.gFirstNote();
        Assertions.assertEquals(nTitle, note.getNoteTitle());
        Assertions.assertEquals(nDescription, note.getNotesDescription());
        deleteNote(hPage);
        hPage.logout();
    }

    @Test
    public void modify() {
        String noteTitle = "New Note";
        String noteDescription = "New test note";
        HPage hPage = signUpAndLogin();
        saveNote(noteTitle, noteDescription, hPage);
        hPage.nToNotesTab();
        hPage = new HPage(driver);
        hPage.eNote();
        String modifiedNoteTitle = "Modified test";
        hPage.mNoteTitle(modifiedNoteTitle);
        String modifiedNoteDescription = "Existing note is modified";
        hPage.mNoteDescription(modifiedNoteDescription);
        hPage.saveNoteChanges();
        RPage rPage = new RPage(driver);
        rPage.clickOk();
        hPage.nToNotesTab();
        Notes note = hPage.gFirstNote();
        Assertions.assertEquals(modifiedNoteTitle, note.getNoteTitle());
        Assertions.assertEquals(modifiedNoteDescription, note.getNotesDescription());
    }

    private void saveNote(String nTitle, String nDescription, HPage hPage) {
        hPage.nToNotesTab();
        hPage.aNewNote();
        hPage.sNoteTitle(nTitle);
        hPage.sNoteDescription(nDescription);
        hPage.saveNoteChanges();
        RPage rPage = new RPage(driver);
        rPage.clickOk();
        hPage.nToNotesTab();
    }
}
