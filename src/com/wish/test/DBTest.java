package com.wish.test;

import com.db.model.*;
import com.wish.model.BookMaster;
import java.util.*;

public class DBTest{
	public static void main(String args[])throws Exception{
		BookMaster bm = new BookMaster();
		LinkedHashMap<String, String> fields = new LinkedHashMap<String, String>();
		fields.put("s-name", "abhialsh3");
		fields.put("s-publisher", "iiit3");
		
		LinkedHashMap<String, String> wheres = new LinkedHashMap<String, String>();
		wheres.put("copies", "10");
		wheres.put("price", "45.5");
		bm.update(fields,wheres);
	}
}

