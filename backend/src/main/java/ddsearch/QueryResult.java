package ddsearch;

import lombok.Getter;

public class QueryResult {

//    @Getter private final long id;
    @Getter private final String url;
//    @Getter private final String title;
//    @Getter private final String summary;
//    @Getter private final long timestamp;

    public QueryResult(final String url) {
        this.url = url;
    }

    /*
    public QueryResult(final long id, final String url, final String title,
            final String summary, final long timestamp) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.summary = summary;
        this.timestamp = timestamp;
    }
    */
}    
