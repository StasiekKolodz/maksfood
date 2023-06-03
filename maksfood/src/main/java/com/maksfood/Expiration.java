package com.maksfood;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Vector;


public class Expiration {

    public DataBase DB;

    public long currTimeSecs;
    public long difference;
    public String dateFormat;
    public Vector<String> aboutToExpire = new Vector<String>();

        public Expiration(DataBase dataBase){
            this.currTimeSecs = System.currentTimeMillis() / 1000;
            this.DB = dataBase;
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
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = formatter.format(date);
            return formattedDate;
        }

        public String formatSecondsToDays(long seconds){
            String days = Double.toString(Math.floor(seconds / 86400));
            return days;
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

        public void updateExpired() throws SQLException{
            DB.sqlSelect("SELECT * from maksfood.fridge WHERE name NOT IN (SELECT name from maksfood.expiredProducts)");
            Vector<String> prodExpDates = DB.getElements(4);
            Vector<String> prodNames = DB.getElements(2);
            Vector<String> expiredProds = new Vector<String>();
            for(int i = 0; i < prodExpDates.size(); i++){
                String prodDate = prodExpDates.get(i);
                long prodDateSec = convertDateToSeconds(prodDate);
                if(prodDateSec < this.currTimeSecs){
                    expiredProds.add(prodNames.get(i));
                    DB.sqlUpdate("INSERT INTO maksfood.expiredProducts VALUES(DEFAULT, '"+
                    prodNames.get(i) + "','" + "0" + "','" + "1" + "')"
                    );
                }
            }
        }

        public void updateAboutToExpire(){
            DB.sqlSelect("SELECT * from maksfood.fridge WHERE name NOT IN (SELECT name from maksfood.expiredProducts)");
            Vector<String> prodExpDates = new Vector<String>();
            Vector<String> prodNames = new Vector<String>();
            int count = DB.CountRS();
            for(int i=1; i<=count; i++){
                Vector<String> prodData = DB.getRow(i, 2, 4);
                if(prodData.size()!=0){
                    prodExpDates.add(prodData.elementAt(2));
                    prodNames.add(prodData.elementAt(0));
                }
            }
            
            for(int i = 0; i < prodExpDates.size(); i++){
                String prodDate = prodExpDates.get(i);
                long prodDateSec = convertDateToSeconds(prodDate);
                // check if the exp date is in 3 days
                if(0 < prodDateSec - this.currTimeSecs &&  prodDateSec - this.currTimeSecs < 259200){
                    aboutToExpire.add(prodNames.get(i));
                    aboutToExpire.add(prodNames.get(i));
                    long secsToExpire = prodDateSec - this.currTimeSecs;
                    String daysToExpire = formatSecondsToDays(secsToExpire);
                    DB.sqlUpdate("INSERT INTO maksfood.expiredProducts VALUES(DEFAULT, '"+
                    prodNames.get(i) + "','" + daysToExpire + "','" + "0" + "')"
                    );
                }
            }
    }
}
    
