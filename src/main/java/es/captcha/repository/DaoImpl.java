package es.captcha.repository;

import  java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import es.captcha.domain.User;
import es.captcha.domain.CaptchaSettings;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@PropertySource("classpath:variables.properties")
@Repository
public class DaoImpl implements Dao {

    @PersistenceContext
    private EntityManager em;

    @Value("${default.id.settings}")
    private String SETTINGS_ID;

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
        update.where(cb.equal(e.get("id"), Integer.parseInt(SETTINGS_ID)));
        this.em.createQuery(update).executeUpdate();
        return true;
    };
    public List<User> getUsers() {
        CriteriaQuery<User> criteriaQuery = em.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.select(criteriaQuery.from(User.class));
        return em.createQuery(criteriaQuery).getResultList();
    }
    @Transactional
    public void saveOrUpdateUser(User user){
        Session session = em.unwrap(Session.class);
        session.saveOrUpdate(user);
    }
}