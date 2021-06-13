package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("select * from credentials where userid=#{userId}")
    List<Credentials> getAllCredentialsOfAUser(Integer userId);

    @Insert("insert into credentials(url,username,key,password,userid) values(#{url}, " +
            "#{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addCredential(Credentials credential);
}
