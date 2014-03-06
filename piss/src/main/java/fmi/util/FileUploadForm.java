package fmi.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 13-8-28
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
public class FileUploadForm {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
