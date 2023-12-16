package  stock ; 
 
public class  Mouvement { 
 
	String id_mouvement;
	String id_entree;
	String id_sortie;
	Object quantite_detail_sortie;

	public void setId_mouvement(String id_mouvement) {
 		this.id_mouvement = id_mouvement;
	}

	public String getId_mouvement () {
		return id_mouvement;
	}

	public void setId_entree(String id_entree) {
 		this.id_entree = id_entree;
	}

	public String getId_entree () {
		return id_entree;
	}

	public void setId_sortie(String id_sortie) {
 		this.id_sortie = id_sortie;
	}

	public String getId_sortie () {
		return id_sortie;
	}

	public void setQuantite_detail_sortie(Object quantite_detail_sortie) {
 		this.quantite_detail_sortie = quantite_detail_sortie;
	}

	public Object getQuantite_detail_sortie () {
		return quantite_detail_sortie;
	}
}
