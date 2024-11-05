package com.postech.lending.contract.service.impl;

import com.postech.lending.client.model.Client;
import com.postech.lending.client.repository.ClientRepository;
import com.postech.lending.common.exception.ErrorMessageException;
import com.postech.lending.contract.dto.ContractDTO;
import com.postech.lending.contract.service.ContractService;
import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import com.postech.lending.creditanalysis.repository.CreditCalculationResultRepository;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    private final ClientRepository clientRepository;
    private final CreditCalculationResultRepository calculationResultRepository;


    public ContractServiceImpl(ClientRepository clientRepository,
            CreditCalculationResultRepository calculationResultRepository) {
        this.clientRepository = clientRepository;
        this.calculationResultRepository = calculationResultRepository;

    }

    @Override
    public String generateContract(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ErrorMessageException("Erro: Cliente não encontrado."));

        String clientDocument = client.getDocument();

        CreditCalculationResult result = calculationResultRepository.findCreditCalculationResultByDocumentClient(clientDocument);

        if (result == null) {
            return "Erro: Resultado de cálculo de crédito não encontrado.";
        }

        ContractDTO contractDTO = new ContractDTO(result);

        String contractDetails = formatContractDetails(contractDTO);

        try (FileWriter writer = new FileWriter("")) {
            writer.write(contractDetails);
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao salvar o contrato em arquivo!";
        }

        return "Contrato gerado com sucesso!";
    }


    private String formatContractDetails(ContractDTO contractDTO) {
        return new StringBuilder()
                .append("Contrato de Lending - Empréstimo Fácil")
                .append("Nome do Cliente: ").append(contractDTO.getClientFullName()).append("\n")
                .append("Documento do Cliente: ").append(contractDTO.getClientDocument()).append("\n")
                .append("Valor do Empréstimo: ").append(contractDTO.getAnalysisAmount()).append("\n")
                .append("Número de Parcelas: ").append(contractDTO.getNumberOfInstallments()).append("\n")
                .append("Detalhes da Primeira Parcela: ").append(contractDTO.getInstallmentDetails()).append("\n")
                .append("Taxa de Juros: ").append(contractDTO.getInterestRate()).append("%\n")
                .append("Data de Início: ").append(contractDTO.getStartDate()).append("\n")
                .append("Data de Limite de Aceite: ").append(contractDTO.getEndDate()).append("\n")
                .append("Status: ").append(contractDTO.getStatus()).append("\n")
                .toString();
    }
}
