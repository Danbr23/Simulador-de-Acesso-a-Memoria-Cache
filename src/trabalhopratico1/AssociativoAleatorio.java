package trabalhopratico1;

import java.util.ArrayList;
import java.lang.Math;

public class AssociativoAleatorio extends Cache {

	private ArrayList<String> linhas = new ArrayList<String>();
	
	// Como a linha a ser removida sera sorteada de forma aleatoria, uma ArrayList ja basta
	
	private int numRamdom = 0;
	
	public AssociativoAleatorio(ArrayList<String> enderecos) {
		
		super(enderecos);
		executarBuscas(getVetor_De_Enderecos());	
	}
	
	public void buscarEndereco(String endereco) {
		if(linhas.contains(endereco)) {
			setAcertos(getAcertos()+1);
		}else {
			setFalhas(getFalhas()+1);
			if(linhas.size() < getNumero_De_Linhas()) {
				linhas.add(endereco);
			}else {
				numRamdom = (int)(Math.random() * getTamanho()); // recebe um numero aleatorio
//				teste = linhas.get(numRamdom);
				linhas.remove(numRamdom); // remove o endereco que esta nessa posicao do numero aleatorio
				linhas.add(endereco);
			}
		}
	}
	
	public void executarBuscas(ArrayList<String> vetor_De_Enderecos) {
		
		for(String endereco : vetor_De_Enderecos) {
			
			buscarEndereco(endereco.substring(0,13));
		}
		System.out.println("Acertos: " + getAcertos());
		System.out.println("Falhas: " + getFalhas());
	}

	public ArrayList<String> getLinhas() {
		return linhas;
	}

	public void setLinhas(ArrayList<String> linhas) {
		this.linhas = linhas;
	}

	public int getNumRamdom() {
		return numRamdom;
	}

	public void setNumRamdom(int numRamdom) {
		this.numRamdom = numRamdom;
	}
	
	

}
