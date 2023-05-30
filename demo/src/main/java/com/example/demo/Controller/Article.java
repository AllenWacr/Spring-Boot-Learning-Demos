package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Dao.ArticleDaoImplement;
import com.example.demo.Mapper.ArticleMapper;
import com.example.demo.Model.T_Article;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/article")
public class Article {

    @Autowired
    private ArticleDaoImplement articleDaoImplement;

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/test")
    @ResponseBody
    public T_Article articleTest() {
        return articleMapper.selectArticle(4);
    }
    
    @GetMapping("/list")
    public String articleList(ModelMap map) {
        List<Map<String, Object>> articleList = articleDaoImplement.listArticle();
        map.addAttribute("articleList", articleList);
        map.addAttribute("flag", "list");
        return "article";
    }

    @GetMapping("/add")
    public String addArticle(ModelMap map) {
        map.addAttribute("flag", "add");
        return "article";
    }

    @PostMapping("/add")
    public String addArticleDone(ModelMap map, String author, String content, String type) {
        T_Article t_Article = T_Article.builder().author(author).content(content).type(type).build();
        int success = articleDaoImplement.addArticle(t_Article);
        map.addAttribute("success", success);
        map.addAttribute("flag", "add");
        return "article";
    }

    @GetMapping("/update/{id}")
    public String updateArticle(ModelMap map, Integer id) {
        T_Article t_Article = articleDaoImplement.findArticleById(id);
        map.addAttribute("article", t_Article);
        map.addAttribute("flag", "update");
        return "article";
    }

    @PostMapping("/update/{id}")
    public String updateArticleDone(ModelMap map, Integer id, String content) {
        T_Article t_Article = T_Article.builder().id(id).content(content).build();
        int success = articleDaoImplement.addArticle(t_Article);
        map.addAttribute("success", success);
        map.addAttribute("flag", "update");
        return "article";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(ModelMap map, Integer id) {
        T_Article t_Article = articleDaoImplement.findArticleById(id);
        map.addAttribute("article", t_Article);
        map.addAttribute("flag", "delete");
        return "article";
    }

    @PostMapping("/delete/{id}")
    public String deleteArticleDone(ModelMap map, Integer id) {
        int success = articleDaoImplement.deleteArticle(T_Article.builder().id(id).build());
        map.addAttribute("success", success);
        map.addAttribute("flag", "update");
        return "article";
    }
}
