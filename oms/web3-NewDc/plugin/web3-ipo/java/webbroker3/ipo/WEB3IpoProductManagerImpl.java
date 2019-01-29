head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOプロダクトマネージャ(WEB3IpoProductManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 李頴淵 新規作成
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>050
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3IpoStopDef;
import webbroker3.common.define.WEB3LotStatusDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * IPOプロダクトマネージャクラス。<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 * 
 * IPO銘柄に対する操作を実装する。<BR>
 * <BR>
 * (*) IPOでは取引銘柄は使用しないため、getTradedProduct()、<BR>toTradedProduct()はスタブとする。
 */
public class WEB3IpoProductManagerImpl implements ProductManager 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3IpoProductManagerImpl.class);
    
    /**
     * @@roseuid 4113017900F7
     */
    public WEB3IpoProductManagerImpl() 
    {
     
    }
    
    /**
     * (get銘柄)
     * （getProductの実装）<BR>
     * <BR>
     * 指定したIPO銘柄ＩＤに該当する行をIPO銘柄テーブルより検索する。<BR>
     * 検索結果のIPO銘柄行オブジェクトを引数に指定して、IPO銘柄オブジェクトを<BR>生成する。<BR>
     * 生成したオブジェクトを返却する。<BR>
     * <BR>
     * [コンストラクタに指定する引数]<BR>
     * IPO銘柄行オブジェクト
     * @@param l_lngProductId - IPO銘柄ＩＤ
     * @@return Product
     * @@throws NotFoundException
     * @@roseuid 40BDA43E0298
     */
    public Product getProduct(long l_lngProductId) throws NotFoundException
    {
        final String STR_METHOD_NAME = " getProduct(long)";
        log.entering(STR_METHOD_NAME);
                    
        IpoProductRow l_productRow;
        try
        {
            l_productRow = IpoProductDao.findRowByPk(l_lngProductId);
            log.debug("l_productRow =" + l_productRow);

            log.exiting(STR_METHOD_NAME);
            return toProduct(l_productRow);
        }
        
        catch (DataFindException l_ex)
        {
            throw new NotFoundException("該当するIPO銘柄行が存在しない");
        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataNetworkException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }            
    }
    
    /**
     * (get有効銘柄)<BR>
     * 証券会社、IPO登録区分に該当する有効なIPO銘柄オブジェクト配列を<BR>
     * 取得する。<BR>
     * <BR>
     * １）　@IPO銘柄テーブル検索<BR>
     * 　@IPO銘柄テーブルを検索し、以下の条件に一致する行を取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@IPO銘柄テーブル.証券会社コード = 引数.証券会社コード<BR>
     * 　@IPO銘柄テーブル.IPO登録区分 = 引数.IPO登録区分<BR>
     * 　@IPO銘柄テーブル.削除フラグ = 削除でない（BooleanEnum.FALSE）<BR>
     * <BR>
     *   [取得順]<BR>
     *    公開日　@降順（：desc），<BR>
     *  　@銘柄コード　@昇順（：asc）<BR> 
     * <BR>
     * ２）　@IPO銘柄オブジェクト返却<BR>
     * 　@取得した各行オブジェクトにてIPO銘柄オブジェクトを生成(*1)し、<BR>
     * IPO銘柄オブジェクトの配列を返却する。<BR>
     * <BR>
     * 　@(*1)　@行オブジェクトにてIPO銘柄オブジェクトを生成<BR>
     * 　@this.to銘柄()にてIPO銘柄オブジェクトを生成する。<BR>
     * <BR>
     * 　@[to銘柄()に指定する引数]<BR>
     * 　@取得した行オブジェクト<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strRegType - IPO登録区分<BR>
     * <BR>
     * 　@1：新規上場<BR>
     * 　@2：既上場<BR>
     * 　@※ DBレイアウト「IPO銘柄テーブル」参照。
     * 
     * @@return webbroker3.ipo.WEB3IpoProductImpl[]
     * @@roseuid 40BEFBF503B8
     */
    public WEB3IpoProductImpl[] getOpenIpoProducts(String l_strInstitutionCode, String l_strRegType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenIpoProducts(String,String)";
        log.entering(STR_METHOD_NAME);
        
        QueryProcessor l_queryProcesser = null;
        WEB3IpoProductImpl[] l_ipoProduct = null;
        try
        {
            //IPO銘柄テーブル検索
            l_queryProcesser = Processors.getDefaultProcessor();
            String l_strWhere =
                    "institution_code = ? and " +
                    "ipo_regist_div = ? and " +
                    "delete_flag = ?";
                    
            String l_strOrderBy = " public_offering_date desc, product_code asc";
            
            Object l_bindVars[] =
                {   new String(l_strInstitutionCode),
                    new String(l_strRegType),
                    new Integer(BooleanEnum.IntValues.FALSE)};
            log.debug("new String(l_strInstitutionCode) = " + new String(l_strInstitutionCode));        
            log.debug("new String(l_strRegType) = " + new String(l_strRegType));
            log.debug("new Integer(BooleanEnum.IntValues.FALSE) = " + new Integer(BooleanEnum.IntValues.FALSE));
                    
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                IpoProductRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);
            
            int l_intSize = l_lisRows.size();
            log.debug("l_lngSize = " + l_intSize);
            
            if (l_intSize == 0)
            {
                return null;
//                log.exiting(STR_METHOD_NAME);
//
//                throw new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                    getClass().getName() + STR_METHOD_NAME);
            }
            
            //IPO銘柄オブジェクト返却
            List l_lisProductRows = new ArrayList();
            
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("各行オブジェクトにてIPO銘柄オブジェクトを生成");

                l_lisProductRows.add(this.toProduct((IpoProductRow)l_lisRows.get(i)));
            }
            
            l_ipoProduct = new WEB3IpoProductImpl[l_intSize];
            
            l_lisProductRows.toArray(l_ipoProduct);
        
            log.exiting(STR_METHOD_NAME);
            
            log.debug("l_ipoProduct.length = " + l_ipoProduct.length);
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("l_ipoProductid = " + l_ipoProduct[i].getProductId());
            }
            
            return l_ipoProduct;         
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }   

    }
    
    /**
     * (get取扱中有効銘柄)<BR>
     * 証券会社、IPO登録区分に該当する有効なIPO銘柄で、<BR>
     * 取扱中（公開日 > １ヶ月前）のものを配列で取得する。<BR>
     * <BR>
     * １）　@公開日の１ヶ月前取得<BR>
     * 現在日時(*1)の１ヶ月前のTimestampを取得する。<BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()にて取得する。<BR>
     * <BR>
     * 
     * ２）　@IPO銘柄テーブル検索<BR>
     * 　@IPO銘柄テーブルを検索し、以下の条件に一致する行を取得する。<BR>
     * <BR>
     * 　@[検索条件] 
     * 　@IPO銘柄テーブル.証券会社コード = 引数.証券会社コード　@And 
     * 　@IPO銘柄テーブル.IPO登録区分 = 引数.IPO登録区分　@And 
     * 　@IPO銘柄テーブル.削除フラグ = 削除でない（BooleanEnum.FALSE）　@And 
     * 　@（IPO銘柄テーブル.公開日 > １）で取得したTimestamp　@Or　@ IPO銘柄テーブル.公開日 = null）
     * <BR>
     *   [取得順]<BR>
     *    公開日　@降順（：desc），<BR>
     *  　@銘柄コード　@昇順（：asc）<BR> 
     * <BR>
     * ２）　@IPO銘柄オブジェクト返却<BR>
     * 　@取得した各行オブジェクトにてIPO銘柄オブジェクトを生成(*1)し、<BR>
     * IPO銘柄オブジェクトの配列を返却する。<BR>
     * <BR>
     * 　@(*1)　@行オブジェクトにてIPO銘柄オブジェクトを生成<BR>
     * 　@this.to銘柄()にてIPO銘柄オブジェクトを生成する。<BR>
     * <BR>
     * 　@[to銘柄()に指定する引数]<BR>
     * 　@取得した行オブジェクト<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strRegType - IPO登録区分<BR>
     * <BR>
     * 　@1：新規上場<BR>
     * 　@2：既上場<BR>
     * 　@※ DBレイアウト「IPO銘柄テーブル」参照。
     * 
     * @@return webbroker3.ipo.WEB3IpoProductImpl[]
     * @@roseuid 40D2B7520278
     */
    public WEB3IpoProductImpl[] getDealtInProcessOpenIpoProducts(String l_strInstitutionCode, String l_strRegType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDealtInProcessOpenIpoProducts(String,String)";
        log.entering(STR_METHOD_NAME);
        
        //公開日の１ヶ月前取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();   //現在日時
        
        //IPO銘柄テーブル検索
        QueryProcessor l_queryProcesser = null;
        WEB3IpoProductImpl[] l_ipoProduct = null;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            
            String l_strWhere =
                    "institution_code = ? and " +
                    "ipo_regist_div = ? and " +
                    "delete_flag = ? and " + 
                    "(public_offering_date > add_months(?, ?) or " +
                    "public_offering_date is null)";
                    
            String l_strOrderBy = " public_offering_date desc, product_code asc";
            
            Object l_bindVars[] =
                {   new String(l_strInstitutionCode),
                    new String(l_strRegType),
                    new Integer(BooleanEnum.IntValues.FALSE),
                    WEB3DateUtility.formatDate(l_tsCurrentTime,"yyyy/MM/dd"),
                    new Integer(-1)};
                    
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                IpoProductRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);
            
            int l_intSize = l_lisRows.size();
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //IPO銘柄オブジェクト返却
            List l_lisProductRows = new ArrayList();
            
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("各行オブジェクトにてIPO銘柄オブジェクトを生成");

                l_lisProductRows.add(this.toProduct((IpoProductRow)l_lisRows.get(i)));
            }
            
            l_ipoProduct = new WEB3IpoProductImpl[l_intSize];
            
            l_lisProductRows.toArray(l_ipoProduct);
        
            log.exiting(STR_METHOD_NAME);
            
            log.debug("l_ipoProduct.length = " + l_ipoProduct.length);
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("l_ipoProductid = " + l_ipoProduct[i].getProductId());
            }
            
            return l_ipoProduct;  
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        } 
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param l_arg0
     * @@param l_arg1
     * @@return TradedProduct
     * @@throws NotFoundException
     * @@roseuid 40BDA43E029A
     */
    public TradedProduct getTradedProduct(Product l_arg0, Market l_arg1) throws NotFoundException 
    {
        final String STR_METHOD_NAME = " getTradedProduct(Product,Market)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return null;
        
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param l_lngArg0
     * @@param l_lngArg1
     * @@return TradedProduct
     * @@throws NotFoundException
     * @@roseuid 40BDA43E029D
     */
    public TradedProduct getTradedProduct(long l_lngArg0, long l_lngArg1) throws NotFoundException 
    {
        final String STR_METHOD_NAME = " getTradedProduct(long,long)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param l_lngArg0
     * @@return TradedProduct
     * @@throws NotFoundException
     * @@roseuid 40BDA43E02A0
     */
    public TradedProduct getTradedProduct(long l_lngArg0) throws NotFoundException 
    {
        final String STR_METHOD_NAME = " getTradedProduct(long)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (to銘柄)
     * （toProductの実装）<BR>
     * <BR>
     * 指定のProductRowオブジェクトからProductオブジェクトを生成する。<BR>
     * <BR>
     * IPO銘柄行オブジェクトを引数に指定して、IPO銘柄オブジェクトを生成する。<BR>
     * 生成したオブジェクトを返却する。<BR>
     * <BR>
     * [コンストラクタに指定する引数]<BR>
     * IPO銘柄行オブジェクト
     * @@param l_ipoProductParams - IPO銘柄行オブジェクト
     * @@return Product
     * @@roseuid 40BDA43E02A2
     */
    public Product toProduct(Row l_ipoProductParams) 
    {
        final String STR_METHOD_NAME = " toProduct(Row)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return new WEB3IpoProductImpl(l_ipoProductParams);
        
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param l_arg0
     * @@return TradedProduct
     * @@roseuid 40BDA43E02A4
     */
    public TradedProduct toTradedProduct(Row l_arg0) 
    {
        final String STR_METHOD_NAME = " toTradedProduct(Row)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * （save銘柄）<BR>
     * <BR>
     * １） IPO銘柄行オブジェクト取得<BR>
     * 　@IPO銘柄.getDataSourceObject()にてIPO銘柄行を取得する。<BR>
     * <BR>
     * ２） 更新情報をセットする。<BR>
     * 　@IPO銘柄行の以下の項目に、値をセットする。<BR>
     * <BR>
     * 　@IPO銘柄行.更新者コード = 管理者.getInstanceFromログイン情報().get<BR>管理者コード()<BR>
     * 　@IPO銘柄行.更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * ３） DB更新<BR>
     * 　@IPO銘柄行オブジェクトの内容で、IPO銘柄テーブルを更新（update）する。<BR>
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@roseuid 40BEBC0B03A8
     */
    public void saveProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //IPO銘柄行オブジェクト取得
        IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProduct.getDataSourceObject();
        IpoProductParams l_ipoProductParams = new IpoProductParams(l_ipoProductRow); 
        
        //更新情報をセットする。
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();  
        
        try{
            QueryProcessor l_queryProcesser = null;
            l_queryProcesser = Processors.getDefaultProcessor();
            
            l_ipoProductParams.setLastUpdater(l_strAdministratorCode);
            l_ipoProductParams.setLastUpdatedTimestamp(l_currentTime);
            
            l_queryProcesser.doUpdateQuery(l_ipoProductParams);        
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }         
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * （saveNew銘柄）<BR>
     * <BR>
     * 指定されたIPO銘柄オブジェクトの内容で、IPO銘柄テーブルを更新（insert）する。<BR>
     * <BR>
     * １）　@IPO銘柄ＩＤ新規採番<BR>
     * 　@引数.IPO銘柄.setNewId()をコールする。<BR>
     * <BR>
     * ２）　@行オブジェクト取得<BR>
     * 　@引数.IPO銘柄.getDataSourceObject()にて行オブジェクトを取得する。<BR>
     *   <BR>
     *   　@２－１）　@取得した行オブジェクトに以下の通り、項目の初期値をセットする。<BR> 
　@   *     銘柄タイプ = ProductTypeEnum.IPO <BR>
　@   *     IPO停止 = 0：DEFAULT（開催中） <BR>
　@   *     削除フラグ = BooleanEnum.FALSE <BR>
　@   *     更新者コード = 管理者.getInstanceFromログイン情報().get管理者コード()<BR> 
　@   *     作成日時 = TradingSystem.getSystemTimestamp() <BR>
　@   *     更新日時 = TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * ３）　@DB更新<BR>
     * 　@取得した行オブジェクトの内容でIPO銘柄テーブルに行を挿入（insert）する。<BR>
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@roseuid 40BEEC4600F9
     */
    public void saveNewProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //IPO銘柄ＩＤ新規採番
        l_ipoProduct.setNewId();
        
        //行オブジェクト取得
        IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProduct.getDataSourceObject();
        log.debug("l_ipoProductRow.getIpoProductId() = " + l_ipoProductRow.getIpoProductId());
        IpoProductParams l_ipoProductParams =  new IpoProductParams(l_ipoProductRow);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();  
        
        //銘柄タイプ
        l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
        //抽選状態
        l_ipoProductParams.setLotStatus(WEB3LotStatusDef.DEFAULT);
        //IPO停止
        l_ipoProductParams.setIpoStop(WEB3IpoStopDef.DEFAULT);
        //削除フラグ
        l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
        //作成日時
        l_ipoProductParams.setCreatedTimestamp(l_currentTime);
        //更新日時
        l_ipoProductParams.setLastUpdatedTimestamp(l_currentTime);
        
        //DB更新
        QueryProcessor l_queryProcesser = null;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            
            l_queryProcesser.doInsertQuery(l_ipoProductParams);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        } 
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (validate期間重複登録)　@
     * 同一銘柄コードで、IPO期間が重なっている銘柄が既に登録していないか<BR>
     * チェックを行う。<BR>
     * <BR>
     * １）　@銘柄検索<BR>
     * 　@以下の条件でIPO銘柄テーブルを検索する。<BR>
     * 行が存在しない場合は、処理を終了(return)する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@IPO銘柄テーブル.証券会社コード = 引数.IPO銘柄.証券会社コード<BR>
     * 　@IPO銘柄テーブル.銘柄コード = 引数.IPO銘柄.銘柄コード<BR>
     * 　@IPO銘柄テーブル.削除フラグ = ”削除でない”（BooleanEnum.FALSE）<BR>
     * <BR>
     * ２）　@期間判定<BR>
     * 　@取得した各IPO銘柄Paramsについて、以下の処理を行う。<BR>
     * 　@２－１）　@訂正前の行Skip<BR>
     * 　@（IPO銘柄Params.IPO銘柄ＩＤ == 引数.IPO銘柄ＩＤ）の行は、以降の処理を<BR>
     * 実施しない。<BR>
     * <BR>
     * 　@２－２）　@IPO銘柄オブジェクト生成<BR>
     * 　@IPO銘柄Paramsオブジェクトを引数に指定し、IPO銘柄オブジェクトを生成する。<BR>
     * 　@※ new IPO銘柄(IPO銘柄Params)<BR>
     * <BR>
     * 　@２－３）　@既存行スケジュール判定<BR>
     * 　@IPO銘柄.isスケジュール決定()にて、既存行のスケジュールが決定しているか<BR>
     * 判定する。<BR>
     * 　@スケジュールが決定していない場合（IPO銘柄.isスケジュール決定() == false）、<BR>
     * 例外をスローし処理を終了（return）する。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01982<BR>
     * <BR>
     * 　@２－４）　@期間重複チェック<BR>
     * 　@　@○ 入力公開日が未定（公開日入力値 == null）で、以下の条件に当ては<BR>
     * まる場合、例外をスローする。<BR>
     * 　@　@[Error条件]<BR>
     * 　@　@　@（IPO銘柄Params.ブックビルディング開始日時 <= ブックビルディング<BR>
     * 開始日時入力値 <= IPO銘柄Params.公開日）<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00506<BR>
     * <BR>
     * 　@　@○ 入力公開日が決定済（公開日入力値 != null）で、以下のいずれかの<BR>
     * 条件に当てはまる場合、例外をスローする。<BR>
     * 　@　@[Error条件 1]<BR>
     * 　@　@　@（IPO銘柄Params.ブックビルディング開始日時 <= ブックビルディング<BR>
     * 開始日時入力値 <= IPO銘柄Params.公開日）<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00507<BR>
     * 　@　@[Error条件 2]<BR>
     * 　@　@　@（IPO銘柄Params.ブックビルディング開始日時 <= 公開日入力値 <= <BR>
     * IPO銘柄Params.公開日）<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00508<BR>
     * 　@　@[Error条件 3]<BR>
     * 　@　@　@（ブックビルディング開始日時入力値  <= IPO銘柄Params.ブックビルディング<BR>
     * 開始日時）&&<BR>
     * 　@　@　@（IPO銘柄Params.公開日 <= IPO銘柄Params.公開日入力値）<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00509<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * 
     * @@param l_strProductCode - 銘柄コード
     * 
     * @@param l_datBookbuildingStartDate - ブックビルディング開始日時
     * @@param l_datPublicOfferingDate - 公開日
     * @@param l_lngProductId - IPO銘柄ＩＤ
     * @@roseuid 40BF071E0127
     */
    public void validateDuplicateTerm(String l_strInstitutionCode, String l_strProductCode, Date l_datBookbuildingStartDate, Date l_datPublicOfferingDate, long l_lngProductId) 
                                          throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDuplicateTerm(String,String,Date,Date,long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //銘柄検索
            QueryProcessor l_queryProcesser = null;
            l_queryProcesser = Processors.getDefaultProcessor();
            
            String l_strWhere =
                    "institution_code = ? and " +
                    "product_code = ? and " +
                    "delete_flag = ?";
            
            Object l_bindVars[] =
                {   new String(l_strInstitutionCode),
                    new String(l_strProductCode),
                    new Integer(BooleanEnum.IntValues.FALSE)};
                    
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                IpoProductRow.TYPE,
                l_strWhere,
                l_bindVars);
            
            int l_intSize = l_lisRows.size();
            log.debug("l_lngSize = " + l_intSize);
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return;    
            }
            
            //期間判定                                
            for (int i= 0; i < l_intSize; i++)
            {
                log.debug("期間判定");
                IpoProductRow l_ipoProductRow = (IpoProductRow)l_lisRows.get(i);
                
                if (l_lngProductId != l_ipoProductRow.getIpoProductId())
                {
                    log.debug("l_lngProductId != l_ipoProductRow.getIpoProductId()");
                    log.debug("l_ipoProductRow.getIpoProductId() = " + l_ipoProductRow.getIpoProductId());
                    //IPO銘柄オブジェクト生成
                    WEB3IpoProductImpl l_ipoProduct = new WEB3IpoProductImpl(l_ipoProductRow);
                    //既存行スケジュール判定
                    if (!l_ipoProduct.isScheduleDecision())
                    {
                        log.debug("既存行スケジュール判定");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01982,
                            getClass().getName() + STR_METHOD_NAME
                        );
                    }
                    //期間重複チェック
                    if (l_datPublicOfferingDate == null && 
                        l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getBookbuildingStartDatetime()) >= 0 &&
                        l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getPublicOfferingDate()) <= 0)
                    {
                        log.debug("期間重複チェック");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00506,
                            getClass().getName() + STR_METHOD_NAME
                        );    
                    }
                    
                    if (l_datPublicOfferingDate != null)   //入力公開日が決定済
                    {
                        log.debug("l_datPublicOfferingDate != null");
                        //Error条件 1
                        if (l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getBookbuildingStartDatetime()) >= 0 &&
                           l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getPublicOfferingDate()) <= 0 )
                        {
                            log.debug("Error条件 1");
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00506,
                                getClass().getName() + STR_METHOD_NAME
                            ); 
                        }                                           
                        
                        //Error条件 2
                        if (l_datPublicOfferingDate.compareTo(l_ipoProductRow.getBookbuildingStartDatetime()) >= 0 && 
                           l_datPublicOfferingDate.compareTo(l_ipoProductRow.getPublicOfferingDate()) <= 0)
                        {
                            log.debug("Error条件 2");
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00506,
                                getClass().getName() + STR_METHOD_NAME
                            ); 
                        }
                        
                        //Error条件 3
                        if (l_datBookbuildingStartDate.compareTo(l_ipoProductRow.getBookbuildingStartDatetime()) <= 0 &&
                            l_datPublicOfferingDate.compareTo(l_ipoProductRow.getPublicOfferingDate()) >= 0)
                        {
                            log.debug("Error条件 3");
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00506,
                                getClass().getName() + STR_METHOD_NAME
                            ); 
                        }
                    }
                }
            }
        
            log.exiting(STR_METHOD_NAME);
                   
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }        
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager#searchProducts(com.fitechlabs.xtrade.plugin.tc.gentrade.Institution, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, java.lang.String, com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     */
    public List searchProducts(Institution arg0, ProductTypeEnum arg1, String arg2, PaginationQueryParamsSpec arg3, SortKeySpec arg4)
    {
        return null;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager#searchProductsBeginningWith(com.fitechlabs.xtrade.plugin.tc.gentrade.Institution, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, java.lang.String, com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     */
    public List searchProductsBeginningWith(Institution arg0, ProductTypeEnum arg1, String arg2, PaginationQueryParamsSpec arg3, SortKeySpec arg4)
    {
        return null;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager#searchProducts(com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, com.fitechlabs.xtrade.plugin.tc.gentrade.Market, com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     */
    public List searchProducts(ProductTypeEnum arg0, Market arg1, PaginationQueryParamsSpec arg2, SortKeySpec arg3)
    {
        return null;
    }
}
@
