package com.mmn.translation.controller;

import java.util.List;

import com.mmn.translation.dto.I18NDto;
import com.mmn.translation.model.I18NData;
import com.mmn.translation.service.I18NDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lang")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class I18NDataController {
    private final I18NDataService service;

    @GetMapping
    @ResponseBody
    public List<I18NDto> allDto() {
        return this.service.allDto();
    }

    @GetMapping("/{lang}")
    @ResponseBody
    public I18NDto by(@PathVariable("lang") final String lang) {
        return this.service.by(lang);
    }

    @PostMapping("/one")
    @ResponseBody
    public I18NData saveOne(@RequestBody final I18NData data) {
        return this.service.saveOne(data);
    }

    @PostMapping("/many")
    @ResponseBody
    public List<I18NData> saveMany(@RequestBody final I18NDto dto) {
        return this.service.saveMany(dto);
    }

    @DeleteMapping("/one")
    @ResponseBody
    public String deleteOne(@RequestBody final I18NData data) {
        return this.service.deleteOne(data);
    }

    @DeleteMapping("/many")
    @ResponseBody
    public List<String> deleteAll(@RequestBody final I18NDto dto) {
        return this.service.deleteMany(dto);
    }
}
