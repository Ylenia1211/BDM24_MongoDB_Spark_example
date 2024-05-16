import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class LogAnalysis {
    public static void main(String[] args) {
        // Create a SparkConf object to configure the Spark Application
        SparkConf conf = new SparkConf().setAppName("LogAnalysis").setMaster("local");

        // Create a JavaSparkContext object to initialize Spark
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Load log file into an RDD
        JavaRDD<String> logFile = sc.textFile("src/main/resources/sample_log.txt");

        // Filter lines containing "ERROR"
        JavaRDD<String> errorLines = logFile.filter(line -> line.contains("ERROR"));

        // Count the number of error lines
        long errorCount = errorLines.count();

        // Print the number of error lines
        System.out.println("Number of error lines: " + errorCount);

        // Stop Spark Context to release resources
        sc.stop();
    }
}