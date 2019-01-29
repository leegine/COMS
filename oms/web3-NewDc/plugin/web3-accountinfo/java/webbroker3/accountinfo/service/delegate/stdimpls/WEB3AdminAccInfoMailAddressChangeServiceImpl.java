head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレス変更サービスImpl(WEB3AdminAccInfoMailAddressChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
                 : 2006/05/19 周捷 (中訊) 仕様変更・モデル104
                 : 2006/05/22 周捷 (中訊) 仕様変更・モデル105
                 : 2006/05/23 周捷 (中訊) 仕様変更・モデル106、107
                 : 2006/06/12 猪俣（SCS）　@仕様変更・モデル109
Revesion History : 2007/08/28 謝旋（中訊）ＤＢ更新仕様・モデル049
Revesion History : 2007/08/28 武波 (中訊) 仕様変更管理No.218
Revesion History : 2007/08/30 武波 (中訊) 仕様変更管理No.221
Revesion History : 2010/02/22 武波 (中訊) 仕様変更管理モデルNo.260,No.265,No.269,No.270,No.273,No.274 ＤＢ更新仕様No.061,No062
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressUpdateInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3AddressDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3DuplicationMailAddressCheckDef;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMailAddressDuplicationCheck;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMobileMailAddressCheck;
import webbroker3.gentrade.data.AccountMailAddressPK;
import webbroker3.gentrade.data.AccountMailAddressParams;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (管理者お客様情報メールアドレス変更サービスImpl)<BR>
 * 管理者お客様情報メールアドレス変更サービス実装クラス<BR>
 * 
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeServiceImpl 
    extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMailAddressChangeService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeServiceImpl.class);
    /**
     * (部店コードキー名)<BR>
     * 部店コードキー名<BR>
     */
    private static String KEY_BRANCH_CODE = "branch_code";

    /**
     * (顧客コードキー名)<BR>
     * 顧客コードキー名<BR>
     */
    private static String KEY_ACCOUNT_CODE = "account_code";
    /**
     * @@roseuid 418F3A02037A
     */
    public WEB3AdminAccInfoMailAddressChangeServiceImpl() 
    {
     
    }
    
    /**
     * メールアドレス変更処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報メールアドレス変更<BR>
     * 入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報メールアドレス変更<BR>
     * 確認リクエストの場合 <BR>
     * 　@－validate変更()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報メールアドレス変更<BR>
     * 完了リクエストの場合 <BR>
     * 　@－submit変更()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F9B503E3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAccInfoMailAddressChangeInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminAccInfoMailAddressChangeInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressChangeConfirmRequest)
        {           
            l_response = this.validateChange((WEB3AdminAccInfoMailAddressChangeConfirmRequest)l_request) ;
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressChangeCompleteRequest)
        {           
            l_response = this.submitChange((WEB3AdminAccInfoMailAddressChangeCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME
                );            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * メールアドレス変更入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者お客様情報（メールアドレス変更）get入力画面」参照。<BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputResponse
     * @@roseuid 416662610376
     */
    protected WEB3AdminAccInfoMailAddressChangeInputResponse getInputScreen(WEB3AdminAccInfoMailAddressChangeInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminAccInfoMailAddressChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5 get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_accountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //1.6 validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        //get顧客メールアドレス
        //証券会社コード：　@get証券会社コード
        //部店コード：　@リクエストデータ.部店コード
        //口座コード：　@リクエストデータ.顧客コード
        List l_lisAccountMailAddresss =
            this.getAccountMailAddress(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);

        List l_lisAccInfoMailAddressUpdateInfo = new ArrayList();
        //get顧客メールアドレス()戻り値の要素数分LOOP処理
        Iterator l_itAccountMailAddresss = l_lisAccountMailAddresss.iterator();
        while (l_itAccountMailAddresss.hasNext())
        {
            AccountMailAddressRow l_accountMailAddressRow =
                (AccountMailAddressRow)l_itAccountMailAddresss.next();
            //createメールアドレス変更情報
            //顧客メールアドレス行 ： get顧客メールアドレス()にて取得した顧客メールアドレスParams
            WEB3AccInfoMailAddressUpdateInfo l_accInfoMailAddressUpdateInfo =
                this.createMailAddressUpdateInfo(l_accountMailAddressRow);
            l_lisAccInfoMailAddressUpdateInfo.add(l_accInfoMailAddressUpdateInfo);
        }
        //1.7 createResponse()
        WEB3AdminAccInfoMailAddressChangeInputResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeInputResponse) l_request.createResponse();
        if (l_lisAccountMailAddresss.isEmpty())
        {
            l_response.mailAddressUpdateInfo = null;
        }
        else
        {
            WEB3AccInfoMailAddressUpdateInfo[] l_accInfoMailAddressUpdateInfo =
                new WEB3AccInfoMailAddressUpdateInfo[l_lisAccInfoMailAddressUpdateInfo.size()];
            l_lisAccInfoMailAddressUpdateInfo.toArray(l_accInfoMailAddressUpdateInfo);
            l_response.mailAddressUpdateInfo = l_accInfoMailAddressUpdateInfo;
        }
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (validate変更)<BR>
     * メールアドレス変更確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者お客様情報（メールアドレス変更）validate変更」参照。<BR>
     * <BR> 
     * ===============================================<BR>
     *          シーケンス図 :  管理者お客様情報（メールアドレス変更）validate変更<BR>
     *          具体位置     :  1.7  isMailAddress(String)<BR>
     *          メールアドレスとして適切でない場合、例外をスローする。<BR>
     *          （isMailAddress() == false）<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_00777<BR>
     * ================================================<BR> 
     * ================================================<BR>
     *          シーケンス図 :  管理者お客様情報（メールアドレス変更）validate変更<BR>
     *          具体位置     :  1.9.2 重複メールアドレスが存在した場合（get重複アドレス()戻り値の長さ>0）、<BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@条件により例外をスローする。<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02444<BR> 
     * ================================================<BR>
     * <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F9B503E5
     */
    protected WEB3AdminAccInfoMailAddressChangeConfirmResponse 
        validateChange(WEB3AdminAccInfoMailAddressChangeConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminAccInfoMailAddressChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5 get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradMainAccount = l_accountManager.getMainAccount(
            l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        
        //1.6 validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.7 isMailAddress(String)
        if (l_request.changedMailAddress != null)
        {
            if (!WEB3StringTypeUtility.isMailAddress(l_request.changedMailAddress))
            {
                //1.8 メールアドレスとして適切でない場合（isMailAddress() == false）、例外をスローする。
                log.exiting(STR_METHOD_NAME);
                log.error("メールアドレスとして適切でない場合、例外をスロー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, 
                    getClass().getName() + " " + "validateChange");
            }
        }

        //validate複数メールアドレス
        WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo =
            l_request.multiMailAddressInfo;
        if (l_accInfoMultiMailAddressInfo != null)
        {
            l_accInfoMultiMailAddressInfo.validateMultiMailAddressInfo();
        }

        //get複数メールアドレス対応実施
        //部店コード：get顧客().getBranch().getBranchCode()の戻り値
        //証券会社コード：get顧客().getInstitution().getInstitutionCode()の戻り値
        //プリファ@レンス名： "accountinfo.multi.mailaddress.flag"
        //プリファ@レンス名の連番： 1
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_gentradMainAccount.getBranch();
        int l_intSerialNo = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);
        String l_strBranchPreferences = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_gentradeBranch.getBranchCode(),
            l_gentradMainAccount.getInstitution().getInstitutionCode(),
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            l_intSerialNo);

        //複数メールアドレス対応実施（1.7 get複数メールアドレス対応実施() == "2"）の場合
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
        {
            //リクエストデータ.メールアドレス情報の要素数分Loop処理を行う。
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.mailAddressInfoList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }

            for (int i = 0; i < l_intLength; i++)
            {
                l_accInfoMailAddressInfoUnits[i].validateMailAddressInfo();

                //リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス区分　@== "1"
                if (WEB3AddressDivDef.PC_MAIL_ADDRESS.equals(
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv)
                    && l_accInfoMailAddressInfoUnits[i].mailAddress != null)
                {
                    //validatePCメールアドレス(メールアドレス : String)
                    //メールアドレス： リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス
                    WEB3GentradeMobileMailAddressCheck.validatePCMailAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress);
                }
                //リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス区分　@== "2"
                //　@且つ　@リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス != null）の場合
                if (WEB3AddressDivDef.MOBILE_MAIL_ADDRESS.equals(
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv)
                    && l_accInfoMailAddressInfoUnits[i].mailAddress != null)
                {
                    //validate携帯メールアドレス
                    //メールアドレス： リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス
                    try
                    {
                        WEB3GentradeMobileMailAddressCheck.validateMobileAddress(
                            l_accInfoMailAddressInfoUnits[i].mailAddress);

                        log.debug("入力されたメールアドレスは携帯メールアドレスではありません。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03189,
                            WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                            "入力されたメールアドレスは携帯メールアドレスではありません。");
                    }
                    catch(WEB3BaseException l_ex)
                    {
                        if (WEB3ErrorCatalog.BUSINESS_ERROR_03168 != l_ex.getErrorInfo())
                        {
                            log.error(l_ex.getErrorMessage(), l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                l_ex.getErrorInfo(),
                                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                    }
                }
            }
        }

        String[] l_strDuplicationAccounts = null;
        String[] l_strDuplicationAddressInfos = null;
        StringBuffer l_strDuplicationAccount = new StringBuffer();
        Object[] l_objDuplicationCheck = null;
        int l_intDuplicationCheckLenth = 0;
        
        //1.9 get重複チェック実行フラグ(long, String, int)
        // 重複メールチェックの対象かどうかを判別する。 
        // ・部店ID：get顧客().getBranch().getBranchId()の戻り値 
        // ・プリファ@レンス名：WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO（部店プリファ@レンステーブル仕様>シート
        //  「値設定仕様」参照） 
        // ・連番：プリファ@レンス名の連番 
        int l_intDuplicateCheckExecFlag = 
            this.getDuplicateCheckExecFlag(
                l_gentradMainAccount.getBranch().getBranchId(), 
                WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO, 
                1);
        
        //分岐フロー。
        //重複メールアドレスチェック対象（get重複チェック実行フラグ() != 0）である場合、重複アドレス検索を行う。 
        if (l_intDuplicateCheckExecFlag != 0)
        {
            //リクエストデータ.メールアドレス情報の要素数分Loop処理を行う。
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.mailAddressInfoList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }
            List l_lisDuplicationChecks = new ArrayList();
            for (int i = 0; i < l_intLength; i++)
            {
                //get重複アドレス(String, String, String, String)
                //・メールアドレス： リクエストデータ.メールアドレス情報.メールアドレス
                //・顧客コード：get顧客().getAccountCode()の戻り値
                //・部店コード：get顧客().getBranch().getBranchCode()の戻り値
                //・証券会社コード：get顧客().getInstitution().getInstitutionCode()の戻り値
                if (l_accInfoMailAddressInfoUnits[i].mailAddress == null)
                {
                    continue;
                }
                l_objDuplicationCheck =
                    WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress,
                        l_gentradMainAccount.getAccountCode(),
                        l_gentradMainAccount.getBranch().getBranchCode(),
                        l_gentradMainAccount.getInstitution().getInstitutionCode());

                if (l_objDuplicationCheck != null)
                {
                    l_intDuplicationCheckLenth = l_objDuplicationCheck.length;
                }
                for (int j = 0; j < l_intDuplicationCheckLenth; j++)
                {
                    if (!(l_lisDuplicationChecks.contains(l_objDuplicationCheck[j])))
                    {
                        l_lisDuplicationChecks.add(l_objDuplicationCheck[j]);
                    }
                }
            }
            int l_intSize = l_lisDuplicationChecks.size();
            for (int i = l_intSize - 1; i >= 0; i--)
            {
                for (int j = 0; j < i; j ++)
                {
                    HashMap l_hmDuplicationCheckFor = (HashMap)l_lisDuplicationChecks.get(j);
                    String l_strBranchCodeFor = (String)l_hmDuplicationCheckFor.get(KEY_BRANCH_CODE);
                    String l_strAccountCodeFor = (String)l_hmDuplicationCheckFor.get(KEY_ACCOUNT_CODE);
                    HashMap l_hmDuplicationCheckUp = (HashMap)l_lisDuplicationChecks.get(j + 1);
                    String l_strBranchCodeUp = (String)l_hmDuplicationCheckUp.get(KEY_BRANCH_CODE);
                    String l_strAccountCodeUp = (String)l_hmDuplicationCheckUp.get(KEY_ACCOUNT_CODE);
                    if (Long.parseLong(l_strBranchCodeFor) > Long.parseLong(l_strBranchCodeUp))
                    {
                        l_lisDuplicationChecks.set(j, l_hmDuplicationCheckUp);
                        l_lisDuplicationChecks.set(j + 1, l_hmDuplicationCheckFor);
                    }
                    else if (Long.parseLong(l_strBranchCodeFor) == Long.parseLong(l_strBranchCodeUp))
                    {
                        if (Long.parseLong(l_strAccountCodeFor) > Long.parseLong(l_strAccountCodeUp))
                        {
                            l_lisDuplicationChecks.set(j, l_hmDuplicationCheckUp);
                            l_lisDuplicationChecks.set(j + 1, l_hmDuplicationCheckFor);
                        }
                    }
                }
            }
            l_objDuplicationCheck = new Object[l_lisDuplicationChecks.size()];
            l_lisDuplicationChecks.toArray(l_objDuplicationCheck);
            l_intDuplicationCheckLenth = 0;
            if (l_objDuplicationCheck != null)
            {
                l_intDuplicationCheckLenth = l_objDuplicationCheck.length;
            }
            l_strDuplicationAccounts = new String[l_intDuplicationCheckLenth];
            l_strDuplicationAddressInfos = new String[l_intDuplicationCheckLenth];
            for (int i = 0; i < l_intDuplicationCheckLenth; i++)
            {
//				コーディングミスの為変更***2006.06.12 SCS Inomata-->
//                l_strDuplicationAccounts[i] = 
//                    "[" + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + ":" + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]) + "]";
//                
//                l_strDuplicationAddressInfos[i] =
//                    WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + "," + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]);
//                
				l_strDuplicationAccounts[i] = 
					"\n[部店 " + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "：顧客コード " + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]) + "]";
				l_strDuplicationAddressInfos[i] =
					WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "," + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]);
//				<--コーディングミスの為変更***2006.06.12 SCS Inomata
                l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
            }
            //1.9.2 重複メールアドレスが存在した場合（get重複アドレス()戻り値の長さ>0）、条件により例外をスローする。
            //分岐フロー。
            //重複メールアドレスが存在した場合（get重複アドレス()戻り値長さ>0）、以下条件時に例外をスローする。
            //「重複メールアドレスチェック対象」(get重複チェック実行フラグ() == 2)&& 
            //「重複メールアドレスが存在する」（get重複アドレス()戻り値の長さ>0）
            if (l_intDuplicateCheckExecFlag == 2 && l_intDuplicationCheckLenth > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02444, 
                    getClass().getName() + " " + "validateChange", 
                    l_strDuplicationAccount.toString());
            }
        }
        //1.10 createResponse()
        WEB3AdminAccInfoMailAddressChangeConfirmResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeConfirmResponse) l_request.createResponse();
        
        if (l_intDuplicationCheckLenth > 0 && l_intDuplicateCheckExecFlag == 1)
        {
            l_response.duplicationAddressInfo = l_strDuplicationAddressInfos ;
        }
        
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (submit変更)<BR>
     * メールアドレス変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者お客様情報（メールアドレス変更）submit変更」参照。<BR>
     * <BR>
     *  ===============================================<BR>
     *          シーケンス図 :  管理者お客様情報（メールアドレス変更）submit変更<BR>
     *          具体位置     :  1.8  isMailAddress(String)<BR>
     *          メールアドレスとして適切でない場合、例外をスローする。<BR>
     *          （isMailAddress() == false）<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_00777<BR> 
     * ================================================<BR>
     * ================================================<BR>
     *          シーケンス図 :  管理者お客様情報（メールアドレス変更）submit変更<BR>
     *          具体位置     :  1.11.2重複メールアドレスが存在した場合（get重複アドレス()戻り値長さ>0）、<BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@条件により例外をスローする。<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02444<BR> 
     * ================================================<BR>
     * <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F9B503E7
     */
    protected WEB3AdminAccInfoMailAddressChangeCompleteResponse submitChange(
        WEB3AdminAccInfoMailAddressChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminAccInfoMailAddressChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.4 validate取引パスワード(String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6 get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount = 
            l_accountManager.getMainAccount(
                l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
            
        //1.7 validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.8 isMailAddress(String)
        if (l_request.changedMailAddress != null)
        {
            if (!WEB3StringTypeUtility.isMailAddress(l_request.changedMailAddress))
            {
                //1.9 (*1) メールアドレスとして適切でない場合（isMailAddress() == false）、例外をスローする。
                log.exiting(STR_METHOD_NAME);
                log.debug("メールアドレスとして適切でない場合、例外をスロー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, getClass().getName() + " " + "submitChange");
            }            
        }      

        //validate複数メールアドレス
        WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo =
            l_request.multiMailAddressInfo;
        if (l_accInfoMultiMailAddressInfo != null)
        {
            l_accInfoMultiMailAddressInfo.validateMultiMailAddressInfo();
        }

        //get複数メールアドレス対応実施
        //部店コード：get顧客().getBranch().getBranchCode()の戻り値
        //証券会社コード：get顧客().getInstitution().getInstitutionCode()の戻り値
        //プリファ@レンス名： "accountinfo.multi.mailaddress.flag"
        //プリファ@レンス名の連番： 1
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_gentradeMainAccount.getBranch();
        int l_intSerialNo = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);
        String l_strBranchPreferences = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_gentradeBranch.getBranchCode(),
            l_gentradeMainAccount.getInstitution().getInstitutionCode(),
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            l_intSerialNo);

        //複数メールアドレス対応実施（1.7 get複数メールアドレス対応実施() == "2"）の場合
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
        {
            //リクエストデータ.メールアドレス情報の要素数分Loop処理を行う。
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.mailAddressInfoList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }

            for (int i = 0; i < l_intLength; i++)
            {
                l_accInfoMailAddressInfoUnits[i].validateMailAddressInfo();

                //リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス区分　@== "1"
                if (WEB3AddressDivDef.PC_MAIL_ADDRESS.equals(
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv)
                    && l_accInfoMailAddressInfoUnits[i].mailAddress != null)
                {
                    //validatePCメールアドレス(メールアドレス : String)
                    //メールアドレス： リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス
                    WEB3GentradeMobileMailAddressCheck.validatePCMailAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress);
                }
                //リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス区分　@== "2"
                //　@且つ　@リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス != null）の場合
                if (WEB3AddressDivDef.MOBILE_MAIL_ADDRESS.equals(
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv)
                    && l_accInfoMailAddressInfoUnits[i].mailAddress != null)
                {
                    //validate携帯メールアドレス
                    //メールアドレス： リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス
                    try
                    {
                        WEB3GentradeMobileMailAddressCheck.validateMobileAddress(
                            l_accInfoMailAddressInfoUnits[i].mailAddress);

                        log.debug("入力されたメールアドレスは携帯メールアドレスではありません。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03189,
                            WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                            "入力されたメールアドレスは携帯メールアドレスではありません。");
                    }
                    catch(WEB3BaseException l_ex)
                    {
                        if (WEB3ErrorCatalog.BUSINESS_ERROR_03168 != l_ex.getErrorInfo())
                        {
                            log.error(l_ex.getErrorMessage(), l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                l_ex.getErrorInfo(),
                                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                    }
                }
            }
        }

        String[] l_strDuplicationAccounts = null;
        String[] l_strDuplicationAddressInfos = null;
        StringBuffer l_strDuplicationAccount = new StringBuffer();
        Object[] l_objDuplicationCheck = null;
        int l_intDuplicationCheckLenth = 0;
        
        //1.10 get重複チェック実行フラグ(long, String, int)
        // 重複メールチェックの対象かどうかを判別する。 
        // ・部店ID：get顧客().getBranch().getBranchId()の戻り値 
        // ・プリファ@レンス名：WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO（部店プリファ@レンステーブル仕様>シート
        //  「値設定仕様」参照） 
        // ・連番：プリファ@レンス名の連番 
        int l_intDuplicateCheckExecFlag = 
            this.getDuplicateCheckExecFlag(
                l_gentradeMainAccount.getBranch().getBranchId(), 
                WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO, 
                1);
        
        //分岐フロー。
        //重複メールアドレスチェック対象（get重複チェック実行フラグ() != 0）である場合、重複アドレス検索を行う。 
        if (l_intDuplicateCheckExecFlag != 0)
        {
            //リクエストデータ.メールアドレス情報の要素数分Loop処理を行う。
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.mailAddressInfoList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }
            List l_lisDuplicationChecks = new ArrayList();
            for (int i = 0; i < l_intLength; i++)
            {
                //get重複アドレス(String, String, String, String)
                //・メールアドレス： リクエストデータ.メールアドレス情報.メールアドレス
                //・顧客コード：get顧客().getAccountCode()の戻り値
                //・部店コード：get顧客().getBranch().getBranchCode()の戻り値
                //・証券会社コード：get顧客().getInstitution().getInstitutionCode()の戻り値
                if (l_accInfoMailAddressInfoUnits[i].mailAddress == null)
                {
                    continue;
                }
                l_objDuplicationCheck =
                    WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress,
                        l_gentradeMainAccount.getAccountCode(),
                        l_gentradeMainAccount.getBranch().getBranchCode(),
                        l_gentradeMainAccount.getInstitution().getInstitutionCode());
                if (l_objDuplicationCheck != null)
                {
                    l_intDuplicationCheckLenth = l_objDuplicationCheck.length;
                }
                for (int j = 0; j < l_intDuplicationCheckLenth; j++)
                {
                    if (!(l_lisDuplicationChecks.contains(l_objDuplicationCheck[j])))
                    {
                        l_lisDuplicationChecks.add(l_objDuplicationCheck[j]);
                    }
                }
            }
            int l_intSize = l_lisDuplicationChecks.size();
            for (int i = l_intSize - 1; i >= 0; i--)
            {
                for (int j = 0; j < i; j ++)
                {
                    HashMap l_hmDuplicationCheckFor = (HashMap)l_lisDuplicationChecks.get(j);
                    String l_strBranchCodeFor = (String)l_hmDuplicationCheckFor.get(KEY_BRANCH_CODE);
                    String l_strAccountCodeFor = (String)l_hmDuplicationCheckFor.get(KEY_ACCOUNT_CODE);
                    HashMap l_hmDuplicationCheckUp = (HashMap)l_lisDuplicationChecks.get(j + 1);
                    String l_strBranchCodeUp = (String)l_hmDuplicationCheckUp.get(KEY_BRANCH_CODE);
                    String l_strAccountCodeUp = (String)l_hmDuplicationCheckUp.get(KEY_ACCOUNT_CODE);
                    if (Long.parseLong(l_strBranchCodeFor) > Long.parseLong(l_strBranchCodeUp))
                    {
                        l_lisDuplicationChecks.set(j, l_hmDuplicationCheckUp);
                        l_lisDuplicationChecks.set(j + 1, l_hmDuplicationCheckFor);
                    }
                    else if (Long.parseLong(l_strBranchCodeFor) == Long.parseLong(l_strBranchCodeUp))
                    {
                        if (Long.parseLong(l_strAccountCodeFor) > Long.parseLong(l_strAccountCodeUp))
                        {
                            l_lisDuplicationChecks.set(j, l_hmDuplicationCheckUp);
                            l_lisDuplicationChecks.set(j + 1, l_hmDuplicationCheckFor);
                        }
                    }
                }
            }
            l_objDuplicationCheck = new Object[l_lisDuplicationChecks.size()];
            l_lisDuplicationChecks.toArray(l_objDuplicationCheck);
            l_intDuplicationCheckLenth = 0;
            if (l_objDuplicationCheck != null)
            {
                l_intDuplicationCheckLenth = l_objDuplicationCheck.length;
            }
            
            l_strDuplicationAccounts = new String[l_intDuplicationCheckLenth];
            l_strDuplicationAddressInfos = new String[l_intDuplicationCheckLenth];
            
            for (int i = 0; i < l_intDuplicationCheckLenth; i++)
            {
//				コーディングミスの為変更***2006.06.12 SCS Inomata-->
//                l_strDuplicationAccounts[i] = 
//                    "[" + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + ":" + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]) + "]";
//                
//                l_strDuplicationAddressInfos[i] =
//                    WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + "," + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]);
//                
				l_strDuplicationAccounts[i] = 
					"\n[部店 " + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "：顧客コード " + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]) + "]";
				l_strDuplicationAddressInfos[i] =
					WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "," + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]);
//				<--コーディングミスの為変更***2006.06.12 SCS Inomata
                l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
            }
            
            //1.10.2 重複メールアドレスが存在した場合（get重複アドレス()戻り値の長さ>0）、条件により例外をスローする。
            //分岐フロー。
            //重複メールアドレスが存在した場合（get重複アドレス()戻り値長さ>0）、以下条件時に例外をスローする。
            //「重複メールアドレスチェック対象」(get重複チェック実行フラグ() == 2)&& 
            //「重複メールアドレスが存在する」（get重複アドレス()戻り値の長さ>0）
            
            if (l_intDuplicateCheckExecFlag == 2 && l_intDuplicationCheckLenth > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02444, 
                    getClass().getName() + " " + "submitChange", 
                    l_strDuplicationAccount.toString());
            }
        }
               
        if (l_intDuplicateCheckExecFlag == 0 
            || (l_intDuplicateCheckExecFlag != 0 && l_intDuplicationCheckLenth == 0) 
            || (l_intDuplicateCheckExecFlag == 1 && l_intDuplicationCheckLenth > 0))
        {
            //1.11 getDataSourceObject( )
            MainAccountParams l_mainAccountParams = 
                new MainAccountParams((MainAccountRow)l_gentradeMainAccount.getDataSourceObject());
            
            //1.12 doUpdateQuery(arg0 : PrimaryKey, arg1(*2) : Map)
            l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            if (l_request.mailAddressDelFlag)
            {
                l_mainAccountParams.setEmailAddress(null);

                //emailアドレス更新者コード
                //管理者テーブル.管理者コード
                l_mainAccountParams.setEmailLastUpdater(l_administrator.getAdministratorCode());
                //emailアドレス更新日時
                //処理日時　@※TradingSystem.getSystemTimestamp()
                l_mainAccountParams.setEmailLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            }
            else if (l_request.changedMailAddress != null)
            {
                if (!l_request.changedMailAddress.equals(l_mainAccountParams.getEmailAddress()))
                {
                    //emailアドレス更新者コード
                    //管理者テーブル.管理者コード
                    l_mainAccountParams.setEmailLastUpdater(l_administrator.getAdministratorCode());
                    //emailアドレス更新日時
                    //処理日時　@※TradingSystem.getSystemTimestamp()
                    l_mainAccountParams.setEmailLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
                }

                l_mainAccountParams.setEmailAddress(l_request.changedMailAddress);
            }
            if (l_request.accountOpenMailFlag)
            {
                l_mainAccountParams.setAccOpenSendMailStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
            }
            else
            {
                l_mainAccountParams.setAccOpenSendMailStatus(WEB3EmailStatusDef.EMAIL_SEND_END);
            }
            
            //案内メール送信フラグ
            //get複数メールアドレス対応実施の戻り値 != 2 且つ 案内メール送信フラグの変更が発生した場合
            boolean l_blnInformationMailFlag = true;
            //顧客マスタ.案内メール送信フラグがfalseの場合、falseをセット
            if (BooleanEnum.FALSE.equals(l_mainAccountParams.getInformationMailFlag()))
            {
                l_blnInformationMailFlag = false;
            }

            //※リクエスト.案内メール送信フラグ != 顧客マスタ.案内メール送信フラグ
            if ((!WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
                && l_request.guideMailFlag != l_blnInformationMailFlag)
            {
                //案内メール送信フラグを、既存値と逆の値で更新（DB Update）更新する
                if (!l_blnInformationMailFlag)
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
                }
                else
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
                }

                //案内メール送信フラグ更新者コード = 管理者テーブル.管理者コード
                l_mainAccountParams.setInfMailFlgLastUpdater(l_administrator.getAdministratorCode());

                //案内メール送信フラグ更新日時 = 処理日時　@※TradingSystem.getSystemTimestamp() 
                l_mainAccountParams.setInfMailFlgUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }

            //（リクエストデータ.複数アドレス情報.メールアドレス２削除フラグ == true）の場合、null。
            if (l_request.multiMailAddressInfo.mailAddressDelFlag2)
            {
                l_mainAccountParams.setEmailAddressAlt1(null);
            }
            else
            {
                //（リクエストデータ.複数アドレス情報.メールアドレス２削除フラグ == false）の場合、
                //リクエストデータ.複数アドレス情報.メールアドレス２。
                l_mainAccountParams.setEmailAddressAlt1(l_request.multiMailAddressInfo.mailAddress2);
            }

            //（リクエストデータ.複数アドレス情報.メールアドレス３削除フラグ == true）の場合、null。
            if (l_request.multiMailAddressInfo.mailAddressDelFlag3)
            {
                l_mainAccountParams.setEmailAddressAlt2(null);
            }
            else
            {
                //（リクエストデータ.複数アドレス情報.メールアドレス３削除フラグ == false)の場合、
                //リクエストデータ.複数アドレス情報.メールアドレス３。
                l_mainAccountParams.setEmailAddressAlt2(l_request.multiMailAddressInfo.mailAddress3);
            }

            try 
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_mainAccountParams);
            } 
            catch (DataFindException l_ex) 
            { 
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  
            catch (DataQueryException l_ex) 
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataNetworkException l_ex) 
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
        if (l_request.mailAddressInfoList != null)
        {
            l_accInfoMailAddressInfoUnits =
                l_request.mailAddressInfoList;
        }
        int l_intLength = 0;
        if (l_accInfoMailAddressInfoUnits != null)
        {
            l_intLength = l_accInfoMailAddressInfoUnits.length;
        }

        for (int i = 0; i < l_intLength; i++)
        {
            if (l_accInfoMailAddressInfoUnits[i] != null)
            {
                this.updateAccountMailAddress(
                    l_gentradeMainAccount, l_accInfoMailAddressInfoUnits[i]);
            }
        }

        //get顧客メールアドレス
        List l_lisAccountMailAddresss = this.getAccountMailAddress(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        List l_lisAccInfoMailAddressUpdateInfo = new ArrayList();
        //get顧客メールアドレス()戻り値の要素数分LOOP処理
        Iterator l_itAccountMailAddresss = l_lisAccountMailAddresss.iterator();
        while (l_itAccountMailAddresss.hasNext())
        {
            AccountMailAddressRow l_accountMailAddressRow =
                (AccountMailAddressRow)l_itAccountMailAddresss.next();
            //createメールアドレス変更情報
            //顧客メールアドレス行 ： get顧客メールアドレス()にて取得した顧客メールアドレスParams
            WEB3AccInfoMailAddressUpdateInfo l_accInfoMailAddressUpdateInfo =
                this.createMailAddressUpdateInfo(l_accountMailAddressRow);
            l_lisAccInfoMailAddressUpdateInfo.add(l_accInfoMailAddressUpdateInfo);
        }
        
        //1.13 createResponse()
        WEB3AdminAccInfoMailAddressChangeCompleteResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeCompleteResponse) l_request.createResponse();
        
        if(l_intDuplicationCheckLenth > 0 && l_intDuplicateCheckExecFlag  == 1)
        {
            l_response.duplicationAddressInfo = l_strDuplicationAddressInfos;
        }
        WEB3AccInfoMailAddressUpdateInfo[] l_accInfoMailAddressUpdateInfo =
            new WEB3AccInfoMailAddressUpdateInfo[l_lisAccInfoMailAddressUpdateInfo.size()];
        l_lisAccInfoMailAddressUpdateInfo.toArray(l_accInfoMailAddressUpdateInfo);
        l_response.mailAddressUpdateInfo = l_accInfoMailAddressUpdateInfo;
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (get重複チェック実行フラグ)<BR>
     * 部店プリファ@レンスを参照し、重複メールチェックの対象かどうかを判別する。<BR>
     * <BR>
     * １）以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR> 
     * 　@[条件] <BR>
     * 　@部店ID = 引数：部店ID <BR>
     * 　@プリファ@レンス名 = 引数：プリファ@レンス名 <BR>
     * 　@プリファ@レンス名の連番 = 引数：プリファ@レンス名の連番 <BR>
     * <BR>             
     * ２）取得したレコード.プリファ@レンスの値を返却する。 <BR>
     * <BR>             
     * 　@レコード.プリファ@レンスの値： "0" OR ""　@⇒ 0 <BR>
     * 　@レコード.プリファ@レンスの値： "1" OR "3" ⇒ 1 <BR>
     * 　@レコード.プリファ@レンスの値： "2" OR "4" ⇒ 2 <BR>
     * 　@※レコードが取得できなかった場合 ⇒ 0<BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID。<BR>
     * @@param l_strName - (プリファ@レンス名)<BR>
     * プリファ@レンス名。<BR>
     * @@param l_strNameSerialNo - (プリファ@レンス名の連番)<BR>
     * プリファ@レンス名の連番。<BR>
     * @@return int
     * @@throws WEB3BaseException
     */
    protected int getDuplicateCheckExecFlag(
        long l_lngBranchId, String l_strName, int l_strNameSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDuplicateCheckExecFlag(long, String, int)";
        log.entering(STR_METHOD_NAME);
        
        BranchPreferencesRow l_branchPreferencesRow = null;
        
        try
        {
            l_branchPreferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId, 
                    l_strName, 
                    l_strNameSerialNo);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }  
        
        //２）取得したレコード.プリファ@レンスの値を返却する。
        //※レコードが取得できなかった場合 ⇒ 0
        if (l_branchPreferencesRow == null) 
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
            
        }
        //レコード.プリファ@レンスの値： "0" OR "" ⇒ 0
        else if ("".equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.DEFAULT.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        
        //レコード.プリファ@レンスの値： "1" OR "3" ⇒ 1
        else if (WEB3DuplicationMailAddressCheckDef.NO_EXCEPTION.equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION_CUST.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }
        
        //レコード.プリファ@レンスの値： "2" OR "4" ⇒ 2
        else if (WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION.equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION_ADMIN.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 2;
        }
        
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (get顧客メールアドレス)<BR>
     * 顧客が登録しているメールアドレスをすべて取得する。<BR>
     * <BR>
     * 顧客メールアドレステーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@顧客メールアドレス.証券会社コード = 引数.証券会社コード<BR>
     * 　@顧客メールアドレス.部店コード = 引数.部店コード<BR>
     * 　@顧客メールアドレス.口座コード = 引数.口座コード<BR>
     * <BR>
     * 該当行にて顧客メールアドレスオブジェクトを生成し、配列にて返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getAccountMailAddress(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountMailAddress("
            + "String, String, String)";
        log.entering(STR_METHOD_NAME);

        String l_strQuery =
            " institution_code = ? "
            + " and branch_code = ? "
            + " and SubStr(account_code,0,6) = ? ";

        Object[] l_objQuery =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisAccountMailAddressRows = l_queryProcessor.doFindAllQuery(
                AccountMailAddressRow.TYPE,
                l_strQuery,
                l_objQuery);

            log.exiting(STR_METHOD_NAME);
            return l_lisAccountMailAddressRows;
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
    }

    /**
     * (createメールアドレス変更情報)<BR>
     * メールアドレス変更情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@メールアドレス変更情報オブジェクトの生成<BR>
     * <BR>
     * ２）　@以下の通り、プロパティをセットする。<BR>
     * <BR>
     * 　@　@・　@メールアドレス変更情報.メールアドレス更新者コード　@ ＝<BR>
     * 　@　@　@　@(引数)顧客メールアドレス行.メールアドレス更新者コード<BR>
     * 　@　@・　@メールアドレス変更情報.メールアドレス更新日時　@ ＝<BR>
     * 　@　@　@　@(引数)顧客メールアドレス行.メールアドレス更新日時<BR>
     * 　@　@・　@メールアドレス情報.メールアドレス　@＝　@(引数)顧客メールアドレス行.メールアドレス <BR>
     * 　@　@・　@メールアドレス情報.メールアドレス番号　@＝　@(引数)顧客メールアドレス行.メールアドレス番号<BR>
     * 　@　@・　@メールアドレス情報.メールアドレス区分　@＝　@(引数)顧客メールアドレス行.メールアドレス区分<BR>
     * <BR>
     * ３）　@生成したメールアドレス変更情報オブジェクトを返却する。<BR>
     * @@param l_accountMailAddressRow - (顧客メールアドレス行)<BR>
     * 顧客メールアドレス行<BR>
     * @@return WEB3AccInfoMailAddressUpdateInfo
     */
    protected WEB3AccInfoMailAddressUpdateInfo createMailAddressUpdateInfo(
        AccountMailAddressRow l_accountMailAddressRow)
    {
        final String STR_METHOD_NAME = "createMailAddressUpdateInfo(AccountMailAddressRow)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoMailAddressUpdateInfo l_mailAddressUpdateInfo =
            new WEB3AccInfoMailAddressUpdateInfo();

        l_mailAddressUpdateInfo.mailAddressUpdaterCode =
            l_accountMailAddressRow.getEmailLastUpdater();
        if (l_accountMailAddressRow.getEmailLastUpdatedTimestamp() != null)
        {
            l_mailAddressUpdateInfo.mailAddressUpdateDate =
                WEB3DateUtility.formatDate(
                    l_accountMailAddressRow.getEmailLastUpdatedTimestamp(),
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
        }
        l_mailAddressUpdateInfo.mailAddress = l_accountMailAddressRow.getEmailAddress();
        l_mailAddressUpdateInfo.mailAddressNo = l_accountMailAddressRow.getEmailAddressNumber() + "";
        l_mailAddressUpdateInfo.mailAddressDiv = l_accountMailAddressRow.getAddressDiv();
        return l_mailAddressUpdateInfo;
    }

    /**
     * (update顧客メールアドレス)<BR>
     *１）リクエストデータ.メールアドレス情報.メールアドレス == null の場合、以下処理を実施する。<BR>
     *以下の条件で「顧客メールアドレステーブル」から検索する。<BR>
     *　@　@[検索条件]<BR>
     *　@　@証券会社コード：顧客.証券会社コード<BR>
     *　@　@部店コード：顧客.部店コード<BR>
     *　@　@口座コード：顧客.口座コード<BR>
     *　@　@メールアドレス番号：リクエストデータ.メールアドレス情報.メールアドレス番号<BR>
     *<BR>
     *　@検索結果が取得できる場合、<BR>
     *　@ＤＢ更新を行う。<BR>
     *　@「管理者・メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_delete」参照。<BR>
     *<BR>
     *２） １）以外の場合、以下の条件で「顧客メールアドレステーブル」から検索する。<BR>
     *<BR>
     *　@　@[検索条件]<BR>
     *　@　@証券会社コード：顧客.証券会社コード<BR>
     *　@　@部店コード：顧客.部店コード<BR>
     *　@　@口座コード：顧客.口座コード<BR>
     *　@　@メールアドレス番号：リクエストデータ.メールアドレス情報.メールアドレス番号<BR>
     *<BR>
     *　@２－１） 検索結果が取得できる場合、<BR>
     *　@ＤＢ更新を行う。<BR>
     *　@「管理者・メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_update」参照。<BR>
     *<BR>
     *　@２－２） 検索結果が取得できない場合、<BR>
     *　@ＤＢ更新を行う。<BR>
     *　@「管理者・メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_insert」参照。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_mailAddressInfo - (メールアドレス情報)<BR>
     * メールアドレス情報<BR>
     * @@throws WEB3BaseException
     */
    protected void updateAccountMailAddress(
        WEB3GentradeMainAccount l_mainAccount,
        WEB3AccInfoMailAddressInfoUnit l_mailAddressInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAccountMailAddress("
            + "WEB3GentradeMainAccount, WEB3AccInfoMailAddressInfoUnit)";
        log.entering(STR_METHOD_NAME);

        //以下の条件で「顧客メールアドレステーブル」から検索する。
        //証券会社コード：顧客.証券会社コード
        //部店コード：顧客.部店コード
        //口座コード：顧客.口座コード
        //メールアドレス番号：リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス番号
        //顧客.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //顧客.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //顧客.getAccountCode()
        String l_strAccountCode = l_mainAccount.getAccountCode();
        //１）　@顧客メールアドレステーブルを以下の条件で検索する。
        String l_strQuery =
            " institution_code = ? "
            + " and branch_code = ? "
            + " and account_code = ? "
            + " and email_address_number = ? ";

        Object[] l_objQuery =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_mailAddressInfo.mailAddressNo};
        
        List l_lisAccountMailAddressRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccountMailAddressRows = l_queryProcessor.doFindAllQuery(
                AccountMailAddressRow.TYPE,
                l_strQuery,
                l_objQuery);

            //１）リクエストデータ.メールアドレス情報.メールアドレス == null の場合、以下処理を実施する。
            if (l_mailAddressInfo.mailAddress == null)
            {
                if (l_lisAccountMailAddressRows.size() == 1)
                {
                    //検索結果が取得できる場合、
                    //「管理者・メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_delete」参照。
                    AccountMailAddressPK l_accountMailAddressPK =
                        new AccountMailAddressPK(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            Long.parseLong(l_mailAddressInfo.mailAddressNo));
    
                    l_queryProcessor.doDeleteQuery(l_accountMailAddressPK);
                }
            }
            //２） １）以外の場合、以下の条件で「顧客メールアドレステーブル」から検索する。
            else
            {
                if (l_lisAccountMailAddressRows.size() == 1)
                {
                    AccountMailAddressRow l_accountMailAddressRow =
                        (AccountMailAddressRow)l_lisAccountMailAddressRows.get(0);
                    //検索結果が取得できる場合、
                    //「管理者・メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_update」参照。
                    Map l_map = new HashMap();
                    //リクエストデータ.複数アドレスリスト.メールアドレス情報.アドレス区分
                    l_map.put("address_div", l_mailAddressInfo.mailAddressDiv);
                    //リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス
                    l_map.put("email_address", l_mailAddressInfo.mailAddress);
                    //顧客メールアドレス行.メールアドレス !=
                    // 「リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス」の場合、口座コード。
                    if (!WEB3Toolkit.isEquals(
                        l_mailAddressInfo.mailAddress,
                        l_accountMailAddressRow.getEmailAddress()))
                    {
                        l_map.put("email_last_updater",
                            WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
                        l_map.put("email_last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());
                    }
                    //TradingSystem.getSystemTimestamp() 。
                    l_map.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());
                    log.exiting(STR_METHOD_NAME);
                    l_queryProcessor.doUpdateAllQuery(
                        AccountMailAddressRow.TYPE,
                        l_strQuery,
                        l_objQuery,
                        l_map);
                }
                else
                {
                    //２－２） 検索結果が取得できない場合、
                    //「管理者・メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_insert」参照。
                    AccountMailAddressParams l_accountMailAddressParams =
                        new AccountMailAddressParams();
                    //顧客.証券会社コード
                    l_accountMailAddressParams.setInstitutionCode(l_strInstitutionCode);
                    //顧客.部店コード
                    l_accountMailAddressParams.setBranchCode(l_strBranchCode);
                    //顧客.口座コード
                    l_accountMailAddressParams.setAccountCode(l_strAccountCode);
                    //リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス番号
                    l_accountMailAddressParams.setEmailAddressNumber(
                        Long.parseLong(l_mailAddressInfo.mailAddressNo));
                    //リクエストデータ.複数アドレスリスト.メールアドレス情報.アドレス区分
                    l_accountMailAddressParams.setAddressDiv(l_mailAddressInfo.mailAddressDiv);
                    //リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス
                    l_accountMailAddressParams.setEmailAddress(l_mailAddressInfo.mailAddress);
                    //管理者テーブル.管理者コード
                    l_accountMailAddressParams.setEmailLastUpdater(
                        WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
                    //TradingSystem.getSystemTimestamp() 。
                    l_accountMailAddressParams.setEmailLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_accountMailAddressParams.setCreatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_accountMailAddressParams.setLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_queryProcessor.doInsertQuery(l_accountMailAddressParams);
                }
            }
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
        log.exiting(STR_METHOD_NAME);
    }
}
@
