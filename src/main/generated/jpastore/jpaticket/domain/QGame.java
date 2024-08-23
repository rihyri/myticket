package jpastore.jpaticket.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGame is a Querydsl query type for Game
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGame extends EntityPathBase<Game> {

    private static final long serialVersionUID = 765063213L;

    public static final QGame game = new QGame("game");

    public final StringPath blueTeam = createString("blueTeam");

    public final StringPath gameDate = createString("gameDate");

    public final StringPath gameTime = createString("gameTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath place = createString("place");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath redTeam = createString("redTeam");

    public final StringPath title = createString("title");

    public QGame(String variable) {
        super(Game.class, forVariable(variable));
    }

    public QGame(Path<? extends Game> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGame(PathMetadata metadata) {
        super(Game.class, metadata);
    }

}

