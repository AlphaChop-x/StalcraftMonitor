package manakin.ru.stalcraftmonitor.service;

import lombok.AllArgsConstructor;
import manakin.ru.stalcraftmonitor.entity.ApplicationRecord;
import manakin.ru.stalcraftmonitor.entity.RecordStatus;
import manakin.ru.stalcraftmonitor.repository.ItemRepository;
import manakin.ru.stalcraftmonitor.repository.RecordRepository;
import manakin.ru.stalcraftmonitor.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public String getRecordContent(int recordId) {
        return recordRepository.getApplicationRecordContentById(recordId);
    }

    public ApplicationRecord getApplicationRecord(int recordId) {
        return recordRepository.getApplicationRecordById(recordId);
    }

    public int createRecord() {
        ApplicationRecord record = new ApplicationRecord();

        record.setStatus(RecordStatus.CREATED);
        record = recordRepository.save(record);

        return record.getId();
    }

    public void fillRecord(int recordId) {
        ApplicationRecord record = recordRepository.findById(recordId);

        long absoluteStartTime = System.currentTimeMillis();

        CompletableFuture<String> userDetails = CompletableFuture.supplyAsync(() -> {
            long startTime = System.currentTimeMillis();
//            Thread userThread = new Thread(() -> {
//            });
//            userThread.start();
//            try {
//                userThread.join();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Всего пользователей: " + userRepository.count() + "\nВремя подсчёта пользователей: " + (System.currentTimeMillis() - startTime) + " миллисекунд(ы/а)";
        });

        CompletableFuture<String> itemDetails = CompletableFuture.supplyAsync(() -> {
            long startTime = System.currentTimeMillis();
//            Thread itemThread = new Thread(() -> {
//            });
//            itemThread.start();
//            try {
//                itemThread.join();
            return "Всего предметов: " + itemRepository.count() + "\nВремя подсчёта предметов: " + (System.currentTimeMillis() - startTime) + " миллисекунд(ы/а)";
//            } catch (Exception e) {
//                throw new RuntimeException("Error counting items!\n", e);
//            }
        });

        CompletableFuture<String> recordBody = userDetails.thenCombine(
                itemDetails, (userText, itemText) ->
                        userText + "\n" + itemText +
                                "\nОбщее затраченное время на формирование отчёта: "
                                + (System.currentTimeMillis() - absoluteStartTime) + " миллисекунд(ы/а)");

        final ApplicationRecord finalRecord = record;

        recordBody.thenAccept(result -> {
                    finalRecord.setRecordContent(result);
                    finalRecord.setStatus(RecordStatus.COMPLETED);
                    recordRepository.save(finalRecord);
                })
                .exceptionally(ex ->
                {
                    finalRecord.setStatus(RecordStatus.FAILED);
                    recordRepository.save(finalRecord);
                    return null;
                });
    }
}

