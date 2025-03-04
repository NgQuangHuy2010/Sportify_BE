package com.sportify.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.AddressClientDTO;
import com.sportify.service.dtos.admin.sport.SportsFieldDTO;
import com.sportify.service.dtos.admin.sport.UpdateSportsFieldDTO;
import com.sportify.service.entities.Address;
import com.sportify.service.entities.SportsField;
import com.sportify.service.repositories.AddressClientRepository;

@Service
public class AddressClientService {
	
	  private final AddressClientRepository addressClientRepository;

	    public AddressClientService(AddressClientRepository addressClientRepository) {
	        this.addressClientRepository = addressClientRepository;
	    }

	    public AddressClientDTO updateAddressClient(Long id, AddressClientDTO dto) {
	        Address address = addressClientRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Address not found"));

	        // Cập nhật thông tin địa chỉ
	        address.setCity(dto.getCity());
	        address.setDistrict(dto.getDistrict());
	        address.setWard(dto.getWard());
	        address.setNo(dto.getNo());

	        // Lưu vào database
	        addressClientRepository.save(address);

	        // Trả về DTO đã cập nhật
	        return new AddressClientDTO(address.getWard(), address.getDistrict(), address.getCity(), address.getNo());
	    }
}
