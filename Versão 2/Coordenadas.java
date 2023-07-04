public class Coordenadas {
    private Boolean vizitada=false;
    private int linha,coluna;

    public Coordenadas(int linha, int coluna) 
	{
		super();
		this.linha = linha;
		this.coluna = coluna;
		vizitada = true;
	}
    public int getColuna() {
        return coluna;
    }
    public int getLinha() {
        return linha;
    }
    public Boolean getVizitada() {
        return vizitada;
    }
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    public void setLinha(int linha) {
        this.linha = linha;
    }
    public void setVizitada(Boolean vizitada) {
        this.vizitada = vizitada;
    }
}