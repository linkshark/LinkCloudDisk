package top.wheet.servicefileoperation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.wheet.servicefileoperation.entity.FileData;
import top.wheet.servicefileoperation.repository.FileDataRepository;

import javax.xml.ws.http.HTTPException;
import javax.xml.ws.spi.http.HttpExchange;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("file")
public class Controller {
    //private static Map<String, Map<String, Map<String, List<>>>>

    @Autowired
    private FileDataRepository fileDataRepository;

    @GetMapping("{id}")
    public Object getFile(@PathVariable String id) {
        return fileDataRepository.findById(id);
    }

    @PostMapping("{name}")
    public Object addFile(@PathVariable String name, MultipartFile[] multipartFiles, String userName, Integer fileOrde) {
        if (userName != null && !userName.isEmpty()) {
            if (multipartFiles != null) {
                List<FileData> fileDataList = new ArrayList<>();
                for (MultipartFile multipartFile : multipartFiles) {
                    if (!multipartFile.isEmpty()) {
                        // saveFile
                        FileData fileData = new FileData().quickSetFileName(name).quickSetPath("savePath");
                        fileDataRepository.save(fileData);
                        fileDataList.add(fileData);
                    }
                }
                return fileDataList;
            } else {
                return new FileData();
            }
        } else {
            throw new HTTPException(412);
        }
    }

    @PutMapping("{id}")
    public Object upFile(@PathVariable String id, String userName, MultipartFile multipartFile) {
        if (userName != null && !userName.isEmpty()) {
            Optional<FileData> fileData = fileDataRepository.findById(id);
            if (fileData.isPresent()) {
                if (multipartFile != null) {
                    fileData.get().quickSetFileName("fileName").quickSetPath("savePath");
                    fileDataRepository.save(fileData.get());
                    return fileData;
                } else {
                    throw new HTTPException(412);
                }
            } else {
                throw new HTTPException(204);
            }
        } else {
            throw new HTTPException(412);
        }
    }

    @DeleteMapping("{id}")
    public Object delFile(@PathVariable String id) {
        fileDataRepository.deleteById(id);
        return true;
    }

    @GetMapping
    public Object getFiles(String userName) {
        if (userName!=null && !userName.isEmpty()) {
            return fileDataRepository.findAllByUserName(userName);
        } else {
            throw new HTTPException(412);
        }
    }
}
