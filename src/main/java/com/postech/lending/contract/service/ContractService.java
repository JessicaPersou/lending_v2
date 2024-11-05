package com.postech.lending.contract.service;

import com.postech.lending.contract.dto.ContractDTO;

public interface ContractService {

    String generateContract(Long clientId);
}
