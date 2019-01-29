head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinInstitutionSearchServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設金融機@関検索サービスImpl(WEB3AccOpenFinInstitutionSearchServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 張学剛 (中訊) 新規作成
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import webbroker3.accountopen.WEB3AccOpenFinInstitutionBank;
import webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchRequest;
import webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchResponse;
import webbroker3.accountopen.message.WEB3AccOpenFinancialSearchRequest;
import webbroker3.accountopen.message.WEB3AccOpenFinancialSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenFinInstitutionSearchService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設金融機@関検索サービスImpl)<BR>
 * 口座開設金融機@関検索サービス実装クラス<BR>
 * @@author 張学剛
 * @@version 1.0 
 */
public class WEB3AccOpenFinInstitutionSearchServiceImpl implements WEB3AccOpenFinInstitutionSearchService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenFinInstitutionSearchServiceImpl.class);

    /**
     * @@roseuid 41B45E720186
     */
    public WEB3AccOpenFinInstitutionSearchServiceImpl() 
    {
     
    }
    
    /**
     * 金融機@関検索処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、口座開設金融機@関検索リクエストの場合 <BR>
     * 　@－get銀行一覧()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、口座開設支店検索リクエストの場合 <BR>
     * 　@－get支店一覧()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A1744D01D7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AccOpenFinancialSearchRequest)
        {
            log.debug("WEB3AccOpenFinancialSearchRequest");
            WEB3AccOpenFinancialSearchResponse l_response = 
                getFinInstitutionList((WEB3AccOpenFinancialSearchRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AccOpenFinancialBranchSearchRequest)
        {
            log.debug("WEB3AccOpenFinancialBranchSearchRequest");
            WEB3AccOpenFinancialBranchSearchResponse l_response = 
                getFinBranchList((WEB3AccOpenFinancialBranchSearchRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get銀行一覧)<BR>
     * 銀行一覧表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（金融機@関検索）get銀行一覧」参照。 <BR>
     * @@param l_request - 口座開設金融機@関検索リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AccOpenFinancialSearchResponse
     * @@roseuid 41A174540310
     */
    protected WEB3AccOpenFinancialSearchResponse getFinInstitutionList(WEB3AccOpenFinancialSearchRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFinInstitutionList(WEB3AccOpenFinancialSearchRequest)";
        log.entering(STR_METHOD_NAME);    
        
        //1.1 get銀行(String)
        WEB3AccOpenFinInstitutionBank[] l_bank = 
            WEB3AccOpenFinInstitutionBank.getFinInstitution(l_request.headCharacter);
        
        //1.2 createResponse( )
        WEB3AccOpenFinancialSearchResponse l_response = 
            (WEB3AccOpenFinancialSearchResponse)l_request.createResponse();
        
        int l_int = 0;
        if (l_bank != null)
        {
            l_int = l_bank.length;   
        }
        //1.3 プロパティセット
        l_response.financialInstitutionName = new String[l_int];
        l_response.financialInstitutionNameKana = new String[l_int];
        l_response.financialInstitutionCode = new String[l_int];
        for (int i = 0; i < l_int; i++)
        {
            log.debug("1.3 プロパティセット");
            //get銀行名
            
            l_response.financialInstitutionName[i] = l_bank[i].getFinInstitutionName();
            
            //銀行名（カナ）
            l_response.financialInstitutionNameKana[i] = l_bank[i].getFinInstitutionNameKana();
            
            //銀行コード
            l_response.financialInstitutionCode[i] = l_bank[i].getFinInstitutionCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get支店一覧)<BR>
     * 支店一覧表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（金融機@関検索）get支店一覧」参照。 <BR>
     * @@param l_request - 口座開設支店検索リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchResponse
     * @@roseuid 41A17484036D
     */
    protected WEB3AccOpenFinancialBranchSearchResponse getFinBranchList(WEB3AccOpenFinancialBranchSearchRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFinBranchList(WEB3AccOpenFinancialBranchSearchRequest)";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 get支店(String, String)
        WEB3AccOpenFinInstitutionBank[] l_bank = WEB3AccOpenFinInstitutionBank.getFinBranch(
            l_request.financialInstitutionName, l_request.headCharacter);
            
        //1.2 createResponse( )
        WEB3AccOpenFinancialBranchSearchResponse l_response = 
            (WEB3AccOpenFinancialBranchSearchResponse)l_request.createResponse();
        
        int l_int = 0;
        if (l_bank != null)
        {
            l_int = l_bank.length;   
        }
        
        l_response.financialBranchName = new String[l_int];
        l_response.financialBranchNameKana = new String[l_int];
        l_response.financialBranchCode = new String[l_int];
        //1.3 プロパティセット
        for (int i = 0; i < l_int; i++)
        {
            log.debug("1.3 プロパティセット");
            //get支店名
            l_response.financialBranchName[i] = l_bank[i].getFinBranchName();
            
            //get支店名（カナ）
            l_response.financialBranchNameKana[i] = l_bank[i].getFinBranchNameKana();
            
            //get支店コード
            l_response.financialBranchCode[i] = l_bank[i].getFinBranchCode();
        }   
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
