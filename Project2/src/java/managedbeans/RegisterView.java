package managedbeans;

import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.inject.Inject;
import ejb.UserEJB;
import entity.User;
import jakarta.inject.Named;

@Named(value = "registerView")
@SessionScoped

/**
 *
 * @author Seamus Ryan, Niall Herarne, Portia Gannon
 */
public class RegisterView implements Serializable {

    private static final long serialVersionUID = 1685823449195612778L;

    @Inject
    private UserEJB userEJB;

    private String name;
    private String email;
    private String userType;
    private String password;
    private String confirmPassword;

    /**
     *
     * @param event
     */
    public void validatePassword(ComponentSystemEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent component = event.getComponent();

        UIInput uiInputPassword = (UIInput) component.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();

        UIInput uiInputConfirmPassword = (UIInput) component.findComponent("confirmpassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }

        String passwordId = uiInputPassword.getClientId();
        String confirmId = uiInputConfirmPassword.getClientId();

        if (!password.equals(confirmPassword)) {
            FacesMessage message = new FacesMessage("Confirm password does not match password");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(confirmId, message);
            context.renderResponse();
        }

        if (userEJB.findUserById(email) != null) {
            FacesMessage message = new FacesMessage("User with this e-mail already exists");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(email, message);
            context.renderResponse();
        }
    }

    /**
     *
     * @return
     */
    public String register() {
        User user = new User(email, password, name);
        userEJB.createUser(user, userType);
        return "regdone";
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     *
     * @return
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     *
     * @param confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     *
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
}
