import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


import static org.apache.spark.sql.functions.*;

public class DiseaseScoreGrouping {
    public static void main(String[] args) {
        // Create a SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("DiseaseScoreGrouping")
                .master("local[*]") // Use all available cores
                .getOrCreate();

        // Read the CSV file into a DataFrame
        Dataset<Row> diseaseDF = spark.read()
                .option("header", "true") // First line in file contains headers
                .csv("src/main/resources/diseases.csv")
                .toDF("id", "disease", "score");; // Path to your CSV file


        // Register the DataFrame as a temporary SQL view
        diseaseDF.createOrReplaceTempView("diseaseScores");

        // Run SQL query to group diseases by score and concat the name of diseases
        Dataset<Row> groupedDiseases = spark.sql(
                "SELECT score, CONCAT_WS(', ', COLLECT_LIST(disease)) AS diseases " +
                        "FROM diseaseScores " +
                        "GROUP BY score " +
                        "ORDER BY score;"
        );


        // Group diseases by score and count occurrences
        //Dataset<Row> groupedDiseases = df.groupBy("score", "disease").count();

        // Show the resulting table
        groupedDiseases.show();

        // 2 method : Group by score and aggregate diseases
        Dataset<Row> resultDF = diseaseDF.groupBy("score")
                .agg(concat_ws(", ", collect_list(col("disease"))).alias("diseases"))
                .sort("score");

        // Show the result
        resultDF.show(false);

        // Stop SparkSession to release resources
        spark.stop();
    }
}