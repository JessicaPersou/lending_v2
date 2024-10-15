package com.postech.lending.client.repository;

import com.postech.lending.client.model.Address;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
