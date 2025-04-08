package com.blog;

import classes.BlogManager;
import classes.BlogPost;
import classes.BlogPostFactory;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    private static Scanner scanner;
    private static BlogManager blogManager = BlogManager.getInstance();

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getUserChoice(scanner);
            switch (choice) {
                case 1:
                    createPost(scanner);
                    break;
                case 2:
                    listPosts();
                    break;
                case 3:
                    viewPost(scanner);
                    break;
                case 4:
                    updatePost(scanner);
                    break;
                case 5:
                    removePost(scanner);
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Blog App...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nWelcome to the Blog App CLI!");
        System.out.println("1. Create a new blog post");
        System.out.println("2. List all blog posts");
        System.out.println("3. View a blog post");
        System.out.println("4. Update a blog post");
        System.out.println("5. Remove a blog post");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }

    static void createPost(Scanner scanner) {
        System.out.println("\nCreate a new blog post:");

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter content: ");
        String content = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        BlogPost post = BlogPostFactory.createPost(title, content, author);
        blogManager.addPost(post);
        System.out.println("Blog post created successfully!");
    }

    public static void listPosts() {
        List<BlogPost> posts = blogManager.listPosts();
        if (posts.isEmpty()) {
            System.out.println("No blog posts available.");
        } else {
            System.out.println("\nList of blog posts:");
            for (BlogPost post : posts) {
                System.out.println("ID: " + post.getId() + " | Title: " + post.getTitle() +
                        " | Author: " + post.getAuthor() + " | Created at: " + post.getCreatedAt());
            }
        }
    }

    private static void updatePost(Scanner scanner) {
        System.out.print("Enter the ID of the post you want to update: ");
        int postId = Integer.parseInt(scanner.nextLine());

        BlogPost post = blogManager.getPost(postId);
        if (post != null) {
            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();

            System.out.print("Enter new content: ");
            String newContent = scanner.nextLine();

            blogManager.updatePost(postId, newTitle, newContent);
            System.out.println("Blog post updated successfully!");
        } else {
            System.out.println("Post not found.");
        }
    }

    public static void viewPost(Scanner scanner) {
        System.out.print("Enter the ID of the post you want to view: ");
        int postId = Integer.parseInt(scanner.nextLine());

        BlogPost post = blogManager.getPost(postId);
        if (post != null) {
            System.out.println("\n--- Blog Post Details ---");
            System.out.println("ID: " + post.getId());
            System.out.println("Title: " + post.getTitle());
            System.out.println("Content: " + post.getContent());
            System.out.println("Author: " + post.getAuthor());
            System.out.println("Created at: " + post.getCreatedAt());
            System.out.println("Updated at: " + post.getUpdatedAt());
            System.out.println("--------------------------");
        } else {
            System.out.println("Post not found.");
        }
    }

    public static void removePost(Scanner scanner) {
        System.out.print("Enter the ID of the post you want to remove: ");
        int postId = Integer.parseInt(scanner.nextLine());

        blogManager.removePost(postId);
        System.out.println("Blog post removed successfully.");
    }
}
