package managedbeans;

import ejb.JobEJB;
import entity.Jobs;
import entity.Provider;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import jakarta.inject.Inject;

@Named(value = "createJobsBean")
@SessionScoped

/**
 *
 * @author Seamus Ryan, Niall Herarne, Portia Gannon
 */
public class CreateJobsBean implements Serializable {

    @Inject
    private JobEJB jobejb;

    private Integer id;
    private String title;
    private String keywords;
    private String description;
    private Double payment;

    /**
     *
     * @param provider
     * @return
     */
    public String register(Provider provider) {
        Jobs jobs = new Jobs(title, keywords, description, payment, "open", provider);
        jobejb.createJob(jobs);
        return "regdone";
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Double getPayment() {
        return payment;
    }

    /**
     *
     * @param payment
     */
    public void setPayment(Double payment) {
        this.payment = payment;
    }

}
