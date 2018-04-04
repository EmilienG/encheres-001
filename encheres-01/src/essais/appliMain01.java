package essais;

import entites.Enchere;
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

        Vente v01 = new Vente(505F, 1001F, d01, 55555);
        Vente v02 = new Vente(125F, 1545F, d02, 5665);
        //--------------- les associations -----
        e01.setUtilisateur(u01);
        e02.setUtilisateur(u01);
        e03.setUtilisateur(u02);
        e01.setVente(v01);
        e02.setVente(v01);
        e03.setVente(v02);
        //-------------- mettre les objets dans le cache de l'em(contexte de persistance) -----------
        em.persist(u01);
        em.persist(u02);
        em.persist(e01);
        em.persist(e02);
        em.persist(e03);
        em.persist(v01);
        em.persist(v02);

        //------------ recuperer une transaction, la demarrer et la valider avec un commit
        EntityTransaction et = em.getTransaction();
        et.begin();
        et.commit();

        em.close();
        emf.close();
    }
}
