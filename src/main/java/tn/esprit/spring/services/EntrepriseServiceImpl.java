package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
				Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
				if (depManagedEntity!=null) {
					depManagedEntity.setEntreprise(entrepriseManagedEntity);
					deptRepoistory.save(depManagedEntity);
				}
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.debug("methode getAllDepartementsNamesByEntreprise ");
		List<String> depNames = new ArrayList<>();
		try {
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			if(entrepriseManagedEntity!=null && entrepriseManagedEntity.getDepartements()!=null){
				for(Departement dep:entrepriseManagedEntity.getDepartements()){
					depNames.add(dep.getName());
			}
				l.debug("getAllDepartementsNamesByEntreprise fini avec succes ");
				return depNames;
			}
				else {
					l.error("erreur methode getAllDepartementsNamesByEntreprise : " );
					return depNames;
				}
			
		} catch (Exception e) {
			l.error("erreur methode getAllDepartementsNamesByEntreprise : " +e);
			return depNames;
		}
		
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		l.debug("methode deleteEntrepriseById ");
		try {
			Optional<Entreprise> Optentrp = entrepriseRepoistory.findById(entrepriseId);
			if(Optentrp.isPresent()){
			Entreprise entrp = Optentrp.get();
			entrepriseRepoistory.delete(entrp);
			l.debug("deleteEntrepriseById fini avec succes ");
			}
			else {
				l.error("erreur methode deleteEntrepriseById : " );
			;
			}
		} catch (Exception e) {
			l.error("erreur methode deleteEntrepriseById : " +e);
			;
		}

	}

	@Transactional
	public void deleteDepartementById(int depId) {
		l.debug("methode deleteDepartementById ");
		try {
			Optional<Departement> op = deptRepoistory.findById(depId);
			if(op.isPresent()){
				Departement dp = op.get();
				deptRepoistory.delete(dp);
			l.debug("deleteDepartementById fini avec succes ");
			}
			else {
				l.error("erreur methode deleteDepartementById : " );
			}
		} catch (Exception e) {
			l.error("erreur methode deleteDepartementById : " +e);
		}	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		l.debug("methode getEntrepriseById ");
		try {
			Entreprise entreprise= entrepriseRepoistory.findById(entrepriseId).orElse(null);
			l.debug("getEntrepriseById fini avec succes ");
			return entreprise;
		} catch (Exception e) {
			l.error("erreur methode getEntrepriseById : " +e);
			return null;
		}
	}

}
