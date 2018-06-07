package com.example.fionasiu.temperatureconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converters {
    private static String na = "N/A";
    private static Double area = 0.404686;
    private static Double length = 0.3048;
    private static Double weight = 0.453592;
    public static Convert leftConversion;
    public static Convert rightConversion;

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ConverterItem> ITEMS = new ArrayList<ConverterItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ConverterItem> ITEM_MAP = new HashMap<>();

    static {
        // Add some sample items.
        addItem(createConverterItem(0, "Area", "ac > ha", "ha > ac", (ac) ->
        {
            // Returns 0.0 if value entered is a negative
            if (ac < 0){
                return 0.0;
            }
            return ac * area;
        }, (ha) -> {
            if (ha < 0){
                return 0.0;
            }
            return ha / area;
        }));
        addItem(createConverterItem(1,"Length", "ft > m", "m > ft", (ft) -> {
            // Returns 0.0 if value entered is a negative
            if (ft < 0)
            {
                return 0.0;
            }
            return ft * length;
        }, (m) -> {
            if (m < 0){
                return 0.0;
            }
            return m / length;
        }));
        addItem(createConverterItem(2, "Temperature", "F > C", "C > F", (f) -> ((f - 32.0) * 5.0 / 9.0), (c) -> (c * 9.0/5.0 + 32.0)));
        addItem(createConverterItem(3, "Weight", "lbs > kg", "kg > lbs", (lbs) ->
        {
            // Returns 0.0 if value entered is a negative
            if (lbs < 0)
            {
                return 0.0;
            }
            return lbs * weight;
        }, (kg) -> {
            if (kg < 0){
                return 0.0;
            }
            return kg / weight;
        }));
    }

    private static void addItem(ConverterItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ConverterItem createConverterItem(int id, String name, String leftButtonLabel, String rightButtonLabel, Convert leftConversion, Convert rightConversion) {
        return new ConverterItem(String.valueOf(id), name ,leftButtonLabel, rightButtonLabel, leftConversion, rightConversion);
    }

    public interface Convert{
        Double convert(Double input);
    }

    public static class ConverterItem{
        public final String id;
        public final String name;
        public final String leftButtonLabel;
        public final String rightButtonLabel;
        public final Convert leftConversion;
        public final Convert rightConversion;

        public ConverterItem(String id, String name, String leftButtonLabel, String rightButtonLabel, Convert leftConversion, Convert rightConversion){
            this.id = id;
            this.name = name;
            this.leftButtonLabel = leftButtonLabel;
            this.rightButtonLabel = rightButtonLabel;
            this.leftConversion = leftConversion;
            this.rightConversion = rightConversion;
        }

        @Override
        public String toString() {return name;}
    }

}
