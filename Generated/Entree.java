package  stock ; 
 
public class  Entree { 
 
	String id_entree;
	String id_article_entree;
	Object quantite_entree;
	String id_magasin;
	Object date_entree;
	Object prix_unitaire;

	public void setId_entree(String id_entree) {
 		this.id_entree = id_entree;
	}

	public String getId_entree () {
		return id_entree;
	}

	public void setId_article_entree(String id_article_entree) {
 		this.id_article_entree = id_article_entree;
	}

	public String getId_article_entree () {
		return id_article_entree;
	}

	public void setQuantite_entree(Object quantite_entree) {
 		this.quantite_entree = quantite_entree;
	}

	public Object getQuantite_entree () {
		return quantite_entree;
	}

	public void setId_magasin(String id_magasin) {
 		this.id_magasin = id_magasin;
	}

	public String getId_magasin () {
		return id_magasin;
	}

	public void setDate_entree(Object date_entree) {
 		this.date_entree = date_entree;
	}

	public Object getDate_entree () {
		return date_entree;
	}

	public void setPrix_unitaire(Object prix_unitaire) {
 		this.prix_unitaire = prix_unitaire;
	}

	public Object getPrix_unitaire () {
		return prix_unitaire;
	}
}
