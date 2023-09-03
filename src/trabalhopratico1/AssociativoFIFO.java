package trabalhopratico1;

import java.util.LinkedList;
import java.util.ArrayList;

public class AssociativoFIFO extends Cache {
		
	private LinkedList<String> linhas = new LinkedList<String>();
	
	/* 
	  Para a cache com mapeamento associativo e algoritmo de substituicao FIFO, optei por utilizar a Linked List
	  pois ela armazena os itens adicionados na ordem em que sao adicionados. Assim, quando ela estiver cheia,
	  eh so remover o primeiro e adicionar o novo endereco
	
	
	*/
	public AssociativoFIFO(ArrayList<String> enderecos) {
		
		super(enderecos);
				
		executarBuscas(getVetor_De_Enderecos());
		
	}
	
	void buscarEndereco(String endereco) {
		if(linhas.contains(endereco)) {
			setAcertos(getAcertos()+1);
		}else {
			setFalhas(getFalhas()+1);
			if(linhas.size() < getNumero_De_Linhas()) {
				linhas.add(endereco);
			}else {
				linhas.remove(linhas.getFirst());
				linhas.add(endereco);
			}
		}
	}
	
	void executarBuscas(ArrayList<String> vetor_De_Enderecos) {
		
		for(String endereco : vetor_De_Enderecos) {
			
			buscarEndereco(endereco.substring(0,13));
			
		}
		
		System.out.println("Acertos: " + getAcertos());
		System.out.println("Falhas: " + getFalhas());
	}

	public LinkedList<String> getLinhas() {
		return linhas;
	}

	public void setLinhas(LinkedList<String> linhas) {
		this.linhas = linhas;
	}
	
	
}
