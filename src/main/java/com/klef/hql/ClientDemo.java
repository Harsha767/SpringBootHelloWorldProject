package com.klef.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {

    public static void main(String[] args) {
        // Set up session factory and session
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        Session session = null;

        try {
            // Start a session
            session = factory.getCurrentSession();

            // Begin a transaction
            session.beginTransaction();

            // HQL query to update department details using positional parameters
            String hql = "update Department set name = ?, location = ? where deptId = ?";

            // Create query and set parameters (positional parameters)
            Query query = session.createQuery(hql);
            query.setParameter(1, "Computer Science");
            query.setParameter(2, "Building A");
            query.setParameter(3, 1);

            // Execute update
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
