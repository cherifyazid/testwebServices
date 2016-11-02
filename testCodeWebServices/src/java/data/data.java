
package data;

import entity.Article;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author cherif.yazid
 * this classe load data from the first run application 
 */

@ManagedBean
@ApplicationScoped
public class data {


   static ArrayList<Article> listArticle = new ArrayList<>();

    public ArrayList<Article> loadArticles() {

        ArrayList<Article> listArticleStart = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ArrayList<Article> listArticle = new ArrayList<>();
            long d = (long) (Math.random() * 100);
            long w = d * 1000;

            listArticleStart = addArticle(i, w, "Spring coding V" + i, d);
        }
        return listArticleStart;
    }

    public ArrayList<Article> addArticle(long article_id, double weight, String type, long parent_id) {
        Article article = new Article();

        article.setArticle_id(article_id);
        article.setWeight(weight);
        article.setType(type);
        article.setParent_id(parent_id);

        listArticle.add(article);
        System.out.println("listArticle = " + listArticle.size());
        return listArticle;

    }

    public data() {
               ArrayList<Article> listArticleload = new ArrayList<>();
       // listArticleload = loadArticles();
    }

    @PostConstruct
    public void loadFromData() {
        ArrayList<Article> listArticleload = new ArrayList<>();
        listArticleload = loadArticles();
    }

    public static ArrayList<Article> getListArticle() {
        return listArticle;
    }

}
