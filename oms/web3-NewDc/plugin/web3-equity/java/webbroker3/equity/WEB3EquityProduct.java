head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式銘柄(WEB3EquityProduct.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 中尾寿彦(SRA) 新規作成
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1177
Revesion History : 2009/09/10 孟亞南 (中訊) モデルNo.1332
Revesion History : 2009/09/21 車進 (中訊) モデル No.1336
Revesion History : 2009/10/02 肖志偉 (中訊) モデル No.1350
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式銘柄）。<BR>
 * <BR>
 * 株式銘柄情報
 * @@version 1.0
 */
public class WEB3EquityProduct extends EqTypeProductImpl
{
   
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityProduct.class);
        
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    private String productCode;
   
    /**
     * (証券会社コード)<BR>
     * 証券会社コード
     */
    private String institutionCode;
   
    /**
     * (優先市場)<BR>
     * 銘柄の優先市場オブジェクト
     */
    private WEB3GentradeMarket primaryMarket = null;
    
    /**
     * コンストラクタ<BR>
     * @@param productId 銘柄ID
     * @@throws DataQueryException
     * @@throws DataNetworkException
    *  @@roseuid 4042EC8402B6
     */
    public WEB3EquityProduct(long l_lngProductId)
            throws DataQueryException, DataNetworkException
    { 
        super(l_lngProductId);    
    }
    /**
     * コンストラクタ<BR>
     * @@param l_productRow
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 4042EC8500B2
     */
    public WEB3EquityProduct(ProductRow l_productRow)
            throws DataQueryException, DataNetworkException
    { 
        super(l_productRow);    
    }
    /**
     * コンストラクタ<BR>
     * @@param l_eqtypeProductRow
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 4042EC850277
     */
    public WEB3EquityProduct(EqtypeProductRow l_eqtypeProductRow)
            throws DataQueryException, DataNetworkException
    { 
        super(l_eqtypeProductRow);
    }
    /**
     * (get権利落日)<BR>
     * 銘柄の権利落日を取得する。<BR>
     *  <BR>
     * this.株式銘柄行.決算日の２営業日前の日付を返却する。<BR>
     * @@return Date
     */
    public Date getDevidendRightDate() throws WEB3BaseException
    {
        EqtypeProductParams l_eqtypeProductParams = (EqtypeProductParams)this.getDataSourceObject();
        WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_eqtypeProductParams.getYearlyBooksClosingDate());
        return l_gentradeBizDate.roll(-2);
    }
    /**
     * (getミニ株市場)<BR>
     * ミニ株市場を取得する。<BR>                                                                                                            
     * 当該銘柄がミニ株を取り扱っていない場合はnullを返却する。 <BR>
     *  <BR>
     * １）　@取引銘柄テーブル<BR>
     *       （eqtype_traded_product，eqtype_traded_product_updq）検索<BR>
     * 　@発注日（取引時間管理.get発注日()）に該当する株式取引銘柄テーブル※1より、<BR>
     * 以下の条件に当てはまる株式取引銘柄行オブジェクト※2を取得する。<BR>
     *  <BR>
     * 　@※1　@発注日に該当する株式取引銘柄テーブル   <BR>
     * 　@　@株式取引銘柄マスタテーブル＞株式取引銘柄マスタupdqテーブルの順で、<BR>
     * 　@　@発注日に該当するデータを検索する。<BR>
     *  <BR>
     * 　@－株式取引銘柄マスタテーブル<BR>
     * 　@　@[検索条件] <BR>
     * 　@　@銘柄ID = 株式銘柄.getProductId() And <BR>
     * 　@　@有効日 = 発注日（取引時間管理.get発注日()） <BR>
     *  <BR>                                                                                                                                          
     * 　@－株式取引銘柄マスタupdqテーブル<BR>
     * 　@　@[検索条件] <BR>
     * 　@　@銘柄ID = 株式銘柄.getProductId() And <BR>
     * 　@　@有効日 = 発注日（取引時間管理.get発注日()） <BR>
     *   <BR>                                                                                                                 
     * 　@※2 株式取引銘柄行オブジェクト<BR>                                                                        
     * 　@EqtypeTradedProductParams（株式取引銘柄マスタテーブルから検索した場合）または、 <BR>                                                                  
     * 　@EqtypeTradedProductUpdqParams（株式取引銘柄マスタupdqテーブルから検索した場合）<BR>                                                                   
     *  <BR>                                                                                                                                                   
     * ２）　@ミニ株市場チェック  <BR>                                                                                                                          
     * 　@１）で取得した株式取引銘柄行オブジェクト※2のうち、<BR>                                                                                               
     * ミニ株取扱 == BooleanEnum.TRUE（1：ミニ株取扱）<BR>　@
     *  である取引銘柄行オブジェクトを取得する。<BR>                                                           
     * ミニ株取扱 == BooleanEnum.TRUE（1：ミニ株取扱）の行オブジェクトがない場合は、<BR>             
     * ミニ株を取り扱っていない銘柄であると判定し、nullを返却する。<BR>                              
     *  <BR>                                                                                                                         
     * また、ミニ株取扱 == BooleanEnum.TRUE（1：ミニ株取扱）<BR>
     * の行オブジェクトが複数ある場合は、<BR>                                                             
     * データ不整合と判定し、例外をスローする。<BR>  
     * class: WEB3SystemLayerException <BR>
     * tag:   SYSTEM_ERROR_80006 <BR>                                                                                                       
     * <BR>                                                                                                                                      
     * ３）　@市場オブジェクト返却                                                                                      
     * 　@２）で取得した取引銘柄行.getMarketId() にて市場ＩＤを取得する。<BR>                                               
     * 市場ＩＤに該当する市場オブジェクトを返却する。<BR> 
     * @@return Market
     */
    public Market getMiniStockMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMiniStockMarket()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
			FinApp finApp = (FinApp)Services.getService(FinApp.class);
			String l_strWhere = "product_id = ? and (valid_until_biz_date = ?)";
			Object l_objWhere[] = 
			{
				new Long(this.getProductId()),
				WEB3DateUtility.formatDate(WEB3GentradeTradingTimeManagement.getOrderBizDate(),"yyyyMMdd"),
			};
			List l_list = null;
			l_list = Processors.getDefaultProcessor().doFindAllQuery(
				 EqtypeTradedProductRow.TYPE,
				 l_strWhere,
				 l_objWhere);
                
			int l_intLength = l_list.size();
			int l_intCount = 0;
			int l_intFlag = 0;
			for (int i = 0; i < l_intLength; i++)
			{
				EqtypeTradedProductParams l_eqtypeTradedProductParams = 
					new EqtypeTradedProductParams((EqtypeTradedProductRow)l_list.get(i));
				if(BooleanEnum.TRUE.equals(l_eqtypeTradedProductParams.getMiniStockCanDealt()))
				{
					l_intCount++;
					l_intFlag = i;
				}
			}
			if(l_intCount == 1)
			{
				EqtypeTradedProductParams l_eqtypeTradedProductParams = 
					new EqtypeTradedProductParams((EqtypeTradedProductRow)l_list.get(l_intFlag));
				long l_lngMarketId = l_eqtypeTradedProductParams.getMarketId();
				return finApp.getFinObjectManager().getMarket(l_lngMarketId);
			}
			else if (l_intCount == 0)
			{
				l_list = Processors.getDefaultProcessor().doFindAllQuery(
					EqtypeTradedProductUpdqRow.TYPE,
					l_strWhere,
					l_objWhere);
                
				l_intLength = l_list.size();
				l_intCount = 0;
				l_intFlag = 0;
				for (int i = 0; i < l_intLength; i++)
				{
					EqtypeTradedProductUpdqParams l_eqtypeTradedProductParams = 
						new EqtypeTradedProductUpdqParams((EqtypeTradedProductUpdqRow)l_list.get(i));
					if(BooleanEnum.TRUE.equals(l_eqtypeTradedProductParams.getMiniStockCanDealt()))
					{
						l_intCount++;
						l_intFlag = i;
					}
				}
				if (l_intCount == 0)
				{
					return null;
				}
				else if (l_intCount == 1)
				{
					EqtypeTradedProductUpdqParams l_eqtypeTradedProductParams = 
						new EqtypeTradedProductUpdqParams((EqtypeTradedProductUpdqRow)l_list.get(l_intFlag));
					long l_lngMarketId = l_eqtypeTradedProductParams.getMarketId();
					return finApp.getFinObjectManager().getMarket(l_lngMarketId);
				}
				else
				{
					throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80006,
						STR_METHOD_NAME);
				}
			}
			else
			{
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80006,
					STR_METHOD_NAME);
			}
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException(
                "System exception while searching product with market id :",
                l_ex);
        }
        catch(NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);  
        }
    }

    /**
     * (get優先市場時価情報)<BR>
     * <BR>
     * 優先市場の順に時価情報（時価取得区分、時価、時価発表時間）を取得する。<BR>
     * 全ての市場で時価情報を取得できなかった場合は、nullを返す。<BR>
     * <BR>
     * シーケンス図「（株式銘柄）get優先市場時価情報」参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@return WEB3EquityProductQuote
     */
    public WEB3EquityProductQuote getPrimaryMarketProductQuote(
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrimaryMarketProductQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        // getPrimaryMarket
        Market l_primaryMarket = this.getPrimaryMarket();
        
        // 1.1. getTradedMarkets
        Market[] l_markets = this.getTradedMarkets();
        // 1.2. getPrimaryMarket() == null(優先市場設定なし) かつ getTradedMarket( )==null（取扱市場なし）の場合、「取扱市場なし」の例外をthrowする。
        if (l_primaryMarket == null && l_markets == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01365,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1.3. 時価取得対象市場一覧を決定する。
        Vector l_vecMarkets = new Vector();
        
        // this.getPrimaryMarket() ≠ nullの場合のみ、
        // this.getPrimaryMarket().市場コードを「時価取得対象市場一覧」の先頭に設定する。
        if (l_primaryMarket != null)
        {
            // 先頭に設定
            l_vecMarkets.add(l_primaryMarket.getMarketCode());
        }
        
        boolean l_blnNNM = false;
        for (int i = 0;i < l_markets.length;i++)
        {
            if (WEB3MarketCodeDef.TOKYO.equals(l_markets[i].getMarketCode()) ||
                WEB3MarketCodeDef.OSAKA.equals(l_markets[i].getMarketCode()) ||
                WEB3MarketCodeDef.NAGOYA.equals(l_markets[i].getMarketCode()) ||
			    WEB3MarketCodeDef.JASDAQ.equals(l_markets[i].getMarketCode()))
            {
				l_vecMarkets.add(l_markets[i].getMarketCode());
            }
            else if (WEB3MarketCodeDef.NNM.equals(l_markets[i].getMarketCode()))
            {
				l_blnNNM = true;
            }
        }
        if (l_blnNNM == true)
        {
			l_vecMarkets.add(WEB3MarketCodeDef.NNM);
        }
        // 1.4. （１）～（５）の全市場が当該銘柄の取扱可能市場に含まれていない場合、
        // 　@「優先市場一覧に取扱市場なし」の例外をthrowする。
        int l_intSize = l_vecMarkets.size();
        if (l_intSize < 1)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01366,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1.5. 時価取得対象市場一覧の要素数分、LOOP。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
           (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
        // 取引カレンダコンテキストにセットされている市場コードを退避させる
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        String l_strOriginalMarketCode = l_context.getMarketCode();
        
        for (int i = 0;i < l_intSize;i++)
        {
            String l_strMarketCode = (String)l_vecMarkets.get(i);
            // reset市場コード
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            // 1.5.1. get取引銘柄(証券会社 : 証券会社, 銘柄コード : String, 市場コード : String)
            WEB3EquityTradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct =
                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                        this.getInstitution(),
                        this.getProductCode(),
                        l_strMarketCode);
            }
            catch (NotFoundException l_exp)
            {
                // 取引カレンダコンテキストの市場コードを元の設定に戻す
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOriginalMarketCode); 
                continue;
			}
            
            // 1.5.2. get時価情報(補助口座 : 補助口座)
            WEB3EquityProductQuote l_productQuote = null;
            try
            {
                l_productQuote = l_tradedProduct.getProductQuote(l_subAccount);
            }
            catch (WEB3BusinessLayerException l_exp)
            {
                if (WEB3ErrorCatalog.BUSINESS_ERROR_01367.equals(l_exp.getErrorInfo()))
                {
                    continue;
                }
                else
                {
                    // 取引カレンダコンテキストの市場コードを元の設定に戻す
                    WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOriginalMarketCode);
                    throw l_exp;
                }
            }
            
            // 1.5.3. 戻り値≠null（時価情報が取得できた場合）
            if (l_productQuote != null)
            {
                // 取引カレンダコンテキストの市場コードを元の設定に戻す
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOriginalMarketCode);
                
                return l_productQuote;
            }
        }

        // 取引カレンダコンテキストの市場コードを元の設定に戻す
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOriginalMarketCode);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get優先市場)<BR>
     * <BR>
     * (getPrimaryMarket()のオーバーライド)<BR>
     * 銘柄.優先市場に該当する市場IDの市場オブジェクトを返却する。<BR>
     * ※銘柄.優先市場 == nullの場合、nullを返却する。<BR>
     * <BR>
     * @@return WEB3GentradeMarket<BR>
     */
    public Market getPrimaryMarket()
    {
        final String STR_METHOD_NAME = "getPrimaryMarket()";
        log.entering(STR_METHOD_NAME);
        
        if(primaryMarket == null)
        {
            try
            {
                if (m_productRow.getPrimaryMarketIdIsNull() == false)
                {
                    primaryMarket =
                        (WEB3GentradeMarket)GtlUtils.getFinObjectManager().getMarket(m_productRow.getPrimaryMarketId());
                }
            }
            catch(NotFoundException nfe)
            {
                log.debug("銘柄テーブル.優先市場:[" + m_productRow.getPrimaryMarketId() +
                    "]に該当する市場オブジェクトなし");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return primaryMarket;
    }
    
    /**
     * (get取引市場一覧)<BR>
     * <BR>
     * (getTradedMarkets()のオーバーライド)<BR>
     * this.銘柄IDに該当する取引銘柄テーブルのデータを取得し、<BR>
     * 各市場IDに該当する市場オブジェクトの一覧を返却する。<BR>
     * <BR>
     * this.銘柄IDに該当する取引銘柄が存在しない場合、nullを返却する。<BR>
     * <BR>
     * @@return WEB3GentradeMarket[]<BR>
     */
    public Market[] getTradedMarkets()
    {
        final String STR_METHOD_NAME = "getTradedMarkets()";
        log.entering(STR_METHOD_NAME);
        
        EqtypeTradedProductRow row = null;
        List rows = null;
        try
        {
            rows = EqtypeTradedProductDao.findRowsByProductId(m_productRow.getProductId());
        }
        catch(DataException dex)
        {
            String msg = "銘柄IDに該当する株式取引銘柄一覧の取得に失敗しました : " + m_productRow.getProductId();
            log.debug(msg);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        FinObjectManager finObjMgr = GtlUtils.getFinObjectManager();
        TreeMap l_map = new TreeMap();
        for(int i = 0; i < rows.size(); i++)
        {
            try
            {
                 row = (EqtypeTradedProductRow)rows.get(i);
                WEB3GentradeMarket l_market =
                     (WEB3GentradeMarket)finObjMgr.getMarket(row.getMarketId());
                l_map.put(Integer.valueOf(l_market.getMarketCode()), l_market);
            }
            catch(NotFoundException nfex)
            {
                String msg = "取引銘柄.市場IDに該当する市場オブジェクトの取得に失敗しました : " + row.getMarketId();
                log.error(msg, nfex);
                throw new RuntimeSystemException(msg, nfex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        WEB3GentradeMarket markets[] = new WEB3GentradeMarket[l_map.size()];
        l_map.values().toArray(markets);
        return markets;
    }
    
    /**
     * (is募集銘柄)<BR>
     * <BR>
     * 募集銘柄かどうか判別する。 <BR>
     * <BR>
     * 募集銘柄の場合はtrueを、以外falseを返却する。 <BR>
     * <BR>
     * １）募集銘柄判定 <BR>
     * 　@this.銘柄コードの5byte目が1の場合、trueを返却する。 <BR>
     * 　@以外、falseを返却する。 <BR>
     * @@return boolean
     */
    public boolean isSubscriptionProduct()
    {
        final String STR_METHOD_NAME = "isSubscriptionProduct()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        // 募集銘柄判定
        return this.getProductCode().endsWith("1");
    }

    /**
     * (get権利付き最終日)<BR>
     * 銘柄の権利付き最終日を取得する。<BR>
     *  <BR>
     * 権利落日（this.get権利落日()の戻り値）の前営業日の日付を返却する。<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getRightCondOrderEndDay() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRightCondOrderEndDay()";
        log.entering(STR_METHOD_NAME);

        //this.get権利落日()の戻り値
        Date l_datDevidendRightDate = this.getDevidendRightDate();

        Timestamp l_tsDevidendRightDate = new Timestamp(l_datDevidendRightDate.getTime());

        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(l_tsDevidendRightDate);

        // 権利落日（this.get権利落日()の戻り値）の前営業日の日付を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_gentradeBizDate.roll(-1);
    }
}
@
