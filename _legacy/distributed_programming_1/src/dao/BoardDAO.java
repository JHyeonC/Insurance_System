package dao;

import common.Board;
import common.Product;

import java.util.ArrayList;
import java.util.List;

public class BoardDAO extends DAO {

    public BoardDAO(String daoName) {
        super(daoName);
        databaseName = "insurance_system";
        tableName = "boards";
    }

    public boolean addBoard(Board board) {
        String sql = "insert into " + tableName + " values(\'" + board.id + "\', \'" + board.author + "\', \'" + board.title + "\', \'" + board.content + "\', \'"
                + board.answer + "\', \'" + board.answerer + "\', " + board.isAnswered + ");";
        return executeUpdate(sql);
    }

    public List<Board> getBoards() {
        String sql = "select * from " + tableName + ";";
        if (!executeQuery(sql)) return null;
        if(!next()) return null;
        List<Board> boards = new ArrayList<>();
        do {
            Board board = new Board();
            board.id = getString("id");
            board.author = getString("author");
            board.title = getString("title");
            board.content = getString("content");
            board.answer = getString("answer");
            board.answerer = getString("answerer");
            board.isAnswered = getInt("is_answered");
            boards.add(board);
        } while(next());
        return boards;
    }

    public boolean updateBoard(Board board) {
        String sql = "update " + tableName + " set answer=\'" + board.answer + "\', answerer=\'" + board.answerer + "\', is_answered=" + board.isAnswered +
                " where id=\'" + board.id + "\';";
        return executeUpdate(sql);
    }
}
