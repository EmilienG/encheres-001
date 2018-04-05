package entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Vente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float montantInitial;
    private float montantReserve;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateDebut;
    private int duree;

    // associations ------------------
    @OneToMany(mappedBy = "vente")
    private Collection<Enchere> encheres;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Produit produit;

    public Vente() {
        encheres = new ArrayList<>();
    }

    public Vente(float montantInitial, float montantReserve, Date dateDebut, int duree, Utilisateur utilisateur) {
        this();
        this.montantInitial = montantInitial;
        this.montantReserve = montantReserve;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vente)) {
            return false;
        }
        Vente other = (Vente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public float getMontantInitial() {
        return montantInitial;
    }

    public void setMontantInitial(float montantInitial) {
        this.montantInitial = montantInitial;
    }

    public float getMontantReserve() {
        return montantReserve;
    }

    public void setMontantReserve(float montantReserve) {
        this.montantReserve = montantReserve;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Collection<Enchere> getEncheres() {
        return encheres;
    }

    public void setEncheres(Collection<Enchere> encheres) {
        this.encheres = encheres;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Vente[ montantInitial : " + montantInitial + " montantReserve : " + montantReserve + " dateDebut : " + dateDebut + " duree : " + duree + " (" + id + ") ]";
    }
}
