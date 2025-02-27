package fr.eni.encheresgr4.converter;

import fr.eni.encheresgr4.model.Categorie;
import fr.eni.encheresgr4.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategorie implements Converter<String, Categorie> {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Categorie convert(String source) {

        return categorieRepository.findOneById(Integer.parseInt(source));

    }
}
