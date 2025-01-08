package com.itmo.web_laba_3.dbManager;

import com.influxdb.client.*;
import com.influxdb.client.domain.DeletePredicateRequest;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import com.itmo.web_laba_3.model.Dot;


import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class DotInfluxDBManager {
    private static final String URL = InfluxDBConfig.getUrl();
    private static final String TOKEN = InfluxDBConfig.getToken();
    private static final String ORG = InfluxDBConfig.getOrg();
    private static final String BUCKET = InfluxDBConfig.getBucket();

    private static final String MEASUREMENT = "hits";
    private static final String FIELD_X = "x";
    private static final String FIELD_Y = "y";
    private static final String FIELD_R = "r";
    private static final String FIELD_HIT = "_value";

    private static InfluxDBClient createClient() {
        return InfluxDBClientFactory.create(URL, TOKEN.toCharArray(), ORG, BUCKET);
    }

    public static void addDot(Dot dot) {
        try (InfluxDBClient influxDBClient = createClient()) {
            try (WriteApi writeApi = influxDBClient.getWriteApi()) {
                Point point = Point.measurement(MEASUREMENT)
                        .addTag(FIELD_X, String.valueOf(dot.getX()))
                        .addTag(FIELD_Y, String.valueOf(dot.getY()))
                        .addTag(FIELD_R, String.valueOf(dot.getR()))
                        .addField(FIELD_HIT, dot.isHit())
                        .time(System.currentTimeMillis(), WritePrecision.MS);
                writeApi.writePoint(point);
            }
        }
    }

    public static List<Dot> getDots() {
        try (InfluxDBClient influxDBClient = createClient()) {

            QueryApi queryApi = influxDBClient.getQueryApi();

            String fluxQuery = "from(bucket: \"" + BUCKET + "\") " +
                    "|> range(start: -1y) " +
                    "|> filter(fn: (r) => r._measurement == \"" + MEASUREMENT + "\") " +
                    "|> sort(columns: [\"_time\"], desc: true)";

            List<FluxTable> tables = queryApi.query(fluxQuery);
            List<Dot> dots = new ArrayList<>();
            for (FluxTable table : tables) {
                for (FluxRecord record : table.getRecords()) {
                    int x = Integer.parseInt((String) Objects.requireNonNull(record.getValueByKey(FIELD_X)));
                    double y = Double.parseDouble((String) Objects.requireNonNull(record.getValueByKey(FIELD_Y)));
                    int r = Integer.parseInt((String) Objects.requireNonNull(record.getValueByKey(FIELD_R)));
                    boolean hit = (Boolean) Objects.requireNonNull(record.getValueByKey(FIELD_HIT));
                    Instant time = record.getTime();

                    dots.add(new Dot(x, y, r, hit, time));
                }
            }
            dots.sort(Comparator.comparing(Dot::getTime).reversed());
            return dots;
        }
    }

    public static void clearAllDots() {
        try (InfluxDBClient influxDBClient = createClient()) {

            DeleteApi deleteApi = influxDBClient.getDeleteApi();

            OffsetDateTime start = Instant.ofEpochMilli(0).atOffset(ZoneOffset.UTC);
            OffsetDateTime stop = Instant.now().atOffset(ZoneOffset.UTC);

            DeletePredicateRequest request = new DeletePredicateRequest()
                    .start(start)
                    .stop(stop)
                    .predicate("_measurement=\"" + MEASUREMENT + "\"");

            deleteApi.delete(request, BUCKET, ORG);
        }
    }
}