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
filename	WEB3IfoCloseNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効通知更新インタセプタ(WEB3IfoCloseNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/7/26 肖志偉 (中訊) 新規作成
                 : 2006/11/30 周捷(中訊) 仕様変更モデルNo.586, ＤＢ更新仕様No.128
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;



/**
 * (先物OP失効通知更新インタセプタ)<BR>
 * 先物OP失効通知更新インタセプタクラス
 * 
 * @@author  肖志偉
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{   
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoChangeConfirmUpdateInterceptor.class);

    /**
     * (失効通知区分)<BR>
     * 失効通知区分
     */
    private String closeNotifyType;

    /**
     * (先物OP失効通知更新インタセプタ)<BR>
     * コンストラクタ
     * @@return webbroker3.ifo.WEB3IfoCloseNotifyUpdateInterceptor
     * @@roseuid 44A8F3F101E2
     */
    public WEB3IfoCloseNotifyUpdateInterceptor() 
    {
     
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １）　@super.mutate(IfoOrderActionParams)をコールする。<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@注文エラー理由コード：<BR>
     * 　@　@パラメータ.注文履歴Params.注文エラー理由コードに”0000:正常”をセットする。 <BR>
     * <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * @@param l_dealing - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_ifoOrderActionParams - (注文履歴Params)<BR>
     * 注文履歴が保持する項目のパラメータクラス。<BR>
     * @@return IfoOrderActionParams
     * @@roseuid 44A8F3F101D2
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_dealing, 
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
                    this.getClass().getName() + "." + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }          
        
        //１）　@super.mutate(IfoOrderActionParams)をコールする。
        super.mutate(l_updateType, l_dealing, l_ifoOrderActionParams);
        
        //２）　@拡張項目セット
        //注文エラー理由コード：パラメータ.注文履歴Params.注文エラー理由コードに”0000:正常”をセットする。
        l_ifoOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderActionParams;
    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * 更新内容は、 <BR>
     * DB更新仕様 <BR>
     * 「先OP失効通知_注文単位テーブル仕様.xls」参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。 <BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。
     * @@param l_dealing - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_ifoOrderUnitParams - (注文単位Params)<BR>
     * 注文単位行オブジェクト
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_dealing,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, "
                + "OrderManagerPersistenceContext, "
                + "IfoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //○　@(*1)先物OPデータアダプタ.getＷ指値用有効状態区分()＝
            //"リミット注文有効"の場合、リミット注文有効。
            //引数に設定する注文単位には、更新前の注文単位を指定する
            //（IfoOrderUnitParamsをOP注文マネージャ.toOrderUnit()にて注文単位型にする）
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_opOrderMgr = (WEB3OptionOrderManagerImpl)
                l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit =
                (IfoOrderUnit)l_opOrderMgr.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
            IfoOrderUnitRow l_orderUnitRow = 
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            IfoOrderUnitParams l_beforeOrderUnitParams = new IfoOrderUnitParams(l_orderUnitRow);

            String l_strWLimitEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

            //失効通知区分=="失効取消"かつリミット注文有効(*1)の場合
            if (WEB3CloseNotifyTypeDef.CLOSE_CANCEL.equals(this.getCloseNotifyType())
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
                //発注条件
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、0：DEFAULT（条件指定なし）
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //発注条件演算子
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrderCondOperator(null);

                //逆指値基準値タイプ
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setStopPriceType(null);

                //逆指値基準値
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setStopOrderPrice(null);

                //（W指値）訂正指値
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setWLimitPrice(null);

                //元発注条件
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、更新前の発注条件
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrgOrderConditionType(
                    l_beforeOrderUnitParams.getOrderConditionType());

                //元発注条件演算子
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、更新前の発注条件演算子
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrgOrderCondOperator(
                    l_beforeOrderUnitParams.getOrderCondOperator());

                //元逆指値基準値タイプ
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、更新前の逆指値基準値タイプ
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrgStopPriceType(
                    l_beforeOrderUnitParams.getStopPriceType());

                //元逆指値基準値
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、更新前の逆指値基準値
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
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、更新前の（W指値）訂正指値
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
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、更新前の（W指値）執行条件
                //以外、（既存値）
                l_ifoOrderUnitParams.setOrgWLimitExecCondType(
                    l_beforeOrderUnitParams.getWLimitExecCondType());

                //（W指値）執行条件
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合、null
                //以外、（既存値）
                l_ifoOrderUnitParams.setWLimitExecCondType(null);

                //リクエストタイプ
                //失効通知区分=="失効取消"かつリミット注文有効(*1）の場合　@5:失効
                l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error("注文単位が取得できませんでした。" + "注文単位ID="
                + l_ifoOrderUnitParams.getOrderUnitId(), l_nfe);
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

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }

    /**
     * (set失効通知区分)<BR>
     * 引数の失効通知区分をthis.失効通知区分にセットする。<BR>
     * @@param l_strCloseNotifyType - (失効通知区分)<BR>
     * 失効通知区分
     */
    public void setCloseNotifyType(String l_strCloseNotifyType)
    {
        this.closeNotifyType = l_strCloseNotifyType;
    }

    /**
     * (get失効通知区分)<BR>
     * this.失効通知区分を返却する。<BR>
     * @@return String
     */
    public String getCloseNotifyType()
    {
        return this.closeNotifyType;
    }
}
@
