package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("select fileName from files where userid = #{userId}")
    List<String> getAllFilesNamesOfAUser(Integer userId);

    @Insert("insert into files(filename, contenttype,filesize,userid, filedata) values" +
            "(#{fileName}, " +
            "#{contentType}, #{fileSize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addFile(Files file);

    @Select("select * from files where filename=#{fileName}")
    Files getFile(String fileName);

    @Delete("delete from files where fileName=#{fileName}")
    void deleteFile(String fileName);

}
