head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenJudgeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設審査サービスImpl (WEB3AdminAccOpenJudgeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 周捷(中訊) 新規作成
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenJudgeWaiting;
import webbroker3.accountopen.data.AccOpenWaitingParams;
import webbroker3.accountopen.message.WEB3AccOpenInspectInfo;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenJudgeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CheckDivDef;
import webbroker3.common.define.WEB3ReviewCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (管理者口座開設審査サービスImpl)<BR>
 * 管理者口座開設審査サービス実装クラス
 *   
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminAccOpenJudgeServiceImpl implements WEB3AdminAccOpenJudgeService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance
        (WEB3AdminAccOpenJudgeServiceImpl.class);
    
    /**
     * @@roseuid 44912C1C0119
     */
    public WEB3AdminAccOpenJudgeServiceImpl() 
    {
     
    }
    
    /**
     * 口座開設審査処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設審査対象一覧検索リクエストの場合<BR> 
     * 　@－get審査対象一覧検索画面()をコールする。<BR> 
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設審査一覧リクエストの場合<BR>
     * 　@－get審査一覧()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設審査承認確認リクエストの場合<BR> 
     * 　@－validate承認()をコールする。<BR> 
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設審査承認完了リクエストの場合<BR> 
     * 　@－submit承認()をコールする。<BR> 
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設審査否認確認リクエストの場合<BR> 
     * 　@－validate否認()をコールする。<BR> 
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設審査否認完了リクエストの場合<BR> 
     * 　@－submit否認()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4473B1140125
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //引数のリクエストデータが、管理者口座開設審査対象一覧検索リクエストの場合
        if (l_request instanceof WEB3AdminAccOpenInspectListSearchRequest)
        {
            //get審査対象一覧検索画面()をコールする。
            log.info("WEB3AdminAccOpenInspectListSearchRequest");
            WEB3AdminAccOpenInspectListSearchResponse l_response = getInspectObjectListSearchScreen(
                (WEB3AdminAccOpenInspectListSearchRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        else if (l_request instanceof WEB3AdminAccOpenInspectListRequest)
        //引数のリクエストデータが、管理者口座開設審査一覧リクエストの場合
        {
            //get審査一覧()をコールする。
            log.info("WEB3AdminAccOpenInspectListRequest");
            WEB3AdminAccOpenInspectListResponse l_response = getInspectList(
                (WEB3AdminAccOpenInspectListRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        else if (l_request instanceof WEB3AdminAccOpenInspectConsentConfirmRequest)
        // 引数のリクエストデータが、管理者口座開設審査承認確認リクエストの場合
        {
            //validate承認()をコールする。
            log.info("WEB3AdminAccOpenInspectConsentConfirmRequest");
            WEB3AdminAccOpenInspectConsentConfirmResponse l_response = validateConsent(
                (WEB3AdminAccOpenInspectConsentConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        else if (l_request instanceof WEB3AdminAccOpenInspectConsentCompleteRequest)
        // 引数のリクエストデータが、管理者口座開設審査承認完了リクエストの場合 
        {
            // submit承認完了()をコールする。
            log.info("WEB3AdminAccOpenInspectConsentCompleteRequest");
            WEB3AdminAccOpenInspectConsentCompleteResponse l_response = submitConsent(
                (WEB3AdminAccOpenInspectConsentCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        else if (l_request instanceof WEB3AdminAccOpenInspectDenyConfirmRequest)
        // 引数のリクエストデータが、管理者口座開設審査否認確認リクエストの場合 
        {
            //validate否認確認()をコールする。
            log.info("WEB3AdminAccOpenInspectDenyConfirmRequest");
            WEB3AdminAccOpenInspectDenyConfirmResponse l_response = validateDeny(
                (WEB3AdminAccOpenInspectDenyConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenInspectDenyCompleteRequest)
        // 引数のリクエストデータが、管理者口座開設審査否認完了リクエストの場合
        {
            // submit否認完了()をコールする。
            log.info("WEB3AdminAccOpenInspectDenyCompleteRequest");
            WEB3AdminAccOpenInspectDenyCompleteResponse l_response = submitDeny(
                (WEB3AdminAccOpenInspectDenyCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }                 
    }
    
    /**
     * (get審査対象一覧検索画面)<BR>
     * 口座開設審査対象一覧検索画面表示処理を行う。<BR> 
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（審査）get審査対象一覧検索画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査対象一覧検索リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectListSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C74B0019
     */
    protected WEB3AdminAccOpenInspectListSearchResponse getInspectObjectListSearchScreen(
        WEB3AdminAccOpenInspectListSearchRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInspectObjectListSearchScreen(WEB3AdminAccOpenInspectListSearchRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.2. validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, false);
        
        //1.3. createResponse( )
        WEB3AdminAccOpenInspectListSearchResponse l_response = 
            (WEB3AdminAccOpenInspectListSearchResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get審査一覧)<BR>
     * 口座開設審査一覧画面表示処理を行う。<BR> 
     * <BR>
     * シーケンス図<BR> 
     * 「口座開設（審査）get審査一覧」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査一覧リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C7510365
     */
    protected WEB3AdminAccOpenInspectListResponse getInspectList(
        WEB3AdminAccOpenInspectListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInspectList(WEB3AdminAccOpenInspectListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, false);
        
        //1.4. validate部店権限(部店コード : String)
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5. to口座開設審査待ち( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        //1.5.1. 口座開設審査待ち( )
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        String l_strOccurredDateFrom = null; 
        String l_strOccurredDateTo = null;
        
        if (l_request.occurredDateFrom != null)
        {
            l_strOccurredDateFrom = WEB3DateUtility.formatDate(l_request.occurredDateFrom, "yyyyMMdd");
        }
        
        if (l_request.occurredDateTo != null)
        {
            l_strOccurredDateTo = WEB3DateUtility.formatDate(l_request.occurredDateTo, "yyyyMMdd");
        }
        
        //1.6. get証券会社コード( )
        String l_strInstitutionCode= l_admin.getInstitutionCode();
        
        //1.7. select審査対象識別コード一覧(String, String, String, String, String, 
        //     String, String, String, String, 口座開設ソートキー[])
        String[] l_strRequestNumberLists = 
            l_judgeWaiting.selectJudgeObjectAccOpenRequestNumberList(
                l_strInstitutionCode, 
                l_request.branchCode,
                l_request.accountCode,
                l_request.requestNumber,
                l_strOccurredDateFrom,
                l_strOccurredDateTo,
                l_request.reviewCode,
                l_request.checkerCode,
                l_request.checkStatus,
                l_request.sortKeys);
        
        //1.8. (*)識別コード一覧を編集する  
        int l_intPageSize = 0;
        int l_intPageIndex = 0;
        
        l_intPageSize = Integer.parseInt(l_request.pageSize);
        l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_strRequestNumberLists,
                l_intPageIndex,
                l_intPageSize);
        
        //総ページ数
        String l_strTotalPages = l_pageIndexInfo.getTotalPages() + "";
        
        //総レコード数
        String l_strTotalRecords = l_pageIndexInfo.getTotalRecords() + "";
        
        //表示ページ番号
        String l_strPageIndex = l_pageIndexInfo.getPageIndex() + "";
        
        l_strRequestNumberLists =
            (String[]) l_pageIndexInfo.getArrayReturned(String.class);
        
        //1.8.1
        if (l_strRequestNumberLists.length == 0)
        {
            WEB3AdminAccOpenInspectListResponse l_response = 
                (WEB3AdminAccOpenInspectListResponse)l_request.createResponse();
            //口座開設審査対象一覧：null
            l_response.accopenInspectList = null;
            //総ページ数：0
            l_response.totalPages = l_strTotalPages;
            
            //総レコード数：0
            l_response.totalRecords = l_strTotalRecords;
            
            //表示ページ番号：表示ページ番号
            l_response.pageIndex = l_strPageIndex;
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.9. select審査対象一覧(String, String[], 口座開設ソートキー[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_strRequestNumberLists, l_request.sortKeys);
        
        //1.10. get口座開設審査待ち行数( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.11. (*)口座開設審査情報の配列オブジェクトの定義
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.12. (*)get口座開設審査待ち行数()の戻り値の件数分、Loop処理実施
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.12.1. get口座開設審査待ち行(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            
            //1.12.2. 口座開設見込客(String, String)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            //1.12.3. (*)口座開設審査情報オブジェクトの定義
            //部店コード：口座開設審査待ちParamsオブジェクト.部店コード 
            l_accOpenInspectInfos[i].branchCode = l_accOpenWaitingParams.getBranchCode();
            
            //顧客コード：口座開設審査待ちParamsオブジェクト.顧客コード 
            l_accOpenInspectInfos[i].accountCode = l_accOpenWaitingParams.getAccountCode();
            
            //識別コード：口座開設審査待ちParamsオブジェクト.識別コード 
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            
            //顧客名（カナ）：口座開設見込客オブジェクト.get顧客姓（カナ）() + "　@" + 口座開設見込客オブジェクト.get顧客名（カナ）() 
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "　@" + l_expAccountOpen.getGivenNameAlt1();
            
            //生年月日年号：口座開設見込客オブジェクト.get生年月日年号() 
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            
            //生年月日：口座開設見込客オブジェクト.get生年月日() 
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();

            //電話番号：口座開設見込客オブジェクト.get電話番号() 
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            
            //携帯番号：口座開設見込客オブジェクト.get連絡先電話番号（携帯）() 
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            
            //メールアドレス：口座開設見込客オブジェクト.getemailアドレス() 
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            
            //住所１：口座開設見込客オブジェクト.get住所１() 
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            
            //住所２：口座開設見込客オブジェクト.get住所２() 
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            
            //住所３：口座開設見込客オブジェクト.get住所３() 
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            
            //発生日時：口座開設審査待ちParamsオブジェクト.作成日時 
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            
            //審査種別：口座開設審査待ちParamsオブジェクト.審査種別 
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            
            //審査区分：口座開設審査待ちParamsオブジェクト.審査区分 
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            
            //重複区分：口座開設審査待ちParamsオブジェクト.重複区分 
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
            
            //口座開設審査待ちParamsオブジェクト.審査区分 != "0"(審査待ち)の場合
            if (!WEB3CheckDivDef.CHECK_WAITING.equals(l_accOpenWaitingParams.getCheckDiv()))
            {
	            //審査日時：口座開設審査待ちParamsオブジェクト.更新日時 
	            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
	            
	            //審査担当者：口座開設審査待ちParamsオブジェクト.審査者コード
	            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            }
            //その他の場合
            else
            {
	            //審査日時：null 
	            l_accOpenInspectInfos[i].checkDate = null;
	            
	            //審査担当者：null
	            l_accOpenInspectInfos[i].checkerCode = null;
            }

            //1.12.5. (*)審査種別が 1：同一顧客チェック の場合
            if (WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN.equals(l_accOpenInspectInfos[i].reviewCode))
            {
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_gentradeAccountManager = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                WEB3GentradeMainAccount l_mainAccount = null;
                MainAccountRow l_mainAccountRow = null;
                MainAccountAllRow l_mainAccountAllRow = null;
                
                try
                {
                    //1.12.5.1. get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                    l_mainAccount = 
                        l_gentradeAccountManager.getMainAccount(
                            l_strInstitutionCode, 
                            l_accOpenWaitingParams.getDiploBranchCode(), 
                            l_accOpenWaitingParams.getDiploAccountCode());
                    
                    // get顧客行( )
                    l_mainAccountRow = l_mainAccount.getMainAccountRow();
                }
                catch(Exception l_ex)
                {
                    l_mainAccountRow = null;
                }
                
                try
                {
                    //1.12.5.2. (*)get顧客で顧客Rowオブジェクトを取得できなかった場合
                    //1.12.5.2.1 get顧客（全部店分）(証券会社コード : String, 部店コード : String, 顧客コード : String)
                    if (l_mainAccountRow == null)
                    {
                        l_mainAccountAllRow = 
                            l_gentradeAccountManager.getMainAccountAll(
                                l_strInstitutionCode, 
                                l_accOpenWaitingParams.getDiploBranchCode(), 
                                l_accOpenWaitingParams.getDiploAccountCode());
                    }
                }
                catch(Exception l_ex)
                {
                    l_mainAccountAllRow = null; 
                }
                
                //顧客Rowオブジェクトが取得できた場合
                if (l_mainAccountRow != null)
                {
                    //重複顧客名（カナ）：顧客Rowオブジェクト.名前(苗字1) 
                    l_accOpenInspectInfos[i].dupliAccountNameKana = l_mainAccountRow.getFamilyNameAlt1();
                    
                    //重複生年月日年号：顧客Rowオブジェクト.生年月日年号 
                    l_accOpenInspectInfos[i].dupliEraBorn = l_mainAccountRow.getEraBorn();
                    
                    //重複生年月日：顧客Rowオブジェクト.生年月日 
                    l_accOpenInspectInfos[i].dupliBornDate = l_mainAccountRow.getBornDate();
                    
                    //重複電話番号：顧客Rowオブジェクト.電話番号 
                    l_accOpenInspectInfos[i].dupliTelephone = l_mainAccountRow.getTelephone();
                    
                    //重複携帯番号：顧客Rowオブジェクト.連絡先電話番号（携帯）
                    l_accOpenInspectInfos[i].dupliMobileTelephone = l_mainAccountRow.getMobile();
                    
                    //重複メールアドレス：顧客Rowオブジェクト.emailアドレス 
                    l_accOpenInspectInfos[i].dupliMailAddress = l_mainAccountRow.getEmailAddress();
                    
                    //重複住所１：顧客Rowオブジェクト.住所１ 
                    l_accOpenInspectInfos[i].dupliAddress1 = l_mainAccountRow.getAddressLine1();
                    
                    //重複住所２：顧客Rowオブジェクト.住所２ 
                    l_accOpenInspectInfos[i].dupliAddress2 = l_mainAccountRow.getAddressLine2();
                    
                    //重複住所３：顧客Rowオブジェクト.住所３
                    l_accOpenInspectInfos[i].dupliAddress3 = l_mainAccountRow.getAddressLine3();
                    
                    //重複部店コード：口座開設審査待ちParamsオブジェクト.重複対象部店コード 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //重複顧客コード：口座開設審査待ちParamsオブジェクト.重複対象顧客コード 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
                
                //顧客（全部店分）Rowオブジェクトが取得できた場合
                else if (l_mainAccountAllRow != null)
                {
                    //重複顧客名（カナ）：顧客（全部店分）Rowオブジェクト.顧客名（カナ） 
                    l_accOpenInspectInfos[i].dupliAccountNameKana = l_mainAccountAllRow.getFamilyNameAlt1();
                    
                    //重複生年月日年号：顧客（全部店分）Rowオブジェクト.生年月日（元号） 
                    l_accOpenInspectInfos[i].dupliEraBorn = l_mainAccountAllRow.getEraBorn();
                    
                    //重複生年月日：顧客（全部店分）Rowオブジェクト.生年月日 
                    if (l_mainAccountAllRow.getBornDate().length() == 8)
                    {
                        l_accOpenInspectInfos[i].dupliBornDate = 
                            l_mainAccountAllRow.getBornDate().substring(2, 8);
                    }
                    else
                    {
                        l_accOpenInspectInfos[i].dupliBornDate = null;
                    }
                    
                    //重複電話番号：顧客（全部店分）Rowオブジェクト.電話番号 
                    l_accOpenInspectInfos[i].dupliTelephone = l_mainAccountAllRow.getTelephone();
                    
                    //重複携帯番号：顧客（全部店分）Rowオブジェクト.連絡先電話番号 
                    l_accOpenInspectInfos[i].dupliMobileTelephone = l_mainAccountAllRow.getContactAddressTelephone();
                    
                    //重複メールアドレス：null  
                    l_accOpenInspectInfos[i].dupliMailAddress = null;
                    
                    //重複住所１：顧客（全部店分）Rowオブジェクト.住所漢字１ 
                    l_accOpenInspectInfos[i].dupliAddress1 = l_mainAccountAllRow.getAddressLine1();
                    
                    //重複住所２：顧客（全部店分）Rowオブジェクト.住所漢字2 
                    l_accOpenInspectInfos[i].dupliAddress2 = l_mainAccountAllRow.getAddressLine2();
                    
                    //重複住所３：顧客（全部店分）Rowオブジェクト.住所漢字3
                    l_accOpenInspectInfos[i].dupliAddress3 = l_mainAccountAllRow.getAddressLine3();
                    
                    //重複部店コード：口座開設審査待ちParamsオブジェクト.重複対象部店コード 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //重複顧客コード：口座開設審査待ちParamsオブジェクト.重複対象顧客コード 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
                
                //顧客と顧客（全部店分）が両方とも取得できない場合
                else
                {
                    //重複部店コード：口座開設審査待ちParamsオブジェクト.重複対象部店コード 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //重複顧客コード：口座開設審査待ちParamsオブジェクト.重複対象顧客コード 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
                
            }
            
            //1.12.6. (*)審査種別が 2：同一顧客チェック（見込） の場合
            else if (WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP.equals(l_accOpenInspectInfos[i].reviewCode))
            {
                //1.12.6.1. 口座開設見込客(String, String, String)
                try
                {
                    l_expAccountOpen = 
                        new WEB3AccOpenExpAccountOpen(
                            l_strInstitutionCode, 
                            l_accOpenWaitingParams.getDiploBranchCode(), 
                            l_accOpenWaitingParams.getDiploAccountCode());
                }
                catch(NotFoundException l_ex)
                {
                    //口座開設見込客が取得できない場合
                    l_expAccountOpen = null;
                    
                    //重複部店コード：口座開設審査待ちParamsオブジェクト.重複対象部店コード 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //重複顧客コード：口座開設審査待ちParamsオブジェクト.重複対象顧客コード 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
                
                if(l_expAccountOpen != null)
                {
                    //1.12.6.1.1. (*)プロパティセット
                    //重複顧客名（カナ）：口座開設見込客.get顧客姓（カナ） + "　@" + 口座開設見込客.get顧客名（カナ）
                    l_accOpenInspectInfos[i].dupliAccountNameKana = 
                        l_expAccountOpen.getFamilyNameAlt1() + "　@" + l_expAccountOpen.getGivenNameAlt1();
                    
                    //重複生年月日年号：口座開設見込客.get生年月日年号 
                    l_accOpenInspectInfos[i].dupliEraBorn = l_expAccountOpen.getEraBorn();
                    
                    //重複生年月日：口座開設見込客.get生年月日 
                    l_accOpenInspectInfos[i].dupliBornDate = l_expAccountOpen.getBornDate();
                    
                    //重複電話番号：口座開設見込客.get電話番号 
                    l_accOpenInspectInfos[i].dupliTelephone = l_expAccountOpen.getTelephone();
                    
                    //重複携帯番号：口座開設見込客.get連絡先電話番号（携帯） 
                    l_accOpenInspectInfos[i].dupliMobileTelephone = l_expAccountOpen.getMobile();                
                    
                    //重複メールアドレス：口座開設見込客.getemailアドレス 
                    l_accOpenInspectInfos[i].dupliMailAddress = l_expAccountOpen.getEmailAddress();
                    
                    //重複住所１：口座開設見込客.get住所１ 
                    l_accOpenInspectInfos[i].dupliAddress1 = l_expAccountOpen.getAddressLine1();
                    
                    //重複住所２：口座開設見込客.get住所２ 
                    l_accOpenInspectInfos[i].dupliAddress2 = l_expAccountOpen.getAddressLine2();
                    
                    //重複住所３：口座開設見込客.get住所３ 
                    l_accOpenInspectInfos[i].dupliAddress3 = l_expAccountOpen.getAddressLine3();
                    
                    //重複部店コード：口座開設審査待ちParamsオブジェクト.重複対象部店コード 
                    l_accOpenInspectInfos[i].dupliBranchCode = l_accOpenWaitingParams.getDiploBranchCode();
                    
                    //重複顧客コード：口座開設審査待ちParamsオブジェクト.重複対象顧客コード 
                    l_accOpenInspectInfos[i].dupliAccountCode = l_accOpenWaitingParams.getDiploAccountCode();
                }
            }
            
            //1.12.7. (*)審査種別が 3：Y客チェック の場合
            else if (WEB3ReviewCodeDef.YELLOW_ACCOUNT_CHECK.equals(l_accOpenInspectInfos[i].reviewCode))
            {
                //1.12.7.1. (*)プロパティセット
                //Y客ID：口座開設審査待ちParamsオブジェクト.Y客ID
                l_accOpenInspectInfos[i].yellowAccountId = String.valueOf(l_accOpenWaitingParams.getYCustomerId());
                
                //Y客管理部店コード：口座開設審査待ちParamsオブジェクト.Y客管理部店コード 
                l_accOpenInspectInfos[i].yAccountBranchCode = l_accOpenWaitingParams.getControlBranchCode();
                
                //Y客業務区分：口座開設審査待ちParamsオブジェクト.Y客業務区分 
                l_accOpenInspectInfos[i].yAccountBusinessDiv = l_accOpenWaitingParams.getOperationDiv();
                
                //Y客管理No：口座開設審査待ちParamsオブジェクト.Y客管理No 
                l_accOpenInspectInfos[i].yAccountMngNo = String.valueOf(l_accOpenWaitingParams.getControlNumber());
            }
        }
        
        //1.13. (*)プロパティセット
        WEB3AdminAccOpenInspectListResponse l_response = (WEB3AdminAccOpenInspectListResponse)l_request.createResponse();
        
        //口座開設審査対象一覧：口座開設審査情報オブジェクトの配列
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        //総ページ数：総ページ数
        l_response.totalPages = l_strTotalPages;
        
        //総レコード数：総レコード数
        l_response.totalRecords = l_strTotalRecords;
        
        //表示ページ番号：表示ページ番号
        l_response.pageIndex = l_strPageIndex;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate承認)<BR>
     * 口座開設審査承認確認画面表示処理を行う。<BR> 
     * <BR>
     * シーケンス図<BR> 
     * 「口座開設（審査）validate承認」参照。<BR> 
     * ==========================================================<BR>
     * 具体位置     :　@1.9.(*) 承認済・否認済のデータが存在する場合<BR>
     * （is審査済() == true）、例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02448 <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査承認確認リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectConsentConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C757029A
     */
    protected WEB3AdminAccOpenInspectConsentConfirmResponse validateConsent(
        WEB3AdminAccOpenInspectConsentConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateConsent(WEB3AdminAccOpenInspectConsentConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, true);
        
        //1.4. to口座開設審査待ち( )
        //1.4.1. 口座開設審査待ち( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        
        //1.5. get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6. select審査対象一覧(String, String[], 口座開設ソートキー[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_request.requestNumber, l_request.sortKeys);
        
        //1.7. get口座開設審査待ち行数( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.8. is審査済( )   
        //1.9. (*) 承認済・否認済のデータが存在する場合（is審査済() == true）、例外をスローする。
        if (l_judgeWaiting.isChecked())
        {
            log.debug("対象のデータは審査済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02448,
                this.getClass().getName() + STR_METHOD_NAME,
                "対象のデータは審査済です。");
        }
        
        //1.10. (*)口座開設審査情報の配列オブジェクトの定義
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.11. (*)get口座開設審査待ち行数()の戻り値の件数分、Loop処理実施
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.11.1 get口座開設審査待ち行(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            //1.11.2 口座開設見込客(String, String)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。",l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            
            //1.11.3. (*)口座開設審査情報オブジェクトの定義
            //1.11.4. (*)プロパティセット
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            l_accOpenInspectInfos[i].branchCode = null;
            l_accOpenInspectInfos[i].accountCode = null;
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "　@" + l_expAccountOpen.getGivenNameAlt1();
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
        }
        
        //1.12. (*)プロパティセット
        WEB3AdminAccOpenInspectConsentConfirmResponse l_response = (WEB3AdminAccOpenInspectConsentConfirmResponse)l_request.createResponse();
        
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit承認)<BR>
     * 口座開設審査承認完了画面表示処理を行う。<BR> 
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（審査）submit承認」参照。<BR>
     * ==========================================================<BR>
     * 具体位置     :　@1.10.(*) 承認済・否認済のデータが存在する場合<BR>
     * （is審査済() == true）、例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02448 <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査承認完了リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectConsentCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C75C025C
     */
    protected WEB3AdminAccOpenInspectConsentCompleteResponse submitConsent(
        WEB3AdminAccOpenInspectConsentCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitConsent(WEB3AdminAccOpenInspectConsentCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, true);
        
        //1.4. to口座開設審査待ち( )
        //1.4.1. 口座開設審査待ち( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        
        //1.5. get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6. select審査対象一覧(String, String[], 口座開設ソートキー[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_request.requestNumber, l_request.sortKeys);
        
        //1.7. get口座開設審査待ち行数( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.8. validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.9. is審査済( )   
        //1.10. (*) 承認済・否認済のデータが存在する場合（is審査済() == true）、例外をスローする。
        if (l_judgeWaiting.isChecked())
        {
            log.debug("対象のデータは審査済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02448,
                this.getClass().getName() + STR_METHOD_NAME,
                "対象のデータは審査済です。");
        }
        
        //1.11. get管理者コード( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.12. (*)口座開設審査情報の配列オブジェクトの定義
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.13. (*)get口座開設審査待ち行数()の戻り値の件数分、Loop処理実施
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.13.1. get口座開設審査待ち行(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            
            //1.13.2. 口座開設見込客(String, String)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            
            //1.13.3. (*)プロパティセット
            //口座開設見込客.部店コード
            l_accOpenWaitingParams.setBranchCode(l_expAccountOpen.getBranchCode());
            l_accOpenWaitingParams.setAccountCode(l_expAccountOpen.getAccountCode());
            l_accOpenWaitingParams.setCheckDiv(WEB3CheckDivDef.AGREE);
            l_accOpenWaitingParams.setCheckerCode(l_strAdministratorCode);
            l_accOpenWaitingParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //1.13.4. set口座開設審査待ち行(口座開設審査待ちParam, int)
            l_judgeWaiting.setAccOpenWaitingParams(l_accOpenWaitingParams, i);
            
            //1.13.5. create口座開設伝票(口座開設見込客)
            WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
                (WEB3AccOpenVoucherCreatedService)Services.getService(WEB3AccOpenVoucherCreatedService.class);
            l_voucherCreatedService.createAccOpenVoucher(l_expAccountOpen);
            
            //1.13.6. (*)口座開設審査情報オブジェクトの定義
            //1.13.7. (*)プロパティセット
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            l_accOpenInspectInfos[i].branchCode = null;
            l_accOpenInspectInfos[i].accountCode = null;
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "　@" + l_expAccountOpen.getGivenNameAlt1();
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
        }
        
        //1.14. update口座開設審査待ち( )
        l_judgeWaiting.updateAccOpenWaiting();
        
        //1.15. (*)プロパティセット
        WEB3AdminAccOpenInspectConsentCompleteResponse l_response = (WEB3AdminAccOpenInspectConsentCompleteResponse)l_request.createResponse();
        
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate否認)<BR>
     * 口座開設審査否認確認画面表示処理を行う。<BR> 
     * <BR>
     * シーケンス図<BR> 
     * 「口座開設（審査）validate否認」参照。<BR> 
     * ==========================================================<BR>
     * 具体位置     :　@1.9.(*) 承認済・否認済のデータが存在する場合<BR>
     * （is審査済() == true）、例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02448 <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査否認確認リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectDenyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C76300A6
     */
    protected WEB3AdminAccOpenInspectDenyConfirmResponse validateDeny(
            WEB3AdminAccOpenInspectDenyConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDeny(WEB3AdminAccOpenInspectDenyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, true);
        
        //1.4. to口座開設審査待ち( )
        //1.4.1. 口座開設審査待ち( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        
        //1.5. get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6. select審査対象一覧(String, String[], 口座開設ソートキー[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_request.requestNumber, l_request.sortKeys);
        
        //1.7. get口座開設審査待ち行数( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.8. is審査済( )   
        //1.9. (*) 承認済・否認済のデータが存在する場合（is審査済() == true）、例外をスローする。
        if (l_judgeWaiting.isChecked())
        {
            log.debug("対象のデータは審査済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02448,
                this.getClass().getName() + STR_METHOD_NAME,
                "対象のデータは審査済です。");
        }
        
        //1.10. (*)口座開設審査情報の配列オブジェクトの定義
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.11. (*)get口座開設審査待ち行数()の戻り値の件数分、Loop処理実施
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.11.1 get口座開設審査待ち行(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            
            //1.11.2 口座開設見込客(口座開設見込客Params)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            
            //1.11.3. (*)口座開設審査情報オブジェクトの定義
            //1.11.4. (*)プロパティセット
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            l_accOpenInspectInfos[i].branchCode = null;
            l_accOpenInspectInfos[i].accountCode = null;
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "　@" + l_expAccountOpen.getGivenNameAlt1();
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
        }
        
        //1.12. (*)プロパティセット
        WEB3AdminAccOpenInspectDenyConfirmResponse l_response = (WEB3AdminAccOpenInspectDenyConfirmResponse)l_request.createResponse();
        
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit否認)<BR>
     * 口座開設審査否認完了画面表示処理を行う。<BR> 
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（審査）submit否認」参照。<BR> 
     * ==========================================================<BR>
     * 具体位置     :　@1.10.(*) 承認済・否認済のデータが存在する場合<BR>
     * （is審査済() == true）、例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02448 <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査否認完了リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectDenyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473C76701DF
     */
    protected WEB3AdminAccOpenInspectDenyCompleteResponse submitDeny(
        WEB3AdminAccOpenInspectDenyCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitDeny(WEB3AdminAccOpenInspectDenyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. validate( )
        l_request.validate();
        
        //1.2. getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
        
        //1.3. validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.JUDGE, true);
        
        //1.4. to口座開設審査待ち( )
        //1.4.1. 口座開設審査待ち( )
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        WEB3AccOpenJudgeWaiting l_judgeWaiting = 
            l_infoCreatedService.toAccOpenJudgeWaiting();
        
        //1.5. get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6. select審査対象一覧(String[], 口座開設ソートキー[])
        l_judgeWaiting.selectJudgeObjectList(l_strInstitutionCode, l_request.requestNumber, l_request.sortKeys);
        
        //1.7. get口座開設審査待ち行数( )
        int l_intAccOpenWaitingParams = l_judgeWaiting.getAccOpenWaitingParamsNumber();
        
        //1.8. validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.9. is審査済( )   
        //1.10. (*) 承認済・否認済のデータが存在する場合（is審査済() == true）、例外をスローする。
        if (l_judgeWaiting.isChecked())
        {
            log.debug("対象のデータは審査済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02448,
                this.getClass().getName() + STR_METHOD_NAME,
                "対象のデータは審査済です。");
        }
        
        //1.11. get管理者コード( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.12. (*)口座開設審査情報の配列オブジェクトの定義
        WEB3AccOpenInspectInfo[] l_accOpenInspectInfos = 
            new WEB3AccOpenInspectInfo[l_intAccOpenWaitingParams];
        
        //1.13. (*)get口座開設審査待ち行数()の戻り値の件数分、Loop処理実施
        for (int i = 0; i < l_intAccOpenWaitingParams; i++)
        {
            //1.13.1. get口座開設審査待ち行(int)
            AccOpenWaitingParams l_accOpenWaitingParams = 
                l_judgeWaiting.getAccOpenWaitingParams(i);
            WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
            
            //1.13.2. 口座開設見込客(口座開設見込客Params)
            try
            {
                l_expAccountOpen = 
                    new WEB3AccOpenExpAccountOpen(
                        l_strInstitutionCode, 
                        l_accOpenWaitingParams.getAccOpenRequestNumber());
            }
            catch(NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            
            //1.13.3. (*)プロパティセット
            //口座開設見込客.部店コード
            l_accOpenWaitingParams.setCheckDiv(WEB3CheckDivDef.DISAGREE);
            l_accOpenWaitingParams.setCheckerCode(l_strAdministratorCode);
            l_accOpenWaitingParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //1.13.4. set口座開設審査待ち行(口座開設審査待ちParam, int)
            l_judgeWaiting.setAccOpenWaitingParams(l_accOpenWaitingParams, i);
            
            //1.13.5. (*)口座開設審査情報オブジェクトの定義
            //1.13.6. (*)プロパティセット
            l_accOpenInspectInfos[i] = new WEB3AccOpenInspectInfo();
            l_accOpenInspectInfos[i].branchCode = null;
            l_accOpenInspectInfos[i].accountCode = null;
            l_accOpenInspectInfos[i].requestNumber = l_accOpenWaitingParams.getAccOpenRequestNumber();
            l_accOpenInspectInfos[i].accountNameKana = 
                l_expAccountOpen.getFamilyNameAlt1() + "　@" + l_expAccountOpen.getGivenNameAlt1();
            l_accOpenInspectInfos[i].bornDate = l_expAccountOpen.getBornDate();
            l_accOpenInspectInfos[i].eraBorn = l_expAccountOpen.getEraBorn();
            l_accOpenInspectInfos[i].telephone = l_expAccountOpen.getTelephone();
            l_accOpenInspectInfos[i].mobileTelephone = l_expAccountOpen.getMobile();
            l_accOpenInspectInfos[i].mailAddress = l_expAccountOpen.getEmailAddress();
            l_accOpenInspectInfos[i].address1 = l_expAccountOpen.getAddressLine1();
            l_accOpenInspectInfos[i].address2 = l_expAccountOpen.getAddressLine2();
            l_accOpenInspectInfos[i].address3 = l_expAccountOpen.getAddressLine3();
            l_accOpenInspectInfos[i].occurredDate = l_accOpenWaitingParams.getCreatedTimestamp();
            l_accOpenInspectInfos[i].reviewCode = l_accOpenWaitingParams.getReviewCode();
            l_accOpenInspectInfos[i].checkDiv = l_accOpenWaitingParams.getCheckDiv();
            l_accOpenInspectInfos[i].checkDate = l_accOpenWaitingParams.getLastUpdatedTimestamp();
            l_accOpenInspectInfos[i].checkerCode = l_accOpenWaitingParams.getCheckerCode();
            l_accOpenInspectInfos[i].duplicateDiv = l_accOpenWaitingParams.getDuplicationDiv();
        }
        
        //1.14. update口座開設審査待ち( )
        l_judgeWaiting.updateAccOpenWaiting();
        
        //1.15. (*)プロパティセット
        WEB3AdminAccOpenInspectDenyCompleteResponse l_response = (WEB3AdminAccOpenInspectDenyCompleteResponse)l_request.createResponse();
        
        l_response.accopenInspectList = l_accOpenInspectInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
}
@
