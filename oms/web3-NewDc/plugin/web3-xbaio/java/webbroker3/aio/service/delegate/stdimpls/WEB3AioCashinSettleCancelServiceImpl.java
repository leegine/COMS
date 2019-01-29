head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文要求中止サービス実装クラス(WEB3AioCashinSettleCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 屈陽 (中訊) 新規作成
                   2004/10/25 黄建 (中訊) レビュー 
                   2005/1/11 周勇 (中訊) 残対応
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.WEB3AioFinInstitutionCashTransStatus;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.message.WEB3AioCashinSettleCancelRequest;
import webbroker3.aio.message.WEB3AioCashinSettleCancelResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.util.WEB3LogUtility;

/**
 * (注文要求中止サービスImpl)<BR>
 * 注文要求中止サービス実装クラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCancelServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinSettleCancelService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCancelServiceImpl.class);         
    
    /**
     * 注文要求中止処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文要求中止）execute」 参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / （オンライン入金）注文要求中止 ( )」) <BR>  
     * 　@　@　@　@: （注文要求中止）execute<BR>   
     *     インスタンスの生成に失敗（レコードなし） or<BR>
     * （処理FLAG（注文） != '2'（応答送信） or<BR>
     *   処理FLAG（決済開始） != '0'（未処理） or<BR>
     *   処理区分 != '0'（未処理））<BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00764<BR>
     * <BR>
     * ==========================================================<BR>
     *<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4119A78202B1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }        
        WEB3AioCashinSettleCancelRequest l_aioCashinSettleCancelRequest = 
            (WEB3AioCashinSettleCancelRequest)l_request;
            
        //1.1 リクエストデータの整合性をチェックする
        l_aioCashinSettleCancelRequest.validate();
        
        //1.2 金融機@関連携入出金状況インスタンスを生成する
        //［引数］ 
        //証券会社コード： リクエストデータ.証券会社コード
        String l_strInstitutionCode = l_aioCashinSettleCancelRequest.institutionCode;
        //部店コード： リクエストデータ.部店コード 
        String l_strBrachCode = l_aioCashinSettleCancelRequest.branchCode;
        //識別コード： リクエストデータ.識別コード 
        String l_strOrderRequestNumber = l_aioCashinSettleCancelRequest.orderRequestNumber;                
        //インスタンスの生成に失敗（レコードなし） or
        //（処理FLAG（注文） != '2'（応答送信） or
        //処理FLAG（決済開始） != '0'（未処理） or
        //処理区分 != '0'（未処理））
        //の場合、例外をスローする        
        WEB3AioFinInstitutionCashTransStatus l_aioFinInstitutionCashTransStatus = 
            new WEB3AioFinInstitutionCashTransStatus(
                l_strInstitutionCode, l_strBrachCode, l_strOrderRequestNumber); 
            
        BankCashTransferStatusRow l_bankCashTransferStatusRow = 
            (BankCashTransferStatusRow)l_aioFinInstitutionCashTransStatus.getDataSourceObject();   
                         
        //========remain zhou-yong begin =========
        
        if (!WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(l_bankCashTransferStatusRow.getOrderStatusFlag()) ||
            !WEB3StartStatusFlgDef.NOT_DEAL.equals(l_bankCashTransferStatusRow.getStartStatusFlag()) ||
            !WEB3StatusDef.NOT_DEAL.equals(l_bankCashTransferStatusRow.getTransactionStatus()))
        {
            log.debug("金融機@関連携入出金状況インスタンスを生成する");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00764,
                this.getClass().getName() + "." + l_strMethodName,
                "処理FLAG（注文） != '2'（応答送信）but is " + l_bankCashTransferStatusRow.getOrderStatusFlag() 
                + " and 処理FLAG（決済開始） != '0'（未処理）but is " + l_bankCashTransferStatusRow.getStartStatusFlag() 
                + "and 処理区分 != '0'（未処理）but is " + l_bankCashTransferStatusRow.getTransactionStatus());
        }                        
        
        //========remain zhou-yong end =========
        
        //1.3 金融機@関連携入出金状況テーブルを注文要求中止状態に更新する
        //［引数］入出金状況：金融機@関連携入出金状況オブジェクト 
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService = 
            (WEB3AioMultiBankSettleControlService)Services.getService(WEB3AioMultiBankSettleControlService.class);
        // create a new l_aioFinInstitutionCashTransStatus
        l_aioFinInstitutionCashTransStatus.createForUpdateParams();
        // excute update
        l_aioMultiBankSettleControlService.updateOrderRequireDiscontinuation(l_aioFinInstitutionCashTransStatus);
                                            
        //レスポンスデータを生成する
        WEB3AioCashinSettleCancelResponse l_aioCashinSettleCancelResponse = 
            (WEB3AioCashinSettleCancelResponse)l_aioCashinSettleCancelRequest.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_aioCashinSettleCancelResponse;
    }
}
@
