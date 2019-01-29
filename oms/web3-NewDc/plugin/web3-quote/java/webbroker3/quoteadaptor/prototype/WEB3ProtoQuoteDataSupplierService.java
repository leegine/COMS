head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3ProtoQuoteDataSupplierService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : WebBroker3プロトタイプ用の時価サービスの実装クラス(WEB3ProtoQuoteDataSupplierService.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/02 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.prototype;

import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.*;

import webbroker3.quoteadaptor.*;
import webbroker3.quoteadaptor.prototype.data.*;
import webbroker3.util.WEB3LogUtility;

/**
 * WebBroker3プロトタイプ用の時価サービスの実装クラス<br>
 * <br>
 * 通常、時価情報を提供するサーバーに接続し、リアルタイムで配信される時価情報を返すが、
 * この実装では、DBのテーブルに登録された時価情報を返す。<br>
 * プロトタイプでサポートする商品の種類は株式のみ。<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3ProtoQuoteDataSupplierService implements WEB3QuoteDataSupplierService
{

    /** ログユーティリティ */
    private static final WEB3LogUtility _log = WEB3LogUtility.getInstance(WEB3ProtoQuoteDataSupplierService.class);

    /** ブランクの時価情報 */
    private static final WEB3ProtoEqTypeQuoteData BLANK_QUOTE_DATA =
        new WEB3ProtoEqTypeQuoteData(new Web3QuoteProtoParams());

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getEqTypeQuote(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct)
     */
    public EqTypeQuoteData getEqTypeQuote(final EqTypeTradedProduct tradedProduct)
        throws NotFoundException
    {
        return getEqTypeQuote(tradedProduct, RealType.REAL);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getEqTypeQuote(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct, webbroker3.quoteadaptor.RealType)
     */
    public WEB3EqTypeQuoteData getEqTypeQuote(
        final EqTypeTradedProduct tradedProduct,
        final RealType realType)
        throws NotFoundException
    {
        QuoteDataQueryParamsSpec spec = new EquityQuoteDataQueryParamsSpec(tradedProduct, realType);
        return (WEB3EqTypeQuoteData) getQuote(spec);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getIfoQuote(com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct)
     */
    public IfoQuoteData getIfoQuote(IfoTradedProduct tradedProduct) throws NotFoundException
    {
        return getIfoQuote(tradedProduct, RealType.REAL);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getIfoQuote(com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct, webbroker3.quoteadaptor.RealType)
     */
    public WEB3IfoQuoteData getIfoQuote(IfoTradedProduct tradedProduct, RealType realType)
        throws NotFoundException
    {
        QuoteDataQueryParamsSpec spec =
            new FutureOptionQuoteDataQueryParamsSpec(tradedProduct, realType);
        return (WEB3IfoQuoteData) getQuote(spec);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getQuote(com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct)
     */
    public QuoteData getQuote(TradedProduct tradedProduct) throws NotFoundException
    {
        return getQuote(tradedProduct, RealType.REAL);
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getQuote(com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct, webbroker3.quoteadaptor.RealType)
     */
    public QuoteData getQuote(TradedProduct tradedProduct, RealType realType)
        throws NotFoundException
    {
        ProductTypeEnum productType = tradedProduct.getProduct().getProductType();
        if (ProductTypeEnum.EQUITY.equals(productType))
        {
            return getEqTypeQuote((EqTypeTradedProduct) tradedProduct, realType);
        } else if (ProductTypeEnum.IFO.equals(productType))
        {
            return getIfoQuote((IfoTradedProduct) tradedProduct, realType);
        } else
        {
            StringBuffer message = new StringBuffer();
            message.append("ProductType[").append(productType).append("]");
            message.append(" is unsupported product type.");
            throw new IllegalArgumentException(message.toString());
        }
    }
    
    public QuoteData getQuoteForCheck(TradedProduct tradedProduct, RealType realType)
        throws NotFoundException
    {
        return null;
    }

    /**
     * @@see webbroker3.quoteadaptor.WEB3QuoteDataSupplierService#getIndexQuote(webbroker3.quoteadaptor.IndexType, webbroker3.quoteadaptor.RealType)
     */
    public WEB3IndexQuoteData getIndexQuote(IndexType indexType, RealType realType)
        throws NotFoundException
    {
        QuoteDataQueryParamsSpec spec = new IndexQuoteDataQueryParamsSpec(realType, indexType);
        return (WEB3IndexQuoteData) getQuote(spec);
    }

    /**
     * 時価テーブルから時価情報を取得する。
     * 時価テーブルに検索対象の時価情報が存在しない場合は、
     * 時価テーブルに新しい時価情報のレコードを挿入する。
     * 
     * @@param spec 時価情報の検索条件
     * @@return 時価情報
     */
    private QuoteData getQuote(final QuoteDataQueryParamsSpec spec)
    {
        try
        {
            return (QuoteData) Processors
                .getDefaultProcessor()
                .doTransaction(QueryProcessor.TX_CREATE_NEW, new TransactionCallback()
            {
                public Object process()
                    throws DataNetworkException, DataQueryException, DataCallbackException
                {

                    if (_log.ison())
                    {
                        _log.debug("QuoteDataQueryParamsSpec : " + spec.toString());
                    }

                    Web3QuoteProtoRow quoteRow = null;
                    List results =
                        Processors.getDefaultProcessor().doFindAllQuery(
                            Web3QuoteProtoRow.TYPE,
                            spec.getQueryString(),
                            spec.getBindVars());

                    if (results.size() > 0)
                    {
                        quoteRow = (Web3QuoteProtoRow) results.get(0);

                        if (_log.ison())
                        {
                            _log.debug("QuoteData found. " + quoteRow);
                        }

                    } else
                    {
                        try
                        {
                            quoteRow = spec.newWeb3QuoteProtoRow();

                            if (_log.ison())
                            {
                                _log.debug(
                                    "QuoteData not found, creating new QuoteData : " + quoteRow);
                            }

                        } catch (DataException de)
                        {
                            new DataCallbackException(de.getMessage(), de);
                        }

                        Processors.getDefaultProcessor().doInsertQuery(quoteRow);

                    }

                    WEB3ProtoEqTypeQuoteData quote = new WEB3ProtoEqTypeQuoteData(quoteRow);
                    if (DataType.INDEX.equals(quote.getDataType()))
                    {
                        quote.indexType = ((IndexQuoteDataQueryParamsSpec) spec).getIndexType();
                    }

                    return quote;
                }

            });
        } catch (DataException de)
        {
            _log.warn("DataException occured.", de);
            return BLANK_QUOTE_DATA;
        }
    }

}
@
