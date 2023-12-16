package  stock ; 
 
public class  Article { 
 
	String id_article;
	String famille;
	String nom_article;
	String id_unite;
	String type_stockage;

	public void setId_article(String id_article) {
 		this.id_article = id_article;
	}

	public String getId_article () {
		return id_article;
	}

	public void setFamille(String famille) {
 		this.famille = famille;
	}

	public String getFamille () {
		return famille;
	}

	public void setNom_article(String nom_article) {
 		this.nom_article = nom_article;
	}

	public String getNom_article () {
		return nom_article;
	}

	public void setId_unite(String id_unite) {
 		this.id_unite = id_unite;
	}

	public String getId_unite () {
		return id_unite;
	}

	public void setType_stockage(String type_stockage) {
 		this.type_stockage = type_stockage;
	}

	public String getType_stockage () {
		return type_stockage;
	}
}
