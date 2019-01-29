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
filename	WEB3AdminFaqListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者問合せ管理お問合せ一覧サービスImpl(WEB3AdminFaqListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.faq.WEB3Faq;
import webbroker3.faq.define.WEB3FaqKeyItemDef;
import webbroker3.faq.message.WEB3AdminFaqListInputRequest;
import webbroker3.faq.message.WEB3AdminFaqListInputResponse;
import webbroker3.faq.message.WEB3AdminFaqListRequest;
import webbroker3.faq.message.WEB3AdminFaqListResponse;
import webbroker3.faq.message.WEB3FaqInfo;
import webbroker3.faq.message.WEB3FaqSortKey;
import webbroker3.faq.service.delegate.WEB3AdminFaqListService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者問合せ管理お問合せ一覧サービスImpl)<BR>
 * 管理者問合せ管理お問合せ一覧サービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminFaqListServiceImpl implements WEB3AdminFaqListService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqListServiceImpl.class);   
        
    /**
     * @@roseuid 41C25C8B01E4
     */
    public WEB3AdminFaqListServiceImpl() 
    {
     
    }
    
    /**
     * 問合せ一覧処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者問合せ管理お問合せ一覧入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者問合せ管理お問合せ一覧リクエストの場合 <BR>
     * 　@－get問合せ一覧()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41AC19D301F3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminFaqListInputRequest)
        {
            l_response = getInputScreen((WEB3AdminFaqListInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFaqListRequest)
        {
            l_response = getFaqList((WEB3AdminFaqListRequest)l_request);
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
     * (get入力画面)<BR>
     * 問合せ一覧入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「問合せ管理（問合せ一覧）get入力画面」参照。 <BR>
     * @@param l_request - 管理者問合せ管理お問合せ一覧入力リクエストデータオブジェクト
     * 
     * 
     * @@return webbroker3.faq.message.WEB3AdminFaqListInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41AC19D30203
     */
    protected WEB3AdminFaqListInputResponse getInputScreen(WEB3AdminFaqListInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminFaqListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //管理者権限チェックを行う。 
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FAQ, false);
        
        //レスポンスデータを生成する。 
        WEB3AdminFaqListInputResponse l_response = (WEB3AdminFaqListInputResponse)l_request.createResponse();
       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get問合せ一覧)<BR>
     * 問合せ一覧表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「問合せ管理（問合せ一覧）get問合せ一覧」参照。 <BR>
     * @@param l_request - 管理者問合せ管理お問合せ一覧リクエストデータオブジェクト
     * @@return webbroker3.faq.message.WEB3AdminFaqListResponse
     * @@roseuid 41AC19DE006C
     */
    protected WEB3AdminFaqListResponse getFaqList(WEB3AdminFaqListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getFaqList(WEB3AdminFaqListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。 
        l_request.validate();

        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //リクエストデータ.機@能カテゴリコード[]の数分、権限チェックを行う。
        for (int i = 0; i < l_request.transactionCategoryCode.length; i++)
        {
            //管理者権限チェックを行う。 
            l_administrator.validateAuthority(l_request.transactionCategoryCode[i], false);            
        }
        
        //部店権限をチェックする。
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //検索条件文字列を編集する。
        String l_strQueryString = createQueryString(l_request);
        
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //検索条件データコンテナを生成する。
        Object[] l_queryContainer = createQueryContainer(l_request, l_strInstitutionCode); 
                
        //ソート条件を編集する。
        String l_strSortCond = createSortCond(l_request.sortKeys);
        
        //問合せデータを取得する。
        WEB3Faq[] l_faqs = WEB3Faq.getFaq(l_strQueryString, l_queryContainer, l_strSortCond);
        
        //ページ内表示行数
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        //要求ページ番号
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);  
        
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_faqs, l_intPageIndex, l_intPageSize);
        
        WEB3Faq[] l_pagedFaqs = (WEB3Faq[])l_pageIndexInfo.getArrayReturned(WEB3Faq.class);
        
        //レスポンスデータを生成する。 
        WEB3AdminFaqListResponse l_response = (WEB3AdminFaqListResponse)l_request.createResponse();
        
        l_response.faqInfo = new WEB3FaqInfo[l_pagedFaqs.length];

        for (int i = 0; i < l_pagedFaqs.length; i++)
        {
            WEB3FaqInfo l_faqInfo = new WEB3FaqInfo();
            
            //問合せコード：　@問合せ[index].get問合せコード()
            l_faqInfo.faqCode = l_pagedFaqs[i].getFaqNumber();
            
            //部店コード：　@問合せ[index].get部店コード()
            l_faqInfo.branchCode = l_pagedFaqs[i].getBranchCode();
            
            //顧客コード：　@問合せ[index].get口座コード()の左6byte
            if (l_pagedFaqs[i].getAccountCode() != null)
            {
                l_faqInfo.accountCode = l_pagedFaqs[i].getAccountCode().substring(0, 6);
            }
            
            //顧客名：　@問合せ[index].get顧客名（漢字）()
            l_faqInfo.accountName = l_pagedFaqs[i].getName();
            
            //メールアドレス：　@問合せ[index].getメールアドレス()
            l_faqInfo.mailAddress = l_pagedFaqs[i].getEmailAddress();
            
            //問合せ日時：　@問合せ[index].get問合せ日時()
            l_faqInfo.faqDate = l_pagedFaqs[i].getFaqDatetime();
            
            //件名：　@問合せ[index].get件名()
            l_faqInfo.subject = l_pagedFaqs[i].getSubject();
            
            //機@能ＩＤ：　@問合せ[index].get機@能ＩＤ()
            l_faqInfo.transactionId = l_pagedFaqs[i].getTransactionId();
            
            l_response.faqInfo[i] = l_faqInfo;
        }
        
        //総ページ数
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        
        //総レコード数
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        
        //表示ページ番号
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();

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
     * ２）　@証券会社条件追加<BR>
     * 　@証券会社コード条件を追加する。<BR>
     * <BR>
     * 　@" institution_code = ? "<BR>
     * <BR>
     * ３）　@部店条件追加<BR>
     * 　@部店コード配列の要素数分、部店コード条件を追加する。 <BR>
     * <BR>
     * 　@" and branch_code in (?, ?, ･･･)" <BR>
     * <BR>
     * ４）　@問合せコード条件追加　@※指定がある場合のみ，前方一致検索<BR>
     * 　@（リクエストデータ.問合せコード != null）の場合、問合せコード条件を追加する。<BR>
     * <BR>
     * 　@" and faq_number like "<BR>
     * <BR>
     * ５）　@顧客コード条件追加　@※指定がある場合のみ <BR>
     * 　@（リクエストデータ.顧客コード != null）の場合、口座コード条件を追加する。 <BR>
     * <BR>
     * 　@" and account_code like ? " <BR>
     * <BR>
     * ６）　@顧客名条件追加　@※指定がある場合のみ，前方／後方一致検索<BR>
     * 　@（リクエストデータ.顧客名　@ != null）の場合、顧客名（漢字）条件を追加する。 <BR>
     * <BR>
     * 　@" and name like ? " <BR>
     * <BR>
     * ７）　@件名条件追加　@※指定がある場合のみ<BR>
     * 　@（リクエストデータ.件名　@ != null）の場合、件名条件を追加する。 <BR>
     * <BR>
     * 　@" and subject = ? "<BR>
     * <BR>
     * ８）　@問合せ日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.問合せ日（自） != null）の場合、問合せ日条件を追加する。 <BR>
     * <BR>
     * 　@" and created_timestamp >= ? " <BR>
     * <BR>
     * ９）　@問合せ日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.問合せ日（至） != null）の場合、問合せ日条件を追加する。 <BR>
     * <BR>
     * 　@" and created_timestamp <= ? " <BR>
     * <BR>
     * １０）　@機@能ＩＤ条件追加<BR>
     * 　@機@能ＩＤ配列の要素数分、機@能ＩＤ条件を追加する。 <BR>
     * <BR>
     * 　@" and transaction_id in (?, ?, ･･･)" <BR>
     * <BR>
     * １１）　@文字列インスタンスを返却 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者問合せ管理お問合せ一覧リクエストデータオブジェクト<BR>
     * 
     * @@return String
     * @@roseuid 41AC22610389
     */
    protected String createQueryString(WEB3AdminFaqListRequest l_request) 
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3AdminFaqListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbQueryString = new StringBuffer();
        
        //証券会社コード条件を追加する。 
        l_sbQueryString.append("institution_code = ?");
        
        //部店コード配列の要素数分、部店コード条件を追加する。         
        if (l_request.branchCode.length > 0)
        {
            String l_strSubCond = null;

            for (int i = 0; i < l_request.branchCode.length; i++)
            {
                if (l_strSubCond == null)
                {
                    l_strSubCond = "?";
                }
                else
                {
                    l_strSubCond += ", ?";
                }
            }
        
            l_sbQueryString.append(" and branch_code in(" + l_strSubCond + ")");
        }
        
        //問合せコード条件追加　@※指定がある場合のみ，前方一致検索
        //（リクエストデータ.問合せコード != null）の場合、問合せコード条件を追加する。 
        if (l_request.faqCode != null)
        {
            l_sbQueryString.append(" and faq_number like ? || '%'");
        }
        
        
        //顧客コード条件追加　@※指定がある場合のみ 
        //（リクエストデータ.顧客コード != null）の場合、口座コード条件を追加する。 
        if (l_request.accountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '_'");
        }
        
        //顧客名条件追加　@※指定がある場合のみ，前方／後方一致検索
        //（リクエストデータ.顧客名　@ != null）の場合、顧客名（漢字）条件を追加する。 
        if (l_request.accountName != null)
        {
            l_sbQueryString.append(" and name like '%' || ? || '%'");
        }
        
        //件名条件追加　@※指定がある場合のみ 
        //（リクエストデータ.件名　@ != null）の場合、件名条件を追加する。 
        if (l_request.subject != null)
        {
            l_sbQueryString.append(" and subject = ?");
        }
        
        //問合せ日（自）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.問合せ日（自） != null）の場合、問合せ日条件を追加する。 
        if (l_request.faqDateFrom != null)
        {
            l_sbQueryString.append(" and created_timestamp >= ?");
        }
        
        //問合せ日（至）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.問合せ日（至） != null）の場合、問合せ日条件を追加する。
        if (l_request.faqDateTo != null)
        {
            l_sbQueryString.append(" and created_timestamp <= ?");
        }
        
        //機@能ＩＤ条件追加
        //機@能ＩＤ配列の要素数分、機@能ＩＤ条件を追加する。
        if (l_request.transactionId.length > 0)
        {
            String l_strSubCond = null;

            for (int i = 0; i < l_request.transactionId.length; i++)
            {
                if (l_strSubCond == null)
                {
                    l_strSubCond = "?";
                }
                else
                {
                    l_strSubCond += ", ?";
                }
            }
        
            l_sbQueryString.append(" and transaction_id in(" + l_strSubCond + ")");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
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
     * ３）　@部店条件追加 ※指定がある場合のみ <BR>
     * 　@（リクエストデータ.部店コード[] != null）の場合、<BR>
     * 　@戻り値編集用インスタンスに、部店コード配列の要素数分部店コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.部店コード[index]<BR>
     * <BR>
     * ４）　@問合せコード条件追加　@※指定がある場合のみ，前方一致検索<BR>
     * 　@（リクエストデータ.問合せコード != null）の場合、戻り値編集用<BR>
     * インスタンスに問合せコードを追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.問合せコード + "%"<BR>
     * <BR>
     * ５）　@顧客コード条件追加　@※指定がある場合のみ <BR>
     * 　@（リクエストデータ.顧客コード != null）の場合、戻り値編集用<BR>
     * インスタンスに口座コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.顧客コード + "%"<BR>
     * <BR>
     * ６）　@顧客名条件追加　@※指定がある場合のみ，前方／後方一致検索<BR>
     * 　@（リクエストデータ.顧客名　@ != null）の場合、戻り値編集用<BR>
     * インスタンスに顧客名（漢字）を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@"%" + リクエストデータ.顧客名（漢字） + "%"<BR>
     * <BR>
     * ７）　@件名条件追加　@※指定がある場合のみ<BR>
     * 　@（リクエストデータ.件名　@ != null）の場合、戻り値編集用<BR>
     * インスタンスに件名を追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.件名<BR>
     * <BR>
     * ８）　@問合せ日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.問合せ日（自） != null）の場合、<BR>
     * 　@戻り値編集用インスタンスに問合せ日文字列を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.問合せ日（自）<BR>
     * <BR>
     * ９）　@問合せ日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.問合せ日（至） != null）の場合、<BR>
     * 　@戻り値編集用インスタンスに問合せ日文字列を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.問合せ日（至）　@※時間部分は 23:59:59　@に編集する。<BR>
     * <BR>
     * １０）　@機@能ＩＤ条件追加<BR>
     * 　@戻り値編集用インスタンスに、機@能ＩＤ配列の要素数分機@能ＩＤを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.機@能ＩＤ[index]<BR>
     * <BR>
     * １１）　@配列を返却 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者問合せ管理お問合せ一覧リクエストデータオブジェクト<BR>
     * 
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String[]
     * @@roseuid 41AC22610399
     */
    protected Object[] createQueryContainer(WEB3AdminFaqListRequest l_request, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = "createQueryContainer(WEB3AdminFaqListRequest, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstQueryContainer = new ArrayList();
        
        //証券会社条件追加
        l_lstQueryContainer.add(l_strInstitutionCode);
        
        //部店条件追加 ※指定がある場合のみ 
        //戻り値編集用インスタンスに、部店コード配列の要素数分部店コードを追加する。 
        if (l_request.branchCode.length > 0)
        {
            for (int i = 0; i < l_request.branchCode.length; i++)
            {
                l_lstQueryContainer.add(l_request.branchCode[i]);
            }
        }
        
        //問合せコード条件追加　@※指定がある場合のみ，前方一致検索
        //（リクエストデータ.問合せコード != null）の場合、戻り値編集用インスタンスに問合せコードを追加する。 
        if (l_request.faqCode != null)
        {
            l_lstQueryContainer.add(l_request.faqCode);
        }
        
        //顧客コード条件追加　@※指定がある場合のみ 
        //（リクエストデータ.顧客コード != null）の場合、戻り値編集用インスタンスに口座コードを追加する。 
        if (l_request.accountCode != null)
        {
            l_lstQueryContainer.add(l_request.accountCode);
        }
        
        //顧客名条件追加　@※指定がある場合のみ，前方／後方一致検索
        //（リクエストデータ.顧客名　@ != null）の場合、戻り値編集用インスタンスに顧客名（漢字）を追加する。 
        if (l_request.accountName != null)
        {
            l_lstQueryContainer.add(l_request.accountName);
        }
        
        //件名条件追加　@※指定がある場合のみ 
        //（リクエストデータ.件名　@ != null）の場合、戻り値編集用インスタンスに件名を追加する。
        if (l_request.subject != null)
        {
            l_lstQueryContainer.add(l_request.subject);
        }
        
        //問合せ日（自）条件追加 ※指定がある場合のみ
        //（リクエストデータ.問合せ日（自） != null）の場合、
        //戻り値編集用インスタンスに問合せ日文字列を追加する。         
        if (l_request.faqDateFrom != null)
        {
            l_lstQueryContainer.add(l_request.faqDateFrom);
        }
        
        //問合せ日（至）条件追加 ※指定がある場合のみ 
        //（リクエストデータ.問合せ日（至） != null）の場合、 
        //戻り値編集用インスタンスに問合せ日文字列を追加する。 
        if (l_request.faqDateTo != null)
        {            
            Calendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(l_request.faqDateTo);
            gCalendar.set(Calendar.HOUR_OF_DAY, 23);
            gCalendar.set(Calendar.MINUTE, 59);
            gCalendar.set(Calendar.SECOND, 59);
            l_lstQueryContainer.add(gCalendar.getTime());

        }
        
        //機@能ＩＤ条件追加 
        //戻り値編集用インスタンスに、機@能ＩＤ配列の要素数分機@能ＩＤを追加する。 
        if (l_request.transactionId.length > 0)
        {
            for (int i = 0; i < l_request.transactionId.length; i++)
            {
                l_lstQueryContainer.add(l_request.transactionId[i]);
            }
        }
        
        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);
        
        log.exiting(STR_METHOD_NAME);
        return l_queryContainer;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件文字列を編集する。 <BR>
     * <BR>
     * 引数のソートキーが示す項目に該当する問合せテーブル列物理名を使用し、 <BR>
     * ソートキーの指定の通り、ソート条件文字列（order by句）を編集し返却する。 <BR>
     * <BR>
     * ※　@ソートキーに指定される項目は以下の通り。<BR>
     * 　@ 問合せ情報.問合せコード （問合せテーブル.問合せコード）<BR>
     * 　@ 問合せ情報.部店コード （問合せテーブル.部店コード）<BR>
     * 　@ 問合せ情報.顧客コード （問合せテーブル.口座コード）<BR>
     * 　@ 問合せ情報.問合せ日時（口座開設見込客.資料請求日時）<BR>
     * 　@ 問合せ情報.機@能ＩＤ（問合せテーブル.機@能ＩＤ）<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * 問合せ管理ソートキーの配列<BR>
     * 
     * @@return String
     * @@roseuid 41AC2261039C
     */
    protected String createSortCond(WEB3FaqSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3FaqSortKey[] l_sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbSortCond = new StringBuffer();
        
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            String l_strSubCond = null;
            if (WEB3FaqKeyItemDef.FAQ_NUMBER.equals(l_sortKeys[i].keyItem))
            {
                //問合せ情報.問合せコード （問合せテーブル.問合せコード） 
                l_strSubCond = "faq_number";
            }
            else if (WEB3FaqKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //問合せ情報.部店コード （問合せテーブル.部店コード）  
                l_strSubCond = "branch_code";
            }
            else if (WEB3FaqKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //問合せ情報.顧客コード （問合せテーブル.口座コード）   
                l_strSubCond = "account_code";
            }
            else if (WEB3FaqKeyItemDef.FAQ_DATETIME.equals(l_sortKeys[i].keyItem))
            {
                //問合せ情報.問合せ日時（口座開設見込客.資料請求日時）    
                l_strSubCond = "created_timestamp";
            }
            else if (WEB3FaqKeyItemDef.TRANSACTION_ID.equals(l_sortKeys[i].keyItem))
            {
                //問合せ情報.機@能ＩＤ（問合せテーブル.機@能ＩＤ） 
                l_strSubCond = "transaction_id";
            }
            else
            {
                continue;
            }
            
            if (l_sbSortCond.length() != 0)
            {
                l_sbSortCond.append(", ");
            }
            l_sbSortCond.append(l_strSubCond);
            
            if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append(" desc");
            }
            else
            {
                l_sbSortCond.append(" asc");
            }
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCond.toString();
    }
}
@
