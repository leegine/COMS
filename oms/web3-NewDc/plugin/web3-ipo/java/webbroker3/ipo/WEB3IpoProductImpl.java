head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : IPO銘柄(WEB3IpoProductImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2004/08/11 李頴淵 新規作成
Revesion History : 2004/12/27 坂上(SRA) 残対応>>>037
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>057
Revesion History : 2005/01/06 坂上(SRA) 残対応>>>059
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>060,067
                   2006/10/16 徐宏偉(中訊) モデル  No.158
                   2006/11/22 何文敏 (中訊) モデル No.165
Revesion History : 2010/09/21 趙天月 (中訊) モデル No.180
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.define.WEB3IpoStopDef;
import webbroker3.common.define.WEB3LotStatusDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProvisionalValueDivDef;
import webbroker3.common.define.WEB3PublicMarketDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3IpoScheduleDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO銘柄クラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3IpoProductImpl implements Product
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3IpoProductImpl.class);
            
    /**
     * IPO銘柄行オブジェクト<BR>
     * ※ IPO銘柄ParamsクラスはDDLより自動生成する。
     */
    private IpoProductParams ipoProductRow;
    
    /**
     * @@roseuid 411301780038
     */
    public WEB3IpoProductImpl() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * <BR>
     * 指定したIPO銘柄ＩＤに該当する行をIPO銘柄テーブルより検索する。<BR>
     * 検索結果のIPO銘柄行オブジェクトを引数に指定して、コンストラクタをコール
     * <BR>する。<BR>
     * コンストラクタの戻り値を返却する。<BR>
     * @@param l_lngIpoProductId - IPO銘柄ID
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40BDB1C30315
     */
    public WEB3IpoProductImpl(long l_lngIpoProductId) throws DataFindException, DataQueryException, DataNetworkException
    {

        this(IpoProductDao.findRowByPk(l_lngIpoProductId));
                  
    }
    
    /**
     * コンストラクタ。<BR>
     * <BR>
     * 行指定行オブジェクトをプロパティにセットし、インスタンスを生成する。
     * @@param l_ipoProductParams - IPO銘柄行オブジェクト
     * ※ IPO銘柄ParamsクラスはDDLより自動生成する。
     * 
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40BDB0C20363
     */
    public WEB3IpoProductImpl(Row l_ipoProductParams) 
    {
        final String STR_METHOD_NAME = " WEB3IpoProductImpl(Row)";
        log.entering(STR_METHOD_NAME);
        
        ipoProductRow = new IpoProductParams((IpoProductRow)l_ipoProductParams);
        log.debug("ipoProductRow = " + ipoProductRow);
                
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.IPO銘柄行を返却する。
     * @@return Object
     * @@roseuid 40BDA40702D7
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return ipoProductRow;       
    }
    
    /**
     * (get初期化行)<BR>
     * （static method）
     * @@return webbroker3.ipo.data.IpoProductParams
     * @@roseuid 40BEF64403B8
     */
    public static IpoProductParams getInitializationParams() 
    {
        final String STR_METHOD_NAME = " getInitializationParams()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return new IpoProductParams();
    }
    
    /**
     * (getIPO銘柄ＩＤ)
     * （getProductIdの実装）<BR>
     * <BR>
     * this.IPO銘柄行.IPO銘柄ＩＤ を返却する。
     * @@return long
     * @@roseuid 40BDA40702D8
     */
    public long getProductId() 
    {
        final String STR_METHOD_NAME = " getProductId()";
        log.entering(STR_METHOD_NAME);      
        
        log.exiting(STR_METHOD_NAME);
        return ipoProductRow.getIpoProductId();
    }
    
    /**
     * (get銘柄タイプ)
     * （getProductTypeの実装）<BR>
     * <BR>
     * this.IPO銘柄行.銘柄タイプ を返却する。
     * @@return ProductTypeEnum
     * @@roseuid 40BDA407030A
     */
    public ProductTypeEnum getProductType() 
    {
        final String STR_METHOD_NAME = " getProductType()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return ipoProductRow.getProductType();
    }
    
    /**
     * (get購入申込単位)<BR>
     * （getLotSizeの実装）<BR>
     * <BR>
     * this.IPO銘柄行.購入申込単位 を返却する。
     * @@return double
     * @@roseuid 40BDA40702F6
     */
    public double getLotSize() 
    {
        final String STR_METHOD_NAME = " getLotSize()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        if(ipoProductRow.getLotSizeIsNull())
        {
            return (0.0D / 0.0D);
        }
        else
        {
            return ipoProductRow.getLotSize();
        }     
    }
    
    /**
     * (get公開市場)<BR>
     * this.IPO銘柄行.市場コード を返却する。
     * @@return String
     * @@roseuid 40BDA4070308
     */
    public String getPublicMarket() 
    {
        final String STR_METHOD_NAME = " getPublicMarket()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return ipoProductRow.getPublicMarket();
    }
    
    /**
     * (get銘柄名)<BR>
     * （getStandardNameの実装）<BR>
     * <BR>
     * this.IPO銘柄行.銘柄名 を返却する。
     * @@return String
     * @@roseuid 40BDA4070315
     */
    public String getStandardName() 
    {
        final String STR_METHOD_NAME = " getStandardName()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return ipoProductRow.getStandardName();
    }
    
    /**
     * (get当社取扱数量)<BR>
     * this.IPO銘柄行.当社取扱数量 を返却する。
     * @@return double
     * @@roseuid 40F519310345
     */
    public double getDealingQuantity() 
    {
        final String STR_METHOD_NAME = " getDealingQuantity()";
        log.entering(STR_METHOD_NAME);
        
        if(ipoProductRow.getDealingQuantityIsNull())
        {
            return (0.0D / 0.0D);
        }
        else
        {
            return ipoProductRow.getDealingQuantity();
        }  
    }
    
    /**
     * (get証券会社)<BR>
     * （getInstitutionの実装）<BR>
     * <BR>
     * this.IPO銘柄行.証券会社コードに該当する証券会社オブジェクトを返却する。
     * @@return Institution
     * @@roseuid 40BDA4070318
     */
    public Institution getInstitution()
    {
        final String STR_METHOD_NAME = " getInstitution()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        String l_strInstitutionCode = ipoProductRow.getInstitutionCode();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Institution l_institution;

        try
        {
            l_institution =
                l_finApp.getAccountManager().getInstitution(
                    l_strInstitutionCode);
            log.debug("証券会社オブジェクトを取得する");
            log.exiting(STR_METHOD_NAME);
            return l_institution;        
        }
        catch (NotFoundException l_ex)
        {
            log.debug("証券会社オブジェクトを取得しない");
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
        }                
    }
    
    /**
     * (getIPOスケジュール)
     * IPOスケジュールを取得する。<BR>
     * <BR>
     * [IPOスケジュール]<BR>
     * 1：　@ブックビルディング開始前<BR>
     * 2：　@ブックビルディング期間中<BR>
     * 3：　@ブックビルディング終了<BR>
     * 4：　@公開価格決定<BR>
     * 5：　@抽選終了<BR>
     * 6：　@購入申込期間中<BR>
     * 7：　@購入申込終了<BR>
     * 8：　@公開<BR>
     * <BR>
     * １）　@ブックビルディング開始前の判定<BR>
     * 　@－（this.isブックビルディング開始() == false）の場合、<BR>
     * 　@　@　@IPOスケジュール.”ブックビルディング開始前”を返却する。<BR>
     * <BR>
     * ２）　@ブックビルディング期間中の判定<BR>
     * 　@－（this.isブックビルディング開始() == true && <BR>
     * 　@　@　@this.isブックビルディング終了() == false）の場合、<BR>
     * 　@　@　@IPOスケジュール.”ブックビルディング期間中”を返却する。<BR>
     * <BR>
     * ３）　@ブックビルディング終了の判定<BR>
     * 　@－（this.isブックビルディング終了() == true &&<BR>
     * 　@　@　@this.is公開価格決定() == false）の場合、<BR>
     * 　@　@　@IPOスケジュール.”ブックビルディング終了”を返却する。<BR>
     * <BR>
     * ４）　@公開価格決定の判定<BR>
     * 　@－（this.is公開価格決定() == true &&<BR>
     * 　@　@　@this.is新規抽選終了() == false）の場合、<BR>
     * 　@　@　@IPOスケジュール.”公開価格決定”を返却する。<BR>
     * <BR>
     * ５）　@抽選終了の判定<BR>
　@   * －（this.is新規抽選終了() == true） && <BR>
　@　@ *     this.is購入申込開始（当社設定）() == false）の場合、<BR> 
　@　@ *     IPOスケジュール.”抽選終了”を返却する。<BR> 
     * <BR>
     * ６）　@購入申込期間中の判定<BR>
     * 　@－（this.is購入申込開始（当社設定）() == true &&<BR>
     * 　@　@　@this.is購入申込終了（当社設定）() == false）の場合、<BR>
     * 　@　@　@IPOスケジュール.”購入申込期間中”を返却する。<BR>
     * <BR>
     * ７）　@購入申込終了の判定<BR>
     * 　@－（this.is購入申込終了（当社設定）() == true &&<BR>
     * 　@　@　@this.is公開済() == false）の場合、<BR>
     * 　@　@　@IPOスケジュール.”購入申込終了”を返却する。<BR>
     * <BR>
     * ８）　@公開の判定<BR>
     * 　@－（this.is公開済() == true）の場合、<BR>
     * 　@　@　@IPOスケジュール.”公開”を返却する。<BR>
     * <BR>
     * @@return String
     * @@roseuid 40C69A6E01BB
     */
    public String getIpoSchedule() 
    {
        final String STR_METHOD_NAME = " getIpoSchedule()";
        log.entering(STR_METHOD_NAME);
        //１）　@ブックビルディング開始前の判定
        if (!this.isBookbuildingStart())
        {
            log.debug("ブックビルディング開始前の判定");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.BOOKBUIDING_START_BEFORE;
        }
        
        //２）　@ブックビルディング期間中の判定
        if (this.isBookbuildingStart() && 
            !this.isBookbuildingEnd())
        { 
            log.debug("ブックビルディング期間中の判定");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.BOOKBUIDING_TERM;
        }
        
        //３）　@ブックビルディング終了の判定
        if (this.isBookbuildingEnd() && 
            !this.isPublicPriceSettle())
        {
            log.debug("ブックビルディング終了の判定");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.BOOKBUIDING_END;
        }
        
        //４）　@公開価格決定の判定
        //仮条件区分が円の時
        if (this.isPublicPriceSettle() && !this.isNewLotteryEnd())
        {   
            log.debug("公開価格決定の判定");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.PUBLIC_PRICE_DECISION;
        }
        
        //５）　@抽選終了の判定
        log.debug("this.isNewLotteryEnd() = " + this.isNewLotteryEnd());
        log.debug("this.isOfferStart() = " + this.isOfferStart());
        if (this.isNewLotteryEnd() &&
            !this.isOfferStart())
        {
            log.debug("抽選終了の判定");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.LOTTERY_END;
        }
        
        //６）　@購入申込期間中の判定
        if (this.isOfferStart() && 
            !this.isOfferEnd())
        {
            log.debug("購入申込期間中の判定");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.PURCHASE_APPLICATION_TERM;
        }
        
        //７）　@購入申込終了の判定
        if (this.isOfferEnd() && 
            !this.isPublicEnd())
        {
            log.debug("購入申込終了の判定");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.PURCHASE_APPLICATION_END;
        }
        
        //公開の判定
        if (this.isPublicEnd())
        {
            log.debug("公開の判定");
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoScheduleDef.PUBLIC;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get時価)<BR>
     * 時価サーバより、IPO銘柄に該当する時価を取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（IPO商品）get時価」参照。
     * @@param l_account - 顧客オブジェクト
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@return double
     * @@roseuid 40D7DBBD0181
     */
    public double getCurrentPrice(MainAccount l_account, WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCurrentPrice(MainAccount,WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);       
        
        // 1.get市場コード( )
        log.debug("get市場コード");
        String l_strMarketCode = this.getMarketCode();
        
        //2.reset市場コード(String)
        log.debug("reset市場コード");
        log.debug("l_strMarketCode = " + l_strMarketCode);
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        //3.reset受付時間区分(String)
        log.debug("reset受付時間区分");
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //4.getTradingModule(ProductTypeEnum)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY );
        
        //5.getProductManager( )
        log.debug("getProductManager");
        EqTypeProductManager l_eqTypeProductManager = (EqTypeProductManager)l_tradingModule.getProductManager();
        
        //6.getInstitution( )
        Institution l_institution = l_account.getInstitution();        
        log.debug("l_institution = " + l_institution);
        
        //7.get株式銘柄コード()
        String l_strIpoProductCode = this.getIpoProductCode();        
        
        try
        {
            //8.getTradedProduct            
            WEB3EquityTradedProduct l_eqTypeTradedProduct = 
               (WEB3EquityTradedProduct)l_eqTypeProductManager.getTradedProduct(l_institution,l_strIpoProductCode,l_ipoProduct.getMarketCode());
            
            //9.getSubAccount
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_account.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //10.get時価情報
            WEB3EquityProductQuote l_equityProductQuote =
                l_eqTypeTradedProduct.getProductQuote(l_subAccount);
            double l_dblQuote;
            if (l_equityProductQuote != null)
            {
                //11.get時価
                l_dblQuote = l_equityProductQuote.getQuote();
            }
            else
            {
                l_dblQuote = Double.NaN;
            }
            
            //12.reset市場コード(String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarginTradingDivDef.DEFAULT);
            
            //13.reset受付時間区分(String)
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.IPO);
            
            log.exiting(STR_METHOD_NAME);
            return l_dblQuote;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get市場コード)
     * 公開市場に該当する市場コードを取得する。<BR>
     * <BR>
     * －this.get公開市場()が以下のいずれかであれば、市場コード.”東京”を返却する。<BR>
     * 　@10：　@東証<BR>　@
     * 　@11：　@東証一部　@<BR>
     * 　@12：　@東証二部　@<BR>
     * 　@13：　@マザーズ　@<BR>
     * <BR>
     * －this.get公開市場()が以下のいずれかであれば、市場コード.”大阪”を返却する。<BR>
     * 　@20：　@大証<BR>　@
     * 　@21：　@大証一部　@<BR>
     * 　@22：　@大証二部　@<BR>
     * <BR>
     * －this.get公開市場()が以下のいずれかであれば、市場コード.”名古屋”を返却する。<BR>
     * 　@30：　@名証<BR>　@
     * 　@31：　@名証一部　@<BR>
     * 　@32：　@名証二部　@<BR>
     * 　@33：　@セントレックス<BR>
     * <BR>
     * －this.get公開市場()が以下のいずれかであれば、市場コード.”福岡”を返却する。<BR>
     * 　@40：　@福証<BR>　@
     * 　@41：　@Q-Board<BR>
     * <BR>
     * －this.get公開市場()が以下のいずれかであれば、市場コード.”札幌”を返却する。<BR>
     * 　@50：　@札証<BR>　@
     * 　@51：　@アンビシャス<BR>
     * <BR>
     * －this.get公開市場()が以下のいずれかであれば、市場コード.”JASDAQ”を<BR>
     * 返却する。<BR>
     * 　@90：　@JASDAQ（スタンダード）<BR>
     * 　@91：　@JASDAQ（グロース）<BR>
     * <BR>
     * @@return String
     * @@roseuid 40D7DC1B00B6
     */
    public String getMarketCode() 
    {
        final String STR_METHOD_NAME = " getMarketCode()";
        log.entering(STR_METHOD_NAME); 
        
        //市場コード.”東京”を返却する。
        String l_strPublicmarket = this.getPublicMarket();
        if (WEB3PublicMarketDef.TOKYO_STOCK_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.TSE_NO_ONE_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.TSE_NO_TWO_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.MOTHERS.equals(l_strPublicmarket))
        {
            log.debug("市場コード.”東京”を返却する。");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.TOKYO;
        }
        
        //市場コード.”大阪”を返却する
        if (WEB3PublicMarketDef.OSAKA_SECURITIES_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.OSE_NO_ONE_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.OSE_NO_TWO_DEPT.equals(l_strPublicmarket))
        {
            log.debug("市場コード.”大阪”を返却する");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.OSAKA;
        }
        
        //市場コード.”名古屋”を返却する
        if (WEB3PublicMarketDef.NAGOYA_STOCK_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.NSE_NO_ONE_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.NSE_NO_TWO_DEPT.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.CENTREX.equals(l_strPublicmarket))
        {
            log.debug("市場コード.”名古屋”を返却する");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.NAGOYA;
        }
        
        //市場コード.”福岡”を返却する
        if (WEB3PublicMarketDef.FUKUOKA_STOCK_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.Q_BOARD.equals(l_strPublicmarket)) 
        {
            log.debug("市場コード.”福岡”を返却する");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.FUKUOKA;
        }
        
        //市場コード.”札幌”を返却する
        if (WEB3PublicMarketDef.SAPPORO_STOCK_EXCHANGE.equals(l_strPublicmarket) || 
            WEB3PublicMarketDef.AMBITIOUS.equals(l_strPublicmarket)) 
        {
            log.debug("市場コード.”札幌”を返却する");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.SAPPORO;
        }

        //市場コード.”JASDAQ”を返却する
        if (WEB3PublicMarketDef.JASDAQ_STANDARD.equals(l_strPublicmarket)
            || WEB3PublicMarketDef.JASDAQ_CLOSE.equals(l_strPublicmarket)) 
        {
            log.debug("市場コード.”JASDAQ”を返却する");
            log.exiting(STR_METHOD_NAME);
            return WEB3MarketCodeDef.JASDAQ;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * 0を返却する。
     * @@return double
     * @@roseuid 40BDA4070306
     */
    public double getMarginRatio() 
    {
        final String STR_METHOD_NAME = " getMarginRatio()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Market[]
     * @@roseuid 40BDA4070307
     */
    public Market[] getTradedMarkets() 
    {
        final String STR_METHOD_NAME = " getTradedMarkets()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return TradedProduct
     * @@roseuid 40BDA4070309
     */
    public TradedProduct getPrimaryTradedProduct() 
    {
        final String STR_METHOD_NAME = " getPrimaryTradedProduct()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * trueを返却する。
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 40BDA407030B
     */
    public boolean isTradedOnMarket(Market l_arg0) 
    {
        final String STR_METHOD_NAME = " isTradedOnMarket()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return String
     * @@roseuid 40BDA4070316
     */
    public String getNameAlt1() 
    {
        final String STR_METHOD_NAME = " getNameAlt1()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return String
     * @@roseuid 40BDA4070317
     */
    public String getNameAlt2() 
    {
        final String STR_METHOD_NAME = " getNameAlt2()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * （未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@return Market
     * @@roseuid 40D18A81006D
     */
    public Market getPrimaryMarket() 
    {
        final String STR_METHOD_NAME = " getPrimaryMarket()";
        log.entering(STR_METHOD_NAME); 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (isブックビルディング開始)
     * ブックビルディング開始日時を過ぎているかを判定する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.ブックビルディング開始日時 <= 現在日時(*1)）の場合、<BR>
     * trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()にて取得する。<BR>
     * @@return boolean
     * @@roseuid 40BE92670297
     */
    public boolean isBookbuildingStart() 
    {
        final String STR_METHOD_NAME = " isBookbuildingStart()";
        log.entering(STR_METHOD_NAME); 
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        log.debug("l_tsCurrentTime = " + l_tsCurrentTime);
        if (WEB3DateUtility.compareToMinute(this.ipoProductRow.getBookbuildingStartDatetime(), l_tsCurrentTime) <= 0)
        {
            log.debug("trueを返却する");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("falseを返却する");
            log.exiting(STR_METHOD_NAME);
            return false;       
        }
    }
    
    /**
     * (isブックビルディング終了)<BR>
     * ブックビルディング終了日時を過ぎているかを判定する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.ブックビルディング終了日時 <= 現在日時(*1)）の場合、<BR>
     * trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()にて取得する。
     * @@return boolean
     * @@roseuid 40BE94850381
     */
    public boolean isBookbuildingEnd() 
    {
        final String STR_METHOD_NAME = " isBookbuildingEnd()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        log.debug("l_tsCurrentTime = " + l_tsCurrentTime);
        log.debug("ipoProductRow.getBookbuildingEndDatetime() = " + ipoProductRow.getBookbuildingEndDatetime());
		if (WEB3DateUtility.compareToMinute(this.ipoProductRow.getBookbuildingEndDatetime(), l_tsCurrentTime) <= 0)
        {
            log.debug("trueを返却する");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("falseを返却する");
            log.exiting(STR_METHOD_NAME);
            return false;       
        }
    }
    
    /**
     * (is公開価格決定)<BR>
     * 公開価格決定が決定し、登録が終わっているかを判定する。<BR>
     * <BR>
     * 　@－（this.isブックビルディング終了() == false）の場合、falseを返却する。<BR>
     * 　@－（this.IPO銘柄行.公開価格 == null）の場合、falseを返却する。<BR>
     * 　@－以外、trueを返却する。
     * @@return boolean
     * @@roseuid 40BEA1E50379
     */
    public boolean isPublicPriceSettle() 
    {
        final String STR_METHOD_NAME = " isPublicPriceSettle()";
        log.entering(STR_METHOD_NAME);
        
        if (!this.isBookbuildingEnd())
        {
            log.debug("falseを返却する");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else if(this.ipoProductRow.getPublicPriceIsNull())
        {
            log.debug("falseを返却する");            
            log.exiting(STR_METHOD_NAME);
            return false;            
        }
        else
        {
            log.debug("trueを返却する");
            log.exiting(STR_METHOD_NAME);
            return true;
        }       
    }
    
    /**
     * (is購入申込開始（目論見書記載）)
     * 購入申込開始日（目論見書記載）を過ぎているかを判定する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.購入申込開始日（目論見書記載） <= 現在日(*1)）の
     * <BR>場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * ※対象項目がnullの場合もfalseとする。 <BR>
     * <BR>
     * (*1) 現在日<BR>
     * TradingSystem.getSystemTimestamp()で取得した現在日時の日付
     * <BR>（YYYYMMDD）部分。
     * @@return boolean
     * @@roseuid 40BEA4B8001E
     */
    public boolean isOfferStartProspec() 
    {
        final String STR_METHOD_NAME = " isOfferStartProspec()";
        log.entering(STR_METHOD_NAME);
        if(ipoProductRow.getOfferStartDateProspec() != null)
        {
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
			if (WEB3DateUtility.compareToDay(this.ipoProductRow.getOfferStartDateProspec(), l_tsCurrentTime) <= 0)
			{
				log.debug("trueを返却する");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("falseを返却する");
				log.exiting(STR_METHOD_NAME);
				return false;
			} 			
        }	
	    else
	    {
			log.debug("購入申込開始日（目論見書記載）が未定なのでfalseを返却する");
			log.exiting(STR_METHOD_NAME);
			return false;
        }            
   
    }
    
    /**
     * (is購入申込終了（目論見書記載）)
     * 購入申込終了日（目論見書記載）を過ぎているかを判定する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.購入申込終了日（目論見書記載） < 現在日(*1)）の
     * <BR>
     * 場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * ※対象項目がnullの場合もfalseとする。 <BR>
     * <BR>
     * (*1) 現在日<BR>
     * TradingSystem.getSystemTimestamp()で取得した現在日時の日付<BR>
     * （YYYYMMDD）部分。
     * @@return boolean
     * @@roseuid 40BEA55A033B
     */
    public boolean isOfferEndProspec() 
    {
        final String STR_METHOD_NAME = " isOfferEndProspec()";
        log.entering(STR_METHOD_NAME);
        
		if(ipoProductRow.getOfferEndDateProspec() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
			if (WEB3DateUtility.compareToDay(this.ipoProductRow.getOfferEndDateProspec(), l_tsCurrentTime) < 0)
			{
				log.debug("trueを返却する");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("falseを返却する");
				log.exiting(STR_METHOD_NAME);
				return false;
			} 			
		}	
		else
		{
			log.debug("購入申込終了日（目論見書記載）が未定なのでfalseを返却する");
			log.exiting(STR_METHOD_NAME);
			return false;
		}                           

    }
    
    /**
     * (is購入申込開始（当社設定）)
     * 購入申込開始日時（当社設定）を過ぎているかを判定する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.購入申込開始日時（当社設定） <= 現在日時(*1)）の
     * <BR>
     * 場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * ※対象項目がnullの場合もfalseとする。 <BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()で取得する。
     * @@return boolean
     * @@roseuid 40BEA5930212
     */
    public boolean isOfferStart() 
    {
        final String STR_METHOD_NAME = " isOfferStart()";
        log.entering(STR_METHOD_NAME);

		if(ipoProductRow.getOfferStartDatetime() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
			if (WEB3DateUtility.compareToMinute(this.ipoProductRow.getOfferStartDatetime(), l_tsCurrentTime) <= 0)
			{
				log.debug("trueを返却する");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("falseを返却する");
				log.exiting(STR_METHOD_NAME);
				return false;
			} 			
		}	
		else
		{
			log.debug("購入申込開始日時（当社設定）が未定なのでfalseを返却する");
			log.exiting(STR_METHOD_NAME);
			return false;
		}         

    }
    
    /**
     * (is購入申込終了（当社設定）)
     * 購入申込終了日時（当社設定）を過ぎているかを判定する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.購入申込終了日時（当社設定） <= 現在日時(*1)）の
     * <BR>
     * 場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * ※対象項目がnullの場合もfalseとする。 <BR>
     * <BR>
     *      * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()で取得する。
     * @@return boolean
     * @@roseuid 40BEA5C301F3
     */
    public boolean isOfferEnd() 
    {
        final String STR_METHOD_NAME = " isOfferEnd()";
        log.entering(STR_METHOD_NAME);
        
		if(ipoProductRow.getOfferEndDatetime() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
        
			if (WEB3DateUtility.compareToMinute(this.ipoProductRow.getOfferEndDatetime(), l_tsCurrentTime) <= 0)
			{
				log.debug("trueを返却する");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("falseを返却する");
				log.exiting(STR_METHOD_NAME);
				return false;
			} 			
		}	
		else
		{
			log.debug("購入申込終了日時（当社設定）が未定なのでfalseを返却する");
			log.exiting(STR_METHOD_NAME);
			return false;
		}                
    }
    
    /**
     * (is新規抽選終了)
     * 抽選が終了しているかを判定する。<BR>
     * <BR>
     * 　@－以下の条件に当てはまる場合、trueを返却する。<BR>
     *  　@　@（this.IPO銘柄行.抽選状態 != ”0：DEFAULT（抽選未済）”）<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * (*1) 現在日<BR>
     * TradingSystem.getSystemTimestamp()で取得した現在日時の日付<BR>
     * （YYYYMMDD）部分。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40BEA686035A
     */
    public boolean isNewLotteryEnd() 
    {
        final String STR_METHOD_NAME = " isNewLotteryEnd()";
        log.entering(STR_METHOD_NAME);
        
        if (!WEB3LotStatusDef.DEFAULT.equals(this.ipoProductRow.getLotStatus()))
        {
            log.debug("trueを返却する");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("falseを返却する");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is公開済)
     * 公開済のIPO銘柄かを判定する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.公開日 <= 現在日(*2)）の場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * ※対象項目がnullの場合もfalseとする。 <BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * (*2) 現在日<BR>
     * 現在日時の日付（YYYYMMDD）部分。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40BEA5E40270
     */
    public boolean isPublicEnd() 
    {
        final String STR_METHOD_NAME = " isPublicEnd()";
        log.entering(STR_METHOD_NAME);
            
		if(ipoProductRow.getPublicOfferingDate() != null)
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
			Timestamp l_tsCurrentTime = l_finApp.getTradingSystem().getSystemTimestamp();
			String l_strCurrentTime = WEB3DateUtility.formatDate(l_tsCurrentTime,"yyyyMMdd");
        
			String l_strPublicOfferingDate = WEB3DateUtility.formatDate(this.ipoProductRow.getPublicOfferingDate(),"yyyyMMdd");

            if (l_strPublicOfferingDate.compareTo(l_strCurrentTime) <= 0)
			{
				log.debug("公開日 <= 現在日");
				log.exiting(STR_METHOD_NAME);
				return true;
			}
			else
			{
				log.debug("falseを返却する");
				log.exiting(STR_METHOD_NAME);
				return false;
			}			
		}
		else
		{
			log.debug("公開日が未定なのでfalseを返却する");
			log.exiting(STR_METHOD_NAME);
			return false;
		}       

    }
    
    /**
     * (isブックビルディング申告可能)
     * 該当銘柄がブックビルディング申告可能な状態かを判定する。<BR>
     * <BR>
     * １）　@this.IPO銘柄行の以下の項目にnullがセットされている場合、falseを
     * <BR>
     * 返却する。<BR>
     * <BR>
     * 　@IPO登録区分詳細<BR>
     * 　@銘柄コード<BR>
     * 　@銘柄名<BR>
     * 　@公開市場<BR>
     * 　@仮条件区分<BR>
     * 　@仮条件提示日<BR>
     * 　@主幹事証券会社名<BR>
     * 　@表示用単位区分<BR>
     * 　@ブックビルディング開始日時<BR>
     * 　@ブックビルディング終了日時<BR>　@
     *   仮条件下限値 <BR>
     *   仮条件上限値 <BR>
     *   当社取扱数量 <BR>
     *   購入申込単位 <BR>
     *   刻み <BR>
     *   公募数量 <BR>
     *   売出数量 <BR>
     * <BR>
     * ２）　@ブックビルディング期間内の判定<BR>
     * <BR>
     * 　@－ブックビルディング期間内でない場合<BR>
     * 　@（this.isブックビルディング開始() == false Or<BR> 
     * 　@　@this.isブックビルディング終了() == true）、falseを返却する。<BR>
     * <BR>
     * ３）　@IPO中止／IPO停止の判定<BR>
     * 　@－IPO中止、または停止の場合<BR>
     * 　@（this.is取扱中() == false）、falseを返却する。<BR>
     * <BR>
     * ４）　@以外の場合、trueを返却する。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40D2BE5800E1
     */
    public boolean isBookbuildingOrderPossible() 
    {
        final String STR_METHOD_NAME = " isBookbuildingOrderPossible()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@
        log.debug("ipoProductRow.getPublicOfferingDate() = " + ipoProductRow.getPublicOfferingDate());
        log.debug("this.ipoProductRow.getPublicOfferingDateCount() = " + this.ipoProductRow.getPublicOfferingDateCount());
        if (this.ipoProductRow.getIpoRegistDetailDiv() == null ||
            this.ipoProductRow.getProductCode() == null ||
            this.ipoProductRow.getStandardName() == null ||
            this.ipoProductRow.getPublicMarket() == null ||
            this.ipoProductRow.getProvisionalValueDiv() == null ||
            this.ipoProductRow.getProvisionalValueOpenDate() == null ||
            this.ipoProductRow.getLeadManagingUnderwriter() == null ||
            this.ipoProductRow.getIpoUnitDiv() == null ||
            this.ipoProductRow.getBookbuildingStartDatetime() == null ||
            this.ipoProductRow.getBookbuildingEndDatetime() == null ||
            this.ipoProductRow.getProvisionalMinValueIsNull() ||
            this.ipoProductRow.getProvisionalMaxValueIsNull() || 
            this.ipoProductRow.getDealingQuantityIsNull()||
            this.ipoProductRow.getLotSizeIsNull() ||
            this.ipoProductRow.getTickValueIsNull() ||
            this.ipoProductRow.getPublicOfferingQuantityIsNull() ||
            this.ipoProductRow.getPublicSalesQuantityIsNull())
        {
            log.debug(STR_METHOD_NAME + "= false" + " : IPOブックビルディング申告不可能");
            log.exiting(STR_METHOD_NAME);
            return false;
        }                            
        //２）ブックビルディング期間内でない場合
        else if (!this.isBookbuildingStart() || this.isBookbuildingEnd())
        {
            log.debug(STR_METHOD_NAME + "= false" + " : ブックビルディング期間内でない");
            log.exiting(STR_METHOD_NAME);
            return false;
        }        
        //３）IPO中止、または停止の場合
        else if (!this.isDealtInProcess())
        {
            log.debug(STR_METHOD_NAME + "= false" + " : IPO開催中でない");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //４）
        else
        {
            log.debug(STR_METHOD_NAME + "= true" + " : IPOブックビルディング申告可能");   
            log.exiting(STR_METHOD_NAME);
            return true;    
        }
    }
    
    /**
     * (is購入申込可能)
     * 該当銘柄が購入申込可能な状態かを判定する。<BR>
     * <BR>
     * １）　@IPO中止／IPO停止の判定<BR>
     * 　@－IPO中止、または停止の場合<BR>
     * 　@（this.is取扱中() == false）、falseを返却する。<BR>
     * ２）　@購入申込期間内であるかの判定<BR>
     * 　@－　@（IPO銘柄.is購入申込開始（当社設定）() == ture && IPO銘柄.is<BR>購入申込終了（当社設定）() == 
     * false）の場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40DBB716029D
     */ 
    public boolean isOfferPossible() 
    {
        final String STR_METHOD_NAME = " isOfferPossible()";
        log.entering(STR_METHOD_NAME);
        
        //IPO中止／IPO停止の判定
        if (!this.isDealtInProcess())
        {
            log.debug("IPO中止／IPO停止の判定");
            log.exiting(STR_METHOD_NAME);
            return false;    
        }        
        //購入申込期間内であるかの判定
        if (this.isOfferStart() && 
            !this.isOfferEnd())
        {
            log.debug("購入申込期間内であるかの判定");
            log.exiting(STR_METHOD_NAME);
            return true;   
        }
        else   
        {
            log.debug("以外、falseを返却する");
            log.exiting(STR_METHOD_NAME);
            return false;
        }      
    }
    
    /**
     * (is辞退可能)
     * 該当銘柄が辞退可能な状態かを判定する。<BR>
     * <BR>
     * １）　@IPO中止／IPO停止の判定<BR>
     * 　@－IPO中止、または停止の場合<BR>
     * 　@（this.is取扱中() == false）、falseを返却する。
     * ２）　@辞退可能期間内であるかの判定<BR>
     * 　@－（IPO銘柄.is新規抽選終了() == ture && IPO銘柄.is購入申込終了
     *  <BR>（当社設定）() == false）の場合、trueを返却する。<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40DBB76200B4
     */
    public boolean isDeclinePossible() 
    {
        final String STR_METHOD_NAME = " isDeclinePossible()";
        log.entering(STR_METHOD_NAME); 
        //IPO中止／IPO停止の判定
        if (!this.isDealtInProcess())
        {
            log.debug("IPO中止／IPO停止の判定");
            log.exiting(STR_METHOD_NAME);
            return false;    
        } 
        //辞退可能期間内であるかの判定
        if (this.isNewLotteryEnd() && 
            !this.isOfferEnd())
        {
            log.debug("辞退可能期間内であるかの判定");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("以外、falseを返却する");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is削除銘柄)
     * 有効な銘柄（削除されていない）かを判定する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.削除フラグ == BooleanEnum.TRUE）の場合、trueを<BR>返却する。<BR>
     * 　@－以外、falseを返却する。
     * @@return boolean
     * @@roseuid 40C6B2CF026F
     */
    public boolean isDeletedProduct() 
    {
        final String STR_METHOD_NAME = " isDeletedProduct()";
        log.entering(STR_METHOD_NAME);
        
        if (BooleanEnum.TRUE.equals(this.ipoProductRow.getDeleteFlag()))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is中止)
     * IPO銘柄が募集中止状態であるかを判定する。<BR>
     * <BR>
     * 　@（this.IPO銘柄行.IPO停止 == ”中止”）の場合true、以外はfalseを返却する。
     * @@return boolean
     * @@roseuid 40C6B6B002DC
     */
    public boolean isDiscontinuation() 
    {
        final String STR_METHOD_NAME = " isDiscontinuation()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3IpoStopDef.DISCONTINUATION.equals(this.ipoProductRow.getIpoStop()))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is取扱中)
     * IPO銘柄が取扱中であるかを判定する。<BR>
     * <BR>
     * 　@（this.IPO銘柄行.IPO停止 == ”DEFAULT（開催中）”）の場合true、<BR>
     * 以外はfalseを返却する。
     * @@return boolean
     * @@roseuid 40BF11C70212
     */
    public boolean isDealtInProcess() 
    {
        final String STR_METHOD_NAME = " isDealtInProcess()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3IpoStopDef.DEFAULT.equals(this.ipoProductRow.getIpoStop()))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (isスケジュール決定)
     * スケジュールが決定しているかを判定する。<BR>
     * <BR>
     * －以下の項目で、１つでもnullが入っている場合は、falseを返却する。<BR>
     * －以下の項目が、すべてnullでない場合（not null）は、trueを返却する。<BR>
     * <BR>
     * 　@this.IPO銘柄行.公開価格決定日<BR>
     * 　@this.IPO銘柄行.抽選日<BR>
     * 　@this.IPO銘柄行.購入申込開始日（目論見書記載）<BR>
     * 　@this.IPO銘柄行.購入申込終了日（目論見書記載）<BR>
     * 　@this.IPO銘柄行.購入申込開始日時（当社設定）<BR>
     * 　@this.IPO銘柄行.購入申込終了日時（当社設定）<BR>
     * 　@this.IPO銘柄行.公開日<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40C57241009A
     */
    public boolean isScheduleDecision() 
    {
        final String STR_METHOD_NAME = " isScheduleDecision()";
        log.entering(STR_METHOD_NAME);

        if (this.ipoProductRow.getPublicPriceSettleDate() == null ||
            this.ipoProductRow.getLotDate() == null ||
            this.ipoProductRow.getOfferStartDateProspec() == null || 
            this.ipoProductRow.getOfferEndDateProspec() == null || 
            this.ipoProductRow.getOfferStartDatetime() == null ||
            this.ipoProductRow.getOfferEndDatetime() == null || 
            this.ipoProductRow.getPublicOfferingDate() == null)
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
    
    /**
     * (isディスカウント扱い)
     * 当該銘柄がディスカウント扱いかを判定する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行仮条件区分 == ”ディスカウント率”）の場合、<BR>
     * trueを返却する。<BR>
     * 　@－以外、falseを返却する。
     * @@return boolean
     * @@roseuid 40D7E584026C
     */
    public boolean isDiscountHandling() 
    {
        final String STR_METHOD_NAME = " isDiscountHandling()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.ipoProductRow.getProvisionalValueDiv()))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (get株式銘柄コード)<BR>
     * 株式銘柄コードを取得する。<BR> 
     * <BR>
     * this.IPO銘柄行.銘柄コードの末尾が”1”の場合、<BR>
     * 末尾を”0”に変更した銘柄コードを返却する。<BR>
     * 以外、this.IPO銘柄行.銘柄コードを返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getIpoProductCode() 
    {
        final String STR_METHOD_NAME = " getIpoProductCode() ";
        log.entering(STR_METHOD_NAME);
        String l_strIpoProductCode = ipoProductRow.product_code;
        if(l_strIpoProductCode.endsWith("1"))
        {
            log.debug("末尾が1の場合の株式銘柄コード返却値; " 
                + (l_strIpoProductCode.substring(0,(l_strIpoProductCode.length()-1)) + "0"));
            log.exiting(STR_METHOD_NAME);
            return (l_strIpoProductCode.substring(0,(l_strIpoProductCode.length()-1)) + "0");
        }
        else
        {
            log.debug("末尾が0の場合の株式銘柄コード返却値; " + l_strIpoProductCode);
            log.exiting(STR_METHOD_NAME);
            return l_strIpoProductCode;
        }
    }
        
    /**
     * IPO銘柄ＩＤを新規採番(*1)し、this.IPO銘柄行.IPO銘柄ＩＤにセットする。<BR>
     * <BR>
     * (*1) IPO銘柄ＩＤの新規採番<BR>
     * 　@IPO銘柄DAO.newPkValue()メソッドにて取得する。<BR>
     * 　@※ IPO銘柄DAOクラスはDDLより自動生成する。
     * @@roseuid 40BEEC7E0221
     */
    public void setNewId() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setNewId()";
        log.entering(STR_METHOD_NAME);
        try
        {
            log.debug("IPO銘柄ＩＤの新規採番");
            long l_lngNewPkValue = IpoProductDao.newPkValue();
            this.ipoProductRow.setIpoProductId(l_lngNewPkValue);
            log.debug("l_lngNewPkValue = " + l_lngNewPkValue);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80004,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80004,
            getClass().getName() + STR_METHOD_NAME,
            l_ex.toString(),
            l_ex);
        }     
    }
    
    /**
     * (set銘柄名)
     * 株式銘柄テーブルより銘柄名取得し、本オブジェクトにセットする。<BR>
     * ※既上場（this.IPO銘柄行.IPO登録区分 == ”既上場”）の場合のみ処理実施。<BR>
     * ※新規上場（this.IPO銘柄行.IPO登録区分 == ”新規上場）の場合は、<BR>
     *   セットを行わず処理を終了する。<BR> 
     * <BR>
     * <BR>
     * １）　@株式銘柄テーブル読込<BR>
     * 　@株式銘柄テーブルより、証券会社、銘柄コードに該当する行を取得する(*1)。<BR>
     *   行が取得できなかった場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00495<BR>
     * <BR>
     * (*1) 株式銘柄を取得するメソッド<BR>
     * 　@　@EqtypeProductDao.findRowByInstitutionCodeProductCode()<BR>
     * <BR>
     * 　@　@[findRowByInstitutionCodeProductCode()に指定する引数]<BR>
     * 　@　@p_institutionCode：　@this.IPO銘柄行.証券会社コード<BR>
     * 　@　@p_productCode：　@this.get株式銘柄コード() <BR>
     * <BR>
     * ２）　@銘柄名セット<BR>
     * 　@取得した株式銘柄行（EqTypeProductParams）.銘柄名を、<BR>
     * this.IPO銘柄行.銘柄名にセットする。<BR>
     * <BR>
     * @@roseuid 40BEA8B0030C
     */
    public void setStandardName() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setStandardName()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //銘柄名セット
            if (WEB3IpoRegistDivDef.LISTED.equals(this.ipoProductRow.getIpoRegistDiv()))
            {
                //株式銘柄テーブル読込
                EqtypeProductRow l_eqtypeProductRow = 
                    EqtypeProductDao.findRowByInstitutionCodeProductCode(this.ipoProductRow.getInstitutionCode(),
                        this.getIpoProductCode());
                log.debug(this.getIpoProductCode());
            
                if (l_eqtypeProductRow == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00495,
                        getClass().getName() + STR_METHOD_NAME);
                }
            
                log.debug("既上場");
                this.ipoProductRow.setStandardName(l_eqtypeProductRow.getStandardName());
                log.exiting(STR_METHOD_NAME);
            }
            else if (WEB3IpoRegistDivDef.OPEN_LISTING.equals(this.ipoProductRow.getIpoRegistDiv()))
            {
                log.debug("新規上場");
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
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
     * (set募集停止／再開)
     * IPO銘柄のIPO停止状態を変更する。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.IPO停止 == ”停止中”）の場合、<BR>
     * 　@　@this.IPO銘柄行.IPO停止 = ”DEFAULT（開催中）”をセットする。<BR>
     * 　@－（this.IPO銘柄行.IPO停止 == ”DEFAULT（開催中）”）の場合、<BR>
     * 　@　@this.IPO銘柄行.IPO停止 = ”停止中”をセットする。<BR>
     * 　@－以外、何もしない。
     * @@roseuid 40D0255A01B3
     */
    public void setRecruitStopResumption() 
    {
        final String STR_METHOD_NAME = " setRecruitStopResumption()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3IpoStopDef.STOPPING.equals(this.ipoProductRow.getIpoStop()))
        {
            log.debug("IPO停止 == ”停止中”");
            this.ipoProductRow.setIpoStop(WEB3IpoStopDef.DEFAULT);
        }
        else if (WEB3IpoStopDef.DEFAULT.equals(this.ipoProductRow.getIpoStop()))
        {
            log.debug("IPO停止 == ”DEFAULT（開催中）”");
            this.ipoProductRow.setIpoStop(WEB3IpoStopDef.STOPPING);
        }
        
        log.exiting(STR_METHOD_NAME);    
    }
    
    /**
     * (set中止)
     * IPO銘柄にIPO中止状態をセットする。<BR>
     * <BR>
     * 　@－（this.IPO銘柄行.IPO停止 = ”中止”）をセットする。
     * @@roseuid 40D027D000A9
     */
    public void setDiscontinuation() 
    {
        final String STR_METHOD_NAME = " setDiscontinuation()";
        log.entering(STR_METHOD_NAME);
        
        this.ipoProductRow.setIpoStop(WEB3IpoStopDef.DISCONTINUATION);
        log.debug("setDiscontinuation------------->ok");
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set抽選終了)
     * IPO銘柄に抽選終了をセットする。<BR>
     * <BR>
     * ○　@新規抽選（is新規抽選() == true）の場合<BR>
     * 　@－（this.IPO銘柄行.抽選状態 = ”新規抽選済”）をセットする。<BR>
     * <BR>
     * ○　@繰上抽選（is新規抽選() == false）の場合<BR>
     * 　@－（this.IPO銘柄行.抽選状態 = ”繰上抽選済”）をセットする。<BR>
     * @@param l_isNewLottery　@ - 新規抽選かの判定<BR>
     * <BR>
     * true：　@新規抽選<BR>
     * false：　@繰上抽選<BR>
     * @@roseuid 40F656CE03AB
     */
    public void setLotteryEnd(boolean l_isNewLottery) 
    {
        final String STR_METHOD_NAME = " setLotteryEnd(boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_isNewLottery)     //新規抽選
        {
            log.debug("新規抽選");
            this.ipoProductRow.setLotStatus(WEB3LotStatusDef.OPEN_LOTTERY_END);
        }
        else if (!l_isNewLottery)     //繰上抽選
        {
            log.debug("繰上抽選");
            this.ipoProductRow.setLotStatus(WEB3LotStatusDef.ADVANCED_LOTTERY_END);
        }
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (削除)
     * 銘柄に削除状態をセットする。<BR>
     * <BR>
     * 　@this.IPO銘柄行.削除フラグ = BooleanEnum.TRUE
     * @@roseuid 40C7F009005D
     */
    public void delete() 
    {
        final String STR_METHOD_NAME = " delete()";
        log.entering(STR_METHOD_NAME);
        
        this.ipoProductRow.setDeleteFlag(BooleanEnum.TRUE);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateスケジュール)
     * IPOスケジュールが正しいかをチェックする。<BR>
     * <BR>
     * １）　@ブックビルディング開始日時のチェック①@<BR>
     * 　@（新規作成日時 != null）の場合のみチェックを実施する。<BR>
     * 以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@新規作成日時 <= this.IPO銘柄行.ブックビルディング開始日時<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00496<BR>
     * 　@<BR>
     * ２）　@ブックビルディング開始日時のチェック②<BR>
     * 　@以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@this.IPO銘柄行.仮条件提示日 <= this.IPO銘柄行.ブックビルディング開始日時<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00497<BR>
     * <BR>
     * ３）　@ブックビルディング終了日時のチェック<BR>
     * 　@以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@this.IPO銘柄行.ブックビルディング開始日時 < this.IPO銘柄行.ブックビルディング<BR>終了日時<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00498<BR>
     * <BR>
     * ４）　@公開価格決定日のチェック<BR>
     * 　@公開価格決定日が入力されている場合<BR>
     * （this.IPO銘柄行.公開価格決定日 != null）<BR>
     * 　@以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@this.IPO銘柄行.ブックビルディング終了日時 の日付部分 <=
     * <BR>
     * this.IPO銘柄行.公開価格決定日<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00499<BR>
     * <BR>
     * ５）　@抽選日のチェック<BR>
     * 　@抽選日が入力されている場合<BR>
     * （this.IPO銘柄行.抽選日 != null）のみ、チェックを行う。<BR>
     * 　@以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@this.IPO銘柄行.公開価格決定日 <= this.IPO銘柄行.抽選日<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00500<BR>
     * <BR>
     * ６）　@購入申込開始日（目論見書記載）のチェック<BR>
     * 　@購入申込開始日（目論見書記載）が入力されている場合<BR>
     * （this.IPO銘柄行.購入申込開始日（目論見書記載） != null）
     * <BR>のみ、チェックを行う。<BR>
     * 　@以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@this.IPO銘柄行.抽選日 <= this.IPO銘柄行.購入申込開始日<BR>
     * （目論見書記載）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00501<BR>
     * <BR>
     * ７）　@購入申込開始日時（当社設定）のチェック<BR>
     * 　@購入申込開始日時（当社設定）が入力されている場合、<BR>
     * （this.IPO銘柄行.購入申込開始日時（当社設定） != null）<BR>
     * のみ、チェックを行う。<BR>
     * 　@以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@this.IPO銘柄行.ブックビルディング終了日時 < this.IPO銘柄行.購入申込<BR>
     * 開始日時（当社設定） &&<BR>
     * 　@this.IPO銘柄行.購入申込開始日（目論見書記載） <= <BR>
     * this.IPO銘柄行.購入申込開始日時（当社設定） の日付部分<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00502<BR>
     * <BR>
     * 
     * ８）　@購入申込終了日時（当社設定）のチェック<BR>
     * 　@購入申込終了日時（当社設定）が入力されている場合<BR>
     * （this.IPO銘柄行.購入申込終了日時（当社設定） != null）<BR>
     * のみ、チェックを行う。<BR>
     * 　@以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@this.IPO銘柄行.購入申込開始日時（当社設定） < this.IPO銘柄行.購入申込<BR>
     * 終了日時（当社設定）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00503<BR>
     * <BR>
     * ９）　@購入申込終了日（目論見書記載）のチェック<BR>
     * 　@購入申込終了日（目論見書記載）が入力されている場合<BR>
     * （this.IPO銘柄行.購入申込終了日（目論見書記載） != null）<BR>
     * のみ、チェックを行う。<BR>
     * 　@以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@・this.IPO銘柄行.購入申込終了日時（当社設定）<BR>
     * の日付部分（YYYYMMDD） <= this.IPO銘柄行.購入申込終了日<BR>
     * （目論見書記載） <BR>
     * <BR>
     * 　@・this.IPO銘柄行.購入申込終了日（目論見書記載） が「非営業日」<BR>
     * でないこと。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00504<BR>
     * <BR>
     * １０）　@公開日のチェック<BR>
     * 　@公開日が入力されている場合（this.IPO銘柄行.公開日 != null）<BR>
     * のみ、チェックを行う。<BR>
     * 　@以下の条件に当てはまらない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@this.IPO銘柄行.購入申込終了日（目論見書記載） < this.IPO銘柄行.公開日<BR>
     * <BR>
     * ※ ＸＸ日時（datetime）の項目と、ＸＸ日（date）の項目は、datetime項目<BR>
     * の日付部分（YYYYMMDD）のみを比較する。<BR>
     * 　@　@（時間は考慮しない）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00505<BR>
     * <BR>
     * １１）　@未定スケジュールのチェック<BR>
     * 　@未定の場合に登録される日数項目に矛盾がないかをチェックする。<BR>
     * 　@但し、nullがセットされている場合、その項目はチェック対象としない。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@this.IPO銘柄行.抽選日日数 <BR>
     * 　@　@<= this.IPO銘柄行.購入申込開始日日数（目論見書記載）<BR>
     * 　@　@<= this.IPO銘柄行.購入申込開始日日数（当社設定）<BR>
     * 　@　@<= this.IPO銘柄行.購入申込終了日日数（当社設定）<BR>
     * 　@　@<= this.IPO銘柄行.購入申込終了日日数（目論見書記載）<BR>
     * 　@　@< 公開日日数<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00596<BR> 
     * <BR>
     * @@param l_tsCreatedTimestamp - 新規作成日時
     * @@roseuid 40BDBF0B006C
     */
    public void validateSchedule(Timestamp l_tsCreatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSchedule(Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ブックビルディング開始日時のチェック①@ 
        if (l_tsCreatedTimestamp != null && 
            WEB3DateUtility.compareToMinute(l_tsCreatedTimestamp, this.ipoProductRow.getBookbuildingStartDatetime()) > 0)    
        {
            log.debug("ブックビルディング開始日時のチェック①@");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00496,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //ブックビルディング開始日時のチェック②
        if (WEB3DateUtility.compareToDay(ipoProductRow.getProvisionalValueOpenDate(), ipoProductRow.getBookbuildingStartDatetime()) > 0)
        {
            log.debug("ブックビルディング開始日時のチェック②");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00497,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //３）　@ブックビルディング終了日時のチェック
		if (WEB3DateUtility.compareToMinute(ipoProductRow.getBookbuildingStartDatetime(), ipoProductRow.getBookbuildingEndDatetime()) >= 0)
		{
            log.debug("ブックビルディング終了日時のチェック");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00498,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //４）　@公開価格決定日のチェック
        String l_strPublicPriceSettleDate = WEB3DateUtility.formatDate(this.ipoProductRow.getPublicPriceSettleDate(),"yyyyMMdd");
        String l_strBookbuildingEndDate = WEB3DateUtility.formatDate(this.ipoProductRow.getBookbuildingEndDatetime(),"yyyyMMdd");
        if (this.ipoProductRow.getPublicPriceSettleDate() != null && 
            l_strBookbuildingEndDate.compareTo(l_strPublicPriceSettleDate) > 0)
        {
            log.debug("公開価格決定日のチェック");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00499,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //５）　@抽選日のチェック
        if (this.ipoProductRow.getLotDate() != null && 
            WEB3DateUtility.compareToDay(this.ipoProductRow.getPublicPriceSettleDate(), this.ipoProductRow.getLotDate()) > 0)
        {
            log.debug("抽選日のチェック");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00500,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //６）　@購入申込開始日（目論見書記載）のチェック
        if (this.ipoProductRow.getOfferStartDateProspec() != null && 
            WEB3DateUtility.compareToDay(this.ipoProductRow.getLotDate(), this.ipoProductRow.getOfferStartDateProspec()) > 0)
        {
            log.debug("購入申込開始日（目論見書記載）のチェック");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00501,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //７）　@購入申込開始日時（当社設定）のチェック
        if (this.ipoProductRow.getOfferStartDatetime() != null)
        { 
			if (WEB3DateUtility.compareToMinute(ipoProductRow.getBookbuildingEndDatetime(), ipoProductRow.getOfferStartDatetime()) >= 0 ||
				 WEB3DateUtility.compareToDay(ipoProductRow.getOfferStartDateProspec(), ipoProductRow.getOfferStartDatetime()) > 0) 
            {
                log.debug("購入申込開始日時（当社設定）のチェック");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00502,
                    getClass().getName() + STR_METHOD_NAME);                
            }
        }
        
        //８）　@購入申込終了日時（当社設定）のチェック 
		if (this.ipoProductRow.getOfferEndDatetime() != null && 
					WEB3DateUtility.compareToMinute(ipoProductRow.getOfferStartDatetime(), ipoProductRow.getOfferEndDatetime()) >= 0)   
        {
            log.debug("購入申込終了日時（当社設定）のチェック");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00503,
                getClass().getName() + STR_METHOD_NAME);                
        }
        
        //９）　@購入申込終了日（目論見書記載）のチェック
        String l_strOfferEndDate = WEB3DateUtility.formatDate(this.ipoProductRow.getOfferEndDatetime(),"yyyyMMdd");    
        String l_strOfferEndDateProspec = WEB3DateUtility.formatDate(this.ipoProductRow.getOfferEndDateProspec(),"yyyyMMdd");
        Timestamp l_tsOfferEndDateProspec = this.ipoProductRow.getOfferEndDateProspec();

        if (this.ipoProductRow.getOfferEndDateProspec() != null && 
            (l_strOfferEndDate.compareTo(l_strOfferEndDateProspec) > 0 || 
                WEB3BizDateTypeDef.NO_BIZ_DATE.equals(WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOfferEndDateProspec))))
        {
            log.debug("購入申込終了日（目論見書記載）のチェック");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00504,
                getClass().getName() + STR_METHOD_NAME);                
        }  
        
        //１０）　@公開日のチェック
        if (this.ipoProductRow.getPublicOfferingDate() != null && 
            WEB3DateUtility.compareToDay(ipoProductRow.getOfferEndDateProspec(), ipoProductRow.getPublicOfferingDate()) >= 0)
        {
            log.debug("公開日のチェック");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00505,
                getClass().getName() + STR_METHOD_NAME);                
        }   
        
        //１１）　@未定スケジュールのチェック
        ArrayList l_arrayDateCount = new ArrayList();
        if (!this.ipoProductRow.getLotDateCountIsNull())                             //抽選日日数
        {
            l_arrayDateCount.add(this.ipoProductRow.lot_date_count);           
        }
        
        if (!this.ipoProductRow.getOfferStartDateCountProspecIsNull())             //購入申込開始日日数（目論見書記載）
        {
            l_arrayDateCount.add(this.ipoProductRow.offer_start_date_count_prospec);
        }
        
        if (!this.ipoProductRow.getOfferStartDateCountIsNull())                      //購入申込開始日日数（当社設定）
        {
            l_arrayDateCount.add(this.ipoProductRow.offer_start_date_count);
        } 
               
        if (!this.ipoProductRow.getOfferEndDateCountIsNull())                        //購入申込終了日日数（当社設定）
        {
            l_arrayDateCount.add(this.ipoProductRow.offer_end_date_count);
        }
        
        if (!this.ipoProductRow.getOfferEndDateCountProspecIsNull())                //購入申込終了日日数（目論見書記載）
        {
            l_arrayDateCount.add(this.ipoProductRow.offer_end_date_count_prospec);
        }

        boolean l_blnCheck = true;
        if (l_arrayDateCount.size() > 0)
        {
            Integer[] l_IntDateCount = (Integer[]) l_arrayDateCount.toArray(new Integer[0]);
            for (int i = 0; i < l_IntDateCount.length; i++)
            {
                for(int j = i + 1; j < l_IntDateCount.length; j++)
                {
                    if(l_IntDateCount[i].compareTo(l_IntDateCount[j]) > 0)
                    {
                        l_blnCheck = false;
                        break;
                    }
                }
                
                if (!l_blnCheck)
                {
                    break;                 
                }
                
                if (!this.ipoProductRow.getPublicOfferingDateCountIsNull()
                    && l_IntDateCount[i].compareTo(this.ipoProductRow.public_offering_date_count) >= 0 )
                {
                    l_blnCheck = false;
                    break;
                }                
            }   
        }
        
        if (!l_blnCheck)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00596,
                getClass().getName() + STR_METHOD_NAME); 
        }
        
        log.exiting(STR_METHOD_NAME);     
    } 
    
    /**
     * (validate株式銘柄)<BR>
     * 株式銘柄／株式取引銘柄に銘柄が存在するかをチェックする。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（IPO商品）validate株式銘柄」参照。 <BR>
     */
    public void validateProduct() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateProduct()";
        log.entering(STR_METHOD_NAME);
        
        //1.1.isディスカウント扱い()
        boolean l_blisDH = this.isDiscountHandling();
        
        //1.2.(*)ディスカウント扱いでない場合(isディスカウント扱い()==false),処理終了
        if(!l_blisDH)
        {
            return;
        }
        
        //1.3.get市場コード()
        String l_strMarketCode = this.getMarketCode();
        
        //1.4.reset市場コード(市場コード)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
        //1.5.reset受付時間区分(受付時間区分(="株式・信用"))
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //1.6.getTradingModule(ProductTypeEnum."株式")
        ////FinAppの取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
              
        //1.7.getProductManager
        l_tradingModule.overrideProductManager(new WEB3EquityProductManager());
        WEB3EquityProductManager l_eqProdMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        
        //1.8.getInstitution()
        Institution l_instInstitution = this.getInstitution();
        
        
        //1.9.get株式銘柄コード
        String l_strProdCode = this.getIpoProductCode();
        
        
        //1.10.getTradedProduct(証券会社,銘柄コード,市場コード)
        log.debug("証券会社コード = "+l_instInstitution.getInstitutionCode());
        log.debug("株式銘柄コード = "+l_strProdCode);
        log.debug("市場コード = "+l_strMarketCode);
        try
        {

            l_eqProdMgr.getTradedProduct(l_instInstitution,
                                         l_strProdCode,
                                         l_strMarketCode);
        }
        catch(NotFoundException nf_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                            getClass().getName() + STR_METHOD_NAME); 
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (createForUpdateParams ())<BR>
     * this.IPO銘柄行をコピーして、<BR>
     * 同じ内容の別インスタンスを作成する（clone）。<BR>
     * <BR>
     * 作成したコピーを自身のthis.IPO銘柄行にセットする。<BR>
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        IpoProductParams l_ipoProductParams = new IpoProductParams(this.ipoProductRow);
        this.ipoProductRow = l_ipoProductParams;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get目論見書閲覧区分())<BR>
     * this.IPO銘柄行.目論見書閲覧区分を返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getDocReadingDiv()
    {
        final String STR_METHOD_NAME = "getDocReadingDiv()";
        log.entering(STR_METHOD_NAME);

        String l_strDocReadingDiv = ipoProductRow.getDocReadingDiv();

        log.exiting(STR_METHOD_NAME);
        return l_strDocReadingDiv;
    }

}
@
