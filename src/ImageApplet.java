
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import org.python.util.PythonInterpreter;

public class ImageApplet extends JFrame {
	TextField field = new TextField();
	TextField out = new TextField();
	JLabel titlePanel, bgPanel, heading;
	JLabel imageSearch;
	File image;
	ArrayList<String> output = new ArrayList<String>();
	{
		field.setBounds(220, 290, 150, 25);
		out.setBounds(100, 240, 400, 30);
		out.setSize(400, 30);
		out.setFont(new Font(Font.SANS_SERIF, 18, 18));
	}
	String input = "";
	public ImageApplet(String name)
	{
		setName(name);
		setLayout(null);
		add(field);
		add(out);
		out.setVisible(false);
		titlePanel = new JLabel();
		bgPanel = new JLabel();
		JButton link = new JButton("More Info");
		
		link.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try
				{
					JFrame frame2 = new JFrame();
					frame2.setSize(600, 413);
					JLabel text = new JLabel();
					text.setBounds(0,0,600,413);
					text.setText("<html>" + searchWiki()+ "</html>");
					frame2.add(text);
					frame2.setLocationRelativeTo(null);
					frame2.setVisible(true);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
			}
		});
		link.setBounds(470, 350, 100, 20);
		link.setVisible(false);
		add(link);
		
		JButton submit = new JButton("Guide Me!");
		submit.setBackground(Color.GREEN);
		submit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						input = field.getText();
						request();
						out.setVisible(true);
						try {
							
						    URL imageUrl = new URL(input);
						    Image image = ImageIO.read(imageUrl);
						   
						    image = image.getScaledInstance(100, 100, 100);
						    imageSearch.setIcon(new ImageIcon(image));
						    imageSearch.setVisible(true);
						    JLabel l =  new JLabel(new ImageIcon(image));
						    add(l);
						    link.setVisible(true);
						    
						}
						catch (IOException e) {
						    e.printStackTrace();
						}
						
					}
			
				});
		submit.setBounds(220, 340, 150, 25);
		add(submit);
		
		
		
		
		imageSearch = new JLabel();
		imageSearch.setBounds(75, 100, 100, 100);
		
		add(imageSearch);
		
		titlePanel.setBounds(73, 20, 500, 50);
		bgPanel.setBounds(0,0,600,413);
		heading = new JLabel("Enter your image URL here!");
		heading.setBounds(220, 270, 200, 20);
		add(heading);
		titlePanel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("Logo.png")));
		bgPanel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("background.jpg")));
		add(titlePanel);
		add(bgPanel);

		
	}
	public static void main(String args[])
	{
		ImageApplet img = new ImageApplet("GuidesZoo");
		img.setSize(600, 413);
		img.setVisible(true);
		img.setResizable(false);
		img.setLocationRelativeTo(null);
		
		
		
	}
	
	public String searchWiki() throws UnsupportedEncodingException, IOException
	{
		String google = "http://www.google.com/search?q=";
		String search = out.getText() + "wikipedia";
		String charset = "UTF-8";
		String userAgent = "Mozilla";
		Elements links = Jsoup.connect(google + search).userAgent(userAgent).get().select("h3.r > a");
		String[] urls = new String[links.size()];
		urls[0] = links.get(0).absUrl("href");
		urls[0] = URLDecoder.decode(urls[0].substring(urls[0].indexOf('=') + 1, urls[0].indexOf('&')), "UTF-8");
		String answer = "";
		try
		{
		Document doc = Jsoup.connect(urls[0]).get();
		Elements paragraphs = doc.select(".mw-content-ltr p");
		answer = paragraphs.first().text();
		}
		catch(Exception e)
		{
			System.out.println("Could not connect");
		}
		return answer;
		
	}
	public void request()
	{
		try
		{
			String s = "";
			String path = this.getClass().getResource("meta.py").getPath();
			path = path.substring(1);
			Process p = Runtime.getRuntime().exec("python " + path + " " + input);
			
			BufferedReader stdInput = new BufferedReader(new
					InputStreamReader(p.getInputStream()));
			while ((s = stdInput.readLine()) != null) {
				output.add(s);
			}
			String outtext = "This is a";
			char firstchar = output.get(output.size()-1).charAt(0);
			switch(firstchar)
			{
			case 'a': outtext += "n ";
			break;
			case 'e': outtext += "n ";
			break;
			case 'i': outtext += "n ";
			break;
			case 'o': outtext += "n ";
			break;
			case 'u': outtext += "n ";
			break;
			default: outtext += " ";
			}
			out.setText(outtext + output.get(output.size()-1)+"!");
			if(output.size() == 1)
			{
				out.setText("");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
