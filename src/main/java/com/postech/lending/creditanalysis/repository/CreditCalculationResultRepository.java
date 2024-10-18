package com.postech.lending.creditanalysis.repository;

import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCalculationResultRepository extends JpaRepository<CreditCalculationResult, Long> {

}
