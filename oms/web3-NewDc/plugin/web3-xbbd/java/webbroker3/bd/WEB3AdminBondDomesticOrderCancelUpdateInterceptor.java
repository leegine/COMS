head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticOrderCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券管理者注文取消更新インタセプタ(WEB3AdminBondDomesticOrderCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.239
*/
package webbroker3.bd;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (国内債券管理者注文取消更新インタセプタ)<BR>
 * 国内債券管理者注文取消更新インタセプタ<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondDomesticOrderCancelUpdateInterceptor extends WEB3AdminBondDefaultInterceptor
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticOrderCancelUpdateInterceptor.class);

    /**
     * (国内債券管理者注文取消更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D84FAD015E
     */
    public WEB3AdminBondDomesticOrderCancelUpdateInterceptor()
    {

    }

    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * 債券注文単位テーブル更新。<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * 項目設定内容は、DB更新仕様<BR>
     * 「債券注文取消（未約定_国内債券）_債券注文単位テーブルDB更新仕様.xls」<BR>
     * 「債券注文取消（約定済_国内債券）_債券注文単位テーブルDB更新仕様.xls」<BR>
     * <BR>
     * 参照。<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME =
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        //１）拡張項目セット
        //項目設定内容は 「債券注文取消（未約定_国内債券）_債券注文単位テーブルDB更新仕様.xls」参照。
        //項目設定内容は 「債券注文取消（約定済_国内債券）_債券注文単位テーブルDB更新仕様.xls」参照。
        //2：取消済
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.CANCELED);

        //  債券注文単位.発注日＜取引時間管理.get発注日の場合、
        //　@  0：未送信
        //  それ以外の場合、
        //　@　@2:送信不要
        try
        {
            if (WEB3DateUtility.compareToDay(
                WEB3DateUtility.getDate(l_params.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                WEB3GentradeTradingTimeManagement.getOrderBizDate()) < 0)
            {
                l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
            }
            else
            {
                l_params.setHostSendDiv(WEB3HostSendDivDef.NOT_SEND);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //管理者.get管理者コード()
        l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
