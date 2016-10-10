import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgTempMapper extends
		Mapper<LongWritable, Text, Text, Pair> {

	Map<String,Pair> map;
	private final static IntWritable one = new IntWritable();
	private Text word = new Text();
	
	public void setup(Context context) throws IOException, InterruptedException{
		map = new HashMap<String,Pair>();
	}

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String record = value.toString();
		
		int num = 0;
		String date = "";
		String temp = "";
		
		if (record.length() > 0) {
			date = record.substring(15, 19).trim();
			temp = record.substring(record.length() - 18, record.length() - 13)
					.trim();

			System.out.println(date);
			System.out.println(temp);

			if (temp.charAt(0) == '-') {
				num = -Integer.parseInt(temp.substring(1));
			} else {
				num = Integer.parseInt(temp.substring(1));
			}

			word.set(date);
			Pair tmpCntPair = (Pair) this.map.get(date);
			if(tmpCntPair != null)
			{
				tmpCntPair.addSum(num);
				tmpCntPair.incrementCount();
			}else
			{				
				tmpCntPair = new Pair(num);
				map.put(date, tmpCntPair);
			}
		}
	}
	
public void cleanup(Context context) throws IOException, InterruptedException{
	for(String key : map.keySet())
	{
		Pair pair = map.get(key);
		context.write(new Text(key), pair);
		System.out.println("Mapper key " + key.toString());
	}
	}
}
