package org.sid.bankaccountservice.services;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;

public interface AccountService {
     BankAccountResponseDTO AddAccount(BankAccountRequestDTO bankAccountDTO);

     BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
