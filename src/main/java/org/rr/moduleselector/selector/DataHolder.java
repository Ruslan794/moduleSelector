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
        semester3.addAll(Arrays.asList(new Module("KAFU1", "International Coaching Project", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.KAFU), new Module("KAFU2", "Project Management", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.KAFU)));


        Module kafu3 = new Module("KAFU3", "Corporate Governance", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.KAFU);
        Module kafu4 = new Module("KAFU4", "Architecture of information systems", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.KAFU);
        Module kafuOpt = new Module("OPT-KAFU-1", "Optional Course 1", 3, false, Arrays.asList(kafu3, kafu4), Subject.SCIENTIFIC_ASSISTANT, Universities.KAFU);
        semester3.add(kafuOpt);

        // INAI Modules
        semester3.addAll(Arrays.asList(new Module("INAI1", "International Coaching Project", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.INAI), new Module("INAI2", "Philosophical problems of science", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.INAI)));


        Module inai3 = new Module("INAI3", "Business processes in software development", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.INAI);
        Module inai4 = new Module("INAI4", "Enterprise Application Development", 3, false, List.of(), Subject.SCIENTIFIC_ASSISTANT, Universities.INAI);

        Module inaiOpt = new Module("OPT-INAI-1", "Optional Course 1", 3, false, Arrays.asList(inai3, inai4), Subject.SCIENTIFIC_ASSISTANT, Universities.INAI);
        semester3.add(inaiOpt);

        scientificAssistant.put(3, semester3);

        // Semester 4
        List<Module> semester4 = new ArrayList<>();
        semester4.addAll(Arrays.asList(new Module("WIW34190", "Expert talks", 4, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null), new Module("WIW30650", "Master Project", 4, true, List.of(), Subject.SCIENTIFIC_ASSISTANT, null)));

        scientificAssistant.put(4, semester4);

        modulesBySubjectAndSemester.put(Subject.SCIENTIFIC_ASSISTANT, scientificAssistant);
    }

    private void initializeBusinessAllrounder() {
        Map<Integer, List<Module>> businessAllrounder = new HashMap<>();

        // Semester 1 (same as before)
        List<Module> semester1 = new ArrayList<>();
        semester1.addAll(Arrays.asList(new Module("WIW34200", "Applied Programming Project", 1, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW64000", "Business Information Systems", 1, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW32090", "Risk Management and Management Control", 1, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW03750", "Training of Language, Research and Intercultural Skills", 1, true, List.of(), Subject.BUSINESS_ALLROUNDER, null)));

        // Optional with alternatives for semester 1
        Module pti90190 = new Module("PTI90190", "Computer Science project", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        Module pti90180 = new Module("PTI90180", "Car-to-Car Communication", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        semester1.add(new Module("OPT1", "Optional Course 1", 1, false, Arrays.asList(pti90190, pti90180), Subject.BUSINESS_ALLROUNDER, null));

        Module wiw64010 = new Module("WIW64010", "Digital Business Models", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        Module wiw31500 = new Module("WIW31500", "Managing Challenges in the Globalized Economy", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        Module wiw00390 = new Module("WIW00390", "Change Management", 1, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        semester1.add(new Module("OPT2", "Optional Course 2", 1, false, Arrays.asList(wiw64010, wiw31500, wiw00390), Subject.BUSINESS_ALLROUNDER, null));

        businessAllrounder.put(1, semester1);

        // Semester 2
        List<Module> semester2 = new ArrayList<>();
        semester2.addAll(Arrays.asList(new Module("PTI90290", "Machine Learning", 2, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW64031", "Analytics for Data Driven Decisions", 2, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW32530", "Advanced Fields of Management", 2, true, List.of(), Subject.BUSINESS_ALLROUNDER, null)));

        // Optional with alternatives for semester 2
        Module pti90080 = new Module("PTI90080", "Large Scale Data Processing", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        Module pti90190_sem2 = new Module("PTI90190", "Computer Science Project", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        semester2.add(new Module("OPT3", "Optional Course 3", 2, false, Arrays.asList(pti90080, pti90190_sem2), Subject.BUSINESS_ALLROUNDER, null));

        Module wiw34060 = new Module("WIW34060", "Information & Knowledge Management", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        Module wiw64021 = new Module("WIW64021", "Internet of Things (Digitale Technologien)", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        semester2.add(new Module("OPT4", "Optional Course 4", 2, false, Arrays.asList(wiw34060, wiw64021), Subject.BUSINESS_ALLROUNDER, null));

        Module wiw32040 = new Module("WIW32040", "Business Monitoring Systems and Internal Audit", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        Module wiw67500 = new Module("WIW67500", "Business Cultures", 2, false, List.of(), Subject.BUSINESS_ALLROUNDER, null);
        semester2.add(new Module("OPT5", "Optional Course 5", 2, false, Arrays.asList(wiw32040, wiw67500), Subject.BUSINESS_ALLROUNDER, null));

        businessAllrounder.put(2, semester2);

        // Semester 3
        List<Module> semester3 = new ArrayList<>();
        semester3.addAll(Arrays.asList(new Module("PTI90310", "Design and Implementation of Software Systems", 3, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW34170", "Digital Business Modeling", 3, true, List.of(), Subject.BUSINESS_ALLROUNDER, null), new Module("WIW35010", "Strategic Management", 3, true, List.of(), Subject.BUSINESS_ALLROUNDER, null)));

        // ASUE Modules - grouped into alternatives
        Module asue1 = new Module("ASUE1", "Python Programming", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE);
        Module asue2 = new Module("ASUE2", "Neural Networks and Deep Learning", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE);
        Module asue3 = new Module("ASUE3", "Decision Making Process", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE);
        Module asue4 = new Module("ASUE4", "Digital Marketing", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE);
        Module asue5 = new Module("ASUE5", "International Coaching Project", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.ASUE);
        semester3.addAll(Arrays.asList(asue1, asue2));
        semester3.add(new Module("OPT-ASUE", "ASUE Optional Courses", 3, false, Arrays.asList(asue3, asue4, asue5), Subject.BUSINESS_ALLROUNDER, Universities.ASUE));

        // IBSU Modules - grouped into alternatives
        Module ibsu1 = new Module("IBSU1", "Advanced Algorithms", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU);
        Module ibsu2 = new Module("IBSU2", "Human - Machine Interaction", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU);
        Module ibsu3 = new Module("IBSU3", "Artificial Intelligence Applications", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU);
        Module ibsu4 = new Module("IBSU4", "Leadership Strategies", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU);
        Module ibsu5 = new Module("IBSU5", "Advanced Project Management", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU);
        Module ibsu6 = new Module("IBSU6", "International Coaching Project", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.IBSU);
        semester3.add(ibsu1);
        semester3.add(new Module("OPT-IBSU1", "IBSU Optional Courses 1", 3, false, Arrays.asList(ibsu2, ibsu3), Subject.BUSINESS_ALLROUNDER, Universities.IBSU));
        semester3.add(new Module("OPT-IBSU2", "IBSU Optional Courses 2", 3, false, Arrays.asList(ibsu4, ibsu5, ibsu6), Subject.BUSINESS_ALLROUNDER, Universities.IBSU));

        // KAFU Modules - grouped into alternatives
        Module kafu1 = new Module("KAFU1", "Computing systems and telecommunications network", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU);
        Module kafu2 = new Module("KAFU2", "Architecture of information systems", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU);
        Module kafu3 = new Module("KAFU3", "Strategic Marketing", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU);
        Module kafu4 = new Module("KAFU4", "Personnel Management", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU);
        Module kafu5 = new Module("KAFU5", "International Coaching Project", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.KAFU);
        semester3.addAll(Arrays.asList(kafu1, kafu2));
        semester3.add(new Module("OPT-KAFU", "KAFU Optional Courses", 3, false, Arrays.asList(kafu3, kafu4, kafu5), Subject.BUSINESS_ALLROUNDER, Universities.KAFU));

        // INAI Modules - grouped into alternatives
        Module inai1 = new Module("INAI1", "Advanced Marketing", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI);
        Module inai2 = new Module("INAI2", "International Coaching Project", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI);
        Module inai3 = new Module("INAI3", "Business progresses in software development", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI);
        Module inai4 = new Module("INAI4", "Enterprise Application Development", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI);
        Module inai5 = new Module("INAI5", "Legal aspects", 3, false, List.of(), Subject.BUSINESS_ALLROUNDER, Universities.INAI);

        semester3.add(new Module("OPT-INAI1", "INAI Optional Courses 1", 3, false, Arrays.asList(inai1, inai2), Subject.BUSINESS_ALLROUNDER, Universities.INAI));
        semester3.add(inai2);
        semester3.add(new Module("OPT-INAI2", "INAI Optional Courses 2", 3, false, Arrays.asList(inai4, inai5), Subject.BUSINESS_ALLROUNDER, Universities.INAI));


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

        // Optional with alternatives for semester 1
        Module wiw00390 = new Module("WIW00390", "Change Management", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        Module pti90190 = new Module("PTI90190", "Computer Science Project", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        semester1.add(new Module("OPT1", "Optional Course 1", 1, false, Arrays.asList(wiw00390, pti90190), Subject.IT_PROJECT_MANAGER, null));

        Module wiw65540 = new Module("WIW65540", "International Human Resource Management", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        Module wiw31500 = new Module("WIW31500", "Managing Challenges in the Globalized Economy", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        Module wiw08520 = new Module("WIW08520", "Managing Intercultural Collaboration", 1, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        semester1.add(new Module("OPT2", "Optional Course 2", 1, false, Arrays.asList(wiw65540, wiw31500, wiw08520), Subject.IT_PROJECT_MANAGER, null));

        itProjectManager.put(1, semester1);

        // Semester 2
        List<Module> semester2 = new ArrayList<>();
        semester2.addAll(Arrays.asList(new Module("PTI90290", "Machine Learning", 2, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW64031", "Analytics for Data Driven Decisions", 2, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW32530", "Advanced Fields of Management", 2, true, List.of(), Subject.IT_PROJECT_MANAGER, null)));

        Module wiw67500 = new Module("WIW67500", "Business Cultures", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        Module wiw32040 = new Module("WIW32040", "Business Monitoring Systems and Internal Audit", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        semester2.add(new Module("OPT3", "Optional Course 3", 2, false, Arrays.asList(wiw67500, wiw32040), Subject.IT_PROJECT_MANAGER, null));

        semester2.add(new Module("SPR06590", "Global Business and Project Communication English", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null));

        Module wiw64021 = new Module("WIW64021", "Internet of Things (Digitale Technologien)", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        Module wiw34060 = new Module("WIW34060", "Information & Knowledge Management", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        Module pti90190_sem2 = new Module("PTI90190", "Computer Science Project", 2, false, List.of(), Subject.IT_PROJECT_MANAGER, null);
        semester2.add(new Module("OPT4", "Optional Course 4", 2, false, Arrays.asList(wiw64021, wiw34060, pti90190_sem2), Subject.IT_PROJECT_MANAGER, null));

        itProjectManager.put(2, semester2);

        // Semester 3
        List<Module> semester3 = new ArrayList<>();
        semester3.addAll(Arrays.asList(new Module("PTI90310", "Design and Implementation of Software Systems", 3, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW34170", "Digital Business Modeling", 3, true, List.of(), Subject.IT_PROJECT_MANAGER, null), new Module("WIW35010", "Strategic Management", 3, true, List.of(), Subject.IT_PROJECT_MANAGER, null)));


        // IBSU Modules - grouped into alternatives
        Module ibsu1 = new Module("IBSU1", "International Coaching Project", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU);
        Module ibsu2 = new Module("IBSU2", "Advanced Project Management", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU);
        Module ibsu3 = new Module("IBSU3", "Leadership Strategies", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU);
        Module ibsu4 = new Module("IBSU4", "Computer Networks and Security", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU);
        Module ibsu5 = new Module("IBSU5", "Models of Computation", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU);
        Module ibsu6 = new Module("IBSU6", "Artificial Intelligence Application", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU);
        Module ibsu7 = new Module("IBSU7", "Human-Machine Interaction", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.IBSU);

        semester3.add(ibsu3);
        semester3.add(new Module("OPT-IBSU1", "IBSU Optional Courses 1", 3, false, Arrays.asList(ibsu1, ibsu2), Subject.IT_PROJECT_MANAGER, Universities.IBSU));
        semester3.add(new Module("OPT-IBSU2", "IBSU Optional Courses 2", 3, false, Arrays.asList(ibsu4, ibsu5, ibsu6, ibsu7), Subject.IT_PROJECT_MANAGER, Universities.IBSU));

        // KAFU Modules - grouped into alternatives
        Module kafu1 = new Module("KAFU1", "Computing systems and telecommunications networks", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU);
        Module kafu2 = new Module("KAFU2", "Corporate Governance", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU);
        Module kafu3 = new Module("KAFU3", "Personnel Management", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU);
        Module kafu4 = new Module("KAFU4", "International Coaching Project", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU);
        Module kafu5 = new Module("KAFU5", "Project Management", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.KAFU);

        semester3.add(ibsu1);
        semester3.add(new Module("OPT-KAFU1", "KAFU Optional Courses 1", 3, false, Arrays.asList(kafu2, kafu3), Subject.IT_PROJECT_MANAGER, Universities.KAFU));
        semester3.add(new Module("OPT-KAFU2", "KAFU Optional Courses 2", 3, false, Arrays.asList(kafu4, kafu5), Subject.IT_PROJECT_MANAGER, Universities.KAFU));


        // ASUE Modules - grouped into alternatives
        Module asue1 = new Module("ASUE1", "Decision Making Process", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE);
        Module asue2 = new Module("ASUE2", "Digital Marketing", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE);
        Module asue3 = new Module("ASUE3", "International Coaching Project", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE);
        Module asue4 = new Module("ASUE4", "Neural Networks and Deep Learning", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE);
        Module asue5 = new Module("ASUE5", "Python Programming", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE);
        Module asue6 = new Module("ASUE6", "Business Statistics", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.ASUE);

        semester3.add(asue3);
        semester3.add(new Module("OPT-ASUE1", "ASUE Optional Courses 1", 3, false, Arrays.asList(asue1, asue2), Subject.IT_PROJECT_MANAGER, Universities.ASUE));
        semester3.add(new Module("OPT-ASUE2", "ASUE Optional Courses 2", 3, false, Arrays.asList(asue5, asue6), Subject.IT_PROJECT_MANAGER, Universities.ASUE));


        // INAI Modules - grouped into alternatives
        Module inai1 = new Module("INAI1", "Enterprise Application Development", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI);
        Module inai2 = new Module("INAI2", "Business processes in software development", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI);
        Module inai3 = new Module("INAI3", "International Coaching Project", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI);
        Module inai4 = new Module("INAI4", "Startup coaching", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI);
        Module inai5 = new Module("INAI5", "Business plan", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI);
        Module inai6 = new Module("INAI6", "Legal aspects", 3, false, List.of(), Subject.IT_PROJECT_MANAGER, Universities.INAI);

        semester3.add(inai3);
        semester3.add(new Module("OPT-INAI1", "INAI Optional Courses 1", 3, false, Arrays.asList(inai1, inai2), Subject.IT_PROJECT_MANAGER, Universities.INAI));
        semester3.add(new Module("OPT-INAI2", "INAI Optional Courses 2", 3, false, Arrays.asList(inai4, inai5, inai6), Subject.IT_PROJECT_MANAGER, Universities.INAI));

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
        semester3.add(new Module("ASUE1", "Python Programming", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.ASUE));
        semester3.add(new Module("ASUE2", "Neural Networks and Deep Learning", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.ASUE));

        Module asue3 = new Module("ASUE3", "International Coaching Project", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.ASUE);
        Module asue4 = new Module("ASUE4", "Advanced Project Management", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.ASUE);
        semester3.add(new Module("OPT-ASUE", "ASUE Optional Courses", 3, false, Arrays.asList(asue3, asue4), Subject.SOFTWARE_ENGINEER, Universities.ASUE));

        // IBSU Modules
        semester3.add(new Module("IBSU1", "Advanced Algorithms", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU));
        Module ibsu2 = new Module("IBSU2", "Artificial Intelligence Applications", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU);
        Module ibsu3 = new Module("IBSU3", "Numerical Analysis", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU);

        Module ibsu4 = new Module("IBSU4", "International Coaching Project", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU);
        Module ibsu5 = new Module("IBSU5", "Models of Computation", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.IBSU);
        semester3.add(new Module("OPT-IBSU 1", "IBSU Optional Courses 1", 3, false, Arrays.asList(ibsu2, ibsu4), Subject.SOFTWARE_ENGINEER, Universities.IBSU));
        semester3.add(new Module("OPT-IBSU 2", "IBSU Optional Courses 1", 3, false, Arrays.asList(ibsu3, ibsu5), Subject.SOFTWARE_ENGINEER, Universities.IBSU));

        // KAFU Modules
        semester3.add(new Module("KAFU1", "Computing systems and telecommunications networks", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.KAFU));
        semester3.add(new Module("KAFU2", "Architecture of information systems", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.KAFU));
        semester3.add(new Module("KAFU3", "International Coaching Project", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.KAFU));

        // INAI Modules
        semester3.add(new Module("INAI1", "Enterprise Application Development", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.INAI));
        semester3.add(new Module("INAI2", "Business processes in software development", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.INAI));

        Module inai3 = new Module("INAI3", "International Coaching Project", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.INAI);
        Module inai4 = new Module("INAI4", "Legal aspects", 3, false, List.of(), Subject.SOFTWARE_ENGINEER, Universities.INAI);
        semester3.add(new Module("OPT-INAI", "INAI Optional Courses", 3, false, Arrays.asList(inai3, inai4), Subject.SOFTWARE_ENGINEER, Universities.INAI));

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
        semester3.add(new Module("ASUE1", "Python Programming", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE));
        semester3.add(new Module("ASUE2", "Neural Networks and Deep Learning", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE));

        Module asue3 = new Module("ASUE3", "International Coaching Project", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE);
        Module asue4 = new Module("ASUE4", "Business Statistics", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE);
        Module asue5 = new Module("ASUE5", "Digital Marketing", 3, false, List.of(), Subject.DATA_ANALYST, Universities.ASUE);
        semester3.add(new Module("OPT-ASUE", "ASUE Optional Courses", 3, false, Arrays.asList(asue3, asue4, asue5), Subject.DATA_ANALYST, Universities.ASUE));

        // IBSU Modules
        semester3.add(new Module("IBSU1", "Models of Computation", 3, false, List.of(), Subject.DATA_ANALYST, Universities.IBSU));
        semester3.add(new Module("IBSU2", "Deep Reinforcement Learning", 3, false, List.of(), Subject.DATA_ANALYST, Universities.IBSU));

        Module ibsu3 = new Module("IBSU3", "Artificial Intelligence Applications", 3, false, List.of(), Subject.DATA_ANALYST, Universities.IBSU);
        Module ibsu4 = new Module("IBSU4", "International Coaching Project", 3, false, List.of(), Subject.DATA_ANALYST, Universities.IBSU);
        semester3.add(new Module("OPT-IBSU", "IBSU Optional Courses", 3, false, Arrays.asList(ibsu3, ibsu4), Subject.DATA_ANALYST, Universities.IBSU));

        // KAFU Modules
        semester3.add(new Module("KAFU1", "Architecture of information systems", 3, false, List.of(), Subject.DATA_ANALYST, Universities.KAFU));
        semester3.add(new Module("KAFU2", "Corporate Governance", 3, false, List.of(), Subject.DATA_ANALYST, Universities.KAFU));

        Module kafu3 = new Module("KAFU3", "International Coaching Project", 3, false, List.of(), Subject.DATA_ANALYST, Universities.KAFU);
        Module kafu4 = new Module("KAFU4", "Strategic Marketing", 3, false, List.of(), Subject.DATA_ANALYST, Universities.KAFU);
        semester3.add(new Module("OPT-KAFU", "KAFU Optional Courses", 3, false, Arrays.asList(kafu3, kafu4), Subject.DATA_ANALYST, Universities.KAFU));

        // INAI Modules
        semester3.add(new Module("INAI1", "Legal aspects", 3, false, List.of(), Subject.DATA_ANALYST, Universities.INAI));
        semester3.add(new Module("INAI2", "Business plan", 3, false, List.of(), Subject.DATA_ANALYST, Universities.INAI));

        Module inai3 = new Module("INAI3", "International Coaching Project", 3, false, List.of(), Subject.DATA_ANALYST, Universities.INAI);
        Module inai4 = new Module("INAI4", "Advanced Marketing", 3, false, List.of(), Subject.DATA_ANALYST, Universities.INAI);
        semester3.add(new Module("OPT-INAI", "INAI Optional Courses", 3, false, Arrays.asList(inai3, inai4), Subject.DATA_ANALYST, Universities.INAI));

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