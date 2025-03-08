package tests.main.tools;
import main.tools.FileManager;

import static org.junit.jupiter.api.Assertions.*;

import main.tools.LoginManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginManagerTest {

    @Test
    public void testEffettuaLogin_Success() {
        // Mock di FileManager
        FileManager fileManagerMock = Mockito.mock(FileManager.class);
        String[] datiUtente = new String[] {"utente1", "password1", "0.0", "0.0", "1 gennaio 2025", "0.0", "0.0", "0.0", "0.0"};

        // Simuliamo il comportamento di caricaUtente
        Mockito.when(fileManagerMock.caricaUtente("utente1")).thenReturn(datiUtente);

        // Chiamata al metodo effettuaLogin
        String[] risultato = LoginManager.effettuaLogin("utente1", "password1");

        // Verifica che il risultato non sia null e che contenga i dati dell'utente
        assertNotNull(risultato);
        assertEquals("utente1", risultato[0]);
        assertEquals("password1", risultato[1]);
    }

    @Test
    public void testEffettuaLogin_Failure() {
        // Mock di FileManager
        FileManager fileManagerMock = Mockito.mock(FileManager.class);

        // Simuliamo il comportamento di caricaUtente (utente non trovato o password errata)
        Mockito.when(fileManagerMock.caricaUtente("utente1")).thenReturn(null);

        // Chiamata al metodo effettuaLogin
        String[] risultato = LoginManager.effettuaLogin("utente1", "password1");

        // Verifica che il risultato sia null (login fallito)
        assertNull(risultato);
    }

    @Test
    public void testRegistraUtente_Success() {
        // Mock di FileManager
        FileManager fileManagerMock = Mockito.mock(FileManager.class);

        // Simuliamo che l'utente non esista già
        Mockito.when(fileManagerMock.caricaUtente("nuovoUtente")).thenReturn(null);

        // Chiamata al metodo registraUtente
        String[] risultato = LoginManager.registraUtente("nuovoUtente", "passwordNuovo");

        // Verifica che il risultato non sia null e contenga i dati dell'utente
        assertNotNull(risultato);
        assertEquals("nuovoUtente", risultato[0]);
        assertEquals("passwordNuovo", risultato[1]);
    }

    @Test
    public void testRegistraUtente_Failure() {
        // Mock di FileManager
        FileManager fileManagerMock = Mockito.mock(FileManager.class);

        // Simuliamo che l'utente esista già
        String[] datiUtente = new String[] {"utenteEsistente", "passwordEsistente", "0.0", "0.0", "1 gennaio 2025", "0.0", "0.0", "0.0", "0.0"};
        Mockito.when(fileManagerMock.caricaUtente("utenteEsistente")).thenReturn(datiUtente);

        // Chiamata al metodo registraUtente
        String[] risultato = LoginManager.registraUtente("utenteEsistente", "passwordEsistente");

        // Verifica che il risultato sia null (utente già esistente)
        assertNull(risultato);
    }
}


