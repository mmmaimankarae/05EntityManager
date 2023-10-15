package lockingdemo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;

public class IncrementPriceThread extends Thread {
    @Override
    public void run() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LockingDemoPU");
        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, 1L);
        Random rand = new Random();
        int num = rand.nextInt(10);
        try {
            Thread.sleep(num * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(IncrementPriceThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* book.setPrice(book.getPrice() + 5); เพิ่มราคาหนังสือแบบไม่Lock */
        em.getTransaction().begin();
        try {
                /* กลไกการLock แบบมองโลกในแง่ดี */
            em.lock(book, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            /* << การทำงานเหมือนบรรทัดบน (Lock ไม่ให้เขียน)
             * em.lock(book, LockModeType.WRITE); */
            
            /* Lock ให้รู้ว่าข้อมูลที่อ่านอยู่เป็นปัจจุบันหรือยัง
             * em.lock(book, LockModeType.OPTIMISTIC);
             * em.lock(book, LockModeType.READ); */
            
                /* กลไกการLock แบบมองโลกในแง่ร้าย */
            /* Lock ไม่ให้เขียน และเพิ่มเลขVersion
             * em.lock(book, LockModeType.PESSIMISTIC_FORCE_INCREMENT); */
            
            /* em.lock(book, LockModeType.PESSIMISTIC_WRITE);
             * em.lock(book, LockModeType.PESSIMISTIC_READ); */
            em.persist(book);
            book.setPrice(book.getPrice() + 5); /* เพิ่มราคาหนังสือ 5 บาท */
            em.getTransaction().commit();     
        } 
        catch (Exception e) { /* แจ้งเตือน */
            System.out.println("failed 5");           
        } finally {
            em.close();
        }
    }
}
