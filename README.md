# Kolači — Sistem za evidenciju prodaje kolača

Klijent-server desktop aplikacija za vođenje prodaje u poslastičarnici. Podržava rad više poslasticara istovremeno preko sopstvenog socket protokola.

## Arhitektura

Rešenje je podeljeno na tri NetBeans modula:

| Modul | Uloga |
| :--- | :--- |
| **KolacZajednicki** | Domenski model, transfer objekti i enum operacija. Pakuje se kao JAR koji koriste i klijent i server. |
| **KolacServer** | Sluša konekcije, za svakog klijenta pokreće zasebnu nit, izvršava sistemske operacije i komunicira sa bazom kroz generički `DBBroker`. |
| **KolacKlijent** | Swing grafički interfejs — forme za prijavu, kupce, kolače i račune. |

Komunikacija ide preko Java serijalizacije objekata (`ObjectOutputStream` / `ObjectInputStream`) nad TCP socketom. Klijent šalje zahtev sa oznakom operacije, server vraća odgovor sa rezultatom ili greškom.

## Funkcionalnosti

- Prijava poslasticara na sistem
- Evidencija kupaca — unos, pretraga, izmena
- Evidencija kolača — unos, pretraga, izmena
- Kreiranje računa sa više stavki, sa automatskim obračunom iznosa
- Pretraga i pregled detalja postojećih računa

## Tehnologije

- Java SE (Swing)
- MySQL
- JDBC
- Apache Ant (NetBeans build)

## Model podataka

Tabela i struktura baze: `kupac`, `mesto`, `kolac`, `poslasticar`, `racun`, `stavkaracuna`. Kompletna šema se nalazi u `kolaci.sql`.

## Pokretanje

### 1. Baza

```sql
CREATE DATABASE kolaci2;
USE kolaci2;
SOURCE kolaci.sql;
```
### 2. Konfiguracija konekcije
U folderu KolacServer/ napravi fajl dbconfig.properties po uzoru na dbconfig.properties.example:
```properties
url=jdbc:mysql://localhost:3306/kolaci2
username=tvoj_korisnik
password=tvoja_lozinka
```
### 3. Redosled pokretanja
Build KolacZajednicki (pravi JAR od koga zavise ostala dva modula)

Pokreni KolacServer — server sluša konekcije

Pokreni KolacKlijent — otvara se forma za prijavu

## Napomene o implementaciji

`DBBroker` je implementiran kao generički broker nad apstraktnom klasom `AbstractDomainObject`. Svaka domenska klasa sama opisuje naziv svoje tabele, kolone i uslove, pa su CRUD operacije napisane jednom i rade nad svim entitetima bez dupliranja koda.

Sistemske operacije su realizovane po Template Method obrascu — apstraktna operacija definiše redosled koraka (validacija, izvršenje, transakcija), a konkretne operacije popunjavaju samo svoju logiku.
