package com.netflix.GifService.controllers;

import com.netflix.GifService.objects.GifData;
import com.netflix.GifService.objects.GifSearchResult;
import com.netflix.GifService.services.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class GifController {

    @Autowired
    GifService gifService;

    @GetMapping("/query")
    @ResponseBody
    public GifSearchResult gifSearch(@RequestParam(name = "searchTerm") String searchTerm) throws Exception{
        ArrayList<GifData> result = new ArrayList<>();

        String[] searchTermsList = searchTerm.split(",");
        for (String term: searchTermsList){
            result.add(gifService.getBatchGifData(term));
        }

        return new GifSearchResult(result);
    }
}
