import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WordCount {
    public static void main(String[] args) {
        // Create a SparkConf object to configure your application
        SparkConf conf = new SparkConf()
                .setAppName("App_WordCount")
                .setMaster("local[*]");
        //JavaSparkContext sc = new JavaSparkContext(conf);

        // Create a JavaSparkContext to interact with Spark
           JavaSparkContext sc = new JavaSparkContext(conf);
            // Load the text file into an RDD
            JavaRDD<String> lines = sc.textFile("src/main/resources/little_story.txt");
                    //.filter(x -> !Objects.equals(x, ":")); //path_to_your_text_file.txt
            // Split each line into words and convert to lowercase
            JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.toLowerCase()
                                                                    .split(" "))
                                                                    .iterator());

            // Map each word to a key-value pair (word, 1)
            JavaPairRDD<String, Integer> wordCounts = words.mapToPair(word -> new Tuple2<>(word, 1));

            // Reduce by key to get word counts
            JavaPairRDD<String, Integer> wordCountsReduced = wordCounts.reduceByKey((a, b) -> a + b);
            System.out.println(wordCountsReduced.collect());

            // Collect results
            //List<Tuple2<String, Integer>> results = wordCountsReduced.collect();

            // Print word counts
            //for (Tuple2<String, Integer> tuple : results) {
            //    System.out.println(tuple._1() + " -> " + tuple._2());
            //}

            // Stop Spark Context to release resources
            sc.stop();
        }
    }
