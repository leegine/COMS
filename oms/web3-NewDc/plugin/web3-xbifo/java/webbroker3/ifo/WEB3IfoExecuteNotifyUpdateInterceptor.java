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
filename	WEB3IfoExecuteNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来通知更新インタセプタ(WEB3IfoExecuteNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 盧法@旭 (中訊) 新規作成
				 : 2006/7/19 李　@俊 (中訊) 仕様変更　@モデル525
                 : 2006/8/10 肖志偉 (中訊) 仕様変更  モデル543
                 : 2006/11/29 周捷 (中訊) DB更新仕様No.129、130
Revesion History : 2007/07/02 孟亜南 (中訊) DB更新仕様No.193
*/

package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (先物OP出来通知更新インタセプタ)<BR>
 * 先物OP出来通知更新インタセプタクラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3IfoExecuteNotifyUpdateInterceptor extends WEB3IfoUpdateInterceptor
{
    /**
      * ログ出力ユーティリティ。
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoExecuteNotifyUpdateInterceptor.class);

    /**
     * 出来通知区分<BR>
     * <BR>
     * DBレイアウト<BR>
     * 「先物OP出来通知キューテーブル」参照。<BR>
     */
    private String dealedType;

    /**
     * (set出来通知区分)<BR>
     * 出来通知区分をセットする。<BR>
     * @@param l_strDealedType - 出来通知区分
     */
    public void setDealedType(String l_strDealedType)
    {
        this.dealedType = l_strDealedType;
    }

    /**
     * (get出来通知区分)<BR>
     * 出来通知区分を取得する。<BR>
     * @@return String
     * @@roseuid 4084E05500B8
     */
    public String getDealedType()
    {
        return this.dealedType;
    }

	/**
	 * 約定日時<BR>
	 * <BR>
	 * DBレイアウト<BR>
	 * 「先物OP出来通知キューテーブル」参照。<BR>
	 */
	private Date execTimestamp;

	/**
	 * (set約定日時)<BR>
	 * 約定日時をセットする。<BR>
	 * @@param l_datExecTimestamp - 約定日時
	 */
	public void setExecTimestamp(Date l_datExecTimestamp)
	{
		this.execTimestamp = l_datExecTimestamp;
	}

	/**
	 * (get約定日時)<BR>
	 * 約定日時を取得する。<BR>
	 * @@return String
	 * @@roseuid 4084E05500B8
	 */
	public Date getExecTimestamp()
	{
		return this.execTimestamp;
	}

     /**
     * (更新値設定)<BR>
     *（mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     *  (OrderManagerPersistenceTypeにて定数定義) <BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_ifoOrderUnitParams - (注文単位Params)<BR>
     * 　@注文単位オブジェクト<BR>
     * @@return IfoOrderUnitParams
     * @@roseuid 40875C9F0270
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        String STR_METHOD_NAME = "mutate()";
        log.entering(STR_METHOD_NAME);

        if((WEB3DealedTypeDef.FULLY_EXECUTED.equals(this.dealedType))||
          (WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(this.dealedType)))
        {
	        l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
	        l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        }
            
        //ThreadLocalに設定されているシステム処理時間を取得する			
        Timestamp l_realTimestamp = 
                 (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
                 
        //更新日付を設定する
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(l_realTimestamp);
		
        //注文単位にひもづく注文を取得する
        long l_orderID = l_ifoOrderUnitParams.getOrderId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Order l_ifoOrder = null;
        IfoOrderParams l_ifoOrderParams = null;

        try
        {
            l_ifoOrder = l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager().getOrder(l_orderID);
            l_ifoOrderParams = (IfoOrderParams)l_ifoOrder.getDataSourceObject();

            WEB3OptionOrderManagerImpl l_opOrderMgr = (WEB3OptionOrderManagerImpl)
                l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit =
                (IfoOrderUnit)l_opOrderMgr.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());

            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            IfoOrderUnitParams l_beforeOrderUnitParams = new IfoOrderUnitParams(l_row);

            String l_strWLimitEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

            //○　@(*1)
            //  ・引数の処理 == "UNDO_EXECUTION"の場合、約定取消と判定する。
            //  ・xTrade更新前の注文単位（IfoOrderUnitParams.注文単位IDに該当する注文単位をDBから
            //  再取得したもの）の注文有効状態 == "クローズ"であれば約定取消後再オープンと判定する。
            //○　@(*2)先物OPデータアダプタ.getＷ指値用有効状態区分()＝"リミット注文有効"の場合、リミット注文有効。
            //  引数に設定する注文単位には、更新前の注文単位を指定する
            //  （IfoOrderUnitParamsをOP注文マネージャ.toOrderUnit()にて注文単位型にする）
            if (OrderManagerPersistenceContext.UNDO_EXECUTION.equals(l_orderManagerPersistenceContext)
                && OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
                //発注条件
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、0：DEFAULT（条件指定なし）
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //発注条件演算子
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrderCondOperator(null);

                //逆指値基準値タイプ
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setStopPriceType(null);

                //逆指値基準値
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setStopOrderPrice(null);

                //（W指値）訂正指値
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setWLimitPrice(null);

                //元発注条件
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、更新前の発注条件
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrgOrderConditionType(
                    l_beforeOrderUnitParams.getOrderConditionType());

                //元発注条件演算子
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、更新前の発注条件演算子
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrgOrderCondOperator(
                    l_beforeOrderUnitParams.getOrderCondOperator());

                //元逆指値基準値タイプ
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、更新前の逆指値基準値タイプ
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrgStopPriceType(
                    l_beforeOrderUnitParams.getStopPriceType());

                //元逆指値基準値
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、更新前の逆指値基準値
                //以外、（既存値）
                if (l_beforeOrderUnitParams.getStopOrderPriceIsNull())
                {
                    l_ifoOrderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_ifoOrderUnitParams.setOrgStopOrderPrice(
                        l_beforeOrderUnitParams.getStopOrderPrice());
                }

                //元（W指値）訂正指値
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、更新前の（W指値）訂正指値
                //以外、（既存値）
                if (l_beforeOrderUnitParams.getWLimitPriceIsNull())
                {
                    l_ifoOrderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_ifoOrderUnitParams.setOrgWLimitPrice(
                        l_beforeOrderUnitParams.getWLimitPrice());
                }

                //元（W指値）執行条件
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、更新前の（W指値）執行条件
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrgWLimitExecCondType(
                    l_beforeOrderUnitParams.getWLimitExecCondType());

                //（W指値）執行条件
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setWLimitExecCondType(null);

                //リクエストタイプ
                //約定取消後再オープン(*1) かつ リミット注文有効(*2)の場合、5：失効
                l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }

            //注文.更新日付を設定する
            l_ifoOrderParams.setLastUpdatedTimestamp(l_realTimestamp);
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_queryProcessor.doUpdateQuery(l_ifoOrderParams);
		}
		catch (DataException l_dqe)
		{
			log.error("DBへのアクセスに失敗しました。", l_dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("注文が取得できませんでした。" + "注文ID=" + l_orderID, l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }
    
	/**
	 * （mutateメソッドの実装）<BR>
	 * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
	 * <BR>
	 * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
	 * <BR>
	 * １）　@super.mutate(IfoOrderActionParams)をコールする。<BR>
	 * <BR>
	 * ２）　@実時間用タイムスタンプから実時間を取得する <BR>
	 * 　@－ThreadLocalSystemAttributesRegistry.getAttribute( )<BR> 
	 *   設定キー： 設定キー定数定義インタフェイス.REAL_TIMESTAMP <BR>
	 * <BR>
     * ３）　@作成日付・更新日付の設定<BR> 
     * 　@パラメータ.注文履歴Params.作成日付＝２）で取得した現在時刻<BR> 
     * 　@パラメータ.注文履歴Params.更新日付＝２）で取得した現在時刻<BR> 
	 * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
	 * <BR>
	 * OrderManagerPersistenceTypeにて定数定義。<BR>
     * @@param l_context - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
	 * @@param l_ifoOrderActionParams - (注文履歴Params)<BR>
     * 株式注文履歴が保持する項目のパラメータクラス。
	 * @@return webbroker3.ifo.data.IfoOrderActionParams
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

        //スーパークラスの処理をコールする
        super.mutate(l_updateType, l_context, l_ifoOrderActionParams);
        
        //ThreadLocalに設定されているシステム処理時間を取得する			
        Timestamp l_realTimestamp = 
				 (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
                     
		//作成日時、更新日時を設定する
		l_ifoOrderActionParams.setCreatedTimestamp(l_realTimestamp);
		l_ifoOrderActionParams.setLastUpdatedTimestamp(l_realTimestamp);
        
		log.exiting(STR_METHOD_NAME);
		return l_ifoOrderActionParams;
	}

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 約定Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １）　@注文単位オブジェクト取得<BR>
     * <BR>
     * 引数の注文単位Params.注文ID、<BR>
     * 注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@パラメータ.約定Paramsの識別コードに、注文単位オブジェクトの同名項目から<BR>
     * 値をセットし、返却する。<BR>
     * 　@パラメータ.約定Paramsの約定日時に、プロパティの同名項目から<BR>
     * 値をセットし、返却する。<BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     *
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_ifoOrderExecutionParams - (約定Params)<BR>
     * 約定行オブジェクト<BR>
     * @@return webbroker3.ifo.data.IfoOrderExecutionParams
     * @@roseuid 40875C9F0270
     */
    public IfoOrderExecutionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderExecutionParams l_ifoOrderExecutionParams)
    {
        final String STR_METHOD_NAME = ".mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderExecutionParams == null)
        {
            log.error(STR_METHOD_NAME, new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //取得注文単位オブジェクト
            long l_lngOrderUnitId = l_ifoOrderExecutionParams.getOrderUnitId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_lngOrderUnitId);

            // get orderUnitRow object
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //受渡日
            l_ifoOrderExecutionParams.setDeliveryDate(l_ifoOrderUnitRow.getDeliveryDate());

			if((WEB3DealedTypeDef.FULLY_EXECUTED.equals(this.dealedType))||
			  (WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(this.dealedType)))
			{
            //識別コードを設定する
            l_ifoOrderExecutionParams.setOrderRequestNumber(l_ifoOrderUnitRow.getOrderRequestNumber());
			}

			//約定日時を設定する
			l_ifoOrderExecutionParams.setExecTimestamp(this.execTimestamp);

            //ThreadLocalに設定されているシステム処理時間を取得する			
			Timestamp l_realTimestamp = 
                     (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
                     
            //更新日付を設定する
			l_ifoOrderExecutionParams.setLastUpdatedTimestamp(l_realTimestamp);
			
			if(l_orderManagerPersistenceType.equals(OrderManagerPersistenceType.INSERT))
			{
	            //作成日付を設定する
				l_ifoOrderExecutionParams.setCreatedTimestamp(l_realTimestamp);
			}
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DBへのアクセスに失敗しました。", l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderExecutionParams;
    }

    /**
     * (先物OP出来通知更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.ifo.WEB3IfoExecuteNotifyUpdateInterceptor
     * @@roseuid 40875F10035B
     */
    public WEB3IfoExecuteNotifyUpdateInterceptor()
    {

    }
}
@
