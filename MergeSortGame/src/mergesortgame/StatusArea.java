/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesortgame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author asmateus
 */
public class StatusArea extends JPanel {
    
    public final static String HELP_TEXT_FILE_PATH = "data/help.html";
    
    private ArrayList<SimpleUser> top_players = new ArrayList<>();
    
    private GridBagConstraints c = new GridBagConstraints();
    private JPanel help_panel = new JPanel();
    private JEditorPane top_player_disp = new JEditorPane();
    
    public StatusArea() {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[] {1};
        gbl.rowWeights = new double[] {0.5, 0.5, 0};
        
        this.setLayout(gbl);
        this.setBackground(Color.BLACK);
        
        this.createHelpPanel();
        this.printTopPlayers();
    }
    
    private void createHelpPanel() {
        JEditorPane help_text = new JEditorPane();
        help_text.setBackground(Color.BLACK);
        help_text.setFocusable(false);
        help_text.setEditable(false);
        help_text.setEditorKit(new HTMLEditorKit());
        
        try {
            FileReader reader = new FileReader(HELP_TEXT_FILE_PATH);
            help_text.read(reader, HELP_TEXT_FILE_PATH);
        }
        catch(Exception e) {}
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        
        this.add(help_text, c);
    }
    
    public void printTopPlayers() {
        IOManager io = new IOManager("visitor");
        this.top_players = io.getTopPlayers(5);
        String table = this.buildHTMLTable();
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        
        this.top_player_disp.setFocusable(false);
        this.top_player_disp.setEditable(false);
        this.top_player_disp.setContentType("text/html");
        this.top_player_disp.setText(table);
        this.add(this.top_player_disp, c);
    }
    
    private String buildHTMLTable() {
        String table = "<html>";
        table += "<table><tr><th>#</th><th>User</th><th>Level</th><th>Local difficulty</th><th>Deaths</th><th>Score</th></tr>";
        int i = 0;
        for(SimpleUser e: this.top_players) {
            ++i;
            table += "<tr>";
            table += "" + i;
            table += "<td>" + e.getUserName() + "</td>";
            table += "<td>" + e.getLevel() + "</td>";
            table += "<td>" + SimpleUser.transforDifficulty(e.getDiff()) + "</td>";
            table += "<td>" + e.getFailCount() + "</td>";
            table += "<td>" + e.getScore() + "</td>";
            table += "</tr>";
        }
        table += "</table></html>";
        
        return table;
    }
}
