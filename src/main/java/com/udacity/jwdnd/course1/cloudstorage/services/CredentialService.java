package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private UserMapper userMapper;
    private EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper,
                             EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public Integer addCredential(String userName, CredentialForm credential) {
        String url = credential.getUrl();
        String credentialUserName = credential.getUsername();
        String password = credential.getPassword();
        String key = getKey();
        Integer userId = userMapper.getUser(userName).getId();
        String encryptedPassword = encryptionService.encryptValue(password, key);
        return credentialMapper.addCredential(new Credentials(null, url,credentialUserName, key,
                encryptedPassword, userId));
    }

    public List<Credentials> getAllCredentials(String userName) {
        Integer userid = userMapper.getUser(userName).getId();
        return credentialMapper.getAllCredentialsOfAUser(userid);
    }

    private String getKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public Integer updateCredential(CredentialForm credential, String username) {
        String url = credential.getUrl();
        String password = credential.getPassword();
        String credentialUserName = credential.getUsername();
        String key = getKey();
        Integer userid = userMapper.getUser(username).getId();
        Integer credentialId = Integer.parseInt(credential.getId());
        String encryptedPassword = encryptionService.encryptValue(password,key);
        return credentialMapper.updateCredential(new Credentials(credentialId,url,
                credentialUserName,key,encryptedPassword, userid));
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }
}
