head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ロック顧客登録問合せ一覧ｻｰﾋﾞｽ実装クラス (WEB3AdminAccInfoLockAccountListImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.WEB3AccInfoLockAccYAccRegisterRelease;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3AccInfoSearchCondTypeDef;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfoUnit;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLockAccountListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MngLockDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者お客様情報ロック顧客登録問合せ一覧ｻｰﾋﾞｽ実装クラス )<BR>
 * 管理者お客様情報ロック顧客登録問合せ一覧ｻｰﾋﾞｽ実装クラス <BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountListServiceImpl extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoLockAccountListService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLockAccountListServiceImpl.class);
    
    /**
     * ロック顧客登録問合せ一覧処理を行う。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報ロック顧客問合せ一覧入力リクエストの場合 <BR>
     * －get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報ロック顧客問合せ一覧リクエストの場合 <BR>
     * －get一覧画面()をコールする。 <BR>
     * @@param l_request<BR>
     * @@throws WEB3BaseException<BR>
     * @@return WEB3GenResponse<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAccInfoLockAccountSearchInputRequest)
        {
            l_response = this.getInputScreenDisplay((WEB3AdminAccInfoLockAccountSearchInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoLockAccountSearchListRequest)
        {
            l_response = this.getLockAccountRegistList((WEB3AdminAccInfoLockAccountSearchListRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * ロック顧客登録問合せ一覧入力画面表示処理を行う。<BR> 
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（ロック顧客登録問合せ一覧）get入力画面」参照。<BR>
     * @@param l_request<BR>
     * @@throws WEB3BaseException<BR>
     * @@return WEB3AdminAccInfoLockAccountSearchInputResponse  <BR>
     */
    protected WEB3AdminAccInfoLockAccountSearchInputResponse getInputScreenDisplay(WEB3AdminAccInfoLockAccountSearchInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreenDisplay(WEB3AdminAccInfoLockAccountSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //ログイン情報より管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //[validate権限()に指定する引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.顧客基本情報（基本）
        //is更新：　@false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        
        //レスポンスデータを生成する。
        WEB3AdminAccInfoLockAccountSearchInputResponse l_response = 
            (WEB3AdminAccInfoLockAccountSearchInputResponse)l_request.createResponse();
        
        //レスポンスデータプロパティに値をセットする。
        //(*)プロパティセット
        //レスポンスデータプロパティに以下の通り、値をセットする。
        //登録日（自）：　@TradingSystem.getSystemTimestamp()の１ヶ月前（*1)
        //　@登録日（至）：　@TradingSystem.getSystemTimestamp()
        Timestamp l_TimSystem = GtlUtils.getSystemTimestamp();
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_TimSystem);
        l_calendar.add(Calendar.MONTH, -1);
        Date l_datSystemFrom = l_calendar.getTime();
        
        l_response.registDateFrom = l_datSystemFrom ;        
        l_response.registDateTo = l_TimSystem;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getロック顧客登録問合せ一覧)<BR>
     * ロック顧客登録問合せ一覧表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（ロック顧客登録問合せ一覧）getロック顧客登録問合せ一覧」参照。<BR>
     * @@param l_request<BR>
     * @@throws WEB3BaseException<BR>
     * @@return WEB3AdminAccInfoLockAccountSearchListResponse  <BR>
     */
    protected WEB3AdminAccInfoLockAccountSearchListResponse getLockAccountRegistList(WEB3AdminAccInfoLockAccountSearchListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLockAccountRegistList(WEB3AdminAccInfoLockAccountSearchListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1リクエストデータの整合性をチェックする。         
        l_request.validate();
        
        //1.2ログイン情報より管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3管理者の権限チェックを行う。
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        //1.4部店権限をチェックする。
        l_administrator.validateBranchPermission(l_request.branchCode);
        //1.5証券会社コードを取得する。 
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        //1.6
        /*
         * 検索条件文字列を編集する。
         * [create検索条件文字列()に指定する引数] 
         * 部店コード[]：　@リクエストデータ.部店コード[] 
         * 登録日（自）：　@リクエストデータ.登録日（自） 
         * 登録日（至）：　@リクエストデータ.登録日（至）
         * 検索条件区分：　@リクエストデータ.検索条件区分
         */
        String l_strQueryString = this.createQueryString(l_request.branchCode, 
            l_request.registDateFrom, 
            l_request.registDateTo,
            l_request.searchCondType);
        
        //1.7
        /*
         * 検索条件データコンテナを編集する。 
         * [create検索条件データコンテナ()に指定する引数] 
         * 証券会社コード：　@get証券会社コード() 
         * 部店コード[]：　@リクエストデータ.部店コード[] 
         * 開始日：　@リクエストデータ.開始日 
         * 終了日：　@リクエストデータ.終了日 
         * 検索条件区分：　@リクエストデータ.検索条件区分
         */
        List l_lisQueryContainer = this.createQueryContainer(l_strInstitutionCode, 
            l_request.branchCode, 
            l_request.registDateFrom, 
            l_request.registDateTo,
            l_request.searchCondType);
        
        //1.8
        /*
         * ソート条件を編集する。
         * [createソート条件()に指定する引数]
         * ソートキー：リクエストデータ.ソートキー
         */
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.9ロック客Y客登録解除一覧List（：ArrayList）生成
        List l_lisLockAccount = new ArrayList();
        
        /*1.10ロック顧客登録一覧を取得する。
         *[getロック登録顧客一覧()に指定する引数] 
         *    検索条件文字列：　@create検索条件文字列()の戻り値 
         *    検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値 
         *    検索条件ソート条件：　@createソート条件()の戻り値
         * 
         */     
        String[] l_strQueryContainer = new String [l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainer);
        List l_lisRegistAcc = 
            this.getLockRegistAccList(l_strQueryString, l_strQueryContainer, l_strSortCond);
        
        if (l_lisRegistAcc != null)
        {
            //1.11getロック登録顧客一覧( )の戻り値の各要素毎のLOOP
            for (int i = 0; i < l_lisRegistAcc.size(); i++)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRegistAcc.get(i);
                WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountRow);
                
                /*1.11.1
                 * 停止情報一覧Unitメッセージデータを作成する。
                 * 
                 * [create停止情報一覧Unit()に指定する引数] 
                 * 顧客：　@getロック登録顧客一覧（）の戻り値の各要素
                 */
                WEB3AccInfoStopInfoUnit l_stopInfoUnit = this.createStopInfoListUnit(l_mainAccount);
                //1.11.2
                l_lisLockAccount.add(l_stopInfoUnit);
            }
            
        }
        
        //1.12レスポンスデータを生成する。
        WEB3AdminAccInfoLockAccountSearchListResponse l_response = 
            (WEB3AdminAccInfoLockAccountSearchListResponse)l_request.createResponse();
        //1.13メッセージ (*)プロパティセット
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_lisLockAccount,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));
                
        l_response.stopInfoList =
            (WEB3AccInfoStopInfoUnit[]) l_pageIndexInfo.getArrayReturned(
                WEB3AccInfoStopInfoUnit.class);
        
        //レスポンス.総ページ数
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        //レスポンス.総レコード数
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        //レスポンス.表示ページ番号 
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();  
        
        //レスポンスデータを生成する。
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を編集する。 <BR> 
     * <BR>
     * １）　@戻り値生成  <BR>
     * 　@戻り値の検索条件文字列インスタンス（：String）を生成  <BR>
     * <BR>
     * ２）　@証券会社条件追加 <BR>
     * 　@証券会社コード条件を追加する。 <BR>
     * <BR>
     * 　@" institution_code =  ? " <BR>
     * <BR>
     * ３）　@部店条件追加 <BR>
     * 　@部店条件を追加する。部店コード[]の要素数分"?"を編集する。 <BR>
     * <BR>
     * 　@" and branch_code in (?, ?,,,) " <BR>
     * <BR>
     * ４）　@登録日（自），登録日（至）条件追加  <BR>
     * 　@開始日，終了日が指定されている場合（開始日 != null && 終了日 != null）、 <BR>
     * 　@申込日の範囲指定を文字列インスタンスに追加する。 <BR>
     * <BR>
     * 　@" and to_char(enable_order_updated_timestamp,'YYYYMMDD') " + <BR>
     * 　@" between ? and ? " 
     * <BR>
     * ５）　@検索条件区分追加 <BR>
     * 　@検索条件区分が指定されている場合（検索条件 != null）、<BR> 
     * 　@検索条件区分指定を文字列インスタンスに追加する。 <BR>
     * <BR>
     * 　@－検索条件区分 == ”1：Y客”の場合 <BR>
     * 　@　@　@" and yellow_customer = ? "<BR> 
     * <BR>
     * 　@－検索条件区分 == ”2：管理ロック”の場合 <BR>
     * 　@　@　@" and mng_lock_flag = ? " <BR>
     * <BR>
     * 　@－検索条件区分 == ”3：支店ロック”の場合 <BR>
     * 　@　@　@" and branch_lock = ? " <BR>
     * <BR>
     * 　@－検索条件区分 == ”0：全て”の場合 <BR>
     * 　@　@　@" and (yellow_customer = ? " + <BR>
     *       " or mng_lock_flag = ? " + <BR>
     *       " or  branch_lock = ? ) "  <BR>
     * <BR>
     * ６）　@文字列インスタンスを返却 <BR>
     * <BR>
     * @@param l_strBranchCodes - 部店コード配列<BR>
     * @@param l_datRegistDateFrom - 登録日（自）<BR>
     * @@param l_datRegistDateTo - 登録日（至）<BR>
     * @@param l_strSearchCondType - 検索条件区分<BR>
     * @@return String
     */
    protected String createQueryString(String[] l_strBranchCodes,
        Date l_datRegistDateFrom, Date l_datRegistDateTo, String l_strSearchCondType)
    {
        
        final String STR_METHOD_NAME = " createQueryString(String[] l_strBranchCodes, Date l_datRegistDateFrom, Date l_datRegistDateTo, String l_strSearchCondType)";
        log.entering(STR_METHOD_NAME);
        //１）　@戻り値生成  <BR>
        //* 　@戻り値の検索条件文字列インスタンス（：String）を生成
        String l_strSearchCond;
        
       /*
        * ２）　@証券会社条件追加<BR>
        * 　@証券会社コード条件を追加する。
        * <BR>
        * 　@" institution_code =  ? "
        */
        l_strSearchCond = " institution_code = ? ";
        
        //部店条件を追加する。部店コード[]の要素数分"?"を編集する。
        
        if (l_strBranchCodes != null)
        {
            int l_intBranchCodesCnt = l_strBranchCodes.length;
            
            if (l_intBranchCodesCnt > 0)
            {
                StringBuffer l_sbQueryBranchCodes = new StringBuffer();
                                                
                for (int i = 0; i < l_intBranchCodesCnt; i++)
                {

                    if (i != 0)
                    {
                        l_sbQueryBranchCodes.append(", ");
                    }
                    l_sbQueryBranchCodes.append("?");
                    
                }                
                l_strSearchCond += " and branch_code in (" + l_sbQueryBranchCodes.toString() + ")";          
            }
        }

        
        /*
         * ４）　@登録日（自），登録日（至）条件追加  
         *      * 　@開始日，終了日が指定されている場合（開始日 != null && 終了日 != null）、 
         *      * 　@申込日の範囲指定を文字列インスタンスに追加する。 
         */
        if (l_datRegistDateFrom != null && l_datRegistDateTo != null)
        {
            l_strSearchCond += " and to_char(enable_order_updated_timestamp,'YYYYMMDD') " + " between ? and ? " ;
        }
        
        /*５）　@検索条件区分追加 
         * 　@検索条件区分が指定されている場合（検索条件 != null）、 
         * 　@検索条件区分指定を文字列インスタンスに追加する。 
         */
        if (l_strSearchCondType != null)
        {
            /*
             *－検索条件区分 == ”1：Y客”の場合 
             * " and yellow_customer = ? " 
             *         
             */
            if (WEB3AccInfoSearchCondTypeDef.YACCOUNT.equals(l_strSearchCondType))
            {
                l_strSearchCond +=" and yellow_customer = ?";
            }
            
            /*
             * －検索条件区分 == ”2：管理ロック”の場合 
             *" and mng_lock_flag = ? " 
             */
            else if (WEB3AccInfoSearchCondTypeDef.ADMINLOCK.equals(l_strSearchCondType))
            {
                l_strSearchCond +=" and mng_lock_flag = ?";
            }
            
            /*
             * －検索条件区分 == ”3：支店ロック”の場合 
             * " and branch_lock = ? " 
             */
            else if (WEB3AccInfoSearchCondTypeDef.BRANCHLOCK.equals(l_strSearchCondType))
            {
                l_strSearchCond +=" and branch_lock = ?";
            }
            
           /* －検索条件区分 == ”0：全て”の場合 
            * 　@　@　@" and (yellow_customer = ? " + 
            *       " or mng_lock_flag = ? " + 
            *       " or  branch_lock = ? ) " 
            */
            else if (WEB3AccInfoSearchCondTypeDef.ALL.equals(l_strSearchCondType))
            {
                l_strSearchCond +=" and (yellow_customer = ?" + " or mng_lock_flag = ?" + " or  branch_lock = ?" + ")";
            }
        }
                
        log.exiting(STR_METHOD_NAME);
        return l_strSearchCond;
    }
    
    /**
     * (create検索データコンテナ )<BR>
     * 検索条件データコンテナを編集する。  <BR>
     * <BR>
     * １）　@戻り値生成  <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）を生成  <BR>
     * <BR>
     * ２）　@証券会社条件追加  <BR>
     * 　@証券会社コード文字列を追加する。  <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR> 
     * 　@証券会社コード<BR>  
     * <BR>
     * ３）　@部店条件追加  <BR>
     * 　@部店コード[]をすべて追加する。  <BR>
     * <BR>
     * 　@[add()に指定する引数]　@ <BR> 
     * 　@部店コード <BR>
     * <BR>
     * ４）　@登録日（自），登録日（至）条件追加  <BR>
     * 　@登録日（自），登録日（至）が指定されている場合（登録日（自） != null && 登録日（至） != null）、 <BR> 
     * 　@登録日（自），登録日（至）をリストに追加する。 <BR> 
     * <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@登録日（自）を文字列（yyyyMMdd）に変換した値  <BR>
     * <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@登録日（至）を文字列（yyyyMMdd）に変換した値  <BR>
     * <BR>
     * ５）　@検索条件区分追加 <BR>
     * 　@検索条件区分が指定されている場合（検索条件区分 != null）、 <BR>
     * 　@検索条件区分指定をリストに追加する。 <BR>
     * <BR>
     * 　@－検索条件区分 == ”1：Y客”の場合 <BR>
     * 　@”1:Y客”をリストに追加する。<BR> 
     * <BR>
     * 　@－検索条件区分 == ”2：管理ロック”の場合 <BR>
     * 　@”1:ロック”をリストに追加する。 <BR>
     * <BR>
     * 　@－検索条件区分 == ”3：支店ロック”の場合 <BR>
     * 　@”1:ロック"をリストに追加する。<BR> 
     * <BR>
     * 　@－検索条件区分 == ”0：全て”の場合 <BR>
     * 　@”1:Y客”、”1:ロック”、”1:ロック"をリストに追加する。<BR> 
     * <BR>
     * ６）　@配列を返却  <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。  <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strBranchCodes - 部店コード配列<BR>
     * @@param l_datRegistDateFrom - 登録日（自）<BR>
     * @@param l_datRegistDateTo - 登録日（至）<BR>
     * @@param l_strSearchCondType - 検索条件区分
     * @@throws WEB3BaseException
     * @@return List
     * @@roseuid 415103500340
     */
    protected List createQueryContainer(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        Date l_datRegistDateFrom, 
        Date l_datRegistDateTo,
        String l_strSearchCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryContainer()";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_strInstitutionCode] = " + l_strInstitutionCode
                );
        }
        
        //１）　@戻り値生成  <BR>
        //* 　@戻り値編集用インスタンス（：ArrayList）を生成戻り値編集用インスタンス（：ArrayList）を生成
        List l_lisQueryContainer = new ArrayList();
        
        //２）　@証券会社条件追加
        l_lisQueryContainer.add(l_strInstitutionCode);
        
        //３）　@部店条件追加             
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisQueryContainer.add(l_strBranchCodes[i]);
        }
        /*
         * ４）　@登録日（自），登録日（至）条件追加  
         *      * 　@登録日（自），登録日（至）が指定されている場合（登録日（自） != null && 登録日（至） != null）、 
         *      * 　@登録日（自），登録日（至）をリストに追加する。 
         *      * 
         *      * 　@[add()に指定する引数]  
         *      * 　@登録日（自）を文字列（yyyyMMdd）に変換した値  
         */
        if (l_datRegistDateFrom != null && l_datRegistDateTo != null)
        {
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datRegistDateFrom, "yyyyMMdd"));
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datRegistDateTo, "yyyyMMdd"));
        }
        
        if (l_strSearchCondType != null)
        {
            /*
             *－検索条件区分 == ”1：Y客”の場合 
             * " and yellow_customer = 1:Y客 " 
             *         
             */
            if (WEB3AccInfoSearchCondTypeDef.YACCOUNT.equals(l_strSearchCondType))
            {
                l_lisQueryContainer.add(WEB3YellowCustomerDef.YELLOW_CUSTOMER);
            }
            
            /*
             * －検索条件区分 == ”2：管理ロック”の場合 
             *" and mng_lock_flag = 1:ロック " 
             */
            else if (WEB3AccInfoSearchCondTypeDef.ADMINLOCK.equals(l_strSearchCondType))
            {
                l_lisQueryContainer.add(WEB3MngLockDef.LOCK);
            }
            
            /*
             * －検索条件区分 == ”3：支店ロック”の場合 
             * " and branch_lock = 1:ロック " 
             */
            else if (WEB3AccInfoSearchCondTypeDef.BRANCHLOCK.equals(l_strSearchCondType))
            {
                l_lisQueryContainer.add(WEB3MngLockDef.LOCK);
            }
            
           /* －検索条件区分 == ”0：全て”の場合 <BR>
            * 　@　@　@" and (yellow_customer = 1:Y客 " + 
            *       " or mng_lock_flag = 1:ロック " + 
            *       " or  branch_lock = 1:ロック ) " 
            */
            else if (WEB3AccInfoSearchCondTypeDef.ALL.equals(l_strSearchCondType))
            {
                l_lisQueryContainer.add(WEB3YellowCustomerDef.YELLOW_CUSTOMER);
                l_lisQueryContainer.add(WEB3MngLockDef.LOCK);
                l_lisQueryContainer.add(WEB3MngLockDef.LOCK);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisQueryContainer;     
    }
    
    /**
     * (create停止情報一覧Unit)<BR>
     * 引数のロック顧客登録オブジェクトより、停止情報一覧Unitメッセージデータを作成する。<BR> 
     * <BR>
     * １）　@停止情報一覧Unit生成 <BR>
     * 　@停止情報一覧Unitを生成する。 <BR>
     * <BR>
     * ２）　@プロパティセット <BR>
     * 　@１）にて生成した停止情報一覧Unitに以下のプロパティをセットする。 <BR>
     * <BR>
     * 　@停止情報一覧Unit.部店コード = 顧客.部店コード <BR>
     * 　@停止情報一覧Unit.顧客コード = 顧客.顧客コード <BR>
     * 　@停止情報一覧Unit.顧客名 = 顧客.名前（苗字）※顧客名（漢字）として使用する。<BR> 
     * 　@停止情報一覧Unit.管理者ID = 管理者.get管理者コード(*1)※取得できなかった場合、nullをセットする。 <BR>
     * 　@停止情報一覧Unit.登録日時 = 顧客.停止状況更新日時 <BR>
     * 　@停止情報一覧Unit.Y客区分 = 顧客.Y客区分 <BR>
     * 　@停止情報一覧Unit.管理ロック区分 = 顧客.管理ロック <BR>
     * 　@停止情報一覧Unit.管理ロック理由フラグ（立替金） = 顧客.管理ロック理由フラグ（立替金） <BR>
     * 　@停止情報一覧Unit.管理ロック理由フラグ（保証金未入） = 顧客.管理ロック理由フラグ（保証金未入） <BR>
     * 　@停止情報一覧Unit.管理ロック理由フラグ（適格担保不足） =　@顧客.管理ロック理由フラグ（適格担保不足） <BR>
     * 　@停止情報一覧Unit.管理ロック理由フラグ（預り長期未差替） =　@顧客.管理ロック理由フラグ（預り長期未差替） <BR>
     * 　@停止情報一覧Unit.管理ロック解除開始日 = 顧客.管理ロック解除開始日<BR> 
     * 　@停止情報一覧Unit.管理ロック解除終了日 = 顧客.管理ロック解除終了日 <BR>
     * 　@停止情報一覧Unit.支店ロック区分 = 顧客.支店ロック <BR>
     * 　@停止情報一覧Unit.注文認可区分 = 顧客.注文認可 <BR>
     * 　@停止情報一覧Unit.停止状況登録理由 = 顧客.停止状況登録理由 <BR>
     * 　@停止情報一覧Unit.ロック客登録解除SONAR受付状況 = <BR>
     * 　@　@－（ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*2) == null）の場合、null。<BR> 
     * 　@　@－以外、ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*2).get処理区分()。<BR> 
     * 　@停止情報一覧Unit.Ｙ客登録解除SONAR受付状況 = <BR>
     * 　@　@－（ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*3) == null）の場合、null。<BR> 
     * 　@　@－以外、ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*3).get処理区分()。<BR> 
     * <BR>
     * 　@　@（*1）AdministratorDao.findRowByInstitutionCodeAdministratorCode（証券会社コード，管理者コード）にて取得 <BR>
     * 　@　@　@　@　@証券会社コード：　@顧客.証券会社コード <BR>
     * 　@　@　@　@　@管理者コード：　@顧客.停止状況更新者コード 　@<BR>　@　@　@　@ 
     * <BR>
     * 　@　@（*2)（*3）[getロック客Ｙ客登録解除()に指定する引数]  <BR>
     * 　@　@　@　@　@　@　@　@顧客：　@顧客 <BR>
     * 　@　@　@　@　@　@　@　@データコード：　@(*2) ”GI846”(WEB3HostRequestCodeDef) ／ (*3) ”GI847”(WEB3HostRequestCodeDef) <BR>
     * <BR>
     * ３）プロパティセットした停止情報一覧Unitを返却する。<BR>
     *
     *@@param 顧客 - 顧客
     *@@throws WEB3BaseException
     *@@return WEB3AccInfoInsiderInfoUnit
     */
    protected WEB3AccInfoStopInfoUnit createStopInfoListUnit(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createInsiderInfoListUnit(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        //１）　@停止情報一覧Unit生成 
        //* 　@停止情報一覧Unitを生成する。 
        WEB3AccInfoStopInfoUnit l_stopInfoUnit = new WEB3AccInfoStopInfoUnit();

        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        //２）　@プロパティセット 
        //* 　@１）にて生成した停止情報一覧Unitに以下のプロパティをセットする。
        
        //停止情報一覧Unit.部店コード = 顧客.部店コード
        l_stopInfoUnit.branchCode = l_mainAccount.getBranch().getBranchCode();
        //停止情報一覧Unit.顧客コード = 顧客.顧客コード
        l_stopInfoUnit.accountCode = l_mainAccount.getDisplayAccountCode();
        //停止情報一覧Unit.顧客名 = 顧客.名前（苗字）※顧客名（漢字）として使用する。
        l_stopInfoUnit.accountName = l_mainAccountRow.getFamilyName();
        
        //停止情報一覧Unit.管理者ID = 管理者.get管理者コード(*1)※取得できなかった場合、nullをセットする。
        //（*1）AdministratorDao.findRowByInstitutionCodeAdministratorCode（証券会社コード，管理者コード）にて取得 
        //* 　@　@　@　@　@証券会社コード：　@顧客.証券会社コード 
        //* 　@　@　@　@　@管理者コード：　@顧客.停止状況更新者コード 　@
        AdministratorRow l_row = null;
        try
        {
            l_row = AdministratorDao.findRowByInstitutionCodeAdministratorCode(
                l_mainAccountRow.getInstitutionCode(), l_mainAccountRow.getEnableOrderLastUpdater());
        }
        catch (DataNetworkException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        if (l_row == null)
        {
            l_stopInfoUnit.managerID = null;
        }
        else
        {
            l_stopInfoUnit.managerID = l_row.getAdministratorCode();
        }

        //停止情報一覧Unit.登録日時 = 顧客.停止状況更新日時 
        l_stopInfoUnit.registDate = 
            l_mainAccountRow.getEnableOrderUpdatedTimestamp();
        
        //停止情報一覧Unit.Y客区分 = 顧客.Y客区分 
        l_stopInfoUnit.yellowAccountDiv = l_mainAccountRow.getYellowCustomer();
        //停止情報一覧Unit.管理ロック区分 = 顧客.管理ロック
        l_stopInfoUnit.mngLockDiv = l_mainAccountRow.getMngLockFlag();
        //停止情報一覧Unit.管理ロック理由フラグ（立替金） = 顧客.管理ロック理由フラグ（立替金）
        if (BooleanEnum.FALSE.equals(l_mainAccountRow.getMngLockFlagAdvance()))
        {
            l_stopInfoUnit.mngExpenseLockReasonFlag = false;
        }
        else
        {
            l_stopInfoUnit.mngExpenseLockReasonFlag = true;
        }
        //停止情報一覧Unit.管理ロック理由フラグ（保証金未入） = 顧客.管理ロック理由フラグ（保証金未入）
        if (BooleanEnum.FALSE.equals(l_mainAccountRow.getMngLockFlagUnpayMargin()))
        {
            l_stopInfoUnit.mngDepositLockReasonFlag = false;
        }
        else
        {
            l_stopInfoUnit.mngDepositLockReasonFlag = true;
        }
        //停止情報一覧Unit.管理ロック理由フラグ（適格担保不足） =　@顧客.管理ロック理由フラグ（適格担保不足）
        if (BooleanEnum.FALSE.equals(l_mainAccountRow.getMngLockFlagShortSecurity()))
        {
            l_stopInfoUnit.mngCollateralLockReasonFlag = false;
        }
        else
        {
            l_stopInfoUnit.mngCollateralLockReasonFlag = true;
        }
        //停止情報一覧Unit.管理ロック理由フラグ（預り長期未差替） =　@顧客.管理ロック理由フラグ（預り長期未差替）
        if (BooleanEnum.FALSE.equals(l_mainAccountRow.getMngLockFlagUnsubstitDepo()))
        {
            l_stopInfoUnit.mngReceiptLockReasonFlag = false;
        }
        else
        {
            l_stopInfoUnit.mngReceiptLockReasonFlag = true;
        }
        //停止情報一覧Unit.管理ロック解除開始日 = 顧客.管理ロック解除開始日
        l_stopInfoUnit.mngLockCancelStartDate = l_mainAccountRow.getMngLockOffStartDate();
        //停止情報一覧Unit.管理ロック解除終了日 = 顧客.管理ロック解除終了日
        l_stopInfoUnit.mngLockCancelEndDate = l_mainAccountRow.getMngLockOffEndDate();
        //停止情報一覧Unit.支店ロック区分 = 顧客.支店ロック
        l_stopInfoUnit.branchLockDiv = l_mainAccountRow.getBranchLock();
        //停止情報一覧Unit.注文認可区分 = 顧客.注文認可 
        l_stopInfoUnit.orderPermitDiv = l_mainAccountRow.getOrderPermission();
        //停止情報一覧Unit.停止状況登録理由 = 顧客.停止状況登録理由
        l_stopInfoUnit.stopStateRegistReason = l_mainAccountRow.getLockRegistrationReason();
        /*
         * 停止情報一覧Unit.ロック客登録解除SONAR受付状況 = 
         *      －（ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*2) == null）の場合、null。
         * 　@　@ －以外、ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*2).get処理区分()。
         */

        WEB3AccInfoLockAccYAccRegisterRelease l_registerRelease = 
            WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL);
        if (l_registerRelease == null)
        {
            l_stopInfoUnit.lockAccountSonarState = null;

        }
        else
        {
            l_stopInfoUnit.lockAccountSonarState = l_registerRelease.getStatus();

        }
        
        /*
         * 停止情報一覧Unit.Ｙ客登録解除SONAR受付状況 = 
         *      －（ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*3) == null）の場合、null。
         *      －以外、ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*3).get処理区分()。
         */
        WEB3AccInfoLockAccYAccRegisterRelease l_release = 
            WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL);
        if (l_release == null)
        {
            l_stopInfoUnit.yAccountSonarState = null;
        }
        else
        {
            l_stopInfoUnit.yAccountSonarState = l_release.getStatus();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_stopInfoUnit;
    }

    /**
     * (createソート条件)<BR>
     * ソート条件文字列を編集する。<BR>  
     * <BR>
     * テーブル列物理名を使用し、以下の通り、ソート条件文字列（order by句）を編集する。<BR>  
     * <BR>
     * １）パラメータ.ソートキーの要素数分以下の処理を繰り返し、<BR>  
     * 　@　@　@ソート条件文字列を作成する。 <BR> 
     * 　@１－１）ソートキーを編集  <BR>
     * 　@　@１－１－１）ソートキー = 登録日時の場合 <BR> 
     * 　@　@　@　@　@　@　@　@顧客マスタテーブル.停止状況更新日時 <BR>
     * <BR>
     * 　@　@１－１－２）ソート条件 = 顧客コードの場合  <BR>
     * 　@　@　@　@　@　@　@　@顧客マスタテーブル.顧客コード <BR> 
     * <BR>
     * 　@　@１－１－４）ソート条件 = 管理者ID  <BR>
     * 　@　@　@　@　@　@　@　@顧客マスタ.停止状況更新者コード <BR>
     * <BR>
     * 　@１－２）ソート条件に該当するソート条件を編集する。  <BR>
     * 　@　@　@　@　@昇順：asc  <BR>
     * 　@　@　@　@　@降順：desc  <BR>
     * <BR>
     * ２） 作成したソート条件文字列を返却する。<BR>  
     * @@param  l_sortKeys - ソートキー<BR>
     * @@return String<BR>
     * @@roseuid 4164C934006A
     */
    protected String createSortCond(WEB3AccInfoSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3AccInfoSortKey[] l_sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_sortKeys] = " + l_sortKeys
                );
        }
        
        StringBuffer l_sbQuery = new StringBuffer();
        
        int l_intLength = l_sortKeys.length;

        for (int i = 0; i < l_intLength; i++ )
        {
            
            if(WEB3AccInfoKeyItemDef.REGIST_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" enable_order_updated_timestamp ");
            }
            else if(WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" account_code ");
            }
            else if(WEB3AccInfoKeyItemDef.MANAGER_ID.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" enable_order_last_updater ");
            }
            else
            {
                continue;
            }
            String l_sort = null;
            if(WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sort = "Asc";
            }
            else
            {
                l_sort = "Desc";
            }
            l_sbQuery.append(l_sort);
            
            if(i < l_intLength - 1)
            {
                l_sbQuery.append(", ");
            }
                        
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sbQuery.toString();

    }
    
    /**
     * (getロック登録顧客一覧)<BR> 
     * 引数の検索条件に該当する顧客Paramsの一覧を返却する。 <BR> 
     * <BR> 
     * １）ArrayListを生成する。  <BR> 
     * <BR> 
     * ２）顧客行オブジェクトのListを取得する。 <BR>  
     * 　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>  
     * <BR> 
     * 　@　@[doFindAllQuery()に指定する引数]  <BR> 
     * 　@　@rowType：　@顧客Row.TYPE  <BR> 
     * 　@　@where：　@検索条件文字列  <BR> 
     * 　@　@orderBy：　@検索条件ソート条件 <BR> 
     * 　@　@conditions：　@null  <BR> 
     * 　@　@bindVars：　@検索条件データコンテナ  <BR> 
     * <BR> 
     * 　@　@検索結果が取得できた場合、検索結果より  <BR> 
     * 　@　@顧客インスタンスを生成し、ArrayListに追加する。<BR>   
     * 　@　@※検索結果の数分インスタンスを生成し、追加する。<BR>   
     * <BR> 
     * ４）ArrayListを返却する。<BR>   
     * 　@※ArrayListの要素数が0の場合、nullを返却する。<BR> 
     * <BR> 
     * @@param  String - 検索条件文字列<BR> 
     * @@param  String[] - 検索条件データコンテナ<BR> 
     * @@param  String - 検索条件ソート条件<BR> 
     * @@throws WEB3BaseException <BR> 
     * return List
     */
    public List getLockRegistAccList(String l_strSearchCond, String[] l_strBindVars, String l_strOrderBy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLockRegistAccList(String l_strSearchCond, String[] l_strBindVars, String l_strOrderBy)";
        log.entering(STR_METHOD_NAME);
        
        //１）ArrayListを生成する。 
        List l_lisRecords = null;

        try
        {

            //２）顧客行オブジェクトのListを取得する。

            /*
             * QueryProcessor.doFindAllQuery()メソッドをコールする。 
             *      [doFindAllQuery()に指定する引数]  
             *      rowType：　@顧客Row.TYPE   
             *      where：　@検索条件文字列  
             *      orderBy：　@検索条件ソート条件 
             *      conditions：　@null  
             *      bindVars：　@検索条件データコンテナ  
             */
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_strSearchCond,
                l_strOrderBy,
                null,
                l_strBindVars);
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //行が取得できなかった場合は、nullを返却する。 
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;

    }
}
@
