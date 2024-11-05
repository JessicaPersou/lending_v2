package com.postech.lending.creditanalysis.repository;

import com.postech.lending.client.dto.ClientDTO;
import com.postech.lending.client.model.Client;
import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCalculationResultRepository extends JpaRepository<CreditCalculationResult, Long> {

    CreditCalculationResult findCreditCalculationResultByDocumentClient(String documentClient);

}
