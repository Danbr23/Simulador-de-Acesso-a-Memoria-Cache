package trabalhopratico1;

import java.util.ArrayList;

// Classe pai se onde serao extendidas todos os outros tipos de cache

public abstract class Cache {
	
	private int tamanho; //tamanho da cache
	private int palavra; //tamanho da palavra
	private int palavras_Por_Linha; // numero de palavras por linha
	private int numero_De_Linhas; //numero de linhas
	private String potencia; // Byte, MB, GB, TB
	private int acertos; //numero de vezes em que a linha requisitada estava na cache
	private int falhas; // vezes em que a linha solicitada nao estava na cache
	private ArrayList<String> vetor_De_Enderecos = new ArrayList<String>(); // Vetor de enderecos que serao solicitados a cache
	
	public Cache(ArrayList<String> vetor_De_Enderecos) {
		quantidadeDeLinhas();
		this.vetor_De_Enderecos = vetor_De_Enderecos;
		
	}
	
	//Funcao para definir a quantidade de linhas na memoria cache
	public void quantidadeDeLinhas() {
		
		String linhaConfig; // linha i do arquivo de configuracao
		String spliter[] = new String[5]; //vetor de strings para dividir o arquivo de teste em linhas
		ArrayList<String> dadosConfig = FileManager.stringReader("./dados/config.txt"); //Funcao do Douglas para dividir o arquivo de configuracoes em linhas de string
		
        linhaConfig = dadosConfig.get(1); // linha 1 da cache que faz referenca ao tamanho da palavra
        spliter = linhaConfig.split(" ");
        setPalavra(Integer.parseInt(spliter[2]));
        
        linhaConfig = dadosConfig.get(2); //linha 2 que faz referencia ao tamanho da cache
        spliter = linhaConfig.split(" ");
        setTamanho(Integer.parseInt(spliter[2]));
        
        linhaConfig = dadosConfig.get(3); //linha 3 = palavras por linhas
        spliter = linhaConfig.split(" ");
        setPalavras_Por_Linha(Integer.parseInt(spliter[2]));
        
        linhaConfig = dadosConfig.get(2); // linha 2 novamente para pegar a potencia 
        spliter = linhaConfig.split(" ");
        setPotencia(spliter[3]); 
        
        /* A linha 0 nao eh utilizada pois eh o tamanho da memoria ram, o que nao tem uso para o programa (tem uso para se calcular o n. de bits do endereco) */
        
        
        // Switch para cada caso possivel da potencia
        switch(getPotencia()) {
        
        	case "B;":
        		
        		/* Assim como nos outros casos, setamos o numero de linhas da cache da seguinte forma:
        			
        			N. DE LINHAS DA CACHE = TAMANHO DA CACHE / TAMANHO DA LINHA
        			
        			1 - a var tamanho eh multiplicada pela potencia de 2 que vai de acordo com cada caso, e assim obtemos o tamanho verdadeiro da cache
        			2 - a var palavras_Por_Linha eh multiplicada pela var palavra para termos o tamanho de uma linha
        			3 - O produto *1 eh divido pelo produto *2 para termos a quantidade linhas na cache
        					
        		*/
        		
        		setNumero_De_Linhas((int) ( ( tamanho * Math.pow(2, 0) ) / (palavras_Por_Linha * palavra)));
        		
        		break;
        	
        	case "KB;":
        		
        		setNumero_De_Linhas((int) ( ( tamanho * Math.pow(2, 10) ) / (palavras_Por_Linha * palavra)));
        		
        		break;
        		
        	case "MB;":
        		
        		setNumero_De_Linhas((int) ( ( tamanho * Math.pow(2, 20) ) / (palavras_Por_Linha * palavra)));
        		
        		break;
        		
        	case "GB;":
        		
        		setNumero_De_Linhas((int) ( ( tamanho * Math.pow(2, 30) ) / (palavras_Por_Linha * palavra)));
        		
        		break;
        	
        }
		
	}
	
	// Getters e Setters
	
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public int getPalavra() {
		return palavra;
	}
	public void setPalavra(int palavra) {
		this.palavra = palavra;
	}
	public int getNumero_De_Linhas() {
		return numero_De_Linhas;
	}
	public void setNumero_De_Linhas(int numero_De_Linhas) {
		this.numero_De_Linhas = numero_De_Linhas;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public int getPalavras_Por_Linha() {
		return palavras_Por_Linha;
	}

	public void setPalavras_Por_Linha(int palavras_Por_Linha) {
		this.palavras_Por_Linha = palavras_Por_Linha;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	public int getFalhas() {
		return falhas;
	}

	public void setFalhas(int falhas) {
		this.falhas = falhas;
	}

	public ArrayList<String> getVetor_De_Enderecos() {
		return vetor_De_Enderecos;
	}

	public void setVetor_De_Enderecos(ArrayList<String> vetor_De_Enderecos) {
		this.vetor_De_Enderecos = vetor_De_Enderecos;
	}
	
	
	
	
	

}
