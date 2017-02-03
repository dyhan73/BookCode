package com.ensoa.order.controller.product;

@SuppressWarnings("serial")
public class ImageUploadException extends RuntimeException {
  public ImageUploadException(String message) {
    super(message);
  }
  
  public ImageUploadException(String message, Throwable cause) {
    super(message, cause);
  }
}
