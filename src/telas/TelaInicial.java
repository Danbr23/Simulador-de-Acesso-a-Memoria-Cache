package telas;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class TelaInicial extends JFrame {
	
	//Variaveis
	
	JComboBox<String> testes = new JComboBox<String>();       
	JComboBox<String> mapeamento = new JComboBox<String>();
	ArrayList<String> enderecos = new ArrayList<String>();
	
	BufferedImage img = null;
	Font pixeledFont;
	File music;
	AudioInputStream audioStream;
	Clip clip;
	
	
	
	public TelaInicial() {
		
		// Tres trycatch seguidos para tentar pegar a musica, a imagem de fundo e a fonte
		
		try {
			music = new File("./sounds/telaInicial.wav");
			audioStream = AudioSystem.getAudioInputStream(music);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			img = ImageIO.read(new File("./imgs/fundo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		try {
			
			pixeledFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonte/PixelMplus10-Regular.ttf")).deriveFont(20f);

		} catch (IOException | FontFormatException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		//Definindo os atributos do JFrame
		
		setSize(800,500);
		setTitle("Simulador de memória cache");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		
		JLabel fundo = new JLabel(); //JLabel que serivará para colocar o plano de fundo
		fundo.setForeground(Color.blue);
		
		Image dimg = img.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		fundo.setIcon(imageIcon);
		
		
		
		JButton iniciar = new JButton("INICIAR"); //JButton que fecha a tela de inicio e abre a tela de configuracoes
		iniciar.setBounds(325, 350, 150, 50);
		iniciar.setFont(pixeledFont);
		iniciar.setForeground(new Color(85, 132, 172));
		iniciar.addActionListener(this::rodar);
		iniciar.setBorder(null);
		iniciar.setFocusable(false);
		fundo.add(iniciar);
		add(fundo);
		
		setVisible(true);
		
		clip.start();
		
	}
	
	// Funcao que para a musica, fecha esta tela e abre a tela configuracoes
	public void rodar(ActionEvent e) {
		// TODO Auto-generated method stub
		clip.stop();
		this.dispose(); 
		Configuracoes configuracao = new Configuracoes();
		
	}
	
}
