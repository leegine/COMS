head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleErrorServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済エラーサービス実装クラス(WEB3AioCashinSettleErrorServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 王蘭芬(中訊) 新規作成
                   2004/10/22 周勇(中訊) レビュー
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.aio.WEB3AioFinInstitutionCashTransStatus;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.message.WEB3AioCashinSettleErrorRequest;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleErrorService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.util.WEB3LogUtility;

/**
 * (決済エラーサービスImpl)<BR>
 * 決済エラーサービス実装クラス
 *
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettleErrorServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinSettleErrorService 
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleErrorServiceImpl.class);

    /**
     * @@roseuid 415A48CE00FD
     */
    public WEB3AioCashinSettleErrorServiceImpl() 
    {
     
    }
    
    /**
     * 決済エラー処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（決済エラー）execute」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 411B30440283
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        if (!(l_request instanceof WEB3AioCashinSettleErrorRequest))
        {
            log.debug("Error l_request require type: " + WEB3AioCashinSettleErrorRequest.class.getName()
                + ", But is type:" + l_request.getClass().getName());
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        WEB3AioCashinSettleErrorRequest l_realRequest = (WEB3AioCashinSettleErrorRequest)l_request;
        //1.1 validate( )
        // --- リクエストデータの整合性をチェックする。
        l_realRequest.validate();
        //1.2 金融機@関連携入出金状況(String, String, String)
        // 金融機@関連携入出金状況インスタンスを生成する。 
        // ［引数］ 
        // 証券会社コード： リクエストデータ.証券会社コード 
        // 部店コード： リクエストデータ.部店コード 
        // 識別コード： リクエストデータ.識別コード 
        WEB3AioFinInstitutionCashTransStatus l_finTransStatus = 
            new WEB3AioFinInstitutionCashTransStatus(
                    l_realRequest.institutionCode,
                    l_realRequest.branchCode,
                    l_realRequest.orderRequestNumber);
        //1.3. 処理区分 = ０：未処理 の場合
        BankCashTransferStatusRow l_row = 
            (BankCashTransferStatusRow)l_finTransStatus.getDataSourceObject();
        // test log
        log.debug("BankCashTransferStatusRow l_row = " + l_row);
        
        if (WEB3TransactionStatusDef.NOT_DEAL.equals(l_row.getTransactionStatus()))
        {
            l_finTransStatus.createForUpdateParams();
            //1.3.1 update決済エラー状態(金融機@関連携入出金状況)
            // 金融機@関連携入出金状況テーブルを決済エラー状態に更新する。
            // ［引数］ 
            // 入出金状況： 金融機@関連携入出金状況オブジェクト 
            WEB3AioMultiBankSettleControlService l_service =
                (WEB3AioMultiBankSettleControlService) Services.getService(
                        WEB3AioMultiBankSettleControlService.class);
            l_service.updateSettleError(l_finTransStatus);
        }
        // 1.4.  createResponse( )
        WEB3GenResponse l_response = l_realRequest.createResponse();
        log.exiting(l_strMethodName);
        return l_response;
    }
}
@
