package br.com.diogo.bookcollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Livro getLivroById(String id) {
        return livroRepository.findById(id).orElse(null);
    }

    public Livro createLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro updateLivro(String id, Livro livro) {
        Livro livroExistente = livroRepository.findById(id).orElseThrow(() -> new LivroNotFoundException(id));

        livroExistente.setTitulo(livro.getTitulo());
        livroExistente.setAutor(livro.getAutor());
        livroExistente.setAno(livro.getAno());

        return livroRepository.save(livroExistente);
    }

    public void deleteLivro(String id) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new LivroNotFoundException(id));
        livroRepository.delete(livro);
    }

    public void marcarComoFavorito(String livroId) {
        Livro livro = livroRepository.findById(livroId).orElseThrow(() -> new LivroNotFoundException(livroId));
        livro.setFavorito(true);
        livroRepository.save(livro);
    }

    public void desmarcarComoFavorito(String livroId) {
        Livro livro = livroRepository.findById(livroId).orElseThrow(() -> new LivroNotFoundException(livroId));
        livro.setFavorito(false);
        livroRepository.save(livro);
    }
}
