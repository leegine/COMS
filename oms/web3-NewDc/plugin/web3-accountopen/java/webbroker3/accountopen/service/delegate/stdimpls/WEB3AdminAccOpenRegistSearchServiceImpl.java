head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenRegistSearchServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込検索サービスImpl(WEB3AdminAccOpenRegistSearchServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AdminAccOpenRegistCsv;
import webbroker3.accountopen.define.WEB3AccOpenAccountOpenDivDef;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenRegistSearchService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座開設申込検索サービスImpl)<BR>
 * 管理者口座開設申込検索サービス実装クラス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminAccOpenRegistSearchServiceImpl implements WEB3AdminAccOpenRegistSearchService 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenRegistSearchServiceImpl.class);
    
    /**
     * @@roseuid 41B45E720271
     */
    public WEB3AdminAccOpenRegistSearchServiceImpl() 
    {
     
    }
    
    /**
     * 口座開設申込検索処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設申込検索入力リクエストの場合<BR> 
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設申込ダウンロードリクエストの場合<BR> 
     * 　@－getダウンロードファ@イル()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A155810029
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminAccOpenApplySearchInputRequest)
        {
            log.debug("管理者口座開設申込検索入力リクエスト");
            WEB3AdminAccOpenApplySearchInputResponse l_response = 
                getInputScreen((WEB3AdminAccOpenApplySearchInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyDownloadRequest)
        {
            log.debug("管理者口座開設申込ダウンロードリクエスト");
            WEB3AdminAccOpenApplyDownloadResponse l_response = 
                getDownloadFile((WEB3AdminAccOpenApplyDownloadRequest)l_request);
        
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
     * (get入力画面)<BR>
     * 口座開設申込検索画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（申込検索）get入力画面」参照。 <BR>
     * @@param l_request - 管理者口座開設申込検索入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A1550101DF
     */
    protected WEB3AdminAccOpenApplySearchInputResponse getInputScreen(WEB3AdminAccOpenApplySearchInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccOpenApplySearchInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限(機@能カテゴリコード（=口座開設） : String, is更新（=false） : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.3 createResponse( )
        WEB3AdminAccOpenApplySearchInputResponse l_response = 
            (WEB3AdminAccOpenApplySearchInputResponse)l_request.createResponse();
            
        Timestamp l_tsCurrentDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        //1.4 (*) プロパティセット
        //前営業日
        l_response.previousBizDate = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);
        
        //前日
        l_response.previousDate =  WEB3DateUtility.toDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1));
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * 口座開設申込ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（申込検索）getダウンロードファ@イル」参照。 <BR>
     * @@param l_request - 管理者口座開設申込ダウンロードリクエストデータオブジェクト
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A1550101FE
     */
    protected WEB3AdminAccOpenApplyDownloadResponse getDownloadFile(WEB3AdminAccOpenApplyDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        log.debug("1.1 validate( )");
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        log.debug("1.2 getInstanceFromログイン情報( )");
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=口座開設） : String, is更新（=false） : boolean)
        log.debug("1.3 validate権限(機@能カテゴリコード（=口座開設） : String, is更新（=false） : boolean)");
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.4 validate部店権限(String[])
        log.debug("1.4 validate部店権限(String[])");
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 create検索条件文字列(管理者口座開設申込ダウンロードリクエスト)
        log.debug("1.6 create検索条件文字列(管理者口座開設申込ダウンロードリクエスト)");
        String l_strQueryString = this.createQueryString(l_request);
        
        log.debug("1.7 create検索条件データコンテナ(管理者口座開設申込ダウンロードリクエスト, String)");
        //1.7 create検索条件データコンテナ(管理者口座開設申込ダウンロードリクエスト, String)
        String[] l_queryContainers = this.createQueryContainer(l_request, l_strInstitutionCode);
        
        //1.8 get口座開設見込客(String, String[], String, Date, Date, String)
        log.debug("1.8 get口座開設見込客(String, String[], String, Date, Date, String)");
        List l_lisExpAccountOpens = WEB3AccOpenExpAccountOpen.getExpAccountOpen(
            l_strQueryString, 
            l_queryContainers,
            "branch_code, infomation_claim_datetime asc",
            null,
            null,
            null);
       
        //1.9 口座開設申込CSV(String, String)
        log.debug("1.9 口座開設申込CSV(String, String)");
        WEB3AdminAccOpenRegistCsv l_csv = new WEB3AdminAccOpenRegistCsv(l_strInstitutionCode, l_request.accountType);
        
        //1.10 get口座開設見込客()戻り値の各要素毎のLOOP処理
        //(*) get口座開設見込客()戻り値の各要素毎のLOOP処理。
        //  但し、get口座開設見込客()戻り値の件数が
        //  リクエストデータ.ダウンロード件数より多い場合、
        //  ダウンロード件数までとする。
        log.debug("1.10 get口座開設見込客()戻り値の各要素毎のLOOP処理");
        int l_intExpAccountOpenCount = l_lisExpAccountOpens.size();
        int l_intDownloadNumber = 0;
        int l_intLoop = 0;
        if (l_request.downloadNumber != null)
        {
            l_intDownloadNumber = Integer.parseInt(l_request.downloadNumber);
        }
        
        if (l_intExpAccountOpenCount > l_intDownloadNumber)
        {
            l_intLoop = l_intDownloadNumber;
        }
        else
        {
            l_intLoop = l_intExpAccountOpenCount;
        }
        
        for (int i = 0; i < l_intLoop; i++)
        {
            //1.10.1 add明細行( )
            int l_int = l_csv.addRow();
            
            //1.10.2 set項目値(int, 口座開設見込客)
            log.debug("1.10.1 set項目値(int, 口座開設見込客)");
            l_csv.setValue(l_int, (WEB3AccOpenExpAccountOpen)l_lisExpAccountOpens.get(i));
        }
        
        //1.11 getCSVファ@イル行( )
        log.debug("1.11 getCSVファ@イル行( )");
        String[] l_strLines = l_csv.getCsvFileLines();
        
        //1.12 createResponse( )
        WEB3AdminAccOpenApplyDownloadResponse l_response = (WEB3AdminAccOpenApplyDownloadResponse)l_request.createResponse();
        
        //1.13 プロパティセット
        //ダウンロードファ@イル
        log.debug("l_intExpAccountOpenCount = " + l_intExpAccountOpenCount);
        log.debug("l_strLines.length = " + l_strLines.length);
        l_response.downloadFile = l_strLines;
        
        //　@総レコード数：　@get口座開設見込客()戻り値の要素数
        l_response.totalRecords = Integer.toString(l_intExpAccountOpenCount);
        
        //現在日時
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
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
     * 　@" and （branch_code = ? or branch_code = ? ･･･）" <BR>
     * <BR>
     * ４）　@口座区分条件追加<BR>
     * 　@口座区分条件を追加する。 <BR>
     * <BR>
     * 　@" and account_div = ? " <BR>
     * <BR>
     * ５）　@顧客コード（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.顧客コード（自） != null）の場合、口座コード条件を追加する。 <BR>
     * <BR>
     * 　@" and account_code >= ? " <BR>
     * <BR>
     * ６）　@顧客コード（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.顧客コード（至） != null）の場合、口座コード条件を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and account_code <= ? " <BR>
     * <BR>
     * ７）　@資料請求日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.資料請求日（自） != null）の場合、資料請求日時条件を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and to_char(infomation_claim_datetime, 'YYYYMMDD') >= ? " <BR>
     * <BR>
     * ８）　@資料請求日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.資料請求日（至） != null）の場合、資料請求日時条件を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and to_char(infomation_claim_datetime, 'YYYYMMDD') <= ? " <BR>
     * <BR>
     * ９）　@口座開設日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.口座開設日（自） != null）の場合、口座開設日条件を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and to_char(account_open_date, 'YYYYMMDD') >= ? " <BR>
     * <BR>
     * １０）　@口座開設日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.口座開設日（至） != null）の場合、口座開設日条件を<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@" and to_char(account_open_date, 'YYYYMMDD') <= ? " <BR>
     * <BR>
     * １１）　@識別コード（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.識別コード（自） != null）の場合、識別コード条件を追加する。 <BR>
     * <BR>
     * 　@" and acc_open_request_number >= ? " <BR>
     * <BR>
     * １２）　@識別コード（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.識別コード（至） != null）の場合、識別コード条件を追加する。 <BR>
     * <BR>
     * 　@" and acc_open_request_number <= ? " <BR>
     * <BR>
     * １３）　@口座開設区分条件追加※指定がある場合のみ <BR>
　@   *   （リクエストデータ.口座開設区分 != null）の場合、区分値により、条件を追加する。 <BR>
     * <BR>
　@   *     [リクエストデータ.口座開設区分が "口座開設済み"の場合］<BR> 
     * <BR> 
　@   *     " and account_open_date is not null " <BR>
     * <BR> 
     *　@  ［リクエストデータ.口座開設区分が "口座未開設"の場合］<BR>  
     * <BR>
     *　@   " and account_open_date is null " <BR>
     * <BR>
     * １４）　@検索オプション区分条件追加※指定がある場合のみ <BR>
　@   *   （リクエストデータ.検索オプション区分 != null）の場合、<BR>
     *    検索オプション区分条件を追加する。<BR> 
     * <BR> 
　@   *    " and id_confirm_flag = ? " <BR>
     * <BR> 
     * １５）　@文字列インスタンスを返却 <BR>
     * @@param l_request - 管理者口座開設申込ダウンロードリクエストデータオブジェクト
     * 
     * @@return String
     * @@roseuid 41A1665F0070
     */
    protected String createQueryString(WEB3AdminAccOpenApplyDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@戻り値生成
        String l_strQueryString = "";
        
        //２）　@証券会社条件追加
        l_strQueryString += " institution_code = ? ";
        
        //３）　@部店条件追加
        String l_strBranchCodeCondition = "";
        int l_intBranchCodeCount = 0;
        if (l_request.branchCode != null)
        {
            l_intBranchCodeCount = l_request.branchCode.length;
        }
        
        if (l_intBranchCodeCount != 0)
        {
            l_strBranchCodeCondition += " and ( ";
            
            for (int i = 0; i < l_intBranchCodeCount; i++)
            {
                log.debug("３）　@部店条件追加");
                if ( i == 0)
                {
                    l_strBranchCodeCondition += " branch_code = ? ";
                }
                else
                {
                    l_strBranchCodeCondition += " or branch_code = ? ";
                }
            }
            
            l_strBranchCodeCondition += " ) ";
        }
        l_strQueryString += l_strBranchCodeCondition;
        
        //４）　@口座区分条件追加
        l_strQueryString += " and account_div = ? ";
        
        //５）　@顧客コード（自）条件追加 ※指定がある場合のみ
        if (l_request.accountCodeFrom != null)
        {
            log.debug("５）　@顧客コード（自）条件追加 ※指定がある場合のみ");
            l_strQueryString += " and account_code >= ? ";
        }
        
        //６）　@顧客コード（至）条件追加 ※指定がある場合のみ
        if (l_request.accountCodeTo != null)
        {
            log.debug("６）　@顧客コード（至）条件追加 ※指定がある場合のみ");
            l_strQueryString += " and account_code <= ? ";
        }
        
        //７）　@資料請求日（自）条件追加 ※指定がある場合のみ
        if (l_request.infoClaimDateFrom != null)
        {
            log.debug("７）　@資料請求日（自）条件追加 ※指定がある場合のみ");
            l_strQueryString += " and to_char(infomation_claim_datetime, 'YYYYMMDD') >= ? ";
        }

        //８）　@資料請求日（至）条件追加 ※指定がある場合のみ
        if (l_request.infoClaimDateTo != null)
        {
            log.debug("８）　@資料請求日（至）条件追加 ※指定がある場合のみ");
            l_strQueryString += " and to_char(infomation_claim_datetime, 'YYYYMMDD') <= ? ";
        }

        //９）　@口座開設日（自）条件追加 ※指定がある場合のみ
        if (l_request.accountOpenDateFrom != null)
        {
            log.debug("９）　@口座開設日（自）条件追加 ※指定がある場合のみ");
            l_strQueryString += " and to_char(account_open_date, 'YYYYMMDD') >= ? ";
        }

        //１０）　@口座開設日（至）条件追加 ※指定がある場合のみ
        if (l_request.accountOpenDateTo != null)
        {
            log.debug("１０）　@口座開設日（至）条件追加 ※指定がある場合のみ");
            l_strQueryString += " and to_char(account_open_date, 'YYYYMMDD') <= ? ";
        }

        //１１）　@識別コード（自）条件追加 ※指定がある場合のみ
        if (l_request.requestNumberFrom != null)
        {
            log.debug("１１）　@識別コード（自）条件追加 ※指定がある場合のみ");
            l_strQueryString += " and acc_open_request_number >= ? ";
        }

        //１２）　@識別コード（至）条件追加 ※指定がある場合のみ
        if (l_request.requestNumberTo != null)
        {
            log.debug("１２）　@識別コード（至）条件追加 ※指定がある場合のみ");
            l_strQueryString += " and acc_open_request_number <= ? ";
        }

        //１３） 口座開設区分条件追加※指定がある場合のみ
        if (l_request.accountOpenDiv != null)
        {
            // [リクエストデータ.口座開設区分が "口座開設済み"の場合］ 
            if(WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_request.accountOpenDiv))
            {
                log.debug("１３） 口座開設区分条件追加※指定がある場合のみ");
                log.debug("[リクエストデータ.口座開設区分が \"口座開設済み\"の場合］");
                l_strQueryString += " and account_open_date is not null ";
            }
            
            //［リクエストデータ.口座開設区分が "口座未開設"の場合］
            if(WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_request.accountOpenDiv))
            {
                log.debug("１３） 口座開設区分条件追加※指定がある場合のみ");
                log.debug("[リクエストデータ.口座開設区分が \"口座未開設\"の場合］"); 
                l_strQueryString += " and account_open_date is null ";                               
            }
        }


        //１４）　@検索オプション区分条件追加※指定がある場合のみ 
        if (l_request.searchOptionDiv != null)
        {
            log.debug("１４）　@検索オプション区分条件追加※指定がある場合のみ");  
            l_strQueryString += " and id_confirm_flag = ? ";                
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
     * 　@戻り値編集用インスタンスに、部店コード配列の要素数分部店コードを<BR>
     * 追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.部店コード[index]<BR>
     * <BR>
     * ４）　@口座区分条件追加<BR>
     * 　@戻り値編集用インスタンスに、口座区分を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.口座区分<BR>
     * <BR>
     * ５）　@顧客コード（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.顧客コード（自） != null）の場合、戻り値編集用<BR>
     * インスタンスに口座コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.顧客コード（自）<BR>
     * <BR>
     * ６）　@顧客コード（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.顧客コード（至） != null）の場合、戻り値編集用<BR>
     * インスタンスに口座コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.顧客コード（至）<BR>
     * <BR>
     * ７）　@資料請求日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.資料請求日（自） != null）の場合、戻り値編集用<BR>
     * インスタンスに資料請求日時を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.資料請求日（自）をYYYYMMDDに編集した文字列<BR>
     * <BR>
     * ８）　@資料請求日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.資料請求日（至） != null）の場合、戻り値編集用<BR>
     * インスタンスに資料請求日時を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.資料請求日（至）をYYYYMMDDに編集した文字列<BR>
     * <BR>
     * ９）　@口座開設日（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.口座開設日（自） != null）の場合、戻り値編集用<BR>
     * インスタンスに口座開設日を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.口座開設日（自）をYYYYMMDDに編集した文字列<BR>
     * <BR>
     * １０）　@口座開設日（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.口座開設日（至） != null）の場合、戻り値編集用<BR>
     * インスタンスに口座開設日を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.口座開設日（至）をYYYYMMDDに編集した文字列<BR>
     * <BR>
     * １１）　@識別コード（自）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.識別コード（自） != null）の場合、戻り値編集用<BR>
     * インスタンスに識別コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.識別コード（自）<BR>
     * <BR>
     * １２）　@識別コード（至）条件追加 ※指定がある場合のみ<BR>
     * 　@（リクエストデータ.識別コード（至） != null）の場合、戻り値編集用<BR>
     * インスタンスに識別コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@リクエストデータ.識別コード（至）<BR>
     * <BR>
     * １３）　@検索オプション区分条件追加※指定がある場合のみ <BR>
　@   *   （リクエストデータ.検索オプション区分 != null）の場合、<BR>
     *    戻り値編集用インスタンスに値を追加する。<BR> 
     * <BR>
　@   *    [add()に指定する引数] <BR>  
　@   *    リクエストデータ.検索オプション区分 <BR>
     * <BR> 
     * １４）　@配列を返却 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。 <BR>
     * @@param l_request - 管理者口座開設申込ダウンロードリクエストデータオブジェクト
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@return String[]
     * @@roseuid 41A1665F007F
     */
    protected String[] createQueryContainer(WEB3AdminAccOpenApplyDownloadRequest l_request, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " createQueryContainer(WEB3AdminAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //１）　@戻り値生成 
        ArrayList l_arrayList = new ArrayList();
        
        //２）　@証券会社条件追加
        l_arrayList.add(l_strInstitutionCode);
        
        //３）　@部店条件追加
        int l_intBranchCodeCount = 0;
        if (l_request.branchCode != null)
        {
            l_intBranchCodeCount = l_request.branchCode.length;
        }
        
        for (int i = 0; i < l_intBranchCodeCount; i++)
        {
            log.debug("３）　@部店条件追加");
            l_arrayList.add(l_request.branchCode[i]);
        }
        
        //４）　@口座区分条件追加
        l_arrayList.add(l_request.accountType);
        
        //５）　@顧客コード（自）条件追加 ※指定がある場合のみ
        if (l_request.accountCodeFrom != null)
        {
            log.debug("５）　@顧客コード（自）条件追加 ※指定がある場合のみ");
            l_arrayList.add(l_request.accountCodeFrom);
        }
        
        //６）　@顧客コード（至）条件追加 ※指定がある場合のみ
        if (l_request.accountCodeTo != null)
        {
            log.debug("//６）　@顧客コード（至）条件追加 ※指定がある場合のみ");
            l_arrayList.add(l_request.accountCodeTo);
        }
        
        //７）　@資料請求日（自）条件追加 ※指定がある場合のみ
        if (l_request.infoClaimDateFrom != null)
        {
            log.debug("７）　@資料請求日（自）条件追加 ※指定がある場合のみ");
            l_arrayList.add(WEB3DateUtility.formatDate(l_request.infoClaimDateFrom, "yyyyMMdd"));
        }

        //８）　@資料請求日（至）条件追加 ※指定がある場合のみ
        if (l_request.infoClaimDateTo != null)
        {
            log.debug("８）　@資料請求日（至）条件追加 ※指定がある場合のみ");
            l_arrayList.add(WEB3DateUtility.formatDate(l_request.infoClaimDateTo, "yyyyMMdd"));
        }

        //９）　@口座開設日（自）条件追加 ※指定がある場合のみ
        if (l_request.accountOpenDateFrom != null)
        {
            log.debug("９）　@口座開設日（自）条件追加 ※指定がある場合のみ");
            l_arrayList.add(WEB3DateUtility.formatDate(l_request.accountOpenDateFrom, "yyyyMMdd"));
        }

        //１０）　@口座開設日（至）条件追加 ※指定がある場合のみ
        if (l_request.accountOpenDateTo != null)
        {
            log.debug("１０）　@口座開設日（至）条件追加 ※指定がある場合のみ");
            l_arrayList.add(WEB3DateUtility.formatDate(l_request.accountOpenDateTo, "yyyyMMdd"));
        }

        //１１）　@識別コード（自）条件追加 ※指定がある場合のみ
        if (l_request.requestNumberFrom != null)
        {
            log.debug("１１）　@識別コード（自）条件追加 ※指定がある場合のみ");
            l_arrayList.add(l_request.requestNumberFrom);
        }

        //１２）　@識別コード（至）条件追加 ※指定がある場合のみ
        if (l_request.requestNumberTo != null)
        {
            log.debug("１２）　@識別コード（至）条件追加 ※指定がある場合のみ");
            l_arrayList.add(l_request.requestNumberTo);
        }

        //１３）　@検索オプション区分条件追加※指定がある場合のみ
        if (l_request.searchOptionDiv != null)
        {
            log.debug("１３）　@検索オプション区分条件追加※指定がある場合のみ");  
            l_arrayList.add(l_request.searchOptionDiv);          
        }    
        
        //１４）　@配列を返却
        String[] l_queryContainers = new String[l_arrayList.size()];
        l_arrayList.toArray(l_queryContainers); 
        
        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }
}
@
