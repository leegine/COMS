head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨MMF受付正常処理一件TransactionCallback(WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 柴双紅 (中訊) 新規作成 (モデル534)
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptParams;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptRow;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外貨MMF受付正常処理一件TransactionCallback)<BR>
 * 
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback implements TransactionCallback
{

    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback.class);

    /**
     * (投信注文単位)<BR>
     * 投信注文単位<BR>
     */
    public MutualFundOrderUnit mutualFundOrderUnit;

    /**
     * (投信受付確定インタセプタ)<BR>
     * 投信受付確定インタセプタ<BR>
     */
    public WEB3MutualFundAcceptConfirmInterceptor mutualFundAcceptConfirmInterceptor;

    /**
     * (外貨MMF注文受付キューParams)<BR>
     * 外貨MMF注文受付キューParams<BR>
     */
    public HostFrgnMmfOrderAcceptParams hostMutualFrgnMmfOrderAcceptParams;

    /**
     * @@roseuid 45C440D7011F
     */
    public WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback() 
    {
     
    }

    /**
     * (外貨MMF受付正常処理一件TransactionCallback)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 1) this.投信注文単位 = 引数.投信注文単位<BR>
     * <BR>
     * 2) this.投信受付確定インタセプタ = 引数.投信受付確定インタセプタ<BR>
     * <BR>
     * 3) this.外貨MMF注文受付キューParams = 引数.外貨MMF注文受付キューParams<BR>
     * @@param mutualFundOrderUnit - (投信注文単位)<BR>
     * 投信注文単位<BR>
     * @@param mutualFundAcceptConfirmInterceptor - (投信受付確定インタセプタ)<BR>
     * 投信受付確定インタセプタ<BR>
     * @@param hostMutualFrgnMmfOrderAcceptParams - (外貨MMF注文受付キューParams)<BR>
     * 外貨MMF注文受付キューParams<BR>
     * @@roseuid 45BEFEDF0118
     */
    public WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback(
        MutualFundOrderUnit mutualFundOrderUnit,
        WEB3MutualFundAcceptConfirmInterceptor mutualFundAcceptConfirmInterceptor,
        HostFrgnMmfOrderAcceptParams hostMutualFrgnMmfOrderAcceptParams)
    {
        this.mutualFundOrderUnit = mutualFundOrderUnit;
        this.mutualFundAcceptConfirmInterceptor = mutualFundAcceptConfirmInterceptor;
        this.hostMutualFrgnMmfOrderAcceptParams = hostMutualFrgnMmfOrderAcceptParams;
    }

    /**
     * シーケンス図 「（外貨MMF注文受付正常処理一件）process」参照。
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     * @@roseuid 45BEFF8101D0
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = " Object process()";
        log.entering(STR_METHOD_NAME);

        //投信注文受付UnitServiceを取得する
        WEB3MutualOrderAcceptUnitService l_OrderAcceptUnitService =
            (WEB3MutualOrderAcceptUnitService) Services.getService(
                WEB3MutualOrderAcceptUnitService.class);

        MutualFundOrderUnit l_mfOrderUnit = this.mutualFundOrderUnit;
        WEB3MutualFundAcceptConfirmInterceptor l_confirmInterceptor =
            this.mutualFundAcceptConfirmInterceptor;
        HostFrgnMmfOrderAcceptParams l_orderAcceptParams =
            this.hostMutualFrgnMmfOrderAcceptParams;
        String l_strAcceptStatus =
            l_orderAcceptParams.getAcceptStatus();

        try
        {
            //＜分岐処理＞注文受付処理
            if (WEB3AcceptStatusDef.OVER.equals(l_strAcceptStatus))
            {
                //notify注文受付完了(MutualFundOrderUnit, 投信受付確定インタセプタ)
                l_OrderAcceptUnitService.notifyOrderAcceptComplete(
                    l_mfOrderUnit,
                    l_confirmInterceptor);
            }
            else if (WEB3AcceptStatusDef.ERROR.equals(l_strAcceptStatus))
            {
                //notify注文受付失敗(MutualFundOrderUnit, 投信受付確定インタセプタ)
                l_OrderAcceptUnitService.notifyOrderAcceptFail(
                    l_mfOrderUnit,
                    l_confirmInterceptor);
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                log.error("予期しないシステムエラーが発生しました。");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch(WEB3BaseException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("予期しないシステムエラーが発生しました。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //処理区分変更
        HashMap l_map = new HashMap();
        String l_strUpdateWhere =
            " institution_code = ? " +
            " and branch_code = ? " +
            " and order_request_number = ? ";
        String[] l_strUpdateParams =
            {l_orderAcceptParams.getInstitutionCode(),
             l_orderAcceptParams.getBranchCode(),
             l_orderAcceptParams.getOrderRequestNumber()};
        l_map.put("status", WEB3StatusDef.DEALT);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateAllQuery(
            HostFrgnMmfOrderAcceptRow.TYPE,
            l_strUpdateWhere,
            l_strUpdateParams,
            l_map);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
