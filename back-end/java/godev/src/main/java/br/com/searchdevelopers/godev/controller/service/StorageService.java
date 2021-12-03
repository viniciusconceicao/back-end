package br.com.searchdevelopers.godev.controller.service;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageService {

  private final Path root;
  private Logger log = LoggerFactory.getLogger(StorageService.class);

  public StorageService() {
    this.root = Paths.get("").toAbsolutePath();
  }

  public String savePhoto(MultipartFile photo) throws IOException {
    Path directory = root.resolve("photos");

    if (!Files.exists(directory)) {
      log.info("Creating directory");
      Files.createDirectories(directory);
    }

    String fileUrl = UUID.randomUUID() + "." + FilenameUtils.getExtension(photo.getOriginalFilename());
    Path path = directory.resolve(fileUrl);
    log.info("Creating file: " + path.getFileName());
    byte[] content = photo.getBytes();
    Files.write(path, content);

    return path.getFileName().toString();
  }
}
