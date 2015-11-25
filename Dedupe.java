import java.util.Scanner;

public class Dedupe {

	public static void main(String [] args)  {

        //// 1) Accept input from the command line
        int postCount;
        double dupeRatio;

        if (args.length >= 2) {
            postCount = Integer.parseInt(args[0]);
            dupeRatio = Double.parseDouble(args[1]);
        } else {
            Scanner scanner = new Scanner(System.in);
            postCount = scanner.nextInt();
            dupeRatio = scanner.nextDouble();
            scanner.close();
        }

        // Override for testing
        postCount = 100;
        dupeRatio = 0.2;

        //// 2) Generate posts with duplicates
        Post[] posts = generatePostsWithDupes(postCount, dupeRatio);

        //// Implicit shuffle in generatePostsWithDupes


        //// Filter duplicates



	}


	/**
	 * Generates an array of {@code Post} objects containing {@code percentage}% Post objects that are
     * deep copies of other objects which appear in the array
     *
	 * @param N size of Post array
	 * @param percentage the percentage of entries that should be a duplicate of some other entry
	 * @return array containing duplicates.
	 */
	public static Post [] generatePostsWithDupes(int N, double percentage) {
		Post[] posts = new Post[N];

        // Create and copy random post objects
        int numberOfUniquePosts = (int) (N * (1 - percentage));
        System.arraycopy(generatePosts(numberOfUniquePosts), 0, posts, 0, numberOfUniquePosts);

        for (int i = numberOfUniquePosts; i < N; i++) {
            // duplicate posts
            try {
                posts[i] = (Post) posts[(int)(Math.random() * i)].clone();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        shuffle(posts); // for brevity

        return posts;
	}

	/**
	 * shuffles an array of posts
	 *
	 * @param arr
	 */
	public static void shuffle(Post [] arr) {
		// knuth shuffle
		for(int i=0;i<arr.length;i++) {
			Post tp = arr[i];
			int rnd = StdRandom.uniform(i,arr.length);
			arr[i]=arr[rnd];
			arr[rnd]=tp;
		}
	}


	/**
	 * generates a random set of Posts
	 * @param N size of set
	 * @return array of posts  (the set)
	 */
	public static Post [] generatePosts(int N) {

		if (N <= 0) throw new IllegalArgumentException("N must be >= 1");
		Post [] posts = new Post[N];
		for(int i=0;i<N;i++) {
			posts[i] = new Post();
		}

		return posts;
	}
}
