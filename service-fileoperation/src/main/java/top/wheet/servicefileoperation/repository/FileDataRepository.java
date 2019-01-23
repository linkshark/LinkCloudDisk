package top.wheet.servicefileoperation.repository;

import org.springframework.data.repository.CrudRepository;
import top.wheet.servicefileoperation.entity.FileData;

import java.util.List;

public interface FileDataRepository extends CrudRepository<FileData, String> {
    List<FileData> findAllByUserName(String userName);
}
