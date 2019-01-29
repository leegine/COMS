head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationUnitTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携トランザクションコールバック(WEB3AioCashinCooperationUnitTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/12 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.BankDepositNotifyRow;
import webbroker3.aio.define.WEB3AioBankDepositNotifyCashTransferDivDef;
import webbroker3.aio.define.WEB3AioBankDepositNotifyTradeDivDef;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationNotifyUnitService;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.util.WEB3LogUtility;

/**
 * （入金連携トランザクションコールバック）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 韋念瓊
 * @@version 1.0
 */
public class WEB3AioCashinCooperationUnitTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationUnitTransactionCallback.class);
    
    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3AioCashinCooperationUnitTransactionCallback()
    {
        
    }

   /**
     * 入金通知処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(入金連携通知非同期サービスImpl)run()」 参照 <BR>
     * <BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        //1.1.1.1.1 銀行入金通知テーブルから
        //以下の条件で検索。

        //処理区分 = 未処理
        String l_strWhere = " status = ? and cash_transfer_div = ? and trade_div = ? ";

        Object[] l_bindVars = {
                WEB3StatusDef.NOT_DEAL, 
                WEB3AioBankDepositNotifyCashTransferDivDef.CASH_IN,
                WEB3AioBankDepositNotifyTradeDivDef.PAY_BY_TRANSFER,
                };
        
        List l_lisRows = 
            Processors.getDefaultProcessor().doFindAllQuery(
                BankDepositNotifyRow.TYPE, 
                l_strWhere, 
                null, 
                l_bindVars);        
        
        int l_intResultSize = 0;

        if (l_lisRows != null && l_lisRows.size() != 0)
        {
            l_intResultSize = l_lisRows.size();
            log.debug("l_intResultSize = " + l_intResultSize);
        }
        
        //1.1.1.1.2 検索された入金通知レコード件数分LOOP        

        for (int i = 0; i < l_intResultSize; i++)
        {
           
            BankDepositNotifyRow l_bankDepositNotifyRow = 
                (BankDepositNotifyRow) l_lisRows.get(i);
            
            BankDepositNotifyParams l_bankDepositNotifyParams = 
                new BankDepositNotifyParams(l_bankDepositNotifyRow);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
            WEB3GentradeMainAccount l_mainAccount = null;
            
            try
            {
                //1.1.1.1.2.1
                //入金通知レコード.部店コード != null　@かつ 
                //入金通知レコード.顧客コード != null の場合
                if (l_bankDepositNotifyParams.getBranchCode() != null &&
                    l_bankDepositNotifyParams.getAccountCode() != null)
                {
                    //1.1.1.1.2.1.1 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String) 
                    //顧客オブジェクトを取得し返却。
                    //引数：
                    //証券会社コード = 入金通知レコード.証券会社コード
                    //部店コード = 入金通知レコード.部店コード
                    //顧客コード = 入金通知レコード.顧客コード
                    
                    l_mainAccount = l_accountManager.getMainAccount(
                        l_bankDepositNotifyParams.getInstitutionCode(),
                        l_bankDepositNotifyParams.getBranchCode(),
                        l_bankDepositNotifyParams.getAccountCode());            
                }
                //1.1.1.1.2.2 (1.1.1.2.1以外)
                else
                {                
                    //1.1.1.1.2.2.1
					//専用振込先口座テーブルを以下の条件で検索。
					//証券会社コード=入金通知レコード.証券会社コード
					//銀行コード = 入金通知レコード.銀行コード
					//支店コード = 入金通知レコード.銀行支店コード
					//口座番号 = 入金通知レコード.口座番号                   
                    StringBuffer l_strBfWhere = new StringBuffer();
                    l_strBfWhere.append(" institution_code = ? ");
                    l_strBfWhere.append(" and fin_institution_code = ? ");
                    l_strBfWhere.append(" and fin_branch_code = ? ");
                    l_strBfWhere.append(" and fin_account_no = ? ");
                            
                    Object[] l_objWhereValues = {                
                        l_bankDepositNotifyRow.getInstitutionCode(), 
                        l_bankDepositNotifyRow.getBankCode(), 
                        l_bankDepositNotifyRow.getBankBranchCode(), 
                        l_bankDepositNotifyRow.getDepositDataTransPersonCode()};               
                    
    
                    List l_lisExclusiveUseAccountRows =
                        l_queryProcessor.doFindAllQuery(
                                ExclusiveUseAccountRow.TYPE,
                            l_strBfWhere.toString(),
                            null,
                            l_objWhereValues);

                    ////1.1.1.1.2.2.2
                    //[検索結果リスト== nullあるいは検索結果件数 < 1の場合]
                     //"該当データなし"エラー例外をスローする。
                    
                    if((l_lisExclusiveUseAccountRows == null) ||
                            (l_lisExclusiveUseAccountRows.size() < 1))
                    {
                        log.debug("テーブルに該当するデータがありません。（専用振込先口座）");
                        throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "テーブルに該当するデータがありません。（専用振込先口座）");
                        
                    }
                    
                    ////1.1.1.1.2.3
                    //[検索結果件数 > 1の場合]
                    //"該当データ重複。"エラー例外をスローする。                    
                    if (l_lisExclusiveUseAccountRows.size() > 1)
                    {
                        log.debug("テーブルに重複する該当データが存在します。（専用振込先口座）");
                        throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "テーブルに重複する該当データが存在します。（専用振込先口座）");
                    }
                    
                    ExclusiveUseAccountRow l_exclusiveUseAccountRow = 
                        (ExclusiveUseAccountRow) l_lisExclusiveUseAccountRows.get(0);                
                
                    //1.1.1.1.2.2.3 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                    //顧客オブジェクトを取得し返却。
                    //引数：
                    //証券会社コード = 振込先金融機@関テーブル.証券会社コード
                    //部店コード = 振込先金融機@関テーブル.部店コード
                    //顧客コード = 振込先金融機@関テーブル.顧客コード
                    l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                            l_exclusiveUseAccountRow.getAccountId());
                    
                }
                
                //1.1.1.1.2.3
                //notify入金連携(入金通知Params, 顧客)            
                WEB3AioCashinCooperationNotifyUnitService l_notifyUnitService = 
                    (WEB3AioCashinCooperationNotifyUnitService)Services.getService(
                        WEB3AioCashinCooperationNotifyUnitService.class);
                
                l_notifyUnitService.notifyCashinCooperation(
                    l_bankDepositNotifyParams, 
                    l_mainAccount);
                
            }
            catch (Exception l_ex)
            {
                log.debug("入金通知１件処理を例外発生時................... ", l_ex);
                //1.1.1.1.2.4 例外発生時
	            //入金連携通知1件異常時トランザクションコールバックを作成し
	            //新規トランザクションで
	            //当該入金通知レコードを更新、
	            //入金通知処理エラー履歴テーブルに1件追加する。
	            //[更新内容]
	            //処理区分 = "エラー"
	            //エラーコメント = 例外.例外メッセージ
	            //1.1.1.1.2.4.1 入金連携通知1件異常時トランザクションコールバック(入金通知Params, Exception)
	            WEB3AioCashinCooperationNotifyUnitExpTransactionCallback l_expTransactionCallback = 
	                new WEB3AioCashinCooperationNotifyUnitExpTransactionCallback(
	                    l_bankDepositNotifyParams, 
	                    l_ex);
	            //1.1.1.1.2.4.2 doTransaction(arg0 : int, arg1 : TransactionCallback)               
	            l_queryProcessor.doTransaction(l_expTransactionCallback);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
}




@
