package lockingdemo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LockingDemo {
    public static void main(String[] args) {
            /* สร้างThreads */
        IncrementPriceThread increment1 = new IncrementPriceThread();
        IncrementPriceThread1 increment2 = new IncrementPriceThread1();
        increment2.start();
        increment1.start();     
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LockingDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
