package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from users where username=#{userName}")
    User getUser(String userName);

    @Insert("insert into users(username, salt,password, firstname,lastname) values(#{userName), " +
            "#{salt},#{password},#{firstName},#{lastName}")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    Integer addUser(User user);

}
