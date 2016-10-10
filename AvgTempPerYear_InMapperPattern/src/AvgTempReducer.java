import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgTempReducer extends
		Reducer<Text, Pair, Text, DoubleWritable> {
	private DoubleWritable result = new DoubleWritable();

	@Override
	public void reduce(Text key, Iterable<Pair> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		int count = 0;
		for (Pair val : values) {
			sum += val.getSum();
			count += val.getCount();
		}
		result.set((count!=0?((double)sum / count):count));
		context.write(key, result);
	}
}
