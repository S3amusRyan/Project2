package ejb;
import entity.Jobs;
import entity.Freelancer;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;




@Stateless
public class JobEJB {
    @PersistenceContext(unitName = "FormBasedAuthPU")
    private EntityManager em;
    
    public Jobs createJob(Jobs job){
        /*
            @param Jobs job
            Create a new job in the database
        */
        persist(job);
        return job;
    }
    
public Jobs editJob(Jobs job, String status, Freelancer freelancer) {
    /*
        @param Jobs job
        @param String status
        @param String id
        edit the status and freelancerid of a job in the database
    */
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
        /*
            @param String keywords
            Find a job inside the database, searching by keyword
        */
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.findByKeywords", Jobs.class);
            query.setParameter("keywords", keywords);
            List<Jobs> jobs = null;
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {}
            return jobs;
    }
    
        public List<Jobs> findByID(String ID){
        /*
            @param Integer ID
            Find a job inside the database, searching by its ID
        */
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
            /*
                @param String status 
                Find jobs inside the database by status
            */
        
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.findByStatus", Jobs.class);
            
            query.setParameter("status", status);
            List<Jobs> jobs= null;
            
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {}
            return jobs;
    }

    
}
