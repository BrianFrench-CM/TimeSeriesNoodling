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
import com.cloudera.sparkts
object DPAnalyzer {
  def DummyFunction():Unit = {
  println("this is a dummy function")
  }
  def ScapeDerivedPorts():DataFrame = {
    val RawData = spark.read.parquet("s3://provider-portdata-prodwest/seaweb-clean/*/*/*/*/tblPortBerth/*").withColumn("inputFile", input_file_name())
    val RawWithDate = RawData.withColumn("date", regexp_extract(RawData.col("inputFile"),"(?<=/day=)(.*?)(?=/gen)",1).cast("date"))
    var GroupedOnDate = RawWithDate.groupBy("date").agg(count("Berth_ID").alias("berthcount")).withColumn("keys",lit("A"))
    GroupedOnDate =
    val zone = ZoneId.systemDefault()
    val dtIndex = DateTimeIndex.uniformFromInterval(
      ZonedDateTime.of(LocalDateTime.parse("2014-05-20T00:00:00"), zone),
      ZonedDateTime.of(LocalDateTime.parse("2020-07-06T00:00:00"), zone),
      new BusinessDayFrequency(1))
    val BerthCountTSRDD = TimeSeriesRDD.timeSeriesRDDFromObservations(dtIndex, GroupedOnDate, "date","keys", "berthcount")
    val filled = BerthCountTSRDD.fill("linear")
  }
}
