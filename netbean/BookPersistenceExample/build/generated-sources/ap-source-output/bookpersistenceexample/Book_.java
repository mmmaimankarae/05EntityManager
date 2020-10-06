package bookpersistenceexample;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-02-29T19:12:55")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, Long> id;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, Double> price;
    public static volatile SingularAttribute<Book, Short> illustrations;
    public static volatile SingularAttribute<Book, String> description;
    public static volatile SingularAttribute<Book, String> isbn;
    public static volatile SingularAttribute<Book, Integer> nbofpages;

}