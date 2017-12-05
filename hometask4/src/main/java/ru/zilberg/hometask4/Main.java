package ru.zilberg.hometask4;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.ListenerNotFoundException;
import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

//-Xmx512m
//-Xms512m
//-verbose:gc
//-Xloggc:.//logs/gc_%p.log
//-XX:+PrintGCDateStamps
//-XX:+PrintGCDetails

//-XX:+PrintFlagsFinal

//-XX:+UseSerialGC
//-XX:+UseParallelGC
//-XX:+UseParNewGC
//-XX:+UseG1GC


public class Main {
    public static void main(String[] args) throws InterruptedException{
        int count = 190_000;
        if(args.length == 1 )
            count = Integer.parseInt(args[0]);

        startGCMonitor();
        fillList(new String(""), count);
        stopGCMonitor();

    }
    private static <T> void fillList(T obj, int count) throws InterruptedException {
       int i = 0;
       List<T> list = new ArrayList<>();
       while(true){
           i++;
           list.add(obj);
           if (i % count == 0){
               List<T> list2 = new ArrayList<>(list);
               list.clear();
               list.addAll(list2);
               Thread.sleep(1000);
           }
       }
    }




    //Оброботчик сообщений о сборке муссора
    private static NotificationListener gcHangler = new NotificationListener(){
        int countYoungGenerationGC = 0;
        int countOldGenerationGC = 0;
        long timeOfWorkYoungGC = 0L;
        long timeOfWorkOldGC = 0L;
        long startTime = System.currentTimeMillis();
        int counter = 1;
        @Override
      public void handleNotification(Notification notification, Object handback){

          if(notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)){

              GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
              if(gcInfo.getGcAction().contains("end of minor")){
                  countYoungGenerationGC++;
                  timeOfWorkYoungGC += gcInfo.getGcInfo().getDuration();
              }else if(gcInfo.getGcAction().contains("end of major GC")){
                  countOldGenerationGC++;
                  timeOfWorkOldGC += gcInfo.getGcInfo().getDuration();
              }
              /*StringBuilder sb = new StringBuilder();
              sb.append("[").append(gcInfo.getGcAction())
                      .append(" / ").append(gcInfo.getGcName())
                      .append(" / ").append(gcInfo.getGcInfo().getDuration()).append(" ms]");

              System.out.println(sb.toString());*/
              StringBuilder sb1 = new StringBuilder();

              if((System.currentTimeMillis() - startTime) / 60_000 >= counter) {
                  System.out.println((System.currentTimeMillis() - startTime)/60_000 + " min.");
                  sb1.append("Young generation GC count").append(" = ").append(countYoungGenerationGC).append(" # ")
                          .append("Time to spend").append(" : ").append(timeOfWorkYoungGC).append("\n")
                          .append("Old generation GC count").append(" = ").append(countOldGenerationGC).append(" # ")
                          .append("Time to spend").append(" : ").append(timeOfWorkOldGC);
                  System.out.println(sb1);
                  counter++;
              }
          }
      }
    };

    /**
     * Запускаем процесс мониторинга сборки мусора
     */
    private static void startGCMonitor(){
        for(GarbageCollectorMXBean mxBean : ManagementFactory.getGarbageCollectorMXBeans()){
            System.out.println(mxBean.getName());
            ((NotificationEmitter) mxBean).addNotificationListener(gcHangler, null, null);
        }
    }
    /**
     * Останавливаем процесс мониторинга сборки мусора
     */
    private static void stopGCMonitor(){
        for(GarbageCollectorMXBean mxBean: ManagementFactory.getGarbageCollectorMXBeans()){
            try{
                ((NotificationEmitter) mxBean).removeNotificationListener(gcHangler);
            }catch(ListenerNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    /*private static void installGCMonitoring(){
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcbean : gcbeans){
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            int [] countYoungGenerationGC = new int[] {0};
            int [] countOldGenerationGC = new int[] {0};
            long [] timeOfWorkYoungGC = new long[] {0L};
            long [] timeOfWorkOldGC = new long[] {0L};
            long startTime = System.currentTimeMillis();
            int [] counter = new int[] {1};
            NotificationListener listener = (notification, handback) -> {


                if(notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)){
                    GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    if(gcInfo.getGcAction().contains("end of minor")){
                        countYoungGenerationGC[0]++;
                        timeOfWorkYoungGC[0] += gcInfo.getGcInfo().getDuration();
                    }else if(gcInfo.getGcAction().contains("end of major GC")){
                        countOldGenerationGC[0]++;
                        timeOfWorkOldGC[0] += gcInfo.getGcInfo().getDuration();
                    }

                    StringBuilder sb1 = new StringBuilder();

                    if((System.currentTimeMillis() - startTime) / 60_000 >= counter[0]) {
                        System.out.println((System.currentTimeMillis() - startTime)/60_000 + " min.");
                        sb1.append("Young generation GC count").append(" = ").append(countYoungGenerationGC).append(" # ")
                                .append("Time to spend").append(" : ").append(timeOfWorkYoungGC).append("\n")
                                .append("Old generation GC count").append(" = ").append(countOldGenerationGC).append(" # ")
                                .append("Time to spend").append(" : ").append(timeOfWorkOldGC);
                        System.out.println(sb1);
                        counter[0]++;
                    }
                }
            };
            emitter.addNotificationListener(listener, null, null);

            try{
                emitter.removeNotificationListener(listener);
            }catch(ListenerNotFoundException e){
                e.printStackTrace();
            }


        }
    }*/

}
