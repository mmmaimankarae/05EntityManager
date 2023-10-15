package entitymanagerdemo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Address;
import model.Customer;

public class EntityManagerDemo {

    public static void main(String[] args) {
        /* tredsafe: การแชร์กันในหลายๆcomponent อาจจะต้องดูแลด้วยตัวเอง */
        
            /* "APPLICATIONS - MANAGED": ส่วนของการสร้าง Entity manager */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        
            /* "ส่วนของการสร้างข้อมูล" */
        /* Customer customer = new Customer(1L, "Antony", "Balla", "tballa@mail.com"); 
        Address address = new Address(1L, "Ritherdon Rd", "London", "8QE", "UK"); 
        address.setCustomerFk(customer);
        customer.setAddressId(address); */
            
            /* " ส่วนของการค้นข้อมูล " 
        * Customer customer = em.find(Customer.class, 1L); */
        Customer customer = em.getReference(Customer.class, 1L);

        /* customer.setFirstname("Prayuth");
         * customer.setAddressId(null);
                *****
         * Address address = em.find(Address.class, 1L);
         * address.setCustomerFk(customer);
         * Address address = customer.getAddressId();
         * address.setCity("Gotham"); */
        
            /* " ลบข้อมูลในcustomer " แบบการปลดความสัมพันธ์
         * address.setCustomerFk(null); */
        
        em.getTransaction().begin();
        try {
            /*  " ส่วนของการสร้างข้อมูล "
             * address (Owning Side) มีForeign Key เป็นcustomer 
             * ถ้าทำบางอย่างกับ address โดยไม่มีcustomer ไม่ได้ 
             * วางลำดับไหนก่อนหลังก็ได้ เพราะ persist คือ ยังไม่ได้เขียนข้อมูลลงฐานข้อมูล
                    *****
             * em.persist(address);
             * em.persist(customer); */
            
            /* " สั่งให้เขียนข้อมูลลงตาราง (ไม่รอcommit) "
             * em.flush(); */
            
            /* " เอาข้อมูลที่เตรียมลงตารางมาใส่คืน 'บางตัว' " 
             * em.persist(address);
             * em.persist(customer);
             * em.refresh(customer); << ตรงข้ามกับflush */
            
            /* " ถอนObject ที่persist (สถานะmanage) 
             * กลับมาเป็นObject ธรรมดา (สถานะdetached) 'ทุกตัว' "
             * em.clear(); */
            
            /* " ถอนObject ที่persist (สถานะmanage) 
             * กลับมาเป็นObject ธรรมดา (สถานะdetached) 'บางตัว' "
             * em.detach(customer); */
            
            /* " เอาObject ที่อยู่ในสถานะdetached กลับไปอยู่ในสถานะmanage "
             * em.persist(customer); ' Error Detached >> Manage '
             * em.merge(customer); ' ต้องทำวิธีนี้ ' */
            
            /* ลบข้อมูลไม่ได้ เพราะยังมีaddress ที่อ้างถึงkeyในcustomer
             * ถ้าอยากลบจริงๆ ก็set CascadeType.REMOVE (Address จะถูกลบไปด้วย)
             * em.remove(customer); */
            
            //em.persist(customer);
            /* ลบAddress แล้วCustomer จะถูกลบด้วย เพราะไม่มีความสัมพันธ์แล้ว orphanRemoval
             * em.remove(address); */
            
            /* ส่วนที่เขียนข้อมูลลงฐานข้อมูล */
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
