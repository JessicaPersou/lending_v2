package com.postech.lending.creditanalysis.controller;

import com.postech.lending.creditanalysis.dto.AnalysisCreditDTO;
import com.postech.lending.creditanalysis.dto.CreditCalculationResultDTO;
import com.postech.lending.creditanalysis.service.AnalysisCreditService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis-credit")
public class AnalysisCreditController {

    private AnalysisCreditService analysisCreditService;

    public AnalysisCreditController(AnalysisCreditService analysisCreditService) {
        this.analysisCreditService = analysisCreditService;
    }

    @PostMapping("/create")
    public CreditCalculationResultDTO create(@RequestBody AnalysisCreditDTO analysisCredit) {
        return analysisCreditService.processCreditAnalysis(analysisCredit);
    }
}
