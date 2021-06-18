package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {
    @Select("select * from notes where userid = #{userId}")
    List<Notes> getAllNotesOfAUser(Integer userId);

    @Insert("insert into notes(notetitle, notedescription, userid) values(#{noteTitle}," +
            "#{notesDescription}, #{userid})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int addNote(Notes note);

    @Delete("delete from notes where noteid= #{noteid}")
    void deleteNote(Integer noteid);

    @Update("update notes set notetitle=#{noteTitle}, notedescription=#{noteDescription} where " +
            "noteid=#{noteId}")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int updateNote(NoteForm newNote);
}
