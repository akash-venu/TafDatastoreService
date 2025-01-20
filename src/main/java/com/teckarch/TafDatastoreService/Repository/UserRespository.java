package com.teckarch.TafDatastoreService.Repository;

import com.teckarch.TafDatastoreService.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<Users,Long>  {

}
