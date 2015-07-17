package ru.ncedu.java.tasks;

import java.text.ParseException;
import java.util.*;
import java.text.DateFormat;

/**
 * Created by user on 16.07.2015.
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        DateCollections q=new DateCollectionsImpl();
        Calendar cal= Calendar.getInstance();
        Date date=cal.getTime();
        System.out.println(date+"fwe");
        System.out.println(Calendar.getInstance());
        DateFormat df=DateFormat.getDateInstance(2);
        DateFormat df2=DateFormat.getDateInstance(3);
        String str = df.format(date);
        date=df2.parse(str);
        Random rand=new Random();
        q.setDateStyle(3);
        q.initMainMap(5, Calendar.getInstance());

        Calendar cal2= Calendar.getInstance();
        Map map=q.getMainMap();
        System.out.println(cal);
        System.out.println(rand.nextInt(2));
        System.out.println(map);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.compareTo(cal2));
        q.getMainList();
        List<DateCollections.Element> list=q.getMainList();
        q.removeFromList(list);
        System.out.println(list);
        System.out.println(cal2.get(Calendar.MONTH));
        System.out.println();
    }
}
