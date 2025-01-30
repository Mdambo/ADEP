import jakarta.persistence.*;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title", nullable = false)
    private String titulo;

    @Column(name="publication_date")
    private String fechaPubli;

    @Column(name= "genre")
    private String genero;
    
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author autor;
    
    public Book() {}
    
    public Book(String titulo, String fechaPubli, String genero) {
    	this.titulo = titulo;
    	this.fechaPubli = fechaPubli;
    	this.genero = genero;
    }
    
    public void setAuthor(Author autor) {
    	this.autor = autor;
    }
}