package org.rr.moduleselector.selector;

import org.rr.moduleselector.selector.model.Module;
import org.rr.moduleselector.selector.model.Subject;
import org.rr.moduleselector.selector.model.Universities;

import java.util.*;
import java.util.stream.Collectors;

public class DataHolder {
    private static final DataHolder INSTANCE = new DataHolder();
    private final Map<Subject, Map<Integer, List<Module>>> modulesBySubjectAndSemester;

    private DataHolder() {
        modulesBySubjectAndSemester = new EnumMap<>(Subject.class);
        initializeModules();
    }

    public static DataHolder getInstance() {
        return INSTANCE;
    }

    private void initializeModules() {
        initializeScientificAssistant();
        initializeBusinessAllrounder();
        initializeITProjectManager();
        initializeSoftwareEngineer();
        initializeDataAnalyst();
    }

    private void initializeScientificAssistant() {
        Map<Integer, List<Module>> scientificAssistant = new HashMap<>();

        // Semester 1
        List<Module> semester1 = new ArrayList<>();
        // Compulsory
        semester1.addAll(Arrays.asList(new Module("WIW34200", "Applied Programming Project", 1, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW64000", "Business Information Systems", 1, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW32090", "Risk Management and Management Control", 1, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW03750", "Training of Language, Research and Intercultural Skills", 1, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null)));

        // Optional with alternatives
        Module pti90190 = new Module("PTI90190", "Computer Science Project", 1, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null);
        Module pti90220 = new Module("PTI90220", "Advanced Computer Graphics", 1, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null);
        semester1.add(new Module("OPT1", "Optional Course 1", 1, false, Arrays.asList(pti90190, pti90220), Subject.SCIENTIFIC_ASSISTANT, null));

        Module wiw00390 = new Module("WIW00390", "Change Management", 1, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null);
        Module wiw31500 = new Module("WIW31500", "Managing Challenges in the Globalized Economy", 1, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null);
        semester1.add(new Module("OPT2", "Optional Course 2", 1, false, Arrays.asList(wiw00390, wiw31500), Subject.SCIENTIFIC_ASSISTANT, null));

        scientificAssistant.put(1, semester1);

        // Semester 2
        List<Module> semester2 = new ArrayList<>();
        // Compulsory
        semester2.addAll(Arrays.asList(new Module("PTI90290", "Machine Learning", 2, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW64031", "Analytics for Data Driven Decisions", 2, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW32530", "Advanced Fields of Management", 2, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null)));

        // Optional
        semester2.addAll(Arrays.asList(new Module("PTI90080", "Large Scale Data Processing", 2, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW30610", "Forschungs- und Projektarbeit I", 2, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null)));

        List<Module> businessOptions = Arrays.asList(new Module("WIW32040", "Business Monitoring Systems and Internal Audit", 2, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW67500", "Business Cultures", 2, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("PTI90190", "Computer Science Project", 2, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("PTI90300", "Science Communication", 2, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, null));
        semester2.add(new Module("OPT3", "Optional Course 3", 2, false, businessOptions, Subject.SCIENTIFIC_ASSISTANT, null));

        scientificAssistant.put(2, semester2);

        // Semester 3
        List<Module> semester3 = new ArrayList<>();
        semester3.addAll(Arrays.asList(new Module("PTI90310", "Design and Implementation of Software Systems", 3, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW34170", "Digital Business Modeling", 3, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW35010", "Strategic Management", 3, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null)));

        // ASUE Modules
        semester3.addAll(Arrays.asList(new Module("ASUE1", "International Coaching Project", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.ASUE), new Module("ASUE2", "Management Theory - Modern Issues", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.ASUE), new Module("ASUE3", "Research Methods in Data Science", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.ASUE)));

        // IBSU Modules
        semester3.addAll(Arrays.asList(new Module("IBSU1", "International Coaching Project", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.IBSU), new Module("IBSU2", "Research Methods for Business", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.IBSU), new Module("IBSU3", "Advanced Algorithms", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.IBSU)));

        // KAFU Modules
        semester3.addAll(Arrays.asList(new Module("KAFU1", "International Coaching Project", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.KAFU), new Module("KAFU2", "Project Management", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.KAFU), new Module("KAFU3", "Corporate Governance", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.KAFU), new Module("KAFU4", "Architecture of information systems", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.KAFU)));

        // INAI Modules
        semester3.addAll(Arrays.asList(new Module("INAI1", "International Coaching Project", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.INAI), new Module("INAI2", "Philosophical problems of science", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.INAI), new Module("INAI3", "Business processes in software development", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.INAI), new Module("INAI4", "Enterprise Application Development", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.INAI)));

        scientificAssistant.put(3, semester3);

        // Semester 4
        List<Module> semester4 = new ArrayList<>();
        semester4.addAll(Arrays.asList(new Module("WIW34190", "Expert talks", 4, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW30650", "Master Project", 4, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null)));

        scientificAssistant.put(4, semester4);

        modulesBySubjectAndSemester.put(Subject.SCIENTIFIC_ASSISTANT, scientificAssistant);
    }

    private void initializeBusinessAllrounder() {
        Map<Integer, List<Module>> businessAllrounder = new HashMap<>();

        // Semester 1
        List<Module> semester1 = new ArrayList<>();
        semester1.addAll(Arrays.asList(new Module("WIW34200", "Applied Programming Project", 1, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW64000", "Business Information Systems", 1, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW32090", "Risk Management and Management Control", 1, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW03750", "Training of Language, Research and Intercultural Skills", 1, true, List.of(), Subject.BUSINESS_ALLROUNDER, null)));

        List<Module> optionalGroup1 = Arrays.asList(new Module("PTI90190", "Computer Science project", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("PTI90180", "Car-to-Car Communication", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null));
        semester1.add(new Module("OPT1", "Optional Course 1", 1, false, optionalGroup1, Subject.BUSINESS_ALLROUNDER, null));

        List<Module> optionalGroup2 = Arrays.asList(new Module("WIW64010", "Digital Business Models", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW31500", "Managing Challenges in the Globalized Economy", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW00390", "Change Management", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null));
        semester1.add(new Module("OPT2", "Optional Course 2", 1, false, optionalGroup2, Subject.BUSINESS_ALLROUNDER, null));

        businessAllrounder.put(1, semester1);

        // Semester 2
        List<Module> semester2 = new ArrayList<>();
        semester2.addAll(Arrays.asList(new Module("PTI90290", "Machine Learning", 2, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW64031", "Analytics for Data Driven Decisions", 2, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW32530", "Advanced Fields of Management", 2, true, List.of(), Subject.BUSINESS_ALLROUNDER, null)));

        List<Module> optionalGroup3 = Arrays.asList(new Module("PTI90080", "Large Scale Data Processing", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("PTI90190", "Computer Science Project", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null));
        semester2.add(new Module("OPT3", "Optional Course 3", 2, false, optionalGroup3, Subject.BUSINESS_ALLROUNDER, null));

        List<Module> optionalGroup4 = Arrays.asList(new Module("WIW34060", "Information & Knowledge Management", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW64021", "Internet of Things (Digitale Technologien)", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null));
        semester2.add(new Module("OPT4", "Optional Course 4", 2, false, optionalGroup4, Subject.BUSINESS_ALLROUNDER, null));

        List<Module> optionalGroup5 = Arrays.asList(new Module("WIW32040", "Business Monitoring Systems and Internal Audit", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW67500", "Business Cultures", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null));
        semester2.add(new Module("OPT5", "Optional Course 5", 2, false, optionalGroup5, Subject.BUSINESS_ALLROUNDER, null));

        businessAllrounder.put(2, semester2);

        // Semester 3
        List<Module> semester3 = new ArrayList<>();
        semester3.addAll(Arrays.asList(new Module("PTI90310", "Design and Implementation of Software Systems", 3, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW34170", "Digital Business Modeling", 3, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW35010", "Strategic Management", 3, true, List.of(), Subject.BUSINESS_ALLROUNDER, null)));

        // ASUE Modules
        semester3.addAll(Arrays.asList(new Module("ASUE1", "Python Programming", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE), new Module("ASUE2", "Neural Networks and Deep Learning", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE), new Module("ASUE3", "Decision Making Process", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE), new Module("ASUE4", "Digital Marketing", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE), new Module("ASUE5", "International Coaching Project", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE)));

        // IBSU Modules
        semester3.addAll(Arrays.asList(new Module("IBSU1", "Advanced Algorithms", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU), new Module("IBSU2", "Human - Machine Interaction", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU), new Module("IBSU3", "Artificial Intelligence Applications", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU), new Module("IBSU4", "Leadership Strategies", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU), new Module("IBSU5", "Advanced Project Management", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU), new Module("IBSU6", "International Coaching Project", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU)));

        // KAFU Modules
        semester3.addAll(Arrays.asList(new Module("KAFU1", "Computing systems and telecommunications network", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU), new Module("KAFU2", "Architecture of information systems", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU), new Module("KAFU3", "Strategic Marketing", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU), new Module("KAFU4", "Personnel Management", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU), new Module("KAFU5", "International Coaching Project", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU)));

        // INAI Modules
        semester3.addAll(Arrays.asList(new Module("INAI1", "Advanced Marketing", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI), new Module("INAI2", "International Coaching Project", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI), new Module("INAI3", "Business progresses in software development", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI), new Module("INAI4", "Enterprise Application Development", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI), new Module("INAI5", "Legal aspects", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI)));

        businessAllrounder.put(3, semester3);

        // Semester 4
        List<Module> semester4 = new ArrayList<>();
        semester4.addAll(Arrays.asList(new Module("WIW34190", "Expert talks", 4, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW30650", "Master Project", 4, true, List.of(), Subject.BUSINESS_ALLROUNDER, null)));

        businessAllrounder.put(4, semester4);

        modulesBySubjectAndSemester.put(Subject.BUSINESS_ALLROUNDER, businessAllrounder);
    }

    private void initializeITProjectManager() {
        Map<Integer, List<Module>> itProjectManager = new HashMap<>();

        // Semester 1
        List<Module> semester1 = new ArrayList<>();
        semester1.addAll(Arrays.asList(new Module("WIW34200", "Applied Programming Project", 1, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW64000", "Business Information Systems", 1, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW32090", "Risk Management and Management Control", 1, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW03750", "Training of Language, Research and Intercultural Skills", 1, true, List.of(), Subject.IT_PROJECT_MANAGER, null)));

        List<Module> optionalGroup1 = Arrays.asList(new Module("WIW00390", "Change Management", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("PTI90190", "Computer Science Project", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null));
        semester1.add(new Module("OPT1", "Optional Course 1", 1, false, optionalGroup1, Subject.IT_PROJECT_MANAGER, null));

        List<Module> optionalGroup2 = Arrays.asList(new Module("WIW65540", "International Human Resource Management", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW31500", "Managing Challenges in the Globalized Economy", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW08520", "Managing Intercultural Collaboration", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null));
        semester1.add(new Module("OPT2", "Optional Course 2", 1, false, optionalGroup2, Subject.IT_PROJECT_MANAGER, null));

        itProjectManager.put(1, semester1);

        // Semester 2
        List<Module> semester2 = new ArrayList<>();
        semester2.addAll(Arrays.asList(new Module("PTI90290", "Machine Learning", 2, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW64031", "Analytics for Data Driven Decisions", 2, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW32530", "Advanced Fields of Management", 2, true, List.of(), Subject.IT_PROJECT_MANAGER, null)));

        List<Module> optionalGroup3 = Arrays.asList(new Module("WIW67500", "Business Cultures", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW32040", "Business Monitoring Systems and Internal Audit", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null));
        semester2.add(new Module("OPT3", "Optional Course 3", 2, false, optionalGroup3, Subject.IT_PROJECT_MANAGER, null));

        semester2.add(new Module("SPR06590", "Global Business and Project Communication English", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null));

        List<Module> optionalGroup4 = Arrays.asList(new Module("WIW64021", "Internet of Things (Digitale Technologien)", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW34060", "Information & Knowledge Management", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("PTI90190", "Computer Science Project", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null));
        semester2.add(new Module("OPT4", "Optional Course 4", 2, false, optionalGroup4, Subject.IT_PROJECT_MANAGER, null));

        itProjectManager.put(2, semester2);

        // Semester 3
        List<Module> semester3 = new ArrayList<>();
        semester3.addAll(Arrays.asList(new Module("PTI90310", "Design and Implementation of Software Systems", 3, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW34170", "Digital Business Modeling", 3, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW35010", "Strategic Management", 3, true, List.of(), Subject.IT_PROJECT_MANAGER, null)));

        // ASUE Modules
        semester3.addAll(Arrays.asList(new Module("ASUE1", "Decision Making Process", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE), new Module("ASUE2", "Digital Marketing", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE), new Module("ASUE3", "International Coaching Project", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE), new Module("ASUE4", "Neural Networks and Deep Learning", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE), new Module("ASUE5", "Python Programming", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE), new Module("ASUE6", "Business Statistics", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE)));

        // IBSU Modules
        semester3.addAll(Arrays.asList(new Module("IBSU1", "International Coaching Project", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU), new Module("IBSU2", "Advanced Project Management", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU), new Module("IBSU3", "Leadership Strategies", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU), new Module("IBSU4", "Computer Networks and Security", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU), new Module("IBSU5", "Models of Computation", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU), new Module("IBSU6", "Artificial Intelligence Application", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU), new Module("IBSU7", "Human-Machine Interaction", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU)));

        // KAFU Modules
        semester3.addAll(Arrays.asList(new Module("KAFU1", "Computing systems and telecommunications networks", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU), new Module("KAFU2", "Corporate Governance", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU), new Module("KAFU3", "Personnel Management", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU), new Module("KAFU4", "International Coaching Project", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU), new Module("KAFU5", "Project Management", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU)));

        // INAI Modules
        semester3.addAll(Arrays.asList(new Module("INAI1", "Enterprise Application Development", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI), new Module("INAI2", "Business processes in software development", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI), new Module("INAI3", "International Coaching Project", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI), new Module("INAI4", "Startup coaching", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI), new Module("INAI5", "Business plan", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI), new Module("INAI6", "Legal aspects", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI)));

        itProjectManager.put(3, semester3);

        // Semester 4
        List<Module> semester4 = new ArrayList<>();
        semester4.addAll(Arrays.asList(new Module("WIW34190", "Expert talks", 4, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW30650", "Master Project", 4, true, List.of(), Subject.IT_PROJECT_MANAGER, null)));

        itProjectManager.put(4, semester4);

        modulesBySubjectAndSemester.put(Subject.IT_PROJECT_MANAGER, itProjectManager);
    }

    private void initializeSoftwareEngineer() {
        Map<Integer, List<Module>> softwareEngineer = new HashMap<>();

        // Semester 1
        List<Module> semester1 = new ArrayList<>();
        semester1.addAll(Arrays.asList(new Module("WIW34200", "Applied Programming Project", 1, true, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW64000", "Business Information Systems", 1, true, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW32090", "Risk Management and Management Control", 1, true, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW03750", "Training of Language, Research and Intercultural Skills", 1, true, List.of(), Subject.SOFTWARE_ENGINEER, null)));

        semester1.add(new Module("PTI90220", "Advanced Computer Graphics", 1, false, List.of(), Subject.SOFTWARE_ENGINEER, null));

        List<Module> optionalGroup1 = Arrays.asList(new Module("PTI90190", "Computer Science Project", 1, false, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("PTI90180", "Car-to-Car Communication", 1, false, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW64010", "Digital Business Models", 1, false, List.of(), Subject.SOFTWARE_ENGINEER, null));
        semester1.add(new Module("OPT1", "Optional Course 1", 1, false, optionalGroup1, Subject.SOFTWARE_ENGINEER, null));

        softwareEngineer.put(1, semester1);

        // Semester 2
        List<Module> semester2 = new ArrayList<>();
        semester2.addAll(Arrays.asList(new Module("PTI90290", "Machine Learning", 2, true, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW64031", "Analytics for Data Driven Decisions", 2, true, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW32530", "Advanced Fields of Management", 2, true, List.of(), Subject.SOFTWARE_ENGINEER, null)));

        List<Module> optionalGroup2 = Arrays.asList(new Module("PTI90080", "Large Scale Data Processing", 2, false, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("PTI90190", "Computer Science Project", 2, false, List.of(), Subject.SOFTWARE_ENGINEER, null));
        semester2.add(new Module("OPT2", "Optional Course 2", 2, false, optionalGroup2, Subject.SOFTWARE_ENGINEER, null));

        List<Module> optionalGroup3 = Arrays.asList(new Module("WIW64021", "Internet of Things (Digitale Technologien)", 2, false, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW30610", "Forschungs- und Projektarbeit I", 2, false, List.of(), Subject.SOFTWARE_ENGINEER, null));
        semester2.add(new Module("OPT3", "Optional Course 3", 2, false, optionalGroup3, Subject.SOFTWARE_ENGINEER, null));

        List<Module> optionalGroup4 = Arrays.asList(new Module("SPR06590", "Global Business and Project Communication English", 2, false, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW32040", "Business Monitoring Systems and Internal Audit", 2, false, List.of(), Subject.SOFTWARE_ENGINEER, null));
        semester2.add(new Module("OPT4", "Optional Course 4", 2, false, optionalGroup4, Subject.SOFTWARE_ENGINEER, null));

        softwareEngineer.put(2, semester2);

        // Semester 3
        List<Module> semester3 = new ArrayList<>();
        semester3.addAll(Arrays.asList(new Module("PTI90310", "Design and Implementation of Software Systems", 3, true, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW34170", "Digital Business Modeling", 3, true, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW35010", "Strategic Management", 3, true, List.of(), Subject.SOFTWARE_ENGINEER, null)));

        // ASUE Modules
        semester3.addAll(Arrays.asList(new Module("ASUE1", "Python Programming", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.ASUE), new Module("ASUE2", "Neural Networks and Deep Learning", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.ASUE), new Module("ASUE3", "International Coaching Project", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.ASUE), new Module("ASUE4", "Advanced Project Management", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.ASUE)));

        // IBSU Modules
        semester3.addAll(Arrays.asList(new Module("IBSU1", "Advanced Algorithms", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU), new Module("IBSU2", "Artificial Intelligence Applications", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU), new Module("IBSU3", "International Coaching Project", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU), new Module("IBSU4", "Numerical Analysis", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU), new Module("IBSU5", "Models of Computation", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU)));

        // KAFU Modules
        semester3.addAll(Arrays.asList(new Module("KAFU1", "Computing systems and telecommunications networks", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.KAFU), new Module("KAFU2", "Architecture of information systems", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.KAFU), new Module("KAFU3", "International Coaching Project", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.KAFU)));

        // INAI Modules
        semester3.addAll(Arrays.asList(new Module("INAI1", "Enterprise Application Development", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.INAI), new Module("INAI2", "Business processes in software development", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.INAI), new Module("INAI3", "International Coaching Project", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.INAI), new Module("INAI4", "Legal aspects", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.INAI)));

        softwareEngineer.put(3, semester3);

        // Semester 4
        List<Module> semester4 = new ArrayList<>();
        semester4.addAll(Arrays.asList(new Module("WIW34190", "Expert talks", 4, true, List.of(), Subject.SOFTWARE_ENGINEER, null), new Module("WIW30650", "Master Project", 4, true, List.of(), Subject.SOFTWARE_ENGINEER, null)));

        softwareEngineer.put(4, semester4);

        modulesBySubjectAndSemester.put(Subject.SOFTWARE_ENGINEER, softwareEngineer);
    }

    private void initializeDataAnalyst() {
        Map<Integer, List<Module>> dataAnalyst = new HashMap<>();

        // Semester 1
        List<Module> semester1 = new ArrayList<>();
        semester1.addAll(Arrays.asList(new Module("WIW34200", "Applied Programming Project", 1, true, List.of(), Subject.DATA_ANALYST, null), new Module("WIW64000", "Business Information Systems", 1, true, List.of(), Subject.DATA_ANALYST, null), new Module("WIW32090", "Risk Management and Management Control", 1, true, List.of(), Subject.DATA_ANALYST, null), new Module("WIW03750", "Training of Language, Research and Intercultural Skills", 1, true, List.of(), Subject.DATA_ANALYST, null)));

        semester1.add(new Module("PTI90180", "Car-to-Car Communication", 1, false, List.of(), Subject.DATA_ANALYST, null));

        List<Module> optionalGroup1 = Arrays.asList(new Module("PTI90220", "Advanced Computer Graphics", 1, false, List.of(), Subject.DATA_ANALYST, null), new Module("WIW31500", "Managing Challenges in the Globalized Economy", 1, false, List.of(), Subject.DATA_ANALYST, null), new Module("WIW64010", "Digital Business Models", 1, false, List.of(), Subject.DATA_ANALYST, null), new Module("WIW00390", "Change Management", 1, false, List.of(), Subject.DATA_ANALYST, null));
        semester1.add(new Module("OPT1", "Optional Course 1", 1, false, optionalGroup1, Subject.DATA_ANALYST, null));

        dataAnalyst.put(1, semester1);

        // Semester 2
        List<Module> semester2 = new ArrayList<>();
        semester2.addAll(Arrays.asList(new Module("PTI90290", "Machine Learning", 2, true, List.of(), Subject.DATA_ANALYST, null), new Module("WIW64031", "Analytics for Data Driven Decisions", 2, true, List.of(), Subject.DATA_ANALYST, null), new Module("WIW32530", "Advanced Fields of Management", 2, true, List.of(), Subject.DATA_ANALYST, null)));

        List<Module> optionalGroup2 = Arrays.asList(new Module("PTI90080", "Large Scale Data Processing", 2, false, List.of(), Subject.DATA_ANALYST, null), new Module("PTI90190", "Computer Science Project", 2, false, List.of(), Subject.DATA_ANALYST, null));
        semester2.add(new Module("OPT2", "Optional Course 2", 2, false, optionalGroup2, Subject.DATA_ANALYST, null));

        semester2.add(new Module("WIW34060", "Information & Knowledge Management", 2, false, List.of(), Subject.DATA_ANALYST, null));

        List<Module> optionalGroup3 = Arrays.asList(new Module("WIW30610", "Forschungs- und Projektarbeit I", 2, false, List.of(), Subject.DATA_ANALYST, null), new Module("WIW64021", "Internet of Things (Digitale Technologien)", 2, false, List.of(), Subject.DATA_ANALYST, null), new Module("SPR06590", "Global Business and Project Communication English", 2, false, List.of(), Subject.DATA_ANALYST, null));
        semester2.add(new Module("OPT3", "Optional Course 3", 2, false, optionalGroup3, Subject.DATA_ANALYST, null));

        dataAnalyst.put(2, semester2);

        // Semester 3
        List<Module> semester3 = new ArrayList<>();
        semester3.addAll(Arrays.asList(new Module("PTI90310", "Design and Implementation of Software Systems", 3, true, List.of(), Subject.DATA_ANALYST, null), new Module("WIW34170", "Digital Business Modeling", 3, true, List.of(), Subject.DATA_ANALYST, null), new Module("WIW35010", "Strategic Management", 3, true, List.of(), Subject.DATA_ANALYST, null)));

        // ASUE Modules
        semester3.addAll(Arrays.asList(new Module("ASUE1", "Python Programming", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE), new Module("ASUE2", "Neural Networks and Deep Learning", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE), new Module("ASUE3", "International Coaching Project", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE), new Module("ASUE4", "Business Statistics", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE), new Module("ASUE5", "Digital Marketing", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE)));

        // IBSU Modules
        semester3.addAll(Arrays.asList(new Module("IBSU1", "Models of Computation", 3, false, List.of(), Subject.DATA_ANALYST, Universities.IBSU), new Module("IBSU2", "Deep Reinforcement Learning", 3, false, List.of(), Subject.DATA_ANALYST, Universities.IBSU), new Module("IBSU3", "Artificial Intelligence Applications", 3, false, List.of(), Subject.DATA_ANALYST, Universities.IBSU), new Module("IBSU4", "International Coaching Project", 3, false, List.of(), Subject.DATA_ANALYST, Universities.IBSU)));

        // KAFU Modules
        semester3.addAll(Arrays.asList(new Module("KAFU1", "Architecture of information systems", 3, false, List.of(), Subject.DATA_ANALYST, Universities.KAFU), new Module("KAFU2", "Corporate Governance", 3, false, List.of(), Subject.DATA_ANALYST, Universities.KAFU), new Module("KAFU3", "International Coaching Project", 3, false, List.of(), Subject.DATA_ANALYST, Universities.KAFU), new Module("KAFU4", "Strategic Marketing", 3, false, List.of(), Subject.DATA_ANALYST, Universities.KAFU)));

        // INAI Modules
        semester3.addAll(Arrays.asList(new Module("INAI1", "Legal aspects", 3, false, List.of(), Subject.DATA_ANALYST, Universities.INAI), new Module("INAI2", "Business plan", 3, false, List.of(), Subject.DATA_ANALYST, Universities.INAI), new Module("INAI3", "International Coaching Project", 3, false, List.of(), Subject.DATA_ANALYST, Universities.INAI), new Module("INAI4", "Advanced Marketing", 3, false, List.of(), Subject.DATA_ANALYST, Universities.INAI)));

        dataAnalyst.put(3, semester3);

        // Semester 4
        List<Module> semester4 = new ArrayList<>();
        semester4.addAll(Arrays.asList(new Module("WIW34190", "Expert talks", 4, true, List.of(), Subject.DATA_ANALYST, null), new Module("WIW30650", "Master Project", 4, true, List.of(), Subject.DATA_ANALYST, null)));

        dataAnalyst.put(4, semester4);

        modulesBySubjectAndSemester.put(Subject.DATA_ANALYST, dataAnalyst);
    }

    public List<Module> getModulesForSubjectAndSemester(Subject subject, int semester) {
        return modulesBySubjectAndSemester.getOrDefault(subject, new HashMap<>()).getOrDefault(semester, new ArrayList<>());
    }

    public List<Module> getCompulsoryModules(Subject subject, int semester) {
        return getModulesForSubjectAndSemester(subject, semester).stream().filter(Module::isCompulsory).collect(Collectors.toList());
    }

    public List<Module> getAdditionalModules(Subject subject, int semester) {
        return getModulesForSubjectAndSemester(subject, semester).stream().filter(module -> !module.isCompulsory()).collect(Collectors.toList());
    }

    public List<Module> getStudyAbroadModules(Subject subject, Universities university) {
        return getModulesForSubjectAndSemester(subject, 3).stream().filter(module -> module.getUniversity() == university).collect(Collectors.toList());
    }
}