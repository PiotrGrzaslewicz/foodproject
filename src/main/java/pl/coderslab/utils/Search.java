package pl.coderslab.utils;

import com.google.common.base.CaseFormat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search<T> {

    private Class<T> type;
    private String table;
    private EntityFactory<T> factory;

    public Search(Class<T> type, String table) {
        this.type = type;
        this.table = table;
        this.factory = new EntityFactory<>(type);
    }

    public List<T> inColumns(String phrase, String[] colNames, boolean sortByHitCount) {

        String[] tokens = phrase.split(" ");
        tokens = Arrays.stream(tokens)
                .map(t -> t.replaceAll("[^a-zA-Z0-9żźćńółęąśŻŹĆĄŚĘŁÓŃ]", ""))
                .filter(t -> t.length() > 2)
                .distinct()
                .toArray(String[]::new);
        String searchParams = " REGEXP '" + String.join("|", tokens) + "'";
        List<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table + " WHERE ";
        for (String name : colNames) {
            sql += name + searchParams + " OR ";
        }
        sql = sql.substring(0, sql.length() - 4);
        try (PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql)) {
            ResultSet set = stmt.executeQuery();
            list = factory.getAsList(set);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(sortByHitCount) sortByHitCount(list, colNames, tokens);
        return list;
    }

    public List<T> inColumns(String phrase, String[] colNames) {
        return inColumns(phrase, colNames, true);
    }

    private List<T> sortByHitCount(List<T> list, String[] colNames, String[] tokens){
        list.sort((a, b) -> getHitsInRow(b, colNames, tokens) - getHitsInRow(a, colNames, tokens));
        return list;
    }

    private String columnToGetter(String colName) {
        return "get" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, colName);
    }

    private int getHitsInRow(T obj, String[] colNames, String[] tokens){
        int n = 0;
        try {
            for (String colName : colNames) {
                String val = (String) type.getMethod(columnToGetter(colName)).invoke(obj);
                for (String token : tokens) {
                    Pattern pattern = Pattern.compile(token, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(val);
                    while (matcher.find()) {
                        n++;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return n;
    }
}
