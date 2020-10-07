import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.html",
                errorPage = "/login.html"))
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:global/jdbc/db",
        callerQuery = "SELECT password FROM USERS WHERE login = ?",
        groupsQuery = "SELECT role FROM USERS WHERE login = ?"
)
@ApplicationScoped
public class AppConfig {
}
