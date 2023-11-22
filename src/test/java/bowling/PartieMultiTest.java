package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PartieMultiTest {

	private PartieMultiJoueurs partie;

	@BeforeEach
	public void setUp() {
		partie = new PartieMultiJoueurs();
		partie.demarreNouvellePartie(new String[]{"Pierre", "Paul"});
	}

	@Test
	void Demarrage() {
		PartieMultiJoueurs partie2 = new PartieMultiJoueurs();
		assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 1", partie2.demarreNouvellePartie(new String[]{"Pierre", "Paul"}),
			"On doit commencer au joueur Pierre");

	}
	
	@Test
	void nvLance(){
		assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 2",partie.enregistreLancer(5),
			"prochain lancer : Pierre ");
		assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1",partie.enregistreLancer(3),
			"prochain lancer : Paul ");

	}
	

	@Test
	void joueurInconnu() {
		assertThrows(IllegalArgumentException.class, () -> {
			// On doit avoir une exception
			partie.scorePour("Jacques");
		}, "Le joueur esixte pas");


	}

	@Test
	void scoreExact() {
		partie.enregistreLancer(5);
		partie.enregistreLancer(3);
		partie.enregistreLancer(10);
		partie.enregistreLancer(7);
		partie.enregistreLancer(3);
		assertEquals(18, partie.scorePour("Pierre"),
			"score Pierre = 18"
		);
		assertEquals(10, partie.scorePour("Paul"),
			"score Paul = 10"
		);

	}
	
	
}
