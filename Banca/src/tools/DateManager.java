package tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateManager {

    private LocalDate dataCorrente;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ITALIAN);

    public DateManager() {
        this.dataCorrente = LocalDate.now();
    }

    public void avanzaDiUnMese() {
        dataCorrente = dataCorrente.plusMonths(1);
    }

    public String getDataCorrente() {
        return dataCorrente.format(DATE_FORMATTER);
    }
}

