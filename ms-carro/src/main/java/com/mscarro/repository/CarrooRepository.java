package com.mscarro.repository;

import com.mscarro.entity.Carroo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrooRepository extends JpaRepository<Carroo,Integer> {
    List<Carroo> findByUserid(int userid);

}
