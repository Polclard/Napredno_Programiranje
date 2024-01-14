package VtorKolokviumVezbi.Zadaca37;
import java.util.*;
import java.util.stream.Collectors;

class Comment {
    String username;
    String commentId;
    String content;
    String replyToId;
    int likes;
    List<Comment> replies;

    public Comment(String username, String commentId, String content, String replyToId) {
        this.username = username;
        this.commentId = commentId;
        this.content = content;
        this.replyToId = replyToId;
        this.likes = 0;
        this.replies = new ArrayList<>();
    }

    public void addLike() {
        likes++;
    }

    // Recursive method for string representation of comments and their replies
    public String toString(String indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent).append("Comment: ").append(content).append("\n");
        sb.append(indent).append("Written by: ").append(username).append("\n");
        sb.append(indent).append("Likes: ").append(likes).append("\n");

        for (Comment reply : replies) {
            sb.append(reply.toString(indent + "\t"));
        }
        return sb.toString();
    }
}

class Post {
    String username;
    String postContent;
    Map<String, Comment> commentMap;

    public Post(String username, String postContent) {
        this.username = username;
        this.postContent = postContent;
        this.commentMap = new HashMap<>();
    }

    public void addComment(String username, String commentId, String content, String replyToId) {
        Comment comment = new Comment(username, commentId, content, replyToId);
        if (replyToId == null) {
            commentMap.put(commentId, comment);
        } else {
            Comment parentComment = commentMap.get(replyToId);
            if (parentComment != null) {
                parentComment.replies.add(comment);
            }
            commentMap.put(commentId, comment);
        }
    }

    public void likeComment(String commentId) {
        Comment comment = commentMap.get(commentId);
        if (comment != null) {
            comment.addLike();
        }
    }

    // Method to get comments in a structured string format
    private String getCommentsString() {
        StringBuilder sb = new StringBuilder();
        List<Comment> topLevelComments = commentMap.values().stream()
                .filter(c -> c.replyToId == null)
                .sorted((c1, c2) -> Integer.compare(c2.likes, c1.likes))
                .collect(Collectors.toList());

        for (Comment comment : topLevelComments) {
            sb.append(comment.toString("\t"));
        }
        return sb.toString();
    }

    public String toString() {
        return "Post: " + postContent + "\nWritten by: " + username + "\nComments:\n" + getCommentsString();
    }
}

public class PostTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String postAuthor = sc.nextLine();
        String postContent = sc.nextLine();

        Post p = new Post(postAuthor, postContent);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String testCase = parts[0];

            if (testCase.equals("addComment")) {
                String author = parts[1];
                String id = parts[2];
                String content = parts[3];
                String replyToId = null;
                if (parts.length == 5) {
                    replyToId = parts[4];
                }
                p.addComment(author, id, content, replyToId);
            } else if (testCase.equals("likes")) {
                for (int i = 1; i < parts.length; i++) {
                    p.likeComment(parts[i]);
                }
            } else if (testCase.equals("print")) {
                System.out.println(p);
            }
        }

        sc.close();
    }
}
