[00:04, 14/11/2020] Christian Luzzetti: 1. Deve essere possibile inserire un'anagrafica di dipendenti, che abbiano un nome, un cognome, ed una mansione.
La mansione, al momento, può essere:
Barelliere/Infermiere/Medico/Autista
[00:05, 14/11/2020] Christian Luzzetti: 2. Deve essere possibile inserire dei mezzi., che abbiano:
codice (Attenzione, non è l'id), targa, ente di appartenenza, riferimenti

(I riferimenti, sono uno o più numeri di telefono)
[00:07, 14/11/2020] Christian Luzzetti: (Mi sono dimenticato. Il mezzo, deve avere anche un tipo. AM/MSB/MSA
[00:07, 14/11/2020] Christian Luzzetti: am sarebbe l'automedica, MSB è l'ambulanza grossa normale. MSA è l'ambulanza grossa con a bordo un medico.
Sticazzi, era solo per rendertene partecipe
[00:07, 14/11/2020] Christian Luzzetti: Tornando al punto 3
[00:08, 14/11/2020] Christian Luzzetti: Deve essere possibile inserire dei turni
[00:10, 14/11/2020] Christian Luzzetti: Ogni turno, è composto da:

- 1 mezzo, 
- Membri dell'equipaggio. (I dipendenti di prima), almeno 2, massimo 3
- Il tipo di turno. (Notturno o Diurno)
- la zona (Roma, o provincia)
- La postazione. (Al momento è una stringa, immaginala come un indirizzo)
- L'orario in cui inizia il turno
- L'orario in cui dovrebbe finire il turno
- L'orario in cui finisce effettivamente il turno.
[00:11, 14/11/2020] Christian Luzzetti: Ti spiego gli ultimi due valori.

Questa roba, deve essere inserita a priori.
Ad esempio: il mezzo 0135, MSB, nella postazione di TIVOLI, fa un turno notturno dalle 20:00 alle 8:00 del giorno dopo.
[00:11, 14/11/2020] Christian Luzzetti: Ora, magari il giorno dopo il mezzo sta ancora su un soccorso, e fa gli straordinari, finendo alle 8:45
[00:12, 14/11/2020] Christian Luzzetti: quindi, inizio turno e fine turno, li inserisco prima.
Se poi vedo che il mezzo ha fatto gli straordinari, in un campo apposito, inserisco l'orario in cui ha effettivamente concluso il turno
[00:13, 14/11/2020] Christian Luzzetti: (Questi campi, vorrei si chiamassero:

istanteInizio
istanteFine
istanteFineEffettivo

)
[00:14, 14/11/2020] Christian Luzzetti: Ovviamente, ci sono dei requisiti di business (Finalmente usiamo i services!)

1- Un mezzo, non può stare su due turni che si accavallano, ovviamente
[00:16, 14/11/2020] Christian Luzzetti: Così come un dipendente, non può stare contemporaneamente su due mezzi contemporaneamente.

(Mezzo A in turno dalle 8:00 alle 20)
(Mezzo B in turno dalle 7:00 alle 19:00)

è impossibile che Luzzetti Christian stia contemporaneamente assegnato sia a quel turno, sia all'altro
[00:16, 14/11/2020] Christian Luzzetti: Però, se due turni non si accavallano, il tizio può farlo
[00:16, 14/11/2020] Christian Luzzetti: Ok, direi che così può bastare