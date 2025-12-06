import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//dabatase connectio
class DatabaseManager {

    private static final String DB_URL =
        "jdbc:postgresql://db.pfwlzhntprnujryamacs.supabase.co:5432/postgres"
      + "?sslmode=require&currentSchema=public";

    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "rozhinasaberi"; //this is my dabatase password lol 

    public static Connection open() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}


class Player {
    int id;
    String first, last, address, postal, province, phone;

    @Override
    public String toString() {
        return id + " | " + first + " " + last;
    }
}

class Game {
    int id;
    String title;

    @Override
    public String toString() {
        return title + " (ID: " + id + ")";
    }
}

class GamePlayRecord {
    String game;
    Date date;
    int score;
}

//accessinf data 
class PlayerRepository {

    List<Player> fetchAllPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();

        String sql =
            "SELECT player_id, first_name, last_name " +
            "FROM public.player ORDER BY player_id";

        try (Connection c = DatabaseManager.open();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Player p = new Player();
                p.id = rs.getInt("player_id");
                p.first = rs.getString("first_name");
                p.last  = rs.getString("last_name");
                players.add(p);
            }
        }
        return players;
    }
}

class GameRepository {

    List<Game> fetchGames() throws SQLException {
        List<Game> games = new ArrayList<>();

        String sql = "SELECT game_id, game_title FROM public.game ORDER BY game_id";

        try (Connection c = DatabaseManager.open();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Game g = new Game();
                g.id = rs.getInt("game_id");
                g.title = rs.getString("game_title");
                games.add(g);
            }
        }
        return games;
    }
}

class GamePlayRepository {

    void addGamePlay(int playerId, int gameId, Date date, int score) throws SQLException {

        String sql =
            "INSERT INTO public.player_game (player_id, game_id, playing_date, score) " +
            "VALUES (?, ?, ?, ?)";

        try (Connection c = DatabaseManager.open();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, playerId);
            ps.setInt(2, gameId);
            ps.setDate(3, date);
            ps.setInt(4, score);
            ps.executeUpdate();
        }
    }

    List<GamePlayRecord> fetchPlayerHistory(int playerId) throws SQLException {
        List<GamePlayRecord> list = new ArrayList<>();

        String sql =
            "SELECT g.game_title, pg.playing_date, pg.score " +
            "FROM public.player_game pg " +
            "JOIN public.game g ON g.game_id = pg.game_id " +
            "WHERE pg.player_id = ? " +
            "ORDER BY pg.playing_date DESC";

        try (Connection c = DatabaseManager.open();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, playerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GamePlayRecord r = new GamePlayRecord();
                    r.game = rs.getString("game_title");
                    r.date = rs.getDate("playing_date");
                    r.score = rs.getInt("score");
                    list.add(r);
                }
            }
        }
        return list;
    }
}

//main app 
public class GameTrackerApp extends JFrame {

    private final PlayerRepository playerRepo = new PlayerRepository();
    private final GameRepository gameRepo = new GameRepository();
    private final GamePlayRepository playRepo = new GamePlayRepository();

    private JComboBox<Player> playerBox;
    private JComboBox<Game> gameBox;
    private JTextField dateField;
    private JTextField scoreField;
    private JTable table;

    public GameTrackerApp() {
        setTitle("Online Game Tracker");
        setSize(900, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(buildInputPanel(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);

        loadDropdowns();
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private JPanel buildInputPanel() {
        JPanel container = new JPanel(new GridLayout(2, 1));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Player:"));
        playerBox = new JComboBox<>();
        row1.add(playerBox);

        row1.add(new JLabel("Game:"));
        gameBox = new JComboBox<>();
        row1.add(gameBox);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField(8);
        row2.add(dateField);

        row2.add(new JLabel("Score (0–1000):"));
        scoreField = new JTextField(5);
        row2.add(scoreField);

        JButton saveBtn = new JButton("Save Game Play");
        JButton reportBtn = new JButton("View Player History");
        row2.add(saveBtn);
        row2.add(reportBtn);

        saveBtn.addActionListener(e -> saveGamePlay());
        reportBtn.addActionListener(e -> loadHistory());

        container.add(row1);
        container.add(row2);
        return container;
    }

    private JScrollPane buildTablePanel() {
        table = new JTable(new DefaultTableModel(
            new Object[]{"Game", "Date", "Score"}, 0
        ));
        return new JScrollPane(table);
    }

    

    private void loadDropdowns() {
        try {
            playerBox.removeAllItems();
            for (Player p : playerRepo.fetchAllPlayers())
                playerBox.addItem(p);

            gameBox.removeAllItems();
            for (Game g : gameRepo.fetchGames())
                gameBox.addItem(g);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Failed to load players or games.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

   
    private void saveGamePlay() {
        try {
            if (playerBox.getSelectedItem() == null ||
                gameBox.getSelectedItem() == null) {
                showError("Please select both a player and a game.");
                return;
            }

            Date playDate;
            try {
                playDate = Date.valueOf(dateField.getText().trim());
            } catch (IllegalArgumentException ex) {
                showError("Date must be in YYYY-MM-DD format.");
                return;
            }

            int score;
            try {
                score = Integer.parseInt(scoreField.getText().trim());
                if (score < 0 || score > 1000)
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                showError("Score must be a number between 0 and 1000.");
                return;
            }

            Player p = (Player) playerBox.getSelectedItem();
            Game g = (Game) gameBox.getSelectedItem();

            playRepo.addGamePlay(p.id, g.id, playDate, score);
            JOptionPane.showMessageDialog(this, "Game play saved successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            showError("Failed to save game play.");
        }
    }

    private void loadHistory() {
        try {
            Player p = (Player) playerBox.getSelectedItem();
            if (p == null) {
                showError("Select a player first.");
                return;
            }

            List<GamePlayRecord> rows = playRepo.fetchPlayerHistory(p.id);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            for (GamePlayRecord r : rows)
                model.addRow(new Object[]{r.game, r.date, r.score});

        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to load report.");
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        new GameTrackerApp();
    }
}

