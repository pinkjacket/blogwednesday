package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PostTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Post.clearAllPosts(); //I clear out allll the posts before each test.
    }


    @Test
    public void NewPostObjectGetsCorrectlyCreated_true() throws Exception {
        Post post = setupNewPost();
        assertEquals(true, post instanceof Post);
    }

    @Test
    public void PostInstantiatesWithContent_true() throws Exception {
        Post post = setupNewPost();
        assertEquals("Day 1: Intro", post.getContent());
    }

    @Test
    public void AllPostsAreCorrectlyReturned_true() throws Exception {
        Post post = setupNewPost();
        Post otherPost = new Post ("How to pair successfully");
        assertEquals(2, Post.getAll().size());
    }

    @Test
    public void AllPostsContainsAllPosts_true() throws Exception {
        Post post = setupNewPost();
        Post otherPost = new Post("How to pair successfully");
        assertTrue(Post.getAll().contains(post));
        assertTrue(Post.getAll().contains(otherPost));
    }

    @Test
    public void isPublishedPropertyIsFalseAfterInstantiation() throws Exception {
        Post post = setupNewPost();
        assertEquals(false, post.getPublished()); //should never start as published
    }

    @Test
    public void getCreatedAtInstantiatesWithCurrentTimeToday() throws Exception {
    Post post = setupNewPost();
        assertEquals(LocalDateTime.now().getDayOfWeek(), post.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void postsInstantiateWithId() throws Exception {
        Post post = setupNewPost();
        assertEquals(1, post.getId());
    }

    @Test
    public void findReturnsCorrectPost() throws Exception {
        Post post = setupNewPost();
        assertEquals(1, Post.findById(post.getId()).getId());
    }


    @Test
    public void findReturnsCorrectPostWhenMoreThanOnePostExists() throws Exception {
        Post post = setupNewPost();
        Post otherPost = new Post("How to pair successfully");
        assertEquals(2, Post.findById(otherPost.getId()).getId());
    }

    @Test
    public void updateChangesPostContent() throws Exception {
        Post post = setupNewPost();
        String formerContent = post.getContent();
        LocalDateTime formerDate = post.getCreatedAt();
        int formerId = post.getId();


        post.update("Android: Day 40");

        assertEquals(formerId, post.getId());
        assertEquals(formerDate, post.getCreatedAt());
        assertNotEquals(formerContent, post.getContent());
    }

    @Test
    public void deleteDeletesASpecificPost() throws Exception {
        Post post = setupNewPost();
        Post otherPost = new Post("How to pair successfully");
        post.deletePost();
        assertEquals(1, Post.getAll().size()); //one is left
        assertEquals(Post.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2
    }

    @Test
    public void deleteAllPostsDeletesAllPosts() throws Exception {
        Post post = setupNewPost();
        Post otherPost = setupNewPost();
        Post.clearAllPosts();
        assertEquals(0, Post.getAll().size());
    }


    //helper methods
    public Post setupNewPost(){
        return new Post ("Day 1: Intro");
    }
}