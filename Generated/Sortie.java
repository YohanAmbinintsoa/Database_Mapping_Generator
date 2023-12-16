package  stock ; 
 
public class  Sortie { 
 
	String id_sortie;
	String id_article_sortie;
	Object quantite_sortie;
	String id_magasin;
	Object date_sortie;

	public void setId_sortie(String id_sortie) {
 		this.id_sortie = id_sortie;
	}

	public String getId_sortie () {
		return id_sortie;
	}

	public void setId_article_sortie(String id_article_sortie) {
 		this.id_article_sortie = id_article_sortie;
	}

	public String getId_article_sortie () {
		return id_article_sortie;
	}

	public void setQuantite_sortie(Object quantite_sortie) {
 		this.quantite_sortie = quantite_sortie;
	}

	public Object getQuantite_sortie () {
		return quantite_sortie;
	}

	public void setId_magasin(String id_magasin) {
 		this.id_magasin = id_magasin;
	}

	public String getId_magasin () {
		return id_magasin;
	}

	public void setDate_sortie(Object date_sortie) {
 		this.date_sortie = date_sortie;
	}

	public Object getDate_sortie () {
		return date_sortie;
	}
}
