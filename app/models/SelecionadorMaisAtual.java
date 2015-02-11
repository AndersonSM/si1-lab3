package models;

import java.util.List;

import javax.persistence.Entity;

@Entity(name="SelecionadorMaisAtual")
public class SelecionadorMaisAtual extends SelecionadorDePreferencia{

	public SelecionadorMaisAtual(){}
	
	@Override
	public Episodio getProximoEpisodio(Serie serie){
		List<Episodio> lista = serie.getTodosEpisodios();
		Episodio proxEpisodio = lista.get(0);
		
		for (int i = lista.size()-1; i >= 0; i--) {
			if(lista.get(i).isAssistido()){
				if(i+1<lista.size()){
					proxEpisodio = lista.get(i+1);
				}else{
					proxEpisodio = new Episodio("Nenhum episódio a ser recomendado nesta série.", null, 0);
				}
				break;
			}
		}
		return proxEpisodio;
	}
}
