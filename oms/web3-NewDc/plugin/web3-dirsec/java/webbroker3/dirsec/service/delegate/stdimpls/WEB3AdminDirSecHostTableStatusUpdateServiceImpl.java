head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableStatusUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者キューテーブルステータス更新サービスImpl(WEB3AdminDirSecHostTableStatusUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 李俊(中訊) 新規作成
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.RowType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.WEB3AdminDirSecHostTableDataManager;
import webbroker3.dirsec.data.HostManagementRow;
import webbroker3.dirsec.define.WEB3AdminDirSecSortKeyItemDef;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableUnit;
import webbroker3.dirsec.message.WEB3AdminDirSecSortKey;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecHostTableStatusUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者キューテーブルステータス更新サービスImpl)<BR>
 * 管理者キューテーブルステータス更新サービス実装クラス。<BR>
 * 
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableStatusUpdateServiceImpl implements 
    WEB3AdminDirSecHostTableStatusUpdateService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
      private  static WEB3LogUtility log = WEB3LogUtility.getInstance(
              WEB3AdminDirSecHostTableStatusUpdateServiceImpl.class);
    
    /**
     * @@roseuid 442A1C85002E
     */
    public WEB3AdminDirSecHostTableStatusUpdateServiceImpl() 
    {
     
    }
    
    /**
     * 管理者・キューテーブルステータス更新処理を開始する。<BR>
     * <BR>
     * リクエストデータの型により、 <BR>
     * 以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * ○管理者・キューテーブル一覧リクエストの場合 <BR>
     * 　@this.getキューテーブル一覧()をコールする。 <BR>
     * <BR>
     * ○管理者・キューテーブル検索入力リクエストの場合 <BR>
     * 　@this.get検索条件入力画面()をコールする。<BR>
     * <BR>
     * ○管理者・キューテーブル検索結果リクエストの場合 <BR>
     * 　@this.get検索結果一覧()をコールする。<BR>
     * <BR>
     * ○管理者・キューテーブルステータス更新確認リクエストの場合 <BR>
     * 　@this.get更新確認画面()をコールする。<BR>
     * <BR>
     * ○管理者・キューテーブルステータス更新完了リクエストの場合 <BR>
     * 　@this.get更新完了画面()をコールする。<BR>
     * @@param l_request - リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4417DA7E03C0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        WEB3GenResponse l_response = null;
        
        //○管理者・キューテーブル一覧リクエストの場合  
        //this.getキューテーブル一覧()をコールする
        if (l_request instanceof WEB3AdminDirSecHostTableReferenceRequest)
        {
            l_response = this.getHostTableList(
                (WEB3AdminDirSecHostTableReferenceRequest)l_request);
        }
        
        //○管理者・キューテーブル検索入力リクエストの場合  
        //this.get検索条件入力画面()をコールする。
        else if (l_request instanceof WEB3AdminDirSecHostTableSearchInputRequest)
        {   
            l_response = this.getQueryConditionInputScreen(
                (WEB3AdminDirSecHostTableSearchInputRequest)l_request);
        }
        
        //○管理者・キューテーブル検索結果リクエストの場合  
        //this.get検索結果一覧()をコールする。 
        else if (l_request instanceof WEB3AdminDirSecHostTableSearchListRequest)
        {   
            l_response = this.getQueryResultList(
                (WEB3AdminDirSecHostTableSearchListRequest)l_request);
        }
        
        //○管理者・キューテーブルステータス更新確認リクエストの場合  
        //this.get更新確認画面()をコールする。
        else if (l_request instanceof WEB3AdminDirSecHostTableStatusConfirmRequest)
        {   
            l_response = this.getUpdateConfirmScreen(
                (WEB3AdminDirSecHostTableStatusConfirmRequest)l_request);
        }
        
        //○管理者・キューテーブルステータス更新完了リクエストの場合  
        //this.get更新完了画面()をコールする。
        else if (l_request instanceof WEB3AdminDirSecHostTableStatusCompleteRequest)
        {   
            l_response = this.getUpdateCompleteScreen(
                (WEB3AdminDirSecHostTableStatusCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
    
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getキューテーブル一覧)<BR>
     * 管理者キューテーブル一覧画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者）getキューテーブル一覧」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （管理者）getキューテーブル一覧       <BR>
     *         具体位置    :  1.4 isDIR管理者( )  <BR>
     *         DIR管理者権限チェック 。<BR>
     *        （isDIR管理者( ) == false の場合、<BR>
     *         [DIR管理者権限チェックエラー。]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     *  
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・キューテーブル一覧リクエストクラス。<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 4418B74F01EB
     */
    protected WEB3AdminDirSecHostTableReferenceResponse 
        getHostTableList(WEB3AdminDirSecHostTableReferenceRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getHostTableList(WEB3AdminDirSecHostTableReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数] 
        //機@能カテゴリコード：システム管理（キューテーブルステータス更新） 
        //is更新：true 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);
        
        //1.4 isDIR管理者( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR管理者権限チェックエラー。");
        }
        
        //1.5 createキューテーブル管理ソート条件(キューテーブルソートキー［］)
        //[createソート条件（）に指定する引数] 
        //キューテーブルソートキー　@：　@リクエストオブジェクト.キューテーブルソートキー 
        String l_strSortCondition = createHostManageSortCondition(l_request.sortKeys);
        
        //1.6 getキューテーブル一覧(String, String, Object［］)
        //[getキューテーブル一覧()に指定する引数] 
        //検索条件： null 
        //ソート条件：　@createソート条件（）で取得した値。 
        //検索条件データ：　@null
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        List l_lisHostTableList = l_hostTableDataManager.getHostTableList(
            null, l_strSortCondition, null);
        
        //1.7 WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[WEB3PageIndexInfo（）に指定する引数] 
        //l_list : getキューテーブル一覧（）の戻り値 
        //l_intRequestPageIndex :　@リクエストデータ.要求ページ番号をint型に変換した値 
        //l_intRequestPageSize :　@リクエストデータ.ページ内表示行数をint型に変換した値
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisHostTableList, l_intPageIndex, l_intPageSize);
        
        //1.8 getListReturned( )
        List l_lisPageIndexInfoResult = l_pageIndexInfo.getListReturned();
        
        //1.9 createキューテーブル一覧情報(List)
        //[createキューテーブル一覧情報（）に指定する引数] 
        //キューテーブル一覧List　@：　@getListReturned（）で取得した値。
        WEB3AdminDirSecHostTableUnit[] l_hostTableUnits = 
            l_hostTableDataManager.createHostTableListInfo(l_lisPageIndexInfoResult); 
        
        //1.10 getTotalPages( )
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.11 getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        // 1.12 createResponse( )
        WEB3AdminDirSecHostTableReferenceResponse l_response = 
            (WEB3AdminDirSecHostTableReferenceResponse) l_request.createResponse();
        
        // 1.13 （*）プロパティセット
        //総ページ数       = WEB3StringTypeUtility.formatNumber
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@(totalPages()の戻り値);
        //総レコード数     = WEB3StringTypeUtility.formatNumber
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@(totalSize()の戻り値);
        //表示ページ番号 = リクエストオブジェクト.表示ページ番号
        //
        //キューテーブル一覧 = createキューテーブル一覧情報()の戻り値
        l_response.totalPages = "" + l_intTotalPages;
        l_response.totalRecords = "" + l_intTotalRecords;
        l_response.pageIndex = l_request.pageIndex;
        l_response.hostTables = l_hostTableUnits;
        
        log.exiting(STR_METHOD_NAME);                
        return l_response;
    }
    
    /**
     * (get検索条件入力画面)<BR>
     * 管理者キューテーブルレコード検索画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者）get検索条件入力画面」参照。 <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （管理者）get検索条件入力画面       <BR>
     *         具体位置    :  1.4 isDIR管理者( )  <BR>
     *         DIR管理者権限チェック 。<BR>
     *        （isDIR管理者( ) == false の場合、<BR>
     *         [DIR管理者権限チェックエラー。]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・キューテーブル検索入力リクエストクラス。<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4418B93F03C0
     */
    protected WEB3AdminDirSecHostTableSearchInputResponse getQueryConditionInputScreen(
        WEB3AdminDirSecHostTableSearchInputRequest l_request) 
        throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = 
            " getQueryConditionInputScreen(WEB3AdminDirSecHostTableSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数] 
        //機@能カテゴリコード：システム管理（キューテーブルステータス更新） 
        //is更新：true 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //1.4 isDIR管理者( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR管理者権限チェックエラー。");
        }
        
        //1.5 get部店コード( )
        String l_strBranchCode = l_admin.getBranchCode();
        
        //1.6 getキューテーブル管理(String)
        //[getキューテーブル管理（）に指定する値] 
        //キューテーブル名　@：　@リクエストオブジェクト.キューテーブル名。
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        HostManagementRow l_hostManagementRow = 
            l_hostTableDataManager.getHostTableManagement(l_request.tableJpnName);
        
        //1.7 createResponse( )
        WEB3AdminDirSecHostTableSearchInputResponse l_response = 
            (WEB3AdminDirSecHostTableSearchInputResponse) l_request.createResponse();
        
        //1.8 (*)プロパティセット
        //テーブル名    = 　@ getキューテーブル管理（）の戻り値.getテーブル名（）
        l_response.tableJpnName = l_hostManagementRow.getHostTableName();
        
        //テーブル物理名　@=　@getキューテーブル管理（）の戻り値.getテーブル物理名（）
        l_response.tableName = l_hostManagementRow.getHostTablePhysicsName();
        
        //識別コード有無フラグ　@=　@getキューテーブル管理（）の戻り値.get識別コード有無フラグ（）
        l_response.identityCodeFlag = l_hostManagementRow.getOrderRequestNumberDiv();
        
        //作成日付有無フラグ　@=　@getキューテーブル管理（）の戻り値.get作成日付有無フラグ（）
        l_response.createDateFlag = l_hostManagementRow.getCreatedTimestampDiv();
        
        //部店コード　@=　@管理者クラスオブジェクト.get部店コード（）の戻り値
        l_response.branchCode = l_strBranchCode;
        
        log.exiting(STR_METHOD_NAME);                
        return l_response;        
    }
    
    /**
     * (get検索結果一覧)<BR>
     * 管理者キューテーブルレコード検索結果画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者）get検索結果一覧」参照。 <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （管理者）get検索結果一覧       <BR>
     *         具体位置    :  1.4 isDIR管理者( )  <BR>
     *         DIR管理者権限チェック 。<BR>
     *        （isDIR管理者( ) == false の場合、<BR>
     *         [DIR管理者権限チェックエラー。]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・キューテーブル検索結果リクエストクラス。<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4418B9C900D2
     */
    protected WEB3AdminDirSecHostTableSearchListResponse getQueryResultList(
        WEB3AdminDirSecHostTableSearchListRequest l_request) 
        throws WEB3BaseException 
    {
        final  String  STR_METHOD_NAME = 
            " getQueryResultList(WEB3AdminDirSecHostTableSearchListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数] 
        //機@能カテゴリコード：システム管理（キューテーブルステータス更新） 
        //is更新：true 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);
        
        //1.4 isDIR管理者( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR管理者権限チェックエラー。");
        }
        
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 getキューテーブル管理(String)
        //[getキューテーブル管理（）に指定する値] 
        //キューテーブル名　@：　@リクエストオブジェクト.キューテーブル名。
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        HostManagementRow l_hostManagementRow = 
            l_hostTableDataManager.getHostTableManagement(l_request.tableJpnName);
        
        //1.7 getRowType(String, String)
        //[getRowType()に指定する引数] 
        //テーブル物理名　@：　@リクエストオブジェクト.テーブル物理名 
        //クエリプロセッサ名　@：　@getキューテーブル管理()で取得した 
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@Rowオブジェクト.getクエリプロセッサ名
        RowType l_rowType = l_hostTableDataManager.getRowType(
            l_request.tableName, l_hostManagementRow.getQueryprocessorName());
        
        //1.8 createキューテーブルソート条件(キューテーブルソートキー[])
        //[createキューテーブルソート条件( )に指定する引数] 
        //キューテーブルソートキー　@：　@リクエストオブジェクト.キューテーブルソートキー[]
        String l_strSortCondition = createHostTableSortCondition(l_request.sortKeys);
        
        //1.9 createキューテーブル検索条件(WEB3GenRequest, String)
        //[createキューテーブル検索条件()に指定する引数] 
        //リクエストオブジェクト　@：　@管理者・キューテーブル検索結果リクエストクラス 
        //証券会社コード　@：　@get証券会社コード（）の戻り値
        String l_strQueryCondition = createHostTableQueryCondition(
            l_request, l_strInstitutionCode);
        
        //1.10 createキューテーブル検索条件データ(WEB3GenRequest, String)
        //[createキューテーブル検索条件データ（）に指定する引数] 
        //リクエストオブジェクト　@：　@管理者・キューテーブル検索結果リクエストクラス 
        //証券会社コード　@：　@get証券会社コード（）の戻り値
        Object[] l_objQueryConditionDatas = createHostTableQueryConditionData(
            l_request, l_strInstitutionCode);
        
        //1.11 getキューテーブルレコード(String, Object[], RowType, String, int, int)
        //[getキューテーブルレコード(）に指定する引数] 
        //検索条件　@：　@createキューテーブル検索条件（）の戻り値 
        //検索条件データ　@：　@createキューテーブル検索条件データ（）の戻り値 
        //キューテーブルRowType　@：　@getRowType（）の戻り値 
        //ソート条件　@：　@createキューテーブルソート条件（）の戻り値
        List l_lisHostTableRecords = l_hostTableDataManager.getHostTableRecord(
            l_strQueryCondition,
            l_objQueryConditionDatas,
            l_rowType,
            l_strSortCondition);
        
        //1.12 WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[WEB3PageIndexInfo（）に指定する引数] 
        //l_list : getキューテーブル一覧（）の戻り値 
        //l_intRequestPageIndex :　@リクエストデータ.要求ページ番号をint型に変換した値 
        //l_intRequestPageSize :　@リクエストデータ.ページ内表示行数をint型に変換した値
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisHostTableRecords, l_intPageIndex, l_intPageSize);
        
        //1.13 getListReturned( )
        List l_lisPageIndexInfoResult = l_pageIndexInfo.getListReturned();
        
        //1.14 createキューテーブルレコード詳細(List, String, String)
        //[createキューテーブルレコード詳細（）に指定する引数] 
        //キューテーブルレコードList　@：　@getListReturned（）で取得した値  
        //識別コード有無フラグ　@：　@getキューテーブル管理（）の戻り値.get識別コード有無フラグ 
        //作成日付有無フラグ　@：　@getキューテーブル管理（）の戻り値.get作成日付有無フラグ
        WEB3AdminDirSecHostTableDetail[] l_hostTableDetails = 
            l_hostTableDataManager.createHostTableDetails(
                l_lisPageIndexInfoResult, 
                l_hostManagementRow.getOrderRequestNumberDiv(),
                l_hostManagementRow.getCreatedTimestampDiv());
        
        //1.15 getTotalPages( )
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.16 getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        //1.17 createResponse( )
        WEB3AdminDirSecHostTableSearchListResponse l_response = 
            (WEB3AdminDirSecHostTableSearchListResponse) l_request.createResponse();
        
        //1.18 (*)プロパティセット
        //テーブル名    = 　@ getキューテーブル管理（）の戻り値.getテーブル名（）
        l_response.tableJpnName = l_hostManagementRow.getHostTableName();
        
        //テーブル物理名　@=　@getキューテーブル管理（）の戻り値.getテーブル物理名（）
        l_response.tableName = l_hostManagementRow.getHostTablePhysicsName();
        
        //識別コード有無フラグ　@=　@getキューテーブル管理（）の戻り値.get識別コード有無フラグ（）
        l_response.identityCodeFlag = l_hostManagementRow.getOrderRequestNumberDiv();
        
        //作成日付有無フラグ　@=　@getキューテーブル管理（）の戻り値.get作成日付有無フラグ（）
        l_response.createDateFlag = l_hostManagementRow.getCreatedTimestampDiv();
        
        //総ページ数       = WEB3StringTypeUtility.formatNumber
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@(totalPages()の戻り値);
        l_response.totalPages = "" + l_intTotalPages;
        
        //総レコード数     = WEB3StringTypeUtility.formatNumber
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@(totalSize()の戻り値);
        l_response.totalRecords = "" + l_intTotalRecords;

        //表示ページ番号 = リクエストオブジェクト.表示ページ番号
        l_response.pageIndex = l_request.pageIndex;

        //キューテーブルレコード詳細 = createキューテーブルレコード詳細()の戻り値
        l_response.hostTableDetails = l_hostTableDetails;
        
        log.exiting(STR_METHOD_NAME);                
        return l_response;   
    }
    
    /**
     * (get更新確認画面)<BR>
     * 管理者キューテーブルステータス更新確認画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者）get更新確認画面」参照。 <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （管理者）get更新確認画面      <BR>
     *         具体位置    :  1.4 isDIR管理者( )  <BR>
     *         DIR管理者権限チェック 。<BR>
     *        （isDIR管理者( ) == false の場合、<BR>
     *         [DIR管理者権限チェックエラー。]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・キューテーブルステータス更新確認リクエストクラス。<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4418BAB30007
     */
    protected WEB3AdminDirSecHostTableStatusConfirmResponse getUpdateConfirmScreen(
        WEB3AdminDirSecHostTableStatusConfirmRequest l_request) 
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getUpdateConfirmScreen" +
                "(WEB3AdminDirSecHostTableStatusConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：システム管理（キューテーブルステータス更新） 
        //is更新：true 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);
                
        //1.4 isDIR管理者( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR管理者権限チェックエラー。");
        }
        //1.5 get証券会社コード( )   
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 getキューテーブル管理(String)
        //[getキューテーブル管理（）に指定する引数] 
        //キューテーブル名　@：　@リクエストオブジェクト.テーブル名        
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        
        HostManagementRow l_hostManagementRow = 
            l_hostTableDataManager.getHostTableManagement(
            l_request.tableJpnName);
        
        //1.7 getRowType(String, String)
        //[引数] 
        //テーブル物理名　@：　@リクエストオブジェクト.テーブル物理名 
        //クエリプロセッサ名　@：　@getキューテーブル管理()で取得した
        //Rowオブジェクト.getクエリプロセッサ名 
        RowType l_rowType = l_hostTableDataManager.getRowType(
            l_request.tableName,
            l_hostManagementRow.getQueryprocessorName());
        
        //1.8 createキューテーブル管理ソート条件(キューテーブルソートキー［］)
        //[createキューテーブルソート条件( )に指定する引数] 
        //キューテーブルソートキー　@：　@リクエストオブジェクト.キューテーブルソートキー[]
        String l_strSortCondition = this.createHostTableSortCondition(
            l_request.sortKeys);
        
        //1.9 createキューテーブル検索条件(WEB3GenRequest, String)
        //[createキューテーブル検索条件()に指定する引数] 
        //リクエストオブジェクト　@：　@管理者・キューテーブルステータス更新完了リクエストクラス 
        //証券会社コード　@：　@get証券会社コード（）の戻り値 
        String l_strQueryCondition = this.createHostTableQueryCondition(
            l_request, l_strInstitutionCode);
        
        //1.10 createキューテーブル検索条件データ(WEB3GenRequest, String)
        //[createキューテーブル検索条件データ（）に指定する引数] 
        //リクエストオブジェクト　@：　@管理者・キューテーブルステータス更新完了リクエストクラス 
        //証券会社コード　@：　@get証券会社コード（）の戻り値
        Object[] l_conditionDatas = this.createHostTableQueryConditionData(
            l_request, l_strInstitutionCode);
        
        //1.11 getキューテーブルレコード(String, Object[], RowType, String)
        //[getキューテーブルレコード（）に指定する引数] 
        //検索条件　@：　@createキューテーブル検索条件（）の戻り値 
        //検索条件データ　@：　@createキューテーブル検索条件データ（）の戻り値 
        //キューテーブルRowType　@：　@getRowType（）の戻り値 
        //ソート条件　@：　@createキューテーブルソート条件（）の戻り値
        List l_lstHostTableRecord = 
            l_hostTableDataManager.getHostTableRecord(
                l_strQueryCondition,
                l_conditionDatas,
                l_rowType,
                l_strSortCondition);
        
        //1.12 createキューテーブルレコード詳細(List, String, String)
        //[createキューテーブルレコード詳細（）に指定する引数] 
        //キューテーブルレコードList　@：　@getキューテーブルレコード（）の戻り値 
        //識別コード有無フラグ　@：　@getキューテーブル管理（）の戻り値.get識別コード有無フラグ 
        //作成日付有無フラグ　@：　@getキューテーブル管理（）の戻り値.get作成日付有無フラグ
        WEB3AdminDirSecHostTableDetail[] l_hostTableDetails =
            l_hostTableDataManager.createHostTableDetails(
            l_lstHostTableRecord,
            l_hostManagementRow.getOrderRequestNumberDiv(),
            l_hostManagementRow.getCreatedTimestampDiv());
        
        //1.13 createResponse( )
        WEB3AdminDirSecHostTableStatusConfirmResponse l_response = 
            (WEB3AdminDirSecHostTableStatusConfirmResponse)l_request.createResponse();
        
        //テーブル名    = 　@ getキューテーブル管理（）の戻り値.getテーブル名（）
        l_response.tableJpnName = l_hostManagementRow.getHostTableName();
        
        //テーブル物理名　@=　@getキューテーブル管理（）の戻り値.getテーブル物理名（）
        l_response.tableName = l_hostManagementRow.getHostTablePhysicsName();
        
        //識別コード有無フラグ　@=　@getキューテーブル管理（）の戻り値.get識別コード有無フラグ（）
        l_response.identityCodeFlag = l_hostManagementRow.getOrderRequestNumberDiv();
        
        //作成日付有無フラグ　@=　@getキューテーブル管理（）の戻り値.get作成日付有無フラグ（）
        l_response.createDateFlag = l_hostManagementRow.getCreatedTimestampDiv();
        
        //キューテーブルレコード詳細 = createキューテーブル
        l_response.hostTableDetails = l_hostTableDetails;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get更新完了画面)<BR>
     * 管理者キューテーブルステータス更新完了画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者）get更新完了画面」参照。 <BR>
     * =============================================== <BR>
     *         シーケンス図 :  （管理者）get更新完了画面     <BR>
     *         具体位置    :  1.4 isDIR管理者( )  <BR>
     *         DIR管理者権限チェック 。<BR>
     *        （isDIR管理者( ) == false の場合、<BR>
     *         [DIR管理者権限チェックエラー。]として例外をスローする。）<BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_00857          <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・キューテーブルステータス更新完了リクエストクラス。<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4418BC550362
     */
    protected WEB3AdminDirSecHostTableStatusCompleteResponse getUpdateCompleteScreen(
        WEB3AdminDirSecHostTableStatusCompleteRequest l_request) 
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getUpdateCompleteScreen" +
                "(WEB3AdminDirSecHostTableStatusCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：システム管理（キューテーブルステータス更新） 
        //is更新：true 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);
                
        //1.4 isDIR管理者( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "DIR管理者権限チェックエラー。");
        }
        //1.5 get証券会社コード( )   
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.7 getキューテーブル管理(String)
        //[getキューテーブル管理（）に指定する引数] 
        //キューテーブル名　@：　@リクエストオブジェクト.テーブル名        
        WEB3AdminDirSecHostTableDataManager l_hostTableDataManager = 
            new WEB3AdminDirSecHostTableDataManager();
        
        HostManagementRow l_hostManagementRow = 
            l_hostTableDataManager.getHostTableManagement(
            l_request.tableJpnName);
        
        //1.8 getRowType(String, String)
        //[引数] 
        //テーブル物理名　@：　@リクエストオブジェクト.テーブル物理名 
        //クエリプロセッサ名　@：　@getキューテーブル管理()で取得した
        //Rowオブジェクト.getクエリプロセッサ名 
        RowType l_rowType = l_hostTableDataManager.getRowType(
            l_request.tableName,
            l_hostManagementRow.getQueryprocessorName());
        
        //1.9 createキューテーブル管理ソート条件(キューテーブルソートキー［］)
        //[createキューテーブルソート条件( )に指定する引数] 
        //キューテーブルソートキー　@：　@リクエストオブジェクト.キューテーブルソートキー[]
        String l_strSortCondition = this.createHostTableSortCondition(
            l_request.sortKeys);
        
        //1.10 createキューテーブル検索条件(WEB3GenRequest, String)
        //[createキューテーブル検索条件()に指定する引数] 
        //リクエストオブジェクト　@：　@管理者・キューテーブルステータス更新完了リクエストクラス 
        //証券会社コード　@：　@get証券会社コード（）の戻り値 
        String l_strQueryCondition = this.createHostTableQueryCondition(
            l_request, l_strInstitutionCode);
        
        //1.11 createキューテーブル検索条件データ(WEB3GenRequest, String)
        //[createキューテーブル検索条件データ（）に指定する引数] 
        //リクエストオブジェクト　@：　@管理者・キューテーブルステータス更新完了リクエストクラス 
        //証券会社コード　@：　@get証券会社コード（）の戻り値
        Object[] l_conditionDatas = this.createHostTableQueryConditionData(
            l_request, l_strInstitutionCode);
        
        //1.12 getキューテーブルレコード(String, Object[], RowType, String)
        //[getキューテーブルレコード（）に指定する引数] 
        //検索条件　@：　@createキューテーブル検索条件（）の戻り値 
        //検索条件データ　@：　@createキューテーブル検索条件データ（）の戻り値 
        //キューテーブルRowType　@：　@getRowType（）の戻り値 
        //ソート条件　@：　@createキューテーブルソート条件（）の戻り値
        List l_lstHostTableRecord = 
            l_hostTableDataManager.getHostTableRecord(
                l_strQueryCondition,
                l_conditionDatas,
                l_rowType,
                l_strSortCondition);
        
        //1.13 getステータスMap(String)
        //[getステータスMap（）に指定する引数] 
        //ステータス　@：　@リクエストオブジェクト.更新後ステータス
        Map l_mapStatusMap = l_hostTableDataManager.getStatusMap(
            l_request.updateStatus);
        
        //1.14 updateキューテーブルレコード(String, Object[], RowType, Map)
        //[updateキューテーブルレコード（）に指定する引数] 
        //検索条件　@：　@create検索条件（）で取得した値 
        //検索条件データ　@：　@create検索条件データ（）で取得した値 
        //キューテーブルRowType　@：　@getRowType（）で取得した値 
        //更新ステータス　@：　@getステータスMap（）で取得した値 
        l_hostTableDataManager.updateHostTableRecord(
            l_strQueryCondition,
            l_conditionDatas,
            l_rowType,
            l_mapStatusMap);
        
        //1.15 createキューテーブルレコード詳細(List, String, String)
        //[createキューテーブルレコード詳細（）に指定する引数] 
        //キューテーブルレコードList　@：　@getキューテーブルレコード（）の戻り値 
        //識別コード有無フラグ　@：　@getキューテーブル管理（）の戻り値.get識別コード有無フラグ 
        //作成日付有無フラグ　@：　@getキューテーブル管理（）の戻り値.get作成日付有無フラグ
        WEB3AdminDirSecHostTableDetail[] l_hostTableDetails =
            l_hostTableDataManager.createHostTableDetails(
            l_lstHostTableRecord,
            l_hostManagementRow.getOrderRequestNumberDiv(),
            l_hostManagementRow.getCreatedTimestampDiv());
        
        //1.16 createResponse( )
        WEB3AdminDirSecHostTableStatusCompleteResponse l_response = 
            (WEB3AdminDirSecHostTableStatusCompleteResponse)l_request.createResponse();
        
        //テーブル名    = 　@ getキューテーブル管理（）の戻り値.getテーブル名（）
        l_response.tableJpnName = l_hostManagementRow.getHostTableName();
        
        //テーブル物理名　@=　@getキューテーブル管理（）の戻り値.getテーブル物理名（）
        l_response.tableName = l_hostManagementRow.getHostTablePhysicsName();
        
        //識別コード有無フラグ　@=　@getキューテーブル管理（）の戻り値.get識別コード有無フラグ（）
        l_response.identityCodeFlag = l_hostManagementRow.getOrderRequestNumberDiv();
        
        //作成日付有無フラグ　@=　@getキューテーブル管理（）の戻り値.get作成日付有無フラグ（）
        l_response.createDateFlag = l_hostManagementRow.getCreatedTimestampDiv();
        
        //キューテーブルレコード詳細 = createキューテーブル
        l_response.hostTableDetails = l_hostTableDetails;
        
        log.exiting(STR_METHOD_NAME);   
        return l_response;
    }
    
    /**
     * (createキューテーブル管理ソート条件)<BR>
     * キューテーブル管理検索ソート条件を作成する。 <BR>
     * <BR>
     * １）　@ソート条件文字列オブジェクト(：String)を作成する。 <BR>
     * <BR>
     * ２）　@パラメータ.ソートキー == nullの場合、 <BR>
     * <BR>
     * 　@　@"テーブル名"のソート条件を返却する。 <BR>
     * <BR>
     * ３）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。 <BR>
     * <BR>
     * 　@３－１）　@ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@　@　@　@　@作成したソート条件文字列に追加する。 <BR>
     * <BR>
     * 　@・「テーブル名」　@→　@キューテーブル管理.テーブル名 <BR>
     * 　@・「テーブル物理名」　@→　@キューテーブル管理.テーブル物理名  <BR>
     * <BR>
     * 　@３－２）　@ソートキー.昇順／降順に対応する取得順序(asc or desc)<BR>
     * 　@　@　@　@　@　@をソート条件文字列に追加する。 <BR>
     * <BR>
     * ４）　@作成したソート条件文字列を返却する。 <BR>
     * @@param l_hostTableSortKeys - (キューテーブルソートキー)<BR>
     * キューテーブルソートキークラス。<BR>
     * @@return String
     * @@roseuid 4418EE5C02A6
     */
    private String createHostManageSortCondition(
        WEB3AdminDirSecSortKey[] l_hostTableSortKeys)
    {
        final String STR_METHOD_NAME = "createHostManageSortCondition(WEB3AdminDirSecSortKey[])";
        log.entering(STR_METHOD_NAME);

        //１）　@ソート条件文字列オブジェクト(：String)を作成する。
        StringBuffer l_sbSortQueryString = new StringBuffer();
        //２）パラメータ.ソートキー == nullの場合、"テーブル名"のソート条件を返却する。
        if (l_hostTableSortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            l_sbSortQueryString.append("host_table_name ");
            return l_sbSortQueryString.toString();
        }

        //３）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        for(int i = 0; i < l_hostTableSortKeys.length; i++)
        {
            //３－１）　@ソートキー.キー項目を対応する列物理名に変換し、作成したソート条件文字列に追加する。
            if(WEB3AdminDirSecSortKeyItemDef.HOST_TABLE_NAME.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("host_table_name ");
            }
            else if(WEB3AdminDirSecSortKeyItemDef.HOST_TABLE_PHYSICS_NAME.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("host_table_physics_name ");
            }
            else 
            {
                continue;
            }
            //３－２）　@ソートキー.昇順／降順に対応する取得順序(asc or desc)をソート条件文字列に追加する。
            if (WEB3AscDescDef.ASC.equals(l_hostTableSortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("asc,");
            }
            if (WEB3AscDescDef.DESC.equals(l_hostTableSortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("desc,");
            }
        }
        String l_strSortString = l_sbSortQueryString.toString();
        
        if ((!"".equals(l_strSortString)) && 
            l_strSortString.charAt(l_strSortString.length()-1) == ',')
        {
            l_strSortString = l_strSortString.substring(0,l_strSortString.length()-1);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortString;
    }

    /**
     * (createキューテーブルソート条件)<BR>
     * キューテーブル検索ソート条件を作成する。 <BR>
     * <BR>
     * １）　@ソート条件文字列オブジェクト(：String)を作成する。 <BR>
     * <BR>
     * ２）　@パラメータ.ソートキー == nullの場合、 <BR>
     * <BR>
     * 　@　@"識別コード"のソート条件を返却する。 <BR>
     * <BR>
     * ３）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。 <BR>
     * <BR>
     * 　@３－１）　@ソートキー.キー項目を対応する列物理名に変換し、<BR>
     *             作成したソート条件文字列に追加する。 <BR>
     * <BR>
     * 　@・「識別コード」　@→　@キューテーブル.識別コード<BR>
     * 　@・「ステータス」　@→　@キューテーブル.ステータス<BR>
     * 　@・「作成日付」　@→　@キューテーブル.作成日付<BR>
     * <BR>
     * <BR>
     * 　@３－２）　@ソートキー.昇順／降順に対応する取得順序(asc or desc)を<BR>
     *              ソート条件文字列に追加する。 <BR>
     * <BR>
     * ４）　@作成したソート条件文字列を返却する。 <BR>
     * @@param l_hostTableSortKeys - (キューテーブルソートキー)<BR>
     * キューテーブルソートキー。<BR>
     * @@return String
     * @@roseuid 44210D0402DD
     */
    private String createHostTableSortCondition(WEB3AdminDirSecSortKey[] l_hostTableSortKeys)
    {
        final String STR_METHOD_NAME = " createHostTableSortCondition(WEB3AdminDirSecSortKey[])";
        log.entering(STR_METHOD_NAME);

        //１）　@ソート条件文字列オブジェクト(：String)を作成する。
        StringBuffer l_sbSortQueryString = new StringBuffer();
        //２）パラメータ.ソートキー == nullの場合、"識別コード"のソート条件を返却する。
        if (l_hostTableSortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            l_sbSortQueryString.append(" order_request_number ");
            return l_sbSortQueryString.toString();
        }

        //３）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        for(int i = 0; i < l_hostTableSortKeys.length; i++)
        {
            //３－１）　@ソートキー.キー項目を対応する列物理名に変換し、作成したソート条件文字列に追加する。
            if(WEB3AdminDirSecSortKeyItemDef.ORDER_REQUEST_NUMBER.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("order_request_number ");
            }
            else if(WEB3AdminDirSecSortKeyItemDef.BEFORESTATUS.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("status ");
            }
            else if(WEB3AdminDirSecSortKeyItemDef.CREATED_TIMESTAMP.equals(
                l_hostTableSortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("created_timestamp ");
            }
            else
            {
                continue;
            }
            //３－２）　@ソートキー.昇順／降順に対応する取得順序(asc or desc)をソート条件文字列に追加する。
            if (WEB3AscDescDef.ASC.equals(l_hostTableSortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("asc,");
            }
            if (WEB3AscDescDef.DESC.equals(l_hostTableSortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("desc,");
            }
        }
        
        String l_strSortString = l_sbSortQueryString.toString();
        
        if ((!"".equals(l_strSortString)) && 
            l_strSortString.charAt(l_strSortString.length()-1) == ',')
        {
            l_strSortString = l_strSortString.substring(0,l_strSortString.length()-1);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortString;
    }

    /**
     * (createキューテーブル検索条件)<BR>
     * キューテーブルの検索条件文字列を作成する。 <BR>
     * <BR>
     * １）　@検索条件文字列インスタンスを生成する。 <BR>
     * <BR>
     * ２）　@引数:証券会社コード != nullの場合、<BR>
     * 　@　@証券会社コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@"institution_code = ? "<BR>
     * <BR>
     * ３）　@引数:リクエストオブジェクト.部店コード != nullの場合、 <BR>
     * 　@　@ 部店コードを検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and branch_code = ? " <BR>
     * <BR>
     * ４）　@引数:リクエストオブジェクト.識別コード != nullの場合、 <BR>
     * 　@　@ 識別コードを検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and order_request_number like ? " <BR>
     * <BR>
     * ５）　@引数:リクエストオブジェクト.作成日付From != nullの場合、 <BR>
     * 　@　@ 作成日付Fromを検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and<BR> TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') >= ? " <BR>
     * <BR>
     * ６）　@引数:リクエストオブジェクト.作成日付To != nullの場合、 <BR>
     * 　@　@ 作成日付Toを検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and<BR> TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') <= ? "<BR>
     * <BR>
     * ７）　@引数:リクエストオブジェクト.ステータス != nullの場合、 <BR>
     * 　@　@ ステータスを検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and status= ? " <BR>
     * <BR>
     * ８）　@作成した検索条件文字列インスタンスを返却する。 <BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * リクエストオブジェクト。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4421492300FC
     */
    private String createHostTableQueryCondition(
        WEB3GenRequest l_request, String l_strInstitutionCode)
        throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = 
            " createHostTableQueryCondition(WEB3GenRequest,String)";
        log.entering(STR_METHOD_NAME);
    
        //１）　@検索条件文字列インスタンスを生成する。
        StringBuffer l_sbSortQueryString = new StringBuffer();
        //２）　@引数:証券会社コード != nullの場合、証券会社コードを検索条件文字列に追加する。
        if(l_strInstitutionCode != null)
        {
            l_sbSortQueryString.append("institution_code = ? ");
        }
    
        if (l_request instanceof WEB3AdminDirSecHostTableSearchListRequest)
        {
            WEB3AdminDirSecHostTableSearchListRequest l_tempRequest =
                (WEB3AdminDirSecHostTableSearchListRequest) l_request;
            //３）　@引数:リクエストオブジェクト.部店コード != nullの場合、
            //      部店コードを検索条件文字列に追加する。
            if(l_tempRequest.branchCode != null)
            {
                l_sbSortQueryString.append("and branch_code = ? ");
            }
            //４）引数:リクエストオブジェクト.識別コード != nullの場合、
            //    識別コードを検索条件文字列に追加する。
            if(l_tempRequest.identityCode != null)
            {
                l_sbSortQueryString.append("and order_request_number like ? ");
            }
            //５）　@引数:リクエストオブジェクト.作成日付From != nullの場合、
            //      作成日付Fromを検索条件文字列に追加する。
            if(l_tempRequest.createDateFrom != null)
            {
                l_sbSortQueryString.append(
                    "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') >= ? ");
            }
            //６）　@引数:リクエストオブジェクト.作成日付To != nullの場合、
            //      作成日付Toを検索条件文字列に追加する。
            if(l_tempRequest.createDateTo != null)
            {
                l_sbSortQueryString.append(
                    "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') <= ? ");
            }
            //７）　@引数:リクエストオブジェクト.ステータス != nullの場合、
            //      ステータスを検索条件文字列に追加する。
            if(l_tempRequest.status != null)
            {
                l_sbSortQueryString.append("and status= ? ");
            }
        }
        else if (l_request instanceof WEB3AdminDirSecHostTableStatusConfirmRequest)
        {
            WEB3AdminDirSecHostTableStatusConfirmRequest l_tempRequest =
                (WEB3AdminDirSecHostTableStatusConfirmRequest) l_request;
            //３）　@引数:リクエストオブジェクト.部店コード != nullの場合、
            //      部店コードを検索条件文字列に追加する。
            if(l_tempRequest.branchCode != null)
            {
                l_sbSortQueryString.append("and branch_code = ? ");
            }
            //４）引数:リクエストオブジェクト.識別コード != nullの場合、
            //    識別コードを検索条件文字列に追加する。
            if(l_tempRequest.identityCode != null)
            {
                l_sbSortQueryString.append("and order_request_number like ? ");
            }
            //５）　@引数:リクエストオブジェクト.作成日付From != nullの場合、
            //      作成日付Fromを検索条件文字列に追加する。
            if(l_tempRequest.createDateFrom != null)
            {
                l_sbSortQueryString.append(
                    "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') >= ? ");
            }
            //６）　@引数:リクエストオブジェクト.作成日付To != nullの場合、
            //      作成日付Toを検索条件文字列に追加する。
            if(l_tempRequest.createDateTo != null)
            {
                l_sbSortQueryString.append(
                    "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') <= ? ");
            }
            //７）　@引数:リクエストオブジェクト.ステータス != nullの場合、
            //      ステータスを検索条件文字列に追加する。
            if(l_tempRequest.status != null)
            {
                l_sbSortQueryString.append("and status= ? ");
            }
        }
        else if(l_request instanceof WEB3AdminDirSecHostTableStatusCompleteRequest)
        {
            WEB3AdminDirSecHostTableStatusCompleteRequest l_tempRequest =
                (WEB3AdminDirSecHostTableStatusCompleteRequest) l_request;
            //３）　@引数:リクエストオブジェクト.部店コード != nullの場合、
            //      部店コードを検索条件文字列に追加する。
            if(l_tempRequest.branchCode != null)
            {
                l_sbSortQueryString.append("and branch_code = ? ");
            }
            //４）引数:リクエストオブジェクト.識別コード != nullの場合、
            //    識別コードを検索条件文字列に追加する。
            if(l_tempRequest.identityCode != null)
            {
                l_sbSortQueryString.append("and order_request_number like ? ");
            }
            //５）　@引数:リクエストオブジェクト.作成日付From != nullの場合、
            //      作成日付Fromを検索条件文字列に追加する。
            if(l_tempRequest.createDateFrom != null)
            {
                l_sbSortQueryString.append(
                        "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') >= ? ");
            }
            //６）　@引数:リクエストオブジェクト.作成日付To != nullの場合、
            //      作成日付Toを検索条件文字列に追加する。
            if(l_tempRequest.createDateTo != null)
            {
                l_sbSortQueryString.append(
                        "and TO_CHAR(created_timestamp,'YYYYMMDDHH24MI') <= ? ");
            }
            //７）　@引数:リクエストオブジェクト.ステータス != nullの場合、
            //      ステータスを検索条件文字列に追加する。
            if(l_tempRequest.status != null)
            {
                l_sbSortQueryString.append("and status= ? ");
            }
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

    log.exiting(STR_METHOD_NAME);
    //８）　@作成した検索条件文字列インスタンスを返却する。
    return l_sbSortQueryString.toString();
    }

    /**
     * (createキューテーブル検索条件データ)<BR>
     * 検索条件データコンテナを編集する。 <BR>
     * <BR>
     * <BR>
     * １）　@ArrayListオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@引数:証券会社コード != nullの場合、<BR>
     * 　@　@証券会社コードをListオブジェクトに追加する。<BR>
     * <BR>
     * 　@Listオブジェクト.add(引数:証券会社コード); <BR>
     * <BR>
     * ３）　@引数:リクエストオブジェクト.部店コード != nullの場合、 <BR>
     * 　@　@ 部店コードをListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(引数:リクエストオブジェクト.部店コード); <BR>
     * <BR>
     * ４）　@引数:リクエストオブジェクト.識別コード != nullの場合、 <BR>
     * 　@　@ 識別コードをListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(引数:リクエストオブジェクト.識別コード); <BR>
     * <BR>
     * ５）　@引数:リクエストオブジェクト.作成日付From != nullの場合、 <BR>
     * 　@　@ 作成日付FromをListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(引数:リクエストオブジェクト.作成日付From); <BR>
     * <BR>
     * ６）　@引数:リクエストオブジェクト.作成日付To != nullの場合、 <BR>
     * 　@　@ 作成日付ToをListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(引数:リクエストオブジェクト.作成日付To); <BR>
     * <BR>
     * ７）　@引数:リクエストオブジェクト.ステータス != nullの場合、 <BR>
     * 　@　@ ステータスをListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(引数:リクエストオブジェクト.ステータス); <BR>
     * <BR>
     * <BR>
     * ８）　@Object型の配列オブジェクトをArrayListオブジェクトのサイズで生成する。<BR>
     * <BR>
     * ９）　@toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。<BR>
     * <BR>
     * 　@　@ArrayListオブジェクト.toArray(Object型の配列オブジェクト); <BR>
     * <BR>
     * １０）　@変換した配列オブジェクトを返却する。 <BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * リクエストオブジェクト。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 44214E080254
     */
    private Object[] createHostTableQueryConditionData(
        WEB3GenRequest l_request, String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHostTableQueryConditionData(" +
            "WEB3GenRequest, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@ArrayListオブジェクトを生成する。
        List l_lisQueryCondition = new ArrayList();
        //２）　@引数:証券会社コード != nullの場合、
        //      証券会社コードをListオブジェクトに追加する。
        if(l_strInstitutionCode != null)
        {
            l_lisQueryCondition.add(l_strInstitutionCode);
        }
        if (l_request instanceof WEB3AdminDirSecHostTableSearchListRequest)
        {
            WEB3AdminDirSecHostTableSearchListRequest l_tempRequest =
                (WEB3AdminDirSecHostTableSearchListRequest) l_request;
            //３）　@引数:リクエストオブジェクト.部店コード != nullの場合、
            //      部店コードをListオブジェクトに追加する。
            if(l_tempRequest.branchCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.branchCode);
            }
            //４）引数:リクエストオブジェクト.識別コード != nullの場合、
            //    識別コードをListオブジェクトに追加する。
            if(l_tempRequest.identityCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.identityCode);
            }
            //５）　@引数:リクエストオブジェクト.作成日付From != nullの場合、
            //      作成日付FromをListオブジェクトに追加する。
            if(l_tempRequest.createDateFrom != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateFrom);
            }
            //６）　@引数:リクエストオブジェクト.作成日付To != nullの場合、
            //      作成日付ToをListオブジェクトに追加する。
            if(l_tempRequest.createDateTo != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateTo);
            }
            //７）　@引数:リクエストオブジェクト.ステータス != nullの場合、
            //      ステータスをListオブジェクトに追加する。
            if(l_tempRequest.status != null)
            {
                l_lisQueryCondition.add(l_tempRequest.status);
            }
        }
        else if (l_request instanceof WEB3AdminDirSecHostTableStatusConfirmRequest)
        {
            WEB3AdminDirSecHostTableStatusConfirmRequest l_tempRequest =
                (WEB3AdminDirSecHostTableStatusConfirmRequest) l_request;
            //３）　@引数:リクエストオブジェクト.部店コード != nullの場合、
            //      部店コードをListオブジェクトに追加する。
            if(l_tempRequest.branchCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.branchCode);
            }
            //４）引数:リクエストオブジェクト.識別コード != nullの場合、
            //    識別コードをListオブジェクトに追加する。
            if(l_tempRequest.identityCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.identityCode);
            }
            //５）　@引数:リクエストオブジェクト.作成日付From != nullの場合、
            //      作成日付FromをListオブジェクトに追加する。
            if(l_tempRequest.createDateFrom != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateFrom);
            }
            //６）　@引数:リクエストオブジェクト.作成日付To != nullの場合、
            //      作成日付ToをListオブジェクトに追加する。
            if(l_tempRequest.createDateTo != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateTo);
            }
            //７）　@引数:リクエストオブジェクト.ステータス != nullの場合、
            //      ステータスをListオブジェクトに追加する。
            if(l_tempRequest.status != null)
            {
                l_lisQueryCondition.add(l_tempRequest.status);
            }
        }
        else if(l_request instanceof WEB3AdminDirSecHostTableStatusCompleteRequest)
        {
            WEB3AdminDirSecHostTableStatusCompleteRequest l_tempRequest = 
                (WEB3AdminDirSecHostTableStatusCompleteRequest) l_request;
            //３）　@引数:リクエストオブジェクト.部店コード != nullの場合、
            //      部店コードをListオブジェクトに追加する。
            if(l_tempRequest.branchCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.branchCode);
            }
            //４）引数:リクエストオブジェクト.識別コード != nullの場合、
            //    識別コードをListオブジェクトに追加する。
            if(l_tempRequest.identityCode != null)
            {
                l_lisQueryCondition.add(l_tempRequest.identityCode);
            }
            //５）　@引数:リクエストオブジェクト.作成日付From != nullの場合、
            //      作成日付FromをListオブジェクトに追加する。
            if(l_tempRequest.createDateFrom != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateFrom);
            }
            //６）　@引数:リクエストオブジェクト.作成日付To != nullの場合、
            //      作成日付ToをListオブジェクトに追加する。
            if(l_tempRequest.createDateTo != null)
            {
                l_lisQueryCondition.add(l_tempRequest.createDateTo);
            }
            //７）　@引数:リクエストオブジェクト.ステータス != nullの場合、
            //      ステータスをListオブジェクトに追加する。
            if(l_tempRequest.status != null)
            {
                l_lisQueryCondition.add(l_tempRequest.status);
            }
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        //８） Object型の配列オブジェクトをArrayListオブジェクトのサイズで生成する。
        Object[] l_objQueryConditions = new Object[l_lisQueryCondition.size()];
        //９）　@toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。
        l_lisQueryCondition.toArray(l_objQueryConditions);

        log.exiting(STR_METHOD_NAME);
        //１０）　@変換した配列オブジェクトを返却する。
        return l_objQueryConditions;
    }
}
@
