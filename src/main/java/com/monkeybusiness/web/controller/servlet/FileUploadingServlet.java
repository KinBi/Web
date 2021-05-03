package com.monkeybusiness.web.controller.servlet;

import com.monkeybusiness.web.controller.RequestParameter;
import com.monkeybusiness.web.controller.SessionAttribute;
import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.exception.ImageServiceException;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.dao.impl.UserDaoImpl;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.service.ImageService;
import com.monkeybusiness.web.model.service.UserService;
import com.monkeybusiness.web.model.service.impl.ImageServiceImpl;
import com.monkeybusiness.web.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import java.util.UUID;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String UPLOAD_DIR_PATH = "C:\\MonkeybusinessWebFirstStorage\\userImages\\";

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
          throws ServletException, IOException {
    LOGGER.log(Level.DEBUG, "FileUploadingServlet has been handling file");
    String uploadFileDir = UPLOAD_DIR_PATH;

    File fileSaveDir = new File(uploadFileDir);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }

    HttpSession session = request.getSession();
    long userId = (long) session.getAttribute(SessionAttribute.USER_ID);
    User user = new User();
    user.setUserId(userId);

    ImageService imageService = ImageServiceImpl.INSTANCE;
    UserService userService = UserServiceImpl.INSTANCE;

    request.getParts().stream().forEach(part -> {
      try {
        String path = part.getSubmittedFileName();
        if (imageService.isJpg(part.getInputStream())) {
          String randFilename = UUID.randomUUID() + path.substring(path.lastIndexOf("."));
          String imagePath = uploadFileDir + randFilename;
          if (userService.updateUserImage(user, imagePath)) {
            new File((String) session.getAttribute(SessionAttribute.USER_IMAGE)).delete();
            part.write(imagePath);
            session.setAttribute(SessionAttribute.USER_IMAGE, imagePath);
          }
          request.setAttribute(RequestParameter.UPLOAD_RESULT, " upload successfully ");
        } else {
          request.setAttribute(RequestParameter.UPLOAD_RESULT, " upload failed ");
        }
      } catch (IOException | ImageServiceException | UserServiceException e) {
        LOGGER.log(Level.ERROR, "File uploading has been failed");
        request.setAttribute(RequestParameter.UPLOAD_RESULT, " upload failed ");
      }
    });
    request.getRequestDispatcher(UrlPath.PROFILE).forward(request, response);
  }
}