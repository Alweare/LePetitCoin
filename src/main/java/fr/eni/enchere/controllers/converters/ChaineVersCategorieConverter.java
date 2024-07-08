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
		System.err.println("laaaaaaaaaaaaaaaaaaaaaaaaaaaaallllllllllllllllllllllllaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(this.as.consulterCategorieParId(1));
		return this.as.consulterCategorieParId(1);
//		System.out.println(Integer.parseInt(idCategorie));
//		
//		int id = Integer.parseInt(idCategorie);
//		return this.as.consulterCategorieParId(id);
	}

}

