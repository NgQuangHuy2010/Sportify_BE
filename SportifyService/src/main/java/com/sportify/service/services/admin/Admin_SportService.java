package com.sportify.service.services.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sportify.service.dtos.admin.AdminMapper;
import com.sportify.service.dtos.admin.sport.CreateSportDTO;
import com.sportify.service.dtos.admin.sport.SportDetail;
import com.sportify.service.dtos.admin.sport.SportList;
import com.sportify.service.entities.Sport;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.admin.Admin_SportRepository;
import com.sportify.service.repositories.admin.Admin_UserProfileRepository;

import jakarta.transaction.Transactional;

@Service
public class Admin_SportService {
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
    private Admin_SportRepository sportRepository;
	@Autowired
    private Admin_UserProfileRepository userProfileRepository;
	@Autowired
	private Admin_UserProfileService userService;
	
	private static final String UPLOAD_DIR = "uploads/sports/";

	public List<SportList> getAllSports() {
        return sportRepository.findAll()
                .stream()
                .map(sport -> new SportList(sport.getId(), sport.getSportName(), sport.getImage()))
                .collect(Collectors.toList());
    }
    

    public SportDetail getSportById(Long id) {
    	System.out.println("ID: "+ id);
        Sport sport = sportRepository.findById(id)
                                     .orElseThrow(() -> new RuntimeException("Sport not found with id: " + id));
        if (sport != null) {

            String sportName = sport.getSportName();
            System.out.println("SPORT NAME: " + sport.getSportName());
        } else {
        	System.out.println("NO SPORT");
        }
//        int userCount = sport.getUserProfiles().size();
        Long userCount = userService.getTotalUsersBySport(id);
        System.out.println("USER COUNT: " + userCount);
        
        return adminMapper.SportToSportDetail(sport, userCount);
    }
    
    public Sport createSport(CreateSportDTO dto) throws IOException {
        Sport sport = new Sport();
        sport.setSportName(dto.getSportName());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String fileName = saveImage(dto.getImage());
            sport.setImage(fileName);
        }

        Sport savedSport = sportRepository.save(sport);
        return savedSport;
    }
    
    public SportList updateSport(Long id, CreateSportDTO dto) throws IOException {
        Sport sport = sportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sport not found with ID: " + id));

        // Cập nhật tên môn thể thao
        sport.setSportName(dto.getSportName());

        // Nếu có ảnh mới, lưu ảnh và cập nhật đường dẫn
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String fileName = saveImage(dto.getImage()); // Dùng lại hàm lưu ảnh
            sport.setImage(fileName);
        }
        Sport savedSport = sportRepository.save(sport);
        return new SportList(savedSport.getId(), savedSport.getSportName(), savedSport.getImage());
    }


    private String saveImage(MultipartFile image) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path imagePath = Paths.get(UPLOAD_DIR, fileName);
        Files.createDirectories(imagePath.getParent()); // Tạo thư mục nếu chưa có
        Files.write(imagePath, image.getBytes());
        return fileName;
    }
    
    @Transactional
    public void deleteSport(Long id) {
        Sport sport = sportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sport not found with ID: " + id));

        // Xóa quan hệ trong bảng trung gian trước
        userProfileRepository.removeSportFromUsersNative(id);

        // Xóa môn thể thao
        sportRepository.delete(sport);
    }
    
    
    
}
