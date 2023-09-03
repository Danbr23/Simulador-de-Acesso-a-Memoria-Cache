package trabalhopratico1;

import java.util.ArrayList;
import java.util.LinkedList;

public class AssociativoConjunto extends Cache {
	
	private int numero_De_Conjuntos = 0; // Numero de conjuntos da cache escolhido de forma arbitraria
	private int linhas_Por_Conjunto = 4;
	LinkedList<String>[] conjuntos;
	
	/*
	 Neste mapeamento, temos um vetor, onde cada posicao do vetor eh uma LinkedList de String
	 
	 Estou usando o LinkedList pois o algoritmo de substituicao dentro de cada conjunto sera FIFO
	 
	 */
	
	public AssociativoConjunto(ArrayList<String> enderecos) {
		
		super(enderecos);
		
		numero_De_Conjuntos = getNumero_De_Linhas()/4;
		conjuntos = new LinkedList[numero_De_Conjuntos];
	
		for(int i = 0; i< conjuntos.length; i++) {
			conjuntos[i] = new LinkedList<String>(); 
		}
		
		executarBuscas(enderecos);
	
	}
	
	
	
	void buscarEndereco(String endereco, int conjunto_Da_Cache) { //Verifica se enderco esta na cache
		
//		System.out.println(conjunto_Da_Cache);
		
		// Verifica se a LinkedList na posicao 'conjunto_Da_Cache' do vetor possui o endereco
		if(conjuntos[conjunto_Da_Cache].contains(endereco)) { 
			setAcertos(getAcertos()+1);
		}else {
			setFalhas(getFalhas()+1);
			if(conjuntos[conjunto_Da_Cache].size() < linhas_Por_Conjunto) {
				conjuntos[conjunto_Da_Cache].add(endereco);
			}else {
				conjuntos[conjunto_Da_Cache].remove(conjuntos[conjunto_Da_Cache].getFirst()); //substituicao por FIFO
				conjuntos[conjunto_Da_Cache].add(endereco);
			}
		}
	}
	
		
	int conjuntoCache(String endereco_Bin) { // recebe o endereco e retorna o conjunto em decimal ao qual pertence o endereco
		
		int i;
		int conjunto = 0;
		String linha_Em_Bin = endereco_Bin.substring(10,13);
//		System.out.println(linhaBin);
		
		for(i = 0; i<=2; i++) {
		
			if(linha_Em_Bin.substring(i,i+1).equals("1")) {
				conjunto += Math.pow(2, 2-i);
			}
		}	
		return conjunto;
	}
	
	void executarBuscas(ArrayList<String> vetor_De_Enderecos) {
		
		int conjunto;
		
		for(String endereco : vetor_De_Enderecos) {
			conjunto = conjuntoCache(endereco); // retorna o conjunto ao qual pertence o endereco
			buscarEndereco(endereco.substring(0, 10), conjunto);
		}
		
		System.out.println("Acertos: " + getAcertos());
		System.out.println("Falhas: " + getFalhas());
		
	}
	
	void mostar_Linhas_Por_Conjunto() {
		int i;
		for(i=0;i<numero_De_Conjuntos;i++) {
			System.out.println(conjuntos[i]);
		}
	}



	public int getNumero_De_Conjuntos() {
		return numero_De_Conjuntos;
	}



	public void setNumero_De_Conjuntos(int numero_De_Conjuntos) {
		this.numero_De_Conjuntos = numero_De_Conjuntos;
	}



	public int getLinhas_Por_Conjunto() {
		return linhas_Por_Conjunto;
	}



	public void setLinhas_Por_Conjunto(int linhas_Por_Conjunto) {
		this.linhas_Por_Conjunto = linhas_Por_Conjunto;
	}



	public LinkedList<String>[] getConjuntos() {
		return conjuntos;
	}



	public void setConjuntos(LinkedList<String>[] conjuntos) {
		this.conjuntos = conjuntos;
	}
	

	
}
