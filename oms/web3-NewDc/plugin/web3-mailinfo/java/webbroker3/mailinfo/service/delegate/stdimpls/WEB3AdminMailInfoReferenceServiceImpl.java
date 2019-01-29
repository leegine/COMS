head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.23.04.50.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1cc4d897c1210b4;
filename	WEB3AdminMailInfoReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報一覧サービスImpl(WEB3AdminMailInfoReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.mailinfo.WEB3AdminMailInfoClientRequestService;
import webbroker3.mailinfo.WEB3AdminMailInfoManager;
import webbroker3.mailinfo.define.WEB3AdminMailInfoKeyItemDef;
import webbroker3.mailinfo.message.WEB3AdminMailInfoGroup;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoSortKey;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoReferenceService;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3LogUtility;


/**
 * (メール情報一覧サービスImpl)<BR>
 * メール情報一覧サービス実装クラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0.1
 */
public class WEB3AdminMailInfoReferenceServiceImpl extends WEB3AdminMailInfoClientRequestService implements WEB3AdminMailInfoReferenceService 
{    
    /**
     * ログ出力ユーティリティ。
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoReferenceServiceImpl.class);
    
    /**
     * @@roseuid 416F1DCB03D8
     */
    public WEB3AdminMailInfoReferenceServiceImpl() 
    {
     
    }
    
    /**
     * メール情報一覧照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（メール情報一覧）execute」参照<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C46DF005B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if(!(l_request instanceof WEB3AdminMailInfoReferenceRequest))
        {
            String l_strErrorMessage = "パラメータの類型が不正";
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80018.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
        
        WEB3AdminMailInfoReferenceRequest l_referenceRequest = (WEB3AdminMailInfoReferenceRequest)l_request;
        String l_strSortCond = null;
        String l_strInstitutionCode = null;
              
        // (1.1)validate
        l_referenceRequest.validate();

        // (1.2)getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //(1.3)validate権限()
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, false);

        //(1.4)get証券会社コード( )
        l_strInstitutionCode = l_administartor.getInstitutionCode();

        //(1.5)createソート条件(メール情報ソートキー[ ])
        l_strSortCond = createSortCond(l_referenceRequest.sortKeys);        

        //(1.6)getメール一覧(String, String)
        WEB3GentradeMailInfo[] l_genMails = WEB3AdminMailInfoManager.getMailList(l_strInstitutionCode, l_strSortCond);
        WEB3AdminMailInfoReferenceResponse l_response = null;   
        
            //(1.7)繰り返し処
            int l_intSizeCnt = l_genMails.length;
            WEB3AdminMailInfoGroup[] l_mailGroups = new WEB3AdminMailInfoGroup[l_intSizeCnt];
            for (int i = 0; i < l_intSizeCnt; i ++)
            {
                WEB3AdminMailInfoGroup l_mailGroup = new WEB3AdminMailInfoGroup();
                l_mailGroup.sendMailDiv = l_genMails[i].getSendmailDiv();
                l_mailGroup.discernId = l_genMails[i].getDiscernmentId();
                l_mailGroup.mailName = l_genMails[i].getMailName();
                l_mailGroup.mailFrom = l_genMails[i].getMailSender();
                l_mailGroup.mailSubject = l_genMails[i].getSubject();     
                l_mailGroups[i] = l_mailGroup;    
            }
            
            //1.8 createレスポンス( )
            l_response = (WEB3AdminMailInfoReferenceResponse) l_referenceRequest.createResponse();
            
            //1.9 <ページング制御>
            //ページ内表示行数
            int l_intPageSize = Integer.parseInt(l_referenceRequest.pageSize);
            
            //要求ページ番号
            int l_intPageIndex = Integer.parseInt(l_referenceRequest.pageIndex);
                       
            //ページング制御
            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_mailGroups, l_intPageIndex, l_intPageSize);
            
            //プロパティ・セット
            //(表示ページ番号)
            l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
            //総ページ数
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";  
            //総レコード数
            l_response.totalRecords = l_pageIndexInfo.getTotalRecords() +"";
              
            Object[] l_temps = l_pageIndexInfo.getArrayReturned(WEB3AdminMailInfoGroup.class);
            if (l_temps != null)
            {
                l_response.mailInfoList = (WEB3AdminMailInfoGroup[])l_temps;             
            }

        
        log.exiting(STR_METHOD_NAME);                
        return l_response;
    }
    
    /**
     * (createソート条件)<BR>
     * リクエストされたソートキーから、ソート条件として使用可能な<BR>
     * 文字列を作成し、返却する。<BR>
     * <BR>
     * 1) ソート条件の作成<BR>
     * 　@引数.ソート条件の件数分、以下を繰り返す。<BR>
     *  1-1) 対応するテーブルの列物理名を昇順／降順指定を付加しセットする。 <BR>
     * <BR>
     * 　@　@○キー項目とテーブルの列名称との対応は以下の通り。 <BR>
     * 　@　@　@※キー項目文字列(シンボル名)は、メッセージ定義書を参照。 <BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照。 <BR>
     * <BR>
     * 　@　@　@　@・プログラムID=メール.プログラムID<BR>
     * 　@　@　@　@・識別ID =メール.識別ID<BR>
     * <BR>
     * 　@　@○昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定する。 <BR>
     * <BR>
     * 2) 1)で作成した文字列を返却する。<BR>
     * @@param l_sortKey - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return String
     * @@roseuid 413C48CC015B
     */
    protected  String createSortCond(WEB3AdminMailInfoSortKey[] l_sortKey) 
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3AdminMailInfoSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbReturn = new StringBuffer();
        
        for (int i = 0; i < l_sortKey.length; i++)
        {
            if(WEB3AdminMailInfoKeyItemDef.DISCERN_ID.equals(l_sortKey[i].keyItem ))
            {
                if (l_sbReturn.length() != 0) 
                {
                    l_sbReturn.append(", ");
                }
                l_sbReturn.append("discernment_id");
            }
            else if(WEB3AdminMailInfoKeyItemDef.SENDMAIL_DIV.equals(l_sortKey[i].keyItem))
            {
                if (l_sbReturn.length() != 0) 
                {
                    l_sbReturn.append(", ");
                }
                l_sbReturn.append("sendmail_div");
            }
            else
            {
                continue;
            }
            
            if (WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
            {
                l_sbReturn.append(" ASC");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKey[i].ascDesc)) 
            {
                l_sbReturn.append(" DESC");
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbReturn.toString();
    }
}
@
