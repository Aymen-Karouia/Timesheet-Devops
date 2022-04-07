package tn.esprit.spring;

import static org.junit.Assert.*;

import java.util.Date;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.ITimesheetService;;

public class TimesheetServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(TimesheetServiceImplTest.class);
	
	@Autowired
	ITimesheetService iTimesheetService;
	
	@Autowired
	IEmployeService iEmployeService;
	
	@Autowired
    TimesheetRepository timesheetRepository;

	@Test
	public void testAjouterMission() {
		l.debug("Methode ajouterMission");
		 try {
			 int idM=iTimesheetService.ajouterMission(new Mission("Project", "Project"));
			 Assert.assertNotNull(String.valueOf(idM));
		 } catch (Exception e) {
			 l.error("erreur methode ajouterMission :" +e);	
			 }
	}

	@Test
	public void testAjouterTimesheet() {
		l.debug("Methode ajouterTimesheet");
		 try {
			 int idE = iEmployeService.ajouterEmploye(new Employe("Aymen", "Karouia", "karouia.aymen@spring.tn", true, Role.ADMINISTRATEUR));
			 int idM = iTimesheetService.ajouterMission(new Mission("Project", "Project"));
			 iTimesheetService.ajouterTimesheet(idM, idE,new Date(2/2/2022),new Date(3/2/2022));
			 Assert.assertNotNull(String.valueOf(idE));
			 Assert.assertNotNull(String.valueOf(idM));
		 } catch (Exception e) {
			 l.error("erreur methode ajouterTimesheet :" +e);	
			 }
	}

	@Test
	public void testFindAllMissionByEmployeJPQL() {
		l.debug("Methode findAllMissionByEmploye");
		 try {
			 int idM = iEmployeService.ajouterEmploye(new Employe("Aymen", "Karouia", "karouia.aymen@spring.tn", true, Role.ADMINISTRATEUR));
			 String prenomEmp = iEmployeService.getEmployePrenomById(idM);
			 assertNotNull(prenomEmp);
		 } catch (Exception e) {
			 l.error("erreur methode findAllMissionByEmploye :" +e);	
			 }
	}

	@Test
	public void testGetAllEmployeByMission() {
		l.debug("Methode getAllEmployeByMission");
		try {
			int idM = iEmployeService.ajouterEmploye(new Employe("Aymen", "Karouia", "karouia.aymen@spring.tn", true, Role.ADMINISTRATEUR));
			String prenomEmp = iEmployeService.getEmployePrenomById(idM);
			Assert.assertTrue(prenomEmp.equals("Karouia"));
			iEmployeService.deleteEmployeById(idM);
		} catch (Exception e) {
			l.error("erreur methode getAllEmployeByMission :" +e);
		}
	}

}
