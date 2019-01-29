head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceEquityServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������I�l�ۑ��T�[�r�X����(WEB3QuotePriceServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/10 ��(FLJ) �V�K�쐬
 */

package webbroker3.quoteprice.service.delegate.stdimpls;

import java.sql.*;
import java.util.*;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.common.*;
import webbroker3.common.define.*;
import webbroker3.equity.*;
import webbroker3.gentrade.*;
import webbroker3.quoteadaptor.*;
import webbroker3.gentrade.data.*;
import webbroker3.quoteprice.message.*;
import webbroker3.quoteprice.service.delegate.*;
import webbroker3.util.*;

/**
 * �i���������I�l�ۑ��T�[�r�X�����j�B
 * @@version 1.0
 */
public class WEB3QuotePriceEquityServiceImpl
    implements WEB3QuotePriceEquityService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuotePriceEquityServiceImpl.class);

    /**
     * ���R�~�b�g�����ݒ�l�擾�L�[
     */
    private final static String STR_MAX_COMMIT_ROWS_KEY = "quote.price.max.commit.rows";

    /**
     *�f�t�H���g���R�~�b�g����
     */
    private final static int DEFAULT_MAX_COMMIT_ROWS = 200;

    /**
     *�f�t�H���g�،��]���敪
     */
    private final static String DEFAULT_SECURITIES_ESTIMATION_DIV = "2";

    /**
     * ���������I�l�ۑ�
     *
     * @@param l_request WEB3QuotePriceSaveRequest
     * @@throws WEB3BaseException
     * @@return WEB3QuotePriceSaveResult
     */
    public WEB3QuotePriceSaveResult execute(WEB3QuotePriceSaveRequest l_request) throws
        WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3QuotePriceSaveRequest request = (WEB3QuotePriceSaveRequest) l_request;
        WEB3QuotePriceSaveResult l_response = new WEB3QuotePriceSaveResult();
        try
        {
            // 1.1. WEB3QuotePriceRequest.validate()
            request.validate();

            // 1.2 �s��A�����A���������A������������}�X�^����
            Hashtable l_tblProduct = getProducts(request.institutionCd);
            Hashtable l_tblMarketPrioritys = getMarketPrioritys(request.institutionCd);
            Hashtable l_tblEqtypeProduct = getEqtypeProducts(request.institutionCd);
            Hashtable l_tblQuotePrice = getQuotePrices(request.institutionCd,
                request.fromProductCd, request.toProductCd);
            List l_lisSearchResult = getTradedProducts(request.institutionCd,
                request.fromProductCd, request.toProductCd);

            List l_lstInsertRows = new ArrayList();
            Hashtable l_tblTargetRows = new Hashtable();
            Hashtable l_tblMarket = new Hashtable(10);

            //1.3�@@�Ώۃ��R�[�h�쐬
            long l_lngProductId = -1;
            for (int i = 0; i < l_lisSearchResult.size(); i++)
            {
                EqtypeTradedProductParams l_row = (EqtypeTradedProductParams)
                    l_lisSearchResult.get(i);
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
                        saveRows2List(request.institutionCd, l_lngProductId,
                                      l_tblTargetRows,
                                      l_lstInsertRows, l_tblMarket,
                                      l_tblProduct, l_tblEqtypeProduct,
                                      l_tblMarketPrioritys);
                        l_tblTargetRows.clear();
                    }
                }
                WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_tblMarket.get(new
                    Long(l_row.getMarketId()));
                if (l_market == null)
                {
                    l_market = new WEB3GentradeMarket(l_row.
                        getMarketId());
                    l_tblMarket.put(new Long(l_row.getMarketId()), l_market);
                }
                l_tblTargetRows.put(l_market.getMarketCode(), l_row);
                l_lngProductId = l_row.getProductId();
            }

            if (l_tblTargetRows.size() > 0)
            {
                saveRows2List(request.institutionCd, l_lngProductId, l_tblTargetRows,
                              l_lstInsertRows,
                              l_tblMarket,
                              l_tblProduct, l_tblEqtypeProduct, l_tblMarketPrioritys);
                l_tblTargetRows.clear();
            }

            //1.4�@@�Ώۃ��R�[�h�ۑ�
            l_response = new WEB3QuotePriceSaveResult();
            l_response.institutionCd = l_request.institutionCd;
            log.info("try save count=" + l_lstInsertRows.size());
            log.info("save to DB start...");
            save2DB(l_lstInsertRows, l_response);
            l_lstInsertRows.clear();
            log.info("save to DB end.");
        }
        catch (Exception l_e)
        {
            log.error(l_e.getMessage(), l_e);
            throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,this.getClass().getName() + STR_METHOD_NAME,l_e.getMessage());
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;

    }

    /**
     * ������������ꗗ�擾
     *
     * @@param l_strInstitutionCode String
     * @@param l_strFromProductCd Long
     * @@param l_strToProductCd Long
     * @@throws Exception
     * @@return List
     */
    private List getTradedProducts(String l_strInstitutionCode,
                                   Long l_strFromProductCd,
                                   Long l_strToProductCd) throws Exception
    {

        String l_strWhere = " product_id in  (select product_id from eqtype_product where institution_code = ? and product_code >= ? and product_code <= ?) ";
        l_strWhere = l_strWhere + " and valid_until_biz_date= ? and list_flag= ?";

        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.
            getTradingSystem().getSystemTimestamp(), "yyyyMMdd");

        Object[] l_bindVars = new Object[5];
        l_bindVars[0] = l_strInstitutionCode;
        l_bindVars[1] = l_strFromProductCd;
        l_bindVars[2] = l_strToProductCd;
        l_bindVars[3] = l_strBaseDate;
        l_bindVars[4] = BooleanEnum.TRUE;

        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        log.debug("BindVars[2]=" + l_bindVars[2]);
        log.debug("BindVars[3]=" + l_bindVars[3]);
        log.debug("BindVars[4]=" + l_bindVars[4]);

        String l_strOrderBy = " product_id,market_id ";

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            EqtypeTradedProductParams.TYPE,
            l_strWhere.toString(),
            l_strOrderBy,
            null,
            l_bindVars,
            new RowType[]
            {EqtypeProductParams.TYPE});

        return l_lisSearchResult;

    }

    /**
     * �C���T�[�g�ςݎ����I�l�ꗗ�擾
     *
     * @@param l_strInstitutionCode String
     * @@param l_strFromProductCd Long
     * @@param l_strToProductCd Long
     * @@throws Exception
     * @@return Hashtable
     */
    private Hashtable getQuotePrices(String l_strInstitutionCode,
                                     Long l_strFromProductCd,
                                     Long l_strToProductCd) throws Exception
    {

        String l_strQuotePriceWhere =
            "institution_code = ? and product_code >= ? and product_code <= ? and  base_date= ? ";
        String l_strBaseDate = WEB3DateUtility.formatDate(GtlUtils.
            getTradingSystem().getSystemTimestamp(), "yyyyMMdd");
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
        List l_lisQuotePriceSearchResult = l_queryProcessor.doFindAllQuery(
            QuoteClosingPriceRow.TYPE,
            l_strQuotePriceWhere.toString(),
            null,
            null,
            l_bindQuotePriceVars);

        Hashtable l_tblQuotePrice = new Hashtable();
        for (int i = 0; i < l_lisQuotePriceSearchResult.size(); i++)
        {
            QuoteClosingPriceRow l_row = (QuoteClosingPriceRow)
                l_lisQuotePriceSearchResult.get(i);
            l_tblQuotePrice.put(new Long(l_row.getProductId()), l_row);
        }
        return l_tblQuotePrice;
    }

    /**
     * �s��̗D�揇�ʎ擾
     *
     * @@param l_strInstitutionCode String
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
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            MarketMarginRatioRow.TYPE,
            l_strWhere.toString(),
            null,
            null,
            l_bindVars);

        Hashtable l_tblMarketPrioritys = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            MarketMarginRatioRow l_row = (MarketMarginRatioRow)
                l_lisSearchResult.get(i);
            String l_strKey = getKey(l_row);
            l_tblMarketPrioritys.put(l_strKey,
                                     new Double(l_row.getPriority()));
        }
        return l_tblMarketPrioritys;
    }

    /**
     * �s��̗D�揇�ʃL�[���擾
     *
     * @@param l_row MarketMarginRatioRow
     * @@return String
     */
    private String getKey(MarketMarginRatioRow l_row)
    {

        return l_row.getMarketId() + ":" + l_row.getListType() + ":" +
            l_row.getNewListType() + ":" + DEFAULT_SECURITIES_ESTIMATION_DIV;
    }

    /**
     * �s��̗D�揇�ʃL�[���擾
     *
     * @@param l_lngMarketId Long
     * @@param l_row EqtypeTradedProductRow
     * @@return String
     */
    private String getKey(Long l_lngMarketId, EqtypeTradedProductRow l_row)
    {

        return l_lngMarketId + ":" + l_row.getListType() + ":" +
            l_row.getNewListType() + ":" + DEFAULT_SECURITIES_ESTIMATION_DIV;
    }

    /**
     * �����ꗗ���擾
     *
     * @@param l_strInstitutionCode String
     * @@throws Exception
     * @@return Hashtable
     */
    private Hashtable getProducts(String l_strInstitutionCode) throws Exception
    {
        String l_strWhere = "institution_code= ? and product_type = ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = l_strInstitutionCode;
        l_bindVars[1] = ProductTypeEnum.EQUITY;
        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            ProductRow.TYPE,
            l_strWhere.toString(),
            null,
            null,
            l_bindVars);

        Hashtable l_tblProduct = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            ProductRow l_row = (ProductRow)
                l_lisSearchResult.get(i);
            l_tblProduct.put(new Long(l_row.getProductId()), l_row);
        }
        return l_tblProduct;
    }

    /**
     * ���������ꗗ���擾
     *
     * @@param l_strInstitutionCode String
     * @@throws Exception
     * @@return Hashtable
     */
    private Hashtable getEqtypeProducts(String l_strInstitutionCode) throws Exception
    {
        String l_strWhere = "institution_code= ? and product_type = ?";
        Object[] l_bindVars = new Object[2];
        l_bindVars[0] = l_strInstitutionCode;
        l_bindVars[1] = ProductTypeEnum.EQUITY;
        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            EqtypeProductRow.TYPE,
            l_strWhere.toString(),
            null,
            null,
            l_bindVars);

        Hashtable l_tblEqtypeProduct = new Hashtable();
        for (int i = 0; i < l_lisSearchResult.size(); i++)
        {
            EqtypeProductRow l_row = (EqtypeProductRow)
                l_lisSearchResult.get(i);
            l_tblEqtypeProduct.put(new Long(l_row.getProductId()), l_row);
        }
        return l_tblEqtypeProduct;
    }

    /**
     * �����I�l�����擾�A�C���T�[�g���R�[�h�쐬
     *
     * @@param l_strInstitutionCd String
     * @@param l_lngProductId long
     * @@param l_tbl Hashtable
     * @@param insertRows List
     * @@param l_tblMarket Hashtable
     * @@param l_tblProduct Hashtable
     * @@param l_tblEqtypeProduct Hashtable
     * @@param l_tblMarketPrioritys Hashtable
     * @@throws Exception
     */
    private void saveRows2List(String l_strInstitutionCd, long l_lngProductId,
                               Hashtable l_tbl, List insertRows,
                               Hashtable l_tblMarket, Hashtable l_tblProduct,
                               Hashtable l_tblEqtypeProduct,
                               Hashtable l_tblMarketPrioritys) throws
        Exception
    {

        log.debug("product l_lngProductId=" + l_lngProductId);
        log.debug("market size =" + l_tbl.size());

        ProductRow l_prow = (ProductRow) l_tblProduct.get(new Long(l_lngProductId));
        if (l_prow == null)
        {
            log.error("skip inconsistence data!!! product_id=" + l_lngProductId);
            return;
        }
        EqtypeProductRow l_eqprow = (EqtypeProductRow) l_tblEqtypeProduct.get(new Long(
            l_lngProductId));
        EqTypeProduct l_eqp = new WEB3EqTypeProductFakeImpl(l_prow, l_eqprow);
        QuoteClosingPriceParams l_params = new QuoteClosingPriceParams();
        ArrayList l_lstOtherMarkets = new ArrayList();

        Enumeration e = l_tbl.keys();
        while (e.hasMoreElements())
        {
            String l_strMarketCode = (String) e.nextElement();
            EqtypeTradedProductRow l_tpRow = (EqtypeTradedProductRow) l_tbl.get(
                l_strMarketCode);

            Market l_market = null;
            l_market = (WEB3GentradeMarket) l_tblMarket.get(new
                Long(l_tpRow.getMarketId()));
            if (l_market == null)
            {
                l_market = new WEB3GentradeMarket(l_tpRow.getMarketId());
                l_tblMarket.put(new Long(l_tpRow.getMarketId()), l_market);
            }
            l_params.setInstitutionCode(l_strInstitutionCd);
            l_params.setProductCode(l_eqp.getProductCode());
            l_params.setProductId(l_eqp.getProductId());
            l_params.setBaseDate(l_tpRow.getValidUntilBizDate());
            l_params.setProductType(ProductTypeEnum.EQUITY);

            WEB3EqTypeTradedProductFakeImpl l_tp = new WEB3EqTypeTradedProductFakeImpl(
                l_eqp, l_market);
            WEB3EquityProductQuote l_eqQuote = getProductQuote(l_tp);

            if (l_eqQuote != null)
            {
                //����
                if (WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
                {
                    log.debug("update market TOKYO.");
                    l_params.setTokyoClosingPrice(l_eqQuote.getQuote());
                    l_params.setTokyoClosingPriceTime(l_eqQuote.getQuoteTime());
                    l_params.setTokyoClosingPriceType(l_eqQuote.getQuoteTypeDiv());
                }
                //���
                else if (WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
                {
                    log.debug("update market OSAKA.");
                    l_params.setOosakaClosingPrice(l_eqQuote.getQuote());
                    l_params.setOosakaClosingPriceTime(l_eqQuote.getQuoteTime());
                    l_params.setOosakaClosingPriceType(l_eqQuote.getQuoteTypeDiv());
                }
                //����
                else if (WEB3MarketCodeDef.NAGOYA.equals(l_strMarketCode))
                {
                    log.debug("update market NAGOYA.");
                    l_params.setNagoyaClosingPrice(l_eqQuote.getQuote());
                    l_params.setNagoyaClosingPriceTime(l_eqQuote.getQuoteTime());
                    l_params.setNagoyaClosingPriceType(l_eqQuote.getQuoteTypeDiv());
                }
                //���̂�
                else
                {
                    log.debug("update market other :" + l_eqQuote.getMarketCode() + ".");
                    l_lstOtherMarkets.add(l_eqQuote.getMarketCode());
                    if (l_params.getOtherMarketIdIsNull())
                    {
                        l_params.setOtherMarketId(l_tp.getMarket().getMarketId());
                        l_params.setOtherClosingPrice(l_eqQuote.getQuote());
                        l_params.setOtherClosingPriceTime(l_eqQuote.getQuoteTime());
                        l_params.setOtherClosingPriceType(l_eqQuote.getQuoteTypeDiv());
                    }
                    else
                    {
                        String l_strPreKey = getKey(new Long(l_params.getOtherMarketId()),
                            l_tpRow);
                        Double l_dblPrePriority = (Double) l_tblMarketPrioritys.get(
                            l_strPreKey);
                        String l_strNowKey = getKey(new Long(l_tp.getMarket().getMarketId()),
                            l_tpRow);
                        Double l_dblNowPriority = (Double) l_tblMarketPrioritys.get(
                            l_strNowKey);
                        if (l_dblPrePriority != null && l_dblNowPriority != null)
                        {
                            if (l_dblPrePriority.compareTo(l_dblNowPriority) > 0)
                            {
                                l_params.setOtherMarketId(l_tp.getMarket().getMarketId());
                                l_params.setOtherClosingPrice(l_eqQuote.getQuote());
                                l_params.setOtherClosingPriceTime(l_eqQuote.getQuoteTime());
                                l_params.setOtherClosingPriceType(l_eqQuote.
                                    getQuoteTypeDiv());
                            }
                        }
                    }
                }
            }
        }

        //�D��s��
        Market l_primaryMarket = null;
        if (!l_prow.getPrimaryMarketIdIsNull())
        {

            long l_primaryMarketId = l_prow.getPrimaryMarketId();
            l_primaryMarket = (WEB3GentradeMarket) l_tblMarket.get(new
                Long(l_primaryMarketId));
            if (l_primaryMarket == null && !l_prow.getPrimaryMarketIdIsNull())
            {
                l_primaryMarket = new WEB3GentradeMarket(l_primaryMarketId);
                l_tblMarket.put(new Long(l_primaryMarketId), l_primaryMarket);
            }

            if (l_primaryMarket != null)
            {
                WEB3EqTypeTradedProductFakeImpl l_tp = new
                    WEB3EqTypeTradedProductFakeImpl(
                    l_eqp, l_primaryMarket);
                WEB3EquityProductQuote l_eqQuote = getProductQuote(l_tp);
                if (l_eqQuote != null)
                {
                    log.debug("update primary market other :" + l_eqQuote.getMarketCode() +
                              ".");
                    l_params.setPrimaryClosingPrice(l_eqQuote.getQuote());
                    l_params.setPrimaryClosingPriceTime(l_eqQuote.getQuoteTime());
                    l_params.setPrimaryClosingPriceType(l_eqQuote.getQuoteTypeDiv());
                }
                //���̑��s��ɗD��s�ꑶ�݂����ꍇ�A�D��s���D�悵�Ă��̑��s��֐ݒ肷��
                for (int k = 0; k < l_lstOtherMarkets.size(); k++)
                {
                    String l_OtherMarketCode = (String) l_lstOtherMarkets.get(k);
                    if (l_OtherMarketCode.equals(l_primaryMarket.getMarketCode()))
                    {
                        log.debug("update other market with primary.");
                        l_params.setOtherMarketId(l_primaryMarket.getMarketId());
                        l_params.setOtherClosingPrice(l_params.getPrimaryClosingPrice());
                        l_params.setOtherClosingPriceTime(l_params.
                            getPrimaryClosingPriceTime());
                        l_params.setOtherClosingPriceType(l_params.
                            getPrimaryClosingPriceType());
                    }
                }
            }
        }

        Timestamp l_tm = GtlUtils.getSystemTimestamp();
        l_params.setCreatedTimestamp(l_tm);
        l_params.setLastUpdatedTimestamp(l_tm);
        insertRows.add(l_params);
    }

    /**
     * �����I�l���R�[�h��DB�֕ۑ�
     *
     * @@param insertRows List
     * @@param l_response WEB3QuotePriceSaveResult
     * @@throws Exception
     */
    private void save2DB(List insertRows, WEB3QuotePriceSaveResult l_response) throws
        Exception
    {
        String l_strCount = GtlUtils.getTradingSystem().getPreference(
            STR_MAX_COMMIT_ROWS_KEY);
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
     * �����I�l�������T�[�o����擾
     *
     * @@param l_eqtypeTradedProduct EqTypeTradedProduct
     * @@throws Exception
     * @@return WEB3EquityProductQuote
     */
    private WEB3EquityProductQuote getProductQuote(EqTypeTradedProduct
        l_eqtypeTradedProduct) throws Exception
    {
        final String STR_METHOD_NAME =
            "getProductQuote(TradedProductRow)";
        log.entering(STR_METHOD_NAME);

        String l_strMarketCode = l_eqtypeTradedProduct.getMarket().getMarketCode();

        // �����T�[�o���玞�����擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3QuoteDataSupplierService l_quoteDataSupplierService =
            (WEB3QuoteDataSupplierService) l_finApp.getTradingModule(ProductTypeEnum.
            EQUITY).getQuoteDataSupplierService();

        WEB3EqTypeQuoteData l_quoteData = null;
        try
        {
            l_quoteData = l_quoteDataSupplierService.getEqTypeQuote(l_eqtypeTradedProduct,
                RealType.REAL);
        }
        catch (NotFoundException l_exp)
        {
            log.info(" ����ID�F[" + l_eqtypeTradedProduct.getProduct().getProductId()
                     + "] �s��R�[�h�F[" + l_strMarketCode
                     + "�ɑΉ����鎞����񂪌�����܂���B"
                     );
            return null;
        }

        // 1.1. ���ݒl�A���C�z�l�A���C�z�l�̏����ŉ��i�����擾����
        //     �i�l���t���Ă���i�擾�����l > 0�j�̎��_�ŁA�ȍ~�̉��i���͎擾���Ȃ��j�B
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

        WEB3EquityProductQuote l_productQuote = null;
        // 1.2. ���ݒl�A���C�z�l�A���C�z�l�̂����ꂩ���擾�ł����ꍇ�i�擾�����l > 0�̏ꍇ�j
        if (l_dblMarketPrice > 0.0D)
        {
            l_productQuote = new WEB3EquityProductQuote();
            l_productQuote.setQuote(l_dblMarketPrice);
            l_productQuote.setComparedPreviousDay(l_quoteData.getChange());
            l_productQuote.setQuoteTime(l_tsQuoteTime);
            l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
            l_productQuote.setQuoteTypeDiv(l_strQuoteTypeDiv);
            l_productQuote.setMarketCode(l_strMarketCode);
        }
        // 1.3. ���ݒl�A���C�z�l�A���C�z�l�̂����ꂩ���擾�ł��Ȃ��ꍇ
        else
        {
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }

}
@