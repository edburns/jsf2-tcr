package com.jsfcompref.trainer.entity.accessor;

import com.jsfcompref.trainer.entity.Event;
import com.jsfcompref.trainer.entity.TrainingSession;
import com.jsfcompref.trainer.entity.User;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.Query;


@ManagedBean(eager = true)
@ApplicationScoped
public class UserRegistry extends AbstractEntityAccessor implements Serializable {


    // <editor-fold defaultstate="collapsed" desc="Accessing and initializing the instance">

    public static UserRegistry getCurrentInstance() {
        UserRegistry result = null;
        Map<String, Object> appMap = FacesContext.getCurrentInstance().
                getExternalContext().getApplicationMap();
        result = (UserRegistry) appMap.get("userRegistry");
        assert(null != result);

        return result;
    }

    @PostConstruct
    public void perApplicationConstructor() {
        try {
            doInTransaction(new PersistenceActionWithoutResult() {

                public void execute(EntityManager em) {
                    Query query = em.createNamedQuery("user.getAll");
                    List<User> results = query.getResultList();
                    if (results.isEmpty()) {
                        populateUsers(em);
                        query = em.createNamedQuery("user.getAll");
                        results = query.getResultList();
                        assert(!results.isEmpty());
                    }
                }
            });
        } catch (EntityAccessorException ex) {
            Logger.getLogger(UserRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void populateUsers(EntityManager em) {

        // the trainers
        em.persist(new User("Jake", "DeJoque", "male", new java.util.Date(), "jake@vtrainer.com", "Premium", "jake", "jake", true));
        em.persist(new User("Frauke", "Fu§nochel", "female", new java.util.Date(), "frauke@vtrainer.com", "Premium", "frauke", "frauke", true));
        em.persist(new User("Andrew", "Abs", "male", new java.util.Date(), "andrew@vtrainer.com", "Premium", "andrew", "andrew", true));

        // the users
        em.persist(new User("Joe", "Fitness", "male", new java.util.Date(), "jfitness@vtrainer.com", "Premium", "jfitness", "iamcool", false));
        em.persist(new User("Scott", "Tiger", "male", new java.util.Date(), "stiger@vtrainer.com", "Medium", "stiger", "welcome", false));
        em.persist(new User("Karen", "Knees", "female", new java.util.Date(), "karen@vtrainer.com", "Premium", "karen", "karen", false));
        em.persist(new User("Gina", "Glutes", "female", new java.util.Date(), "gina@vtrainer.com", "Basic", "gina", "gina", false));
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Reading User instances">

    public DataModel<User> getUsers() {
        DataModel<User> users = new ListDataModel<User>(getUserList());
        return users;
    }

    public User getUserByUserid(final String userid) {
        User result = null;

        // PENDING do a query to get this information, don't iterate over
        // the list
        List<User> users = getUserList();
        for (User user : users) {
            if (user.getUserid().equals(userid)) {
                result = user;
                break;
            }
        }

        return result;
    }

    public User getUserById(final Long id) {
        User result = null;
        try {
            result = doInTransaction(new PersistenceAction<User>() {

                public User execute(EntityManager em) {
                    return em.find(User.class, id);
                }
            });
        } catch (EntityAccessorException ex) {
            Logger.getLogger(UserRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<User> getUserList() {
        List<User> result = Collections.emptyList();
        try {
            result = doInTransaction(new PersistenceAction<List<User>>() {

                public List<User> execute(EntityManager em) {
                    Query query = em.createNamedQuery("user.getAll");
                    List<User> results = query.getResultList();
                    return results;
                }
            });
        } catch (EntityAccessorException ex) {
            Logger.getLogger(UserRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<User> getTrainerList() {
        List<User> result = Collections.emptyList();
        try {
            result = doInTransaction(new PersistenceAction<List<User>>() {

                public List<User> execute(EntityManager em) {
                    Query query = em.createNamedQuery("user.getTrainers");
                    List<User> results = query.getResultList();
                    return results;
                }
            });
        } catch (EntityAccessorException ex) {
            Logger.getLogger(UserRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<User> getTraineesForTrainer(final User trainer) {
        List<User> result = Collections.emptyList();
        try {
            result = doInTransaction(new PersistenceAction<List<User>>() {

                public List<User> execute(EntityManager em) {
                    Query query = em.createNamedQuery("user.getUsersForTrainerId");
                    query.setParameter("theId", trainer.getId());
                    List<User> results = query.getResultList();
                    return results;
                }
            });
        } catch (EntityAccessorException ex) {
            Logger.getLogger(UserRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Writing User instances">

    public void addUser(final User toAdd) throws EntityAccessorException {
        doInTransaction(new PersistenceActionWithoutResult() {

            public void execute(EntityManager em) {
                em.persist(toAdd);
            }
        });
    }

    public void updateUser(final User toUpdate) throws EntityAccessorException {
        doInTransaction(new PersistenceActionWithoutResult() {

            public void execute(EntityManager em) {
                em.merge(toUpdate);
            }
        });
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Reading TrainingSession instances">

    public List<TrainingSession> getTrainingSessionsForUserAndEvent(final User user,
            final Event e) {
        List<TrainingSession> result = null;
        try {
            result = doInTransaction(new PersistenceAction<List<TrainingSession>>() {

                public List<TrainingSession> execute(EntityManager em) {
                    Query query = em.createNamedQuery("trainingSession.getSessionsForUserAndEvent");
                    query.setParameter("theId", user.getId());
                    query.setParameter("eventId", e.getId());
                    List<TrainingSession> results = query.getResultList();
                    return results;
                }
            });
        } catch (EntityAccessorException ex) {
            Logger.getLogger(UserRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Writing TrainingSession instances">

    public void addTrainingSessions(final List<TrainingSession> toAdd) throws EntityAccessorException {
        doInTransaction(new PersistenceActionWithoutResult() {

            public void execute(EntityManager em) {
                for (TrainingSession t : toAdd) {
                    em.persist(t);
                }
            }
        });
    }

    public void updateTrainingSession(final TrainingSession toUpdate) throws EntityAccessorException {
        doInTransaction(new PersistenceActionWithoutResult() {

            public void execute(EntityManager em) {
                em.merge(toUpdate);
            }
        });
    }


    public void removeTrainingSessionForUserAndEvent(final User user,
            final Event e, final TrainingSession trainingSession) {
        try {
            doInTransaction(new PersistenceActionWithoutResult() {

                public void execute(EntityManager em) {
                    em.remove(em.contains(trainingSession) ? trainingSession :
                        em.merge(trainingSession)); // em.contains(r) ? r : em.merge(r)
                    user.getTrainingSessions().remove(trainingSession);
                    em.merge(user);
                }
            });
        } catch (EntityAccessorException ex) {
            Logger.getLogger(UserRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTrainingSessionForUser(final User user,
            final TrainingSession trainingSession) {
        try {
            doInTransaction(new PersistenceActionWithoutResult() {

                public void execute(EntityManager em) {
                    em.merge(trainingSession);
                    em.merge(user);
                }
            });
        } catch (EntityAccessorException ex) {
            Logger.getLogger(UserRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // </editor-fold>



}
