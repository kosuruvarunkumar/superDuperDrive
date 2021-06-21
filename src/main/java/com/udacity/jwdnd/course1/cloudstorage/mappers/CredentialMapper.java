package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("select * from credentials where userid=#{userId}")
    List<Credentials> getAllCredentialsOfAUser(Integer userId);

    @Insert("insert into credentials(url,username,key,password,userid) values(#{url}, " +
            "#{userName}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addCredential(Credentials credential);

    @Update("update credentials set url=#{url}, userName=#{userName}, key=#{key}, " +
            "password=#{password} where credentialid=#{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    Integer updateCredential(Credentials credential);

    @Delete("delete from credentials where credentialid=#{credentialId}")
    void deleteCredential(Integer credentialId);
}
