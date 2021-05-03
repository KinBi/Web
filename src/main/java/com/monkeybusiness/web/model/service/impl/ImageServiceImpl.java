package com.monkeybusiness.web.model.service.impl;

import com.monkeybusiness.web.exception.ImageServiceException;
import com.monkeybusiness.web.model.service.ImageService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public enum ImageServiceImpl implements ImageService {
  INSTANCE;

  private static final Logger LOGGER = LogManager.getLogger();
  private static final int[] jpgBytes = {0xFF, 0xD8, 0xFF};

  @Override
  public boolean isJpg(InputStream fileStream) throws ImageServiceException {
    boolean result = true;
    for (int jpgByte : jpgBytes) {
      try {
        int b = fileStream.read();
        if (b != jpgByte) {
          result = false;
          break;
        }
      } catch (IOException e) {
        LOGGER.log(Level.ERROR, e);
        throw new ImageServiceException(e);
      }
    }
    return result;
  }
}
