package th.ac.ku.bankaccount.controller;

import org.springframework.web.bind.annotation.*;
import th.ac.ku.bankaccount.data.BankAccountRepository;
import th.ac.ku.bankaccount.model.BankAccount;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {

    private BankAccountRepository repository;

    public BankAccountRestController(BankAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customer/{customerId}")
    public List<BankAccount> getAllCustomerId(@PathVariable int customerId) {
        return repository.findByCustomerId(customerId);
    }

    @GetMapping
    public List<BankAccount> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public BankAccount getOne(@PathVariable int id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @PostMapping
    public BankAccount crete(@RequestBody BankAccount bankAccount) {
        repository.save(bankAccount);
        return bankAccount;
    }

    @PutMapping("/{id}")
    public BankAccount update(@PathVariable int id,
                              @RequestBody BankAccount bankAccount) {
        try {
            BankAccount record = repository.findById(id).get();
            record.setBalance(bankAccount.getBalance());
            repository.save(record);
            return record;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public BankAccount delete(@PathVariable int id) {
        try {
            BankAccount record = repository.findById(id).get();
            repository.deleteById(id);
            return record;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
