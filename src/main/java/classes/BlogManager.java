package classes;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class BlogManager {
    private static BlogManager instance;
    private List<BlogPost> posts = new ArrayList<>();
    private final String filename = "src/blogPosts.json";

    private BlogManager() {
        loadPosts();
    }

    public static BlogManager getInstance() {
        if (instance == null) {
            instance = new BlogManager();
        }
        return instance;
    }

    public void addPost(BlogPost post) {
        posts.add(post);
        savePosts();
    }

    public BlogPost getPost(int id) {
        for (BlogPost post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public void updatePost(int id, String title, String content) {
        BlogPost post = getPost(id);
        if (post != null) {
            post.updatePost(title, content);
            savePosts();
        }
    }

    public void removePost(int id) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == id) {
                posts.remove(i);
                savePosts();
                break;
            }
        }
    }

    // Method for test cleanup
    public void clearPosts() {
        posts.clear();
        savePosts();
    }

    private void savePosts() {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filename))) {
            Gson gson = new Gson();
            // Convert the list of BlogPost objects to a list of dictionaries (maps) and
            // then to JSON
            List<Map<String, Object>> postMaps = new ArrayList<>();
            for (BlogPost post : posts) {
                postMaps.add(post.toMap());
            }
            gson.toJson(postMaps, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPosts() {
        try {
            if (Files.exists(Paths.get(filename))) {
                String json = new String(Files.readAllBytes(Paths.get(filename)));
                Gson gson = new Gson();
                // Deserialize the JSON into a list of maps, then convert each map back into a
                // BlogPost
                Type type = new TypeToken<List<Map<String, Object>>>() {
                }.getType();
                List<Map<String, Object>> postMaps = gson.fromJson(json, type);
                for (Map<String, Object> map : postMaps) {
                    posts.add(BlogPost.fromMap(map));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BlogPost> listPosts() {
        return posts;
    }
}