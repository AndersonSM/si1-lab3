package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="SelecionadorDePreferencia")
public abstract class SelecionadorDePreferencia {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	protected boolean indicacaoEspecial = false;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Serie serie;
	
	public SelecionadorDePreferencia(){
	}
	
	public Episodio getProximoEpisodio(Serie serie){
		return null;
	}
	
	public boolean isIndicacaoEspecial() {
		return indicacaoEspecial;
	}
	
	public void setIndicacaoEspecial(boolean indicacaoEspecial) {
		this.indicacaoEspecial = indicacaoEspecial;
	}
	
	public Long getId(){
		return id;
	}
	
	private void setId(Long id){
		this.id = id;
	}
}
