package es.captcha.repository;

import java.util.ArrayList;
import  java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;

import es.captcha.domain.*;
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
        try {
            session.saveOrUpdate(user);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Transactional
    public void saveOrUpdateGraphicData(GraphicValues graphicsValues){
        Session session = em.unwrap(Session.class);
        session.saveOrUpdate(graphicsValues);
    }

    @Transactional
    public void mergeGraphic(Graphic graphic){
        Session session = em.unwrap(Session.class);
        session.merge(graphic);
    }
    @Transactional
    public List<Experience> getExperiencesByUser(int userId){
        Session session = em.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Experience> cr = cb.createQuery(Experience.class);
        Root<Experience> root = cr.from(Experience.class);
        Join<Experience, User> joinUser = root.join("user", JoinType.INNER);
        cr.select(root).where(cb.equal(joinUser.get("id"), userId));
        Query query = session.createQuery(cr);
        return  query.getResultList();
    }
    @Transactional
    public List<Experience> getExperiences(){
        Session session = em.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Experience> cr = cb.createQuery(Experience.class);
        Root<Experience> root = cr.from(Experience.class);
        cr.select(root);
        Query query = session.createQuery(cr);
        return query.getResultList();
    }
    @Transactional
    public List<DevToolItems> getDevToolsItems(){
        Session session = em.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<DevToolItems> cr = cb.createQuery(DevToolItems.class);
        Root<DevToolItems> root = cr.from(DevToolItems.class);
        cr.select(root);
        Query query = session.createQuery(cr);
        return query.getResultList();
    };
    @Transactional
    public List<JobFunctionItems> getJobFunctionsItems(){
        Session session = em.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<JobFunctionItems> cr = cb.createQuery(JobFunctionItems.class);
        Root<JobFunctionItems> root = cr.from(JobFunctionItems.class);
        cr.select(root);
        Query query = session.createQuery(cr);
        return query.getResultList();
    };
    @Transactional
    public List<SkillItem> getSkillItems(){
        Session session = em.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<SkillItem> cr = cb.createQuery(SkillItem.class);
        Root<SkillItem> root = cr.from(SkillItem.class);
        cr.select(root);
        Query query = session.createQuery(cr);
        return query.getResultList();
    };
    @Transactional
    public void mergeSkillItem(SkillItem skillItem){
        Session session = em.unwrap(Session.class);
        session.merge(skillItem);
    }
    @Transactional
    public void mergeDevToolsItem(DevToolItems devToolItems){
        Session session = em.unwrap(Session.class);
        session.merge(devToolItems);
    }
    @Transactional
    public void mergeFunctionsItem(JobFunctionItems jobFunctionItems){
        Session session = em.unwrap(Session.class);
        session.merge(jobFunctionItems);
    }
    @Transactional
    public void deleteSkillItem(SkillItem skillItem){
        Session session = em.unwrap(Session.class);
        session.remove(session.find(SkillItem.class,skillItem.getId()));
    }
    @Transactional
    public void deleteDevToolItem(DevToolItems devToolItems){
        Session session = em.unwrap(Session.class);
        session.delete(session.find(DevToolItems.class,devToolItems.getId()));
    }
    @Transactional
    public void deleteJobFunctionItem(JobFunctionItems jobFunctionItems){
        Session session = em.unwrap(Session.class);
        session.remove(session.find(JobFunctionItems.class,jobFunctionItems.getId()));
    }
}