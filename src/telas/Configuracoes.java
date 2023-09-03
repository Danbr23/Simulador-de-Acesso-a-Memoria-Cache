package telas;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class Configuracoes extends JFrame {
	
	//Variaveis
		
	JComboBox<String> testes = new JComboBox<String>();
	JComboBox<String> mapeamento = new JComboBox<String>();
	ArrayList<String> enderecos = new ArrayList<String>();
	
	BufferedImage img = null;
	Font pixeledFont;
	File music;
	AudioInputStream audioStream;
	Clip clip;
	
	public Configuracoes() {
		
		// Tres trycatch seguidos para tentar pegar a musica, a imagem de fundo e a fonte
		
		try {
			music = new File("./sounds/04-Wandering-the-Plains.wav");
			audioStream = AudioSystem.getAudioInputStream(music);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			img = ImageIO.read(new File("./imgs/fundoConfig.jpg"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		try {
			
			pixeledFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonte/PixelMplus10-Regular.ttf")).deriveFont(25f);

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
		
		JLabel fundo = new JLabel();
		
		Image dimg = img.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		fundo.setIcon(imageIcon);
		
		JLabel opcoes_Teste = new JLabel("Arquivo de teste:");
		opcoes_Teste.setFont(pixeledFont);
		opcoes_Teste.setBounds(180, 105, 230,30);
		fundo.add(opcoes_Teste);
		
		testes.addItem("Teste 1"); //Adicionando os arquivos de teste no JComboBox
		testes.addItem("Teste 2");
		testes.addItem("Avaliacao");
		testes.setSelectedIndex(0);
		testes.setBounds(425,105,120,30);
		testes.setFont(pixeledFont);
		fundo.add(testes);
		
		JLabel opcoes_Mapeamento = new JLabel("Tipo de mapeamento:");
		opcoes_Mapeamento.setFont(pixeledFont);
		opcoes_Mapeamento.setBounds(180, 185, 280,30);
		fundo.add(opcoes_Mapeamento);
		
		mapeamento.addItem("Direto");	//Adicionando os tipos de mapeamento no JComboBox das chaces
		mapeamento.addItem("Asso. FIFO");
		mapeamento.addItem("Asso. LRU");
		mapeamento.addItem("Asso. LFU");
		mapeamento.addItem("Asso. Aleatorio");
		mapeamento.addItem("Asso. em Conjunto");
		mapeamento.setSelectedIndex(0);
		mapeamento.setFont(pixeledFont);
		mapeamento.setBounds(425,185,225,30);
		fundo.add(mapeamento);
		
		JButton ok = new JButton("OK"); // JButton que ira inicializar a cache escolhida pelo usuario com o arquivo de teste selecionado
		ok.setFont(pixeledFont);
		ok.setBounds(330, 350, 100, 35);
		ok.setFocusable(false);
		ok.addActionListener(this::rodar);
		fundo.add(ok);
		
		add(fundo);
		
		setVisible(true);
		clip.start();
	}

	//Inicia a Tela que mostrara os resultados passando como parametro o arquivo de teste selecionado e a cache selecionada
	// Diferente da tela inicial, esta tela continua aberta para que o usuario possa voltar a ela de novo (maneira mais simples)
	public void rodar(ActionEvent e) {
		
		Resultados tela_De_Resultados = new Resultados(String.valueOf(testes.getSelectedItem()), String.valueOf(mapeamento.getSelectedItem()));
			
	}

}
