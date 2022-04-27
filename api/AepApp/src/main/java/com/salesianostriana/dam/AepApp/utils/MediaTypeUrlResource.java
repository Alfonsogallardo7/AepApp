package com.salesianostriana.dam.AepApp.utils;

import com.salesianostriana.dam.AepApp.exception.StorageException;
import org.apache.tika.Tika;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MediaTypeUrlResource extends UrlResource {
    public MediaTypeUrlResource(URI uri) throws MalformedURLException {
        super(uri);
    }

    public MediaTypeUrlResource(URL url) {
        super(url);
    }

    public MediaTypeUrlResource(String path) throws MalformedURLException {
        super(path);
    }

    public MediaTypeUrlResource(String protocol, String location) throws MalformedURLException {
        super(protocol, location);
    }

    public MediaTypeUrlResource(String protocol, String location, String fragment) throws MalformedURLException {
        super(protocol, location, fragment);
    }

    public String getType() {
        Tika tika = new Tika();

        try {
            return tika.detect(this.getFile());
        } catch (IOException exception) {
            throw  new StorageException("Error obteniendo el tipo MIME del fichero", exception);
        }
    }
}