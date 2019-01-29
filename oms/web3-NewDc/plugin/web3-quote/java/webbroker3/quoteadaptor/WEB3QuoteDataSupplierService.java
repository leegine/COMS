head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteDataSupplierService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : 時価サービスインターフェース(WEB3DefaultQuoteDataSupplierServiceImpl.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/02 山田　@卓司(FLJ) 新規作成
                    : 2009/01/28 許　@　@競(FLJ) CSK時価情報チェックの強化対応のため、メソッド追加
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.*;

/**
 * <p>
 * WebBroker3で使用する時価サービスのインターフェース。<br>
 * </p>
 * <p>
 * パラメータに<code>RealType<code>を指定して、時価情報を取得できるようにするため、
 * 各トレーディングモジュールが提供する<code>QuoteDataSupplierService</code>
 * のインターフェースを拡張している。<br>
 * 各トレーディングモジュールに定義済みの取引銘柄のみを指定して、
 * 時価情報を取得するメソッドでは、<code>RealType.REAL<code>の時価情報を取得する。<br>
 * <br>
 * また、日経平均株価など各種指数の時価情報取得インターフェースも提供する。
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 * @@see webbroker3.quoteadaptor.RealType
 */
public interface WEB3QuoteDataSupplierService
    extends EqTypeQuoteDataSupplierService, IfoQuoteDataSupplierService
{

    /**
     * 株式の時価情報を取得する。
     * 
     * @@param tradedProduct 時価情報を取得する株式の取引銘柄。
     * @@param realType リアル区分
     * @@return 株式の時価情報。
     * @@throws NotFoundException
     */
    public WEB3EqTypeQuoteData getEqTypeQuote(
        EqTypeTradedProduct tradedProduct,
        RealType realType)
        throws NotFoundException;

    /**
     * 株価指数先物・オプションの時価情報を取得する。
     * 
     * @@param tradedProduct 時価情報を取得する株化指数先物・オプションの取引銘柄
     * @@param realType リアル区分
     * @@return 株価指数先物・オプションの時価情報
     * @@throws NotFoundException
     */
    public WEB3IfoQuoteData getIfoQuote(
        IfoTradedProduct tradedProduct,
        RealType realType)
        throws NotFoundException;

    /**
     * 抽象的な銘柄情報クラス用のメソッド
     * 
     * @@param tradedProduct 時価情報を取得する銘柄の取引銘柄。
     * @@param realType リアル区分
     * @@return 時価情報
     * @@throws NotFoundException
     */
    public QuoteData getQuote(TradedProduct tradedProduct, RealType realType)
        throws NotFoundException;
    
    /**
     * 時価情報が存在するかどうかチェック用のメソッド<BR>
     * 時価がない場合、nullを返す<BR>
     * 
     * @@param tradedProduct 時価情報を取得する銘柄の取引銘柄。
     * @@param realType リアル区分
     * @@return 時価情報
     * @@throws NotFoundException
     */
    public QuoteData getQuoteForCheck(TradedProduct tradedProduct, RealType realType)
        throws NotFoundException;

    /**
     * 指数の時価情報を取得する。
     * 
     * @@param indexType 指数種類
     * @@param realType リアル区分
     * @@return 指数の時価情報
     * @@throws NotFoundException
     */
    public WEB3IndexQuoteData getIndexQuote(
        IndexType indexType,
        RealType realType)
        throws NotFoundException;

}
@
