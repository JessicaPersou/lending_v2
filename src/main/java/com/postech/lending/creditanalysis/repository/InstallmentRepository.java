package com.postech.lending.creditanalysis.repository;

import com.postech.lending.creditanalysis.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {

}
