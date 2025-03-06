/**
  * Open a Spark shell and include the JAR file
  * spark-shell --master "local[2]" --jars ./v5.jar
  */

import org.apache.spark.SparkContext
import org.apache.hadoop.io.IntWritable
import eCP.Java.SiftDescriptorContainer

/** 
 * Loads a sequence file containing SIFT descriptors into an RDD.
 * @param sc SparkContext used to read the file.
 * @param fileName Path to the sequence file.
 * @return An RDD of SiftDescriptorContainer objects.
 */
def loadSIFTs(sc: SparkContext, fileName: String): RDD[SiftDescriptorContainer] = {
  sc.sequenceFile(fileName, classOf[IntWritable], classOf[SiftDescriptorContainer])
    .map(it => {
      val desc: SiftDescriptorContainer = new SiftDescriptorContainer()
      desc.id = it._2.id
      it._2.vector.copyToArray(desc.vector) // Deep copy to avoid memory issues
      desc
    })
}

// Load SIFT descriptors into an RDD and convert it to a JSON file
val testRDD = loadSIFTs(sc, "./bigann_query.seq")
val df = testRDD.map(desc => (desc.id, desc.vector.toSeq)).toDF("id", "vector")
val jsonOutputPath = "./output/10Ksift_data.json"
df.write.json(jsonOutputPath)