head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCオペレータ一覧サービスImpl(WEB3AdminMCCCOperatorListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.adminmc.WEB3AdminMCCCOperatorRegistUnitComparator;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistUnitCreateService;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit;


/**
 * (管理者メニュー制御CCオペレータ一覧サービスImpl)<BR>
 * 管理者メニュー制御CCオペレータ一覧サービス実装クラス<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorListServiceImpl implements WEB3AdminMCCCOperatorListService 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorListServiceImpl.class);     

    /**
     * @@roseuid 4198640C01E4
     */
    public WEB3AdminMCCCOperatorListServiceImpl() 
    {
     
    }
    
    /**
     * CCオペレータ一覧処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧入力ﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾘｸｴｽﾄの場合 <BR>
     * 　@－getCCオペレータ一覧()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F770902BE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCCCOperatorListInputRequest)
        {
            
            l_response = this.getInputScreen((WEB3AdminMCCCOperatorListInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminMCCCOperatorListRequest)
        {
            l_response = this.getCCOperatorList((WEB3AdminMCCCOperatorListRequest)l_request);           
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * CCオペレータ一覧条件入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（CC一覧）get入力画面」参照。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧入力ﾘｸｴｽﾄデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse
     * @@roseuid 417F7435010E
     */
    protected WEB3AdminMCCCOperatorListInputResponse getInputScreen(WEB3AdminMCCCOperatorListInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminMCCCOperatorListInputRequest l_request)";         
        log.entering(STR_METHOD_NAME); 
        
        //1.1 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限(機@能カテゴリコード（=オペレータ管理） : String, is更新（=false） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,false);
        
        //1.3 createResponse( )
        WEB3AdminMCCCOperatorListInputResponse l_Response = (WEB3AdminMCCCOperatorListInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_Response;
    }
    
    /**
     * (getCCオペレータ一覧)<BR>
     * CCオペレータ一覧表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（CC一覧）getCCオペレータ一覧」参照。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse
     * @@roseuid 417F74350110
     */
    protected WEB3AdminMCCCOperatorListResponse getCCOperatorList(WEB3AdminMCCCOperatorListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getCCOperatorList(WEB3AdminMCCCOperatorListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=オペレータ管理） : String, is更新（=false） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,false);
        
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
                
        //1.7 get扱者(管理者, String, String[], String, Integer
        Integer l_interrorcount;
        if (l_request.errorCount == null || l_request.errorCount.equals("0"))
        {
            l_interrorcount = null;
        }
        else
        {
            l_interrorcount = Integer.valueOf(l_request.errorCount);
        }
        WEB3GentradeTrader[] l_web3GentradeTraders =  WEB3GentradeTrader.getTraders(l_web3Administrator, l_strQueryString, l_strQueryDataContainers, null, l_interrorcount);
        
        //1.8 createCCオペレータ登録情報(扱者[])
        WEB3AdminMCCCOperatorRegistUnitCreateService l_service= (WEB3AdminMCCCOperatorRegistUnitCreateService)Services.getService(WEB3AdminMCCCOperatorRegistUnitCreateService.class); 
        WEB3AdminMCCCOperatorRegistUnit[] l_web3RegistUnits = l_service.createCCOperatorRegistUnit(l_web3GentradeTraders);

        if (l_web3RegistUnits == null || l_web3RegistUnits.length == 0)
        {
            log.debug("createCCオペレータ登録情報の結果が　@0件である");
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01948,
                     STR_METHOD_NAME);
        }   
        
        //1.9 (*) リクエストデータ.ソートキー数分、Comparatorを生成する。       
        int l_intLength = l_request.sortKeys.length;
        WEB3AdminMCCCOperatorRegistUnitComparator[] l_comprtors = new WEB3AdminMCCCOperatorRegistUnitComparator[l_intLength];
        for(int i = 0;i < l_intLength;i ++ )
        {
            //1.9.1 CCオペレータ登録情報Comparator(String, String)
            l_comprtors[i] = new WEB3AdminMCCCOperatorRegistUnitComparator(l_request.sortKeys[i].ascDesc, l_request.sortKeys[i].keyItem);
            
        }
        
        //1.10 WEB3ArraysUtility.sort()
        WEB3ArraysUtility.sort(l_web3RegistUnits, l_comprtors);

        //1.11 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾚｽﾎﾟﾝｽ(WEB3GenRequest) 
        WEB3AdminMCCCOperatorListResponse l_reponse = (WEB3AdminMCCCOperatorListResponse)l_request.createResponse();
        
        //1.12 (*2) プロパティセット
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    
        WEB3PageIndexInfo l_pagiIndexInfo = new WEB3PageIndexInfo(l_web3RegistUnits, l_intPageIndex, l_intPageSize);

        l_reponse.ccOperatorRegistUnits = (WEB3AdminMCCCOperatorRegistUnit[])l_pagiIndexInfo.getArrayReturned(WEB3AdminMCCCOperatorRegistUnit.class);
        l_reponse.totalRecords = l_pagiIndexInfo.getTotalRecords() + "";
        l_reponse.totalPages = l_pagiIndexInfo.getTotalPages() + "";
        l_reponse.pageIndex = l_pagiIndexInfo.getPageIndex() + "";
        
        log.exiting(STR_METHOD_NAME);
        return l_reponse;
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
     * ３）　@扱者コード条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.オペレータコード != null）の場合、扱者コード条件を追加する。 <BR>
     * <BR>
     * 　@" and trader_code = ? "<BR>
     * <BR>
     * ４）　@扱者名条件追加 ※指定がある場合のみ，曖昧検索<BR>
     * 　@（リクエストデータ.オペレータ名 != null）の場合、扱者苗字条件（like）を追加する。 <BR>
     * <BR>
     * 　@" and family_name like ? "<BR>
     * <BR>
     * ５）　@代行注文可能条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.代行注文可能区分 != null）の場合、代行注文可否フラグ条件を追加する。 <BR>
     * <BR>
     * 　@" and account_order_flag = ? "<BR>
     * <BR>
     * ６）　@所属コード条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.所属コード != null）の場合、所属コード条件を追加する。 <BR>
     * <BR>
     * 　@" and department_code = ? "<BR>
     * <BR>
     * ７）　@文字列インスタンスを返却 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return String
     * @@roseuid 417F7435011D
     */
    protected String createQueryString(WEB3AdminMCCCOperatorListRequest l_request) 
    { 
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminMCCCOperatorListRequest l_request)"; 
        
        log.entering(STR_METHOD_NAME); 
         
        // 検索条件文字列を編集する。
        // １）　@戻り値生成 
        StringBuffer l_strQueryString = new StringBuffer();
 
        // ２）（リクエストデータ.部店コード != null）の場合、部店コード条件を追加する。 
        // 　@" and branch_code = ? "
        if (l_request.branchCode != null)
        {
            l_strQueryString.append("and branch_code = ? ");
        }        

        // ３）（リクエストデータ.オペレータコード != null）の場合、扱者コード条件を追加する。 
        // 　@" and trader_code = ? "
        if (l_request.operatorCode != null)
        {
            l_strQueryString.append(" and trader_code = ? ");
        }

        // ４）　@（リクエストデータ.オペレータ名 != null）の場合、扱者苗字条件（like）を追加する。 <BR>
        // 　@" and family_name like ? "
        if (l_request.operatorName != null)
        {
            l_strQueryString.append(" and family_name like ? ");
        }

        // ５)（リクエストデータ.代行注文可能区分 != null）の場合、代行注文可否フラグ条件を追加する
        // 　@" and account_order_flag = ? "
        if (l_request.accountOrderDiv != null)
        {
            l_strQueryString.append(" and account_order_flag = ? ");
        }

        // ６）（リクエストデータ.所属コード != null）の場合、所属コード条件を追加する。 
        // 　@" and department_code = ? "
        if (l_request.departmentCode != null)
        {
            l_strQueryString.append(" and department_code = ? ");
        }

        // ７）　@文字列インスタンスを返却
        String l_strQueryStringReturn = l_strQueryString.toString();
        
        if (l_strQueryStringReturn.length() == 0)
        {
            l_strQueryStringReturn = null;
        }
        
        log.exiting(STR_METHOD_NAME); 
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
     * ３）　@扱者コード条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.オペレータコード != null）の場合、戻り値編集用インスタンスに管理者コード条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.オペレータコード <BR>
     * <BR>
     * ４）　@扱者名条件追加 ※指定がある場合のみ，曖昧検索<BR>
     * 　@（リクエストデータ.管理者名 != null）の場合、戻り値編集用インスタンスに扱者苗字名条件（like）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@"%" + リクエストデータ.オペレータ名 + "%"<BR>
     * <BR>
     * ５）　@代行注文可能条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.代行注文可能区分 != null）の場合、戻り値編集用インスタンスに代行注文可否フラグ条件を追加する。<BR> 
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.代行注文可能区分<BR>
     * <BR>
     * ６）　@所属コード条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.所属コード != null）の場合、戻り値編集用インスタンスに所属コード条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.所属コード<BR>
     * <BR>
     * ７）　@配列を返却 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return java.lang.String[]
     * @@roseuid 417F7435011F
     */
    protected String[] createQueryDataContainer(WEB3AdminMCCCOperatorListRequest l_request) 
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(WEB3AdminMCCCOperatorListRequest l_request)"; 
        
        log.entering(STR_METHOD_NAME);

        // 検索条件データコンテナを編集する。
        // １)戻り値編集用インスタンス（：ArrayList）を生成 
        List l_lisContainers = new ArrayList(); 
        
        // ２）(リクエストデータ.部店コード != null）の場合、戻り値編集用インスタンスに部店コードを追加する。
        if (l_request.branchCode != null)
        {
            l_lisContainers.add(l_request.branchCode);
        }

        // ３）（リクエストデータ.オペレータコード != null）の場合,リクエストデータ.オペレータコードを追加する。
        if (l_request.operatorCode != null)
        {
            l_lisContainers.add(l_request.operatorCode);
        }

        // ４）（リクエストデータ.管理者名 != null）の場合、戻り値編集用インスタンスに扱者苗字名条件（like）を追加する。
        // 　@"%" + リクエストデータ.オペレータ名 + "%"
        if (l_request.operatorName != null)
        {
            l_lisContainers.add("%" + l_request.operatorName + "%");
        }

        // ５）（リクエストデータ.代行注文可能区分 != null）の場合、リクエストデータ.代行注文可能区分を追加する。
        if (l_request.accountOrderDiv != null)
        {
            l_lisContainers.add(l_request.accountOrderDiv);
        }

        // ６）（リクエストデータ.所属コード != null）の場合、所属コードを追加する。
        if (l_request.departmentCode != null)
        {
            l_lisContainers.add(l_request.departmentCode);
        }
        
        // ７）戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。
        String[] l_strQueryDataContainers = new String[l_lisContainers.size()];
        l_lisContainers.toArray(l_strQueryDataContainers);

        log.exiting(STR_METHOD_NAME);         
        return l_strQueryDataContainers;
    }
    
}
@
