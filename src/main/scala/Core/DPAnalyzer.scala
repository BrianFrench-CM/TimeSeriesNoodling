package Core
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.SQLContext._
import scala.collection.JavaConversions._
import org.apache.spark.sql.functions._
import org.apache.spark.mllib.feature._
import org.apasche.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.feature.MinHashLSH
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.NGram
import org.apache.spark.ml.feature.CountVectorizer
import java.time._
import java.sql.Timestamp
import org.apache.spark.sql.expressions.Window
object DPAnalyzer {
  def DummyFunction():Unit = {
  println("this is a dummy function")
  }
  def ScapeDerivedPorts():DataFrame = {
    val RawData = spark.read.parquet("s3://provider-portdata-prodwest/seaweb-clean/*/*/*/*/tblPortBerth/*").withColumn("inputFile", input_file_name())
    val RawWithDate = RawData.withColumn("date", regexp_extract(RawData.col("inputFile"),"(?<=/day=)(.*?)(?=/gen)",1).cast("date"))
    val ValidByDay = RawWithDate.filter("Dry_Bulk is not null").groupBy("date").agg(count("Dry_Bulk"))

  }
}
