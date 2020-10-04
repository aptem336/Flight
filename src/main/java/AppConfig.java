import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@BasicAuthenticationMechanismDefinition(realmName = "defaultRealm")
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:global/jdbc/db",
        callerQuery = "SELECT password FROM USERS WHERE login = ?",
        groupsQuery = "SELECT role FROM USERS WHERE login = ?"
)
@ApplicationScoped
public class AppConfig {
}
