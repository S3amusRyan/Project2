/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import ejb.UserEJB;
import entity.User;
import entity.Provider;
import entity.Freelancer;
import entity.Jobs;
import java.util.List;
import jakarta.annotation.Resource;
import jakarta.inject.Named;
import ejb.JobEJB;

@Named(value = "removeView")
@SessionScoped

/**
 *
 * @author Seamus Ryan, Niall Herarne, Portia Gannon
 */
public class RemoveView implements Serializable {

    private static final long serialVersionUID = 4685823449195612778L;

    @Inject
    private UserEJB userEJB;

    @Resource
    private jakarta.transaction.UserTransaction utx;

    private String email;

    @Inject
    private JobEJB jobEJB;

    /**
     *
     * @param user
     */
    public void removeUser(User user) {
        userEJB.remove(user); //Call userEJB class to use the defined entity manager.
        //No need to create a new entity manager instance here.
    }

    /**
     *
     * @param jobs
     */
    public void removeJobDescriptions(Jobs jobs) {
        userEJB.removeJob(jobs);
    }

    /**
     *
     * @return
     */
    public User findUserByEmail() {
        User user = null;
        user = userEJB.findUserById(email);
        return user;
    }

    /**
     *
     * @return
     */
    public List<Jobs> findJobDescriptionByEmail() {
        Provider provider = null;
        List<Jobs> jobs = null;
        provider = userEJB.findProviderById(email);
        jobs = userEJB.findJobsById(provider);
        return jobs;
    }

    /**
     *
     * @param user
     * @param jobs
     * @param freelancer
     */
    public void accept(User user, Jobs jobs, Freelancer freelancer) {
        jobEJB.changeJob(jobs, "closed", freelancer);
    }

    /**
     *
     * @param user
     * @param jobs
     * @param freelancer
     */
    public void revoke(User user, Jobs jobs, Freelancer freelancer) {
        jobEJB.changeJob(jobs, "open", freelancer);
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
