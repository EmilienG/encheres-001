package essais;

import entites.Categorie;
import entites.Enchere;
import entites.Produit;
import entites.Utilisateur;
import entites.Vente;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class appliMain01 {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("encheres-01PU");
        EntityManager em = emf.createEntityManager();

        //------------- creation des objets ---------
        Utilisateur u01 = new Utilisateur("Dupont", "Toto", "toto.dupont@monmail.com", "afpa");
        Utilisateur u02 = new Utilisateur("Dupond", "Jack", "jack.dupond@tonmail.com", "afpapa");

        Enchere e01 = new Enchere(155F);
        Enchere e02 = new Enchere(1000F);
        Enchere e03 = new Enchere(1909F);

        Date d01 = new GregorianCalendar(2018, 3, 03).getTime();
        Date d02 = new GregorianCalendar(2019, 11, 13).getTime();

        Vente v01 = new Vente(505F, 1001F, d01, 55555, u01);
        Vente v02 = new Vente(125F, 1545F, d02, 5665, u02);

        Produit p01 = new Produit("Horloge", "super belle et utile en plus");
        Produit p02 = new Produit("Tableau", "super ultra beau");

        Categorie c01 = new Categorie("Art");
        Categorie c02 = new Categorie("Meubles");

        //--------------- les associations -----
        e01.setUtilisateur(u01);
        e02.setUtilisateur(u01);
        e03.setUtilisateur(u02);
        e01.setVente(v01);
        e02.setVente(v01);
        e03.setVente(v02);
        v01.setUtilisateur(u01);
        v02.setUtilisateur(u02);
        v01.setProduit(p01);
        v02.setProduit(p02);
        p01.setCategorie(c01);
        p02.setCategorie(c02);

        //-------------- mettre les objets dans le cache de l'em(contexte de persistance) -----------
        em.persist(u01);
        em.persist(u02);
        em.persist(e01);
        em.persist(e02);
        em.persist(e03);
        em.persist(v01);
        em.persist(v02);
        em.persist(p01);
        em.persist(p02);
        em.persist(c01);
        em.persist(c02);

        //------------ recuperer une transaction, la demarrer et la valider avec un commit
        EntityTransaction et = em.getTransaction();
        et.begin();
        et.commit();

        //-----------Affichage lecture vues -----------------
        Utilisateur uu01 = em.find(Utilisateur.class, 1L);
        Produit pp01 = em.find(Produit.class, 1L);
        Vente vv01 = em.find(Vente.class, 1L);
        Enchere ee01 = em.find(Enchere.class, 1L);
        Categorie cc01 = em.find(Categorie.class, 1L);
        System.out.println("=================================================\n");
        System.out.println("L'utilisateur d'une enchere : " + ee01.getUtilisateur());
        System.out.println("La vente d'une enchere : " + ee01.getVente());
        System.out.println("Le produits d'une vente : " + vv01.getProduit());
        System.out.println("La catégorie d'un produit : " + pp01.getCategorie());
        System.out.println("--------------------------------------------------");
        System.out.println("Les ventes d'un utilisateur : " + uu01.getVentes());
        System.out.println("Les encheres d'une vente : " + vv01.getEncheres());
        System.out.println("Les ventes d'un produit : " + pp01.getVentes());
        System.out.println("\n=================================================");

        em.close();
        emf.close();
    }
}
