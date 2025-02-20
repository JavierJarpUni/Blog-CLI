package classes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class BlogPost {
    private static int counter = 1;
    private int id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public BlogPost(String title, String content, String author) {
        this.id = counter++;
        this.title = title;
        this.content = content;
        this.author = author;
        this.created_at = LocalDateTime.now();
        this.updated_at = this.created_at;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public LocalDateTime getCreatedAt() { return created_at; }
    public LocalDateTime getUpdatedAt() { return updated_at; }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
        this.updated_at = LocalDateTime.now();
    }

    // Convert BlogPost to a dictionary (Map)
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("title", this.title);
        map.put("content", this.content);
        map.put("author", this.author);
        map.put("created_at", this.created_at.toString());
        map.put("updated_at", this.updated_at.toString());
        return map;
    }

    // Static method to create a BlogPost from a dictionary (Map)
    public static BlogPost fromMap(Map<String, Object> map) {
        String title = (String) map.get("title");
        String content = (String) map.get("content");
        String author = (String) map.get("author");
        BlogPost post = new BlogPost(title, content, author);
        post.id = (int) map.get("id");
        post.created_at = LocalDateTime.parse((String) map.get("created_at"));
        post.updated_at = LocalDateTime.parse((String) map.get("updated_at"));
        return post;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}