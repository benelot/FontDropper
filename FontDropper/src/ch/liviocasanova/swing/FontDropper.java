package ch.liviocasanova.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import javax.swing.*;

public class FontDropper {

	public static Font font;
	public static int dotsize;
	public static long printMillis;
	public static String writtenString;
	private JTextArea msgArea;
	private JScrollPane msgScroller;
	private Timer timer;
	private Random r;
	private int WINDOW_WIDTH = 800;
	private int WINDOW_HEIGHT = 600;
	private int FONT_SIZE = 40;
	private int PRINT_DELAY_MILLIS = 100;

	public FontDropper() {
		getPropValues();
		OTFFont otffont = new OTFFont();
		font = otffont.grabFont("LivioCasanova.otf", FONT_SIZE);
		r = new Random();
	}

	private ActionListener timerAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ae) {
			int[] characterPositions = { 65, 66, 67, 68, 69, 70, 71, 72, 73,
					74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88,
					89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107,
					108 };
			char c = (char) (characterPositions[r
					.nextInt(characterPositions.length)]);
			msgArea.append(String.valueOf(c));
		}
	};
	
	
	public void getPropValues() {
		 
		Properties prop = new Properties();
		String propFileName = "config.properties";
 
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(propFileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				System.out.println("property file '" + propFileName + "' not found in the classpath");
			}
		}
		// get the property value and print it out
		WINDOW_WIDTH = Integer.parseInt(prop.getProperty("windowwidth"));
		WINDOW_HEIGHT = Integer.parseInt(prop.getProperty("windowheight"));
		FONT_SIZE = Integer.parseInt(prop.getProperty("fontsize"));
		PRINT_DELAY_MILLIS = Integer.parseInt(prop.getProperty("delay"));
	}

	private void appendCharacter() {
		JFrame frame = new JFrame("Livio Casanova: Font Dropper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(1024, 786));
		contentPane.setLayout(new BorderLayout(5, 5));

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 1, 5, 5));

		msgArea = new JTextArea(WINDOW_WIDTH, WINDOW_HEIGHT);
		msgArea.setWrapStyleWord(true);
		msgArea.setLineWrap(true);
		msgArea.setFont(font);

		msgScroller = new JScrollPane();
		msgScroller.setBorder(BorderFactory.createTitledBorder("Messages"));
		msgScroller.setViewportView(msgArea);
		msgArea.setBackground(new Color(230, 230, 230));

		centerPanel.add(msgScroller);

		contentPane.add(centerPanel, BorderLayout.CENTER);

		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		timer = new Timer(PRINT_DELAY_MILLIS, timerAction);
		timer.start();
	}

	public static void main(String[] a) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FontDropper().appendCharacter();
			}
		});
	}

}
