package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import spark.Request;
import spark.utils.IOUtils;

public class ImageFetcher {

  public static List<String> fetchImageURL(Request req) {
    final List<String> images = new ArrayList<>();
    final File uploadDir = new File("uploads");
    try {
      List<Part> parts = req.raw().getParts().stream().filter(p -> p.getSubmittedFileName() != null).collect(Collectors.toList());
      for (Part p : parts) {
        System.out.println("Leyendo archivo => " + p.getSubmittedFileName());
        final InputStream inputStream = p.getInputStream();
        final String imageID = UUID.randomUUID().toString();
        final String[] splitted = p.getSubmittedFileName().split("\\.");
        final String fileExtension = splitted[splitted.length - 1];
        final String uri = imageID + "." + fileExtension;
        final String fsPath = uploadDir.toPath().toString() + "/" + uri;
        final OutputStream fos = new FileOutputStream(fsPath);
        IOUtils.copy(inputStream, fos);
        fos.flush();
        fos.close();
        images.add(uri);
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ServletException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return images;
  }

}
