package br.com.searchdevelopers.godev.controller.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("/download")
public class DowloadController {

  @GetMapping("/layout")
  public void getFile( HttpServletResponse response) {

    Path arquivo = Paths.get( "/layout.docx");

    if (Files.exists(arquivo))
    {
      response.setContentType("application/pdf");
      response.addHeader("Content-Disposition", "attachment; filename=\"" + "layout.docx" + "\"");
      try {
        Files.copy(arquivo, response.getOutputStream());
        response.getOutputStream().flush();
      }
      catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }
}
