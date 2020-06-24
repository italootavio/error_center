package com.challenge.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventLog is a Querydsl query type for EventLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEventLog extends EntityPathBase<EventLog> {

    private static final long serialVersionUID = 1882821703L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventLog eventLog = new QEventLog("eventLog");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.challenge.enums.Level> level = createEnum("level", com.challenge.enums.Level.class);

    public final StringPath log = createString("log");

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath source = createString("source");

    public final QUser user;

    public QEventLog(String variable) {
        this(EventLog.class, forVariable(variable), INITS);
    }

    public QEventLog(Path<? extends EventLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventLog(PathMetadata metadata, PathInits inits) {
        this(EventLog.class, metadata, inits);
    }

    public QEventLog(Class<? extends EventLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

