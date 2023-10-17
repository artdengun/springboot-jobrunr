import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.cron.Cron;
import org.jobrunr.storage.InMemoryStorageProvider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static java.time.LocalTime.now;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World");

        // konfigurasi pertama pada JobRunner
        JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider())
                .useBackgroundJobServer()
                .useDashboard()
                .initialize();
        // contoh kita  buat background procces
        BackgroundJob.<ContohClass>enqueue(x  -> x.kitaPanggilMethodContoh(5));
        //  scheduling
        ContohScheduler contohScheduler = new ContohScheduler();
        BackgroundJob.schedule(Instant.now().plus(1, MINUTES), contohScheduler::contohSchedulerRunningDong);

        ContohRecurring contohRecurring = new  ContohRecurring();
        BackgroundJob.scheduleRecurrently(Cron.every30seconds(), () -> System.out.println("Easy!"));
    }
}
