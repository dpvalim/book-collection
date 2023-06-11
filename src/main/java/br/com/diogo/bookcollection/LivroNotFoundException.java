package br.com.diogo.bookcollection;

import java.io.Serial;

public class LivroNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5168278689946991838L;

    public LivroNotFoundException(String livroId) {
        super("Livro não encontrado: " + livroId);
    }
}
