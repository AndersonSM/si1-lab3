package models;

import java.util.List;

import javax.persistence.Entity;

@Entity(name="SelecionadorMaisAntigo")
public class SelecionadorMaisAntigo extends SelecionadorDePreferencia{
	
	public SelecionadorMaisAntigo(){}
	
	@Override
	public Episodio getProximoEpisodio(Serie serie){
		Episodio nenhumEp = new Episodio("Nenhum episódio a ser recomendado nesta série.", null, 0);
		Episodio proxEpisodio = nenhumEp;
		//boolean serieTerminada = true;

		/*for (int i = 0; i < serie.getTemporadasTotal(); i++) {
			for (int j = 0; j < serie.getTemporadas().get(i).getEpisodios().size(); j++) {
				if(!serie.getTemporadas().get(i).getEpisodios().get(j).isAssistido()){
					proxEpisodio = serie.getTemporadas().get(i).getEpisodios().get(j);
					serieTerminada = false;
					break;
				}
			}
			if(!serieTerminada)
				break;
		}
		if(!serieTerminada && indicacaoEspecial){
			Logger.info("Entrou");
			proxEpisodio = verificaTresProximos(serie, proxEpisodio.getTemporada().getNumero()-1, proxEpisodio.getNumero());
		}*/
		
		/*List<Episodio> lista = serie.getTodosEpisodios();
		
		if(indicacaoEspecial)
			return verificaTresProximos(lista);*/
		
		/*for (Episodio ep : lista) {
			if(!ep.isAssistido()){
				proxEpisodio = ep;
				break;
			}
		}*/
		
		boolean achou = false;
		for (Temporada temp : serie.getTemporadas()) {
			for (Episodio ep : temp.getEpisodios()) {
				if(!ep.isAssistido()){
					achou = true;
					proxEpisodio = ep;
					break;
				}
			}
			if(achou) break;
		}
		
		if(achou && indicacaoEspecial){
			Episodio next = verificaTresProximos(serie.getTodosEpisodios(), proxEpisodio);
			if(next!=null) proxEpisodio = next;
			else proxEpisodio = nenhumEp;
		}
		
		return proxEpisodio;
	}
	
	private Episodio verificaTresProximos(List<Episodio> lista, Episodio ep){
		Episodio proximoEpisodio = ep;
		/*
		boolean flag = false;
		for (int i = 0; i < lista.size(); i++) {
			if(!lista.get(i).isAssistido()){
				proximoEpisodio = lista.get(i);
				for (int j = i+1; j <= (i+3); j++) {
					if(i<lista.size() && !lista.get(j).isAssistido()){
						flag = false;
						break;
					}
				}
				if(!flag) break;
			}
		}
		return proximoEpisodio;*/
		int assistidos = 0;
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i).compareTo(ep) >= 1 && lista.get(i).isAssistido()){
				assistidos++;
			}
			if(assistidos == 3){
				return null;
			}
		}
		
		return proximoEpisodio; 
	}
}
