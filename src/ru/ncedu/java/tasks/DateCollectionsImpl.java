package ru.ncedu.java.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by user on 16.07.2015.
 */
public class DateCollectionsImpl implements DateCollections{

    private Map<String,Element> map;
    private Comparator<String> comp;
    private int dateStyle=2;

    public DateCollectionsImpl(){
        this.comp=new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                try {
                    Calendar cal1 = toCalendar(s1);
                    Calendar cal2 = toCalendar(s2);
                    return cal1.compareTo(cal2);
                }
                catch(ParseException exception){
                    System.out.println(exception);
                    return 0;
                }
            }
        };
    }

    public DateCollectionsImpl(Comparator<String> comp){
        this.comp=comp;
    }

    @Override
    public Calendar toCalendar(String dateString) throws ParseException {
        DateFormat df=DateFormat.getDateInstance(dateStyle,Locale.US);
        Date date=df.parse(dateString);
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    @Override
    public void setDateStyle(int dateStyle) {
        this.dateStyle = dateStyle;
    }

    @Override
    public String toString(Calendar date) {
        DateFormat df=DateFormat.getDateInstance(dateStyle,Locale.US);
        Date date2=date.getTime();
        return df.format(date2);
    }

    @Override
    public void initMainMap(int elementsNumber, Calendar firstDate) {
        map= new TreeMap<String,Element>(comp);
        Element element;
        Random rand=new Random();
        for(int i=0;i<elementsNumber;++i){
            element=new Element((Calendar)firstDate.clone(),rand.nextInt(2000));
            //map.put(element.toString(),element);
            String str=toString(firstDate);
            firstDate.add(Calendar.DATE, 110);
            map.put(str,element);
        }
    }

    @Override
    public void setMainMap(Map<String, Element> map) {
        this.map= new TreeMap<String,Element>(comp);
        this.map.putAll(map);
    }

    @Override
    public Map<String, Element> getMainMap() {
        return map;
    }

    @Override
    public SortedMap<String, Element> getSortedSubMap() {
        Comparator<String> comp=new Comparator<String>(){
            public int compare(String s1,String s2) {
                try {
                    Calendar cal1 = toCalendar(s1);
                    Calendar cal2 = toCalendar(s2);
                    return cal1.compareTo(cal2);
                }
                catch(ParseException exception){
                    System.out.println(exception);
                    return 0;
                }
            }
        };
        SortedMap submap=new TreeMap(comp);
        Element element;
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Calendar cal2;
        for(Map.Entry<String,Element> e : map.entrySet()){
            element=e.getValue();
            cal2=element.getBirthDate();
            cal2.set(Calendar.HOUR_OF_DAY, 0);
            cal2.set(Calendar.MINUTE, 0);
            cal2.set(Calendar.SECOND, 0);
            cal2.set(Calendar.MILLISECOND, 0);
            if(cal.compareTo(cal2)<0){
                    submap.put(e.getKey(), element);
            }
        }
        return submap;
    }

    @Override
    public List<Element> getMainList() {
        List<Element> list=new ArrayList<>();
        for(Map.Entry<String,Element> e : map.entrySet()){
            list.add(e.getValue());
        }
        Collections.sort(list, new Comparator<Element>() {
            public int compare(Element e1, Element e2) {
                return e1.getBirthDate().compareTo(e2.getBirthDate());
            }
        });
        return list;
    }

    @Override
    public void sortList(List<Element> list) {
        Collections.sort(list, new Comparator<Element>() {
            public int compare(Element e1, Element e2) {
                return e1.getDeathDate().compareTo(e2.getDeathDate());
            }
        });

    }

    @Override
    public void removeFromList(List<Element> list) {
        Iterator<Element> iter=list.iterator();
        Element element;
        Calendar cal;
        while(iter.hasNext()) {
            element=iter.next();
            cal=element.getBirthDate();
            int month=cal.get(Calendar.MONTH);
            if((month==11) || (month==0) || (month==1)) {
                iter.remove();
            }
        }
    }
}
