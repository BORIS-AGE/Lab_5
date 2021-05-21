package shrek.industries.Lab_5.repository;

import org.json.JSONObject;
import shrek.industries.Lab_5.repository.model.Post;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PostsRepository {
    private static final PostsRepository REPOSITORY_REF = new PostsRepository();

    private PostsRepository() {
    }

    public static PostsRepository getInstance() {
        return REPOSITORY_REF;
    }

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void savePost(Post post) {
//        lock.writeLock().lock();
//        JSONObject json = new JSONObject();
//        json.put("title", post.value);
//        json.put("id", post.id);
//        Runnable saveRunnable = Runnable {
//            write(json);
//            lock.writeLock().unlock();
//        };
//        val thread = Thread(saveRunnable);
//        thread.start();
        JSONObject json = new JSONObject();
        json.put("title", post.value);
        json.put("id", post.id);
        write(json);
    }

    private void write(JSONObject json) {
        lock.writeLock().lock();
        String prevValue = readTotalFile();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("lab_5"));
            writer.write(prevValue + json.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock.writeLock().unlock();
    }

    public List<Post> getPosts() {
        lock.readLock().lock();
        LinkedList<Post> list = new LinkedList<Post>();
        try {
            File myObj = new File("lab_5");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                JSONObject json = new JSONObject(data);
                System.out.println(json.toString());
                list.add(new Post(json.get("id").toString(), json.get("title").toString()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        lock.readLock().unlock();
        return list;
    }

    private String readTotalFile() {
        lock.readLock().lock();
        StringBuilder res = new StringBuilder();
        try {
            File myObj = new File("lab_5");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                res.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(res.toString());
        lock.readLock().unlock();
        return res.toString();
    }
}
