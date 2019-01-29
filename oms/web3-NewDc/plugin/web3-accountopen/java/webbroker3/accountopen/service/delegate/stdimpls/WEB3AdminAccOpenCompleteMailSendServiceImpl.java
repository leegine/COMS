head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設完了メール送信サービスImpl(WEB3AdminAccOpenCompleteMailSendServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座開設完了メール送信サービスImpl)<BR>
 * 管理者口座開設完了メール送信サービス実装クラス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendServiceImpl implements WEB3AdminAccOpenCompleteMailSendService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendServiceImpl.class);

    /**
     * @@roseuid 41B45E7100FA
     */
    public WEB3AdminAccOpenCompleteMailSendServiceImpl() 
    {
     
    }
    
    /**
     * 口座開設完了メール送信処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設申込検索入力リクエストの場合 <BR>
     * 　@－getメール送信一覧()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設完了メール送信リクエストの場合 <BR>
     * 　@－submitメール()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A194C6036D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminAccOpenCompleteMailSendListRequest)
        {
            log.debug("WEB3AdminAccOpenCompleteMailSendListRequest");
            WEB3AdminAccOpenCompleteMailSendListResponse l_response = 
                getMailSendList((WEB3AdminAccOpenCompleteMailSendListRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenCompleteMailSendRequest)
        {
            log.debug("WEB3AdminAccOpenCompleteMailSendRequest");
            WEB3AdminAccOpenCompleteMailSendResponse l_response = 
                submitMail((WEB3AdminAccOpenCompleteMailSendRequest)l_request);
        
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
     * (getメール送信一覧)<BR>
     * 口座開設完了メール送信一覧表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（完了メール送信）getメール送信一覧」参照。 <BR>
     * @@param l_request - 管理者口座開設完了メール送信一覧リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A194C6038D
     */
    protected WEB3AdminAccOpenCompleteMailSendListResponse getMailSendList(WEB3AdminAccOpenCompleteMailSendListRequest l_request) throws WEB3BaseException 
    {       
        final String STR_METHOD_NAME = " getMailSendList(WEB3AdminAccOpenCompleteMailSendListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 is全部店許可( )
        boolean l_blnIsAuthority = l_admin.isAllBranchsPermission();
        
        //1.6 自部店のみ可の管理者の場合（is全部店許可() == false）
        
        String l_strBranchCode = null;
        if (!l_blnIsAuthority)
        {
            log.debug("is全部店許可() == false");
            //1.6.1 get部店コード( )
            l_strBranchCode = l_admin.getBranchCode();
        }
        
        log.debug("l_strInstitutionCode="+l_strInstitutionCode);
        log.debug("l_strBranchCode="+l_strBranchCode);
        
        //1.7メール(String, String, String)
        //証券会社コード：　@get証券会社コード() 
        //送信メール区分：　@送信メール区分.口座開設完了メール（0202） 
        //識別ID：　@”----” 
        new WEB3GentradeMailInfo(l_strInstitutionCode, 
            WEB3SendmailDivDef.ACCOPEN_COMPLETE_MAIL, "----");//WEB3BusinessLayerException,WEB3SystemLayerException
            
        //1.8 get口座開設完了顧客(String, String)
        WEB3GentradeMainAccount[] l_gentradeMainAccount= 
            this.getAccOpenCompletedMainAccounts(l_strInstitutionCode, l_strBranchCode);
        
        //1.9 createResponse( )
        WEB3AdminAccOpenCompleteMailSendListResponse l_response = 
            (WEB3AdminAccOpenCompleteMailSendListResponse)l_request.createResponse();
        
        //1.10 プロパティセット
        int l_intLength = l_gentradeMainAccount.length;
        
        log.debug("l_intLength=="+l_intLength);
        
        if (l_intLength == 0) {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[口座開設完了メール送信対象顧客]");
        }
        
        int l_dispLength = Integer.parseInt(l_request.dispSize);
        if (l_dispLength > l_intLength) {
            l_dispLength = l_intLength;
        }
        
        String[] l_strBranchCodes = new String[l_dispLength];
        String[] l_strAccountCodes = new String[l_dispLength];
        
        for (int i = 0; i < l_dispLength; i++)
        {
            l_strBranchCodes[i] = new String();
            l_strAccountCodes[i] = new String();
            
            l_strBranchCodes[i] = l_gentradeMainAccount[i].getBranch().getBranchCode();
            l_strAccountCodes[i] = l_gentradeMainAccount[i].getDisplayAccountCode();           
        }
        
        l_response.branchCode = l_strBranchCodes;
        l_response.accountCode = l_strAccountCodes;
        
        //総レコード数
        l_response.totalRecords = String.valueOf(l_intLength);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitメール)<BR>
     * 口座開設完了メール送信処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（完了メール送信）submitメール」参照。 <BR>
     * @@param l_request - 管理者口座開設完了メール送信リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A194C603AC
     */
    protected WEB3AdminAccOpenCompleteMailSendResponse submitMail(WEB3AdminAccOpenCompleteMailSendRequest l_request) throws WEB3BaseException 
    {      
        final String STR_METHOD_NAME = " submitMail(WEB3AdminAccOpenCompleteMailSendRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, true);
        
        //1.4 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.6 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7 メール(String, String, String)
        //証券会社コード：　@get証券会社コード() 
        //送信メール区分：　@送信メール区分.口座開設完了メール（0202） 
        //識別ID：　@”----” 
        new WEB3GentradeMailInfo(l_strInstitutionCode, 
            WEB3SendmailDivDef.ACCOPEN_COMPLETE_MAIL, "----");//WEB3BusinessLayerException,WEB3SystemLayerException
            
        //1.8 リクエストデータ.顧客コード[]各要素毎にLOOP処理実施。
        int l_accountCodeLength = l_request.accountCode.length;
        
        //拡張アカウントマネージャ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        for (int i = 0; i < l_accountCodeLength; i++)
        {                
            //1.8.1 get顧客(String, String, String) 
            String l_strBranchCode = l_request.branchCode[i];
            String l_strAccountCode = l_request.accountCode[i];
            
            WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
                
            //1.8.2 sendMailProcess(顧客)
            WEB3AdminAccOpenCompleteMailSendUnitService l_service = 
                (WEB3AdminAccOpenCompleteMailSendUnitService)Services.getService(
                    WEB3AdminAccOpenCompleteMailSendUnitService.class);
                    
            l_service.sendMailProcess(l_mainAccount);  
        }

        //1.9 createResponse( )
        WEB3AdminAccOpenCompleteMailSendResponse l_response = 
            (WEB3AdminAccOpenCompleteMailSendResponse)l_request.createResponse();
               
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get口座開設完了顧客)<BR>
     * 口座開設完了顧客を取得する。<BR>
     * <BR>
     * １）　@顧客マスタ検索<BR>
     * 　@以下の条件で顧客マスタを検索する。<BR>
     * <BR>
     * 　@顧客マスタ.証券会社コード = 証券会社コード And
     * 　@顧客マスタ.部店コード = 部店コード　@And　@（※）<BR>
     * 　@顧客マスタ.emailアドレス != null And<BR>
     * 　@顧客マスタ.口座開設完了メール送信ステータス = 0：未処理（Email未送信）<BR>
     * <BR>
     * [取得順] <BR>
     * 部店コード（昇順），　@口座コード（昇順） <BR>
     * <BR>
     * 　@（※）部店コード条件は、指定がある場合（部店コード != null）のみ、<BR>
     * 条件に追加する。<BR>
     * <BR>
     * ２）　@顧客List（：ArrayList）生成<BR>
     * 　@顧客List（：ArrayList）を生成する。<BR>
     * <BR>
     * ３）　@ログイン情報取得<BR>
     * 　@検索結果の各行オブジェクトについて、３－１）～３－５）を行う。<BR>
     * <BR>
     * 　@３－１）　@顧客オブジェクト生成<BR>
     * 　@　@処理対象行オブジェクトを指定して顧客オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@[コンストラクタの引数]<BR>
     * 　@　@顧客Row：　@対象要素<BR>
     * <BR>
     * 　@３－２）　@ログインＩＤ取得<BR>
     * 　@　@顧客.getログインＩＤ()にて、ログインＩＤを取得する。<BR>
     * 　@　@ログインＩＤが取得できなかった場合は、以降の処理を実施しない。（continue;）<BR>
     * <BR>
     * 　@３－３）　@ログイン属性取得<BR>
     * 　@　@OpLoginAdminService.getLoginAttributes()にて、 <BR>
     * 　@　@ログイン属性行.ログインＩＤに該当するログイン属性（：Map）を取得する。 <BR>
     * 　@　@ログイン属性が取得できなかった場合は、以降の処理を実施しない。（continue;）<BR>
     * <BR>
     * 　@３－４）　@初期パスワードチェック<BR>
     * 　@　@取得したログイン属性（：Map）より、以下の属性名に該当する属性値を取得する。<BR>
     * 　@　@ログイン属性値が取得できなかった場合は、以降の処理を実施しない。（continue);<BR>
     * <BR>
     * 　@　@復号化可能初期パスワード（WEB3_ENCRYPTED_INITIAL_PASSWORD）<BR>
     * <BR>
     * 　@３－５）　@顧客List（：ArrayList）に要素追加<BR>
     * 　@　@３－４）で取得した属性値が両方nullでない場合、<BR>
     * 顧客List（：ArrayList）に対象顧客オブジェクトを追加（add）する。<BR>
     * <BR>
     * ４）　@返却値取得<BR>
     * 　@顧客List（：ArrayList）.toArray()を返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@return webbroker3.gentrade.WEB3GentradeMainAccount[]
     * @@roseuid 41A53BAF01D6
     */
    protected WEB3GentradeMainAccount[] getAccOpenCompletedMainAccounts(String l_strInstitutionCode, String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccOpenCompletedMainAccounts(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）顧客マスタ検索
        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        
        if (l_strBranchCode != null && !"".equals(l_strBranchCode.trim()))
        {
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and branch_code = ? ");
            l_sbWhere.append(" and email_address IS NOT NULL "); 
            l_sbWhere.append(" and acc_open_send_mail_status = ? ");
            
            l_objWhere = new Object[] {l_strInstitutionCode, l_strBranchCode, WEB3EmailStatusDef.EMAIL_NOT_SEND};
            //l_sbWhere.append("where institution_code = " + l_strInstitutionCode + " and  branch_code = " + l_strBranchCode + " and email_address IS NOT null and acc_open_send_mail_status = " + WEB3EmailStatusDef.EMAIL_NOT_SEND+"");
        }
        else
        {
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and email_address IS NOT NULL "); 
            l_sbWhere.append(" and acc_open_send_mail_status = ? ");
            
            l_objWhere = new Object[] {l_strInstitutionCode, WEB3EmailStatusDef.EMAIL_NOT_SEND};
        }

        List l_lisRecords = null;
        try
        {
            log.debug(MainAccountRow.TYPE + l_sbWhere.toString());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_sbWhere.toString(),
                "branch_code asc, account_code asc",
                "",
                l_objWhere);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3AdminAccOpenCompleteMailSendServiceImpl.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminAccOpenCompleteMailSendServiceImpl.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3AdminAccOpenCompleteMailSendServiceImpl.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminAccOpenCompleteMailSendServiceImpl.class.getName() + STR_METHOD_NAME);
        }
        
        //２）顧客List（：ArrayList）生成
        ArrayList l_arrayListRecords = new ArrayList();
        
        //３）ログイン情報取得
        int l_intLength = l_lisRecords.size();
        log.debug("l_intLength==="+l_intLength);
        
        for (int i = 0; i < l_intLength; i++)
        {        
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRecords.get(i);
                
            //３－１）顧客オブジェクト生成
            WEB3GentradeMainAccount l_mainAccount = 
                new WEB3GentradeMainAccount(l_mainAccountRow);
            
            //３－２）ログインＩＤ取得
            long l_lngAccountLoninId;
            try
            {            
                l_lngAccountLoninId = l_mainAccount.getLoginId();//WEB3SystemLayerException
            }
            catch(WEB3SystemLayerException l_ex)
            {
                log.debug("ログインＩＤが取得できなかった場合は、以降の処理を実施しない。");
                continue;
            }
            
            //３－３）ログイン属性取得
            //OpLoginAdminService.getLoginAttributes()にて、
            //ログイン属性行.ログインＩＤに該当するログイン属性（：Map）を取得する。
            //ログイン属性が取得できなかった場合は、以降の処理を実施しない。（continue;）
           
            OpLoginAdminService l_service = (OpLoginAdminService) 
                Services.getService(OpLoginAdminService.class);
               
            Map l_map = l_service.getLoginAttributes(l_lngAccountLoninId);
            if (l_map == null || l_map.size() == 0)
            {
                log.debug("l_map == null");
                continue;
            }
           
            //３－４）初期パスワードチェック
            //取得したログイン属性（：Map）より、以下の属性名に該当する属性値を取得する。
            //WEB3_ENCRYPTED_INITIAL_PASSWORD

            Object l_obj = l_map.get(WEB3LoginAttributeKeyDef.INITIAL_PASSWORD);
            
            log.debug("Map size is " + l_map.size());
            
            if (l_obj == null)
            {
                log.debug("l_obj == null");
                continue;
            }
           
            //３－５）顧客List（：ArrayList）に要素追加
            //３－４）で取得した属性値が両方nullでない場合、
            //顧客List（：ArrayList）に対象顧客オブジェクトを追加（add）
            l_arrayListRecords.add(l_mainAccount);                
        }
        
        //４）返却値取得
        WEB3GentradeMainAccount[] l_mainAccountNew = new WEB3GentradeMainAccount[l_arrayListRecords.size()];
        l_arrayListRecords.toArray(l_mainAccountNew);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_mainAccountNew;
    }
}
@
