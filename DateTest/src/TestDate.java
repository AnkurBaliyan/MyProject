import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		 df.setTimeZone(TimeZone.getDefault());  
		 int i=(int) new Date().getHours();
		 System.out.println(i);
		 System.out.println(new SimpleDateFormat("yyMMdd").format(new Date()));  
		System .out.print(new Date());
	}

}
