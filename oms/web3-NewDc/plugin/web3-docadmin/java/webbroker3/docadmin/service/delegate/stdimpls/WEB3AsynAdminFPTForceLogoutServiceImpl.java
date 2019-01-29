head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AsynAdminFPTForceLogoutServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (非同期)管理者 書面未承諾 強制ログアウトサービスImpl(WEB3AsynAdminFPTForceLogoutServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/18 孫(FLJ) 新規作成
 */
package webbroker3.docadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DeliveryTargetDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.docadmin.WEB3AdminFPTCommon;
import webbroker3.docadmin.data.DocForceLogoutRunStatusParams;
import webbroker3.docadmin.data.DocForceLogoutRunStatusRow;
import webbroker3.docadmin.define.WEB3AdminFPTForceLogoutValidityDef;
import webbroker3.docadmin.define.WEB3DocForceLogoutRunStatusStatusDef;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainRequest;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (非同期)管理者 書面未承諾 強制ログアウトサービスImpl
 * 
 * @@author 孫
 * @@version 1.0
 */
public class WEB3AsynAdminFPTForceLogoutServiceImpl implements Runnable
{

    /**
     * ログユーティリティ <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AsynAdminFPTForceLogoutServiceImpl.class);

    /**
     * リクエストデータ
     */
    private WEB3AdminFPTForceLogoutMainRequest requestData;

    /**
     * 属性取得キー 検索単位
     */
    private static final String SEARCH_UNIT_KEY = ".doc.force.logout.query.unit";

    /**
     * 属性取得キー リトライ数
     */
    private static final String RETRY_COUNT_KEY = ".doc.force.logout.retry.count";

    /**
     * デフォルト検索単位
     */
    private long defaultSearchUnits = 1000;

    /**
     * デフォルトリトライ数
     */
    private long defaultRetryCount = 1;

    /**
     * 日付フォーマット
     */
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";

    /**
     * @@roseuid 47DF4FB700B5
     */
    public WEB3AsynAdminFPTForceLogoutServiceImpl()
    {

    }

    /**
     * コンストラクタ
     * 
     * 引数をthis.リクエストデータにセットする。
     * 
     * @@param l_request -
     *            管理者 書面未承諾 強制ログアウトメインリクエスト
     * @@roseuid 47D720B20025
     */
    public WEB3AsynAdminFPTForceLogoutServiceImpl(WEB3AdminFPTForceLogoutMainRequest l_request)
    {
        requestData = l_request;
    }

    /**
     * （非同期）管理者 書面未承諾 強制ログアウト処理を行う。
     * 
     * シーケンス図 「（（非同期）管理者 書面未承諾 強制ログアウト）run」参照。
     * 
     * @@throws DataNetworkException
     * @@throws DataFindException
     * @@roseuid 47D71F5402CE
     */
    public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME);

        WEB3DescendRacCtxService l_racService = null;

        try
        {
            //DBの振り分け先を決定する
            l_racService = (WEB3DescendRacCtxService) Services.getService(WEB3DescendRacCtxService.class);
            l_racService.setAccountIdCtx(requestData.accountIdFrom.longValue());

            //管理者 書面未承諾 強制ログアウトデーモントリガーTransactionCallbackを生成する。
            WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback l_daemonCallback = new WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback(
                    requestData.threadNo.longValue());

            //書面交付対象Map
            Map l_docDeliveryMap = new HashMap();
            //ログアウト候補Map
            Map l_logoutMap = new HashMap();
            //交付済みアカウントMap
            Map l_deliveredAccMap = new HashMap();
            //アカウント情報Map
            Map l_accMap = new HashMap();
            //ログアウト実施セッションList
            List l_logoutSessionList = new ArrayList();
            //処理結果isエラー
            boolean l_isError = false;
            //処理件数
            int l_count = 0;

            QueryProcessor l_qp = Processors.getDefaultProcessor();

            DaemonTriggerRow l_daemonRow = (DaemonTriggerRow) l_qp.doTransaction(QueryProcessor.TX_JOIN_EXISTING, l_daemonCallback);

            //nullが返却された場合は、処理を終了する。
            if (l_daemonRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            //管理者を作成する
            WEB3Administrator l_admin = new WEB3Administrator(requestData.adminId.longValue());

            //書面未承諾 強制ログアウト実行結果の更新準備
            //[引数]
            //証券会社コード ＝this.リクエストデータ.証券会社コード
            //From口座ID ＝this.リクエストデータ.From口座ID
            //To口座ID ＝this.リクエストデータ.To口座ID
            //書面種類コード一覧＝this.リクエストデータ.管理者 書面未承諾 強制ログアウト対象銘柄条件.書面種類コード一覧
            //更新者コード ＝管理者.getAdministratorCode()
            WEB3AdminFPTForceLogoutExecuteResultTransactionCallback l_resultCallback = new WEB3AdminFPTForceLogoutExecuteResultTransactionCallback(
                    requestData.institutionCode, requestData.accountIdFrom.longValue(), requestData.accountIdTo.longValue(),
                    requestData.forceLogoutProductCondition.documentCatCodeArr, l_admin);

            //実行結果トランザクションを開始する。
            DocForceLogoutRunStatusRow l_exeResultRow = (DocForceLogoutRunStatusRow) l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW,
                    l_resultCallback);

            try
            {
                //証券会社コードを取得する。
                String l_institutionCode = l_admin.getInstitutionCode();
    
                //検索単位を取得する。
                //[引数]
                //プロパティ・キー： get証券会社コード()+".doc.force.logout.query.unit"
                //デフォルト値 ：デフォルト検索単位
                //リトライ回数を取得する。
                //[引数]
                //プロパティ・キー： get証券会社コード()+".doc.force.logout.retry.count"
                //デフォルト値 ：デフォルトリトライ数
                long l_searchUnits = getProperties(l_institutionCode + SEARCH_UNIT_KEY, defaultSearchUnits);
                long l_retryCount = getProperties(l_institutionCode + RETRY_COUNT_KEY, defaultRetryCount);
    
                if(log.ison())
                {
                    log.debug("検索単位=" + l_searchUnits);
                    log.debug("リトライ回数=" + l_retryCount);
                }
    
                //部店コードを取得する。
                String l_branchCode = l_admin.getBranchCode();
    
                //書面種類管理テーブル検索条件を準備
                //[条件]
                //・証券会社コード ＝ 証券会社コード
                //・部店コード ＝ 部店コード
                //・書面種類コード in 管理者 書面未承諾 強制ログアウト対象銘柄条件.書面種類コード一
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append("institution_code = ? ");
                l_sbWhere.append("and branch_code = ? ");
                l_sbWhere.append("and document_category in (");
    
                List l_objList = new ArrayList();
                l_objList.add(l_institutionCode);
                l_objList.add(l_branchCode);
    
                for (int i = 0; i < requestData.forceLogoutProductCondition.documentCatCodeArr.length; i++)
                {
                    if (i != 0)
                    {
                        l_sbWhere.append(",");
                    }
                    l_sbWhere.append("?");
                    l_objList.add(requestData.forceLogoutProductCondition.documentCatCodeArr[i]);
                }
    
                l_sbWhere.append(")");
                Object[] l_vars = l_objList.toArray();
    
                //書面種類管理テーブル検索を実行
                List l_docCateMngList = l_qp.doFindAllQuery(DocCategoryManagementRow.TYPE, l_sbWhere.toString(), l_vars);
    
                //書面種類管理テーブルの検索結果数分Loop
                int l_listCnt = l_docCateMngList.size();
                for (int i = 0; i < l_listCnt; i++)
                {
                    //書面交付対象Mapにキー＝書面種類コード、値＝交付対象をセットする。
                    DocCategoryManagementRow l_tempRow = (DocCategoryManagementRow) l_docCateMngList.get(i);
                    l_docDeliveryMap.put(l_tempRow.getDocumentCategory(), l_tempRow.getDeliveryTarget());
                }
    
                //ログインタイプテーブル検索準備
                //[条件]
                //・ログインタイプ名 like getInstitutionId()+ '_customer_login_type%'
                l_sbWhere = new StringBuffer();
                l_sbWhere.append("type_name like ?");
    
                l_vars = new Object[] { l_institutionCode + "_customer_login_type%" };
    
                //ログインタイプテーブルを検索
                List l_loginTypeList = l_qp.doFindAllQuery(LoginTypeRow.TYPE, l_sbWhere.toString(), l_vars);
    
                if (l_loginTypeList.size() > 0)
                {
                    StringBuffer l_typeConditionWhere = new StringBuffer();
                    List l_typeConditionList = new ArrayList();
                    for (int i = 0; i < l_loginTypeList.size(); i++)
                    {
                        Long l_typeId = new Long(((LoginTypeRow) l_loginTypeList.get(i)).getTypeId());
                        if(i!=0)
                        {
                            l_typeConditionWhere.append(",");
                        }
                        l_typeConditionWhere.append("?");
                        l_typeConditionList.add(l_typeId);
                    }
                    
                    if(log.ison())
                    {
                        log.debug("検索完了時 ログインタイプID=" + l_typeConditionList);
                    }

                    RowType[] l_rowType = new RowType[]{LoginSessionRow.TYPE};
                    
                    //処理件数 0で初期化
                    int l_execCount = 0;
                    //システム時刻を取得
                    // ログインセッションテーブルへの検索なので、GtlUtils.getSystemTimestamp()ではなく、System.currentTimeMillis()を使用する
                    Timestamp l_now = new Timestamp(System.currentTimeMillis());
                    String l_strNow = WEB3DateUtility.formatDate(l_now, DATE_FORMAT);
                    
                    //ループ
                    while (true)
                    {
                        //ログインセッションテーブルの検索の準備
                        //[条件]
                        //サブクエリを実行する
                        //session_id in
                        //(select session_id from
                        //     (select session_id
                        //      from login_session
                        //      where 有効フラグ = 有効
                        //      and 失効日付 > システム時刻
                        //      and ログインタイプID in getTypeId()の一覧
                        //      and ログインID => this.リクエストデータ.From口座ID
                        //      and ログインID <= this.リクエストデータ.To口座ID
                        //      order by session_id
                        //      )
                        //where rownum > 処理単位*ループ数[0..n]
                        //and rownum <= 処理単位*(ループ数[0..n]+1)
                        //)
                        Long l_rownumFrom = new Long(l_searchUnits * l_execCount);
                        Long l_rownumTo = new Long(l_searchUnits * (++l_execCount));
                        l_sbWhere = new StringBuffer();
                        l_sbWhere.append("session_id in (select session_id from (select rownum as rowno,session_id from login_session where");
                        l_sbWhere.append(" validity = ?");
                        l_sbWhere.append(" and to_char(expiration_date,'YYYYMMDDHH24MISS') > ?");
                        l_sbWhere.append(" and type_id in (" +l_typeConditionWhere.toString()+ ")");
                        l_sbWhere.append(" and login_id >= ?");
                        l_sbWhere.append(" and login_id <= ?");
                        l_sbWhere.append(" order by session_id)");
                        l_sbWhere.append(" where rowno > ?");
                        l_sbWhere.append(" and rowno <= ? )");
    
                        l_objList = new ArrayList();
                        l_objList.add(WEB3AdminFPTForceLogoutValidityDef.INT_VALIDITY_VALID_USER_LOGOUT);
                        l_objList.add(l_strNow);
                        l_objList.addAll(l_typeConditionList);
                        l_objList.add(requestData.accountIdFrom);
                        l_objList.add(requestData.accountIdTo);
                        l_objList.add(l_rownumFrom);
                        l_objList.add(l_rownumTo);
                        
                        l_vars = l_objList.toArray();
                        
                        //ログインセッションテーブルを検索
                        List l_loginSessionList = l_qp.doFindAllQuery(LoginSessionRow.TYPE, l_sbWhere.toString(), null,null,l_vars,l_rowType);
    
                        //ログインセッションテーブルの検索件数分Loop
                        l_listCnt = l_loginSessionList.size();
                        for (int i = 0; i < l_listCnt; i++)
                        {
                            //ログアウト候補Mapにキー＝login_id、 値＝ログインセッションRowのListをセットする。
                            LoginSessionRow l_row = (LoginSessionRow) l_loginSessionList.get(i);
                            Long l_loginId = new Long(l_row.getLoginId());
                            List l_valueList = (List) l_logoutMap.get(l_loginId);
                            if (l_valueList == null)
                            {
                                l_valueList = new ArrayList();
                                l_logoutMap.put(l_loginId, l_valueList);
                            }
                            l_valueList.add(l_row);
                        }
                        //書面交付管理テーブルの検索を準備
                        //[条件]
                        //サブクエリを実行する
                        //account_id in
                        //(select login_id from
                        //     (select login_id
                        //      from login_session
                        //      where 有効フラグ = 有効
                        //      and 失効日付 > システム時刻
                        //      and ログインタイプID in getTypeId()の一覧
                        //      and ログインID => this.リクエストデータ.From口座ID
                        //      and ログインID <= this.リクエストデータ.To口座ID
                        //      order by session_id
                        //      )
                        //where rownum > 処理単位*ループ数[0..n]
                        //and rownum <= 処理単位*(ループ数[0..n]+1)
                        //)
                        //and 書面区分 in 管理者 書面未承諾 強制ログアウト対象銘柄条件.書面区分一覧
                        //and 銘柄コード in 管理者 書面未承諾 強制ログアウト対象銘柄条件.電子鳩銘柄コード一覧
                        //and 削除フラグ＝有効
                        //and 口座ID => this.リクエストデータ.From口座ID
                        //and 口座ID <= this.リクエストデータ.To口座ID
                        l_sbWhere = new StringBuffer();
                        l_sbWhere.append("account_id in (select login_id from (select rownum as rowno,login_id from login_session where");
                        l_sbWhere.append(" validity = ?");
                        l_sbWhere.append(" and to_char(expiration_date,'YYYYMMDDHH24MISS') > ?");
                        l_sbWhere.append(" and type_id in (" +l_typeConditionWhere.toString()+ ")");
                        l_sbWhere.append(" and login_id >= ?");
                        l_sbWhere.append(" and login_id <= ?");
                        l_sbWhere.append(" order by session_id)");
                        l_sbWhere.append(" where rowno > ?");
                        l_sbWhere.append(" and rowno <= ? )");
                        l_sbWhere.append(" and document_div in (");
    
                        l_objList = new ArrayList();
                        l_objList.add(WEB3AdminFPTForceLogoutValidityDef.INT_VALIDITY_VALID_USER_LOGOUT);
                        l_objList.add(l_strNow);
                        l_objList.addAll(l_typeConditionList);
                        l_objList.add(requestData.accountIdFrom);
                        l_objList.add(requestData.accountIdTo);
                        l_objList.add(l_rownumFrom);
                        l_objList.add(l_rownumTo);
    
                        for (int i = 0; i < requestData.forceLogoutProductCondition.documentDivArr.length; i++)
                        {
                            if (i != 0)
                            {
                                l_sbWhere.append(",");
                            }
                            l_sbWhere.append("?");
                            l_objList.add(requestData.forceLogoutProductCondition.documentDivArr[i]);
                        }
                        l_sbWhere.append(")");
                        l_sbWhere.append(" and product_code in (");
                        for (int i = 0; i < requestData.forceLogoutProductCondition.batoProductCodeArr.length; i++)
                        {
                            if (i != 0)
                            {
                                l_sbWhere.append(",");
                            }
                            l_sbWhere.append("?");
                            l_objList.add(requestData.forceLogoutProductCondition.batoProductCodeArr[i]);
                        }
                        l_sbWhere.append(")");
                        l_sbWhere.append(" and delete_flag = ?");
                        l_sbWhere.append(" and account_id >= ?");
                        l_sbWhere.append(" and account_id <= ?");
                        l_objList.add(BooleanEnum.FALSE);
                        l_objList.add(requestData.accountIdFrom);
                        l_objList.add(requestData.accountIdTo);
    
                        l_vars = l_objList.toArray();
    
                        //書面交付管理テーブルを検索
                        List l_docDeliMngList = l_qp.doFindAllQuery(DocDeliveryManagementRow.TYPE,l_sbWhere.toString(), null,null,l_vars,l_rowType);
                        //書面交付管理テーブルの検索結果数分Loop
                        l_listCnt = l_docDeliMngList.size();
                        for (int i = 0; i < l_listCnt; i++)
                        {
                            //書面ごと交付済アカウントMapにキー＝書面種類コード、値＝アカウントIDのListをセットする。
                            DocDeliveryManagementRow l_row = (DocDeliveryManagementRow) l_docDeliMngList.get(i);
                            Long l_accountId = new Long(l_row.getAccountId());
                            String l_docCateCode = l_row.getDocumentCategory();
                            List l_valueList = (List) l_deliveredAccMap.get(l_docCateCode);
                            if (l_valueList == null)
                            {
                                l_valueList = new ArrayList();
                                l_deliveredAccMap.put(l_docCateCode, l_valueList);
                            }
                            l_valueList.add(l_accountId);
                        }
    
                        //ログインセッションテーブルの検索結果.size()<処理単位の場合 break
                        if (l_loginSessionList.size() < l_searchUnits)
                        {
                            break;
                        }
                    }
    
                    if(log.ison())
                    {
                        log.debug("検索完了時 ログアウト候補=" + l_logoutMap.size());
                    }
                    
                    //ログアウト候補ある場合、継続
                    if (l_logoutMap.size() > 0)
                    {                
                        
                        //顧客マスターテーブルを検索する。
                        //[条件]
                        //・アカウントID IN ログインID一覧
                        //・and 口座ID => this.リクエストデータ.From口座ID
                        //・and 口座ID <= this.リクエストデータ.To口座ID
        
                        l_objList = new ArrayList();
        
                        //ログアウト候補MapのキーSetのサイズ分Loop
                        long l_cnt = 0;
                        boolean l_start = true;
                        for (Iterator iter = l_logoutMap.keySet().iterator(); iter.hasNext();)
                        {
                            Long l_loginId = (Long) iter.next();
                            //IN (ログインID,....)の文字列を作成する。
                            //検索単位ごとにテーブル検索を行う。
                            if (!l_start)
                            {
                                l_sbWhere.append(",");
                            }
                            else
                            {
                                l_sbWhere = new StringBuffer();
                                l_sbWhere.append(" account_id in (");
                                l_start = false;
                            }
                            l_sbWhere.append("?");
                            l_objList.add(l_loginId);
                            
                            if(++l_cnt==l_searchUnits || !iter.hasNext())
                            {
                                l_sbWhere.append(") and account_id >= ?");
                                l_sbWhere.append(" and account_id <= ?");
                
                                l_objList.add(requestData.accountIdFrom);
                                l_objList.add(requestData.accountIdTo);
                
                                l_vars = l_objList.toArray();
                
                                List l_accountList = l_qp.doFindAllQuery(MainAccountRow.TYPE, l_sbWhere.toString(), l_vars);
                                //顧客マスターの検索結果数分Loop
                                l_listCnt = l_accountList.size();
                                for (int i = 0; i < l_listCnt; i++)
                                {
                                    MainAccountRow l_row = (MainAccountRow) l_accountList.get(i);
                
                                    //アカウント情報Mapにキー＝アカウントID、値＝顧客オブジェクトをセットする。
                                    //              l_accMap.put(new Long(l_row.getAccountId()),new
                                    l_accMap.put(new Long(l_row.getAccountId()), new WEB3GentradeMainAccount(l_row));
                                }
                                l_cnt=0;
                                l_start=true;
                                l_objList.clear();
                            }
                        }
        
                        if(log.ison())
                        {
                            log.debug("検索完了時 アカウント情報=" + l_accMap.size());
                        }
        
                        //アカウント情報MapのキーSetのサイズ分Loop
                        for (Iterator iter = l_accMap.values().iterator(); iter.hasNext();)
                        {
                            WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount) iter.next();
        
                            Long l_accId = new Long(l_account.getAccountId());
        
                            //isログアウト必要＝false顧客
                            boolean l_isNeedLogout = false;
        
                            //書面ごと交付対象MapのキーSetのサイズ分Loop
                            for (Iterator iterator = l_docDeliveryMap.keySet().iterator(); iterator.hasNext();)
                            {
                                boolean l_isMustDeliver = false;
                                String l_docCateCode = (String) iterator.next();
                                //書面ごと交付対象Mapから交付対象を取得する。
                                String l_deliveryTarget = (String) l_docDeliveryMap.get(l_docCateCode);
        
                                //交付対象＝＝全顧客の場合
                                if (WEB3DeliveryTargetDef.ALL_ACCOUNT.equals(l_deliveryTarget))
                                {
                                    l_isMustDeliver = true;
                                }
                                //交付対象＝＝信用開設済顧客の場合
                                else if (WEB3DeliveryTargetDef.MARGIN_OPENED_ACCOUNT.equals(l_deliveryTarget))
                                {
                                    //信用口座開設済か判定する。
                                    //[引数]
                                    //弁済区分： "指定なし"
                                    if (l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
                                    {
                                        l_isMustDeliver = true;
                                    }
        
                                }
                                //交付対象＝＝先物・オプション開設済顧客の場合
                                else if (WEB3DeliveryTargetDef.FUTURE_OPTION_OPENED_ACCOUNT.equals(l_deliveryTarget))
                                {
                                    //is先物OP口座開設(先物)＝＝true 又は
                                    // is先物OP口座開設(オプション)＝＝trueの場合
                                    if (l_account.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES)
                                            || l_account.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION))
                                    {
                                        l_isMustDeliver = true;
                                    }
        
                                }
                                if (l_isMustDeliver)
                                {
                                    //書面ごと交付済アカウントMapからアカウントListを取得する。
                                    List l_accList = (List) l_deliveredAccMap.get(l_docCateCode);
        
                                    //アカウントList＝＝null 又は アカウントListにアカウントIDが含まれていない場合
                                    if (l_accList == null || !l_accList.contains(l_accId))
                                    {
                                        //isログアウト必要==true
                                        l_isNeedLogout = true;
                                        break;
                                    }
                                }
                            }
                            //isログアウト必要＝＝falseの場合(＝ログアウトが不要の場合)
                            if (! l_isNeedLogout)
                            {
                                //ログアウト候補Mapから現在のアカウントIDを削除する。
                                l_logoutMap.remove(l_accId);
                            }
        
                        }
        
                        if(log.ison())
                        {
                            log.debug("入力書面の交付対象口座と書面交付状況 比較後 ログアウト対象顧客数=" + l_logoutMap.size());
                        }
        
                        WEB3AdminFPTForceLogoutUnitService l_service = (WEB3AdminFPTForceLogoutUnitService) Services
                                .getService(WEB3AdminFPTForceLogoutUnitService.class);
        
                        //ログアウト候補Mapの値Setのサイズ分Loop
                        for (Iterator iter = l_logoutMap.values().iterator(); iter.hasNext();)
                        {
                            List l_valueList = (List) iter.next();
                            //ログインセッションRowのListのサイズ分Loop
                            for (Iterator iterator = l_valueList.iterator(); iterator.hasNext();)
                            {
                                //ログアウト実施セッションList.add(セッションID)
                                LoginSessionRow l_row = (LoginSessionRow) iterator.next();
                                l_logoutSessionList.add(new Long(l_row.getSessionId()));
                                try
                                {
                                    //ログアウト処理をする
                                    //[引数]
                                    //ログインセッションRow： 現在処理中のログインセッションRow
                                    l_service.logout(l_row);
                                    //処理件数++
                                    l_count++;
                                }
                                catch (WEB3BaseException e1)
                                {
                                    log.error("一件強制ログアウトエラー。", e1);
                                    //リトライ数＝＝0の場合、isエラー=true
                                    if (l_retryCount == 0)
                                    {
                                        l_isError = true;
                                    }
                                }
                            }
        
                        }
        
                        if(log.ison())
                        {
                            log.debug("ログアウト実施セッション数=" + l_logoutSessionList.size());
                            log.debug("ログアウト実施成功セッション数=" + l_count);
                        }
                        //リトライ数分Loop
                        for (int i = 0; i < l_retryCount; i++)
                        {
        
                            if(log.ison())
                            {
                                log.debug("リトライ 実施 index=" + i);
                            }
        
                            //リトライログアウト実施List
                            List l_retryList = new ArrayList();
                            //処理カウント 0で初期化
                            l_execCount = 0;
                            //システム時刻を取得
                            // ログインセッションテーブルへの検索なので、GtlUtils.getSystemTimestamp()ではなく、System.currentTimeMillis()を使用する
                            l_now = new Timestamp(System.currentTimeMillis());
                            l_strNow = WEB3DateUtility.formatDate(l_now, DATE_FORMAT);
                            //ループ
                            while (true)
                            {
                                //ログインセッションテーブルの検索の準備
                                //[条件]
                                //サブクエリを実行する
                                //session_id in
                                //(select session_id from
                                //     (select session_id
                                //      from login_session
                                //      where 有効フラグ = 有効
                                //      and 失効日付 > システム時刻
                                //      and ログインタイプID = getTypeId()
                                //      and ログインID => this.リクエストデータ.From口座ID
                                //      and ログインID <= this.リクエストデータ.To口座ID
                                //      order by session_id
                                //      )
                                //where rownum > 処理単位*ループ数[0..n]
                                //and rownum <= 処理単位*(ループ数[0..n]+1)
                                //)
                                Long l_rownumFrom = new Long(l_searchUnits * l_execCount);
                                Long l_rownumTo = new Long(l_searchUnits * (++l_execCount));
                                l_sbWhere = new StringBuffer();
                                l_sbWhere.append("session_id in (select session_id from (select rownum as rowno,session_id from login_session where");
                                l_sbWhere.append(" validity = ?");
                                l_sbWhere.append(" and to_char(expiration_date,'YYYYMMDDHH24MISS') > ?");
                                l_sbWhere.append(" and type_id in (" +l_typeConditionWhere.toString()+ ")");
                                l_sbWhere.append(" and login_id >= ?");
                                l_sbWhere.append(" and login_id <= ?");
                                l_sbWhere.append(" order by session_id)");
                                l_sbWhere.append(" where rowno > ?");
                                l_sbWhere.append(" and rowno <= ? )");
        
                                l_objList = new ArrayList();
                                l_objList.add(WEB3AdminFPTForceLogoutValidityDef.INT_VALIDITY_VALID_USER_LOGOUT);
                                l_objList.add(l_strNow);
                                l_objList.addAll(l_typeConditionList);
                                l_objList.add(requestData.accountIdFrom);
                                l_objList.add(requestData.accountIdTo);
                                l_objList.add(l_rownumFrom);
                                l_objList.add(l_rownumTo);
                                
                                l_vars = l_objList.toArray();
        
                                //ログインセッションテーブルを検索
                                List l_loginSessionList = l_qp.doFindAllQuery(LoginSessionRow.TYPE,l_sbWhere.toString(), null,null,l_vars,l_rowType);
        
                                //ログインセッションテーブルの検索結果数分Loop
                                l_listCnt = l_loginSessionList.size();
                                for (int j = 0; j < l_listCnt; j++)
                                {
                                    LoginSessionRow l_row = (LoginSessionRow) l_loginSessionList.get(j);
                                    Long l_sessionId = new Long(l_row.getSessionId());
                                    //ログアウト実施セッションListに現在のセッションIDが存在する場合
                                    if (l_logoutSessionList.contains(l_sessionId))
                                    {
                                        //リトライログアウト実施Listに現在のログインセッションRowを追加する。
                                        l_retryList.add(l_row);
                                    }
                                }
                                //ログインセッションテーブルの検索結果.size()<処理単位の場合
                                if (l_listCnt < l_searchUnits)
                                {
                                    break;
                                }
                            }
        
                            //リトライログアウト実施Listの数分Loop
                            l_listCnt = l_retryList.size();
                            for (int j = 0; j < l_listCnt; j++)
                            {
                                LoginSessionRow l_row = (LoginSessionRow) l_retryList.get(j);
                                try
                                {
                                    l_service.logout(l_row);
                                }
                                catch (WEB3BaseException e1)
                                {
                                    //エラー発生時は、ログ出力して続行
                                    log.error("一件強制ログアウトエラー。", e1);
                                    //最後のLoopの場合、isエラー=true
                                    if (i == l_retryCount - 1)
                                    {
                                        l_isError = true;
                                    }
                                }
                            }
        
                            if(log.ison())
                            {
                                log.debug("リトライ ログアウト実施数=" + l_retryList.size());
                                log.debug("リトライ ログアウト実施対象詳細=" + l_retryList);
                            }
        
                        }
                    }
                    else
                    {
                        if(log.ison())
                        {
                            log.debug("ログアウト候補なし 処理終了");
                        }
                    }
                }
                else
                {
                    if(log.ison())
                    {
                        log.debug("ログインタイプ検索結果なし　@処理終了");
                    }
                }
            }
            catch(Exception l_ex)
            {
                l_isError = true;
                log.error("強制ログアウト処理中に例外が発生しました。" + l_ex.getMessage(), l_ex);
            }

            //ThreadLocalのTIMESTAMP_TAGをnullでリセットする。
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

            //テーブルを更新する。
            //[引数]
            //arg0： 管理者 書面未承諾 強制ログアウト実行結果TransactionCallbackの戻り値のコピーに値をセットしたもの
            //書面未承諾 強制ログアウト実行結果テーブル
            //終了日時 ＝GtlUtils.getSystemTimestamp()
            //処理件数 ＝処理件数
            //実行ステータス区分＝isエラー ? エラー : 処理済
            //更新日付 ＝終了日時
            DocForceLogoutRunStatusParams l_execParam = new DocForceLogoutRunStatusParams(l_exeResultRow);
            l_execParam.setEndTimestamp(GtlUtils.getSystemTimestamp());
            l_execParam.setProcessCount(l_count);
            l_execParam.setStatus(l_isError ? WEB3DocForceLogoutRunStatusStatusDef.PROCESS_ERROR : WEB3DocForceLogoutRunStatusStatusDef.PROCESSED);
            l_execParam.setLastUpdatedTimestamp(l_execParam.getEndTimestamp());

            l_qp.doUpdateQuery(l_execParam);

            //(*)排他ロックしたデーモントリガーテーブルのレコードを"未稼働"でupdateする。
            //デーモントリガーテーブル
            //　@　@処理状態 ＝未稼働
            //　@　@最終処理日時 ＝GtlUtils.getSystemTimestamp()
            DaemonTriggerParams l_daemonParam = new DaemonTriggerParams(l_daemonRow);
            l_daemonParam.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            l_daemonParam.setTriggerDate(GtlUtils.getSystemTimestamp());

            l_qp.doUpdateQuery(l_daemonParam);

        }
        catch (DataNetworkException l_dataException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dataException.getMessage(), l_dataException);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataCallbackException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dataCallbackException.getMessage(), l_dataCallbackException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dataQueryException.getMessage(), l_dataQueryException);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(), this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        finally
        {
            //clearAccountIdCtx( )
            if (l_racService != null)
            {
                l_racService.clearAccountIdCtx();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * getプロパティ
     * 
     * １）金商法@共通.getシステムプリファ@レンス(引数.プロパティ・キー)
     * 
     * ２）値の判定 １）の値 != nullの場合、１）の値をlongにして返す。 それ以外の場合、デフォルト値を返す。
     * 
     * @@param プロパティ・キー -
     *            プロパティ・キー
     * @@param デフォルト値 -
     *            デフォルト値
     * @@return long
     * @@roseuid 47D758C00306
     */
    public long getProperties(String l_proKey, long l_defaultValue)
    {
        String l_value = null;
        try
        {
            l_value = WEB3AdminFPTCommon.getSystemPreferences(l_proKey);
            if (l_value != null)
            {
                return Long.valueOf(l_value).longValue();
            }
            else
            {
                return l_defaultValue;
            }
        }
        catch (WEB3BaseException e)
        {
            log.warn("プロパティを取得するとき、エラーが発生。デフォールト値を使用。", e);
            return l_defaultValue;
        }
        catch (NumberFormatException e)
        {
            log.warn("プロパティをlongに変更するとき、エラーが発生。デフォールト値を使用。", e);
            return l_defaultValue;
        }
    }

    /**
     * 管理者 書面未承諾 強制ログアウトデーモントリガーTransactionCallback
     * （トランザクション属性：TX_JOIN_EXISTING）
     * 
     * @@author 孫
     * @@version 1.0
     */
    public class WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback implements TransactionCallback
    {

        /**
         * スレッドNo
         */
        private long threadNo;

        /**
         * @@roseuid 47DF4FE4020C
         */
        public WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback()
        {

        }

        /**
         * コンストラクタ。
         * 
         * 引数を自身の同名項目にセットする。
         * 
         * @@param スレッドNo -
         *            スレッドNo
         * @@roseuid 47D656F0010C
         */
        public WEB3AdminFPTForceLogoutDaemonTrigerTransactionCallback(long l_threadNo)
        {
            this.threadNo = l_threadNo;
        }

        /**
         * this.スレッドNoに該当するデーモントリガーテーブルの レコードをロックする。
         * 
         * １） DB検索 以下の条件に該当するデーモントリガーテーブルの レコードを、"for update nowait"オプションで読み込む。
         * 
         * [条件] スレッド番号 = this.スレッドNo
         * 
         * ２） 検索結果を返却する。 ※検索結果が取得できなかった場合、処理スレッドの
         * 占有ロックに失敗した旨をERRORでログに出力し、nullを返却する。
         * 
         * @@return java.lang.Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 47D657BB009B
         */
        public java.lang.Object process() throws com.fitechlabs.xtrade.kernel.data.DataNetworkException,
                com.fitechlabs.xtrade.kernel.data.DataQueryException, com.fitechlabs.xtrade.kernel.data.DataCallbackException
        {
            //１） DB検索
            //以下の条件に該当するデーモントリガーテーブルの
            //レコードを、"for update nowait"オプションで読み込む。
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            String l_strWhere = " thread_no = ? ";
            String l_strCondition = " for update nowait ";
            Object l_bindVars[] = { new Long(threadNo) };
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(DaemonTriggerRow.TYPE, l_strWhere, l_strCondition, l_bindVars);

            //２） 検索結果を返却する。
            //検索結果が取得できなかった場合、処理スレッドの<BR>
            // 占有ロックに失敗した旨をERRORでログに出力し、nullを返却する。
            if (l_lisRows.isEmpty())
            {
                log.error("処理スレッドの占有ロックに失敗した");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_lisRows.get(0);
            }
        }
    }

    /**
     * 管理者 書面未承諾 強制ログアウト実行結果TransactionCallback
     * 
     * @@author 孫
     * @@version 1.0
     */
    public class WEB3AdminFPTForceLogoutExecuteResultTransactionCallback implements TransactionCallback
    {

        /**
         * 証券会社コード
         */
        private String institutionCode;

        /**
         * From口座ID
         */
        private long accountIdFrom;

        /**
         * To口座ID
         */
        private long accountIdTo;

        /**
         * 書面種類コード一覧
         */
        private String[] documentCategoryArr;

        /**
         * 管理者
         */
        private WEB3Administrator admin;

        /**
         * @@roseuid 47DF4FE4024A
         */
        public WEB3AdminFPTForceLogoutExecuteResultTransactionCallback()
        {

        }

        /**
         * コンストラクタ。
         * 
         * 引数を自身の同名項目にセットする。
         * 
         * @@param l_institutionCode -
         *            証券会社コード
         * @@param l_fromAccountID -
         *            From口座ID
         * @@param l_toAccountID -
         *            To口座ID
         * @@param l_docCatDivArr -
         *            書面種類コード一覧
         * @@param l_admin -
         *            管理者
         * @@roseuid 47D658D00103
         */
        public WEB3AdminFPTForceLogoutExecuteResultTransactionCallback(String l_institutionCode, long l_fromAccountID, long l_toAccountID,
                String[] l_docCatDivArr, WEB3Administrator l_admin)
        {
            institutionCode = l_institutionCode;
            accountIdFrom = l_fromAccountID;
            accountIdTo = l_toAccountID;
            documentCategoryArr = l_docCatDivArr;
            admin = l_admin;
        }

        /**
         * 指定内容で書面未承諾 強制ログアウト実行結果テーブルを検索し、 １レコードの登録（データなし時）／更新（データあり時）を行う。
         * 
         * １） DB検索 指定内容で、書面未承諾 強制ログアウト実行結果テーブルを検索する。
         * 
         * ----------------------------------------------------- ＜検索条件＞ 証券会社コード =
         * this.証券会社コード かつ From口座ID = this.From口座ID
         * -----------------------------------------------------
         * 
         * ２） 該当データの有無により、以下の通り分岐する。
         * 
         * ２－１） 該当データなしの場合
         * 
         * ・this.書面未承諾 強制ログアウト実行結果Rowを生成する。 証券会社コード ＝this.証券会社コード From口座ID
         * ＝this.From口座ID To口座ID ＝this.To口座ID 開始日時
         * ＝GtlUtils.getSystemTimestamp() 終了日時 ＝null
         * 書面種類コード一覧＝this.書面種類コード一覧のコンマ区切り 処理件数 ＝null 実行ステータス区分＝処理中 更新者コード
         * ＝管理者.getAdministratorCode() 作成日付 ＝開始日時 更新日付 ＝開始日時
         * 
         * ・インサートする。
         * 
         * ２－２） 該当データありの場合
         * 
         * ・取得したレコード.実行ステータス区分 = "処理中"の場合は、 「指定AP起動中（二重起動エラー）」の例外をthrowする。
         * 
         * ・取得したレコード.実行ステータス区分 != "処理中"の場合は、以下の処理を行う。 書面未承諾
         * 強制ログアウト実行結果Rowを生成する。コンストラクタ(取得したレコード) To口座ID ＝this.To口座ID 開始日時
         * ＝GtlUtils.getSystemTimestamp() 終了日時 ＝null
         * 書面種類コード一覧＝this.書面種類コード一覧のコンマ区切り 処理件数 ＝null 実行ステータス区分＝処理中 更新者コード
         * ＝管理者.getAdministratorCode() 作成日付 ＝開始日時 更新日付 ＝開始日時
         * 
         * ・アップデートする。
         * 
         * ３）書面未承諾 強制ログアウト実行結果Rowを返す。
         * 
         * @@return java.lang.Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 47D65DA901AC
         */
        public java.lang.Object process() throws com.fitechlabs.xtrade.kernel.data.DataNetworkException,
                com.fitechlabs.xtrade.kernel.data.DataQueryException, com.fitechlabs.xtrade.kernel.data.DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            DocForceLogoutRunStatusRow l_resultRow = null;

            StringBuffer l_docSb = new StringBuffer();
            for (int i = 0; i < documentCategoryArr.length; i++)
            {
                if (i != 0)
                {
                    l_docSb.append(",");
                }
                l_docSb.append(documentCategoryArr[i]);
            }

            // １） DB検索
            // 　@指定内容で、書面未承諾 強制ログアウト実行結果テーブルを検索する。
            // 　@-----------------------------------------------------
            // 　@＜検索条件＞
            // 　@　@　@証券会社コード = this.証券会社コード
            // 　@かつ From口座ID = this.From口座ID
            // 　@-----------------------------------------------------
            String l_strWhere = " institution_code = ? and account_id_from = ?";
            Object l_bindVars[] = { institutionCode, new Long(accountIdFrom) };
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(DocForceLogoutRunStatusRow.TYPE, l_strWhere, l_bindVars);

            // ２－１） 該当データなしの場合
            if (l_lisRows.size() < 1)
            {
                //・this.書面未承諾 強制ログアウト実行結果Rowを生成する。
                // 　@　@証券会社コード ＝this.証券会社コード
                // 　@　@From口座ID ＝this.From口座ID
                // 　@　@To口座ID ＝this.To口座ID
                // 　@　@開始日時 ＝GtlUtils.getSystemTimestamp()
                // 　@　@終了日時 ＝null
                // 　@　@書面種類コード一覧＝this.書面種類コード一覧のコンマ区切り
                // 　@　@処理件数 ＝null
                // 　@　@実行ステータス区分＝処理中
                // 　@　@更新者コード ＝管理者.getAdministratorCode()
                // 　@　@作成日付 ＝開始日時
                // 　@　@更新日付 ＝開始日時
                // 　@・インサートする。
                DocForceLogoutRunStatusParams l_param = new DocForceLogoutRunStatusParams();
                l_param.setInstitutionCode(institutionCode);
                l_param.setAccountIdFrom(accountIdFrom);
                l_param.setAccountIdTo(accountIdTo);
                l_param.setStartTimestamp(GtlUtils.getSystemTimestamp());
                l_param.setEndTimestamp(null);
                l_param.setDocumentCategoryList(l_docSb.toString());
                l_param.setProcessCount(null);
                l_param.setStatus(WEB3DocForceLogoutRunStatusStatusDef.PROCESSING);
                l_param.setLastUpdater(admin.getAdministratorCode());
                l_param.setCreatedTimestamp(l_param.getStartTimestamp());
                l_param.setLastUpdatedTimestamp(l_param.getStartTimestamp());

                l_queryProcesser.doInsertQuery(l_param);
                l_resultRow = l_param;
            }
            // ２－２） 該当データありの場合
            else
            {
                DocForceLogoutRunStatusRow l_row = (DocForceLogoutRunStatusRow) l_lisRows.get(0);
                //　@・取得したレコード.実行ステータス区分 = "処理中"の場合は、
                //　@「指定AP起動中（二重起動エラー）」の例外をthrowする。
                if (WEB3DocForceLogoutRunStatusStatusDef.PROCESSING.equals(l_row.getStatus()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_01992, this.getClass().getName() + "." + STR_METHOD_NAME,
                            "書面未承諾 強制ログアウト実行結果 処理中。");
                }
                //　@・取得したレコード.実行ステータス区分 != "処理中"の場合は、以下の処理を行う。
                // 　@　@書面未承諾
                //     強制ログアウト実行結果Rowを生成する。コンストラクタ(取得したレコード)
                // 　@　@　@To口座ID ＝this.To口座ID
                // 　@　@　@開始日時 ＝GtlUtils.getSystemTimestamp()
                // 　@　@　@終了日時 ＝null
                // 　@　@　@書面種類コード一覧＝this.書面種類コード一覧のコンマ区切り
                // 　@　@　@処理件数 ＝null
                // 　@　@　@実行ステータス区分＝処理中
                // 　@　@　@更新者コード ＝管理者.getAdministratorCode()
                // 　@　@　@作成日付 ＝開始日時
                // 　@　@　@更新日付 ＝開始日時
                else
                {
                    DocForceLogoutRunStatusParams l_param = new DocForceLogoutRunStatusParams(l_row);
                    l_param.setAccountIdTo(accountIdTo);
                    l_param.setStartTimestamp(GtlUtils.getSystemTimestamp());
                    l_param.setEndTimestamp(null);
                    l_param.setDocumentCategoryList(l_docSb.toString());
                    l_param.setProcessCount(null);
                    l_param.setStatus(WEB3DocForceLogoutRunStatusStatusDef.PROCESSING);
                    l_param.setLastUpdater(admin.getAdministratorCode());
                    l_param.setCreatedTimestamp(l_param.getStartTimestamp());
                    l_param.setLastUpdatedTimestamp(l_param.getLastUpdatedTimestamp());

                    l_queryProcesser.doUpdateQuery(l_param);
                    l_resultRow = l_param;
                }

            }

            //３）書面未承諾 強制ログアウト実行結果Rowを返す。
            log.exiting(STR_METHOD_NAME);
            return l_resultRow;
        }
    }
}@
