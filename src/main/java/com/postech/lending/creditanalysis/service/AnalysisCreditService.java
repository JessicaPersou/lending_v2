package com.postech.lending.creditanalysis.service;

import com.postech.lending.creditanalysis.dto.AnalysisCreditDTO;
import com.postech.lending.creditanalysis.dto.CreditCalculationResultDTO;

public interface AnalysisCreditService {
    CreditCalculationResultDTO processCreditAnalysis (AnalysisCreditDTO analysisCreditDTO);

}
