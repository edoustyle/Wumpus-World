public class Posicao 
{

	private String tipo, sentido, ambiente;
	private char personalidade;

	public Posicao(String tipo, String sentido, String ambiente) 
	{
		super();
		this.tipo = tipo;
		this.sentido = sentido;
		this.ambiente = ambiente;
		personalidade='k';
	}
	public void setPersonalidade(char pernality){
		personalidade=pernality;
	}
	public char getPersonalidade(){
		return personalidade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSentido() {
		return sentido;
	}
	public void setSentido(String sentido) {
		this.sentido = sentido;
	}
	public String getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
}