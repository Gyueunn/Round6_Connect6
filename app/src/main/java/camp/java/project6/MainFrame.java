package camp.java.project6;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import camp.java.project6.Board.FillBaduk;

public class MainFrame extends JFrame {
	private Board board;
	private JButton start, reset;
	private JLabel block;
	private JPanel back;
	private JTextField blockNum;
	private int blockCount;
	
	public MainFrame() {
		setTitle("Connect6");
		setLayout(null);
		setBounds(0, 0, 1440, 800);
		setLocationRelativeTo(null);
		ButtonPanel();
		board = new Board();
		backPanel();
		this.add(back);
		this.add(board);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
	public void backPanel() {
		back = new JPanel();
		back.setBounds(40, 50, 620, 120);
		back.setBackground(Color.WHITE);
		back.add(board.makeLabel());
	}
	public void ButtonPanel() {
		this.setLayout(null);
		block = new JLabel("Block's number : ");
		block.setFont(new Font("Arial", Font.PLAIN, 30));
		block.setBounds(170, 230, 300, 70);
		
		this.add(block);
		
		blockNum = new JTextField("");
		blockNum.setFont(new Font("Arial", Font.PLAIN, 30));
		blockNum.setBounds(420, 240, 100, 50);
		this.add(blockNum);
		
		start = new JButton("Start");
		start.setFont(new Font("Arial", Font.PLAIN, 30));
		start.setBounds(220, 350, 200, 50);
		start.addActionListener(listener);
		this.add(start);
		
		reset = new JButton("Reset");
		reset.setFont(new Font("Arial", Font.PLAIN, 30));
		reset.setBounds(220, 440, 200, 50);
		reset.setEnabled(false);
		reset.addActionListener(listener);
		this.add(reset);
	}
	
	ActionListener listener = new ActionListener() {
		@Override
        public void actionPerformed(ActionEvent e) {
        	String input = e.getActionCommand();
        	if(input.equals("Start")) {
        		if(!blockNum.getText().equals("")) {
        			reset.setEnabled(true);
            		board.getTurn().setText("Setting Blocks");
            		try {
            			blockCount = Integer.parseInt(blockNum.getText());
            			board.setBlockCount(blockCount);
            		} catch (Exception e1) {
            			
            		}
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "착수돌의 수를 먼저 입력하세요!!", "MESSAGE", JOptionPane.PLAIN_MESSAGE);
        		}
	       	}
	       	else if(input.equals("Reset")) {
	       		int deleteFill [][] = new int [19][19];
	       		board.setBadukArrayList(null);
	       		for(int i=0; i<19; i++) {
	    			for(int j=0; j<19; j++) {
	    				deleteFill[i][j] = -1;
	    			}
	    		}
	       		board.setFill(deleteFill);
	       		new MainFrame();
		    }
        }
	};
	
	
}
