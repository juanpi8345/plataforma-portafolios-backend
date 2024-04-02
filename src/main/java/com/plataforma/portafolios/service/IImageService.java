package com.plataforma.portafolios.service;

import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService <E,K>{
    void uploadImage(K id, MultipartFile imageFile) throws IOException, EntityNotFoundException;

    void deleteImage(E e) throws EntityNotFoundException;
}
