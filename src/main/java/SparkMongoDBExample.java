import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;
import com.mongodb.spark.MongoSpark;

public class SparkMongoDBExample {
    public static void main(String[] args) {
            // Crea una sessione Spark
            SparkSession spark = SparkSession.builder()
            .appName("MongoDBSparkConnectorExample")
            .master("local[*]")  // Imposta il master URL
            .config("spark.mongodb.input.uri","mongodb://root:example@localhost:27017/db_spark.collection1?authSource=admin")
                    //change db_spark with the name of your db, and change "collection1" with name of your collection!
            .config("spark.mongodb.output.uri", "mongodb://root:example@localhost:27017/db_spark.collection1?authSource=admin")
            .config("spark.jars.packages", "org.mongodb.spark:mongo-spark-connector_2.12:10.0.0")
            .getOrCreate();

            // Create a spark context
            JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

            //Read data from collection mongo
            JavaRDD<Document> rdd = MongoSpark.load(jsc);

            // Show first 10 documents
            rdd.take(10).forEach(System.out::println);

            // Perform an example operation
            long count = rdd.count();
            System.out.println("Number of documents in the collection: " + count);

            //Close Spark Session and Context
            jsc.close();
            spark.stop();
        }
}
