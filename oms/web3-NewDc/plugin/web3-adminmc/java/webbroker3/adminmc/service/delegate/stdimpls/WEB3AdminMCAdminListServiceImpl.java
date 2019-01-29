head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者一覧サービスImpl(WEB3AdminMCAdminListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.adminmc.WEB3AdminMCAdminRegistUnitComparator;
import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistUnitCreateService;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit;


/**
 * (管理者メニュー制御管理者一覧サービスImpl)<BR>
 * 管理者メニュー制御管理者一覧サービス実装クラス<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminListServiceImpl implements WEB3AdminMCAdminListService 
{
    
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminListServiceImpl.class);
    
    /**
     * @@roseuid 419864110290
     */
    public WEB3AdminMCAdminListServiceImpl() 
    {
     
    }
    
    /**
     * 管理者一覧処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者一覧入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御管理者一覧リクエストの場合 <BR>
     * 　@－get管理者一覧()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DEEC703B5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminListInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminMCAdminListInputRequest)l_request);
        
        }
        else if (l_request instanceof WEB3AdminMCAdminListRequest)
        {
            l_response = this.getAdminList((WEB3AdminMCAdminListRequest)l_request);
        
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                                      WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                       STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 管理者一覧条件入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者一覧）get入力画面」参照。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者一覧入力リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminListResponse
     * @@roseuid 417DEEC703B9
     */
    protected WEB3AdminMCAdminListInputResponse getInputScreen(WEB3AdminMCAdminListInputRequest l_request)   throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminMCAdminListInputRequest l_request) ";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.2  validate権限(機@能カテゴリコード)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR,false);
        //1.3 get証券会社コード
        String l_strInstitutionCode = l_administartor.getInstitutionCode();
        //1.4  isDIR管理者()
        boolean l_blnDir = l_administartor.isDirAdministrator();
        //1.5  get管理者タイプ
        String l_strSortCond = "permission_Level";        
        WEB3AdminMCAdminType[] l_strAdminMCAdminType = WEB3AdminMCAdminType.getAdminType(l_strInstitutionCode, l_strSortCond, l_blnDir);
        //1.6  メッセージ 管理者メニュー制御管理者一覧入力レスポンス
        WEB3AdminMCAdminListInputResponse l_response = (WEB3AdminMCAdminListInputResponse)l_request.createResponse();
        //1.7 (*1) プロパティセット
        //レスポンスデータプロパティに以下の通り値をセットする。
        //権限レベルコード一覧：　@get管理者タイプ()の戻り値より、権限レベルの配列を作成しセットする。
        //限レベル名一覧：　@get管理者タイプ()の戻り値より、権限レベル名
        int l_intLength = l_strAdminMCAdminType.length;
        String[] l_permissionLevel =new String[l_intLength];
        String[] l_permissionLevelName =new String[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {       
           l_permissionLevel[i]  = l_strAdminMCAdminType[i].getPermissionLevel();
           l_permissionLevelName[i]  = l_strAdminMCAdminType[i].getPermissionLevelName();
        }
        l_response.permissionLevelList = l_permissionLevel;
        l_response.permissionLevelNameList = l_permissionLevelName;

        log.exiting(STR_METHOD_NAME);  
        return l_response;


    }
    
    /**
     * (get管理者一覧)<BR>
     * 管理者一覧表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（管理者一覧）get管理者一覧」参照。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者一覧リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminListResponse
     * @@roseuid 417DEEC703B7
     */
    protected WEB3AdminMCAdminListResponse getAdminList(WEB3AdminMCAdminListRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAdminList(WEB3AdminMCAdminListRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=機@能カテゴリコード.管理者管理） : String, is更新（=false） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_ADMINISTRATOR,false);
        
        //1.4 (*1) 部店指定がある場合（リクエストデータ.部店コード != null）
        if (l_request.branchCode != null)
        {
            //1.4.1 validate部店権限(String)
            l_web3Administrator.validateBranchPermission(l_request.branchCode);
        }
        
        //1.5 create検索条件文字列
        String l_strQueryString = this.createQueryString(l_request);
        
        //1.6 create検索条件データコンテナ
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_request);
              
        //1.7 get管理者(管理者, String, String[], String, Integer )
        Integer l_integerValue;
        if (l_request.errorCount == null || l_request.errorCount.equals("0"))
        {
            l_integerValue = null;
        }
        else
        {
            l_integerValue = Integer.valueOf(l_request.errorCount);
        }
        WEB3Administrator[] l_administrator =  WEB3Administrator.getAdministrators(l_web3Administrator, l_strQueryString, l_strQueryDataContainers, null, l_integerValue);
                
        if (l_administrator == null || l_administrator.length == 0)
        {
            log.debug("get管理者の結果が　@0件である");
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                     STR_METHOD_NAME);
        }        
        
        //1.8 create管理者登録情報(管理者[])
        WEB3AdminMCAdminRegistUnitCreateService l_service= (WEB3AdminMCAdminRegistUnitCreateService)Services.getService(WEB3AdminMCAdminRegistUnitCreateService.class);
        WEB3AdminMCAdminRegistUnit[] l_web3RegistUnits = l_service.createAdminRegistUnit(l_administrator);     
        
        //1.9 リクエストデータ.ソートキー数分、Comparatorを生成する。
        int l_intSortKeyLength = l_request.sortKeys.length; 
        WEB3AdminMCAdminRegistUnitComparator[] l_comparator = new WEB3AdminMCAdminRegistUnitComparator[l_intSortKeyLength];
        for (int i = 0; i < l_intSortKeyLength; i ++)        
        {
            //1.9.1 管理者登録情報Comparator(String, String)。
            l_comparator[i] = new WEB3AdminMCAdminRegistUnitComparator(l_request.sortKeys[i].ascDesc, l_request.sortKeys[i].keyItem);
        }        
        //1.10 sort(obj（=管理者登録情報[]） : Object[], com（=管理者登録情報Comparator[]） : Comparator[])
        WEB3ArraysUtility.sort(l_web3RegistUnits, l_comparator);        
           
        //1.11 メッセージ 管理者メニュー制御管理者一覧レスポンス(WEB3GenRequest) 
        WEB3AdminMCAdminListResponse l_response = (WEB3AdminMCAdminListResponse)l_request.createResponse();
        
        //1.12 プロパティセット
        //get管理者()の戻り値の配列のうち、表示対象行（fromIndex～toIndex）のオブジェクト配列を指定する。
        
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    
        
        WEB3PageIndexInfo l_pagiIndexInfo = new WEB3PageIndexInfo(l_web3RegistUnits, l_intPageIndex, l_intPageSize);
        
        l_response.adminRegistUnits = (WEB3AdminMCAdminRegistUnit[])l_pagiIndexInfo.getArrayReturned(WEB3AdminMCAdminRegistUnit.class);
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_pagiIndexInfo.getTotalRecords());
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_pagiIndexInfo.getTotalPages());
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pagiIndexInfo.getPageIndex());
        
        log.exiting(STR_METHOD_NAME);  
        return l_response;        
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値の検索条件文字列インスタンス（：String）を生成 <BR>
     * <BR>
     * ２）　@部店条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.部店コード != null）の場合、部店コード条件を追加する。 <BR>
     * <BR>
     * 　@" and branch_code = ? "<BR>
     * <BR>
     * ３）　@管理者コード条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.管理者コード != null）の場合、管理者コード条件を追加する。 <BR>
     * <BR>
     * 　@" and administrator_code = ? "<BR>
     * <BR>
     * ４）　@管理者名条件追加 ※指定がある場合のみ，曖昧検索<BR>
     * 　@（リクエストデータ.管理者名 != null）の場合、管理者名条件（like）を追加する。<BR> 
     * <BR>
     * 　@" and name like ? "<BR>
     * <BR>
     * ５）　@権限レベル条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.権限レベルコード != null）の場合、権限レベル条件を追加する。 <BR>
     * <BR>
     * 　@" and permission_level = ? "<BR>
     * <BR>
     * ６）　@文字列インスタンスを返却 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者一覧リクエストデータオブジェクト<BR>
     * @@return String
     * @@roseuid 417F3A1201E0
     */
    protected String createQueryString(WEB3AdminMCAdminListRequest l_request) 
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminMCAdminListRequest l_request)"; 
        
        log.entering(STR_METHOD_NAME);  
        
        // １）　@戻り値生成 <BR>
        // 　@戻り値の検索条件文字列インスタンス（：String）を生成 <BR>
        StringBuffer l_strQueryString = new StringBuffer();
        // ２）　@部店条件追加 ※指定がある場合のみ<BR>
        // 　@（リクエストデータ.部店コード != null）の場合、部店コード条件を追加する。 <BR>
        // <BR>
        // 　@" and branch_code = ? "<BR>
        if (l_request.branchCode != null)
        {
             l_strQueryString.append(" and branch_code = ? ");
        }
        
        // ３）　@管理者コード条件追加 ※指定がある場合のみ<BR>
        // 　@（リクエストデータ.管理者コード != null）の場合、管理者コード条件を追加する。 <BR>
        // <BR>
        // 　@" and administrator_code = ? "<BR>
        if (l_request.administratorCode != null)
        {
             l_strQueryString.append(" and administrator_code = ? ");
        }

        // ４）　@管理者名条件追加 ※指定がある場合のみ，曖昧検索<BR>
        // 　@（リクエストデータ.管理者名 != null）の場合、管理者名条件（like）を追加する。<BR> 
        // <BR>
        // 　@" and name like ? "<BR>
        if (l_request.administratorName != null)
        {
             l_strQueryString.append(" and name like ? ");
        }

        // ５）　@権限レベル条件追加 ※指定がある場合のみ<BR>
        // 　@（リクエストデータ.権限レベルコード != null）の場合、権限レベル条件を追加する。 <BR>
        // <BR>
        // 　@" and permission_level = ? "<BR>
        if (l_request.permissionLevel != null)
        {
             l_strQueryString.append(" and permission_level = ? ");
        }

        // ６）　@文字列インスタンスを返却 <BR>        
        log.exiting(STR_METHOD_NAME);
        String l_strQueryStringReturn = l_strQueryString.toString();
        return l_strQueryStringReturn;

    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）を生成 <BR>
     * <BR>
     * ２）　@部店条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.部店コード != null）の場合、戻り値編集用インスタンスに部店コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.部店コード <BR>
     * <BR>
     * ３）　@管理者コード条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.管理者コード != null）の場合、戻り値編集用インスタンスに管理者コード条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.管理者コード <BR>
     * <BR>
     * ４）　@管理者名条件追加 ※指定がある場合のみ，曖昧検索<BR>
     * 　@（リクエストデータ.管理者名 != null）の場合、戻り値編集用インスタンスに管理者名条件（like）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@"%" + リクエストデータ.管理者名 + "%"<BR>
     * <BR>
     * ５）　@権限レベル条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.権限レベルコード != null）の場合、戻り値編集用インスタンスに権限レベル条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.権限レベルコード <BR>
     * <BR>
     * ６）　@配列を返却 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者一覧リクエストデータオブジェクト<BR>
     * @@return java.lang.String[]
     * @@roseuid 417F42DB01C0
     */
    protected String[] createQueryDataContainer(WEB3AdminMCAdminListRequest l_request) 
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(WEB3AdminMCAdminListRequest l_request) "; 
        
        log.entering(STR_METHOD_NAME);  
        
        // １）　@戻り値生成 <BR>
        // 　@戻り値編集用インスタンス（：ArrayList）を生成 <BR>
        List l_lisContainers = new ArrayList();  
        
        // ２）　@部店条件追加 ※指定がある場合のみ<BR>
        // 　@（リクエストデータ.部店コード != null）の場合、戻り値編集用インスタンスに部店コードを追加する。 <BR>
        // <BR>
        // 　@[add()に指定する引数] <BR>
        // 　@リクエストデータ.部店コード <BR>
        if (l_request.branchCode != null)
        {
             l_lisContainers.add(l_request.branchCode);
        }

        // ３）　@管理者コード条件追加 ※指定がある場合のみ<BR>
        // 　@（リクエストデータ.管理者コード != null）の場合、戻り値編集用インスタンスに管理者コード条件を追加する。 <BR>
        // <BR>
        // 　@[add()に指定する引数] <BR>
        // 　@リクエストデータ.管理者コード <BR>
        if (l_request.administratorCode != null)
        {
             l_lisContainers.add(l_request.administratorCode);
        }

        // ４）　@管理者名条件追加 ※指定がある場合のみ，曖昧検索<BR>
        // 　@（リクエストデータ.管理者名 != null）の場合、戻り値編集用インスタンスに管理者名条件（like）を追加する。 <BR>
        // <BR>
        // 　@[add()に指定する引数] <BR>
        // 　@"%" + リクエストデータ.管理者名 + "%"<BR>
        if (l_request.administratorName != null)
        {
             String l_administratorName = null;
             l_administratorName = "%" + l_request.administratorName + "%";
             l_lisContainers.add(l_administratorName);
        }

        // ５）　@権限レベル条件追加 ※指定がある場合のみ<BR>
        // 　@（リクエストデータ.権限レベルコード != null）の場合、戻り値編集用インスタンスに権限レベル条件を追加する。 <BR>
        // <BR>
        // 　@[add()に指定する引数] <BR>
        // 　@リクエストデータ.権限レベルコード <BR>
        if (l_request.permissionLevel != null)
        {
             l_lisContainers.add(l_request.permissionLevel);
        }

        // ６）　@配列を返却 <BR>
        // 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。 <BR>        
        String[] l_strQueryDataContainers = new String[l_lisContainers.size()];
        l_lisContainers.toArray(l_strQueryDataContainers);
        log.exiting(STR_METHOD_NAME);         
        return l_strQueryDataContainers;
                        
    }
    

}
@
