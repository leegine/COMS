head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoProductManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OPプロダクトマネージャ(WEB3IfoProductManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 李強 新規作成
Revesion History : 2007/06/28 張騰宇(中訊) モデル768
Revesion History : 2008/07/25 趙林鵬(中訊) モデル893
*/
package webbroker3.ifo;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.ifo.data.IfoDeliveryMonthMasterRow;
import webbroker3.ifo.data.IfoTickValuesMasterRow;
import webbroker3.ifo.data.IfoLimitPriceRangeMasterRow;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;

/**
 * (先物OPプロダクトマネージャ)<BR>
 * 先物OPプロダクトマネージャクラス<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3IfoProductManagerImpl extends IfoProductManagerImpl
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3IfoProductManagerImpl.class);
    /**
     * @@roseuid 40C0751201B5
     */
    public WEB3IfoProductManagerImpl()
    {

    }

    /**
     * (get銘柄)<BR>
     * （getProductのオーバーロード）<BR>
     * <BR>
     * 先物OP銘柄オブジェクトを取得する。<BR>
     * @@param l_institution - 証券会社オブジェクト<BR>
     * @@param l_strUnderlyingProductCode - (原資産銘柄コード)<BR>
     * @@param l_strMonthOfDelivery - 限月（”YYYYMMDD”）<BR>
     * @@param l_ifoDerivativeType - (先物オプション商品)<BR>
     * @@param l_dblStrikePrice - (行使価格)<BR>
     * @@param l_strSplitType - (分割)<BR>
     * @@param l_strMarketCode - (対象市場コード)<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@roseuid 404D4E7C01B9
     */
    public WEB3IfoProductImpl getIfoProduct(
        Institution l_institution,
        String l_strUnderlyingProductCode,
        String l_strMonthOfDelivery,
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        double l_dblStrikePrice,
        String l_strSplitType,
        String l_strMarketCode)
        throws WEB3BaseException,NotFoundException
    {
        String METHOD_NAME = "getIfoProduct()";
        log.entering(METHOD_NAME);

        if (l_institution == null ||
            l_strUnderlyingProductCode == null ||
            l_strMonthOfDelivery == null ||
            l_ifoDerivativeType ==null ||
            l_strSplitType == null)
        {
            log.error("parameter is null!");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);
        }

        try
        {   /*
            //throw NotFoundException
            IfoProduct l_ifoProduct =  super.getIfoProduct(
                    l_institution,
                    l_strUnderlyingProductCode,
                    l_ifoDerivativeType,
                    l_strMonthOfDelivery,
                    l_dblStrikePrice);

            long l_lngProductId = l_ifoProduct.getProductId();

            log.debug("ProductId = " + l_lngProductId);
            */
            IfoProductRow l_productRow =
                IfoProductDao.findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType(
                l_institution.getInstitutionCode(),
                l_strUnderlyingProductCode,
                l_ifoDerivativeType,
                l_dblStrikePrice,
                l_strMonthOfDelivery,
                l_strSplitType);

            if (l_productRow == null)
            {
                log.exiting(METHOD_NAME);
                throw new NotFoundException("原資産銘柄コード = " + l_strUnderlyingProductCode +
                    "証券会社コード = " + l_institution.getInstitutionCode() + 
                    "限月（”YYYYMMDD”）= " + l_strMonthOfDelivery + 
                    "先物オプション商品= " + l_ifoDerivativeType + 
                    "行使価格 = " + l_dblStrikePrice +
                    "分割 = " + l_strSplitType + "に該当する銘柄を取得できません");
            }

            long l_lngProductId = l_productRow.getProductId();

            log.exiting(METHOD_NAME);
            //throw DataFindException, DataNetworkException, DataQueryException
            return new WEB3IfoProductImpl(l_productRow.getProductId());
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(METHOD_NAME);
            throw new NotFoundException("原資産銘柄コード = " + l_strUnderlyingProductCode +
                "証券会社コード = " + l_institution.getInstitutionCode() + 
                "限月（”YYYYMMDD”）= " + l_strMonthOfDelivery + 
                "先物オプション商品= " + l_ifoDerivativeType + 
                "行使価格 = " + l_dblStrikePrice +
                "分割 = " + l_strSplitType + "に該当する銘柄を取得できません");
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }

    }

    /**
     * (get銘柄)<BR>
     * （getProductのオーバーロード）<BR>
     * <BR>
     * 銘柄コードに該当する銘柄を取得する。<BR>
     * @@param l_institution - 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@roseuid 404D4ECC019A
     */
    public WEB3IfoProductImpl getIfoProduct(
        Institution l_institution,
        String l_strProductCode)
        throws NotFoundException

    {
        String METHOD_NAME = "getIfoProduct()";
        log.entering(METHOD_NAME);

        try
        {
            IfoProductRow l_productRow =
            IfoProductDao.findRowByProductCodeInstitutionCode(
                l_strProductCode,
                l_institution.getInstitutionCode());

            if (l_productRow == null)
            {
                log.exiting(METHOD_NAME);
                throw new NotFoundException("銘柄コード = " + l_strProductCode +
                    "証券会社コード = " + l_institution.getInstitutionCode() + "に該当する銘柄を取得できません");
            }

            long l_lngProductId = l_productRow.getProductId();
            log.debug("ProductId = " + l_lngProductId);

            log.exiting(METHOD_NAME);
            //throw DataFindException, DataNetworkException, DataQueryException
            return new WEB3IfoProductImpl(l_lngProductId);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(METHOD_NAME);
            throw new NotFoundException("銘柄コード = " + l_strProductCode +
                "証券会社コード = " + l_institution.getInstitutionCode() + "に該当する銘柄を取得できません");        
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);
            log.exiting(METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }

    }

    /**
     * (get取引銘柄)<BR>
     * （getTradedProductのオーバーロード）<BR>
     * <BR>
     * 先物OP取引銘柄オブジェクトを取得する。<BR>
     * @@param l_institution - 証券会社オブジェクト<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@param l_strUnderlyingProductCode - (原資産銘柄コード)<BR>
     * @@param l_strMonthOfDelivery - 限月（”YYYYMMDD”）<BR>
     * @@param l_strDerivativeType - (先物オプション商品)<BR>
     * @@param l_dblStrikePrice - 行使価格
     *
     * @@param l_strDivision - (分割)<BR>
     * @@param l_strObjectMarketCode - (対象市場コード)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.WEB3IfoTradedProductImpl
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 404D4B840003
     */
    public WEB3IfoTradedProductImpl getIfoTradedProduct(
        Institution l_institution,
        String l_strMarketCode,
        String l_strUnderlyingProductCode,
        String l_strMonthOfDelivery,
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        double l_dblStrikePrice,
        String l_strSplitType,
        String l_strObjectMarketCode)
        throws WEB3BaseException,NotFoundException
    {
        String METHOD_NAME = "getIfoTradedProduct()";
        log.entering(METHOD_NAME);      
        
        if (l_institution == null || 
            l_strMarketCode == null ||
            l_strUnderlyingProductCode == null || 
            l_strMonthOfDelivery == null ||
            l_ifoDerivativeType == null ||
            l_strSplitType == null )
        {   
            log.error("parameter is null!");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);                 
        }
        
        try
        {   
            //throw NotFoundException            
            IfoTradedProduct l_ifoTradedProduct =  super.getIfoTradedProduct(
                    l_institution,
                    l_strUnderlyingProductCode,            
                    l_ifoDerivativeType,
                    l_strMonthOfDelivery,
                    l_dblStrikePrice,
                    l_strMarketCode);
                  
            long l_lngTradedProductId = l_ifoTradedProduct.getTradedProductId();         
            log.debug("TradedProductId  = " + l_lngTradedProductId);
            
            log.exiting(METHOD_NAME);      
             
            //throw DataFindException, DataNetworkException, DataQueryException   
            return new WEB3IfoTradedProductImpl(l_lngTradedProductId);
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);  
            log.exiting(METHOD_NAME);                             
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }
    }

    /**
     * (get取引銘柄)<BR>
     * （getIfoTradedProduct(Institution, String, IfoDerivativeTypeEnum, String,<BR>
     * 　@double, String)のオーバーライド）<BR>
     * <BR>
     * 先物OP取引銘柄オブジェクトを取得する。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@param l_strUnderlyingProductCode - (原資産銘柄コード)<BR>
     * 原資産銘柄コード
     * @@param l_ifoDerivativeType - (先物オプション商品)<BR>
     * 先物オプション商品
     * @@param l_strMonthOfDelivery - (限月)<BR>
     * 限月（”YYYYMMDD”）
     * @@param l_dblStrikePrice - (行使価格)<BR>
     * 行使価格
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード
     * @@return IfoTradedProduct
     * @@throws NotFoundException
     */
    public IfoTradedProduct getIfoTradedProduct(
        Institution l_institution,
        String l_strUnderlyingProductCode,
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        String l_strMonthOfDelivery,
        double l_dblStrikePrice,
        String l_strMarketCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getIfoTradedProduct(Institution, String, IfoDerivativeTypeEnum," +
            "String, double, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            IfoTradedProduct l_ifoTradedProduct = super.getIfoTradedProduct(
                l_institution,
                l_strUnderlyingProductCode,
                l_ifoDerivativeType,
                l_strMonthOfDelivery,
                l_dblStrikePrice,
                l_strMarketCode);

            long l_lngTradedProductId = l_ifoTradedProduct.getTradedProductId();
            log.debug("TradedProductId  = " + l_lngTradedProductId);

            log.exiting(STR_METHOD_NAME);
            return new WEB3IfoTradedProductImpl(l_lngTradedProductId);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (get取引銘柄)<BR>
     * （getTradedProductのオーバーロード）<BR>
     * <BR>
     * 銘柄コードに該当する先物OP取引銘柄オブジェクトを取得する。
     * @@param l_institution - 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 4059609C0119
     */
    public WEB3IfoTradedProductImpl getIfoTradedProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strMarketCode)
        throws NotFoundException
    {
        String METHOD_NAME = "getIfoTradedProduct()";
        log.entering(METHOD_NAME);      
        
        try
        {                       
            Product l_product = this.getIfoProduct(l_institution, l_strProductCode);
            log.debug("ProductId = " + l_product.getProductId());
            
            Market l_market = 
                GtlUtils.getFinObjectManager().getMarket(l_institution, l_strMarketCode);
            log.debug("MarketId = " + l_market.getMarketId());
                    
            IfoTradedProduct ifoTradedProduct = (IfoTradedProduct)super.getTradedProduct(l_product, l_market);
                   
            long l_lngTradedProductId = ifoTradedProduct.getTradedProductId();
            log.debug("TradedProductId  = " + l_lngTradedProductId);
            
            log.exiting(METHOD_NAME);     
            //throw DataFindException, DataNetworkException, DataQueryException   
            return new WEB3IfoTradedProductImpl(l_lngTradedProductId);
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);  
            log.exiting(METHOD_NAME);                             
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }

    }

    /**
     * (get刻み値)<BR>
     * <BR>
     * 呼値テーブルから、該当する刻み値を取得する。<BR>
     * <BR>
     * １）　@対象行取得<BR>
     * 　@－先物オプション呼値テーブルを以下の条件で読む<BR>
     * 　@[条件]<BR>
     * 　@先物オプション呼値テーブル.原資産銘柄コード = 先物OP銘柄.原資産銘柄コード<BR>
     * 　@先物オプション呼値テーブル.先物／オプション区分 = 先物OP銘柄.先物／オプション区分<BR>
     * <BR>
     * 　@－条件に一致する行より、<BR>
     * 　@（先物オプション呼値テーブル.下限値　@< 引数.単価 <= 先物オプション呼値テーブル.上限値）<BR>
     * 　@に該当する行の行オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@刻み値返却<BR>
     * 　@取得した行.刻み値を返却する。<BR>
     * @@param l_futuresOptionProduct - 先物OP銘柄オブジェクト
     * @@param l_dblUnitPrice - (単価)<BR>
     * @@return double
     * @@roseuid 4067B6F901DE
     */
    public double getTickValue(
        WEB3IfoProductImpl l_futuresOptionProduct,
        double l_dblUnitPrice) throws WEB3BaseException
    {
        String METHOD_NAME =
              "getTickValue(WEB3IfoProductImpl,double)";

        log.entering(METHOD_NAME);

        if (l_futuresOptionProduct == null)
        {
            log.error("parameter is null type");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);
        }

        String l_strUnderlyingProductCode =
            l_futuresOptionProduct.getUnderlyingProductCode();
        String l_strFutureOptionDiv =
            ((IfoProductRow) l_futuresOptionProduct.getDataSourceObject()).getFutureOptionDiv();

        log.debug("l_strUnderlyingProductCode =" + l_strUnderlyingProductCode);
        log.debug("l_strFutureOptionDiv =" + l_strFutureOptionDiv);

        QueryProcessor l_queryProcesser = null;
        double l_dblTickValue = 0;
        List l_lisRows = null;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            String l_strWhere =
                  "target_product_code = ? and "
                     + "future_option_div = ? and "
                     + "(low_price < ?) and (? <= cap_price)";

            Object l_bindVars[] =
            {   new String(l_strUnderlyingProductCode),
                new String(l_strFutureOptionDiv),
                new Double(l_dblUnitPrice),
                new Double(l_dblUnitPrice)};
            //throw DataFindException, DataNetworkException, DataQueryException
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                        IfoTickValuesMasterRow.TYPE,
                        l_strWhere,
                        l_bindVars);
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        if (l_lisRows.size() == 0)
        {
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME);
        }

        log.debug("Rows count : " + l_lisRows.size());

        l_dblTickValue =
                ((IfoTickValuesMasterRow) l_lisRows.get(0)).getTickValue();

        log.exiting(METHOD_NAME);
        return l_dblTickValue;
    }

    /**
     * (get制限値幅)<BR>
     * <BR>
     * 値幅テーブルから、該当する制限値幅を取得する。<BR>
     * <BR>
     * １）　@対象行取得<BR>
     * 　@－先物オプション値幅テーブルを以下の条件で読む<BR>
     * 　@[条件]<BR>
     * 　@先物オプション値幅テーブル.原資産銘柄コード = 先物OP銘柄.原資産銘柄コード<BR>
     * 　@先物オプション値幅テーブル.先物／オプション区分 = 先物OP銘柄.先物／オプション区分<BR>
     * <BR>
     * 　@－条件に一致する行より、<BR>
     * 　@（先物オプション値幅テーブル.下限値　@<= 引数.単価 < 先物オプション値幅テーブル.上限値）<BR>
     * 　@に該当する行の行オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@制限値幅返却<BR>
     * 　@取得した行.値幅を返却する。<BR>
     * @@param l_futuresOptionProduct - 先物OP銘柄オブジェクト
     * @@param l_dblUnitPrice - 単価
     * @@return double
     * @@roseuid 4067BECC01EE
     */
    public double getDeregulatedPriceRange(
        WEB3IfoProductImpl l_futuresOptionProduct,
        double l_dblUnitPrice) throws WEB3BaseException
    {
        String METHOD_NAME =
            "getDeregulatedPriceRange(WEB3IfoProductImpl,double)";

        log.entering(METHOD_NAME);

        if (l_futuresOptionProduct == null)
        {
            log.error("parameter is null type");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);
        }

        String l_strUnderlyingProductCode =
            l_futuresOptionProduct.getUnderlyingProductCode();
        String l_strFutureOptionDiv =
            ((IfoProductRow) l_futuresOptionProduct.getDataSourceObject()).getFutureOptionDiv();

        log.debug("l_strUnderlyingProductCode =" + l_strUnderlyingProductCode);
        log.debug("l_strFutureOptionDiv =" + l_strFutureOptionDiv);

        QueryProcessor l_queryProcesser = null;
        double l_dblPriceRange = 0;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            String l_strWhere =
                    "target_product_code = ? and " +
                    "future_option_div = ? and " +
                    "(low_price <= ?) and (? < cap_price)";

            Object l_bindVars[] =
                {   new String(l_strUnderlyingProductCode),
                    new String(l_strFutureOptionDiv),
                    new Double(l_dblUnitPrice),
                    new Double(l_dblUnitPrice)};
            //throw DataFindException, DataNetworkException, DataQueryException
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    IfoLimitPriceRangeMasterRow.TYPE,
                    l_strWhere,
                    l_bindVars);

            if (l_lisRows.size() == 0)
            {
               throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            l_dblPriceRange =
                ((IfoLimitPriceRangeMasterRow) l_lisRows.get(0)).getRange();
        }
        catch (DataException de)
        {
            log.error(de.getMessage(),de);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                de.getMessage(),
                de);
        }
        catch (WEB3BaseException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.toString(),
                l_nfe);
        }

        log.exiting(METHOD_NAME);
        return l_dblPriceRange;
    }

    /**
     * (get時価)<BR>
     * <BR>
     * 時価を取得する。<BR>
     * <BR>
     * 手続きについては、<BR>
     * 「基本設計計算式書(株価指数OP）.doc」　@5.時価取得<BR>
     * 参照。<BR>
     * @@param l_futuresOptionTradedProduct - 先物OP取引銘柄オブジェクト
     * @@return double
     * @@roseuid 4072A7270232
     */
    public double getCurrentPrice(WEB3IfoTradedProductImpl l_futuresOptionTradedProduct)
        throws WEB3BaseException
    {
        String METHOD_NAME =
                "getCurrentPrice(WEB3IfoTradedProductImpl)";

        log.entering(METHOD_NAME);

        if (l_futuresOptionTradedProduct == null)
        {
            log.error("parameter is null type");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);
        }

        double l_lngPrice = 0;
        try
        {
            // TradingModuleからQuoteDataSupplierServiceを取得
            //throw NotInstalledException
            FinApp finApp = (FinApp) Services.getService(FinApp.class);
            WEB3QuoteDataSupplierService  l_quoteSupplier =
                (WEB3QuoteDataSupplierService ) finApp
                   .getTradingModule(ProductTypeEnum.IFO)
                   .getQuoteDataSupplierService();
            // TradedProductを指定して時価情報を取得
            //throw NotFoundException
            WEB3IfoQuoteData l_ifoQuoteData =
                (WEB3IfoQuoteData) l_quoteSupplier.getIfoQuote(l_futuresOptionTradedProduct);

            log.debug("l_ifoQuoteData =" + l_ifoQuoteData);
            // 時価の取得
            //時価サービス（QuoteDataSupplierService）より、時価情報（IfoQuoteData）を取得する。
            //以下の優先順位で、取得できる（0でない）単価を時価として返却する。
            //1.  現在値（IfoQuoteData.getCurrentPrice()）
            //2.  売気配値（IfoQuoteData.getBidPrice()）
            //3.  買気配値（IfoQuoteData.getAskPrice()）
            //4.  先物OP取引銘柄.基準値（終値）

            l_lngPrice = l_ifoQuoteData.getCurrentPrice();
            log.debug("getCurrentPrice() = " + l_lngPrice);

            if (l_lngPrice == 0)
            {
                l_lngPrice = l_ifoQuoteData.getBidPrice();
                log.debug("getBidPrice() = " + l_lngPrice);

                if (l_lngPrice == 0)
                {
                    l_lngPrice = l_ifoQuoteData.getAskPrice();
                    log.debug("getAskPrice() = " + l_lngPrice);

                    if ((l_lngPrice == 0)||(Double.isNaN(l_lngPrice)))
                    {
                        l_lngPrice = ((IfoTradedProductRow) l_futuresOptionTradedProduct
                        .getDataSourceObject()).getLastClosingPrice();
                        log.debug("getLastClosingPrice() = " + l_lngPrice);

						if((l_lngPrice == 0)||(Double.isNaN(l_lngPrice)))
						{
							throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_01997, 
							this.getClass().getName() + METHOD_NAME,
							"指定の銘柄は成行注文に必要な市場時価がないため、指値注文のみ受付可能です。");
						}
                    }
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (NotInstalledException l_nie)
        {
            log.error(l_nie.getMessage(), l_nie);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nie.toString(),
                l_nie);
        }

        log.exiting(METHOD_NAME);
        return l_lngPrice;

    }

    /**
     * (get限月)<BR>
     * 先物OP限月マスタテーブルから、該当する限月をリストで取得する。<BR>
     * １）先物OP限月マスタテーブルを以下の条件で検索する<BR>
     * 　@[条件]<BR>
     * 　@先物OP限月マスタテーブル.原資産銘柄コード = 引数.原資産銘柄コード<BR>
     * 　@先物OP限月マスタテーブル.先物／オプション区分 = 引数.先物／オプション区分<BR>
     * <BR>
     * ２）取得結果の限月をリストで返却する<BR>
     * ※該当するデータが存在しない場合には「データ不整合エラー」の例外をスローする。<BR>
     *      class:   WEB3SystemLayerException<BR>
     *        tag:   SYSTEM_ERROR_80006<BR>
     * <BR>
     * @@param l_strUnderlyingProductCode - (原資産銘柄コード)<BR>
     * 原資産銘柄コード<BR>
     * @@param l_strFutureOptionDiv - (先物/オプション区分)<BR>
     * 先物/オプション区分<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getMonthOfDelivery(String l_strUnderlyingProductCode, String l_strFutureOptionDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMonthOfDelivery(String, String)";
        log.entering(STR_METHOD_NAME);

        //１）先物OP限月マスタテーブルを以下の条件で検索する
        List l_lisRows = new ArrayList();

        //先物OP限月マスタテーブル.原資産銘柄コード = 引数.原資産銘柄コード
        //先物OP限月マスタテーブル.先物／オプション区分 = 引数.先物／オプション区分
        String l_strWhere = " underlying_product_code = ? and future_option_div = ? ";
        Object[] l_bindVars = {l_strUnderlyingProductCode, l_strFutureOptionDiv};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRows = l_queryProcessor.doFindAllQuery(
                IfoDeliveryMonthMasterRow.TYPE,
                l_strWhere,
                l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //該当するデータが存在しない場合には「データ不整合エラー」の例外をスローする。
        if (l_lisRows == null || l_lisRows.isEmpty())
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        //２）取得結果の限月をリストで返却する
        List l_lisDeliveryMonths = new ArrayList();
        int l_intLength = l_lisRows.size();
        for (int i = 0; i < l_intLength; i++)
        {
            IfoDeliveryMonthMasterRow l_ifoDeliveryMonthMasterRow = (IfoDeliveryMonthMasterRow)l_lisRows.get(i);
            l_lisDeliveryMonths.add(l_ifoDeliveryMonthMasterRow.getDeliveryMonth());
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisDeliveryMonths;
    }

    /**
     * (get限月一覧)<BR>
     * 取扱可能な原資産銘柄コードと先物/オプション区分に一致する限月の一覧を取得する。<BR>
     * ※取得した全ての限月で重複しない値をセット。<BR>
     * <BR>
     * １）戻り値Listのインスタンスを生成する。<BR>
     * ２）引数の(部店指数別)取扱条件オブジェクト分、以下の処理を行う。<BR>
     * <BR>
     * 　@２－１）先物OPプロダクトマネージャ.get限月()メソッドのコールにより、<BR>
     * 　@　@　@　@　@限月のListを取得する。<BR>
     * 　@[引数]<BR>
     * 　@　@原資産銘柄コード 　@　@：パラメータ.(部店指数別)取扱条件の要素.原資産銘柄コード<BR>
     * 　@　@先物／オプション区分 ：パラメータ.先物／オプション区分<BR>
     * <BR>
     * 　@２－２）　@２－１）で取得した限月のList数分、<BR>
     * 　@　@　@　@　@　@１）で生成した戻り値Listに、２－１）で取得した限月を追加する。<BR>
     * 　@　@　@　@　@　@※同じ限月が重複して含まれる場合は、重複分を除く。<BR>
     * <BR>
     * ３）戻り値Listの限月一覧を返却する。<BR>
     * <BR>
     * @@param l_gentradeBranchIndexDealtConds - (（部店指数別）取扱条件[])<BR>
     * （部店指数別）取扱条件<BR>
     * @@param l_strFutureOptionDiv - (先物／オプション区分)<BR>
     * 先物／オプション区分<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDeliveryMonthList(
        WEB3GentradeBranchIndexDealtCond[] l_gentradeBranchIndexDealtConds,
        String l_strFutureOptionDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryMonthList(WEB3GentradeBranchIndexDealtCond[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_gentradeBranchIndexDealtConds == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //１）戻り値Listのインスタンスを生成する。
        List l_lisDeliveryMonths = new ArrayList();

        //２）引数の(部店指数別)取扱条件オブジェクト分、以下の処理を行う。
        int l_intListLength = l_gentradeBranchIndexDealtConds.length;
        for (int i = 0; i < l_intListLength; i++)
        {
            //２－１）this.get限月()メソッドのコールにより、限月のListを取得する。
            List l_lisMonthOfDeliverys =
                this.getMonthOfDelivery(
                    l_gentradeBranchIndexDealtConds[i].getUnderlyingProductCode(),
                    l_strFutureOptionDiv);

            //２－２）　@２－１）で取得した限月のList数分、
            //１）で生成した戻り値Listに、２－１）で取得した限月を追加する。
            //※同じ限月が重複して含まれる場合は、重複分を除く。
            int l_intLength = l_lisMonthOfDeliverys.size();
            for (int j = 0; j < l_intLength; j++)
            {
                //重複しない限月を取得
                if (!l_lisDeliveryMonths.contains(l_lisMonthOfDeliverys.get(j)))
                {
                    l_lisDeliveryMonths.add(l_lisMonthOfDeliverys.get(j));
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        //３）戻り値Listの限月一覧を返却する。
        return l_lisDeliveryMonths;
    }

    public Product toProduct(Row l_row)
    {
        ProductRow l_prow = (ProductRow) l_row;
        try
        {
            return new WEB3IfoProductImpl(l_prow);
        }
        catch(DataException l_dex)
        {
            String msg = "Exception when creating IfoProduct for product id: " + l_prow.getProductId();
            log.error(msg, l_dex);
            throw new RuntimeSystemException(msg, l_dex);
        }

    }

    public TradedProduct toTradedProduct(Row l_row)
    {

        TradedProductRow l_tprow = (TradedProductRow) l_row;
        try
        {
            return new WEB3IfoTradedProductImpl(l_tprow);
        }
        catch(DataException l_dex)
        {
            String msg = "Exception when creating IfoTradedProduct for traded product id: " + l_tprow.getTradedProductId();
            log.error(msg, l_dex);
            throw new RuntimeSystemException(msg, l_dex);
        }
    }

    public IfoProduct getIfoProduct(
        Institution l_inst,
        String l_strUnderlyingProductCode,
        IfoDerivativeTypeEnum l_derivativeType,
        String l_strMonthOfDelivery,
        double l_dblStrikePrice) throws NotFoundException
    {
        try
        {

            IfoProductRow l_mRow =
            IfoProductDao.findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType
                (l_inst.getInstitutionCode(),
                l_strUnderlyingProductCode,
                l_derivativeType,
                l_dblStrikePrice,
                l_strMonthOfDelivery,
                "000");
            if(l_mRow == null)
            {
                String l_strMsg = "No IfoProduct found with institutionCode," +
                " underlyingProductCode, derivativeType, monthOfDelivery and strikePrice : " +
                l_inst.getInstitutionCode() + "," + l_strUnderlyingProductCode + "," + l_derivativeType + "," +
                l_strMonthOfDelivery + "," + l_dblStrikePrice;
                throw new NotFoundException(l_strMsg);
            }

            return new IfoProductImpl(l_mRow);
        }
        catch(DataFindException l_dfe)
        {
            String l_strMmsg = "No IfoProduct found with institutionCode, " +
            "underlyingProductCode, derivativeType, monthOfDelivery and strikePrice : " +
            l_inst.getInstitutionCode() + "," + l_strUnderlyingProductCode + "," + l_derivativeType + "," +
            l_strMonthOfDelivery + "," + l_dblStrikePrice;
            log.warn(l_strMmsg, l_dfe);
            throw new NotFoundException(l_strMmsg);
        }
        catch(DataException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            throw new RuntimeSystemException(
                "System exception while getting IfoProduct with institutionCode, underlyingProductCode, " +
                "derivativeType, monthOfDelivery and strikePrice : " + l_inst.getInstitutionCode() + "," +
                l_strUnderlyingProductCode + "," + l_derivativeType + "," + l_strMonthOfDelivery + "," + l_dblStrikePrice);
        }
    }
}
@
