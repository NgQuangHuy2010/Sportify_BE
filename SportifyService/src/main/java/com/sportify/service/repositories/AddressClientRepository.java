package com.sportify.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.service.entities.Address;

public interface AddressClientRepository extends JpaRepository<Address, Long>{

}
