head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqDetailsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者問い合わせ管理お問合せ詳細サービスImpl(WEB3AdminFaqDetailsServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.faq.WEB3Faq;
import webbroker3.faq.message.WEB3AdminFaqDetailsRequest;
import webbroker3.faq.message.WEB3AdminFaqDetailsResponse;
import webbroker3.faq.message.WEB3FaqInfo;
import webbroker3.faq.service.delegate.WEB3AdminFaqDetailsService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者問い合わせ管理お問合せ詳細サービスImpl)<BR>
 * 管理者問い合わせ管理お問合せ詳細サービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminFaqDetailsServiceImpl implements WEB3AdminFaqDetailsService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqDetailsServiceImpl.class);
           
    /**
     * @@roseuid 41C25C8B038A
     */
    public WEB3AdminFaqDetailsServiceImpl() 
    {
     
    }
    
    /**
     * 問合せ詳細表示処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR> 
     * <BR>
     * ○ 引数のリクエストデータが、管理者問合せ管理お問合せ詳細リクエストの場合 <BR>
     * 　@－get問合せ詳細()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41AC2E9501BC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminFaqDetailsRequest)
        {
            l_response = getFaqDetails((WEB3AdminFaqDetailsRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get問合せ詳細)<BR>
     * 問合せ詳細表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「問合せ管理（問合せ詳細）get問合せ詳細」参照。 <BR>
     * @@param l_request - 管理者問合せ管理お問合せ詳細リクエストデータオブジェクト
     * @@return webbroker3.faq.message.WEB3AdminFaqDetailsResponse
     * @@roseuid 41AC2E9501CC
     */
    protected WEB3AdminFaqDetailsResponse getFaqDetails(WEB3AdminFaqDetailsRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getFaqDetails(WEB3AdminFaqDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //管理者権限チェックを行う。 
        String l_strTransactionCategory = null;
        if (l_request.transactionCategoryCode != null)
        {
            l_strTransactionCategory = l_request.transactionCategoryCode;
        }
        else
        {
            l_strTransactionCategory = WEB3TransactionCategoryDef.FAQ;
        }
        
        l_administrator.validateAuthority(l_strTransactionCategory, false);
        
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        WEB3Faq l_faq = null;
        
        try
        {
            l_faq = new WEB3Faq(l_strInstitutionCode, l_request.faqCode);
        }
        catch (NotFoundException e)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当データ無し。" + 
                " [証券会社コード] = " + l_strInstitutionCode + 
                " [問合せコード] = " + l_request.faqCode);
        }
        
        //部店コードを取得する。
        String l_strBranchCode = l_faq.getBranchCode();
        
        //部店権限をチェックする。
        l_administrator.validateBranchPermission(l_strBranchCode);
        
        WEB3FaqInfo l_faqInfo = new WEB3FaqInfo();
        
        //問合せ情報メッセージデータプロパティに以下の通り、値をセットする。
        
        //問合せコード：　@問合せ.get問合せコード()
        l_faqInfo.faqCode = l_faq.getFaqNumber();
            
        //部店コード：　@問合せ.get部店コード()
        l_faqInfo.branchCode = l_faq.getBranchCode();
            
        //顧客コード：　@問合せet口座コード()の左6byte
        String l_strAccountCode = l_faq.getAccountCode();
        if (l_strAccountCode != null)
        {
            l_faqInfo.accountCode = l_strAccountCode.substring(0, 6);
        }
            
        //顧客名：　@問合せ.get顧客名（漢字）()
        l_faqInfo.accountName = l_faq.getName();
            
        //メールアドレス：　@問合せ.getメールアドレス()
        l_faqInfo.mailAddress = l_faq.getEmailAddress();
            
        //問合せ日時：　@問合せ.get問合せ日時()
        l_faqInfo.faqDate = l_faq.getFaqDatetime();
            
        //件名：　@問合せ.get件名()
        l_faqInfo.subject = l_faq.getSubject();
            
        //機@能ＩＤ：　@問合せ.get機@能ＩＤ()
        l_faqInfo.transactionId = l_faq.getTransactionId();
        
        //問合せ内容：　@問合せ.get問合せ内容()
        l_faqInfo.faqText = l_faq.getFaqText();
        
        //レスポンスデータを生成する。 
        WEB3AdminFaqDetailsResponse l_response = (WEB3AdminFaqDetailsResponse)l_request.createResponse();
        
        l_response.faqInfo = l_faqInfo;
       
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
}
@
