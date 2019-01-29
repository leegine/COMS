head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInformAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 各種連絡受付UnitServiceImpl(WEB3AccOpenInformAcceptUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/28 柴双紅 (中訊) 新規作成 モデル No.123,No.128、ＤＢ更新仕様 No.028, No.029
Revision History : 2007/06/05 柴双紅 (中訊) モデル 132、モデル 133
Revision History : 2007/06/12 柴双紅 (中訊) モデル No.140、モデル 142
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInformAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostOrderAcceptStatusDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (各種連絡受付UnitServiceImpl)<BR>
 * 各種連絡受付１件サービス実装クラス<BR>
 * （トランザクション属性=TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AccOpenInformAcceptUnitServiceImpl implements WEB3AccOpenInformAcceptUnitService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccOpenInformAcceptUnitServiceImpl.class);

    /**
     * (update各種連絡テーブル)<BR>
     * 各種連絡受付1件処理を実施する。<BR>
     * <BR>
     * １）各種連絡テーブルをPK更新する。<BR>
     * <BR>
     * ※更新内容は、DB更新仕様<BR>
     * 「利金・分配金伝票受付_各種連絡テーブルDB更新仕様.xls」 を参照<BR>
     * <BR>
     * @@param l_hostAccOpenAcceptParams - (受付キューParams)<BR>
     * 口座開設伝票登録受付キューParams<BR>
     * @@param l_variousInformParams - (各種連絡Params)<BR>
     * 各種連絡Params<BR>
     * @@throws WEB3BaseException
     */
    private void updateVariousInform(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
        VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateVariousInform(HostAccOpenAcceptParams, "
            + "VariousInformParams)";
        log.entering(STR_METHOD_NAME);

        if (l_hostAccOpenAcceptParams == null || l_variousInformParams == null)
        {
            log.debug("パラメータ値不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //各種連絡テーブルをPK更新する。
        //※更新内容は、DB更新仕様「利金・分配金伝票受付_各種連絡テーブルDB更新仕様.xls」 を参照
        //伝票作成状況
        //口座開設伝票登録受付キュー.受付結果が 1：受付完了の場合 3：受付完了
        //口座開設伝票登録受付キュー.受付結果が 2：エラーの場合  4：受付エラー
        if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_COMPLETE.equals(
            l_hostAccOpenAcceptParams.getAcceptStatus()))
        {
            l_variousInformParams.setStatus(WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);
        }
        else if (WEB3HostOrderAcceptStatusDef.ORDER_ACCEPT_ERROR.equals(
            l_hostAccOpenAcceptParams.getAcceptStatus()))
        {
            l_variousInformParams.setStatus(WEB3VoucherCreateStatusDef.ACCEPT_ERROR);
        }

        //エラー理由コード    口座開設伝票登録受付キュー.エラーコード
        l_variousInformParams.setErrorReasonCode(l_hostAccOpenAcceptParams.getErrorCode());

        //伝票受信日時   現在時刻（システムタイムスタンプ）
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_variousInformParams.setReceiptTimestamp(l_tsSystemTime);

        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doUpdateQuery(l_variousInformParams);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(), l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(), l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(), l_dqe);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify各種連絡受付)<BR>
     * 各種連絡受付を通知する<BR>
     * <BR>
     * １）this.update各種連絡テーブル()をコールし、各種連絡受付1件処理を実施する。<BR>
     * <BR>
     * [引数]<BR>
     * 受付キューParams： 引数.受付キューParams<BR>
     * 各種連絡Params： 引数.各種連絡Params<BR>
     * <BR>
     * ２）this.update受付キュー()をコールし、口座開設伝票登録受付キューを更新する。<BR>
     * <BR>
     * [引数]<BR>
     * 受付キューParams： 引数.受付キューParams<BR>
     * 処理区分： 引数.処理区分<BR>
     * <BR>
     * @@param l_hostAccOpenAcceptParams - (受付キューParams)<BR>
     * 口座開設伝票登録受付キューParams<BR>
     * @@param l_variousInformParams - (各種連絡Params)<BR>
     * 各種連絡Params<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 1： 処理済<BR>
     * 9： エラー<BR>
     * @@throws WEB3BaseException
     */
    public void notifyInformAccept(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
        VariousInformParams l_variousInformParams,
        String l_strProcessDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyContactAccept(HostAccOpenAcceptParams, "
            + "VariousInformParams, String)";
        log.entering(STR_METHOD_NAME);

        //this.update各種連絡テーブル()をコールし、各種連絡受付1件処理を実施する。
        //[引数]
        //受付キューParams： 引数.受付キューParams
        //各種連絡Params： 引数.各種連絡Params
        this.updateVariousInform(l_hostAccOpenAcceptParams, l_variousInformParams);

        //this.update受付キュー()をコールし、口座開設伝票登録受付キューを更新する。
        //[引数]
        //受付キューParams： 引数.受付キューParams
        //処理区分： 引数.処理区分
        this.updateAcceptQueue(l_hostAccOpenAcceptParams, l_strProcessDiv);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update受付キュー)<BR>
     * 口座開設伝票登録受付キューを更新する<BR>
     * <BR>
     * １）口座開設伝票登録受付キューをPK更新する。<BR>
     * <BR>
     * ※更新内容は、DB更新仕様<BR>
     * 「受付_口座開設伝票登録受付キューテーブル.xls」 を参照<BR>
     * <BR>
     * @@param l_hostAccOpenAcceptParams - (口座開設伝票登録受付キュー)<BR>
     * 口座開設伝票登録受付キュー<BR>
     * <BR>
     * ※　@口座開設伝票登録受付キューParamsクラスは、DDLより自動生成する。<BR>
     * <BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 1： 処理済<BR>
     * 9： エラー<BR>
     * @@throws WEB3BaseException
     */
    private void updateAcceptQueue(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
        String l_strProcessDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateHostAccept(HostAccOpenAcceptParams, String)";
        log.entering(STR_METHOD_NAME);

        if (l_hostAccOpenAcceptParams == null)
        {
            log.debug("パラメータ値不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //口座開設伝票登録受付キュー.処理区分 = 引数.処理区分
        l_hostAccOpenAcceptParams.setStatus(l_strProcessDiv);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doUpdateQuery(l_hostAccOpenAcceptParams);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(), l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(), l_dqe);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
