package org.rr.moduleselector.pdf;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.rr.moduleselector.selector.model.Module;
import org.rr.moduleselector.selector.model.Universities;
import org.rr.moduleselector.survey.model.SurveyData;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class PdfGenerationService {
    private static final Color PRIMARY_COLOR = new DeviceRgb(63, 81, 181);  // Material Indigo
    private static final Color SECONDARY_COLOR = new DeviceRgb(92, 107, 192);  // Lighter Indigo
    private static final float SECTION_SPACING = 20f;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public byte[] generateModulesPdf(SurveyData userData, Map<Integer, List<Module>> compulsoryModules, Map<Integer, List<Module>> chosenModules, List<Module> partnerModules) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfDocument pdf = new PdfDocument(new PdfWriter(baos));
            pdf.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdf);
            document.setMargins(36, 36, 36, 36);

            PdfFont titleFont = PdfFontFactory.createFont();
            PdfFont headerFont = PdfFontFactory.createFont();
            PdfFont normalFont = PdfFontFactory.createFont();

            // Title
            addTitle(document, titleFont);

            // User Information
            addUserInformation(document, userData, headerFont, normalFont);

            // Selected Subject
            if (!compulsoryModules.isEmpty() && !compulsoryModules.get(1).isEmpty()) {
                String subject = compulsoryModules.get(1).get(0).getSubject().getDisplayName();
                addSubjectSection(document, subject, headerFont);
            }

            // Add modules semester by semester in correct order
            for (int semester = 1; semester <= 4; semester++) {
                if (semester == 3 && partnerModules != null && !partnerModules.isEmpty()) {
                    // Special handling for semester 3 with partner university
                    addSemester3WithPartner(document, compulsoryModules.get(3), partnerModules, headerFont, normalFont);
                } else {
                    addSemesterSection(document, semester, compulsoryModules.get(semester), chosenModules.get(semester), headerFont, normalFont);
                }
            }

            // Footer with timestamp
            addFooter(document, normalFont);

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

    private void addSemester3WithPartner(Document document, List<Module> compulsoryModules, List<Module> partnerModules, PdfFont headerFont, PdfFont normalFont) {
        // Semester 3 header
        Table semesterTable = new Table(1).setWidth(UnitValue.createPercentValue(100));

        Cell semesterCell = new Cell().add(new Paragraph("Semester 3")).setFont(headerFont).setFontSize(14).setFontColor(ColorConstants.WHITE).setBackgroundColor(PRIMARY_COLOR).setPadding(8);

        semesterTable.addCell(semesterCell);
        document.add(semesterTable);

        // Compulsory modules
        if (compulsoryModules != null && !compulsoryModules.isEmpty()) {
            addModuleList(document, "Compulsory Modules", compulsoryModules, headerFont, normalFont);
        }

        // Partner University section
        if (partnerModules != null && !partnerModules.isEmpty()) {
            Universities university = partnerModules.get(0).getUniversity();

            Table uniTable = new Table(1).setWidth(UnitValue.createPercentValue(100));

            Cell uniCell = new Cell().add(new Paragraph("Selected Partner University: " + university.getDisplayName() + " (" + university.getCountry() + ")")).setFont(headerFont).setFontSize(14).setFontColor(ColorConstants.WHITE).setBackgroundColor(PRIMARY_COLOR).setPadding(8);

            uniTable.addCell(uniCell);
            document.add(uniTable);

            // Add partner modules table
            Table moduleTable = new Table(1).setWidth(UnitValue.createPercentValue(100));

            Cell titleCell = new Cell().add(new Paragraph("Selected Partner University Modules")).setFont(headerFont).setFontSize(12).setFontColor(ColorConstants.WHITE).setBackgroundColor(SECONDARY_COLOR).setPadding(5);
            moduleTable.addCell(titleCell);

            // Add all selected modules (including those from alternatives)
            for (Module module : partnerModules) {
                if (module.getAlternative().isEmpty()) {
                    // Regular module
                    Cell moduleCell = new Cell().add(new Paragraph(getModuleNameOnly(module))).setFont(normalFont).setFontSize(11).setPadding(5);
                    moduleTable.addCell(moduleCell);
                } else {
                    // For modules with alternatives, add the selected alternative
                    for (Module alt : module.getAlternative()) {
                        Cell moduleCell = new Cell().add(new Paragraph(getModuleNameOnly(alt))).setFont(normalFont).setFontSize(11).setPadding(5);
                        moduleTable.addCell(moduleCell);
                    }
                }
            }

            document.add(moduleTable);
        }

        document.add(new Paragraph().setMarginBottom(SECTION_SPACING));
    }

    private String getModuleNameOnly(Module module) {
        String fullName = module.getFullName();
        int dashIndex = fullName.indexOf(" - ");
        if (dashIndex != -1) {
            return fullName.substring(dashIndex + 3);
        }
        return fullName;
    }

    private void addModuleList(Document document, String title, List<Module> modules, PdfFont headerFont, PdfFont normalFont) {
        Table moduleTable = new Table(1).setWidth(UnitValue.createPercentValue(100));

        Cell titleCell = new Cell().add(new Paragraph(title)).setFont(headerFont).setFontSize(12).setFontColor(ColorConstants.WHITE).setBackgroundColor(SECONDARY_COLOR).setPadding(5);
        moduleTable.addCell(titleCell);

        // For partner university modules, show only names without IDs
        boolean isPartnerModule = modules.stream().anyMatch(m -> m.getUniversity() != null);

        for (Module module : modules) {
            if (!module.getAlternative().isEmpty()) {
                continue;
            }
            String displayText = isPartnerModule ? getModuleNameOnly(module) : module.getFullName();
            Cell moduleCell = new Cell().add(new Paragraph(displayText)).setFont(normalFont).setFontSize(11).setPadding(5);
            moduleTable.addCell(moduleCell);
        }

        document.add(moduleTable);
    }

    private void addTitle(Document document, PdfFont titleFont) {
        document.add(new Paragraph("Module Selection Overview").setFont(titleFont).setFontSize(24).setFontColor(PRIMARY_COLOR).setTextAlignment(TextAlignment.CENTER).setMarginBottom(SECTION_SPACING));
    }

    private void addUserInformation(Document document, SurveyData userData, PdfFont headerFont, PdfFont normalFont) {
        Table userInfo = new Table(new float[]{1, 2}).setWidth(UnitValue.createPercentValue(100));

        Cell headerCell = new Cell(1, 2).add(new Paragraph("Personal Information")).setFont(headerFont).setFontSize(14).setFontColor(ColorConstants.WHITE).setBackgroundColor(PRIMARY_COLOR).setPadding(8);
        userInfo.addHeaderCell(headerCell);

        addUserInfoRow(userInfo, "Name", userData.getName(), normalFont);
        addUserInfoRow(userInfo, "Surname", userData.getSurname(), normalFont);
        addUserInfoRow(userInfo, "Matriculation", (userData.getMatriculation() != null && !userData.getMatriculation().isEmpty()) ? userData.getMatriculation() : "Not available", normalFont);
        addUserInfoRow(userInfo, "Email", userData.getEmail(), normalFont);
        addUserInfoRow(userInfo, "Country", userData.getCountry(), normalFont);
        addUserInfoRow(userInfo, "Birth Date", userData.getBirthDate() != null ? userData.getBirthDate().format(DATE_FORMATTER) : "Not available", normalFont);

        document.add(userInfo);
        document.add(new Paragraph().setMarginBottom(SECTION_SPACING));
    }

    private void addSubjectSection(Document document, String subject, PdfFont headerFont) {
        Table subjectTable = new Table(1).setWidth(UnitValue.createPercentValue(100));

        Cell subjectCell = new Cell().add(new Paragraph("Selected Subject: " + subject)).setFont(headerFont).setFontSize(14).setFontColor(ColorConstants.WHITE).setBackgroundColor(PRIMARY_COLOR).setPadding(8);

        subjectTable.addCell(subjectCell);
        document.add(subjectTable);
        document.add(new Paragraph().setMarginBottom(SECTION_SPACING));
    }

    private void addSemesterSection(Document document, int semester, List<Module> compulsoryModules, List<Module> optionalModules, PdfFont headerFont, PdfFont normalFont) {
        // Semester header
        Table semesterTable = new Table(1).setWidth(UnitValue.createPercentValue(100));

        Cell semesterCell = new Cell().add(new Paragraph("Semester " + semester)).setFont(headerFont).setFontSize(14).setFontColor(ColorConstants.WHITE).setBackgroundColor(PRIMARY_COLOR).setPadding(8);

        semesterTable.addCell(semesterCell);
        document.add(semesterTable);

        // Modules table
        if (compulsoryModules != null && !compulsoryModules.isEmpty()) {
            addModuleList(document, "Compulsory Modules", compulsoryModules, headerFont, normalFont);
        }

        if (optionalModules != null && !optionalModules.isEmpty()) {
            addModuleList(document, "Selected Optional Modules", optionalModules, headerFont, normalFont);
        }

        document.add(new Paragraph().setMarginBottom(SECTION_SPACING));
    }

    private void addUserInfoRow(Table table, String label, String value, PdfFont font) {
        table.addCell(new Cell().add(new Paragraph(label).setFont(font)).setFontSize(11).setPadding(5).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.2f));

        table.addCell(new Cell().add(new Paragraph(value).setFont(font)).setFontSize(11).setPadding(5).setBorder(Border.NO_BORDER));
    }

    private void addFooter(Document document, PdfFont normalFont) {
        String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        document.add(new Paragraph("Generated on: " + timestamp).setFont(normalFont).setFontSize(8).setTextAlignment(TextAlignment.RIGHT));
    }
}