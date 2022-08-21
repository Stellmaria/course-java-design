package com.rakovets.course.design.practice.solid.source.impl;

import com.rakovets.course.design.practice.solid.model.ingredient.Sauce;
import com.rakovets.course.design.practice.solid.source.IngredientsStorage;
import com.rakovets.course.design.practice.solid.source.util.IngredientsDataParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SauceStorageImpl implements IngredientsStorage {
    private static final Logger LOGGER = Logger.getLogger("CheeseStorage");
    private static final Path PATH = Path.of("src", "main", "java", "com", "rakovets", "course", "design",
            "practice", "solid", "source", "storage", "sauce_storage.txt");

    private final Collection<Sauce> sauces;

    public SauceStorageImpl() {
        sauces = new ArrayList<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Collection<T> showAllStorage() {
        IngredientsDataParser parser = IngredientsDataParser.getINSTANCE();
        Sauce sauce;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sauce = new Sauce(parser.getName(line),
                        parser.getPrice(line),
                        parser.getWeight(line));
                sauces.add(sauce);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return (Collection<T>) sauces;
    }
}
