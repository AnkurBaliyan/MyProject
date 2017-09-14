package urlDownload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UrlDownload {

	public static void main(String args[]) throws IOException {

		URL downloadUrl;
		FileOutputStream fileOut;
		int i = (int) new Date().getHours();
		if (i < 15) {
			downloadUrl = new URL("http://weather.cod.edu/digatmos/syn/"
					+ new SimpleDateFormat("yyMMdd").format(new Date()) + "03.syn");

			fileOut = new FileOutputStream("/home/sparkuser/gtsdata/GTSFiles/GTS03");
			System.out.println("Data is in GTS03");
		}
		else {
			downloadUrl = new URL("http://weather.cod.edu/digatmos/syn/"
					+ new SimpleDateFormat("yyMMdd").format(new Date()) + "12.syn");

			fileOut = new FileOutputStream("/home/sparkuser/gtsdata/GTSFiles/GTS12");
			System.out.println("Data is in GTS12");
		}
		ReadableByteChannel rbc = Channels.newChannel(downloadUrl.openStream());

		fileOut.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

		System.out.println("success");
	}

}
