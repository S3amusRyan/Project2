package managedbeans;

import ejb.JobEJB;
import ejb.UserEJB;
import entity.Jobs;
import entity.Provider;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import jakarta.inject.Inject;
import java.util.List;
import jakarta.inject.Named;
import jakarta.annotation.Resource;

@Named(value = "searchJobsBeans")
@SessionScoped

/**
 *
 * @author Seamus Ryan, Niall Herarne, Portia Gannon
 */
public class SearchJobsBeans implements Serializable {

    private static final long serialVersionUID = 4685823449195612778L;

    @Inject
    private JobEJB jobEJB;

    @Inject
    private UserEJB userEJB;

    @Resource
    private jakarta.transaction.UserTransaction utx;
    private String keywords;
    private int jobsid;
    private String providerId;
    private String uniqueId;

    /**
     *
     * @return
     */
    public List<Jobs> findbyKeyword() {
        List<Jobs> jobs = null;
        jobs = jobEJB.findByKeywords(keywords);
        return jobs;
    }

    /**
     *
     * @return
     */
    public List<Jobs> findbyID() {
        List<Jobs> jobs = null;
        System.out.println("TEST");
        jobs = jobEJB.findByID(uniqueId);
        return jobs;
    }

    /**
     *
     * @param status
     * @return
     */
    public List<Jobs> findbyStatus(String status) {
        List<Jobs> jobs = null;
        jobs = jobEJB.findByStatus(status);
        return jobs;
    }

    /**
     *
     * @param provider
     * @return
     */
    public List<Jobs> findJobByProviderId(Provider provider) {
        List<Jobs> jobs = null;
        jobs = userEJB.findJobsById(provider);
        return jobs;
    }

    /**
     *
     * @param jobs
     */
    public void removeJobByProvider(Jobs jobs) {
        userEJB.removeJob(jobs);
    }

    /**
     *
     * @return
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     *
     * @return
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     *
     * @param uniqueId
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     *
     * @return
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     *
     * @param providerId
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

}
