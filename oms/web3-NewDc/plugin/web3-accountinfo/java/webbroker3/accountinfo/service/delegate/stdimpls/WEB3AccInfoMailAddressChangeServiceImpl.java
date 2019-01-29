head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報メールアドレス変更サービスImpl(WEB3AccInfoMailAddressChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
                 : 2006/05/19 周捷 (中訊) 仕様変更・モデル104
                 : 2006/05/22 周捷 (中訊) 仕様変更・モデル105
                 : 2006/05/23 周捷 (中訊) 仕様変更・モデル106、107
                 : 2006/06/12 猪俣（SCS）　@仕様変更・モデル109
Revesion History : 2007/08/28 謝旋（中訊）ＤＢ更新仕様・モデル048
Revesion History : 2007/08/28 武波 (中訊) 仕様変更管理No.218
Revesion History : 2007/08/30 武波 (中訊) 仕様変更管理No.221
Revesion History : 2008/03/05 車進 (中訊) ＤＢ更新仕様・モデル050
Revesion History : 2009/02/12 SCS大嶋 仕様変更管理No.253
Revesion History : 2010/02/22 武波 (中訊) 仕様変更管理モデルNo.259,No.265,No.266,No.268,No.269,No.270,No.272 ＤＢ更新仕様No.060,No062,No063
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressTypeUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressInfo;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailAddressChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenSendMailStatusDef;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3AddressDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3DuplicationMailAddressCheckDef;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3InformationMail2FlagDef;
import webbroker3.common.define.WEB3MailAssortmentDivDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3OrderExeFlagDef;
import webbroker3.common.define.WEB3OrderUnexeFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMailAddressDuplicationCheck;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMobileMailAddressCheck;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AccountMailAddressPK;
import webbroker3.gentrade.data.AccountMailAddressParams;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.MailAssortmentPK;
import webbroker3.gentrade.data.MailAssortmentParams;
import webbroker3.gentrade.data.MailAssortmentRow;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * (お客様情報メールアドレス変更サービスImpl)<BR>
 * お客様情報メールアドレス変更サービス実装クラス<BR>
 * 
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressChangeServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoMailAddressChangeService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressChangeServiceImpl.class);
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
     * @@roseuid 418F39FE0261
     */
    public WEB3AccInfoMailAddressChangeServiceImpl() 
    {
     
    }
    
    /**
     * メールアドレス変更処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、お客様情報メールアドレス変更確認リクエストの場合 <BR>
     * 　@－validate変更()をコールする。 <BR>
     * ○ 引数のリクエストデータが、お客様情報メールアドレス変更完了リクエストの場合 <BR>
     * 　@－submit変更()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D3CCB0047
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AccInfoMailAddressChangeConfirmRequest)
        {
            l_response = this.validateChange((WEB3AccInfoMailAddressChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AccInfoMailAddressChangeCompleteRequest)
        {           
            l_response = this.submitChange((WEB3AccInfoMailAddressChangeCompleteRequest)l_request);
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
     * (validate変更)<BR>
     * メールアドレス変更確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（メールアドレス変更）validate変更」参照。<BR>
     * <BR>
     *  =============================================== <BR>
     *          シーケンス図 :  （メールアドレス変更）validate変更<BR>
     *          具体位置     :  1.4  isMailAddress(String)<BR>
     *          メールアドレスとして適切でない場合、例外をスローする。<BR>
     *          （isMailAddress() == false<BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag              : BUSINESS_ERROR_00777 <BR>
     * ===============================================<BR>
     * ===============================================<BR>
     *          シーケンス図 :  （メールアドレス変更）validate変更<BR>
     *          具体位置     :  1.6.3重複メールアドレスが存在した場合（get重複アドレス()戻り値の長さ>0）<BR>
     *                          ログインが顧客の場合<BR>
     *          class           : WEB3BusinessLayerException <BR>
     *          tag              : BUSINESS_ERROR_02443 <BR> 
     * ===============================================<BR>
     * ===============================================<BR>
     *          シーケンス図 :  （メールアドレス変更）validate変更<BR>
     *          具体位置     :  1.6.3重複メールアドレスが存在した場合（get重複アドレス()戻り値の長さ>0）<BR>
     *                          ログインが代理入力者の場合 <BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02444<BR> 
     * ===============================================<BR>
     * @@param l_request - お客様情報メールアドレス変更確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D3CCB0067
     */
    protected WEB3AccInfoMailAddressChangeConfirmResponse validateChange(WEB3AccInfoMailAddressChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AccInfoMailAddressChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 get顧客( )
        MainAccount l_mainAccount = this.getMainAccount();
        
        //1.5 isMailAddress(String)
        if (!l_request.mailAddressDelFlag)
        {
            if (!WEB3StringTypeUtility.isMailAddress(l_request.changedMailAddress))
            {
                log.debug("メールアドレスとして適切でない場合、例外をスロー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, getClass().getName() + " " +"validateChange");
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
        int l_intSerialNo = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_mainAccount.getBranch();
        String l_strBranchPreferences = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_gentradeBranch.getBranchCode(),
            l_mainAccount.getInstitution().getInstitutionCode(),
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            l_intSerialNo);
        if (l_accInfoMultiMailAddressInfo != null)
        {
            //複数メールアドレス対応会社（get部店プリファ@レンス() == "1"）の場合
            if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T0.equals(l_strBranchPreferences))
            {
                //  [validate複数アドレス送信フラグ()に設定する引数]
                //基本メールアドレス： リクエストデータ.変更後メールアドレス
                l_accInfoMultiMailAddressInfo.validateMultiSendMailAddressInfoFlag(
                    l_request.changedMailAddress);
            }
        }
        //複数メールアドレス対応実施（1.7 get複数メールアドレス対応実施() == "2"）の場合
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
        {
            //リクエストデータ.複数アドレスリスト.メールアドレス情報の要素数分Loop処理を行う。
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.multiMailAddressList.mailAddressInfoList;
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
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv))
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
            WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressTypeUnits =
                    l_request.multiMailAddressList.mailTypeInfoList;
            }
            int l_intLengthMailAddressType = 0;
            if (l_accInfoMailAddressTypeUnits != null)
            {
                l_intLengthMailAddressType = l_accInfoMailAddressTypeUnits.length;
            }
            for (int j = 0; j < l_intLengthMailAddressType; j++)
            {
                l_accInfoMailAddressTypeUnits[j].validateMailAddressTypeInfo(
                    l_accInfoMailAddressInfoUnits);
            }
        }
        //1.6get重複チェック実行フラグ(long, String, int)
        // 重複メールチェックの対象かどうかを判別する。 
        // ・部店ID：get顧客().getBranch().getBranchId()の戻り値 
        // ・プリファ@レンス名：WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO（部店プリファ@レンステーブル仕様>シート
        //  「値設定仕様」参照） 
        // ・連番：プリファ@レンス名の連番 
        int l_intDuplicateCheckExecFlag = 0;
        l_intDuplicateCheckExecFlag = 
            getDuplicateCheckExecFlag(
                l_mainAccount.getBranch().getBranchId(),
                WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO, 
                1);

        Trader l_trader = null;
        String[] l_strDuplicationAccounts = null;
        StringBuffer l_strDuplicationAccount = new StringBuffer();
        Object[] l_objDuplicationCheck = null;
        String[] l_strDuplicationAddressInfos = null;
        int l_intDuplicationCheckLenth = 0;

        //分岐フロー。
        //重複メールアドレスチェック対象（get重複チェック実行フラグ() != 0）である場合、重複アドレス検索を行う。 
        if (l_intDuplicateCheckExecFlag != 0)
        {
            //リクエストデータ.複数アドレスリスト.メールアドレス情報の要素数分Loop処理を行う。
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.multiMailAddressList.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }
            List l_lisDuplicationChecks = new ArrayList();
            for (int i = 0; i < l_intLength; i++)
            {
                //1.6.1get重複アドレス(String, String, String, String)
                //・メールアドレス： リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス
                //・証券会社コード：get顧客().getInstitution().getInstitutionCode()の戻り値
                //・部店コード：get顧客().getBranch().getBranchCode()の戻り値
                //・顧客コード：get顧客().getAccountCode()の戻り値
                if (l_accInfoMailAddressInfoUnits[i].mailAddress == null)
                {
                    continue;
                }
                l_objDuplicationCheck =
                    WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress,
                        l_mainAccount.getAccountCode(),
                        l_mainAccount.getBranch().getBranchCode(),
                        l_mainAccount.getInstitution().getInstitutionCode());
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
            //1.6.2get代理入力者( )
            l_trader = this.getTrader();
            
            //1.6.3
            //分岐フロー。
            //重複メールアドレスが存在した場合（get重複アドレス()戻り値長さ>0）、以下条件時に例外をスローする。 
            //(「重複メールアドレスチェック対象」（get重複チェック実行フラグ() != 0)&& 
            //「重複メールアドレスが存在する」（get重複アドレス()戻り値の長さ>0）&& 
            //「現在のログインが顧客（get代理入力者()==null）」) 
            l_strDuplicationAccounts = new String[l_intDuplicationCheckLenth];
            l_strDuplicationAddressInfos = new String[l_intDuplicationCheckLenth];
            for (int i = 0; i < l_intDuplicationCheckLenth; i++)
            {
//				コーディングミスの為変更***2006.06.12 SCS Inomata-->
//                l_strDuplicationAccounts[i] = 
//                    "[" + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + ":" + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]) + "]";
//                l_strDuplicationAddressInfos[i] =
//                    WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//                    + "," + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]);
				l_strDuplicationAccounts[i] = 
					"\n[部店 " + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "：顧客コード " + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]) + "]";
				l_strDuplicationAddressInfos[i] =
					WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
					+ "," + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]);
//				<--コーディングミスの為変更***2006.06.12 SCS Inomata
                l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
            }
            
            if (l_intDuplicateCheckExecFlag != 0 && l_intDuplicationCheckLenth > 0 && l_trader == null)
            {
                //l_errorMethod エラーが発生したメソッド名 ：getClass().getName() + "validateChange" 
                //l_errorMessage エラーの内容 ："[部店コード:顧客コード]" 
                //（get重複アドレス()戻り値要素。複数存在する場合は、"[部店コード:顧客コード][部店コード:顧客コード]"）                
                log.exiting(STR_METHOD_NAME);
//				仕様変更***2006.06.12 SCS Inomata-->
//                throw new WEB3BusinessLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_02443, 
//                    getClass().getName() + " " + "validateChange",
//                    l_strDuplicationAccount.toString());

				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02443, 
					getClass().getName() + " " + "validateChange", "");
//				<--仕様変更***2006.06.12 SCS Inomata
            }
            
            //or
            //(「重複メールアドレスチェック対象」(get重複チェック実行フラグ() == 2)&& 
            //「重複メールアドレスが存在する」（get重複アドレス()戻り値の長さ>0）&& 
            //「現在のログインが代理入力者（get代理入力者()!=null）」) 
            else if (l_intDuplicateCheckExecFlag == 2 && l_intDuplicationCheckLenth > 0 && l_trader != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02444, 
                    getClass().getName() + " " + "validateChange", 
                    l_strDuplicationAccount.toString());
            } 
        }      

        //1.7createResponse( )
        WEB3AccInfoMailAddressChangeConfirmResponse l_response = 
            (WEB3AccInfoMailAddressChangeConfirmResponse) l_request.createResponse();
        
        if (l_intDuplicationCheckLenth > 0 && l_trader != null && l_intDuplicateCheckExecFlag  == 1)
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
     * 「お客様情報（メールアドレス変更）submit変更」参照。<BR>
     * <BR>
     *  ===============================================<BR>
     *          シーケンス図 :  （メールアドレス変更）submit変更<BR>
     *          具体位置     :  1.3  isMailAddress(String)<BR>
     *          メールアドレスとして適切でない場合、例外をスローする。<BR>
     *          （isMailAddress() == false）<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_00777 <BR> 
     *  ===============================================<BR>
     * ================================================<BR>
     *          シーケンス図 :  （メールアドレス変更）submit変更<BR>
     *          具体位置     :  1.8.3重複メールアドレスが存在する<BR> 
     *          「現在のログインが顧客（get代理入力者()==null）」<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02443 <BR> 
     * ================================================ <BR>
     * ================================================  <BR>
     *          シーケンス図 :  （メールアドレス変更）submit変更<BR>
     *          具体位置     :  1.8.3重複メールアドレスが存在する<BR> 
     *          「現在のログインが代理入力者（get代理入力者()!=null）」<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag              : BUSINESS_ERROR_02444<BR> 
     * ================================================<BR>
     * @@param l_request - お客様情報メールアドレス変更完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D3CF00374
     */
    protected WEB3AccInfoMailAddressChangeCompleteResponse 
        submitChange(WEB3AccInfoMailAddressChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AccInfoMailDistributionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //1.1 get顧客( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

        //証券会社コード
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //部店コード
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //顧客コード
        String l_accountCode = l_mainAccount.getAccountCode();
        
        //1.2 lock口座
        WEB3GentradeAccountManager l_accManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            l_accManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_accountCode);

        
        //1.3 validate( )
        l_request.validate();
        
        //1.4 validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5isMailAddress(String)
        if (!l_request.mailAddressDelFlag)
        {
            if (!WEB3StringTypeUtility.isMailAddress(l_request.changedMailAddress))
            {
                //1.6 メールアドレスとして適切でない場合（isMailAddress() == false）、例外をスローする。
                log.exiting(STR_METHOD_NAME);
                log.debug("メールアドレスとして適切でない場合、例外をスロー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00777, getClass().getName() + " " + "submitChange");
            }
        }
        
        //1.7 get補助口座( )
        //   補助口座オブジェクトを取得する。 
        SubAccount l_subAccount = getSubAccount();

        //1.8 get代理入力者( )
        //   代理入力者オブジェクトを取得する。
        Trader l_trader = getTrader();

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
        int l_intSerialNo = Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO);
        WEB3GentradeBranch l_gentradeBranch = (WEB3GentradeBranch)l_mainAccount.getBranch();
        String l_strBranchPreferences = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_strBranchCode,
            l_mainAccount.getInstitution().getInstitutionCode(),
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            l_intSerialNo);

        if (l_accInfoMultiMailAddressInfo != null)
        {
            //複数メールアドレス対応会社（get部店プリファ@レンス() == "1"）の場合
            if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T0.equals(l_strBranchPreferences))
            {
                //  [validate複数アドレス送信フラグ()に設定する引数]
                //基本メールアドレス： リクエストデータ.変更後メールアドレス
                l_accInfoMultiMailAddressInfo.validateMultiSendMailAddressInfoFlag(
                    l_request.changedMailAddress);
            }
        }
        //複数メールアドレス対応実施（1.7 get複数メールアドレス対応実施() == "2"）の場合
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strBranchPreferences))
        {
            //リクエストデータ.複数アドレスリスト.メールアドレス情報の要素数分Loop処理を行う。
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.multiMailAddressList.mailAddressInfoList;
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
                    l_accInfoMailAddressInfoUnits[i].mailAddressDiv))
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
            WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressTypeUnits =
                    l_request.multiMailAddressList.mailTypeInfoList;
            }
            int l_intLengthMailAddressType = 0;
            if (l_accInfoMailAddressTypeUnits != null)
            {
                l_intLengthMailAddressType = l_accInfoMailAddressTypeUnits.length;
            }
            for (int j = 0; j < l_intLengthMailAddressType; j++)
            {
                l_accInfoMailAddressTypeUnits[j].validateMailAddressTypeInfo(
                    l_accInfoMailAddressInfoUnits);
            }
        }
        //1.9 get重複チェック実行フラグ(long, String, int)
        // 重複メールチェックの対象かどうかを判別する。 
        // ・部店ID：get顧客().getBranch().getBranchId()の戻り値 
        // ・プリファ@レンス名：WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO（部店プリファ@レンステーブル仕様>シート
        //  「値設定仕様」参照） 
        // ・連番：プリファ@レンス名の連番 
        int l_intDuplicateCheckExecFlag = 
            this.getDuplicateCheckExecFlag(
                l_mainAccount.getBranch().getBranchId(), 
                WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO, 
                1);      
        String[] l_strDuplicationAccounts = null;
        String[] l_strDuplicationAddressInfos = null;
        StringBuffer l_strDuplicationAccount = new StringBuffer();
        Object[] l_objDuplicationCheck = null;
        int l_intDuplicationCheckLenth = 0;
        if (l_intDuplicateCheckExecFlag != 0)
        {
            //リクエストデータ.複数アドレスリスト.メールアドレス情報の要素数分Loop処理を行う。
            WEB3AccInfoMailAddressInfoUnit[] l_accInfoMailAddressInfoUnits = null;
            if (l_request.multiMailAddressList != null)
            {
                l_accInfoMailAddressInfoUnits =
                    l_request.multiMailAddressList.mailAddressInfoList;
            }
            int l_intLength = 0;
            if (l_accInfoMailAddressInfoUnits != null)
            {
                l_intLength = l_accInfoMailAddressInfoUnits.length;
            }
            List l_lisDuplicationChecks = new ArrayList();
            for (int i = 0; i < l_intLength; i++)
            {
                //1.9.1get重複アドレス(String, String, String, String)
                //・メールアドレス： リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス
                //・証券会社コード：get顧客().getInstitution().getInstitutionCode()の戻り値 
                //・部店コード：get顧客().getBranch().getBranchCode()の戻り値 
                //・顧客コード：get顧客().getAccountCode()の戻り値
                if (l_accInfoMailAddressInfoUnits[i].mailAddress == null)
                {
                    continue;
                }
                l_objDuplicationCheck = 
                    WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                        l_accInfoMailAddressInfoUnits[i].mailAddress,
                        l_mainAccount.getAccountCode(),
                        l_mainAccount.getBranch().getBranchCode(),
                        l_mainAccount.getInstitution().getInstitutionCode());
                
                if(l_objDuplicationCheck != null)
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
            //1.9.2
            //分岐フロー。
            //重複メールアドレスが存在した場合（get重複アドレス()戻り値長さ>0）、以下条件時に例外をスローする。 
            //(「重複メールアドレスチェック対象」（get重複チェック実行フラグ() != 0)&& 
            //「重複メールアドレスが存在する」（get重複アドレス()戻り値の長さ>0）&& 
            //「現在のログインが顧客（get代理入力者()==null）」) 
            l_strDuplicationAccounts = new String[l_intDuplicationCheckLenth];
            l_strDuplicationAddressInfos = new String[l_intDuplicationCheckLenth];
            for (int i = 0; i < l_intDuplicationCheckLenth; i++)
            {
            	
//				コーディングミスの為変更***2006.06.12 SCS Inomata-->
//				l_strDuplicationAccounts[i] = 
//					"[" + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//					+ ":" + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]) + "]";
//				l_strDuplicationAddressInfos[i] =
//					WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i])
//					+ "," + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i]);
//				l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
                l_strDuplicationAccounts[i] = 
                    "\n[部店 " + WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
                    + "：顧客コード " + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]) + "]";
                l_strDuplicationAddressInfos[i] =
                    WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_objDuplicationCheck[i])
                    + "," + WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_objDuplicationCheck[i]);
//				<--コーディングミスの為変更***2006.06.12 SCS Inomata
                l_strDuplicationAccount.append(l_strDuplicationAccounts[i]);
            }
            if (l_intDuplicateCheckExecFlag != 0 && l_intDuplicationCheckLenth > 0 && l_trader == null)
            {
                log.exiting(STR_METHOD_NAME);
//				仕様変更***2006.06.12 SCS Inomata-->
//                throw new WEB3BusinessLayerException(
//                    WEB3ErrorCatalog.BUSINESS_ERROR_02443, 
//                    getClass().getName() + " " + "submitChange",
//                    l_strDuplicationAccount.toString());
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_02443, 
					getClass().getName() + " " + "submitChange","");
//				<--仕様変更***2006.06.12 SCS Inomata
            }
            
            //or
            //(「重複メールアドレスチェック対象」(get重複チェック実行フラグ() == 2)&& 
            //「重複メールアドレスが存在する」（get重複アドレス()戻り値の長さ>0）&& 
            //「現在のログインが代理入力者（get代理入力者()!=null）」) 
            else if (l_intDuplicateCheckExecFlag == 2 && l_intDuplicationCheckLenth > 0 && l_trader != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02444, 
                    getClass().getName() + " " + "submitChange",
                    l_strDuplicationAccount.toString());
            } 
        }

        //getDataSourceObject( )
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);

        if (l_intDuplicateCheckExecFlag == 0 
            || (l_intDuplicateCheckExecFlag != 0 && l_intDuplicationCheckLenth == 0) 
            || (l_intDuplicateCheckExecFlag == 1 && l_intDuplicationCheckLenth > 0 && l_trader != null))
        {
            //1.10 getCommonOrderValidator( )
            //注文チェックインスタンスを取得する。
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            
            //1.11 validate取引パスワード(代理入力者 : Trader, 補助口座 : SubAccount, パスワード : String)
            OrderValidationResult l_validationResult = 
                l_orderValidator.validateTradingPassword(
                    l_trader, 
                    l_subAccount, 
                    l_request.password); 
            
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("チェックエラーの場合はを例外をスローする");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.13 doUpdateQuery(arg0 : PrimaryKey, arg1(*2) : Map)
            if (l_request.multiMailAddressInfo.execMailFlag != null)
            {
                //リクエストデータ.複数アドレス情報.約定通知送信フラグ != nullかつ、
                //リクエストデータ.複数アドレス情報.約定通知送信フラグ=0の場合
                if (WEB3OrderExeFlagDef.NOT_SEND_MAIL.equals(
                    l_request.multiMailAddressInfo.execMailFlag))
                {
                    //株式約定メール送信フラグ: 0
                    l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.FALSE);

                    //先物OP約定メール送信フラグ:0
                    l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.FALSE);
                }
                //リクエストデータ.複数アドレス情報.約定通知送信フラグ != nullかつ、
                //リクエストデータ.複数アドレス情報.約定通知送信フラグ!=0の場合
                else
                {
                    //株式約定メール送信フラグ: 1
                    l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);

                    //先物OP約定メール送信フラグ:1
                    l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
                }
            }

            if (l_request.multiMailAddressInfo.unExecMailFlag != null)
            {
                //リクエストデータ.複数アドレス情報.未約定通知送信フラグ != nullかつ、
                //リクエストデータ.複数アドレス情報.未約定通知送信フラグ=0の場合
                if (WEB3OrderUnexeFlagDef.NOT_SEND_MAIL.equals(
                    l_request.multiMailAddressInfo.unExecMailFlag))
                {
                    //株式未約定メール送信フラグ:0
                    l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.FALSE);

                    //先物OP未約定メール送信フラグ:0
                    l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.FALSE);
                }
                //リクエストデータ.複数アドレス情報.未約定通知送信フラグ != nullかつ、
                //リクエストデータ.複数アドレス情報.未約定通知送信フラグ!=0の場合
                else
                {
                    //株式未約定メール送信フラグ:1
                    l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);

                    //先物OP未約定メール送信フラグ:1
                    l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
                }
            }

            if (l_request.multiMailAddressInfo.guidanceMailFlag2 != null)
            {
                //リクエストデータ.複数アドレス情報.案内メール２送信フラグ != nullかつ、
                //リクエストデータ.複数アドレス情報.案内メール２送信フラグ=0の場合
                //案内メール送信フラグ:0
                if (WEB3InformationMail2FlagDef.NOT_SEND_MAIL.equals(
                    l_request.multiMailAddressInfo.guidanceMailFlag2))
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
                }
                //リクエストデータ.複数アドレス情報.案内メール２送信フラグ != nullかつ、
                //リクエストデータ.複数アドレス情報.案内メール２送信フラグ!=0の場合
                else
            	{
                	//案内メール送信フラグ:1
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
                }
            }
            
            if (l_request.mailAddressDelFlag)
            {
                l_mainAccountParams.setEmailAddress(null);

                //emailアドレス更新者コード
                //口座コード
                l_mainAccountParams.setEmailLastUpdater(l_mainAccountRow.getAccountCode());
                //emailアドレス更新日時
                //処理日時　@※TradingSystem.getSystemTimestamp()
                l_mainAccountParams.setEmailLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            }
            else
            {
                l_mainAccountParams.setAccOpenSendMailStatus(WEB3EmailStatusDef.EMAIL_SEND_END);

                if (l_request.changedMailAddress != null
                    && !l_request.changedMailAddress.equals(l_mainAccountParams.getEmailAddress()))
                {
                    //emailアドレス更新者コード
                    //口座コード
                    l_mainAccountParams.setEmailLastUpdater(l_mainAccountRow.getAccountCode());
                    //emailアドレス更新日時
                    //処理日時　@※TradingSystem.getSystemTimestamp()
                    l_mainAccountParams.setEmailLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //（リクエストデータ.メールアドレス削除フラグ == false && リクエストデータ.変更後メールアドレス != null）の場合、
                //リクエストデータ.変更後メールアドレス
                if (l_request.changedMailAddress != null)
                {
                    l_mainAccountParams.setEmailAddress(l_request.changedMailAddress);
                }
            }

            l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());

            //（リクエストデータ.メールアドレス削除フラグ == false）の場合、1：処理済（Email送信済）。
            //以外、（既存値）。
            if (!l_request.mailAddressDelFlag)
            {
                l_mainAccountParams.setAccOpenSendMailStatus(WEB3AccOpenSendMailStatusDef.EMAIL_SENDED);
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

            //リクエストデータ.複数アドレス情報.約定通知送信フラグ != nullの場合、リクエストデータ.複数アドレス情報.約定通知送信フラグ
            if (l_request.multiMailAddressInfo.execMailFlag != null)
            {
                int l_intExecMailFlag = Integer.parseInt(l_request.multiMailAddressInfo.execMailFlag);
                l_mainAccountParams.setOrderExeFlag(l_intExecMailFlag);
            }

            //リクエストデータ.複数アドレス情報.未約定通知送信フラグ != nullの場合、リクエストデータ.複数アドレス情報.未約定通知送信フラグ
            if (l_request.multiMailAddressInfo.unExecMailFlag != null)
            {
                int l_intUnExecMailFlag = Integer.parseInt(l_request.multiMailAddressInfo.unExecMailFlag);
                l_mainAccountParams.setOrderUnexeFlag(l_intUnExecMailFlag);
            }

            //リクエストデータ.複数アドレス情報.重要連絡メール送信フラグ != nullの場合、リクエストデータ.複数アドレス情報.重要連絡メール送信フラグ
            if (l_request.multiMailAddressInfo.importantMailFlag != null)
            {
                int l_intImportantMailFlag =
                    Integer.parseInt(l_request.multiMailAddressInfo.importantMailFlag);
                l_mainAccountParams.setImportantConnectionMailFlag(l_intImportantMailFlag);
            }

            //リクエストデータ.複数アドレス情報.案内メール２送信フラグ != nullの場合、リクエストデータ.複数アドレス情報.案内メール２送信フラグ
            if (l_request.multiMailAddressInfo.guidanceMailFlag2 != null)
            {
                int l_intGuidanceMailFlag2 =
                    Integer.parseInt(l_request.multiMailAddressInfo.guidanceMailFlag2);
                l_mainAccountParams.setInformationMail2Flag(l_intGuidanceMailFlag2);
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
        if (l_request.multiMailAddressList != null)
        {
            l_accInfoMailAddressInfoUnits =
                l_request.multiMailAddressList.mailAddressInfoList;
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
                this.updateAccountMailAddress(l_mainAccount, l_accInfoMailAddressInfoUnits[i]);
            }
        }

        WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits = null;
        if (l_request.multiMailAddressList != null)
        {
            l_accInfoMailAddressTypeUnits =
                l_request.multiMailAddressList.mailTypeInfoList;
        }
        int l_intLengthMailAddressType = 0;
        if (l_accInfoMailAddressTypeUnits != null)
        {
            l_intLengthMailAddressType = l_accInfoMailAddressTypeUnits.length;
        }

        Map l_hmMainAccounts = new HashMap();
        for (int j = 0; j < l_intLengthMailAddressType; j++)
        {
            //株式約定メール送信フラグ
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 = 1:株式約定メールの場合、更新する。
            if (WEB3MailAssortmentDivDef.EQUITY_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号= nullの場合、0
                    l_hmMainAccounts.put("equity_order_exe_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != nullの場合、1
                    l_hmMainAccounts.put("equity_order_exe_mail_flag", BooleanEnum.TRUE);
                }
            }
            
            //株式未約定メール送信フラグ
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 = 2:株式未約定メールの場合、更新する。
            if (WEB3MailAssortmentDivDef.EQUITY_NOT_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの場合、0
                    l_hmMainAccounts.put("equity_order_unexec_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != nullの場合、1
                    l_hmMainAccounts.put("equity_order_unexec_mail_flag", BooleanEnum.TRUE);
                }
            }

            //先物OP約定メール送信フラグ
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 = 3:先OP約定メールの場合、 更新する。
            if (WEB3MailAssortmentDivDef.FUTURES_OPTION_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの場合、0
                    l_hmMainAccounts.put("ifo_order_exec_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != nullの場合、1
                    l_hmMainAccounts.put("ifo_order_exec_mail_flag", BooleanEnum.TRUE);
                }
            }

            //先物OP未約定メール送信フラグ
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 = 4:先OP未約定メールの場合、更新する。
            if (WEB3MailAssortmentDivDef.FUTURES_OPTION_NOT_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの場合、0
                    l_hmMainAccounts.put("ifo_order_unexec_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != nullの場合、1
                    l_hmMainAccounts.put("ifo_order_unexec_mail_flag", BooleanEnum.TRUE);
                }
            }

            //案内メール送信フラグ
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 = 6:案内メールの場合、更新する。
            if (WEB3MailAssortmentDivDef.GUIDE_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null)
                {
                    //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの場合、0
                    l_hmMainAccounts.put("information_mail_flag", BooleanEnum.FALSE);
                }
                else
                {
                    //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != nullの場合、1
                    l_hmMainAccounts.put("information_mail_flag", BooleanEnum.TRUE);
                }
            }

            //更新日時
            //処理日時　@※TradingSystem.getSystemTimestamp() 
            l_hmMainAccounts.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

            //案内メール送信フラグ更新者コード
            //案内メール送信フラグ更新日時
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 =
            //6:案内メールの場合、以下判定を実施する。
            if (WEB3MailAssortmentDivDef.GUIDE_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、
                //顧客マスター行.案内メール送信フラグ!=0))の場合、口座コード。
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、 
                //顧客マスター行.案内メール送信フラグ!=0))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getInformationMailFlag()))
                {
                    l_hmMainAccounts.put("inf_mail_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("inf_mail_flg_updated_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、
                // 顧客マスター行.案内メール送信フラグ !=1))の場合、口座コード。
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、 
                //顧客マスター行.案内メール送信フラグ !=1))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getInformationMailFlag()))
                {
                    l_hmMainAccounts.put("inf_mail_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("inf_mail_flg_updated_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            //株式約定メール送信フラグ更新者コード
            //株式約定メール送信フラグ更新日時
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 =
            //1:株式約定メールの場合、以下判定を実施する。
            if (WEB3MailAssortmentDivDef.EQUITY_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、
                //顧客マスター行.株式約定メール送信フラグ!=0))の場合、口座コード。
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、
                //顧客マスター行.株式約定メール送信フラグ!=0))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getEquityOrderExeMailFlag()))
                {
                    l_hmMainAccounts.put("eq_exe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("eq_exe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、
                //顧客マスター行.株式約定メール送信フラグ!=1))の場合、口座コード。
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、 
                //顧客マスター行.株式約定メール送信フラグ!=1))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getEquityOrderExeMailFlag()))
                {
                    l_hmMainAccounts.put("eq_exe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("eq_exe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            //株式未約定メール送信フラグ更新者コード
            //株式未約定メール送信フラグ更新日時
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 =  
            //2:株式未約定メールの場合、以下判定を実施する。
            if (WEB3MailAssortmentDivDef.EQUITY_NOT_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、
                //顧客マスター行.株式未約定メール送信フラグ!=0))の場合、口座コード。
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、
                //顧客マスター行.株式未約定メール送信フラグ!=0))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getEquityOrderUnexecMailFlag()))
                {
                    l_hmMainAccounts.put("eq_unexe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("eq_unexe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、
                //顧客マスター行.株式未約定メール送信フラグ !=1))の場合、口座コード。
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、
                //顧客マスター行.株式未約定メール送信フラグ !=1))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getEquityOrderUnexecMailFlag()))
                {
                    l_hmMainAccounts.put("eq_unexe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("eq_unexe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            //先OP約定メール送信フラグ更新者コード
            //先OP約定メール送信フラグ更新日時
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 =
            //3:先OP約定メールの場合、以下判定を実施する。
            if (WEB3MailAssortmentDivDef.FUTURES_OPTION_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、
                //顧客マスター行.先OP約定メール送信フラグ!=0))の場合、口座コード。
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、
                //顧客マスター行.先OP約定メール送信フラグ!=0))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getIfoOrderExecMailFlag()))
                {
                    l_hmMainAccounts.put("ifo_exe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("ifo_exe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、
                //顧客マスター行.先OP約定メール送信フラグ !=1))の場合、口座コード。
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、
                //顧客マスター行.先OP約定メール送信フラグ !=1))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getIfoOrderExecMailFlag()))
                {
                    l_hmMainAccounts.put("ifo_exe_ml_flg_last_updater", l_accountCode);
                    l_hmMainAccounts.put("ifo_exe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            //先OP未約定メール送信フラグ更新者コード
            //先OP未約定メール送信フラグ更新日時
            //リクエストデータ.複数アドレスリスト.メール種別情報.メール種別番号 =
            //4:先OP未約定メールの場合、以下判定を実施する。
            if (WEB3MailAssortmentDivDef.FUTURES_OPTION_NOT_ORDER_MAIL.equals(
                l_accInfoMailAddressTypeUnits[j].mailAddressTypeNo))
            {
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、
                //顧客マスター行.先OP未約定メール送信フラグ!=0))の場合、口座コード。
                //①@リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 = nullの且つ、
                //顧客マスター行.先OP未約定メール送信フラグ!=0))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                if (l_accInfoMailAddressTypeUnits[j].mailAddressNo == null
                    && !BooleanEnum.FALSE.equals(l_mainAccountParams.getIfoOrderUnexecMailFlag()))
                {
                    l_hmMainAccounts.put("ifo_unexe_ml_flg_last_updater",l_accountCode);
                    l_hmMainAccounts.put("ifo_unexe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、
                //顧客マスター行.先OP未約定メール送信フラグ !=1))の場合、口座コード。
                //②リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号 != null且つ、
                //顧客マスター行.先OP未約定メール送信フラグ !=1))の場合、処理日時　@※TradingSystem.getSystemTimestamp() 。
                else if (l_accInfoMailAddressTypeUnits[j].mailAddressNo != null
                    && !BooleanEnum.TRUE.equals(l_mainAccountParams.getIfoOrderUnexecMailFlag()))
                {
                    l_hmMainAccounts.put("ifo_unexe_ml_flg_last_updater",l_accountCode);
                    l_hmMainAccounts.put("ifo_unexe_ml_flg_timestamp",
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                }
            }

            if (l_accInfoMailAddressTypeUnits[j] != null)
            {
                this.updateMailAssortment(l_mainAccount, l_accInfoMailAddressTypeUnits[j]);
            }
        }
        
        if (l_intLengthMailAddressType != 0)
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(
                    (MainAccountPK)l_mainAccountRow.getPrimaryKey(), l_hmMainAccounts);
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
        //1.14 createResponse()
        WEB3AccInfoMailAddressChangeCompleteResponse l_response = 
            (WEB3AccInfoMailAddressChangeCompleteResponse) l_request.createResponse();
        
        if (l_intDuplicationCheckLenth > 0 && l_trader != null && l_intDuplicateCheckExecFlag  == 1)
        {
            l_response.duplicationAddressInfo = l_strDuplicationAddressInfos ;
        }
        
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
     * 　@レコード.プリファ@レンスの値： "1" OR "4" ⇒ 1 <BR>
     * 　@レコード.プリファ@レンスの値： "2" OR "3" ⇒ 2 <BR>
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
        
        //レコード.プリファ@レンスの値： "1" OR "4" ⇒ 1
        else if (WEB3DuplicationMailAddressCheckDef.NO_EXCEPTION.equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION_ADMIN.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }
        
        //レコード.プリファ@レンスの値： "2" OR "3" ⇒ 2
        else if (WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION.equals(l_branchPreferencesRow.getValue()) 
            || WEB3DuplicationMailAddressCheckDef.CREATE_EXCEPTION_CUST.equals(l_branchPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return 2;
        }
        
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (update顧客メールアドレス)<BR>
     * １）リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス区分==<BR>
     * ”2:携帯メールアドレス”<BR>
     * かつリクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス == null の場合、<BR>
     * 以下の条件で「顧客メールアドレステーブル」から検索する。<BR>
     * 　@　@[検索条件]<BR>
     * 　@　@証券会社コード：顧客.証券会社コード<BR>
     * 　@　@部店コード：顧客.部店コード<BR>
     * 　@　@口座コード：顧客.口座コード<BR>
     * 　@　@メールアドレス番号：リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス番号<BR>
     * <BR>
     * 　@検索結果が取得できる場合、<BR>
     * 　@ＤＢ更新を行う。<BR>
     * 　@「メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_delete」参照。<BR>
     * <BR>
     * ２） １）以外の場合、以下の条件で「顧客メールアドレステーブル」から検索する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@証券会社コード：顧客.証券会社コード<BR>
     * 　@　@部店コード：顧客.部店コード<BR>
     * 　@　@口座コード：顧客.口座コード<BR>
     * 　@　@メールアドレス番号：リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス番号<BR>
     * <BR>
     * 　@２－１） 検索結果が取得できる場合、<BR>
     * 　@ＤＢ更新を行う。<BR>
     * 　@「メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_update」参照。<BR>
     * <BR>
     * 　@２－２） 検索結果が取得できない場合、<BR>
     * 　@ＤＢ更新を行う。<BR>
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

            //１）リクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス区分==”2:携帯メールアドレス”
            //かつリクエストデータ.複数アドレスリスト.メールアドレス情報.メールアドレス == null の場合、
            if (WEB3AddressDivDef.MOBILE_MAIL_ADDRESS.equals(l_mailAddressInfo.mailAddressDiv)
                && l_mailAddressInfo.mailAddress == null)
            {
                if (l_lisAccountMailAddressRows.size() == 1)
                {
                    //検索結果が取得できる場合、
                    //「メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_delete」参照。
                    AccountMailAddressPK l_accountMailAddressPK =
                        new AccountMailAddressPK(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            Long.parseLong(l_mailAddressInfo.mailAddressNo));
    
                    l_queryProcessor.doDeleteQuery(l_accountMailAddressPK);
                    log.exiting(STR_METHOD_NAME);
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
                    //「メールアドレス変更_顧客メールアドレステーブル.xls#顧客メールアドレステーブル_update」参照。
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
                        l_map.put("email_last_updater", l_strAccountCode);
                        l_map.put("email_last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());
                    }
                    //TradingSystem.getSystemTimestamp() 。
                    l_map.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_queryProcessor.doUpdateAllQuery(
                        AccountMailAddressRow.TYPE,
                        l_strQuery,
                        l_objQuery,
                        l_map);
                    log.exiting(STR_METHOD_NAME);
                }
                else
                {
                    //２－２） 検索結果が取得できない場合、
                    //顧客メールアドレステーブル_insert
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
                    //口座コード
                    l_accountMailAddressParams.setEmailLastUpdater(l_strAccountCode);
                    //TradingSystem.getSystemTimestamp() 。
                    l_accountMailAddressParams.setEmailLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_accountMailAddressParams.setCreatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_accountMailAddressParams.setLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_queryProcessor.doInsertQuery(l_accountMailAddressParams);
                    log.exiting(STR_METHOD_NAME);
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
    }

    /**
     * (updateメール種別)<BR>
     * １）リクエストデータ.複数アドレスリスト.メール種別情報[i]メールアドレス番号 == null の場合、<BR>
     * 　@以下の条件で「メール種別情報テーブル」から検索する。<BR>
     * 　@　@[検索条件]<BR>
     * 　@　@証券会社コード：顧客.証券会社コード<BR>
     * 　@　@部店コード：顧客.部店コード<BR>
     * 　@　@口座コード：顧客.口座コード<BR>
     * 　@　@メール種別区分：リクエストデータ.複数アドレスリスト.メール種別情報.メール種別区分<BR>
     * <BR>
     * 　@検索結果が取得できる場合、<BR>
     * 　@ＤＢ更新を行う。<BR>
     * 　@「メールアドレス変更_メール種別テーブル.xls#メール種別テーブル_delete」参照。<BR>
     * <BR>
     * ２）リクエストデータ.複数アドレスリスト.メール種別情報[i]メールアドレス番号 != null の場合、<BR>
     * 　@以下の条件で「メール種別情報テーブル」から検索する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@証券会社コード：顧客.証券会社コード<BR>
     * 　@　@部店コード：顧客.部店コード<BR>
     * 　@　@口座コード：顧客.口座コード<BR>
     * 　@　@メール種別区分：リクエストデータ.複数アドレスリスト.メール種別情報.メール種別区分<BR>
     * <BR>
     * 　@２－１）検索結果が取得できる場合、<BR>
     * 　@ＤＢ更新を行う。<BR>
     * 　@「メールアドレス変更_メール種別テーブル.xls#メール種別テーブル_update」参照。<BR>
     * <BR>
     * 　@２－２）検索結果が取得できない場合、<BR>
     * 　@ＤＢ更新を行う。<BR>
     * 　@「メールアドレス変更_メール種別テーブル.xls#メール種別テーブル_insert」参照。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_mailAddressTypeUnit - (メール種別情報)<BR>
     * メール種別情報<BR>
     * @@throws WEB3BaseException
     */
    protected void updateMailAssortment(
        WEB3GentradeMainAccount l_mainAccount,
        WEB3AccInfoMailAddressTypeUnit l_mailAddressTypeUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateMailAssortment("
            + "WEB3GentradeMainAccount, WEB3AccInfoMailAddressTypeUnit)";
        log.entering(STR_METHOD_NAME);

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
            + " and account_code = ? ";

        l_strQuery += " and mail_assortment_div = ? ";

        Object[] l_objQuery = null;

        l_objQuery = new Object[]{
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode,
            l_mailAddressTypeUnit.mailAddressTypeNo};

        List l_lisMailAssortmentRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisMailAssortmentRows = l_queryProcessor.doFindAllQuery(
                MailAssortmentRow.TYPE,
                l_strQuery,
                l_objQuery);

            //リクエストデータ.複数アドレスリスト.メール種別情報[i]メールアドレス番号 == null の場合、
            if (l_mailAddressTypeUnit.mailAddressNo == null)
            {
                if (l_lisMailAssortmentRows.size() > 0)
                {
                    int l_intSize = l_lisMailAssortmentRows.size();
                    for (int i = 0; i < l_intSize; i++)
                    {
                        MailAssortmentPK l_mailAssortmentPK = new MailAssortmentPK(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            ((MailAssortmentRow)l_lisMailAssortmentRows.get(i)).getEmailAddressNumber(),
                            l_mailAddressTypeUnit.mailAddressTypeNo);
                        l_queryProcessor.doDeleteQuery(l_mailAssortmentPK);
                        log.exiting(STR_METHOD_NAME);
                    }
                }
            }
            //リクエストデータ.複数アドレスリスト.メール種別情報[i]メールアドレス番号 != null の場合
            else
            {
                if (l_lisMailAssortmentRows.size() > 0)
                {
                    int l_intSize = l_lisMailAssortmentRows.size();
                    for (int i = 0; i < l_intSize; i++)
                    {
                        MailAssortmentRow l_mailAssortmentRow =
                            (MailAssortmentRow)l_lisMailAssortmentRows.get(i);

                        MailAssortmentPK l_mailAssortmentPK = new MailAssortmentPK(
                            l_strInstitutionCode,
                            l_strBranchCode,
                            l_strAccountCode,
                            l_mailAssortmentRow.getEmailAddressNumber(),
                            l_mailAddressTypeUnit.mailAddressTypeNo);
                        l_queryProcessor.doDeleteQuery(l_mailAssortmentPK);
    
                        MailAssortmentParams l_mailAssortmentParams = new MailAssortmentParams(l_mailAssortmentRow);
                        //メール種別行.メールアドレス番号 !=リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号
                        //且つリクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号！＝nullの場合
                        //リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号。
                        //上記以外は更新無し。
                        //メール種別行.メールアドレス番号 !=
                        //リクエストデータ.複数アドレスリスト.メール種別情報.メールアドレス番号の場合、口座コード。
                        //上記以外は更新無し。
                        if (!WEB3Toolkit.isEquals(
                            l_mailAddressTypeUnit.mailAddressNo, l_mailAssortmentRow.getEmailAddressNumber() + ""))
                        {
                            l_mailAssortmentParams.setEmailAddressNumber(new Long(l_mailAddressTypeUnit.mailAddressNo).longValue());
                            l_mailAssortmentParams.setLastUpdater(l_strAccountCode);
                        }
    
                        l_mailAssortmentParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
                        l_queryProcessor.doInsertQuery(l_mailAssortmentParams);
                    }
                }
                else
                {
                    MailAssortmentParams l_mailAssortmentParams =
                        new MailAssortmentParams();
                    l_mailAssortmentParams.setInstitutionCode(l_strInstitutionCode);
                    l_mailAssortmentParams.setBranchCode(l_strBranchCode);
                    l_mailAssortmentParams.setAccountCode(l_strAccountCode);
                    l_mailAssortmentParams.setEmailAddressNumber(
                        Long.parseLong(l_mailAddressTypeUnit.mailAddressNo));
                    l_mailAssortmentParams.setMailAssortmentDiv(l_mailAddressTypeUnit.mailAddressTypeNo);
                    l_mailAssortmentParams.setLastUpdater(l_strAccountCode);
                    l_mailAssortmentParams.setCreatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_mailAssortmentParams.setLastUpdatedTimestamp(
                        GtlUtils.getTradingSystem().getSystemTimestamp());
                    l_queryProcessor.doInsertQuery(l_mailAssortmentParams);
                    log.exiting(STR_METHOD_NAME);
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
    }
}
@
