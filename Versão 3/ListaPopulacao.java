import java.util.ArrayList;

public class ListaPopulacao {
	public ArrayList<Populacao> lG;

	public ListaPopulacao() {
		lG = new ArrayList<Populacao>();
	}
	
	public void addLista(Populacao valor){
		lG.add(valor);
	}

	public Populacao getLista(int indice) {
		return lG.get(indice);
	}
}
