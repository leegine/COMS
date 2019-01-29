head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginUpdateEventInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 更新イベントインタセプタ(WEB3MarginUpdateEventInterceptor.java)
Author Name      : 2004/9/2４   盧法@旭(中訊) 新規作成
Revesion History : 2004/11/1    法@旭　@修正   
                 　@2004/11/30   SRA水落　@残案件対応
                 　@2006/07/19 肖志偉 (中訊) ＤＢ更新仕様156
                   2006/11/01 柴双紅 (中訊) ＤＢ更新仕様No.180
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （更新イベントインタセプタ）。<BR>
 * <BR>
 * 拡張株式注文マネージャによる、<BR>
 * 注文単位／注文履歴／約定オブジェクトの更新仕様カスタマイズのための<BR>
 * 基底クラス。
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginUpdateEventInterceptor implements EqTypeOrderManagerPersistenceEventInterceptor 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginUpdateEventInterceptor.class);
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １）　@注文単位オブジェクト取得<BR>
     * <BR>
     * 　@引数の注文単位Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@○注文エラー理由コード<BR>
     * 　@　@パラメータ.注文履歴Params.注文エラー理由コードに”0000:正常”をセットする。<BR>
     * 　@<BR>
     * 　@○その他の項目　@<BR>　@　@　@　@　@　@　@　@　@　@　@
     * 　@　@パラメータ.注文履歴Paramsの拡張項目に、注文単位オブジェクトの同名項目から値をセットし、<BR>
     * 　@　@返却する。<BR>
     * <BR>
     * @@param l_updateType - 更新タイプ<BR>
     * INSERTまたは、UPDATE。<BR>
     * EqTypeOrderManagerPersistenceTypeにて定数定義。<BR>
     * <BR>
     * @@param l_process - 処理<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderHistoryParams - 株式注文履歴Params<BR>
     * 株式注文履歴が保持する項目のパラメータクラス。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams
     * @@roseuid 40B1901D0176
     */
    public EqtypeOrderActionParams mutate(OrderManagerPersistenceType l_updateType, OrderManagerPersistenceContext l_process, EqtypeOrderActionParams l_orderHistoryParams) 
    {
        final String STR_METHOD_NAME = "mutate";
        log.entering(STR_METHOD_NAME);
        if (l_orderHistoryParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        long l_lngOrderUnitId = l_orderHistoryParams.getOrderUnitId();
        //get the order unit object
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager) l_tradingMod.getOrderManager();                
        EqTypeOrderUnit l_orderUnit;
        
        try 
        {
            l_orderUnit = (EqTypeOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);
            log.debug("get the order Unit object susussfully!");
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        // 取引者ＩＤ（注文単位テーブル．取引者ＩＤ）
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_orderHistoryParams.setTraderId(null);
        }
        else
        {
            l_orderHistoryParams.setTraderId(l_orderUnitRow.getTraderId());
        }
        // set error reason code normal:0000
        l_orderHistoryParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        //値段条件
        l_orderHistoryParams.setPriceConditionType(l_orderUnitRow.getPriceConditionType());
        //発注条件
        l_orderHistoryParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());
        //発注条件演算子
        l_orderHistoryParams.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());
        
        double l_dblSPrice = l_orderUnitRow.getStopOrderPrice();
        if (l_orderUnitRow.getStopOrderPriceIsNull())
        {
            l_orderHistoryParams.setStopOrderPrice(null);
        }
        else
        {
            //逆指値基準値
            log.debug("逆指値基準値" + l_dblSPrice);
            l_orderHistoryParams.setStopOrderPrice(l_dblSPrice);            
        }

        double l_dblWPrice = l_orderUnitRow.getWLimitPrice();
        if (l_orderUnitRow.getWLimitPriceIsNull())
        {
            l_orderHistoryParams.setWLimitPrice(null);
        }
        else
        {
            //（W指値）訂正指値
            l_orderHistoryParams.setWLimitPrice(l_dblWPrice);
            log.debug("（W指値）訂正指値" + l_dblWPrice);            
        }

        //注文失効日付
        l_orderHistoryParams.setExpirationDate(l_orderUnitRow.getExpirationDate());
        //概算受渡代金
        double l_dblEstimatePrice = l_orderUnitRow.getEstimatedPrice();
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_orderHistoryParams.setEstimatedPrice(null);            
        }
        else 
        {
            log.debug("概算受渡代金" + l_dblEstimatePrice);
            l_orderHistoryParams.setEstimatedPrice(l_dblEstimatePrice);            
        }

        //注文訂正・取消区分
        l_orderHistoryParams.setModifyCancelType(l_orderUnitRow.getModifyCancelType());
        
        // 注文経路区分（注文単位テーブル．注文経路区分）
        l_orderHistoryParams.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());
        
        //決済順序区分
        l_orderHistoryParams.setClosingOrderType(l_orderUnitRow.getClosingOrderType());
        
        //リクエストタイプ
        l_orderHistoryParams.setRequestType(l_orderUnitRow.getRequestType());
        if (l_orderUnitRow.getLimitPriceIsNull())
        {
            l_orderHistoryParams.setPrice(null);
        }
        else
        {
            l_orderHistoryParams.setPrice(l_orderUnitRow.getLimitPrice());
        }
        
        //IPアドレス
		OpLoginSecurityService l_securityService =
			(OpLoginSecurityService) Services.getService(
				OpLoginSecurityService.class);
		try
		{
			String l_strIpAddress = 
				l_securityService.getSessionProperty(
					WEB3SessionAttributeDef.IP_ADDRESS);
			l_orderHistoryParams.setIpAddress(l_strIpAddress);
		}
		catch (IllegalSessionStateException e)
		{
			l_orderHistoryParams.setIpAddress(null);
		}
        
        //元発注条件
        l_orderHistoryParams.setOrgOrderConditionType(l_orderUnitRow.getOrgOrderConditionType());
        
        //元発注条件演算子
        l_orderHistoryParams.setOrgOrderCondOperator(l_orderUnitRow.getOrgOrderCondOperator());
        
        //元逆指値基準値
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_orderHistoryParams.setOrgStopOrderPrice(null);
        }
        else 
        {
            l_orderHistoryParams.setOrgStopOrderPrice(l_orderUnitRow.getOrgStopOrderPrice());
        }
        
        //元（W指値）訂正指値
        if (l_orderUnitRow.getOrgWLimitPriceIsNull())
        {
            l_orderHistoryParams.setOrgWLimitPrice(null);
        }
        else 
        {
            l_orderHistoryParams.setOrgWLimitPrice(
                l_orderUnitRow.getOrgWLimitPrice());
        }
        
        //元（W指値）執行条件
        l_orderHistoryParams.setOrgWLimitExecCondType(
            l_orderUnitRow.getOrgWLimitExecCondType());
        
        //（W指値）執行条件
        l_orderHistoryParams.setWLimitExecCondType(
            l_orderUnitRow.getWLimitExecCondType());
        
        //（W指値）切替前指値
        if (l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
        {
            l_orderHistoryParams.setWLimitBeforeLimitPrice(null);
        }
        else 
        {
            l_orderHistoryParams.setWLimitBeforeLimitPrice(
                l_orderUnitRow.getWLimitBeforeLimitPrice());
        }
        
        //（W指値）切替前執行条件
        l_orderHistoryParams.setWLimitBeforeExecCondType(
            l_orderUnitRow.getWLimitBeforeExecCondType());

        // 市場から確認済みの執行条件
        l_orderHistoryParams.setConfirmedExecConditionType(
            l_orderUnitRow.getConfirmedExecConditionType());

        log.exiting(STR_METHOD_NAME);
        return l_orderHistoryParams;
    }
    
    /**
     * (更新イベントインタセプタ)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.margin.WEB3MarginUpdateEventInterceptor
     * @@roseuid 40B1901D017A
     */
    public WEB3MarginUpdateEventInterceptor() 
    {
     
    }
    
    /**
     * (更新値設定)<BR>
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams
     * @@roseuid 4142BBF60107
     */
    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, EqtypeOrderUnitParams arg2) 
    {
        return arg2;
    }
    
    /**
     * (更新値設定)<BR>
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams
     * @@roseuid 4142BBF60139
     */
    public EqtypeOrderExecutionParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, EqtypeOrderExecutionParams arg2) 
    {
        return arg2;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@return BatchedQuery
     * @@roseuid 4142BBF60175
     */
    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1) 
    {
        return null;
    }
    

}
@
