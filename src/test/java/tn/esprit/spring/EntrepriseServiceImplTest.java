package tn.esprit.spring;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class EntrepriseServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImplTest.class);
	
	@Autowired
	IEntrepriseService ientrepriseservice;
	
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	
	Integer idE;
	
	@Test
	public void testAjouterEntreprise() {
		l.debug("Test méthode AjouterEntreprise");
		 try {
			 idE = ientrepriseservice.ajouterEntreprise(new Entreprise("vermeg","raisonSociale "));
			 assertNotNull(idE);
		 } catch (Exception e) {
		       l.error("méthode AjouterEntreprise error :" + e);	

			}
	}

	@Test
	public void testGetAllDepartementsNamesByEntreprise() {
		l.debug("Test méthode GetAllDepartementsNamesByEntreprise");
		List<String> depNames = ientrepriseservice.getAllDepartementsNamesByEntreprise(1);
		assertNotNull(depNames);
	}

	@Test
	public void testDeleteEntrepriseById() {
	
	}

	@Test
	public void testGetEntrepriseById() {
		
	}

}
