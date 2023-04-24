package ejb;
import entity.Jobs;
import entity.Freelancer;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author Seamus Ryan, Niall Herarne, Portia Gannon
 */
@Stateless
public class JobEJB {
    @PersistenceContext(unitName = "FormBasedAuthPU")
    private EntityManager em;
    
    public Jobs createJob(Jobs job){
        persist(job);
        return job;
    }
    
public Jobs changeJob(Jobs job, String status, Freelancer freelancer) {
    Jobs updatedJob = em.find(Jobs.class, job.getJobsId());
    if ((updatedJob != null) && ("closed".equals(status))){
        updatedJob.setStatus(status);
        updatedJob.setFreelancerId(freelancer);
        em.merge(updatedJob);
    }
    if ((updatedJob != null) && ("open".equals(status))){
        updatedJob.setStatus(status);
        updatedJob.removeFreelancerId();
        em.merge(updatedJob);
    }
    return updatedJob;
}


    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Jobs> findByKeywords(String keywords){
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.findByKeywords", Jobs.class);
            query.setParameter("keywords", keywords);
            List<Jobs> jobs = null;
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {}
            return jobs;
    }
    
        public List<Jobs> findByID(String ID){
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.findByID", Jobs.class);
            int i = 0;
            System.out.println("ID is = " + ID);
            if(ID != null && !ID.equals("")){
                i = Integer.parseInt(ID);
            }   
            query.setParameter("jobsId", i);
            List<Jobs> jobs = null;
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {}
            return jobs;
    }
    
    
    public List<Jobs> findByStatus(String status){
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.findByStatus", Jobs.class);
            
            query.setParameter("status", status);
            List<Jobs> jobs= null;
            
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {}
            return jobs;
    }

    
}
