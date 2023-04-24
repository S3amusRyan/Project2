package managedbeans;

import java.io.Serializable;
import java.util.Map;
import jakarta.faces.application.FacesMessage;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ejb.UserEJB;
import entity.Freelancer;
import entity.Provider;
import entity.User;
import jakarta.inject.Named;
import utils.AuthenticationUtils;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Named(value = "loginView")
@SessionScoped

/**
 *
 * @author Seamus Ryan, Niall Herarne, Portia Gannon
 */
public class LoginView implements Serializable {

    private static final long serialVersionUID = 3254181235309041386L;

    @Inject
    private UserEJB userEJB;

    private String email;
    private String password;

    private User user;
    private Provider provider;
    private Freelancer freelancer;

    private int tries = 0;

    /**
     *
     * @return
     *
     * Function to login user, checks what type user is and sends them to the
     * according page
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        this.user = userEJB.findUserById(email);

        if (this.user == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No user with that email exists", null));
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            return "signin";
        }

        try {
            if (!this.user.getPassword().equals(AuthenticationUtils.encodeSHA256(password))) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect password", null));
                ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
                tries++;
                return "signin";
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("UTF-8 is not supported in the system.");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA256 is not supported in the system.");
        }

        tries = 0;

        System.out.println("Logged in user: " + user.getName() + " (" + user.getEmail() + ")");

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("User", user);

        this.provider = userEJB.findProviderById(user.getEmail());
        this.freelancer = userEJB.findFreelancerById(user.getEmail());

        if (this.provider != null) {
            this.provider = userEJB.findProviderById(user.getEmail());
            return "/provider/privatepage?faces-redirect=true";
        } else if (this.freelancer != null) {
            this.freelancer = userEJB.findFreelancerById(user.getEmail());
            return "/freelancer/privatepage?faces-redirect=true";
        } else {
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            return "/admin/adminpage?faces-redirect=true";
        }
    }

    /**
     *
     * @return
     *
     * Function used to logout user
     */
    public String logout() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("logging out");
        try {
            this.user = null;
            request.logout();
            // clear the session
            HttpSession sess = (HttpSession) context.getExternalContext().getSession(false);
            if (sess != null) {
                ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            }
        } catch (ServletException e) {
        }
        return "/signin?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public User getAuthenticatedUser() {
        return user;
    }

    /**
     *
     * @return
     */
    public Provider getAuthenticatedProvider() {
        return provider;
    }

    /**
     *
     * @return
     */
    public Freelancer getAuthenticatedFreelancer() {
        return freelancer;
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

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
