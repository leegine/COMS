head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecuteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券自動約定サービスImpl (WEB3BondAutoExecuteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 齊珂 (中訊) 新規作成
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.message.WEB3BondAutoExecRequest;
import webbroker3.bd.message.WEB3BondAutoExecResponse;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteService;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券自動約定サービスImpl)<BR>
 * 債券自動約定サービス実装クラス<BR>
 *
 * @@author 齊珂(中訊)
 * @@version 1.0
 */
public class WEB3BondAutoExecuteServiceImpl implements WEB3BondAutoExecuteService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondAutoExecuteServiceImpl.class);

    /**
     * 債券自動約定処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「債券自動約定execute」参照。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (!(l_request instanceof WEB3BondAutoExecRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        Object l_objDoTransaction = null;

        //1.1 validate()
        WEB3BondAutoExecRequest l_bondAutoExecRequest =
            (WEB3BondAutoExecRequest)l_request;
        l_bondAutoExecRequest.validate();

        WEB3BondAutoExecResponse l_response = null;
        try
        {
            //1.2 getDefaultProcessor()
            QueryProcessor l_queryProcessors = Processors.getDefaultProcessor();

            //1.3 オンライン実行結果TransactionCallback(String, long, long)
            //[引数]
            //  証券会社コード：リクエストデータ.証券会社コード
            //  from口座ID：リクエストデータ.from口座ID
            //  to口座ID：リクエストデータ.to口座ID
            WEB3GentradeOnlineRunStatusTransactionCallback l_onlineRunStatusTransactionCallback =
                new WEB3GentradeOnlineRunStatusTransactionCallback(
                    l_bondAutoExecRequest.institutionCode,
                    l_bondAutoExecRequest.fromAccountId,
                    l_bondAutoExecRequest.toAccountId);

            //1.4 doTransaction(arg0 : int, arg1 : TransactionCallback)
            //[引数]
            //  Tx：QueryProcessor.TX_CREATE_NEW
            //  TransactionCallback：オンライン実行結果TransactionCallback
            l_objDoTransaction =
                l_queryProcessors.doTransaction(QueryProcessor.TX_CREATE_NEW,
                    l_onlineRunStatusTransactionCallback);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            //1.5 二重起動の例外がthrowされた場合
            Object l_exception = l_dataCallbackException.getDetails();
            if (l_exception instanceof WEB3BaseException)
            {
                WEB3BaseException l_baseException = (WEB3BaseException)l_exception;
                if (WEB3ErrorCatalog.BUSINESS_ERROR_01992.equals(l_baseException.getErrorInfo()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw l_baseException;
                }
            }
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }

        WEB3GentradeOnlineRunStatus l_onlineRunStatus =
            (WEB3GentradeOnlineRunStatus)l_objDoTransaction;


        //1.6 get処理対象顧客一覧(String, long, long)
        MainAccount[] l_mainAccount = this.getAccountList(
            l_bondAutoExecRequest.institutionCode,
            l_bondAutoExecRequest.fromAccountId,
            l_bondAutoExecRequest.toAccountId);

        //1.7 getService(arg0 : Class)
        //サービス：債券自動約定サービス
        WEB3BondAutoExecuteService l_service =
            (WEB3BondAutoExecuteService)Services.getService(
                WEB3BondAutoExecuteService.class);

        //1.8 取得した顧客オブジェクト数分Loop
        boolean l_blnIsError = false;
        if (l_mainAccount != null)
        {
            for(int i = 0; i < l_mainAccount.length; i++)
            {
                try
                {
                    //1.8.1 exec自動約定For顧客(顧客)
                    l_service.execAutoExecuteForAccount(l_mainAccount[i]);
                }
                catch(WEB3BaseException l_ex)
                {
                    log.debug("__an WEB3BaseException ", l_ex);
                    l_blnIsError = true;
                }
            }
        }

        //1.9 update実行ステータス区分(実行ステータス区分 : String)
        String l_strRunStatusDiv = null;
        if(l_blnIsError)
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }
        else
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;
        }

        l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);

        l_response =
            (WEB3BondAutoExecResponse)l_bondAutoExecRequest.createResponse();


        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get処理対象顧客一覧)<BR>
     * get処理対象顧客一覧 <BR>
     * <BR>
     * １）　@注文単位検索  <BR>
     * 　@拡張債券注文マネージャ.get自動約定対象注文()をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@　@証券会社コード：引数.証券会社コード  <BR>
     * 　@　@　@from口座ID：　@　@引数.from口座ID  <BR>
     * 　@　@　@to口座ID：　@　@　@引数.to口座ID  <BR>
     * <BR>
     * ２）顧客オブジェクト作成  <BR>
     * 　@２－１）１）の検索結果について、ユニークな口座IDの一覧を作成する。 <BR>
     * 　@２－２）２－１）にて作成した口座IDの一覧分、顧客オブジェクトを作成し、<BR>
     * 　@　@配列として返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param  l_lngFromAccountId - (from口座ID)<BR>
     * from口座ID<BR>
     * @@param l_lngToAccountId - (to口座ID)<BR>
     * to口座ID<BR>
     * @@return MainAccount[]
     * @@throws WEB3BaseException
     */
    protected MainAccount[] getAccountList(
        String l_strInstitutionCode,
        long l_lngFromAccountId,
        long l_lngToAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountList(String, long, long)";
        log.entering(STR_METHOD_NAME);

        //１）　@注文単位検索
        //拡張債券株式注文マネージャ.get自動約定対象注文()をコールする。
        //　@[引数]
        //　@　@証券会社コード：引数.証券会社コード
        //　@　@from口座ID：　@　@引数.from口座ID
        //　@　@to口座ID：　@　@　@引数.to口座ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        WEB3BondOrderUnit[] l_bondOrderUnit =
            l_bondOrderManager.getAutoExecuteOrder(
                l_strInstitutionCode,
                l_lngFromAccountId,
                l_lngToAccountId);

        //２）　@顧客オブジェクト作成
        AccountManager l_accountManager = l_finApp.getAccountManager();
        int l_intSize = 0;
        if(l_bondOrderUnit == null)
        {
            return null;
        }
        else
        {
            l_intSize = l_bondOrderUnit.length;
        }
        List l_lisMainAccount = new ArrayList();

        HashSet l_hsAccountId = new HashSet();
        for(int i = 0; i < l_intSize; i++)
        {
            //２－１）１）の検索結果について、ユニークな口座IDの一覧を作成する。
            Long l_lngAccountId = new Long(l_bondOrderUnit[i].getAccountId());

            l_hsAccountId.add(l_lngAccountId);
        }
        try
        {
            Iterator l_iterator = l_hsAccountId.iterator();
            while(l_iterator.hasNext())
            {
                MainAccount l_mainAccount = l_accountManager.getMainAccount(
                    Long.parseLong(l_iterator.next().toString()));
                l_lisMainAccount.add(l_mainAccount);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //  配列として返却する。
        MainAccount[] l_mainAccounts = new MainAccount[l_lisMainAccount.size()];
        l_lisMainAccount.toArray(l_mainAccounts);
        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
    }

    /**
     * (exec自動約定For顧客)<BR>
     * exec自動約定For顧客 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「exec自動約定For顧客」参照。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@throws WEB3BaseException
     */
    public void execAutoExecuteForAccount(MainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execAutoExecuteForAccount(MainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBrachCode = l_mainAccount.getBranch().getBranchCode();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        // 1.1 lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        // 証券会社コード：顧客.get証券会社.get証券会社コード
        // 部店コード：顧客.get部店.get部店コード
        // 口座コード：顧客.get顧客コード
        l_accountManager.lockAccount(
            l_strInstitutionCode,
            l_strBrachCode,
            l_mainAccount.getAccountCode());

        // 1.2 get自動約定対象注文(String, long, long)
        // 証券会社コード：顧客.get証券会社.get証券会社コード
        // from口座ID：顧客.get口座ID
        // to口座ID：顧客.get口座ID
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit[] l_bondOrderUnit =
            l_bondOrderManager.getAutoExecuteOrder(
                l_strInstitutionCode,
                l_mainAccount.getAccountId(),
                l_mainAccount.getAccountId());

        // 1.3 取得した注文オブジェクト数分Loop
        // 債券注文単位：Loop中の注文オブジェクト
        if (l_bondOrderUnit != null)
        {
            for(int i = 0; i < l_bondOrderUnit.length; i++)
            {
                // 1.3.1 notify自動約定(拡張債券注文単位)
                WEB3BondAutoExecuteUnitService l_autoExecuteUnitService =
                    (WEB3BondAutoExecuteUnitService) Services.getService(
                        WEB3BondAutoExecuteUnitService.class);
                l_autoExecuteUnitService.notifyAutoExecute(l_bondOrderUnit[i]);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (オンライン実行結果TransactionCallback)<BR>
     * オンライン実行結果TransactionCallbackクラス<BR>
     */
    public class WEB3GentradeOnlineRunStatusTransactionCallback implements TransactionCallback
    {
        /**
         *(証券会社コード)<BR>
         *証券会社コード<BR>
         */
        private String institutionCode;

        /**
         * (from口座ID)<BR>
         * from口座ID<BR>
         */
        private long fromAccountId;

        /**
         * (to口座ID)<BR>
         * to口座ID<BR>
         */
        private long toAccountId;

        /**
         * (オンライン実行結果TransactionCallback) <BR>
         * コンストラクタ<BR>
         * <BR>
         * 証券会社コード＝引数.証券会社コード <BR>
         * from口座ID＝引数.from口座ID<BR>
         * to口座ID＝引数.to口座ID <BR>
         * <BR>
         * @@param l_strInstitutionCode - (証券会社コード)<BR>
         * 証券会社コード<BR>
         * @@param l_lngFromAccountId - (from口座ID)<BR>
         * from口座ID<BR>
         * @@param l_lngToAccountId - (to口座ID)<BR>
         * to口座ID
         */
        public WEB3GentradeOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngFromAccountId,
            long l_lngToAccountId)
        {
            final String STR_METHOD_NAME =
                "WEB3GentradeOnlineRunStatusTransactionCallback(Sting, long, long)";
            log.entering(STR_METHOD_NAME);

            //証券会社コード＝引数.証券会社コード
            this.institutionCode = l_strInstitutionCode;
            //from口座ID＝引数.from口座ID
            this.fromAccountId = l_lngFromAccountId;
            //to口座ID＝引数.to口座ID
            this.toAccountId = l_lngToAccountId;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (processメソッドのオーバーライド) <BR>
         * <BR>
         * １）オンライン実行結果.set処理中(証券会社コード、<BR>
         * 　@銘柄タイプ、先物／オプション区分、オンラインサービス<BR>
         * 　@区分、From口座ID、To口座ID)を呼ぶ。 <BR>
         * <BR>
         * 　@[引数] <BR>
         * 　@　@証券会社コード　@　@　@＝証券会社コード <BR>
         * 　@　@銘柄タイプ　@　@　@　@　@　@＝'債券' <BR>
         * 　@　@先物／オプション区分＝'DEFAULT'<BR>
         * 　@　@オンラインサービス区分＝'自動約定'<BR>
         * 　@　@From口座ID　@　@　@　@　@＝from口座ID<BR>
         * 　@　@To口座ID　@　@　@　@　@　@　@＝to口座ID<BR>
         * <BR>
         * ２）１）の戻り値を返す。<BR>
         * @@return Object
         * @@throws DataCallbackException
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                //１）オンライン実行結果.set処理中(証券会社コード、銘柄タイプ、先物／オプション区分、
                // オンラインサービス区分、From口座ID、To口座ID)を呼ぶ。
                // [引数]
                //　@ 証券会社コード　@　@　@＝証券会社コード
                //　@ 銘柄タイプ　@　@　@　@　@　@＝'債券'
                //　@ 先物／オプション区分＝'DEFAULT'
                //　@ オンラインサービス区分＝'自動約定'
                //　@ From口座ID　@　@　@　@　@＝from口座ID
                //　@ To口座ID　@　@　@　@　@　@　@＝to口座ID
                l_onlineRunStatus =
                    WEB3GentradeOnlineRunStatus.setDealing(
                        this.institutionCode,
                        ProductTypeEnum.BOND,
                        WEB3FuturesOptionDivDef.DEFAULT,
                        WEB3OnlineServiceDiv.AUTO_EXECUTE,
                        this.fromAccountId,
                        this.toAccountId);
            }
            catch (WEB3BaseException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }

            //２）１）の戻り値を返す。
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
}@
