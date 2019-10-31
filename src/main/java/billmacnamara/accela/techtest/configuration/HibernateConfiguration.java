package billmacnamara.accela.techtest.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {

    private static HibernateConfiguration instance;
    private Configuration cfg;
    private SessionFactory sessionFactory;

    private HibernateConfiguration() {
        cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/acceladb");
        cfg.setProperty("hibernate.connection.username", "accela_user");
        cfg.setProperty("hibernate.connection.password", "accpwd123");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.addResource("person.hbm.xml");

        sessionFactory = cfg.buildSessionFactory();
    }

    public static synchronized HibernateConfiguration getInstance() {
        if (instance == null) {
            instance = new HibernateConfiguration();
        }
        return instance;
    }

    public Session getSession() {
        Session session = this.sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }

    private void reconnect() {
        this.sessionFactory = cfg.buildSessionFactory();
    }


}