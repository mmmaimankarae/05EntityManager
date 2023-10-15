package bookpersistenceexample;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class BookPersistenceExample {
    public static void main(String[] args) {
        /* " ส่วนของการset data "
         * Book book = new Book();
         * book.setTitle("The Hitchhiker's Guide to the Galaxy");
         * book.setPrice(12.5);
         * book.setDescription("Science fiction comedy book");
         * book.setIsbn("1-84023-742-2");
         * book.setNbofpages(354);
         * book.setIllustrations(Short.MIN_VALUE);
         * persist(book); */
        
        /* " ส่วนของการหาข้อมูล "
         * List<Book> bookList = queryAllBooks();
         * List<Book> bookList = findByIsbn("1-84023-742-3");
         * List<Book> bookList = findByTitle("The ABC Guide to the Galaxy");
         * List<Book> bookList = queryAllBooksSql();
         * List<Book> bookList = findByTitleCriteria("The ABC Guide to the Galaxy"); */
        List<Book> bookList = findByTitleSql("The ABC Guide to the Galaxy");
        if (bookList != null) {
            showBooks(bookList);
        }
    }
    
    public static void showBooks(List<Book> bookList) {
        for(Book b : bookList) {
            System.out.print(b.getId() + " ");
            System.out.print(b.getDescription() + " ");
            System.out.print(b.getIsbn()+ " ");
            System.out.print(b.getTitle() + " ");
            System.out.print(b.getPrice()+ " ");
            System.out.println();
            
        }
    }
        /* Named Quries */
    public static List<Book> queryAllBooks() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPersistenceExamplePU");
        EntityManager em = emf.createEntityManager();
            /* call name of JPQL command */
        Query query = em.createNamedQuery("Book.findAll");
        List<Book> bookList = (List<Book>) query.getResultList();
        return bookList;
    }
    
    public static List<Book> queryAllBooksSql() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPersistenceExamplePU");
        EntityManager em = emf.createEntityManager();
        Query query =  em.createNamedQuery("Book.findAllSql");
        
        List<Book> bookList = (List<Book>) query.getResultList();
        return bookList;
    }
    
    public static List<Book> findByIsbn(String isbn) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPersistenceExamplePU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Book.findByIsbn");
        query.setParameter("isbn", isbn);
        List<Book> bookList = (List<Book>) query.getResultList();
        return bookList;
    }
        /* Dynamic Queries */
    public static List<Book> findByTitle(String title) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPersistenceExamplePU");
        EntityManager em = emf.createEntityManager();
            /* " Positional Parameters "
         * String jpql = "SELECT b FROM Book b WHERE b.title = ?1";
         * query.setParameter(1, title); */
            
            /* Named Parameters */
        String jpql = "SELECT b FROM Book b WHERE b.title = :title";
        Query query = em.createQuery(jpql);
        query.setParameter("title", title);
        List<Book> bookList = (List<Book>) query.getResultList();
        return bookList;
    }
        /* Native Quries */
    public static List<Book> findByTitleSql(String title) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPersistenceExamplePU");
        EntityManager em = emf.createEntityManager();
        /* String sql = "SELECT * FROM Book WHERE title = ?1";
         * query.setParameter(1, title); */
        
        String sql = "SELECT * FROM Book WHERE title = ?title";
        Query query = em.createNativeQuery(sql, Book.class);
        query.setParameter("title", title);
        
        List<Book> bookList = (List<Book>) query.getResultList();
        return bookList;
    }
        /* Criteria API */   
    public static List<Book> findByTitleCriteria(String title) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPersistenceExamplePU");
        EntityManager em = emf.createEntityManager(); 
            /* Create Setting Criteria API */
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
            /* ดึงข้อมูลจาก Book */
        Root<Book> b = criteria.from(Book.class);
        criteria.select(b);
        criteria.where(builder.equal(b.get("title"), title));
        TypedQuery<Book> query = em.createQuery(criteria);
        
        List<Book> bookList = query.getResultList();
        return bookList;
    }
    
    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPersistenceExamplePU");
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
