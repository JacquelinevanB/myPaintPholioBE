# My PaintPholio 
[](uploads/mypaintpholio.png)  

My PaintPholio is een portfolio applicatie die hobbymatige kunstenaars wil helpen hun projecten digitaal te tracken.
My PaintPholio is ontworpen en gecodeerd in het kader van de Bootcamp Fullstack Developer van Novi Hogeschool.

Voor het bouwen van de applicatie is er een functioneel ontwerp en technisch ontwerp gemaakt, die beide een integraal 
onderdeel vormen van de eindopdracht waar deze code dus onderdeel van is. 

## Installatie
[De applicatie bestaat uit deze frontend broncode](https://github.com/JacquelinevanB/myPaintPholioFE)

[In combinatie met deze backend broncode die je nu voor je hebt, te clonen via:](https://github.com/JacquelinevanB/myPaintPholioBE)
- HTTPS: `https://github.com/JacquelinevanB/myPaintPholioBE.git`
- SSH: `git@github.com:JacquelinevanB/myPaintPholioBE.git`


De backed code is geschreven in Java binnen het Spring Boot framework en bevat meerdere lagen t.b.v. bestandsverwerking. 
De structuur van de code is als volgt:
- _De main application_: MyPaintPholioApplication
- _Configuratiebestanden_: Global Cors en Spring Security
- _Controllers_: Authentication, Exception, FileUpload, Person, Project, Quote en Reflection
- _Domain-Entity_: Authority, AutorityKey, FileUploadResponse, Person, Project, Quote, Reflection en User
- _Domain-Dto_: CreatePerson, Person, Project, Quote, Reflection
- _Exceptions_:
- _JwtRequestFilter_
- _Payload_: AuthenticationRequest, AuthenticationResponse
- _Repositories_: FileUpload, Person, Project, Quote, Reflection
- _Service_: CustomUserDetails, FileUpload, Person, Project, Quote, Reflection
- _Utilities_: Jwt, RandomStringGenerator

## Gebruikers instellingen
In de Application.properties staan belangrijke instelingen ten behoeve van de communicatie met de database. Het is 
belangrijk om deze aan te passen met eigen gegevens.
Er is een data.sql bestand gevuld met basic data om een aardige indruk te kunnen krijgen van hoe mooi de applicatie kan werken.

Deze data kan gebruikt worden in combinatie met de volgende accounts:

#### Admin:
- gebruikersnaam: admin
- wachtwoord: adminpassword

#### User:
- gebruikersnaam: user
- wachtwoord: userpassword

Door in te loggen met het admin account krijg je beeld van het admindashboard.
Via het account van de user ervaar je de kernfunctionaliteit van de applicatie. 

![](C:\Users\jacqu\Documents\Jacqueline\Novi\Fullstack\mypaintpholiosmall.png)

## Probleemstelling en oplossing
Super leuk en praktisch om foto's te nemen van tussenfases van je schilderwerk om te zien welke ontwikkeling je doormaakt. 
Foto's raken echter snel zoek in de dagelijkse fotostroom die onze telefoons tegenwoordig overspoelen. 

Deze applicatie biedt een digitale plek om foto's van de diverse fases in het ontstaansproces van een schilderij bij 
elkaar te bewaren. Binnen een afgeschermd profiel is voor ieder schilderwerk een projectpagina beschikbaar. Deze wordt 
na iedere schilderronde aangevuld met een foto - voorzien van reflectie, ideën en aantekeningen. De volgende keer komt 
met de foto's en de aantekeningen meteen weer boven borrelen hoe het ook alweer was. En dat niet alleen: 
er ontstaat voor ieder project een mooi verslag, portfolio van hoe het schilderwerk stap voor stap tot stand gekomen is. 
Waarbij de ontwikkeling teruggelezen kan worden. 

Binnen het gebruikersprofiel staan de projecten overzichtelijk bij elkaar op het dashboard van de gebruiker. Via de 
projecttegel kan doorgeklikt worden naar alle reflecties die bij het project geüpload zijn, waarna er een aantal opties 
zijn om deze op het scherm weer te geven. De reflectietekst bij de foto kan gelezen worden, maar het is ook mogelijk om 
twee foto’s van werkmomenten naast elkaar te vergelijken. Tenslotte kan dat wat is ingevoerd bij project en reflectie 
te allen tijde worden aangepast of aangevuld. 

![](C:\Users\jacqu\Documents\Jacqueline\Novi\Fullstack\Landingpage1.png) 

#### Jacqueline van Burk | copyright © augustus 2022 | Eindopdracht Bootcamp Fullstack Developer

