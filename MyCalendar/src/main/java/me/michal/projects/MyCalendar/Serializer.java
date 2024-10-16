package me.michal.projects.MyCalendar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public final class Serializer {
  public static void serializeData(String fileName, Calendar calendar) {
    try
    {
      final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
      out.writeObject(calendar.getEventData());
      out.close();
    }  catch (Exception e) {
      e.printStackTrace();
    }
  }  
	
  public static void deserializeData(final String fileName, final Calendar calendar) {
    try
    {
      final ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
 
      @SuppressWarnings("unchecked")
      final List<Task> eventData = (List<Task>) in.readObject();

      for (final Task data : eventData) {
        if (data != null) {
          calendar.book(data.getStart(), data.getFinish(), data.getName());
         }
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
