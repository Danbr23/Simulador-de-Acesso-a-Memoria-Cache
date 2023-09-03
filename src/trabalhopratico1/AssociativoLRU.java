package trabalhopratico1;

import java.util.ArrayList;
import java.util.LinkedList;

public class AssociativoLRU extends Cache {
	

	public LinkedList<String> linhas = new LinkedList<String>();
	
	/*
	   Aqui foi utilizado uma LinkedList assim como no FIFO, mas a diferenca eh que
	   caso o endereco solicitado estiver na cache, ele eh removido daquela posicao e colocado no comeco 
	 */
	
	
	public AssociativoLRU(ArrayList<String> enderecos) {
		super(enderecos);		
		executarBuscas(getVetor_De_Enderecos());

	}
	
	void buscarEndereco(String endereco) {
		if(linhas.contains(endereco)) {
			setAcertos(getAcertos()+1);
			linhas.remove(endereco); //remove da posicao atual
			linhas.add(endereco); //adiciona ao comeco
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
	


