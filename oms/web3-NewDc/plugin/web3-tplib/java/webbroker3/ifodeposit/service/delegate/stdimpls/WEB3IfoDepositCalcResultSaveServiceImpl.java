head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoDepositCalcResultSaveServiceImplクラス(WEB3IfoDepositCalcResultSaveServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/22 孫(FLJ) 新規作成
 */

package webbroker3.ifodeposit.service.delegate.stdimpls;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifodeposit.data.IfoDepositCalcLockParams;
import webbroker3.ifodeposit.data.IfoDepositCalcLockRow;
import webbroker3.ifodeposit.data.IfoDepositCalcResultParams;
import webbroker3.ifodeposit.define.WEB3IfoDepositCalcResultSaveDiv;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveResponse;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultCreatePerAccountService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultSaveService;
import webbroker3.util.WEB3LogUtility;
import java.util.Hashtable;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * (証拠金計算結果保存サービスImpl)<BR>
 * 証拠金計算結果保存サービス実装クラス。<BR>
 *
 * @@author 孫(FLJ)
 * @@version 1.0
 */
public class WEB3IfoDepositCalcResultSaveServiceImpl implements WEB3IfoDepositCalcResultSaveService
{

    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalcResultSaveServiceImpl.class);

    /**
     * 一回コミット件数設定値取得キー
     */
    private final static String STR_MAX_COMMIT_ROWS_KEY = "ifodeposit.calcresult.max.commit.rows";

    /**
     * デフォルト一回コミット件数
     */
    private final static int DEFAULT_MAX_COMMIT_ROWS = 100;

    /**
     *
     */
    public WEB3IfoDepositCalcResultSaveServiceImpl()
    {
        super();
        // TODO 自動生成されたコンストラクター・スタブ
    }

    /**
     * 証拠金計算結果保存サービスを実施する。
     * 
     * １．処理スレッドをロックする。
     * ２．補助口座のリストを取得する
     * ３．顧客毎に証拠金計算結果データを作成する
     * ４．作成した結果をDbに保存する
     * ５．スレッドを停止する
     * 
     * @@param l_request
     *  - (リクエストデータ)
     * リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoDepositCalcResultSaveRequest l_req = (WEB3IfoDepositCalcResultSaveRequest) l_request;
        
        //レスポンスを作成
        WEB3IfoDepositCalcResultSaveResponse l_res = new WEB3IfoDepositCalcResultSaveResponse();
        
        // スレッド開始
        if (lockThread(l_req.threadNo.longValue()))
        {

            //処理対象の口座データを取得
            List l_subAccList = getSubAccountList(l_req.fromAccountId, l_req.toAccountId);

            //顧客毎に証拠金計算結果データを作成する
            List l_ifoDepositCalcResultList = new ArrayList();
            if (l_subAccList != null)
            {
                int l_size = l_subAccList.size();
                for (int i = 0; i < l_size; i++)
                {
                    WEB3IfoDepositCalcResultCreatePerAccountService l_createService = (WEB3IfoDepositCalcResultCreatePerAccountService) Services
                            .getService(WEB3IfoDepositCalcResultCreatePerAccountService.class);
                    IfoDepositCalcResultParams l_param = l_createService.createIfoDepositCalcResult((WEB3GentradeSubAccount) l_subAccList.get(i));
                    if (l_param != null)
                    {
                        l_ifoDepositCalcResultList.add(l_param);
                    }
                }
            }

            //DBに保存する
            save2DB(l_ifoDepositCalcResultList, l_res);

            // スレッド停止
            releaseThread(l_req.threadNo.longValue());
        }

        log.exiting(STR_METHOD_NAME);
        //レスポンスを返す
        return l_res;
    }

    /**
     * 作成した証拠金計算結果RowをDBに保存する。
     * 固定件数分一回コミットする。(コミット件数はSYSTEM_PREDERENCEに保存する、キー："ifodeposit.calcresult.max.commit.rows"。デフォールトは100)
     * 保存成功数と失敗数はレスポンス該当項目に設定する。
     * 
     * @@param depositCalcResultList 作成した証拠金計算結果Rowのリスト
     * @@param l_res レスポンス
     */
    private void save2DB(List l_depositCalcResultList, WEB3IfoDepositCalcResultSaveResponse l_res)
    {
        final String STR_METHOD_NAME = "save2DB(List, WEB3IfoDepositCalcResultSaveResponse)";
        log.entering(STR_METHOD_NAME);
        
        //コミット件数を取得
        String l_strCount = GtlUtils.getTradingSystem().getPreference(STR_MAX_COMMIT_ROWS_KEY);
        int l_intMaxCount = DEFAULT_MAX_COMMIT_ROWS;
        if (l_strCount != null)
        {
            try
            {
                l_intMaxCount = Integer.parseInt(l_strCount);
            }
            catch (Exception e)
            {
            }
        }

        ArrayList l_lst = new ArrayList();
        long l_lngSCount = 0;
        long l_lngFCount = 0;
        for (int i = 0; i < l_depositCalcResultList.size(); i++)
        {
            l_lst.add(l_depositCalcResultList.get(i));
            if (l_lst.size() == l_intMaxCount)
            {

                boolean l_blnRet = WEB3IfoDepositDBManager.getInstance().insert(l_lst);
                if (l_blnRet == true)
                {
                    l_lngSCount += l_intMaxCount;
                    log.info("save db success count=" + l_lngSCount);
                }
                l_lst.clear();
            }

        }

        if (l_lst.size() > 0)
        {
            boolean l_blnRet = WEB3IfoDepositDBManager.getInstance().insert(l_lst);
            if (l_blnRet == true)
            {
                l_lngSCount += l_lst.size();
                log.info("save db success count=" + l_lngSCount);
            }
            l_lst.clear();
        }

        log.info("save db all success count=" + l_lngSCount);
        l_lngFCount = l_depositCalcResultList.size() - l_lngSCount;
        log.info("save db all failure count=" + l_lngFCount);
        l_res.success = new Long(l_lngSCount);
        if (l_lngFCount > 0)
        {
            l_res.failure = new Long(l_lngFCount);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 補助口座情報のリストを取得する。
     * １、補助口座を検索する。
     * 絞り条件：
     * A、補助口座タイプ＝７：先物証拠金
     * B、顧客IDは以下の子検索の範囲内
     * 　@　@子検索：顧客マスターに対して、検索する。
     * 　@　@絞り条件：
     * 　@　@a.顧客ID <= 指定する開始顧客ID
     *     b.顧客ID >= 指定する終了顧客ID
     *     c.先物OP口座開設区分の3項目(東証、大証、名証)の何れかが"先物OP口座開設:3"、または、"先物口座開設:2"
     * ２、顧客範囲内の顧客マスターリストを取得。
     * ３、検索した補助口座Rowと顧客マスターより補助口座オブジェクト(Gentrade)を作成してリストに保存する。
     * ４、リストを返す
     * 
     * @@param fromAccountId 開始の顧客ID
     * @@param toAccountId 終了の顧客ID
     * @@return 補助口座情報のリスト
     * @@throws WEB3BaseException DB検索失敗の場合
     */
    private List getSubAccountList(long l_fromAccountId, long l_toAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccountList(long, long)";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "sub_account_type = ? and account_id in (select account_id from main_account where account_id >= ? and account_id <= ? and ((IFO_ACC_OPEN_DIV_TOKYO = ? or IFO_ACC_OPEN_DIV_TOKYO = ?) or (IFO_ACC_OPEN_DIV_OSAKA = ? or IFO_ACC_OPEN_DIV_OSAKA = ?) or (IFO_ACC_OPEN_DIV_NAGOYA = ? or IFO_ACC_OPEN_DIV_NAGOYA = ?)))";
        Object[] l_bindVars = new Object[9];
        l_bindVars[0] = SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;//先物証拠金（７）
        l_bindVars[1] = new Long(l_fromAccountId);
        l_bindVars[2] = new Long(l_toAccountId);
        l_bindVars[3] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;//先物口座開設
        l_bindVars[4] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;//先物OP口座開設
        l_bindVars[5] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;//先物口座開設
        l_bindVars[6] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;//先物OP口座開設
        l_bindVars[7] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;//先物口座開設
        l_bindVars[8] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;//先物OP口座開設
        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        log.debug("BindVars[2]=" + l_bindVars[2]);
        log.debug("BindVars[3]=" + l_bindVars[3]);
        log.debug("BindVars[4]=" + l_bindVars[4]);
        log.debug("BindVars[5]=" + l_bindVars[5]);
        log.debug("BindVars[6]=" + l_bindVars[6]);
        log.debug("BindVars[7]=" + l_bindVars[7]);
        log.debug("BindVars[8]=" + l_bindVars[8]);

        QueryProcessor l_queryProcessor = null;
        List l_listSearchResult = null;
        try
        {
            //検索開始
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listSearchResult = l_queryProcessor.doFindAllQuery(SubAccountRow.TYPE, l_strWhere.toString(), null, null, l_bindVars);
        }
        catch (DataException e)
        {
            final String l_message = "証拠金計算結果保存の補助口座取得に失敗しました。";
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, "getSubAccount()", l_message, e);
        }
        //顧客情報一括取得(キャッシューのために)
        Hashtable l_tblMainAcc=getMainAccountTbl(l_fromAccountId, l_toAccountId);

        int l_size = l_listSearchResult.size();
        List l_subAccList = new ArrayList(l_size);
        for (int i = 0; i < l_size; i++)
        {
            SubAccountRow l_row = (SubAccountRow) l_listSearchResult.get(i);
            MainAccountRow l_mainrow=(MainAccountRow)l_tblMainAcc.get(new Long(l_row.getAccountId()));
            //補助口座オブジェクトを作成
            if(l_mainrow==null){
              WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_row);
               l_subAccList.add(l_subAccount);
            }
            else {
              WEB3GentradeMainAccount l_mainAccount=new WEB3GentradeMainAccount(l_mainrow);
              WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount,l_row);
               l_subAccList.add(l_subAccount);
            }


        }
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccList;
    }

    /**
     * 顧客情報を一括取得する。
     * 　@　@絞り条件：
     * 　@　@a.顧客ID <= 指定する開始顧客ID
     *     b.顧客ID >= 指定する終了顧客ID
     *     c.先物OP口座開設区分の3項目(東証、大証、名証)の何れかが"先物OP口座開設:3"、または、"先物口座開設:2"
     * @@param fromAccountId 開始の顧客ID
     * @@param toAccountId 終了の顧客ID
     * @@return Hashtable　@顧客IDをキー、顧客マスタRowはバリュー
     * @@throws WEB3BaseException DB検索失敗の場合
     */
    private Hashtable getMainAccountTbl(long l_fromAccountId, long l_toAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccountTbl(long, long)";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "account_id >= ? and account_id <= ? and ((IFO_ACC_OPEN_DIV_TOKYO = ? or IFO_ACC_OPEN_DIV_TOKYO = ?) or (IFO_ACC_OPEN_DIV_OSAKA = ? or IFO_ACC_OPEN_DIV_OSAKA = ?) or (IFO_ACC_OPEN_DIV_NAGOYA = ? or IFO_ACC_OPEN_DIV_NAGOYA = ?))";
        Object[] l_bindVars = new Object[8];
        l_bindVars[0] = new Long(l_fromAccountId);
        l_bindVars[1] = new Long(l_toAccountId);
        l_bindVars[2] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;
        l_bindVars[3] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;
        l_bindVars[4] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;
        l_bindVars[5] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;
        l_bindVars[6] = WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH;
        l_bindVars[7] = WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH;
        log.debug("WHERE=" + l_strWhere);
        log.debug("BindVars[0]=" + l_bindVars[0]);
        log.debug("BindVars[1]=" + l_bindVars[1]);
        log.debug("BindVars[2]=" + l_bindVars[2]);
        log.debug("BindVars[3]=" + l_bindVars[3]);
        log.debug("BindVars[4]=" + l_bindVars[4]);
        log.debug("BindVars[5]=" + l_bindVars[5]);
        log.debug("BindVars[6]=" + l_bindVars[6]);
        log.debug("BindVars[7]=" + l_bindVars[7]);

        QueryProcessor l_queryProcessor = null;
        List l_listSearchResult = null;
        try
        {
            //検索開始
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listSearchResult = l_queryProcessor.doFindAllQuery(MainAccountRow.TYPE, l_strWhere.toString(), null, null, l_bindVars);
        }
        catch (DataException e)
        {
            final String l_message = "証拠金計算結果保存の顧客口座取得に失敗しました。";
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, "getMainAccountTbl()", l_message, e);
        }

        int l_size = l_listSearchResult.size();
        Hashtable l_tblMainAcc = new Hashtable(l_size);
        for (int i = 0; i < l_size; i++)
        {
            MainAccountRow l_row = (MainAccountRow) l_listSearchResult.get(i);
            l_tblMainAcc.put(new Long(l_row.getAccountId()),l_row);

        }
        log.exiting(STR_METHOD_NAME);
        return l_tblMainAcc;
    }
    
    /**
     * (スレッド開始)<BR>
     * ［処理概要］<BR>
     * 指定されたスレッドのロック情報を検索する。
     * 1)取得できない場合、新しいロックレコード追加（Thread_no = スレッドNo. 　@処理状態　@= 1:処理中）
     * 2)取得した場合、処理状態は「１：処理中」であるかどうかを判断する：
     * 2-1)「１：処理中」である場合、falseを返す
     * 2-2)「１：処理中」でない場合、スレッドNo条件に、処理状態を１：処理中に更新する。 更新成功したら、trueを返す。
     * 
     * 更新対象スレッド情報の更新に失敗した場合<BR>
     * WEB3SystemLayerException<BR>
     * SYSTEM_ERROR_80003<BR>
     * 
     * @@param l_strThreadNo - (スレッドNo)
     * @@return ロック成功の場合true,他のスレッドロック中の場合false
     */
    protected boolean lockThread(
        final long l_lngThreadNo) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "lockThread(String)";
        final int UPDATE_SUCCESS = 0; // 更新処理が正常終了した値
        final int UPDATE_FAIL = -1; // 更新対象スレッド情報の更新に失敗した場合の値
        final int LOCKED = -2; // 更新対象スレッド情報を取得できなかった場合の値

        log.entering(STR_METHOD_NAME);

        try
        {
            final String l_serviceName = this.getClass().getName();
            final String l_apName = getAppName();
            final Timestamp l_tm = GtlUtils.getSystemTimestamp();
            
            final String l_strWhere = "service_name = ? and thread_no = ?";
            final String l_strCondition = "for update";

            final Object l_bindVars[] = { l_serviceName, new Long(l_lngThreadNo) };

            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW,
                                               new TransactionCallback()
            {
                public Object process() throws DataNetworkException,
                    DataQueryException,
                    DataCallbackException
                {
                    Integer l_intResult = null;
                    //ロックしたいスレッドを検索
                    List l_lisRows = l_queryProcesser.doFindAllQuery(
                            IfoDepositCalcLockRow.TYPE,
                        l_strWhere, l_strCondition, l_bindVars);
                    //スレッド記録がある場合
                    if (l_lisRows.size() > 0)
                    {
                        IfoDepositCalcLockRow l_row =
                            (IfoDepositCalcLockRow) l_lisRows.get(0);

                        //ロック中であるかを判断する
                        if(WEB3IfoDepositCalcResultSaveDiv.LOCK_PROC.equals(l_row.getStatus()))
                        {
                            //ロック中の場合
                            l_intResult = new Integer(LOCKED);
                        }
                        else
                        {
                            //未ロックの場合、「ロック中」に更新
                            IfoDepositCalcLockParams l_params = new IfoDepositCalcLockParams(l_row);
                            l_params.setApHostName(l_apName);
                            l_params.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_PROC);
                            l_params.setLastUpdatedTimestamp(l_tm);
                            l_queryProcesser.doUpdateQuery(l_params);
                            l_intResult = new Integer(UPDATE_SUCCESS);
                        }
                    }
                    else
                    {
                        //スレッド記録がなしの場合、新規インサートする
                        IfoDepositCalcLockParams l_params = new IfoDepositCalcLockParams();
                        l_params.setServiceName(l_serviceName);
                        l_params.setThreadNo(l_lngThreadNo);
                        l_params.setApHostName(l_apName);
                        l_params.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_PROC);
                        l_params.setCreatedTimestamp(l_tm);
                        l_params.setLastUpdatedTimestamp(l_tm);
                        l_queryProcesser.doInsertQuery(l_params);
                        l_intResult = new Integer(UPDATE_SUCCESS);
                    }

                    return l_intResult;
                }
            }
            );
            
            log.debug("startThread result:"+l_intResult);
            
            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );
            }
            else if (l_intResult.intValue() == LOCKED)
            {
                return false;
            }
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(), de);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (スレッド開放)<BR>
     * ［処理概要］<BR>
     * １）引数で指定されたスレッドNoを条件にして対象レコードの処理状態を<BR>
     * 0（：未稼動）する。<BR>
     *<BR>
     * 更新対象スレッド情報を取得できなかった場合<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80003<BR>
     *<BR>
     * @@param l_strThreadNo - (スレッドNo)
     */
    protected void releaseThread(final long l_lngThreadNo) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "releaseThread(String)";
        final int UPDATE_SUCCESS = 0; // 更新処理が正常終了した値
        final int UPDATE_FAIL = -1; // 更新対象スレッド情報を取得できなかった場合の値

        log.entering(STR_METHOD_NAME);

        try
        {
            final String l_serviceName = this.getClass().getName();
            final Timestamp l_tm = GtlUtils.getSystemTimestamp();
            
            final String l_strWhere = "service_name = ? and thread_no = ?";
            final String l_strCondition = "for update";

            final Object l_bindVars[] = { l_serviceName, new Long(l_lngThreadNo) };

            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW,
                                               new TransactionCallback()
            {
                public Object process() throws DataNetworkException,
                    DataQueryException,
                    DataCallbackException
                {
                    Integer l_intResult = null;
                    //開放したいスレッドを検索
                    List l_lisRows = l_queryProcesser.doFindAllQuery(
                            IfoDepositCalcLockRow.TYPE,
                        l_strWhere, l_strCondition, l_bindVars);
                    if (l_lisRows.size() > 0)
                    {
                        //レコードがある場合、「未稼働」に更新する
                        IfoDepositCalcLockRow l_row =
                            (IfoDepositCalcLockRow) l_lisRows.get(0);
                        IfoDepositCalcLockParams l_params = new IfoDepositCalcLockParams(l_row);
                        l_params.setApHostName("NONE");
                        l_params.setStatus(WEB3IfoDepositCalcResultSaveDiv.LOCK_NONE);
                        l_params.setLastUpdatedTimestamp(l_tm);
                        l_queryProcesser.doUpdateQuery(l_params);
                        l_intResult = new Integer(UPDATE_SUCCESS);
                    }
                    else
                    {
                        l_intResult = new Integer(UPDATE_FAIL);
                    }
                    return l_intResult;
                }
            }
            );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );
            }
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(), de);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * AP名を取得する
     * 
     * @@return String AP名
     */
    private String getAppName()
    {
        try
        {
            InetAddress l_localHost = InetAddress.getLocalHost();
            return l_localHost.getHostName();
        }
        catch (UnknownHostException ex)
        {
            log.error(ex.getMessage(), ex);
            return "";
        }

    }

}
@
