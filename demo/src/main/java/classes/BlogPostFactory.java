package classes;

public class BlogPostFactory {
    public static BlogPost createPost(String title, String content, String author) {
        return new BlogPost(title, content, author);
    }
}
