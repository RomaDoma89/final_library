package team2.spring.library;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

  private static SessionFactory sessionFactory;
  private static String TAG = "Main";

  public static void main(String[] args) {
    Configuration cfg =
        new Configuration()
            .addAnnotatedClass(team2.spring.library.entities.Book.class)
            .addAnnotatedClass(team2.spring.library.entities.Author.class)
            .addAnnotatedClass(team2.spring.library.entities.Copy.class)
            .addAnnotatedClass(team2.spring.library.entities.Reader.class)
            .addAnnotatedClass(team2.spring.library.entities.Story.class)
            .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
            .setProperty(
                "hibernate.connection.url",
                "jdbc:mysql://localhost:3306/library_spring?createDatabaseIfNotExist=true&serverTimezone=UTC")
            .setProperty("hibernate.current_session_context_class", "thread")
            .setProperty("hibernate.connection.username", "root")
            .setProperty("hibernate.connection.password", "root")
            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
            .setProperty("hibernate.show_sql", "true")
            .setProperty("hibernate.format_sql", "true")
            .setProperty("hibernate.hbm2ddl.auto", "update");

    // Create SessionFactory
    sessionFactory = cfg.buildSessionFactory();
    sessionFactory.getCurrentSession().getTransaction().begin();

    Log.debug(TAG, "Create spring_library scheme!");

    sessionFactory.getCurrentSession().getTransaction().commit();
  }
}
