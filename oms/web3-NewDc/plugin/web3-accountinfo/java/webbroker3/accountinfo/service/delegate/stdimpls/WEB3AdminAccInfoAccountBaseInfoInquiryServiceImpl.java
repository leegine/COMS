head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccountBaseInfoInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報顧客基本情報問合せサービス実装クラス(WEB3AdminAccInfoAccountBaseInfoInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccountBaseInfoInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報顧客基本情報問合せサービスImpl)<BR>
 * 管理者お客様情報顧客基本情報問合せサービス実装クラス<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoAccountBaseInfoInquiryServiceImpl extends WEB3AccInfoClientRequestService 
    implements WEB3AdminAccInfoAccountBaseInfoInquiryService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoAccountBaseInfoInquiryServiceImpl.class);
    
    /**
     * @@roseuid 418F3A04009C
     */
    public WEB3AdminAccInfoAccountBaseInfoInquiryServiceImpl() 
    {
     
    }
    
    /**
     * 顧客基本情報問合せ処理を行う。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報顧客基本情報問合せ<BR>
     * 入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報顧客基本情報問合せ<BR>
     * リクエストの場合 <BR>
     * 　@－get顧客基本情報()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、管理者お客様情報顧客基本情報問合せ入力リクエストの場合
        if(l_request instanceof WEB3AdminAccInfoAccountBaseInfoInquiryRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoAccountBaseInfoInquiryRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報顧客基本情報問合せリクエストの場合
        else if(l_request instanceof WEB3AdminAccInfoAccountBaseInfoResultRequest)
        {
            l_response = this.getAccountBaseInfo((WEB3AdminAccInfoAccountBaseInfoResultRequest)l_request);
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
     * 顧客基本情報問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（顧客基本情報問合せ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報顧客基本情報問合せ入力リクエストデータオブジェクト
     * @@return WEB3AdminAccInfoAccountBaseInfoInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 416CBD180078
     */
    protected WEB3AdminAccInfoAccountBaseInfoInquiryResponse getInputScreen(
        WEB3AdminAccInfoAccountBaseInfoInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoAccountBaseInfoInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //2) validate権限(機@能カテゴリコード : String, is更新 : boolean)        
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        
        //3) createResponse()
        WEB3AdminAccInfoAccountBaseInfoInquiryResponse l_response = 
            (WEB3AdminAccInfoAccountBaseInfoInquiryResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get顧客基本情報)<BR>
     * 顧客基本情報問合せ処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（顧客基本情報問合せ）get顧客基本情報」参照。 <BR>
     * @@param l_request - 管理者お客様情報顧客基本情報問合せリクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultResponse
     * @@roseuid 4163B2130050
     */
    protected WEB3AdminAccInfoAccountBaseInfoResultResponse getAccountBaseInfo(
        WEB3AdminAccInfoAccountBaseInfoResultRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccountBaseInfo(WEB3AdminAccInfoAccountBaseInfoResultRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate()
        l_request.validate();
        
        //2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //3) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        
        //4) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //5) get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount = null;
        try
        {
            l_gentradeMainAccount = l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_ex.getErrorInfo()))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの入力が不正です。");
            }
            else
            {
                throw l_ex;
            }
        }
        
        //6) validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //7) create顧客基本情報(顧客)
        WEB3AccInfoAccountBaseInfoCreatedService l_accInfoAccountBaseInfoCreatedService = 
            (WEB3AccInfoAccountBaseInfoCreatedService)Services.getService(WEB3AccInfoAccountBaseInfoCreatedService.class);
        WEB3AccInfoAccountBaseInfo l_accInfoAccountBaseInfo = 
            l_accInfoAccountBaseInfoCreatedService.createAccountBaseInfo(l_gentradeMainAccount);
        
        //8) 管理者お客様情報顧客基本情報問合せレスポンス(WEB3GenRequest)
        WEB3AdminAccInfoAccountBaseInfoResultResponse l_response = 
            (WEB3AdminAccInfoAccountBaseInfoResultResponse)l_request.createResponse();
        
        //9) (*1) プロパティセット
        l_response.accountBaseInfo = l_accInfoAccountBaseInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
