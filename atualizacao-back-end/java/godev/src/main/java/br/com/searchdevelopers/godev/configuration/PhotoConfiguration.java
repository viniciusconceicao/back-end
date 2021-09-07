package br.com.searchdevelopers.godev.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class PhotoConfiguration implements WebMvcConfigurer {

  private final Path root = Paths.get("").toAbsolutePath();
  private Logger log = LoggerFactory.getLogger(PhotoConfiguration.class);

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    Path directory = root.resolve("photos");
    log.info("Current directory: " + root);

    if (!Files.exists(directory)) {
      try {
        log.info("Creating directory");
        Files.createDirectories(directory);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    log.info("Serving static files in: " + directory.toUri());

    registry.addResourceHandler("/public/images/**").addResourceLocations(directory.toUri().toString());
  }
}
