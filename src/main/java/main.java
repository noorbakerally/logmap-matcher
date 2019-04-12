import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import uk.ac.ox.krr.logmap2.LogMap2_Matcher;
import uk.ac.ox.krr.logmap2.mappings.objects.MappingObjectStr;

import java.util.Set;

public class main {
    public static void main(String [] args) throws OWLOntologyCreationException {

        OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
        //load SEAS ontology
        String seasOntologyIRI = "https://ci.mines-stetienne.fr/seas/ElectricPowerSystemOntology-1.0.rdf";
        OWLOntology seasOntology = ontologyManager.loadOntology(IRI.create(seasOntologyIRI));



        //load SSN ontology
        String ssnOntologyIRI = "https://www.w3.org/ns/ssn/ssn.rdf";
        OWLOntology ssnOntology = ontologyManager.loadOntology(IRI.create(ssnOntologyIRI));

        String dogontIRI = "http://elite.polito.it/ontologies/dogont.owl";
        OWLOntology dogont = ontologyManager.loadOntology(IRI.create(dogontIRI));




        System.out.println("Generating Mappings");

        System.out.println("Mapping with SSN ontology:");
        LogMap2_Matcher logmap2 = new LogMap2_Matcher(ssnOntology,seasOntology);


        Set <MappingObjectStr> logmap2_mappings = logmap2.getLogmap2_Mappings();


        System.out.println("Number of mappings:"+logmap2_mappings.size());

        for (MappingObjectStr mapping: logmap2_mappings){
            System.out.println("Mapping: ");
            System.out.println("\t"+ mapping.getIRIStrEnt1());
            System.out.println("\t"+ mapping.getIRIStrEnt2());
            System.out.println("\t"+ mapping.getConfidence());


            //MappingObjectStr.EQ or MappingObjectStr.SUB or MappingObjectStr.SUP
            System.out.println("\t"+ mapping.getMappingDirection()); //Utilities.EQ;

            //MappingObjectStr.CLASSES or MappingObjectStr.OBJECTPROPERTIES or MappingObjectStr.DATAPROPERTIES or MappingObjectStr.INSTANCES
            System.out.println("\t"+ mapping.getTypeOfMapping());


        }
    }
}
