package fr.eni.encheresgr4.converter;

import fr.eni.encheresgr4.model.Categorie;
import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUtilisateur implements Converter<String, Utilisateur> {


    @Override
    public Utilisateur convert(String source) {

        return new Utilisateur(1,"Dede","André", "Bastos","afb.bastos@gmail.com", "0123456789", "rue des machins", "44000", "Nantes", "azerty", 500, true);

    }
}
