# Egyedi fájlnév kereső alkalmazás

## Leírás
Ez a projekt egy egyszerű API-t kínál a felhasználóknak, hogy egyedi fájlokat keressenek egy megadott könyvtárban, valamint lekérjék korábbi kereséseik részleteit. Az API használata rendkívül egyszerű, és lehetővé teszi a fájlok egyediségének ellenőrzését a megadott könyvtárban.

## Futtatás

- Az alkalmazás forráskódja a GitHub-on található.
- https://github.com/promlock/uniquefinder
- Az alkalmazásból képfájl is készül, amely alább érhető el:
- https://hub.docker.com/repository/docker/kovacsp22/uniquefinder/general

- Az alaklmazás pull után a make paranccsal indítható. 2 példányban indul, amelyek itt érhetőek el:
- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/swagger-ui/index.html


## Végpontok

Az API két fő végpontot kínál:

### /unique-files
- Leírás: Ezen a végponton keresztül a felhasználók megadhatnak egy könyvtár elérési útvonalat, és az API ellenőrzi a könyvtárban található fájlok egyediségét.
- HTTP metódus: GET
- Példa hívás: GET /unique-files?path=/path/to/directory
- Válasz: A válasz tartalmazza a keresett könyvtárban található egyedi fájlok listáját.

### /history
- Leírás: Ezen a végponton keresztül a felhasználók lekérhetik a korábbi kereséseik részleteit, beleértve a keresési időpontot, a felhasználó nevét és a keresett könyvtár elérési útvonalát.
- HTTP metódus: GET
- Példa hívás: GET /history?page=1&size=10
- Válasz: A válasz tartalmazza a korábbi keresések részleteit oldalazva.
