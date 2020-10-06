/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Address;
import model.Customer;

/**
 *
 * @author sarun
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Customer customer = new Customer(1L, "Antony", "Balla", "tballa@mail.com"); 
        //Address address = new Address(1L, "Ritherdon Rd", "London", "8QE", "UK"); 
        //address.setCustomerFk(customer);
        //customer.setAddressId(address); 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        //Customer customer = em.getReference(Customer.class, 1L);
        Customer customer = em.find(Customer.class, 1L);
        //customer.setFirstname("Prayuth");
        //customer.setAddressId(null);
        //Address address = em.find(Address.class, 1L);
        //address.setCustomerFk(customer);
        //Address address = customer.getAddressId();
        //address.setCity("Gotham");
        //address.setCustomerFk(null);
        
        em.getTransaction().begin();
        try {
            //em.persist(address);
            //em.persist(customer);
            //em.flush();
            //em.persist(address);
            //em.persist(customer);
            //em.refresh(customer);
            //em.detach(customer);
            //em.persist(customer);
            //em.merge(customer);
            //em.remove(customer);
            //em.persist(customer);
            //em.remove(address);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
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
