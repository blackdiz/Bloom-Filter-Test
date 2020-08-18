import com.blackdiz.BloomFilter;
import org.junit.Assert;
import org.junit.Test;

public class BloomFilterTest {

    @Test
    public void testBloomFilter() {
        String[] wordPresent = {"abound","abounds","abundance","abundant","accessable",
                "bloom","blossom","bolster","bonny","bonus","bonuses",
                "coherent","cohesive","colorful","comely","comfort",
                "gems","generosity","generous","generously","genial"};
        String[] wordAbsent = {"bluff","cheater","hate","war","humanity",
                "racism","hurt","nuke","gloomy","facebook",
                "geeksforgeeks","twitter"};

        BloomFilter bloomFilter = new BloomFilter(128);
        for (String word : wordPresent) {
            bloomFilter.add(word);
        }

        System.out.println("Start testing present words.");
        for (String word : wordPresent) {
            System.out.println("Test: " + word);
            Assert.assertTrue(bloomFilter.check(word));
        }

        System.out.println("Start testing absent words.");
        for (String word : wordAbsent) {
            System.out.println("Test: " + word);
            if (bloomFilter.check(word)) {
                System.out.println(word + " is false positive.");
            } else {
                System.out.println(word + " is definitely not present.");
            }
        }
    }
}
