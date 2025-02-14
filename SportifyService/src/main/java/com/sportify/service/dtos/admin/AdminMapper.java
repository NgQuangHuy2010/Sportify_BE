package com.sportify.service.dtos.admin;

import org.springframework.stereotype.Component;

import com.sportify.service.dtos.admin.sport.CreateSportsFieldDTO;
import com.sportify.service.dtos.admin.sport.SportDetail;
import com.sportify.service.dtos.admin.sport.SportList;
import com.sportify.service.dtos.admin.sport.SportsCenterDTO;
import com.sportify.service.dtos.admin.sport.SportsFieldDTO;
import com.sportify.service.dtos.admin.sport.UpdateSportsFieldDTO;
import com.sportify.service.dtos.admin.user.UserAccountDTO;
import com.sportify.service.dtos.admin.user.UserDetailDTO;
import com.sportify.service.dtos.admin.user.UserListDTO;
import com.sportify.service.dtos.admin.user.UserUpdateDTO;
import com.sportify.service.entities.ConnectSetting;
import com.sportify.service.entities.Sport;
import com.sportify.service.entities.SportsCenter;
import com.sportify.service.entities.SportsField;
import com.sportify.service.entities.UserAccount;
import com.sportify.service.entities.UserProfile;

@Component
public class AdminMapper {
	// SPORT:
	public SportList SportToSportList(Sport sport) {
		return new SportList(sport.getId(), sport.getSportName(), sport.getImage());
	}

	public SportDetail SportToSportDetail(Sport sport, Long userCount) {
		return new SportDetail(sport.getId(), sport.getSportName(), sport.getImage(), userCount, sport.getCreatedOn());
	}

	// USER:
	public UserListDTO UserProfileToUserList(UserProfile user) {
		return new UserListDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(),
				user.getBirthday(), user.getPhone(), user.getAvatar(), user.getBio(),
				user.getUserAccount().getUsername(), user.getIsLocked());
	}
	public UserDetailDTO UserProfileToUserDetailDto (UserProfile user) {
		return new UserDetailDTO (
				user.getId(), 
				user.getCreatedOn(), 
				user.getFirstname(), 
				user.getLastname(), 
				user.getEmail(),
				user.getBirthday(), 
				user.getPhone(), 
				user.getAvatar(), 
				user.getBio(), 
				user.getIsLocked(), 
				UserAccountToUserAccountDto(user.getUserAccount()), 
				ConnectSettingToConnectSettingDto(user.getConnectSetting()));
	}
	
	public UserAccountDTO UserAccountToUserAccountDto (UserAccount userAcc) {
		return new UserAccountDTO(userAcc.getUsername(), userAcc.getPassword(), userAcc.getLastLogin(), userAcc.getRegistrationMethod());
	}
	
	public UserAccount UserAccountDtoToUserAccount (UserAccount entity, UserAccountDTO dto) {
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setRegistrationMethod(dto.getRegistrationMethod());
		return entity;
	}
	
	public void updateUserFromDto (UserProfile entity, UserUpdateDTO dto) {
		entity.setFirstname(dto.getFirstName());
		entity.setLastname(dto.getLastname());
		entity.setEmail(dto.getEmail());
		entity.setBirthday(dto.getBirthday());
		entity.setPhone(dto.getPhone());
		entity.setAvatar(dto.getAvatar());
		entity.setBio(dto.getBio());
		entity.setIsLocked(dto.isLocked());
		entity.setUserAccount(UserAccountDtoToUserAccount(entity.getUserAccount(), dto.getUserAccount()));
		entity.setConnectSetting(ConnectSettingDtoToConnectSetting(entity.getConnectSetting(), dto.getConnectSetting()));
	}
	
	//ConnectSetting
	public ConnectSettingDTO ConnectSettingToConnectSettingDto (ConnectSetting conSetting) {
		return new ConnectSettingDTO(conSetting.getWeekDay(), conSetting.getFromTime(), conSetting.getToTime(), conSetting.getAgeMax(), conSetting.getAgeMin(), conSetting.getGenderFind(), conSetting.getStatus());
	}
	public ConnectSetting ConnectSettingDtoToConnectSetting (ConnectSetting entity, ConnectSettingDTO dto) {
		entity.setWeekDay(dto.getWeekDay());
		entity.setFromTime(dto.getFromTime());
		entity.setToTime(dto.getToTime());
		entity.setAgeMax(dto.getAgeMax());
		entity.setAgeMin(dto.getAgeMin());
		entity.setGenderFind(dto.getGenderFind());
		entity.setStatus(dto.getStatus());
		return entity;
	}
	
	// Sport Field: 

    public SportsFieldDTO SportsFieldToDTO(SportsField sportsField) {
        return new SportsFieldDTO(
                sportsField.getId(),
                sportsField.getName(),
                sportsField.getType(),
                sportsField.getSize(),
                sportsField.getPricePerHour(),
                sportsField.getIsAvailable(),
                sportsField.getSportsCenter().getId()
        );
    }

    public SportsField SportsFieldDtoToEntity(CreateSportsFieldDTO dto, SportsCenter sportsCenter) {
        SportsField sportsField = new SportsField();
        sportsField.setName(dto.getName());
        sportsField.setType(dto.getType());
        sportsField.setSize(dto.getSize());
        sportsField.setPricePerHour(dto.getPricePerHour());
        sportsField.setIsAvailable(dto.getIsAvailable());
        sportsField.setSportsCenter(sportsCenter);
        return sportsField;
    }
    
    public void updateSportFieldEntity(SportsField sportsField, UpdateSportsFieldDTO dto) {
        sportsField.setName(dto.getName());
        sportsField.setType(dto.getType());
        sportsField.setSize(dto.getSize());
        sportsField.setPricePerHour(dto.getPricePerHour());
        sportsField.setIsAvailable(dto.getIsAvailable());
    }
	
	//Sport Center: 
	public SportsCenterDTO SportsCenterToDTO(SportsCenter sportsCenter) {
        return new SportsCenterDTO(
                sportsCenter.getId(),
                sportsCenter.getName(),
                sportsCenter.getLocation(),
                sportsCenter.getDescription()
        );
    }

    public SportsCenter SportsCemterDtoToEntity(SportsCenterDTO dto) {
        SportsCenter sportsCenter = new SportsCenter();
        sportsCenter.setName(dto.getName());
        sportsCenter.setLocation(dto.getLocation());
        sportsCenter.setDescription(dto.getDescription());
        return sportsCenter;
    }
	
}
