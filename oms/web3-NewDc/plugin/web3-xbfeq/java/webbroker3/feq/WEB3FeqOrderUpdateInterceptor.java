head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文更新インタセプタ(WEB3FeqOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19王煜 (中訊) 新規作成
                   2005/07/27 艾興　@(中訊) レビュー
*/
package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;

import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文更新インタセプタ)<BR>
 * 外国株式注文更新インタセプタクラス<BR>
 * @@author 王煜(中訊)
 * @@version 1.0
*/
public class WEB3FeqOrderUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{
    
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderUpdateInterceptor.class);

    /**
     * (注文内容)<BR>
     * 外国株式新規注文内容オブジェクト<BR>
     */
    private WEB3FeqNewOrderSpec orderSpec;
    
    /**
     * (金額計算結果)<BR>
     * 外国株式金額計算結果オブジェクト<BR>
     */
    private WEB3FeqAmountCalcResult amountRound;
    
    /**
     * (計算単価)<BR>
     * 計算単価<BR>
     */
    private double roundPrice;
    
    /**
     * (発注条件)<BR>
     * 発注条件<BR>
     */
    private String orderConditionType;
    
    /**
     * (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     */
    private String orderCondOperator;
    
    /**
     * (発注条件単価)<BR>
     * 発注条件単価<BR>
     */
    private double orderConditionTypePrice;

    
    /**
     * @@roseuid 42D0DB2F0242
     */    
    public WEB3FeqOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * (外国株式注文更新イベントインタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * パラメータの項目を、各プロパティにセットする。<BR>
     * @@param l_orderSpec - (注文内容)<BR>
     * 注文内容<BR>
     * 
     * @@param l_calcResult - (計算結果)<BR>
     * 計算結果<BR>
     * 
     * @@param l_dblCalcPrice - (計算単価)<BR>
     * 計算単価<BR>
     * 
     * @@param l_strOrderCond - (発注条件)<BR>
     * 発注条件<BR>
     * 
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * 
     * @@param l_dblOrderCondPrice - (発注条件単価)<BR>
     * 発注条件単価<BR>
     * @@roseuid 428C83A6010C
     */
    public WEB3FeqOrderUpdateInterceptor(
        WEB3FeqNewOrderSpec l_orderSpec, 
        WEB3FeqAmountCalcResult l_calcResult, 
        double l_dblCalcPrice, 
        String l_strOrderCond, 
        String l_strOrderCondOperator, 
        double l_dblOrderCondPrice) 
    {
        this.orderSpec = l_orderSpec;
        this.amountRound = l_calcResult;
        this.roundPrice = l_dblCalcPrice;
        this.orderConditionType = l_strOrderCond;
        this.orderCondOperator = l_strOrderCondOperator;
        this.orderConditionTypePrice = l_dblOrderCondPrice;         
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * １）　@注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * 　@super.mutate(OrderManagerPersistenceType,<BR>
     * 　@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * 　@をコールする。<BR>
     * <BR>
     * ２）　@注文単位テーブル更新<BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * 項目設定内容は、DB更新仕様<BR>
     * 「買付_外株注文単位テーブル.xls」<BR>
     * 「売付_外株注文単位テーブル.xls」<BR>
     * 参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ<BR>
     * @@param l_context - (処理)<BR>
     * 処理<BR>
     * @@param l_orderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）<BR>
     * @@return FeqOrderUnitParams
     * @@roseuid 428C83A600ED
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, " 
            + "FeqOrderUnitParams) ";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.error("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //引数.更新タイプ != nullの場合、注文（ﾍｯﾀﾞ）テーブル更新を行う
        if (l_updateType != null)
        {
            //１）　@注文（ﾍｯﾀﾞ）テーブル更新
            l_orderUnitParams = super.mutate(
                l_updateType,
                l_context, 
                l_orderUnitParams);
        }
        
        //２）　@注文単位テーブル更新
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
         
        try
        {
            //証券会社コード:補助口座.getInstitution()に該当する証券会社コード
            AccountManager l_accountManager = l_finApp.getAccountManager();
                           
            l_orderUnitParams.setInstitutionCode(
                l_accountManager.getSubAccount(l_orderUnitParams.getAccountId(), 
                l_orderUnitParams.getSubAccountId()).getInstitution().getInstitutionCode());
            
            //発注条件:インタセプタ.get発注条件()
            String l_strOrderConditionType = getOrderConditionType();
            l_orderUnitParams.setOrderConditionType(l_strOrderConditionType);
            
            //発注条件演算子:インタセプタ.get発注条件演算子()
            //(* 発注条件＝0：DEFAULT（条件指定なし）の場合は、null）
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setOrderCondOperator(null);
            }
            else
            {    
                l_orderUnitParams.setOrderCondOperator(getOrderCondOperator());
            }
            
            //逆指値基準値:インタセプタ.get発注条件単価()
            //（* 発注条件＝0：DEFAULT（条件指定なし）の場合は、null）
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setStopOrderPrice(null);
            }
            else
            {    
                l_orderUnitParams.setStopOrderPrice(getOrderConditionTypePrice());
            }
            
            //（W指値）訂正指値:注文内容.get（W指値）訂正指値()
            //(* 発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null）
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                l_orderUnitParams.setWLimitPrice(this.orderSpec.getWLimitPrice());
            }
            else
            {
                l_orderUnitParams.setWLimitPrice(null);    
            }
            
            //決済区分:注文内容.get決済区分()
            l_orderUnitParams.setSettleDiv(this.orderSpec.getSettleDiv());
            
            //初回注文の注文チャネル:セッションより取得した同項目の値
            //セッションより取得した同項目の値
             OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);   

            l_orderUnitParams.setOrderChanel(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
            
            //受注日時:サーバ側でサービスが起動された時間（計算式書（共通）(*2) 処理日付　@を参照）
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            
            //伝票No:"9"(WebBroker)＋識別コードの下3桁
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
                    
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(
                l_orderUnitParams.getBranchId());

            String l_strOrderRequestNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_orderUnitParams.getInstitutionCode(), 
                l_branch.getBranchCode(), 
                ProductTypeEnum.FOREIGN_EQUITY);
            if(l_strOrderRequestNumber == null || l_strOrderRequestNumber.length() < 3)
            {
                log.error("識別コード不整合。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //識別コード:注文識別コード採番サービス.get新規識別コード()
            l_orderUnitParams.setOrderRequestNumber(l_strOrderRequestNumber);  
            
            l_orderUnitParams.setVoucherNo("9" 
                + l_orderUnitParams.getOrderRequestNumber().substring(l_strOrderRequestNumber.length() - 3));
            
            //初回注文の手数料No:金額計算結果.get手数料No()(* 手数料計算時に使用した値）
            l_orderUnitParams.setCommTblNo(this.amountRound.getCommissionNumber());
            
            //初回注文の手数料No枝番:金額計算結果.get手数料No枝番()（* 手数料計算時に使用した値）
            l_orderUnitParams.setCommTblSubNo(this.amountRound.getCommissionBranchNumber());
            
            //扱者コード（SONAR）:顧客.扱者コード（SONAR）（* SONARで管理している顧客の扱者）                
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_accountManager.getMainAccount(l_orderUnitParams.getAccountId()).getDataSourceObject();
            l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
            
            //注文単価:インタセプタ.計算単価()（* 概算受渡代金算出に使用した単価）
            l_orderUnitParams.setPrice(this.roundPrice);            
                                                                      
            //概算受渡代金:金額計算結果.get受渡代金()
            l_orderUnitParams.setEstimatedPrice(this.amountRound.getNetAmount());
            
            //概算受渡代金（外貨）:金額計算結果.get受渡代金（外貨）()
            l_orderUnitParams.setFEstimatedPrice(this.amountRound.getNetAmountFc());
            
            //取引コード（SONAR）: 11：普通株式(WEB3TransactionTypeSONARDefにて定義)
            l_orderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
                            
            //市場コード（SONAR）:市場IDに該当する市場オブジェクト.市場コード(SONAR)
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market  = (WEB3GentradeMarket) l_finObjectManager.getMarket(
                l_orderUnitParams.getMarketId());
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
            l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
           
            //手数料商品コード:４０：外国株式(WEB3CommisionProductCodeDefにて定義)
            l_orderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.FOREIGN_EQITY);
            
            //譲渡益金額:0
            l_orderUnitParams.setCapitalGain(0);
            
            //譲渡益税額:0
            l_orderUnitParams.setCapitalGainTax(0);
            
            //注文訂正・取消区分:0:初期値
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            //注文経路区分:セッションより取得した同項目の値
            l_orderUnitParams.setOrderRootDiv(
                l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));          
            
            //市場から確認済みの注文単価:null                         
            l_orderUnitParams.setConfirmedOrderPrice(null);
            
            //市場から確認済みの概算受渡代金:null                         
            l_orderUnitParams.setConfirmedEstimatedPrice(null);
            
            //市場から確認済みの執行条件:null                          
            l_orderUnitParams.setConfirmedExecConditionType(null);
            
            //注文エラー理由コード:0000：正常
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //ファ@クター:ブランク                
            l_orderUnitParams.setFactor(" ");

            //引数.更新タイプ != nullの場合、運用コードの設定を行う
            if (l_updateType != null)
            {
                //運用コード:外国株式運用コード採番サービス.get新規運用コード()
                WEB3FeqOrderEmpCodeManageService l_service =
                     (WEB3FeqOrderEmpCodeManageService)Services.getService(
                        WEB3FeqOrderEmpCodeManageService.class);
                String l_strBizDate = l_orderUnitParams.getBizDate();
                Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");

                l_orderUnitParams.setOrderEmpCode(l_service.getNewEmpCode(l_market, l_datBizDate));
            }
            
            //顧客区分:”一般”
            l_orderUnitParams.setAccountDiv(WEB3AccountDivDef.NORMAL);
            
            //出来終了処理日時:null
            l_orderUnitParams.setExecEndTimestamp(null);  
            
            //初回注文の注文単位ＩＤ:注文内容.get初回注文の注文単位ID()の戻り値。(WEB3-XBFEQ-A-CD-0065)
            l_orderUnitParams.setFirstOrderUnitId(this.orderSpec.getFirstOrderUnitId());
            
            //更新者コード:"顧客入力の場合： 顧客コード;代理入力の場合： 扱者コード" 
            if (this.orderSpec.getTrader() == null)
            {
                // 顧客コードを取得
                String l_strAccountCode = l_accountManager.getMainAccount(
                    l_orderUnitParams.getAccountId()).getAccountCode();
                l_orderUnitParams.setLastUpdater(l_strAccountCode);
            }
            // 代理入力の場合
            else
            {
                l_orderUnitParams.setLastUpdater(this.orderSpec.getTrader().getTraderCode());
            }  
        }               
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(),
                l_ex);
        }            
        catch (NotFoundException l_nfex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_nfex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfex.getMessage(),
                l_nfex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (get注文内容)<BR>
     * this.注文内容を返却する。<BR>
     * @@return webbroker3.feq.WEB3FeqNewOrderSpec
     * @@roseuid 4295DD2C0212
     */
    public WEB3FeqNewOrderSpec getOrderSpec() 
    {
        return this.orderSpec;
    }
    
    /**
     * (get金額計算結果)<BR>
     * this.金額計算結果を返却する。<BR>
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 4295DD510203
     */
    public WEB3FeqAmountCalcResult getAmountRound() 
    {
        return this.amountRound;
    }
    
    /**
     * (get計算単価)<BR>
     * this.計算単価を返却する。<BR>
     * @@return double
     * @@roseuid 4295DD760195
     */
    public double getRoundPrice() 
    {
        return this.roundPrice;
    }
    
    /**
     * (get発注条件)<BR>
     * this.発注条件を返却する。<BR>
     * @@return String
     * @@roseuid 4295DD9301D4
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }   
    
    /**
     * (get発注条件演算子)<BR>
     * this.発注条件演算子を返却する。<BR>
     * @@return String
     * @@roseuid 4295DDB002CE
     */
    public String getOrderCondOperator() 
    {
        return this.orderCondOperator;
    }
    
    /**
     * (get発注条件単価)<BR>
     * this.発注条件単価を返却する。<BR>
     * @@return double
     * @@roseuid 4295DDB202FD
     */
    public double getOrderConditionTypePrice() 
    {
        return this.orderConditionTypePrice;
    }
}
@
