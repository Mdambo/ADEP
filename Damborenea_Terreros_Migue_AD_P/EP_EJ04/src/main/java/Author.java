import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.List;

@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name", nullable = false)
    private String nombre;

    @Column(name="last_name", nullable = false)
    private String apellido;

    @Column(name= "birth_date")
    private String cumple;
    
    @Column(name="nationality")
    private String nacional;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> libros;
    
    public Author() {}
	
    public Author(String nombre, String apellido, String cumple, String nacional) {
    	this.nombre = nombre;
    	this.apellido = apellido;
    	this.cumple = cumple;
    	this.nacional = nacional;
    }
    
    public void addLibro(Book libro) {
    	libros.add(libro);
    	libro.setAuthor(this);
    }
    
    public List<Book> getBooks() {
    	return libros;
    }
}
