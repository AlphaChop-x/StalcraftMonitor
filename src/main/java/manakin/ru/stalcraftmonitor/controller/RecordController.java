package manakin.ru.stalcraftmonitor.controller;

import lombok.RequiredArgsConstructor;
import manakin.ru.stalcraftmonitor.entity.ApplicationRecord;
import manakin.ru.stalcraftmonitor.service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    @GetMapping()
    public ResponseEntity<String> createRecord() {
        int recordId = recordService.createRecord();
        recordService.fillRecord(recordId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(String.valueOf(recordId));
    }

    @GetMapping("/{recordId}")
    public String getRecords(
            @PathVariable int recordId,
            Model model
    ) {
        ApplicationRecord record = recordService.getApplicationRecord(recordId);
        model.addAttribute("record", record);
        return "recordPage";
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(recordService.getRecordContent(recordId));
    }
}
