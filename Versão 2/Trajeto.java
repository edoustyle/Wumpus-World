import java.util.ArrayList;
import java.util.List;

public class Trajeto {

    public ArrayList<Coordenadas> rL;

	public Trajeto() {
		rL = new ArrayList<Coordenadas>();
	}

	public void addCaminho (Coordenadas poze) {
		rL.add(poze);
	}

	public Coordenadas getCaminho (int indice) {
		return rL.get(indice);
	}
	
	public void removeCaminho(Coordenadas poze) {
		rL.remove(poze);
	}
	
	public void removeCaminho (int indice) {
		rL.remove(indice);
	}
	
	public int size() {
		return rL.size();
	}
}
