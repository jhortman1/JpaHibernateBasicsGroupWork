package org.example.bank.db;

import org.example.bank.model.BankAccount;
import org.example.shared.io.db.Repository;
import org.w3c.dom.Entity;

import javax.persistence.EntityManager;
import java.util.Optional;

public class BankAccountRepository implements Repository<BankAccount> {
    private EntityManager entityManager;
    public BankAccountRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public BankAccount save(BankAccount bankAccount) {
        entityManager.persist(bankAccount);
        return bankAccount;
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BankAccount.class, id));
    }
}