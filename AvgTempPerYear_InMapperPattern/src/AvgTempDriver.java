import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AvgTempDriver {

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "Average Tempreture");
		job.setJarByClass(AvgTempDriver.class);

		FileInputFormat.addInputPath(job, new Path("input"));
		FileOutputFormat.setOutputPath(job, new Path("output"));

		job.setMapperClass(AvgTempMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Pair.class);
		
		
		job.setReducerClass(AvgTempReducer.class);

        job.setOutputKeyClass(Text.class);
 		job.setOutputValueClass(DoubleWritable.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}
