package fr.eni.enchere.controllers.converters;

import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;

import fr.eni.enchere.bll.ArticleService;
import fr.eni.enchere.bo.Categorie;

@Component
public class ChaineVersCategorieConverter implements Converter<String, Categorie> {
	
	private ArticleService as;

	public ChaineVersCategorieConverter(ArticleService as) {
		this.as = as;
	}

	@Override
	public Categorie convert(String idCategorie) {

		return this.as.consulterCategorieParId(Integer.parseInt(idCategorie));
	}

}

