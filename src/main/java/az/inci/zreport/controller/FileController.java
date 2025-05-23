package az.inci.zreport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@Controller
@RequestMapping("/image")
public class FileController
{
    @ResponseBody
    @GetMapping(value = "/{file-name}", produces = IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("file-name") String fileName)
    {

        try
        {
            File file = new File("images/" + fileName.concat(".jpeg"));
            Path path = Paths.get(file.getPath());
            return Files.readAllBytes(path);
        }
        catch(IOException e)
        {
            try
            {
                File file = new File("images/" + fileName.concat(".jpg"));
                Path path = Paths.get(file.getPath());
                return Files.readAllBytes(path);
            }
            catch(IOException e1)
            {
                File file = new File("images/default.jpeg");
                Path path = Paths.get(file.getPath());
                try
                {
                    return Files.readAllBytes(path);
                }
                catch(IOException e2)
                {
                    return new byte[]{};
                }
            }
        }
    }
}
