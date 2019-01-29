head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoTradedProductImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP取引銘柄クラス(WEB3IfoTradedProductImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 李強(中訊) 新規作成
Revesion History : 2004/06/11 李強(中訊) 属性(証券会社コード、銘柄コード、市場コード)を追加
Revesion History : 2007/02/09 趙林鵬(中訊) モデル No.630                   
Revesion History : 2007/06/08 張騰宇(中訊) モデル No.649
*/
package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlDbUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradedProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3QuoteFromDivDef;
import webbroker3.common.define.WEB3RegistDivisionDef;
import webbroker3.common.define.WEB3SkipMarketCodeDef;
import webbroker3.common.define.WEB3SkipProductCodeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.data.IfoOrderCarryoverSkipProdRow;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP取引銘柄オブジェクト<BR>
 * 先物OP取引銘柄オブジェクトクラス<BR>
 *（DBレイアウト 「先物OP取引銘柄テーブル.xls」参照）<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3IfoTradedProductImpl extends IfoTradedProductImpl 
{
    /**
     * 先物OP取引銘柄行オブジェクト<BR>
     * （自動生成DAOクラス）<BR>
     */
    private IfoTradedProductRow futuresOptionTradedProductRow;

    /**
     * (証券会社コード)<BR>
     */
    private String institutionCode;

    /**
     * (銘柄コード)<BR>
     */
    private String productCode;

    /**
     * (市場コード)<BR>
     */
    private String marketCode;
    
    /**
     * Logger
     */
    public static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3IfoTradedProductImpl.class);
        
    /**
     * コンストラクタ。<BR>
     * 引数の取引銘柄IDに一致する先物OP取引銘柄オブジェクトを生成し返却する。<BR>
     * <BR>
     * １）　@取引銘柄取得<BR>
     * 　@取引銘柄IDにて先物OP取引銘柄を取得する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@取得結果の行オブジェクト（先物OP取引銘柄Row）に<BR>
     *   証券会社コード、銘柄コード、市場コードをセットする<BR>
     *
     * @@param l_lngTradedProductID - 取引銘柄ID
     * @@return webbroker3.ifo.WEB3IfoTradedProductImpl
     * @@roseuid 405E7AF503D6
     */

    public WEB3IfoTradedProductImpl(long l_lngTradedProductID) 
        throws DataQueryException, DataNetworkException, DataFindException
    {
        super(GtlDbUtils.getTradedProductRow(l_lngTradedProductID));
        this.futuresOptionTradedProductRow = (IfoTradedProductRow)super.getDataSourceObject();
		
		//証券会社コード、銘柄コード、市場コードをセットする
		this.institutionCode = this.futuresOptionTradedProductRow.getInstitutionCode();
		log.debug("institutionCode = " + institutionCode);  

		WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(futuresOptionTradedProductRow.getProductId());
		this.productCode = l_ifoProduct.getProductCode();
		log.debug("productCode = " + productCode);

		if (l_ifoProduct.getPrimaryMarket() != null)
		{
			this.marketCode = l_ifoProduct.getPrimaryMarket().getMarketCode();
		}
		log.debug("marketCode = " + marketCode);
    }
    
    public WEB3IfoTradedProductImpl(TradedProductRow l_row) 
        throws DataQueryException, DataNetworkException, DataFindException
    {        
        super(l_row);      
        this.futuresOptionTradedProductRow = (IfoTradedProductRow)super.getDataSourceObject();
        
        //証券会社コード、銘柄コード、市場コードをセットする
        this.institutionCode = this.futuresOptionTradedProductRow.getInstitutionCode();
        log.debug("institutionCode = " + institutionCode);  

		WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(l_row.getProductId());
        this.productCode = l_ifoProduct.getProductCode();
        log.debug("productCode = " + productCode);

        if (l_ifoProduct.getPrimaryMarket() != null)
        {
            this.marketCode = l_ifoProduct.getPrimaryMarket().getMarketCode();
        }
        log.debug("marketCode = " + marketCode);  
    }    
    
    /**
     * （getDeliveryDateShiftDaysの実装）<BR>
     * <BR>
     * １を返却する。<BR>
     * （受渡日が発注日の何日後になるかの日数）<BR>
     * @@return int
     * @@roseuid 405ABF0C0306
     */
    protected int getDeliveryDateShiftDays() 
    {
        return 1;
    }
    
    /**
     * this.先物OP取引銘柄Rowを返却する。<BR>
     * @@return Object
     * @@roseuid 405E7AF503B6
     */
    public Object getDataSourceObject() 
    {
        return this.futuresOptionTradedProductRow;
    }
    
    /**
     * (get清算値)<BR>
     * <BR>
     * 該当銘柄の清算値を取得する。<BR>
     * <BR>
     * 本オブジェクトが保持する行オブジェクトの清算値を返却する。<BR>
     * @@return double
     * @@roseuid 40612A87027D
     */
    public double getLiquidationPrice() 
    {
        return this.futuresOptionTradedProductRow.getLiquidationPrice();
    }
    
    /**
     * (is取引規制)<BR>
     * <BR>
     * （isTradingSuspendedのオーバーロード）<BR>
     * 　@本オブジェクトが保持する行オブジェクトの項目値にて<BR>
     * 取引規制を判定、判定結果を返却する。<BR>
     * <BR>
     * １） 各取引の規制判定<BR>
     * ○　@新規建買（is買建==true && is新規建==true）の場合<BR>
     * 　@先物OP取引銘柄.新規売建停止 == ”停止中”の場合trueを返す。<BR>
     * <BR>
     * ○　@新規建売（is買建==false && is新規建==true）の場合<BR>
     * 　@先物OP取引銘柄.新規買建停止 == ”停止中”の場合trueを返す。<BR>
     * <BR>
     * ○　@売建買返済（is買建==false && is新規建==false）の場合<BR>
     * 　@先物OP取引銘柄.売建買返済停止 == ”停止中”の場合trueを返す。<BR>
     * <BR>
     * ○　@買建売返済（is買建==true && is新規建==false）の場合<BR>
     * 　@先物OP取引銘柄.買建売返済停止 == ”停止中”の場合trueを返す。<BR>
     * <BR>
     * ２）　@売買停止（全取引）の判定<BR>
     * 　@this.is取引規制()（引数なしのオーバーロードメソッド）の戻り値を返却する。<BR>
     * @@param l_blnLongOrder - （isLongOrder）
     * 買建かどうかの判定。
     * 買建の場合true、売建の場合false。
     * @@param l_blnIsOpenContract - (is新規建)<BR>
     * <BR>
     * 新規建取引かどうかの判定。<BR>
     * 新規建の場合true、返済の場合false。<BR>
     * 
     * @@return boolean
     * @@roseuid 40643AC3036C
     */
    public boolean isTradingSuspended(
        boolean l_blnLongOrder, 
        boolean l_blnIsOpenContract) 
    {   
        //１） 各取引の規制判定         
        if (l_blnLongOrder && l_blnIsOpenContract)
        {   
            log.debug("is買建 == true ;is新規建 == true");
            //先物OP取引銘柄.新規買建停止 == ”停止中”の場合trueを返す。
            if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getBuyToOpenStopFlag()))
            {
                return true;
            }
        }
        else if ((!l_blnLongOrder) && l_blnIsOpenContract)        
        {   
            log.debug("is買建 == false ;is新規建 == true");
            //先物OP取引銘柄.新規売建停止 == ”停止中”の場合trueを返す。
            if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getSellToOpenStopFlag()))
            {
                return true;
            }
            
        }
        else if ((!l_blnLongOrder) && (!l_blnIsOpenContract))
        {   //先物OP取引銘柄.売建買返済停止 == ”停止中”の場合trueを返す
            if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getBuyToCloseStopFlag()))
            {
                return true;
            }            
        }
        else 
        {   //先物OP取引銘柄.買建売返済停止 == ”停止中”の場合trueを返す
            if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getSellToCloseStopFlag()))
            {
                return true;
            }            
        }
        //２）　@売買停止（全取引）の判定   
        return this.isTradingSuspended();
    }
    
    /**
     * (get原資産時価)<BR>
     * <BR>
     * 該当銘柄の原資産時価を取得する。<BR>
     * <BR>
     * 本オブジェクトが保持する行オブジェクトの原資産時価を返却する。<BR>
     * @@return double
     * @@roseuid 4067BA340104
     */
    public double getUnderlyingCurrentPrice() 
    {
        return this.futuresOptionTradedProductRow.getTargetSpotPrice();
    }
    
    /**
     * (is取引規制)<BR>
     * <BR>
     * （isTradingSuspended）<BR>
     * 　@本オブジェクトが保持する行オブジェクトの項目値にて<BR>
     * 取引規制を判定、判定結果を返却する。<BR>
     * <BR>
     * １） 売買停止（当社規制／取引所規制）の判定<BR>
     * 　@先物OP取引銘柄.売買停止 == ”停止でない”の場合、falseを返す。<BR>
     * 　@以外、trueを返す。<BR>
     * @@return boolean
     * @@roseuid 4076501403C7
     */
    public boolean isTradingSuspended() 
    {   
        log.debug("getTradeStopFlag() =" + this.futuresOptionTradedProductRow.getTradeStopFlag().toString());
        if (BooleanEnum.FALSE.equals(this.futuresOptionTradedProductRow.getTradeStopFlag()))
        {
            return  false;            
        }
        else
        {
            return true;
        }        
    }
    
    /**
     * (is上場)<BR>
     * <BR>
     * 銘柄が対象市場に上場中であるかを判定する。<BR>
     * <BR>
     * 　@本オブジェクトが保持する行オブジェクトの上場フラグが”上場”であればtrue、以外はfalseを返却する。<BR>
     * @@return boolean
     * @@roseuid 407652560399
     */
    public boolean isListed() 
    {
        log.debug("getListFlag() =" + this.futuresOptionTradedProductRow.getListFlag().toString());
        if (BooleanEnum.TRUE.equals(this.futuresOptionTradedProductRow.getListFlag()))
        {
            return true;
        }
        else
        {
            return false;
        }
     
    }
    
    /**
     * (is値幅チェック実施)<BR>
     * <BR>
     * 値幅チェックを行うかを判定する。<BR>
     * <BR>
     * （先物OP取引銘柄.原資産時価 == 0　@または、<BR>
     * 先物OP取引銘柄.清算値 == 0）の場合、falseを返却する。<BR>
     * <BR>
     * 以外（どちらの項目も0でない場合）、trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 407693430379
     */
    public boolean isPriceRangeChecked() 
    {
        log.debug("getUnderlyingCurrentPrice() = " + this.getUnderlyingCurrentPrice());
        log.debug("getLiquidationPrice() = " + this.getLiquidationPrice());
        if (this.getUnderlyingCurrentPrice() == 0 ||
                this.getLiquidationPrice() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
     
    }
    
    /**
     * (is繰越スキップ銘柄)<BR>
     * <BR>
     * 注文繰越処理のスキップ対象銘柄であるかどうかを判定する。<BR>
     * 繰越スキップ銘柄である場合はtrueを、そうでない場合はfalseを返す。<BR>
     * <BR>
     * １）　@先物OP注文繰越スキップ銘柄テーブルより、以下の条件でデータを取得する。<BR>
     * <BR>
     * 　@　@証券会社コード＝this.証券会社コード<BR>
     * 　@　@かつ　@銘柄コード＝(ALL(全銘柄) or this.銘柄コード)<BR>
     * 　@　@かつ　@市場コード＝(F(全市場) or this.市場コード)<BR>
     * 　@　@かつ　@登録区分＝1(注文繰越スキップ)<BR>
     * <BR>
     * ２）　@該当するデータが存在する場合はtrueを、該当データなしの場合はfalseを返す。<BR>
     * @@return boolean
     * @@roseuid 407CD5130242
     */
    public boolean isCarryOverSkipProduct()         
        throws WEB3BaseException
    {
        String METHOD_NAME = "isCarryOverSkipProduct()";
        log.entering(METHOD_NAME);        
        
        //返還の値の設定
        boolean l_blnReturn = false;
        List l_lisRows = null;
        
        try
        {
            String l_strWhere = " institution_code = ? and (product_code = ? or product_code = ?)"
            + " and (market_code = ? or market_code = ?) and regist_division = ? ";
            
            Object l_bindVars[] = 
                {
                    this.institutionCode,
                    WEB3SkipProductCodeDef.ALL,
                    this.productCode,
                    WEB3SkipMarketCodeDef.FULL_MARKET,
                    this.marketCode,
                    WEB3RegistDivisionDef.ORDER_TRANSFER_STOP
                };
            l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                    IfoOrderCarryoverSkipProdRow.TYPE,
                    l_strWhere,
                    null,
                    null,
                    l_bindVars);                
          } 
          catch(DataException l_de)
          {
              log.error(l_de.getMessage(), l_de);  
              log.exiting(METHOD_NAME);
              throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + METHOD_NAME,
                            l_de.getMessage(),
                            l_de);
          }
          
          if (l_lisRows != null && l_lisRows.size() != 0)
          {
              //該当するデータが存在する場合はtrueを
              l_blnReturn = true;
          }
          
          log.exiting(METHOD_NAME);
          return l_blnReturn;
    }
    
    /**
     * (is上場期間内)<BR>
     * <BR>
     * 銘柄が上場中であるかを判定する。<BR>
     * <BR>
     * １）　@発注日取得<BR>
     * 　@取引時間管理.get発注日()にて発注日を取得する。<BR>
     * <BR>
     * ２）　@上場期間中判定<BR>
     * 　@本オブジェクトが保持する行オブジェクトの項目値と発注日を次の通り比較し、<BR>判定結果を返却する。<BR>
     * <BR>
     * ○（先物OP取引銘柄.売買開始日 <= 発注日 <= 先物OP取引銘柄.売買終了日） &&<BR>
     * 　@（先物OP取引銘柄.上場（登録）日 <= 発注日 < 先物OP取引銘柄.上場（登録）廃止日）の場合<BR>
     * 　@trueを返却する。<BR>
     * <BR>
     * ○上記以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 409F7E5F02F8
     */
    public boolean isInListedTerm() 
        throws WEB3BaseException
    {             
        Date l_datOrder;        
        Date l_datStartTradingDate;
        Date l_datLastTradingDate;
        Date l_datListDate;
        Date l_datUnlistedDate;        
           
        l_datOrder              = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_datStartTradingDate   = this.futuresOptionTradedProductRow.getStartTradingDate();
        l_datLastTradingDate    = this.futuresOptionTradedProductRow.getLastTradingDate();
        l_datListDate           = this.futuresOptionTradedProductRow.getListDate();
        l_datUnlistedDate       = this.futuresOptionTradedProductRow.getUnlistedDate();               
      
        log.debug("発注日 = " + l_datOrder);
        log.debug("売買開始日 = " + l_datStartTradingDate);
        log.debug("売買終了日 = " + l_datLastTradingDate);
        log.debug("上場(登録)日 = " + l_datListDate);
        log.debug("上場(登録)廃止日 = " + l_datUnlistedDate);
        
        if ((WEB3DateUtility.compareToDay(l_datOrder, l_datStartTradingDate) >= 0 
            && WEB3DateUtility.compareToDay(l_datOrder, l_datLastTradingDate) <= 0) 
            && WEB3DateUtility.compareToDay(l_datOrder, l_datListDate) >= 0 
            && WEB3DateUtility.compareToDay(l_datOrder, l_datUnlistedDate) < 0)
        {
            return true;
        }
        else
        {
            return false;
        }
        
     
    }
    
    /**
     * (get時価情報)<BR>
     * 時価情報（時価取得区分、時価、時価発表時間）を取得する。 <BR>
     * 時価情報を取得できなかった場合は、nullを返す。 <BR>
     * <BR>
     * １）戻り値オブジェクト生成<BR>
     * 　@先物OP時価情報インスタンスを生成し、以下のプロパティをセットする。<BR>
     * <BR>
     * 　@先物OP時価情報.市場コード = this.getMarket().市場コード<BR>
     * <BR>
     * ２）時価情報取得サービスを取得する。<BR>
     * 　@WEB3DefaultQuoteDataSupplierService.getIfoQuote()メソッドを<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[getQuote()にセットするパラメータ]<BR>
     * 　@　@取引銘柄：　@this<BR>
     * 　@　@リアル区分：　@<BR>
     * 　@　@　@[パラメータ.補助口座 == nullの場合]<BR>
     * 　@　@　@　@"リアル"をセット。<BR>
     * 　@　@　@[上記以外]<BR>
     * 　@　@　@　@①@パラメータ.補助口座.getMainAccount()により<BR>
     * 　@　@　@　@　@顧客オブジェクトを取得する。<BR>
     * 　@　@　@　@②顧客オブジェクト.isリアル顧客() == trueの場合、"リアル"をセット。<BR>
     * 　@　@　@　@　@以外、"20分ディレイ"をセット。<BR>
     * <BR>
     * ３）時価取得<BR>
     * 　@以下の順序で２）の戻り値より時価、時価発表時刻を取得し、<BR>
     * 　@時価を取得できた(取得した時価 > 0)時点で、<BR>
     * 　@生成した先物OP時価情報のプロパティにセットする。<BR>
     * 　@　@①@現在値、現在値時刻<BR>
     * 　@　@②売気配値、売気配値時刻<BR>
     * 　@　@③買気配値、買気配値時刻<BR>
     * 　@　@④this.基準値(終値)　@※④の場合、時価発表時刻はnullとする。　@<BR>
     * <BR>
     * 　@先物OP時価情報.時価 = 取得した時価<BR>
     * 　@先物OP時価情報.前日比 = 時価情報取得サービス.getChange()<BR>
     * 　@先物OP時価情報.時価取得時間 = 取得した時価発表時刻<BR>
     * 　@先物OP時価情報.時価取得区分 = "時価"　@※④の場合、"前日終値"をセット。<BR>
     * <BR> 
     * 　@時価が取得できなかった場合は、nullを返却して終了する。<BR>
     * <BR>
     * ４）プロパティセットした先物OP時価情報を返却する。<BR>
     * <BR>
     * @@param l_subAccount - 補助口座オブジェクト<BR>
     * <BR>
     * ※時価取得区分(リアル or 20分ディレイ)の判別に使用。<BR>
     * 　@常にリアルで取得する場合は、nullをセット。<BR>
     * @@return webbroker3.ifo.WEB3IfoCurrentInfo
     * @@roseuid 41AC32D5038A
     */
    public WEB3IfoProductQuote getCurrentInfo(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrentInfo()";
        log.entering(STR_METHOD_NAME);
        try
        {

            //先物OP時価情報インスタンスを生成し
            WEB3IfoProductQuote l_ProductQuote = new WEB3IfoProductQuote();
            //先物OP時価情報.市場コード = this.getMarket().市場コード
            l_ProductQuote.setMarketCode(this.getMarket().getMarketCode());
            // ２）時価情報取得サービスを取得する。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3QuoteDataSupplierService l_supplierService = (WEB3QuoteDataSupplierService)l_tradingModule.getQuoteDataSupplierService();
            RealType l_realType = null;
            //パラメータ.補助口座 == nullの場合
            if (l_subAccount == null)
            {
                l_realType = RealType.REAL;
            }
            else
            {
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
                //顧客オブジェクト.isリアル顧客() == trueの場合、"リアル"をセット。           
                if (l_mainAccount.isRealCustomer())
                {
                    l_realType = RealType.REAL;
                }
                //以外、"20分ディレイ"をセット。
                else
                {
                    l_realType = RealType.DELAY;
                }
            }
        
            //３）時価取得
            WEB3IfoQuoteData l_IfoQuoteData = l_supplierService.getIfoQuote(this,l_realType);
            //先物OP時価情報.前日比 = 時価情報取得サービス.getChange() 
            double l_dblChange = l_IfoQuoteData.getChange();
            log.debug("先物OP時価情報.前日比 = " + l_dblChange);
            l_ProductQuote.setComparedPreviousDay(l_dblChange);
            //①@現在値、現在値時刻
            double l_dblcurrentPrice = l_IfoQuoteData.getCurrentPrice();
            if (Double.isNaN(l_dblcurrentPrice))
            {
                l_dblcurrentPrice = 0;
            }
            
            log.debug("先物OP時価情報.現在値 = " + l_dblcurrentPrice);
            if (l_dblcurrentPrice > 0)
            {
                l_ProductQuote.setCurrentPrice(l_dblcurrentPrice);
                //先物OP時価情報.時価取得時間 = 取得した時価発表時刻
                l_ProductQuote.setCurrentPriceTime(l_IfoQuoteData.getCurrentPriceTime());
                //先物OP時価情報.時価取得区分 = "時価"
                l_ProductQuote.setCurrentPriceDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
                
                log.exiting(STR_METHOD_NAME);
                return l_ProductQuote;
            }
            //②売気配値、売気配値時刻
            double l_dblBidPrice = l_IfoQuoteData.getBidPrice();
            if (Double.isNaN(l_dblBidPrice))
            {
                l_dblBidPrice = 0;
            }
            
            log.debug("先物OP時価情報.売気配値 = " + l_dblBidPrice);
            if (l_dblBidPrice > 0)
            {
                l_ProductQuote.setCurrentPrice(l_dblBidPrice);
                //先物OP時価情報.時価取得時間 = 取得した時価発表時刻
                l_ProductQuote.setCurrentPriceTime(l_IfoQuoteData.getBidPriceTime());
                //先物OP時価情報.時価取得区分 = "時価"
                l_ProductQuote.setCurrentPriceDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
                
                log.exiting(STR_METHOD_NAME);
                return l_ProductQuote;
            }
            //③買気配値、買気配値時刻
            double l_dblAskPrice = l_IfoQuoteData.getAskPrice();
            if (Double.isNaN(l_dblAskPrice))
            {
                l_dblAskPrice = 0;
            }
            
            log.debug("先物OP時価情報.買気配値 = " + l_dblAskPrice);
            if (l_dblAskPrice > 0)
            {
                l_ProductQuote.setCurrentPrice(l_dblAskPrice);
                //先物OP時価情報.時価取得時間 = 取得した時価発表時刻
                l_ProductQuote.setCurrentPriceTime(l_IfoQuoteData.getAskPriceTime());
                //先物OP時価情報.時価取得区分 = "時価"
                l_ProductQuote.setCurrentPriceDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
                
                log.exiting(STR_METHOD_NAME);
                return l_ProductQuote;
            }
            //④this.基準値(終値)　@※④の場合、時価発表時刻はnullとする。
            double l_dblClosingPrice = this.getLastClosingPrice();
            if (Double.isNaN(l_dblClosingPrice))
            {
                l_dblClosingPrice = 0;
            }
            
            log.debug("基準値 = " + l_dblClosingPrice);
            if (l_dblClosingPrice > 0)
            {
                l_ProductQuote.setCurrentPrice(l_dblClosingPrice);
                l_ProductQuote.setCurrentPriceTime(null);
                //④の場合、"前日終値"をセット。
                l_ProductQuote.setCurrentPriceDiv(WEB3QuoteFromDivDef.LAST_CLOSING_PRICE);
                
                log.exiting(STR_METHOD_NAME);
                return l_ProductQuote;
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        //時価が取得できなかった場合は、nullを返却して終了する。
        log.exiting(STR_METHOD_NAME);    
        return null;
    }

    /**
     * (get受渡日)<BR>
     * （getDailyDeliveryDateのオーバーライドメソッド） <BR>
     * 取引時間帯を加味した受渡日を返却する。 <BR>
     * <BR>
     * １） super.getDailyDeliveryDateにて受渡日を取得する。 <BR>
     * <BR>
     * ２） 取引時間帯を判定する。 <BR>
     * <BR>
     * 　@　@［取引時間管理.is夕場時間帯() == trueの場合］ <BR>
     * 　@　@　@　@営業日計算.calc営業日()(*)にて算出された受渡日を返却する。 <BR>
     * <BR>
     * 　@　@［取引時間管理.is夕場時間帯() == falseの場合］ <BR>
     * 　@　@　@　@１）で取得した受渡日を返却する。 <BR>
     * <BR>
     * <BR>
     * 　@　@(*) 営業日計算.calc営業日()への引数 <BR>
     * 　@　@　@　@　@　@基準日 ： 1）で取得した受渡日 <BR>
     * 　@　@　@　@　@　@加算／減算日数 ： 1（翌営業日）<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getDailyDeliveryDate()
    {
        final String STR_METHOD_NAME = "getDailyDeliveryDate()";
        log.entering(STR_METHOD_NAME);
        //１） super.getDailyDeliveryDateにて受渡日を取得する。
        Date l_datDeliveryDate = super.getDailyDeliveryDate();

        //２） 取引時間帯を判定する。
        //　@　@［取引時間管理.is夕場時間帯() == trueの場合］
        //　@　@　@　@営業日計算.calc営業日()(*)にて算出された受渡日を返却する。
        //　@　@(*) 営業日計算.calc営業日()への引数
        //　@　@　@　@　@　@基準日 ： 1）で取得した受渡日
        //　@　@　@　@　@　@加算／減算日数 ： 1（翌営業日）
        Date l_datRetDeliveryDate = null;
        try
        {
            if (WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone())
            {
                WEB3GentradeBizDate l_bizDate =
                    new WEB3GentradeBizDate(new Timestamp(l_datDeliveryDate.getTime()));
                l_datRetDeliveryDate = l_bizDate.roll(1);
            }
            //　@　@［取引時間管理.is夕場時間帯() == falseの場合］
            //　@　@　@　@１）で取得した受渡日を返却する。
            else
            {
                l_datRetDeliveryDate = l_datDeliveryDate;
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_datRetDeliveryDate;
    }
}
@
