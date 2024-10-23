package com.postech.lending.creditanalysis.service.impl;

import com.postech.lending.client.model.Client;
import com.postech.lending.client.repository.ClientRepository;
import com.postech.lending.client.service.impl.ClientServiceImpl;
import com.postech.lending.creditanalysis.dto.AnalysisCreditDTO;
import com.postech.lending.creditanalysis.dto.CreditCalculationResultDTO;
import com.postech.lending.creditanalysis.model.AnalysisCredit;
import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import com.postech.lending.creditanalysis.model.Installment;
import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import com.postech.lending.creditanalysis.repository.AnalysisCreditRepository;
import com.postech.lending.creditanalysis.repository.CreditCalculationResultRepository;
import com.postech.lending.creditanalysis.repository.InstallmentRepository;
import com.postech.lending.creditanalysis.service.AnalysisCreditService;
import com.postech.lending.creditanalysis.service.calculator.InstallmentsCalculator;
import com.postech.lending.creditanalysis.service.calculator.InterestRateCalculator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AnalysisCreditServiceImpl implements AnalysisCreditService {

    public final AnalysisCreditRepository analysisCreditRepository;
    public final CreditCalculationResultRepository creditCalculationResultRepository;
    public final ClientRepository clientRepository;
    public final InstallmentRepository installmentRepository;

    public AnalysisCreditServiceImpl(AnalysisCreditRepository analysisCreditRepository,
            CreditCalculationResultRepository creditCalculationResultRepository, ClientRepository clientRepository, InstallmentRepository installmentRepository) {
        this.analysisCreditRepository = analysisCreditRepository;
        this.creditCalculationResultRepository = creditCalculationResultRepository;
        this.clientRepository = clientRepository;
        this.installmentRepository = installmentRepository;
    }

    @Override
    public CreditCalculationResultDTO processCreditAnalysis(AnalysisCreditDTO analysisCreditDTO) {
        AnalysisCredit analysisCredit = new AnalysisCredit();

        Client client = clientRepository.findByDocument(ClientServiceImpl.removeCaracterDocument(analysisCreditDTO.getDocument()));
        String clientDocument = client.getDocument();

        analysisCredit.setDocument(clientDocument);
        analysisCredit.setMonthlyIncome(new BigDecimal(analysisCreditDTO.getMonthlyIncome().toString()));
        analysisCredit.setRequestedValue(new BigDecimal(analysisCreditDTO.getRequestedValue().toString()));
        analysisCredit.setNumberInstallment(analysisCreditDTO.getNumberInstallment());
        analysisCredit.setId(analysisCreditDTO.getId());
        analysisCredit.setStatusAnalysis(StatusAnalysisEnum.PRE_APPROVED);
        analysisCredit.setClientId(client);
        analysisCreditRepository.save(analysisCredit);
        analysisCreditDTO.setId(analysisCredit.getId());

        return calculatedTotalRequestValue(analysisCredit);
    }

    public CreditCalculationResultDTO calculatedTotalRequestValue(AnalysisCredit analysisCredit) {
        CreditCalculationResult creditCalculationResult = new CreditCalculationResult();
        InterestRateCalculator interestRateCalculator = new InterestRateCalculator();
        InstallmentsCalculator installments = new InstallmentsCalculator();

        creditCalculationResult.setInterestRate(new BigDecimal(
                interestRateCalculator.calculateTotalRate(analysisCredit).toString()));
        creditCalculationResult.setTotalAmount(new BigDecimal(
                interestRateCalculator.amountToBePaidAfterPeriod(analysisCredit).toString()));
        creditCalculationResult.setDocumentClient(analysisCredit.getDocument());
        creditCalculationResult.setNameClient(
                analysisCredit.getClientId().getFirstName() + " " + analysisCredit.getClientId().getLastName());
        creditCalculationResult.setInstallmentNumber(analysisCredit.getNumberInstallment());
        creditCalculationResult.setCalculationDate(LocalDate.now());
        creditCalculationResult.setAnalysisExpirationDate(LocalDate.now().plusDays(2));
        creditCalculationResult.setInstallmentsList(installments.calculateInstallments(creditCalculationResult));
        creditCalculationResult.setTotalInterestPercentage(interestRateCalculator.calculateTotalPercentage(analysisCredit));
        creditCalculationResult.setTotalInterestPaid(interestRateCalculator.calculateTotalRate(analysisCredit));
        creditCalculationResult.setAnalysisStatusDescription(StatusAnalysisEnum.APPROVED);
        creditCalculationResult.setAnalysisCreditId(analysisCredit);
        creditCalculationResultRepository.save(creditCalculationResult);

        List<Installment> installmentList = creditCalculationResult.getInstallmentsList();

        return new CreditCalculationResultDTO(creditCalculationResult, installmentList);
    }



}
