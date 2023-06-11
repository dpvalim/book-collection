package br.com.diogo.bookcollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> getAllLivros() {
        return livroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivroById(@PathVariable String id) {
        Livro livro = livroService.getLivroById(id);

        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(livro);
    }

    @PostMapping
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.createLivro(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateLivro(@PathVariable String id, @RequestBody Livro livro) {
        try {
            Livro livroAtualizado = livroService.updateLivro(id, livro);
            return ResponseEntity.ok(livroAtualizado);
        } catch (LivroNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable String id) {
        try {
            livroService.deleteLivro(id);
            return ResponseEntity.noContent().build();
        } catch (LivroNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{livroId}/favorito")
    public ResponseEntity<Void> marcarComoFavorito(@PathVariable String livroId) {
        livroService.marcarComoFavorito(livroId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{livroId}/favorito")
    public ResponseEntity<Void> desmarcarComoFavorito(@PathVariable String livroId) {
        livroService.desmarcarComoFavorito(livroId);
        return ResponseEntity.ok().build();
    }
}

