package org.example.fxdatawarehouse.Repositories;

import org.example.fxdatawarehouse.Models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DealRepository extends JpaRepository<Deal, String> { }