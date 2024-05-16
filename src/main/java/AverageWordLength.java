import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

import static java.lang.Math.ceil;

public class AverageWordLength {

    public static void main(String[] args) {
        // Create a SparkConf object to configure the Spark Application
        SparkConf conf = new SparkConf().setAppName("AverageWordLength").setMaster("local");

        // Create a JavaSparkContext object to initialize Spark
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Load text file into an RDD
        JavaRDD<String> textFile = sc.textFile("src/main/resources/little_story.txt");

        // Split each line into words
        JavaRDD<String> words = textFile.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        // Calculate the length of each word
        JavaRDD<Integer> wordLengths = words.map(word -> word.length());

        // Calculate the total length of all words
        int totalLength = wordLengths.reduce((a, b) -> a + b);

        // Count the total number of words
        long wordCount = words.count();

        // Calculate the average word length
        double averageLength = ceil((double) totalLength / wordCount);

        // Print the average word length
        System.out.println("Average Word Length: " + averageLength);

        // Stop Spark Context to release resources
        sc.stop();
    }
}