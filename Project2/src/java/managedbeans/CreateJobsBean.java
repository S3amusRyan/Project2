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
    
    
    public String register(Provider provider) {
	Jobs jobs = new Jobs(title, keywords, description, payment, "open", provider);
        jobejb.createJob(jobs);
        return "regdone";
	}
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title=title;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id=id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }
     
    
}
