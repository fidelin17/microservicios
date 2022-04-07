package com.msmoto.repository;

import com.msmoto.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {
    List<Moto> findByUserid(int userid);
}
