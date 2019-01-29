head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文取消更新インタセプタ(WEB3AdminBondOrderCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
Revesion History : 2007/03/09 齊珂   (中訊) ＤＢ更新仕様 028
Revesion History : 2007/07/25 劉立峰(中訊) 仕様変更モデルNO.240
*/

package webbroker3.bd;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (債券管理者注文取消更新インタセプタ)<BR>
 * 債券管理者注文取消更新インタセプタ<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondOrderCancelUpdateInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondOrderCancelUpdateInterceptor.class);
    
    /**
     * (債券管理者注文取消更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D96B960157
     */
    public WEB3AdminBondOrderCancelUpdateInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * １）拡張項目セット<BR>
     * 　@パラメータ.債券注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * <BR>
     * 　@１－１）注文単位Params.get市場から確認済みの数量IsNull() == true の場合、<BR>
     * 　@　@項目設定内容は 「債券注文取消（未約定_外国債券）_債券注文単位テーブルDB更新仕様.xls」参照。<BR>
     * <BR>
     * 　@１－２）　@上記以外の場合、<BR>
     * 　@　@項目設定内容は 「債券注文取消（約定済_外国債券）_債券注文単位テーブルDB更新仕様.xls」参照。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D96B960167
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //１）拡張項目セット
        if (l_params.getConfirmedQuantityIsNull())
        {
            //１－１）注文単位Params.get市場から確認済みの数量IsNull() == true の場合
            //項目設定内容は 「債券注文取消（未約定_外国債券）_債券注文単位テーブルDB更新仕様.xls」参照。
            //2：取消済
            l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.CANCELED);

            //  債券注文単位.発注日＜取引時間管理.get発注日の場合、
            //　@  0：未送信
            //  それ以外の場合、
            //　@　@2:送信不要
            try
            {
                if (WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate(l_params.getBizDate(), "yyyyMMdd"),
                    WEB3GentradeTradingTimeManagement.getOrderBizDate()) < 0)
                {
                    l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
                }
                else
                {
                    l_params.setHostSendDiv(WEB3HostSendDivDef.NOT_SEND);
                }
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //管理者.get管理者コード()
            l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());
        }
        //１－２）　@上記以外の場合、
        //項目設定内容は 「債券注文取消（約定済_外国債券）_債券注文単位テーブルDB更新仕様.xls」参照。

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
