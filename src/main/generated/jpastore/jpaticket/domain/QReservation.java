package jpastore.jpaticket.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 1861252913L;

    public static final QReservation reservation = new QReservation("reservation");

    public final StringPath blueTeam = createString("blueTeam");

    public final StringPath gameDate = createString("gameDate");

    public final StringPath redTeam = createString("redTeam");

    public final StringPath reservation_day = createString("reservation_day");

    public final NumberPath<Long> reservation_id = createNumber("reservation_id", Long.class);

    public final StringPath reservation_num = createString("reservation_num");

    public final StringPath title = createString("title");

    public final StringPath userName = createString("userName");

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

