package com.postech.lending.creditanalysis.repository;

import com.postech.lending.creditanalysis.model.AnalysisCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisCreditRepository extends JpaRepository<AnalysisCredit, Long> {

}
