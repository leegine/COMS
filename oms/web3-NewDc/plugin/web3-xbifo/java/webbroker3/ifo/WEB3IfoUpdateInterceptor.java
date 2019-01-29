head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP更新イベントインタセプタ(WEB3IfoUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/16 艾興 (中訊) 新規作成
                 : 2006/7/11 李俊 (中訊) ＤＢ更新仕様 096
                 : 2006/7/13 郭英 (中訊) DB更新仕様No.085,086,087,089,090,091,092,093,094,095,100,104,111              
Revesion History : 2008/03/17 張騰宇 (中訊) DB更新仕様No.197
*/
package webbroker3.ifo;

import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoPositionManagerPersistenceEventInterceptor;

import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP更新イベントインタセプタ)<BR>
 * 先物OP更新イベントインタセプタクラス。<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoUpdateInterceptor
    implements
        IfoOrderManagerPersistenceEventInterceptor,
        IfoPositionManagerPersistenceEventInterceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoUpdateInterceptor.class);

    /**
     * コンストラクタ
     * @@roseuid 408CC33C01ED
     */
    public WEB3IfoUpdateInterceptor()
    {

    }

    /**
     * （mutateメソッドの実装）<BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １）　@注文単位オブジェクト取得<BR>
     * <BR>
     * 引数の注文単位Params.注文ID、注文単位ＩＤに該当する<BR>
     * 注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 
     * ２）　@拡張項目セット<BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に、<BR>
     * 注文単位オブジェクトの同名項目から値をセットし、返却する。<BR>
     * 
     * @@param l_updateType - INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_context - （OrderManagerPersistenceContextにて定数定義）
     * @@param l_ifoOrderActionParams - 株式注文履歴が保持する項目のパラメータクラス。
     * @@return webbroker3.ifo.data.IfoOrderActionParams
     * @@roseuid 4084B89C0279
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        IfoOrderActionParams l_ifoOrderActionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_updateType, "
                + "OrderManagerPersistenceContext l_context, "
                + "IfoOrderActionParams l_ifoOrderActionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderActionParams == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            return l_ifoOrderActionParams;
        }

        long l_orderUnitID;
        l_orderUnitID = l_ifoOrderActionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        OrderUnit l_ifoOrderUnit = null;
        IfoOrderUnitParams l_params = null;
        try
        {
            l_ifoOrderUnit = l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager().getOrderUnit(l_orderUnitID);
            l_params = (IfoOrderUnitParams)l_ifoOrderUnit.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
        }

        // 取引者ID
        // 注文単位テーブル.取引者IDより編集
        if (l_params.getTraderId() == 0)
        {   
            l_ifoOrderActionParams.setTraderId(null);
        }
        else
        {
            l_ifoOrderActionParams.setTraderId(l_params.getTraderId());
        }
        
        // 注文経路区分
        // 注文単位テーブル.注文経路区分より編集
        l_ifoOrderActionParams.setOrderRootDiv(l_params.getOrderRootDiv());

        //発注条件
        l_ifoOrderActionParams.setOrderConditionType(l_params.getOrderConditionType());

        //発注条件演算子
        l_ifoOrderActionParams.setOrderCondOperator(l_params.getOrderCondOperator());

        //逆指値基準値タイプ
        l_ifoOrderActionParams.setStopPriceType(l_params.getStopPriceType());
        
        if (l_params.getStopOrderPriceIsNull())
        {
            //逆指値基準値
            l_ifoOrderActionParams.setStopOrderPrice(null);            
        }
        else
        {
            //逆指値基準値
            l_ifoOrderActionParams.setStopOrderPrice(l_params.getStopOrderPrice());                
        }
                
        if (l_params.getWLimitPriceIsNull())
        {
            //（W指値）訂正指値
            l_ifoOrderActionParams.setWLimitPrice(null);            
        }
        else
        {
            //（W指値）訂正指値
            l_ifoOrderActionParams.setWLimitPrice(l_params.getWLimitPrice());                
        }

        //注文失効日付
        l_ifoOrderActionParams.setExpirationDate(WEB3DateUtility.toDay(l_params.getExpirationDate()));
      
        if (l_params.getEstimatedPriceIsNull())
        {
            //概算受渡代金
            l_ifoOrderActionParams.setEstimatedPrice(null);    
        }
        else
        {
            //概算受渡代金
            l_ifoOrderActionParams.setEstimatedPrice(l_params.getEstimatedPrice());              
        }
        
        //注文訂正・取消区分
        l_ifoOrderActionParams.setModifyCancelType(l_params.getModifyCancelType());

        //決済順序
        l_ifoOrderActionParams.setClosingOrder(l_params.getClosingOrder());

        //注文エラー理由コード
        l_ifoOrderActionParams.setErrorReasonCode(l_params.getErrorReasonCode());

        //リクエストタイプ
        l_ifoOrderActionParams.setRequestType(l_params.getRequestType());
        
        //元発注条件
        l_ifoOrderActionParams.setOrgOrderConditionType(l_params.getOrgOrderConditionType());
        
        //元発注条件演算子
        l_ifoOrderActionParams.setOrgOrderCondOperator(l_params.getOrgOrderCondOperator());
        
        //元逆指値基準値タイプ
        l_ifoOrderActionParams.setOrgStopPriceType(l_params.getOrgStopPriceType());
        
        //元逆指値基準値
        if (l_params.getOrgStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_ifoOrderActionParams.setOrgStopOrderPrice(l_params.getOrgStopOrderPrice());
        }
        
        //元（W指値）訂正指値
        if (l_params.getOrgWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_ifoOrderActionParams.setOrgWLimitPrice(l_params.getOrgWLimitPrice());
        }
        
        //元（W指値）執行条件
        l_ifoOrderActionParams.setOrgWLimitExecCondType(l_params.getOrgWLimitExecCondType());

        //（W指値）執行条件
        l_ifoOrderActionParams.setWLimitExecCondType(l_params.getWLimitExecCondType());
       
        //（W指値）切替前指値
        if (l_params.getWLimitBeforeLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitBeforeLimitPrice(null);
        }
        else
        {
            l_ifoOrderActionParams.setWLimitBeforeLimitPrice(l_params.getWLimitBeforeLimitPrice());
        }
        
        //（W指値）切替前執行条件
        l_ifoOrderActionParams.setWLimitBeforeExecCondType(l_params.getWLimitBeforeExecCondType());

        //市場から確認済みの執行条件
        l_ifoOrderActionParams.setConfirmedExecConditionType(l_params.getConfirmedExecConditionType());

        //注文期限区分
        l_ifoOrderActionParams.setExpirationDateType(l_params.getExpirationDateType());
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderActionParams;
    }
    
    public IfoContractParams mutateBeforeInsert(IfoContractParams ifocontractparams)
    {
        return ifocontractparams;
    }

    public BatchedQuery getQueryToExecute(
        OrderManagerPersistenceType OrderManagerPersistenceType,
        Class class1)
    {
        return null;
    }

    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType OrderManagerPersistenceType,
        OrderManagerPersistenceContext OrderManagerPersistenceContext,
        IfoOrderUnitParams ifoorderunitparams)
    {
        return ifoorderunitparams;
    }
    public IfoOrderExecutionParams mutate(
        OrderManagerPersistenceType OrderManagerPersistenceType,
        OrderManagerPersistenceContext OrderManagerPersistenceContext,
        IfoOrderExecutionParams ifoorderexecutionparams)
    {
        return ifoorderexecutionparams;
    }
    public Map mutateBeforeUpdate(IfoContractParams ifocontractparams, Map map)
    {
        return map;
    }

    public Map mutateBeforeUpdate(
        IfoFinTransactionParams ifofintransactionparams,
        Map map)
    {
        return map;
    }
    public IfoFinTransactionParams mutateBeforeInsert(IfoFinTransactionParams ifofintransactionparams)
    {
        return ifofintransactionparams;
    }

}
@
