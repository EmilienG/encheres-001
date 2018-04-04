package essais;

import entites.Enchere;
import entites.Utilisateur;
import java.util.Collection;
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

        //--------------- les associations -----
        e01.setUtilisateur(u01);
        e02.setUtilisateur(u01);
        e03.setUtilisateur(u02);

        //-------------- mettre les objets dans le cache de l'em(contexte de persistance) -----------
        em.persist(u01);
        em.persist(u02);
        em.persist(e01);
        em.persist(e02);
        em.persist(e03);

        //------------ recuperer une transaction, la demarrer et la valider avec un commit
        EntityTransaction et = em.getTransaction();
        et.begin();
        et.commit();

        em.close();
        emf.close();
    }
}
