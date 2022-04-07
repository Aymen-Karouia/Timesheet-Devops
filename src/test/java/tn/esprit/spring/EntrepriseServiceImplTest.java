package tn.esprit.spring;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
	IEntrepriseService iEntrepriseService;
	
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	
	Integer idE;
	
	@Test
	public void testAjouterEntreprise() {
		l.debug("Test méthode AjouterEntreprise");
		 try {
			 Entreprise E = new Entreprise("Vermeg","raisonSociale");
			 idE = iEntrepriseService.ajouterEntreprise(E);
			 assertNotNull(idE);
		 } catch (Exception e) {
		       l.error("méthode AjouterEntreprise error :" + e);	

			}
	}
	
@Test
	public void testDeleteEntreprise() {
		l.debug("Test méthode DeleteEntreprise");
		try {
			iEntrepriseService.deleteEntrepriseById(30); 
			assertNull(iEntrepriseService.getEntrepriseById(30));
		} catch (Exception e) {
			l.error("méthode DeleteEntreprise error :"+ e);
		}
		
	}
	

@Test
	public void testGetAllDepartementsNamesByEntreprise() {
		l.debug("Test méthode GetAllDepartementsNamesByEntreprise");
		try {
			List<String> depNamByEnt = iEntrepriseService.getAllDepartementsNamesByEntreprise(30);
			assertNotNull(depNamByEnt);
		} catch (Exception e) {
			l.error("méthode GetAllDepartementsNamesByEntreprise error :"+ e);	
		}
		
	}


}
