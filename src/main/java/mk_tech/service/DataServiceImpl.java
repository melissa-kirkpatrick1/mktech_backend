package mk_tech.service;

import mk_tech.models.Week;
import mk_tech.repository.WeekRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Service
public class DataServiceImpl implements DataService {

    private final WeekRepository weekRepository;
    public DataServiceImpl(WeekRepository weekRepository) {
        this.weekRepository = weekRepository;
    }
    public void loadWeeks() {
        this.populateWeekData();

    }

    private void populateWeekData() {
        String startDate = "01/02/2023";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        System.out.println("Current Date: "+sdf.format(cal.getTime()));
        try{
            for (int i=1; i < 53; i++) {
                Week week = new Week();
                cal.setTime(sdf.parse(startDate));
                week.setStartWeekDate(cal.getTime());
                cal.add(Calendar.DAY_OF_MONTH, 6);
                week.setEndWeekDate(cal.getTime());
                week.setWeekNumber(i);
                this.weekRepository.save(week);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                startDate = sdf.format(cal.getTime());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
