package bookpersistenceexample;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "BOOK")
@XmlRootElement
@NamedQueries({
        /* Named Parameters, Named Queries */
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id"),
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    @NamedQuery(name = "Book.findByPrice", query = "SELECT b FROM Book b WHERE b.price = :price"),
    @NamedQuery(name = "Book.findByDescription", query = "SELECT b FROM Book b WHERE b.description = :description"),
    @NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Book.findByNbofpages", query = "SELECT b FROM Book b WHERE b.nbofpages = :nbofpages"),
    @NamedQuery(name = "Book.findByIllustrations", query = "SELECT b FROM Book b WHERE b.illustrations = :illustrations")})
    @NamedNativeQuery(name="Book.findAllSql", query="select * from book")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)
    //@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "NBOFPAGES")
    private Integer nbofpages;
    @Column(name = "ILLUSTRATIONS")
    private Short illustrations;

    public Book() {
        
    }

    public Book(Long id) {
        this.id = id;
    }

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNbofpages() {
        return nbofpages;
    }

    public void setNbofpages(Integer nbofpages) {
        this.nbofpages = nbofpages;
    }

    public Short getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Short illustrations) {
        this.illustrations = illustrations;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bookpersistenceexample.Book[ id=" + id + " ]";
    }
}
