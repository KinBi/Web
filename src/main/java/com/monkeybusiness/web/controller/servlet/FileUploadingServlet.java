package com.monkeybusiness.web.controller.servlet;

import org.xml.sax.InputSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.UUID;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
  private static final String UPLOAD_DIR_PATH = "C:\\MonkeybusinessWebFirstStorage\\images\\";

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
          throws ServletException, IOException {

    String uploadFileDir = UPLOAD_DIR_PATH;

    File fileSaveDir = new File(uploadFileDir);
    if(!fileSaveDir.exists()){
      fileSaveDir.mkdirs();
    }
    request.getParts().stream().forEach(part -> {
      try {
        String path = part.getSubmittedFileName();
        String randFilename = UUID.randomUUID()+path.substring(path.lastIndexOf("."));//
        part.write(uploadFileDir  + randFilename);
        request.setAttribute("upload_result", " upload successfully ");
      } catch (IOException e) {
        request.setAttribute("upload_result", " upload failed ");
      }
    });
    // FIXME: 3/5/2021
    request.getRequestDispatcher("/user/profile").forward(request, response);
//        try {
//            for(Part part : request.getParts()) {
//                if (part.getSubmittedFileName() != null) {
//                   System.out.println(part.getSubmittedFileName());
//                   part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
//                  // part.write("d:\\tmp\\" + part. getSubmittedFileName());
//                   response.getWriter().print(part.getSubmittedFileName() + " upload successfully");
//                }
//            }
//        } catch (IOException e) {
//            System.out.println( "------>>>>" + e);;
//        }

//        InputStream in = null;
//        try {
//            in = request.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        InputSource is = new InputSource(in);

//        File file = new File(uploadFilePath + File.separator + part.getSubmittedFileName().substring(2));
//        OutputStream out = new FileOutputStream(file);
//        byte[] buffer = new byte[1024];
//
//        int len = 0;
//        while((len=in.read(buffer))!=-1){
//            out.write(buffer, 0, len);
//        }
//        out.close();
//        in.close();
  }
}