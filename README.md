## Linee Guida Generali di Progetto

### Architettura e Vincoli Tecnologici
* **Separazione delle versioni:** Le varianti **Pure HTML** e **JavaScript** devono essere sviluppate come due applicazioni web distinte e indipendenti.
* **Vincolo Pure HTML:** Nella versione *Pure HTML* è severamente vietato l'utilizzo di qualsiasi codice JavaScript.
* **Uso di librerie esterne:** L'impiego di librerie aggiuntive è consentito, ma richiede l'inserimento di una sezione dedicata nella documentazione del progetto e la piena conoscenza del loro funzionamento in sede di discussione.

### Validazione e Sicurezza
* **Doppia validazione:** Il controllo di validità dei parametri deve essere eseguito sistematicamente **sia lato client sia lato server**.
* **Gestione degli errori nei form:** In caso di fallimento nel salvataggio di un form, l'applicazione deve ricaricare la pagina corrente, **preservando i dati già inseriti** dall'utente e segnalando chiaramente i campi mancanti o errati.
* **Controllo degli accessi (RBAC):** L'applicazione deve impedire tassativamente agli utenti di eseguire operazioni non previste dal proprio ruolo.
* **Sanificazione e robustezza:** Devono essere implementate misure di sicurezza per prevenire tentativi di manomissione o violazione del sistema tramite l'invio di parametri malevoli o formattati in modo scorretto.

### Funzionalità Opzionali
* **Gestione dati per il testing:** L'implementazione di funzionalità non esplicitamente richieste (es. modifica o cancellazione dei dati) è permessa esclusivamente se utile alle fasi di test. Tali funzioni rimangono opzionali e non saranno oggetto di valutazione.