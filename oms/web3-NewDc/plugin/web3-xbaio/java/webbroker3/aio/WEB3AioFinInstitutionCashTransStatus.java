head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioFinInstitutionCashTransStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金融機@関連携入出金状況クラス(WEB3AioFinInstitutionCashTransStatus)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 屈陽 (中訊) 新規作成
                   2004/10/22 韋念瓊 (中訊) レビュー 
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.aio.data.BankCashTransferStatusDao;
import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;


/**
 * (金融機@関連携入出金状況)<BR>
 * 金融機@関連携入出金状況クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0 
 */
public class WEB3AioFinInstitutionCashTransStatus implements BusinessObject 
{
    /**
    * ログユーティリティ
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioFinInstitutionCashTransStatus.class);
        
    /**
     * (金融機@関連携入出金状況Row)<BR>
     * 金融機@関連携入出金状況行オブジェクト
     */
    private BankCashTransferStatusParams bankCashTransferStatusParams;
    
    /**
     * (金融機@関連携入出金状況)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数をキーとして、金融機@関連携入出金状況テーブルの<BR>
     * レコードを取得しプロパティにセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strOrderRequestNumber - (識別コード)
     * @@return WEB3AioFinInstitutionCashTransStatus
     * @@throws WEB3BaseException
     * @@roseuid 40F7BB060399
     */
    public WEB3AioFinInstitutionCashTransStatus(           
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strOrderRequestNumber)
        throws WEB3BaseException
    {
        String l_strMethodName = "WEB3AioFinInstitutionCashTransStatus("
            + "String l_strInstitutionCode," 
            + "String l_strBranchCode," 
            + "String l_strOrderRequestNumber)";
        log.entering(l_strMethodName);
        
        try
        {
            // 引数をキーとして、金融機@関連携入出金状況テーブルのレコードを取得する
            BankCashTransferStatusRow l_row = 
                BankCashTransferStatusDao.findRowByInstitutionCodeBranchCodeOrderRequestNumber(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strOrderRequestNumber);
            if (l_row == null)
            {
                log.debug("金融機@関連携入出金状況テーブルのレコードを取得しない");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName);
            }
            bankCashTransferStatusParams = new BankCashTransferStatusParams(l_row);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理FLAG（注文）)<BR>
     * 処理FLAG（注文）をセットする。
     * @@param l_strFlag - (処理FLAG)
     * @@roseuid 40E8D4E50183
     */
    public void setOrderStatusFlag(String l_strFlag) 
    {
        String l_strMethodName = "setOrderStatusFlag(String l_strFlag)";
        log.entering(l_strMethodName);
        
        //処理FLAG（注文）をセットする
        bankCashTransferStatusParams.setOrderStatusFlag(l_strFlag);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理時間（注文要求）)<BR>
     * 処理時間（注文要求）をセットする。
     * @@param l_datTransactionTime - (処理時間)
     * @@roseuid 40E8D6C60057
     */
    public void setOrderRequestTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setOrderRequestTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        // 処理時間（注文要求）をセットする
        bankCashTransferStatusParams.setOrderRequestTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理時間（注文応答）)<BR>
     * 処理時間（注文応答）をセットする。
     * @@param l_datTransactionTime - (処理時間)
     * @@roseuid 40E8D6D60289
     */
    public void setOrderResponseTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setOrderResponseTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //処理時間（注文応答）をセットする
        bankCashTransferStatusParams.setOrderResponseTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理FLAG（決済開始）)<BR>
     * 処理FLAG（決済開始）をセットする。
     * @@param l_strFlag - (処理FLAG)
     * @@roseuid 40E8D6E403D0
     */
    public void setStartStatusFlg(String l_strFlag) 
    {
        String l_strMethodName = "setStartStatusFlg(String l_strFlag)";
        log.entering(l_strMethodName);
        //処理FLAG（決済開始）をセットする      
        bankCashTransferStatusParams.setStartStatusFlag(l_strFlag);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理時間（決済開始要求）)<BR>
     * 処理時間（決済開始要求）をセットする。
     * @@param l_datTransactionTime - (処理時間)
     * @@roseuid 40E8D6FF03CF
     */
    public void setStartRequestTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setStartRequestTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //処理時間（決済開始要求）をセットする        
        bankCashTransferStatusParams.setStartRequestTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理時間（決済開始応答）)<BR>
     * 処理時間（決済開始応答）をセットする。
     * @@param l_datTransactionTime - (処理時間)
     * @@roseuid 40E8D710012F
     */
    public void setStartResponseTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setStartResponseTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //処理時間（決済開始応答）をセットする
        bankCashTransferStatusParams.setStartResponseTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理FLAG（決済結果）)<BR>
     * 処理FLAG（決済結果）をセットする。
     * @@param l_strFlag - (処理FLAG)
     * @@roseuid 40E8D71F0082
     */
    public void setResultStatusFlag(String l_strFlag) 
    {
        String l_strMethodName = "setResultStatusFlag(String l_strFlag)";
        log.entering(l_strMethodName);
        //処理FLAG（決済結果）をセットする
        bankCashTransferStatusParams.setResultStatusFlag(l_strFlag);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理時間（決済結果通知）)<BR>
     * 処理時間（決済結果通知）をセットする。
     * @@param l_datTransactionTime - (処理時間)
     * @@roseuid 40E8D72B02B4
     */
    public void setResultRequestTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setResultRequestTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //処理時間（決済結果通知）をセットする           
        bankCashTransferStatusParams.setResultRequestTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理時間（決済結果応答）)<BR>
     * 処理時間（決済結果応答）をセットする。
     * @@param l_datTransactionTime - (処理時間)
     * @@roseuid 40E8D7500236
     */
    public void setResultResponseTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setResultResponseTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //処理時間（決済結果応答）をセットする
        bankCashTransferStatusParams.setResultResponseTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set処理区分)<BR>
     * 処理区分をセットする。
     * @@param l_strTransactionTime - (処理区分)
     * @@roseuid 4118ACC80179
     */
    public void setStatus(String l_strTransactionTime) 
    {
        String l_strMethodName = "setStatus(String l_strTransactionTime)";
        log.entering(l_strMethodName);
        //処理区分をセットする
        bankCashTransferStatusParams.setTransactionStatus(l_strTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (処理時間)<BR>
     * 処理時間をセットする。
     * @@param l_datTransactionTime - (処理時間)
     * @@roseuid 4119AF7502D0
     */
    public void setTransactionTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setTransactionTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //処理時間をセットする
        bankCashTransferStatusParams.setTransactionTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (.comデビット決済取引番号)<BR>
     * .comデビット決済取引番号をセットする。
     * @@param l_strTradeNumber - (取引番号)<BR>
     * .comデビット決済取引番号
     * @@roseuid 4118B25C025B
     */
    public void setComDebitNumber(String l_strTradeNumber) 
    {
        String l_strMethodName = "setComDebitNumber(String l_strTradeNumber)";
        log.entering(l_strMethodName);
        //.comデビット決済取引番号をセットする
        bankCashTransferStatusParams.setCenterPayId(l_strTradeNumber);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set最終更新日時)<BR>
     * 最終更新日時をセットする。
     * @@param l_datTransactionTime - (処理時間)
     * @@roseuid 4119BA3C0040
     */
    public void setLastUpdateTimestamp(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setLastUpdateTimestamp(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //最終更新日時をセットする
        bankCashTransferStatusParams.setLastUpdateTimestamp(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * this.金融機@関連携入出金状況Rowを返却する。
     * @@return Object
     * @@roseuid 4118B4FA03B3
     */
    public Object getDataSourceObject() 
    {
        //this.金融機@関連携入出金状況Rowを返却する
        return bankCashTransferStatusParams;
    }
    
    /**
     * this.金融機@関連携入出金状況Rowをコピーして、<BR>
     * 同じ内容の別インスタンスを作成する（clone）。 <BR>
     * 作成したコピーを自身のthis.金融機@関連携入出金状況Rowにセットする。
     * @@roseuid 4133F9CF0279
     */
    public void createForUpdateParams() 
    {
        String l_strMethodName = "createForUpdateParams()";
        log.entering(l_strMethodName);
        //this.金融機@関連携入出金状況Rowをコピーして
        BankCashTransferStatusParams l_params = new BankCashTransferStatusParams(this.bankCashTransferStatusParams);
        //作成したコピーを自身のthis.金融機@関連携入出金状況Rowにセットする
        this.bankCashTransferStatusParams = l_params;
        
        log.exiting(l_strMethodName);
    }

}
@
