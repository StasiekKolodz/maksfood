package com.maksfood;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Vector;


public class Time {

    public DataBase fridgeDB;

    public long currTimeSecs;
    public long difference;
    public String dateFormat;

        public Time(DataBase dataBase){
            this.currTimeSecs = System.currentTimeMillis() / 1000;
            this.fridgeDB = dataBase;
            this.dateFormat = formatSecondsToDate(this.currTimeSecs);
        }

        public LocalDateTime getCurrentTime(){
            return LocalDateTime.now();
        }

        public void calculateDateDifference(LocalDateTime startDate, LocalDateTime endDate){
            this.difference = ChronoUnit.SECONDS.between(startDate, endDate);
        }

        public String formatSecondsToDate(long seconds){
            Date date = new Date(seconds * 1000);
            // Instant instant = Instant.ofEpochSecond(seconds);
            // LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = formatter.format(date);
            return formattedDate;
        }

        public String formatSecondsToTime(long seconds){
            Duration duration = Duration.ofSeconds(seconds);
            long hours = duration.toHoursPart();
            long minutes = duration.toMinutesPart();
            long remainingSeconds = duration.toSecondsPart();
    
            LocalTime t = LocalTime.of((int) hours, (int) minutes, (int) remainingSeconds);
            return t.toString();
        }

        public static long convertDateToSeconds(String dateString){
            try{
            LocalDate date = LocalDate.parse(dateString);
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIN);
            return dateTime.toEpochSecond(ZoneOffset.UTC);
        }
        catch(Exception e){
            return 0;
        }
        }

        public Vector<String> getExpired(){
            fridgeDB.sqlSelect("SELECT * from maksfood.fridge");
            Vector<String> prodExpDates = fridgeDB.getElements(30, 4);
            Vector<String> prodNames = fridgeDB.getElements(30, 2);
            Vector<String> expiredProds = new Vector<String>();
            for(int i = 0; i < prodExpDates.size(); i++){
                String prodDate = prodExpDates.get(i);
                long prodDateSec = convertDateToSeconds(prodDate);
                if(prodDateSec < this.currTimeSecs){
                    expiredProds.add(prodNames.get(i));
                }
            }
            return expiredProds;
        }

        public Vector<String> getAboutToExpire(){
            fridgeDB.sqlSelect("SELECT * from maksfood.fridge");
            Vector<String> prodExpDates = new Vector<String>();
            Vector<String> prodNames = new Vector<String>();
            for(int i=0; i<30; i++){
                Vector<String> prodData = fridgeDB.getRow(i, 2, 4);
                if(prodData.size()!=0){
                prodExpDates.add(prodData.elementAt(2));
                prodNames.add(prodData.elementAt(0));
                }
            }
            Vector<String> aboutToExpire = new Vector<String>();
            for(int i = 0; i < prodExpDates.size(); i++){
                String prodDate = prodExpDates.get(i);
                long prodDateSec = convertDateToSeconds(prodDate);
                // check if the exp date is in 3 days
                if(0 < prodDateSec - this.currTimeSecs &&  prodDateSec - this.currTimeSecs < 259200000){
                    aboutToExpire.add(prodNames.get(i));
                }
            }
            return aboutToExpire;
    }
}
    
