package com.mcubillos.urlshortener.controller;

import com.mcubillos.urlshortener.model.ShortenRequest;
import com.mcubillos.urlshortener.service.URLConverterService;
import com.mcubillos.urlshortener.util.URLValidator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class URLController {
    private final URLConverterService urlConverterService;

    public URLController(URLConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

    @RequestMapping(value = "/shortUrl", method= RequestMethod.POST, consumes = {"application/json"})
    public String shortUrl(@RequestBody @Valid final ShortenRequest shortenRequest, HttpServletRequest request) throws Exception {
        String longUrl = shortenRequest.getUrl();
        if (URLValidator.validateURL(longUrl)) {
            String localURL = request.getRequestURL().toString();
            return urlConverterService.shortenURL(localURL, shortenRequest.getUrl());
        }
        throw new Exception("Please enter a valid URL");
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public RedirectView redirectUrl(@PathVariable String id){
        String redirectUrlString = urlConverterService.getURLFromID(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://" + redirectUrlString);
        return redirectView;
    }
}
