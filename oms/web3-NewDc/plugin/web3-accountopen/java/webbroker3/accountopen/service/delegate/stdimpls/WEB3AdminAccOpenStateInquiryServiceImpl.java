head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設状況問合せサービスImpl(WEB3AdminAccOpenStateInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
                   2006/06/09 周捷 (中訊) 仕様変更 モデル050、053、059
                   2006/08/15 張騰宇(中訊) 仕様変更 モデル090
                   2006/08/16 張騰宇(中訊) 仕様変更 モデル093
                   2006/09/11 柴雙紅(中訊) 仕様変更 モデル101
                   2006/09/19 柴雙紅 (中訊) 仕様変更 モデル098
                   2006/09/27 柴雙紅 (中訊) 仕様変更 モデル106
                   2007/01/12 岡安 (SCS) （モデル）120修正
Revesion History : 2009/08/13 柴双紅 (中訊) 仕様変更 モデル No.173、174、184、186
Revesion History : 2010/02/10 武波 (中訊) 仕様変更モデル No.217
Revesion History : 2010/02/21 張騰宇 (中訊) 仕様変更モデル No.219
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenInvalidValues;
import webbroker3.accountopen.WEB3AccOpenJudgeWaiting;
import webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus;
import webbroker3.accountopen.data.AccOpenWaitingParams;
import webbroker3.accountopen.data.AccOpenWaitingRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenAccountOpenDivDef;
import webbroker3.accountopen.define.WEB3AccOpenExclusiveAccountWarningDivDef;
import webbroker3.accountopen.define.WEB3AccOpenTaxTypeDivDef;
import webbroker3.accountopen.define.WEB3AccOpenUpdateItemDef;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenKeyItemDef;
import webbroker3.accountopen.define.WEB3AccountOpenStatusDivDef;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.message.WEB3AccOpenChangeRequest;
import webbroker3.accountopen.message.WEB3AccOpenChangeResponse;
import webbroker3.accountopen.message.WEB3AccOpenExclusiveAccountInfo;
import webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo;
import webbroker3.accountopen.message.WEB3AccOpenSortKey;
import webbroker3.accountopen.message.WEB3AccOpenStateUnit;
import webbroker3.accountopen.message.WEB3AccOpenVoucherInfo;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenAccountCodeService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenStateInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CheckAlartUpdateDef;
import webbroker3.common.define.WEB3ExclusiveUseAccountStatusDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3SpecialAccDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.ExclusiveUseAccountCondRow;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (管理者口座開設状況問合せサービスImpl)<BR>
 * 管理者口座開設状況問合せサービス実装クラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AdminAccOpenStateInquiryServiceImpl implements WEB3AdminAccOpenStateInquiryService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenStateInquiryServiceImpl.class);

    /**
     * @@roseuid 41B45E7102AF
     */
    public WEB3AdminAccOpenStateInquiryServiceImpl() 
    {
     
    }
    
    /**
     * (部店プリファ@レンス)<BR>
     * 部店プリファ@レンス値<BR> 
     * <BR>
     * <BR>
     * [設定値] <BR>
     * 0：チェック不要-アラート表示無-審査待ちUPDATE無 <BR>
     * 1：チェック実行-アラート表示有-審査待ちUPDATE無 <BR>
     * 2：チェック実行-アラート表示無-審査待ちUPDATE有 <BR>
     * 3：チェック実行-アラート表示有-審査待ちUPDATE有 <BR>
     */
    private int branchPreferences = 0;
    
    /**
     * 口座開設状況問合せ処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設状況問合せ入力リクエストの場合<BR> 
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設状況問合せ一覧リクエストの場合 <BR>
     * 　@－get口座開設状況一覧()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設状況問合せ詳細リクエストの場合 <BR>
     * 　@－get口座開設状況詳細()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設申込更新確認リクエストの場合 <BR>
     * 　@－validate申込更新()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設申込更新完了リクエストの場合<BR> 
     * 　@－submit申込更新()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設伝票作成確認リクエストの場合 <BR>
     * 　@－validate伝票作成()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設伝票作成完了リクエストの場合 <BR>
     * 　@－submit伝票作成()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設伝票取消確認リクエストの場合 <BR>
     * 　@－validate伝票取消()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設伝票取消完了リクエストの場合 <BR>
     * 　@－submit伝票取消()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者口座開設切替リクエストの場合<BR>
     * 　@－submit切替()をコールする<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41943B38039C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";

        log.entering(STR_METHOD_NAME);
        
        //引数のリクエストデータが、管理者口座開設状況問合せ入力リクエストの場合
        if (l_request instanceof WEB3AdminAccOpenStateInquiryInputRequest)
        {
            //get入力画面()をコールする。
            log.info("WEB3AdminAccOpenStateInquiryInputRequest");
            WEB3AdminAccOpenStateInquiryInputResponse l_response = getInputScreen(
                (WEB3AdminAccOpenStateInquiryInputRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenStateInquiryListRequest)
        //引数のリクエストデータが、管理者口座開設状況問合せ一覧リクエストの場合
        {
            //get口座開設状況一覧()をコールする。
            log.info("WEB3AdminAccOpenStateInquiryListRequest");
            WEB3AdminAccOpenStateInquiryListResponse l_response =  getAccOpenStatusList(
                (WEB3AdminAccOpenStateInquiryListRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenStateInquiryDetailRequest)
        // 引数のリクエストデータが、管理者口座開設状況問合せ詳細リクエストの場合
        {
            //get口座開設状況詳細()をコールする。
            log.info("WEB3AdminAccOpenStateInquiryDetailRequest");
            WEB3AdminAccOpenStateInquiryDetailResponse l_response =  getAccOpenStatusDetail(
                (WEB3AdminAccOpenStateInquiryDetailRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUpdateConfirmRequest)
        // 引数のリクエストデータが、管理者口座開設申込更新確認リクエストの場合 
        {
            //validate申込更新()をコールする。
            log.info("WEB3AdminAccOpenApplyUpdateConfirmRequest");
            WEB3AdminAccOpenApplyUpdateConfirmResponse l_response =  validateRegistUpdated(
                (WEB3AdminAccOpenApplyUpdateConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyUpdateCompleteRequest)
        // 引数のリクエストデータが、管理者口座開設申込更新完了リクエストの場合 
        {
            //submit申込更新()をコールする。
            log.info("WEB3AdminAccOpenApplyUpdateCompleteRequest");
            WEB3AdminAccOpenApplyUpdateCompleteResponse l_response =  submitRegistUpdated(
                (WEB3AdminAccOpenApplyUpdateCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenVoucherMakeConfirmRequest)
        // 引数のリクエストデータが、管理者口座開設伝票作成確認リクエストの場合  
        {
            //validate伝票作成()をコールする。
            log.info("WEB3AdminAccOpenVoucherMakeConfirmRequest");
            WEB3AdminAccOpenVoucherMakeConfirmResponse l_response =  validateVoucherCreated(
                (WEB3AdminAccOpenVoucherMakeConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenVoucherMakeCompleteRequest)
        // 引数のリクエストデータが、管理者口座開設伝票作成完了リクエストの場合 
        {
            //submit伝票作成()をコールする。
            log.info("WEB3AdminAccOpenVoucherMakeCompleteRequest");
            WEB3AdminAccOpenVoucherMakeCompleteResponse l_response =  submitVoucherCreated(
                (WEB3AdminAccOpenVoucherMakeCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenVoucherCancelConfirmRequest)
        // 引数のリクエストデータが、管理者口座開設伝票取消確認リクエストの場合 
        {
            //validate伝票取消()をコールする。
            log.info("WEB3AdminAccOpenVoucherCancelConfirmRequest");
            WEB3AdminAccOpenVoucherCancelConfirmResponse l_response =  validateVoucherCanceled(
                (WEB3AdminAccOpenVoucherCancelConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenVoucherCancelCompleteRequest)
        // 引数のリクエストデータが、管理者口座開設伝票取消完了リクエストの場合 
        {
            //submit伝票取消()をコールする
            log.info("WEB3AdminAccOpenVoucherCancelCompleteRequest");
            WEB3AdminAccOpenVoucherCancelCompleteResponse l_response =  submitVoucherCanceled(
                (WEB3AdminAccOpenVoucherCancelCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AccOpenChangeRequest)
        {
            //引数のリクエストデータが、管理者口座開設切替リクエストの場合
            log.info("WEB3AccOpenChangeRequest");
            WEB3AccOpenChangeResponse l_response = submitChange(
                (WEB3AccOpenChangeRequest)l_request);

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
     * (get入力画面)<BR>
     * 口座開設状況問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者口座開設状況問合せ入力リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4194378303BB
     */
    protected WEB3AdminAccOpenStateInquiryInputResponse getInputScreen(WEB3AdminAccOpenStateInquiryInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccOpenStateInquiryInputRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2:validate権限(機@能カテゴリコード（=口座開設） : String, is更新（=false） : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.3:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4:get専用振込先口座銀行コード(String)
        String[] l_strExclusiveAccountBankCodes = this.getExclusiveAccountFinancialInstitutionCode(l_strInstitutionCode);
        
        //1.5:専用振込先口座情報を格納するArrayListオブジェクトを生成
        ArrayList l_arrayList = new ArrayList();
        
        if(l_strExclusiveAccountBankCodes != null)
        {
            for(int i = 0; i < l_strExclusiveAccountBankCodes.length; i++)
            {
                String l_strExclusiveAccountBankCode = l_strExclusiveAccountBankCodes[i];
                //1.6.1:get専用振込先口座残数合計(String, String)
                int l_inttotalNumber = 
                    this.getExclusiveAccountTotalNumber(l_strInstitutionCode, l_strExclusiveAccountBankCode);
                
                //1.6.2:get専用振込先口座警告区分(String, String, int)
                String l_strExclusiveAccountWarningDiv = 
                    this.getExclusiveAccountWarningDiv(
                        l_strInstitutionCode, 
                        l_strExclusiveAccountBankCode,
                        l_inttotalNumber);
                
                //1.6.3create専用振込先口座情報(String, int, String)
                WEB3AccOpenExclusiveAccountInfo l_ExculsiveAccountInfo = 
                    this.createExclusiveAccountInfo(
                        l_strExclusiveAccountBankCode, 
                        l_inttotalNumber, 
                        l_strExclusiveAccountWarningDiv);
                l_arrayList.add(l_ExculsiveAccountInfo);
            }
        }

        //1.7:createResponse( )
        WEB3AdminAccOpenStateInquiryInputResponse l_response = 
            (WEB3AdminAccOpenStateInquiryInputResponse)l_request.createResponse();
            
        //1.8:(*) プロパティセット
        WEB3AccOpenExclusiveAccountInfo[] l_exclusiveAccountInfo = 
            new WEB3AccOpenExclusiveAccountInfo[l_arrayList.size()];
        l_arrayList.toArray(l_exclusiveAccountInfo);
        l_response.exclusiveAccountInfoList = l_exclusiveAccountInfo;

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get口座開設状況一覧)<BR>
     * 口座開設状況一覧表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）get口座開設状況一覧」参照。 <BR>
     * @@param l_request - 管理者口座開設状況問合せ一覧リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse
     * @@roseuid 4194378303DA
     */
    protected WEB3AdminAccOpenStateInquiryListResponse getAccOpenStatusList(WEB3AdminAccOpenStateInquiryListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getAccOpenStatusList(WEB3AdminAccOpenStateInquiryListRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.4:validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:create検索条件文字列(管理者口座開設状況問合せ一覧リクエスト)
        String l_strQueryString = createQueryString(l_request);
        
        log.debug("l_strQueryString:" + l_strQueryString);
        
        //1.7:create検索条件データコンテナ(管理者口座開設状況問合せ一覧リクエスト, String)
        String[] l_strQueryContainers = createQueryContainer(l_request, l_strInstitutionCode);
        log.debug("l_strQueryContainers:" + l_strQueryContainers.toString());
        
        //1.8:createソート条件(口座開設ソートキー[])
        String l_strSortCond = createSortCond(l_request.sortKeys);
        log.debug("l_strQueryContainers:" + l_strSortCond.toString());

        //1.9:get口座開設見込客伝票(int, int, String, String[], String, Date, Date, String)
        ListPage l_lisExpAccountOpens = WEB3AccOpenExpAccountOpen.getExpAccountOpenVoucher(
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize),
            l_strQueryString, 
            l_strQueryContainers,
            l_strSortCond,
            l_request.sonarSendDateFrom,
            l_request.sonarSendDateTo,
            l_request.accountOpenStateDiv);


        int l_intLisReturnCnt = l_lisExpAccountOpens.size();
        WEB3AccOpenStateUnit[] l_accOpenStateUnits = new WEB3AccOpenStateUnit[l_intLisReturnCnt];
        
        for (int i = 0; i < l_intLisReturnCnt; i++)
        {
            log.debug("loop:" + l_intLisReturnCnt);
                
            //1.10.1:口座開設状況( )
            l_accOpenStateUnits[i] = new WEB3AccOpenStateUnit();
            WEB3AccOpenExpAccountOpen l_expAccountOpen =
                new WEB3AccOpenExpAccountOpen((ExpAccountOpenParams)l_lisExpAccountOpens.get(i));

            //1.10.2:get伝票ステータス( )
            WEB3AccOpenVoucherCreatedStatus[] l_voucherCreatedStatus = 
                l_expAccountOpen.getVoucherStatus();
                    
            //1.10.3: to伝票作成情報(口座開設伝票作成ステータス[])
            WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService)
                Services.getService(WEB3AccOpenInfoCreatedService.class);
                
            WEB3AccOpenVoucherInfo l_voucherInfo = l_service.toAccOpenVoucherInfo(l_voucherCreatedStatus);
                
            //1.10.4:get口座開設状況審査区分( )
            String l_strAccountOpenStatusDiv = l_expAccountOpen.getAccountOpenStatusCheckDiv();
                
            //1.10.5:is取消可能( )
            boolean l_blnCanceledPossible = l_expAccountOpen.isCanceledPossible();
                
            //1.10.6:getDataSourceObject( )
            ExpAccountOpenRow l_row = (ExpAccountOpenRow)l_expAccountOpen.getDataSourceObject();
                
            //1.10.7:プロパティセット
            //識別コード:  口座開設見込客List[index].口座開設見込客行.識別コード
            l_accOpenStateUnits[i].requestNumber = l_row.getAccOpenRequestNumber();
                
            //部店コード：　@口座開設見込客List[index].口座開設見込客行.部店コード
            l_accOpenStateUnits[i].branchCode = l_row.getBranchCode();
                
            //顧客コード：　@口座開設見込客List[index].口座開設見込客行.口座コード
            l_accOpenStateUnits[i].accountCode = l_row.getAccountCode();
                
            //入力区分：　@口座開設見込客List[index].口座開設見込客行.入力区分
            l_accOpenStateUnits[i].inputDiv = l_row.getOrderDiv();

            //作成者コード：  口座開設見込客List[index].口座開設見込客行.作成者コード
            l_accOpenStateUnits[i].creatorCode = l_row.getCreator();
                
            //資料請求日：　@口座開設見込客List[index].口座開設見込客行.資料請求日時
            l_accOpenStateUnits[i].infoClaimDate = l_row.getInfomationClaimDatetime();
                
            //口座開設日：　@口座開設見込客List[index].口座開設見込客行.口座登録日
            l_accOpenStateUnits[i].accountOpenDate = l_row.getAccountOpenDate();

            //削除フラグ：　@口座開設見込客List[index].口座開設見込客行.削除フラグ
            if (l_row.getDeleteFlag() != null)
            {
                l_accOpenStateUnits[i].deleteFlag = l_row.getDeleteFlag().intValue() + "";
            }

            //削除日時：　@口座開設見込客List[index].口座開設見込客行.削除日時
            l_accOpenStateUnits[i].deleteDate = l_row.getDeleteTimestamp();

            //印刷フラグ：　@口座開設見込客List[index].口座開設見込客行.印刷フラグ
            l_accOpenStateUnits[i].printFlag = l_row.getPrintFlag();

            //受領フラグ：　@口座開設見込客List[index].口座開設見込客行.受領フラグ
            if (l_row.getReceiptFlag() != null)
            {
                l_accOpenStateUnits[i].receiveFlag = l_row.getReceiptFlag().intValue() + "";
            }

            //特定口座区分：　@特定口座区分（※）
            //口座開設見込客List[index].口座開設見込客行.特定口座区分=
            //  「0：一般口座」の場合、「0：一般口座」
            if (WEB3TaxTypeDivDef.NORMAL.equals(l_row.getSpecialAcc()))
            {
                l_accOpenStateUnits[i].taxTypeDiv = WEB3AccOpenTaxTypeDivDef.NORMAL;
            }
            //口座開設見込客List[index].口座開設見込客行.特定口座区分=
            //  「1：特定口座（源泉徴収なし）」の場合、「1：特定口座」
            //口座開設見込客List[index].口座開設見込客行.特定口座区分=
            //  「2：特定口座（源泉徴収あり）」の場合、「1：特定
            if (WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE.equals(l_row.getSpecialAcc())
                || WEB3TaxTypeDivDef.SPECIAL_SOURCE.equals(l_row.getSpecialAcc()))
            {
                l_accOpenStateUnits[i].taxTypeDiv = WEB3AccOpenTaxTypeDivDef.SPECIAL;
            }

            //外国人フラグ：　@口座開設見込客List[index].口座開設見込客行.外国人フラグ
            if (l_row.getForeignFlag() != null)
            {
                l_accOpenStateUnits[i].foreignerFlag = l_row.getForeignFlag().intValue() + "";
            }

            //顧客姓（カナ）：　@口座開設見込客List[index].口座開設見込客行.顧客姓（カナ）
            l_accOpenStateUnits[i].accountFamilyNameKana = l_row.getFamilyNameAlt1();
                
            //顧客名（カナ）：　@口座開設見込客List[index].口座開設見込客行.顧客名（カナ）
            l_accOpenStateUnits[i].accountNameKana = l_row.getGivenNameAlt1();
   
            //口座開設状況：　@get口座開設状況審査区分()
            l_accOpenStateUnits[i].accountOpenStateDiv = l_strAccountOpenStatusDiv;
            
            //専用振込先口座番号:  口座開設見込客List[index].口座開設見込客行.専用振込先口座番号
            l_accOpenStateUnits[i].exclusiveAccountCode = l_row.getExclusiveUseAccountNo();
                
            //取消可能フラグ：　@is取消可能()
            l_accOpenStateUnits[i].cancelFlag = l_blnCanceledPossible;
                
            //伝票作成情報：　@to伝票作成情報()
            l_accOpenStateUnits[i].voucherInfo = l_voucherInfo;

            //内部者登録区分：内部者登録区分
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_row.getAccountDiv()))
            {
                l_accOpenStateUnits[i].insiderDiv = l_row.getInsiderFlag().intValue() + "";
            }
            else
            {
                l_accOpenStateUnits[i].insiderDiv = l_row.getInsiderVoucherDiv();
            }
        }        
        
        //1.11:createResponse( )
        WEB3AdminAccOpenStateInquiryListResponse l_response = 
            (WEB3AdminAccOpenStateInquiryListResponse)l_request.createResponse();
        
        //1.12:プロパティセット
        //口座開設状況一覧：　@生成した口座開設状況オブジェクトの配列。
        l_response.accountOpenStateList = l_accOpenStateUnits;
        
        //総レコード数：　@get口座開設見込客伝票()の戻り値.totalSize()
        l_response.totalRecords = Integer.toString(l_lisExpAccountOpens.totalSize());

        //総ページ数：　@get口座開設見込客伝票()の戻り値.totalPages()
        //※計算結果は小数点以下1位を切り上げた整数値。
        l_response.totalPages = Integer.toString(l_lisExpAccountOpens.totalPages());
        
        //表示ページ番号：　@get口座開設見込客伝票()の戻り値.pageNumber() + 1
        //※計算結果は小数点以下1位を切り上げた整数値。
        //pageNumberは0から始まる為、表示ページ番号を変換する。
        l_response.pageIndex = Integer.toString(l_lisExpAccountOpens.pageNumber() + 1);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get口座開設状況詳細)<BR>
     * 口座開設状況詳細表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）get口座開設状況詳細」参照。 <BR>
     * @@param l_request - 管理者口座開設状況問合せ詳細リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse
     * @@roseuid 4194386300CD
     */
    protected WEB3AdminAccOpenStateInquiryDetailResponse getAccOpenStatusDetail(WEB3AdminAccOpenStateInquiryDetailRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccOpenStatusDetail(WEB3AdminAccOpenStateInquiryDetailRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate権限(機@能カテゴリコード（=口座開設） : String, is更新（=false） : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.4:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        
        //口座開設見込客(証券会社コード : String, 部店コード : String, 識別コード : String, 口座コード : String)
        try
        {
            l_expAccountOpen = new WEB3AccOpenExpAccountOpen(
                l_strInstitutionCode, l_request.branchCode, l_request.requestNumber, l_request.accountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                " 口座開設見込客がnullである。" + l_expAccountOpen);
        }

        //1.7:get部店コード( )
        String l_strBranchCode = l_expAccountOpen.getBranchCode();
        
        //1.8:validate部店権限(String[])
        l_admin.validateBranchPermission(l_strBranchCode);
        
        //1.9:get変更不可項目一覧(口座開設見込客)
        WEB3AccOpenVoucherCreatedService l_voucherCreatedService = (WEB3AccOpenVoucherCreatedService)
            Services.getService(WEB3AccOpenVoucherCreatedService.class);
            
        String[] l_strChnagedImpossibleItemListBefores = 
            l_voucherCreatedService.getChangedImpossibleItemList(l_expAccountOpen);
        
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        //1.10:get変更不可項目一覧()戻り値の各要素毎にLOOP処理
        int l_intChnagedImpossibleItemCnt = l_strChnagedImpossibleItemListBefores.length;
        
        log.debug("l_intChnagedImpossibleItemCnt:" + l_intChnagedImpossibleItemCnt);
        
        Hashtable l_chnagedImpossibleItemList = new Hashtable();
        
        for (int i = 0; i < l_intChnagedImpossibleItemCnt; i++)
        {
            log.debug("loop:" + i);
            
            //1.10.1:toメッセージ項目名(String)            
            String l_str = 
                l_infoCreatedService.toMessageItemName(l_strChnagedImpossibleItemListBefores[i]);
            if (!"".equals(l_str))
            {
                l_chnagedImpossibleItemList.put(l_str, l_str);
            }
                
            log.debug("l_strChnagedImpossibleItemListAfters[" + i + "]:" + l_str);
            log.debug("l_strChnagedImpossibleItemListBefores[" + i + "]:" + l_strChnagedImpossibleItemListBefores[i]);
        }
        String[] l_strChnagedImpossibleItemListAfters = new String[l_chnagedImpossibleItemList.size()];
        l_chnagedImpossibleItemList.values().toArray(l_strChnagedImpossibleItemListAfters);
        
        //1.11: is伝票作成可能( )
        boolean l_blnIsVoucherCreatedPossible = l_expAccountOpen.isVoucherCreatedPossible();
        
        //1.12: is更新可能( )
        boolean l_blnIsUpdatedPossible = l_expAccountOpen.isUpdatedPossible();
        
        //1.13:get識別コード( )
        String l_strRequestNumber = l_expAccountOpen.getRequestNumber();
        
        String l_strNote1 = null;
        String l_strNote2 = null;
        WEB3AccOpenInvalidItemInfo[] l_invalidItemInfos = null;
        
        try
        {
            //1.14:口座開設不備(String, String)
            WEB3AccOpenInvalidValues l_invalidValues = 
                new WEB3AccOpenInvalidValues(l_strInstitutionCode, l_strRequestNumber);//NotFoundException
        
            //1.15:口座開設不備が生成できた場合のみ処理実施
            //1.15.1:to不備項目情報(口座開設不備)
            l_invalidItemInfos = l_infoCreatedService.toAccOpenInvalidItemInfo(l_invalidValues);
            
            //1.15.2:get備考１( )            
            l_strNote1 = l_invalidValues.getNote1();
            
            //1.15.2:get備考２( )
            l_strNote2 = l_invalidValues.getNote2();
        }
        catch (NotFoundException l_ex)
        {
            log.debug(" 口座開設不備がnullである。");
        }

        //1.16:get伝票ステータス( )
        WEB3AccOpenVoucherCreatedStatus[] l_voucherCreatedStatus = l_expAccountOpen.getVoucherStatus();
        
        //1.17:to伝票作成情報(口座開設伝票作成ステータス[])
        WEB3AccOpenVoucherInfo l_voucherInfo = 
            l_infoCreatedService.toAccOpenVoucherInfo(l_voucherCreatedStatus);
        
        //1.18:to口座開設申込情報(口座開設見込客)
        WEB3AccOpenApplyInfo l_applyInfo = 
            l_infoCreatedService.toAccOpenApplyInfo(l_expAccountOpen);
        
        //1.19:createResponse( )
        WEB3AdminAccOpenStateInquiryDetailResponse l_response = 
            (WEB3AdminAccOpenStateInquiryDetailResponse)l_request.createResponse();
            
        //1.20:プロパティセット
        //変更不可項目一覧：　@get変更不可項目一覧()の戻り値をtoメッセージ項目名()にて変換した文字列の配列
        l_response.changeUnableItemList = l_strChnagedImpossibleItemListAfters;
        
        //更新可能フラグ：　@is更新可能()
        l_response.changeFlag = l_blnIsUpdatedPossible;
        
        //伝票作成可能フラグ：　@is伝票作成可能()
        l_response.voucherFlag = l_blnIsVoucherCreatedPossible;
    
        //備考１：　@get備考１()
        l_response.bikou1 = l_strNote1;
        
        //備考２：　@get備考２()
        l_response.bikou2 = l_strNote2;
        
        //伝票作成情報：　@to伝票作成情報()
        l_response.voucherInfo = l_voucherInfo;
        
        //口座開設申込情報：　@to口座開設申込情報()
        l_response.accoutOpenApplyInfo = l_applyInfo;
        
        //不備項目情報一覧：　@to不備項目情報()
        l_response.invalidItemInfo = l_invalidItemInfos;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate申込更新)<BR>
     * 口座開設申込更新確認処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）validate申込更新」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 : 管理者・口座開設状況問合せ / 口座開設（状況問合せ）validate申込更新 <BR>
     *          具体位置     : 1.9 更新不可の場合（is更新可能() == false）、例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01315 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     *          シーケンス図 : 管理者・口座開設状況問合せ / 口座開設（状況問合せ）validate申込更新 <BR>
     *          具体位置     : 削除済みの場合（is削除済み() == true）、例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_03178<BR>
     * =============================================== <BR>
     * @@param l_request - 管理者口座開設申込更新確認リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse
     * @@roseuid 419438A7015A
     */
    protected WEB3AdminAccOpenApplyUpdateConfirmResponse validateRegistUpdated(WEB3AdminAccOpenApplyUpdateConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegistUpdated(WEB3AdminAccOpenApplyUpdateConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);
        
        //1.5:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.6:validateステータス変更(伝票作成情報, String, String)
        validateStatusUpdated(l_request.voucherInfo, 
            l_strInstitutionCode,
            l_request.accoutOpenApplyInfo.requestNumber);
        
        //1.7:to口座開設見込客(口座開設申込情報)
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = 
            l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.8:validate部店コード存在(String)
        validateBranchCode(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

        //is削除済み( )
        boolean l_blnIsDeleted = l_expAccountOpen.isDeleted();

        //削除済みの場合（is削除済み() == true）、例外をスローする。
        if (l_blnIsDeleted)
        {
            log.debug("口座開設見込客が削除済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設見込客が削除済です。" );
        }

        //1.9:is更新可能( )
        boolean l_blnIsUpdatePossible = l_expAccountOpen.isUpdatedPossible();
        
        //1.10:更新不可の場合（is更新可能() == false）、例外をスローする。
        if (!l_blnIsUpdatePossible)
        {
            log.debug("1.9:更新不可の場合");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01315,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設見込客が更新不可です。" + l_blnIsUpdatePossible);
        }
        
        log.debug("1.9:更新可の場合");
        
        //1.11:validate口座開設申込情報(String,String)
        l_expAccountOpen.validateAccountOpenRegistInfo(
            WEB3ValidateTypeDef.ADMINISTRATOR_REGIST_UPDATE,
            WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
            
        log.debug("1.10:validate口座開設申込情報(String) over!");
            
        //1.12:validate顧客コード( )
        l_expAccountOpen.validateAccountCode();
        
        ArrayList l_lisErrorList = new ArrayList();
        
        //1.13:validate金融機@関( )
        l_expAccountOpen.validateFinInstitution();
        
        //1.14:validate部店設定別チェック( )
        Collection l_collection =  validateBranchSetCheck(l_expAccountOpen);
        
        //1.15:(*)validate部店設定別チェック警告メッセージコードが返却された場合
        //（validate部店設定別チェック().size()>0）、処理実施
        if (l_collection != null || l_collection.size() > 0)
        {
            //1.14.1: addAll(arg0：Collection)
            l_lisErrorList.addAll(l_collection);
        }
      
        //1.16.1:toArray( )
        String[] l_strErrors = new String[l_lisErrorList.size()];
        l_lisErrorList.toArray(l_strErrors);
        
        //1.17: createResponse( )
        WEB3AdminAccOpenApplyUpdateConfirmResponse l_response = 
            (WEB3AdminAccOpenApplyUpdateConfirmResponse)l_request.createResponse();
            
        //1.18:プロパティセット
        l_response.warningMessageList = l_strErrors;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit申込更新)<BR>
     * 口座開設申込更新完了処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）submit申込更新」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 :  管理者・口座開設状況問合せ / 口座開設（状況問合せ）submit申込更新 <BR>
     *          具体位置     : 1.10 更新不可の場合（is更新可能() == false）、例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01315 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     *          シーケンス図 :  管理者・口座開設状況問合せ / 口座開設（状況問合せ）submit申込更新 <BR>
     *          具体位置     : 削除済みの場合（is削除済み() == true）、例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_03178<BR>
     * =============================================== <BR>
     * @@param l_request - 管理者口座開設申込更新完了リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse
     * @@roseuid 4194392B01F6
     */
    protected WEB3AdminAccOpenApplyUpdateCompleteResponse submitRegistUpdated(WEB3AdminAccOpenApplyUpdateCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitRegistUpdated(WEB3AdminAccOpenApplyUpdateCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);
        
        //1.5: validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7:validateステータス変更(伝票作成情報, String, String)
        validateStatusUpdated(l_request.voucherInfo, 
            l_strInstitutionCode,
            l_request.accoutOpenApplyInfo.requestNumber);
        
        //1.8:to口座開設見込客(口座開設申込情報)
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = 
            l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.9:validate部店コード存在(String)
        validateBranchCode(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

        //is削除済み( )
        boolean l_blnIsDeleted = l_expAccountOpen.isDeleted();

        //削除済みの場合（is削除済み() == true）、例外をスローする。
        if (l_blnIsDeleted)
        {
            log.debug("口座開設見込客が削除済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設見込客が削除済です。" );
        }

        //1.10:is更新可能( )
        boolean l_blnIsUpdatePossible = l_expAccountOpen.isUpdatedPossible();
        
        //1.11:更新不可の場合（is更新可能() == false）、例外をスローする。
        if (!l_blnIsUpdatePossible)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01315,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設見込客が更新不可です。" + l_blnIsUpdatePossible);
        }
        
        //1.12:validate口座開設申込情報(String,String)
        l_expAccountOpen.validateAccountOpenRegistInfo(
            WEB3ValidateTypeDef.ADMINISTRATOR_REGIST_UPDATE,
            WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);
        
        //1.13:validate顧客コード( )
        l_expAccountOpen.validateAccountCode();
        
        //1.14:validate金融機@関( )
        l_expAccountOpen.validateFinInstitution();

        //1.15:get管理者コード( )
        String l_strAdminCode = l_admin.getAdministratorCode();
        
        //1.16:save口座開設見込客(String)
        l_expAccountOpen.saveExpAccountOpen(WEB3ValidateTypeDef.ADMINISTRATOR_REGIST_UPDATE, l_strAdminCode);
        
        //1.17:get証券会社コード( )
        String l_strInstitutionCode2 = l_expAccountOpen.getInstitutionCode();
        
        //1.18: get識別コード( )
        String l_strRequestNumber = l_expAccountOpen.getRequestNumber();
        
        //1.19:to口座開設不備(String, String, 不備項目情報[])
        WEB3AccOpenInvalidValues l_invalidValues = l_infoCreatedService.toAccOpenInvalidValues(l_strInstitutionCode2,
            l_strRequestNumber,
            l_request.invalidItemInfo);
            
        //1.20:set備考(String, String)
        l_invalidValues.setNote(l_request.bikou1, l_request.bikou2);
            
        //1:21:save口座開設不備(String, String, String)
            l_invalidValues.saveAccOpenInvalidValues(l_strInstitutionCode2,
            l_strRequestNumber,
            l_strAdminCode);
            
        //1.22:createResponse( )
        WEB3AdminAccOpenApplyUpdateCompleteResponse l_response = 
            (WEB3AdminAccOpenApplyUpdateCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (validate伝票作成)<BR>
     * 口座開設伝票作成確認処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）validate伝票作成」参照。 <BR>
     *  <BR>
     * =============================================== <BR>
     *          シーケンス図 : 管理者・口座開設状況問合せ / 口座開設（状況問合せ）validate伝票作成 <BR>
     *          具体位置     : 1.9 伝票作成不可の場合（is伝票作成可能() == false）、例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01316 <BR>
     * =============================================== <BR> 
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 : 管理者・口座開設状況問合せ / 口座開設（状況問合せ）validate伝票作成 <BR>
     *          具体位置     : 1.12 の不備がある場合（is完了() == false）、例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01317 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 : 管理者・口座開設状況問合せ / 口座開設（状況問合せ）validate伝票作成 <BR>
     *          具体位置     : 削除済みの場合（is削除済み() == true）、例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_03178<BR>
     * =============================================== <BR>
     * @@param l_request - 管理者口座開設伝票作成確認リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse
     * @@roseuid 4194395F012B
     */
    protected WEB3AdminAccOpenVoucherMakeConfirmResponse validateVoucherCreated(WEB3AdminAccOpenVoucherMakeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateVoucherCreated(WEB3AdminAccOpenVoucherMakeConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);
        
        //1.5:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:validateステータス変更(伝票作成情報, String, String)
        validateStatusUpdated(l_request.voucherInfo, 
            l_strInstitutionCode,
            l_request.accoutOpenApplyInfo.requestNumber);
            
        //1.7:to口座開設見込客(口座開設申込情報)
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = 
            l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.8:validate部店コード存在(String)
        validateBranchCode(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

        //is削除済み( )
        boolean l_blnIsDeleted = l_expAccountOpen.isDeleted();

        //削除済みの場合（is削除済み() == true）、例外をスローする。
        if (l_blnIsDeleted)
        {
            log.debug("口座開設見込客が削除済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設見込客が削除済です。" );
        }

        //1.9: is伝票作成可能( )
        boolean l_blnIsVoucherCreatedPossible = l_expAccountOpen.isVoucherCreatedPossible();
        
        //1.10:伝票作成不可の場合（is伝票作成可能() == false）、例外をスローする。。
        if (!l_blnIsVoucherCreatedPossible)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01316,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設見込客が伝票作成不可です。" + 
                l_blnIsVoucherCreatedPossible);
        }
        
        //1.11: to口座開設不備(String, String, 不備項目情報[])
        WEB3AccOpenInvalidValues l_invalidValues = l_infoCreatedService.toAccOpenInvalidValues(
            l_request.accoutOpenApplyInfo.institutionCode,
            l_request.accoutOpenApplyInfo.requestNumber,
            l_request.invalidItemInfo);
        
        //1.12:is完了( )
        boolean l_blnIsComplete = l_invalidValues.isComplete();
        
        //1.13:未完了の不備がある場合（is完了() == false）、例外をスローする。
        if (!l_blnIsComplete)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01317,
                this.getClass().getName() + STR_METHOD_NAME,
                "不備項目が未完了です。" + l_blnIsComplete);
        }
            
        //1.14:validate口座開設申込情報(String,String)
        l_expAccountOpen.validateAccountOpenRegistInfo(
            WEB3ValidateTypeDef.VOUCHER_CREATED,
            l_request.accoutOpenApplyInfo.accountCodeAutoFlag);          
        
        ArrayList l_errorList = new ArrayList();
        
        //1.15（リクエストデータ.口座開設申込情報.顧客コード自動採番フラグ != 1）、処理実施
        if (!WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_request.accoutOpenApplyInfo.accountCodeAutoFlag))
        {
            //1.141:validate顧客コード( )
            l_expAccountOpen.validateAccountCode();
        }

        //validate自動採番(口座開設申込情報 : 口座開設申込情報, 証券会社コード : String)
        //[引数]
        //口座開設申込情報：リクエストデータ.口座開設申込情報
        //証券会社コード：get証券会社コード()
        this.validateAuto(l_request.accoutOpenApplyInfo, l_strInstitutionCode);

        //1.17:validate金融機@関( )
        l_expAccountOpen.validateFinInstitution();
        
        //1.18:validate部店設定別チェック( )
        Collection l_collection =  validateBranchSetCheck(l_expAccountOpen);

        //1.19:(*)部店設定別チェック警告メッセージコードが返却され
        //た場合（validate部店設定別チェック().size()>0）、処理実施
        if (l_collection != null && l_collection.size() > 0)
        {
            //1.18.1:addAll(arg0 : Collection)
            l_errorList.addAll(l_collection);
        }       
        
        //1.20:validate顧客名サイズ( )
        String l_strValidateAccountNameResult = l_expAccountOpen.validateAccountNameSize();
        
        //1.21:文字サイズ超過警告メッセージコードが返却された場合（validate顧客名サイズ() != null）
        if (l_strValidateAccountNameResult != null)
        {
            //1.20.1:add(arg0（=顧客名文字サイズ超過警告メッセージコード）
            l_errorList.add(l_strValidateAccountNameResult);
        }
        
        //1.22: validate住所サイズ( )
        String[] l_strValidateAddressResult = l_expAccountOpen.validateAddressSize();
        
        //1.23:文字サイズ超過警告メッセージコードが返却された場合（validate住所サイズ() != null）
        if (l_strValidateAddressResult != null)
        {
            //1.23.1:addAll(Collection)
            List l_list = Arrays.asList(l_strValidateAddressResult);
            
            l_errorList.addAll(l_list);
        }
        
        //1.24:toArray( )
        String[] l_strErrors = new String[l_errorList.size()];
        l_errorList.toArray(l_strErrors);
        
        //1.25:createResponse( )
        WEB3AdminAccOpenVoucherMakeConfirmResponse l_response = 
            (WEB3AdminAccOpenVoucherMakeConfirmResponse)l_request.createResponse();
            
        //1.26:プロパティセット
        l_response.warningMessageList = l_strErrors;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (submit伝票作成)<BR>
     * 口座開設伝票作成完了処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）submit伝票作成」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 :  管理者・口座開設状況問合せ / 口座開設（状況問合せ）submit伝票作成 <BR>
     *          具体位置     : 1.10 伝票作成不可の場合（is伝票作成可能() == false）、例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01316 <BR>
     * =============================================== <BR> 
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 :  管理者・口座開設状況問合せ / 口座開設（状況問合せ）submit伝票作成 <BR>
     *          具体位置     : 1.13 の不備がある場合（is完了() == false）、例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01317 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 :  管理者・口座開設状況問合せ / 口座開設（状況問合せ）submit伝票作成 <BR>
     *          具体位置     : 削除済みの場合（is削除済み() == true）、例外をスローする。<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_03178<BR>
     * =============================================== <BR>
     * @@param l_request - 管理者口座開設伝票作成完了リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse
     * @@roseuid 4194395F014A
     */
    protected WEB3AdminAccOpenVoucherMakeCompleteResponse submitVoucherCreated(
        WEB3AdminAccOpenVoucherMakeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitVoucherCreated(WEB3AdminAccOpenVoucherMakeCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.accoutOpenApplyInfo.branchCode);
        
        //1.5: validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7:validateステータス変更(伝票作成情報, String, String)
        validateStatusUpdated(l_request.voucherInfo, 
            l_strInstitutionCode,
            l_request.accoutOpenApplyInfo.requestNumber);
        
        //1.8:to口座開設見込客(口座開設申込情報)
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = 
            l_infoCreatedService.toAccOpenExpAccountOpen(l_request.accoutOpenApplyInfo);

        //1.9:validate部店コード存在(String)
        validateBranchCode(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

        //is削除済み( )
        boolean l_blnIsDeleted = l_expAccountOpen.isDeleted();

        //削除済みの場合（is削除済み() == true）、例外をスローする。
        if (l_blnIsDeleted)
        {
            log.debug("口座開設見込客が削除済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設見込客が削除済です。" );
        }

        //1.10:is伝票作成可能( )
        boolean l_blnIsVoucherCreatedPossible = l_expAccountOpen.isVoucherCreatedPossible();
        
        //1.11:伝票作成不可の場合（is伝票作成可能() == false）、例外をスローする。。
        if (!l_blnIsVoucherCreatedPossible)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01316,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設見込客が伝票作成不可です。" + 
                l_blnIsVoucherCreatedPossible);
        }
        
        //1.12:to口座開設不備(String, String, 不備項目情報[])
        WEB3AccOpenInvalidValues l_invalidValues = l_infoCreatedService.toAccOpenInvalidValues(
            l_request.accoutOpenApplyInfo.institutionCode,
            l_request.accoutOpenApplyInfo.requestNumber,
            l_request.invalidItemInfo);
        
        //1.13:is完了( )
        boolean l_blnIsComplete = l_invalidValues.isComplete();

        //1.14:未完了の不備がある場合（is完了() == false）、例外をスローする。
        if (!l_blnIsComplete)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01317,
                this.getClass().getName() + STR_METHOD_NAME,
                "不備項目が未完了です。" + l_blnIsComplete);
        }
        
        //1.15:set備考(String, String)
        l_invalidValues.setNote(l_request.bikou1, l_request.bikou2);
        
        //1.16:validate口座開設申込情報(String,String)
        l_expAccountOpen.validateAccountOpenRegistInfo(
            WEB3ValidateTypeDef.VOUCHER_CREATED,
            l_request.accoutOpenApplyInfo.accountCodeAutoFlag);
        
        //1.17（リクエストデータ.口座開設申込情報.顧客コード自動採番フラグ != 1）、処理実施
        if (!WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_request.accoutOpenApplyInfo.accountCodeAutoFlag))
        {
            //1.171:validate顧客コード( )
            l_expAccountOpen.validateAccountCode();
        }

        //validate自動採番(口座開設申込情報 : 口座開設申込情報, 証券会社コード : String)
        //[引数]
        //口座開設申込情報：リクエストデータ.口座開設申込情報
        //証券会社コード：get証券会社コード()
        this.validateAuto(l_request.accoutOpenApplyInfo, l_strInstitutionCode);

        //1.19:validate金融機@関( )
        l_expAccountOpen.validateFinInstitution();
        
        //1.20:validate部店設定別チェック( )
        validateBranchSetCheck(l_expAccountOpen);

        //1.21:get管理者コード( )
        String l_strAdminCode = l_admin.getAdministratorCode();
        
        //1.22（リクエストデータ.口座開設申込情報.顧客コード自動採番フラグ = 1）、処理実施
        if (WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_request.accoutOpenApplyInfo.accountCodeAutoFlag))
        {
            //1.22.1 get新規顧客コード(String, String, String)
            //[引数] 
            //　@証券会社コード：リクエストデータ.口座開設申込情報.証券会社コード 
            //　@部店コード：リクエストデータ.口座開設申込情報.部店コード 
            //　@顧客区分：リクエストデータ.顧客区分
            WEB3AccOpenAccountCodeService l_service = 
                (WEB3AccOpenAccountCodeService)Services.getService(WEB3AccOpenAccountCodeService.class);
            String l_strInstCode = l_request.accoutOpenApplyInfo.institutionCode;
            String l_strBranchCode = l_request.accoutOpenApplyInfo.branchCode;
            String l_strNewAccountCode = l_service.getNewAccountCode(
                l_strInstCode, l_strBranchCode, l_request.accountDiv);
            
            //1.22.2 set口座コード(String)
            l_expAccountOpen.setAccountCode(l_strNewAccountCode);
        }
        
        //1.23:save口座開設見込客(String, String)
        l_expAccountOpen.saveExpAccountOpen(WEB3ValidateTypeDef.VOUCHER_CREATED, l_strAdminCode);
        
        //1.24:get証券会社コード( )
        String l_strInstitutionCode2 = l_expAccountOpen.getInstitutionCode();
        
        //1.25: get識別コード( )
        String l_strRequestNumber = l_expAccountOpen.getRequestNumber();
        
        //1.26:save口座開設不備(String, String, String)
        l_invalidValues.saveAccOpenInvalidValues(l_strInstitutionCode2,
            l_strRequestNumber,
            l_strAdminCode);
            
        //1.27:get口座開設審査待ち情報リスト( )
        ArrayList l_judgeWaitingInfoList = l_expAccountOpen.getAccOpenJudgeWaitingInfoList();
        
        String[] l_strVouchers = null;
		int l_intWaitingCount = 0;
        
        //1.28:get口座開設審査待ち情報リスト().size()==0の場合は処理を行う。
        if (l_judgeWaitingInfoList == null || l_judgeWaitingInfoList.size() == 0)
        {
            //1.28.1:create口座開設伝票(口座開設見込客)
            WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
                (WEB3AccOpenVoucherCreatedService)Services.getService(WEB3AccOpenVoucherCreatedService.class);
            l_strVouchers = l_voucherCreatedService.createAccOpenVoucher(l_expAccountOpen);
        }
        
        //1.29:get口座開設審査待ち情報リスト().size()>0の場合は処理を行う。
        else if (l_judgeWaitingInfoList.size() > 0)
        {
            //1.29.1:to口座開設審査待ち( ) 
            //1.29.1.1:口座開設審査待ち( )
            WEB3AccOpenJudgeWaiting l_judgeWaiting = 
                l_infoCreatedService.toAccOpenJudgeWaiting();
            String[] l_strRequestNumbers = new String[1];
			l_strRequestNumbers[0]=l_strRequestNumber;
			//1.29.2:select審査対象一覧( )
			//1.29.3:select審査対象一覧()==0の場合は処理を行う。
			l_intWaitingCount = l_judgeWaiting.selectJudgeObjectList(
					   l_strInstitutionCode2, l_strRequestNumbers);
			if (l_intWaitingCount == 0)
			{
				//1.29.4:get口座開設審査待ち情報リスト().size()回数繰り返す。
				//1.29.4.1.1:add口座開設審査待ち行(口座開設審査待ちParam)
				for (int i = 0; i < l_judgeWaitingInfoList.size(); i++)
				{
					AccOpenWaitingRow l_accOpenWaitingRow = (AccOpenWaitingRow)l_judgeWaitingInfoList.get(i);
					AccOpenWaitingParams l_accOpenWaitingParams = new AccOpenWaitingParams(l_accOpenWaitingRow);
					l_judgeWaiting.addAccOpenWaitingParams(l_accOpenWaitingParams);
				}
            
				//1.29.3.1:insert口座開設審査待ち( )
				l_judgeWaiting.insertAccOpenWaiting();
			}          
        }
        
        //1.30:get口座開設審査待ち情報リスト().size()>0 && select審査対象一覧() != 0 の場合は処理を行う。
		//※ 審査承認後の伝票再作成用処理
        if ((l_judgeWaitingInfoList != null && l_judgeWaitingInfoList.size() > 0)
                  && l_intWaitingCount != 0)
        {
			//1.30.1:create口座開設伝票(口座開設見込客)
			WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
				(WEB3AccOpenVoucherCreatedService)Services.getService(WEB3AccOpenVoucherCreatedService.class);
			l_strVouchers = l_voucherCreatedService.createAccOpenVoucher(l_expAccountOpen);
        }
        
        //1.31:createResponse( )
        WEB3AdminAccOpenVoucherMakeCompleteResponse l_response = 
            (WEB3AdminAccOpenVoucherMakeCompleteResponse)l_request.createResponse();
            
        //1.32:プロパティセット
        l_response.creationCompleteList = l_strVouchers;
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (validate伝票取消)<BR>
     * 口座開設伝票取消確認処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）validate伝票取消」参照。 <BR>
     * <BR> 
     * =============================================== <BR>
     *          シーケンス図 : 管理者・口座開設状況問合せ / 口座開設（状況問合せ）validate伝票取消 <BR>
     *          具体位置     : 1.9 取消可能でない場合（is取消可能() == false）、例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01319 <BR>
     * =============================================== <BR>
     * @@param l_request - 管理者口座開設伝票取消確認リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse
     * @@roseuid 41943A4D037D
     */
    protected WEB3AdminAccOpenVoucherCancelConfirmResponse validateVoucherCanceled(WEB3AdminAccOpenVoucherCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateVoucherCanceled(WEB3AdminAccOpenVoucherCancelConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        
        try
        {
            //1.5:口座開設見込客(String, String)
            l_expAccountOpen = new WEB3AccOpenExpAccountOpen(
            l_strInstitutionCode,
            l_request.requestNumber);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.debug("NotFoundException");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                " 口座開設見込客がnullである。" + l_expAccountOpen); 
        }
        
        //1.6: get部店コード( )
        String l_strBranchCode = l_expAccountOpen.getBranchCode();
        
        //1.7:validate部店権限(String[])
        l_admin.validateBranchPermission(l_strBranchCode);
        
        //1.8: is取消可能( )
        boolean l_blnIsCanceledPossible = l_expAccountOpen.isCanceledPossible();
        
        //1.9: 取消可能でない場合（is取消可能() == false）、例外をスローする。
        if (!l_blnIsCanceledPossible)
        {
            log.debug("1.9: 取消可能でない場合（is取消可能() == false）");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01319,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設見込客が取消不可です。" + l_blnIsCanceledPossible);
        }
        
        //1.10: getDataSourceObject( )
        ExpAccountOpenParams l_params = (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
        
        //1.11:createResponse( )
        WEB3AdminAccOpenVoucherCancelConfirmResponse l_response = 
            (WEB3AdminAccOpenVoucherCancelConfirmResponse)l_request.createResponse();
            
        //1.12:プロパティセット
        l_response.branchCode = l_params.branch_code;
        l_response.accountCode = l_params.account_code;
        l_response.accountFamilyNameKana = l_params.family_name_alt1;
        l_response.accountNameKana = l_params.given_name_alt1;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (submit伝票取消)<BR>
     * 口座開設伝票取消完了処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）submit伝票取消」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 : 管理者・口座開設状況問合せ / 口座開設（状況問合せ）submit伝票取消 <BR>
     *          具体位置     : 1.7 口座開設見込客既存データが存在しない場合（nullが返却された場合）、<BR>
     *                         例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01318 <BR>
     * =============================================== <BR> 
     * <BR> 
     * =============================================== <BR>
     *          シーケンス図 : 管理者・口座開設状況問合せ / 口座開設（状況問合せ）submit伝票取消 <BR>
     *          具体位置     : 1.11 取消可能でない場合（is取消可能() == false）、例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01319 <BR>
     * =============================================== <BR>
     * @@param l_request - 管理者口座開設伝票取消完了リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse
     * @@roseuid 41943AB601F6
     */
    protected WEB3AdminAccOpenVoucherCancelCompleteResponse submitVoucherCanceled(WEB3AdminAccOpenVoucherCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitVoucherCanceled(WEB3AdminAccOpenVoucherCancelCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5: validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        
        //1.6:口座開設見込客(String, String)
        try
        {
            l_expAccountOpen = new WEB3AccOpenExpAccountOpen(
                l_strInstitutionCode,
                l_request.requestNumber);//NotFoundException
        }
        //1.7:口座開設見込客既存データが存在しない場合（nullが返却された場合）
        catch (NotFoundException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01318,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設見込客既存データが存在しない。");
        }
        
        //1.8: get部店コード( )
        String l_strBranchCode = l_expAccountOpen.getBranchCode();
        
        //1.9:validate部店権限(String[])
        l_admin.validateBranchPermission(l_strBranchCode);
        
        //1.10 is取消可能( )
        boolean l_blnIsCanceledPossible = l_expAccountOpen.isCanceledPossible();
        
        //1.11 取消可能でない場合（is取消可能() == false）、例外をスローする。
        if (!l_blnIsCanceledPossible)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01319,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設見込客が取消不可です。" + l_blnIsCanceledPossible);
        }
        
        //1.12:delete口座開設伝票(口座開設見込客)
        WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
            (WEB3AccOpenVoucherCreatedService)Services.getService(WEB3AccOpenVoucherCreatedService.class);
        l_voucherCreatedService.deleteAccOpenVoucher(l_expAccountOpen);     
            
        //1.11:createResponse( )
        WEB3AdminAccOpenVoucherCancelCompleteResponse l_response = 
            (WEB3AdminAccOpenVoucherCancelCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (validateステータス変更)<BR>
     * 照会時のステータスと変更がないかをチェックする。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（状況問合せ）validateステータス変更」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     *          シーケンス図 : 管理者・口座開設状況問合せ / 口座開設（状況問合せ）validateステータス変更 <BR>
     *          具体位置     : 1.5 照会時のステータスと現在のステータスが一致しない場合（equals() == false）、<BR>
     *                         例外をスローする。 <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01320 <BR>
     * =============================================== <BR>
     * @@param l_accOpenVoucherInfo - 伝票作成情報メッセージデータ
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strRequestNumber - 識別コード
     * @@roseuid 419C04260003
     */
    protected void validateStatusUpdated(WEB3AccOpenVoucherInfo l_accOpenVoucherInfo, String l_strInstitutionCode, String l_strRequestNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateStatusUpdated(WEB3AccOpenVoucherInfo, String, String) ";
        log.entering(STR_METHOD_NAME);        
        
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        
        try
        {
            //1.1:口座開設見込客(String, String)
            l_expAccountOpen = new WEB3AccOpenExpAccountOpen(
            l_strInstitutionCode,
            l_strRequestNumber);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                " 口座開設見込客がnullである。" + l_expAccountOpen);    
        }
          
        //1.2:get伝票ステータス( )
        WEB3AccOpenVoucherCreatedStatus[] l_voucherCreatedStatus = 
            l_expAccountOpen.getVoucherStatus();
        
        //1.3:to伝票作成情報(口座開設伝票作成ステータス[])
        WEB3AccOpenInfoCreatedService l_infoCreatedService = 
            (WEB3AccOpenInfoCreatedService)Services.getService(WEB3AccOpenInfoCreatedService.class);
        
        WEB3AccOpenVoucherInfo l_voucherInfo = 
            l_infoCreatedService.toAccOpenVoucherInfo(l_voucherCreatedStatus);
            
        boolean l_blnFlag = false;
        
        if (l_voucherInfo != null)
        {
            //1.4:equals(Object)
            l_blnFlag = l_accOpenVoucherInfo.equals(l_voucherInfo);
        }
        else
        {
            l_voucherInfo = new WEB3AccOpenVoucherInfo();
            l_blnFlag = l_accOpenVoucherInfo.equals(l_voucherInfo);
        }
        
        //1.5:照会時のステータスと現在のステータスが一致しない場合（equals() == false）、例外をスローする。
        if (!l_blnFlag)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01320,
                this.getClass().getName() + STR_METHOD_NAME,
                "照会時のステータスと現在のステータスが不一致です。" + 
                l_blnFlag);                 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値の検索条件文字列インスタンス（：String）を生成 <BR>
     * <BR>
     * ２）　@証券会社条件追加<BR>
     * 　@証券会社コード条件を追加する。<BR>
     * <BR>
     * 　@" institution_code = ? "<BR>
     * <BR>
     * ３）　@部店条件追加 <BR>
     * 　@（リクエストデータ.部店コード != null）の場合、<BR>
     * 　@部店コード配列の要素数分、部店コード条件を追加する。 <BR>
     * <BR>
     * 　@" and （branch_code = ? or branch_code = ? ･･･）" <BR>
     * <BR>
     * ４）　@識別コード条件追加　@※指定がある場合のみ<BR>
     * 　@（リクエストデータ.識別コード != null）の場合、識別コード条件を追加する。<BR>
     * <BR>
     * 　@" and acc_open_request_number like ? " <BR>
     * <BR>
     * ５）　@顧客コード条件追加　@※指定がある場合のみ <BR>
     * 　@（リクエストデータ.顧客コード != null）の場合、口座コード条件を追加する。<BR> 
     * <BR>
     * 　@" and account_code like ? " <BR>
     * <BR>
     * ６）　@顧客コード（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.顧客コード（自） != null）の場合、口座コード条件を追加する。 <BR>
     * <BR>
     * 　@" and account_code >= ? " <BR>
     * <BR>
     * ７）　@顧客コード（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.顧客コード（至） != null）の場合、口座コード条件を追加する。 <BR>
     * <BR>
     * 　@" and account_code <= ? " <BR>
     * <BR>
     * ８）　@口座区分条件追加<BR>
     * 　@（リクエストデータ.口座区分 != null）の場合、口座区分条件を追加する。 <BR>
     * <BR>
     * 　@" and account_div = ? " <BR>
     * <BR>
     * ９）　@国籍条件追加<BR>
     * 　@（リクエストデータ.外国人フラグ != null）の場合、国籍条件を追加する。<BR>
     * <BR>
     * 　@" and foreign_flag = ? "<BR>
     * <BR>
     * １０）　@削除条件追加<BR>
     * 　@（リクエストデータ.削除フラグ != null）の場合、削除条件を追加する。<BR>
     * <BR>
     * 　@" and delete_flag = ? "<BR>
     * <BR>
     * １１）　@印刷条件追加<BR>
     * 　@（リクエストデータ.印刷フラグ != null）の場合、印刷条件を追加する。<BR>
     * <BR>
     * 　@" and print_flag = ? "<BR>
     * <BR>
     * １２）　@受領条件追加<BR>
     * 　@（リクエストデータ.受領フラグ != null）の場合、受領条件を追加する。<BR>
     * <BR>
     * 　@" and receipt_flag = ? "<BR>
     * <BR>
     * １３）　@特定口座区分条件追加<BR>
     * 　@（リクエストデータ.特定口座区分 != null）の場合、特定口座区分条件を追加する。<BR>
     * <BR>
     * 　@　@[リクエストデータ.特定口座区分 == "一般"の場合]<BR>
     * " and special_acc = ? "<BR>
     * [リクエストデータ.特定口座区分 == "特定"の場合]<BR>
     * " and special_acc in (?, ?) "<BR>
     * <BR>
     * １４）　@顧客姓（カナ）条件追加 ※指定がある場合のみ，曖昧検索 <BR>
     * 　@（リクエストデータ.顧客姓（カナ） != null）の場合、顧客姓（カナ）条件（like）を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and family_name_alt1 like ? " <BR>
     * <BR>
     * １５）　@顧客名（カナ）条件追加 ※指定がある場合のみ，曖昧検索 <BR>
     * 　@（リクエストデータ.顧客名（カナ） != null）の場合、顧客名（カナ）条件（like）を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and given_name_alt1 like ? " <BR>
     * <BR>
     * １６）　@資料請求日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.資料請求日（自） != null）の場合、資料請求日時条件を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and to_char(infomation_claim_datetime, 'YYYYMMDD') >= ? " <BR>
     * <BR>
     * １７）　@資料請求日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.資料請求日（至） != null）の場合、資料請求日時条件を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and to_char(infomation_claim_datetime, 'YYYYMMDD') <= ? " <BR>
     * <BR>
     * １８）　@口座開設日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.口座開設日（自） != null）の場合、口座開設日条件を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and to_char(account_open_date, 'YYYYMMDD') >= ? " <BR>
     * <BR>
     * １９）　@口座開設日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.口座開設日（至） != null）の場合、口座開設日条件を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and to_char(account_open_date, 'YYYYMMDD') <= ? " <BR>
     * <BR>
     * <BR>
     * ２０）　@内部者登録区分条件追加<BR>
     * 　@（リクエストデータ.内部者登録区分 != null）　@且つ<BR>
     * 　@　@（リクエストデータ.口座区分=0：個人アカウント）の場合、内部者登録フラグ条件を追加する。<BR> 
     * <BR>
     * 　@" and insider_flag = ? "<BR>
     * <BR>
     * 　@（リクエストデータ.内部者登録区分 != null）　@且つ<BR>
     * 　@　@（リクエストデータ.口座区分=1：法@人アカウント）の場合、（内部者）作成区分条件を追加する。<BR> 
     * <BR>
     * 　@" and insider_voucher_div = ? "<BR>
     * <BR>
     * ２１）　@文字列インスタンスを返却 
     * @@param l_request - 管理者口座開設状況問合せ一覧リクエストデータオブジェクト
     * 
     * @@return String
     * @@roseuid 419C6657009F
     */
    protected String createQueryString(WEB3AdminAccOpenStateInquiryListRequest l_request) 
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminAccOpenStateInquiryListRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@戻り値生成 
        String l_strQueryString = "";
        
        //２）　@証券会社条件追加 
        l_strQueryString = " institution_code = ? "; 

        //３）　@部店条件追加 
        //（リクエストデータ.部店コード != null）の場合、 
        //部店コード配列の要素数分、部店コード条件を追加する。
        if (l_request.branchCode != null && l_request.branchCode.length > 0)
        {
            l_strQueryString += " and (";
            
            int l_intCnt = l_request.branchCode.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                l_strQueryString += "branch_code = ? or ";
            }
            
            l_strQueryString = l_strQueryString.substring(0, l_strQueryString.length() - 4) + ") ";
        }

        //４）　@識別コード条件追加　@※指定がある場合のみ 
        //（リクエストデータ.識別コード != null）の場合、識別コード条件を追加する。
        if (l_request.requestNumber != null)
        {
            l_strQueryString += " and acc_open_request_number like ? ";
        } 
        
        //５）　@顧客コード条件追加　@※指定がある場合のみ 
        //（リクエストデータ.顧客コード != null）の場合、口座コード条件を追加する。
        if (l_request.accountCode != null)
        {
            l_strQueryString += " and account_code like ? ";
        } 

        //６）　@顧客コード（自）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.顧客コード（自） != null）の場合、口座コード条件を追加する。
        if (l_request.accountCodeFrom != null)
        {
            l_strQueryString += " and account_code >= ? ";
        }
         
        //７）　@顧客コード（至）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.顧客コード（至） != null）の場合、口座コード条件を追加する。
        if (l_request.accountCodeTo != null)
        {
            l_strQueryString += " and account_code <= ? ";
        } 

        //８）　@口座区分条件追加
        //（リクエストデータ.口座区分 != null）の場合、口座区分条件を追加する。
        if (l_request.accountType != null)
        {
            l_strQueryString += " and account_div = ? ";
        } 

        //国籍条件追加
        //（リクエストデータ.外国人フラグ != null）の場合、国籍条件を追加する。
        // " and foreign_flag = ? "
        if (l_request.foreignerFlag != null)
        {
            l_strQueryString += " and foreign_flag = ? ";
        }

        //削除条件追加
        //（リクエストデータ.削除フラグ != null）の場合、削除条件を追加する。
        // " and delete_flag = ? "
        if (l_request.deleteFlag != null)
        {
            l_strQueryString += " and delete_flag = ? ";
        }

        //印刷条件追加
        //（リクエストデータ.印刷フラグ != null）の場合、印刷条件を追加する。
        // " and print_flag = ? "
        if (l_request.printFlag != null)
        {
            l_strQueryString += " and print_flag = ? ";
        }

        //受領条件追加
        //（リクエストデータ.受領フラグ != null）の場合、受領条件を追加する。
        // " and receipt_flag = ? "
        if (l_request.receiveFlag != null)
        {
            l_strQueryString += " and receipt_flag = ? ";
        }

        //特定口座区分条件追加
        //（リクエストデータ.特定口座区分 != null）の場合、特定口座区分条件を追加する。
        if (l_request.taxTypeDiv != null)
        {
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxTypeDiv))
            {
                // [リクエストデータ.特定口座区分 == "一般"の場合]
                // " and special_acc = ? "
                l_strQueryString += " and special_acc = ? ";
            }

            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxTypeDiv))
            {
                // [リクエストデータ.特定口座区分 == "特定"の場合]
                // " and special_acc in (?, ?) "
                l_strQueryString += " and special_acc in (?, ?) ";
            }
        }

        //９）　@顧客姓（カナ）条件追加 ※指定がある場合のみ，曖昧検索 
        //（リクエストデータ.顧客姓（カナ） != null）の場合、顧客姓（カナ）条件（like）を追加する。 
        if (l_request.accountFamilyNameKana != null)
        {
            l_strQueryString += " and family_name_alt1 like ? ";
        }

        //１０）　@顧客名（カナ）条件追加 ※指定がある場合のみ，曖昧検索 
        //（リクエストデータ.顧客名（カナ） != null）の場合、顧客名（カナ）条件（like）を追加する。
        if (l_request.accountNameKana != null)
        {
            l_strQueryString += " and given_name_alt1 like ? ";
        } 

        //１１）　@資料請求日（自）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.資料請求日（自） != null）の場合、資料請求日時条件を追加する。
        if (l_request.infoClaimDateFrom != null)
        {
            l_strQueryString += " and to_char(infomation_claim_datetime, 'YYYYMMDD') >= ? ";
        }  

        //１２）　@資料請求日（至）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.資料請求日（至） != null）の場合、資料請求日時条件を追加する。
        if (l_request.infoClaimDateTo != null)
        {
            l_strQueryString += " and to_char(infomation_claim_datetime, 'YYYYMMDD') <= ? ";
        }  
        
        //１３）　@口座開設日（自）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.口座開設日（自） != null）の場合、口座開設日条件を追加する。
        if (l_request.accountOpenDateFrom != null)
        {
            l_strQueryString += " and to_char(account_open_date, 'YYYYMMDD') >= ? ";
        }   

        //１４）　@口座開設日（至）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.口座開設日（至） != null）の場合、口座開設日条件を追加する。
        if (l_request.accountOpenDateTo != null)
        {
            l_strQueryString += " and to_char(account_open_date, 'YYYYMMDD') <= ? ";
        }  

        //２０）　@内部者登録区分条件追加
        //（リクエストデータ.内部者登録区分 != null）
        if (l_request.insiderDiv != null)
        {
            //（リクエストデータ.口座区分=0：個人アカウント）の場合、内部者登録フラグ条件を追加する。
            //" and insider_flag = ? "
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_request.accountType))
            {
                l_strQueryString += " and insider_flag = ? ";
            }
            //（リクエストデータ.口座区分=1：法@人アカウント）の場合、（内部者）作成区分条件を追加する。
            //" and insider_voucher_div = ? "
            else if (WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_request.accountType))
            {
                l_strQueryString += " and insider_voucher_div = ? ";
            }
        }

        log.exiting(STR_METHOD_NAME);
        
        //１５）　@文字列インスタンスを返却 
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）を生成 <BR>
     * <BR>
     * ２）　@証券会社条件追加<BR>
     * 　@戻り値編集用インスタンスに、証券会社コードを追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@証券会社コード<BR>
     * <BR>
     * ３）　@部店条件追加<BR>
     * 　@（リクエストデータ.部店コード[] != null）の場合、<BR>
     * 　@戻り値編集用インスタンスに、部店コード配列の要素数分部店コードを<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.部店コード[index]<BR>
     * <BR>
     * ４）　@識別コード条件追加　@※指定がある場合のみ<BR>
     * 　@（リクエストデータ.識別コード != null）の場合、戻り値編集用インスタンスに<BR>
     * 識別コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.識別コード + '%'<BR>
     * <BR>
     * ５）　@顧客コード条件追加　@※指定がある場合のみ <BR>
     * 　@（リクエストデータ.顧客コード != null）の場合、戻り値編集用インスタンスに<BR>
     * 顧客コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.顧客コード + '%'<BR>
     * <BR>
     * ６）　@顧客コード（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.顧客コード（自） != null）の場合、戻り値編集用インスタンスに<BR>
     * 顧客コード（自）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.顧客コード（自）<BR>
     * <BR>
     * ７）　@顧客コード（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.顧客コード（至） != null）の場合、戻り値編集用インスタンスに<BR>
     * 顧客コード（至）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.顧客コード（至）<BR>
     * <BR>
     * ８）　@口座区分条件追加<BR>
     * 　@（リクエストデータ.口座区分 != null）の場合、戻り値編集用インスタンスに<BR>
     * 口座区分を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.口座区分<BR>
     * <BR>
     * ９）　@国籍条件追加<BR>
     * 　@（リクエストデータ.外国人フラグ != null）の場合、<BR>
     * 　@　@　@戻り値編集用インスタンスに外国人フラグを追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@リクエストデータ.外国人フラグ<BR>
     * <BR>
     * １０）　@削除条件追加<BR>
     * 　@（リクエストデータ.削除フラグ != null）の場合、<BR>
     * 　@　@　@戻り値編集用インスタンスに削除フラグを追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@リクエストデータ.削除フラグ<BR>
     * <BR>
     * １１）　@印刷条件追加<BR>
     * 　@（リクエストデータ.印刷フラグ != null）の場合、<BR>
     * 　@　@　@戻り値編集用インスタンスに印刷フラグを追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@リクエストデータ.印刷フラグ<BR>
     * <BR>
     * １２）　@受領条件追加<BR>
     * 　@（リクエストデータ.受領フラグ != null）の場合、<BR>
     * 　@　@　@戻り値編集用インスタンスに受領フラグを追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@リクエストデータ.受領フラグ<BR>
     * <BR>
     * １３）　@特定口座区分条件追加<BR>
     * 　@（リクエストデータ.特定口座区分 != null）の場合、<BR>
     * 　@　@　@戻り値編集用インスタンスに特定口座区分を追加する。<BR>
     * <BR>
     * 　@リクエストデータ.特定口座区分 == "一般"の場合、<BR>
     * 　@[add()に指定する引数]<BR>
     * 　@「0：一般口座」<BR>
     * 　@リクエストデータ.特定口座区分 == "特定"の場合、<BR>
     * 　@[add()に指定する引数]<BR>
     * 　@「1：特定口座（源泉徴収なし）」<BR>
     * 　@[add()に指定する引数]<BR>
     * 　@「2：特定口座（源泉徴収あり）」<BR>
     * <BR>
     * １４）　@顧客姓（カナ）条件追加 ※指定がある場合のみ，曖昧検索 <BR>
     * 　@（リクエストデータ.顧客姓（カナ） != null）の場合、戻り値編集用インスタンスに<BR>
     * 顧客姓（カナ）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@'%' + リクエストデータ.顧客姓（カナ） + '%'<BR>
     * <BR>
     * １５）　@顧客名（カナ）条件追加 ※指定がある場合のみ，曖昧検索 <BR>
     * 　@（リクエストデータ.顧客名（カナ） != null）の場合、戻り値編集用インスタンスに<BR>
     * 顧客名（カナ）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@'%' + リクエストデータ.顧客名（カナ） + '%'<BR>
     * <BR>
     * １６）　@資料請求日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.資料請求日（自） != null）の場合、戻り値編集用<BR>
     * インスタンスに資料請求日（自）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.資料請求日（自）をYYYYMMDDに編集した文字列<BR>
     * <BR>
     * １７）　@資料請求日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.資料請求日（至） != null）の場合、戻り値編集用<BR>
     * インスタンスに資料請求日（至）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.資料請求日（至）をYYYYMMDDに編集した文字列
     * <BR>
     * １８）　@口座開設日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.口座開設日（自） != null）の場合、戻り値編集用<BR>
     * インスタンスに口座開設日（自）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.口座開設日（自）をYYYYMMDDに編集した文字列<BR>
     * <BR>
     * １９）　@口座開設日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.口座開設日（至） != null）の場合、戻り値編集用<BR>
     * インスタンスに口座開設日（至）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.口座開設日（至）をYYYYMMDDに編集した文字列<BR>
     * <BR>
     * ２０）　@内部者登録区分条件追加<BR>
     * 　@（リクエストデータ.内部者登録区分 != null）の場合、戻り値編集用インスタンスに内部者登録区分を追加する。<BR>
     * <BR>
     * 「（リクエストデータ.内部者登録区分 != null）　@且つ<BR>
     * 　@（リクエストデータ.口座区分=0：個人アカウント）の場合」<BR>
     * 　@[add()に指定する引数]<BR>
     * 　@リクエストデータ.内部者登録区分<BR>
     * <BR>
     * 「（リクエストデータ.内部者登録区分 != null）　@且つ<BR>
     * 　@（リクエストデータ.口座区分=1：法@人アカウント）の場合」<BR>
     * 　@[add()に指定する引数]<BR>
     * 　@リクエストデータ.内部者登録区分<BR>
     * <BR>
     * ２１）　@配列を返却<BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。 <BR>
     * @@param l_request - 管理者口座開設状況問合せ一覧リクエストデータオブジェクト
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@return String[]
     * @@roseuid 419C66D702F1
     */
    protected String[] createQueryContainer(WEB3AdminAccOpenStateInquiryListRequest l_request, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " createQueryContainer(WEB3AdminAccOpenStateInquiryListRequest, String) ";
        log.entering(STR_METHOD_NAME);        
        
        //１）　@戻り値生成 
        ArrayList l_queryContainerList = new ArrayList();

        //２）　@証券会社条件追加 
        l_queryContainerList.add(l_strInstitutionCode);

        //３）　@部店条件追加
        //（リクエストデータ.部店コード[] != null）の場合、
        if (l_request.branchCode != null && l_request.branchCode.length > 0)
        {
            String[] l_strBranchCodes = l_request.branchCode;
            int l_intCnt = l_strBranchCodes.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                l_queryContainerList.add(l_strBranchCodes[i]);
            }
        }

        //４）　@識別コード条件追加　@※指定がある場合のみ 
        //（リクエストデータ.識別コード != null）の場合、 
        if (l_request.requestNumber != null)
        {
            l_queryContainerList.add(l_request.requestNumber + '%');
        } 

        //５）　@顧客コード条件追加　@※指定がある場合のみ 
        //（リクエストデータ.顧客コード != null）の場合、
        if (l_request.accountCode != null)
        {
            l_queryContainerList.add(l_request.accountCode + '%');
        }  

        //６）　@顧客コード（自）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.顧客コード（自） != null）の場合 
        if (l_request.accountCodeFrom != null)
        {
            l_queryContainerList.add(l_request.accountCodeFrom);
        } 

        //７）　@顧客コード（至）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.顧客コード（至） != null）の場合
        if (l_request.accountCodeTo != null)
        {
            l_queryContainerList.add(l_request.accountCodeTo);
        }
         
        //８）　@口座区分条件追加 
        //（リクエストデータ.口座区分 != null）の場合、
        if (l_request.accountType != null)
        {
            l_queryContainerList.add(l_request.accountType);
        } 

        //国籍条件追加
        //（リクエストデータ.外国人フラグ != null）の場合、戻り値編集用インスタンスに外国人フラグを追加する。
        //　@[add()に指定する引数]
        //　@リクエストデータ.外国人フラグ
        if (l_request.foreignerFlag != null)
        {
            l_queryContainerList.add(l_request.foreignerFlag);
        }

        //削除条件追加
        //（リクエストデータ.削除フラグ != null）の場合、戻り値編集用インスタンスに削除フラグを追加する。
        //　@[add()に指定する引数]
        //　@リクエストデータ.削除フラグ
        if (l_request.deleteFlag != null)
        {
            l_queryContainerList.add(l_request.deleteFlag);
        }

        //印刷条件追加
        //（リクエストデータ.印刷フラグ != null）の場合、戻り値編集用インスタンスに印刷フラグを追加する。
        //　@[add()に指定する引数]
        //　@リクエストデータ.印刷フラグ
        if (l_request.printFlag != null)
        {
            l_queryContainerList.add(l_request.printFlag);
        }

        //受領条件追加
        //（リクエストデータ.受領フラグ != null）の場合、戻り値編集用インスタンスに受領フラグを追加する。
        //　@[add()に指定する引数]
        //　@リクエストデータ.受領フラグ
        if (l_request.receiveFlag != null)
        {
            l_queryContainerList.add(l_request.receiveFlag);
        }

        //特定口座区分条件追加
        //（リクエストデータ.特定口座区分 != null）の場合、戻り値編集用インスタンスに特定口座区分を追加する。
        if (l_request.taxTypeDiv != null)
        {
            if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxTypeDiv))
            {
                // リクエストデータ.特定口座区分 == "一般"の場合、
                // [add()に指定する引数]「0：一般口座」
                l_queryContainerList.add(WEB3SpecialAccDef.NORMAL);
            }

            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxTypeDiv))
            {
                // リクエストデータ.特定口座区分 == "特定"の場合、
                // [add()に指定する引数]「1：特定口座（源泉徴収なし）」
                l_queryContainerList.add(WEB3SpecialAccDef.SPECIAL);

                // [add()に指定する引数]「2：特定口座（源泉徴収あり）」
                l_queryContainerList.add(WEB3SpecialAccDef.SPECIAL_WITHHOLD);
            }
        }

        //９）　@顧客姓（カナ）条件追加 ※指定がある場合のみ，曖昧検索 
        //（リクエストデータ.顧客姓（カナ） != null）の場合、 
        if (l_request.accountFamilyNameKana != null)
        {
            l_queryContainerList.add('%' + l_request.accountFamilyNameKana + '%');
        } 

        //１０）　@顧客名（カナ）条件追加 ※指定がある場合のみ，曖昧検索 
        //（リクエストデータ.顧客名（カナ） != null）の場合、
        if (l_request.accountNameKana != null)
        {
            l_queryContainerList.add('%' + l_request.accountNameKana + '%');
        } 
        
        //１１）　@資料請求日（自）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.資料請求日（自） != null）の場合、
        if (l_request.infoClaimDateFrom != null)
        {
            l_queryContainerList.add(WEB3DateUtility.formatDate
                (l_request.infoClaimDateFrom, "yyyyMMdd"));
        }  

        //１２）　@資料請求日（至）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.資料請求日（至） != null）の場合、
        if (l_request.infoClaimDateTo != null)
        {
            l_queryContainerList.add(WEB3DateUtility.formatDate
                (l_request.infoClaimDateTo, "yyyyMMdd"));
        }  
        
        //１３）　@口座開設日（自）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.口座開設日（自） != null）の場合、
        if (l_request.accountOpenDateFrom != null)
        {
            l_queryContainerList.add(WEB3DateUtility.formatDate
                (l_request.accountOpenDateFrom, "yyyyMMdd"));
        }  

        //１４）　@口座開設日（至）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.口座開設日（至） != null）の場合、
        if (l_request.accountOpenDateTo != null)
        {
            l_queryContainerList.add(WEB3DateUtility.formatDate
                (l_request.accountOpenDateTo, "yyyyMMdd"));
        } 

        //２０）　@内部者登録区分条件追加 
        //（リクエストデータ.内部者登録区分 != null）の場合、戻り値編集用インスタンスに内部者登録区分を追加する。 
        //「（リクエストデータ.内部者登録区分 != null）　@且つ　@（リクエストデータ.口座区分=0：個人アカウント）の場合」
        //[add()に指定する引数] 
        //リクエストデータ.内部者登録区分 
        //
        //「（リクエストデータ.内部者登録区分 != null）　@且つ　@（リクエストデータ.口座区分=1：法@人アカウント）の場合」
        //[add()に指定する引数] 
        //リクエストデータ.内部者登録区分
        if (l_request.insiderDiv != null)
        {
            l_queryContainerList.add(l_request.insiderDiv);
        }

        //１５）　@配列を返却 
        String[] l_strQueryContainers = new String[l_queryContainerList.size()];
        l_queryContainerList.toArray(l_strQueryContainers); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
        }
    
    /**
     * (createソート条件)<BR>
     * ソート条件文字列を編集する。 <BR>
     * <BR>
     * 引数のソートキーが示す項目に該当する口座開設見込客列物理名を使用し、<BR> 
     * ソートキーの指定の通り、ソート条件文字列（order by句）を編集し返却する。 <BR>
     * <BR>
     * ※　@ソートキーに指定される項目は以下の通り。<BR>
     * 　@ 口座開設状況.部店コード （口座開設見込客.部店コード）<BR>
     * 　@ 口座開設状況.顧客コード （口座開設見込客.口座コード）<BR>
     * 　@ 口座開設状況.識別コード （口座開設見込客.識別コード）<BR>
     * 　@ 口座開設状況.資料請求日（口座開設見込客.資料請求日時）<BR>
     * 　@ 口座開設状況.口座開設日（口座開設見込客.口座登録日）<BR>
     * @@param l_sortKeys - 口座開設ソートキーの配列
     * 
     * @@return String
     * @@roseuid 419C670C014B
     */
    protected String createSortCond(WEB3AccOpenSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3AccOpenSortKey[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        String l_strSortCond = " ";
        
        int l_intSortKeyCnt = l_sortKeys.length;
        
        for (int i = 0; i < l_intSortKeyCnt; i++)
        {
            WEB3AccOpenSortKey l_sortKey = l_sortKeys[i];
            
            if (l_sortKey == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //口座開設状況.部店コード （口座開設見込客.部店コード） 
            if (WEB3AccountOpenKeyItemDef.BRANCH_CODE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.BRANCH_CODE;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            //口座開設状況.顧客コード （口座開設見込客.口座コード） 
            else if (WEB3AccountOpenKeyItemDef.ACCOUNT_CODE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            //口座開設状況.識別コード （口座開設見込客.識別コード）
            else if (WEB3AccountOpenKeyItemDef.REQUEST_NUMBER.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.ACC_OPEN_REQUEST_NUMBER;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            //口座開設状況.資料請求日（口座開設見込客.資料請求日時）
            else if (WEB3AccountOpenKeyItemDef.INFO_CLAIM_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.INFOMATION_CLAIM_DATETIME;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            } 
            //口座開設状況.口座開設日（口座開設見込客.口座登録日）
            else if (WEB3AccountOpenKeyItemDef.ACCOUNT_OPEN_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_OPEN_DATE;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
        }
        
        l_strSortCond = l_strSortCond.substring(0, l_strSortCond.length() - 2) + " ";
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strSortCond;
    }
    
    /**
     * (get専用振込先口座残数合計)<BR>
     * 専用振込先口座の残数の合計値を取得する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@　@以下の条件を指定して、【専用振込先口座条件テーブル】を検索する。<BR>
     *     該当するレコード数を取得する。 <BR>
     * <BR>
     * 　@　@------------------------------- <BR>
     * 　@　@＜検索条件＞<BR>
     * <BR>
     * 　@　@　@　@証券会社コード = 引数.証券会社コード and  <BR>
     * 　@　@　@　@銀行コード = 引数.専用振込先口座銀行コード and <BR>
     * 　@　@　@　@ステータス = "未使用レコード"  <BR>
     * <BR>
     * 　@　@------------------------------- <BR>
     * <BR>
     * ２）　@１）の結果を返却する。 
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strExclusiveAccountBankCode - (専用振込先口座銀行コード)<BR>
     * 専用振込先口座銀行コード<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 437D2EB90349
     */
    protected int getExclusiveAccountTotalNumber(
        String l_strInstitutionCode,
        String l_strExclusiveAccountBankCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExclusiveAccountTotalNumber(String, String) ";
        log.entering(STR_METHOD_NAME);

        int l_intRowCnt = 0;

        try
        {            
            String l_strWhere = 
                " institution_code = ? " + 
                " and fin_institution_code = ? " +
                " and status = ? ";

            Object[] l_obj = 
                {l_strInstitutionCode, 
                 l_strExclusiveAccountBankCode, 
                 WEB3ExclusiveUseAccountStatusDef.UNUSED_RECORD};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();  
 
            l_intRowCnt = l_queryProcessor.doGetCountQuery(
                ExclusiveUseAccountCondRow.TYPE, 
                l_strWhere, 
                l_obj);
            log.debug("専用振込先口座条件「未使用」件数： " + l_intRowCnt);

        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
            
        return l_intRowCnt;
    }
    
    /**
     * (get専用振込先口座警告区分)<BR>
     * 証券会社ごとの設定件数により、<BR>
     * 専用振込先口座の残数合計が不足していないか判定し、<BR>
     * 警告区分を返却する。<BR>
     * <BR>
     * [戻り値]<BR>
     * 0：警告なし<BR>
     * 1：注意<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件で、システムプリファ@レンステーブルからレコードを取得する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@　@名称 = 引数.証券会社コード + "_MIN_ACCOUNT"<BR>
     * <BR>
     * ２）　@警告区分判定<BR>
     * 　@２－１）１）でレコードが取得できた場合、<BR>
     * 　@　@１）で取得した値 ≧ 引数.専用振込先口座残数の場合、"注意"を返却する。<BR>
     * <BR>
     * 　@２－２）１）でレコードが取得できなかった場合、<BR>
     * 　@　@以下の条件で、【システムプリファ@レンステーブル】からレコードを取得する。<BR>
     * [条件] <BR>  
     * 　@名称 = 引数.証券会社コード + "_MIN_ACCOUNT" + "_" + 引数.専用振込先口座銀行コード <BR>
     * <BR>
     * ２－３）　@２－２）でレコードが取得できた場合、<BR>  
     * 　@２－２）で取得した値 ≧ 引数.専用振込先口座残数の場合、"注意"を返却する。
     * <BR> 
     * ２－５）　@以外、"警告なし"を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strExclusiveAccountBankCode - (専用振込先口座銀行コード)<BR>
     * 専用振込先口座銀行コード<BR>
     * @@param l_intExclusiveAccountNumber - (専用振込先口座残数)<BR>
     * 専用振込先口座残数<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 437D64E001A9
     */
    protected String getExclusiveAccountWarningDiv(
        String l_strInstitutionCode, 
        String l_strExclusiveAccountBankCode,
        int l_intExclusiveAccountNumber) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExclusiveAccountWarningDiv(String, String, int) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            double l_dblValue = 0;

            //１）　@DB検索  
            //名称 = 引数.証券会社コード + "_MIN_ACCOUNT"
            String l_strName = l_strInstitutionCode + WEB3SystemPreferencesNameDef.MIN_ACCOUNT;
            
            SystemPreferencesRow l_sysPreRow = SystemPreferencesDao.findRowByName(l_strName);
            //でレコードが取得できた場合、、
            //１）で取得した値 ≧ 引数.専用振込先口座残数の場合、"注意"を返却する。
            if (l_sysPreRow != null)
            {
                String l_strValue = l_sysPreRow.getValue();
                if (l_strValue != null && WEB3StringTypeUtility.isNumber(l_strValue))
                {
                    l_dblValue = Double.parseDouble(l_strValue);
                    
                    if(l_dblValue >= l_intExclusiveAccountNumber)
                    {
                        log.exiting(STR_METHOD_NAME);
                        return WEB3AccOpenExclusiveAccountWarningDivDef.NOTICE;
                    }
                    else
                    {
                        log.exiting(STR_METHOD_NAME);
                        return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                    }
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                }
            }
            //でレコードが取得できなかった場合、
            //で取得した値 ≧ 引数.専用振込先口座残数の場合、"注意"を返却する。
            else
            {
                l_strName = 
                    l_strInstitutionCode + WEB3SystemPreferencesNameDef.MIN_ACCOUNT + "_" + l_strExclusiveAccountBankCode;
                l_sysPreRow = SystemPreferencesDao.findRowByName(l_strName);
                if(l_sysPreRow != null)
                {
                    String l_strValue = l_sysPreRow.getValue();
                    if (l_strValue != null && WEB3StringTypeUtility.isNumber(l_strValue))
                    {
                        l_dblValue = Double.parseDouble(l_strValue);
                        
                        if(l_dblValue >= l_intExclusiveAccountNumber)
                        {
                            log.exiting(STR_METHOD_NAME);
                            return WEB3AccOpenExclusiveAccountWarningDivDef.NOTICE;
                        }
                        else
                        {
                            log.exiting(STR_METHOD_NAME);
                            return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                        }
                    }
                    else
                    {
                        log.exiting(STR_METHOD_NAME);
                        return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                    }
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AccOpenExclusiveAccountWarningDivDef.NOT_WARNING;
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

    }
    
    /**
     * (set部店プリファ@レンス)<BR>
     * 部店プリファ@レンスの取得を行う。 <BR>
     * <BR>
     *１） 部店用プリファ@レンステーブルから値を取得する。 <BR>
     * <BR>
     *　@[条件] <BR>
     *　@　@部店ID⇒（引数）部店ID <BR>
     *　@　@プリファ@レンス名⇒（引数）部店プリファ@レンス名 <BR>
     *　@　@プリファ@レンス名の連番⇒1 <BR>
     *  <BR>
     *  <BR>
     *　@[設定値] <BR>
     *　@　@"0"（チェック不要-アラート表示無-審査待ちUPDATE無） OR "" ⇒ 0 をthis.部店プリファ@レンスに設定 <BR>
     *　@　@"1"（チェック実行-アラート表示有-審査待ちUPDATE無） ⇒ 1 をthis.部店プリファ@レンスに設定 <BR>
     *　@　@"2"（チェック実行-アラート表示無-審査待ちUPDATE有） ⇒ 2 をthis.部店プリファ@レンスに設定 <BR>
     *　@　@"3"（チェック実行-アラート表示有-審査待ちUPDATE有） ⇒ 3 をthis.部店プリファ@レンスに設定 <BR>
     *　@　@（レコードが選択されない ⇒ 0 をthis.部店プリファ@レンスに設定）<BR> 
     *@@param l_strBranchPreferenceName - (部店プリファ@レンス名)<BR>
     *部店プリファ@レンス名。<BR>
     *@@param l_lngBranchId - (部店ID)<BR>
     *部店ID。<BR>
     * @@throws WEB3BaseException 
     */
    private void setBranchPreferences(
        String l_strBranchPreferenceName, long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setBranchPreference(String, long) ";
        log.entering(STR_METHOD_NAME);
        //１） 部店用プリファ@レンステーブルから値を取得する。
        //　@[条件]
        //　@　@部店ID⇒（引数）部店ID
        //　@　@プリファ@レンス名⇒（引数）部店プリファ@レンス名
        //　@　@プリファ@レンス名の連番⇒1
        BranchPreferencesParams l_branchPreferencesParams = null;
        BranchPreferencesRow l_branchPreferencesRow = null;
        
        try 
        {
            l_branchPreferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId, 
                    l_strBranchPreferenceName, 
                    1);
        } 
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //[設定値] 
        // "0"（チェック不要-アラート表示無-審査待ちUPDATE無） OR "" ⇒ 0 をthis.部店プリファ@レンスに設定 
        // "1"（チェック実行-アラート表示有-審査待ちUPDATE無） ⇒ 1 をthis.部店プリファ@レンスに設定 
        // "2"（チェック実行-アラート表示無-審査待ちUPDATE有） ⇒ 2 をthis.部店プリファ@レンスに設定 
        // "3"（チェック実行-アラート表示有-審査待ちUPDATE有） ⇒ 3 をthis.部店プリファ@レンスに設定 
        // （レコードが選択されない ⇒ 0 をthis.部店プリファ@レンスに設定）
        if (l_branchPreferencesRow == null)
        {
            this.branchPreferences = 0;
        }
        else
        {      
            l_branchPreferencesParams = new BranchPreferencesParams(l_branchPreferencesRow);
            
            if (WEB3CheckAlartUpdateDef.DEFAULT.equals(l_branchPreferencesParams.getValue()) 
                || "".equals(l_branchPreferencesParams.getValue()))
            {
                this.branchPreferences = 0;
            }
            else if (WEB3CheckAlartUpdateDef.CHECK_ALART_UPD_1.equals(l_branchPreferencesParams.getValue()))
            {
                this.branchPreferences = 1;
            }
            else if (WEB3CheckAlartUpdateDef.CHECK_ALART_UPD_2.equals(l_branchPreferencesParams.getValue()))
            {
                this.branchPreferences = 2;
            }
            else if (WEB3CheckAlartUpdateDef.CHECK_ALART_UPD_3.equals(l_branchPreferencesParams.getValue()))
            {
                this.branchPreferences = 3;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate部店設定別チェック)<BR>
     * 部店設定別チェックを行う。<BR> 
     * <BR>
     * １）警告メッセージリスト（ArrayList）を生成 <BR>
     * <BR>
     * <BR>
     * ２）部店ID及び口座区分を取得する。<BR> 
     *　@２－１）部店IDを取得する。 <BR>
     *　@　@部店オブジェクト(管理者.get証券会社(),口座開設見込客.get部店コード()).getBranchId()の戻り値 <BR>
     * <BR>
     *　@２－２）口座区分を取得する。<BR> 
     *　@　@口座開設見込客.get口座区分()の戻り値 <BR>
     * <BR>
     * <BR>
     * ３） 同一顧客チェックを行う。（口座開設見込客.validate重複顧客()）<BR> 
     *　@３－１） 部店プリファ@レンスを取得する。 <BR>
     *　@　@[set部店プリファ@レンス()に設定する引数] <BR>
     *　@　@　@部店プリファ@レンス名：２－２）で取得した口座区分=="0"の場合"accopen.examination.duplicateaccount.indiv" <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@２－２）で取得した口座区分=="1"の場合"accopen.examination.duplicateaccount.corp" <BR>
     *　@　@　@部店ID： ２－１）で取得した部店ID <BR>
     * <BR>
     *　@３－２）  this.部店プリファ@レンス値 !=0（チェック不要） の場合、以下処理を行う。<BR> 
     *　@　@３－２－1） 口座開設見込客.validate重複顧客()を実行する。 <BR>
     *　@　@　@[口座開設見込客.validate重複顧客()に指定する引数] <BR>
     *　@　@　@部店プリファ@レンス：this.部店プリファ@レンス <BR>
     * <BR>
     *　@　@　@[戻り値] <BR>
     *　@　@　@　@同一顧客警告メッセージコード（String） <BR>
     * <BR>
     *　@　@３－２－２） this.部店プリファ@レンス値 !=2（アラート表示無） の場合、以下処理を行う。<BR> 
     *　@　@　@同一顧客警告メッセージコードが返却された場合（口座開設見込客.validate重複顧客() != null）、<BR> 
     *　@　@　@同一顧客警告メッセージコードを警告メッセージリスト（ArrayList）に追加する。 <BR>
     *　@　@　@[add()に指定する引数]<BR> 
     *　@　@　@　@arg0（同一顧客警告メッセージコード）：　@口座開設見込客.validate重複顧客()の戻り値 <BR>
     * <BR>
     * <BR>
     * ４） Y客チェックを行う。（口座開設見込客.validateＹ客()） <BR>
     *　@４－１） 部店プリファ@レンスを取得する。 <BR>
     *　@　@[set部店プリファ@レンス()に設定する引数] <BR>
     *　@　@　@部店プリファ@レンス名：２－２）で取得した口座区分=="0"の場合"accopen.examination.yellowaccount.indiv" <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@２－２）で取得した口座区分=="1"の場合"accopen.examination.yellowaccount.corp" <BR>
     *　@　@　@部店ID： ２－１）で取得した部店ID <BR>
     * <BR>
     *　@４－２）  this.部店プリファ@レンス値 !=0（チェック不要） の場合、以下処理を行う。 <BR>
     *　@　@４－２－1） 口座開設見込客.validateＹ客()を実行する。 <BR>
     *　@　@　@[口座開設見込客.validateＹ客()に指定する引数] <BR>
     *　@　@　@部店プリファ@レンス：this.部店プリファ@レンス <BR>
     * <BR>
     *　@　@　@[戻り値] <BR>
     *　@　@　@　@Y客警告メッセージコード（String） <BR>
     * <BR>
     *　@　@４－２－２） this.部店プリファ@レンス値 !=2（アラート表示無） の場合、以下処理を行う。<BR> 
     *　@　@　@Y客警告メッセージコードが返却された場合（口座開設見込客.validateＹ客() != null）、<BR> 
     *　@　@　@Y客警告メッセージコードを警告メッセージリスト（ArrayList）に追加する。 <BR>
     *　@　@　@[add()に指定する引数] <BR>
     *　@　@　@　@arg0（Y客警告メッセージコード）：　@口座開設見込客.validateＹ客()の戻り値 <BR>
     * <BR>
     * <BR>
     * ５） メールアドレスチェックを行う。（口座開設見込客.validateメールアドレス()） <BR>
     *　@５－１） 部店プリファ@レンスを取得する。 <BR>
     *　@　@[set部店プリファ@レンス()に設定する引数] <BR>
     *　@　@　@部店プリファ@レンス名：２－２）で取得した口座区分=="0"の場合"accopen.examination.mailaddress.indiv" <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@２－２）で取得した口座区分=="1"の場合"accopen.examination.mailaddress.corp" <BR>
     *　@　@　@部店ID： ２－１）で取得した部店ID <BR>
     * <BR>
     *　@５－２）  this.部店プリファ@レンス値 !=0（チェック不要） の場合、以下処理を行う。 <BR>
     *　@　@５－２－1） 口座開設見込客.validateメールアドレス()を実行する。 <BR>
     *　@　@　@[口座開設見込客.validateメールアドレス()に指定する引数] <BR>
     *　@　@　@部店プリファ@レンス：this.部店プリファ@レンス <BR>
     * <BR>
     *　@　@　@[戻り値] <BR>
     *　@　@　@　@メールアドレスチェック警告メッセージコード（String） <BR>
     * <BR>
     *　@　@５－２－２） this.部店プリファ@レンス値 !=2（アラート表示無） の場合、以下処理を行う。<BR> 
     *　@　@　@メールアドレスチェック警告メッセージコードが返却された場合（口座開設見込客.validateメールアドレス() != null）、<BR> 
     *　@　@　@メールアドレスチェック警告メッセージコードを警告メッセージリスト（ArrayList）に追加する。 <BR>
     *　@　@　@[add()に指定する引数] <BR>
     *　@　@　@　@arg0（メールアドレスチェック警告メッセージコード）：　@口座開設見込客.validateメールアドレス()の戻り値 <BR>
     * <BR>
     * <BR>
     * ６） 電話番号チェックを行う。（口座開設見込客.validate電話番号()）<BR> 
     *　@６－１） 部店プリファ@レンスを取得する。 <BR>
     *　@　@[set部店プリファ@レンス()に設定する引数] <BR>
     *　@　@　@部店プリファ@レンス名：２－２）で取得した口座区分=="0"の場合"accopen.examination.telno.indiv" <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@２－２）で取得した口座区分=="1"の場合"accopen.examination.telno.corp" <BR>
     *　@　@　@部店ID： ２－１）で取得した部店ID <BR>
     * <BR>
     *　@６－２）  this.部店プリファ@レンス値 !=0（チェック不要） の場合、以下処理を行う。 <BR>
     *　@　@６－２－1） 口座開設見込客.validate電話番号()を実行する。 <BR>
     *　@　@　@[口座開設見込客.validate電話番号()に指定する引数] <BR>
     *　@　@　@部店プリファ@レンス：this.部店プリファ@レンス <BR>
     * <BR>
     *　@　@　@[戻り値] <BR>
     *　@　@　@　@電話番号チェック警告メッセージコード（ArrayList） <BR>
     * <BR>
     *　@　@６－２－２） this.部店プリファ@レンス値 !=2（アラート表示無） の場合、以下処理を行う。 <BR>
     *　@　@　@電話番号チェック警告メッセージコードが返却された場合（口座開設見込客.validate電話番号()の長さ >0）、 <BR>
     *　@　@　@電話番号チェック警告メッセージコードを警告メッセージリスト（ArrayList）に追加する。 <BR>
     *　@　@　@[addAll()に指定する引数] <BR>
     *　@　@　@　@arg0（電話番号チェック警告メッセージコード）：　@口座開設見込客.validate電話番号()の戻り値 <BR>
     * <BR>
     * ７） 警告メッセージリストを返却。<BR> 
     * @@param l_expAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客オブジェクト。<BR>
     * @@throws WEB3BaseException 
     * @@return Collection
     * @@throws  
     */
    protected Collection validateBranchSetCheck(
        WEB3AccOpenExpAccountOpen l_expAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBranchSetCheck(WEB3AccOpenExpAccountOpen) ";
        log.entering(STR_METHOD_NAME);
        
        //１）警告メッセージリスト（ArrayList）を生成
        Collection l_collection = new ArrayList();
        
        //２）部店ID及び口座区分を取得する。 
        //２－１）部店IDを取得する。 
        //  部店オブジェクト(管理者.get証券会社(),口座開設見込客.get部店コード()).getBranchId()の戻り値 
        //２－２）口座区分を取得する。 
        //  口座開設見込客.get口座区分()の戻り値        
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        Branch l_branch = null;

        try 
        {
            l_branch = l_accountManager.getBranch(
                l_admin.getInstitution(), l_expAccountOpen.getBranchCode());
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("部店インスタンスを生成する:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  

        long l_lngBranchId = l_branch.getBranchId();
        String l_strAccountDiv = l_expAccountOpen.getAccountDiv();
        
        //３） 同一顧客チェックを行う。（口座開設見込客.validate重複顧客()） 
        //３－１） 部店プリファ@レンスを取得する。
        String l_branchPreferencesName = null;
        if (WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_DUPLO_INDV;
        }
        else if (WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_DUPLO_COP;
        }
        setBranchPreferences(l_branchPreferencesName, l_lngBranchId);
        
        //３－２） this.部店プリファ@レンス値 !=0（チェック不要） の場合、以下処理を行う。 
        if (branchPreferences != 0)
        {
            //３－２－1） 口座開設見込客.validate重複顧客()を実行する。
            String l_strDuplicateAccountMessageCode = 
                l_expAccountOpen.validateDuplicateAccount(branchPreferences);
            
            //３－２－２） this.部店プリファ@レンス値 !=2（アラート表示無） の場合、以下処理を行う。
            if (branchPreferences != 2 && l_strDuplicateAccountMessageCode != null)
            {
                l_collection.add(l_strDuplicateAccountMessageCode);
            }
        }
        
        //４） Y客チェックを行う。（口座開設見込客.validateＹ客()） 
        //４－１） 部店プリファ@レンスを取得する。 
        if (WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_YACC_INDV;
        }
        else if (WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_YACC_COP;
        }
        setBranchPreferences(l_branchPreferencesName, l_lngBranchId);
        
        //４－２） this.部店プリファ@レンス値 !=0（チェック不要） の場合、以下処理を行う。 

        if (branchPreferences != 0)
        {
            //４－２－1） 口座開設見込客.validateＹ客()を実行する。
            String l_strYellowAccountMessageCode =
                l_expAccountOpen.validateYellowAccount(branchPreferences);
            
            //４－２－２） this.部店プリファ@レンス値 !=2（アラート表示無） の場合、以下処理を行う。 
            if (branchPreferences != 2 && l_strYellowAccountMessageCode != null)
            {
                l_collection.add(l_strYellowAccountMessageCode);
            }
        }
        
        //５） メールアドレスチェックを行う。（口座開設見込客.validateメールアドレス()） 
        //５－１） 部店プリファ@レンスを取得する。 
        if (WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_MAIL_INDV;
        }
        else if (WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_MAIL_COP;
        }
        setBranchPreferences(l_branchPreferencesName, l_lngBranchId);
        
        //５－２） this.部店プリファ@レンス値 !=0（チェック不要） の場合、以下処理を行う。 
 
        if (branchPreferences != 0)
        {
            //５－２－1） 口座開設見込客.validateメールアドレス()を実行する。
            String l_strMailAddressMessageCode =
                l_expAccountOpen.validateMailAddress(branchPreferences);
            if (branchPreferences != 2 && l_strMailAddressMessageCode != null)
            {
                //５－２－２） this.部店プリファ@レンス値 !=2（アラート表示無） の場合、以下処理を行う。
                l_collection.add(l_strMailAddressMessageCode);
            }
        }
        
        //６） 電話番号チェックを行う。（口座開設見込客.validate電話番号()） 
        //６－１） 部店プリファ@レンスを取得する。 
        if (WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_TEL_INDV;
        }
        else if (WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_strAccountDiv))
        {
            l_branchPreferencesName = WEB3BranchPreferencesNameDef.ACC_EX_TEL_COP;
        }
        setBranchPreferences(l_branchPreferencesName, l_lngBranchId);
        
        //６－２） this.部店プリファ@レンス値 !=0（チェック不要） の場合、以下処理を行う。 
        if (branchPreferences != 0)
        {
            //６－２－1） 口座開設見込客.validate電話番号()を実行する。 
            ArrayList l_arrayListTelephoneMessageCode =
                l_expAccountOpen.validateTelephone(branchPreferences, l_strAccountDiv);
            
            //６－２－２） this.部店プリファ@レンス値 !=2（アラート表示無） の場合、以下処理を行う。 
            if (branchPreferences != 2 
                && l_arrayListTelephoneMessageCode != null 
                && l_arrayListTelephoneMessageCode.size() > 0)
            {
                l_collection.addAll(l_arrayListTelephoneMessageCode);
            }
        }
        
        //７） 警告メッセージリストを返却。 
        log.exiting(STR_METHOD_NAME);
        return l_collection;
    }
    
    /**
     * (validate自動採番)<BR>
     *自動採番選択時に、顧客が個人で国内居住者かチェックする <BR>
     *<BR>
     *１）引数.口座開設申込情報.顧客コード自動採番フラグ != 1(自動採番を行う)の場合<BR>
     *<BR>
     *　@メソッドを正常終了する <BR>
     *<BR>
     * ２）証券会社IDを取得する<BR>
     * <BR>
     * ３）以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する<BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社ID = ２）で取得した証券会社ID<BR>
     * 　@　@プリファ@レンス名 = "accountopen.corporate.auto.div"<BR>
     * 　@　@プリファ@レンスの値 = "1"<BR>
     * <BR>
     * ４）レコードが取得できない場合、以下のチェックを行う<BR>
     * 　@　@引数.口座開設申込情報.口座区分 != 0:個人アカウント の場合<BR>
     * 　@　@「自動採番できません。」の例外をスローする<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_02610<BR>
     * <BR>
     * ５）引数.口座開設申込情報.居住／非居住区分 != 0:住居者　@の場合<BR>
     * 　@　@「自動採番できません。」の例外をスローする<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@: BUSINESS_ERROR_02610<BR>
     * <BR>
     * @@param l_accOpenApplyInfo - (口座開設申込情報)<BR>
     * 口座開設申込情報オブジェクト<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@throws WEB3BaseException 
     */
    private void validateAuto(WEB3AccOpenApplyInfo l_accOpenApplyInfo, String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAuto(WEB3AccOpenApplyInfo, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）引数.口座開設申込情報.顧客コード自動採番フラグ != 1(自動採番を行う)の場合メソッドを正常終了する
        if (WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_accOpenApplyInfo.accountCodeAutoFlag))
        {
            try
            {
                InstitutionRow l_institutionRow =
                    InstitutionDao.findRowByInstitutionCode(l_strInstitutionCode);
                if (l_institutionRow == null)
                {
                    log.debug("DBへのアクセスに失敗しました。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "DBへのアクセスに失敗しました。");
                }
                long l_lngInstitutionId = l_institutionRow.getInstitutionId();

                //以下の条件で、証券会社プリファ@レンステーブルからレコードを取得する
                //　@[条件]
                //　@証券会社ID = ２）で取得した証券会社ID
                //　@プリファ@レンス名 = "accountopen.corporate.auto.div"
                //　@プリファ@レンスの値 = "1"
                String l_strWhere = " institution_id = ? and name = ? and value = ? ";
                Object[] l_bindVars =
                    {
                        new Long(l_lngInstitutionId),
                        WEB3InstitutionPreferencesNameDef.ACCOUNTOPEN_CORPORATE_AUTO_DIV,
                        new Integer(1)
                    };

                List l_lisInstitutionPreferences =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        InstitutionPreferencesRow.TYPE,
                        l_strWhere,
                        l_bindVars);

                if (l_lisInstitutionPreferences == null
                    || l_lisInstitutionPreferences.size() == 0)
                {
                    //レコードが取得できない場合、以下のチェックを行う
                    //  引数.口座開設申込情報.口座区分 != 0:個人アカウント の場合
                    // 「自動採番できません。」の例外をスローする
                    if (!WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_accOpenApplyInfo.accountType))
                    {
                        log.debug("自動採番できません。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                             WEB3ErrorCatalog.BUSINESS_ERROR_02610,
                             this.getClass().getName() + "." + STR_METHOD_NAME,
                             "自動採番できません。");
                    }
                }
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

            //引数.口座開設申込情報.居住／非居住区分 != 0:住居者の場合
            //「自動採番できません。」の例外をスローする
            if (!WEB3ResidentDef.RESIDENT.equals(l_accOpenApplyInfo.residentDiv))
            {
                log.debug("自動採番できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_02610,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "自動採番できません。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
        /**
     * (get専用振込先口座銀行コード )<BR> 
     * 専用振込先口座条件テーブルより専用振込先口座銀行コードを取得し、<BR>
     * 重複しない専用振込先口座銀行コードの配列を返却する。<BR>
     * <BR>
     * １）　@DB検索 <BR>
     * 　@以下の条件を指定して、【専用振込先口座条件テーブル】を検索し、<BR>
     * 　@該当するレコード数を取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     *　@ 証券会社コード = 引数.証券会社コード<BR>
     * <BR>
     * ２）専用振込先口座銀行コードを格納するArrayListオブジェクトを生成<BR>
     * <BR>
     * ３）１）の戻り値の要素分、Loop処理を行う<BR>
     * <BR>
     * ３－１）２）のリストオブジェクトに以下を追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@　@専用振込先口座条件Row.get専用振込先口座銀行コード<BR>
     * <BR>
     * ４）WEB3Toolkit.toUnique()にて重複しない専用振込先口座銀行コードの一覧を作成する<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@２）のリストオブジェクトをtoArray()した値<BR>
     * <BR>
     * ５）４）の戻り値を返却する<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    protected String[] getExclusiveAccountFinancialInstitutionCode(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExclusiveAccountFinancialInstitutionCode(String) ";
        log.entering(STR_METHOD_NAME);
        try
        {
            //DB検索  証券会社コード = 引数.証券会社コード
            String l_strWhere = " institution_code = ? ";
            Object[] l_obj = {l_strInstitutionCode};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); 
            List l_listRecords = l_queryProcessor.doFindAllQuery(
                    ExclusiveUseAccountCondRow.TYPE,
                    l_strWhere,
                    l_obj);
            //２）専用振込先口座銀行コードを格納するArrayListオブジェクトを生成
            ArrayList l_arrayList = new ArrayList();
            String[] l_strLists = null;
            if(l_listRecords != null && l_listRecords.size() > 0)
            {
                for(int i = 0; i < l_listRecords.size(); i++)
                {
                    ExclusiveUseAccountCondRow l_condRow = 
                        (ExclusiveUseAccountCondRow)l_listRecords.get(i);
                    l_arrayList.add(l_condRow.getFinInstitutionCode());
                }
                
                Object[] l_objs = WEB3Toolkit.toUnique(l_arrayList.toArray());
                l_strLists = new String[l_objs.length];
                for(int i = 0; i < l_objs.length; i++)
                {
                    l_strLists[i] = new String();
                    l_strLists[i] = (String)l_objs[i];
                }                
            }
            log.exiting(STR_METHOD_NAME);
            return l_strLists;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (create専用振込先口座情報)<BR>
     * 専用振込先口座情報オブジェクトを返却する。<BR>
     * <BR>
     * １）専用振込先口座情報クラスのオブジェクト生成 <BR>
     * <BR>
     * ２）専用振込先口座情報オブジェクト.専用振込先口座銀行コード = 引数.専用振込先口座銀行コード<BR> 
     * <BR>
     * ３）専用振込先口座情報オブジェクト.専用振込先口座残数 = 引数.専用振込先口座残数　@(※Stringに変換する) <BR>
     * <BR>
     * ４）専用振込先口座情報オブジェクト.専用振込先口座警告区分 = 引数.専用振込先口座警告区分 <BR>
     * <BR>
     * ５）専用振込先口座情報オブジェクトを返却する<BR>
     * @@param l_strExclusiveAccountBankCode - (専用振込先口座銀行コード)<BR>
     * 専用振込先口座銀行コード<BR>
     * @@param l_intExclusiveAccountNumber - (専用振込先口座残数)<BR>
     * 専用振込先口座残数<BR>
     * @@param l_strExclusiveAccountWarningDiv - (専用振込先口座警告区分)<BR>
     * 専用振込先口座警告区分<BR>
     * @@return WEB3AccInfoAccountInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenExclusiveAccountInfo createExclusiveAccountInfo(
        String l_strExclusiveAccountBankCode,
        int l_intExclusiveAccountNumber,
        String l_strExclusiveAccountWarningDiv) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createExclusiveAccountInfo(String, int, String) ";
        log.entering(STR_METHOD_NAME);
        
        //１）専用振込先口座情報クラスのオブジェクト生成
        WEB3AccOpenExclusiveAccountInfo l_accountInfo = new WEB3AccOpenExclusiveAccountInfo();
        
        //２）専用振込先口座情報オブジェクト.専用振込先口座銀行コード = 引数.専用振込先口座銀行コード
        l_accountInfo.exclusiveAccountFinancialInstitutionCode = l_strExclusiveAccountBankCode;
        
        //３）専用振込先口座情報オブジェクト.専用振込先口座残数 = 引数.専用振込先口座残数
        l_accountInfo.exclusiveAccountNumber = l_intExclusiveAccountNumber + "";
        
        //４）専用振込先口座情報オブジェクト.専用振込先口座警告区分 = 引数.専用振込先口座警告区分
        l_accountInfo.exclusiveAccountWarningDiv = l_strExclusiveAccountWarningDiv;
        
        log.exiting(STR_METHOD_NAME);
        return l_accountInfo;
    }

    /**
     * (validate部店コード)<BR>
     * 指定された証券会社コード、部店コードが部店テーブルに存在するかをチェックする。<BR>
     * <BR>
     * １）　@部店ＤＡＯを利用して部店テーブルを検索する。<BR>
     * 　@－該当データがない場合は、以下の例外をスローする。<BR>
     * 　@　@「該当部店データなし」<BR>
     * 
     * @@param l_institutionCode 証券会社コード
     * @@param l_branchCode 部店コード
     * @@throws WEB3BaseException
     */
    private void validateBranchCode(String l_institutionCode, String l_branchCode) throws WEB3BaseException {
    	
        final String STR_METHOD_NAME = " validateBranchCode(String) ";
        log.entering(STR_METHOD_NAME);

        BranchRow l_branchRow = null;
        boolean l_existBranchCode = false;
		try
        {
			l_branchRow = BranchDao.findRowByInstitutionCodeBranchCode(
                    l_institutionCode,
                    l_branchCode);

            l_existBranchCode = (l_branchRow != null);
        }
		catch (DataFindException l_ex)
        {
            l_existBranchCode = false;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (!l_existBranchCode)
        {
            log.error("指定された部店コードは存在しません。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386, 
                this.getClass().getName() + STR_METHOD_NAME,
                "指定された部店コードは存在しません。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit切替)<BR>
     * 口座開設切替処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（状況問合せ）submit切替」参照。<BR>
     * ====================================================<BR>
     * 　@シーケンス図　@: 口座開設（状況問合せ）submit切替<BR>
     * 　@具体位置　@　@　@: 口座開設見込客既存データが存在しない場合（nullが返却された場合）、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_01318<BR>
     * ====================================================<BR>
     * ====================================================<BR>
     * 　@シーケンス図　@: 口座開設（状況問合せ）submit切替<BR>
     * 　@具体位置　@　@　@: リクエスト.更新後状態と<BR>
     * 　@　@　@　@　@　@　@　@　@　@口座開設見込客.印刷フラグが一致する場合（equals() == true）、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_03177<BR>
     * ====================================================<BR>
     * ====================================================<BR>
     * 　@シーケンス図　@: 口座開設（状況問合せ）submit切替<BR>
     * 　@具体位置　@　@　@: リクエスト.更新後状態と<BR>
     * 　@　@　@　@　@　@　@　@　@　@口座開設見込客.受領フラグが一致する場合（equals() == true）、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_03177<BR>
     * ====================================================<BR>
     * ====================================================<BR>
     * 　@シーケンス図　@: 口座開設（状況問合せ）submit切替<BR>
     * 　@具体位置　@　@　@: リクエスト.更新後状態と<BR>
     * 　@　@　@　@　@　@　@　@　@　@口座開設見込客.削除フラグが一致する場合（equals() == true）、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_03177<BR>
     * ====================================================<BR>
     * ====================================================<BR>
     * 　@シーケンス図　@: 口座開設（状況問合せ）submit切替<BR>
     * 　@具体位置　@　@　@: 口座開設状況区分が「0：　@DEFAULT（未開設）」以外の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_03181<BR>
     * ====================================================<BR>
     * <BR>
     * @@param l_request - (管理者口座開設切替リクエスト)<BR>
     * 管理者口座開設切替リクエスト<BR>
     * @@return WEB3AccOpenChangeResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenChangeResponse submitChange(WEB3AccOpenChangeRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AccOpenChangeRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.口座開設
        //is更新：　@true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACC_OPEN, true);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //口座開設見込客(証券会社コード : String, 識別コード : String)
        //[口座開設見込客()に指定する引数]
        // 証券会社コード：　@get証券会社コード()の戻り値
        // 識別コード：　@リクエスト.識別コード
        WEB3AccOpenExpAccountOpen l_expAccountOpen = null;
        try
        {
            //口座開設見込客既存データが存在しない場合（nullが返却された場合）、例外をスローする。
            l_expAccountOpen =
                new WEB3AccOpenExpAccountOpen(
                    l_strInstitutionCode,
                    l_request.requestNumber);
        }
        catch (NotFoundException l_ex)
        {
            log.error("口座開設見込客既存データが存在しない。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01318,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get部店コード( )
        String l_strBranchCode = l_expAccountOpen.getBranchCode();

        //validate部店権限(部店コード : String[])
        //[validate部店権限()に指定する引数]
        // 部店コード：　@get部店コード()
        l_administrator.validateBranchPermission(l_strBranchCode);

        //get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        ExpAccountOpenParams l_expAccountOpenParams =
            (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();

        //リクエスト更新項目が「1：印刷切替」の場合は処理を行う
        if (WEB3AccOpenUpdateItemDef.PRINT_CHANGE.equals(l_request.updateItem))
        {
            //リクエスト.更新後状態と口座開設見込客.印刷フラグが一致する場合、
            // 例外をスローする。
            if (WEB3Toolkit.isEquals(l_request.updateStatus, l_expAccountOpenParams.getPrintFlag()))
            {
                log.debug("更新後状態の入力値が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03177,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "更新後状態の入力値が不正です。");
            }

            //save口座開設見込客(更新項目 : String, 更新値 : String, 更新者コード : String)
            //[save口座開設見込客()に指定する引数]
            //更新項目：　@「1：印刷フラグ」
            //更新値：　@リクエスト.更新後状態
            //更新者コード：　@get管理者コード( )
            l_expAccountOpen.saveExpAccountOpen(
                WEB3AccOpenUpdateItemDef.PRINT_CHANGE,
                l_request.updateStatus,
                l_strAdministratorCode);
        }

        //リクエスト更新項目が「2：受領切替」の場合は処理を行う
        if (WEB3AccOpenUpdateItemDef.RECEIVE_CHANGE.equals(l_request.updateItem))
        {
            //リクエスト.更新後状態と
            //口座開設見込客.受領フラグが一致する場合（equals() == true）、例外をスローする。
            String l_strReceiptFlag = null;
            if (l_expAccountOpenParams.getReceiptFlag() != null)
            {
                l_strReceiptFlag = l_expAccountOpenParams.getReceiptFlag().intValue() + "";
            }

            if (WEB3Toolkit.isEquals(l_request.updateStatus, l_strReceiptFlag))
            {
                log.debug("更新後状態の入力値が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03177,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "更新後状態の入力値が不正です。");
            }

            //save口座開設見込客(更新項目 : String, 更新値 : String, 更新者コード : String)
            //[save口座開設見込客()に指定する引数]
            //更新項目：　@「2：受領フラグ」
            //更新値：　@リクエスト.更新後状態
            //更新者コード：　@get管理者コード( )
            l_expAccountOpen.saveExpAccountOpen(
                WEB3AccOpenUpdateItemDef.RECEIVE_CHANGE,
                l_request.updateStatus,
                l_strAdministratorCode);
        }

        //リクエスト更新項目が「3：削除切替」の場合は処理を行う
        if (WEB3AccOpenUpdateItemDef.DELETE_CHANGE.equals(l_request.updateItem))
        {
            //リクエスト.更新後状態と
            //口座開設見込客.削除フラグが一致する場合（equals() == true）、例外をスローする。
            String l_strDeleteFlag = null;
            if (l_expAccountOpenParams.getDeleteFlag() != null)
            {
                l_strDeleteFlag = l_expAccountOpenParams.getDeleteFlag().intValue() + "";
            }

            if (WEB3Toolkit.isEquals(l_request.updateStatus, l_strDeleteFlag))
            {
                log.debug("更新後状態の入力値が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03177,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "更新後状態の入力値が不正です。");
            }

            //get口座開設状況区分( )
            String l_strAccountOpenStatusDiv = l_expAccountOpen.getAccountOpenStatusDiv();

            //口座開設状況区分が「0：　@DEFAULT（未開設）」以外の場合、例外をスローする
            if (!WEB3AccountOpenStatusDivDef.DEFAULT.equals(l_strAccountOpenStatusDiv))
            {
                log.debug("口座開設状況が未開設以外の場合、削除フラグ切替不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03181,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "口座開設状況が未開設以外の場合、削除フラグ切替不可。");
            }

            //save口座開設見込客(更新項目 : String, 更新値 : String, 更新者コード : String)
            //[save口座開設見込客()に指定する引数]
            //更新項目：　@「3：削除フラグ」
            //更新値：　@リクエスト.更新後状態
            //更新者コード：　@get管理者コード( )
            l_expAccountOpen.saveExpAccountOpen(
                WEB3AccOpenUpdateItemDef.DELETE_CHANGE,
                l_request.updateStatus,
                l_strAdministratorCode);
        }

        //createResponse( )
        WEB3AccOpenChangeResponse l_response =
            (WEB3AccOpenChangeResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
