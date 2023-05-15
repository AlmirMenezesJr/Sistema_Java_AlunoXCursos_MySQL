/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Aluno;
import beans.Curso;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jr_jm
 */
public class AlunoDao {
     
    private Conexao conexao;
    private Connection con;

    public AlunoDao() {
        this.conexao = new Conexao();
        this.con = this.conexao.getConexao();
    }

    public void inserir (Aluno aluno){
        String sql = "INSERT INTO alunos(nomealuno, cursoid) VALUES " +
                "(?,?)";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, aluno.getNomeAluno());
            stmt.setInt(2, aluno.getCursoId().getId());
            stmt.execute();
            
        } catch (Exception e) {
            System.out.println("Erro ao inserir Aluno"+ e.getMessage());
        }
        
    }
        
    public Aluno getAluno(int id){
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setInt (1,id);
            ResultSet rs = stmt.executeQuery();
            Aluno aluno = new Aluno();
            
            rs.first();
            aluno.setId(id);
            aluno.setNomeAluno(rs.getString("nomealuno"));
            Curso cursoid = new Curso();
            cursoid.setId(rs.getInt("cursoid"));
            aluno.setCursoId(cursoid);
           
            return aluno;
        } catch (Exception e) {
        return null;
        }
        
    }
     
     public List<Aluno> getAlunos(String nomeAl){
         String sql = "SELECT a.id, a.nomealuno, c.nomecurso FROM alunos a "+
                 "INNER JOIN cursos c ON a.cursoid = c.id WHERE a.nomealuno like ?";
         try {
             PreparedStatement stmt = this.con.prepareStatement(sql);
             stmt.setString(1, "%"+ nomeAl+"%");
             ResultSet rs = stmt.executeQuery();
             List<Aluno> lista = new ArrayList<>();
             while(rs.next()){
                 Aluno aluno = new Aluno();
                 Curso curso = new Curso();
                 
                 aluno.setId(rs.getInt("id"));
                 aluno.setNomeAluno(rs.getString("nomealuno"));
                 curso.setNomeCurso(rs.getString("nomecurso"));
                 aluno.setCursoId(curso);
                 
                 lista.add(aluno);
                 
             }
             return lista;
         } catch (Exception e) {
               
        return null;
         }
     }
     
    public void editar (Aluno aluno){
        String sql = "UPDATE alunos SET nomealuno=?, cursoid=? WHERE id=?";
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setString(1, aluno.getNomeAluno());
            stmt.setInt(2, aluno.getCursoId().getId());
            stmt.setInt(3, aluno.getId());
            stmt.execute();
                    
        } catch (Exception e) {
            
            System.out.println("Erro ao Editar parâmetros! "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM alunos WHERE id=?";
        
        try {
            PreparedStatement stmt = this.con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
        } catch (Exception e) {
            System.out.println("Erro ao excluir aluno.  "+ e.getMessage());
        }
    }
    
}
