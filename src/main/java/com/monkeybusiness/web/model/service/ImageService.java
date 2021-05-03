package com.monkeybusiness.web.model.service;

import com.monkeybusiness.web.exception.ImageServiceException;

import java.io.InputStream;

public interface ImageService {
  boolean isJpg(InputStream fileStream) throws ImageServiceException;
}
