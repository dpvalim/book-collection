package br.com.diogo.bookcollection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter @Getter
@Document(collection = "livros")
public class Livro {

    @Id
    private String id;
    private String titulo;
    private String autor;
    private int ano;

}
