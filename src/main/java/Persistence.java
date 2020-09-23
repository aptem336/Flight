import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataSourceDefinition(name = "java:global/jdbc/db",
        className = "org.apache.derby.jdbc.EmbeddedDriver",
        url = "jdbc:derby:D:\\flight;create=true"
)
public class Persistence {
    @Produces
    @PersistenceContext(unitName = "pu")
    private EntityManager em;
}
