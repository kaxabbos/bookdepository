package com.bookdepository.repo;

import com.bookdepository.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressesRepo extends JpaRepository<Addresses, Long> {
}
