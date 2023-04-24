package entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "JOBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j"),
    @NamedQuery(name = "Jobs.findByTitle", query = "SELECT j FROM Jobs j WHERE j.title = :title"),
    @NamedQuery(name = "Jobs.findByKeywords", query = "SELECT j FROM Jobs j WHERE j.keywords = :keywords"),
    @NamedQuery(name = "Jobs.findByID", query = "SELECT j FROM Jobs j WHERE j.jobsId = :jobsId"),
    @NamedQuery(name = "Jobs.findByStatus", query = "SELECT j FROM Jobs j WHERE j.status = :status"),
    @NamedQuery(name = "Jobs.getJobsID", query = "SELECT MAX(j.jobsId) from Jobs j"),
    @NamedQuery(name = "Jobs.getProviderID", query = "SELECT j FROM Jobs j WHERE j.providerId = :providerId")})

/**
 *
 * @author Seamus Ryan, Niall Herarne, Portia Gannon
 */
public class Jobs implements Serializable {

    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @JoinColumn(name = "FREELANCER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Freelancer freelancerId;
    @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Provider providerId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer jobsId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "KEYWORDS")
    private String keywords;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PAYMENT")
    private Double payment;

    /**
     *
     */
    public Jobs() {
    }

    /**
     *
     * @param title
     * @param keywords
     * @param description
     * @param payment
     * @param status
     * @param provider
     */
    public Jobs(String title, String keywords, String description, Double payment,
            String status, Provider provider) {
        /**
         * Job class
         *
         * @param String title: job title
         * @param String keywords: job kwyword
         * @param String description: job description
         * @param Double payment: job payment (eur)
         * @param String status: job status
         * @param Provider provider: job provider
         */
        this.title = title;
        this.keywords = keywords;
        this.description = description;
        this.payment = payment;
        this.status = status;
        this.providerId = provider;
    }

    /**
     *
     * @return
     */
    public Integer getJobsId() {
        return jobsId;
    }

    /**
     *
     * @param jobsId
     */
    public void setJobsId(Integer jobsId) {
        this.jobsId = jobsId;
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

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobsId != null ? jobsId.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.jobsId == null && other.jobsId != null) || (this.jobsId != null && !this.jobsId.equals(other.jobsId))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "entities.Jobs[ jobsId=" + jobsId + " ]";
    }

    /**
     *
     * @param title
     */
    public void setTitle(Jobs title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    public Provider getProviderId() {
        return providerId;
    }

    /**
     *
     * @param providerId
     */
    public void setProviderId(Provider providerId) {
        this.providerId = providerId;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public Freelancer getFreelancerId() {
        return freelancerId;
    }

    /**
     *
     * @param freelancerId
     */
    public void setFreelancerId(Freelancer freelancerId) {
        this.freelancerId = freelancerId;
    }

    /**
     *
     */
    public void removeFreelancerId() {
        this.freelancerId = null;
    }

}
