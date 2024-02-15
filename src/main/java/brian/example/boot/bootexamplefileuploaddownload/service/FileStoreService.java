package brian.example.boot.bootexamplefileuploaddownload.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStoreService {

    private final Path fileStorageLocation;

    public FileStoreService() {
        fileStorageLocation = Paths.get("C:/temp").toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        } catch(Exception ex){
            // Do something to throw an error when the directory cannot be created
        }
    }

    public String storeFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch( IOException ex){
            throw new Exception("Could not store files "+fileName+". Please try again!", ex);
        }

        return fileName;
    }

    public Resource loadFileAsResource(String fileName) throws Exception {
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            } else{
                throw new Exception("File not found "+fileName);
            }
        }catch(MalformedURLException ex){

        }
        return null;
    }
}
