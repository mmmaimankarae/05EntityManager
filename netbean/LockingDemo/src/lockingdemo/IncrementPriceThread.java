/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lockingdemo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;

/**
 *
 * @author sarun
 */
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
        //book.setPrice(book.getPrice() + 5);
        em.getTransaction().begin();
        try {
            em.lock(book, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            //em.lock(book, LockModeType.WRITE);
            //em.lock(book, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
            //em.lock(book, LockModeType.PESSIMISTIC_WRITE);
            //em.lock(book, LockModeType.PESSIMISTIC_READ);
            //em.lock(book, LockModeType.OPTIMISTIC);
            //em.lock(book, LockModeType.READ);
            em.persist(book);
            book.setPrice(book.getPrice() + 5);
            em.getTransaction().commit();
                 
            } 
            catch (Exception e) {
                System.out.println("failed 5");
                           
            } finally {
                em.close();
            }
        
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistentBookExamplePU");
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
