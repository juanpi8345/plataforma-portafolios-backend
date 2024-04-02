package com.plataforma.portafolios.service;

import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import com.plataforma.portafolios.model.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IProjectService extends IGenericService<Project,Long>, IImageService<Project,Long>{

}
