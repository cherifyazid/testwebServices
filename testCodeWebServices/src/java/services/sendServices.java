package services;

import com.google.gson.Gson;
import data.data;
import entity.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author cherif.yazid
 */
@Path("articleservice")
@RequestScoped
@ManagedBean
public class sendServices {

    @ManagedProperty(value = "#{loadFromData}")
    private data myData;

    //------------------------ PUT /articleservice/article/$article_id ----------------------------------------------------------------
    @PUT
    @Path("/article/{article_id}")
    @Consumes("application/json")
    public String createArticle(@Context HttpServletRequest requestContext, String valentryJson, @PathParam("article_id") long article_id) {
        JSONObject jsonObject = null;
        try {
            if (valentryJson != null) {

                jsonObject = new JSONObject(valentryJson); // we have used lib jettison.jar to convert elements to json format

                double weight = jsonObject.getDouble("weight");
                String type = jsonObject.getString("type");
                long parent_id = jsonObject.getLong("parent_id");

                Article ar = new Article();
                ar.setArticle_id(article_id);
                ar.setType(type);
                ar.setWeight(weight);
                ar.setParent_id(parent_id);
                myData.addArticle(article_id, weight, type, parent_id);

            }
        } catch (JSONException ex) {
            Logger.getLogger(sendServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }

    
    
    //---------------------------GET /articleservice/article/$article_id
    @GET
    @Path("/article/find/{article_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getArticlesById(@PathParam("article_id") int article_id) throws JSONException {
        ArrayList<Article> listArticle = new ArrayList<Article>();
        listArticle = myData.loadArticles();

        JSONObject jo = new JSONObject();
        for (Article article : listArticle) {

            if ((article.getArticle_id()).equals(article)) {

                jo.put("type", article.getType());
                jo.put("Weight", article.getWeight());
                jo.put("Parent_id", article.getParent_id());

            }
        }
        return jo.toString();
    }
    
    

    ///------------   GET /articleservice/types/$type
    @GET
    @Path("/types/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getArticlesByType(@PathParam("type") int type) throws JSONException {
        ArrayList<Article> listArticle = new ArrayList<Article>();
        listArticle = myData.loadArticles();
        List<Long> l = new ArrayList<>();
        Gson g = new Gson(); /// we have user Gson-1.7.jar lib to convert list to json String
        for (Article article : listArticle) {

            if ((article.getType()).equals(type)) {
                l.add(article.getArticle_id());
            }

        }
        return g.toJson(l);
    }

    
    
    ////----------------------------GET /articleservice/types/$type
    @GET
    @Path("/sum/{article_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSumArticlesLinkedByIdParent(@PathParam("article_id") int article_id) throws JSONException {
        ArrayList<Article> listArticle = new ArrayList<Article>();
        listArticle = myData.loadArticles();

        double sum = 0;
        JSONObject jsonObject = null;
        for (Article article : listArticle) {

            if ((article.getParent_id()) == article_id) {

                jsonObject = new JSONObject();
                sum = sum + article.getWeight();

            }

        }
        jsonObject.put("sum", sum);
        return jsonObject.toString();
    }

    
}
