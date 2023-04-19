package cz.czechitas.lekce10.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Uloziste {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Cat cat;
    private Mouse mouse;


    public void nacistPlochuZeSouboru(Path file) throws IOException {
        UlozenaPlocha ulozenaPlocha = objectMapper.readValue(file.toFile(), UlozenaPlocha.class);
        cat = new Cat(ulozenaPlocha.getCat());
        mouse = new Mouse(ulozenaPlocha.getMouse());
        for (Point treePoint : ulozenaPlocha.getTrees()) {
            new Tree(treePoint);
        }
        new Cheese(ulozenaPlocha.getCheese());
        new Meat(ulozenaPlocha.getMeat());
    }

    public void nacistPlochuZeSouboru() throws IOException {
        nacistPlochuZeSouboru(Paths.get("level-01.json"));
    }

    public void nacistStavZeSouboru(Path path)  throws IOException {
        // TODO
        // Načíst objekt UlozenyStav pomocí objectMapper.readValue(file, UlozenyStav.class)
        UlozenyStav ulozenyStav = objectMapper.readValue(path.toFile(), UlozenyStav.class);

        // Získat z UlozenyStav souřadnice kočky a myši
        Point souradniceKocky = ulozenyStav.getCat();
        Point souradniceMysi = ulozenyStav.getMouse();

        // Zapsat tyto souřadnice do objektů kočky a myši pomocí setLocation()
        cat.setLocation(souradniceKocky);
        mouse.setLocation(souradniceMysi);
    }

    public void nacistStavZeSouboru() throws IOException {
        nacistStavZeSouboru(Paths.get("stav.json"));
    }

    public void ulozitStavDoSouboru(Path path)  throws IOException {
        // TODO - udelat jako prvni, protoze nemuzu nic nacist, nez to nactu
        // Vytvořit objekt UlozenyStav
        UlozenyStav ulozenyStav = new UlozenyStav();

        // Uložit do něj souřadnice kočky a myši – souřadnice získáte voláním getLocation()
        //Point souradniceKocky = cat.getLocation(); - take se tak muze udelat, je to prehlednejsi, nebo rovnou zapsat dolu do zavorek tu druhou cast
        //Point souradniceMysi = mouse.getLocation();

        ulozenyStav.setCat(cat.getLocation());
        ulozenyStav.setMouse(mouse.getLocation());
        // Uložit objekt UlozenyStav do souboru pomocí objectMapper.writeValue(file, object)
        objectMapper.writeValue(path.toFile(), ulozenyStav);
    }

    public void ulozitStavDoSouboru() throws IOException {
        ulozitStavDoSouboru(Paths.get("stav.json"));
    }

    public Cat getCat() {
        return cat;
    }

    public Mouse getMouse() {
        return mouse;
    }


}
