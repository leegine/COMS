head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMultiBankTelegramProcessServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : マルチバンク電文処理サービスImplクラス(WEB3AioMultiBankTelegramProcessServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 王蘭芬(中訊) 新規作成
Revesion History : 2004/10/26 黄建 (中訊) レビュー  
Revesion History : 2004/12/08 周勇 (中訊) 残対応    
Revesion History : 2005/02/17 黄建 (中訊) 残対応 
Revesion History : 2006/04/14 肖志偉(中訊)　@仕様変更・モデル529
Revesion History : 2006/04/26 WeiXin (中訊) 仕様変更・モデル543、545
Revesion History : 2006/05/22 肖志偉(中訊) 仕様変更・モデル587　@
Revesion History : 2007/06/19 柴双紅 (中訊) 仕様変更・モデル　@No.728
Revesion History : 2007/07/12 孟亜南 (中訊)  モデルNo.734
Revesion History : 2007/07/28 孟亜南(中訊) 仕様変更モデル745
Revesion History : 2008/03/12 車進(中訊) 仕様変更モデル831
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioCareerShopId;
import webbroker3.aio.WEB3AioCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioCompanySettleInstitutionConditions;
import webbroker3.aio.WEB3AioFinInstitutionCashTransStatus;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.define.WEB3AioTelegramFormatDef;
import webbroker3.aio.define.WEB3AioTelegramHttpRequestDef;
import webbroker3.aio.define.WEB3AioTelegramReturnCodeDef;
import webbroker3.aio.service.delegate.WEB3AioMultiBankTelegramProcessService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (マルチバンク電文処理サービスImpl)<BR>
 * マルチバンク電文処理サービス実装クラス
 *
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AioMultiBankTelegramProcessServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3AioMultiBankTelegramProcessService 
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMultiBankTelegramProcessServiceImpl.class);

    /**
     * キー - ”受渡日”
     */
    private String DELIVER_DATE = "DeliveryDate";
    
    /**
     * キー - ”振込日””
     */
    private String TRANSFER_DATE = "TransferDate";

    /**
     * @@roseuid 415A48CD0322
     */
    public WEB3AioMultiBankTelegramProcessServiceImpl() 
    {
     
    }
    
    /**
     * マルチバンク電文処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（マルチバンク電文処理）execute」 参照<BR>
     * @@param l_request - リクエストデータ
     * @@param l_response - レスポンスデータ
     * @@roseuid 41255C8703DC
     */
    public void execute(HttpServletRequest l_request, HttpServletResponse l_response)
        throws ServletException, IOException
    {
        final String l_strMethodName = "execute(HttpServletRequest l_request, "
            + "HttpServletResponse l_response)";
        log.entering(l_strMethodName);
        
        //1.1. ReturnCode = "OK"
        String l_strReturnCode = WEB3AioTelegramReturnCodeDef.OK;
        //1.2.createHashMapFrom受信電文(HttpServletRequest)
        WEB3AioMultiBankSettleControlService l_service =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);
        HashMap l_map = null;
        try
        {
            l_map = l_service.createHashMapFromReceiptTelegram(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            // Exception throws
            log.debug("Error IN createHashMapFrom受信電文(HttpServletRequest)");
            l_strReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        if (l_map == null)
        {
            log.debug("Error IN createHashMapFrom受信電文(HttpServletRequest) with return null!");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + l_strMethodName);
        }
        
        //=======remain zhou-yong NO.5 障害票 U00621 begin ============
        
        //set証券会社コード
        //証券会社コードをセットする。
        //[引数]
        //証券会社コード： HashMap.get("cpy")の戻り値
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)  
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        String l_strInstitutionCode = (String)l_map.get(WEB3AioTelegramFormatDef.cpy);
        l_context.setInstitutionCode(l_strInstitutionCode); 
        
        //set部店コード
        //部店コードをセットする。
        //[引数]
        //部店コード： HashMap.get("btn")の戻り値
        String l_strBranchCode = (String)l_map.get(WEB3AioTelegramFormatDef.btn);
        l_context.setBranchCode(l_strBranchCode);

        try
        {
            //-ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセット
            ThreadLocalSystemAttributesRegistry.setAttribute(
                  WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                  l_context);
            
            //setTimestamp( )
            //受付日時、日付ロールをセットする。 
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__an unexpected error__ when setTimestamp()",l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        } 
        
        String l_strAccountCode = (String)l_map.get(WEB3AioTelegramFormatDef.acc);
        long l_accountId = -1;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(
                l_strInstitutionCode);

            long l_lngInstitutionId = l_institution.getInstitutionId();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_lngInstitutionId,
                l_strBranchCode,
                l_strAccountCode
                );
            l_accountId = l_mainAccount.getAccountId();

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        }

        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.setAccountIdCtx(l_accountId);
        }

        String[] l_strTelegramSendDatas = null;
        //1.3 注文要求受付の場合
        String l_strWeb3Request = (String)l_map.get(WEB3AioTelegramFormatDef.web3Request);
        // test log
        if (WEB3AioTelegramHttpRequestDef.ORDERDEMAND.equals(l_strWeb3Request))
        {
            //1.3.1 注文要求受付処理を行う。
            l_strTelegramSendDatas = this.orderRequireAccept(l_strReturnCode, l_map);
        }
        //1.4 決済開始受付の場合
        if (WEB3AioTelegramHttpRequestDef.SETTLE_START.equals(l_strWeb3Request))
        {
            //1.4.1 決済開始受付処理を行う。
            l_strTelegramSendDatas = this.settlementStartAccept(l_strReturnCode, l_map);
        }
        //1.5 決済結果通知の場合
        if (WEB3AioTelegramHttpRequestDef.SETTLE_RESULT.equals(l_strWeb3Request))
        {
            //1.5.1 決済結果通知処理を行う。
            l_strTelegramSendDatas = this.settlementResultNotify(l_strReturnCode, l_map);
        }
        //1.6  create送信電文(HttpServletResponse, String[])
        // ------------- 送信電文を生成する。
        log.debug("l_strTelegramSendDatas = " + l_strTelegramSendDatas);
        l_service.createSendTelegram(l_response, l_strTelegramSendDatas);
        
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }

        log.exiting(l_strMethodName);
    }
    
    /**
     * (注文要求受付)<BR>
     * 注文要求受付処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（マルチバンク電文処理）注文要求受付」 参照<BR>
     * @@param l_strReturnCode - (リターンコード)
     * @@param l_receiptTelegramData - (受信電文データ)
     * @@return String[]
     * @@roseuid 41255E2E0246
     */
    protected String[] orderRequireAccept(String l_strReturnCode, HashMap l_receiptTelegramData) 
    {
        final String l_strMethodName = "orderRequireAccept(String l_strReturnCode, "
            + " HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        
        //1.1.  returnCode = 引数.returnCode
        String l_strTargetReturnCode = l_strReturnCode;
        WEB3AioMultiBankSettleControlService l_service =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);

        WEB3AioFinInstitutionCashTransStatus l_finInstiCashTransStatus = null;
        boolean l_blnCashTransStatus = false;
        BankCashTransferStatusRow l_bankStatusRow = null;
        
        try
        {
            // 1.2 金融機@関連携入出金状況(String, String, String)
            //［引数］ 
            // 証券会社コード： 受信電文データ.get("cpy")の戻り値 
            // 部店コード： 受信電文データ.get("btn")の戻り値 
            // 識別コード： 受信電文データ.get("req")の戻り値
            l_finInstiCashTransStatus = 
                new WEB3AioFinInstitutionCashTransStatus(
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            l_bankStatusRow = 
                (BankCashTransferStatusRow)l_finInstiCashTransStatus.getDataSourceObject();
            // test log

            // [金融機@関連携入出金状況.処理区分 == ０：未処理 AND 処理FLAG（注文）== ０：未処理] 以外の場合
            if (!(WEB3TransactionStatusDef.NOT_DEAL.equals(l_bankStatusRow.getTransactionStatus())
                && WEB3OrderStatusFlagDef.NOT_DEAL.equals(l_bankStatusRow.getOrderStatusFlag())))
            {
                log.debug("Error in [金融機@関連携入出金状況.処理区分 == ０：未処理 AND 処理FLAG（注文）== ０：未処理]以外の場合");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
            l_blnCashTransStatus = true;
        }
        // インスタンスのエラ―の場合
        catch (WEB3BaseException l_ex)
        {
            log.debug("金融機@関連携入出金状況インスタンスのエラ―", l_ex);
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        // 1.3 returnCode = OKの場合
        if(WEB3AioTelegramReturnCodeDef.OK.equals(l_strTargetReturnCode))
        {
            //1.3.1 受信電文の整合性をチェックする。 
            //［引数］ 
            // 受信電文データ： リクエストデータ.受信電文データ 
            // 入出金状況： 金融機@関連携入出金状況オブジェクト 
            l_strTargetReturnCode = l_service.validateReceiptTelegram(l_receiptTelegramData, l_finInstiCashTransStatus);
        }
        // 1.4 insert注文情報要求(HashMap)
        // 注文情報要求テーブルにレコードを追加する。 
        //［引数］ 
        // 受信電文データ： リクエストデータ.受信電文データ 
        try
        {
            l_service.insertOrderInfoRequire(l_receiptTelegramData);
        }
        // エラー：注文情報要求テーブルにレコードを追加する。
        catch (WEB3BaseException l_ex)
        {
            log.debug("エラー：注文情報要求テーブルにレコードを追加する。");
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        
        //金融機@関連携入出金状況インスタンスが取得できている and 処理FLAG(注文)='0'の場合
        if (l_blnCashTransStatus && WEB3TransactionStatusDef.NOT_DEAL.equals(
                l_bankStatusRow.getOrderStatusFlag()))
        {
            // 1.5 createForUpdateParams( )
            l_finInstiCashTransStatus.createForUpdateParams();
            
            // 1.6 update注文要求受付状態(String, String, 金融機@関連携入出金状況)
            // 金融機@関連携入出金状況テーブルを注文要求受付処理中のステータスに更新する。
            // ［引数］ 
            // returnCode： returnCode 
            // .comデビット決済取引番号： 受信電文データ.get("centerPayId")の戻り値 
            // 入出金状況： 金融機@関連携入出金状況オブジェクト 
            try
            {
                l_service.updateOrderRequireAccept(
                        l_strTargetReturnCode, 
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId),
                        l_finInstiCashTransStatus);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("Error in 金融機@関連携入出金状況テーブルを注文要求受付処理中のステータスに更新する。");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        // 1.7 returnCode = "OK"の場合
        if(WEB3AioTelegramReturnCodeDef.OK.equals(l_strTargetReturnCode))
        {
            try
            {
                //1.7.1 金融機@関連携入出金状況インスタンスを再取得する。 
                //［引数］ 
                //証券会社コード： 受信電文データ.get("cpy")の戻り値 
                //部店コード： 受信電文データ.get("btn")の戻り値 
                //識別コード： 受信電文データ.get("req")の戻り値 
                l_finInstiCashTransStatus = 
                    new WEB3AioFinInstitutionCashTransStatus(
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("金融機@関連携入出金状況インスタンスのエラ―", l_ex);
            }
            //1.7.2 更新用の行オブジェクトを生成する。 
            l_finInstiCashTransStatus.createForUpdateParams();
            
            //1.7.3 update注文要求応答状態(金融機@関連携入出金状況)
            // 金融機@関連携入出金状況テーブルを注文要求受付処理終了（応答送信）のステータスに更新する。 
            //［引数］ 
            // 入出金状況： 金融機@関連携入出金状況オブジェクト 
            try
            {
                l_service.updateOrderRequireResponse(l_finInstiCashTransStatus);
            }
            // エラー：update注文要求応答状態(金融機@関連携入出金状況)。
            catch (WEB3BaseException l_ex)
            {
                log.debug("エラー：update注文要求応答状態(金融機@関連携入出金状況)");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        // 1.8 create注文要求応答データ(String, HashMap)
        // 送信電文にセットするデータを生成する。
        // ［引数］ 
        //  returnCode： returnCode 
        //  受信電文データ： リクエストデータ.受信電文データ 
        //  入出金状況： 金融機@関連携入出金状況オブジェクト
        String[] l_strReturns = 
            this.createOrderRequireResponseData(
                    l_strTargetReturnCode, 
                    l_receiptTelegramData, 
                    l_finInstiCashTransStatus);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (決済開始受付)<BR>
     * 決済開始受付処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（マルチバンク電文処理）決済開始受付」 参照<BR>
     * @@param l_strReturnCode - (リターンコード)<BR>
     * @@param l_receiptTelegramData - (受信電文データ)<BR>
     * @@return String[]
     * @@roseuid 41255E0C0033
     */
    protected String[] settlementStartAccept(String l_strReturnCode, HashMap l_receiptTelegramData)  
    {
        final String l_strMethodName = "settlementStartAccept(String l_strReturnCode, "
            + " HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        
        //1.1  returnCode = 引数.returnCode
        String l_strTargetReturnCode = l_strReturnCode;
        WEB3AioMultiBankSettleControlService l_service =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);
        
        WEB3AioFinInstitutionCashTransStatus l_finInstiCashTransStatus = null;
        boolean l_blnCashTransStatus = false;
        BankCashTransferStatusRow l_bankStatusRow = null;
            
        try
        {
            // 1.2 金融機@関連携入出金状況(String, String, String)
            //［引数］ 
            // 証券会社コード： 受信電文データ.get("cpy")の戻り値 
            // 部店コード： 受信電文データ.get("btn")の戻り値 
            // 識別コード： 受信電文データ.get("req")の戻り値
            l_finInstiCashTransStatus = 
                new WEB3AioFinInstitutionCashTransStatus(
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            l_bankStatusRow = 
                (BankCashTransferStatusRow)l_finInstiCashTransStatus.getDataSourceObject();
            if (!(WEB3TransactionStatusDef.NOT_DEAL.equals(l_bankStatusRow.getTransactionStatus())
                && WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(l_bankStatusRow.getOrderStatusFlag())
                && WEB3StartStatusFlgDef.NOT_DEAL.equals(l_bankStatusRow.getStartStatusFlag())))
            {
                log.debug(
                    "「金融機@関連携入出金状況.処理区分 == ０：未処理"
                    + " AND 処理FLAG（注文）== ２：応答送信"
                    + " AND 処理FLAG（決済開始） == ０：未処理」以外の場合");

                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
            l_blnCashTransStatus = true;
        }
        // インスタンスのエラ―の場合
        catch (WEB3BaseException l_ex)
        {
            log.debug("金融機@関連携入出金状況インスタンスのエラ―");
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        // 1.3 returnCode = OKの場合
        if(WEB3AioTelegramReturnCodeDef.OK.equals(l_strTargetReturnCode))
        {
            //1.3.1 受信電文の整合性をチェックする。 
            //［引数］ 
            // 受信電文データ： リクエストデータ.受信電文データ 
            // 入出金状況： 金融機@関連携入出金状況オブジェクト 
            l_strTargetReturnCode = l_service.validateReceiptTelegram(l_receiptTelegramData, l_finInstiCashTransStatus);
        }
        // 1.4 insert決済開始要求(HashMap)
        // 決済開始要求テーブルにレコードを追加する。 
        //［引数］ 
        // 受信電文データ： リクエストデータ.受信電文データ 
        try
        {
            l_service.insertSettleStartRequire(l_receiptTelegramData);
        }
        // エラー：決済開始要求テーブルにレコードを追加する。
        catch (WEB3BaseException l_ex)
        {
            log.debug("エラー：決済開始要求テーブルにレコードを追加する。");
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
        }
        
        //金融機@関連携入出金状況インスタンスが取得できている and 処理FLAG(決済開始)='0'の場合
        if (l_blnCashTransStatus && WEB3TransactionStatusDef.NOT_DEAL.equals(
                l_bankStatusRow.getStartStatusFlag()))
        {
            // 1.5 createForUpdateParams( )
            l_finInstiCashTransStatus.createForUpdateParams();
            
            // 1.6 update決済開始受付状態(String, 金融機@関連携入出金状況)
            // 金融機@関連携入出金状況テーブルを決済開始受付処理中のステータスに更新する。
            // ［引数］ 
            // returnCode： returnCode 
            // 入出金状況： 金融機@関連携入出金状況オブジェクト 
            try
            {
                l_service.updateSettleStartAccept(
                        l_strTargetReturnCode, 
                        l_finInstiCashTransStatus);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("Error in 金融機@関連携入出金状況テーブルを決済開始受付処理中のステータスに更新する。");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        // 1.7 returnCode = "OK"の場合
        if(WEB3AioTelegramReturnCodeDef.OK.equals(l_strTargetReturnCode))
        {
            try
            {
                //1.7.1 金融機@関連携入出金状況インスタンスを再取得する。 
                //［引数］ 
                //証券会社コード： 受信電文データ.get("cpy")の戻り値 
                //部店コード： 受信電文データ.get("btn")の戻り値 
                //識別コード： 受信電文データ.get("req")の戻り値 
                l_finInstiCashTransStatus = 
                    new WEB3AioFinInstitutionCashTransStatus(
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("金融機@関連携入出金状況インスタンスのエラ―");
            }
            
            //1.7.2 createForUpdateParams( )
            l_finInstiCashTransStatus.createForUpdateParams();
            
            //1.7.3 update決済開始応答状態(金融機@関連携入出金状況)
            // 金融機@関連携入出金状況テーブルを決済開始受付処理終了（応答送信）のステータスに更新する。 
            //［引数］ 
            // 入出金状況： 金融機@関連携入出金状況オブジェクト 
            try
            {
                l_service.updateSettleStartResponse(l_finInstiCashTransStatus);
            }
            // エラー：update決済開始応答状態(金融機@関連携入出金状況)。
            catch (WEB3BaseException l_ex)
            {
                log.debug("エラー：update決済開始応答状態(金融機@関連携入出金状況)");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        // 1.8 create決済開始応答データ
        // 送信電文にセットするデータを生成する。
        // ［引数］ 
        //  returnCode： returnCode 
        //  受信電文データ： リクエストデータ.受信電文データ 
        String[] l_strReturns = 
            this.createSettlementStartResponseData(l_strTargetReturnCode, l_receiptTelegramData);
        
        log.exiting(l_strMethodName);
        return l_strReturns; 
    }
    
    /**
     * (決済結果通知)<BR>
     * 決済結果通知を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（マルチバンク電文処理）決済結果通知」 参照<BR>
     * @@param l_strReturnCode - (リターンコード)<BR>
     * @@param l_receiptTelegramData - (受信電文データ)<BR>
     * @@return String[]
     * @@roseuid 41255E37011D
     */
    protected String[] settlementResultNotify(String l_strReturnCode, HashMap l_receiptTelegramData)
    {
        final String l_strMethodName = "settlementResultNotify(String l_strReturnCode, "
            + " HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        //lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        //証券会社コード： 受信電文データ.get("cpy")の戻り値 
        //部店コード： 受信電文データ.get("btn")の戻り値 
        //口座コード： 受信電文データ.get("acc")の戻り値
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
        try
        {
            l_gentradeAccountManager.lockAccount(
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc));
        }
        catch (Exception l_ex)
        {
            log.exiting(l_strMethodName);
            return null;
        }

        // 1.1 returnCode = "ERROR"の場合　@ 'NG'をセットする
        String l_strTargetReturnCode = null;
        if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
        {
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
        }
        // それ以外の場合 受信電文データ.get("payStatus")の戻り値をセットする
        else
        {
            l_strTargetReturnCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus);
        }
        WEB3AioMultiBankSettleControlService l_service =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);

        WEB3AioFinInstitutionCashTransStatus l_finInstiCashTransStatus = null;
        BankCashTransferStatusRow l_bankStatusRow = null;
        boolean l_blnCashTransStatus = false;
        
        try
        {
            // 1.2 金融機@関連携入出金状況(String, String, String)
            //［引数］ 
            // 証券会社コード： 受信電文データ.get("cpy")の戻り値 
            // 部店コード： 受信電文データ.get("btn")の戻り値 
            // 識別コード： 受信電文データ.get("req")の戻り値
            l_finInstiCashTransStatus = 
                new WEB3AioFinInstitutionCashTransStatus(
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            l_bankStatusRow = 
                (BankCashTransferStatusRow)l_finInstiCashTransStatus.getDataSourceObject();
            if (!(WEB3TransactionStatusDef.NOT_DEAL.equals(l_bankStatusRow.getTransactionStatus())
                && WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(l_bankStatusRow.getOrderStatusFlag())
                && WEB3StartStatusFlgDef.RESPONSE_SEND.equals(l_bankStatusRow.getStartStatusFlag())
                && WEB3ResultStatusFlagDef.NOT_DEAL.equals(l_bankStatusRow.getResultStatusFlag())))
            {
                log.debug(
                    "「インスタンスの生成に失敗した（該当レコードなし）」 or" +
                    "「(処理区分 == '0' and 処理FLAG（注文） == '2' and" +
                    " 処理FLAG（決済開始） == '2' and 処理FLAG（決済結果） == '0')以外」の場合、" +
                    "returnCodeに'NG'をセット");
                
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
            }
            l_blnCashTransStatus = true;
        }
        // インスタンスのエラ―の場合
        catch (WEB3BaseException l_ex)
        {
            log.debug("金融機@関連携入出金状況インスタンスのエラ―", l_ex);
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
        }
        // 1.3 returnCode != NGの場合
        if(!WEB3AioTelegramReturnCodeDef.NG.equals(l_strTargetReturnCode))
        {
            // 1.3.1 受信電文の整合性をチェックする。 
            //［引数］ 
            // 受信電文データ： リクエストデータ.受信電文データ 
            // 入出金状況： 金融機@関連携入出金状況オブジェクト 
            String l_strValidateReturnCode = 
                l_service.validateReceiptTelegram(l_receiptTelegramData, l_finInstiCashTransStatus);
            // 戻り値は　@！＝　@'OK'の場合　@'NG'をセットする
            if(!WEB3AioTelegramReturnCodeDef.OK.equals(l_strValidateReturnCode))
            {
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
            }
        }
        // 1.4 insert決済結果通知(HashMap)
        // 決済結果通知テーブルにレコードを追加する。 
        //［引数］ 
        // 受信電文データ： リクエストデータ.受信電文データ 
        try
        {
            l_service.insertSettleResultNotify(l_receiptTelegramData);
        }
        // エラー：決済開始要求テーブルにレコードを追加する。
        catch (WEB3BaseException l_ex)
        {
            log.debug("エラー：決済開始要求テーブルにレコードを追加する。");
            l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.NG;
        }
        // 1.5 returnCode = "COMPLETE"　@and 処理FLAG(決済結果) = '0' の場合　@　@
        HashMap l_mapDeliveryAndTransferDate = null;
        
        if (WEB3AioTelegramReturnCodeDef.COMPLETE.equals(l_strTargetReturnCode) 
            && WEB3ResultStatusFlagDef.NOT_DEAL.equals(l_bankStatusRow.getResultStatusFlag()))
        {
            // 1.5.1 submit注文(HashMap)
            // ［引数］ 
            // 受信電文データ： リクエストデータ.受信電文データ 
            try
            {
                l_mapDeliveryAndTransferDate = this.submitOrder(l_receiptTelegramData);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("Error in 注文の登録を行う。", l_ex);
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        
        // 1.6. 金融機@関連携入出金状況インスタンスが取得できている and 処理FLAG(決済結果)='0'の場合
        if (l_blnCashTransStatus && WEB3TransactionStatusDef.NOT_DEAL.equals(
                l_bankStatusRow.getResultStatusFlag()))
        {
            // 1.6.1  createForUpdateParams( )
            l_finInstiCashTransStatus.createForUpdateParams();
        
            // 1.6.2 update決済結果通知状態(String, Date, Date, 金融機@関連携入出金状況)
            // 金融機@関連携入出金状況テーブルを決済結果通知処理中のステータスに更新する。 
            //［引数］ 
            // returnCode： returnCode 
            // 受渡予定日： [submit注文()の戻り値].get(”受渡日”)の戻り値 
            // 振込入金予定日： [submit注文()の戻り値].get(”振込日”)の戻り値 
            //入出金状況： 金融機@関連携入出金状況オブジェクト 
            try
            {
                if (l_mapDeliveryAndTransferDate == null)
                {
                    l_service.updateSettleResultNotify(
                            l_strTargetReturnCode,
                            null,
                            null,
                            l_finInstiCashTransStatus);
                }
                else
                {
                    l_service.updateSettleResultNotify(
                            l_strTargetReturnCode, 
                            (Date)l_mapDeliveryAndTransferDate.get(this.DELIVER_DATE),
                            (Date)l_mapDeliveryAndTransferDate.get(this.TRANSFER_DATE),
                            l_finInstiCashTransStatus);
                }
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("Error in 金融機@関連携入出金状況テーブルを決済結果通知処理中のステータスに更新する。");
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
        
            //1.6.3 金融機@関連携入出金状況インスタンスを再取得する。 
            //［引数］ 
            //証券会社コード： 受信電文データ.get("cpy")の戻り値 
            //部店コード： 受信電文データ.get("btn")の戻り値 
            //識別コード： 受信電文データ.get("req")の戻り値 
            try
            {
                l_finInstiCashTransStatus = 
                    new WEB3AioFinInstitutionCashTransStatus(
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                        (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
            }
            // インスタンスのエラ―の場合
            catch (WEB3BaseException l_ex)
            {
                log.debug("金融機@関連携入出金状況インスタンスのエラ―", l_ex);
                l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
            }
    
            l_bankStatusRow = 
                (BankCashTransferStatusRow)l_finInstiCashTransStatus.getDataSourceObject();
    
            //処理FLAG（決済結果）= "1"の場合
            if (WEB3ResultStatusFlagDef.NOTIFY_RECEIPT.equals(l_bankStatusRow.getResultStatusFlag()))
            {
                //1.6.4 createForUpdateParams( )
                l_finInstiCashTransStatus.createForUpdateParams();
                
                //1.6.5 update決済結果応答状態(金融機@関連携入出金状況)
                // 金融機@関連携入出金状況テーブルを決済結果通知処理終了（応答送信）のステータスに更新する。
                //［引数］ 
                // 入出金状況： 金融機@関連携入出金状況オブジェクト 
                try
                {
                    l_service.updateSettleResultResponse(l_finInstiCashTransStatus);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.debug("Error in 金融機@関連携入出金状況テーブルを決済結果通知処理終了（応答送信）のステータスに更新する。");
                    l_strTargetReturnCode = WEB3AioTelegramReturnCodeDef.ERROR;
                }
            }
        }
        
        // 1.7  create決済結果応答データ
        // 送信電文にセットするデータを生成する。
        // ［引数］ 
        //  returnCode： returnCode 
        //  受信電文データ： リクエストデータ.受信電文データ 
        String[] l_strReturns = 
            this.createSettlementResultResponseData(l_receiptTelegramData);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (create注文要求応答データ)<BR>
     * 送信電文にセットするデータを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）以下の文字列をリストに登録する。<BR>
     * <BR>
     *    "protocolVersion=V1.0"<BR>
     *    "date=[引数.受信電文データ.get("date")の戻り値]"<BR>
     *    "centerPayId=[引数.受信電文データ.get("centerPayId")の戻り値]"<BR>
     *    "returnCode=[引数.returnCode]"<BR>
     * <BR>
     * ３）引数.returnCode = "OK" の場合、以下の文字列をリストに登録する.<BR>
     * <BR>
     *    "ComOndebiPayMode=10"<BR>
     *    "ComOndebiTypicalGoodsName=証券口座振替"<BR>
     *    "amount=[引数.入出金状況.金額]"<BR>
     *    "ComOndebiTax=0"<BR>
     * <BR>
     * ４）引数.returnCode = "ERROR" の場合、以下の文字列をリストに登録する。<BR>
     * <BR>
     *    "description=ただいまシステムエラーが発生しましたの<BR>
     * で処理を中止します。<BR>
     * お取扱店にご確認下さい。"<BR>
     * <BR>
     * ５）以下の文字列をリストに登録する。<BR> 
     * <BR>
     * "cancelURL=[URL]?io_rturl=2&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]"<BR> 
     * "errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]" <BR>
     * "prsid=[引数.受信電文データ.get("prsid")の戻り値]" <BR>
     * "praid=[引数.受信電文データ.get("praid")の戻り値]" <BR>
     * "praarsid=[引数.受信電文データ.get("praarsid")の戻り値]" <BR>
     * "prssid=[引数.受信電文データ.get("prssid")の戻り値]" <BR>
     * "praadpdv=[引数.受信電文データ.get("prdpdv")の戻り値]" <BR>
     * "apsid=[引数.受信電文データ.get("apsid")の戻り値]" <BR>
     * "cpy=[引数.受信電文データ.get("cpy")の戻り値]" <BR>
     * "btn=[引数.受信電文データ.get("btn")の戻り値]" <BR>
     * "acc=[引数.受信電文データ.get("acc")の戻り値]" <BR>
     * "req=[引数.受信電文データ.get("req")の戻り値]" <BR>
     * "rdiv=[引数.受信電文データ.get("rdiv")の戻り値]"<BR> 
     * "web3Request=SettleStart" <BR>
     * <BR>
     * ６）加盟店IDの取得<BR> 
     *   ６－１）マルチバンク決済制御サービスImpl.getキャリア区分（引数.受信電文データ.get("rdiv")）で、<BR>
     *          キャリア区分を取得する。<BR>
     * <BR>
     *   ６－２）キャリア別加盟店IDインスタンスを生成する。<BR>
     * <BR> 
     * [コンストラクタの引数] <BR>
     * 証券会社コード = 引数.受信電文データ.get("cpy")の戻り値<BR>
     * 部店コード = 引数.受信電文データ.get("btn")の戻り値<BR> 
     * キャリア区分 = getキャリア区分（）で取得した値<BR> 
     * <BR>
     * ※URL：　@キャリア別加盟店ID.getリターンURL()にて取得した値 <BR>
     * <BR>
     * <BR> 
     * *1： PR層セッションキー（ 引数.受信電文データ.get("prsid")の戻り値 ） <BR>
     * *2： PR層アプリケーションID（ 引数.受信電文データ.get("praid")の戻り値 ） <BR>
     * *3： PR層再生成サービスID（ 引数.受信電文データ.get("praarsid")の戻り値 ） <BR>
     * *4： PR層SSID（ 引数.受信電文データ.get("prssid")の戻り値 ） <BR>
     * *5： PR層表示区分（ 引数.受信電文データ.get("prdpdv")の戻り値 ） <BR>
     * *6： AP層セッションID（ 引数.受信電文データ.get("apsid")の戻り値 ） <BR>
     * *7： 証券会社コード（ 引数.受信電文データ.get("cpy")の戻り値 ） <BR>
     * *8： 部店コード（ 引数.受信電文データ.get("btn")の戻り値 ） <BR>
     * *9： 顧客コード（ 引数.受信電文データ.get("acc")の戻り値 ） <BR>
     * *10： 識別コード（ 引数.受信電文データ.get("req")の戻り値 ）<BR>
     * *11： 注文経路区分（ 引数.受信電文データ.get("rdiv")の戻り値 ）<BR>
     * <BR>
     * ７）リストから配列を取得して、返す。 
     * @@param l_strReturnCode - (リターンコード)<BR>
     * @@param l_receiptTelegramData - (受信電文データ)<BR>
     * @@param l_finTransStatus - 入出金状況
     * @@return String[]
     * @@roseuid 41255F4302F2
     */
    protected String[] createOrderRequireResponseData(
            String l_strReturnCode, 
            HashMap l_receiptTelegramData,
            WEB3AioFinInstitutionCashTransStatus l_finTransStatus) 
    {
        final String l_strMethodName = "createOrderRequireResponseData(String " +
                "l_strReturnCode, HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        // １）空のArrayListを生成する。 
        ArrayList l_lisReturn = new ArrayList(); 
        // ２）以下の文字列をリストに登録する。 
        // "protocolVersion=V1.0"
        l_lisReturn.add(WEB3AioTelegramFormatDef.protocolVersion + "=" + WEB3AioTelegramHttpRequestDef.V1DOT0);
        // "date=[引数.受信電文データ.get("date")の戻り値]" 
        String l_strDateInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date);
        l_lisReturn.add("date=" + l_strDateInMap);
        // "centerPayId=[引数.受信電文データ.get("centerPayId")の戻り値]" 
        String l_strCenterPayIdInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId);
        l_lisReturn.add("centerPayId=" + l_strCenterPayIdInMap);
        // "returnCode=[引数.returnCode]" 
        l_lisReturn.add("returnCode=" + l_strReturnCode);
        // ３）引数.returnCode = "OK" の場合、以下の文字列をリストに登録する。 
        if (WEB3AioTelegramReturnCodeDef.OK.equals(l_strReturnCode))
        {
            // "ComOndebiPayMode=10" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.ComOndebiPayMode + "=10");
            // "ComOndebiTypicalGoodsName=証券口座振替" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.ComOndebiTypicalGoodsName + "=証券口座振替");
            // "amount=[引数.入出金状況.金額]" 
            long l_lngAmount = 
                ((BankCashTransferStatusRow)l_finTransStatus.getDataSourceObject()).getAmount();
            l_lisReturn.add(WEB3AioTelegramFormatDef.amount + "=" + l_lngAmount);
            // "ComOndebiTax=0" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.ComOndebiTax + "=0");
        }
        // ４）引数.returnCode = "ERROR" の場合、以下の文字列をリストに登録する。 
        if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
        {
            // "description=ただいまシステムエラーが発生しましたので処理を中止します。<BR>お取扱店にご確認下さい。" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.description + 
                "=ただいまシステムエラーが発生しましたので処理を中止します。<BR>お取扱店にご確認下さい。");
        }
        
        // ５）以下の文字列をリストに登録する。         
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService = 
            (WEB3AioMultiBankSettleControlService)Services.getService(
                WEB3AioMultiBankSettleControlService.class);
        
        // *1： PR層セッションキー（ 引数.受信電文データ.get("prsid")の戻り値 ）
        String l_strPrSessionKey = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prsid);
        
        // *2： PR層アプリケーションID（ 引数.受信電文データ.get("praid")の戻り値 ）
        String l_strPrSoftWareId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.praid);
        
        // *3： PR層再生成サービスID（ 引数.受信電文データ.get("praarsid")の戻り値 ） 
        String l_strPrAgainServiceWareId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.praarsid);
        
        // *4： PR層SSID（ 引数.受信電文データ.get("prssid")の戻り値 ） 
        String l_strPrSsId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prssid);
        
        // *5： PR層表示区分（ 引数.受信電文データ.get("prdpdv")の戻り値 ）
        String l_strPrDpdv = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prdpdv);
        
        // *6： AP層セッションID（ 引数.受信電文データ.get("apsid")の戻り値 ） 
        String l_strAPSessionID = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.apsid);
        
        // *7： 証券会社コード（ 引数.受信電文データ.get("cpy")の戻り値 ） 
        String l_strInstitutionCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy);
        
        // *8： 部店コード（ 引数.受信電文データ.get("btn")の戻り値 ） 
        String l_strBranchCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn);
        
        // *9： 顧客コード（ 引数.受信電文データ.get("acc")の戻り値 ） 
        String l_strAccountCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc);
        
        // *10： 識別コード（ 引数.受信電文データ.get("req")の戻り値 ） 
        String l_strRequestCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req);
        
        // *11： 注文経路区分（ 引数.受信電文データ.get("rdiv")の戻り値 ）
        String l_strOrderRootDiv = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv);
        
        // ６）加盟店IDの取得
        // ６－１）マルチバンク決済制御サービスImpl.getキャリア区分（引数.受信電文データ.get("rdiv")）で、キャリア区分を取得する。
        String l_strCareerDiv = l_aioMultiBankSettleControlService.getCareerDiv(l_strOrderRootDiv);
        // ６－２）キャリア別加盟店IDインスタンスを生成する。
        WEB3AioCareerShopId l_aioCareerShopId = null;
        try
        {
            l_aioCareerShopId = new WEB3AioCareerShopId(l_strInstitutionCode, l_strBranchCode, l_strCareerDiv);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        } 
         
        String l_strReturnUrl = l_aioCareerShopId.getReturnURL();

        //"cancelURL=[URL]?io_rturl=2&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]"  
        l_lisReturn.add(WEB3AioTelegramFormatDef.cancelURL + "=" + l_strReturnUrl + "?" +
            WEB3AioTelegramFormatDef.io_rturl + "=" + WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS2 + "&" + 
            WEB3AioTelegramFormatDef.wolfSessionKey + "=" + l_strPrSessionKey + "&" + 
            WEB3AioTelegramFormatDef.aa_aid + "=" + l_strPrSoftWareId + "&" + 
            WEB3AioTelegramFormatDef.aa_rsid + "=" + l_strPrAgainServiceWareId + "&" +
            WEB3AioTelegramFormatDef.ssid + "=" + l_strPrSsId + "&" + 
            WEB3AioTelegramFormatDef.aa_dpdv + "=" + l_strPrDpdv + "&" + 
            WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID + "&" + 
            WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode + "&" +
            WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode + "&" +
            WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode + "&" +
            WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);
        
        //"errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.errorURL + "=" + l_strReturnUrl + "?" +
            WEB3AioTelegramFormatDef.io_rturl + "=" + WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS1 + "&" +
            WEB3AioTelegramFormatDef.wolfSessionKey + "=" + l_strPrSessionKey + "&" +
            WEB3AioTelegramFormatDef.aa_aid + "=" + l_strPrSoftWareId + "&" +
            WEB3AioTelegramFormatDef.aa_rsid + "=" + l_strPrAgainServiceWareId + "&" +
            WEB3AioTelegramFormatDef.ssid + "=" + l_strPrSsId + "&" +
            WEB3AioTelegramFormatDef.aa_dpdv + "=" + l_strPrDpdv + "&" +
            WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID + "&" +
            WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode + "&" +
            WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode + "&" +
            WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode + "&" +
            WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);
        
        //prsid=[引数.受信電文データ.get("prsid")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.prsid + "=" + l_strPrSessionKey);
        
        //"praid=[引数.受信電文データ.get("praid")の戻り値]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.praid + "=" + l_strPrSoftWareId);
        
        //"praarsid=[引数.受信電文データ.get("praarsid")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.praarsid + "=" + l_strPrAgainServiceWareId);
            
        //"prssid=[引数.受信電文データ.get("prssid")の戻り値]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.prssid + "=" + l_strPrSsId);
        
        //"praadpdv=[引数.受信電文データ.get("prdpdv")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.prdpdv + "=" + l_strPrDpdv);
        
        //"apsid=[引数.受信電文データ.get("apsid")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID);
        
        //"cpy=[引数.受信電文データ.get("cpy")の戻り値]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode);
        
        //"btn=[引数.受信電文データ.get("btn")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode);
        
        //"acc=[引数.受信電文データ.get("acc")の戻り値]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode);
        
        //"req=[引数.受信電文データ.get("req")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);
        
        //"rdiv=[引数.受信電文データ.get("rdiv")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.rdiv+ "=" + l_strOrderRootDiv);
        
        //"web3Request=SettleStart"
        l_lisReturn.add(WEB3AioTelegramFormatDef.web3Request + "=" + WEB3AioTelegramHttpRequestDef.SETTLE_START);
        
        String[] l_strReturns = new String[l_lisReturn.size()];
        
        // test log
        log.debug("送信電文にセットするデータ l_strReturns = " + l_strReturns);
        l_lisReturn.toArray(l_strReturns);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (create決済開始応答データ)<BR>
     * 送信電文にセットするデータを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）以下の文字列をリストに登録する。<BR>
     * <BR>
     *    "protocolVersion=V1.0"<BR>
     *    "date=[引数.受信電文データ.get("date")の戻り値]"<BR>
     *    "centerPayId=[引数.受信電文データ.get("centerPayId")の戻り値]"<BR>
     *    "returnCode=[引数.returnCode]"<BR>
     * <BR>
     * ３）引数.returnCode = "OK" の場合、以下の文字列をリストに登録する。<BR>
     * <BR>
     * "returnURL=[URL]?io_rturl=0&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]&rdiv=[*11]"<BR>
     * <BR>
     * ４）引数.returnCode = "ERROR" の場合、以下の文字列をリストに登録する。<BR>
     * <BR>
     *    "description=ただいまシステムエラーが発生しましたので処理を中止します。<BR>
     * お取扱店にご確認下さい。" <BR>
     * <BR>
     * ５）以下の文字列をリストに登録する。<BR> 
     * <BR>
     * "errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]" 
     * "prsid=[引数.受信電文データ.get("prsid")の戻り値]" 
     * "praid=[引数.受信電文データ.get("praid")の戻り値]" 
     * "praarsid=[引数.受信電文データ.get("praarsid")の戻り値]" 
     * "prssid=[引数.受信電文データ.get("prssid")の戻り値]" 
     * "prdpdv=[引数.受信電文データ.get("prdpdv")の戻り値]" 
     * "apsid=[引数.受信電文データ.get("apsid")の戻り値]" 
     * "cpy=[引数.受信電文データ.get("cpy")の戻り値]" 
     * "btn=[引数.受信電文データ.get("btn")の戻り値]" 
     * "acc=[引数.受信電文データ.get("acc")の戻り値]" 
     * "req=[引数.受信電文データ.get("req")の戻り値]" 
     * "rdiv=[引数.受信電文データ.get("rdiv")の戻り値]" 
     * "web3Request=SettleResult" <BR>
     * <BR>
     * ６）加盟店IDの取得<BR> 
     *   ６－１）マルチバンク決済制御サービスImpl.getキャリア区分（引数.受信電文データ.get("rdiv")）で、<BR>
     *          キャリア区分を取得する。<BR>
     * <BR>
     *   ６－２）キャリア別加盟店IDインスタンスを生成する。<BR>
     * <BR> 
     * [コンストラクタの引数] <BR>
     * 証券会社コード = 引数.受信電文データ.get("cpy")の戻り値<BR>
     * 部店コード = 引数.受信電文データ.get("btn")の戻り値<BR> 
     * キャリア区分 = getキャリア区分（）で取得した値<BR> 
     * <BR>
     * ※URL：　@キャリア別加盟店ID.getリターンURL()にて取得した値 <BR>
     * <BR>
     * *1： PR層セッションキー（ 引数.受信電文データ.get("prsid")の戻り値 ） <BR> 
     * *2： PR層アプリケーションID（ 引数.受信電文データ.get("praid")の戻り値 ） <BR> 
     * *3： PR層再生成サービスID（ 引数.受信電文データ.get("praarsid")の戻り値 ） <BR> 
     * *4： PR層SSID（ 引数.受信電文データ.get("prssid")の戻り値 ） <BR> 
     * *5： PR層表示区分（ 引数.受信電文データ.get("prdpdv")の戻り値 ） <BR> 
     * *6： AP層セッションID（ 引数.受信電文データ.get("apsid")の戻り値 ） <BR> 
     * *7： 証券会社コード（ 引数.受信電文データ.get("cpy")の戻り値 ） <BR> 
     * *8： 部店コード（ 引数.受信電文データ.get("btn")の戻り値 ） <BR> 
     * *9： 顧客コード（ 引数.受信電文データ.get("acc")の戻り値 ） <BR> 
     * *10： 識別コード（ 引数.受信電文データ.get("req")の戻り値 ） <BR>
     * *11： 注文経路区分（ 引数.受信電文データ.get("rdiv")の戻り値 ）<BR>
     * <BR>
     * ６）リストから配列を取得して、返す。<BR>
     * @@param l_strReturnCode - (リターンコード)<BR>
     * @@param l_receiptTelegramData - (受信電文データ)<BR>
     * @@return String[]
     * @@roseuid 41255F6F036F
     */
    protected String[] createSettlementStartResponseData(
            String l_strReturnCode, HashMap l_receiptTelegramData) 
    {
        final String l_strMethodName = "createSettlementStartResponseData(String " +
                "l_strReturnCode, HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        // １）空のArrayListを生成する。 
        ArrayList l_lisReturn = new ArrayList(); 
        // ２）以下の文字列をリストに登録する。 
        // "protocolVersion=V1.0"
        l_lisReturn.add(WEB3AioTelegramFormatDef.protocolVersion + "=" + WEB3AioTelegramHttpRequestDef.V1DOT0);
        // "date=[引数.受信電文データ.get("date")の戻り値]" 
        String l_strDateInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date);
        l_lisReturn.add(WEB3AioTelegramFormatDef.date + "=" + l_strDateInMap);
        // "centerPayId=[引数.受信電文データ.get("centerPayId")の戻り値]" 
        String l_strCenterPayIdInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId);
        l_lisReturn.add(WEB3AioTelegramFormatDef.centerPayId + "=" + l_strCenterPayIdInMap);
        // "returnCode=[引数.returnCode]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.returnCode + "=" + l_strReturnCode);
        
        //========remain zhou-yong NO.2 begin ===========
        
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService = 
            (WEB3AioMultiBankSettleControlService)Services.getService(
                WEB3AioMultiBankSettleControlService.class);
        
        // *1： PR層セッションキー（ 引数.受信電文データ.get("prsid")の戻り値 ） 
        String l_strPrSessionKey = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prsid);
        
        // *2： PR層アプリケーションID（ 引数.受信電文データ.get("praid")の戻り値 ）
        String l_strPrSoftWareId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.praid);
        
        // *3： PR層再生成サービスID（ 引数.受信電文データ.get("praarsid")の戻り値 ） 
        String l_strPrAgainServiceWareId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.praarsid);
        
        // *4： PR層SSID（ 引数.受信電文データ.get("prssid")の戻り値 ） 
        String l_strPrSsId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prssid);
        
        // *5： PR層表示区分（ 引数.受信電文データ.get("prdpdv")の戻り値 ）
        String l_strPrDpdv = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.prdpdv);
        
        // *6： AP層セッションID（ 引数.受信電文データ.get("apsid")の戻り値 ） 
        String l_strAPSessionID = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.apsid);
        
        // *7： 証券会社コード（ 引数.受信電文データ.get("cpy")の戻り値 ） 
        String l_strInstitutionCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy);
        
        // *8： 部店コード（ 引数.受信電文データ.get("btn")の戻り値 ） 
        String l_strBranchCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn);
        
        // *9： 顧客コード（ 引数.受信電文データ.get("acc")の戻り値 ） 
        String l_strAccountCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc);
        
        // *10： 識別コード（ 引数.受信電文データ.get("req")の戻り値 ） 
        String l_strRequestCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req);
        
        // *11： 注文経路区分（ 引数.受信電文データ.get("rdiv")の戻り値 ）
        String l_strOrderRootDiv = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv);
        
        // ６）加盟店IDの取得
        // ６－１）マルチバンク決済制御サービスImpl.getキャリア区分（引数.受信電文データ.get("rdiv")）で、キャリア区分を取得する。
        String l_strCareerDiv = l_aioMultiBankSettleControlService.getCareerDiv(l_strOrderRootDiv);
        // ６－２）キャリア別加盟店IDインスタンスを生成する。
        WEB3AioCareerShopId l_aioCareerShopId = null;
        try
        {
            l_aioCareerShopId = new WEB3AioCareerShopId(l_strInstitutionCode, l_strBranchCode, l_strCareerDiv);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
        } 
         
        String l_strReturnUrl = l_aioCareerShopId.getReturnURL();

        //３）引数.returnCode = "OK" の場合、以下の文字列をリストに登録する。 
        //"returnURL=[URL]?io_rturl=0&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]&rdiv=[*11]"  
        if (WEB3AioTelegramReturnCodeDef.OK.equals(l_strReturnCode))
        {
            l_lisReturn.add(WEB3AioTelegramFormatDef.returnURL + "=" + l_strReturnUrl + "?" +  
                WEB3AioTelegramFormatDef.io_rturl + "=" + WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS0 + "&" + 
                WEB3AioTelegramFormatDef.wolfSessionKey + "=" + l_strPrSessionKey + "&" + 
                WEB3AioTelegramFormatDef.aa_aid + "=" + l_strPrSoftWareId + "&" + 
                WEB3AioTelegramFormatDef.aa_rsid + "=" + l_strPrAgainServiceWareId + "&" +
                WEB3AioTelegramFormatDef.ssid + "=" + l_strPrSsId + "&" + 
                WEB3AioTelegramFormatDef.aa_dpdv + "=" + l_strPrDpdv + "&" + 
                WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID + "&" + 
                WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode + "&" +
                WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode + "&" +
                WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode + "&" +
                WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode + "&" +
                WEB3AioTelegramFormatDef.rdiv + "=" + l_strOrderRootDiv);
        }
        
        // ４）引数.returnCode = "ERROR" の場合、以下の文字列をリストに登録する。 
        if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
        {
            // "description=ただいまシステムエラーが発生しましたので処理を中止します。<BR>お取扱店にご確認下さい。" 
            l_lisReturn.add(WEB3AioTelegramFormatDef.description + 
                "=ただいまシステムエラーが発生しましたので処理を中止します。<BR>お取扱店にご確認下さい。");
        }
        
        //５）以下の文字列をリストに登録する。
        //"errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.errorURL + "=" + l_strReturnUrl + "?" +
            WEB3AioTelegramFormatDef.io_rturl + "=" + WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS1 + "&" + 
            WEB3AioTelegramFormatDef.wolfSessionKey + "=" + l_strPrSessionKey + "&" + 
            WEB3AioTelegramFormatDef.aa_aid + "=" + l_strPrSoftWareId + "&" + 
            WEB3AioTelegramFormatDef.aa_rsid + "=" + l_strPrAgainServiceWareId + "&" +
            WEB3AioTelegramFormatDef.ssid + "=" + l_strPrSsId + "&" + 
            WEB3AioTelegramFormatDef.aa_dpdv + "=" + l_strPrDpdv + "&" + 
            WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID + "&" + 
            WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode + "&" +
            WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode + "&" +
            WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode + "&" +
            WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);

        //prsid=[引数.受信電文データ.get("prsid")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.prsid + "=" + l_strPrSessionKey);
        
        //"praid=[引数.受信電文データ.get("praid")の戻り値]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.praid + "=" + l_strPrSoftWareId);
        
        //"praarsid=[引数.受信電文データ.get("praarsid")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.praarsid + "=" + l_strPrAgainServiceWareId);
            
        //"prssid=[引数.受信電文データ.get("prssid")の戻り値]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.prssid + "=" + l_strPrSsId);
        
        //"prdpdv=[引数.受信電文データ.get("prdpdv")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.prdpdv + "=" + l_strPrDpdv);        
        
        //"apsid=[引数.受信電文データ.get("apsid")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.apsid + "=" + l_strAPSessionID);
        
        //"cpy=[引数.受信電文データ.get("cpy")の戻り値]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.cpy + "=" + l_strInstitutionCode);
        
        //"btn=[引数.受信電文データ.get("btn")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.btn + "=" + l_strBranchCode);
        
        //"acc=[引数.受信電文データ.get("acc")の戻り値]"
        l_lisReturn.add(WEB3AioTelegramFormatDef.acc + "=" + l_strAccountCode);
        
        //"req=[引数.受信電文データ.get("req")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.req + "=" + l_strRequestCode);
        
        //"rdiv=[引数.受信電文データ.get("rdiv")の戻り値]" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.rdiv + "=" + l_strOrderRootDiv);
        
        //"web3Request=SettleResult"
        l_lisReturn.add(WEB3AioTelegramFormatDef.web3Request + "=" + WEB3AioTelegramHttpRequestDef.SETTLE_RESULT);
        
        //========remain zhou-yong NO.2 end ===========
        
        // ６）リストから配列を取得して、返す。 
        String[] l_strReturns = new String[l_lisReturn.size()];
        l_lisReturn.toArray(l_strReturns);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (create決済結果応答データ)<BR>
     * 送信電文にセットするデータを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）以下の文字列をリストに登録する。<BR>
     * <BR>
     *    "protocolVersion=V1.0"<BR>
     *    "date=[引数.受信電文データ.get("date")の戻り値]"<BR>
     *    "centerPayId=[引数.受信電文データ.get("centerPayId")の戻り値]"<BR>
     *    "returnCode=OK"<BR>
     * <BR>
     * ３）リストから配列を取得して、返す。<BR>
     * @@param l_receiptTelegramData - (受信電文データ)
     * @@return String[]
     * @@roseuid 41255F8C0014
     */
    protected String[] createSettlementResultResponseData(HashMap l_receiptTelegramData) 
    {
        final String l_strMethodName = "createSettlementResultResponseData(HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);
        // １）空のArrayListを生成する。 
        ArrayList l_lisReturn = new ArrayList(); 
        // ２）以下の文字列をリストに登録する。 
        // "protocolVersion=V1.0" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.protocolVersion + "=" + WEB3AioTelegramHttpRequestDef.V1DOT0);
        // "date=[引数.受信電文データ.get("date")の戻り値]" 
        String l_strDateInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date);
        l_lisReturn.add(WEB3AioTelegramFormatDef.date + "=" + l_strDateInMap);
        // "centerPayId=[引数.受信電文データ.get("centerPayId")の戻り値]" 
        String l_strCenterPayIdInMap = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId);
        l_lisReturn.add(WEB3AioTelegramFormatDef.centerPayId + "=" + l_strCenterPayIdInMap);
        // "returnCode=OK" 
        l_lisReturn.add(WEB3AioTelegramFormatDef.returnCode + "=" + WEB3AioTelegramReturnCodeDef.OK);

        // ３）リストから配列を取得して、返す。
        String[] l_strReturns = new String[l_lisReturn.size()];
        l_lisReturn.toArray(l_strReturns);
        log.exiting(l_strMethodName);
        return l_strReturns;
    }
    
    /**
     * (submit注文)<BR>
     * オンライン入金の内容から、注文を登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（マルチバンク電文処理）submit注文」 参照
     * @@param l_receiptTelegramData - 受信電文データ
     * @@return HashMap
     * @@roseuid 41255F8C0023
     */
    protected HashMap submitOrder(HashMap l_receiptTelegramData) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "submitOrder(HashMap l_receiptTelegramData)";
        log.entering(l_strMethodName);

        // 1.2) get発注日( ) --- 発注日を取得する。
        Date l_dteOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        // 証券会社コード
        String l_strInstitutionCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy);
        // 部店コード
        String l_strBranchCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn);
        // 顧客コード
        String l_strAccountCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc);
        
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        
        // 証券会社
        Institution l_institution = null;
        // 部店
        Branch l_branch = null; 
        // 顧客
        MainAccount l_mainAccount = null; 
        // 補助口座
        SubAccount l_subAccount = null;
        try
        {
            // 1.3) InstitutionImpl(証券会社コード : String) --- 証券会社インスタンスを生成する。
            // ［引数］ 
            // 証券会社コード： 受信電文データ.get("cpy")の戻り値
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            // 1.4) MainAccountImpl(証券会社ID : long, 部店コード : String, 顧客コード : String)
            // ［引数］ 
            // 証券会社ID： 証券会社.getInstitutionId()の戻り値 
            // 部店コード： 受信電文データ.get("btn")の戻り値 
            // 顧客コード： 受信電文データ.get("acc")の戻り値 
            l_branch = l_accountManager.getBranch(l_institution, l_strBranchCode);
            l_mainAccount = 
                l_accountManager.getMainAccount(l_institution, l_branch, l_strAccountCode);
            // 1.5) getSubAccount(補助口座タイプ : SubAccountTypeEnum) --- 補助口座オブジェクトを取得する。 
            // [引数] 
            // 補助口座タイプ： 1（預り金口座） 
            l_subAccount = 
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("証券会社インスタンスを生成する", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        // 1.6) get商品ID(Institution)  --- 入出金用の商品IDを取得する。
        // [引数] 
        // 証券会社： 証券会社オブジェクト 
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)GtlUtils.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
        long l_lngProductId = l_aioOrderManager.getProductId(l_institution);
        // 受信電文データ.get("ComOndebiCaptureDate")の戻り値がnullの場合
//        Date l_datTransferDate = (Date)l_receiptTelegramData.get("ComOndebiCaptureDate");
        Object l_objComOndebiCaptureDateFromTelegram = 
            l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiCaptureDate);
        Date l_datComOndebiCaptureDate = null;
        if (l_objComOndebiCaptureDateFromTelegram instanceof Date)
        {
            l_datComOndebiCaptureDate = (Date)l_objComOndebiCaptureDateFromTelegram;
        }
        else if (l_objComOndebiCaptureDateFromTelegram instanceof String)
        {
            String l_strComOndebiCaptureDate = (String)l_objComOndebiCaptureDateFromTelegram;
//            l_datComOndebiCaptureDate = WEB3DateUtility.getDate(l_strComOndebiCaptureDate, "yyyyMMddHHmmss");
            if (l_strComOndebiCaptureDate.length() >= 8)
            {
                l_datComOndebiCaptureDate = 
                    WEB3DateUtility.getDate(l_strComOndebiCaptureDate.substring(0, 8), "yyyyMMdd");
            }
        }
        // test log
        if ( l_datComOndebiCaptureDate == null)
        {
            // 決済機@関ID
            String l_strPaySchemeId = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId);
            // 1.7)  get振込日(String, String, String)
            // ［引数］ 
            // 証券会社コード： 受信電文データ.get("cpy")の戻り値 
            // 部店コード： 受信電文データ.get("btn")の戻り値 
            // 決済機@関ID： 受信電文データ.get("paySchemeId")の戻り値 
            l_datComOndebiCaptureDate = this.getTransferDate(l_strInstitutionCode, l_strBranchCode, l_strPaySchemeId);
            // test log
        }
        Object l_objAmount = l_receiptTelegramData.get(WEB3AioTelegramFormatDef.amount);
        if (l_objAmount == null || !WEB3StringTypeUtility.isNumber((String)l_objAmount))
        {
            log.debug("金額： 受信電文データ.get(\"amount\")の戻り値 is NOT NUMBER!!! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + l_strMethodName);
        }
        double l_dblAmount = Double.parseDouble((String)l_objAmount);
        // 1.8) 入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, long, double, String, Date, String, long)
        // --- 入金注文内容インスタンスを生成する。
        //[引数] 
        // 代理入力者： null 
        // 注文種別： 1002（入金注文） 
        // 振替タイプ： 1（入金） 
        // 商品ID： get商品ID()の戻り値 
        // 金額： 受信電文データ.get("amount")の戻り値 
        // 記述： null 
        // 振替予定日： 受信電文データ.get("ComOndebiCaptureDate")の戻り値 
        // 戻り値がnullの場合は、マルチバンク電文処理サービス.get振込日()の戻り値 
        // 決済機@関ID： 受信電文データ.get("paySchemeId")の戻り値 
        // 注文ID： null 
        WEB3AioNewOrderSpec l_orderSpec = 
            new WEB3AioNewOrderSpec(
                    null,
                    OrderTypeEnum.CASH_IN,
                    AssetTransferTypeEnum.CASH_IN,
                    l_lngProductId,
                    l_dblAmount,
                    null,
                    l_datComOndebiCaptureDate,
                    (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.acc),
                    null);
        //1.9) createNewOrderId( ) --- 新規注文IDを取得する。
        long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();
        //1.10)  get受渡日(Date, Date) -- 
        // ［引数］ 
        //振込予定日： 入出金注文内容.振替予定日 
        //発注日： get発注日()の戻り値 
        Date l_datDeliveryDate = 
            this.getDeliveryDate(l_orderSpec.getEstimatedTransferDate(), l_dteOrderBizDate);

        // is信用口座開設(弁済区分 : String)
        boolean l_blnIsMarginAccountEstablished =
            ((WEB3GentradeMainAccount)l_mainAccount).isMarginAccountEstablished(
            WEB3GentradeRepaymentDivDef.DEFAULT);

        log.debug("is信用口座開設の返却値 = " + l_blnIsMarginAccountEstablished);

        if (l_blnIsMarginAccountEstablished)
        {
            // 顧客が信用口座を開設している（is信用口座開設()==TRUE）場合
            WEB3MarginTransferService l_service =
                (WEB3MarginTransferService)Services.getService(
                WEB3MarginTransferService.class);

            //submit保証金振替(顧客, Date, double, String, Trader)
            l_service.submitMarginTransfer(
                (WEB3GentradeMainAccount)l_mainAccount,
                l_datDeliveryDate,
                l_dblAmount,
                (new WEB3Crypt()).decrypt(l_mainAccount.getTradingPassword()),
                null);
        }

        //1.11) 入出金注文更新インタセプタ(入出金注文内容)
        WEB3AioCashTransOrderUpdateInterceptor l_orderUpdateInterceptor = 
            new WEB3AioCashTransOrderUpdateInterceptor(l_orderSpec);
        // 1.12) プロパティセット
        // インタセプタ.受渡日 =  get受渡日の戻り値
        l_orderUpdateInterceptor.setDeliveryDate(l_datDeliveryDate);
        // インタセプタ.comデビット決済取引番号 = 受信電文データ.get("centerPayId")の戻り値
        l_orderUpdateInterceptor.setComDebitNumber(
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId));
        // インタセプタ.保証金区分 = " " ブランク
        l_orderUpdateInterceptor.setGuaranteeDiv(" ");
        // インタセプタ.発注日 =  get受渡日の戻り値
        l_orderUpdateInterceptor.setBizDate(l_datDeliveryDate);
        // インタセプタ.識別コード = 受信電文データ.get("req")の戻り値
        l_orderUpdateInterceptor.setOrderRequestNumber(
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.req));
       // インタセプタ.注文経路区分 = 受信電文データ.get("rdiv")の戻り値
        l_orderUpdateInterceptor.setOrderRootDiv(
            (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv));
        
        // 1.13) setThreadLocalPersistenceEventInterceptor(入出金注文更新インタセプタ :
        // AioOrderManagerPersistenceEventInterceptor)
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_orderUpdateInterceptor);

        // 1.14) submitNewOrder(補助口座 : SubAccount, 商品タイプ : ProductTypeEnum,
        // 入出金注文内容 : NewOrderSpec, 注文ID : long, パスワード : String, isSkip発注審査 : boolean)
        // [引数]
        // 補助口座：　@補助口座オブジェクト
        // 商品タイプ：　@5（現金）
        // 入出金注文内容：　@入出金注文内容オブジェクト
        // 注文ＩＤ：　@createNewOrderId()の戻り値
        // パスワード：　@顧客.getTradingPassword()の戻り値をWEB3Cｒｙｐｔ.decrypt()で復号したもの
        // isSkip発注審査：　@true
        OrderSubmissionResult l_submitResult =
            l_aioOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_orderSpec,
                l_lngNewOrderId,
                (new WEB3Crypt()).decrypt( l_mainAccount.getTradingPassword()),
                true);
        if (l_submitResult.getProcessingResult().isFailedResult())
        {
            log.debug("submitNewOrder Error" + l_submitResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                    l_submitResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + l_strMethodName);
        }

        // 1.15) 余力再計算(補助口座 ： 補助口座)
        // [引数]
        // 補助口座：　@補助口座オブジェクト
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

        l_service.reCalcTradingPower(l_gentradeSubAccount);

        //1.16.HashMap( )
        HashMap l_mapReturn = new HashMap();
        
        // 1.17. put(キー : Object, 値 : Object)
        // 受渡日を登録する。
        // [引数] 
        //  キー： ”受渡日” 
        //  値： get受渡日()の戻り値
        l_mapReturn.put(this.DELIVER_DATE, l_datDeliveryDate);
        
        // 1.18. put(キー : Object, 値 : Object)
        // 振込日を登録する。 
        // [引数] 
        // キー： ”振込日” 
        // 値： get振込日()の戻り値
        l_mapReturn.put(this.TRANSFER_DATE, l_datComOndebiCaptureDate);
        
        log.exiting(l_strMethodName);
        // 1.19. このHashMapを返却し
        return l_mapReturn;
    }
    
    /**
     * (get受渡日)<BR>
     * 受渡日を取得する。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、現在日時を取得する。<BR>
     * 　@    ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     *       ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * ２）　@当日の営業日区分判定。<BR>
     *  　@取得した現在日時の曜日を取得し、土曜日または日曜日の<BR>
     * 場合は営業日区分を”非営業日”とする。<BR>
     *  　@以外の場合、カレンダテーブルを現在日時の日付部分で検索し、<BR>
     * 行の営業日区分を取得する。<BR>
     *    行が取得できなかった場合は、営業日区分を”通常営業日”とする。<BR>
     * <BR>
     * ３）受渡日の算出<BR>
     * <BR>
     * ３－１）引数.振込予定日 <= 引数.発注日 の場合<BR>
     * <BR>
     *    受渡日として、引数.発注日を返却する。<BR>
     * <BR>
     * ２）引数.振込予定日 > 引数.発注日 の場合<BR>
     * <BR>
     *    受渡日として、引数.振込予定日を返却する。<BR>
     *    ※引数.振込予定日が非営業日の場合は、その翌営業日を返却する。<BR>
     * @@param l_datEstTransferDate - (振込予定日)
     * @@param l_datBizDate - (発注日)
     * @@return Date
     * @@roseuid 41255F8C0033
     */
    protected Date getDeliveryDate(Date l_datEstTransferDate, Date l_datBizDate)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getDeliveryDate(Date l_datEstTransferDate, "
            + "Date l_datBizDate)";
        log.entering(l_strMethodName);
//        //  １）　@ThreadLocalSystemAttributesRegistryより、現在日時を取得する。 
//        // 　@ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp") 
//        Timestamp l_dteCurrentDate = GtlUtils.getSystemTimestamp();
//        // ２）　@当日の営業日区分判定。 
//        // 　@取得した現在日時の曜日を取得し、土曜日または日曜日の場合は営業日区分を”非営業日”とする。 
//        // 　@以外の場合、カレンダテーブルを現在日時の日付部分で検索し、行の営業日区分を取得する。 
//        // 行が取得できなかった場合は、営業日区分を”通常営業日”とする。 
//        WEB3GentradeTradingTimeManagement.getBizDateType(l_dteCurrentDate);
        // test log
        log.debug("get受渡日#振込予定日l_datEstTransferDate = " + l_datEstTransferDate);
        log.debug("get受渡日#l_datBizDate = " + l_datBizDate);
        // ３）受渡日の算出 
        // ３－１）引数.振込予定日 <= 引数.発注日 の場合 
        if(WEB3DateUtility.compareToDay(l_datEstTransferDate, l_datBizDate) <= 0)
        {
            // 受渡日として、引数.発注日を返却する。 
            log.exiting(l_strMethodName);
            return l_datBizDate;
        }
        // ２）引数.振込予定日 > 引数.発注日 の場合 
        else
        {
            // 受渡日として、引数.振込予定日を返却する。 
            // ※引数.振込予定日が非営業日の場合は、その翌営業日を返却する。 
            String l_strEstTransferDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(new Timestamp(l_datEstTransferDate.getTime()));
            // test log
            log.debug("引数.振込予定日 > 引数.発注日 の場合....");
            log.debug("引数.振込予定日の営業日区分l_strEstTransferDateType = " + l_strEstTransferDateType);

            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strEstTransferDateType))
            {
                Date l_returnDate = new WEB3GentradeBizDate(new Timestamp(l_datEstTransferDate.getTime())).roll(1);
                log.exiting(l_strMethodName);
                return l_returnDate;
            }
            else
            {
                return l_datEstTransferDate;
            }
        }
    }
    
    /**
     * (get振込日)<BR>
     * 振込日を取得する。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、現在日時を取得する。<BR>
     *      ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     *      ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * ２）　@当日の営業日区分判定。<BR>
     *  　@取得した現在日時の曜日を取得し、土曜日または日曜日の<BR>
     * 場合は営業日区分を”非営業日”とする。<BR>
     *  　@以外の場合、カレンダテーブルを現在日時の日付部分で検索し、<BR>
     * 行の営業日区分を取得する。<BR>
     *    行が取得できなかった場合は、営業日区分を”通常営業日”とする。<BR>
     * <BR>
     * ３）振込日の算出<BR>
     * <BR>
     * ３－１）現在日時が”非営業日”の場合<BR>
     * <BR>
     *    現在日時の翌営業日を返却する。<BR>
     * <BR>
     * ３－２）現在日時が”通常営業日”の場合<BR>
     * <BR>
     * ３－２－１）会社別決済機@関条件インスタンスを生成する。<BR>
     * <BR>
     *    ［コンストラクタへの引数］<BR>
     *    証券会社コード： 引数.証券会社コード<BR>
     *    部店コード： 引数.部店コード<BR>
     *    決済機@関ID： 引数.決済機@関ID<BR>
     * <BR>
     * ３－２－２）締切時間を取得する。<BR>
     * <BR>
     *    会社別決済機@関条件.get締切時間()<BR>
     * <BR>
     * ３－２－３）現在日時の時間部分 < 締切時間 の場合<BR>
     * <BR>
     *    現在日時の日付部分を返却する。<BR>
     * <BR>
     * ３－２－４）現在日時の時間部分 >= 締切時間 の場合<BR>
     * <BR>
     *    現在日時の翌営業日を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strPaySchemeId - (決済機@関ID)<BR>
     * @@return Date
     * @@roseuid 4125E8D20026
     */
    protected Date getTransferDate(String l_strInstitutionCode, String l_strBranchCode, String l_strPaySchemeId)
        throws WEB3BaseException
    {
        final String l_strMethodName = "getTransferDate(String l_strInstitutionCode, "
            + "String l_strBranchCode, String l_strPaySchemeId)";
        log.entering(l_strMethodName);
        // １）　@ThreadLocalSystemAttributesRegistryより、現在日時を取得する。 
        // 　@ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp") 
        Timestamp l_dteCurrentDate = GtlUtils.getSystemTimestamp();
        // ２）　@当日の営業日区分判定。 
        // 　@取得した現在日時の曜日を取得し、土曜日または日曜日の場合は営業日区分を”非営業日”とする。 
        // 　@以外の場合、カレンダテーブルを現在日時の日付部分で検索し、行の営業日区分を取得する。 
        // 行が取得できなかった場合は、営業日区分を”通常営業日”とする。 
        String l_strCurrentBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_dteCurrentDate);
        
        // test log
        log.debug("当日: " + l_dteCurrentDate);
        log.debug("当日の営業日区分l_strCurrentBizDateType = " + l_strCurrentBizDateType);

        // ３）振込日の算出 
        // ３－１）現在日時が”非営業日”の場合 
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strCurrentBizDateType))
        {
            //// 現在日時の翌営業日を返却する。 
            Date l_dteTransferDate = 
                new WEB3GentradeBizDate(new Timestamp(l_dteCurrentDate.getTime())).roll(1);
            log.exiting(l_strMethodName);
            return l_dteTransferDate;
        }
        else
        {
            // ３－２）現在日時が”通常営業日”の場合 
            // ３－２－１）会社別決済機@関条件インスタンスを生成する。 
            // ［コンストラクタへの引数］ 
            // 証券会社コード： 引数.証券会社コード 
            // 部店コード： 引数.部店コード 
            // 決済機@関ID： 引数.決済機@関ID 
            WEB3AioCompanySettleInstitutionConditions l_companyConditions = 
                new WEB3AioCompanySettleInstitutionConditions(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strPaySchemeId);
            // ３－２－２）締切時間を取得する。 
            // 会社別決済機@関条件.get締切時間() ---- (HHMMSS)
            String l_strLimitTime = l_companyConditions.getLimitTime();
            // test log
            log.debug("会社別決済機@関条件.get締切時間()l_strLimitTime = " + l_strLimitTime);
            String l_strCurrentDate = 
                WEB3DateUtility.formatDate(l_dteCurrentDate, "HHmmss");
            // ３－２－３）現在日時の時間部分 < 締切時間 の場合 
            if (l_strCurrentDate.compareTo(l_strLimitTime) < 0)
            {
                // 現在日時の日付部分を返却する。
                log.exiting(l_strMethodName);
                return l_dteCurrentDate;
            }
            // ３－２－４）現在日時の時間部分 >= 締切時間 の場合 
            else
            {
                // 現在日時の翌営業日を返却する。 
                Date l_dteTransferDate = 
                    new WEB3GentradeBizDate(new Timestamp(l_dteCurrentDate.getTime())).roll(1);
                log.exiting(l_strMethodName);
                return l_dteTransferDate;
            }
        }
    }
}@
