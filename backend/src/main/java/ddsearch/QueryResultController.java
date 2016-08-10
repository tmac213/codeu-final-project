package ddsearch;

import com.flatironschool.javacs.*;
import java.util.*;
import java.util.Map.Entry;
import org.springframework.web.bind.annotation.*;

@RestController
public class QueryResultController {
    
    @RequestMapping(path="/search", method=RequestMethod.GET)
    public ArrayList<QueryResult> results(@RequestParam(value="q", required=true, defaultValue="") String query,
                                @RequestParam(value="min_words", defaultValue="0") int minWords,
                                @RequestParam(value="summaries", defaultValue="true") boolean summaries,
                                @RequestParam(value="errors_allowed", defaultValue="true") boolean errorsAllowed,
                                @RequestParam(value="language", defaultValue="EN_US") String language,
                                @RequestParam(value="num_results", defaultValue="100") int numResults) {

        //TODO add support for two keywords 
        List<Entry<String, Integer>> results = WikiSearch.search(query).sort();
        ArrayList<QueryResult> ret = new ArrayList<>();

        for (Entry<String, Integer> entry : results) {
            ret.add(new QueryResult(entry.getKey()));
        }

        return ret;
    }
}
