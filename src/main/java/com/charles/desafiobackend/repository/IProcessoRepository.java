package com.charles.desafiobackend.repository;

import com.charles.desafiobackend.entity.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProcessoRepository extends JpaRepository<Processo, Long> {
}