package com.solvd.project;

import com.solvd.project.model.Vehicles;
import com.solvd.project.model.WeatherConditions;
import com.solvd.project.model.Witness;
import com.solvd.project.model.WitnessJAXB;
import com.solvd.project.service.DriverService;
import com.solvd.project.service.InsuranceData;
import com.solvd.project.service.JsonInsuranceService;
import com.solvd.project.service.mybatisiml.DriverServiceMyBatis;
import com.solvd.project.service.mybatisiml.PaymentsServiceMyBatis;
import com.solvd.project.service.mybatisiml.PolicyHolderServiceMyBatis;
import com.solvd.project.service.mybatisiml.PolicyServiceMyBatis;
import com.solvd.project.service.mybatisiml.VehicleServiceMyBatis;
import com.solvd.project.service.XMLImportService;
import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import com.solvd.project.model.Adjuster;
import com.solvd.project.model.AdjusterJAXB;
import com.solvd.project.model.Payments;
import com.solvd.project.model.Policy;
import com.solvd.project.model.PolicyHolders;
import com.solvd.project.model.Claims;
import com.solvd.project.model.Drivers;
import com.solvd.project.model.FraudCheck;
import com.solvd.project.model.InjuriRecord;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.project.Main;
import com.solvd.project.dao.PolicyHolderDAO;
import com.solvd.project.dao.VehicleDAO;
import com.solvd.project.dao.PolicyDAO;
import com.solvd.project.dao.WeatherConditionsDAO;
import com.solvd.project.dao.PaymentDAO;
import com.solvd.project.dao.DriverDAO;
import com.solvd.project.dao.AccidentDAO;
import com.solvd.project.dao.ClaimDAO;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {

        public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
                final Logger logger = LogManager.getLogger(Main.class);

                try {
                        // 1. Connect to the database
                        Connection conn = DriverManager.getConnection(
                                        "jdbc:sqlserver://localhost:1433;databaseName=InsuranceCompany;encrypt=true;trustServerCertificate=true",
                                        "Tabler12",
                                        "1110");

                        // 2. Initialize 8 DAOs
                        ClaimDAO claimDAO = new ClaimDAO(conn);
                        DriverDAO driverDAO = new DriverDAO(conn);
                        PaymentDAO paymentsDAO = new PaymentDAO(conn);
                        PolicyDAO policyDAO = new PolicyDAO(conn);
                        PolicyHolderDAO policyHolderDAO = new PolicyHolderDAO(conn);
                        VehicleDAO vehicleDAO = new VehicleDAO(conn);
                        WeatherConditionsDAO weatherDAO = new WeatherConditionsDAO(conn);
                        AccidentDAO accidentDAO = new AccidentDAO(conn);

                        // 3. Initialize 5 Services with MyBatis + StAX Parser Service
                        PolicyHolderServiceMyBatis holderService = new PolicyHolderServiceMyBatis(policyHolderDAO);
                        PolicyServiceMyBatis policyService = new PolicyServiceMyBatis(policyDAO);
                        VehicleServiceMyBatis vehicleService = new VehicleServiceMyBatis(vehicleDAO);
                        DriverServiceMyBatis driverService = new DriverServiceMyBatis(driverDAO);
                        PaymentsServiceMyBatis paymentsService = new PaymentsServiceMyBatis(paymentsDAO);
                        XMLImportService xmlService = new XMLImportService();

                        // 4. Load and process XML data
                        Path path = Paths.get("src/main/java/com/solvd/project/resources", "insurance_data.xml");
                        InputStream xmlStream = new FileInputStream(path.toFile());

                        // 5. Use services
                        System.out.println("📋 All Policy Holders:");
                        List<PolicyHolders> holders = holderService.getAll();
                        for (PolicyHolders h : holders) {
                                System.out.println(" - " + h.getName() + " (" + h.getContact() + ")");
                        }

                        System.out.println("\n🚗 Adding new vehicle...");
                        Vehicles newVehicle = new Vehicles(0, "Tesla", "2025", "TESLA123VIN");
                        vehicleService.create(newVehicle);

                        System.out.println("\n✅ Vehicles added!");

                        System.out.println("📥 Importing Witnesses from XML...");
                        List<Witness> witnesses = xmlService.loadWitnesses(xmlStream);
                        for (Witness w : witnesses) {
                                System.out.println(" - Witness #" + w.getId() + ": " + w.getStatementSummary());

                        }

                        xmlStream = new FileInputStream("src/main/java/com/solvd/project/resources/insurance_data.xml");
                        System.out.println("\n📥 Importing Adjusters from XML...");
                        List<Adjuster> adjusters = xmlService.loadAdjusters(xmlStream);
                        for (Adjuster a : adjusters) {
                                System.out.println(" - Adjuster: " + a.getName() + ", Case: " + a.getAssignedCase());

                        }

                        // Load and parse XML with the use of JAXB
                        File xmlFile = new File("src/main/java/com/solvd/project/resources/insurance_data.xml");
                        JAXBContext context = JAXBContext.newInstance(InsuranceData.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();

                        InsuranceData data = (InsuranceData) unmarshaller.unmarshal(xmlFile);

                        // Display parsed Witnesses
                        System.out.println("📋 Witnesses:");
                        for (WitnessJAXB witness : data.getWitnesses()) {
                                System.out.println(" - ID: " + witness.getIdWitnesses());
                                System.out.println("   Contact: " + witness.getContactInfo());
                                System.out.println("   Summary: " + witness.getStatementSummary());
                        }

                        // Display parsed Adjusters
                        System.out.println("\n🧑‍💼 Adjusters:");
                        for (AdjusterJAXB adjuster : data.getAdjusters()) {
                                System.out.println(" - ID: " + adjuster.getId());
                                System.out.println("   Name: " + adjuster.getName());
                                System.out.println("   Contact: " + adjuster.getContact());
                                System.out.println("   Case: " + adjuster.getAssignedCase());
                        }

                        // Parse JSON file through mapping
                        JsonInsuranceService service = new JsonInsuranceService(
                                        "src/resources/insurance_mock_data.json");

                        logger.info("🔍 Fraud Checks:");
                        for (FraudCheck fc : service.getFraudChecks()) {
                                System.out.println(
                                                " - Reason: " + fc.getFlagReason() + " | Score: " + fc.getRiskScore());
                        }

                        logger.info("\n🩺 Injury Records:");
                        for (InjuriRecord ir : service.getInjuriRecords()) {
                                System.out.println(" - Type: " + ir.getType() + " | Severity: " + ir.getSeverity());
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }

                // --- Strategy Pattern---
                com.solvd.project.model.Claims claim = new com.solvd.project.model.Claims(1, 15000, "pending",
                                java.time.LocalDate.now());
                claim.setFlaggedBy("adjuster");

                com.solvd.project.model.strategy.FraudDetectionStrategy riskStrategy = new com.solvd.project.model.strategy.SimpleRiskScoreStrategy();
                com.solvd.project.model.strategy.FraudDetectionStrategy adjusterStrategy = new com.solvd.project.model.strategy.FlaggedByAdjusterStrategy();

                System.out.println(
                                "\n[Strategy] Is claim fraudulent (risk score)? " + riskStrategy.isFraudulent(claim));
                System.out.println("[Strategy] Is claim fraudulent (flagged by adjuster)? "
                                + adjusterStrategy.isFraudulent(claim));

                // --- Observer Pattern---
                com.solvd.project.model.observer.ClaimSubject claimSubject = new com.solvd.project.model.observer.ClaimSubject();
                claimSubject.addObserver(new com.solvd.project.model.observer.EmailNotificationObserver());
                claimSubject.addObserver(new com.solvd.project.model.observer.LoggingObserver());

                System.out.println("\n[Observer] Notifying observers about claim update...");
                claimSubject.notifyObservers(claim);

        }

}
