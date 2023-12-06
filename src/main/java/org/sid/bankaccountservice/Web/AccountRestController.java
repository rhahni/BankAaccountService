package org.sid.bankaccountservice.Web;

import org.sid.bankaccountservice.Repositories.BankAccountRepository;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format(" Account %s not found " ,id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.AddAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update (@PathVariable String id,BankAccount bankAccount ) {
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
                if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
                if(bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
                if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
                if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping ("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
         bankAccountRepository.deleteById(id);
    }
}
