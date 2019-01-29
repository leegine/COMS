head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座変更サービスImpl(WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
                   2005/12/23 鄭徳懇 (中訊) 仕様変更No.073
                   2006/02/03 王維（日本中訊）仕様変更No.085
                   2006/02/08 呉艶飛 (中訊) ＤＢレイアウトNo.012
                   2006/09/11 車進　@ (中訊) 仕様変更管理No.123
                   2006/09/13 車進　@ (中訊) 仕様変更管理No.127
                   2006/09/14 車進　@ (中訊) 仕様変更管理No.128
                   2006/10/30 趙林鵬 (中訊) モデル134
                   2006/11/10 何文敏 (中訊) 実装の問題No.003
                   2006/11/10 周捷 (中訊) モデル142
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExclusiveUseAccountStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.ExclusiveUseAccountCondRow;
import webbroker3.gentrade.data.ExclusiveUseAccountDao;
import webbroker3.gentrade.data.ExclusiveUseAccountPK;
import webbroker3.gentrade.data.ExclusiveUseAccountParams;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.gentrade.data.FinInstitutionBankDao;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報専用振込先口座変更サービスImpl)<BR>
 * 管理者お客様情報専用振込先口座変更サービス実装クラス<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl
    extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoExclusiveTransferAccountChangeService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl.class);

    /**
     * @@roseuid 418F3A08033C
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl()
    {

    }

    /**
     * 専用振込先口座変更処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報専用振込先口座変更<BR>
     * 入力ﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報専用振込先口座変更<BR>
     * 確認ﾘｸｴｽﾄの場合 <BR>
     * 　@－validate変更()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報専用振込先口座変更<BR>
     * 完了ﾘｸｴｽﾄの場合 <BR>
     * 　@－submit変更()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415CC5690116
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //引数のリクエストデータが、管理者お客様情報専用振込先口座変更入力ﾘｸｴｽﾄの場合
        if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest)
        {
            
            l_response = this.getInputScreen((WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報専用振込先口座変更確認ﾘｸｴｽﾄの場合
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest)
        {
            
            l_response = this.validateChange((WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報専用振込先口座変更完了ﾘｸｴｽﾄの場合 
        else if (l_request instanceof WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest)
        {
            
            l_response = this.submitChange((WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest) l_request);
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
     * 専用振込先口座変更入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（専用振込先口座変更）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座変更入力ﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B5CA30366
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse getInputScreen(WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);

        //1.4) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.5) get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
            l_request.branchCode, 
            l_request.accountCode);

        //1.6) validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);

        //1.7) createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更)<BR>
     * 専用振込先口座変更確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（専用振込先口座変更）validate変更」参照。 <BR>
     *  ========================================================<BR>
     * シーケンス図「管理者お客様情報（専用振込先口座変更）validate変更」参照。<BR>
     *  1.2.1.isExist金融機@関(String, String)<BR>
     * 　@　@存在しない場合は、「銀行データ不整合」エラーをスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_01314<BR> 
     * <BR>
     *  1.9.2.1口座番号は既に使用されています。」（BUSINESS_ERROR_02640）の例外をスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02640<BR> 
     *  ========================================================<BR>
     * @@param l_request - 管理者お客様情報専用振込先口座変更確認ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse
     * @@roseuid 415CC5C3025E
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse validateChange(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        if ( !l_request.exclusiveTransferAccountDeleteFlag )
        {
            //1.2.1) 1.2.1.isExist金融機@関(String, String)
            //存在しない場合は、「銀行データ不整合」エラーをスローする。
            if (!isExistFinInstitution(l_request.changedAccountInfo.financialInstitutionCode, 
                l_request.changedAccountInfo.financialBranchCode))
            {
                log.debug("金融機@関が存在しない。");
                log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "金融機@関が存在しない。");
            }
        }

        //1.3) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);

        //1.5) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.6) get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
            l_request.branchCode, 
            l_request.accountCode);

        //1.7) validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);

        //1.8) 自動採番の場合（リクエストデータ.自動採番フラグ == true）
        if (l_request.codeAutoFlag)
        {
            // 1.8.1 専用振込先口座条件テーブルより、未使用の口座番号を取得する。 
            //[get未使用口座番号()に指定する引数] 
            // 証券会社コード： 管理者.get証券会社コード()の戻り値 
            // 銀行コード： リクエストデータ.金融機@関コード 
            // 支店コード： リクエストデータ.支店コード 
            // ロックフラグ： false
            getUnusedAccountCode(
                l_administrator.getInstitutionCode(), 
                l_request.changedAccountInfo.financialInstitutionCode, 
                l_request.changedAccountInfo.financialBranchCode, 
                false);   
        }
        
        //1.9 )手動採番で口座番号がnull以外の場合（リクエストデータ.自動採番フラグ == false 
        //&& リクエストデータ.専用振込先口座削除フラグ == false 
        //&& リクエストデータ.口座番号 != null）
        if (!l_request.codeAutoFlag && 
            !l_request.exclusiveTransferAccountDeleteFlag && 
            l_request.changedAccountInfo.financialAccountCode != null)
        {
            //1.9.1)口座番号が専用振込先口座条件テーブルで使用済みかどうかをチェックする。 
            //[get口座条件テーブルレコード()に指定する引数] 
            //証券会社コード： 管理者.get証券会社コード()の戻り値 
            //銀行コード： リクエストデータ.金融機@関コード 
            //支店コード： リクエストデータ.支店コード 
            //口座番号： リクエストデータ.口座番号 
            //ロックフラグ： false
            boolean l_blnUpdate = isFinAccountNoUpdatePossible(
                l_administrator.getInstitutionCode(), 
                l_request.changedAccountInfo.financialInstitutionCode, 
                l_request.changedAccountInfo.financialBranchCode, 
                l_request.changedAccountInfo.financialAccountCode,
                false);
            
            //1.9.2口座番号が既に使用済みの場合（is口座番号更新可能() == false）、例外をスローする。
            if (!l_blnUpdate )
            {
                //口座番号が既に使用済みの場合（is口座番号更新可能() == false）、 
                log.exiting(STR_METHOD_NAME);
                //1.9.2.1口座番号は既に使用されています。」（BUSINESS_ERROR_02640）の例外をスローする。
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02640,
                    this.getClass().getName()+ STR_METHOD_NAME);                 
            }
        }
        
        //1.10) createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
 
    /**
     * (submit変更)<BR>
     * 専用振込先口座変更完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（専用振込先口座変更）submit変更」参照。 <BR>
     *  ========================================================<BR>
     * シーケンス図「管理者お客様情報（専用振込先口座変更）submit変更」参照。<BR>
     *  1.2.1.isExist金融機@関(String, String)<BR>
     * 　@　@存在しない場合は、「銀行データ不整合」エラーをスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_01314<BR> 
     * <BR>
     *  1.11.2.2 口座番号が使用済みの場合（is口座番号更新可能() == false）<BR>
     * 　@「口座番号は既に使用されています。」（BUSINESS_ERROR_02640）の例外をスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02640<BR> 
     *  ========================================================<BR>
     * @@param l_request - 管理者お客様情報専用振込先口座変更完了ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse
     * @@roseuid 415CC5C3026E
     */
    protected WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse submitChange(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();
               
        if ( !l_request.exclusiveTransferAccountDeleteFlag )
        {
            //1.2.1) isExist金融機@関(String, String) 
            //存在しない場合は、「銀行データ不整合」エラーをスローする。
            if (!isExistFinInstitution(l_request.changedAccountInfo.financialInstitutionCode, 
                l_request.changedAccountInfo.financialBranchCode))
            {
                log.debug("金融機@関が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "金融機@関が存在しない。");
            }
        }
        
        //1.3) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRANSFER_ACCOUNT, true);

        //1.5) validate取引パスワード(String)
        l_administrator.validateTradingPassword(l_request.password);

        //1.6) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.7) get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);

        //1.8) validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);

        String l_strAccountCode = null;
        //1.9) get管理者コード( )
        try
        {
            String l_strAdministratorCode = l_administrator.getAdministratorCode();
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.10) 削除の場合（リクエストデータ.専用振込先口座削除フラグ == true）
            if (l_request.exclusiveTransferAccountDeleteFlag)
            {
                //1.10.1) doDeleteQuery(PrimaryKey)
                ExclusiveUseAccountPK l_exclusiveUseAccountPK = new ExclusiveUseAccountPK(l_mainAccount.getAccountId());
                l_queryProcessor.doDeleteQuery(l_exclusiveUseAccountPK);
            }
            
            //1.11) 削除でない場合（リクエストデータ.専用振込先口座削除フラグ == false）
            else
            { 
                //1.11.1)自動採番の場合（リクエストデータ.自動採番フラグ==true）
                if (l_request.codeAutoFlag)
                {
                    //1.11.1.1)専用振込先口座条件テーブルより、未使用の口座番号を取得する。 
                    //[get未使用口座番号()に指定する引数] 
                    // 証券会社コード：管理者.get証券会社コード()の戻り値 
                    // 銀行コード： リクエストデータ.金融機@関コード 
                    //支店コード： リクエストデータ.支店コード 
                    // ロックフラグ： true
                    l_strAccountCode = getUnusedAccountCode(l_administrator.getInstitutionCode(), 
                        l_request.changedAccountInfo.financialInstitutionCode, 
                        l_request.changedAccountInfo.financialBranchCode, 
                        true);
                }
                
                //1.11.2 手動採番で口座番号がnull以外の場合（リクエストデータ.自動採番フラグ == false && リクエストデータ.口座番号 != null）
                if (!l_request.codeAutoFlag && l_request.changedAccountInfo.financialAccountCode != null)
                {
                    //1.11.2.1口座番号が専用振込先口座条件テーブルで使用済みかどうかをチェックする。 
                    //[get口座条件テーブルレコード()に指定する引数] 
                    // 証券会社コード： 管理者.get証券会社コード()の戻り値 
                    // 銀行コード： リクエストデータ.金融機@関コード 
                    // 支店コード： リクエストデータ.支店コード 
                    // 口座番号： リクエストデータ.口座番号 
                    // ロックフラグ： true
                    boolean l_blnUpdate = isFinAccountNoUpdatePossible(
                        l_administrator.getInstitutionCode(), 
                        l_request.changedAccountInfo.financialInstitutionCode, 
                        l_request.changedAccountInfo.financialBranchCode, 
                        l_request.changedAccountInfo.financialAccountCode,
                        true);
                    l_strAccountCode = l_request.changedAccountInfo.financialAccountCode;
                    
                    //1.11.2.2 口座番号が使用済みの場合（is口座番号更新可能() == false）
                    //「口座番号は既に使用されています。」（BUSINESS_ERROR_02640）の例外をスローする。
                    if (!l_blnUpdate )
                    {
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02640,
                            this.getClass().getName()+ STR_METHOD_NAME);                 
                    }

                }
                
                Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
                
                //1.11.3) isExist専用振込先口座(long)
                if (this.isExistExclusiveTransferAccount(l_mainAccount.getAccountId()))
                { 
                    //1.11.4.1) doUpdateQuery(Row)
                    ExclusiveUseAccountParams l_exclusiveUseAccountParams =
                        new ExclusiveUseAccountParams(
                            ExclusiveUseAccountDao.findRowByPk(l_mainAccount.getAccountId()));
                     
                    l_exclusiveUseAccountParams.setAccountId(l_mainAccount.getAccountId());
                    l_exclusiveUseAccountParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                    l_exclusiveUseAccountParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());
                    l_exclusiveUseAccountParams.setAccountCode(l_mainAccount.getAccountCode());
                    l_exclusiveUseAccountParams.setFinInstitutionName(l_request.changedAccountInfo.financialInstitutionName);
                    l_exclusiveUseAccountParams.setFinBranchName(l_request.changedAccountInfo.financialBranchName);
                    l_exclusiveUseAccountParams.setFinBranchCode(l_request.changedAccountInfo.financialBranchCode);
                    l_exclusiveUseAccountParams.setFinAccountTypeName(l_request.changedAccountInfo.financialAccountTypeName);
                    l_exclusiveUseAccountParams.setFinAccountNo(l_strAccountCode);
                    l_exclusiveUseAccountParams.setFinAccountName(l_request.changedAccountInfo.financialAccountName);
                    l_exclusiveUseAccountParams.setLastUpdater(l_strAdministratorCode);
                    l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setFinInstitutionCode(l_request.changedAccountInfo.financialInstitutionCode);
                    l_queryProcessor.doUpdateQuery(l_exclusiveUseAccountParams);
                }
                //1.11.5口座IDのレコードが存在しない場合（isExist専用振込先口座() == false）
                else
                {
                    
                    //1.11.5.1) doInsertQuery(Row)    
                    ExclusiveUseAccountParams l_exclusiveUseAccountParams = new ExclusiveUseAccountParams();

                    l_exclusiveUseAccountParams.setAccountId(l_mainAccount.getAccountId());
                    l_exclusiveUseAccountParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
                    l_exclusiveUseAccountParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());
                    l_exclusiveUseAccountParams.setAccountCode(l_mainAccount.getAccountCode());
                    l_exclusiveUseAccountParams.setFinInstitutionName(l_request.changedAccountInfo.financialInstitutionName);
                    l_exclusiveUseAccountParams.setFinBranchName(l_request.changedAccountInfo.financialBranchName);
                    l_exclusiveUseAccountParams.setFinBranchCode(l_request.changedAccountInfo.financialBranchCode);
                    l_exclusiveUseAccountParams.setFinAccountTypeName(l_request.changedAccountInfo.financialAccountTypeName);
                    l_exclusiveUseAccountParams.setFinAccountNo(l_strAccountCode);
                    l_exclusiveUseAccountParams.setFinAccountName(l_request.changedAccountInfo.financialAccountName);
                    l_exclusiveUseAccountParams.setLastUpdater(l_strAdministratorCode);
                    l_exclusiveUseAccountParams.setCreatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
                    l_exclusiveUseAccountParams.setFinInstitutionCode(l_request.changedAccountInfo.financialInstitutionCode);
                    l_queryProcessor.doInsertQuery(l_exclusiveUseAccountParams);
               
                }
                
                //1.11.6更新する口座番号がnull以外の場合。 
                //（リクエストデータ.自動採番 == true 
                // 又は  
                //リクエストデータ.自動採番 == false && リクエストデータ.口座番号 != null）
                if (l_request.codeAutoFlag || 
                    (!l_request.codeAutoFlag && l_request.changedAccountInfo.financialAccountCode != null))
                {
                    //1.11.6.1update専用振込先口座条件テーブル(String, String, String, String, String)
                    //専用振込先口座条件テーブルの更新（ステータスを使用済み）を行う。
                    //[update専用振込先口座条件テーブル()に指定する引数]
                    //証券会社コード： 管理者.get証券会社コード()の戻り値
                    //銀行コード： リクエストデータ.金融機@関コード
                    //支店コード： リクエストデータ.支店コード
                    //口座番号：
                    //（自動採番の場合）get未使用口座番号()の戻り値
                    //（手動採番の場合）リクエストデータ.口座番号
                    //管理者コード：管理者.get管理者コード()の戻り値
                    updateExclusiveUseAccountCond(l_administrator.getInstitutionCode(),
                        l_request.changedAccountInfo.financialInstitutionCode,
                        l_request.changedAccountInfo.financialBranchCode,
                        l_strAccountCode,
                        l_strAdministratorCode);

                }
            }
        }
        catch (DataFindException l_ex)
        {
            
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    
        //1.12 createResponse( )
        WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse l_response =
            (WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse) l_request.createResponse();

        //1.13プロパティセット
        //リクエストデータ.自動採番フラグ==true && リクエストデータ.専用振込先口座削除フラグ==falseの場合
        //    口座番号 = get未使用口座番号()の戻り値
        //リクエストデータ.自動採番フラグ==false && リクエストデータ.専用振込先口座削除フラグ==falseの場合
        //   口座番号 = リクエストデータ.口座番号
        //リクエストデータ.専用振込先口座削除フラグ==trueの場合
        //口座番号 = null
        l_response.accountCode = l_strAccountCode;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (isExist専用振込先口座)<BR>
     * 専用振込先口座テーブルに既存行が存在するかを判定する。<BR>
     * <BR>
     * １）　@専用振込先口座テーブルより、指定の口座IDに該当する行を取得する。<BR>
     * ２）　@行が取得できればtrue、取得できなければfalseを返却する。<BR>
     * @@param l_lngAccountId - 口座ID
     * @@return boolean
     * @@roseuid 4161161B00FF
     */
    protected boolean isExistExclusiveTransferAccount(long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExistExclusiveTransferAccount(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            ExclusiveUseAccountDao.findRowByPk(l_lngAccountId); 

        }
        catch (DataFindException l_ex)
        {
            
            return false;
        }
        catch (DataNetworkException l_ex)
        {
            
            log.error(" 予期しないシステムエラーが発生しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (isExist金融機@関)<BR>
     * 金融機@関（銀行）マスタに既存行が存在するかを判定する。<BR>
     * <BR>
     * １）　@金融機@関（銀行）マスタより、指定の銀行コードと支店コードに <BR>
     * 　@　@　@該当する行を取得する。 <BR>
     * ２）　@行が取得できればtrue、取得できなければfalseを返却する。<BR>
     * @@param l_strFinInstitutionCode - 銀行コード
     * @@param l_strBranchCode - 支店コード
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isExistFinInstitution(String l_strFinInstitutionCode, String l_strBranchCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExistFinInstitution(String, String)";
        log.entering(STR_METHOD_NAME);
        //１）　@金融機@関（銀行）マスタより、指定の銀行コードと支店コードに該当する行を取得する。
        FinInstitutionBankRow l_finInstitutionBankRow = null;
        try
        {
            l_finInstitutionBankRow = FinInstitutionBankDao.findRowByPk(
                l_strFinInstitutionCode,
                l_strBranchCode);
            
            //２）　@行が取得できればtrue、取得できなければfalseを返却する。
            if (l_finInstitutionBankRow != null) 
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataFindException l_ex)
        {
            //例外をスロー
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataException l_ex)
        {
            //例外をスロー
            log.debug("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBへのアクセスに失敗しました。");
        }
    }
    
    /**
     * (get未使用口座番号)<BR>
     * 専用振込先口座条件テーブルより未使用口座番号を取得する。<BR>
     * <BR>
     * 　@　@※未使用口座の取得条件は以下の通り。<BR>
     * 　@　@　@・該当の証券会社コード、銀行コード、支店コード、ステータス='0'（未使用）の口座番号を取得する。<BR>
     * 　@　@　@・作成日時の古いものから取得する<BR>
     * 　@　@　@・作成日時の同じ未使用口座が複数存在する場合は口座番号の小さいものから取得する。<BR>
     * 　@　@　@・（引数）ロックフラグ==trueの場合は"FOR UPDATE NOWAIT"を付加する。<BR>
     * 　@　@　@<BR>
     * 　@　@　@　@　@　@条件サンプル↓<BR>
     * 　@　@　@　@　@　@[select * from (select fin_account_no from exclusive_use_account_cond<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@where institution_code=（引数）証券会社コード<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@and fin_institution_code=（引数）銀行コード<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@and fin_branch_code=（引数）支店コード<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@and status='0'<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@order by created_timestamp, fin_account_no)<BR>
     * 　@　@　@　@　@　@where rownum=1 for update nowait]<BR>
     * <BR>
     * <BR>
     * １）　@Object配列を生成し、以下を要素に設定<BR>
     * 　@　@　@Object[0]（引数）証券会社コード<BR>
     * 　@　@　@Object[1]（引数）銀行コード<BR>
     * 　@　@　@Object[2]（引数）支店コード<BR>
     * <BR>
     * ２）　@QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     * 　@　@　@　@[doFindAllQuery()にセットするパラメータ] <BR>
     * 　@　@　@　@　@arg0：　@専用振込先口座条件テーブルRowType<BR>
     * 　@　@　@　@　@arg1：　@"institution_code=?<BR>
     * 　@　@　@　@　@　@　@　@　@　@and fin_institution_code=?<BR>
     * 　@　@　@　@　@　@　@　@　@　@and fin_branch_code=?<BR>
     * 　@　@　@　@　@　@　@　@　@　@and status='0'"<BR>
     * 　@　@　@　@　@arg2：　@"created_timestamp, fin_account_no"<BR>
     * 　@　@　@　@　@arg3：　@"FOR UPDATE NOWAIT"（（引数）ロックフラグ==trueの場合追加する。）<BR>
     * 　@　@　@　@　@arg4：　@１）で作成したObject配列<BR>
     * 　@　@　@　@　@arg5：　@1<BR>
     * 　@　@　@　@　@arg6：　@0<BR>
     * <BR>
     * ２）　@１）の戻り値ListPageより口座番号を取得し返却する。<BR>
     * 　@　@ListPageの長さ==0 OR ListPage==nullの場合、<BR>
     * 　@　@「未使用の専用振込先口座番号が取得できません。」（BUSINESS_ERROR_02639）の例外を生成する。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02639 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strFinInstitutionCode - (銀行コード)<BR>
     * 銀行コード。<BR>
     * @@param l_strFinBranchCode - (支店コード)<BR>
     * 支店コード。<BR>
     * @@param l_bolLockFlag - (ロックフラグ)<BR>
     * 行ロックフラグ。<BR>
     * <BR>
     * true…行ロックを行う。<BR>
     * false…行ロックを行わない。<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44FBBAE4030E
     */
    protected String getUnusedAccountCode(String l_strInstitutionCode, 
        String l_strFinInstitutionCode, 
        String l_strFinBranchCode, 
        boolean l_blnLockFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUnusedAccountCode(String, " + 
            "String, String, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null || l_strFinInstitutionCode == null || 
            l_strFinBranchCode == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_strInstitutionCode] = " + l_strInstitutionCode +
                "[l_strFinInstitutionCode] = " + l_strFinInstitutionCode +
                "[l_strFinBranchCode] = " + l_strFinBranchCode
                );
        }
        
        //１）　@Object配列を生成し、以下を要素に設定 
        //Object[0]（引数）証券会社コード 
        //Object[1]（引数）銀行コード 
        //Object[2]（引数）支店コード
        Object[] l_objWhere = { 
            l_strInstitutionCode, 
            l_strFinInstitutionCode, 
            l_strFinBranchCode, 
            WEB3ExclusiveUseAccountStatusDef.UNUSED_RECORD
            };
        
        //２）　@QueryProcessor.doFindAllQuery()メソッドをコールする。  
        //[doFindAllQuery()にセットするパラメータ]
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            //arg1：　@"institution_code=? 
            //and fin_institution_code=? 
            //and fin_branch_code=? 
            //and status='0'" 
            String l_strArg1 = " institution_code = ? and fin_institution_code = ? " + 
                " and fin_branch_code = ? and status = ? ";
            
            //arg2：　@"created_timestamp, fin_account_no"
            String l_strArg2 = "created_timestamp, fin_account_no";

            // arg3：　@"FOR UPDATE NOWAIT"（（引数）ロックフラグ==trueの場合追加する。） 
            String l_strArg3 = null;
            if (l_blnLockFlag)
            {
                l_strArg3 = "FOR UPDATE NOWAIT";
            }
            // arg4：　@１）で作成したObject配列 
            // arg5：　@1
            // arg6：　@0 
            List l_lisRecords = l_processor.doFindAllQuery(
                ExclusiveUseAccountCondRow.TYPE,
                l_strArg1, 
                l_strArg2, 
                l_strArg3, 
                l_objWhere, 
                1, 
                0
                    );
             //２） １）の戻り値ListPageより口座番号を取得し返却する。 
             //ListPageの長さ==0 OR ListPage==nullの場合、 
             //「未使用の専用振込先口座番号が取得できません。」（BUSINESS_ERROR_02639）の例外を生成する。   
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02639,
                    this.getClass().getName()+ STR_METHOD_NAME);                       
            }

            ExclusiveUseAccountCondRow l_exclusiveUseAccountCondRow = (ExclusiveUseAccountCondRow)
                l_lisRecords.get(0);
            
            String l_strFinAccountNo = l_exclusiveUseAccountCondRow.getFinAccountNo();
                
            log.exiting(STR_METHOD_NAME);
            return l_strFinAccountNo;     
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (is口座番号更新可能)<BR>
     * 専用振込先口座テーブルに該当口座が存在するかを判定し、  <BR>
     * 存在しない場合かつ、（引数）ロックフラグ==trueの場合は  <BR>
     * 専用振込先口座条件テーブル該当口座レコードの行のロックを行う。 <BR>
     * <BR>
     * １）　@Object配列を生成し、以下を要素に設定 <BR>
     *        Object[0]（引数）証券会社コード <BR>
     *        Object[1]（引数）銀行コード <BR>
     *        Object[2]（引数）支店コード <BR>
     *        Object[3]（引数）口座番号 <BR>
     * <BR>
     * ２）　@専用振込先口座テーブルに口座番号が存在するか検索する。 <BR>
     *       QueryProcessor.doFindAllQuery()メソッドをコールする。  <BR>
     * <BR>
     *       [doFindAllQuery()にセットするパラメータ]  <BR>
     * 　@　@     arg0：　@専用振込先口座テーブルRowType  <BR>
     * 　@　@     arg1：　@"institution_code=? <BR>
     *                     and fin_institution_code=? <BR>
     *                     and fin_branch_code=? <BR>
     *                     and fin_account_no=?" <BR>
     * 　@　@     arg2：　@１）で作成したObject配列 <BR>
     * <BR>
     * ３） ２）の戻り値Listの長さ > 0 の場合該当口座使用中の為、false（更新不可）を返却する。 <BR>
     * <BR>
     * ４） ２）の戻り値Listの長さ==0 OR List==nullの場合、かつ（引数）ロックフラグ==trueの場合。  <BR>
     * <BR>
     *   ４－１） 専用振込先口座条件テーブル上に該当口座レコードが存在する場合は行のロックを行う。  <BR>
     * <BR>
     *   　@              QueryProcessor.doFindAllQuery()メソッドをコールする。   <BR>
     *     　@            [doFindAllQuery()にセットするパラメータ]   <BR>
     * 　@  　@     　@        arg0：　@専用振込先口座条件テーブルRowType   <BR>
     * 　@　@       　@        arg1：　@"institution_code=?  <BR>
     *      　@                         and fin_institution_code=?  <BR>
     *        　@                       and fin_branch_code=?  <BR>
     *                     　@          and fin_account_no=?"  <BR>
     * 　@  　@        　@     arg2：　@"FOR UPDATE NOWAIT"  <BR>
     * 　@  　@        　@     arg3：　@１）で作成したObject配列  <BR>
     * <BR>
     * ５） true（更新可能）を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strFinInstitutionCode - (銀行コード)<BR>
     * 銀行コード。<BR>
     * @@param l_strFinBranchCode - (支店コード）<BR>
     * 支店コード。<BR>
     * @@param l_strFinAccountNo - (口座番号)<BR>
     * 口座番号。<BR>
     * @@param l_bolLockFlag - (ロックフラグ)<BR>
     * 行ロックフラグ。<BR>
     * <BR>
     * true…行ロックを行う。<BR>
     * false…行ロックを行わない。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 44F6A0D90210
     */
    protected boolean isFinAccountNoUpdatePossible(String l_strInstitutionCode, 
        String l_strFinInstitutionCode, 
        String l_strFinBranchCode, 
        String l_strFinAccountNo, 
        boolean l_blnLockFlag)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isFinAccountNoUpdatePossible(String, " + 
            "String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_strInstitutionCode] = " + l_strInstitutionCode
                 );
        }
        
        //１）　@Object配列を生成し、以下を要素に設定 
        //Object[0]（引数）証券会社コード 
        //Object[1]（引数）銀行コード 
        //Object[2]（引数）支店コード 
        //Object[3]（引数）口座番号
        Object[] l_objWhere = { 
            l_strInstitutionCode, 
            l_strFinInstitutionCode, 
            l_strFinBranchCode, 
            l_strFinAccountNo
            };
        
        String l_strArg1 = "institution_code = ? and fin_institution_code = ? " + 
            " and fin_branch_code = ? and fin_account_no = ? ";
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            
            //２）　@専用振込先口座テーブルに口座番号が存在するか検索する。 
            //QueryProcessor.doFindAllQuery()メソッドをコールする。
            //[doFindAllQuery()にセットするパラメータ]  
            //arg0：　@専用振込先口座テーブルRowType 
            //arg1：　@"institution_code=? 
            //and fin_institution_code=? 
            //        and fin_branch_code=?
            //        and fin_account_no=?"
            // arg2：　@１）で作成したObject配列 
            List l_lisRecords = l_processor.doFindAllQuery(ExclusiveUseAccountRow.TYPE, 
                l_strArg1,
                l_objWhere);
            
            //３） ２）の戻り値Listの長さ > 0 の場合該当口座使用中の為、false（更新不可）を返却する。
            if ( l_lisRecords != null && l_lisRecords.size() > 0)
            {   
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            
            //４） ２）の戻り値Listの長さ==0 OR List==nullの場合、かつ（引数）ロックフラグ==trueの場合。
            if ((l_lisRecords == null || l_lisRecords.isEmpty()) && l_blnLockFlag)
            {
                // ４－１） 専用振込先口座条件テーブル上に該当口座レコードが存在する場合は行のロックを行う。  
                // 　@              QueryProcessor.doFindAllQuery()メソッドをコールする。   
                //   　@            [doFindAllQuery()にセットするパラメータ]   
                //  　@     　@        arg0：　@専用振込先口座条件テーブルRowType   
                //　@       　@        arg1：　@"institution_code=?  
                //    　@                         and fin_institution_code=?  
                //      　@                       and fin_branch_code=?  
                //                   　@          and fin_account_no=?"  
                //  　@        　@     arg2：　@"FOR UPDATE NOWAIT"  
                // 　@        　@      arg3：　@１）で作成したObject配列  
                l_processor.doFindAllQuery(
                    ExclusiveUseAccountCondRow.TYPE, 
                    l_strArg1,
                    "FOR UPDATE NOWAIT",
                    l_objWhere);
            }
            
            //５） true（更新可能）を返却する。 
            log.exiting(STR_METHOD_NAME);
            return true;
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
        catch (IllegalStateException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }  
        
    }
    
    /**
     * (update専用振込先口座条件テーブル)<BR>
     * 専用振込先口座条件テーブルのステータス更新を行う。<BR>
     * （更新内容は【ｘTrade】補足資料.DB更新管理者・専用振込先口座変更_専用振込先口座条件テーブル.xls参照）<BR>
     * <BR>
     * １）　@自動採番実施会社かどうかを判定する。<BR>
     * 　@　@　@専用振込先口座条件テーブルから証券会社コードを条件としてレコード件数を取得する。<BR>
     * <BR>
     * ２）　@１）の結果 レコード件数 > 0 の場合、専用振込先口座条件テーブルのステータス更新を行う。<BR>
     * <BR>
     * 　@２－１）　@Object配列を生成し、以下を要素に設定<BR>
     * 　@　@　@　@　@　@Object[0] = （引数）証券会社コード<BR>
     * 　@　@　@　@　@　@Object[1] = （引数）銀行コード<BR>
     * 　@　@　@　@　@　@Object[2] = （引数）支店コード<BR>
     * 　@　@　@　@　@　@Object[3] = （引数）口座番号<BR>
     * <BR>
     * 　@２－２） 専用振込先口座条件テーブルのステータスを更新する。<BR>
     * <BR>
     * 　@　@２－２－１）　@更新内容を「コラム名-値」のペアで示すMapオブジェクトを生成する。<BR>
     * 　@　@　@　@　@　@　@　@　@コラム名： "status" 値： "1" <BR>
     * 　@　@　@　@　@　@　@　@　@コラム名： "last_updater" 値： （引数）管理者コード<BR>
     * 　@　@　@　@　@　@　@　@　@コラム名： "last_updated_timestamp" 値： TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * 　@　@２－２－２）　@QueryProcessor.doUpdateAllQuery()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@[doUpdateAllQuery()にセットするパラメータ] <BR>
     * 　@　@　@　@　@　@　@　@　@　@arg0：　@専用振込先口座条件テーブルRowType <BR>
     * 　@　@　@　@　@　@　@　@　@　@arg1：　@"institution_code=?<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@and fin_institution_code=?<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@and fin_branch_code=?<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@and fin_account_no=?"<BR>
     * 　@　@　@　@　@　@　@　@　@　@arg2：　@２－１）で作成したObject配列<BR>
     * 　@　@　@　@　@　@　@　@　@　@arg3：　@２－２－１）で作成したMap <BR>
     * <BR>
     * ３）　@１）の結果 レコード件数 = 0 の場合、処理なし。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strFinInstitutionCode - (銀行コード)<BR>
     * 銀行コード。<BR>
     * @@param l_strFinBranchCode - (支店コード)<BR>
     * 支店コード。<BR>
     * @@param l_strFinAccountNo - (口座番号)<BR>
     * 口座番号。<BR>
     * @@param l_strAdministratorCode - (管理者コード)<BR>
     * ログイン管理者コード。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44F696170165
     */
    protected void updateExclusiveUseAccountCond(
        String l_strInstitutionCode,
        String l_strFinInstitutionCode,
        String l_strFinBranchCode,
        String l_strFinAccountNo,
        String l_strAdministratorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExclusiveUseAccountCond(String, " + 
            "String, String, String, String)";
            log.entering(STR_METHOD_NAME);
    
        if (l_strInstitutionCode == null || l_strFinInstitutionCode == null || 
            l_strFinBranchCode == null || l_strFinAccountNo == null ||
            l_strAdministratorCode == null)
        {
            log.debug("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_strInstitutionCode] = " + l_strInstitutionCode +
                "[l_strFinInstitutionCode] = " + l_strFinInstitutionCode +
                "[l_strFinBranchCode] = " + l_strFinBranchCode +
                "[l_strFinAccountNo] = " + l_strFinAccountNo +
                "[l_strAdministratorCode] = " + l_strAdministratorCode
                );
        }
        
        //１） 自動採番実施会社かどうかを判定する。 
        //専用振込先口座条件テーブルから証券会社コードを条件としてレコード件数を取得する。
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException

            String l_strCondWhere = "institution_code = ? ";
            Object[] l_objCondWhere = {l_strInstitutionCode};
            
            List l_lisCondRecords = l_processor.doFindAllQuery(
                ExclusiveUseAccountCondRow.TYPE,
                l_strCondWhere,
                null,
                l_objCondWhere
                );
            int l_intCondSize = 0;
            
            if (l_lisCondRecords != null)
            {
                l_intCondSize = l_lisCondRecords.size();
            }
            
            //２） １）の結果 レコード件数 > 0 の場合、専用振込先口座条件テーブルのステータス更新を行う。 
            if (l_intCondSize > 0)
            {
                //２－１）　@Object配列を生成し、以下を要素に設定 
                //Object[0] = （引数）証券会社コード 
                //Object[1] = （引数）銀行コード 
                //Object[2] = （引数）支店コード 
                //Object[3] = （引数）口座番号 
                Object[] l_objUpdWhere = {l_strInstitutionCode, 
                    l_strFinInstitutionCode, 
                    l_strFinBranchCode, 
                    l_strFinAccountNo};

                //２－２） 専用振込先口座条件テーブルのステータスを更新する
                //    ２－２－１） 更新内容を「コラム名-値」のペアで示すMapオブジェクトを生成する。
                //コラム名： "status" 値： "1"
                //コラム名： "last_updater" 値： （引数）管理者コード
                //コラム名： "last_updated_timestamp" 値： TradingSystem.getSystemTimestamp()
                Map l_map = new Hashtable();
                l_map.put("status", WEB3StatusDef.DEALT);
                l_map.put("last_updater", l_strAdministratorCode);
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

                //２－２－２）　@QueryProcessor.doUpdateAllQuery()メソッドをコールする。  
                //[doUpdateAllQuery()にセットするパラメータ]  
                // arg0：　@専用振込先口座条件テーブルRowType  
                // arg1：　@"institution_code=? 
                //              and fin_institution_code=? 
                //              and fin_branch_code=? 
                //              and fin_account_no=?" 
                //  arg2：　@２－１）で作成したObject配列 
                //  arg3：　@２－２－１）で作成したMap 
                String l_strWhere = "institution_code = ? and fin_institution_code = ? " +
                    " and fin_branch_code = ? and fin_account_no = ? ";
                l_processor.doUpdateAllQuery(ExclusiveUseAccountCondRow.TYPE, 
                    l_strWhere, 
                    l_objUpdWhere, 
                    l_map);         
            }
            
            //３） １）の結果 レコード件数 = 0 の場合、処理なし。
            
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
