package com.postech.lending.creditanalysis.service.impl;

import com.postech.lending.client.model.Client;
import com.postech.lending.client.repository.ClientRepository;
import com.postech.lending.creditanalysis.dto.AnalysisCreditDTO;
import com.postech.lending.creditanalysis.dto.CreditCalculationResultDTO;
import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import com.postech.lending.creditanalysis.model.AnalysisCredit;
import com.postech.lending.creditanalysis.model.InterestRateCalculation;
import com.postech.lending.creditanalysis.repository.AnalysisCreditRepository;
import com.postech.lending.creditanalysis.repository.CreditCalculationResultRepository;
import com.postech.lending.creditanalysis.service.AnalysisCreditService;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class AnalysisCreditServiceImpl implements AnalysisCreditService {

    public final AnalysisCreditRepository analysisCreditRepository;
    public final CreditCalculationResultRepository creditCalculationResultRepository;
    public final ClientRepository clientRepository;

    public AnalysisCreditServiceImpl(AnalysisCreditRepository analysisCreditRepository,
            CreditCalculationResultRepository creditCalculationResultRepository, ClientRepository clientRepository) {
        this.analysisCreditRepository = analysisCreditRepository;
        this.creditCalculationResultRepository = creditCalculationResultRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public CreditCalculationResultDTO processCreditAnalysis(AnalysisCreditDTO analysisCreditDTO) {
//      recebe a analise faz os calculos e retorna o objeto creditcalculationResult
        AnalysisCredit analysisCredit = new AnalysisCredit();

        Client client = clientRepository.findByDocument(analysisCreditDTO.getDocument());
        String clientDocument = client.getDocument();

        analysisCredit.setDocument(clientDocument);
        analysisCredit.setMonthlyIncome(new BigDecimal(analysisCreditDTO.getMonthlyIncome().toString()));
        analysisCredit.setRequestedValue(new BigDecimal(analysisCreditDTO.getRequestedValue().toString()));
        analysisCredit.setNumberInstallment(analysisCreditDTO.getNumberInstallment());
        analysisCredit.setId(analysisCreditDTO.getId());
        analysisCredit.setStatusAnalysis(analysisCreditDTO.getStatusAnalysis());
        analysisCreditRepository.save(analysisCredit);
        analysisCreditDTO.setId(analysisCredit.getId());

        calculatedTotalRequestValue(analysisCredit);

        return calculatedTotalRequestValue(analysisCredit);
    }

    //calcula o valor total do emprestimo
    public CreditCalculationResultDTO calculatedTotalRequestValue(AnalysisCredit analysisCredit) {
        CreditCalculationResult creditCalculationResult = new CreditCalculationResult();
        InterestRateCalculation interestRateCalculation = new InterestRateCalculation();
        //faz os calculos aqui pode chamar o método de calcular parcela
        creditCalculationResult.setCalculationDate(LocalDate.now());
        creditCalculationResult.setDueDate(LocalDate.now().plusDays(2));

        creditCalculationResult.setInstallmentAmount(new BigDecimal(analysisCredit.getNumberInstallment()));
        creditCalculationResult.setInterestRate(new BigDecimal(interestRateCalculation.calculateTotalRate(analysisCredit).toString()));
        creditCalculationResult.setTotalAmount(new BigDecimal(interestRateCalculation.amountToBePaidAfterPeriod(analysisCredit).toString()));

        creditCalculationResultRepository.save(creditCalculationResult);
        creditCalculationResult.setId(creditCalculationResult.getId());

        CreditCalculationResultDTO creditCalculationResultDTO = new CreditCalculationResultDTO();
        creditCalculationResultDTO.setCalculationDate(creditCalculationResult.getCalculationDate());
        creditCalculationResultDTO.setDueDate(creditCalculationResult.getDueDate());
        creditCalculationResultDTO.setInstallmentAmount(creditCalculationResult.getInstallmentAmount());
        creditCalculationResultDTO.setInterestRate(creditCalculationResult.getInterestRate());
        creditCalculationResultDTO.setTotalAmount(creditCalculationResult.getTotalAmount());
        creditCalculationResultDTO.setId(creditCalculationResult.getId());

        return creditCalculationResultDTO;
    }

}
