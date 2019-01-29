head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashOutOrderTriggerIssueUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金注文トリガー発行UnitServiceImpl(WEB3AioCashOutOrderTriggerIssueUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/09 何文敏 (中訊) 新規作成 仕様変更モデルNo.720
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.service.delegate.WEB3AioCashOutOrderTriggerIssueUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金注文トリガー発行UnitServiceImpl)<BR>
 * 出金トリガー発行UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * <BR>
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AioCashOutOrderTriggerIssueUnitServiceImpl implements WEB3AioCashOutOrderTriggerIssueUnitService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashOutOrderTriggerIssueUnitServiceImpl.class);

    /**
     * 出金注文のトリガー発行処理を行う。<BR>
     * <BR>
     * １）　@入出金・入出庫リクエスト送信サービスオブジェクトを生成<BR>
     * <BR>
     * ２）　@入出金・入出庫リクエスト送信サービスオブジェクト . トリガ発行()でメッセージ送信<BR>
     * [指定する引数]<BR>
     * ・証券会社コード：引数:証券会社コード<BR>
     * ・データコード："GI801T"<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@throws WEB3BaseException
     */
    public void execute(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        //入出金・入出庫リクエスト送信サービス
        WEB3AioMarketRequestSenderServiceImpl l_marketRequestSenderServiceImpl =
            (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();

        String l_strRequestCode = WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER + "T";
        // 入出金・入出庫リクエスト送信サービスオブジェクト . トリガ発行()でメッセージ送信
        l_marketRequestSenderServiceImpl.submitTrigger(l_strInstitutionCode, l_strRequestCode);

        log.exiting(STR_METHOD_NAME);
    }
}
@
