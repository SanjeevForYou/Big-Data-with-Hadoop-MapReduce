import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;




public class Pair implements Writable  {

	public int sum;
	public int count;
	
	public Pair(){}
	
	public Pair(int sum)
	{
		this.sum = sum;
		this.count = 1;
	}
	
	public void addSum(int val)
	{
		this.sum += val;
	}
	
	public void incrementCount()
	{
		++this.count;
	}
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		
		sum = in.readInt();
		count = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(sum);
		out.writeInt(count);
	}
	
	  public static Pair read(DataInput in) throws IOException {
	         Pair w = new Pair();
	         w.readFields(in);
	         return w;
	       }
	
	public String toString() {
	    return Integer.toString(sum) + ", "
	        + Integer.toString(count);
	  }
}
