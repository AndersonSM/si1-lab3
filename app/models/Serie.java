package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity(name="Serie")
public class Serie {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String nome;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="TEMP")
	@OrderBy("numero")
	private List<Temporada> temporadas;
	@Column
	private boolean acompanhando;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="SELECAO_ID")
	private SelecionadorDePreferencia selecionador;
	
	public Serie(){
		this.temporadas = new ArrayList<Temporada>();
		this.selecionador = new SelecionadorMaisAntigo();
	}
	
	public Serie(String nome){
		this();
		this.nome=nome;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Temporada> getTemporadas() {
		return temporadas;
	}
	public void addTemporada(Temporada temporada) {
		this.temporadas.add(temporada);
	}
	public boolean isAcompanhada() {
		return acompanhando;
	}

	public void setAssistida(boolean acompanhando) {
		this.acompanhando = acompanhando;
	}
	
	public int getTemporadasTotal() {
		return temporadas.size();
	}
	
	public Temporada getUltimaTemporada() throws Exception {
		if (getTemporadasTotal()==0)
			throw new Exception("Lista de Temporadas vazia! Nome da serie: "+this.getNome());
		return temporadas.get(temporadas.size()-1);
	}
	
	//pega o proximo episodio nao assistido imediatamente depois do ultimo assistido
	public Episodio getProximoEpisodio() {
		return selecionador.getProximoEpisodio(this);
	}
	
	public List<Episodio> getTodosEpisodios(){
		List<Episodio> result = new ArrayList<Episodio>();
		for (Temporada temporada : temporadas) {
			for (Episodio episodio : temporada.getEpisodios()) {
				result.add(episodio);
			}
		}
		return result;
	}

	public SelecionadorDePreferencia getSelecionador() {
		return selecionador;
	}

	public void setSelecionador(SelecionadorDePreferencia selecionador) {
		this.selecionador = selecionador;
	}
}
