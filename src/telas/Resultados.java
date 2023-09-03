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
import javax.swing.*;

import trabalhopratico1.*;

public class Resultados extends JFrame {
	
	//Variaveis
	
	BufferedImage img = null;
	Font pixeledFont;
	
	public Resultados(String arquivo_Teste, String mapeamento) {
		
		// Chamada do metodo iniciarCache para inicializar a cache com os parametros passados na tela de Configuracoes
		Cache cache = iniciarCache(arquivo_Teste, mapeamento);
		
		// Dois trycatchs para pegar a imagem de fundo e a fonte pixelada
		
		try {
			img = ImageIO.read(new File("./imgs/fundoResultados.jpg"));
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
		
		// Definição dos atributos da tela de resultado
		
		setSize(800,500);
		setTitle("Resultados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JLabel fundo = new JLabel();
		
		Image dimg = img.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		fundo.setIcon(imageIcon);
		
		JLabel mostrarAcertos = new JLabel("Acertos: ");
		mostrarAcertos.setFont(pixeledFont);
		mostrarAcertos.setBounds(460, 73, 150, 30);
		fundo.add(mostrarAcertos);
		
		JLabel acertos = new JLabel(String.valueOf(cache.getAcertos())); // Ele converte o atributo acertos da cache para String e passa como parametro para o JLabel
		acertos.setFont(pixeledFont);
		acertos.setBounds(575, 73, 80,30);
		fundo.add(acertos);
		
		JLabel mostrarFalhas = new JLabel("Falhas: ");
		mostrarFalhas.setFont(pixeledFont);
		mostrarFalhas.setBounds(460, 153, 150, 30);
		fundo.add(mostrarFalhas);
		
		JLabel falhas = new JLabel(String.valueOf(cache.getFalhas())); // Ele converte o atributo falhas da cache para String e passa como parametro para o JLabel
		falhas.setFont(pixeledFont);
		falhas.setBounds(575, 153, 80,30);
		fundo.add(falhas);
		
		JLabel mostrarPorcentagem = new JLabel("Resultado: ");
		mostrarPorcentagem.setFont(pixeledFont);
		mostrarPorcentagem.setBounds(460, 233, 200, 30);
		fundo.add(mostrarPorcentagem);
		
		//O JLabel porcentagem recebe como parametro a divisao do atributo acertos da cache pelo total de testes, converte para float e converte para String
		JLabel porcentagem = new JLabel(String.valueOf(((float) cache.getAcertos()/cache.getVetor_De_Enderecos().size()) * 100) + "%"); 
		porcentagem.setFont(pixeledFont);
		porcentagem.setBounds(595, 233, 100,30);
		fundo.add(porcentagem);
		
		JButton voltar = new JButton("Voltar"); //Jbutton que permite voltar para a tela de configuracoes e mudar as opcoes escolhidas
		voltar.setFont(pixeledFont);
		voltar.setBounds(490, 350, 130, 35);
		voltar.setFocusable(false);
		voltar.addActionListener(this::voltarTela);
		fundo.add(voltar);
		
		add(fundo);
		
		setVisible(true);
		
	}
	
	// funcao que fecha a tela de resultados 
	public void voltarTela(ActionEvent e) {
		this.dispose();
	}
	
	
	//metodo que ira inicializar a cache de acordo com a selecionada pelo usuario
	public Cache iniciarCache(String arquivo_Teste, String mapeamento) {
		
		String path = ""; //caminho do arquivo de teste no computador
		Cache cache = null; // inicializacao da cache
		
		switch(arquivo_Teste) { // verifica qual o arquivo de teste selecionado e define path de acordo 
			case "Teste 1":
				path = "./dados/teste_1.txt";
			break;
			
			case "Teste 2":
				path = "./dados/teste_2.txt";
			break;
			
			case "Avaliacao":
				path = "./dados/avaliacao.txt";
			break;
		}
		
		//Metodo do Douglas para converter o arquuivo de teste em ArrayList
		
		ArrayList<String> arquivo = FileManager.stringReader(path);
        ArrayList<String> enderecos = new ArrayList<String>();
        for (String linha : arquivo) {
            int acesso = Integer.parseInt(linha);
            String stringBin = FileManager.intToBinaryString(acesso, 15);
            enderecos.add(stringBin);
        }
        
        
		//Switch para inicializar a cache de acordo com o mapeamento escolhido
		
		switch(mapeamento) {
			
			case "Direto":
				cache = new CacheMapeamentoDireto(enderecos);
				System.out.println(String.valueOf(((float) cache.getAcertos()/cache.getVetor_De_Enderecos().size()) * 100));
				System.out.println(cache.getVetor_De_Enderecos().size());
				break;
				
			case "Asso. FIFO":
				cache = new AssociativoFIFO(enderecos);
				System.out.println(cache.getNumero_De_Linhas());
				break;
			
			case "Asso. LRU":
				cache = new AssociativoLRU(enderecos);
				System.out.println(cache.getNumero_De_Linhas());
				break;
				
			case "Asso. LFU":
				cache = new AssociativoLFU(enderecos);
				System.out.println(cache.getNumero_De_Linhas());
				break;
				
			case "Asso. Aleatorio":
				cache = new AssociativoAleatorio(enderecos);
				System.out.println(cache.getNumero_De_Linhas());
				break;
			
			case "Asso. em Conjunto":
				cache = new AssociativoConjunto(enderecos);
				System.out.println(cache.getNumero_De_Linhas());
				break;
			

		}
		
		return cache; //retorna a cache para que os resultados possam ser mostrados no JFrame resultados
	}

}
