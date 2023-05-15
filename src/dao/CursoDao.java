/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Curso;
import java.sql.PreparedStatement;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jr_jm
 */
public class CursoDao {
    private Conexao conexao;
    private Connection con;

    public CursoDao() {
        this.conexao = new Conexao();
        this.con = this.conexao.getConexao();
    }

    public void inserir (Curso curso){
        String sql = "INSERT INTO cursos(nomecurso, nivel, duracao) VALUES " +
                "(?,?,?)";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, curso.getNomeCurso());
            stmt.setString(2, curso.getNivel());
            stmt.setInt(3, curso.getDuracao());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao inserir curso"+ e.getMessage());
        }
        
    }
    
    public Curso getCurso(int id){
        String sql = "SELECT * FROM cursos WHERE id = ?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setInt (1,id);
            ResultSet rs = stmt.executeQuery();
            Curso curso = new Curso();
            
            rs.first();
            curso.setId(id);
            curso.setNomeCurso(rs.getString("nomeCurso"));
            curso.setNivel(rs.getString("nivel"));
            curso.setDuracao(rs.getInt("duracao"));
           
            return curso;
        } catch (Exception e) {
        return null;
        }
        
    }
    
    public void editar (Curso curso){
        String sql = "UPDATE cursos SET nomecurso=?, nivel=?, duracao=? WHERE id=?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, curso.getNomeCurso());
            stmt.setString(2, curso.getNivel());
            stmt.setInt(3, curso.getDuracao());
            stmt.setInt(4, curso.getId());
            stmt.execute();
                    
        } catch (Exception e) {
            System.out.println("Erro ao Editar parâmetros! "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM cursos WHERE id=?";
        
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
        } catch (Exception e) {
            System.out.println("Erro ao excluir curso.  "+ e.getMessage());
        }
    }
    
    public List<Curso> getCursos(String nomeCurso){
        String sql = "SELECT * FROM cursos WHERE nomecurso LIKE ?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, "%"  + nomeCurso + "%");
            ResultSet rs = stmt.executeQuery();
            List<Curso> listaCursos = new ArrayList<>();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNomeCurso(rs.getString("nomecurso"));
                curso.setNivel(rs.getString("nivel"));
                curso.setDuracao(rs.getInt("duracao"));
                listaCursos.add(curso);
            }
            return listaCursos;
            
        } catch (Exception e) {
            System.out.println("Não forma encontrados cursos! "+ e.getMessage());
            return null;
        }
        
        
    }
        
}
 