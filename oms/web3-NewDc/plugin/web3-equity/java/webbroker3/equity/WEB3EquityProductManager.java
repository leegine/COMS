head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張プロダクトマネージャ(WEB3EquityProductManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/20 髙橋　@良和(SRA) 新規作成
Revesion History : 2006/07/04 周捷 (中訊) 仕様変更管理No.935
Revesion History : 2006/07/19 周捷 (中訊) 仕様変更管理No.956
Revesion History : 2007/02/10 趙林鵬(中訊) モデル No.1122
Revesion History : 2007/11/12 張騰宇(中訊) モデル No.1203
Revesion History : 2007/11/19 張騰宇(中訊) モデル No.1214
Revesion History : 2007/11/22 張騰宇(中訊) モデル No.1223
Revesion History : 2009/09/21 車進  (中訊) モデル No.1336 No.1337 No.1340 No.1342 No.1346 No.1347 計算式書 No.035 No.039
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.CashProductImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.TradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProcessIdDef;
import webbroker3.common.define.WEB3QuoteFromDivDef;
import webbroker3.common.define.WEB3QuoteTypeDivDef;
import webbroker3.equity.data.EquityLimitPriceRangeMstRow;
import webbroker3.equity.data.EquityTickValuesMstRow;
import webbroker3.equity.data.OffFloorOrderProductParams;
import webbroker3.equity.data.OffFloorOrderProductRow;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.LastClosingPriceParams;
import webbroker3.gentrade.data.LastClosingPriceRow;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3EqTypeQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （拡張プロダクトマネージャ）。<BR>
 * <BR>
 * 商品に対する操作を表現します。<BR>
 * xTradeのEqTypeProductManagerを拡張したクラス。
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public class WEB3EquityProductManager extends EqTypeProductManagerImpl
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityProductManager.class);

    /**
     * @@roseuid 4042EC8902B7
     */
    public WEB3EquityProductManager()
    {

    }

    /**
     * (非 Javadoc)
     * @@see 
     * com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager#getProduct(Institution, String)
     * @@param l_institution
     * @@param l_strProductCode
     * @@return EqTypeProduct
     * @@throws NotFoundException
     * @@roseuid 4042EC8902C4
     */
    public EqTypeProduct getProduct(
        Institution l_institution,
        String l_strProductCode)
        throws NotFoundException
    {
        try
        {
            EqtypeProductRow l_eqtypeProductRow =
                EqtypeProductDao.findRowByInstitutionCodeProductCode(
                    l_institution.getInstitutionCode(),
                    l_strProductCode);
            if (l_eqtypeProductRow == null)
                throw new NotFoundException(
                    "No product found with institution_code,product_code : "
                        + l_institution.getInstitutionCode()
                        + ","
                        + l_strProductCode);
            try
            {
                return new WEB3EquityProduct(l_eqtypeProductRow);
            }
            catch (DataFindException dfe)
            {
                log.error(dfe.getMessage(), dfe);
                throw new NotFoundException(
                    "No product found with institutionCode, productCode : "
                        + l_institution.getInstitutionCode()
                        + ","
                        + l_strProductCode);
            }
            catch (DataException de)
            {
                log.error(de.getMessage(), de);
                throw new RuntimeSystemException(
                    "System exception while getting product with "
                        + "institutionCode, productCode : "
                        + l_institution.getInstitutionCode()
                        + ","
                        + l_strProductCode);
            }
        }
        catch (DataException de)
        {
            String msg =
                "Exception while fetching eqtypeProduct table with "
                    + "product code, institution code :"
                    + l_strProductCode
                    + ","
                    + l_institution.getInstitutionCode();
            log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
    }

    /**
     * (非 Javadoc)
     * @@see 
     * ProductManager#toProduct(com.fitechlabs.dbind.Row)<BR>
     * @@param l_row
     * @@return Product
     * @@roseuid 4042EC8A0341
     */
    public Product toProduct(Row l_row)
    {
        String STR_METHOD_NAME = "toProduct(Row)";
        if ((l_row instanceof ProductRow) == false)
        {
            throw new IllegalArgumentException(
                "Expected ProductRow. But the given Row is the type of: "
                    + l_row.getClass());
        }
        ProductRow l_productRow = (ProductRow)l_row;
        TradingModule l_tradingModule =
            GtlUtils.getTradingModule(l_productRow.getProductType());
        if (l_tradingModule instanceof TradingModuleImpl)
        {
            try
            {
                ProductTypeEnum l_productType = l_productRow.getProductType();
                if (ProductTypeEnum.CASH.equals(l_productType))
                {
                    return new CashProductImpl(l_productRow);
                }
                else
                {
                    return new WEB3EquityProduct(l_productRow);
                }
            }
            catch (DataFindException l_dfe)
            {
                String msg =
                    "Cannot generate EqTypeProduct with "
                        + "product type, product id: "
                        + l_productRow.getProductType().stringValue()
                        + ","
                        + l_productRow.getProductId();
                        
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    msg, l_dfe
                );
            }
            catch (DataNetworkException l_dne)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne
                );
            }
            catch (DataQueryException l_dqe)
            {   
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe
                );
            }
        }
        else
        {
            return l_tradingModule.getProductManager().toProduct(l_productRow);
        }
    }

    /**
     * (非 Javadoc)
     * @@see 
     * ProductManager#toTradedProduct(com.fitechlabs.dbind.Row)<BR>
     * @@param l_row
     * @@return TradedProduct
     * @@roseuid 4042EC8A0380
     */
    public TradedProduct toTradedProduct(Row l_row)
    {
        String STR_METHOD_NAME = "toTradedProduct(Row)";
        if ((l_row instanceof TradedProductRow) == false)
        {
            throw new IllegalArgumentException(
                "Expected TradedProductRow. "
                    + "But the given Row is the type of: "
                    + l_row.getClass());
        }
        TradedProductRow l_tradedProductRow = (TradedProductRow)l_row;
        TradingModule l_tradingModule =
            GtlUtils.getTradingModule(l_tradedProductRow.getProductType());
        if (l_tradingModule instanceof TradingModuleImpl)
        {
            try
            {
                return new WEB3EquityTradedProduct(l_tradedProductRow);
            }
            catch (DataFindException l_dfe)
            {
                String msg = 
                    "Cannot generate EqTypeTradedProduct with "
                    + "product type, traded product id: "
                    + l_tradedProductRow.getProductType()
                    + ","
                    + l_tradedProductRow.getTradedProductId();
                    
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    msg, l_dfe
                );
            }
            catch (DataNetworkException l_dne)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne
                );
            }
            catch (DataQueryException l_dqe)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe
                );
            }
        }
        else
        {
            return l_tradingModule.getProductManager().toTradedProduct(
                l_tradedProductRow);
        }
    }

    /**
     * (非 Javadoc)
     * @@see 
     * EqTypeProductManager#searchProductsBeginningWith(com.fitechlabs.xtrade.plugin.tc.gentrade.Institution, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, java.lang.String, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     * @@param l_institution
     * @@param l_productType
     * @@param l_strNamePrefix
     * @@param l_pageSpec
     * @@param l_sortSpec
     * @@return java.util.List
     * @@roseuid 4050212203D7
     */
    public List searchProductsBeginningWith(
        Institution l_institution,
        ProductTypeEnum l_productType,
        String l_strNamePrefix,
        PaginationQueryParamsSpec l_pageSpec,
        SortKeySpec l_sortSpec)
    {
        if (l_strNamePrefix == null)
        {
            throw new IllegalArgumentException("namePrefix should not be null");
        }
        try
        {
            String l_strWhere =
                "institution_code = ? and (product_type = ?) and ( "
                    + "(standard_name like ?) or (name_alt1 like ?) or "
                    + "(name_alt2 like ?) )";
            String l_strPrefixedSearchString = l_strNamePrefix + "%";
            Object l_bindVars[] =
                {
                    l_institution.getInstitutionCode(),
                    new Long(l_productType.intValue()),
                    l_strPrefixedSearchString,
                    l_strPrefixedSearchString,
                    l_strPrefixedSearchString };
            String l_strOrderBy = null;
            if (l_sortSpec != null && (l_sortSpec.isSortKeyNull() == false))
            {
                l_strOrderBy = l_sortSpec.getSortKeySpec();
            }
            int l_intPageSize = l_pageSpec.getPageSize();
            int l_intPageNumber = l_pageSpec.getPageNumber();
            List l_lisRows =
                Processors
                    .getDefaultProcessor()
                    .doFindAllQuery(ProductRow.TYPE,
                //"institution_code = ? and (product_type = ?) and "
        //    + "( (standard_name like ?) or (name_alt1 like ?) or "
        //    + "(name_alt2 like ?) )",
    l_strWhere, l_strOrderBy, null, l_bindVars, l_intPageSize, l_intPageNumber);
            return toProductList(l_lisRows);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new RuntimeSystemException(
                "System exception while searching product with namePrefix :"
                    + l_strNamePrefix,
                de);
        }
    }

    /**
     * (非 Javadoc)
     * @@see 
     * com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager#searchProducts(com.f
     * itechlabs.xtrade.plugin.tc.gentrade.Institution, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, java.lang.String, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     * @@param l_institution
     * @@param l_productType
     * @@param l_strNameSubString
     * @@param l_pageSpec
     * @@param l_sortSpec
     * @@return java.util.List
     * @@roseuid 40502123006C
     */
    public List searchProducts(
        Institution l_institution,
        ProductTypeEnum l_productType,
        String l_strNameSubString,
        PaginationQueryParamsSpec l_pageSpec,
        SortKeySpec l_sortSpec)
    {
        if (l_strNameSubString == null)
            throw new IllegalArgumentException("nameSubString should not be null");
        try
        {
            String l_strWhere =
                "institution_code = ? and (product_type = ?) and "
                    + "( (standard_name like ?) or (name_alt1 like ?) or "
                    + "(name_alt2 like ?) ) ";

            if (l_strNameSubString != null)
            {
                l_strNameSubString = "%" + l_strNameSubString + "%";
            }

            Object[] l_bindVars =
                {
                    l_institution.getInstitutionCode(),
                    new Long(l_productType.intValue()),
                    l_strNameSubString,
                    l_strNameSubString,
                    l_strNameSubString };

            String l_strOrderBy = null;
            if (l_sortSpec != null && (l_sortSpec.isSortKeyNull() == false))
            {
                l_strOrderBy = l_sortSpec.getSortKeySpec();
            }
            int l_intPageSize = l_pageSpec.getPageSize();
            int l_intPageNumber = l_pageSpec.getPageNumber();
            List l_lisRows =
                Processors
                    .getDefaultProcessor()
                    .doFindAllQuery(ProductRow.TYPE,
                //"institution_code = ? and (product_type = ?) and "
        //    + "( (standard_name like ?) or (name_alt1 like ?) or "
        //    + "(name_alt2 like ?) ) ",
    l_strWhere, l_strOrderBy, null, l_bindVars, l_intPageSize, l_intPageNumber);
            return toProductList(l_lisRows);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new RuntimeSystemException(
                "System exception while searching product with nameSubString :"
                    + l_strNameSubString,
                de);
        }
    }

    /**
     * (非 Javadoc)
     * @@see 
     * com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager#searchProducts(com.f
     * itechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.Market, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     * @@param l_productType
     * @@param l_market
     * @@param l_pageSpec
     * @@param l_sortSpec
     * @@return java.util.List
     * @@roseuid 4050212300F9
     */
    public List searchProducts(
        ProductTypeEnum l_productType,
        Market l_market,
        PaginationQueryParamsSpec l_pageSpec,
        SortKeySpec l_sortSpec)
    {
        if (l_market == null)
        {
            throw new IllegalArgumentException("market should not be null");
        }
        try
        {
            String l_strWhere =
                "institution_code = ? and product_type = ? and "
                    + "(product_id in (select product_id from traded_product "
                    + "where market_id =  ?  ) )";
            Object l_bindVars[] =
                {
                    l_market.getInstitution().getInstitutionCode(),
                    new Long(l_productType.intValue()),
                    new Long(l_market.getMarketId())};
            String l_strOrderBy = null;
            if (l_sortSpec != null && (l_sortSpec.isSortKeyNull() == false))
            {
                l_strOrderBy = l_sortSpec.getSortKeySpec();
            }
            int l_intPageSize = l_pageSpec.getPageSize();
            int l_intPageNumber = l_pageSpec.getPageNumber();
            List rows =
                Processors
                    .getDefaultProcessor()
                    .doFindAllQuery(ProductRow.TYPE,
                //"institution_code = ? and product_type = ? and (product_id in "
        //    + "(select product_id from traded_product where market_id =  ?) )",
    l_strWhere, l_strOrderBy, null, l_bindVars, l_intPageSize, l_intPageNumber);
            return toProductList(rows);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new RuntimeSystemException(
                "System exception while searching product with market id :"
                    + l_market.getMarketId(),
                de);
        }
    }

    /**
     * ProductRow(群)を基に、WEB3EquityProduct(群)を生成し返します。<BR>
     * @@param l_lisProductRows
     * @@return List WEB3EquityProduct(群)
     * @@throws DataException
     * @@throws DataException
     * @@roseuid 40502123029F
     */
    private List toProductList(List l_lisProductRows) throws DataException
    {
        int l_intSize = l_lisProductRows.size();
        List l_lisProducts = new ArrayList(l_intSize);
        for (int i = 0; i < l_intSize; i++)
        {
            ProductRow l_productRow = (ProductRow)l_lisProductRows.get(i);
            l_lisProducts.add(toProduct(l_productRow));
        }

        return l_lisProducts;
    }

    /**
     * (get制限値幅)<BR>
     * 【値幅テーブル】より、該当する市場の制限値幅を取得し返す。<BR>
     * 【値幅テーブル】からの取得には、指定された基準値、及び<BR>
     * （入力）取引銘柄．市場IDで【市場テーブル】を検索し、取得した市場コードを使用する。<BR>
     * <BR>
     * 当メソッドの詳細は<BR>
     * 「基本設計計算式書（エクイティ）.doc」の「[●共通2]値幅算出」を参照。<BR>
     * <BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 【値幅テーブル】検索時に、市場コードの指定に使用する。<BR>
     * また、【市場テーブル】検索時の市場ID指定にも使用する。
     * @@param l_dblBasePrice - (基準値)<BR>
     * 【値幅テーブル】から制限値幅を取得する際に、基準値として使用する。
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40502124006C
     */
    public double getDeregPriceRange(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblBasePrice)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getDeregPriceRange(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
 
        // 市場コードの取得
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        
        // 制限値幅の取得
        double l_dblDeregPriceRange = 0.0D;
        String l_strWhere =
            " market_code = ? and low_price <= ? and cap_price > ?";
        Object l_bindVars[] =
        {
            l_strMarketCode,
            new Double(l_dblBasePrice),
            new Double(l_dblBasePrice)
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    EquityLimitPriceRangeMstRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            if (l_lisRows.size() < 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_dblDeregPriceRange = ((EquityLimitPriceRangeMstRow)l_lisRows.get(0)).getRange();
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblDeregPriceRange;
    }

    /**
     * (get刻み値)<BR>
     * 呼値テーブルから、指定された基準値を使用して該当する市場の刻み値を取得する。<BR>
     * 刻み値の取得は、指定された基準値、及び<BR>
     * （入力）取引銘柄．市場IDで【市場テーブル】を検索し、取得した市場コードで行う。<BR>
     * 取得した刻み値は呼値チェックに使用する。<BR>
     * <BR>
     * 当メソッドの詳細は<BR>
     * 「基本設計計算式書（エクイティ）.doc」の「[●共通1]刻み値取得」を参照。<BR>
     * <BR>
     * @@param l_tradedProduct - 取引銘柄 <BR>
     *     【呼値テーブル】検索時の、市場コード指定に使用する。<BR>
     * @@param l_dblBasePrice - 基準値 <BR>
     *     【呼値テーブル】検索時に、対象レコード特定のための基準値として使用する。<BR>
     * <BR>
     * @@throws WEB3BaseException <BR>
     * @@return double
     * @@roseuid 405021240108
     */
    public double getTickValue(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblBasePrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTickValue(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
 
        // 市場コードの取得
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        
        // 刻み値の取得
        double l_dblTickValue = 0.0D;
        String l_strWhere =
            " market_code = ? and low_price < ? and cap_price >= ?";
        Object l_bindVars[] =
        {
            l_strMarketCode,
            new Double(l_dblBasePrice),
            new Double(l_dblBasePrice)
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    EquityTickValuesMstRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            if (l_lisRows.size() < 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_dblTickValue = ((EquityTickValuesMstRow)l_lisRows.get(0)).getTickValue();
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblTickValue;
    }

    /**
     * (get取引銘柄)<BR>
     * 株式銘柄、市場オブジェクトより取引銘柄インスタンスを取得する。<BR>
     * （getTradedProduct(Product,Market)のオーバーライド）<BR>
     * @@param l_product - (株式銘柄)<BR>
     * @@param l_market - (市場)<BR>
     * @@return WEB3EquityTradedProduct<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413AA92301AD
     */
    public TradedProduct getTradedProduct(
        Product l_product,
        Market l_market)
        throws NotFoundException
    {

        TradedProduct l_tradedProduct = null;

        l_tradedProduct =
            super.getTradedProduct(l_product, l_market);

        return l_tradedProduct;

    }

    /**
     * (get取引銘柄)<BR>
     *  <BR>
     * 証券会社オブジェクト、銘柄コード、市場コードより取引銘柄 <BR>
     * インスタンスを取得します。 <BR>
     * （getTradedProduct(Institution, String, String)のオーバーライド） <BR>
     *  <BR>
     * @@param l_gentradeInstitution - (証券会社)<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@return WEB3EquityTradedProduct<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A4120B01C5
     */
    public EqTypeTradedProduct getTradedProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strMarketCode)
            throws NotFoundException
    {

        EqTypeTradedProduct l_tradedProduct = null;

        l_tradedProduct =
            super.getTradedProduct(
                l_institution,
                l_strProductCode,
                l_strMarketCode);


        return l_tradedProduct;

    }
    /**
     * (get時価)<BR>
     * <BR>
     * 時価を取得する。取得順は以下の通り。 <BR>
     * ----------------------------------------------- <BR>
     * 場中：　@　@時価サーバ＞株式取引銘柄テーブル <BR>
     * 引け後or終値テーブル無条件read指定時：<BR>
     * 　@　@　@　@　@　@終値テーブル＞時価サーバ＞株式取引銘柄テーブル<BR>
     * ----------------------------------------------- <BR>
     * <BR>
     * １）　@this.get時価情報( )をコールする。 <BR>
     * <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@＜get時価情報( )：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@取引銘柄：　@引数の取引銘柄 <BR>
     * 　@　@　@RealType：　@"リアル" <BR>
     * 　@　@　@is終値テーブル無条件read：　@引数のis終値テーブル無条件read<BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * ２）　@戻り値オブジェクト.時価 を返却する。 <BR>
     * <BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト
     * @@param l_isLastClosingPriceUnconditionalyRead - (is終値テーブル無条件read)<BR>
     * 終値テーブルを無条件にreadするかどうかのフラグ
     * @@return double
     * @@roseuid 40D16BAA0221
     */
    public double getCurrentPrice(EqTypeTradedProduct l_tradedProduct, boolean l_isLastClosingPriceUnconditionalyRead)
        throws WEB3BaseException
    {
        String METHOD_NAME =
            "getCurrentPrice(EqTypeTradedProduct, boolean)";

        log.entering(METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.error("parameter is null type");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + METHOD_NAME);
        }

        WEB3EquityProductQuote l_productQuote = this.getProductQuote(l_tradedProduct, RealType.REAL, l_isLastClosingPriceUnconditionalyRead);
        
        if (l_productQuote == null)
        {
	        log.exiting(METHOD_NAME);
            return 0.0D;
        }
        else
        {
	        log.exiting(METHOD_NAME);
	        return l_productQuote.getQuote();
        }
    }

    /**
     * (get取引銘柄一覧)<BR>
     * <BR>
     * 引数の条件に該当する株式取引銘柄の一覧を<BR>
     * 取得し、返却する。<BR>
     * ※管理者・株式銘柄条件設定で使用。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(パラメータセット)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@銘柄ID = パラメータ.銘柄ID<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@有効日 = (パラメータ.対象日付区分の値による)<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " product_id = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and institution_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and valid_until_biz_date = ?"<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値をセットする。<BR>
     * 　@　@　@・パラメータ.銘柄ID<BR>
     * 　@　@　@・パラメータ.証券会社コード<BR>
     * 　@　@　@[パラメータ.対象日付区分 == "0：当日"の場合]<BR>
     * 　@　@　@　@・業務(バッチ)日付(*1)をセット。<BR>
     * 　@　@　@[パラメータ.対象日付区分 == "1：翌営業日"の場合]<BR>
     * 　@　@　@　@・業務日付の１営業日後の日付を文字列変換してセット。<BR>
     * 　@　@　@　@　@(フォーマット YYYYMMDD)<BR>
     * 　@　@　@[上記以外の場合]<BR>
     * 　@　@　@　@・業務日付の２営業日後の日付を文字列変換してセット。<BR>
     * 　@　@　@　@　@(フォーマット YYYYMMDD)<BR>
     * <BR>
     * ２）検索対象テーブル名を決定する。<BR>
     * 　@[パラメータ.対象日付区分 == "0：当日"の場合]<BR>
     * 　@　@テーブル名 = "株式取引銘柄テーブル"(eqtype_traded_product)<BR>
     * 　@[上記以外]<BR>
     * 　@　@テーブル名 = "株式取引銘柄一時テーブル"(eqtype_traded_product_updq)<BR>
     * <BR>
     * ３）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@２）にて決定したテーブル名<BR>
     * 　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@arg2：　@生成したArrayList.toArray()<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ４）HashMapを生成する。<BR>
     * <BR>
     * ５）３）の検索結果の件数分以下の処理を繰り返す。<BR>
     * 　@　@５－１）作成したHashMap.put()メソッドにて、株式取引銘柄Paramsを追加す<BR>
     * る。<BR>
     * 　@　@　@　@※株式取引銘柄updqParamsの場合は、株式取引銘柄Paramsに変換してセッ<BR>
     * トすること。<BR>
     * 　@　@　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@　@　@key：　@株式取引銘柄Params.市場IDに該当する市場コード<BR>
     * 　@　@　@　@　@value：　@株式取引銘柄Params<BR>
     * <BR>
     * ６）生成したHashMapを返却する。<BR>
     * <BR>
     * (*1)業務(バッチ)日付・・・TradingSystem.getBizDate()にて取得した業務(バッ<BR>
     * チ)日付。<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strDateDiv - (対象日付区分)<BR>
     * 対象日付区分
     * <BR>
     * 0：　@当日<BR>
     * 1：　@翌営業日<BR>
     * 2：　@翌々営業日<BR>
     * @@throws WEB3BaseException
     * @@return HashMap
     */
    public HashMap getEqtypeTradedProducts(
        long l_lngProductId,
        String l_strInstitutionCode,
        String l_strDateDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEqtypeTradedProducts(long, String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisTradedProducts = null;
        
        // １－１）検索条件文字列を作成する。
        String l_strWhere = "product_id = ? and institution_code = ? and valid_until_biz_date = ? and list_flag != ?";
        
        // １－２）"?"にセットするためのパラメータセットを作成する。
        List l_lisBindVars = new ArrayList();
        l_lisBindVars.add(Long.toString(l_lngProductId));
        l_lisBindVars.add(l_strInstitutionCode);
        Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strDateDiv))
        {
            l_lisBindVars.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_tsBizDate));
        }
        else if (WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strDateDiv))
        {
            WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsBizDate);
            l_lisBindVars.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_gentradeBizDate.roll(1)));
        }
        else
        {
            WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsBizDate);
            l_lisBindVars.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_gentradeBizDate.roll(2)));
        }
        l_lisBindVars.add(BooleanEnum.FALSE);
        
        // ２）検索対象テーブル名を決定する。
        RowType l_rowType = null;
        if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strDateDiv))
        {
            l_rowType = EqtypeTradedProductRow.TYPE;
        }
        else
        {
            l_rowType = EqtypeTradedProductUpdqRow.TYPE;
        }
        
        // ３）QueryProcessor.doFindAllQuery()メソッドをコールする。
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisTradedProducts = l_processor.doFindAllQuery(
                l_rowType,
                l_strWhere,
                l_lisBindVars.toArray());
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        // 検索結果が取得できなかった場合は、nullを返却する。
        if (l_lisTradedProducts == null || l_lisTradedProducts.isEmpty())
        {
            return null;
        }
        
        // ４）HashMapを生成する。
        HashMap l_tradedProductsMap = new HashMap();
        
        // ５）３）の検索結果の件数分以下の処理を繰り返す。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        int l_intSize = l_lisTradedProducts.size();
        for (int i = 0;i < l_intSize;i++)
        {
            // ５－１）作成したHashMap.put()メソッドにて、株式取引銘柄Paramsを追加する。
            Row l_row = (Row)l_lisTradedProducts.get(i);
            EqtypeTradedProductParams l_tradedProductParams = null;
            if (l_row instanceof EqtypeTradedProductUpdqParams)
            {
                l_tradedProductParams = new EqtypeTradedProductParams();
                GtlUtils.copyRow2Params(l_row, l_tradedProductParams);
            }
            else
            {
                l_tradedProductParams = (EqtypeTradedProductParams)l_row;
            }
            Market l_market = null;
            try
            {
                l_market = l_finObjectManager.getMarket(l_tradedProductParams.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.toString(),
                    l_nfe);
            }
            l_tradedProductsMap.put(l_market.getMarketCode(), l_tradedProductParams);
        }
        
        // ６）生成したHashMapを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_tradedProductsMap;
    }

    /**
     * (update銘柄)<BR>
     * <BR>
     * 銘柄テーブルを更新する。<BR>
     * <BR>
     * １）銘柄PKインスタンスを生成する。<BR>
     * <BR>
     * 　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@arg0：　@パラメータ.銘柄ID<BR>
     * <BR>
     * ２）QueryProcessor.doUpdateQuery()をコールする。<BR>
     * <BR>
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@１）にて作成した銘柄PK<BR>
     * 　@　@arg1：　@パラメータ.変更データ<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_changeMap - (変更データ)<BR>
     * 変更データ<BR>
     * @@throws WEB3BaseException
     */
    public void updateProduct(
        long l_lngProductId,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateProduct(long, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // １）銘柄PKインスタンスを生成する。
        ProductPK l_productPK = new ProductPK(l_lngProductId);
        
        // ２）QueryProcessor.doUpdateQuery()をコールする。
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_productPK, l_changeMap);
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update株式銘柄)<BR>
     * <BR>
     * 株式銘柄テーブルを更新する。<BR>
     * <BR>
     * １）株式銘柄PKインスタンスを生成する。<BR>
     * <BR>
     * 　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@arg0：　@パラメータ.銘柄ID<BR>
     * <BR>
     * ２）QueryProcessor.doUpdateQuery()をコールする。<BR>
     * <BR>
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@１）にて作成した株式銘柄PK<BR>
     * 　@　@arg1：　@パラメータ.変更データ<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_changeMap - (変更データ)<BR>
     * 変更データ<BR>
     * @@throws WEB3BaseException
     */
    public void updateEqtypeProduct(
        long l_lngProductId,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateEqtypeProduct(long, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // １）株式銘柄PKインスタンスを生成する。
        EqtypeProductPK l_eqtypeProductPK = new EqtypeProductPK(l_lngProductId);
        
        // ２）QueryProcessor.doUpdateQuery()をコールする。
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_eqtypeProductPK, l_changeMap);
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update取引銘柄)<BR>
     * <BR>
     * 銘柄テーブルを更新する。<BR>
     * <BR>
     * １）株式取引銘柄PKインスタンスを生成する。<BR>
     * <BR>
     * 　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@arg0：　@パラメータ.取引銘柄ID<BR>
     * <BR>
     * ２）QueryProcessor.doUpdateQuery()をコールする。<BR>
     * <BR>
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@１）にて作成した株式取引銘柄PK<BR>
     * 　@　@arg1：　@パラメータ.変更データ<BR>
     * <BR>
     * @@param l_lngTradedProductId - (取引銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_changeMap - (変更データ)<BR>
     * 変更データ<BR>
     * @@throws WEB3BaseException
     */
    public void updateEqtypeTradedProduct(
        long l_lngTradedProductId,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateEqtypeTradedProduct(long, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // １）株式取引銘柄PKインスタンスを生成する。
        EqtypeTradedProductPK l_eqtypeTradedProductPK = new EqtypeTradedProductPK(l_lngTradedProductId);
        
        // ２）QueryProcessor.doUpdateQuery()をコールする。
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_eqtypeTradedProductPK, l_changeMap);
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update取引銘柄updq)<BR>
     * <BR>
     * 銘柄テーブルを更新する。<BR>
     * <BR>
     * １）株式取引銘柄updqPKインスタンスを生成する。<BR>
     * <BR>
     * 　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@arg0：　@パラメータ.取引銘柄ID<BR>
     * 　@　@arg1：　@パラメータ.有効日<BR>
     * <BR>
     * ２）QueryProcessor.doUpdateQuery()をコールする。<BR>
     * <BR>
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@１）にて作成した株式取引銘柄updqPK<BR>
     * 　@　@arg1：　@パラメータ.変更データ<BR>
     * @@param l_lngTradedProductId - (取引銘柄ID)<BR>
     * 銘柄ID
     * @@param l_strBizDate (有効日)<BR>
     * 有効日
     * @@param l_changeMap - (変更データ)<BR>
     * 変更データ
     * @@throws WEB3BaseException
     */
    public void updateEqtypeTradedProductUpdq(
        long l_lngTradedProductId,
        String l_strBizDate,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateEqtypeTradedProductUpdq(long, String, HashMap)";
        log.entering(STR_METHOD_NAME);

        // １）株式取引銘柄PKインスタンスを生成する。
        EqtypeTradedProductUpdqPK l_eqtypeTradedProductUpdqPK =
            new EqtypeTradedProductUpdqPK(
                l_lngTradedProductId,
                l_strBizDate);
        
        // ２）QueryProcessor.doUpdateQuery()をコールする。
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_eqtypeTradedProductUpdqPK, l_changeMap);
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getミニ株取扱取引銘柄)<BR>
     * <BR>
     * ミニ株を取り扱っている取引銘柄を取得し、返却する。<BR>
     * <BR>
     * １）以下の処理をパラメータ.取引銘柄一覧の要素数分<BR>
     * 　@繰り返す。<BR>
     * 　@１－１）パラメータ.取引銘柄一覧[index].ミニ株取扱 == "ミニ株取扱"の場合、<BR>
     * 　@　@　@　@パラメータ.取引銘柄一覧[index]を返却して終了する。<BR>
     * <BR>
     * ２）nullを返却する。　@※ミニ株取扱取引銘柄が取得できなかった為。<BR>
     * @@param - l_tradedProducts - (取引銘柄一覧)<BR>
     * 取引銘柄Paramsオブジェクトの配列
     * @@throws WEB3BaseException
     * @@return EqtypeTradedProductParams
     */
    public EqtypeTradedProductParams getMiniStockTradedProduct(
        EqtypeTradedProductParams[] l_tradedProducts)
    {
        final String STR_METHOD_NAME =
            "getMiniStockTradedProduct(EqtypeTradedProductParams[])";
        log.entering(STR_METHOD_NAME);
        
        for (int i = 0;i < l_tradedProducts.length;i++)
        {
            EqtypeTradedProductParams l_tradedProductParams = l_tradedProducts[i];
            if (BooleanEnum.TRUE.equals(l_tradedProductParams.getMiniStockCanDealt()))
            {
                return l_tradedProducts[i];
            }
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

	/**
	 * (getミニ株取扱取引銘柄)<BR>
	 * <BR>
	 * 指定銘柄の、ミニ株取扱市場の取引銘柄オブジェクトを取得し返却する。 <BR>
	 * <BR>
	 * １）　@ミニ株市場取得<BR>
	 *  株式銘柄.getミニ株市場()にて、市場オブジェクトを取得する。 <BR>
	 *  戻り値がnull（==ミニ株取扱なし銘柄）の場合は、nullを返却する。 <BR>
	 * <BR>
	 * ２）　@取引銘柄取得 <BR>
	 *  プロダクトマネージャ.getTradedProduct()にて、取引銘柄オブジェクトを取得する。 <BR>
	 * <BR>
	 * [getTradedProduct()に指定する引数] <BR>
	 * product：　@株式銘柄 <BR>
	 * market：　@市場<BR>
	 * <BR>
	 * ３）　@取引銘柄オブジェクトを返却する。<BR>
	 * <BR>
	 * @@param - l_product - (株式銘柄)<BR>
	 * 株式銘柄オブジェクト
	 * @@throws WEB3BaseException
	 * @@return WEB3EquityTradedProduct
	 */
	public WEB3EquityTradedProduct getMiniStockTradedProduct(
		WEB3EquityProduct l_product) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getMiniStockTradedProduct(WEB3EquityProduct)";
		log.entering(STR_METHOD_NAME);
        
		if (l_product == null)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + STR_METHOD_NAME);
		}
		Market l_market;
		WEB3EquityTradedProduct l_equityTradedProduct;
		try 
		{
			l_market = l_product.getMiniStockMarket();
			if (l_market == null)
			{
				return null;
			}
			FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
			TradingModule l_tradingModule =
				l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
			WEB3EquityProductManager l_eqTypeProductManager =
				(WEB3EquityProductManager)l_tradingModule.getProductManager();

			l_equityTradedProduct =
				(WEB3EquityTradedProduct)l_eqTypeProductManager.getTradedProduct(
				l_product, l_market);
		}
		catch (NotFoundException l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
			    STR_METHOD_NAME);
		}

		log.exiting(STR_METHOD_NAME);
		return l_equityTradedProduct;
	}

    /**
     * (get立会外分売銘柄)<BR>
     * <BR>
     * 【立会外分売銘柄テーブル】より、<BR>
     * 指定された分売銘柄のレコードを取得する。<BR>
     * （DBレイアウト「立会外分売銘柄テーブル仕様.xls」参照）<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@立会外分売銘柄テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@かつ　@銘柄コード = 引数.銘柄コード<BR>
     * 　@かつ　@市場コード = 引数.市場コード<BR>
     * 　@かつ　@受付終了日時のYYYYMMDD = 引数.受付終了日時のYYYYMMDD<BR>
     * <BR>
     * ２）　@取得したレコードを返す。<BR>
     * 　@　@　@※該当するレコードが存在しない場合はnullを返す。<BR>
     * 　@　@　@※複数レコード存在した場合は、例外をthrowする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード。<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード。<BR>
     * @@param l_datOrderEndDatetime - (受付終了日時)<BR>
     * 受付終了日時。<BR>
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public OffFloorOrderProductParams getOffFloorOrderProduct(
        String l_strInstitutionCode,
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderEndDatetime)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOffFloorOrderProduct(String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        OffFloorOrderProductParams l_params = null;
        
        String l_strWhere = "institution_code = ? and "
                          + "product_code = ? and "
                          + "market_code = ? and "
                          + "to_char(order_end_datetime,'yyyymmdd') = ?";
        Object[] l_bindVars =
        {
            l_strInstitutionCode,
            l_strProductCode,
            l_strMarketCode,
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datOrderEndDatetime)
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    OffFloorOrderProductRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            if (l_lisRows != null)
            {
                int l_intSize = l_lisRows.size();
                if (l_intSize == 1)
                {
                    l_params = (OffFloorOrderProductParams)l_lisRows.get(0);
                }
                else if (l_intSize > 1)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * (get注文可能立会外分売銘柄)<BR>
     * <BR>
     * 【立会外分売銘柄テーブル】より、<BR>
     * 指定された分売銘柄の注文可能なレコードを取得する。<BR>
     * （DBレイアウト「立会外分売銘柄テーブル仕様.xls」参照）<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@立会外分売銘柄テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@かつ　@銘柄コード = 引数.銘柄コード<BR>
     * 　@かつ　@市場コード = 引数.市場コード<BR>
     * 　@かつ　@受付開始日時 ≦ 現在日時(*1)<BR>
     * 　@かつ　@受付終了日時 ＞ 現在日時(*1)<BR>
     * 　@かつ　@分売価格 != null<BR>
     * 　@かつ　@申込株数上限 != null<BR>
     * <BR>
     * 　@(*1)現在日時：GtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * ２）　@取得したレコードを返す。<BR>
     * <BR>
     * 　@　@　@該当するレコードが存在しない場合はnullを返す。<BR>
     * 　@　@　@複数レコード存在する場合は「立会外分売可能レコードが特定不可」の例外をthrowする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード。<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード。<BR>
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public OffFloorOrderProductParams getCanOrderOffFloorOrderProduct(
        String l_strInstitutionCode,
        String l_strProductCode,
        String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCanOrderOffFloorOrderProduct(String, String, String)";
        log.entering(STR_METHOD_NAME);
        OffFloorOrderProductParams l_params = null;
        
        String l_strWhere =
            " institution_code = ? and product_code = ? and market_code = ?"
            + " and order_start_datetime <= ? and order_end_datetime > ?"
            + " and off_floor_order_price is not null and max_apply_quantity is not null";
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        Object l_bindVars[] =
        {
            l_strInstitutionCode,
            l_strProductCode,
            l_strMarketCode,
            l_tsSystemTimestamp,
            l_tsSystemTimestamp
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                OffFloorOrderProductRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            if (l_lisRows != null)
            {
                int l_intSize = l_lisRows.size();
                if (l_intSize == 1)
                {
                    l_params = (OffFloorOrderProductParams)l_lisRows.get(0);
                }
                else if (l_intSize > 1)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01368,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (is更新削除可能立会外分売銘柄)<BR>
     * <BR>
     * 指定された分売銘柄が、現在更新／削除可能かどうかを判定し、<BR>
     * 更新／削除が可能な場合はtrueを、更新／削除が不可な場合はfalseを返す。<BR>
     * <BR>
     * 引数の立会外分売銘柄オブジェクトが以下の[条件１]または[条件２]に合致する場合は、<BR>
     * 更新／削除が可能と判定しtrueを返す。<BR>
     * ------------------------------------------------<BR>
     * [条件１]：受付開始前である<BR>
     * 　@受付開始日時 ＞ 現在日時(*1)<BR>
     * <BR>
     * [条件２]：受付中（登録未完了のデータ）<BR>
     * 　@受付開始日時 ≦ 現在日時(*1)<BR>
     * かつ　@受付終了日時 ＞ 現在日時(*1)<BR>
     * かつ　@（分売価格 == null　@または　@申込株数上限 == null）<BR>
     * <BR>
     * (*1)現在日時：GtlUtils.getSystemTimestamp( )<BR>
     * ------------------------------------------------<BR>
     * <BR>
     * 上記の[条件１][条件２]のいずれにも合致しない場合は、<BR>
     * 更新／削除が不可と判定しfalseを返す。<BR>
     * <BR>
     * @@param l_params - (立会外分売銘柄)<BR>
     * 立会外分売銘柄オブジェクト。<BR>
     * （【立会外分売銘柄テーブル】の１レコード）<BR>
     * @@throws WEB3BaseException
     */
    public boolean isCanUpdateDeleteOffFloorOrderProduct(
        OffFloorOrderProductRow l_row)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCanOrderOffFloorOrderProduct(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_isCanUpdateDeleteOffFloorOrderProduct = false;
        
        // 引数の立会外分売銘柄オブジェクトが以下の[条件１]または[条件２]に合致する場合は、
        // 更新／削除が可能と判定しtrueを返す。
        Timestamp l_tsSysDate = GtlUtils.getSystemTimestamp();
        // [条件１]：受付開始前である
        // 　@受付開始日時 ＞ 現在日時(*1)
        if (l_row.getOrderStartDatetime().compareTo(l_tsSysDate) > 0)
        {
            l_isCanUpdateDeleteOffFloorOrderProduct = true;
        }
        // [条件２]：受付中（登録未完了のデータ）
        // 　@受付開始日時 ≦ 現在日時(*1)
        // かつ　@受付終了日時 ＞ 現在日時(*1)
        // かつ　@（分売価格 == null　@または　@申込株数上限 == null）
        else
        {
            if (l_row.getOrderEndDatetime().compareTo(l_tsSysDate) > 0 &&
                (l_row.getOffFloorOrderPriceIsNull() || l_row.getMaxApplyQuantityIsNull()))
            {
                l_isCanUpdateDeleteOffFloorOrderProduct = true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_isCanUpdateDeleteOffFloorOrderProduct;
    }
    
    /**
     * (get終値)<BR>
     * <BR>
     * 【終値テーブル】から、 <BR>
     * 指定された銘柄ID、基準日に該当するレコードを取得し返す。 <BR>
     * <BR>
     * １）　@DB検索 <BR>
     * 　@終値テーブルを以下の条件で検索する。 <BR>
     * 　@[条件] <BR>
     * 　@銘柄ID = 引数.銘柄ID <BR>
     * 　@かつ　@基準日 = 引数.基準日のYYYYMMDD <BR>
     * <BR>
     * ※引数.基準日==nullの場合は、基準日を条件から外す。<BR>
     * <BR>
     * ２）　@取得したレコードを返す。 <BR>
     * 　@　@　@※該当するレコードが存在しない場合はnullを返す。 <BR>
     * 　@　@　@※該当するレコードが複数件存在する場合は、基準日が最大値のレコードを返す。<BR>
     * <BR>
     * @@params l_lngProductId  (銘柄ID)<BR>
     * 銘柄ID。<BR>
     * @@params l_datBaseDate  (基準日)<BR>
     * 基準日(業務日付)。<BR>
     * @@return LastClosingPriceParams<BR>
     * @@throws WEB3BaseException<BR>
     */ 
    public LastClosingPriceParams getLastClosingPrice(long l_lngProductId, Date l_datBaseDate)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLastClosingPrice(long, Date)";
        log.entering(STR_METHOD_NAME);
        
        // １）DB検索
        String l_strWhere = null;
        Object[] l_objWhere = null;
        List l_lisSearchResult = null;
        if (l_datBaseDate != null)
        {
            l_strWhere = "product_id = ? and to_char(base_date, 'YYYYMMDD') = ?";
            l_objWhere = new Object[2];
            l_objWhere[0] = new Long(l_lngProductId);
            l_objWhere[1] = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datBaseDate);
        }
        else
        {
            l_strWhere = "product_id = ?";
            l_objWhere = new Object[1];
            l_objWhere[0] = new Long(l_lngProductId);
        }
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisSearchResult = l_processor.doFindAllQuery(
                LastClosingPriceRow.TYPE,
                l_strWhere,
                null,
                null,
                l_objWhere);
        }
        catch (DataException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBアクセスに失敗しました。");
        }
        
        // ２）　@取得したレコードを返す。
        log.exiting(STR_METHOD_NAME);
        if (l_lisSearchResult == null || l_lisSearchResult.isEmpty())
        {
            return null;
        }
        else
        {
            int l_intSize = l_lisSearchResult.size();
            LastClosingPriceRow l_lastClosingPriceRow = null;
            if (l_intSize > 1)
            {
                Timestamp l_tsBaseDate = null;
                LastClosingPriceRow l_row = null;
                for (int i = 0;i < l_intSize;i++)
                {
                    l_row = (LastClosingPriceRow)l_lisSearchResult.get(i);
                    if (l_tsBaseDate == null)
                    {
                        l_tsBaseDate = l_row.getBaseDate();
                        l_lastClosingPriceRow = l_row;
                    }
                    else
                    {
                        if (l_row.getBaseDate().compareTo(l_tsBaseDate) > 0)
                        {
                            l_tsBaseDate = l_row.getBaseDate();
                            l_lastClosingPriceRow = l_row; 
                        }
                    }
                }
            }
            else
            {
                l_lastClosingPriceRow = (LastClosingPriceRow)l_lisSearchResult.get(0);
            }
            return new LastClosingPriceParams(l_lastClosingPriceRow);
        }
    }
    
    /**
     * (get終値)<BR>
     * <BR>
     * 【終値テーブル】から、 <BR>
     * 指定された銘柄ID、市場コード、基準日に該当する値を取得し返す。 <BR>
     * <BR>
     * １）　@DB検索 <BR>
     * 　@オーバーロードメソッドをコールする。 <BR>
     * <BR>
     * 　@this.get終値(引数の銘柄ID, 引数の基準日) <BR>
     * <BR>
     * 　@該当データなし時は0を返す。 <BR>
     * <BR>
     * ２）　@取得したレコードの、該当する市場項目の単価設定値(*)を返す。 <BR>
     * <BR>
     * 　@(*)該当する市場項目 <BR>
     * 　@市場コード==東京：　@終値.東証終値 <BR>
     * 　@市場コード==大阪：　@終値.大証終値 <BR>
     * 　@市場コード==名古屋：　@終値.名証終値 <BR>
     * 　@市場コードが上記以外：　@終値.その他市場終値<BR>
     * <BR>
     * @@params l_lngProductId  (銘柄ID)<BR>
     * 銘柄ID。<BR>
     * @@params l_strMarketCode  (市場コード)<BR>
     * 市場コード。<BR>
     * @@params l_datBaseDate  (基準日)<BR>
     * 基準日(業務日付)。<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException<BR>
     */
    public double getLastClosingPrice(long l_lngProductId, String l_strMarketCode, Date l_datBaseDate)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLastClosingPrice(long, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        // this.get終値()
        LastClosingPriceParams l_lastClosingPriceParams =
            this.getLastClosingPrice(l_lngProductId, l_datBaseDate);
        
        if (l_lastClosingPriceParams == null)
        {
            return 0D;
        }
        
        // 取得したレコードの、該当する市場項目の単価設定値を返す。
        double l_dblLastClosingPrice = 0D;
        if (l_strMarketCode.equals(WEB3MarketCodeDef.TOKYO))
        {
            l_dblLastClosingPrice = l_lastClosingPriceParams.getTokyoClosingPrice();
        }
        else if (l_strMarketCode.equals(WEB3MarketCodeDef.OSAKA))
        {
            l_dblLastClosingPrice = l_lastClosingPriceParams.getOosakaClosingPrice();
        }
        else if (l_strMarketCode.equals(WEB3MarketCodeDef.NAGOYA))
        {
            l_dblLastClosingPrice = l_lastClosingPriceParams.getNagoyaClosingPrice();
        }
        else
        {
            l_dblLastClosingPrice = l_lastClosingPriceParams.getOtherClosingPrice();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblLastClosingPrice;
    }
    
    /**
     * (get表示用時価情報)<BR>
     * <BR>
     * 入力画面に表示する時価情報を取得する。 <BR>
     * <BR>
     * １）　@時価サービス（WEB3DefaultQuoteDataSupplierService）より、 <BR>
     * 　@　@　@顧客属性に応じた時価情報（WEB3EqTypeQuoteData）を取得する。 <BR>
     * <BR>
     * 　@　@　@リアル客（引数の補助口座の顧客.isリアル顧客( )==true）の場合： <BR>
     * 　@　@　@　@WEB3DefaultQuoteDataSupplierService.getEqTypeQuote(引数の取引銘柄, "リアル"); <BR>
     * <BR>
     * 　@　@　@ディレイ客（引数の補助口座の顧客.isリアル顧客( )==false）の場合： <BR>
     * 　@　@　@　@WEB3DefaultQuoteDataSupplierService.getEqTypeQuote(引数の取引銘柄, "20分ディレイ"); <BR>
     * <BR>
     * ２）　@取得した時価情報を以下の優先順位で評価し、 <BR>
     * 　@　@　@値が付いている（==0でない）単価を時価として、戻り値オブジェクト.時価 にセットする。 <BR>
     * <BR>
     * 　@　@１．現在値（WEB3EqTypeQuoteData.getCurrentPrice( )） <BR>
     * 　@　@２．売気配値（WEB3EqTypeQuoteData.getBidPrice( )） <BR>
     * 　@　@３．買気配値（WEB3EqTypeQuoteData.getAskPrice( )） <BR>
     * 　@　@４．取引銘柄.基準値（終値）（引数の取引銘柄.getLastClosingPrice( )） <BR>
     * <BR>
     * 　@　@また、時価とした単価に該当する時価区分（現在値／売気配値／買気配値／前日終値）を、 <BR>
     * 　@　@戻り値オブジェクト.時価区分 にセットする。 <BR>
     * <BR>
     * ３）　@戻り値オブジェクト.前日比に、WEB3EqTypeQuoteData.getChange( )をセットする。 <BR>
     * <BR>
     * ４）　@戻り値オブジェクト.時価発表時間に、２）で時価とした単価に対応する時価発表時間を <BR>
     * 　@　@　@セットする。 <BR>
     * <BR>
     * 　@　@　@※例：　@現在値を時価とする場合、WEB3EqTypeQuoteData.getCurrentPriceTime( )をセットする。 <BR>
     * 　@　@　@※nullが返された場合、nullをそのままセットする。<BR>
     * <BR>
     * ５）　@戻り値オブジェクト.時価取得区分に、"時価"をセットする。 <BR>
     * <BR>
     * ６）　@戻り値オブジェクト.市場コードに、引数の取引銘柄.市場IDに該当する市場.市場コードをセットする。 <BR>
     * <BR>
     * ７）　@以下、１）で取得済の時価情報から、板情報のセットを行う。 <BR>
     * <BR>
     * 　@　@戻り値オブジェクト.現在値に、WEB3EqTypeQuoteData.getCurrentPrice( )をセットする。(*1) <BR>
     * 　@　@戻り値オブジェクト.現在値時刻に、WEB3EqTypeQuoteData.getCurrentPriceTime( )をセットする。 <BR>
     * 　@　@戻り値オブジェクト.現在値区分に、WEB3EqTypeQuoteData.getCurrentPriceFlag( )をセットする。(*3) <BR>
     * 　@　@戻り値オブジェクト.現在値前日比に、WEB3EqTypeQuoteData.getChange( )をセットする。(*2) <BR>
     * <BR>
     * 　@　@戻り値オブジェクト.出来高に、WEB3EqTypeQuoteData.getVolume( )をセットする。(*1) <BR>
     * 　@　@戻り値オブジェクト.出来高時刻に、WEB3EqTypeQuoteData.getVolumeTime( )をセットする。 <BR>
     * <BR>
     * 　@　@戻り値オブジェクト.買気配値タイトル区分に、WEB3EqTypeQuoteData.getAskPriceTitle( )をセットする。(*4) <BR>
     * 　@　@戻り値オブジェクト.買気配値に、WEB3EqTypeQuoteData.getAskPrice( )をセットする。(*1) <BR>
     * 　@　@戻り値オブジェクト.買気配値時刻に、WEB3EqTypeQuoteData.getAskPriceTime( )をセットする。 <BR>
     * <BR>
     * 　@　@戻り値オブジェクト.売気配値タイトル区分に、WEB3EqTypeQuoteData.getBidPriceTitle( )をセットする。(*5) <BR> 
     * 　@　@戻り値オブジェクト.売気配値に、WEB3EqTypeQuoteData.getBidPrice( )をセットする。(*1) <BR>
     * 　@　@戻り値オブジェクト.売気配値時刻に、WEB3EqTypeQuoteData.getBidPriceTime( )をセットする。 <BR>
     * <BR>
     * 　@　@戻り値オブジェクト.基準値段に、WEB3EqTypeQuoteData.getBasePrice( )をセットする。(*1) <BR>
     * <BR>
     * ８）　@作成した戻り値オブジェクトを返す。<BR>
     * <BR>
     * (*1)0の場合は、nullをセットする。<BR> 
     * (*2)Double.NaNの場合は、nullをセットする。<BR> 
     * (*3)WEB3EqTypeQuoteData.getCurrentPrice( )==0の場合は、nullをセットする。<BR> 
     * (*4)WEB3EqTypeQuoteData.getAskPrice( )==0の場合は、nullをセットする。<BR>
     * (*5)WEB3EqTypeQuoteData.getBidPrice( )==0の場合は、nullをセットする。<BR>
     * @@params l_eqtypeTradedProduct  (取引銘柄)<BR>
     * 取引銘柄オブジェクト<BR>
     * @@params l_subAccount  (補助口座)<BR>
     * 補助口座。
     * @@return WEB3EquityProductQuote
     * @@throws WEB3BaseException
     */
    public WEB3EquityProductQuote getDisplayEquityProductQuote(EqTypeTradedProduct l_eqtypeTradedProduct, WEB3GentradeSubAccount l_subAccount)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDisplayEquityProductQuote(EqtypeTradedProduct, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //１）　@時価サービス（WEB3DefaultQuoteDataSupplierService）より、
        //     顧客属性に応じた時価情報（WEB3EqTypeQuoteData）を取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3QuoteDataSupplierService l_quoteDataSupplier =
            (WEB3QuoteDataSupplierService)l_tradingModule.getQuoteDataSupplierService();
            
        WEB3EqTypeQuoteData l_eqtypeQuoteData = null;
        boolean l_isRealAccount = l_mainAccount.isRealCustomer();
        if (l_isRealAccount == true)
        {
            try
            {
                l_eqtypeQuoteData =
                    (WEB3EqTypeQuoteData)l_quoteDataSupplier.getEqTypeQuote(l_eqtypeTradedProduct, RealType.REAL);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "株式時価取得サービスが取得出来ませんでした。リアル区分：[" + RealType.REAL + "]");
            }
        }
        else
        {
            try
            {
                l_eqtypeQuoteData =
                    (WEB3EqTypeQuoteData)l_quoteDataSupplier.getEqTypeQuote(l_eqtypeTradedProduct, RealType.DELAY);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "株式時価取得サービスが取得出来ませんでした。リアル区分：[" + RealType.DELAY + "]");
            }
        }
        
        // 戻り値オブジェクトの生成
        WEB3EquityProductQuote l_equityProductQuote = new WEB3EquityProductQuote();

        //２）　@取得した時価情報を以下の優先順位で評価し、 
        // 　@　@値が付いている（==0でない）単価を時価として、戻り値オブジェクト.時価 にセットする。
        // 　@　@１．現在値（WEB3EqTypeQuoteData.getCurrentPrice( )） <BR>
        // 　@　@２．売気配値（WEB3EqTypeQuoteData.getBidPrice( )） <BR>
        // 　@　@３．買気配値（WEB3EqTypeQuoteData.getAskPrice( )） <BR>
        // 　@　@４．取引銘柄.基準値（終値）（引数の取引銘柄.getLastClosingPrice( )）
        l_equityProductQuote.setQuote(l_eqtypeQuoteData.getCurrentPrice());
        l_equityProductQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.CURRENT_PRICE);
        
        //４）　@戻り値オブジェクト.時価発表時間に、２）で時価とした単価に対応する時価発表時間を 
        // 　@　@セットする。 
        l_equityProductQuote.setQuoteTime(l_eqtypeQuoteData.getCurrentPriceTime());
        
        if (l_equityProductQuote.getQuote() == 0.0D)
        {
            l_equityProductQuote.setQuote(l_eqtypeQuoteData.getBidPrice());
            l_equityProductQuote.setQuoteTime(l_eqtypeQuoteData.getBidPriceTime());
            l_equityProductQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.BID_PRICE);
        }
        if (l_equityProductQuote.getQuote() == 0.0D)
        {
            l_equityProductQuote.setQuote(l_eqtypeQuoteData.getAskPrice());
            l_equityProductQuote.setQuoteTime(l_eqtypeQuoteData.getAskPriceTime());
            l_equityProductQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.ASK_PRICE);
        }
        if (l_equityProductQuote.getQuote() == 0.0D)
        {
            l_equityProductQuote.setQuote(l_eqtypeTradedProduct.getLastClosingPrice());
            l_equityProductQuote.setQuoteTime(null);
            l_equityProductQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.LAST_CLOSING_PRICE);
        }
        
        //３）　@戻り値オブジェクト.前日比に、WEB3EqTypeQuoteData.getChange( )をセットする。
        l_equityProductQuote.setComparedPreviousDay(l_eqtypeQuoteData.getChange());
        
        //５）　@戻り値オブジェクト.時価取得区分に、"時価"をセットする。
        l_equityProductQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
        
        //６）　@戻り値オブジェクト.市場コードに、引数の取引銘柄.市場IDに該当する市場.市場コードをセットする。
        l_equityProductQuote.setMarketCode(l_eqtypeTradedProduct.getMarket().getMarketCode());
        
        //７）　@以下、１）で取得済の時価情報から、板情報のセットを行う。 
        //戻り値オブジェクト.現在値に、WEB3EqTypeQuoteData.getCurrentPrice( )をセットする。(*1) 
        if (l_eqtypeQuoteData.getCurrentPrice() == 0.0D)
        {
            l_equityProductQuote.setBoardCurrentPrice(null);
        }
        else
        {
            l_equityProductQuote.setBoardCurrentPrice(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getCurrentPrice()));
        }
        
        //戻り値オブジェクト.現在値時刻に、WEB3EqTypeQuoteData.getCurrentPriceTime( )をセットする。 
        l_equityProductQuote.setBoardCurrentPriceTime(
            l_eqtypeQuoteData.getCurrentPriceTime());
        
        //戻り値オブジェクト.現在値区分に、WEB3EqTypeQuoteData.getCurrentPriceFlag( )をセットする。
        //WEB3EqTypeQuoteData.getCurrentPrice()==0の場合は、nullをセット。
        if (l_eqtypeQuoteData.getCurrentPrice() == 0.0D)
        {
            l_equityProductQuote.setBoardCurrentPriceDiv(null);
        }
        else
        {
            String l_strCurrentPriceFlag = l_eqtypeQuoteData.getCurrentPriceFlag().intValue() + "";
            l_equityProductQuote.setBoardCurrentPriceDiv(l_strCurrentPriceFlag);
        }
        
        //戻り値オブジェクト.現在値前日比に、WEB3EqTypeQuoteData.getChange( )をセットする。(*2)         
        if (Double.isNaN(l_eqtypeQuoteData.getChange()))
        {
            l_equityProductQuote.setBoardChange(null);
        }
        else
        {
            l_equityProductQuote.setBoardChange(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getChange()));
        }
        
        //戻り値オブジェクト.出来高に、WEB3EqTypeQuoteData.getVolume( )をセットする。(*1)        
        if (l_eqtypeQuoteData.getVolume() == 0.0D)
        {
            l_equityProductQuote.setVolume(null);
        }
        else
        {
            l_equityProductQuote.setVolume(WEB3StringTypeUtility.formatNumber(
                l_eqtypeQuoteData.getVolume()));
        }
        
        //戻り値オブジェクト.出来高時刻に、WEB3EqTypeQuoteData.getVolumeTime( )をセットする。 
        l_equityProductQuote.setVolumeTime(l_eqtypeQuoteData.getVolumeTime());
        
        //戻り値オブジェクト.買気配値タイトル区分に、WEB3EqTypeQuoteData.getAskPriceTitle( )をセットする。
        //WEB3EqTypeQuoteData.getAskPrice()==0の場合は、nullをセット
        if (l_eqtypeQuoteData.getAskPrice()== 0.0D)
        {
            l_equityProductQuote.setAskPriceTitle(null);  
        }
        else
        {
            String l_strAskPriceTitle = l_eqtypeQuoteData.getAskPriceTitle().intValue() + "";
            l_equityProductQuote.setAskPriceTitle(l_strAskPriceTitle); 
        }
        
        //戻り値オブジェクト.買気配値に、WEB3EqTypeQuoteData.getAskPrice( )をセットする。(*1) 
        if (l_eqtypeQuoteData.getAskPrice() == 0.0D)
        {
            l_equityProductQuote.setAskPrice(null);
        } 
        else
        {
            l_equityProductQuote.setAskPrice(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getAskPrice()));
        }
        
        //戻り値オブジェクト.買気配値時刻に、WEB3EqTypeQuoteData.getAskPriceTime( )をセットする。 
        l_equityProductQuote.setAskPriceTime(l_eqtypeQuoteData.getAskPriceTime());
        
        //戻り値オブジェクト.売気配値タイトル区分に、WEB3EqTypeQuoteData.getBidPriceTitle( )をセットする。
        //WEB3EqTypeQuoteData.getBidPrice()==0の場合は、nullをセット
        if (l_eqtypeQuoteData.getBidPrice() == 0.0D)
        {
            l_equityProductQuote.setBidPriceTitle(null);
        }
        else
        {
            String l_strBidPriceTitle = l_eqtypeQuoteData.getBidPriceTitle().intValue() + "";
            l_equityProductQuote.setBidPriceTitle(l_strBidPriceTitle);
        }
        
        //戻り値オブジェクト.売気配値に、WEB3EqTypeQuoteData.getBidPrice( )をセットする。(*1) 
        if (l_eqtypeQuoteData.getBidPrice() == 0.0D)
        {
            l_equityProductQuote.setBidPrice(null);
        } 
        else
        {
            l_equityProductQuote.setBidPrice(WEB3StringTypeUtility.formatNumber(
                l_eqtypeQuoteData.getBidPrice()));
        }
        
        //戻り値オブジェクト.売気配値時刻に、WEB3EqTypeQuoteData.getBidPriceTime( )をセットする。 
        l_equityProductQuote.setBidPriceTime(l_eqtypeQuoteData.getBidPriceTime());
        
        //戻り値オブジェクト.基準値段に、WEB3EqTypeQuoteData.getBasePrice( )をセットする。(*1)    
        if (l_eqtypeQuoteData.getBasePrice() == 0.0D)
        {
            l_equityProductQuote.setBasePrice(null);
        } 
        else
        {
            l_equityProductQuote.setBasePrice(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getBasePrice()));
        }
        
        log.exiting(STR_METHOD_NAME);
        //８）　@作成した戻り値オブジェクトを返す。
        return l_equityProductQuote;
    }
    
    /**
     * (get銘柄From募集銘柄)<BR>
     * <BR>
     * 引数の募集銘柄より株式銘柄を取得する。 <BR>
     * <BR>
     * １）銘柄コード変換 <BR>
     * 　@パラメータ.募集銘柄.銘柄コードの5byte目を0に置換する。 <BR>
     * <BR>
     * ２）株式銘柄取得 <BR>
     * 　@this.getProduct()メソッドをコールし、株式銘柄を取得する。 <BR>
     * <BR>
     * 　@[getProduct()にセットするパラメータ] <BR>
     * 　@　@証券会社：　@パラメータ.募集銘柄.getInstitution()の戻り値 <BR>
     * 　@　@銘柄コード：　@１）にて変換した銘柄コード <BR>
     * <BR>
     * ３）２）にて取得した株式株式銘柄を返却する。 <BR>
     * <BR>
     * @@params l_subscriptionProduct 募集銘柄<BR>
     * 募集銘柄の株式銘柄オブジェクト<BR>
     * <BR>
     * @@return EqTypeProduct<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3EquityProduct getProductFromSubscriptionProduct(WEB3EquityProduct l_subscriptionProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductFromSubscription(EqTypeProduct)";
        log.entering(STR_METHOD_NAME);
        
        // 銘柄コード変換
        String l_strChangedProductCode =
            l_subscriptionProduct.getProductCode().substring(0, 4) + "0";
        
        // 株式銘柄取得
        WEB3EquityProduct l_newEqtypeProduct = null;
        try
        {
            l_newEqtypeProduct =
                (WEB3EquityProduct)this.getProduct(l_subscriptionProduct.getInstitution(),
                    l_strChangedProductCode);
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コード:[" + l_strChangedProductCode + "]に該当する株式銘柄が取得できません。",
                l_ex);
        }
        
        return l_newEqtypeProduct;
    }
    
    /**
     * (get時価情報)<BR>
     * <BR>
     * 指定内容に応じた時価情報を取得する。取得順は以下の通り。<BR>
     * -----------------------------------------------------------------<BR>
     * 場中：　@　@時価サーバ＞株式取引銘柄テーブル<BR>
     * 引け後or終値テーブル無条件read指定時：<BR>
     * 　@　@　@　@　@　@終値テーブル＞時価サーバ＞株式取引銘柄テーブル<BR>
     * -----------------------------------------------------------------<BR>
     * <BR>
     * １）　@引数のis終値テーブル無条件read==true、または<BR>
     * 　@　@ 引け後（取引時間管理.is市場開局時間帯( )==false）の場合のみ、当日終値を取得する。<BR>
     * 　@　@　@（this.get終値(銘柄ID, 市場コード, 基準日)で取得する）<BR>
     * <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@＜get終値( )：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@銘柄ID：　@引数の取引銘柄.銘柄ID <BR>
     * 　@　@　@市場コード：　@引数の取引銘柄.市場IDに該当する市場コード <BR>
     * 　@　@　@基準日：　@null（指定なし）<BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * 　@　@取得した値 > 0（終値あり）の場合 <BR>
     * 　@　@this.get取引銘柄(株式銘柄、市場、基準日)により取引銘柄を取得する。 <BR>
     * <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@＜get取引銘柄（）：引数設定＞ <BR>
     * <BR>
     * 　@　@　@株式銘柄：　@引数の取引銘柄.getProduct()の戻り値を株式銘柄にキャスト <BR>
     * 　@　@　@市場：　@引数の取引銘柄.getMarket()の戻り値 <BR>
     * 　@　@　@基準日：　@取引時間管理.get発注日( )で取得した発注日の前営業日 <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * 　@　@戻り値オブジェクトのプロパティを以下の通りに設定し、返却する。 
     * 　@　@------------------------------------------------------------------ <BR>
     * 　@　@＜戻り値プロパティ設定＞ <BR>
     * <BR>
     * 　@　@株式銘柄時価情報.時価 = 取得した値 <BR>
     * 　@　@株式銘柄時価情報.前日比 = 取得した当日終値 - get取引銘柄の戻り値.基準値（終値）<BR>
     * 　@　@　@※上記、基準値(終値) == 0 の場合はDouble.NaNをセット。<BR>
     * 　@　@株式銘柄時価情報.時価発表時間 = null <BR>
     * 　@　@株式銘柄時価情報.時価取得区分 = "当日終値" <BR>
     * 　@　@株式銘柄時価情報.時価区分 = "現在値" <BR>
     * 　@　@株式銘柄時価情報.市場コード = 引数の取引銘柄.市場IDに該当する市場コード <BR>
     * 　@　@------------------------------------------------------------------ <BR>
     * 　@　@上記以外の場合は、以降の処理を行う。 <BR>
     * <BR>
     * ２）　@時価サーバから時価を取得する。 <BR>
     * <BR>
     * ２－１）　@時価サービス（WEB3QuoteDataSupplierService）より、 <BR>
     * 　@　@　@引数のRealTypeに応じた時価情報（EqTypeQuoteData）を取得する。 <BR>
     * <BR>
     * 　@　@　@リアル指定（引数のRealType == "リアル"）の場合： <BR>
     * 　@　@　@　@WEB3QuoteDataSupplierService.getEqTypeQuote(引数の取引銘柄, "リアル"); <BR>
     * <BR>
     * 　@　@　@ディレイ指定（引数のRealType != "リアル"）の場合： <BR>
     * 　@　@　@　@WEB3QuoteDataSupplierService.getEqTypeQuote(引数の取引銘柄, "20分ディレイ"); <BR>
     * <BR>
     * ２－２）　@取得した時価情報を以下の優先順位で評価し、 <BR>
     * 　@　@　@値が付いている（==0でない）単価がある場合は、戻り値オブジェクトのプロパティを以下の通りに設定し、 <BR>
     * 　@　@返却する。 <BR>
     * <BR>
     * 　@　@------------------------------------------------------------------ <BR>
     * 　@　@＜評価順位＞ <BR>
     * <BR>
     * 　@　@１．現在値（EqTypeQuoteData.getCurrentPrice( )） <BR>
     * 　@　@２．売気配値（EqTypeQuoteData.getBidPrice( )） <BR>
     * 　@　@３．買気配値（EqTypeQuoteData.getAskPrice( )） <BR>
     * 　@　@------------------------------------------------------------------ <BR>
     * 　@　@＜戻り値プロパティ設定＞ <BR>
     * <BR>
     * 　@　@株式銘柄時価情報.時価 = 値が付いている値のうち、評価順位が最も高いもの <BR>
     * 　@　@株式銘柄時価情報.前日比 = EqTypeQuoteData.getChange( ) <BR>
     * 　@　@株式銘柄時価情報.時価発表時間 = 時価とした単価に対応する時価発表時間 <BR>
     * 　@　@　@※例：　@現在値を時価とする場合、EqTypeQuoteData.getCurrentPriceTime( )をセットする。 <BR>
     * 　@　@　@※nullが返された場合、nullをそのままセットする。 <BR>
     * 　@　@株式銘柄時価情報.時価取得区分 = "時価" <BR>
     * 　@　@株式銘柄時価情報.時価区分 = 時価とした単価に該当する時価区分 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@（現在値／売気配値／買気配値） <BR>
     * 　@　@株式銘柄時価情報.市場コード = 引数の取引銘柄.市場IDに該当する市場コード <BR>
     * 　@　@------------------------------------------------------------------ <BR>
     * 　@　@上記以外の場合は、以降の処理を行う。 <BR>
     * <BR>
     * ３）　@取引銘柄から、前日終値を取得する。 <BR>
     * <BR>
     * 　@　@戻り値オブジェクトのプロパティを以下の通りに設定し、返却する。 <BR>
     * 　@　@------------------------------------------------------------------ <BR>
     * 　@　@＜戻り値プロパティ設定＞ <BR>
     * <BR>
     * 　@　@株式銘柄時価情報.時価 = 引数の取引銘柄.getLastClosingPrice( )（取引銘柄.基準値（終値）） <BR>
     * 　@　@株式銘柄時価情報.前日比 = Double.NaN<BR>
     * 　@　@株式銘柄時価情報.時価発表時間 = null <BR>
     * 　@　@株式銘柄時価情報.時価取得区分 = "前日終値" <BR>
     * 　@　@株式銘柄時価情報.時価区分 = "前日終値" <BR>
     * 　@　@株式銘柄時価情報.市場コード = 引数の取引銘柄.市場IDに該当する市場コード <BR>
     * 　@　@------------------------------------------------------------------ <BR>
     * <BR>
     * @@param l_eqtypeTradedProduct<BR>
     * (取引銘柄)<BR>
     * @@param l_realType<BR>
     * (RealType)<BR>
     * @@param l_isLastClosingPriceUnconditionalyRead - (is終値テーブル無条件read)<BR>
     * 終値テーブルを無条件にreadするかどうかのフラグ
     * @@return WEB3EquityProduct<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3EquityProductQuote getProductQuote(EqTypeTradedProduct l_eqtypeTradedProduct, RealType l_realType, boolean l_isLastClosingPriceUnconditionalyRead)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductQuote(EqtypeTradedProduct, RealType, boolean)";
        log.entering(STR_METHOD_NAME);
        
        String l_strMarketCode = l_eqtypeTradedProduct.getMarket().getMarketCode();
        
        // 場引け後(取引時間管理.is市場開局時間帯() == false)の場合、当日終値を取得する。
        if (l_isLastClosingPriceUnconditionalyRead ||
            WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone() == false)
        {
            double l_dblTodayLastClosingPrice =
                this.getLastClosingPrice(l_eqtypeTradedProduct.getProduct().getProductId(),
                    l_strMarketCode,
                    null);
            
            // 終値ありの場合
            if (l_dblTodayLastClosingPrice > 0)
            {
                // 発注日を取得
                Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                WEB3GentradeBizDate l_genBizDate =
                    new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
                //取得した値 > 0（終値あり）の場合
                //　@　@this.get取引銘柄(株式銘柄、市場、基準日)により取引銘柄を取得する。
                EqtypeTradedProductParams l_tradeProduct = this.getTradedProduct(
                    (WEB3EquityProduct)l_eqtypeTradedProduct.getProduct(),
                    l_eqtypeTradedProduct.getMarket(),
                    l_genBizDate.roll(-1));

                // 戻り値オブジェクト生成
                WEB3EquityProductQuote l_productQuote = new WEB3EquityProductQuote();
                
                l_productQuote.setQuote(l_dblTodayLastClosingPrice);
                // 前日比
                if (l_tradeProduct.getLastClosingPrice() == 0.0D)
                {
                    l_productQuote.setComparedPreviousDay(Double.NaN);
                }
                else
                {
                    l_productQuote.setComparedPreviousDay(l_dblTodayLastClosingPrice - l_tradeProduct.getLastClosingPrice());
                }
                l_productQuote.setQuoteTime(null);
                l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.CLOSING_PRICE);
                l_productQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.CURRENT_PRICE);
                l_productQuote.setMarketCode(l_strMarketCode);
                    
                return l_productQuote;
            }
        }
        
        // 時価サーバから時価を取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3QuoteDataSupplierService l_quoteDataSupplierService =
            (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getQuoteDataSupplierService();
            
        WEB3EqTypeQuoteData l_quoteData = null;
        try
        {
            l_quoteData = l_quoteDataSupplierService.getEqTypeQuote(l_eqtypeTradedProduct, l_realType);
        }
        catch (NotFoundException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コード：[" + l_eqtypeTradedProduct.getInstitution().getInstitutionCode()
                + "] 銘柄ID：[" + l_eqtypeTradedProduct.getProduct().getProductId()
                + "] 市場コード：[" + l_strMarketCode
                + "に対応する時価情報が見つかりません。",
                l_exp);
        }
        
        // 1.5. 現在値、売気配値、買気配値の順序で価格情報を取得する
        //     （値が付いている（取得した値 > 0）の時点で、以降の価格情報は取得しない）。
        double l_dblMarketPrice = 0.0D;
        Timestamp l_tsQuoteTime = null;
        String l_strQuoteTypeDiv = null;
        
        // 1.5.1. getCurrentPrice
        l_dblMarketPrice = l_quoteData.getCurrentPrice();
        l_tsQuoteTime = l_quoteData.getCurrentPriceTime();
        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.CURRENT_PRICE;
        
        // 1.5.3. getBidPriceTime
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getBidPrice();
            l_tsQuoteTime = l_quoteData.getBidPriceTime();
	        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.BID_PRICE;
        }
        // 1.5.5. getAskPrice
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getAskPrice();
            l_tsQuoteTime = l_quoteData.getAskPriceTime();
	        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.ASK_PRICE;
        }
        
        WEB3EquityProductQuote l_productQuote = null;
        // 1.6. 現在値、売気配値、買気配値のいずれかを取得できた場合（取得した値 > 0の場合）
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
        else
        {
            // 1.7. getLastClosingPrice
            double l_dblLastClosingPrice = l_eqtypeTradedProduct.getLastClosingPrice();
            // 1.8. 前日終値を取得できた場合（取得した値 > 0の場合）
            if (l_dblLastClosingPrice > 0.0D)
            {
	            l_productQuote = new WEB3EquityProductQuote();
                l_productQuote.setQuote(l_dblLastClosingPrice);
                l_productQuote.setComparedPreviousDay(Double.NaN);
                l_productQuote.setQuoteTime(null);
                l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.LAST_CLOSING_PRICE);
                l_productQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.LAST_CLOSING_PRICE);
                l_productQuote.setMarketCode(l_strMarketCode);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }
    
    /**
     * (get時価)<BR>
     * 時価を取得する。取得順は以下の通り。<BR>
     * -----------------------------------------------<BR>
     * 場中：　@　@時価サーバ＞株式取引銘柄テーブル<BR>
     * 引け後：　@終値テーブル＞時価サーバ＞株式取引銘柄テーブル<BR>
     * -----------------------------------------------<BR>
     * <BR>
     * １）　@this.get時価(取引銘柄. boolean)にdelegateする。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get時価( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@取引銘柄：　@引数の取引銘柄<BR>
     * 　@　@　@is終値テーブル無条件read：　@false（readは条件付き）<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getCurrentPrice(EqTypeTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getCurrentPrice(EqTypeTradedProduct)";

        log.entering(STR_METHOD_NAME);
        return this.getCurrentPrice(l_tradedProduct, false);
    }
    
    /**
     * (get時価情報)<BR>
     * 指定内容に応じた時価情報を取得する。取得順は以下の通り。<BR>
     * -----------------------------------------------<BR>
     * 場中：　@　@時価サーバ＞株式取引銘柄テーブル<BR>
     * 引け後：　@終値テーブル＞時価サーバ＞株式取引銘柄テーブル<BR>
     * -----------------------------------------------<BR>
     * <BR>
     * １）　@this.get時価情報(取引銘柄, RealType, boolean)にdelegateする。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get時価情報( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@取引銘柄：　@引数の取引銘柄<BR>
     * 　@　@　@RealType：　@引数のRealType<BR>
     * 　@　@　@is終値テーブル無条件read：　@false（readは条件付き）<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト
     * @@param l_realType - (RealType)<BR>
     * リアル／ディレイ区分
     * @@return WEB3EquityProductQuote
     * @@throws WEB3BaseException
     */
    public WEB3EquityProductQuote getProductQuote(
        EqTypeTradedProduct l_tradedProduct,
        RealType l_realType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductQuote(EqtypeTradedProduct, RealType)";
        log.entering(STR_METHOD_NAME);
        return this.getProductQuote(l_tradedProduct, l_realType, false);
    }
    
    /**
     * (is増担保規制対象銘柄)<BR>
     * 指定銘柄が増担保規制対象であるかどうかを判定する。<BR>
     * －指定のis買建（＝建区分）に該当する規制有無を返却する。<BR>
     * 　@建区分指定なし時は、買建／売建のいずれかで規制がかかっている場合、規制ありと判定する。<BR>
     * －非信用銘柄の場合も、保証金率設定値を使用し一律で判定する。<BR>
     * <BR>
     * １）　@保証金率(*1)、現金保証金率(*2)の部店設定値を取得する。<BR>
     * <BR>
     * 　@　@引数の補助口座.get取引店()で、部店オブジェクトを取得する。<BR>
     * 　@　@以下、部店オブジェクトの同名プロパティを使用する。<BR>
     * <BR>
     * ２）　@指定のis買建（買建／売建）に対し増担保規制が適用されているかどうかをチェックし、<BR>
     * 　@　@　@チェック結果を返却する。<BR>
     * <BR>
     * ２－１）　@引数のis買建==true（買建）の場合<BR>
     * <BR>
     * 　@　@　@以下のいずれかに該当する場合、trueを返却する。<BR>
     * 　@　@　@　@－引数の取引銘柄.買保証金率 > 保証金率(*1)<BR>
     * 　@　@　@　@－引数の取引銘柄.買現金保証金率 > 現金保証金率(*2)<BR>
     * <BR>
     * 　@　@　@上記のいずれにも該当しない場合は、falseを返却する。<BR>
     * <BR>
     * ２－２）　@引数のis買建==false（売建）の場合<BR>
     * <BR>
     * 　@　@　@以下のいずれかに該当する場合、trueを返却する。<BR>
     * 　@　@　@　@－引数の取引銘柄.売保証金率 > 保証金率(*1)<BR>
     * 　@　@　@　@－引数の取引銘柄.売現金保証金率 > 現金保証金率(*2)<BR>
     * <BR>
     * 　@　@　@上記のいずれにも該当しない場合は、falseを返却する。<BR>
     * <BR>
     * ２－３）　@引数のis買建==null（建区分未指定）の場合<BR>
     * <BR>
     * 　@　@　@以下のいずれかに該当する場合、trueを返却する。<BR>
     * 　@　@　@　@－引数の取引銘柄.買保証金率 > 保証金率(*1)<BR>
     * 　@　@　@　@－引数の取引銘柄.買現金保証金率 > 現金保証金率(*2)<BR>
     * 　@　@　@　@－引数の取引銘柄.売保証金率 > 保証金率(*1)<BR>
     * 　@　@　@　@－引数の取引銘柄.売現金保証金率 > 現金保証金率(*2)<BR>
     * <BR>
     * 　@　@　@上記のいずれにも該当しない場合は、falseを返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 株式取引銘柄オブジェクト。
     * @@param isBuyContract - (is買建)<BR>
     * 買建フラグ。<BR>
     * （true：買建、false：売建、null：建区分未決定）
     * @@return boolean
     */
    public boolean isAdditionalCollateralRegulateProduct(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityTradedProduct l_tradedProduct,
        Boolean isBuyContract)
    {
        final String STR_METHOD_NAME =
            "isAdditionalCollateralRegulateProduct(WEB3GentradeSubAccount, WEB3EquityTradedProduct, Boolean)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_isAdditionalCollateralRegulateProduct = false;
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        double l_dblMarginDepositRate = l_branchRow.getMarginDepositRate();
        double l_dblCashMarginDepositRate = l_branchRow.getCashMarginDepositRate();
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        
        if (isBuyContract != null)
        {
            if (Boolean.TRUE.equals(isBuyContract))
            {
                if (l_tradedProductRow.getLongMarginDepositRate() > l_dblMarginDepositRate ||
                    l_tradedProductRow.getLongCashMarginDepositRate() > l_dblCashMarginDepositRate)
                {
                    l_isAdditionalCollateralRegulateProduct = true;
                }
            }
            else
            {
                if (l_tradedProductRow.getShortMarginDepositRate() > l_dblMarginDepositRate ||
                    l_tradedProductRow.getShortCashMarginDepositRate() > l_dblCashMarginDepositRate)
                {
                    l_isAdditionalCollateralRegulateProduct = true;
                }
            }
        }
        else
        {
            if (l_tradedProductRow.getLongMarginDepositRate() > l_dblMarginDepositRate ||
                l_tradedProductRow.getLongCashMarginDepositRate() > l_dblCashMarginDepositRate ||
                l_tradedProductRow.getShortMarginDepositRate() > l_dblMarginDepositRate ||
                l_tradedProductRow.getShortCashMarginDepositRate() > l_dblCashMarginDepositRate)
            {
                l_isAdditionalCollateralRegulateProduct = true;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isAdditionalCollateralRegulateProduct;
    }

    /**
     * (get取引銘柄)<BR>
     * 引数の条件に該当する取引銘柄Rowを取得し、取引銘柄インスタンスを返却する。 <BR>
     * <BR>
     * １）　@DB検索  <BR>
     * 　@株式取引銘柄テーブルを以下の条件で検索する。  <BR>
     * 　@[条件]  <BR>
     * 　@銘柄ID = 引数.株式銘柄.銘柄ID かつ <BR>
     * 　@市場ID = 引数.市場.市場ID かつ <BR>
     * 　@有効日 = 引数.基準日　@（フォーマット：YYYYMMDD） <BR>
     * <BR>
     * 　@※株式取引銘柄テーブルの検索結果が取得できなかった場合、 <BR>
     * 　@　@同条件で株式取引銘柄updqテーブルを検索する。 <BR>
     * <BR>
     * ２）　@取得したレコードを返却する。<BR>
     * @@param l_equityProduct - (株式銘柄)<BR>
     * 株式銘柄オブジェクト<BR>
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト<BR>
     * @@param l_datBaseDate - (基準日)<BR>
     * 基準日<BR>
     * @@return EqtypeTradedProductParams
     * @@throws WEB3SystemLayerException
     */
    public EqtypeTradedProductParams getTradedProduct(
        WEB3EquityProduct l_equityProduct,
        Market l_market,
        Date l_datBaseDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradedProduct(WEB3EquityProduct, Market, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_equityProduct == null || l_market == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //　@[条件]
        //　@銘柄ID = 引数.株式銘柄.銘柄ID かつ
        //　@市場ID = 引数.市場.市場ID かつ
        //　@有効日 = 引数.基準日　@（フォーマット：YYYYMMDD）
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" product_id = ? ");
        l_sbWhere.append(" and market_id = ? ");
        l_sbWhere.append(" and valid_until_biz_date = ? ");

        List l_lisWheres = new ArrayList();
        l_lisWheres.add(new Long(l_equityProduct.getProductId()));
        l_lisWheres.add(new Long(l_market.getMarketId()));
        l_lisWheres.add(WEB3DateUtility.formatDate(l_datBaseDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
        Object[] l_objWhere =  new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_objWhere);

        List l_lisRows = null;
        EqtypeTradedProductParams l_eqtypeTradedProductParams = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //株式取引銘柄テーブルを以下の条件で検索
            l_lisRows = l_processor.doFindAllQuery(
                EqtypeTradedProductRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);

            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                //株式取引銘柄テーブルの検索結果が取得できなかった場合
                //同条件で株式取引銘柄updqテーブルを検索する
                l_lisRows = l_processor.doFindAllQuery(
                    EqtypeTradedProductUpdqRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
                if (l_lisRows == null || l_lisRows.size() == 0)
                {
                    log.debug("テーブルに該当するデータがありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "テーブルに該当するデータがありません。");
                }
                EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow =
                    (EqtypeTradedProductUpdqRow)l_lisRows.get(0);
                l_eqtypeTradedProductParams = new EqtypeTradedProductParams();
                GtlUtils.copyRow2Params(l_eqtypeTradedProductUpdqRow, l_eqtypeTradedProductParams);
            }
            else
            {
                l_eqtypeTradedProductParams =
                    new EqtypeTradedProductParams((EqtypeTradedProductRow)l_lisRows.get(0));
            }

            //取得したレコードを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_eqtypeTradedProductParams;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
    }

    /**
     * (is翌日基準値受信)<BR>
     * <BR>
     * 翌日基準値受信完了かどうかのチェックを行う。 <BR>
     * <BR>
     * 以下の条件で、プロセス管理テーブルを検索する。<BR>
     * テーブルから一件或は複数件のレコードが取得できた場合、trueを返却する。<BR>
     * 取得できなかった場合、falseを返却する。<BR>
     * <BR>
     * [条件]<BR>
     * プロセスＩＤ = "0011：翌日基準値速報受信" <BR>
     * 証券会社コード = 引数.証券会社コード<BR>
     * <BR>
     * @@params l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * <BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException<BR>
     */
    public boolean isNextDayBasePriceMail(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNextDayBasePriceMail(String)";
        log.entering(STR_METHOD_NAME);

        String l_strWhere =
            " process_id = ? and institution_code = ? ";
        Object l_bindVars[] =
        {
    		WEB3ProcessIdDef.NEXT_DAY_BASE_PRICE_QUICK_REPORT_REC,
            l_strInstitutionCode
        };

        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    ProcessManagementRow.TYPE,
                    l_strWhere,
                    l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.entering(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //取得できなかった場合
        if (l_lisRows.size() < 1)
        {
            log.entering(STR_METHOD_NAME);
            return false;
        }
        //テーブルから一件或は複数件のレコードが取得できた場合、trueを返却する。
        else
        {
            log.entering(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (is大引け基準値受信完了)<BR>
     * 翌日基準値受信完了かどうかのチェックを行う。<BR>
     * <BR>
     * 以下の条件で、プロセス管理テーブルを検索する。<BR>
     * テーブルから一件或は複数件のレコードが取得できた場合、trueを返却する。<BR>
     * 取得できなかった場合、falseを返却する。<BR>
     * <BR>
     * [条件]<BR>
     * プロセスＩＤ = "0012：株式終値速報受信"<BR>
     * 証券会社コード = 引数.証券会社コード<BR>
     * @@params l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * <BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException<BR>
     */
    public boolean isBasePriceRecCompleted(String l_strInstitutionCode)
        throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "isBasePriceRecCompleted(String)";
        log.entering(STR_METHOD_NAME);

        String l_strWhere =
            " process_id = ? and institution_code = ? ";
        Object l_bindVars[] =
        {
    		WEB3ProcessIdDef.EQ_LAST_PRICE_QUICK_REPORT_REC,
            l_strInstitutionCode
        };

        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    ProcessManagementRow.TYPE,
                    l_strWhere,
                    l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.entering(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //取得できなかった場合
        if (l_lisRows.size() < 1)
        {
            log.entering(STR_METHOD_NAME);
            return false;
        }
        //テーブルから一件或は複数件のレコードが取得できた場合、trueを返却する。
        else
        {
            log.entering(STR_METHOD_NAME);
            return true;
        }
    }
}
@
