package es.repository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import  java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import es.domain.User;
import es.domain.CaptchaSettings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class DaoImpl implements Dao {

    @PersistenceContext
    private EntityManager em;

    public CaptchaSettings getCaptaSettings() {
        CriteriaQuery<CaptchaSettings> criteriaQuery = em.getCriteriaBuilder().createQuery(CaptchaSettings.class);
        Root root = criteriaQuery.from(CaptchaSettings.class);
        criteriaQuery.select(root);
        return em.createQuery(criteriaQuery).getSingleResult();
    }
    @Transactional
    public boolean setCaptaSettings(CaptchaSettings captchaSettings) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate<CaptchaSettings> update = cb.
                createCriteriaUpdate(CaptchaSettings.class);
        Root e = update.from(CaptchaSettings.class);
        update.set("numCharact", captchaSettings.getNumCharact());
        update.set("attemps", captchaSettings.getAttemps());
        update.set("alfa", captchaSettings.isAlfa());
        update.where(cb.equal(e.get("id"), 1));
        this.em.createQuery(update).executeUpdate();
        return true;
    };
    public List<User> getUsers() {
        CriteriaQuery<User> criteriaQuery = em.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.select(criteriaQuery.from(User.class));
        return em.createQuery(criteriaQuery).getResultList();
    }
}