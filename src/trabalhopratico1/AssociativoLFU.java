package trabalhopratico1;

import java.util.ArrayList;
import java.util.HashMap;

public class AssociativoLFU extends Cache {


	HashMap<String, Integer> linhas = new HashMap<String, Integer>();
	
	/* 
	Para a LFU optei por utilizar o HashMap, pois ele trabalha com uma chave e um valor
	A chave eh o endereco e o valor o contador. Cada vez que o endereco eh requisitado, o contador eh incrementado
	*/
	public AssociativoLFU(ArrayList<String> enderecos) {
		
		super(enderecos);
		executarBuscas(getVetor_De_Enderecos());
		
	}
	
	void buscarEndereco(String endereco) {
		int contador;
		// Sempre  que buscarEndereco eh chamado, a var menosUsado eh setada como null
		Integer menosUsado = null;
		String lfu = "";
		if(linhas.containsKey(endereco)) { 			// Verifica se o HashMap possui aquela linhas
			setAcertos(getAcertos()+1);
			contador = linhas.get(endereco); 			// variavel contador recebe o contador daquele endereco
			++contador;									// o contador eh incrementado
			linhas.replace(endereco, contador); 		// o contador do endereco eh trocado por ele mesmo incrementado
		}else {
			setFalhas(getFalhas()+1);
			if(linhas.size() < getNumero_De_Linhas()) {
				linhas.put(endereco, 0);
			}else {
				for(String i : linhas.keySet()) { 	// procura a linha com o menor valor do contador de usos
					if(menosUsado == null) {		// Se a var menos Usado eh null, ela recebe o contador do primeiro endereco da cache
						menosUsado = linhas.get(i);
					}
					if(linhas.get(i) <= menosUsado) {
						menosUsado = linhas.get(i);
						lfu = i;
					}
				}
				linhas.remove(lfu); 				//remove a linha com menos usos
				linhas.put(endereco, 0);
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

	public HashMap<String, Integer> getLinhas() {
		return linhas;
	}

	public void setLinhas(HashMap<String, Integer> linhas) {
		this.linhas = linhas;
	}
	
	
}


