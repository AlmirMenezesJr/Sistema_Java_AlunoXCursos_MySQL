/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author jr_jm
 */
public class Aluno {
    private String nomealuno;
    private int id;
    private Curso cursoid;

    public String getNomeAluno() {
        return nomealuno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomealuno = nomeAluno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCursoId() {
        return cursoid;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoid = cursoId;
    }
    
}
