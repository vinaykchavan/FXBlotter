package Data;

import java.util.concurrent.ConcurrentHashMap;

public class DataStorage {
	public static ConcurrentHashMap<String,TradeStore> map=new ConcurrentHashMap<String, TradeStore>();
}
class TradeStore
{
	String currency;
	int quantity;
}