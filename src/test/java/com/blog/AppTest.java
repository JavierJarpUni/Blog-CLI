package com.blog;

import classes.BlogManager;
import classes.BlogPost;
import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Blog CLI Test Suite")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final BlogManager blogManager = BlogManager.getInstance();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @Order(6)
    @DisplayName("TC006: Create blog post via CLI - Verify successful creation")
    public void testCreatePostCLI() {
        String input = "Test Blog CLI\nTest Content CLI\nTester CLI\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        App.createPost(scanner);

        assertTrue(outContent.toString().contains("Blog post created successfully"));
        BlogPost post = findPostByTitle("Test Blog CLI");
        assertNotNull(post);
        assertEquals("Tester CLI", post.getAuthor());
    }

    @Test
    @Order(7)
    @DisplayName("TC007: List posts via CLI - Verify post listing")
    public void testListPostsCLI() {
        App.listPosts();
        String output = outContent.toString();
        assertTrue(output.contains("List of blog posts"));
        assertTrue(output.contains("ID:"));
        assertTrue(output.contains("Title:"));
    }

    @Test
    @Order(8)
    @DisplayName("TC008: View invalid post ID - Verify error message")
    public void testViewInvalidPost() {
        String input = "999\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        App.viewPost(scanner);
        assertTrue(outContent.toString().contains("Post not found"));
    }

    @Test
    @Order(9)
    @DisplayName("TC009: Remove post and verify deletion - Verify post removal")
    public void testRemovePostCLI() {
        BlogPost post = new BlogPost("To Remove", "Content", "Tester");
        blogManager.addPost(post);
        int id = post.getId();

        String input = id + "\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        App.removePost(scanner);
        assertTrue(outContent.toString().contains("Blog post removed successfully"));
        assertNull(blogManager.getPost(id));
    }

    private BlogPost findPostByTitle(String title) {
        return blogManager.listPosts().stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }
}
