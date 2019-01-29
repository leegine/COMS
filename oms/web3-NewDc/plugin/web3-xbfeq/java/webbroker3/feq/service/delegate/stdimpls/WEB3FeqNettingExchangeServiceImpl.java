head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式為替ネッティングサービス実装クラス(WEB3FeqNettingExchangeServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/08 車進 (中訊) 新規作成 モデルNo.549
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ProcessIdDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManagerHelper;
import webbroker3.feq.data.FeqOrderexecutionEndRow;
import webbroker3.feq.message.WEB3FeqNettingExchangeRequest;
import webbroker3.feq.message.WEB3FeqNettingExchangeResponse;
import webbroker3.feq.service.delegate.WEB3FeqNettingExchangeService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式為替ネッティングサービスImpl)<BR>
 * 外国株式為替ネッティングサービス実装クラス<BR>
 * <BR>
 * @@author 車進
 * @@version 1.0 
 */
public class WEB3FeqNettingExchangeServiceImpl implements WEB3FeqNettingExchangeService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log  =
        WEB3LogUtility.getInstance(WEB3FeqNettingExchangeServiceImpl.class);

    /**
     * @@roseuid 42CE39F5007D
     */
    public WEB3FeqNettingExchangeServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * 為替ネッティング処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外国株式為替ネッティングサービス）為替ネッティング処理」参照。<BR>
     * @@param l_web3BackRequest - (リクエストデータ)<BR>
     * リクエストデータオブジェクト
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B8A39B0394
     */
    public WEB3BackResponse  execute(WEB3BackRequest  l_web3BackRequest)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_web3BackRequest == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        else
        {
            if (l_web3BackRequest instanceof WEB3FeqNettingExchangeRequest)
            {
                WEB3FeqNettingExchangeRequest l_feqNettingExchangeRequest =
                    (WEB3FeqNettingExchangeRequest)l_web3BackRequest;

                //証券会社コード
                String l_strInstitutionCode = l_feqNettingExchangeRequest.institutionCode;

                //発注日を取得する。
                //証券会社コード：　@パラメータ.証券会社コード
                Date l_datOrderBizDate = this.getOrderBizDate(l_strInstitutionCode);

                //ネッティン処理対象顧客一覧を取得する。
                //証券会社コード： リクエスト.証券会社コード
                //発注日：　@get発注日()の戻り値
                MainAccount[] l_mainAccounts = this.getNettingMainAccountList(
                    l_strInstitutionCode,
                    l_datOrderBizDate);

                //処理区分
                String l_strStatus = WEB3StatusDef.DEALT;

                QueryProcessor l_queryProcessor = null;
                try
                {
                    l_queryProcessor = Processors.getDefaultProcessor();
                }
                catch (DataFindException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                         this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                          WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                          this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                //get処理対象顧客一覧()の要素数分Loop処理
                if (l_mainAccounts != null)
                {
                    for (int i = 0; i < l_mainAccounts.length; i++)
                    {
                        try
                        {
                            //updateトランザクションTransactionCallback
                            WEB3FEQNettingUpdateTransactionCallback l_callBack =
                                new WEB3FEQNettingUpdateTransactionCallback();

                            l_callBack.setMainAccount(l_mainAccounts[i]);
                            l_callBack.setOrderBizDate(l_datOrderBizDate);

                            l_queryProcessor.doTransaction(TransactionalInterceptor.TX_CREATE_NEW, l_callBack);
                        }
                        catch(Exception l_ex)
                        {
                            l_strStatus = WEB3StatusDef.DATA_ERROR;
                            continue;
                        }
                    }
                }

                //証券会社を取得する。
                l_strInstitutionCode = l_feqNettingExchangeRequest.institutionCode;
                WEB3GentradeInstitution l_institution = null;
                try
                {
                    l_institution = new WEB3GentradeInstitution(l_strInstitutionCode);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました" , l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました" , l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //証券会社コードの対応の全ての部店を取得する。
                Branch[] l_branches = l_institution.getBranches();
                //取得した検索結果数分LOOP処理
                //プロセス管理Paramsを生成する。
                ProcessManagementParams l_processManagementParams =
                    new ProcessManagementParams();

                //プロセスＩＤ:'0014'
                l_processManagementParams.setProcessId(WEB3ProcessIdDef.FEQ_NETTING_EXCHANGE_COMPLETE);

                //入力の証券会社コードをセット。
                l_processManagementParams.setInstitutionCode(
                    l_strInstitutionCode);

                //処理区分:
                l_processManagementParams.setStatus(l_strStatus);

                //最終更新者:null
                l_processManagementParams.setLastUpdater(null);

                for (int i = 0; i < l_branches.length; i++)
                {
                    //部店コード:証券会社.部店コード[i]
                    l_processManagementParams.setBranchCode(l_branches[i].getBranchCode());

                    //最終更新時刻:"現在日時(GtlUtils.getSystemTimestamp())"
                    l_processManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                    try
                    {
                        l_queryProcessor.doInsertQuery(l_processManagementParams);
                    }
                    catch (DataQueryException l_ex)
                    {
                        log.error("DBへのアクセスに失敗しました。", l_ex);
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataNetworkException l_ex)
                    {
                        log.error("DBへのアクセスに失敗しました。", l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                }

                WEB3FeqNettingExchangeResponse l_feqNettingExchangeResponse =
                    (WEB3FeqNettingExchangeResponse)l_web3BackRequest.createResponse();

                log.exiting(STR_METHOD_NAME);
                return l_feqNettingExchangeResponse;
            }
            else
            {
                log.debug("パラメータタイプ不正");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータタイプ不正");
            }
        }
    }

    /**
     * (getネッティン処理対象顧客一覧)<BR>
     * ネッティン処理対象となる注文を保持する。<BR>
     * 顧客の一覧を返却する。<BR>
     * <BR>
     * １）　@注文単位検索<BR>
     * 外国株式注文マネージャ.getネッティン対象注文単位()をコールする。<BR>
     * <BR>
     * [getネッティン対象注文単位()に指定する引数]<BR>
     * 口座ID：　@null<BR>
     * 証券会社コード：　@パラメータ.証券会社コード<BR>
     * 発注日：　@get発注日()の戻り値<BR>
     * ※nullが返却された場合、nullを返却する。<BR>
     * <BR>
     * ２）　@顧客オブジェクト作成<BR>
     * ２－１）１）の検索結果について、ユニークな口座IDの一覧を作成する。<BR>
     * ２－２）２－１）にて作成した口座IDの一覧分、顧客オブジェクトを作成し、<BR>
     * 配列として返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードオブジェクト<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * 発注日オブジェクト<BR>
     * @@return MainAccount[]
     * @@throws WEB3BaseException
     */
    private MainAccount[] getNettingMainAccountList(String l_strInstitutionCode,
        Date l_datOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNettingMainAccountList(String, Date)";
        log.entering(STR_METHOD_NAME);

        //１）　@注文単位検索
        //外国株式注文マネージャ.getネッティン対象注文単位()をコールする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        WEB3FeqOrderManager l_feqOrderManager =
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        //[getネッティン対象注文単位()に指定する引数]
        //口座ID：　@null
        //証券会社コード：　@パラメータ.証券会社コード
        //発注日：　@get発注日()の戻り値
        WEB3FeqOrderUnit[] l_feqOrderUnits = l_feqOrderManager.getNettingOrderUnit(
            null,
            l_strInstitutionCode,
            l_datOrderBizDate);

        //※nullが返却された場合、nullを返却する。
        if (l_feqOrderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //２）　@顧客オブジェクト作成
        else
        {
            //２－１）１）の検索結果について、ユニークな口座IDの一覧を作成する。
            //２－２）２－１）にて作成した口座IDの一覧分、顧客オブジェクトを作成し、配列として返却する。
            WEB3GentradeMainAccount[] l_mainAccounts= null;
            List l_lisMainAccounts = new ArrayList();
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            int l_intCnt = l_feqOrderUnits.length;

            List l_lisAccountId = new ArrayList();

            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqOrderUnit l_orderUnit = l_feqOrderUnits[i];

                if (l_orderUnit != null)
                {
                    Long l_accountId = new Long(l_orderUnit.getAccountId());

                    if (!l_lisAccountId.contains(l_accountId))
                    {
                        l_lisAccountId.add(l_accountId);
                    }
                }
            }

            int l_intAccountCnt = l_lisAccountId.size();

            try
            {
                for (int i = 0; i < l_intAccountCnt; i++)
                {
                    Long l_accountId = (Long)l_lisAccountId.get(i);

                    WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)
                        l_accMgr.getMainAccount(l_accountId.longValue());//NotFoundException
                    l_lisMainAccounts.add(l_mainAccount);
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

            if (!l_lisMainAccounts.isEmpty())
            {
                l_mainAccounts = new WEB3GentradeMainAccount[l_lisMainAccounts.size()];
                l_lisMainAccounts.toArray(l_mainAccounts);
            }
            log.exiting(STR_METHOD_NAME);   
            return l_mainAccounts;
        }
    }

    /**
     * (get発注日）<BR>
     * <BR>
     * １）　@注文単位検索<BR>
     * 以下の条件にて外株出来終了テーブルを検索する。<BR>
     * 　@[条件]<BR>
     * 証券会社コード　@=　@パラメータ.証券会社コード<BR>
     * <BR>
     * 検索結果は、以下の項目で降順ソートし、取得すること。<BR>
     * 　@　@①@前回実施日<BR>
     * <BR>
     * 　@※外株出来終了テーブルが取得できない場合は、システムエラー”該当データなし”を返却する。<BR>
     * <BR>
     * ２）発注日取得<BR>
     * 　@外株出来終了List(0)．前回実施日を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードオブジェクト<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    private Date getOrderBizDate(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@注文単位検索
        //[条件]
        //証券会社コード = パラメータ.証券会社コード
        // 検索結果は、以下の項目で降順ソートし、取得すること。
         //①@前回実施日
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        // ソート条件
        String l_strOrderBy = " last_execute_date desc  ";

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderexecutionEndRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                new Object[]{l_strInstitutionCode}
                );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //※外株出来終了テーブルが取得できない場合は、システムエラー”該当データなし”を返却する。
        if (l_lisRecords.size() == 0)
        {
            log.debug("該当データなし");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "テーブルに該当するデータがありません。");
        }
        //外株出来終了List(0)．前回実施日を返却する。
        else
        {
            FeqOrderexecutionEndRow l_feqOrderexecutionEnd = (FeqOrderexecutionEndRow)l_lisRecords.get(0);

            log.exiting(STR_METHOD_NAME);
            return l_feqOrderexecutionEnd.getLastExecuteDate();
        }
    }

    /**
     * updateトランザクションTransactionCallback<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     */
    public class WEB3FEQNettingUpdateTransactionCallback implements TransactionCallback
    {
        /**
         *注文単位ＩＤ一覧<BR>
         */
        private long[] l_lngOrderUnitIdLists;

        /**
         *基準日<BR>
         */
        private Date l_datOrderBizDate;

        /**
         * 顧客
         */
        private MainAccount l_mainAccount;

        /**
         * (set注文単位ＩＤ一覧)<BR>
         * <BR>
         * 引数の注文単位ＩＤ一覧をプロパティにセットする。<BR>
         * @@params long[] - 注文単位ＩＤ一覧<BR>
         */
        public void setOrderUnitIdList(long[] l_lngOrderUnitIdLists)
        {
            this.l_lngOrderUnitIdLists = l_lngOrderUnitIdLists;
        }

        /**
         * (set基準日)<BR>
         * <BR>
         * 引数の基準日をプロパティにセットする。<BR>
         * @@params Date - 基準日<BR>
         */
        public void setOrderBizDate(Date l_datOrderBizDate)
        {
            this.l_datOrderBizDate = l_datOrderBizDate;
        }

        /**
         * (set顧客)<BR>
         * <BR>
         * 引数の顧客をプロパティにセットする。<BR>
         * @@params Date - 基準日<BR>
         */
        public void setMainAccount(MainAccount l_mainAccount)
        {
            this.l_mainAccount = l_mainAccount;
        }

        /**
         * デフォルトコンストラクタ
         * @@return WEB3FeqNettingExchangeServiceImpl.WEB3FEQNettingUpdateTransactionCallback
         * @@roseuid 4088F56A0131
         */
        public WEB3FEQNettingUpdateTransactionCallback()
        {

        }

        /**
         * ネッティング為替となるトランザクションの金額を再計算する。<BR>
         * <BR>
         * <BR>
         * @@return Object
         * @@roseuid 4088F56A0122
         */
        public Object process()
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //外国株式ポジションヘルパーを取得する。
            WEB3FeqPositionManagerHelper l_feqPositionManagerHelper =
                new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //拡張アカウントマネージャを取得する。
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //外国株式注文マネージャを取得する。
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_feqOrderManager =
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

            //口座をロックする。
            //証券会社コード：　@顧客.証券会社コード
            //部店コード：　@顧客.部店コード
            //口座コード：　@顧客.口座コード
            String l_strInstitutionCode =  this.l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = this.l_mainAccount.getBranch().getBranchCode();
            String l_strAccountCode =  this.l_mainAccount.getAccountCode();


            //ネッティン対象となる注文単位を取得する。
            //口座ID：　@処理対象の要素.顧客.口座ID
            //証券会社コード：　@パラメータ.顧客.証券会社コード
            //発注日：　@get発注日()の戻り値
            long l_lngAccountId = this.l_mainAccount.getAccountId();

            try
            {
                WEB3FeqOrderUnit[] l_orderUnits = l_feqOrderManager.getNettingOrderUnit(
                    new Long(l_lngAccountId),
                    l_strInstitutionCode,
                    l_datOrderBizDate);

                //注文単位ID一覧：getネッティン対象注文単位()の戻り値より抽出した注文単位IDの一覧
                long[] l_lngOrderUnitIdList = null;
    
                int l_intCnt = 0;
    
                if (l_orderUnits != null && l_orderUnits.length > 0)
                {
                    l_intCnt = l_orderUnits.length;
                    int l_intCnt2 = 0;
    
                    for (int j = 0; j < l_intCnt; j++)
                    {
                        WEB3FeqOrderUnit l_orderUnit = l_orderUnits[j];
    
                        if (l_orderUnit != null)
                        {
                            l_intCnt2++;
                        }
                    }
                    if (l_intCnt2 > 0)
                    {
                        l_lngOrderUnitIdList = new long[l_intCnt2];
                        int l_intNo = 0;
    
                        for (int k = 0; k < l_intCnt; k++)
                        {
                            WEB3FeqOrderUnit l_orderUnit = l_orderUnits[k];
    
                            if (l_orderUnit != null)
                            {
                                l_lngOrderUnitIdList[l_intNo] = l_orderUnit.getOrderUnitId(); 
                                l_intNo++;
                            }
                        }
                    }
                }
                l_accountManager.lockAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);

                this.setOrderUnitIdList(l_lngOrderUnitIdList);
                //updateトランザクション（ネッティング採用）(注文単位ＩＤ一覧 : long[], 基準日 : Date)
                l_feqPositionManagerHelper.updateTransactionNettingAdoption(l_lngOrderUnitIdLists, l_datOrderBizDate);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
