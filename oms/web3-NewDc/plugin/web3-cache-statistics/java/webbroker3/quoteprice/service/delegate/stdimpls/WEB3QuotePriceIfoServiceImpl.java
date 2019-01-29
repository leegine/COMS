head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceIfoServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OP時価終値保存サービス実装(WEB3QuotePriceIfoServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/05/18 毛(FLJ) 新規作成
 */

package webbroker3.quoteprice.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3QuoteTypeDivDef;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.data.MarketMarginRatioRow;
import webbroker3.gentrade.data.QuoteClosingPriceParams;
import webbroker3.gentrade.data.QuoteClosingPriceRow;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.quoteprice.message.WEB3QuotePriceSaveRequest;
import webbroker3.quoteprice.message.WEB3QuotePriceSaveResult;
import webbroker3.quoteprice.service.delegate.WEB3QuotePriceIfoService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

/**
 * （先物OP時価終値保存サービス実装）。
 * 
 * @@version 1.0
 */
public class WEB3QuotePriceIfoServiceImpl implements WEB3QuotePriceIfoService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3QuotePriceIfoServiceImpl.class);

    /**
     * 一回コミット件数設定値取得キー
     */
    private final static String STR_MAX_COMMIT_ROWS_KEY = "quote.price.max.commit.rows";

    /**
     * デフォルト一回コミット件数
     */
    private final static int DEFAULT_MAX_COMMIT_ROWS = 200;

    /**
     * デフォルト証券評価区分
     */
    private final static String DEFAULT_SECURITIES_ESTIMATION_DIV = "2";

    /**
     * 先物OP時価終値保存
     * 
     * @@param l_request
     *            WEB3QuotePriceSaveRequest
     * @@throws WEB3BaseException
     * @@return WEB3QuotePriceSaveResult
     */
    public WEB3QuotePriceSaveResult execute(WEB3QuotePriceSaveRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3QuotePriceSaveRequest request = (WEB3QuotePriceSaveRequest) l_request;
        WEB3QuotePriceSaveResult l_response = new WEB3QuotePriceSaveResult();
        try
        {
            // 1.1. WEB3QuotePriceRequest.validate()
            request.validate();

            // 1.2 市場、銘柄、先物OP銘柄、先物OP取引銘柄マスタ検索
            Hashtable l_tblProduct = getProducts(request.institutionCd);

            Hashtable l_tblMarketPrioritys = getMarketPrioritys(request.institutionCd);
            Hashtable l_tblIfoProduct = getIfoProducts(request.institutionCd);
            Hashtable l_tblQuotePrice = getQuotePrices(request.institutionCd, request.fromProductCd, request.toProductCd);
            List l_lisSearchResult = getTradedProducts(request.institutionCd, request.fromProductCd, request.toProductCd);

            List l_lstInsertRows = new ArrayList();
            Hashtable l_tblTargetRows = new Hashtable();
            Hashtable l_tblMarket = new Hashtable(10);

            // 1.3 対象レコード作成
            long l_lngProductId = -1;
            for (int i = 0; i < l_lisSearchResult.size(); i++)
            {
                IfoTradedProductParams l_row = (IfoTradedProductParams) l_lisSearchResult.get(i);
                Long l_lngQuotePriceProductId = new Long(l_row.getProductId());
                if (l_tblQuotePrice.get(l_lngQuotePriceProductId) != null)
                {
                    log.info("skip save product:" + l_lngQuotePriceProductId);
                    continue;
                }
                if (l_row.getProductId() != l_lngProductId && l_lngProductId != -1)
                {
                    if (l_tblTargetRows.size() > 0)
                    {
                        saveRows2List(request.institutionCd, l_lngProductId, l_tblTargetRows, l_lstInsertRows, l_tblMarket, l_tblProduct,
                                l_tblIfoProduct, l_tblMarketPrioritys);
                        l_tblTargetRows.clear();
                    }
                }
                WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_tblMarket.get(new Long(l_row.getMarketId()));
                if (l_market == null)
                {
                    l_market = new WEB3GentradeMarket(l_row.getMarketId());
                    l_tblMarket.put(new Long(l_row.getMarketId()), l_market);
                }
                l_tblTargetRows.put(l_market.getMarketCode(), l_row);
                l_lngProductId = l_row.getProductId();
            }

            if (l_tblTargetRows.size() > 0)
            {
                saveRows2List(request.institutionCd, l_lngProductId, l_tblTargetRows, l_lstInsertRows, l_tblMarket, l_tblProduct, l_tblIfoProduct,
                        l_tblMarketPrioritys);
                l_tblTargetRows.clear();
            }

            // 1.4 対象レコード保存
            l_response = new WEB3QuotePriceSaveResult();
            l_response.institutionCd = l_request.institutionCd;
            log.info("try save count=" + l_lstInsertRows.size());
            log.info("save to DB start...");
            save2DB(l_lstInsertRows, l_response);
            l_lstInsertRows.clear();
            log.info("save to DB end.");
        }
        catch (Throwable l_e)
        {
            log.error(l_e.getMessage(), l_e);
            throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,this.getClass().getName() + STR_METHOD_NAME,l_e.getMessage());
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;

    }

    /**
     * 先物OP取引銘柄一覧取得
     * 
     * @@param l_strInstitutionCode
     *            String
     * @@param l_strFromProductCd
     *            Long
     * @@param l_strToProductCd
     *            Long
     * @@throws Exception
     * @@return List
     */
    private List getTradedProducts(String l_strInstitutionCode, Long l_strFromProductCd, Long l_strToProductCd) throws Exception
    {

        String l_strWhere = " product_id in  (select product_id from ifo_product where institution_code = ? and product_code >= ? and product_code <= ?) ";
        l_strWhere = l_strWhere + " and valid_for_biz_date= ? and list_flag= ?";

        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd");

        Object[] l_bindVars = new Object[5];
        l_bindVars[0] = l_strInstitutionCode;
        l_bindVars[1] = l_strFromProductCd;
        l_bindVars[2] = l_strToProductCd;
        l_bindVars[3] = l_strBaseDate;
        l_bindVars[4] = BooleanEnum.TRUE;

        log.info("WHERE=" + l_strWhere + "\nBindVars[0]=" + l_bindVars[0] + "\nBindVars[1]=" + l_bindVars[1] + "\nBindVars[2]=" + l_bindVars[2]
                + "\nBindVars[3]=" + l_bindVars[3] + "\nBindVars[4]=" + l_bindVars[4]);

        String l_strOrderBy = " product_id,market_id ";

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(IfoTradedProductParams.TYPE, l_strWhere.toString(), l_strOrderBy, null, l_bindVars,
                new RowType[] { IfoProductParams.TYPE });

        return l_lisSearchResult;

    }

    /**
     * インサート済み時価終値一覧取得
     * 
     * @@param l_strInstitutionCode
     *            String
     * @@param l_strFromProductCd
     *            Long
     * @@param l_strToProductCd
     *            Long
     * @@throws Exception
     * @@return Hashtable
     */
    private Hashtable getQuotePrices(String l_strInstitutionCode, Long l_strFromProductCd, Long l_strToProductCd) throws Exception
    {

        String l_strQuotePriceWhere = "institution_code = ? and product_code >= ? and product_code <= ? and  base_date= ? ";
        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd");
        Object[] l_bindQuotePriceVars = new Object[4];
        l_bindQuotePriceVars[0] = l_strInstitutionCode;
        l_bindQuotePriceVars[1] = l_strFromProductCd;
        l_bindQuotePriceVars[2] = l_strToProductCd;
        l_bindQuotePriceVars[3] = l_strBaseDate;
        log.debug("WHERE=" + l_strQuotePriceWhere);
        log.debug("BindVars[0]=" + l_bindQuotePriceVars[0]);
        log.debug("BindVars[1]=" + l_bindQuotePriceVars[1]);
        log.debug("BindVars[2]=" + l_bindQuotePriceVars[2]);
        log.debug("BindVars[3]=" + l_bindQuotePriceVars[3]);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisQuotePriceSearchResult = l_queryProcessor.doFindAllQuery(QuoteClosingPriceRow.TYPE, l_strQuotePriceWhere.toString(), null, null,
                l_bindQuotePriceVars);

        Hashtable l_tblQuotePrice = new Hashtable();
        for (int i = 0; i < l_lisQuotePriceSearchResult.size(); i++)
        {
            QuoteClosingPriceRow l_row = (QuoteClosingPriceRow) l_lisQuotePriceSearchResult.get(i);
            l_tblQuotePrice.put(new Long(l_row.getProductId()), l_row);
        }
        return l_tblQuotePrice;
    }

    /**
     * 市場の優先順位取得
     * 
     * @@param l_strInstitutionCode
     *            String
     * @@throws Exception
     * @@return Hashtable
     */
    private Hashtable getMarketPrioritys(String l_strInstitutionCode) throws Exception
    {
        String l_strWhere = " institution_code= ? and securities_estimation_div= ? ";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = l_strInstitutionCode;
        l_bindVars[1] = DEFAULT_SECURITIES_ESTIMATION_DIV;

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(MarketMarginRatioRow.TYPE, l_strWhere.toString(), null, null, l_bindVars);

        Hashtable l_tblMarketPrioritys = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            MarketMarginRatioRow l_row = (MarketMarginRatioRow) l_lisSearchResult.get(i);
            String l_strKey = getKey(l_row);
            l_tblMarketPrioritys.put(l_strKey, new Double(l_row.getPriority()));
        }
        return l_tblMarketPrioritys;
    }

    /**
     * 市場の優先順位キーを取得
     * 
     * @@param l_row
     *            MarketMarginRatioRow
     * @@return String
     */
    private String getKey(MarketMarginRatioRow l_row)
    {

        return l_row.getMarketId() + ":" + l_row.getListType() + ":" + l_row.getNewListType() + ":" + DEFAULT_SECURITIES_ESTIMATION_DIV;
    }

    /**
     * 市場の優先順位キーを取得
     * 
     * @@param l_lngMarketId
     *            Long
     * @@param l_row
     *            IfoTradedProductRow
     * @@return String
     */
    private String getKey(Long l_lngMarketId, IfoTradedProductRow l_row)
    {
        return "";
        // return l_lngMarketId + ":" + l_row.getListType() + ":" +
        // l_row.getNewListType() + ":" + DEFAULT_SECURITIES_ESTIMATION_DIV;
    }

    /**
     * 銘柄一覧を取得
     * 
     * @@param l_strInstitutionCode
     *            String
     * @@throws Exception
     * @@return Hashtable
     */
    private Hashtable getProducts(String l_strInstitutionCode) throws Exception
    {
        String l_strWhere = "institution_code= ? and product_type = ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = l_strInstitutionCode;
        l_bindVars[1] = ProductTypeEnum.IFO;
        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(ProductRow.TYPE, l_strWhere.toString(), null, null, l_bindVars);

        Hashtable l_tblProduct = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            ProductRow l_row = (ProductRow) l_lisSearchResult.get(i);
            l_tblProduct.put(new Long(l_row.getProductId()), l_row);
        }
        return l_tblProduct;
    }

    /**
     * 先物銘柄一覧を取得
     * 
     * @@param l_strInstitutionCode
     *            String
     * @@throws Exception
     * @@return Hashtable
     */
    private Hashtable getIfoProducts(String l_strInstitutionCode) throws Exception
    {
        String l_strWhere = "institution_code= ? and product_type = ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = l_strInstitutionCode;
        l_bindVars[1] = ProductTypeEnum.IFO;
        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(IfoProductRow.TYPE, l_strWhere.toString(), null, null, l_bindVars);

        Hashtable l_tblIfoProduct = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            IfoProductRow l_row = (IfoProductRow) l_lisSearchResult.get(i);
            l_tblIfoProduct.put(new Long(l_row.getProductId()), l_row);
        }
        return l_tblIfoProduct;
    }

    /**
     * 時価終値情報を取得、インサートレコード作成
     * 
     * @@param l_strInstitutionCd
     *            String
     * @@param l_lngProductId
     *            long
     * @@param l_tbl
     *            Hashtable
     * @@param insertRows
     *            List
     * @@param l_tblMarket
     *            Hashtable
     * @@param l_tblProduct
     *            Hashtable
     * @@param l_tblIfoProduct
     *            Hashtable
     * @@param l_tblMarketPrioritys
     *            Hashtable
     * @@throws Exception
     */
    private void saveRows2List(String l_strInstitutionCd, long l_lngProductId, Hashtable l_tbl, List insertRows, Hashtable l_tblMarket,
            Hashtable l_tblProduct, Hashtable l_tblIfoProduct, Hashtable l_tblMarketPrioritys) throws Exception
    {

        log.debug("product l_lngProductId=" + l_lngProductId);
        log.debug("market size =" + l_tbl.size());

        ProductRow l_prow = (ProductRow) l_tblProduct.get(new Long(l_lngProductId));
        if (l_prow == null)
        {
            log.error("skip inconsistence data!!! product_id=" + l_lngProductId);
            return;
        }
        IfoProductRow l_iforow = (IfoProductRow) l_tblIfoProduct.get(new Long(l_lngProductId));
        IfoProduct l_ifo = new WEB3IfoProductFakeImpl(l_prow, l_iforow);
        QuoteClosingPriceParams l_params = new QuoteClosingPriceParams();
        ArrayList l_lstOtherMarkets = new ArrayList();

        Enumeration e = l_tbl.keys();
        while (e.hasMoreElements())
        {
            String l_strMarketCode = (String) e.nextElement();
            IfoTradedProductRow l_tpRow = (IfoTradedProductRow) l_tbl.get(l_strMarketCode);

            Market l_market = null;
            l_market = (WEB3GentradeMarket) l_tblMarket.get(new Long(l_tpRow.getMarketId()));
            if (l_market == null)
            {
                l_market = new WEB3GentradeMarket(l_tpRow.getMarketId());
                l_tblMarket.put(new Long(l_tpRow.getMarketId()), l_market);
            }
            l_params.setInstitutionCode(l_strInstitutionCd);
            l_params.setProductCode(l_ifo.getProductCode());
            l_params.setProductId(l_ifo.getProductId());
            l_params.setBaseDate(l_tpRow.getValidForBizDate());
            l_params.setProductType(ProductTypeEnum.IFO);

            WEB3IfoTradedProductFakeImpl l_tp = new WEB3IfoTradedProductFakeImpl(l_ifo, l_market);
            WEB3IfoProductQuote l_ifoQuote = getProductQuote(l_tp);

            if (l_ifoQuote != null)
            {
                // 東証
                if (WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
                {
                    //
                    // TODO: currently we only use OSE
                    // Test this block before using it.
                    //
                    log.debug("update market TOKYO.");
                    l_params.setTokyoClosingPrice(l_ifoQuote.getCurrentPrice());
                    l_params.setTokyoClosingPriceTime(l_ifoQuote.getCurrentPriceTime());
                    l_params.setTokyoClosingPriceType(l_ifoQuote.getCurrentPriceDiv());
                }
                // 大証
                else if (WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
                {
                    log.debug("update market OSAKA.");
                    l_params.setOosakaClosingPrice(l_ifoQuote.getCurrentPrice());
                    l_params.setOosakaClosingPriceTime(l_ifoQuote.getCurrentPriceTime());
                    l_params.setOosakaClosingPriceType(l_ifoQuote.getCurrentPriceDiv());
                }
                // 名証
                else if (WEB3MarketCodeDef.NAGOYA.equals(l_strMarketCode))
                {
                    //
                    // TODO: currently we only use OSE
                    // Test this block before using it.
                    //
                    log.debug("update market NAGOYA.");
                    l_params.setNagoyaClosingPrice(l_ifoQuote.getCurrentPrice());
                    l_params.setNagoyaClosingPriceTime(l_ifoQuote.getCurrentPriceTime());
                    l_params.setNagoyaClosingPriceType(l_ifoQuote.getCurrentPriceDiv());
                }
                // そのた
                else
                {
                    //
                    // TODO: currently we only use OSE
                    // Test this block before using it.
                    //
                    log.debug("update market other :" + l_ifoQuote.getMarketCode() + ".");
                    l_lstOtherMarkets.add(l_ifoQuote.getMarketCode());
                    if (l_params.getOtherMarketIdIsNull())
                    {
                        l_params.setOtherMarketId(l_tp.getMarket().getMarketId());
                        l_params.setOtherClosingPrice(l_ifoQuote.getCurrentPrice());
                        l_params.setOtherClosingPriceTime(l_ifoQuote.getCurrentPriceTime());
                        l_params.setOtherClosingPriceType(l_ifoQuote.getCurrentPriceDiv());
                    }
                    else
                    {
                        String l_strPreKey = getKey(new Long(l_params.getOtherMarketId()), l_tpRow);
                        Double l_dblPrePriority = (Double) l_tblMarketPrioritys.get(l_strPreKey);
                        String l_strNowKey = getKey(new Long(l_tp.getMarket().getMarketId()), l_tpRow);
                        Double l_dblNowPriority = (Double) l_tblMarketPrioritys.get(l_strNowKey);
                        if (l_dblPrePriority != null && l_dblNowPriority != null)
                        {
                            if (l_dblPrePriority.compareTo(l_dblNowPriority) > 0)
                            {
                                l_params.setOtherMarketId(l_tp.getMarket().getMarketId());
                                l_params.setOtherClosingPrice(l_ifoQuote.getCurrentPrice());
                                l_params.setOtherClosingPriceTime(l_ifoQuote.getCurrentPriceTime());
                                l_params.setOtherClosingPriceType(l_ifoQuote.getCurrentPriceDiv());
                            }
                        }
                    }
                }
            }
        }

        //
        // TODO: currently we only use OSE
        // Test this block before using it.
        //
        // 優先市場
        Market l_primaryMarket = null;
        if (!l_prow.getPrimaryMarketIdIsNull())
        {

            long l_primaryMarketId = l_prow.getPrimaryMarketId();
            l_primaryMarket = (WEB3GentradeMarket) l_tblMarket.get(new Long(l_primaryMarketId));
            if (l_primaryMarket == null && !l_prow.getPrimaryMarketIdIsNull())
            {
                l_primaryMarket = new WEB3GentradeMarket(l_primaryMarketId);
                l_tblMarket.put(new Long(l_primaryMarketId), l_primaryMarket);
            }

            if (l_primaryMarket != null)
            {
                WEB3IfoTradedProductFakeImpl l_tp = new WEB3IfoTradedProductFakeImpl(l_ifo, l_primaryMarket);
                WEB3IfoProductQuote l_ifoQuote = getProductQuote(l_tp);
                if (l_ifoQuote != null)
                {
                    log.debug("update primary market other :" + l_ifoQuote.getMarketCode() + ".");
                    l_params.setPrimaryClosingPrice(l_ifoQuote.getCurrentPrice());
                    l_params.setPrimaryClosingPriceTime(l_ifoQuote.getCurrentPriceTime());
                    l_params.setPrimaryClosingPriceType(l_ifoQuote.getCurrentPriceDiv());
                }
                // その他市場に優先市場存在いた場合、優先市場を優先してその他市場へ設定する
                for (int k = 0; k < l_lstOtherMarkets.size(); k++)
                {
                    String l_OtherMarketCode = (String) l_lstOtherMarkets.get(k);
                    if (l_OtherMarketCode.equals(l_primaryMarket.getMarketCode()))
                    {
                        log.debug("update other market with primary.");
                        l_params.setOtherMarketId(l_primaryMarket.getMarketId());
                        l_params.setOtherClosingPrice(l_params.getPrimaryClosingPrice());
                        l_params.setOtherClosingPriceTime(l_params.getPrimaryClosingPriceTime());
                        l_params.setOtherClosingPriceType(l_params.getPrimaryClosingPriceType());
                    }
                }
            }
        }

        //
        // Notice: we need add one record evenif there are no quote yet.
        // If quote price error occurs then this block will be skipped.
        //
        Timestamp l_tm = GtlUtils.getSystemTimestamp();
        l_params.setCreatedTimestamp(l_tm);
        l_params.setLastUpdatedTimestamp(l_tm);
        insertRows.add(l_params);
    }

    /**
     * 時価終値レコードをDBへ保存
     * 
     * @@param insertRows
     *            List
     * @@param l_response
     *            WEB3QuotePriceSaveResult
     * @@throws Exception
     */
    private void save2DB(List insertRows, WEB3QuotePriceSaveResult l_response) throws Exception
    {
        String l_strCount = GtlUtils.getTradingSystem().getPreference(STR_MAX_COMMIT_ROWS_KEY);
        int l_intMaxCount = DEFAULT_MAX_COMMIT_ROWS;
        if (l_strCount != null)
        {
            try
            {
                l_intMaxCount = Integer.parseInt(l_strCount);
            }
            catch (Exception e)
            {
            }
        }

        ArrayList l_lst = new ArrayList();
        long l_lngSCount = 0;
        long l_lngFCount = 0;
        for (int i = 0; i < insertRows.size(); i++)
        {
            l_lst.add(insertRows.get(i));
            if (l_lst.size() == l_intMaxCount)
            {

                boolean l_blnRet = WEB3QuotePriceDBManager.getInstance().insert(l_lst);
                if (l_blnRet == true)
                {
                    l_lngSCount += l_intMaxCount;
                    log.info("save db success count=" + l_lngSCount);
                }
                l_lst.clear();
            }

        }

        if (l_lst.size() > 0)
        {
            boolean l_blnRet = WEB3QuotePriceDBManager.getInstance().insert(l_lst);
            if (l_blnRet == true)
            {
                l_lngSCount += l_lst.size();
                log.info("save db success count=" + l_lngSCount);
            }
            l_lst.clear();
        }

        log.info("save db all success count=" + l_lngSCount);
        l_lngFCount = insertRows.size() - l_lngSCount;
        log.info("save db all failure count=" + l_lngFCount);
        l_response.success = new Long(l_lngSCount);
        if (l_lngFCount > 0)
        {
            l_response.failure = new Long(l_lngFCount);
        }

    }

    /**
     * 時価終値を時価サーバから取得
     * 
     * @@param l_ifoTradedProduct
     *            IfoTradedProduct
     * @@throws Exception
     * @@return WEB3IfoProductQuote
     */
    private WEB3IfoProductQuote getProductQuote(IfoTradedProduct l_ifoTradedProduct) throws Exception
    {
        final String STR_METHOD_NAME = "getProductQuote(TradedProductRow)";
        log.entering(STR_METHOD_NAME);

        String l_strMarketCode = l_ifoTradedProduct.getMarket().getMarketCode();

        // 時価サーバから時価を取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3QuoteDataSupplierService l_quoteDataSupplierService = (WEB3QuoteDataSupplierService) l_finApp.getTradingModule(ProductTypeEnum.IFO)
                .getQuoteDataSupplierService();

        WEB3IfoQuoteData l_quoteData = null;
        try
        {
            l_quoteData = l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProduct, RealType.REAL);
        }
        catch (NotFoundException l_exp)
        {
            log.info(" 銘柄ID：[" + l_ifoTradedProduct.getProduct().getProductId() + "] 市場コード：[" + l_strMarketCode + "]に対応する時価情報が見つかりません。");
            return null;
        }

        // 1.1. 現在値、売気配値、買気配値の順序で価格情報を取得する
        // （値が付いている（取得した値 > 0）の時点で、以降の価格情報は取得しない）。
        double l_dblMarketPrice = 0.0D;
        Timestamp l_tsQuoteTime = null;
        String l_strQuoteTypeDiv = null;

        // 1.1.1. getCurrentPrice
        l_dblMarketPrice = l_quoteData.getCurrentPrice();
        l_tsQuoteTime = l_quoteData.getCurrentPriceTime();
        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.CURRENT_PRICE;

        // 1.1.2. getBidPriceTime
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getBidPrice();
            l_tsQuoteTime = l_quoteData.getBidPriceTime();
            l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.BID_PRICE;
        }
        // 1.1.3. getAskPrice
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getAskPrice();
            l_tsQuoteTime = l_quoteData.getAskPriceTime();
            l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.ASK_PRICE;
        }

        WEB3IfoProductQuote l_productQuote = null;
        // 1.2. 現在値、売気配値、買気配値のいずれかを取得できた場合（取得した値 > 0の場合）
        if (l_dblMarketPrice > 0.0D)
        {
            l_productQuote = new WEB3IfoProductQuote();
            l_productQuote.setCurrentPrice(l_dblMarketPrice);
            l_productQuote.setComparedPreviousDay(l_quoteData.getChange());
            l_productQuote.setCurrentPriceTime(l_tsQuoteTime);
            // l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
            l_productQuote.setCurrentPriceDiv(l_strQuoteTypeDiv);
            l_productQuote.setMarketCode(l_strMarketCode);
        }
        // 1.3. 現在値、売気配値、買気配値のいずれかを取得できない場合
        else
        {
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }

}
@
