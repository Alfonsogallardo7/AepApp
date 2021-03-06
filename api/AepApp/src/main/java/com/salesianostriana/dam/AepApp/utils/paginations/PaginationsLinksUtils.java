package com.salesianostriana.dam.AepApp.utils.paginations;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PaginationsLinksUtils {

    public String createLinkHeader(Page<?> page, UriComponentsBuilder uriComponentsBuilder) {
        final StringBuilder linkHeader = new StringBuilder ();

        if (page.hasNext()) {
            String uri = constructUri(page.getNumber()+1, page.getSize(), uriComponentsBuilder);
            linkHeader.append(buildLinkHeader(uri, "next"));
        }

        if (page.hasPrevious()) {
            String uri = constructUri(page.getNumber()-1, page.getSize(), uriComponentsBuilder);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(buildLinkHeader(uri, "prev"));
        }

        if (!page.isFirst()) {
            String uri = constructUri(0, page.getSize(), uriComponentsBuilder);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(buildLinkHeader(uri, "first"));
        }

        if (!page.isLast()) {
            String uri = constructUri(page.getTotalPages()-1, page.getSize(), uriComponentsBuilder);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(buildLinkHeader(uri, "last"));
        }

        return linkHeader.toString();
    }

    private String constructUri(int newPageNumber, int size, UriComponentsBuilder uriComponentsBuilder) {
        return uriComponentsBuilder.replaceQueryParam("page", newPageNumber).replaceQueryParam("size", size).build().encode().toUriString();
    }

    private String buildLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }

    private void appendCommaIfNecessary (final StringBuilder linkHeader) {
        if (linkHeader.length() > 0)
            linkHeader.append(", ");
    }
}
