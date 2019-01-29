head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyUnitExpTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携通知1件異常時トランザクションコールバック (WEB3AioCashinCooperationNotifyUnitExpTransactionCallback)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/16 黄建 (中訊) 新規作成
                 : 2006/11/14 徐宏偉 (中訊)  ＤＢ更新仕様　@No.131
                 : 2006/11/14 徐宏偉 (中訊)  ＤＢ更新仕様　@No.132
*/

package webbroker3.aio.service.delegate.stdimpls;

import webbroker3.aio.data.BankDepositErrorHistoryDao;
import webbroker3.aio.data.BankDepositErrorHistoryParams;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.common.WEB3Exception;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3UpdatePersonDef;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * 入金連携通知1件異常時トランザクションコールバック <BR>
 * 
 * @@author 黄建
 * @@version 1.0
 */

public class WEB3AioCashinCooperationNotifyUnitExpTransactionCallback 
	implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashinCooperationNotifyUnitExpTransactionCallback.class);
    
    /**
     * 入金通知Params<BR>
     */
    private BankDepositNotifyParams bankDepositNotifyParams;
    
    /**
     * 例外<BR>
     */
    private Exception exp;
    
    /**
     * コンストラクタ。  <BR>
     * 引数.入金通知Paramsをthis.入金通知Paramsにセット。  <BR>
     * 引数.例外をthis.例外にセット。 <BR>
     *  <BR>
     * @@param l_bankDepositNotifyParams - (入金通知Params)
     * @@param l_exp - (例外)
     */
    public WEB3AioCashinCooperationNotifyUnitExpTransactionCallback(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        Exception l_exp)
    {
        //引数.入金通知Paramsをthis.入金通知Paramsにセット。
        this.bankDepositNotifyParams = l_bankDepositNotifyParams;
        //引数.例外をthis.例外にセット。
        this.exp = l_exp;
    }

   /**
     * 入金通知Paramsを更新、<BR>
     * 入金通知処理エラー履歴テーブルに1件追加する。<BR> 
     * <BR>
     * [更新内容] <BR>
     * <BR>
     * 処理区分 = "エラー" <BR>
     * エラーコメント = 例外.例外メッセージ <BR>
     * <BR>
     * <DB更新仕様参照> 
     * 銀行入金通知_入金通知テーブル.xls <BR>
     * 銀行入金通知_入金通知処理エラー履歴テーブル.xls <BR>
     * <BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);
        
        //入金通知Paramsを更新、
        int l_intLastErrorHistorySerialNo = 
            this.bankDepositNotifyParams.getLastErrorHistorySerialNo() + 1;

        //1) 処理区分 = "エラー" 
        this.bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
        //2) エラーコメント = 例外.例外メッセージ 
        if (this.exp instanceof WEB3Exception)
        {
            WEB3Exception l_web3Exception = (WEB3Exception)this.exp;
            this.bankDepositNotifyParams.setDepositErrorComment(l_web3Exception.getErrorMessage());
        }
        else
        {
            this.bankDepositNotifyParams.setDepositErrorComment(this.exp.getMessage()); 
        }

        //3) エラー履歴最終通番
        this.bankDepositNotifyParams.setLastErrorHistorySerialNo(l_intLastErrorHistorySerialNo);
        //4) 更新担当者
        this.bankDepositNotifyParams.setUpdatePerson(WEB3UpdatePersonDef.SYSTEM);
        //5) 更新日付
        this.bankDepositNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        WEB3DataAccessUtility.updateRow(this.bankDepositNotifyParams);
        
        //入金通知処理エラー履歴テーブルに1件追加する。
        //銀行入金通知処理エラー履歴Params
        BankDepositErrorHistoryParams l_bankDepositErrorHistoryParams = 
            new BankDepositErrorHistoryParams();
        //1)銀行入金通知処理エラー履歴テーブルID
        l_bankDepositErrorHistoryParams.setBankDepositErrorHistoryId(
            BankDepositErrorHistoryDao.newPkValue());
        //2)銀行入金通知テーブルID
        l_bankDepositErrorHistoryParams.setBankDepositNotifyId(
            this.bankDepositNotifyParams.getBankDepositNotifyId());
        //3)履歴番号
        l_bankDepositErrorHistoryParams.setSerialNo(
            this.bankDepositNotifyParams.getLastErrorHistorySerialNo());
        //4)入金エラーコメント
        l_bankDepositErrorHistoryParams.setDepositErrorComment(
            this.bankDepositNotifyParams.getDepositErrorComment());
        //5)備考
        l_bankDepositErrorHistoryParams.setRemark(
            this.bankDepositNotifyParams.getRemark());
        //6)更新担当者
        l_bankDepositErrorHistoryParams.setUpdatePerson(WEB3UpdatePersonDef.SYSTEM);
        //7)作成日付
        l_bankDepositErrorHistoryParams.setCreatedTimestamp(
            GtlUtils.getSystemTimestamp());
        //8)更新日付
        l_bankDepositErrorHistoryParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());
        //9)証券会社コード = 銀行入金通知テーブル.証券会社コード
        l_bankDepositErrorHistoryParams.setInstitutionCode(
            this.bankDepositNotifyParams.getInstitutionCode());
        //10)データ取込区分 = 銀行入金通知テーブル.データ取込区分
        l_bankDepositErrorHistoryParams.setDataLoadDiv(
            this.bankDepositNotifyParams.getDataLoadDiv());
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        l_processor.doInsertQuery(l_bankDepositErrorHistoryParams);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}




@
