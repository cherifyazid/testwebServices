package entity;

/**
 *
 * @author cherif.yazid
 * entity to use article as object
 */
public class Article {

    Long article_id;
    Double weight;
    String type;
    Long parent_id;

    public Article(Long article_id, Double weight, String type, Long parent_id) {
        this.article_id = article_id;
        this.weight = weight;
        this.type = type;
        this.parent_id = parent_id;
    }

    public Article() {

    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

}
