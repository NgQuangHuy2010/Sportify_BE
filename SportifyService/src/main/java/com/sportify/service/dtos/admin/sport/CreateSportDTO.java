package com.sportify.service.dtos.admin.sport;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateSportDTO {
    private String sportName;
    private MultipartFile image;  // Thêm file ảnh
}