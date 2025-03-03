package fr.eni.encheresgr4.converter;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.service.ArticleVenduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToArticleVentu implements Converter<String, ArticleVendu> {

    @Autowired
    private ArticleVenduService articleVenduService;

    @Override
    public ArticleVendu convert(String source) {

        return articleVenduService.findOneById(Integer.parseInt(source));

    }
}
