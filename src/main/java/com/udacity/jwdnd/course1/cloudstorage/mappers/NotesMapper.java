package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotesMapper {
    @Select("select * from notes where userid = #{userId}")
    List<Notes> getAllNotesOfAUser(Integer userId);

    @Insert("insert into notes(notetitle, notesdescription, userid) values(#{notesTitle}," +
            "#{notesDescription}, #{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int addNote(Notes note);
}
