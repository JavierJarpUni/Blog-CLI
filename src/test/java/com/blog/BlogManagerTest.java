package com.blog;

import classes.BlogManager;
import classes.BlogPost;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Blog Manager Test Suite")
public class BlogManagerTest {

    private static BlogManager blogManager;

    @BeforeAll
    public static void setup() {
        blogManager = BlogManager.getInstance();
    }

    @BeforeEach
    void clearData() {
        blogManager.clearPosts();
    }

    @Test
    @Order(1)
    @DisplayName("TC001: Add new blog post - Verify post creation and retrieval")
    public void testAddPost() {
        BlogPost post = new BlogPost("Test Title", "Test Content", "Tester");
        blogManager.addPost(post);
        BlogPost retrieved = blogManager.getPost(post.getId());
        assertNotNull(retrieved);
        assertEquals("Test Title", retrieved.getTitle());
    }

    @Test
    @Order(2)
    @DisplayName("TC002: Update blog post - Verify title and content update")
    public void testUpdatePost() {
        // Create a post first
        BlogPost post = new BlogPost("Initial Title", "Initial Content", "Tester");
        blogManager.addPost(post);

        // Now update it
        blogManager.updatePost(post.getId(), "Updated Title", "Updated Content");
        BlogPost updated = blogManager.getPost(post.getId());
        assertEquals("Updated Title", updated.getTitle());
    }

    @Test
    @Order(3)
    @DisplayName("TC003: Remove blog post - Verify post deletion")
    public void testRemovePost() {
        BlogPost post = new BlogPost("Temp", "To be deleted", "Bot");
        blogManager.addPost(post);
        int id = post.getId();
        blogManager.removePost(id);
        assertNull(blogManager.getPost(id));
    }

    @Test
    @Order(4)
    @DisplayName("TC004: List blog posts - Verify non-empty post list")
    public void testListPosts() {
        // Add a test post first
        BlogPost post = new BlogPost("List Test", "Test Content", "Tester");
        blogManager.addPost(post);

        List<BlogPost> posts = blogManager.listPosts();
        assertTrue(posts.size() > 0);
        assertEquals("List Test", posts.get(0).getTitle());
    }
}
