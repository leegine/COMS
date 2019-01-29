head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteCacheStore.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

/**
 * キャッシュされた時価情報を取得するためのインターフェイス。
 *
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3QuoteCacheStore
{

    /**
     * キャッシュされた株式の時価情報を取得する。
     *
     * @@param tradedProduct 時価情報を取得する取引銘柄
     * @@param realType リアル区分
     * @@return 時価情報
     */
    public WEB3EqTypeQuoteData get(
        EqTypeTradedProduct tradedProduct,
        RealType realType);
    
    /**
     * キャッシュされた指数先物・オプションの時価情報を取得する。
     * 
     * @@param tradedProduct 時価情報を取得する取引銘柄
     * @@param realType リアル区分
     * @@return 時価情報
     */
    public WEB3IfoQuoteData get(
        IfoTradedProduct tradedProduct,
        RealType realType);
    
    /**
     * キャッシュされた株式の時価情報を取得する。
     * 銘柄の時価情報が存在しない場合、nullを返す。
     *
     * @@param tradedProduct 時価情報を取得する取引銘柄
     * @@param realType リアル区分
     * @@return 時価情報
     */
    public WEB3EqTypeQuoteData getQuote(
        EqTypeTradedProduct tradedProduct,
        RealType realType);
    
    /**
     * キャッシュされた指数先物・オプションの時価情報を取得する。
     * 銘柄の時価情報が存在しない場合、nullを返す。
     * 
     * @@param tradedProduct 時価情報を取得する取引銘柄
     * @@param realType リアル区分
     * @@return 時価情報
     */
    public WEB3IfoQuoteData getQuote(
        IfoTradedProduct tradedProduct,
        RealType realType);
    
    /**
     * キャッシュされた指数の時価情報を取得する。
     * 
     * @@param tradedProduct 時価情報を取得する取引銘柄
     * @@param realType リアル区分
     * @@return 時価情報
     */
    public WEB3IndexQuoteData get(IndexType indexType, RealType realType);

}@
