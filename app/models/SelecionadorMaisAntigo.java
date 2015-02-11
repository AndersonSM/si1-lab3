package models;

import java.util.List;

import javax.persistence.Entity;

@Entity(name="SelecionadorMaisAntigo")
public class SelecionadorMaisAntigo extends SelecionadorDePreferencia{
	
	public SelecionadorMaisAntigo(){}
	
	@Override
	public Episodio getProximoEpisodio(Serie serie){
		Episodio proxEpisodio = new Episodio("Nenhum episódio a ser recomendado nesta série.", null, 0);
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
		List<Episodio> lista = serie.getTodosEpisodios();
		
		if(indicacaoEspecial)
			return verificaTresProximos(lista);
		
		for (Episodio ep : lista) {
			if(!ep.isAssistido()){
				proxEpisodio = ep;
				break;
			}
		}
		
		return proxEpisodio;
	}
	
	private Episodio verificaTresProximos(List<Episodio> lista){
		Episodio proximoEpisodio = null;
		boolean flag = true;
		
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
		return proximoEpisodio;
	}
	
}
