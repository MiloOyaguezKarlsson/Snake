package com.milo.snake;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLHandler {
    public static void postScore(String nick, int score) {
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO score VALUES (NULL, ?, ?)");
            stmt.setString(1, nick);
            stmt.setInt(2, score);
            stmt.executeUpdate();

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Score> getScoreList() {
        List<Score> scoreList = new ArrayList<Score>();
        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT nickname, score FROM score ORDER BY score DESC LIMIT 10");
            ResultSet result = stmt.executeQuery();

            while(result.next()){
                scoreList.add(new Score(result.getString("nickname"), result.getInt("score")));
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return scoreList;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/snake_db";
        String user = "root";
        String pass = "";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }
}
