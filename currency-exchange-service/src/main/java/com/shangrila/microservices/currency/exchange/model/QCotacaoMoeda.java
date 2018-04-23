//package com.shangrila.microservices.currency.exchange.model;
//
//import static com.querydsl.core.types.PathMetadataFactory.forVariable;
//
//import javax.annotation.Generated;
//
//import com.querydsl.core.types.Path;
//import com.querydsl.core.types.PathMetadata;
//import com.querydsl.core.types.dsl.EntityPathBase;
//import com.querydsl.core.types.dsl.NumberPath;
//import com.querydsl.core.types.dsl.SimplePath;
//import com.querydsl.core.types.dsl.StringPath;
//
//
///**
// * QCotacaomoeda is a Querydsl query type for Cotacaomoeda
// */
//@Generated("com.querydsl.codegen.EntitySerializer")
//public class QCotacaoMoeda extends EntityPathBase<CotacaoMoeda> {
//
//    private static final long serialVersionUID = 2147150673L;
//
//    public static final QCotacaoMoeda cotacaomoeda = new QCotacaoMoeda("cotacaomoeda");
//
//    public final SimplePath<CotacaoMoedaId> id = createSimple("id", CotacaoMoedaId.class);
//
//    public final StringPath paridadeCompra = createString("paridadeCompra");
//
//    public final StringPath paridadeVenda = createString("paridadeVenda");
//
//    public final NumberPath<Integer> codMoeda = createNumber("codMoeda", Integer.class);
//
//    public final StringPath taxaCompra = createString("taxaCompra");
//
//    public final StringPath TaxaVenda = createString("TaxaVenda");
//
//    public final StringPath taxaVenda = createString("taxaVenda");
//
//    public final StringPath tipo = createString("tipo");
//
//    public QCotacaoMoeda(String variable) {
//        super(CotacaoMoeda.class, forVariable(variable));
//    }
//
//    public QCotacaoMoeda(Path<? extends CotacaoMoeda> path) {
//        super(path.getType(), path.getMetadata());
//    }
//
//    public QCotacaoMoeda(PathMetadata metadata) {
//        super(CotacaoMoeda.class, metadata);
//    }
//
//}
//
