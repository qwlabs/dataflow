package com.qwlabs.dataflow.connector.options;

import com.qwlabs.dataflow.connector.KnownConnectors;

import java.util.Map;
import java.util.Set;

/**
 * https://github.com/ververica/flink-cdc-connectors
 */
public class ConnectorAllowOptions {
    private static final Set<String> JDBC_ALLOW_OPTIONS = Set.of(
        "url",
        "table-name",
        "driver",
        "username",
        "password",
        "connection.max-retry-timeout",
        "scan.partition.column",
        "scan.partition.num",
        "scan.partition.lower-bound",
        "scan.partition.upper-bound",
        "scan.fetch-size",
        "scan.auto-commit",
        "lookup.cache",
        "lookup.partial-cache.max-rows",
        "lookup.partial-cache.expire-after-write",
        "lookup.partial-cache.expire-after-access",
        "lookup.partial-cache.caching-missing-key",
        "lookup.max-retries",
        "sink.buffer-flush.max-rows",
        "sink.buffer-flush.interval",
        "sink.max-retries",
        "sink.parallelism"
    );

    private static final Set<String> MONGODB_CDC_ALLOW_OPTIONS = Set.of(
        "hosts",
        "username",
        "password",
        "database",
        "collection",
        "connection.options",
        "copy.existing",
        "copy.existing.queue.size",
        "batch.size",
        "poll.max.batch.size",
        "poll.await.time.ms",
        "heartbeat.interval.ms",
        "scan.incremental.snapshot.enabled",
        "scan.incremental.snapshot.chunk.size.mb"
    );

    private static final Set<String> MYSQL_CDC_ALLOW_OPTIONS = Set.of(
        "hostname",
        "username",
        "password",
        "database-name",
        "table-name",
        "port",
        "server-id",
        "scan.incremental.snapshot.enabled",
        "scan.incremental.snapshot.chunk.size",
        "scan.snapshot.fetch.size",
        "scan.startup.mode",
        "scan.startup.specific-offset.file",
        "scan.startup.specific-offset.pos",
        "scan.startup.specific-offset.gtid-set",
        "scan.startup.specific-offset.skip-events",
        "scan.startup.specific-offset.skip-rows",
        "server-time-zone",
        "debezium.min.row.count.to.stream.result",
        "connect.timeout",
        "connect.max-retries",
        "connection.pool.size",
        "jdbc.properties.",
        "heartbeat.interval",
        "debezium."
    );


    private static final Set<String> OCEANBASE_CDC_ALLOW_OPTIONS = Set.of(
        "scan.startup.mode",
        "scan.startup.timestamp",
        "username",
        "password",
        "tenant-name",
        "database-name",
        "table-name",
        "table-list",
        "hostname",
        "port",
        "connect.timeout",
        "server-time-zone",
        "logproxy.host",
        "logproxy.port",
        "logproxy.client.id",
        "rootserver-list",
        "config-url",
        "working-mode"
    );

    private static final Set<String> ORACLE_CDC_ALLOW_OPTIONS = Set.of(
        "hostname",
        "username",
        "password",
        "database-name",
        "schema-name",
        "table-name",
        "port",
        "url",
        "scan.startup.mode",
        "scan.incremental.snapshot.enabled",
        "scan.incremental.snapshot.chunk.size",
        "scan.snapshot.fetch.size",
        "connect.max-retries",
        "connection.pool.size",
        "debezium."
    );

    private static final Set<String> POSTGRES_CDC_ALLOW_OPTIONS = Set.of(
        "hostname",
        "username",
        "password",
        "database-name",
        "schema-name",
        "table-name",
        "port",
        "decoding.plugin.name",
        "slot.name",
        "changelog-mode",
        "upsert",
        "debezium.",
        "debezium.snapshot.select.statement.overrides",
        "debezium.snapshot.select.statement.overrides."
    );

    private static final Set<String> SQLSERVER_CDC_ALLOW_OPTIONS = Set.of(
        "hostname",
        "username",
        "password",
        "database-name",
        "schema-name",
        "table-name",
        "port",
        "server-time-zone",
        "debezium."
    );

    private static final Set<String> TIDB_CDC_ALLOW_OPTIONS = Set.of(
        "database-name",
        "table-name",
        "scan.startup.mode",
        "pd-addresses",
        "tikv.grpc.timeout_in_ms",
        "tikv.grpc.scan_timeout_in_ms",
        "tikv.batch_get_concurrency",
        "tikv."
    );

    private static final Set<String> DB2_CDC_ALLOW_OPTIONS = Set.of(
        "hostname",
        "username",
        "password",
        "database-name",
        "schema-name",
        "table-name",
        "port",
        "scan.startup.mode",
        "server-time-zone",
        "debezium."
    );

    private static final Map<String, Set<String>> ALLOW_OPTIONS_MAPPING = Map.of(
        KnownConnectors.JDBC, JDBC_ALLOW_OPTIONS,
        KnownConnectors.MONGODB_CDC, MONGODB_CDC_ALLOW_OPTIONS,
        KnownConnectors.MYSQL_CDC, MYSQL_CDC_ALLOW_OPTIONS,
        KnownConnectors.OCEANBASE_CDC, OCEANBASE_CDC_ALLOW_OPTIONS,
        KnownConnectors.ORACLE_CDC, ORACLE_CDC_ALLOW_OPTIONS,
        KnownConnectors.POSTGRES_CDC, POSTGRES_CDC_ALLOW_OPTIONS,
        KnownConnectors.SQLSERVER_CDC, SQLSERVER_CDC_ALLOW_OPTIONS,
        KnownConnectors.TIDB_CDC, TIDB_CDC_ALLOW_OPTIONS,
        KnownConnectors.DB2_CDC, DB2_CDC_ALLOW_OPTIONS
    );

    public static Set<String> get(String connector) {
        return ALLOW_OPTIONS_MAPPING.get(connector);
    }
}
