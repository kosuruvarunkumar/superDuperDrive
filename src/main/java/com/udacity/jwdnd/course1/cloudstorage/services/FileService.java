package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    private UserMapper userMapper;
    private FileMapper fileMapper;

    public FileService(UserMapper userMapper, FileMapper fileMapper) {
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
    }

    public Integer getUserId(String userName) {
        return userMapper.getUser(userName).getId();
    }

    public List<String> getAllFiles(String userName) {
        return fileMapper.getAllFilesNamesOfAUser(getUserId(userName));
    }

    public boolean isFilePresent(String userName, String fileName) {
        boolean filePresent = false;
        List<String> allFileNames = getAllFiles(userName);
        for(String fName : allFileNames) {
            if(fileName.equals(fName)) {
                filePresent = true;
                break;
            }
        }
        return filePresent;
    }

    public Integer addFile(MultipartFile multipartFile, String userName) throws IOException {
        InputStream fis = multipartFile.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = fis.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] fileData = buffer.toByteArray();
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        Integer userid = userMapper.getUser(userName).getId();
        Files file = new Files(0, fileName, contentType, fileSize, userid, fileData);
        return fileMapper.addFile(file);
    }

    public byte[] getFile(String fileName) {
        return fileMapper.getFile(fileName).getFileData();
    }

    public void deleteFile(String fileName) {
        fileMapper.deleteFile(fileName);
    }

}
