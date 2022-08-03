package org.example.bank.db;

import org.example.bank.model.BankAccount;
import org.example.shared.io.db.Repository;
import org.w3c.dom.Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Optional;

public class BankAccountRepository implements Repository<BankAccount> {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public BankAccountRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("example");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        transaction.begin();
        entityManager.persist(bankAccount);
        transaction.commit();
        return bankAccount;
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BankAccount.class, id));
    }
}