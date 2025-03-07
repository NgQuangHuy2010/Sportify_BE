package com.sportify.service.seed;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportify.service.entities.Address;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@Service
public class LocationService {
    private List<Province> provinces;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Không cần inject từ config
    private final Random random = new Random();

    @PostConstruct
    private void loadLocations() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("locations.json");
            if (inputStream == null) throw new RuntimeException("File locations.json not found!");
            provinces = objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error loading locations.json", e);
        }
    }

    public Address getRandomAddress() {
        Province province = provinces.get(random.nextInt(provinces.size()));
        District district = province.getDistricts().get(random.nextInt(province.getDistricts().size()));
        Ward ward = district.getWards().get(random.nextInt(district.getWards().size()));

        Address address = new Address();
        address.setCity(province.getCode().toString());
        address.setDistrict(district.getCode().toString());
        address.setWard(ward.getCode().toString());
        address.setNo((random.nextInt(500) + 1) + " " + ward.getName());
        return address;
    }

    // ✅ Các class để map JSON
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Province {
        private String name;
        private Integer code;
        private List<District> districts;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class District {
        private String name;
        private Integer code;
        private List<Ward> wards;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ward {
        private String name;
        private Integer code;
    }
}