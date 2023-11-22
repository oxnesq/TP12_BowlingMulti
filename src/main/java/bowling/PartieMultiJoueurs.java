package bowling;

import java.util.ArrayList;

public class PartieMultiJoueurs implements IPartieMultiJoueurs {
	private PartieMonoJoueur[] parties;

	private String[] nomDesJoueurs;
	private int numProchainJoueur;
	private String prochainJoueur;


	/**
	 * Démarre une nouvelle partie pour un groupe de joueurs
	 *
	 * @param nomsDesJoueurs un tableau des noms de joueurs (il faut au moins un joueur)
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 1, boule n° 1"
	 * @throws java.lang.IllegalArgumentException si le tableau est vide ou null
	 */
	@Override
	public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
		if (nomsDesJoueurs == null) throw new IllegalArgumentException("pas de joueurs");
		this.nomDesJoueurs = nomsDesJoueurs;
		this.numProchainJoueur = 0;
		this.prochainJoueur = nomsDesJoueurs[numProchainJoueur];
		this.parties =new PartieMonoJoueur[nomDesJoueurs.length];
		for (int i = 0; i < nomDesJoueurs.length; i++) {
			PartieMonoJoueur partie = new PartieMonoJoueur();
			parties[i] = partie;
		}

		return "Prochain tir : joueur " + prochainJoueur + ", tour n° " + 1 + ", boule n° " + 1;
	}

	/**
	 * Enregistre le nombre de quilles abattues pour le joueur courant, dans le tour courant, pour la boule courante
	 *
	 * @param nombreDeQuillesAbattues : nombre de quilles abattue à ce lancer
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 5, boule n° 2",
	 * ou bien "Partie terminée" si la partie est terminée.
	 * @throws java.lang.IllegalStateException si la partie n'est pas démarrée.
	 */

	@Override
	public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
		int partiesTerm=0;
		for (int i = 0; i < parties.length; i++) {
			if (parties[i].estTerminee()) partiesTerm += 1;

		}
		if (partiesTerm == parties.length)
			throw new IllegalStateException("Partie terminee");

		parties[numProchainJoueur].enregistreLancer(nombreDeQuillesAbattues);
		numProchainJoueur += 1 % nomDesJoueurs.length;
		return "Prochain tir : joueur " + prochainJoueur + ", tour n° " + parties[numProchainJoueur].numeroTourCourant() + ", boule n° " + parties[numProchainJoueur].numeroProchainLancer();
	}

	/**
	 * Donne le score pour le joueur playerName
	 *
	 * @param nomDuJoueur le nom du joueur recherché
	 * @return le score pour ce joueur
	 * @throws IllegalArgumentException si nomDuJoueur ne joue pas dans cette partie
	 */

	@Override
	public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
		if (existe(nomDesJoueurs, nomDuJoueur)==-1)
			throw new IllegalArgumentException("Le joueur n'existe pas");
		
		return parties[existe(nomDesJoueurs,nomDuJoueur)].score();
	}

	static int existe(String T[], String val){
		for(int i = 0 ; i<T.length;i++){
			if(val==T[i])
				//retourner la position courante
				return i;
		}
		System.out.println("La valeur recherchée n'existe pas");
		return -1;
	}
}
