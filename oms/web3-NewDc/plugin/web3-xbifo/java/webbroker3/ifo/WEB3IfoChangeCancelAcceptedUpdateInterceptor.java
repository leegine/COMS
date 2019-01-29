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
filename	WEB3IfoChangeCancelAcceptedUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright       : (株)大和総研 証券ソリューションシステム第二部
File Name       : 先物OP訂正取消受付更新インタセプタ(WEB3IfoChangeCancelAcceptedUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11   王暁傑 (Sinocom) 新規作成 
                 : 2006/07/13 郭英 (中訊) DB更新仕様 No.100
                   2006/11/29 徐大方 (中訊) DB更新仕様 No.122,123
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.ifo.define.WEB3IfoOrderModifyCancelTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;


/**
 * (先物OP訂正取消受付更新インタセプタ)<BR>
 * 先物OP訂正取消受付更新インタセプタクラス<BR>
 *（取消エラー／訂正エラーの場合、訂正入力前の状態に注文内容を戻す）<BR>
 * @@author  王暁傑
 * @@version 1.0
 */
public class WEB3IfoChangeCancelAcceptedUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelAcceptedUpdateInterceptor.class);
               
    /**
     * エラーコード<BR>
     */
    private String errorCode;
    
    /**
     * @@roseuid 40C07C09008C
     */
    public WEB3IfoChangeCancelAcceptedUpdateInterceptor() 
    {
     
    }
    
    /**
     * (取消受付更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数のエラーコードを自身のプロパティにセット、自身のインスタンスを返却する。<BR>
     * @@param l_strErrorCode - エラーコード
     * 
     * @@return webbroker3.ifo.WEB3IfoChangeCancelAcceptedUpdateInterceptor
     * @@roseuid 40838AEB0242
     */
    public WEB3IfoChangeCancelAcceptedUpdateInterceptor(String l_strErrorCode) 
    {
        this.errorCode = l_strErrorCode;
    }
    
    /**
     * (更新値設定)<BR>
     *（mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １） 拡張項目セット<BR>
     * （注文状態が”発注失敗（変更注文）：NOT_MODIFIED” または<BR> 
     * ”発注失敗（取消注文）：NOT_CANCELLED” の場合<BR>
     * のみ処理を実施する。
     * 　@以外は、引数をそのまま返却し処理を終了する）<BR>
     * <BR>
     * 更新内容は、「先OP訂正取消受付_注文単位テーブル.xls」の<BR>
     * 「（先OP訂正取消受付[訂正受付エラー]）注文単位テーブル」シート、<BR>
     * 「（先OP訂正取消受付[取消受付エラー]）注文単位テーブル」シート参照。<BR>
     * 「（Futures訂正取消受付[訂正受付エラー]）注文単位テーブル」シート、<BR>
     * 「（Futures訂正取消受付[取消受付エラー]）注文単位テーブル」シート参照。<BR>
     * @@param l_updateType - INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。
     * 
     * @@param l_dealing - （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderUnitParams - 注文単位Params<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40838AEB023E
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_dealing, 
        IfoOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = this.getClass().getName()  + 
            "mutate( l_updateType, l_dealing,l_orderUnitParams";
        log.entering( STR_METHOD_NAME);

        if(l_orderUnitParams == null)
        {        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        
        //○　@(*1)OP注文マネージャ.isストップ注文切替中()＝trueの場合、ストップ注文切替中。
        //　@　@引数に設定する注文単位には、更新前の注文単位を指定する
        //　@　@（IfoOrderUnitParamsをOP注文マネージャ.toOrderUnit()にて注文単位型にする）
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_opOrderMgr = (WEB3OptionOrderManagerImpl)
            l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        IfoOrderUnitParams l_orderUnitParamsCopy = new IfoOrderUnitParams(l_orderUnitParams);
        IfoOrderUnit l_orderUnit = (IfoOrderUnit)
            l_opOrderMgr.toOrderUnit(l_orderUnitParamsCopy);
        boolean l_blnIsStopOrderChanging = false;
        try
        {
            l_blnIsStopOrderChanging = l_opOrderMgr.isStopOrderSwitching(l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex); 
        }

        if (OrderStatusEnum.MODIFYING.equals(l_orderUnitParams.getOrderStatus()) ||
            OrderStatusEnum.CANCELLING.equals(l_orderUnitParams.getOrderStatus()))
        {            
            //注文訂正・取消区分
            //ストップ注文切替中(*1)の場合、
            if (l_blnIsStopOrderChanging)
            {
                //A：W指値注文切替中
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING);
            }
        }
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitParams.getOrderStatus()) )
        {
            log.debug("注文状態が”発注失敗（変更注文）：NOT_MODIFIED”の場合");
            
            //市場から確認済の執行条件
            l_orderUnitParams.setExecutionConditionType(l_orderUnitParams.getConfirmedExecConditionType());

            if (l_blnIsStopOrderChanging)
            {
                //元発注条件
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());

                //元発注条件演算子
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());

                //元逆指値基準値タイプ
                l_orderUnitParams.setOrgStopPriceType(l_orderUnitParams.getStopPriceType());

                //元逆指値基準値
                if (!l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }

                //元（W指値）訂正指値
                if (!l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }

                //元（W指値）執行条件
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());

                //発注条件
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //発注条件演算子
                l_orderUnitParams.setOrderCondOperator(null);

                //逆指値基準値タイプ
                l_orderUnitParams.setStopPriceType(null);

                //逆指値基準値
                l_orderUnitParams.setStopOrderPrice(null);

                //（W指値）訂正指値
                l_orderUnitParams.setWLimitPrice(null);

				if (this.estimateDeliveryAmounCalcResult != null)
				{
				    //注文単価
				    //ストップ注文切替中(*1)の場合 かつ 概算代金計算結果がnull以外の場合
				    //概算代金計算結果.get計算単価
				    l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());

				    //概算受渡代金
				    //ストップ注文切替中(*1)の場合 かつ 概算代金計算結果がnull以外の場合
				    //概算代金計算結果.get概算受渡代金
				    l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
				}
				else
				{
                    //以外、市場から確認済みの注文単価
					if (!l_orderUnitParams.getConfirmedOrderPriceIsNull())
				    {
                        l_orderUnitParams.setPrice(l_orderUnitParams.getConfirmedOrderPrice());
					}

                    //以外、市場から確認済みの概算受渡代金
                    if (!l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
                    {
                        l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
                    }
			    }

                //市場から確認済みの注文単価
                if (!l_orderUnitParams.getPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                }

                //市場から確認済みの概算受渡代金
                if (!l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                //リクエストタイプ5：失効
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

                //（W指値）執行条件
                l_orderUnitParams.setWLimitExecCondType(null);
            }

            else
            {
                //以外、市場から確認済みの注文単価
                if (!l_orderUnitParams.getConfirmedOrderPriceIsNull())
                {
                    l_orderUnitParams.setPrice(l_orderUnitParams.getConfirmedOrderPrice());
                }

                //以外、市場から確認済みの概算受渡代金
                if (!l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
                }
            }

            //注文訂正・取消区分
            //ストップ注文切替中(*1)の場合、
            if (l_blnIsStopOrderChanging)
            {
                //D：W指値注文切替失敗
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);
            }
            else
            {
                //以外、8:訂正失敗
                l_orderUnitParams.setModifyCancelType(WEB3IfoOrderModifyCancelTypeDef.CHANGE_FAILED);
            }

            //注文エラー理由コード
            l_orderUnitParams.setErrorReasonCode(this.errorCode);
        }
        else if(OrderStatusEnum.NOT_CANCELLED.equals( l_orderUnitParams.getOrderStatus()))
        {
            log.debug("注文状態が”発注失敗（取消注文）：NOT_CANCELLED”の場合");

            //(*1)先物OPデータアダプタ.getW指値用有効状態区分()＝"リミット注文有効"の
            //場合、リミット注文有効。
            String l_strEnableStatusDiv = null;
            try
            {
                l_strEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
            if (WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strEnableStatusDiv))
            {
                //元発注条件
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());

                //元発注条件演算子
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());

                //元逆指値基準値タイプ
                l_orderUnitParams.setOrgStopPriceType(l_orderUnitParams.getStopPriceType());

                //元逆指値基準値
                if (!l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }

                //元（W指値）訂正指値
                if (!l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }

                //元（W指値）執行条件
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());

                //発注条件
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //発注条件演算子
                l_orderUnitParams.setOrderCondOperator(null);

                //逆指値基準値タイプ
                l_orderUnitParams.setStopPriceType(null);

                //逆指値基準値
                l_orderUnitParams.setStopOrderPrice(null);

                //（W指値）訂正指値
                l_orderUnitParams.setWLimitPrice(null);

				if (this.estimateDeliveryAmounCalcResult != null)
				{
					//注文単価
					//リミット注文有効(*1)の場合、注文単価
					l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());

					//概算受渡代金
					//リミット注文有効(*1)の場合、概算受渡代金
					l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
				}

                //市場から確認済みの注文単価
                if (!l_orderUnitParams.getPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                }

                //市場から確認済みの概算受渡代金
                if (!l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                //リクエストタイプ5：失効
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

                //（W指値）執行条件
                l_orderUnitParams.setWLimitExecCondType(null);
            }


            //注文訂正・取消区分   4:取消失敗
            l_orderUnitParams.setModifyCancelType(
                WEB3IfoOrderModifyCancelTypeDef.CANCEL_FAILED);

            //注文エラー理由コード
            l_orderUnitParams.setErrorReasonCode(this.errorCode);
        }

        log.exiting( STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**                             
     * (更新値設定)<BR>                              
     *（mutateメソッドの実装）<BR>                               
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>                               
     * <BR>                             
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>                              
     * <BR>                             
     * １）　@注文単位オブジェクト取得<BR>
     * 引数の注文単位Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。                               
     * <BR>
     * 
     * ２）　@super.mutate(IfoOrderActionParams)をコールする。
     * 
     * ３）　@xTrade標準項目の更新仕様をカスタマイズする。
     * 　@　@※xTrade標準実装では、 
     * 　@　@※訂正失敗時には訂正取消受付を行う前の注文単位の値が設定されてしまうため。
     *  
     * @@param l_updateType - INSERTまたは、UPDATE。<BR>                          
     * <BR>                         
     * OrderManagerPersistenceTypeにて定数定義。                           
     *                          
     * @@param l_dealing - （OrderManagerPersistenceContextにて定数定義）                            
     * @@param l_orderActionParams - 注文履歴Params<BR>                          
     * 株式注文履歴が保持する項目のパラメータクラス。<BR>                          
     * @@return webbroker3.ifo.data.IfoOrderActionParams                         
     * @@roseuid 40838AEB023E                            
     */                         
    public IfoOrderActionParams mutate(OrderManagerPersistenceType l_updateType,                            
        OrderManagerPersistenceContext l_dealing,                       
      IfoOrderActionParams l_orderActionParams)                             
    {                           
        final String STR_METHOD_NAME =                  
            ".mutate(OrderManagerPersistenceType l_updateType," +               
            " OrderManagerPersistenceContext l_dealing, " +             
            "IfoOrderActionParams l_orderActionParams) ";                          
                            
    log.entering(STR_METHOD_NAME);                          
                            
    if (l_orderActionParams == null)                            
    {                           
        log.debug("Enter the if path that l_orderActionParams is null.");                        
        log.error(STR_METHOD_NAME,new WEB3SystemLayerException(                     
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,                    
            this.getClass().getName()+ STR_METHOD_NAME ));                  
        log.debug("Exit the if path that l_orderActionParams is null.");                     
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+ "." + STR_METHOD_NAME);                       
    }                                   
                            
      long l_orderUnitID;                           
      l_orderUnitID = l_orderActionParams.getOrderUnitId();                         
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
                            
      super.mutate(l_updateType, l_dealing, l_orderActionParams);                           
                      
          l_orderActionParams.setPrice(l_params.getLimitPrice());                        
          l_orderActionParams.setQuantity(l_params.getQuantity());                      
          l_orderActionParams.setConfirmedQuantity(l_params.getConfirmedQuantity());                        
          l_orderActionParams.setConfirmedPrice(l_params.getConfirmedPrice());                      
          if (OrderStatusEnum.MODIFYING.equals(l_ifoOrderUnit.getOrderStatus()) ||
              OrderStatusEnum.CANCELLING.equals(l_ifoOrderUnit.getOrderStatus()))
          {
              l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
          }
        
        log.exiting(STR_METHOD_NAME);                       
        return l_orderActionParams;                     
    }                           

}
@
