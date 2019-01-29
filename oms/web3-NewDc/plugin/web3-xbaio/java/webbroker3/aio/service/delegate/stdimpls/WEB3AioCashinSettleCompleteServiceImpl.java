head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCompleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済完了サービス実装クラス(WEB3AioCashinSettleCompleteServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 屈陽 (中訊) 新規作成
                   2004/10/22 周勇(中訊) レビュー
                   2005/1/11 周勇 (中訊) 残対応
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioFinInstitutionCashTransStatus;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioSettleInstitution;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.message.WEB3AioCashinSettleCompleteRequest;
import webbroker3.aio.message.WEB3AioCashinSettleCompleteResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleCompleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.util.WEB3LogUtility;

/**
 * (決済完了サービスImpl)<BR>
 * 決済完了サービス実装クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCompleteServiceImpl extends WEB3ClientRequestService implements WEB3AioCashinSettleCompleteService 
{    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCompleteServiceImpl.class);
    
    /**
     * 決済完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（決済完了）execute」 参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / （オンライン入金)決済完了 ( )」) <BR>
     * 　@　@　@　@: （オンライン入金)決済完了 / （決済完了）execute<BR>   
     *     インスタンスの生成に失敗（レコードなし）した場合、例外をスローする。<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / （オンライン入金)決済完了 ( )」) <BR>
     * 　@　@　@　@: （オンライン入金)決済完了 / （決済完了）execute<BR>   
     *  　@(*1)<BR>
     * 処理FLAG（注文） != '2'（応答送信） or<BR>
     * 処理FLAG（決済開始） != '2'（応答送信） or<BR>
     * 処理FLAG（決済結果） != '2'（応答送信） or<BR>
     * 処理区分 != '1'（OK）<BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00764<BR>
     * <BR>
     * ==========================================================<BR>
     *<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 411B07B80264
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
        WEB3AioCashinSettleCompleteRequest l_aioCashinSettleCompleteRequest = (WEB3AioCashinSettleCompleteRequest)l_request;
        
        //1.1 リクエストデータの整合性をチェックする
        l_aioCashinSettleCompleteRequest.validate();
        
        //1.2 金融機@関連携入出金状況インスタンスを生成する
        //［引数］ 
        //証券会社コード： リクエストデータ.証券会社コード
        String l_strInstitutionCode = l_aioCashinSettleCompleteRequest.institutionCode; 
        //部店コード： リクエストデータ.部店コード 
        String l_strBranchCode = l_aioCashinSettleCompleteRequest.branchCode;
        //識別コード： リクエストデータ.識別コード 
        String l_strOrderRequestNumber = l_aioCashinSettleCompleteRequest.orderRequestNumber;
        
        //インスタンスの生成に失敗（レコードなし）した場合、例外をスローする
        WEB3AioFinInstitutionCashTransStatus l_aioFinInstitutionCashTransStatus =       
            new WEB3AioFinInstitutionCashTransStatus(
                l_strInstitutionCode, l_strBranchCode, l_strOrderRequestNumber);                                              
        
        BankCashTransferStatusRow l_bankCashTransferStatusRow =
            (BankCashTransferStatusRow)l_aioFinInstitutionCashTransStatus.getDataSourceObject();
        //1.3 処理区分 = '0'（未処理）の場合
        if (WEB3TransactionStatusDef.NOT_DEAL.equals(l_bankCashTransferStatusRow.getTransactionStatus()))
        {
            //1.3.1 金融機@関連携入出金状況テーブルを決済完了状態（エラー）に更新する
            //［引数］ 
            //入出金状況： 金融機@関連携入出金状況オブジェクト 
            WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService = 
                (WEB3AioMultiBankSettleControlService)Services.getService(
                WEB3AioMultiBankSettleControlService.class);
            l_aioMultiBankSettleControlService.updateSettleComplete(l_aioFinInstitutionCashTransStatus);
        }      
        
        //=======remain zhou-yong NO.1 begin ========
        
        //処理FLAG（注文） != '2'（応答送信） or
        //処理FLAG（決済開始） != '2'（応答送信） or
        //処理FLAG（決済結果） != '2'（応答送信） or
        //処理区分 != '1'（OK）
        //の場合、例外をスローする
        if (!WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
            l_bankCashTransferStatusRow.getOrderStatusFlag()) ||
            !WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
            l_bankCashTransferStatusRow.getStartStatusFlag()) ||
            !WEB3ResultStatusFlagDef.RESPONSE_SEND.equals(
            l_bankCashTransferStatusRow.getResultStatusFlag()) ||
            !WEB3TransactionStatusDef.OK.equals(
            l_bankCashTransferStatusRow.getTransactionStatus()))
        {
            log.debug("__Judge the Flag__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00764,
                this.getClass().getName() + "." + l_strMethodName,
                "処理FLAG（注文） != '2'（応答送信）but is " + l_bankCashTransferStatusRow.getOrderStatusFlag()
                + "処理FLAG（決済開始） != '2'（応答送信）but is " + l_bankCashTransferStatusRow.getStartStatusFlag()
                + "処理FLAG（決済結果） != '2'（応答送信）but is " + l_bankCashTransferStatusRow.getResultStatusFlag()
                + "処理区分 != '1'（OK）but is " + l_bankCashTransferStatusRow.getTransactionStatus());
        }

        //=======remain zhou-yong NO.1 end ========
        
        //1.4 以下の条件で注文単位テーブルから、レコードを取得する。
        //［条件］
        //識別コード = リクエストデータ.識別コード
        //.comデビット決済取引番号 = 金融機@関連携入出金状況..comデビット決済取引番号    
        String l_strCenterPayId = l_bankCashTransferStatusRow.getCenterPayId();      
        AioOrderUnitRow l_aioOrderUnitRow;
        try
        {
            List l_lisAioOrderUnitRows = 
                AioOrderUnitDao.findRowsByOrderRequestNumberComDebitNumber(
            l_strOrderRequestNumber, l_strCenterPayId);
            if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.size() == 0)
            {
                log.debug("注文単位テーブルから、レコードを取得しない !");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName);
            }
            l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(0);
        }            
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }


        //1.5 レスポンスデータを生成する
        WEB3AioCashinSettleCompleteResponse l_aioCashinSettleCompleteResponse = 
            (WEB3AioCashinSettleCompleteResponse)l_aioCashinSettleCompleteRequest.createResponse();
            
        //1.6 以下のとおりに、プロパティをセットする。
        //レスポンス..comデビット決済取引番号 = 金融機@関連携入出金状況..comデビット決済取引番号
        l_aioCashinSettleCompleteResponse.comDebitNumber = l_bankCashTransferStatusRow.getCenterPayId();
        //レスポンス.入金金額 = 金融機@関連携入出金状況.金額
        l_aioCashinSettleCompleteResponse.cashinAmt = String.valueOf(l_bankCashTransferStatusRow.getAmount());
        //レスポンス.振込予定日 = 金融機@関連携入出金状況.振込入金予定日
        l_aioCashinSettleCompleteResponse.transScheduledDate = l_bankCashTransferStatusRow.getComondebiCaptureDate();
        //レスポンス.証券口座振替日 = 金融機@関連携入出金状況.受渡予定日
        l_aioCashinSettleCompleteResponse.accountTransDate = l_bankCashTransferStatusRow.getDeliveryScheduledDate();
        //レスポンス.更新時間 = 注文単位Params.更新日付
        l_aioCashinSettleCompleteResponse.lastUpdatedTimestamp = l_aioOrderUnitRow.getLastUpdatedTimestamp();
        //レスポンス.注文ID = 注文単位Params.注文ID
        l_aioCashinSettleCompleteResponse.orderId = String.valueOf(l_aioOrderUnitRow.getOrderId());
        //レスポンス.決済機@関IＤ = 注文単位Params.決済機@関IＤ
        String l_strPaySchemeId = l_bankCashTransferStatusRow.getPaySchemeId();
        l_aioCashinSettleCompleteResponse.paySchemeId = l_strPaySchemeId;
        //レスポンス.決済機@関名 = 提携決済機@関Params.名称
        WEB3AioSettleInstitution l_web3AioSettleInstitution =
            new WEB3AioSettleInstitution(l_strPaySchemeId);
        l_aioCashinSettleCompleteResponse.paySchemeName = l_web3AioSettleInstitution.getName();       

        log.exiting(l_strMethodName);

        return l_aioCashinSettleCompleteResponse;                                
    }
}
@
