package br.com.zupacademy.rafaela.proposta.utils.transactions;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.function.Supplier;

@Component
public class TransactionExecutor {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public <T> T saveAndCommit(T object) {
        manager.persist(object);
        return object;
    }

    @Transactional
    public <T> T updateAndCommit(T object) {
        return manager.merge(object);
    }

    @Transactional
    public <T> T execute(Supplier<T> codeWithReturn){
        return codeWithReturn.get();
    }
}
