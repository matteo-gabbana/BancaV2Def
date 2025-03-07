package tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateManager {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ITALIAN);
    private LocalDate dataCorrente;

    public DateManager(String dataSalvata) {
        LocalDate dataSalvataUtente = LocalDate.parse(dataSalvata, DATE_FORMATTER);
        LocalDate dataAttuale = LocalDate.now();

        if (dataSalvataUtente.isBefore(dataAttuale)) {
            this.dataCorrente = dataAttuale;
        } else {
            this.dataCorrente = dataSalvataUtente;
        }
    }

    public void avanzaDiUnMese() {
        dataCorrente = dataCorrente.plusMonths(1);
    }

    public String getDataCorrente() {
        return dataCorrente.format(DATE_FORMATTER);
    }
}

