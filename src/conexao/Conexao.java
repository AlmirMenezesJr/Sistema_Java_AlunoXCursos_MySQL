/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author jr_jm
 */

public class Conexao {
    
    public Connection getConexao(){
        try {
           Connection con = DriverManager.getConnection(
           "jdbc:mysql://localhost:3308/projetojava?serverTimezone=UTC",
           "root",
           ""
           );
            System.out.println("Conexão estabelecida com sucesso!");
           return con;
        } catch (SQLException e) {
            System.out.println("Erro ao estabelecer conexão"+ e.getMessage());
            return null;
        }
        
    }
}
