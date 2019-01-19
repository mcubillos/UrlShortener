package com.mcubillos.urlshortener.service;

import com.mcubillos.urlshortener.model.URL;
import com.mcubillos.urlshortener.repository.URLRepository;
import com.mcubillos.urlshortener.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class URLConverterService {

    @Autowired
    private URLRepository urlRepository;

    public String shortenURL(String localURL, String longUrl) {
        Long id = retrieveNextIndex();
        urlRepository.save(new URL("url:"+id, longUrl));
        String baseString = formatLocalURLFromShortener(localURL);
        return baseString + Converter.createID(id);
    }

    public String getURLFromID(String uniqueID){
        Long dictionaryKey = Converter.getDictionaryKeyFromUniqueID(uniqueID);
        URL longUrl = urlRepository.findURLByIdKey("url:" + dictionaryKey.toString());
        return longUrl.getUrlKey();
    }

    private Long retrieveNextIndex(){
        List<URL> urls = urlRepository.findURLByOrderByIdKeyDesc();
        return Long.valueOf(urls.size());
    }
    private String formatLocalURLFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }

        sb.append('/');
        return sb.toString();
    }
}
