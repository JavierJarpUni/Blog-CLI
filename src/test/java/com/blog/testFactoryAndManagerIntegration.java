package com.blog;

import classes.BlogManager;
import classes.BlogPost;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Blog Integration Test Suite")
public class testFactoryAndManagerIntegration {

    private BlogManager blogManager;

    @BeforeEach
    void setup() {
        blogManager = BlogManager.getInstance();
        blogManager.clearPosts();
    }

    @Test
    @Order(5)
    @DisplayName("TC005: Factory and Manager Integration - Create and retrieve post using factory")
    public void testFactoryAndManagerIntegration() {
        BlogPost post = classes.BlogPostFactory.createPost("Integration", "Testing integration", "Dev");
        blogManager.addPost(post);
        BlogPost retrieved = blogManager.getPost(post.getId());
        assertNotNull(retrieved);
        assertEquals("Integration", retrieved.getTitle());
    }
}
