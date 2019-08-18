package Data;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MarketProducer extends Thread {

	public static ConcurrentHashMap<String,TradeValues> map=new ConcurrentHashMap<String, TradeValues>();
	public void run()
	{
		while(true)
		{
			map.put("EUR/USD", new TradeValues("EUR/USD",BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1,1.4))));
			map.put("USD/EUR", new TradeValues("USD/EUR",BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(2,2.18))));
			map.put("INR/USD", new TradeValues("INR/USD",BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.001,0.010))));
			map.put("USD/INR", new TradeValues("USD/INR",BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(65,75))));
			map.put("USD/GBP", new TradeValues("USD/INR",BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1,1.3))));
			map.put("GBP/USD", new TradeValues("USD/INR",BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1,1.3))));
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class TradeValues{
	BigDecimal bid,ask;
	
	TradeValues(String currency,BigDecimal bid){
		this.bid = bid.setScale(4, BigDecimal.ROUND_DOWN);
		this.ask = BigDecimal.valueOf(bid.doubleValue()+ThreadLocalRandom.current().nextDouble(0,0.2)).setScale(4, BigDecimal.ROUND_DOWN);
		System.out.println(currency + " : BID : " + bid + " : Current Time : " + LocalTime.now());
	}
}