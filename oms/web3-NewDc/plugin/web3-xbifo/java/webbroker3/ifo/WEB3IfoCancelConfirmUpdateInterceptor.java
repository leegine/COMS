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
filename	WEB3IfoCancelConfirmUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP取消確定更新インタセプタクラス(WEB3IfoCancelConfirmUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/16 艾興 (中訊) 新規作成
*/

package webbroker3.ifo;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

/**
 * (先物OP取消確定更新インタセプタ)<BR>
 * 先物OP取消確定更新インタセプタクラス<BR>
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoCancelConfirmUpdateInterceptor
    extends WEB3IfoUpdateInterceptor
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoCancelConfirmUpdateInterceptor.class);

    /**
     * (先物OP取消確定更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.ifo.WEB3IfoCancelConfirmUpdateInterceptor
     * @@roseuid 40A81EF1018B
     */
    public WEB3IfoCancelConfirmUpdateInterceptor()
    {

    }

    /**
     * (更新値設定)<BR>
     *（mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>・
     * 却する。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 注文単位.先物／オプション区分 == "オプション"の場合 <BR>
     * 「OP取消確定_注文単位テーブル仕様.xls」参照。<BR> 
     * 注文単位.先物／オプション区分 == "先物"の場合 <BR>
     * 「先物取消確定_注文単位テーブル仕様.xls」参照。<BR> 
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_ifoOrderUnitParams - (注文単位Params)<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40A81EF1017C
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate()";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        try
        {
            //注文エラー理由コード = 0000：正常                                                                      
            l_ifoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

            long l_lngOrderId = l_ifoOrderUnitParams.getOrderId();
            IfoOrderImpl l_order = new IfoOrderImpl(l_lngOrderId);
            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];
            
            //注文訂正・取消区分をセット
            if (l_orderUnit.isUnexecuted())
            {
                l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);
            }
            else
            {
                l_ifoOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
            }
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }
}
@
