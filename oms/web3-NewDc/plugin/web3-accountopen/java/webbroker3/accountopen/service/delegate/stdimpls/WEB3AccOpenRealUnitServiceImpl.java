head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRealUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 即日口座開設UnitServiceImpl(WEB3AccOpenRealUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/23 何文敏 (中訊) 仕様変更 モデル No.112
                 : 2006/11/27 何文敏 (中訊) ＤＢ更新仕様  No.024、No.025
                 : 2006/11/29 何文敏(中訊) 新規作成 仕様変更 モデル No.113、114、115
                 : 2006/11/29 何文敏(中訊) 新規作成 仕様変更 モデル No.116、117 ＤＢ更新仕様  No.026
                 : 2006/12/18 車進 　@(中訊) ＤＢ更新仕様  No.027
Revesion History : 2007/07/31 周墨洋 (中訊) 仕様変更・ＤＢ更新仕様 No.030、ＤＢレイアウト No.043
Revesion History : 2007/08/02 周墨洋 (中訊) 仕様変更・ＤＢ更新仕様 No.031
Revesion History : 2007/09/20 張騰宇 (中訊) モデル No.146
Revesion History : 2008/08/01 張少傑 (中訊) ＤＢ更新仕様 No.036
                 : 2009/01/26 三島淳一郎 (SCS) モデル No.162
Revesion History : 2009/08/13 柴双紅 (中訊) 仕様変更 モデル176
Revesion History : 2009/09/04 張騰宇 (中訊) 仕様変更 モデル211
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountManagerParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginGroupMemberParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.PasswordUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.accountopen.WEB3AccOpenBatchBranch;
import webbroker3.accountopen.WEB3AccOpenBranch;
import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenHostAccRegVoucher;
import webbroker3.accountopen.WEB3AccOpenLoginAccountRelation;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.BatchBranchParams;
import webbroker3.accountopen.data.BatchPreferencesRow;
import webbroker3.accountopen.data.ExpAccountOpenDao;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccRegVoucherPK;
import webbroker3.accountopen.data.HostAccRegVoucherParams;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherPK;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherRow;
import webbroker3.accountopen.data.HostAgreeTransVoucherPK;
import webbroker3.accountopen.data.HostAgreeTransVoucherRow;
import webbroker3.accountopen.data.HostBankTransVoucherPK;
import webbroker3.accountopen.data.HostBankTransVoucherRow;
import webbroker3.accountopen.data.HostChargedInfoVoucherPK;
import webbroker3.accountopen.data.HostChargedInfoVoucherRow;
import webbroker3.accountopen.data.HostConditionRegVoucherPK;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.accountopen.data.HostContMrgVoucherPK;
import webbroker3.accountopen.data.HostContMrgVoucherRow;
import webbroker3.accountopen.data.HostFDepositVoucherPK;
import webbroker3.accountopen.data.HostFDepositVoucherRow;
import webbroker3.accountopen.data.HostGpRegVoucherPK;
import webbroker3.accountopen.data.HostGpRegVoucherRow;
import webbroker3.accountopen.data.HostInsiderRegVoucherPK;
import webbroker3.accountopen.data.HostInsiderRegVoucherRow;
import webbroker3.accountopen.data.HostMrfAccVoucherPK;
import webbroker3.accountopen.data.HostMrfAccVoucherRow;
import webbroker3.accountopen.data.HostPostalTransVoucherPK;
import webbroker3.accountopen.data.HostPostalTransVoucherRow;
import webbroker3.accountopen.data.HostRealnameRegVoucherPK;
import webbroker3.accountopen.data.HostRealnameRegVoucherRow;
import webbroker3.accountopen.data.HostStockholderRegVoucherPK;
import webbroker3.accountopen.data.HostStockholderRegVoucherRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountKindDef;
import webbroker3.accountopen.define.WEB3AccOpenLastUpdaterDef;
import webbroker3.accountopen.define.WEB3AccOpenSubAccountTypeDef;
import webbroker3.accountopen.define.WEB3AccOpenTradingPasswordDef;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRealUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.define.WEB3AssetEvaluationDivDef;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3BatchPreferencesBranchCodeDef;
import webbroker3.common.define.WEB3BatchPreferencesValueDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3ExclusiveUseAccountStatusDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HtSettlementWayDef;
import webbroker3.common.define.WEB3InformationMailFlagDef;
import webbroker3.common.define.WEB3InitialSetDivDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginDisabledFlagDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MngLockDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3NewAccountDivDef;
import webbroker3.common.define.WEB3OrderPermissionDef;
import webbroker3.common.define.WEB3PersonIdentifyDef;
import webbroker3.common.define.WEB3PosReportDivDef;
import webbroker3.common.define.WEB3PosReportTermDivDef;
import webbroker3.common.define.WEB3QualifiedInstInvestorDivDef;
import webbroker3.common.define.WEB3QuoteTypeDef;
import webbroker3.common.define.WEB3RegDivDef;
import webbroker3.common.define.WEB3SpecialAccDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingPowerStopDef;
import webbroker3.common.define.WEB3TradingReportDivDef;
import webbroker3.common.define.WEB3TradingStopDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCheckDigitalUtility;
import webbroker3.gentrade.data.CommCodeChgMstRow;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.ExclusiveUseAccountCondParams;
import webbroker3.gentrade.data.ExclusiveUseAccountCondRow;
import webbroker3.gentrade.data.ExclusiveUseAccountParams;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsRow;
import webbroker3.tradingpower.data.TpCashBalanceDao;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (即日口座開設UnitServiceImpl)<BR>
 * 即日口座開設１件サービス実装クラス<BR>
 * （トランザクション属性=TX_CREATE_NEW）<BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AccOpenRealUnitServiceImpl implements WEB3AccOpenRealUnitService
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenRealUnitServiceImpl.class);
    
    /**
     * トランザクション処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「即日口座開設（リアル連携）process」参照<BR>
     * @@return String
     * @@param l_hostAccOpenAccept - (口座開設伝票登録受付キュー)<BR>
     * 口座開設伝票登録受付キュー
     * @@throws WEB3BaseException
     * @@roseuid 44D06D220070
     */
    public String process(HostAccOpenAcceptParams l_hostAccOpenAccept) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " process(HostAccOpenAcceptParams)";
        log.entering(STR_METHOD_NAME);

        //[顧客登録伝票（Ｇ１１）キュー()に指定する引数]
        //識別コード：口座開設伝票登録受付キュー.識別コード
        String l_strOrderRequestNumber = l_hostAccOpenAccept.getOrderRequestNumber();

        //証券会社コード：口座開設伝票登録受付キュー.証券会社コード
        String l_strInstitutionCode = l_hostAccOpenAccept.getInstitutionCode();

        //部店コード：口座開設伝票登録受付キュー.部店コード
        String l_strBranchCode = l_hostAccOpenAccept.getBranchCode();

        //顧客コード：口座開設伝票登録受付キュー.顧客コード
        String l_strAccountCode = l_hostAccOpenAccept.getAccountCode();

        //データコード：口座開設伝票登録受付キュー.データコード
        String l_strRequestCode = l_hostAccOpenAccept.getRequestCode();

        String[] l_acceptReqCodes =
            new String[]{"GI82A","GI82G","GI82C","GI82H","GI82B","GI83G","GI82E",
                         "GI81I", "GI82D", "GI84I", "GI84H", "GI84E", "GI85D", "GI86E"};
        String[] l_registReqCodes =
            new String[]{"GI821","GI827","GI823","GI828","GI822","GI837","GI825",
                         "GI819", "GI824", "GI849", "GI848", "GI845", "GI854", "GI865"};

        String l_strRegistReqCode = null;
        for (int i = 0; i < l_acceptReqCodes.length; i++)
        {
            if (l_acceptReqCodes[i].equals(l_strRequestCode))
            {
                l_strRegistReqCode = l_registReqCodes[i];
                break;
            }
        }

        try
        {
            //顧客登録伝票（Ｇ11）キューオブジェクトを取得する
            WEB3AccOpenHostAccRegVoucher l_accOpenHostAccRegVoucher =
                new WEB3AccOpenHostAccRegVoucher(
                    l_strOrderRequestNumber,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strRegistReqCode);

            //顧客登録伝票（Ｇ１１）キュー.登録区分 = "1"（新規）以外の場合、処理を終了する。
            HostAccRegVoucherParams l_accRegVoucherParams = (HostAccRegVoucherParams)
                l_accOpenHostAccRegVoucher.getDataSourceObject();
            String l_strRegistDiv = l_accRegVoucherParams.getRegistDiv();
            if (!WEB3RegDivDef.NEW.equals(l_strRegistDiv))
            {
                log.entering(STR_METHOD_NAME);
                return null;
            }

            //[口座開設見込客()に指定する引数]
            //証券会社コード：顧客登録伝票（Ｇ１１）キュー.証券会社コード
            String l_strInstitutionCodeTemp =
                l_accRegVoucherParams.getInstitutionCode();

            //識別コード：顧客登録伝票（Ｇ１１）キュー.識別コード（口座開設見込客）
            String l_strAccOpenRequestNumber =
                l_accRegVoucherParams.getAccOpenRequestNumber();

            //口座開設見込客オブジェクトを取得する
            WEB3AccOpenExpAccountOpen l_expAccountOpen =
                new WEB3AccOpenExpAccountOpen(
                    l_strInstitutionCodeTemp, l_strAccOpenRequestNumber);
            ExpAccountOpenParams l_params = (ExpAccountOpenParams)
                l_expAccountOpen.getDataSourceObject();

            // 口座開設見込客.口座区分 = "0"(個人)以外の場合、処理を終了する。
            if (!WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(
                l_params.getAccountDiv()))
            {
                log.entering(STR_METHOD_NAME);
                return null;
            }

            //[部店()に指定する引数]
            //証券会社コード：口座開設見込客.証券会社コード
            //部店コード：口座開設見込客.部店コード
            WEB3AccOpenBranch l_accOpenBranch = new WEB3AccOpenBranch(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

            //ログイン・アカウント・リレーションオブジェクトを取得する
            //[ログイン・アカウント・リレーション()に指定する引数]
            //ログインタイプID：部店.顧客ログインタイプID
            WEB3AccOpenLoginAccountRelation l_accOpenLoginAccountRelation =
                new WEB3AccOpenLoginAccountRelation(l_accOpenBranch.getAccountTypeId());

            //バッチ部店オブジェクトを取得する
            //[バッチ部店()に指定する引数]
            //証券会社コード：口座開設見込客.証券会社コード
            //部店コード：口座開設見込客,部店コード
            WEB3AccOpenBatchBranch l_accOpenBatchBranch = new WEB3AccOpenBatchBranch(
                l_expAccountOpen.getInstitutionCode(),
                l_expAccountOpen.getBranchCode());

            //getチェックデジット(口座コード : String)
            //[getチェックデジット()に指定する引数]
            //口座コード：口座開設見込客.口座コード
            String l_strCheckDigital =
                WEB3GentradeCheckDigitalUtility.getCheckDigital(
                    l_params.getAccountCode().substring(0, 6));

            //プロパティのセット
            //ログインＩＤ：1"固定値（顧客）
            StringBuffer l_sbLoginId = new StringBuffer();
            // "1"固定値（顧客）
            l_sbLoginId.append(WEB3AccOpenAccountKindDef.CUSTOMER);
            //口座開設見込客.口座コードの5桁目と6桁目
            String l_strAccCod = l_expAccountOpen.getAccountCode();
            l_sbLoginId.append(l_strAccCod.substring(4, 6));
            //口座開設見込客.証券会社ＩＤ
            l_sbLoginId.append(l_params.getInstitutionId());
            //口座開設見込客.部店コード
            l_sbLoginId.append(l_expAccountOpen.getBranchCode());
            //口座開設見込客.口座コード
            l_sbLoginId.append(l_expAccountOpen.getAccountCode());
            //getチェックデジット（）の戻り値
            l_sbLoginId.append(l_strCheckDigital);

            //ログインユーザ名
            StringBuffer l_sbLoginUsername = new StringBuffer();
            //"1"固定値（顧客)
            l_sbLoginUsername.append(WEB3AccOpenAccountKindDef.CUSTOMER);
            //口座開設見込客.証券会社コード
            l_sbLoginUsername.append(l_expAccountOpen.getInstitutionCode());
            //口座開設見込客.部店コード
            l_sbLoginUsername.append(l_expAccountOpen.getBranchCode());
            //口座開設見込客.口座コード
            l_sbLoginUsername.append(l_expAccountOpen.getAccountCode());

            //口座ＩＤ：
            StringBuffer l_sbAccountId = new StringBuffer();
            // "1"固定値（顧客）
            l_sbAccountId.append(WEB3AccOpenAccountKindDef.CUSTOMER);
            //口座開設見込客.口座コードの5桁目と6桁目
            l_sbAccountId.append(l_strAccCod.substring(4, 6));
            //口座開設見込客.証券会社ＩＤ
            l_sbAccountId.append(l_params.getInstitutionId());
            //口座開設見込客.部店コード
            l_sbAccountId.append(l_expAccountOpen.getBranchCode());
            //口座開設見込客.口座コード
            l_sbAccountId.append(l_expAccountOpen.getAccountCode());
            //getチェックデジット（）の戻り値
            l_sbAccountId.append(l_strCheckDigital);

            //口座コード
            StringBuffer l_sbAccountCode = new StringBuffer();
            //口座開設見込客.口座コード
            l_sbAccountCode.append(l_expAccountOpen.getAccountCode().substring(0, 6));
            //getチェックデジット（）の戻り値
            l_sbAccountCode.append(l_strCheckDigital);

            //補助口座ＩＤ(固定部分)
            StringBuffer l_sbSubAccountId = new StringBuffer();
            //口座開設見込客.証券会社ID
            l_sbSubAccountId.append(l_params.getInstitutionId());
            //口座開設見込客.部店コード
            l_sbSubAccountId.append(l_expAccountOpen.getBranchCode());
            //口座開設見込客.口座コード
            l_sbSubAccountId.append(l_expAccountOpen.getAccountCode());
            // getチェックデジット（）の戻り値
            l_sbSubAccountId.append(l_strCheckDigital);

            //ログインテーブルを更新する。
            //[insertログイン()に指定する引数]
            //ログインID：ログインID
            //ログインタイプID：ログインタイプID
            //口座ID：口座ID
            this.insertLogin(
                l_expAccountOpen,
                l_sbLoginId.toString(),
                l_accOpenBranch.getAccountTypeId(),
                l_sbAccountId.toString());

            //ログイン・アカウント・マネージャテーブルを更新する。
            //[insertログイン・アカウント・マネージャ()に指定する引数]
            //ログインID：ログインID
            //口座ID：口座ID
            //リレーションＩＤ：ログイン・アカウント・リレーション.リレーションID
            this.insertLoginAccountManager(l_sbLoginId.toString(),
                l_sbAccountId.toString(),
                l_accOpenLoginAccountRelation.getRelationId());

            //ログイン・グループ・メンバテーブルを更新する。
            //[insertログイン・グループ・メンバ()に指定する引数]
            //グループＩＤ：部店.顧客ログイングループＩＤ
            //ログインＩＤ：ログインＩＤ
            this.insertLoginGroupMember(l_accOpenBranch.getAccountGroupId(),
                l_sbLoginId.toString());

            //口座開設見込客.初期パスワード = NULLの場合、処理実施しない。
            if (l_params.getInitialPassword() != null)
            {
                //ログイン属性テーブルを更新する。
                //[insertログイン属性()に指定する引数]
                //ログインＩＤ：ログインＩＤ
                this.insertLoginAttribute(l_expAccountOpen, l_sbLoginId.toString());
            }

            //ログインユーザ名テーブルを更新する。
            //[insertログインユーザ名()に指定する引数]
            //ログインユーザ名：ログインユーザ名
            //ログインＩＤ：ログインＩＤ
            this.insertLoginUsername(
                l_sbLoginUsername.toString(), l_sbLoginId.toString());

            //顧客マスターテーブルを更新する。
            //[insert顧客マスター()に指定する引数]
            //口座開設見込客：口座開設見込客オブジェクト
            //顧客登録伝票（Ｇ１１）キュー：顧客登録伝票（Ｇ１１）キューオブジェクト
            //バッチ用部店：バッチ用部店オブジェクト
            //口座ＩＤ：口座ID
            //口座コード：口座コード
            this.insertMainAccount(
                l_expAccountOpen,
                l_accOpenHostAccRegVoucher,
                l_accOpenBatchBranch,
                l_sbAccountId.toString(),
                l_sbAccountCode.toString());

            //補助口座タイプごとによるループ処理
            //①@補助口座タイプList(List型オブジェクト)を用意する。
            List l_listSubAccountType = new ArrayList();
            //②補助口座タイプListへデータをセットする。
            //　@顧客登録伝票（Ｇ１１）キュー.口座開設１（保護預り） = "1"（開設）の場合
            if (WEB3AccountOpenDef.OPEN.equals(
                l_accOpenHostAccRegVoucher.getAccountOpenDiv1()))
            {
                //　@"01"(株式取引口座) を補助口座タイプList
                l_listSubAccountType.add(
                    WEB3AccOpenSubAccountTypeDef.EQUITY_SUB_ACCOUNT);
            }

            if (WEB3AccountOpenDef.OPEN.equals(
                    l_accOpenHostAccRegVoucher.getAccountOpenDiv3()))
            {
                //顧客登録伝票（Ｇ１１）キュー.口座開設３（信用取引） = "1"（開設）の場合
                //"03"(信用取引) を補助口座タイプList
                l_listSubAccountType.add(
                    WEB3AccOpenSubAccountTypeDef.FX_MARGIN_SUB_ACCOUNT);
            }

            if (WEB3AccountOpenDef.OPEN.equals(
                    l_accOpenHostAccRegVoucher.getAccountOpenDiv11()))
            {
                //　@顧客登録伝票（Ｇ１１）キュー.口座開設１１（株先オプション） = "1"（開設）の場合
                //"07"(先物オプション証拠金口座) を補助口座タイプListへ追加する。
                l_listSubAccountType.add(
                    WEB3AccOpenSubAccountTypeDef.EQUITY_OPTIONS_SUB_ACCOUNT);
            }

            int l_intListSize = l_listSubAccountType.size();
            for (int i = 0; i < l_intListSize; i++)
            {
                String l_strSubAccountType = (String)l_listSubAccountType.get(i);
                String l_strSubAccountTypeTemp = l_strSubAccountType;

                if (l_strSubAccountType.startsWith("0"))
                {
                    l_strSubAccountType = l_strSubAccountType.substring(1);
                }
                //[insert補助口座（顧客勘定残高）()に指定する引数]
                //口座開設見込客：口座開設見込客オブジェクト
                //口座ＩＤ：口座ID
                //補助口座タイプ：補助口座タイプList[n]の値（※先頭の"0"を削除した値をセットする。）
                //補助口座ＩＤ：補助口座ＩＤ(固定部分) + 補助口座タイプList[n]の値
                this.insertSubAccount(
                    l_expAccountOpen,
                    l_sbAccountId.toString(),
                    l_strSubAccountType,
                    l_sbSubAccountId + l_strSubAccountTypeTemp);

                //顧客勘定残高（マスタ情報）を更新する。
                //[insert顧客勘定残高（マスタ情報）()に指定する引数]
                //口座ＩＤ：口座ID
                //補助口座ＩＤ：補助口座ＩＤ(固定部分) + 補助口座タイプList[n]の値
                this.insertTpCashBalance(l_sbAccountId.toString(),
                    l_sbSubAccountId + l_strSubAccountTypeTemp);
            }

            //アイテムの定義
            // 顧客余力条件を更新する。
            //[insert顧客余力条件()に指定する引数]
            // 口座開設見込客：口座開設見込客オブジェクト
            // 口座ID：口座ID
            this.insertTradingpowerCalcCondition(l_expAccountOpen,
                l_sbAccountId.toString());

            //insert専用振込先口座(口座開設見込客, String, String)
            //口座開設見込客：口座開設見込客オブジェクト
            //口座ＩＤ：口座ID
            //口座コード：口座コード
            String l_strExclusiveUseAccountNo = this.insertExclusiveUseAccount(
                l_expAccountOpen,
                l_sbAccountId.toString(),
                l_sbAccountCode.toString());

            //insert委託手数料顧客条件登録マスター(口座開設見込客, String)
            //口座開設見込客：口座開設見込客オブジェクト
            //口座ID：口座ID
            this.insertEquityCommissionAccountCondMaster(
                l_expAccountOpen, l_sbAccountId.toString());

            //口座開設見込客テーブルを更新する。
            //[update口座開設見込客()に指定する引数]
            // 証券会社コード：顧客登録伝票（Ｇ１１）キュー.証券会社コード
            // 識別コード：顧客登録伝票（Ｇ１１）キュー.識別コード
            HostAccRegVoucherRow l_hostAccRegVoucherRow
                = (HostAccRegVoucherRow)l_accOpenHostAccRegVoucher.getDataSourceObject();
            this.updateExpAccountOpen(
                l_hostAccRegVoucherRow.getInstitutionCode(),
                l_hostAccRegVoucherRow.getAccOpenRequestNumber(),
                l_strExclusiveUseAccountNo);
        }
        catch (NotFoundException l_ex)
        {
            log.error(" テーブルに該当するデータがありません: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (insertログイン)<BR>
     * ログインテーブルを更新する。<BR>
     * <BR>
     * １） 更新情報をセットする。<BR>
     * ログイン行の項目に値をセットする。<BR>
     * <BR>
     * 　@１ - 1） パスワードの取得<BR>
     * <BR>
     * 　@　@ログインテーブルのパスワードに以下の値をセットする。<BR>
     * <BR>
     * 　@　@パスワード = this.getパスワード（）の戻り値<BR>
     * <BR>
     * 　@１ - 2) ログイン有効性の取得<BR>
     * <BR>
     * 　@　@ログインテーブルのログイン有効性に以下の値をセットする。<BR>
     * <BR>
     * 　@　@①@口座開設見込客.初期パスワード = NULLの場合<BR>
     * <BR>
     * 　@　@　@ログイン有効性 = "1"(disabled) <BR>
     * <BR>
     *　@　@ ②口座開設見込客.初期パスワード ≠ NULLの場合<BR>
     * <BR>
     * 　@　@　@ログイン有効性 = "0"(enabled) <BR>
     * <BR>
     * 　@１ - 3） 上記２項目以外の項目<BR>
     * <BR>
     * 　@　@DB更新仕様「ログインＤＢ更新仕様.xls」参照<BR>
     * <BR>
     * ２） DB更新<BR>
     * 　@ログイン行オブジェクトの内容で、ログインテーブルを更新（insert）する。
     * @@param l_expAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客<BR>
     * @@param l_strLoginId - (ログインID)<BR>
     * ログインID<BR>
     * @@param l_strLoginTypeId - (ログインタイプID)<BR>
     * ログインタイプID<BR>
     * @@param l_strAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D0799F018F
     */
    private void insertLogin(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strLoginId,
        String l_strLoginTypeId,
        String l_strAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " insertLogin(WEB3AccOpenExpAccountOpen，String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null ||
                WEB3StringTypeUtility.isEmpty(l_strLoginId) ||
                WEB3StringTypeUtility.isEmpty(l_strLoginTypeId) ||
                WEB3StringTypeUtility.isEmpty(l_strAccountId))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //１ - 1） パスワードの取得
            //パスワード = this.getパスワード（）の戻り値
            String l_strHashedPassword = this.getPassword(l_expAccountOpen);
            //１ - 2) ログイン有効性の取得
            //①@口座開設見込客.初期パスワード = NULLの場合
            //ログイン有効性 = "1"(disabled)
            //②口座開設見込客.初期パスワード ≠ NULLの場合
            //ログイン有効性 = "0"(enabled)
            ExpAccountOpenParams l_expAccountOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            String l_strDisabled = null;
            if (l_expAccountOpenParams.getInitialPassword() == null)
            {
                l_strDisabled = WEB3LoginDisabledFlagDef.ACCINFO_DISABLED;
            }
            else
            {
                l_strDisabled = WEB3LoginDisabledFlagDef.ACCINFO_ENABLED;
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginParams l_loginParams = new LoginParams();

            //１ - 3） 上記２項目以外の項目
            //ログインID <-- 引数.ログインID
            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_loginParams.setLoginId(l_lngLoginId);

            //ログインタイプID <-- 引数.ログインタイプID
            long l_lngTypeId = Long.parseLong(l_strLoginTypeId);
            l_loginParams.setTypeId(l_lngTypeId);

            //口座ID <-- 引数.口座ID
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_loginParams.setAccountId(l_lngAccountId);

            //初期パスワード <-- 「-」：未使用だがNotNull項目のため
            l_loginParams.setInitialPassword("-");

            //パスワード <-- パスワード
            l_loginParams.setHashedPassword(l_strHashedPassword);

            //アフィニティキー <-- null
            l_loginParams.setAffinityKey(null);

            //ログイン有効性 <-- ログイン有効性
            l_loginParams.setDisabled(Integer.parseInt(l_strDisabled));

            //ログインエラー回数 <-- null
            l_loginParams.setFailureCount(null);

            //最終ログインエラー時刻 <-- null
            l_loginParams.setLastFailureTimestamp(null);

            // ２） DB更新
            l_queryProcessor.doInsertQuery(l_loginParams);

        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update口座開設見込客)<BR>
     * 口座開設見込客テーブルを更新する。<BR>
     * <BR>
     * １）口座開設見込客テーブルの検索<BR>
     * <BR>
     * 　@下記の条件より、口座開設見込客テーブルを検索する。<BR>
     * <BR>
     * [条件]　@ <BR>
     * 　@口座開設見込客.証券会社コード = 引数.証券会社コード <BR>
     * 　@口座開設見込客.識別コード = 引数.識別コード <BR>
     * <BR>
     * 　@検索結果に一致する行が存在しない場合は、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3SystemLayerException <BR>
     * 　@　@　@　@　@　@　@tag　@: SYSTEM_ERROR_80005 <BR>
     * 　@検索結果が１件の場合、口座登日を更新する。<BR>
     * <BR>
     * ２）　@口座登録日の設定<BR>
     * 　@１）で検索されたレコードに以下の値を設定する。<BR>
     * <BR>
     * 　@[設定値]<BR>
     * 　@更新日時　@：処理日時　@<BR>
     * 　@専用振込先口座番号　@：引数.専用振込先口座番号 <BR>
     * <BR>
     * ３）　@更新処理<BR>
     * 　@２）の設定値で口座開設見込客テーブルの更新処理を行う。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strAccOpenRequestNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@param l_strExclusiveUseAccountNo - (専用振込先口座番号)<BR>
     * 専用振込先口座番号 <BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D6C48D036B
     */
    private void updateExpAccountOpen(
        String l_strInstitutionCode,
        String l_strAccOpenRequestNumber,
        String l_strExclusiveUseAccountNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateExpAccountOpen(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //[条件]
            //口座開設見込客.証券会社コード = 引数.証券会社コード
            //口座開設見込客.識別コード = 引数.識別コード

            //１）口座開設見込客テーブルの検索
            ExpAccountOpenRow l_row =
                (ExpAccountOpenRow)ExpAccountOpenDao.findRowByInstitutionCodeAccOpenRequestNumber(
                    l_strInstitutionCode,
                    l_strAccOpenRequestNumber);

            if ( l_row == null)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            }

            //検索結果が１件の場合、口座登日を更新する。
            else
            {
                ExpAccountOpenParams l_params = new ExpAccountOpenParams(l_row);

                Date l_datProcessDate = GtlUtils.getSystemTimestamp();
                //更新日時　@：処理日時
                l_params.setLastUpdatedTimestamp(l_datProcessDate);

                //専用振込先口座番号　@：引数.専用振込先口座番号
                l_params.setExclusiveUseAccountNo(l_strExclusiveUseAccountNo);

                //３）　@更新処理
                l_queryProcessor.doUpdateQuery(l_params);
            }
        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertログイン・アカウント・マネージャ)<BR>
     * ログイン・アカウント・マネージャテーブルを更新する。<BR>
     * <BR>
     * １） 更新情報をセットする。 <BR>
     * 　@ログイン・アカウント・マネージャ行の項目に値をセットする。 <BR>
     * <BR>
     * 　@DB更新仕様「ログイン・アカウント・マネージャＤＢ更新仕様.xls」参照<BR>
     * <BR>
     * ２） DB更新 <BR>
     * 　@ログイン・アカウント・マネージャ行オブジェクトの内容で、ログイン・アカウント・<BR>
     * マネージャテーブルを更新（insert）する。 <BR>
     * @@param l_strLoginId - (ログインID)<BR>
     * ログインID<BR>
     * @@param l_strAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strRelationId - (リレーションＩＤ)<BR>
     * リレーションＩＤ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C320056
     */
    private void insertLoginAccountManager(
        String l_strLoginId,
        String l_strAccountId,
        String l_strRelationId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertLoginAccountManager(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (WEB3StringTypeUtility.isEmpty(l_strLoginId)
                || WEB3StringTypeUtility.isEmpty(l_strAccountId)
                || WEB3StringTypeUtility.isEmpty(l_strRelationId))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginAccountManagerParams l_loginAccManParams =
                new LoginAccountManagerParams();

            //１） 更新情報をセットする。
            //ログインID <-- 引数.ログインID
            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_loginAccManParams.setLoginId(l_lngLoginId);

            //口座ID <-- 引数.口座ID
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_loginAccManParams.setAccountId(l_lngAccountId);

            //リレーションID <-- 引数.リレーションＩＤ
            long l_lngRelationId = Long.parseLong(l_strRelationId);
            l_loginAccManParams.setRelationId(l_lngRelationId);

            //２） DB更新
            l_queryProcessor.doInsertQuery(l_loginAccManParams);

        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertログイン･グループ･メンバ)<BR>
     * ログイン・グループ・メンバテーブルを更新する。<BR>
     * <BR>
     * １） 更新情報をセットする。 <BR>
     * 　@ログイン・グループ・メンバ行の項目に値をセットする。 <BR>
     * <BR>
     * 　@DB更新仕様「ログイン・グループ・メンバＤＢ更新仕様.xls」参照<BR>
     * <BR>
     * ２） DB更新 <BR>
     * 　@ログイン・グループ・メンバ行オブジェクトの内容で、ログイン・グループ・メンバテ<BR>
     * ーブルを更新（insert）する。<BR>
     * @@param l_strGroupId - (グループID)<BR>
     * グループＩＤ<BR>
     * @@param l_strLoginId - (ログインID)<BR>
     * ログインID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C5303A4
     */
    private void insertLoginGroupMember(
        String l_strGroupId,
        String l_strLoginId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertLoginGroupMember(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (WEB3StringTypeUtility.isEmpty(l_strGroupId)
                || WEB3StringTypeUtility.isEmpty(l_strLoginId))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginGroupMemberParams l_loginGroupMemberParams = new LoginGroupMemberParams();

            //１） 更新情報をセットする。
            //グループID <-- 引数.グループＩＤ
            long l_lngGroupId = Long.parseLong(l_strGroupId);
            l_loginGroupMemberParams.setGroupId(l_lngGroupId);

            //ログインID <-- 引数.ログインID
            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_loginGroupMemberParams.setLoginId(l_lngLoginId);

            //２） DB更新
            l_queryProcessor.doInsertQuery(l_loginGroupMemberParams);
        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertログインユーザ名)<BR>
     * ログインユーザ名テーブルを更新する。<BR>
     * <BR>
     * １） 更新情報をセットする。 <BR>
     * 　@ログインユーザ名行の項目に値をセットする。 <BR>
     * <BR>
     * 　@DB更新仕様「ログインユーザ名ＤＢ更新仕様.xls」参照<BR>
     * <BR>
     * ２） DB更新 <BR>
     * 　@ログインユーザ名行オブジェクトの内容で、ログインユーザ名テーブルを更新（insert）する。<BR>
     * @@param l_strLoginUsername - (ログインユーザ名)<BR>
     * ログインユーザ名<BR>
     * @@param l_strLoginId - (ログインID)<BR>
     * ログインID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C7A03B7
     */
    private void insertLoginUsername(
        String l_strLoginUsername,
        String l_strLoginId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertLoginUsername(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (WEB3StringTypeUtility.isEmpty(l_strLoginId))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            LoginUsernameParams l_loginUsernameParams = new LoginUsernameParams();

            //１） 更新情報をセットする。
            //ログインユーザ名 <-- 引数.ログインユーザ名
            l_loginUsernameParams.setUsername(l_strLoginUsername);

            //ログインID <-- 引数.ログインID
            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_loginUsernameParams.setLoginId(l_lngLoginId);

            //２） DB更新
            l_queryProcessor.doInsertQuery(l_loginUsernameParams);

        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insertログイン属性)<BR>
     * ログイン属性テーブルを更新する。<BR>
     * <BR>
     * １） 更新情報をセットする。 <BR>
     * 　@ログイン属性行の項目に値をセットする。 <BR>
     * <BR>
     * 　@DB更新仕様「ログイン属性ＤＢ更新仕様.xls」参照<BR>
     * <BR>
     * ２） DB更新 <BR>
     * 　@ログイン属性行オブジェクトの内容で、ログイン属性テーブルを更新（insert）する。<BR>
     * <BR>
     * @@param l_expAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客<BR>
     * @@param l_strLoginId - (ログインID)<BR>
     * ログインID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C6B0339
     */
    private void insertLoginAttribute(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strLoginId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertLoginAttribute(WEB3AccOpenExpAccountOpen, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null || WEB3StringTypeUtility.isEmpty(l_strLoginId))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //処理日時の取得
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();
            String l_strProcessDate = WEB3DateUtility.formatDate(l_datProcessDate,
                "yyyy.MM.dd HH:mm:ss");

            ExpAccountOpenParams l_expAccountOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String[] l_strAttributeNames = null;
            String[] l_strAttributeValue = null;

            long l_lngLoginId = Long.parseLong(l_strLoginId);
            l_strAttributeNames = new String[]
                {WEB3LoginAttributeKeyDef.INITIAL_PASSWORD,
                 WEB3LoginAttributeKeyDef.PASSWORD,
                 WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER,
                 WEB3LoginAttributeKeyDef.LAST_PWDCHANGE};

            l_strAttributeValue = new String[]
                {l_expAccountOpenParams.getInitialPassword(),
                 l_expAccountOpenParams.getInitialPassword(),
                 WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN,
                 l_strProcessDate};

            LoginAttributeParams l_loginAttributeParams = new LoginAttributeParams();
            for(int i = 0; i < 4; i++)
            {
                l_loginAttributeParams.setLoginId(l_lngLoginId);
                l_loginAttributeParams.setAttributeName(l_strAttributeNames[i]);
                l_loginAttributeParams.setAttributeValue(l_strAttributeValue[i]);
                l_queryProcessor.doInsertQuery(l_loginAttributeParams);
            }
        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert顧客マスター)<BR>
     * 顧客マスターテーブルを更新する。<BR>
     * <BR>
     * １） 更新情報をセットする。 <BR>
     * 　@顧客マスター行の項目に値をセットする。 <BR>
     * <BR>
     * 　@１ - 1） 取引パスワードの取得<BR>
     * <BR>
     * 　@　@①@口座開設見込客.初期パスワード = NULLの場合<BR>
     * <BR>
     * 取引パスワード = "    "(ブランク4桁) <BR>
     * <BR>
     * 　@　@②口座開設見込客.初期パスワード ≠ NULLの場合 <BR>
     * <BR>
     * 　@　@取引パスワード = 口座開設見込客.初期パスワード<BR>
     * <BR>
     * １ - 2） 本人確認区分の取得 <BR>
     * <BR>
     * 　@　@①@口座開設見込客.本人確認書類区分 = NULLの場合<BR>
     * <BR>
     * 　@　@本人確認書類区分 = "0"（未確認） <BR>
     * <BR>
     * 　@　@②口座開設見込客.本人確認書類区分 ≠ NULLの場合 <BR>
     * <BR>
     * 　@　@本人確認書類区分 = "1"（確認済）<BR>
     * <BR>
     * １ - 3） 上記以外の項目<BR>
     * <BR>
     * 　@　@DB更新仕様「顧客マスターＤＢ更新仕様.xls」参照 <BR>
     * <BR>
     * ２） DB更新 <BR>
     * 　@顧客マスター行オブジェクトの内容で、顧客マスターテーブルを更新（insert）する。<BR>
     * <BR>
     * @@param l_expAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客<BR>
     * @@param l_hostAccRegVoucher - (顧客登録伝票（Ｇ１１）キュー)<BR>
     * 顧客登録伝票（Ｇ１１）キュー<BR>
     * @@param l_batchBranch - (バッチ用部店)<BR>
     * バッチ用部店<BR>
     * @@param l_strAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C8B02CE
     */
    private void insertMainAccount(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        WEB3AccOpenHostAccRegVoucher l_hostAccRegVoucher,
        WEB3AccOpenBatchBranch l_batchBranch,
        String l_strAccountId,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertMainAccount(WEB3AccOpenExpAccountOpen," +
            " WEB3AccOpenHostAccRegVoucher, WEB3AccOpenBatchBranch, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null
                || l_hostAccRegVoucher == null
                || l_batchBranch == null
                || WEB3StringTypeUtility.isEmpty(l_strAccountId)
                || WEB3StringTypeUtility.isEmpty(l_strAccountCode))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            MainAccountParams l_mainAccountParams = new MainAccountParams();

            //１） 更新情報をセットする。
            //顧客マスター行の項目に値をセットする。
            ExpAccountOpenParams l_expAccOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            //取引パスワードの取得
            //①@口座開設見込客.初期パスワード = NULLの場合
            String l_strTradingPassword = null;
            if (l_expAccOpenParams.getInitialPassword() == null)
            {
                l_strTradingPassword = WEB3AccOpenTradingPasswordDef.BLANK;
            }
            else
            {
                l_strTradingPassword = l_expAccOpenParams.getInitialPassword();
            }

            //１ - 2） 本人確認区分の取得
            //①@口座開設見込客.本人確認書類区分 = NULLの場合
            //本人確認書類区分 = "0"（未確認）
            //②口座開設見込客.本人確認書類区分 ≠ NULLの場合
            //本人確認書類区分 = "1"（確認済）
            String l_strPersonIdentify = null;
            if (l_expAccOpenParams.getIdConfirmDocDiv() == null)
            {
                l_strPersonIdentify = WEB3PersonIdentifyDef.NOT_IDENTIFY;
            }
            else
            {
                l_strPersonIdentify = WEB3PersonIdentifyDef.ALREADY_IDENTIFY;
            }

            HostAccRegVoucherParams l_hostAccRegVoucherParams =
                (HostAccRegVoucherParams)l_hostAccRegVoucher.getDataSourceObject();
            //口座ＩＤ
            l_mainAccountParams.setAccountId(Long.parseLong(l_strAccountId));

            //証券会社コード
            l_mainAccountParams.setInstitutionCode(l_expAccOpenParams.getInstitutionCode());

            //証券会社ID
            l_mainAccountParams.setInstitutionId(l_expAccOpenParams.getInstitutionId());

            //口座コード
            l_mainAccountParams.setAccountCode(l_strAccountCode);

            //部店ＩＤ
            l_mainAccountParams.setBranchId(l_expAccOpenParams.getBranchId());

            //部店コード
            l_mainAccountParams.setBranchCode(l_expAccOpenParams.getBranchCode());

            //扱者コード（SONAR）
            l_mainAccountParams.setSonarTraderCode(l_expAccOpenParams.getSonarTraderCode());

            //口座タイプ
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);

            //名前（苗字）
            l_mainAccountParams.setFamilyName(l_hostAccRegVoucherParams.getAccountName());

            //名前(ミドルネーム）       null
            //名前（名前）              null

            //名前（苗字1）
            String l_strFamilyNameAalt1 = WEB3StringTypeUtility.toWbyteKana(
                l_hostAccRegVoucherParams.getAccountNameKana());
            l_mainAccountParams.setFamilyNameAlt1(l_strFamilyNameAalt1);

            //名前(ミドルネーム1）     null
            //名前（名前1）            null
            //名前（苗字2）            null
            //名前(ミドルネーム2）     null
            //名前（名前2）            null

            //郵便番号
            l_mainAccountParams.setZipCode(l_expAccOpenParams.getZipCode());

            //（補助）郵便番号          null

            //住所１
            l_mainAccountParams.setAddressLine1(l_expAccOpenParams.getAddressLine1());

            //住所２
            l_mainAccountParams.setAddressLine2(l_expAccOpenParams.getAddressLine2());

            //住所３
            l_mainAccountParams.setAddressLine3(l_expAccOpenParams.getAddressLine3());

            //住所１（カナ）
            l_mainAccountParams.setAddressLine1Kana(l_expAccOpenParams.getAddressLine1Kana());

            //住所２（カナ）
            l_mainAccountParams.setAddressLine2Kana(l_expAccOpenParams.getAddressLine2Kana());

            //住所３（カナ）
            l_mainAccountParams.setAddressLine3Kana(l_expAccOpenParams.getAddressLine3Kana());

            //電話番号
            l_mainAccountParams.setTelephone(l_expAccOpenParams.getTelephone());

            //連絡先電話番号（携帯）
            l_mainAccountParams.setMobile(l_expAccOpenParams.getMobile());

            //ＦＡＸ番号
            l_mainAccountParams.setFax(l_expAccOpenParams.getFax());

            //start 勤務先情報
            //勤務先名称
            l_mainAccountParams.setOffice(l_expAccOpenParams.getOffice());

            //勤務先郵便番号
            l_mainAccountParams.setOfficeZipCode(l_expAccOpenParams.getOfficeZipCode());

            //勤務先住所
            l_mainAccountParams.setOfficeAddress(l_expAccOpenParams.getOfficeAddress());

            //勤務先電話番号
            l_mainAccountParams.setOfficeTelephone(l_expAccOpenParams.getOfficeTelephone());

            //役職
            l_mainAccountParams.setPost(l_expAccOpenParams.getPost());
            //end 勤務先情報

            BatchBranchParams l_batchBranchParams =
                (BatchBranchParams)l_batchBranch.getDataSourceObject();
            //株式約定メール送信フラグ
            if (l_batchBranchParams.getEquityOrderExeMailFlag() == BooleanEnum.TRUE.intValue())
            {
                l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            }
            else if (l_batchBranchParams.getEquityOrderExeMailFlag() == BooleanEnum.FALSE.intValue())
            {
                l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.FALSE);
            }

            //株式未約定メール送信フラグ
            if (l_batchBranchParams.getEquityOrderUnexecMailFlag() == BooleanEnum.TRUE.intValue())
            {
                l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            }
            else if (l_batchBranchParams.getEquityOrderUnexecMailFlag() == BooleanEnum.FALSE.intValue())
            {
                l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.FALSE);
            }

            //先物OP約定メール送信フラグ
            if (l_batchBranchParams.getIfoOrderExecMailFlag() == BooleanEnum.TRUE.intValue())
            {
                l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            }
            else if (l_batchBranchParams.getIfoOrderExecMailFlag() == BooleanEnum.FALSE.intValue())
            {
                l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.FALSE);
            }

            //先物OP未約定メール送信フラグ
            if (l_batchBranchParams.getIfoOrderUnexecMailFlag() == BooleanEnum.TRUE.intValue())
            {
                l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            }
            else if (l_batchBranchParams.getIfoOrderUnexecMailFlag() == BooleanEnum.FALSE.intValue())
            {
                l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.FALSE);
            }

            //案内メール送信フラグ
            String l_strTemp = WEB3StringTypeUtility.formatNumber(
                l_batchBranchParams.getInformationMailFlag());
            if (WEB3InformationMailFlagDef.FALSE.equals(l_strTemp))
            {
                l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
            }
            else if (WEB3InformationMailFlagDef.TRUE.equals(l_strTemp))
            {
                l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            }
            else if (WEB3InformationMailFlagDef.EXT_ITEM_FLAG4_PRIORITY_FALSE.equals(l_strTemp) ||
                WEB3InformationMailFlagDef.EXT_ITEM_FLAG4_PRIORITY_TRUE.equals(l_strTemp))
            {
                if (BooleanEnum.FALSE.equals(l_expAccOpenParams.getExtItemFlag4()))
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
                }
                else if (BooleanEnum.TRUE.equals(l_expAccOpenParams.getExtItemFlag4()))
                {
                    l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
                }
                else
                {
                    if (WEB3InformationMailFlagDef.EXT_ITEM_FLAG4_PRIORITY_FALSE.equals(l_strTemp))
                    {
                        l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
                    }
                    else if (WEB3InformationMailFlagDef.EXT_ITEM_FLAG4_PRIORITY_TRUE.equals(l_strTemp))
                    {
                        l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
                    }
                }
            }

            //居住／非居住区分
            l_mainAccountParams.setResident(l_expAccOpenParams.getResident());

            //新規口座区分
            l_mainAccountParams.setNewAccountDiv(WEB3NewAccountDivDef.NOT_NEW_ACCOUNT);

            //信託経由区分
            l_mainAccountParams.setViaTrustBankDiv(l_hostAccRegVoucherParams.getTrustViaDiv());

            //客層区分      null

            //emailアドレス
            if (l_expAccOpenParams.getEmailAddress() == null)
            {
                l_mainAccountParams.setEmailAddress(l_expAccOpenParams.getEmailAddressAlt1());
            }
            else
            {
                l_mainAccountParams.setEmailAddress(l_expAccOpenParams.getEmailAddress());
            }

            //emailアドレス１     null

            //取引パスワード
            l_mainAccountParams.setTradingPassword(l_strTradingPassword);

            //口座登録日
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();
            Date l_datProcessDays = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            l_mainAccountParams.setAccountOpenDate(l_datProcessDays);

            //口座閉鎖日           null

            //口座開設完了メール送信ステータス
            l_mainAccountParams.setAccOpenSendMailStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);

            //本人確認区分
            l_mainAccountParams.setPersonIdentify(l_strPersonIdentify);

            //生年月日年号
            l_mainAccountParams.setEraBorn(l_expAccOpenParams.getEraBorn());

            //生年月日
            l_mainAccountParams.setBornDate(l_expAccOpenParams.getBornDate());

            //性別
            l_mainAccountParams.setSex(l_expAccOpenParams.getSex());

            //Ｙ客区分
            l_mainAccountParams.setYellowCustomer(WEB3YellowCustomerDef.NOT_YELLOW_CUSTOMER);

            //ホームトレード決済方法@
            l_mainAccountParams.setHtSettlementWay(WEB3HtSettlementWayDef.NORMAL_SETTLEMENT);

            //振込先（銀行口座）登録区分
            //口座開設見込客テーブル.振替区分 = NULLの場合「0」（未登録）
            //口座開設見込客テーブル.振替区分 ≠ NULLの場合「1」（登録）
            if (l_expAccOpenParams.getTransferDiv() == null)
            {
                l_mainAccountParams.setBankAccountRegi(WEB3BankAccountRegiDef.NOT_REGISTER);
            }
            else
            {
                l_mainAccountParams.setBankAccountRegi(WEB3BankAccountRegiDef.ALREADY_REGISTER);
            }

            //口座ステータス
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);

            //考査ロック
            l_mainAccountParams.setExaminLockFlag(WEB3MngLockDef.UNLOCK);

            //管理ロック
            l_mainAccountParams.setMngLockFlag(WEB3MngLockDef.UNLOCK);

            //管理ロック解除開始日          null
            //管理ロック解除終了日          null

            //管理ロック理由フラグ（立替金）
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);

            //管理ロック理由フラグ（保証金未入）
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);

            //管理ロック理由フラグ（適格担保不足）
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);

            //管理ロック理由フラグ（預り証長期未差換）
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);

            //支店ロック
            l_mainAccountParams.setBranchLock(WEB3MngLockDef.UNLOCK);

            //注文認可
            l_mainAccountParams.setOrderPermission(WEB3OrderPermissionDef.NO_PERMISSION);

            //税区分
            //税区分（次年）
            //現物株式特定口座開設日
            if (WEB3SpecialAccDef.NORMAL.equals(l_expAccOpenParams.getSpecialAcc()))
            {
                l_mainAccountParams.setTaxType(TaxTypeEnum.NORMAL);
                l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.NORMAL);
                l_mainAccountParams.setEquitySpAccOpenDate(null);
            }
            else if (WEB3SpecialAccDef.SPECIAL.equals(l_expAccOpenParams.getSpecialAcc()))
            {
                l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
                l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL);
                l_mainAccountParams.setEquitySpAccOpenDate(l_datProcessDays);
            }
            else if (WEB3SpecialAccDef.SPECIAL_WITHHOLD.equals(
                l_expAccOpenParams.getSpecialAcc()))
            {
                l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
                l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
                l_mainAccountParams.setEquitySpAccOpenDate(l_datProcessDays);
            }
            else
            {
                l_mainAccountParams.setTaxType(TaxTypeEnum.UNDEFINED);
                l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.UNDEFINED);
            }

            //信用取引税区分
            //信用取引税区分（次年）
            //信用取引特定口座開設日
            if (WEB3SpecialAccDef.NORMAL.equals(l_expAccOpenParams.getSpecialAccMargin())
                || l_expAccOpenParams.getSpecialAccMargin() == null)
            {
                l_mainAccountParams.setMarginTaxType(TaxTypeEnum.NORMAL);
                l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.NORMAL);
                l_mainAccountParams.setMarginSpAccOpenDate(null);
            }
            else if (WEB3SpecialAccDef.SPECIAL.equals(
                l_expAccOpenParams.getSpecialAccMargin()))
            {
                l_mainAccountParams.setMarginTaxType(TaxTypeEnum.SPECIAL);
                l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.SPECIAL);
                l_mainAccountParams.setMarginSpAccOpenDate(l_datProcessDays);
            }
            else if (WEB3SpecialAccDef.SPECIAL_WITHHOLD.equals(
                l_expAccOpenParams.getSpecialAccMargin()))
            {
                l_mainAccountParams.setMarginTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
                l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
                l_mainAccountParams.setMarginSpAccOpenDate(l_datProcessDays);
            }
            else
            {
                l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
                l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
                l_mainAccountParams.setMarginSpAccOpenDate(null);
            }

            //適格機@関投資家区分
            l_mainAccountParams.setQualifiedInstInvestorDiv(
                WEB3QualifiedInstInvestorDivDef.NO_QUALIFIED_INSTITUTIONAL_INVESTOR);

            //制度信用取引口座開設区分
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv3()))
            {
                l_mainAccountParams.setMarginSysAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);
            }
            else if (
                WEB3AccountOpenDef.OPEN.equals(
                    l_hostAccRegVoucherParams.getAccountOpenDiv3()))
            {
                l_mainAccountParams.setMarginSysAccOpenDiv(WEB3AccountOpenDef.OPEN);
            }

            //一般信用取引口座開設区分
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);

            //振替可能回数
            l_mainAccountParams.setTransferCount(l_batchBranchParams.getTransferCount());

            //外国証券口座開設区分
            if (WEB3ForeignSecAccOpenDiv.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv5()))
            {
                l_mainAccountParams.setForeignSecAccOpenDiv(
                    WEB3ForeignSecAccOpenDiv.NOT_OPEN);
            }
            else if (WEB3ForeignSecAccOpenDiv.OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv5()))
            {
                l_mainAccountParams.setForeignSecAccOpenDiv(
                    WEB3ForeignSecAccOpenDiv.OPEN);
            }

            //先物OP口座開設区分（東証）
            l_mainAccountParams.setIfoAccOpenDivTokyo(
                l_hostAccRegVoucherParams.getIfoAccOpenDivTokyo());

            //先物OP口座開設区分（大証）
            l_mainAccountParams.setIfoAccOpenDivOsaka(
                l_hostAccRegVoucherParams.getIfoAccOpenDivOsaka());

            //先物OP口座開設区分（名証）
            l_mainAccountParams.setIfoAccOpenDivNagoya(
                l_hostAccRegVoucherParams.getIfoAccOpenDivNagoya());

            //累投口座開設区分
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv2()))
            {
                l_mainAccountParams.setRuitoAccOpenDiv(
                    WEB3CumulativeAccountDivDef.NOT_ESTABLISH);
            }
            else if (WEB3AccountOpenDef.OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv2()))
            {
                l_mainAccountParams.setRuitoAccOpenDiv(
                    WEB3CumulativeAccountDivDef.ESTABLISH);
            }

            //ＭＲＦ口座開設区分
            //顧客登録伝票(G11)キューテーブル.口座開設２（積立投資） = 「0」（未開設）の場合「0」（未開設）
            //顧客登録伝票(G11)キューテーブル.口座開設２（積立投資） = 「1」（開設）の場合「1」（開設）
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv2()))
            {
                l_mainAccountParams.setMrfAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);
            }
            else if (WEB3AccountOpenDef.OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv2()))
            {
                l_mainAccountParams.setMrfAccOpenDiv(WEB3AccountOpenDef.OPEN);
            }

            //ＦＸ口座開設区分
            l_mainAccountParams.setFxAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);

            //外国株式連携口座開設区分
            l_mainAccountParams.setFeqConAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);

            //先頭画面ID
            l_mainAccountParams.setTopPageId(l_batchBranchParams.getTopPageId());

            //時価取得区分
            l_mainAccountParams.setQuotoType(WEB3QuoteTypeDef.REAL_CUSTOMER);

            //start 電子鳩状況
            //取引報告書交付区分
            l_mainAccountParams.setTradingReportDiv(
                WEB3TradingReportDivDef.MAIL_DISTRIBUTION);

            //取引残高報告書交付区分
            l_mainAccountParams.setPositionReportDiv(
                WEB3PosReportDivDef.MAIL_DISTRIBUTION);

            //取引残高報告書作成周期区分
            l_mainAccountParams.setPositionReportCycleDiv(
                WEB3PosReportTermDivDef.EVERY_MONTH);

            //取引残高報告書預り証作成フラグ
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.FALSE);

            //取引残高報告書計算書作成フラグ
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.FALSE);

            //emailアドレス更新者コード
            l_mainAccountParams.setEmailLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //emailアドレス更新日時
            l_mainAccountParams.setEmailLastUpdatedTimestamp(l_datProcessDate);

            //取引パスワード更新者コード
            l_mainAccountParams.setTradingPasswordUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //取引パスワード更新日時
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(l_datProcessDate);

            //携帯番号・勤務先情報更新者コード
            l_mainAccountParams.setMbOffLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //携帯番号・勤務先情報更新日時
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(l_datProcessDate);

            //停止状況更新者コード
            l_mainAccountParams.setEnableOrderLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //停止状況更新日時
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(l_datProcessDate);

            //ＦＸ口座開設区分更新者コード         null
            //ＦＸ口座開設区分更新日時             null
            //外国株式連携口座開設区分更新者コード  null
            //外国株式連携口座開設区分更新日時      null

            //ＭＲＦ設定会社                 ブランク
            l_mainAccountParams.setMrfFundCode(" ");

            //作成日時
            l_mainAccountParams.setCreatedTimestamp(l_datProcessDate);

            //更新日時
            l_mainAccountParams.setLastUpdatedTimestamp(l_datProcessDate);

            //特定管理口座開設区分
            l_mainAccountParams.setSpMngAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);

            //停止状況登録理由
            //案内メール送信フラグ更新者コード                   null
            //案内メール送信フラグ更新日時
            //株式約定メール送信フラグ更新者コード               null
            //株式約定メール送信フラグ更新日時
            //株式未約定メール送信フラグ更新者コード              null
            //株式未約定メール送信フラグ更新日時                  null
            //先OP約定メール送信フラグ更新者コード               null
            ///先OP約定メール送信フラグ更新日時                  null
            //先OP未約定メール送信フラグ更新者コード             null
            //先OP未約定メール送信フラグ更新日時                 null
            
            //顧客登録伝票(G11)キューテーブル.口座開設１２(TBONDオプション） = 
            //「0」(未開設)の場合「0」（未開設）
            //顧客登録伝票(G11)キューテーブル.口座開設１２(TBONDオプション） = 
            //「1」(開設)の場合「1」（開設）
            if (WEB3AccountOpenDef.NOT_OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv12()))
            {
                l_mainAccountParams.setStockOptionAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);
            }
            else if (WEB3AccountOpenDef.OPEN.equals(
                l_hostAccRegVoucherParams.getAccountOpenDiv12()))
            {
                l_mainAccountParams.setStockOptionAccOpenDiv(WEB3AccountOpenDef.OPEN);
            }

            l_mainAccountParams.setSecuredLoanSecAccOpenDiv(WEB3AccountOpenDef.NOT_OPEN);

            //信用口座開設日                                    null
            //先物OP口座開設日                                  null

            //モバイル専用口座開設区分 = 口座開設見込客テーブル.モバイル専用口座区分
            l_mainAccountParams.setOnlyMobileOpenDiv(l_expAccOpenParams.getExtItemDiv15());

            //モバイル専用口座開設区分更新者コード = 「account_open」（固定値）
            l_mainAccountParams.setOnlyMblOpnDivLastUpdater(WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //モバイル専用口座開設区分更新日時 = 処理日時
            l_mainAccountParams.setOnlyMblOpnDivTimestamp(GtlUtils.getSystemTimestamp());

            //プロアマ区分 = 顧客登録伝票(G11)キューテーブル.プロ・アマ区分
            l_mainAccountParams.setProamDiv(l_hostAccRegVoucherParams.getProamDiv());

            //放送法@ = 顧客登録伝票(G11)キューテーブル.外国人区分（放送法@）
            l_mainAccountParams.setBroadcastLaw(l_hostAccRegVoucherParams.getForeignerDivBroadcast());

            //航空法@ = 顧客登録伝票(G11)キューテーブル.外国人区分（航空法@）
            l_mainAccountParams.setAviationLaw(l_hostAccRegVoucherParams.getForeignerDivAviation());

            //ＮＴＴ法@ = 顧客登録伝票(G11)キューテーブル.外国人区分（NTT法@）
            l_mainAccountParams.setNttLaw(l_hostAccRegVoucherParams.getForeignerDivNtt());

            //配当金振込指定区分 = 顧客登録伝票(G11)キューテーブル.配当金振込指定区分
            l_mainAccountParams.setDividendTransDesignate(l_hostAccRegVoucherParams.getDividendTransferDiv());

            //常任代理人 = 顧客登録伝票(G11)キューテーブル.代理人区分（常任代理人）
            l_mainAccountParams.setStandingProxy(l_hostAccRegVoucherParams.getAgentDivPermanent());

            //法@定代理人 = 顧客登録伝票(G11)キューテーブル.代理人区分（法@定代理人）
            l_mainAccountParams.setStatutoryAgent(l_hostAccRegVoucherParams.getAgentDivLegal());

            //２） DB更新
            //　@顧客マスター行オブジェクトの内容で、顧客マスターテーブルを更新（insert）する。
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
        }
        catch (DataException l_ex)
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

    /**
     * (insert顧客余力条件)_<BR>
     * 顧客余力条件テーブルを更新する。<BR>
     * <BR>
     * １） 更新情報をセットする。 <BR>
     * 　@顧客余力条件行の項目に値をセットする。 <BR>
     * <BR>
     * 　@１） - ①@ 顧客余力条件ＩＤの設定<BR>
     * 　@　@顧客余力条件.顧客余力条件ＩＤ = 新規採番（*1）<BR>
     * 　@　@<BR>
     * 　@１） - ② １） - ①@ 以外の設定 <BR>
     * 　@　@DB更新仕様「顧客余力条件ＤＢ更新仕様.xls」参照<BR>
     * <BR>
     * ２） DB更新 <BR>
     * 　@顧客余力条件行オブジェクトの内容で、顧客余力条件テーブルを更新（insert）する。<BR>
     * <BR>
     * <BR>
     * <BR>
     * 　@　@（*1） 顧客余力条件ＩＤの新規採番<BR>
     * 　@　@顧客余力条件DAO.newPkValue()メソッドにて取得する。<BR>
     * 　@　@※ 顧客余力条件DAOクラスはDDLより自動生成する。<BR>
     * @@param l_expAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客<BR>
     * @@param l_strAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07CBF0273
     */
    private void insertTradingpowerCalcCondition(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertTradingpowerCalcCondition(WEB3AccOpenExpAccountOpen, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null || WEB3StringTypeUtility.isEmpty(l_strAccountId))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            TradingpowerCalcConditionParams l_tradPowCalConParams =
                new TradingpowerCalcConditionParams();
            //１） 更新情報をセットする。
            //顧客余力条件ID <-- xTradeにより自動採番された値
            long l_lngCalcConId = TradingpowerCalcConditionDao.newPkValue();
            l_tradPowCalConParams.setCalcConditionId(l_lngCalcConId);

            //口座ID <-- 引数.口座ID
            long l_lngAccountID = Long.parseLong(l_strAccountId);
            l_tradPowCalConParams.setAccountId(l_lngAccountID);

            //部店ID <-- 口座開設見込客テーブル.部店ID
            ExpAccountOpenParams l_expAccOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            l_tradPowCalConParams.setBranchId(l_expAccOpenParams.getBranchId());

            //預り証券評価制区分 <-- 0:未実施
            l_tradPowCalConParams.setAssetEvaluationDiv(
                WEB3AssetEvaluationDivDef.NOT_ENFORCEMENT);

            //特別立替金実績 <-- 0
            l_tradPowCalConParams.setSpecialDebitAmount(0);

            //取引停止区分 <-- 0:取引可能
            l_tradPowCalConParams.setTradingStop(WEB3TradingStopDef.TRADING_ENABLE);

            //信用新規建余力区分 <-- 0:余力可
            l_tradPowCalConParams.setMarginOpenPositionStop(
                WEB3TradingPowerStopDef.TRADING_POWER_ENABLE);

            //先物OP新規建余力区分 <-- 0:余力可
            l_tradPowCalConParams.setIfoOpenPositionStop(
                WEB3TradingPowerStopDef.TRADING_POWER_ENABLE);

            //出金余力区分 <-- 0:余力可
            l_tradPowCalConParams.setPaymentStop(
                WEB3TradingPowerStopDef.TRADING_POWER_ENABLE);

            //その他商品買付余力区分 <-- 0:余力可
            l_tradPowCalConParams.setOtherTradingStop(
                WEB3TradingPowerStopDef.TRADING_POWER_ENABLE);

            //更新者コード <-- 「account_open」(固定値)
            l_tradPowCalConParams.setLastUpdater(WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);

            //処理日時の取得
            Date l_datProcessDate = null;

            l_datProcessDate = GtlUtils.getSystemTimestamp();
            //作成日付 <-- 処理日時

            l_tradPowCalConParams.setCreatedTimestamp(l_datProcessDate);
            //更新日付 <-- 処理日時

            l_tradPowCalConParams.setLastUpdatedTimestamp(l_datProcessDate);

            //追証未入金区分 <-- 0:追証未入金なし
            l_tradPowCalConParams.setAdditionalDepositStop(WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_NOT_STOP);

            //２） DB更新
            l_queryProcessor.doInsertQuery(l_tradPowCalConParams);
        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert補助口座_顧客勘定残高)<BR>
     * 補助口座（顧客勘定残高）テーブルを更新する。<BR>
     * <BR>
     * １） 更新情報をセットする。 <BR>
     * 　@補助口座（顧客勘定残高）行の項目に値をセットする。<BR>
     * <BR>
     * 　@DB更新仕様「補助口座（顧客勘定残高）ＤＢ更新仕様.xls」参照 <BR>
     * <BR>
     * ２） DB更新 <BR>
     * 　@補助口座（顧客勘定残高）行オブジェクトの内容で、補助口座（顧客勘定残高）テーブ<BR>
     * ルを更新（insert）する。<BR>
     * @@param l_expAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客<BR>
     * @@param l_strAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strSubAccountType - (補助口座タイプ)<BR>
     * 補助口座タイプ<BR>
     * @@param l_strSubAccountId - (補助口座ID)<BR>
     * 補助口座ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07C9A000F
     */
    private void insertSubAccount(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strAccountId,
        String l_strSubAccountType,
        String l_strSubAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertSubAccount(WEB3AccOpenExpAccountOpen, " +
            "String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_expAccountOpen == null
                || WEB3StringTypeUtility.isEmpty(l_strAccountId)
                || WEB3StringTypeUtility.isEmpty(l_strSubAccountId))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            SubAccountParams l_subAccountPara = new SubAccountParams();

            //１） 更新情報をセットする。
            //口座ＩＤ <-- 引数.口座ＩＤ
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_subAccountPara.setAccountId(l_lngAccountId);

            //補助口座ＩＤ <-- 引数.補助口座ＩＤ
            long l_lngSubAccountId = Long.parseLong(l_strSubAccountId);
            l_subAccountPara.setSubAccountId(l_lngSubAccountId);

            //補助口座タイプ <-- 引数.補助口座タイプ
            if (WEB3StringTypeUtility.formatNumber(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.intValue()).equals(
                    l_strSubAccountType))
            {
                l_subAccountPara.setSubAccountType(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            else if (WEB3StringTypeUtility.formatNumber(
                SubAccountTypeEnum.FX_MARGIN_SUB_ACCOUNT.intValue()).equals(
                    l_strSubAccountType))
            {
                l_subAccountPara.setSubAccountType(
                    SubAccountTypeEnum.FX_MARGIN_SUB_ACCOUNT);
            }
            else if (WEB3StringTypeUtility.formatNumber(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.intValue()).equals(
                    l_strSubAccountType))
            {
                l_subAccountPara.setSubAccountType(
                    SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }

            //証券会社コード <-- 口座開設見込客テーブル.証券会社コード
            l_subAccountPara.setInstitutionCode(l_expAccountOpen.getInstitutionCode());

            //証券会社ID <-- 口座開設見込客テーブル.証券会社ID
            ExpAccountOpenParams l_expAccOpenParams =
                (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
            l_subAccountPara.setInstitutionId(l_expAccOpenParams.getInstitutionId());

            //部店ＩＤ <-- 口座開設見込客テーブル.部店ID
            l_subAccountPara.setBranchId(l_expAccOpenParams.getBranchId());

            //補助口座ステータス <--  「1」（有効）
            l_subAccountPara.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);

            //口座登録日 <-- null
            l_subAccountPara.setOpenDate(null);

            //口座閉鎖日 <-- NULL
            l_subAccountPara.setCloseDate(null);

            //残高(当日） <-- 0
            l_subAccountPara.setCashBalance(0);

            //処理日時の取得
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();

            //作成日付 <-- 処理日時
            l_subAccountPara.setCreatedTimestamp(l_datProcessDate);

            //更新日付 <-- 処理日時
            l_subAccountPara.setLastUpdatedTimestamp(l_datProcessDate);

            //２） DB更新
            l_queryProcessor.doInsertQuery(l_subAccountPara);

        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert顧客勘定残高_マスタ情報)<BR>
     * 顧客勘定残高（マスタ情報）テーブルを更新する。<BR>
     * <BR>
     * １） 更新情報をセットする。 <BR>
     * 　@顧客勘定残高（マスタ情報）行の項目に値をセットする。 <BR>
     * <BR>
     * 　@DB更新仕様「顧客勘定残高（マスタ情報）ＤＢ更新仕様.xls」参照<BR>
     * <BR>
     * ２） DB更新 <BR>
     * 　@顧客勘定残高（マスタ情報）行オブジェクトの内容で、顧客勘定残高（マスタ情報）テ<BR>
     * ーブルを更新（insert）する。<BR>
     * <BR>
     * <BR>
     * 　@（*1） 顧客勘定残高マスタ情報ＩＤの新規採番<BR>
     * 　@　@顧客勘定残高（マスタ情報）DAO.newPkValue()メソッドにて取得する。<BR>
     * 　@　@※ 顧客勘定残高（マスタ情報）DAOクラスはDDLより自動生成する。<BR>
     * @@param l_strAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strSubAccountId - (補助口座ID)<BR>
     * 補助口座ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D07CD50217
     */
    private void insertTpCashBalance(
        String l_strAccountId,
        String l_strSubAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertTpCashBalance(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (WEB3StringTypeUtility.isEmpty(l_strAccountId)
                || WEB3StringTypeUtility.isEmpty(l_strSubAccountId))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            TpCashBalanceParams l_tpCashBalaPara = new TpCashBalanceParams();

            //１） 更新情報をセットする。
            //顧客勘定残高マスタ情報ID <-- xTradeにより自動採番された値
            long l_lngTpCashBalanceId = TpCashBalanceDao.newPkValue();
            l_tpCashBalaPara.setTpCashBalanceId(l_lngTpCashBalanceId);

            //口座ＩＤ <-- 引数.口座ID
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_tpCashBalaPara.setAccountId(l_lngAccountId);

            //補助口座ＩＤ <-- 引数.補助口座ID
            long l_lngSubAccountId = Long.parseLong(l_strSubAccountId);
            l_tpCashBalaPara.setSubAccountId(l_lngSubAccountId);

            //残高（当日+　@０日） <-- 0を設定
            l_tpCashBalaPara.setCashBalance0(0);

            //残高（当日+　@１日） <-- 0を設定
            l_tpCashBalaPara.setCashBalance1(0);

            //残高（当日+　@２日） <-- 0を設定
            l_tpCashBalaPara.setCashBalance2(0);

            //残高（当日+　@３日） <-- 0を設定
            l_tpCashBalaPara.setCashBalance3(0);

            //残高（当日+　@４日） <-- 0を設定
            l_tpCashBalaPara.setCashBalance4(0);

            //残高（当日+　@５日以降） <-- 0を設定
            l_tpCashBalaPara.setCashBalance5(0);

            //ＭＲＦ残高 <--0を設定
            l_tpCashBalaPara.setMrfBalance(0);

            //当期譲渡益金額 <-- 0を設定
            l_tpCashBalaPara.setCurrentTermCapitalGain(0);

            //翌月譲渡益金額 <--0を設定
            l_tpCashBalaPara.setNextMonthCapitalGain(0);

            //作成日付 <-- 処理日時
            //処理日時の取得
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();
            l_tpCashBalaPara.setCreatedTimestamp(l_datProcessDate);

            //更新日付 <-- 処理日時
            l_tpCashBalaPara.setLastUpdatedTimestamp(l_datProcessDate);

            //２） DB更新
            l_queryProcessor.doInsertQuery(l_tpCashBalaPara);
        }
        catch (DataException l_e)
        {
            log.error("DBへのアクセスに失敗しました。", l_e);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getパスワード )<BR>
     * 口座開設見込客テーブルのWEB3暗号化形式の暗証番号を復号化し、<BR>
     * xTrade-Hash形式に暗号化した値を取得する。<BR>
     * <BR>
     * １）口座開設見込客.初期パスワードの取得 <BR>
     * <BR>
     * 　@暗号化パスワード = 口座開設見込客.初期パスワード<BR>
     * <BR>
     * 　@１ - １）口座開設見込客.初期パスワード = NULL の場合、<BR>
     * <BR>
     * 　@暗号化パスワード = NULL を戻り値として返し、処理を終了する。<BR>
     * <BR>
     * ２）WEB3形式で暗号化された暗証番号を復号化する。<BR>
     * <BR>
     * 　@暗号化パスワードについて、WEB3Crypt.decrypt()にて復号化を行う。<BR>
     * <BR>
     * ３）xTrade-Hash形式に暗号化<BR>
     * <BR>
     * 　@暗号化パスワードについて、PasswordTool.hashPassword()にて暗号化を行う。<BR>
     * <BR>
     * ４）戻り値の取得 <BR>
     * <BR>
     * 　@暗号化パスワードを、戻り値に返す。<BR>
     * @@param l_expAccountOpen - (口座開設見込客)
     * 口座開設見込客
     * @@return String
     */
    private String getPassword(WEB3AccOpenExpAccountOpen l_expAccountOpen)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPassword(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);
        if (l_expAccountOpen == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //口座開設見込客.初期パスワードの取得
        //暗号化パスワード = 口座開設見込客.初期パスワード
        String l_strHashPassword = null;
        ExpAccountOpenParams l_expAccountOpenParams =
            (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
        //　@１ - １）口座開設見込客.初期パスワード = NULL の場合、
        //　@暗号化パスワード = NULL を戻り値として返し、処理を終了する。
        if (l_expAccountOpenParams.getInitialPassword() == null)
        {
            log.info("口座開設見込客.初期パスワード = NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            l_strHashPassword = l_expAccountOpenParams.getInitialPassword();
        }

        //２）WEB3形式で暗号化された暗証番号を復号化する。
        //暗号化パスワードについて、WEB3Crypt.decrypt()にて復号化を行う。
        WEB3Crypt l_crypt = new WEB3Crypt();
        String l_strPassword = l_crypt.decrypt(l_strHashPassword);

        //３）xTrade-Hash形式に暗号化
        //暗号化パスワードについて、PasswordTool.hashPassword()にて暗号化を行う。

        log.exiting(STR_METHOD_NAME);
        return PasswordUtils.hashPassword(l_strPassword);

    }

    /**
     * (insert専用振込先口座)<BR>
     * 専用振込先口座のデータを作成する。<BR>
     * <BR>
     * １）専用振込先口座データ有無の確認 <BR>
     * <BR>
     * 　@下記の条件より、専用振込先口座条件テーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.口座開設見込客.証券会社コード<BR>
     * <BR>
     * 　@検索結果に一致する行が存在しない場合は<BR>
     * 　@戻り値にNULLをセットし、処理を終了する。<BR>
     * <BR>
     * ２）金融機@関格納カラム名を取得する<BR>
     * <BR>
     * 　@下記の条件より、バッチ用プリファ@レンステーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.口座開設見込客.証券会社コード<BR>
     * 　@部店コード = "---"<BR>
     * 　@プリファ@レンス名 = "acct_open.exp.bank_code_column"<BR>
     * 　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 　@検索結果に一致する行がある場合、金融機@関格納カラム名にプリファ@レンスの値をセットする。<BR>
     *　@ 検索結果に一致する行がない場合、金融機@関格納カラム名にNULLをセットする。<BR>
     * <BR>
     * ３）指定銀行コードを取得する<BR>
     * <BR>
     * 　@金融機@関格納カラム名がNULL以外の場合<BR>
     * 　@引数.口座開設見込客.get項目値(金融機@関格納カラム名)を、指定銀行コードにセットする。<BR>
     * <BR>
     * 　@金融機@関格納カラム名がNULLの場合<BR>
     * 　@指定銀行コードにNULLをセットする。<BR>
     * <BR>
     * ４）専用振込先口座条件テーブルの検索<BR>
     * <BR>
     * 　@下記の条件より、専用振込先口座条件テーブルを検索する。<BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = 引数.口座開設見込客.証券会社コード<BR>
     * 　@銀行コード like 指定銀行コード || '%'(*1)<BR>
     * 　@ステータス = "0"<BR>
     * <BR>
     * 　@[並べ替え]<BR>
     * 　@作成日時、銀行コード、支店コード、口座番号<BR>
     * <BR>
     * 　@検索結果に一致する行が存在しない場合は<BR>
     * 　@戻り値にNULLをセットし、処理を終了する。<BR>
     * <BR>
     * 　@(*1)指定銀行コードがNULLの場合、この条件は指定しない<BR>
     * <BR>
     * ５）専用振込先口座条件テーブルの１件目のデータを取得する。<BR>
     * <BR>
     * 　@専用振込先口座条件データ = ４）の検索結果[0]<BR>
     * <BR>
     * ６）金融機@関（銀行）マスタの検索<BR>
     * <BR>
     * 　@下記の条件より、金融機@関（銀行）マスタを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     *　@ 銀行コード = 専用振込先口座条件データ.銀行コード<BR>
     * 　@支店コード = 専用振込先口座条件データ.支店コード<BR>
     * <BR>
     * 　@検索結果に一致する行が存在しない場合は<BR>
     * 　@戻り値にNULLをセットし、処理を終了する。<BR>
     * <BR>
     * 　@６－１）取得したデータのチェック<BR>
     * <BR>
     * 　@　@金融機@関（銀行）マスタ.銀行名（漢字） = NULL、または<BR>
     * 　@　@金融機@関（銀行）マスタ.支店名（漢字） = NULLの場合、戻り値にNULLをセットし、処理を終了する。<BR>
     * <BR>
     * ７）システムプリファ@レンスから、口座名義人を取得する<BR>
     * <BR>
     * 　@下記の条件より、システムプリファ@レンスを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@指定銀行コードがNULL以外の場合<BR>
     * 　@　@名称 = 引数.口座開設見込客.証券会社コード + "_FIN_ACCOUNT_NAME_" + 指定銀行コード<BR>
     * 　@指定銀行コードがNULLの場合<BR>
     * 　@　@名称 = 引数.口座開設見込客.証券会社コード + "_FIN_ACCOUNT_NAME" <BR>
     * <BR>
     * 　@検索結果に一致する行がある場合、口座名義人にシステムプリファ@レンス.値をセットする。<BR>
     * 　@検索結果に一致する行がない場合、口座名義人にNULLをセットする。<BR>
     * <BR>
     * ８）更新情報をセットする。<BR>
     * <BR>
     * 　@専用振込先口座行の項目に値をセットする。<BR>
     * <BR>
     * 　@DB更新仕様「専用振込先口座ＤＢ更新仕様.xls」参照<BR>
     * <BR>
     * ９）DB更新<BR>
     * <BR>
     * 　@専用振込先口座行オブジェクトの内容で、専用振込先口座テーブルを更新（insert）する。<BR>
     * <BR>
     * １０）更新情報をセットする。<BR>
     * <BR>
     * 　@専用振込先口座条件データの項目に値をセットする。<BR>
     * <BR>
     * 　@DB更新仕様「専用振込先口座条件ＤＢ更新仕様.xls」参照<BR>
     * <BR>
     * １１）DB更新<BR>
     * <BR>
     * 　@専用振込先口座条件データの内容で、専用振込先口座条件テーブルを更新（update）する。<BR>
     * <BR>
     * １２）口座番号を返す<BR>
     * <BR>
     * 　@専用振込先口座番号 = 専用振込先口座条件データ.銀行コード + "-"<BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@+ 専用振込先口座条件データ.支店コード + "-"<BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@+ 専用振込先口座条件データ.口座番号<BR>
     * <BR>
     * 　@戻り値に専用振込先口座条件データ.口座番号をセットし、処理を終了する。<BR>
     * @@param l_expAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客<BR>
     * @@param l_strAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    private String insertExclusiveUseAccount(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strAccountId,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " insertExclusiveUseAccount(WEB3AccOpenExpAccountOpen, String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            if (l_expAccountOpen == null || WEB3StringTypeUtility.isEmpty(l_strAccountId))
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            String l_strColumnName = null;     //金融機@関格納カラム名
            String l_strBankCode = null;        //指定銀行コード
            String l_strFinAccountName = null;   //口座名義人
            //１）専用振込先口座データ有無の確認
            //専用振込先口座条件テーブルを検索する。
            //[条件] 証券会社コード = 引数.口座開設見込客.証券会社コード
            String l_strInstitutionCode = l_expAccountOpen.getInstitutionCode();
            String l_strWhere = " institution_code = ? ";
            Object[] l_objs = {l_strInstitutionCode};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecordexcs = l_queryProcessor.doFindAllQuery(
                ExclusiveUseAccountCondRow.TYPE,
                l_strWhere,
                l_objs);

            //検索結果に一致する行が存在しない場合は
            //戻り値にNULLをセットし、処理を終了する。
            if (l_lisRecordexcs == null || l_lisRecordexcs.isEmpty())
            {
                log.info("検索結果に一致する行が存在しない場合");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //２）金融機@関格納カラム名を取得する
            //バッチ用プリファ@レンステーブルを検索する
            //　@[条件]
            //証券会社コード = 引数.口座開設見込客.証券会社コード
            //部店コード = "---"
            //プリファ@レンス名 = "acct_open.exp.bank_code_column"
            //プリファ@レンス名の連番 = 1
            l_strWhere =
                " institution_code = ? and " +
                " branch_code = ? and " +
                " name = ? and " +
                " name_serial_no = ? ";

            Object[] l_objBats =
                {l_strInstitutionCode,
                 WEB3BatchPreferencesBranchCodeDef.COMMON_SETTING,
                 WEB3BatchPreferencesValueDef.ACCT_OPEN_EXP_BANK_CODE_COLUMN,
                 WEB3NameSerialNoDef.SERIAL_NO};
            List l_listRecordbats = l_queryProcessor.doFindAllQuery(
                BatchPreferencesRow.TYPE,
                l_strWhere,
                l_objBats);
            //検索結果に一致する行がある場合、金融機@関格納カラム名にプリファ@レンスの値をセットする。
            //検索結果に一致する行がない場合、金融機@関格納カラム名にNULLをセットする。
            if (l_listRecordbats != null && !l_listRecordbats.isEmpty())
            {
                log.info("検索結果に一致する行がある場合、金融機@関格納カラム名にプリファ@レンスの値をセットする。");

                BatchPreferencesRow l_batchPreferencesRow =
                    (BatchPreferencesRow)l_listRecordbats.get(0);
                l_strColumnName = l_batchPreferencesRow.getValue();
            }

            //３）指定銀行コードを取得する
            //金融機@関格納カラム名がNULL以外の場合
            //引数.口座開設見込客.get項目値(金融機@関格納カラム名)を、指定銀行コードにセットする。
            //金融機@関格納カラム名がNULLの場合
            //指定銀行コードにNULLをセットする。
            if (l_strColumnName != null)
            {
                l_strBankCode =
                    (String)l_expAccountOpen.getItemValue(l_strColumnName);
            }

            //４）専用振込先口座条件テーブルの検索
            //[条件]
            //証券会社コード = 引数.口座開設見込客.証券会社コード
            //銀行コード like 指定銀行コード || '%'(*1)
            //ステータス = "0"
            //(*1)指定銀行コードがNULLの場合、この条件は指定しない
            l_strWhere = " institution_code = ? and ";
            Object[] l_objConds = null;
            if (l_strBankCode != null)
            {
                l_strWhere =
                    l_strWhere +  " fin_institution_code like ?||'%' and " + " status = ? ";
                l_objConds =
                    new Object[]{
                        l_strInstitutionCode,
                        l_strBankCode,
                        WEB3ExclusiveUseAccountStatusDef.UNUSED_RECORD};
            }
            else
            {
                l_strWhere = l_strWhere + " status = ? ";
                l_objConds =
                    new Object[]{l_strInstitutionCode, WEB3ExclusiveUseAccountStatusDef.UNUSED_RECORD};
            }
            //　@[並べ替え]
            //作成日時、銀行コード、支店コード、口座番号
            String l_strSort = "created_timestamp, fin_institution_code, fin_branch_code, fin_account_no ASC";
            List l_lisRecordConds = l_queryProcessor.doFindAllQuery(
                ExclusiveUseAccountCondRow.TYPE,
                l_strWhere,
                l_strSort,
                null,
                l_objConds);
            //検索結果に一致する行が存在しない場合は
            //戻り値にNULLをセットし、処理を終了する。
            if (l_lisRecordConds == null || l_lisRecordConds.isEmpty())
            {
                log.info("検索結果に一致する行が存在しない場合");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //５）専用振込先口座条件テーブルの１件目のデータを取得する。
            //専用振込先口座条件データ = ４）の検索結果[0]
            ExclusiveUseAccountCondRow l_accountCondRow =
                (ExclusiveUseAccountCondRow)l_lisRecordConds.get(0);

            //６）金融機@関（銀行）マスタの検索
            //下記の条件より、金融機@関（銀行）マスタを検索する。
            //[条件]
            //銀行コード = 専用振込先口座条件データ.銀行コード
            //支店コード = 専用振込先口座条件データ.支店コード
            String l_strFinInstitutionCode = l_accountCondRow.getFinInstitutionCode();
            String l_strFinBranchCode = l_accountCondRow.getFinBranchCode();
            l_strWhere = " fin_institution_code = ? and " + " fin_branch_code = ? ";
            Object[] l_objBanks = {l_strFinInstitutionCode, l_strFinBranchCode};
            List l_listRecordBanks = l_queryProcessor.doFindAllQuery(
                FinInstitutionBankRow.TYPE,
                l_strWhere,
                l_objBanks);
            //検索結果に一致する行が存在しない場合は
            //戻り値にNULLをセットし、処理を終了する。
            if (l_listRecordBanks == null || l_listRecordBanks.size() == 0)
            {
                log.info("検索結果に一致する行が存在しない場合");
                return null;
            }
            //６－１）取得したデータのチェック
            //金融機@関（銀行）マスタ.銀行名（漢字） = NULL、または
            //金融機@関（銀行）マスタ.支店名（漢字） = NULLの場合、戻り値にNULLをセットし、処理を終了する。
            FinInstitutionBankRow l_finInstitutionBankRow =
                (FinInstitutionBankRow)l_listRecordBanks.get(0);
            String l_finInstitutionName = l_finInstitutionBankRow.getFinInstitutionName();
            String l_finBranchName = l_finInstitutionBankRow.getFinBranchName();
            if (l_finInstitutionName == null || l_finBranchName == null)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            String l_strRegx = "(　@| )+$";
            l_finInstitutionName = l_finInstitutionName.replaceAll(l_strRegx, "");
            l_finBranchName = l_finBranchName.replaceAll(l_strRegx, "");
            //７）システムプリファ@レンスから、口座名義人を取得する
            //下記の条件より、システムプリファ@レンスを検索する。
            //[条件]
            //指定銀行コードがNULL以外の場合
            //  名称 = 引数.口座開設見込客.証券会社コード + "_FIN_ACCOUNT_NAME_" + 指定銀行コード
            //指定銀行コードがNULLの場合
            //  名称 = 引数.口座開設見込客.証券会社コード + "_FIN_ACCOUNT_NAME"

            String l_strName = null;
            if (l_strBankCode == null)
            {
                l_strName =
                    l_strInstitutionCode + WEB3SystemPreferencesNameDef.FIN_ACCOUNT_NAME;
            }
            else
            {
                l_strName =
                    l_strInstitutionCode + WEB3SystemPreferencesNameDef.FIN_ACCOUNT_NAME + "_" + l_strBankCode;
            }
            SystemPreferencesRow l_sysPreRow = SystemPreferencesDao.findRowByName(l_strName);

            //検索結果に一致する行がある場合、口座名義人にシステムプリファ@レンス.値をセットする。
            //検索結果に一致する行がない場合、口座名義人にNULLをセットする。
            if (l_sysPreRow != null)
            {
                l_strFinAccountName = l_sysPreRow.getValue();
            }

            //８）更新情報をセットする。
            //専用振込先口座行の項目に値をセットする。
            ExclusiveUseAccountParams l_exclusiveUseAccountParams =
                new ExclusiveUseAccountParams();
            //口座ＩＤ= 引数.口座ID
            long l_lngAccountId = Long.parseLong(l_strAccountId);
            l_exclusiveUseAccountParams.setAccountId(l_lngAccountId);
            //証券会社コード= 引数.口座開設見込客.証券会社コード
            l_exclusiveUseAccountParams.setInstitutionCode(l_strInstitutionCode);
            //部店コード= 引数.口座開設見込客.部店コード
            l_exclusiveUseAccountParams.setBranchCode(l_expAccountOpen.getBranchCode());
            //口座コード= 引数.口座コード
            l_exclusiveUseAccountParams.setAccountCode(l_strAccountCode);
            //銀行名= 金融機@関（銀行）マスタ.銀行名（漢字）
            l_exclusiveUseAccountParams.setFinInstitutionName(l_finInstitutionName);
            //支店名= 金融機@関（銀行）マスタ.支店名（漢字）
            l_exclusiveUseAccountParams.setFinBranchName(l_finBranchName);
            //支店コード= 専用振込先口座条件データ.支店コード
            l_exclusiveUseAccountParams.setFinBranchCode(
                l_accountCondRow.getFinBranchCode());
            //口座種類名= 専用振込先口座条件データ.口座種類名
            l_exclusiveUseAccountParams.setFinAccountTypeName(
                l_accountCondRow.getFinAccountTypeName());
            //口座番号= 専用振込先口座条件データ.口座番号
            l_exclusiveUseAccountParams.setFinAccountNo(
                l_accountCondRow.getFinAccountNo());
            //口座名義人= 口座名義人
            l_exclusiveUseAccountParams.setFinAccountName(l_strFinAccountName);
            //更新者コード= 「account_open」（固定値）
            l_exclusiveUseAccountParams.setLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);
            //処理日時の取得
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();
            //作成日時= 処理日時
            l_exclusiveUseAccountParams.setCreatedTimestamp(l_datProcessDate);
            //更新日時= 処理日時
            l_exclusiveUseAccountParams.setLastUpdatedTimestamp(l_datProcessDate);
            //銀行コード= 専用振込先口座条件データ.銀行コード
            l_exclusiveUseAccountParams.setFinInstitutionCode(
                l_accountCondRow.getFinInstitutionCode());

            //９）DB更新
            //専用振込先口座行オブジェクトの内容で、専用振込先口座テーブルを更新（insert）する。
            l_queryProcessor.doInsertQuery(l_exclusiveUseAccountParams);

            //１０）更新情報をセットする。
            //　@専用振込先口座条件データの項目に値をセットする。
            ExclusiveUseAccountCondParams l_exclusiveUseAccountCondParams =
                new ExclusiveUseAccountCondParams(l_accountCondRow);
            //証券会社コード   （既存値）
            //銀行コード   （既存値）
            //支店コード    （既存値）
            //口座種類名   （既存値）
            //口座番号     （既存値）
            //作成日時     （既存値）

            //ステータス   1:使用済みレコード
            l_exclusiveUseAccountCondParams.setStatus(
                WEB3ExclusiveUseAccountStatusDef.USED_RECORD);
            //更新者コード    「account_open」（固定値）
            l_exclusiveUseAccountCondParams.setLastUpdater(
                WEB3AccOpenLastUpdaterDef.ACCOUNT_OPEN);
            //更新日時  処理日時
            l_exclusiveUseAccountCondParams.setLastUpdatedTimestamp(l_datProcessDate);

            //１１）DB更新
            //　@専用振込先口座条件データの内容で、専用振込先口座条件テーブルを更新（update）する。
            l_queryProcessor.doUpdateQuery(l_exclusiveUseAccountCondParams);

            //１２）口座番号を返す
            // 専用振込先口座番号 = 専用振込先口座条件データ.銀行コード + "-"
            //　@　@　@　@　@　@　@　@　@　@　@　@+ 専用振込先口座条件データ.支店コード + "-"
            //　@　@　@　@　@　@　@　@　@　@　@　@+ 専用振込先口座条件データ.口座番号
            //　@戻り値に専用振込先口座番号をセットし、処理を終了する。
            String l_strFinAccountNo =
                l_accountCondRow.getFinInstitutionCode() + "-"
                + l_accountCondRow.getFinBranchCode() + "-"
                + l_accountCondRow.getFinAccountNo();

            log.exiting(STR_METHOD_NAME);
            return l_strFinAccountNo;
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
    }

    /**
     * (insert委託手数料顧客条件登録マスター )<BR>
     *委託手数料顧客条件登録マスターテーブルを更新する。 <BR>
     *<BR>
     *　@１） 手数料コースコード変換マスタテーブルの検索　@ <BR>
     *<BR>
     *　@下記の条件より、検索する。 <BR>
     *<BR>
     *　@[条件] <BR>
     *　@手数料コースコード変換マスタ.部店ID = 引数.口座開設見込客.部店ID <BR>
     *　@手数料コースコード変換マスタ.初期設定区分 = "1" <BR>
     *　@手数料コースコード変換マスタ.適用開始日 ≦ 当日営業日（WEB3GentradeBizDate.roll(0)）  <BR>
     *<BR>
     *　@[並べ替え] <BR>
     *　@適用開始日の降順 <BR>
     *<BR>
     *　@検索結果に一致する行が存在しない場合は <BR>
     *　@処理を終了する。 <BR>
     *<BR>
     *　@２） １）で取得した件数でLoop処理を行う。 <BR>
     *<BR>
     *　@※）Loop処理を行う前に、HashMap を定義しておく。 <BR>
     *<BR>
     *　@　@２ - １） 初期設定区分の重複チェック <BR>
     *<BR>
     *　@　@HashMap の containsKey(Objet key) メソッドにより、１）で取得した手数料商品コードをKeyに検索し、<BR>
     *　@　@戻り値がtrueの場合は手数料商品コードが既に存在する為、次のLoop処理を行う。 <BR>
     *<BR>
     *　@　@２ - ２） 手数料商品コードの登録 <BR>
     *<BR>
     *　@　@手数料商品コードをKey値に、 Value = "1"をHashMap へ登録する。 <BR>
     *<BR>
     *　@　@２ - ３） 当日処理の場合、更新情報をセットする。 <BR>
     *<BR>
     *　@　@　@２ - ３ - １） 当日日付の取得 <BR>
     *<BR>
     *　@　@　@wk有効日 = 処理日時（YYYYMMDD）を、WEB3GentradeBizDate.roll(0)より設定する。<BR>
     *<BR>
     *　@　@　@２ - ３ - ２） 委託手数料顧客条件登録マスター行の項目に値をセットする。 <BR>
     *<BR>
     *　@　@　@DB更新仕様「委託手数料顧客条件登録マスターＤＢ更新仕様.xls」参照 <BR>
     *<BR>
     *　@　@　@２ - ３ - ３） DB更新  <BR>
     *<BR>
     *　@　@　@委託手数料顧客条件登録マスター行オブジェクトの内容で、<BR>
     *　@　@　@委託手数料顧客条件登録マスターテーブルを更新（insert）する。  <BR>
     *<BR>
     *　@　@２ - ４） 翌営業日処理の場合、更新情報をセットする。 <BR>
     *<BR>
     *　@　@　@２ - ４ - １） 翌営業日日付の取得 <BR>
     *<BR>
     *　@　@　@wk有効日 = 翌営業日（YYYYMMDD）を、WEB3GentradeBizDate.roll(1)より設定する。<BR>
     *<BR>
     *<BR>
     *　@　@　@２ - ４ - ２） 手数料コースコード変換マスタテーブルの再検索<BR>
     *<BR>
     *　@　@　@下記の条件より、再検索する。<BR>
     *<BR>
     *　@　@　@[条件] <BR>
     *　@　@　@手数料コースコード変換マスタ.手数料商品コード = １）で取得した手数料商品コード<BR>
     *　@　@　@手数料コースコード変換マスタ.部店ID = 引数.口座開設見込客.部店ID<BR>
     *　@　@　@手数料コースコード変換マスタ.初期設定区分 = "1" <BR>
     *　@　@　@手数料コースコード変換マスタ.適用開始日 = wk有効日 <BR>
     *<BR>
     *　@　@　@①@該当データが存在する場合<BR>
     *<BR>
     *　@　@　@　@２ - ４ - ２） の取得データをもとに、委託手数料顧客条件登録マスター行の項目に値をセットする。<BR>
     *<BR>
     *　@　@　@②該当データが存在しない場合<BR>
     *<BR>
     *　@　@　@　@１） の取得データをもとに、委託手数料顧客条件登録マスター行の項目に値をセットする。<BR>
     *<BR>
     *　@　@　@DB更新仕様「委託手数料顧客条件登録マスターＤＢ更新仕様.xls」参照 <BR>
     *<BR>
     *　@　@　@２ - ４ - ３） DB更新 <BR>
     *<BR>
     *　@　@　@委託手数料顧客条件登録マスター行オブジェクトの内容で、<BR>
     *　@　@　@委託手数料顧客条件登録マスターテーブルを更新（insert）する。<BR>
     *@@param l_expAccountOpen - (口座開設見込客)<BR>
     *口座開設見込客<BR>
     *@@param l_strAccountId - (口座ID)<BR>
     *口座ID<BR>
     *@@throws WEB3BaseException
     */
    private void insertEquityCommissionAccountCondMaster(
        WEB3AccOpenExpAccountOpen l_expAccountOpen,
        String l_strAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertEquityCommissionAccountCondMaster(WEB3AccOpenExpAccountOpen, String)";
        log.entering(STR_METHOD_NAME);
        if (l_expAccountOpen == null || WEB3StringTypeUtility.isEmpty(l_strAccountId))
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //１） 手数料コースコード変換マスタテーブルの検索
        //下記の条件より、検索する。
        //[条件]
        //手数料コースコード変換マスタ.部店ID = 引数.口座開設見込客.部店ID
        //手数料コースコード変換マスタ.初期設定区分 = "1"
        //手数料コースコード変換マスタ.適用開始日 ≦ 当日営業日（WEB3GentradeBizDate.roll(0)）
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" branch_id = ? and  ");
        l_sbQueryString.append(" initial_set_div = ? and ");
        l_sbQueryString.append(" appli_start_date <= ? ");
        ExpAccountOpenParams l_expAccountOpenParams =
            (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
        Timestamp l_tsBaseDate = GtlUtils.getSystemTimestamp();
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBaseDate);
        Timestamp l_strDate = l_genBizDate.roll(0);
        String l_strFomateDate = WEB3DateUtility.formatDate(l_strDate, "yyyyMMdd");

        Object[] l_objs =
            {String.valueOf(l_expAccountOpenParams.getBranchId()),
             WEB3InitialSetDivDef.MAKE_OBJECT_WHEN_ACCOUNT_OPEN,
             l_strFomateDate};
        List l_lisResultRow = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisResultRow = l_QueryProcessor.doFindAllQuery(
                CommCodeChgMstRow.TYPE,
                l_sbQueryString.toString(),
                "appli_start_date desc",
                null,
                l_objs);
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

        if (l_lisResultRow != null && !l_lisResultRow.isEmpty())
        {
            HashMap l_hashMap = new HashMap();
            //２） １）で取得した件数でLoop処理を行う。
            for (int i = 0; i < l_lisResultRow.size(); i ++)
            {
                //HashMap の containsKey(Objet key) メソッドにより、１）で取得した手数料商品コードをKeyに検索し、
                //　@戻り値がtrueの場合は手数料商品コードが既に存在する為、次のLoop処理を行う。
                CommCodeChgMstRow l_commCodeChgMstRow = (CommCodeChgMstRow)l_lisResultRow.get(i);
                if (!l_hashMap.containsKey(l_commCodeChgMstRow.getCommProductCode()))
                {
                    //２ - ２） 手数料商品コードの登録
                    //手数料商品コードをKey値に、 Value = "1"をHashMap へ登録する。
                    l_hashMap.put(l_commCodeChgMstRow.getCommProductCode(), "1");

                    //２ - ３） 当日処理の場合、更新情報をセットする。
                    //２ - ３ - １） 当日日付の取得
                    //wk有効日 = 処理日時（YYYYMMDD）を、WEB3GentradeBizDate.roll(0)より設定する。
                    Timestamp l_strWKValidDate = l_genBizDate.roll(0);
                    String l_strProcessDate = WEB3DateUtility.formatDate(l_strWKValidDate, "yyyyMMdd");

                   //２ - ３ - ２） 委託手数料顧客条件登録マスター行の項目に値をセットする。
                   //DB更新仕様「委託手数料顧客条件登録マスターＤＢ更新仕様.xls」参照
                    EquityCommAccountCondMstParams l_equityCommAccCondMstParams =
                        new EquityCommAccountCondMstParams();
                    // 証券会社コード = 引数.口座開設見込客.証券会社コード
                    l_equityCommAccCondMstParams.setInstitutionCode(
                        l_expAccountOpenParams.getInstitutionCode());
                    // 部店ＩＤ = 引数.口座開設見込客.部店ID
                    l_equityCommAccCondMstParams.setBranchId(
                        l_expAccountOpenParams.getBranchId());
                    // 口座ＩＤ = 引数.口座ID
                    long l_lngAccountId = Long.parseLong(l_strAccountId);
                    l_equityCommAccCondMstParams.setAccountId(l_lngAccountId);
                    // 手数料商品コード = 手数料コースコード変換マスタ.手数料商品コード
                    l_equityCommAccCondMstParams.setCommProductCode(
                        l_commCodeChgMstRow.getCommProductCode());
                    // 有効日 = wk有効日
                    l_equityCommAccCondMstParams.setValidUntilBizDate(l_strProcessDate);
                    // 手数料No． = 手数料コースコード変換マスタ.手数料No
                    l_equityCommAccCondMstParams.setCommissionNo(
                        l_commCodeChgMstRow.getCommissionNo());
                    // 顧客徴収率 = 手数料コースコード変換マスタ.顧客徴収率
                    l_equityCommAccCondMstParams.setAccountChargeRatio(
                        l_commCodeChgMstRow.getAccountChargeRatio());
                    // 作成日付 = 処理日時
                    l_equityCommAccCondMstParams.setCreatedTimestamp(l_tsBaseDate);
                    // 作成日付 = 処理日時
                    l_equityCommAccCondMstParams.setLastUpdatedTimestamp(l_tsBaseDate);

                    //２ - ３ - ３） DB更新
                    try
                    {
                        QueryProcessor l_queryProcessor;
                        l_queryProcessor = Processors.getDefaultProcessor();
                        l_queryProcessor.doInsertQuery(l_equityCommAccCondMstParams);
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

                    //２ - ４） 翌営業日処理の場合、更新情報をセットする。
                    //２ - ４ - １） 翌営業日日付の取得
                    //wk有効日 = 翌営業日（YYYYMMDD）を、WEB3GentradeBizDate.roll(1)より設定する。
                    l_strWKValidDate = l_genBizDate.roll(1);
                    l_strProcessDate = WEB3DateUtility.formatDate(l_strWKValidDate, "yyyyMMdd");
                    //２ - ４ - ２） 手数料コースコード変換マスタテーブルの再検索
                    //記の条件より、再検索する。
                    //[条件]
                    //手数料コースコード変換マスタ.手数料商品コード = １）で取得した手数料商品コード
                    //手数料コースコード変換マスタ.部店ID = 引数.口座開設見込客.部店ID
                    //手数料コースコード変換マスタ.初期設定区分 = "1"
                    //手数料コースコード変換マスタ.適用開始日 = wk有効日
                    l_sbQueryString = new StringBuffer();
                    l_sbQueryString.append(" comm_product_code = ? and ");
                    l_sbQueryString.append(" branch_id = ? and ");
                    l_sbQueryString.append(" initial_set_div = ? and ");
                    l_sbQueryString.append(" appli_start_date = ? ");

                    Object[] l_objMsts =
                        {l_commCodeChgMstRow.getCommProductCode(),
                         String.valueOf(l_expAccountOpenParams.getBranchId()),
                         WEB3InitialSetDivDef.MAKE_OBJECT_WHEN_ACCOUNT_OPEN,
                         l_strProcessDate};

                    List l_lisResultMstRow = null;
                    try
                    {
                        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
                        l_lisResultMstRow = l_QueryProcessor.doFindAllQuery(
                            CommCodeChgMstRow.TYPE,
                            l_sbQueryString.toString(),
                            l_objMsts);
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

                    if (l_lisResultMstRow != null && !l_lisResultMstRow.isEmpty())
                    {
                        CommCodeChgMstRow l_commCodeChgRow =
                            (CommCodeChgMstRow)l_lisResultMstRow.get(0);
                        //２ - ４ - ２） の取得データをもとに、委託手数料顧客条件登録マスター行の項目に値をセットする。
                        l_equityCommAccCondMstParams.setCommProductCode(
                            l_commCodeChgRow.getCommProductCode());
                        l_equityCommAccCondMstParams.setValidUntilBizDate(l_strProcessDate);
                        l_equityCommAccCondMstParams.setCommissionNo(
                            l_commCodeChgRow.getCommissionNo());
                        l_equityCommAccCondMstParams.setAccountChargeRatio(
                            l_commCodeChgRow.getAccountChargeRatio());
                    }
                    else
                    {
                        //１） の取得データをもとに、委託手数料顧客条件登録マスター行の項目に値をセットする。
                        l_equityCommAccCondMstParams.setValidUntilBizDate(l_strProcessDate);
                    }

                    //２ - ４ - ３） DB更新
                    try
                    {
                        QueryProcessor l_queryProcessor;
                        l_queryProcessor = Processors.getDefaultProcessor();
                        l_queryProcessor.doInsertQuery(l_equityCommAccCondMstParams);
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
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update口座開設登録日)<BR>
     * 口座開設見込客テーブルの口座開設登録日を更新する。<BR>
     * <BR>
     * １）　@登録キューデータ取得<BR>
     * 　@口座開設伝票登録受付キュー.データコードに対応する登録キューを以下の条件で検索する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@登録キューテーブル(*1).識別コード = 受付キュー.識別コード And<BR>
     * 　@※登録キュー.識別コードは、伝票の識別コード（order_request_number）<BR>
     * 　@登録キューテーブル(*1).データコード = 受付キュー.データコード And<BR>
     * 　@登録キューテーブル(*1).証券会社コード = 受付キュー.証券会社コード And<BR>
     * 　@登録キューテーブル(*1).部店コード = 受付キュー.部店コード And<BR>
     * 　@登録キューテーブル(*1).顧客コード = 受付キュー.顧客コード<BR>
     * <BR>
     * 　@(*1) [データコードに対応する登録キューテーブル名]<BR>
     * 　@GI82A：顧客登録受付　@→　@顧客登録伝票(G11)キューテーブル<BR>
     * 　@GI82G：契約書徴収受付　@→　@契約書徴収伝票（G1151）キューテーブル<BR>
     * 　@GI82C：振替申込（銀行）受付　@→　@振替申込（銀行）伝票（G26）キューテーブル<BR>
     * 　@GI82H：振替申込（郵貯）受付　@→　@振替申込（郵貯）伝票（G26）キューテーブル<BR>
     * 　@GI82B：保振同意受付　@→　@保振同意伝票（GA300）キューテーブル<BR>
     * 　@GI83G：有料情報受付　@→　@有料情報伝票（G5401）キューテーブル<BR>
     * 　@GI82E：MRF口座受付　@→　@MRF口座伝票（GI601)伝票キューテーブル<BR>
     * 　@GI81I：内部者登録受付　@→　@内部者登録伝票（G9801）キューテーブル<BR>
     * 　@GI82D：GP条件登録受付　@→　@GP条件登録伝票（G1220）キューテーブル<BR>
     * 　@GI84I：上場外株・登録受付　@→　@上場外株・株主登録伝票（G8610）キューテーブル<BR>
     * 　@GI84H：顧客名称登録受付　@→　@顧客正式名称登録伝票（G1190）キューテーブル<BR>
     * 　@GI84E：顧客登録（仲介業）受付　@→　@顧客登録伝票(G11)キューテーブル<BR>
     * 　@GI85D：外貨預金口座登録受付　@→　@外貨預金口座登録伝票(G43)キューテーブル<BR>
     *   GI84C：取報・取残電子交付・特定口座登録受付　@→　@<BR>
     *   取報・取残電子交付・特定口座登録（GI311）キューテーブル<BR>
     *   GI86E：機@構通知情報登録受付　@→　@機@構通知情報登録伝票（GS103）キューテーブル<BR>
     * <BR>
     * 　@取得できなかった場合は、後続処理は行わない。<BR>
     * <BR>
     * ２）　@口座開設伝票作成ステータスチェック<BR>
     * <BR>
     * 　@２ - １）　@オンライン処理のデータコードを取得する。<BR>
     * 　@MQ_MESSAGE_ID_MAPPINGSテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@証券会社コード = 口座開設伝票登録受付キュー.証券会社コード<BR>
     * 　@メッセージID = 'WB3' || 口座開設伝票登録受付キュー.証券会社コード || '10301'<BR>
     * <BR>
     * 　@取得できなかった場合は、後続処理は行わない。<BR>
     * 　@取得された検索結果のデータコードの頭５桁を、 データコード[] の配列に格納する。<BR>
     * <BR>
     * 　@２ - ２）　@伝票作成ステータスチェック<BR>
     * 　@口座開設伝票作成ステータステーブルを、以下の条件で検索する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@証券会社コード = 口座開設伝票登録受付キュー.証券会社コード<BR>
     * 　@識別コード = １）で取得した登録キュー.識別コード（口座開設見込客）<BR>
     * 　@データコード in ２ - １）で取得した データコード[]<BR>
     * <BR>
     * 　@取得できなかった場合は、後続処理は行わない。<BR>
     * 　@取得された検索結果の伝票作成ステータスが全て、"4"（受信済）でない場合、後続処理は行わない。<BR>
     * <BR>
     * ３）　@リアル連携の処理結果チェック<BR>
     * <BR>
     * 　@顧客マスターテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@証券会社コード = 口座開設伝票登録受付キュー.証券会社コード<BR>
     * 　@部店コード = 口座開設伝票登録受付キュー.部店コード<BR>
     * 　@口座コード like 口座開設伝票登録受付キュー.口座コード || '%'<BR>
     * <BR>
     * 　@取得できなかった場合は、リアル連携処理が正常終了していない為、後続処理は行わない。<BR>
     * <BR>
     * ４）　@口座開設見込客データの取得<BR>
     * <BR>
     * 　@２ - ２）で取得された検索結果の伝票作成ステータスが全て、"4"（受信済）の場合、<BR>
     * 　@口座開設見込客テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@証券会社コード = 口座開設伝票登録受付キュー.証券会社コード<BR>
     * 　@識別コード = １）で取得した登録キュー.識別コード（口座開設見込客）<BR>
     * <BR>
     * 　@取得できなかった場合、後続処理は行わない。<BR>
     * 　@口座開設見込客.口座登録日がnullでない場合、後続処理は行わない。<BR>
     * <BR>
     * ５）　@口座登録日の設定<BR>
     * 　@４）で検索されたレコードに以下の値を設定する。<BR>
     * <BR>
     * 　@[設定値]<BR>
     * 　@口座登録日：処理日時（YYYY/MM/DD 00:00:00）<BR>
     * 　@更新日時　@：処理日時<BR>
     * <BR>
     * ６）　@更新処理<BR>
     * 　@５）の設定値で口座開設見込客テーブルの更新処理を行う。<BR>
     * <BR>
     * 　@更新内容は、DB更新仕様「口座開設見込客DB更新（口座開設日）仕様.xls」 参照。<BR>
     * <BR>
     * @@param l_hostAccOpenAccept - (口座開設伝票登録受付キュー)<BR>
     * 口座開設伝票登録受付キュー
     * @@throws WEB3BaseException
     */
    public void updateAccOpenOpenDate(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateAccOpenOpenDate(HostAccOpenAcceptParams)";
        log.entering(STR_METHOD_NAME);

        // [検索条件]
        // 登録キューテーブル(*1).識別コード = 受付キュー.識別コード And　@※登録キュー.識別コードは、伝票の識別コード（order_request_number）
        // 登録キューテーブル(*1).データコード = 受付キュー.データコード And
        // 登録キューテーブル(*1).証券会社コード = 受付キュー.証券会社コード And
        // 登録キューテーブル(*1).部店コード = 受付キュー.部店コード And
        // 登録キューテーブル(*1).顧客コード = 受付キュー.顧客コード
        String l_strOrderRequestNumber = l_hostAccOpenAcceptParams.getOrderRequestNumber();
        String l_strRequestCode = l_hostAccOpenAcceptParams.getRequestCode();
        String l_strInstitutionCode = l_hostAccOpenAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_hostAccOpenAcceptParams.getBranchCode();
        String l_strAccountCode = l_hostAccOpenAcceptParams.getAccountCode();

        String l_strAccOpenRequestNumber = null;
        String[] l_strDataCodes = null;

        String[] l_acceptReqCodes =
            new String[]{"GI82A","GI82G","GI82C","GI82H","GI82B","GI83G","GI82E",
                         "GI81I", "GI82D", "GI84I", "GI84H", "GI84E", "GI85D", "GI84C", "GI86E"};
        String[] l_registReqCodes =
            new String[]{"GI821","GI827","GI823","GI828","GI822","GI837","GI825",
                         "GI819", "GI824", "GI849", "GI848", "GI845", "GI854", "GI843", "GI865"};

        String l_strRegistReqCode = null;
        for (int i = 0; i < l_acceptReqCodes.length; i++)
        {
            if (l_acceptReqCodes[i].equals(l_strRequestCode))
            {
                l_strRegistReqCode = l_registReqCodes[i];
                break;
            }
        }
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // １）登録キューデータ取得
            // GI82A：顧客登録受付　@→　@顧客登録伝票(G11)キューテーブル
            if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST_ACCEPT.equals(l_strRequestCode))
            {
                HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAccRegVoucherRow l_row =
                    (HostAccRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82G：契約書徴収受付　@→　@契約書徴収伝票（G1151）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONTRACT_COLLECT_ACCEPT.equals(l_strRequestCode))
            {
                HostContMrgVoucherPK l_pk = new HostContMrgVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostContMrgVoucherRow l_row =
                    (HostContMrgVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82C：振替申込（銀行）受付　@→　@振替申込（銀行）伝票（G26）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK_ACCEPT.equals(l_strRequestCode))
            {
                HostBankTransVoucherPK l_pk = new HostBankTransVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostBankTransVoucherRow l_row =
                    (HostBankTransVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82H：振替申込（郵貯）受付　@→　@振替申込（郵貯）伝票（G26）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL_ACCEPT.equals(l_strRequestCode))
            {
                HostPostalTransVoucherPK l_pk = new HostPostalTransVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostPostalTransVoucherRow l_row =
                    (HostPostalTransVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82B：保振同意受付　@→　@保振同意伝票（GA300）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGREE_TRANSFER_ACCEPT.equals(l_strRequestCode))
            {
                HostAgreeTransVoucherPK l_pk = new HostAgreeTransVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAgreeTransVoucherRow l_row =
                    (HostAgreeTransVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI83G：有料情報受付　@→　@有料情報伝票（G5401）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_CHARGED_INFO_ACCEPT.equals(l_strRequestCode))
            {
                HostChargedInfoVoucherPK l_pk = new HostChargedInfoVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostChargedInfoVoucherRow l_row =
                    (HostChargedInfoVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI82E：MRF口座受付　@→　@MRF口座伝票（GI601)伝票キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_MRF_ACCOUNT_ACCEPT.equals(l_strRequestCode))
            {
                HostMrfAccVoucherPK l_pk = new HostMrfAccVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostMrfAccVoucherRow l_row =
                    (HostMrfAccVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI81I：内部者登録受付　@→　@内部者登録伝票（G9801）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_INSIDER_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostInsiderRegVoucherPK l_pk = new HostInsiderRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostInsiderRegVoucherRow l_row =
                    (HostInsiderRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }  
            // GI82D：GP条件登録受付　@→　@GP条件登録伝票（G1220）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_GP_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostGpRegVoucherPK l_pk = new HostGpRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostGpRegVoucherRow l_row =
                    (HostGpRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI84I：上場外株・登録受付　@→　@上場外株・株主登録伝票（G8610）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_STOCKHOLDER_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostStockholderRegVoucherPK l_pk = new HostStockholderRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostStockholderRegVoucherRow l_row =
                    (HostStockholderRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI84H：顧客名称登録受付　@→　@顧客正式名称登録伝票（G1190）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_REALNAME_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostRealnameRegVoucherPK l_pk = new HostRealnameRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostRealnameRegVoucherRow l_row =
                    (HostRealnameRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }  
            // GI84E：顧客登録（仲介業）受付　@→　@顧客登録伝票(G11)キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_ACC_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostAccRegVoucherPK l_pk = new HostAccRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAccRegVoucherRow l_row =
                    (HostAccRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI85D：外貨預金口座登録受付　@→　@外貨預金口座登録伝票(G43)キューテーブル
            else if (WEB3HostRequestCodeDef.F_DEPOSIT_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostFDepositVoucherPK l_pk = new HostFDepositVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostFDepositVoucherRow l_row =
                    (HostFDepositVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            // GI84C：取報・取残電子交付・特定口座登録受付　@→　@
            //  取報・取残電子交付・特定口座登録（GI311）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostConditionRegVoucherPK l_pk = new HostConditionRegVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostConditionRegVoucherRow l_row =
                    (HostConditionRegVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
                l_strAccOpenRequestNumber = l_row.getAccOpenRequestNumber();
            }
            //GI86E：機@構通知情報登録受付　@→　@機@構通知情報登録伝票（GS103）キューテーブル
            else if (WEB3HostRequestCodeDef.ACCOPEN_AGENCY_INFO_REG_ACCEPT.equals(l_strRequestCode))
            {
                HostAgencyNotifyVoucherPK l_voucherPK = new HostAgencyNotifyVoucherPK(
                    l_strOrderRequestNumber,
                    l_strRegistReqCode,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode);
                HostAgencyNotifyVoucherRow l_voucherRow =
                    (HostAgencyNotifyVoucherRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_voucherPK);
                l_strAccOpenRequestNumber = l_voucherRow.getAccOpenRequestNumber();
            }
            else
            {
                log.debug("データコードエラー");
                log.exiting(STR_METHOD_NAME);
                return ;
            }

            log.debug("l_strAccOpenRequestNumber is:" + l_strAccOpenRequestNumber);
            
            // ２）　@口座開設伝票作成ステータスチェック
            // ２ - １）　@オンライン処理のデータコードを取得する。
            // MQ_MESSAGE_ID_MAPPINGSテーブルを以下の条件で検索する。
            List l_lisRecords = null;
            String l_strWhere = " institution_code = ? and message_Id = (? || ? || ?) ";
            Object[] l_objs = {l_strInstitutionCode, "WB3", l_strInstitutionCode, "10301"};
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MqMessageIdMappingsRow.TYPE,
                l_strWhere,
                null,
                null,
                l_objs);

            // 取得できなかった場合は、後続処理は行わない。
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                log.debug("MQ_MESSAGE_ID_MAPPINGSデータ取得できなかった場合は、後続処理は行わない。");
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            List l_lisParam = new ArrayList();
            l_lisParam.add(l_strInstitutionCode);
            l_lisParam.add(l_strAccOpenRequestNumber);
            
            int l_intLength = l_lisRecords.size();
            // 取得された検索結果のデータコードの頭５桁を、 データコード[] の配列に格納する。
            for (int i = 0; i < l_intLength; i++)
            {
                l_strDataCodes = new String[l_intLength];
                l_strDataCodes[i] =
                    ((MqMessageIdMappingsRow)l_lisRecords.get(i)).getDataCode().substring(0, 5);
                
                l_lisParam.add(l_strDataCodes[i]);
            }
            
            // ２ - ２）　@伝票作成ステータスチェック 
            // 口座開設伝票作成ステータステーブルを、以下の条件で検索する。
            // 証券会社コード = 口座開設伝票登録受付キュー.証券会社コード
            // 識別コード = １）で取得した登録キュー.識別コード（口座開設見込客）
            // データコード in ２ - １）で取得した データコード[]
            List l_lisRecords1 = null;
            String l_strWhere1 =
                " institution_code = ? and acc_open_request_number = ? ";
            l_strWhere1 = l_strWhere1 + " and request_code in (? ";
            
            int l_intRecordLength = l_lisRecords.size();
            for (int i = 1; i < l_intRecordLength; i++)
            {
                l_strWhere1 = l_strWhere1 + ", ?";
            }
            l_strWhere1 = l_strWhere1 + ") ";

            Object[] l_objs1 = new Object[l_lisParam.size()];
            l_lisRecords1 = l_queryProcessor.doFindAllQuery(
                AccOpenVoucherStatusRow.TYPE,
                l_strWhere1,
                null,
                null,
                l_lisParam.toArray(l_objs1));

            // 取得できなかった場合は、後続処理は行わない。
            if (l_lisRecords1 == null || l_lisRecords1.isEmpty())
            {
                log.debug("口座開設伝票作成ステータスデータ取得できなかった場合は、後続処理は行わない。");
                log.exiting(STR_METHOD_NAME);
                return;
            }

            // 取得された検索結果の伝票作成ステータスが全て、"4"（受信済）でない場合、後続処理は行わない。
            int l_intFlag = 0;
            String l_strVoucherStatus = null;
            for (int i = 0; i < l_lisRecords1.size(); i++)
            {
                l_strVoucherStatus =
                    ((AccOpenVoucherStatusRow)l_lisRecords1.get(i)).getVoucherStatus();
                if (WEB3VoucherStatusDef.RECEIVE_COMPLETE.equals(l_strVoucherStatus))
                {
                    l_intFlag = 1;
                    break;
                }
            }
            
            int l_intFlagAllFour = 0;
            for (int i = 0; i < l_lisRecords1.size(); i++)
            {
                l_strVoucherStatus =
                    ((AccOpenVoucherStatusRow)l_lisRecords1.get(i)).getVoucherStatus();
                if (!WEB3VoucherStatusDef.RECEIVE_COMPLETE.equals(l_strVoucherStatus))
                {
                    l_intFlagAllFour = 1;
                    break;
                }
            }
            
            if (l_intFlag == 0)
            {
                log.debug("伝票作成ステータスが全て、4（受信済）でない場合、後続処理は行わない。");
                log.exiting(STR_METHOD_NAME);
                return;
            }

            // ３）　@リアル連携の処理結果チェック
            // 顧客マスターテーブルを以下の条件で検索する。
            // [検索条件]
            // 証券会社コード = 口座開設伝票登録受付キュー.証券会社コード
            // 部店コード = 口座開設伝票登録受付キュー.部店コード
            // 口座コード like 口座開設伝票登録受付キュー.口座コード || '%'
            List l_lisRecords2 = null;
            String l_strWhere2 =
                " institution_code = ? and branch_code = ? and account_code like ( ? || '%' ) ";
            Object[] l_objs2 =
                {l_strInstitutionCode, l_strBranchCode, l_strAccountCode};
            l_lisRecords2 = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_strWhere2,
                null,
                null,
                l_objs2);

            // 取得できなかった場合は、リアル連携処理が正常終了していない為、後続処理は行わない。
            if (l_lisRecords2 == null || l_lisRecords2.isEmpty())
            {
                log.debug("顧客マスタデータ取得できなかった場合は、後続処理は行わない。");
                log.exiting(STR_METHOD_NAME);
                return;
            }

            // ４）　@口座開設見込客データの取得
            // [検索条件]
            //　@証券会社コード = 口座開設伝票登録受付キュー.証券会社コード
            // 　@識別コード = １）で取得した登録キュー.識別コード（口座開設見込客）
            // 　@２ - ２）で取得された検索結果の伝票作成ステータスが全て、"4"（受信済）の場合、
            ExpAccountOpenParams l_params = null;
            if (l_intFlagAllFour == 0)
            {
                ExpAccountOpenRow l_row =
                    (ExpAccountOpenRow)ExpAccountOpenDao.findRowByInstitutionCodeAccOpenRequestNumber(
                        l_strInstitutionCode,
                        l_strAccOpenRequestNumber);

                // 取得できなかった場合、後続処理は行わない。
                // 口座開設見込客.口座登録日がnullでない場合、後続処理は行わない。
                if (l_row == null)
                {
                    log.debug("取得できなかった場合、後続処理は行わない。");
                    log.exiting(STR_METHOD_NAME);
                    return;    
                }
                
                l_params = new ExpAccountOpenParams(l_row);
                if (l_params.getAccountOpenDate() != null)
                {
                    log.debug("口座開設見込客.口座登録日がnullでない場合、後続処理は行わない。");
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
            }
            
            if (l_params != null)
            {
                // ５）　@口座登録日の設定
                // ４）で検索されたレコードに以下の値を設定する。
                // [設定値]
                // 処理日時の取得
                Date l_datProcessDate = GtlUtils.getSystemTimestamp();
                Date l_datAccountOpenDate = WEB3DateUtility.toDay(l_datProcessDate);

                // 口座登録日：処理日時（YYYY/MM/DD 00:00:00）
                l_params.setAccountOpenDate(l_datAccountOpenDate);

                //更新日時　@：処理日時
                l_params.setLastUpdatedTimestamp(l_datProcessDate);

                // ６）　@更新処理
                // ５）の設定値で口座開設見込客テーブルの更新処理を行う。
                // 更新内容は、DB更新仕様「口座開設見込客DB更新（口座開設日）仕様.xls」 参照。
                l_queryProcessor.doUpdateQuery(l_params);
            }

        }
        catch (DataFindException l_ex)
        {
            log.debug("登録キューデータ取得できなかった場合は、後続処理は行わない。");
            log.exiting(STR_METHOD_NAME);
            
            return;
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

        log.exiting(STR_METHOD_NAME);
    }
}@
