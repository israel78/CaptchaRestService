package es.service;

import es.captcha.domain.*;
import es.captcha.pojo.ExperienceItemWithStatus;
import es.captcha.repository.Dao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    private Dao dao;

    public CaptchaSettings getCaptchaSettings() {
        return dao.getCaptaSettings();
    }
    public boolean setCaptchaSettings(CaptchaSettings captchaSettings) {
        return dao.setCaptaSettings(captchaSettings);
    }

    public boolean login(String userName, String pass) {
        return null != dao.getUsers().stream()
                .filter(u -> u.getFirstName().equals(userName))
                .filter(u -> u.getPassw().equals(pass)).findAny().orElse(null);
    }
    public String getUserId(String userName) {
         return String.valueOf(dao.getUsers().stream()
                .filter(u -> u.getFirstName().equals(userName))
                .findAny().orElse(null).getId());

    }
    public User getUserById(int id) {
        return dao.getUsers().stream()
                .filter(u -> u.getId()==id)
                .findAny().orElse(null);

    }
    public void saveOrUpdateUser(User user){
        dao.saveOrUpdateUser(user);
    }
    public void saveOrUpdateGraphicAndGraphicData(Graphic graphic){
        dao.mergeGraphic(graphic);
    }
    public List<Experience> getExperiencesByUser(int userId){
        return dao.getExperiencesByUser(userId);
    };
    public List<ExperienceItemWithStatus> getExperiencesListByType(String type){
        List<Experience> experiencesList = dao.getExperiences();
        List<ExperienceItemWithStatus> experienceItemWithStatusList = new ArrayList<>();
        switch(type){
            case "skillsItems":
                List<Set<SkillItem>> skillItemsListsFromUsers = experiencesList.stream()
                        .map(item -> item.getSkillItems())
                        .collect(Collectors.toList());
                List<SkillItem> skillItemsListsFromUsersReduce = skillItemsListsFromUsers.stream()
                        .reduce((list, value) -> {
                            list.addAll(value);
                            return list;
                        }).get().stream().collect(Collectors.toList());
                skillItemsListsFromUsersReduce = skillItemsListsFromUsersReduce.stream().distinct().collect(Collectors.toList());
                List<SkillItem> skillItemsList = dao.getSkillItems();
                List<SkillItem> finalSkillItemsListsFromUsersReduce = skillItemsListsFromUsersReduce;
                skillItemsList.forEach(skillItem ->{
                    AtomicBoolean exists = new AtomicBoolean(false);
                    finalSkillItemsListsFromUsersReduce.forEach(skillItemUsers->{
                        if(skillItem.getName().equals(skillItemUsers.getName()))
                            exists.set(true);
                    });
                    ExperienceItemWithStatus experienceItemWithStatus = new ExperienceItemWithStatus();
                    experienceItemWithStatus.setStatus(exists.get());
                    experienceItemWithStatus.setId(skillItem.getId().intValue());
                    experienceItemWithStatus.setName(skillItem.getName());
                    experienceItemWithStatusList.add(experienceItemWithStatus);
                });
                return experienceItemWithStatusList;
            case "devToolsItems":
                List<DevToolItems> devToolItemsList =  dao.getDevToolsItems();
                List<Set<DevToolItems>> devToolsItemsListsFromUsers = experiencesList.stream()
                        .map(item -> item.getDevToolItems())
                        .collect(Collectors.toList());
                List<DevToolItems> devToolsItemsListsFromUsersReduce = devToolsItemsListsFromUsers.stream()
                        .reduce((list, value) -> {
                            list.addAll(value);
                            return list;
                        }).get().stream().collect(Collectors.toList());
                List<DevToolItems> finalDevToolsItemsListsFromUsersReduce = devToolsItemsListsFromUsersReduce.stream().distinct().collect(Collectors.toList());
                devToolItemsList.forEach(devToolItem ->{
                    AtomicBoolean exists = new AtomicBoolean(false);
                    finalDevToolsItemsListsFromUsersReduce.forEach(devToolItemUsers->{
                        if(devToolItem.getName().equals(devToolItemUsers.getName()))
                            exists.set(true);
                    });
                    ExperienceItemWithStatus experienceItemWithStatus = new ExperienceItemWithStatus();
                    experienceItemWithStatus.setStatus(exists.get());
                    experienceItemWithStatus.setId(devToolItem.getId().intValue());
                    experienceItemWithStatus.setName(devToolItem.getName());
                    experienceItemWithStatusList.add(experienceItemWithStatus);
                });
                return experienceItemWithStatusList;
            case "functionsItems":
                List<JobFunctionItems> functionsItemsList = dao.getJobFunctionsItems();
                List<Set<JobFunctionItems>> functionsItemsListsFromUsers = experiencesList.stream()
                        .map(item -> item.getJobFunctionItems())
                        .collect(Collectors.toList());
                List<JobFunctionItems> functionsItemsListsFromUsersReduce = functionsItemsListsFromUsers.stream()
                        .reduce((list, value) -> {
                            list.addAll(value);
                            return list;
                        }).get().stream().collect(Collectors.toList());
                List<JobFunctionItems> finalFunctionsItemsListsFromUsersReduce = functionsItemsListsFromUsersReduce.stream().distinct().collect(Collectors.toList());
                functionsItemsList.forEach(functionsItems ->{
                    AtomicBoolean exists = new AtomicBoolean(false);
                    finalFunctionsItemsListsFromUsersReduce.forEach(functionsItemsUsers->{
                        if(functionsItems.getName().equals(functionsItemsUsers.getName()))
                            exists.set(true);
                    });
                    ExperienceItemWithStatus experienceItemWithStatus = new ExperienceItemWithStatus();
                    experienceItemWithStatus.setStatus(exists.get());
                    experienceItemWithStatus.setId(functionsItems.getId().intValue());
                    experienceItemWithStatus.setName(functionsItems.getName());
                    experienceItemWithStatusList.add(experienceItemWithStatus);
                });
                return experienceItemWithStatusList;
        }
        return experienceItemWithStatusList;
    }

}

