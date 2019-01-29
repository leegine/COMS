head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ一覧サービスImpl(WEB3AdminMCAdminPermGrpListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 Tongwei(中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.define.WEB3AdminMCAdminTypeUnitDef;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListRequest;
import webbroker3.adminmc.message.WEB3AdminMCSortKeyUnit;
import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse;


/**
 * (管理者メニュー制御管理者権限グループ一覧サービスImpl)<BR>
 * 管理者メニュー制御管理者権限グループ一覧サービス実装クラス<BR>
 * 
 * @@author Tongwei
 * @@version 1.0 
 */
public class WEB3AdminMCAdminPermGrpListServiceImpl implements WEB3AdminMCAdminPermGrpListService 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpListServiceImpl.class);      
    
    /**
     * @@roseuid 419864120186
     */
    public WEB3AdminMCAdminPermGrpListServiceImpl() 
    {
     
    }
    
    /**
     * 管理者権限グループ一覧／詳細処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ一覧リクエストの場合 <BR>
     * 　@－get権限グループ一覧()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ詳細リクエストの場合 <BR>
     * 　@－get権限グループ詳細()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41774A6A0290
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null; 
        
        //１）　@リクエストデータの型により、以下の通りメソッドをコールする。
        if (l_request instanceof WEB3AdminMCAdminPermGrpListRequest)
        {
           //○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ一覧リクエストの場合 
           //　@－get権限グループ一覧()をコールする。。 
            l_response = this.getPermGrpList((WEB3AdminMCAdminPermGrpListRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminMCAdminPermGrpDetailsRequest)
        {
            //○ 引数のリクエストデータが、管理者メニュー制御管理者権限グループ詳細リクエストの場合 
            // 　@－get権限グループ詳細()をコールする。
            l_response = this.getPermGrpDetail((WEB3AdminMCAdminPermGrpDetailsRequest)l_request);
        }    
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get権限グループ一覧)<BR>
     * 権限グループ一覧表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者グループ一覧）get権限グループ一覧」参照。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ一覧リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse
     * @@roseuid 41774A6A0292
     */
    protected WEB3AdminMCAdminPermGrpListResponse getPermGrpList(WEB3AdminMCAdminPermGrpListRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getPermGrpList(WEB3AdminMCAdminPermGrpListRequest l_request)";
            log.entering(STR_METHOD_NAME); 
       
        //1.1 validate()
        l_request.validate();
        
        //1.2getInstanceFromログイン情報
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=管理者権限管理） : String, is更新（=false） : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, false);
        
        //1.4 get証券会社コード()
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        
        //1.5 createソート条件(管理者メニュー制御管理者権限グループ一覧サービスImpl::createソート条件)
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.6 isDIR管理者()
        boolean l_blnDir =  l_administartor.isDirAdministrator();
        
        //1.7 get管理者タイプ(String, String, boolean)
        WEB3AdminMCAdminType[] l_adminMCAdminType = WEB3AdminMCAdminType.getAdminType(l_strInstitutionCode, l_strSortCond, l_blnDir);
        
        if (l_adminMCAdminType == null || l_adminMCAdminType.length == 0)
        {
            log.debug("get管理者タイプの結果が　@0件である");
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                     STR_METHOD_NAME);
        }   
        
        //get扱者()の戻り値の配列のうち、表示対象行（fromIndex～toIndex）のオブジェクト配列を指定する。
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    
 
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_adminMCAdminType, l_intPageIndex, l_intPageSize);
        l_adminMCAdminType = (WEB3AdminMCAdminType[])l_pageIndexInfo.getArrayReturned(WEB3AdminMCAdminType.class);
        
        //1.8 create管理者タイプ情報(管理者タイプ)
        WEB3AdminMCAdminPermUnitCreateService l_permUnitCreateService = 
            (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class);
            
        //1.8.1 create管理者タイプ情報(管理者タイプ)
        WEB3AdminMCAdminTypeUnit[] l_adminTypeUnit = l_permUnitCreateService.createAdminTypeUnit(l_adminMCAdminType);
        
        //1.9 管理者メニュー制御管理者グループ一覧レスポンス(WEB3GenRequest)
        WEB3AdminMCAdminPermGrpListResponse l_response = 
            (WEB3AdminMCAdminPermGrpListResponse)l_request.createResponse();
    
        //1.10 プロパティセット
        l_response.adminTypeUnits = l_adminTypeUnit;
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalRecords());
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalPages());
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getPageIndex());
       
        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (get権限グループ詳細)<BR>
     * 権限グループ詳細表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者グループ詳細）get権限グループ詳細」参照。 <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ詳細）get権限グループ詳細」<BR>
     *         具体位置    :1.6(*1) 分岐フロー<BR>
     *         既存行が存在しない場合（管理者タイプ() == null）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_00398           <BR>
     * =========================================================== <BR>
     * <BR>
     * =========================================================== <BR>
     *         シーケンス図 :「管理者メニュー制御（管理者グループ詳細）get権限グループ詳細」<BR>
     *         具体位置    : 1.8.2(*2.1) 分岐フロー<BR>
     *         DIR管理者の管理者タイプの場合（isDIR管理者() == true）、<BR>
     *         例外をスローする。<BR>
     *         class :  WEB3BusinessLayerException <BR>
     *         tag :    BUSINESS_ERROR_01225           <BR>
     * =========================================================== <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ詳細リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse
     * @@roseuid 41774A6A0294
     */
    protected WEB3AdminMCAdminPermGrpDetailsResponse getPermGrpDetail(WEB3AdminMCAdminPermGrpDetailsRequest l_request) 
        throws WEB3BaseException     
    {
        final String STR_METHOD_NAME = " getPermGrpDetail(WEB3AdminMCAdminPermGrpDetailsRequest l_request)";
            log.entering(STR_METHOD_NAME); 
       
        //1.1 validate()
        l_request.validate();
   
        //1.2getInstanceFromログイン情報
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=管理者権限管理） : String, is更新（=false） : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_PERMISSION, false);
        
        //1.4 get証券会社コード()
        String l_strInstitutionCode = l_administartor.getInstitutionCode();     
        
        //1.5 管理者タイプ(String, String)
        String l_strPermissionLevel = l_request.permissionLevel;
        WEB3AdminMCAdminType l_adminMCAdminType =
            new WEB3AdminMCAdminType(l_strInstitutionCode, l_strPermissionLevel); 
            
        //1.7  isDIR管理者()
        boolean l_blnIsDir1 = l_administartor.isDirAdministrator();
        
        //1.8 DIR管理者でない（isDIR管理者() == false）場合のみ処理実施
        if (!l_blnIsDir1)
        {
             //  1.8.1 isDIR管理者( )
            boolean l_blnIsDir2 = l_adminMCAdminType.isDIRAdministrator();
            if (l_blnIsDir2)
            {
                log.error("DIR管理者の管理者タイプの場合のエラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01225,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }
        }
        //1.9 create管理者タイプ情報(管理者タイプ)
        WEB3AdminMCAdminPermUnitCreateService l_permUnitCreateService = 
            (WEB3AdminMCAdminPermUnitCreateService)Services.getService(WEB3AdminMCAdminPermUnitCreateService.class);
            
        WEB3AdminMCAdminTypeUnit l_adminTypeUnit = l_permUnitCreateService.createAdminTypeUnit(l_adminMCAdminType);
        
        //1.10 create処理可能機@能カテゴリ一覧(String, String, String[])
        WEB3AdminMCTransactionCategoryUnit[] l_transactionCategoryUnits = 
            l_permUnitCreateService.createOperatePossibleTransactionCategoryUnit(l_strInstitutionCode, 
            l_strPermissionLevel, null);

        //1.11 管理者メニュー制御管理者グループ詳細レスポンス(WEB3GenRequest)
        WEB3AdminMCAdminPermGrpDetailsResponse l_response = 
            (WEB3AdminMCAdminPermGrpDetailsResponse)l_request.createResponse();
   
        //1.12 プロパティセット
        l_response.adminTypeUnit = l_adminTypeUnit;
        l_response.transactionCategoryUnits = l_transactionCategoryUnits;
       
        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件文字列を編集する。<BR> 
     * <BR>
     * 管理者タイプテーブル列物理名を使用し、<BR>
     * 引数のソートキーが示す通りのソート条件文字列（order by句）を編集し返却する。<BR>
     * <BR>
     * @@param l_sortKey - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return String
     * @@roseuid 4177526502CE
     */
    protected String createSortCond(WEB3AdminMCSortKeyUnit[] l_sortKey) 
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3AdminMCSortKeyUnit[] l_sortKey) "; 
        
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKey == null)
        {
            log.debug("l_sortKey = null");
            return null;
        }
        
        StringBuffer l_strSortCond = new StringBuffer();
        
        int l_intSortKeyLength = l_sortKey.length;
        for (int i = 0; i < l_intSortKeyLength; i ++)
        {
            if (WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL.equals(l_sortKey[i].keyItem)) 
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("permission_Level");
            }
            else if (WEB3AdminMCAdminTypeUnitDef.PERMISSION_LEVEL_NAME.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("permission_Level_Name");
            }
            else if (WEB3AdminMCAdminTypeUnitDef.DIR_ADMIN_FLAG.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("dir_Admin_Flag");
            }
            else if (WEB3AdminMCAdminTypeUnitDef.ALL_BRANCH_PERMISSION_FLAG.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("all_Branch_Permission_Flag");
            }
            else
            {
                continue;   
            }

            if(WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
            {
                 l_strSortCond.append( " asc");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKey[i].ascDesc))
            {
                l_strSortCond.append(" desc");
            }                           
        }   
        
        log.exiting(STR_METHOD_NAME);  
        return l_strSortCond.toString();
    }
}
@
