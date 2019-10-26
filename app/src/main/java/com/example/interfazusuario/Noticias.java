package com.example.interfazusuario;

public class Noticias {
    private String titulo;
    private String subtitulo;

    public Noticias(String titulo, String subtitulo) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }
}
