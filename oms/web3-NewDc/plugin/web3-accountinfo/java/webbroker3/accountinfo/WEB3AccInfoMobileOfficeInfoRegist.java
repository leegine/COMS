head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeInfoRegist.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 携帯番号・勤務先情報変更申込(WEB3AccInfoMobileOfficeInfoRegist)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 張宝楠 (中訊) 新規作成
Revesion History : 2006/02/23 呉艶飛 (中訊) モデルNo.086
Revesion History : 2006/03/20 呉艶飛 (中訊) モデルNo.098
Revesion History : 2006/03/20 呉艶飛 (中訊) モデルNo.099
Revesion History : 2006/06/30 丁昭奎 (中訊) 仕様変更管理No.115
Revesion History : 2006/07/20 長瀬亜紀(SCS) get判定確認中フラグ修正(分岐条件追加)対応
Revesion History : 2006/10/9  齊珂   (中訊) モデルNo.124
Revesion History : 2006/10/30 齊珂   (中訊) モデルNo.139
Revesion History : 2006/12/14 周捷 (中訊) モデルNo.154、156
Revesion History : 2007/01/19 何文敏 (中訊) モデルNo.160
*/

package webbroker3.accountinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.data.MobileOfficeInfoRegistDao;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistRow;
import webbroker3.accountinfo.define.WEB3AccInfoContactPriorityDef;
import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.accountinfo.define.WEB3RegistStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeChangeAccount;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3DecisionDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (携帯番号・勤務先情報変更申込)<BR>
 * 携帯番号・勤務先情報変更申込クラス<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeInfoRegist implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeInfoRegist.class);

    /**
     * (携帯番号・勤務先情報変更申込行)<BR>
     * 携帯番号・勤務先情報変更申込行オブジェクト<BR>
     * <BR>
     * ※ 携帯番号・勤務先情報変更申込ParamsクラスはDDLより自動生成する。<BR>
     */
    private MobileOfficeInfoRegistParams mobileOfficeInfoRegistParams;

    /**
     * (携帯番号・勤務先情報変更申込)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 行指定行オブジェクトをプロパティにセットし、インスタンスを生成する。 <BR>
     * @@param l_mobileOfficeInfoRegistParams - 携帯番号・勤務先情報変更申込行オブジェクト<BR>
     * <BR>
     * ※　@携帯番号・勤務先情報変更申込ParamsはDDLより自動生成する。<BR>
     */
    public WEB3AccInfoMobileOfficeInfoRegist(MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams)
    {
        this.mobileOfficeInfoRegistParams = l_mobileOfficeInfoRegistParams;
    }

    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.携帯番号・勤務先情報変更申込行を返却する。 <BR>
     * @@return Object
     * @@roseuid 413D6E910141
     */
    public Object getDataSourceObject()
    {
        return this.mobileOfficeInfoRegistParams;
    }

    /**
     * 　@this.携帯番号・勤務先情報変更申込行をコピーして、同じ内容の<BR>
     * 別インスタンスを作成する（clone）。 <BR>
     * 作成したコピーを自身のthis.携帯番号・勤務先情報変更申込行にセットする。 <BR>
     * @@roseuid 413D6E910161
     */
    public void createForUpdateParams()
    {
        MobileOfficeInfoRegistParams l_params = new MobileOfficeInfoRegistParams(this.mobileOfficeInfoRegistParams);
        this.mobileOfficeInfoRegistParams = l_params;
    }

    /**
     * (is判定確認中)<BR>
     * 管理者が判定確認中かを判定する。<BR>
     * <BR>
     * this.携帯番号・勤務先情報変更申込行.判定確認中フラグ == <BR>
     * BooleanEnum.TRUEの場合true、<BR>
     * 以外はfalseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4141669901BA
     */
    public boolean isDeciding()
    {
        BooleanEnum l_decisionFlag = this.mobileOfficeInfoRegistParams.getDecisionFlag();

        if (BooleanEnum.TRUE.equals(l_decisionFlag))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (set判定)<BR>
     * 本オブジェクトに判定情報をセットする。<BR>
     * <BR>
     * this.携帯番号・勤務先情報変更申込Paramsオブジェクトのプロパティに、<BR>
     * 以下の通りセットを行う。 <BR>
     * <BR>
     * 　@携帯番号・勤務先情報変更申込Params.判定確認中フラグ = <BR>
     * BooleanEnum.FALSE
     * 　@携帯番号・勤務先情報変更申込Params.判定結果 = <BR>
     * 引数の判定結果<BR>
     * 　@携帯番号・勤務先情報変更申込Params.判定者コード = <BR>
     * 引数の管理者コード<BR>
     * 　@携帯番号・勤務先情報変更申込Params.判定日時 = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * 　@携帯番号・勤務先情報変更申込Params.削除フラグ = <BR>
     * BooleanEnum.TRUE<BR>
     * 　@携帯番号・勤務先情報変更申込Params.更新者コード = <BR>
     * 更新者コード<BR>
     * 　@携帯番号・勤務先情報変更申込Params.更新日時 = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * @@param l_strAdministratorCode - 管理者コード
     *
     * @@param l_strDecision - 判定結果<BR>
     * <BR>
     * 1：　@承認<BR>
     * 2：　@不可<BR>
     * @@roseuid 414930F202CA
     */
    public void setDecision(String l_strAdministratorCode, String l_strDecision)
    {
        final String STR_METHOD_NAME = " setDecision(String, String)";
        log.entering(STR_METHOD_NAME);

        //処理日時
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();

        //携帯番号・勤務先情報変更申込Params.判定確認中フラグ = BooleanEnum.FALSE
        this.mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.FALSE);

        //携帯番号・勤務先情報変更申込Params.判定結果 = 引数の判定結果
        this.mobileOfficeInfoRegistParams.setDecision(Integer.parseInt(l_strDecision));

        //携帯番号・勤務先情報変更申込Params.判定者コード = 引数の管理者コード
        this.mobileOfficeInfoRegistParams.setDecisionUpdater(l_strAdministratorCode);

        //携帯番号・勤務先情報変更申込Params.判定日時 = TradingSystem.getSystemTimestamp()
        this.mobileOfficeInfoRegistParams.setDecisionTimestamp(l_datProcessDate);

        //携帯番号・勤務先情報変更申込Params.削除フラグ = BooleanEnum.TRUE
        this.mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.TRUE);

        //携帯番号・勤務先情報変更申込Params.更新者コード = 更新者コード
        this.mobileOfficeInfoRegistParams.setLastUpdater(l_strAdministratorCode);

        //携帯番号・勤務先情報変更申込Params.更新日時 = TradingSystem.getSystemTimestamp()
        this.mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(l_datProcessDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get顧客)<BR>
     * this.携帯番号・勤務先情報変更申込行.口座ＩＤに該当する顧客オブジェクト<BR>
     * を取得する。<BR>
     * @@return WEB3GentradeMainAccount
     * @@roseuid 414A85B0033D
     */
    public WEB3GentradeMainAccount getMainAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMainAccount()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();

        long l_lngAccountId = this.mobileOfficeInfoRegistParams.getAccountId();
        WEB3GentradeMainAccount l_mainAccount = null;

        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_mainAccount;
    }

    /**
     * (get部店)<BR>
     * this.携帯番号・勤務先情報変更申込行.部店ＩＤに該当する部店オブジェクト<BR>
     * を取得する。<BR>
     * @@return WEB3GentradeBranch
     * @@roseuid 414A862101B6
     */
    public WEB3GentradeBranch getBranch() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranch()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();

        long l_lngBranchId = this.mobileOfficeInfoRegistParams.getBranchId();
        WEB3GentradeBranch l_branch = null;

        try
        {
            l_branch = (WEB3GentradeBranch)l_accountMgr.getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_branch;
    }

    /**
     * (get申込日)<BR>
     * this.携帯番号・勤務先情報変更申込行.作成日時の日付部分（YYYYMMDD）<BR>
     * を返却する。<BR>
     * @@return Date
     * @@roseuid 414A86550187
     */
    public Date getRegistDate()
    {
        return WEB3DateUtility.toDay(this.mobileOfficeInfoRegistParams.getCreatedTimestamp());
    }

    /**
     * (get判定日)<BR>
     * this.携帯番号・勤務先情報変更申込行.判定日時の日付部分（YYYYMMDD）<BR>
     * を返却する。<BR>
     * @@return Date
     * @@roseuid 414A868A036C
     */
    public Date getDecisionDate()
    {
        return WEB3DateUtility.toDay(this.mobileOfficeInfoRegistParams.getDecisionTimestamp());
    }

    /**
     * (get申込状況区分)<BR>
     * 申込状況区分を取得する。<BR>
     * <BR>
     * 以下の通り、申込状況区分を返却する。<BR>
     * 　@（判定結果※1 == 0：DEFAULT && 判定確認中フラグ※2 == <BR>
     * BooleanEnum.FALSE）の場合、”判定待ち”<BR>
     * 　@（判定結果※1 == 0：DEFAULT && 判定確認中フラグ※2 == <BR>
     * BooleanEnum.TRUE）の場合、”判定待ち（確認中）”<BR>
     * 　@（.判定結果※1 != 0：DEFAULT）の場合、”判定済み”<BR>
     * <BR>
     * 　@※1 判定結果　@：this.携帯番号・勤務先情報変更申込行.判定結果<BR>
     * 　@※2 判定確認中フラグ　@：this.携帯番号・勤務先情報変更申込行.判定確認中フラグ<BR>
     * @@return String
     * @@roseuid 414A86A60149
     */
    public String getRegistStateDiv()
    {
        final String STR_METHOD_NAME = " getRegistStateDiv()";
        log.entering(STR_METHOD_NAME);

        //判定結果
        int l_intDecision = this.mobileOfficeInfoRegistParams.getDecision();
        //判定確認中フラグ
        BooleanEnum l_decisionFlag = this.mobileOfficeInfoRegistParams.getDecisionFlag();

        String l_strRegistStateDiv = null;

        if (l_intDecision == 0)
        {
            if (BooleanEnum.FALSE.equals(l_decisionFlag))
            {
                //（判定結果※1 == 0：DEFAULT && 判定確認中フラグ※2 == BooleanEnum.FALSE）の場合、”判定待ち”
                l_strRegistStateDiv = WEB3RegistStateDivDef.WAIT_DECISION;

            }
            else if (BooleanEnum.TRUE.equals(l_decisionFlag))
            {
                //（判定結果※1 == 0：DEFAULT && 判定確認中フラグ※2 == BooleanEnum.TRUE）の場合、”判定待ち（確認中）”
                l_strRegistStateDiv = WEB3RegistStateDivDef.WAIT_DECISION_CONFIRMING;
            }
            else
            {
                log.error("データ不整合エラー: 判定確認中フラグ = " + l_decisionFlag);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME
                    );
            }
        }
        else
        {
            //（.判定結果※1 != 0：DEFAULT）の場合、”判定済み”
            l_strRegistStateDiv = WEB3RegistStateDivDef.DECISION_COMPLETE;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strRegistStateDiv;
    }

    /**
     * (createNew携帯番号・勤務先情報変更申込)<BR>
     * （static メソッド）<BR>
     * 携帯番号・勤務先情報変更申込新規行を生成する。
     * <BR>
     * １）　@行オブジェクト生成<BR>
     * 　@携帯番号・勤務先情報変更申込Paramsオブジェクトを生成する。<BR>
     * <BR>
     * 　@※携帯番号・勤務先情報変更申込ParamsはDDLより自動生成する。<BR>
     * <BR>
     * ２）　@行オブジェクトにプロパティをセットする。 <BR>
     * 　@１）で生成した携帯番号・勤務先情報変更申込Paramsオブジェクトの<BR>
     * プロパティに、以下の通りセットを行う。 <BR>
     * <BR>
     * 　@携帯番号・勤務先情報変更申込Params.携帯番号・勤務先情報変更申込ＩＤ =<BR>
     *  新規採番(*1)<BR>
     * 　@携帯番号・勤務先情報変更申込Params.証券会社コード = <BR>
     * 顧客.getInstitution().getInstitutionCode()<BR>
     * 　@携帯番号・勤務先情報変更申込Params.部店ID = <BR>
     *  顧客.getBranch().getBranchId()<BR>
     * 　@携帯番号・勤務先情報変更申込Params.口座ID = <BR>
     * 顧客.getAccountId()<BR>
     * 携帯番号・勤務先情報変更申込Params.口座コード = <BR>
     * 顧客.getAccountCode()<BR>
     * 　@携帯番号・勤務先情報変更申込Params.連絡先電話番号（携帯）　@= <BR>
     * 携帯番号・勤務先情報.携帯番号<BR>
     * 　@携帯番号・勤務先情報変更申込Params.勤務先名称　@= <BR>
     * 携帯番号・勤務先情報.勤務先名称<BR>
     * 　@携帯番号・勤務先情報変更申込Params.勤務先郵便番号　@= <BR>
     * 携帯番号・勤務先情報.勤務先郵便番号<BR>
     * 　@携帯番号・勤務先情報変更申込Params.勤務先住所　@= <BR>
     * 携帯番号・勤務先情報.勤務先住所<BR>
     * 　@携帯番号・勤務先情報変更申込Params.勤務先電話番号　@= <BR>
     * 携帯番号・勤務先情報.勤務先電話番号<BR>
     * 　@携帯番号・勤務先情報変更申込Params.役職　@= <BR>
     * 携帯番号・勤務先情報.役職名<BR>
     * 　@携帯番号・勤務先情報変更申込Params.判定確認中フラグ = <BR>
     * BooleanEnum.FALSE<BR>
     * 　@携帯番号・勤務先情報変更申込Params.判定結果 = <BR>
     * 判定結果.0：DEFAULT<BR>
     * 　@携帯番号・勤務先情報変更申込Params.判定者コード = null<BR>
     * 　@携帯番号・勤務先情報変更申込Params.判定日時 = null<BR>
     * 　@携帯番号・勤務先情報変更申込Params.削除フラグ = <BR>
     * BooleanEnum.FALSE<BR>
     * 　@携帯番号・勤務先情報変更申込Params.更新者コード = <BR>
     * 更新者コード
     * 　@携帯番号・勤務先情報変更申込Params.作成日時 = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * 　@携帯番号・勤務先情報変更申込Params.更新日時 = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * 携帯番号・勤務先情報変更申込Params.連絡先優先順位 1位 = <BR>
     * 携帯番号・勤務先情報.連絡先優先順位 1位 <BR>
     * 携帯番号・勤務先情報変更申込Params.連絡先優先順位 2位 = <BR>
     * 携帯番号・勤務先情報.連絡先優先順位 2位 <BR>
     * 携帯番号・勤務先情報変更申込Params.連絡先優先順位 3位 = <BR>
     * 携帯番号・勤務先情報.連絡先優先順位 3位 <BR>
     * 携帯番号・勤務先情報変更申込Params.顧客正式名称１ = 携帯番号・勤務先情報.正式名称１<BR>  
     * 携帯番号・勤務先情報変更申込Params.顧客正式名称２ = 携帯番号・勤務先情報.正式名称２<BR>  
     * 携帯番号・勤務先情報変更申込Params.職業区分 = 携帯番号・勤務先情報.職業  <BR>
     * 携帯番号・勤務先情報変更申込Params.所属 = 携帯番号・勤務先情報.所属  <BR>
     * 携帯番号・勤務先情報変更申込Params.国籍 = 携帯番号・勤務先情報.国籍  <BR>
     * 携帯番号・勤務先情報変更申込Params.国籍その他 = 携帯番号・勤務先情報.国籍その他 <BR> 
     * 携帯番号・勤務先情報変更申込Params.代表者名（漢字）姓 = 携帯番号・勤務先情報.代表者名（漢字）姓<BR>  
     * 携帯番号・勤務先情報変更申込Params.代表者名（漢字）名 = 携帯番号・勤務先情報.代表者名（漢字）名<BR>  
     * 携帯番号・勤務先情報変更申込Params.代表者名（カナ）姓 = 携帯番号・勤務先情報.代表者名（カナ）姓<BR> 
     * 携帯番号・勤務先情報変更申込Params.代表者名（カナ）名 = 携帯番号・勤務先情報.代表者名（カナ）名<BR>  
     * 携帯番号・勤務先情報変更申込Params.代表者権 = 携帯番号・勤務先情報.代表者権  <BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者名（漢字）姓 = 携帯番号・勤務先情報.取引先責任者名（漢字）姓<BR> 
     * 携帯番号・勤務先情報変更申込Params.取引責任者名（漢字）名 = 携帯番号・勤務先情報.取引先責任者名（漢字）名<BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者名（カナ）姓 = 携帯番号・勤務先情報.取引先責任者名（カナ）姓<BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者名（カナ）名 = 携帯番号・勤務先情報.取引先責任者名（カナ）名<BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者　@所属部署 = 携帯番号・勤務先情報.取引責任者所属部署  <BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者　@役職 = 携帯番号・勤務先情報.取引責任者役職 <BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者郵便番号 = 携帯番号・勤務先情報.取引先責任者郵便番号<BR>  
     * 携帯番号・勤務先情報変更申込Params.取引責任者住所１ = 携帯番号・勤務先情報.取引先責任者住所１  <BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者住所２ = 携帯番号・勤務先情報.取引先責任者住所２  <BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者住所３ = 携帯番号・勤務先情報.取引先責任者住所３  <BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者生年月日　@年号 = 携帯番号・勤務先情報.取引先責任者生年月日年号<BR>  
     * 携帯番号・勤務先情報変更申込Params.取引責任者生年月日 = 携帯番号・勤務先情報.取引先責任者生年月日<BR>
     * 携帯番号・勤務先情報変更申込Params.取引責任者会社直通番号 = 携帯番号・勤務先情報.取引先責任者会社直通番号<BR>  
     * 携帯番号・勤務先情報変更申込Params.その他連絡先（携帯、自宅等） = 携帯番号・勤務先情報.その他の連絡先（携帯、自宅等）<BR> 
     * 携帯番号・勤務先情報変更申込Params.FAX番号 = 携帯番号・勤務先情報.FAX番号 <BR>
     * 携帯番号・勤務先情報変更申込Params.年収 = 携帯番号・勤務先情報.年収 <BR>
     * 携帯番号・勤務先情報変更申込Params.金融資産額 = 携帯番号・勤務先情報.金融資産額 <BR>
     * 携帯番号・勤務先情報変更申込Params.運用予定額 = 携帯番号・勤務先情報.運用予定額 <BR>
     * 携帯番号・勤務先情報変更申込Params.投資目的 = 携帯番号・勤務先情報.投資目的 <BR>
     * 携帯番号・勤務先情報変更申込Params.投資予定期間 = 携帯番号・勤務先情報.投資予定期間 <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（１） = 携帯番号・勤務先情報.投資経験の有無（１） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（２） = 携帯番号・勤務先情報.投資経験の有無（２） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（３） = 携帯番号・勤務先情報.投資経験の有無（３） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（４） = 携帯番号・勤務先情報.投資経験の有無（４） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（５） = 携帯番号・勤務先情報.投資経験の有無（５） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（６） = 携帯番号・勤務先情報.投資経験の有無（６） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（７） = 携帯番号・勤務先情報.投資経験の有無（７） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（８） = 携帯番号・勤務先情報.投資経験の有無（８） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（９） = 携帯番号・勤務先情報.投資経験の有無（９） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験の有無（１０） = 携帯番号・勤務先情報.投資経験の有無（１０） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（１） = 携帯番号・勤務先情報.投資経験（１） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（２） = 携帯番号・勤務先情報.投資経験（２） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（３） = 携帯番号・勤務先情報.投資経験（３） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（４） = 携帯番号・勤務先情報.投資経験（４） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（５） = 携帯番号・勤務先情報.投資経験（５） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（６） = 携帯番号・勤務先情報.投資経験（６） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（７） = 携帯番号・勤務先情報.投資経験（７） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（８） = 携帯番号・勤務先情報.投資経験（８） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（９） = 携帯番号・勤務先情報.投資経験（９） <BR>
     * 携帯番号・勤務先情報変更申込Params.投資経験（１０） = 携帯番号・勤務先情報.投資経験（１０） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（１） = 携帯番号・勤務先情報.取引の種類（１） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（２） = 携帯番号・勤務先情報.取引の種類（２） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（３） = 携帯番号・勤務先情報.取引の種類（３） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（４） = 携帯番号・勤務先情報.取引の種類（４） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（５） = 携帯番号・勤務先情報.取引の種類（５） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（６） = 携帯番号・勤務先情報.取引の種類（６） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（７） = 携帯番号・勤務先情報.取引の種類（７） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（８） = 携帯番号・勤務先情報.取引の種類（８） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（９） = 携帯番号・勤務先情報.取引の種類（９） <BR>
     * 携帯番号・勤務先情報変更申込Params.取引の種類（１０） = 携帯番号・勤務先情報.取引の種類（１０） <BR>
     * 携帯番号・勤務先情報変更申込Params.口座開設の動機@ = 携帯番号・勤務先情報.口座開設の動機@ <BR>
     * 携帯番号・勤務先情報変更申込Params.口座開設の動機@の詳細 = 携帯番号・勤務先情報.口座開設の動機@の詳細 <BR>
     * 携帯番号・勤務先情報変更申込Params.現在利用している証券会社 = 携帯番号・勤務先情報.現在利用している証券会社<BR> 
     * 携帯番号・勤務先情報変更申込Params.インターネット取引区分 = 携帯番号・勤務先情報.インターネット取引区分 <BR>
     * 携帯番号・勤務先情報変更申込Params.紹介支店 = 携帯番号・勤務先情報.紹介支店 <BR>
     * <BR>
     * 　@(*1) 携帯番号・勤務先情報変更申込ＩＤの新規採番 <BR>
     * 　@携帯番号・勤務先情報変更申込DAO.newPkValue()メソッドにて取得する。 <BR>
     * 　@※ 携帯番号・勤務先情報変更申込DAOクラスはDDLより自動生成する。 <BR>
     * <BR>
     * ３）　@携帯番号・勤務先情報変更申込オブジェクト返却<BR>
     * 　@行オブジェクトを指定し、携帯番号・勤務先情報変更申込オブジェクトを<BR>
     * 生成し返却する。<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@携帯番号・勤務先情報変更申込行：　@（２）で生成した行オブジェクト）<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     *
     * @@param l_mobileOfficeInfo - 携帯番号・勤務先情報メッセージ
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 413FF1EF01F7
     */
    public static WEB3AccInfoMobileOfficeInfoRegist createNewMobileOfficeInfoRegist(WEB3GentradeMainAccount l_mainAccount, WEB3AccInfoMobileOfficeInfo l_mobileOfficeInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewMobileOfficeInfoRegist(WEB3GentradeMainAccount, WEB3AccInfoMobileOfficeInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_mobileOfficeInfo == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount + "[l_mobileOfficeInfo] = " + l_mobileOfficeInfo
                );
        }

        MobileOfficeInfoRegistParams l_params = new MobileOfficeInfoRegistParams();

        //処理日時
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();

        long l_lngNewId;
        try
        {
            l_lngNewId = MobileOfficeInfoRegistDao.newPkValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //携帯番号・勤務先情報変更申込Params.携帯番号・勤務先情報変更申込ＩＤ = 新規採番(*1)
        l_params.setMobileOfficeInfoRegistId(l_lngNewId);

        //携帯番号・勤務先情報変更申込Params.証券会社コード = 顧客.getInstitution().getInstitutionCode()
        l_params.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

        //携帯番号・勤務先情報変更申込Params.部店ID = 顧客.getBranch().getBranchId()
        l_params.setBranchId(l_mainAccount.getBranch().getBranchId());
        
        //携帯番号・勤務先情報変更申込Params.口座ID = 顧客.getAccountId()
        l_params.setAccountId(l_mainAccount.getAccountId());
        
        //携帯番号・勤務先情報変更申込Params.口座コード = 顧客.getAccountCode()
        l_params.setAccountCode(l_mainAccount.getAccountCode());

        //携帯番号・勤務先情報変更申込Params.連絡先電話番号（携帯）　@= 携帯番号・勤務先情報.携帯番号
        l_params.setMobile(l_mobileOfficeInfo.mobileTelephone);

        //携帯番号・勤務先情報変更申込Params.勤務先名称　@= 携帯番号・勤務先情報.勤務先名称
        l_params.setOffice(l_mobileOfficeInfo.officeName);

        //携帯番号・勤務先情報変更申込Params.勤務先郵便番号　@= 携帯番号・勤務先情報.勤務先郵便番号
        l_params.setOfficeZipCode(l_mobileOfficeInfo.officeZipCode);

        //携帯番号・勤務先情報変更申込Params.勤務先住所　@= 携帯番号・勤務先情報.勤務先住所
        l_params.setOfficeAddress(l_mobileOfficeInfo.officeAdress);

        //携帯番号・勤務先情報変更申込Params.勤務先電話番号　@= 携帯番号・勤務先情報.勤務先電話番号
        l_params.setOfficeTelephone(l_mobileOfficeInfo.officeTelephone);

        //携帯番号・勤務先情報変更申込Params.役職　@= 携帯番号・勤務先情報.役職名
        l_params.setPost(l_mobileOfficeInfo.position);

        //携帯番号・勤務先情報変更申込Params.申込者コード = 更新者コード
        //Wait QA:WEB3-ACCOUNTINFO-A-DD-0011 - zhang-bn
        l_params.setRegistUpdater(l_strUpdaterCode);

        //携帯番号・勤務先情報変更申込Params.判定確認中フラグ = BooleanEnum.FALSE
        l_params.setDecisionFlag(BooleanEnum.FALSE);

        //携帯番号・勤務先情報変更申込Params.判定結果 = 判定結果.0：DEFAULT
        l_params.setDecision(Integer.parseInt(WEB3DecisionDef.DEFAULT));

        //携帯番号・勤務先情報変更申込Params.判定者コード = null
        l_params.setDecisionUpdater(null);

        //携帯番号・勤務先情報変更申込Params.判定日時 = null
        l_params.setDecisionTimestamp(null);

        //携帯番号・勤務先情報変更申込Params.削除フラグ = BooleanEnum.FALSE
        l_params.setDeleteFlag(BooleanEnum.FALSE);

        //携帯番号・勤務先情報変更申込Params.更新者コード = 更新者コード
        l_params.setLastUpdater(l_strUpdaterCode);

        //携帯番号・勤務先情報変更申込Params.作成日時 = TradingSystem.getSystemTimestamp()
        l_params.setCreatedTimestamp(l_datProcessDate);

        //携帯番号・勤務先情報変更申込Params.更新日時 = TradingSystem.getSystemTimestamp()
        l_params.setLastUpdatedTimestamp(l_datProcessDate);
        
        //携帯番号・勤務先情報変更申込Params.連絡先優先順位 1位 = 携帯番号・勤務先情報.連絡先優先順位 1位 
        l_params.setContactPriority1(l_mobileOfficeInfo.contactPriority1);

        //携帯番号・勤務先情報変更申込Params.連絡先優先順位 2位 = 携帯番号・勤務先情報.連絡先優先順位 2位 
        l_params.setContactPriority2(l_mobileOfficeInfo.contactPriority2);
        
        //携帯番号・勤務先情報変更申込Params.連絡先優先順位 3位 = 携帯番号・勤務先情報.連絡先優先順位 3位 
        l_params.setContactPriority3(l_mobileOfficeInfo.contactPriority3);
        
        //携帯番号・勤務先情報変更申込Params.顧客正式名称１ = 携帯番号・勤務先情報.正式名称１  
        l_params.setRealName1(l_mobileOfficeInfo.accountRealName1);     
        
        //携帯番号・勤務先情報変更申込Params.顧客正式名称２ = 携帯番号・勤務先情報.正式名称２
        l_params.setRealName2(l_mobileOfficeInfo.accountRealName2);   
        
        //携帯番号・勤務先情報変更申込Params.職業区分 = 携帯番号・勤務先情報.職業
        l_params.setOccupationDiv(l_mobileOfficeInfo.occupationDiv);  
        
        //携帯番号・勤務先情報変更申込Params.所属 = 携帯番号・勤務先情報.所属
        l_params.setDepartment(l_mobileOfficeInfo.department); 
        
        //携帯番号・勤務先情報変更申込Params.国籍 = 携帯番号・勤務先情報.国籍
        l_params.setNationality(l_mobileOfficeInfo.nationality); 
        
        //携帯番号・勤務先情報変更申込Params.国籍その他 = 携帯番号・勤務先情報.国籍その他
        l_params.setNationalityOther(l_mobileOfficeInfo.nationalityOther);
        
        //携帯番号・勤務先情報変更申込Params.代表者名（漢字）姓 = 携帯番号・勤務先情報.代表者名（漢字）姓
        l_params.setRepresentFamilyName(l_mobileOfficeInfo.representFamilyName);
        
        //携帯番号・勤務先情報変更申込Params.代表者名（漢字）名 = 携帯番号・勤務先情報.代表者名（漢字）名
        l_params.setRepresentGivenName(l_mobileOfficeInfo.representName);
        
        //携帯番号・勤務先情報変更申込Params.代表者名（カナ）姓 = 携帯番号・勤務先情報.代表者名（カナ）姓
        l_params.setRepresentFamilyNameAlt1(l_mobileOfficeInfo.representFamilyNameKana);
        
        //携帯番号・勤務先情報変更申込Params.代表者名（カナ）名 = 携帯番号・勤務先情報.代表者名（カナ）名
        l_params.setRepresentGivenNameAlt1(l_mobileOfficeInfo.representNameKana);
        
        //携帯番号・勤務先情報変更申込Params.代表者権 = 携帯番号・勤務先情報.代表者権
        l_params.setRepresentPower(l_mobileOfficeInfo.representPower);
        
        //携帯番号・勤務先情報変更申込Params.取引責任者名（漢字）姓 = 携帯番号・勤務先情報.取引先責任者名（漢字）姓
        l_params.setDirectorFamilyName(l_mobileOfficeInfo.directorFamilyName);
        
        //携帯番号・勤務先情報変更申込Params.取引責任者名（漢字）名 = 携帯番号・勤務先情報.取引先責任者名（漢字）名
        l_params.setDirectorGivenName(l_mobileOfficeInfo.directorName);    
        
        //携帯番号・勤務先情報変更申込Params.取引責任者名（カナ）姓 = 携帯番号・勤務先情報.取引先責任者名（カナ）姓
        l_params.setDirectorFamilyNameAlt1(l_mobileOfficeInfo.directorFamilyNameKana);   
        
        //携帯番号・勤務先情報変更申込Params.取引責任者名（カナ）名 = 携帯番号・勤務先情報.取引先責任者名（カナ）名
        l_params.setDirectorGivenNameAlt1(l_mobileOfficeInfo.directorNameKana);   
        
        //携帯番号・勤務先情報変更申込Params.取引責任者　@所属部署 = 携帯番号・勤務先情報.取引責任者所属部署
        l_params.setDirectorDepartment(l_mobileOfficeInfo.directorDepartment); 
        
        //携帯番号・勤務先情報変更申込Params.取引責任者　@役職 = 携帯番号・勤務先情報.取引責任者役職
        l_params.setDirectorPost(l_mobileOfficeInfo.directorPosition); 
        
        //携帯番号・勤務先情報変更申込Params.取引責任者郵便番号 = 携帯番号・勤務先情報.取引先責任者郵便番号  
        l_params.setDirectorZipCode(l_mobileOfficeInfo.directorZipCode); 
        
        //携帯番号・勤務先情報変更申込Params.取引責任者住所１ = 携帯番号・勤務先情報.取引先責任者住所１
        l_params.setDirectorAddress1(l_mobileOfficeInfo.directorAddress1); 
        
        //携帯番号・勤務先情報変更申込Params.取引責任者住所２ = 携帯番号・勤務先情報.取引先責任者住所２
        l_params.setDirectorAddress2(l_mobileOfficeInfo.directorAddress2); 
        
        //携帯番号・勤務先情報変更申込Params.取引責任者住所３ = 携帯番号・勤務先情報.取引先責任者住所３
        l_params.setDirectorAddress3(l_mobileOfficeInfo.directorAddress3);
        
        //携帯番号・勤務先情報変更申込Params.取引責任者生年月日　@年号 = 携帯番号・勤務先情報.取引先責任者生年月日年号
        l_params.setDirectorEraBorn(l_mobileOfficeInfo.directorEraBorn);
        
        //携帯番号・勤務先情報変更申込Params.取引責任者生年月日 = 携帯番号・勤務先情報.取引先責任者生年月日
        l_params.setDirectorBornDate(l_mobileOfficeInfo.directorBornDate);
        
        //携帯番号・勤務先情報変更申込Params.取引責任者会社直通番号 = 携帯番号・勤務先情報.取引先責任者会社直通番号
        l_params.setDirectorCorpTelephone(l_mobileOfficeInfo.directorCorpDirect);
        
        //携帯番号・勤務先情報変更申込Params.その他連絡先（携帯、自宅等） = 携帯番号・勤務先情報.その他の連絡先（携帯、自宅等）
        l_params.setOtherContact(l_mobileOfficeInfo.directorOtherContact);
        
		// 携帯番号・勤務先情報変更申込Params.FAX番号 = 携帯番号・勤務先情報.FAX番号 
        l_params.setFax(l_mobileOfficeInfo.faxTelephone);
        
		// 携帯番号・勤務先情報変更申込Params.年収 = 携帯番号・勤務先情報.年収 
        l_params.setAnnualIncomeDiv(l_mobileOfficeInfo.annualIncomeDiv);
        
		// 携帯番号・勤務先情報変更申込Params.金融資産額 = 携帯番号・勤務先情報.金融資産額 
        l_params.setAssetValueDiv(l_mobileOfficeInfo.assetValueDiv);
        
		// 携帯番号・勤務先情報変更申込Params.運用予定額 = 携帯番号・勤務先情報.運用予定額 
        l_params.setFundBudgetAmountDiv(l_mobileOfficeInfo.fundBundgetAmountDiv);
        
		// 携帯番号・勤務先情報変更申込Params.投資目的 = 携帯番号・勤務先情報.投資目的 
        l_params.setInvestPurposeDiv(l_mobileOfficeInfo.investPurposeDiv);
        
		// 携帯番号・勤務先情報変更申込Params.投資予定期間 = 携帯番号・勤務先情報.投資予定期間 
        l_params.setInvestPlanPeriodDiv(l_mobileOfficeInfo.investPlanPeriodDiv);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（１） = 携帯番号・勤務先情報.投資経験の有無（１）  
        Integer l_intexperienceFlag1 = null;
        if (l_mobileOfficeInfo.experienceFlag1 != null)
        {
            l_intexperienceFlag1 = new Integer(l_mobileOfficeInfo.experienceFlag1);
        }
        l_params.setExperienceFlag1(l_intexperienceFlag1);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（２） = 携帯番号・勤務先情報.投資経験の有無（２）
        Integer l_intexperienceFlag2 = null;
        if (l_mobileOfficeInfo.experienceFlag2 != null)
        {
            l_intexperienceFlag2 = new Integer(l_mobileOfficeInfo.experienceFlag2);
        }
        l_params.setExperienceFlag2(l_intexperienceFlag2);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（３） = 携帯番号・勤務先情報.投資経験の有無（３）
        Integer l_intexperienceFlag3 = null;
        if (l_mobileOfficeInfo.experienceFlag3 != null)
        {
            l_intexperienceFlag3 = new Integer(l_mobileOfficeInfo.experienceFlag3);
        }
        l_params.setExperienceFlag3(l_intexperienceFlag3);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（４） = 携帯番号・勤務先情報.投資経験の有無（４）
        Integer l_intexperienceFlag4 = null;
        if (l_mobileOfficeInfo.experienceFlag4 != null)
        {
            l_intexperienceFlag4 = new Integer(l_mobileOfficeInfo.experienceFlag4);
        }
        l_params.setExperienceFlag4(l_intexperienceFlag4);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（５） = 携帯番号・勤務先情報.投資経験の有無（５）
        Integer l_intexperienceFlag5 = null;
        if (l_mobileOfficeInfo.experienceFlag5 != null)
        {
            l_intexperienceFlag5 = new Integer(l_mobileOfficeInfo.experienceFlag5);
        }
        l_params.setExperienceFlag5(l_intexperienceFlag5);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（６） = 携帯番号・勤務先情報.投資経験の有無（６）
        Integer l_intexperienceFlag6 = null;
        if (l_mobileOfficeInfo.experienceFlag6 != null)
        {
            l_intexperienceFlag6 = new Integer(l_mobileOfficeInfo.experienceFlag6);
        }
        l_params.setExperienceFlag6(l_intexperienceFlag6);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（７） = 携帯番号・勤務先情報.投資経験の有無（７）
        Integer l_intexperienceFlag7 = null;
        if (l_mobileOfficeInfo.experienceFlag7 != null)
        {
            l_intexperienceFlag7 = new Integer(l_mobileOfficeInfo.experienceFlag7);
        }
        l_params.setExperienceFlag7(l_intexperienceFlag7);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（８） = 携帯番号・勤務先情報.投資経験の有無（８）
        Integer l_intexperienceFlag8 = null;
        if (l_mobileOfficeInfo.experienceFlag8 != null)
        {
            l_intexperienceFlag8 = new Integer(l_mobileOfficeInfo.experienceFlag8);
        }
        l_params.setExperienceFlag8(l_intexperienceFlag8);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（９） = 携帯番号・勤務先情報.投資経験の有無（９）
        Integer l_intexperienceFlag9 = null;
        if (l_mobileOfficeInfo.experienceFlag9 != null)
        {
            l_intexperienceFlag9 = new Integer(l_mobileOfficeInfo.experienceFlag9);
        }
        l_params.setExperienceFlag9(l_intexperienceFlag9);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験の有無（１０） = 携帯番号・勤務先情報.投資経験の有無（１０）
        Integer l_intexperienceFlag10 = null;
        if (l_mobileOfficeInfo.experienceFlag10 != null)
        {
            l_intexperienceFlag10 = new Integer(l_mobileOfficeInfo.experienceFlag10);
        }
        l_params.setExperienceFlag10(l_intexperienceFlag10);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験（１） = 携帯番号・勤務先情報.投資経験（１）
        l_params.setExperienceDiv1(l_mobileOfficeInfo.experienceDiv1);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験（２） = 携帯番号・勤務先情報.投資経験（２） 
        l_params.setExperienceDiv2(l_mobileOfficeInfo.experienceDiv2);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験（３） = 携帯番号・勤務先情報.投資経験（３） 
        l_params.setExperienceDiv3(l_mobileOfficeInfo.experienceDiv3);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験（４） = 携帯番号・勤務先情報.投資経験（４） 
        l_params.setExperienceDiv4(l_mobileOfficeInfo.experienceDiv4);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験（５） = 携帯番号・勤務先情報.投資経験（５） 
        l_params.setExperienceDiv5(l_mobileOfficeInfo.experienceDiv5);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験（６） = 携帯番号・勤務先情報.投資経験（６）
        l_params.setExperienceDiv6(l_mobileOfficeInfo.experienceDiv6);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験（７） = 携帯番号・勤務先情報.投資経験（７）
        l_params.setExperienceDiv7(l_mobileOfficeInfo.experienceDiv7);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験（８） = 携帯番号・勤務先情報.投資経験（８）
        l_params.setExperienceDiv8(l_mobileOfficeInfo.experienceDiv8);
        
		// 携帯番号・勤務先情報変更申込Params.投資経験（９） = 携帯番号・勤務先情報.投資経験（９）
        l_params.setExperienceDiv9(l_mobileOfficeInfo.experienceDiv9);
        
		//  携帯番号・勤務先情報変更申込Params.投資経験（１０） = 携帯番号・勤務先情報.投資経験（１０）
        l_params.setExperienceDiv10(l_mobileOfficeInfo.experienceDiv10);
        
		//  携帯番号・勤務先情報変更申込Params.取引の種類（１） = 携帯番号・勤務先情報.取引の種類（１）
        Integer l_intInterest1 = null;
        if (l_mobileOfficeInfo.interest1 != null)
        {
            l_intInterest1 = new Integer(l_mobileOfficeInfo.interest1);
        } 
        l_params.setInterestFlag1(l_intInterest1);
        
		//  携帯番号・勤務先情報変更申込Params.取引の種類（２） = 携帯番号・勤務先情報.取引の種類（２）
        Integer l_intInterest2 = null;
        if (l_mobileOfficeInfo.interest2 != null)
        {
            l_intInterest2 = new Integer(l_mobileOfficeInfo.interest2);
        } 
        l_params.setInterestFlag2(l_intInterest2);
        
		//  携帯番号・勤務先情報変更申込Params.取引の種類（３） = 携帯番号・勤務先情報.取引の種類（３） 
        Integer l_intInterest3 = null;
        if (l_mobileOfficeInfo.interest3 != null)
        {
            l_intInterest3 = new Integer(l_mobileOfficeInfo.interest3);
        } 
        l_params.setInterestFlag3(l_intInterest3);
        
		// 携帯番号・勤務先情報変更申込Params.取引の種類（４） = 携帯番号・勤務先情報.取引の種類（４）
        Integer l_intInterest4 = null;
        if (l_mobileOfficeInfo.interest4 != null)
        {
            l_intInterest4 = new Integer(l_mobileOfficeInfo.interest4);
        } 
        l_params.setInterestFlag4(l_intInterest4);
        
		// 携帯番号・勤務先情報変更申込Params.取引の種類（５） = 携帯番号・勤務先情報.取引の種類（５）
        Integer l_intInterest5 = null;
        if (l_mobileOfficeInfo.interest5 != null)
        {
            l_intInterest5 = new Integer(l_mobileOfficeInfo.interest5);
        } 
        l_params.setInterestFlag5(l_intInterest5);
        
		// 携帯番号・勤務先情報変更申込Params.取引の種類（６） = 携帯番号・勤務先情報.取引の種類（６）
        Integer l_intInterest6 = null;
        if (l_mobileOfficeInfo.interest6 != null)
        {
            l_intInterest6 = new Integer(l_mobileOfficeInfo.interest6);
        } 
        l_params.setInterestFlag6(l_intInterest6);
        
		// 携帯番号・勤務先情報変更申込Params.取引の種類（７） = 携帯番号・勤務先情報.取引の種類（７）
        Integer l_intInteres7 = null;
        if (l_mobileOfficeInfo.interest7 != null)
        {
            l_intInteres7 = new Integer(l_mobileOfficeInfo.interest7);
        } 
        l_params.setInterestFlag7(l_intInteres7);
        
		// 携帯番号・勤務先情報変更申込Params.取引の種類（８） = 携帯番号・勤務先情報.取引の種類（８）
        Integer l_intInteres8 = null;
        if (l_mobileOfficeInfo.interest8 != null)
        {
            l_intInteres8 = new Integer(l_mobileOfficeInfo.interest8);
        } 
        l_params.setInterestFlag8(l_intInteres8);
        
		// 携帯番号・勤務先情報変更申込Params.取引の種類（９） = 携帯番号・勤務先情報.取引の種類（９）
        Integer l_intInteres9 = null;
        if (l_mobileOfficeInfo.interest9 != null)
        {
            l_intInteres9 = new Integer(l_mobileOfficeInfo.interest9);
        } 
        l_params.setInterestFlag9(l_intInteres9);
        
		//  携帯番号・勤務先情報変更申込Params.取引の種類（１０） = 携帯番号・勤務先情報.取引の種類（１０）
        Integer l_intInteres10 = null;
        if (l_mobileOfficeInfo.interest10 != null)
        {
            l_intInteres10 = new Integer(l_mobileOfficeInfo.interest10);
        } 
        l_params.setInterestFlag10(l_intInteres10);
        
		//  携帯番号・勤務先情報変更申込Params.口座開設の動機@ = 携帯番号・勤務先情報.口座開設の動機@
        l_params.setAppliMotivatDiv(l_mobileOfficeInfo.appliMotivatDiv);
        
		//  携帯番号・勤務先情報変更申込Params.口座開設の動機@の詳細 = 携帯番号・勤務先情報.口座開設の動機@の詳細
        l_params.setAppliMotivatDivDetail(l_mobileOfficeInfo.appliMotivatDetail);
        
		//  携帯番号・勤務先情報変更申込Params.現在利用している証券会社 = 携帯番号・勤務先情報.現在利用している証券会社
        l_params.setUseInstitutionDiv(l_mobileOfficeInfo.useInstitutionDiv);
        
		//  携帯番号・勤務先情報変更申込Params.インターネット取引区分 = 携帯番号・勤務先情報.インターネット取引区分
        l_params.setInternetTradeDiv(l_mobileOfficeInfo.internetTradeDiv);
        
		//  携帯番号・勤務先情報変更申込Params.紹介支店 = 携帯番号・勤務先情報.紹介支店 

        l_params.setIntroduceBranchCode(l_mobileOfficeInfo.introduceBranch);
        
        log.exiting(STR_METHOD_NAME);

        //行オブジェクトを指定し、携帯番号・勤務先情報変更申込オブジェクトを生成し返却する。
        return new WEB3AccInfoMobileOfficeInfoRegist(l_params);

    }

    /**
     * (get携帯番号・勤務先情報変更申込)<BR>
     * （static メソッド）<BR>
     * 顧客に該当する携帯番号・勤務先情報変更申込を取得する。<BR>
     * <BR>
     * １）　@携帯番号・勤務先情報変更申込テーブル検索<BR>
     * 　@以下の条件で、携帯番号・勤務先情報変更申込テーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 顧客.getInstitution().getInstitutionCode() And<BR>
     * 　@部店ID = 顧客.getBranch().getBranchId() And<BR>
     * 　@口座ID = 顧客.getAccountId() And<BR>
     * 　@削除フラグ = BooleanEnum.FALSE<BR>
     * <BR>
     * ２）　@携帯番号・勤務先情報変更申込オブジェクト返却<BR>
     * 　@取得した各行オブジェクトについて、携帯番号・勤務先情報変更申込<BR>
     * オブジェクトを生成し返却する。<BR>
     * 　@行が取得できなかった場合は、nullを返却する。<BR>
     * 　@行が複数件取得できた場合は、データ不整合と判定し、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01169<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     *
     * @@return webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist
     * @@roseuid 413FF1EF01FA
     */
    public static WEB3AccInfoMobileOfficeInfoRegist getMobileOfficeInfoRegist(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMobileOfficeInfoRegist(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }

        List l_lisRecords = null;

        try
        {

            //以下の条件で、携帯番号・勤務先情報変更申込テーブルを検索する。

            //[条件]
            //[条件]
            //証券会社コード = 顧客.getInstitution().getInstitutionCode() And
            //部店ID = 顧客.getBranch().getBranchId() And
            //口座ID = 顧客.getAccountId() And
            //削除フラグ = BooleanEnum.FALSE

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ?");
            l_sbQueryString.append(" and branch_id = ?");
            l_sbQueryString.append(" and account_id = ?");
            l_sbQueryString.append(" and delete_flag = ?");


            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionCode(),
                "" + l_mainAccount.getBranch().getBranchId(),
                "" + l_mainAccount.getAccountId(),
                BooleanEnum.FALSE
                };

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MobileOfficeInfoRegistRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            //行が取得できなかった場合は、nullを返却する。
            return null;
        }
        else if (l_intSize > 1)
        {
            //行が複数件取得できた場合は、データ不整合と判定し、例外をスローする。
            log.error("データ不整合エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01169,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME
                );
        }

        WEB3AccInfoMobileOfficeInfoRegist l_mobileOfficeInfoRegist =
            new WEB3AccInfoMobileOfficeInfoRegist((MobileOfficeInfoRegistParams)l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_mobileOfficeInfoRegist;
    }

    /**
     * (get携帯番号・勤務先情報変更申込)<BR>
     * （static メソッド）<BR>
     * 指定に該当する携帯番号・勤務先情報変更申込オブジェクトのListを取得する。<BR>
     * <BR>
     * １）　@QueryProcessor.doFindAllQuery( )により、<BR>
     * 携帯番号・勤務先情報変更申込行オブジェクトのListを取得する。 <BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数]<BR>
     *　@　@   rowType：　@携帯番号・勤務先情報変更申込Row.TYPE<BR>
     *　@　@   where：　@検索条件文字列<BR>
     *　@　@   bindVars：　@検索条件データコンテナ<BR>
     *　@　@   orderBy：　@ソート条件<BR>
     *      conditions: null<BR>
     * <BR>
     * ２）　@検索結果の行オブジェクトで携帯番号・勤務先情報変更申込オブジェクトを生成し、Listで返却する。<BR> 
     *　@但し、抹消済の行オブジェクトの場合、返却値Listに含めない。<BR>
     *<BR>
     *　@[抹消済の判定]<BR>
     *　@削除フラグ == BooleanEnum.TRUE && 判定結果 == 0　@<BR>
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_sortCondition - sort条件
     * @@return List
     * @@roseuid 4149783D02EA
     */
    public static List getMobileOfficeInfoRegist(String l_strQueryString, String[] l_queryContainer, String l_sortCondition)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMobileOfficeInfoRegist(String, String[])";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {

            //QueryProcessor.doFindAllQuery( )により、委託手数料変更申込行オブジェクトのListを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MobileOfficeInfoRegistRow.TYPE,
                l_strQueryString,
                l_sortCondition,
				null,
                l_queryContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            return null;
        }

        List l_lisMobileOfficeInfoRegists = new Vector();

        for (int i = 0; i < l_intSize; i++)
        {
            MobileOfficeInfoRegistParams l_params = (MobileOfficeInfoRegistParams)l_lisRecords.get(i);
            if (BooleanEnum.TRUE.equals(l_params.getDeleteFlag()) && (l_params.getDecision() == 0))
            {
                
            }
            else
            {
                l_lisMobileOfficeInfoRegists.add(new WEB3AccInfoMobileOfficeInfoRegist(l_params));
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_lisMobileOfficeInfoRegists;

    }
    
    /**
     * (get判定日時)<BR>
     * this.携帯番号・勤務先情報変更申込行.判定日時を返却する。<BR>
     * @@return Date
     */
    public Date getDecisionTime()
    {        
        return this.mobileOfficeInfoRegistParams.getDecisionTimestamp();
    }
    
    /**
     * (get申込日時)<BR>
     * this.携帯番号・勤務先情報変更申込行.作成日時を返却する。<BR>
     *<BR>
     * @@return Date 
     */
    public Date getRegistTime()
    {
        return this.mobileOfficeInfoRegistParams.created_timestamp;
    }
    
    /**
     * (is連絡先優先順位)<BR>
     * (static メソッド)<BR>
     * 連絡先優先順位の整合性チェックを行う。<BR>
     * （入力がある場合は、以下のチェックを行う ）<BR>
     * <BR>
     * 1．連絡先優先順位の1位～3位で重複している場合は、例外をスローする。<BR>
     * （※連絡先優先順位 = "0（なし）"の重複は除く）<BR>
     * <BR>
     * 　@　@⇒BUSINESS_ERROR_02400<BR>
     * 　@　@⇒連絡先優先順位1位～3位に重複している連絡先があります。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02400<BR>
     * <BR>
     * <BR>
     * 2．口座タイプ = 法@人の時（3）<BR>
     * （顧客.getAccountType() = MainAccountTypeEnum.CORPORATE_ACCOUNT）<BR>
     * <BR>
     * 　@2-1）未処理<BR>
     * 　@※次期案件にて処理を記入<BR>
     * <BR>
     * <BR>
     * 3．口座タイプ = 個人の時（0，1，2）<BR>
     * （顧客.getAccountType() != MainAccountTypeEnum.CORPORATE_ACCOUNT）<BR>
     * <BR>
     * 　@3-1）連絡先優先順位の1位～3位に、"3（携帯・その他）"が選択されているのに、<BR>
     * 　@「携帯番号」が"未入力"の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@⇒BUSINESS_ERROR_02409<BR>
     * 　@　@⇒携帯番号・その他連絡先が未入力のため、連絡先優先順位に”携帯・その他”を選択する事は出来ません。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02409<BR>
     * <BR>
     * 　@3-2）連絡先優先順位の1位～3位に、"2（勤務先）"が選択されているのに、<BR>
     * 　@「勤務先電話番号」が"未入力"の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@⇒BUSINESS_ERROR_02410<BR>
     * 　@　@⇒勤務先電話番号が未入力のため、連絡先優先順位に”勤務先”を選択する事は出来ません。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02410<BR>
     * @@param l_mainAccount - 顧客オブジェクト<BR>
     * @@param l_changedMobileOfficeInfo - 携帯番号・勤務先情報<BR>
     * @@throws WEB3BaseException
     */
    public static void isContactPriority(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3AccInfoMobileOfficeInfo l_changedMobileOfficeInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isContactPriority(WEB3GentradeMainAccount, WEB3AccInfoMobileOfficeInfo)";
        log.entering(STR_METHOD_NAME);
        
        //連絡先優先順位の整合性チェックを行う。
        // （入力がある場合は、以下のチェックを行う ）
        //1．連絡先優先順位の1位～3位で重複している場合は、例外をスローする。
        // （※連絡先優先順位 = "0（なし）"の重複は除く）        
        boolean l_blnIsContactPriority = false;
        if (l_changedMobileOfficeInfo.contactPriority1 != null)
        {
            if (!WEB3AccInfoContactPriorityDef.DEFAULT.equals(l_changedMobileOfficeInfo.contactPriority1) 
                && (l_changedMobileOfficeInfo.contactPriority1.equals(l_changedMobileOfficeInfo.contactPriority2) 
                || l_changedMobileOfficeInfo.contactPriority1.equals(l_changedMobileOfficeInfo.contactPriority3)))
            {
                l_blnIsContactPriority = true;
            }            
        }
        if (l_changedMobileOfficeInfo.contactPriority2 != null)
        {
            if (!WEB3AccInfoContactPriorityDef.DEFAULT.equals(l_changedMobileOfficeInfo.contactPriority2) 
                    && l_changedMobileOfficeInfo.contactPriority2.equals(l_changedMobileOfficeInfo.contactPriority3))

                {
                    l_blnIsContactPriority = true;
                }   
        }
        
        if (l_blnIsContactPriority)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("連絡先優先順位1位～3位に重複している連絡先があります。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02400,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                "連絡先優先順位1位～3位に重複している連絡先があります。");
        }
        
        //2．口座タイプ = 法@人の時（3）
        // （顧客.getAccountType() = MainAccountTypeEnum.CORPORATE_ACCOUNT）
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
        {
            //2-1）未処理
            // 　@※次期案件にて処理を記入
        }
        
        //3．口座タイプ = 個人の時（0，1，2）
        // （顧客.getAccountType() != MainAccountTypeEnum.CORPORATE_ACCOUNT）
        if (!MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
        {
            //3-1）連絡先優先順位の1位～3位に、"3（携帯・その他）"が選択されているのに、
            // 　@「携帯番号」が"未入力"の場合、例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(l_changedMobileOfficeInfo.mobileTelephone)
                && (WEB3AccInfoContactPriorityDef.MOBILE_OTHER_CONTACT.equals(l_changedMobileOfficeInfo.contactPriority1) 
                || WEB3AccInfoContactPriorityDef.MOBILE_OTHER_CONTACT.equals(l_changedMobileOfficeInfo.contactPriority2) 
                || WEB3AccInfoContactPriorityDef.MOBILE_OTHER_CONTACT.equals(l_changedMobileOfficeInfo.contactPriority3)))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("携帯番号・その他連絡先が未入力のため、連絡先優先順位に”携帯・その他”を選択する事は出来ません。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02409,
                    WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,                    
                    "携帯番号・その他連絡先が未入力のため、連絡先優先順位に”携帯・その他”を選択する事は出来ません。");
            }
           
            //3-2）連絡先優先順位の1位～3位に、"2（勤務先）"が選択されているのに、
            // 　@「勤務先電話番号」が"未入力"の場合、例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(l_changedMobileOfficeInfo.officeTelephone)
                && (WEB3AccInfoContactPriorityDef.OFFICE_TRADER_DUTY.equals(l_changedMobileOfficeInfo.contactPriority1) 
                || WEB3AccInfoContactPriorityDef.OFFICE_TRADER_DUTY.equals(l_changedMobileOfficeInfo.contactPriority2) 
                || WEB3AccInfoContactPriorityDef.OFFICE_TRADER_DUTY.equals(l_changedMobileOfficeInfo.contactPriority3)))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("勤務先電話番号が未入力のため、連絡先優先順位に”勤務先”を選択する事は出来ません。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02410,
                    WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,                    
                    "勤務先電話番号が未入力のため、連絡先優先順位に”勤務先”を選択する事は出来ません。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get判定確認中フラグ)<BR>
     * this.携帯番号・勤務先情報変更申込行.判定確認中フラグを返却する。<BR>
     * @@return String
     */
    public String getDecisionFlag()
    {
        if(this.mobileOfficeInfoRegistParams != null
           && this.mobileOfficeInfoRegistParams.getDecisionFlag() != null)
        {
            return this.mobileOfficeInfoRegistParams.getDecisionFlag().intValue() + "";
        }
        else
        {        
            return null;
        }
    }
    
    /**
     * (get判定結果)<BR>
     * this.携帯番号・勤務先情報変更申込行.判定結果を返却する。<BR>
     * @@return String
     */
    public String getDecision()
    {
        if(this.mobileOfficeInfoRegistParams != null)
        {
            return String.valueOf(this.mobileOfficeInfoRegistParams.getDecision());
        }
        else
        { 
            return null;
        }
    }
    
    /**
     * (get削除フラグ)<BR>
     * this.携帯番号・勤務先情報変更申込行.削除フラグを返却する。<BR>
     * @@return String
     */
    public String getDeleteFlag() 
    {
        if(this.mobileOfficeInfoRegistParams != null)
        {
            return this.mobileOfficeInfoRegistParams.getDeleteFlag().intValue() + "";
        }
        else
        { 
            return null;
        }
    }
    
    /**
     * (is変更項目)<BR>
     * （static メソッド） <BR>
     * 変更項目の有無チェックを行う。  <BR>
     * <BR>
     * １） （引数）変更後情報の全ての項目内容が  <BR>
     *      （引数）変更前情報の全ての項目内容と  <BR>
     *      ひとつでも差異がある場合、変更項目有として  <BR>
     *      TRUEを返却する。  <BR>
     * ２） （引数）変更後情報の全ての項目内容が  <BR>
     *      （引数）変更前情報の全ての項目内容と  <BR>
     *      完全に一致する場合、変更項目無として  <BR>
     *      FALSEを返却する。<BR>
     * <BR>     
     * @@param l_preMobileOfficeInfo - (変更前情報)<BR>
     * 変更前情報<BR>
     * @@param l_changedMobileOfficeInfo - (変更後情報)<BR>
     * 変更後情報<BR>
     * @@return boolean
     */
    public static boolean isChangedItem(
        WEB3AccInfoMobileOfficeInfo l_preMobileOfficeInfo, 
        WEB3AccInfoMobileOfficeInfo l_changedMobileOfficeInfo)
    {
        final String STR_METHOD_NAME = " isChangedItem(WEB3AccInfoMobileOfficeInfo, WEB3AccInfoMobileOfficeInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_preMobileOfficeInfo == null)
        {
            log.error("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                "パラメータ値不正。"
                );
        }
        
        if (l_changedMobileOfficeInfo == null)
        {
            return true;
        }
        //（引数）変更後情報の全ての項目内容が（引数）変更前情報の全ての項目内容とひとつでも差異がある場合、変更項目有として
        // TRUEを返却する。 

        //正式名称1
        if (l_preMobileOfficeInfo.accountRealName1 != null)
        {
            if (!(l_preMobileOfficeInfo.accountRealName1.equals(l_changedMobileOfficeInfo.accountRealName1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.accountRealName1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //正式名称2
        if (l_preMobileOfficeInfo.accountRealName2 != null)
        {
            if (!(l_preMobileOfficeInfo.accountRealName2.equals(l_changedMobileOfficeInfo.accountRealName2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.accountRealName2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //年収
        if (l_preMobileOfficeInfo.annualIncomeDiv != null)
        {
            if (!(l_preMobileOfficeInfo.annualIncomeDiv.equals(l_changedMobileOfficeInfo.annualIncomeDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.annualIncomeDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //口座開設の動機@の詳細
        if (l_preMobileOfficeInfo.appliMotivatDetail != null)
        {
            if (!(l_preMobileOfficeInfo.appliMotivatDetail.equals(l_changedMobileOfficeInfo.appliMotivatDetail)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.appliMotivatDetail != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //口座開設の動機@
        if (l_preMobileOfficeInfo.appliMotivatDiv != null)
        {
            if (!(l_preMobileOfficeInfo.appliMotivatDiv.equals(l_changedMobileOfficeInfo.appliMotivatDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.appliMotivatDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //金融資産額
        if (l_preMobileOfficeInfo.assetValueDiv != null)
        {
            if (!(l_preMobileOfficeInfo.assetValueDiv.equals(l_changedMobileOfficeInfo.assetValueDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.assetValueDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //連絡先優先順位１位
        if (l_preMobileOfficeInfo.contactPriority1 != null)
        {
            if (!(l_preMobileOfficeInfo.contactPriority1.equals(l_changedMobileOfficeInfo.contactPriority1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.contactPriority1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //連絡先優先順位２位
        if (l_preMobileOfficeInfo.contactPriority2 != null)
        {
            if (!(l_preMobileOfficeInfo.contactPriority2.equals(l_changedMobileOfficeInfo.contactPriority2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.contactPriority2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //連絡先優先順位３位
        if (l_preMobileOfficeInfo.contactPriority3 != null)
        {
            if (!(l_preMobileOfficeInfo.contactPriority3.equals(l_changedMobileOfficeInfo.contactPriority3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.contactPriority3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //所属
        if (l_preMobileOfficeInfo.department != null)
        {
            if (!(l_preMobileOfficeInfo.department.equals(l_changedMobileOfficeInfo.department)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.department != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者住所1
        if (l_preMobileOfficeInfo.directorAddress1 != null)
        {
            if (!(l_preMobileOfficeInfo.directorAddress1.equals(l_changedMobileOfficeInfo.directorAddress1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorAddress1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者住所2
        if (l_preMobileOfficeInfo.directorAddress2 != null)
        {
            if (!(l_preMobileOfficeInfo.directorAddress2.equals(l_changedMobileOfficeInfo.directorAddress2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorAddress2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者住所3
        if (l_preMobileOfficeInfo.directorAddress3 != null)
        {
            if (!(l_preMobileOfficeInfo.directorAddress3.equals(l_changedMobileOfficeInfo.directorAddress3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorAddress3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者生年月日
        if (l_preMobileOfficeInfo.directorBornDate != null)
        {
            if (!(l_preMobileOfficeInfo.directorBornDate.equals(l_changedMobileOfficeInfo.directorBornDate)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorBornDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者会社直通番号
        if (l_preMobileOfficeInfo.directorCorpDirect != null)
        {
            if (!(l_preMobileOfficeInfo.directorCorpDirect.equals(l_changedMobileOfficeInfo.directorCorpDirect)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorCorpDirect != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者所属部署
        if (l_preMobileOfficeInfo.directorDepartment != null)
        {
            if (!(l_preMobileOfficeInfo.directorDepartment.equals(l_changedMobileOfficeInfo.directorDepartment)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorDepartment != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者生年月日年号
        if (l_preMobileOfficeInfo.directorEraBorn != null)
        {
            if (!(l_preMobileOfficeInfo.directorEraBorn.equals(l_changedMobileOfficeInfo.directorEraBorn)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorEraBorn != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者名（漢字）姓
        if (l_preMobileOfficeInfo.directorFamilyName != null)
        {
            if (!(l_preMobileOfficeInfo.directorFamilyName.equals(l_changedMobileOfficeInfo.directorFamilyName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorFamilyName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者名（カナ）姓
        if (l_preMobileOfficeInfo.directorFamilyNameKana != null)
        {
            if (!(l_preMobileOfficeInfo.directorFamilyNameKana.equals(l_changedMobileOfficeInfo.directorFamilyNameKana)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorFamilyNameKana != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者名（漢字）名
        if (l_preMobileOfficeInfo.directorName != null)
        {
            if (!(l_preMobileOfficeInfo.directorName.equals(l_changedMobileOfficeInfo.directorName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者名（カナ）名
        if (l_preMobileOfficeInfo.directorNameKana != null)
        {
            if (!(l_preMobileOfficeInfo.directorNameKana.equals(l_changedMobileOfficeInfo.directorNameKana)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorNameKana != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //その他連絡先(携帯、自宅等)
        if (l_preMobileOfficeInfo.directorOtherContact != null)
        {
            if (!(l_preMobileOfficeInfo.directorOtherContact.equals(l_changedMobileOfficeInfo.directorOtherContact)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorOtherContact != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者役職
        if (l_preMobileOfficeInfo.directorPosition != null)
        {
            if (!(l_preMobileOfficeInfo.directorPosition.equals(l_changedMobileOfficeInfo.directorPosition)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorPosition != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引責任者郵便番号
        if (l_preMobileOfficeInfo.directorZipCode != null)
        {
            if (!(l_preMobileOfficeInfo.directorZipCode.equals(l_changedMobileOfficeInfo.directorZipCode)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorZipCode != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（１）
        if (l_preMobileOfficeInfo.experienceDiv1 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv1.equals(l_changedMobileOfficeInfo.experienceDiv1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（２）
        if (l_preMobileOfficeInfo.experienceDiv2 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv2.equals(l_changedMobileOfficeInfo.experienceDiv2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（３）
        if (l_preMobileOfficeInfo.experienceDiv3 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv3.equals(l_changedMobileOfficeInfo.experienceDiv3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（４）
        if (l_preMobileOfficeInfo.experienceDiv4 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv4.equals(l_changedMobileOfficeInfo.experienceDiv4)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv4 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（５）
        if (l_preMobileOfficeInfo.experienceDiv5 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv5.equals(l_changedMobileOfficeInfo.experienceDiv5)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv5 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（６）
        if (l_preMobileOfficeInfo.experienceDiv6 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv6.equals(l_changedMobileOfficeInfo.experienceDiv6)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv6 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（７）
        if (l_preMobileOfficeInfo.experienceDiv7 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv7.equals(l_changedMobileOfficeInfo.experienceDiv7)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv7 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（８）
        if (l_preMobileOfficeInfo.experienceDiv8 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv8.equals(l_changedMobileOfficeInfo.experienceDiv8)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv8 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（９）
        if (l_preMobileOfficeInfo.experienceDiv9 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv9.equals(l_changedMobileOfficeInfo.experienceDiv9)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv9 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験（１０）
        if (l_preMobileOfficeInfo.experienceDiv10 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv10.equals(l_changedMobileOfficeInfo.experienceDiv10)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv10 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（１）
        if (l_preMobileOfficeInfo.experienceFlag1 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag1.equals(l_changedMobileOfficeInfo.experienceFlag1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（２）
        if (l_preMobileOfficeInfo.experienceFlag2 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag2.equals(l_changedMobileOfficeInfo.experienceFlag2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（３）
        if (l_preMobileOfficeInfo.experienceFlag3 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag3.equals(l_changedMobileOfficeInfo.experienceFlag3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（４）
        if (l_preMobileOfficeInfo.experienceFlag4 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag4.equals(l_changedMobileOfficeInfo.experienceFlag4)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag4 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（５）
        if (l_preMobileOfficeInfo.experienceFlag5 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag5.equals(l_changedMobileOfficeInfo.experienceFlag5)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag5 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（６）
        if (l_preMobileOfficeInfo.experienceFlag6 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag6.equals(l_changedMobileOfficeInfo.experienceFlag6)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag6 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（７）
        if (l_preMobileOfficeInfo.experienceFlag7 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag7.equals(l_changedMobileOfficeInfo.experienceFlag7)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag7 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（８）
        if (l_preMobileOfficeInfo.experienceFlag8 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag8.equals(l_changedMobileOfficeInfo.experienceFlag8)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag8 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（９）
        if (l_preMobileOfficeInfo.experienceFlag9 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag9.equals(l_changedMobileOfficeInfo.experienceFlag9)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag9 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資経験の有無（１０）
        if (l_preMobileOfficeInfo.experienceFlag10 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag10.equals(l_changedMobileOfficeInfo.experienceFlag10)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag10 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //FAX番号
        if (l_preMobileOfficeInfo.faxTelephone != null)
        {
            if (!(l_preMobileOfficeInfo.faxTelephone.equals(l_changedMobileOfficeInfo.faxTelephone)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.faxTelephone != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //運用予定額
        if (l_preMobileOfficeInfo.fundBundgetAmountDiv != null)
        {
            if (!(l_preMobileOfficeInfo.fundBundgetAmountDiv.equals(l_changedMobileOfficeInfo.fundBundgetAmountDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.fundBundgetAmountDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（１）
        if (l_preMobileOfficeInfo.interest1 != null)
        {
            if (!(l_preMobileOfficeInfo.interest1.equals(l_changedMobileOfficeInfo.interest1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（２）
        if (l_preMobileOfficeInfo.interest2 != null)
        {
            if (!(l_preMobileOfficeInfo.interest2.equals(l_changedMobileOfficeInfo.interest2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（３）
        if (l_preMobileOfficeInfo.interest3 != null)
        {
            if (!(l_preMobileOfficeInfo.interest3.equals(l_changedMobileOfficeInfo.interest3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（４）
        if (l_preMobileOfficeInfo.interest4 != null)
        {
            if (!(l_preMobileOfficeInfo.interest4.equals(l_changedMobileOfficeInfo.interest4)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest4 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（５）
        if (l_preMobileOfficeInfo.interest5 != null)
        {
            if (!(l_preMobileOfficeInfo.interest5.equals(l_changedMobileOfficeInfo.interest5)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest5 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（６）
        if (l_preMobileOfficeInfo.interest6 != null)
        {
            if (!(l_preMobileOfficeInfo.interest6.equals(l_changedMobileOfficeInfo.interest6)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest6 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（７）
        if (l_preMobileOfficeInfo.interest7 != null)
        {
            if (!(l_preMobileOfficeInfo.interest7.equals(l_changedMobileOfficeInfo.interest7)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest7 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（８）
        if (l_preMobileOfficeInfo.interest8 != null)
        {
            if (!(l_preMobileOfficeInfo.interest8.equals(l_changedMobileOfficeInfo.interest8)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest8 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（９）
        if (l_preMobileOfficeInfo.interest9 != null)
        {
            if (!(l_preMobileOfficeInfo.interest9.equals(l_changedMobileOfficeInfo.interest9)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest9 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //取引の種類（１０）
        if (l_preMobileOfficeInfo.interest10 != null)
        {
            if (!(l_preMobileOfficeInfo.interest10.equals(l_changedMobileOfficeInfo.interest10)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest10 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //インターネット取引区分
        if (l_preMobileOfficeInfo.internetTradeDiv != null)
        {
            if (!(l_preMobileOfficeInfo.internetTradeDiv.equals(l_changedMobileOfficeInfo.internetTradeDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.internetTradeDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //紹介支店
        if (l_preMobileOfficeInfo.introduceBranch != null)
        {
            if (!(l_preMobileOfficeInfo.introduceBranch.equals(l_changedMobileOfficeInfo.introduceBranch)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.introduceBranch != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資予定期間
        if (l_preMobileOfficeInfo.investPlanPeriodDiv != null)
        {
            if (!(l_preMobileOfficeInfo.investPlanPeriodDiv.equals(l_changedMobileOfficeInfo.investPlanPeriodDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.investPlanPeriodDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //投資目的
        if (l_preMobileOfficeInfo.investPurposeDiv != null)
        {
            if (!(l_preMobileOfficeInfo.investPurposeDiv.equals(l_changedMobileOfficeInfo.investPurposeDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.investPurposeDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //携帯番号
        if (l_preMobileOfficeInfo.mobileTelephone != null)
        {
            if (!(l_preMobileOfficeInfo.mobileTelephone.equals(l_changedMobileOfficeInfo.mobileTelephone)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.mobileTelephone != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //国籍
        if (l_preMobileOfficeInfo.nationality != null)
        {
            if (!(l_preMobileOfficeInfo.nationality.equals(l_changedMobileOfficeInfo.nationality)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.nationality != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //国籍その他
        if (l_preMobileOfficeInfo.nationalityOther != null)
        {
            if (!(l_preMobileOfficeInfo.nationalityOther.equals(l_changedMobileOfficeInfo.nationalityOther)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.nationalityOther != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //職業
        if (l_preMobileOfficeInfo.occupationDiv != null)
        {
            if (!(l_preMobileOfficeInfo.occupationDiv.equals(l_changedMobileOfficeInfo.occupationDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.occupationDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //勤務先住所
        if (l_preMobileOfficeInfo.officeAdress != null)
        {
            if (!(l_preMobileOfficeInfo.officeAdress.equals(l_changedMobileOfficeInfo.officeAdress)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.officeAdress != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //勤務先名称
        if (l_preMobileOfficeInfo.officeName != null)
        {
            if (!(l_preMobileOfficeInfo.officeName.equals(l_changedMobileOfficeInfo.officeName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.officeName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //勤務先電話番号
        if (l_preMobileOfficeInfo.officeTelephone != null)
        {
            if (!(l_preMobileOfficeInfo.officeTelephone.equals(l_changedMobileOfficeInfo.officeTelephone)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.officeTelephone != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //勤務先郵便番号
        if (l_preMobileOfficeInfo.officeZipCode != null)
        {
            if (!(l_preMobileOfficeInfo.officeZipCode.equals(l_changedMobileOfficeInfo.officeZipCode)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.officeZipCode != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //役職名
        if (l_preMobileOfficeInfo.position != null)
        {
            if (!(l_preMobileOfficeInfo.position.equals(l_changedMobileOfficeInfo.position)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.position != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //代表者名（漢字）姓
        if (l_preMobileOfficeInfo.representFamilyName != null)
        {
            if (!(l_preMobileOfficeInfo.representFamilyName.equals(l_changedMobileOfficeInfo.representFamilyName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representFamilyName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //代表者名（カナ）姓
        if (l_preMobileOfficeInfo.representFamilyNameKana != null)
        {
            if (!(l_preMobileOfficeInfo.representFamilyNameKana.equals(l_changedMobileOfficeInfo.representFamilyNameKana)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representFamilyNameKana != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //代表者名（漢字）名
        if (l_preMobileOfficeInfo.representName != null)
        {
            if (!(l_preMobileOfficeInfo.representName.equals(l_changedMobileOfficeInfo.representName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //代表者名（カナ）名
        if (l_preMobileOfficeInfo.representNameKana != null)
        {
            if (!(l_preMobileOfficeInfo.representNameKana.equals(l_changedMobileOfficeInfo.representNameKana)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representNameKana != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //代表者権
        if (l_preMobileOfficeInfo.representPower != null)
        {
            if (!(l_preMobileOfficeInfo.representPower.equals(l_changedMobileOfficeInfo.representPower)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representPower != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //現在利用している証券会社
        if (l_preMobileOfficeInfo.useInstitutionDiv != null)
        {
            if (!(l_preMobileOfficeInfo.useInstitutionDiv.equals(l_changedMobileOfficeInfo.useInstitutionDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.useInstitutionDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //引数）変更後情報の全ての項目内容が（引数）変更前情報の全ての項目内容と完全に一致する場合、変更項目無として
        //FALSEを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get変更前申込情報)<BR>
     * 顧客オブジェクトより、変更前の携帯番号・勤務先情報変更申込顧客オブジェクトを生成し、返却する。 <BR>
     * <BR>
     * １）　@this.get変更申込顧客（）で、携帯番号・勤務先情報変更申込顧客オブジェクトを取得。 <BR>
     * <BR>
     * <BR>
     * <BR>
     * ２）　@顧客.getDataSourceObject()にて顧客行オブジェクトを取得する。  <BR>
     * <BR>
     * ３）　@口座情報マスタオブジェクトを取得する。   <BR>
     * 　@　@　@口座情報マスタ.get口座情報マスタ()にて、口座情報マスタオブジェクトを取得する。  <BR>
     * <BR>
     * ４）　@携帯番号・勤務先情報オブジェクトを生成する。  <BR>
     * <BR>
     * ５）　@以下の通り、プロパティをセットする。  <BR>
     * 　@携帯番号・勤務先情報.携帯番号 = 顧客行.連絡先電話番号（携帯）  <BR>
     * 　@携帯番号・勤務先情報.勤務先名称 = 顧客行.勤務先名称  <BR>
     * 　@携帯番号・勤務先情報.勤務先郵便番号 = 顧客行.勤務先郵便番号  <BR>
     * 　@携帯番号・勤務先情報.勤務先住所 = 顧客行.勤務先住所  <BR>
     * 　@携帯番号・勤務先情報.勤務先電話番号 = 顧客行.勤務先電話番号  <BR>
     * 　@携帯番号・勤務先情報.役職名 = 顧客行.役職  <BR>
     * <BR>
     * 　@*get口座情報マスタ（） != null　@の場合、以下にセット <BR>
     * 　@携帯番号・勤務先情報.連絡先優先順位1位 = 口座情報マスタ.連絡先優先順位1位  <BR>
     * 　@携帯番号・勤務先情報.連絡先優先順位2位 = 口座情報マスタ.連絡先優先順位2位  <BR>
     * 　@携帯番号・勤務先情報.連絡先優先順位3位 = 口座情報マスタ.連絡先優先順位3位　@  <BR>
     * 　@携帯番号・勤務先情報.所属 = 口座情報マスタ.所属  <BR>
     * 　@携帯番号・勤務先情報.正式名称１ = 口座情報マスタ.顧客正式名称１   <BR>
     * 　@携帯番号・勤務先情報.正式名称２ = 口座情報マスタ.顧客正式名称２   <BR>
     * 　@携帯番号・勤務先情報.職業 = 口座情報マスタ.職業区分   <BR>
     * 　@携帯番号・勤務先情報.国籍 = 口座情報マスタ.国籍   <BR>
     * 　@携帯番号・勤務先情報.国籍その他 = 口座情報マスタ.国籍その他  <BR>
     * 　@携帯番号・勤務先情報.代表者名（漢字）姓 = 口座情報マスタ.代表者名（漢字）姓   <BR>
     * 　@携帯番号・勤務先情報.代表者名（漢字）名 = 口座情報マスタ.代表者名（漢字）名   <BR>
     * 　@携帯番号・勤務先情報.代表者名（カナ）姓 = 口座情報マスタ.代表者名（カナ）姓   <BR>
     * 　@携帯番号・勤務先情報.代表者名（カナ）名 = 口座情報マスタ.代表者名（カナ）名   <BR>
     * 　@携帯番号・勤務先情報.代表者権 = 口座情報マスタ.代表者権   <BR>
     * 　@携帯番号・勤務先情報.取引責任者名（漢字）姓 = 口座情報マスタ.取引先責任者名（漢字）姓   <BR>
     * 　@携帯番号・勤務先情報.取引責任者名（漢字）名 = 口座情報マスタ.取引先責任者名（漢字）名   <BR>
     * 　@携帯番号・勤務先情報.取引責任者名（カナ）姓 = 口座情報マスタ.取引先責任者名（カナ）姓   <BR>
     * 　@携帯番号・勤務先情報.取引責任者名（カナ）名 = 口座情報マスタ.取引先責任者名（カナ）名   <BR>
     * 　@携帯番号・勤務先情報.取引責任者　@所属部署 = 口座情報マスタ.取引責任者所属部署   <BR>
     * 　@携帯番号・勤務先情報.取引責任者　@役職 = 口座情報マスタ.取引責任者役職   <BR>
     * 　@携帯番号・勤務先情報.取引責任者郵便番号 = 口座情報マスタ.取引先責任者郵便番号   <BR>
     * 　@携帯番号・勤務先情報.取引責任者住所１ = 口座情報マスタ.取引先責任者住所１   <BR>
     * 　@携帯番号・勤務先情報.取引責任者住所２ = 口座情報マスタ.取引先責任者住所２   <BR>
     * 　@携帯番号・勤務先情報.取引責任者住所３ = 口座情報マスタ.取引先責任者住所３   <BR>
     * 　@携帯番号・勤務先情報.取引責任者生年月日　@年号 = 口座情報マスタ.取引先責任者生年月日年号   <BR>
     * 　@携帯番号・勤務先情報.取引責任者生年月日 = 口座情報マスタ.取引先責任者生年月日   <BR>
     * 　@携帯番号・勤務先情報.取引責任者会社直通番号 = 口座情報マスタ.取引先責任者会社直通番号   <BR>
     * 　@携帯番号・勤務先情報.その他連絡先（携帯、自宅等） = 口座情報マスタ.その他の連絡先（携帯、自宅等）   <BR>
     * 　@携帯番号・勤務先情報.FAX番号 = 口座情報マスタ.FAX番号  <BR>
     * 　@携帯番号・勤務先情報.年収 = 口座情報マスタ.年収  <BR>
     * 　@携帯番号・勤務先情報.金融資産額 = 口座情報マスタ.金融資産額  <BR>
     * 　@携帯番号・勤務先情報.運用予定額 = 口座情報マスタ.運用予定額  <BR>
     * 　@携帯番号・勤務先情報.投資目的 = 口座情報マスタ.投資目的  <BR>
     * 　@携帯番号・勤務先情報.投資予定期間 = 口座情報マスタ.投資予定期間  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（１） = 口座情報マスタ.投資経験の有無（１）  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（２） = 口座情報マスタ.投資経験の有無（２）  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（３） = 口座情報マスタ.投資経験の有無（３）  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（４） = 口座情報マスタ.投資経験の有無（４）  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（５） = 口座情報マスタ.投資経験の有無（５）  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（６） = 口座情報マスタ.投資経験の有無（６）  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（７） = 口座情報マスタ.投資経験の有無（７）  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（８） = 口座情報マスタ.投資経験の有無（８）  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（９） = 口座情報マスタ.投資経験の有無（９）  <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（１０） = 口座情報マスタ.投資経験の有無（１０）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（１） = 口座情報マスタ.投資経験（１）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（２） = 口座情報マスタ.投資経験（２）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（３） = 口座情報マスタ.投資経験（３）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（４） = 口座情報マスタ.投資経験（４）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（５） = 口座情報マスタ.投資経験（５）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（６） = 口座情報マスタ.投資経験（６）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（７） = 口座情報マスタ.投資経験（７）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（８） = 口座情報マスタ.投資経験（８）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（９） = 口座情報マスタ.投資経験（９）  <BR>
     * 　@携帯番号・勤務先情報.投資経験（１０） = 口座情報マスタ.投資経験（１０）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（１） = 口座情報マスタ.取引の種類（１）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（２） = 口座情報マスタ.取引の種類（２）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（３） = 口座情報マスタ.取引の種類（３）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（４） = 口座情報マスタ.取引の種類（４）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（５） = 口座情報マスタ.取引の種類（５）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（６） = 口座情報マスタ.取引の種類（６）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（７） = 口座情報マスタ.取引の種類（７）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（８） = 口座情報マスタ.取引の種類（８）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（９） = 口座情報マスタ.取引の種類（９）  <BR>
     * 　@携帯番号・勤務先情報.取引の種類（１０） = 口座情報マスタ.取引の種類（１０）  <BR>
     * 　@携帯番号・勤務先情報.口座開設の動機@ = 口座情報マスタ.口座開設の動機@  <BR>
     * 　@携帯番号・勤務先情報.口座開設の動機@の詳細 = 口座情報マスタ.口座開設の動機@の詳細  <BR>
     * 　@携帯番号・勤務先情報.現在利用している証券会社 = 口座情報マスタ.現在利用している証券会社  <BR>
     * 　@携帯番号・勤務先情報.インターネット取引区分 = 口座情報マスタ.インターネット取引区分  <BR>
     * 　@携帯番号・勤務先情報.紹介支店 = 口座情報マスタ.紹介支店  <BR>
     * <BR>
     * 　@*get口座情報マスタ（） == null　@の場合、上記項目にnullをセット <BR>
     * <BR>
     * ６）　@１）で取得したオブジェクト.勤務先情報 = 生成した携帯番号・勤務先情報オブジェクト <BR>
     * <BR>
     * ７）　@携帯番号・勤務先情報変更申込顧客オブジェクトを返却 <BR>
     * @@param l_mainAccount - (顧客オブジェクト)<BR>
     * 顧客オブジェクト
     * @@return WEB3AccInfoMobileOfficeChangeAccount
     * @@throws WEB3BaseException
     */
    public WEB3AccInfoMobileOfficeChangeAccount getBeforeChangeInfo(
        WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBeforeChangeInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }

        //１）　@this.get変更申込顧客（）で、携帯番号・勤務先情報変更申込顧客オブジェクトを取得。
        WEB3AccInfoMobileOfficeChangeAccount l_changeAccount = this.getChangeRegistAccount();

        //２）　@顧客.getDataSourceObject()にて顧客行オブジェクトを取得する。
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //３）　@口座情報マスタオブジェクトを取得する。
        //口座情報マスタ.get口座情報マスタ()にて、口座情報マスタオブジェクトを取得する。
        WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);

        //４）　@携帯番号・勤務先情報オブジェクトを生成する。
        WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();

        //５）　@以下の通り、プロパティをセットする。
        //携帯番号・勤務先情報.携帯番号 = 顧客行.連絡先電話番号（携帯）
        l_accInfoMobileOfficeInfo.mobileTelephone = l_mainAccountRow.getMobile();

        //携帯番号・勤務先情報.勤務先名称 = 顧客行.勤務先名称
        l_accInfoMobileOfficeInfo.officeName = l_mainAccountRow.getOffice();

        //携帯番号・勤務先情報.勤務先郵便番号 = 顧客行.勤務先郵便番号
        l_accInfoMobileOfficeInfo.officeZipCode = l_mainAccountRow.getOfficeZipCode();

        //携帯番号・勤務先情報.勤務先住所 = 顧客行.勤務先住所
        l_accInfoMobileOfficeInfo.officeAdress = l_mainAccountRow.getOfficeAddress();

        //携帯番号・勤務先情報.勤務先電話番号 = 顧客行.勤務先電話番号
        l_accInfoMobileOfficeInfo.officeTelephone = l_mainAccountRow.getOfficeTelephone();

        //携帯番号・勤務先情報.役職名 = 顧客行.役職
        l_accInfoMobileOfficeInfo.position = l_mainAccountRow.getPost();

        //*get口座情報マスタ（） != null　@の場合、以下にセット
        if (l_accInfoMaster != null)
        {
            AccountInfoMstRow l_accountInfoMstRow =
                (AccountInfoMstRow)l_accInfoMaster.getDataSourceObject();
            //携帯番号・勤務先情報.連絡先優先順位1位 = 口座情報マスタ.連絡先優先順位1位
            l_accInfoMobileOfficeInfo.contactPriority1 = l_accountInfoMstRow.getContactPriority1();

            //携帯番号・勤務先情報.連絡先優先順位2位 = 口座情報マスタ.連絡先優先順位2位
            l_accInfoMobileOfficeInfo.contactPriority2 = l_accountInfoMstRow.getContactPriority2();

            //携帯番号・勤務先情報.連絡先優先順位3位 = 口座情報マスタ.連絡先優先順位3位
            l_accInfoMobileOfficeInfo.contactPriority3 = l_accountInfoMstRow.getContactPriority3();

            //携帯番号・勤務先情報.所属 = 口座情報マスタ.所属
            l_accInfoMobileOfficeInfo.department = l_accountInfoMstRow.getDepartment();

            //携帯番号・勤務先情報.正式名称１ = 口座情報マスタ.顧客正式名称１
            l_accInfoMobileOfficeInfo.accountRealName1 = l_accountInfoMstRow.getRealName1();

            //携帯番号・勤務先情報.正式名称２ = 口座情報マスタ.顧客正式名称２
            l_accInfoMobileOfficeInfo.accountRealName2 = l_accountInfoMstRow.getRealName2();

            //携帯番号・勤務先情報.職業 = 口座情報マスタ.職業区分
            l_accInfoMobileOfficeInfo.occupationDiv = l_accountInfoMstRow.getOccupationDiv();

            //携帯番号・勤務先情報.国籍 = 口座情報マスタ.国籍
            l_accInfoMobileOfficeInfo.nationality = l_accountInfoMstRow.getNationality();

            //携帯番号・勤務先情報.国籍その他 = 口座情報マスタ.国籍その他
            l_accInfoMobileOfficeInfo.nationalityOther = l_accountInfoMstRow.getNationalityOther();

            //携帯番号・勤務先情報.代表者名（漢字）姓 = 口座情報マスタ.代表者名（漢字）姓
            l_accInfoMobileOfficeInfo.representFamilyName = l_accountInfoMstRow.getRepresentFamilyName();

            //携帯番号・勤務先情報.代表者名（漢字）名 = 口座情報マスタ.代表者名（漢字）名
            l_accInfoMobileOfficeInfo.representName = l_accountInfoMstRow.getRepresentGivenName();

            //携帯番号・勤務先情報.代表者名（カナ）姓 = 口座情報マスタ.代表者名（カナ）姓
            l_accInfoMobileOfficeInfo.representFamilyNameKana =
                l_accountInfoMstRow.getRepresentFamilyNameAlt1();

            //携帯番号・勤務先情報.代表者名（カナ）名 = 口座情報マスタ.代表者名（カナ）名
            l_accInfoMobileOfficeInfo.representNameKana =
                l_accountInfoMstRow.getRepresentGivenNameAlt1();

            //携帯番号・勤務先情報.代表者権 = 口座情報マスタ.代表者権
            l_accInfoMobileOfficeInfo.representPower = l_accountInfoMstRow.getRepresentPower();

            //携帯番号・勤務先情報.取引責任者名（漢字）姓 = 口座情報マスタ.取引先責任者名（漢字）姓
            l_accInfoMobileOfficeInfo.directorFamilyName = l_accountInfoMstRow.getDirectorFamilyName();

            //携帯番号・勤務先情報.取引責任者名（漢字）名 = 口座情報マスタ.取引先責任者名（漢字）名
            l_accInfoMobileOfficeInfo.directorName = l_accountInfoMstRow.getDirectorGivenName();

            //携帯番号・勤務先情報.取引責任者名（カナ）姓 = 口座情報マスタ.取引先責任者名（カナ）姓
            l_accInfoMobileOfficeInfo.directorFamilyNameKana =
                l_accountInfoMstRow.getDirectorFamilyNameAlt1();

            //携帯番号・勤務先情報.取引責任者名（カナ）名 = 口座情報マスタ.取引先責任者名（カナ）名
            l_accInfoMobileOfficeInfo.directorNameKana = l_accountInfoMstRow.getDirectorGivenNameAlt1();

            //携帯番号・勤務先情報.取引責任者　@所属部署 = 口座情報マスタ.取引責任者所属部署
            l_accInfoMobileOfficeInfo.directorDepartment = l_accountInfoMstRow.getDirectorDepartment();

            //携帯番号・勤務先情報.取引責任者　@役職 = 口座情報マスタ.取引責任者役職
            l_accInfoMobileOfficeInfo.directorPosition = l_accountInfoMstRow.getDirectorPost();

            //携帯番号・勤務先情報.取引責任者郵便番号 = 口座情報マスタ.取引先責任者郵便番号
            l_accInfoMobileOfficeInfo.directorZipCode = l_accountInfoMstRow.getDirectorZipCode();

            //携帯番号・勤務先情報.取引責任者住所１ = 口座情報マスタ.取引先責任者住所１
            l_accInfoMobileOfficeInfo.directorAddress1 = l_accountInfoMstRow.getDirectorAddress1();

            //携帯番号・勤務先情報.取引責任者住所２ = 口座情報マスタ.取引先責任者住所２
            l_accInfoMobileOfficeInfo.directorAddress2 = l_accountInfoMstRow.getDirectorAddress2();

            //携帯番号・勤務先情報.取引責任者住所３ = 口座情報マスタ.取引先責任者住所３
            l_accInfoMobileOfficeInfo.directorAddress3 = l_accountInfoMstRow.getDirectorAddress3();

            //携帯番号・勤務先情報.取引責任者生年月日　@年号 = 口座情報マスタ.取引先責任者生年月日年号
            l_accInfoMobileOfficeInfo.directorEraBorn = l_accountInfoMstRow.getDirectorEraBorn();

            //携帯番号・勤務先情報.取引責任者生年月日 = 口座情報マスタ.取引先責任者生年月日
            l_accInfoMobileOfficeInfo.directorBornDate = l_accountInfoMstRow.getDirectorBornDate();

            //携帯番号・勤務先情報.取引責任者会社直通番号 = 口座情報マスタ.取引先責任者会社直通番号
            l_accInfoMobileOfficeInfo.directorCorpDirect = l_accountInfoMstRow.getDirectorCorpTelephone();

            //携帯番号・勤務先情報.その他連絡先（携帯、自宅等） = 口座情報マスタ.その他の連絡先（携帯、自宅等）
            l_accInfoMobileOfficeInfo.directorOtherContact = l_accountInfoMstRow.getOtherContact();

            //携帯番号・勤務先情報.FAX番号 = 口座情報マスタ.FAX番号
            l_accInfoMobileOfficeInfo.faxTelephone = l_accountInfoMstRow.getFax();

            //携帯番号・勤務先情報.年収 = 口座情報マスタ.年収
            l_accInfoMobileOfficeInfo.annualIncomeDiv = l_accountInfoMstRow.getAnnualIncomeDiv();

            //携帯番号・勤務先情報.金融資産額 = 口座情報マスタ.金融資産額
            l_accInfoMobileOfficeInfo.assetValueDiv = l_accountInfoMstRow.getAssetValueDiv();

            //携帯番号・勤務先情報.運用予定額 = 口座情報マスタ.運用予定額
            l_accInfoMobileOfficeInfo.fundBundgetAmountDiv = l_accountInfoMstRow.getFundBudgetAmountDiv();

            //携帯番号・勤務先情報.投資目的 = 口座情報マスタ.投資目的
            l_accInfoMobileOfficeInfo.investPurposeDiv = l_accountInfoMstRow.getInvestPurposeDiv();

            //携帯番号・勤務先情報.投資予定期間 = 口座情報マスタ.投資予定期間
            l_accInfoMobileOfficeInfo.investPlanPeriodDiv = l_accountInfoMstRow.getInvestPlanPeriodDiv();

            //携帯番号・勤務先情報.投資経験の有無（１） = 口座情報マスタ.投資経験の有無（１）
            if (!l_accountInfoMstRow.getExperienceFlag1IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag1 = l_accountInfoMstRow.getExperienceFlag1() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（２） = 口座情報マスタ.投資経験の有無（２）
            if (!l_accountInfoMstRow.getExperienceFlag2IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag2 = l_accountInfoMstRow.getExperienceFlag2() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（３） = 口座情報マスタ.投資経験の有無（３）
            if (!l_accountInfoMstRow.getExperienceFlag3IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag3 = l_accountInfoMstRow.getExperienceFlag3() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（４） = 口座情報マスタ.投資経験の有無（４）
            if (!l_accountInfoMstRow.getExperienceFlag4IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag4 = l_accountInfoMstRow.getExperienceFlag4() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（５） = 口座情報マスタ.投資経験の有無（５）
            if (!l_accountInfoMstRow.getExperienceFlag5IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag5 = l_accountInfoMstRow.getExperienceFlag5() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（６） = 口座情報マスタ.投資経験の有無（６）
            if (!l_accountInfoMstRow.getExperienceFlag6IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag6 = l_accountInfoMstRow.getExperienceFlag6() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（７） = 口座情報マスタ.投資経験の有無（７）
            if (!l_accountInfoMstRow.getExperienceFlag7IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag7 = l_accountInfoMstRow.getExperienceFlag7() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（８） = 口座情報マスタ.投資経験の有無（８）
            if (!l_accountInfoMstRow.getExperienceFlag8IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag8 = l_accountInfoMstRow.getExperienceFlag8() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（９） = 口座情報マスタ.投資経験の有無（９）
            if (!l_accountInfoMstRow.getExperienceFlag9IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag9 = l_accountInfoMstRow.getExperienceFlag9() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（１０） = 口座情報マスタ.投資経験の有無（１０）
            if (!l_accountInfoMstRow.getExperienceFlag10IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag10 = l_accountInfoMstRow.getExperienceFlag10() + "";
            }

            //携帯番号・勤務先情報.投資経験（１） = 口座情報マスタ.投資経験（１）
            l_accInfoMobileOfficeInfo.experienceDiv1 = l_accountInfoMstRow.getExperienceDiv1();

            //携帯番号・勤務先情報.投資経験（２） = 口座情報マスタ.投資経験（２）
            l_accInfoMobileOfficeInfo.experienceDiv2 = l_accountInfoMstRow.getExperienceDiv2();

            //携帯番号・勤務先情報.投資経験（３） = 口座情報マスタ.投資経験（３）
            l_accInfoMobileOfficeInfo.experienceDiv3 = l_accountInfoMstRow.getExperienceDiv3();

            //携帯番号・勤務先情報.投資経験（４） = 口座情報マスタ.投資経験（４）
            l_accInfoMobileOfficeInfo.experienceDiv4 = l_accountInfoMstRow.getExperienceDiv4();

            //携帯番号・勤務先情報.投資経験（５） = 口座情報マスタ.投資経験（５）
            l_accInfoMobileOfficeInfo.experienceDiv5 = l_accountInfoMstRow.getExperienceDiv5();

            //携帯番号・勤務先情報.投資経験（６） = 口座情報マスタ.投資経験（６）
            l_accInfoMobileOfficeInfo.experienceDiv6 = l_accountInfoMstRow.getExperienceDiv6();

            //携帯番号・勤務先情報.投資経験（７） = 口座情報マスタ.投資経験（７）
            l_accInfoMobileOfficeInfo.experienceDiv7 = l_accountInfoMstRow.getExperienceDiv7();

            //携帯番号・勤務先情報.投資経験（８） = 口座情報マスタ.投資経験（８）
            l_accInfoMobileOfficeInfo.experienceDiv8 = l_accountInfoMstRow.getExperienceDiv8();

            //携帯番号・勤務先情報.投資経験（９） = 口座情報マスタ.投資経験（９）
            l_accInfoMobileOfficeInfo.experienceDiv9 = l_accountInfoMstRow.getExperienceDiv9();

            //携帯番号・勤務先情報.投資経験（１０） = 口座情報マスタ.投資経験（１０）
            l_accInfoMobileOfficeInfo.experienceDiv10 = l_accountInfoMstRow.getExperienceDiv10();

            //携帯番号・勤務先情報.取引の種類（１） = 口座情報マスタ.取引の種類（１）
            if (!l_accountInfoMstRow.getInterestFlag1IsNull())
            {
                l_accInfoMobileOfficeInfo.interest1 = l_accountInfoMstRow.getInterestFlag1() + "";
            }

            //携帯番号・勤務先情報.取引の種類（２） = 口座情報マスタ.取引の種類（２）
            if (!l_accountInfoMstRow.getInterestFlag2IsNull())
            {
                l_accInfoMobileOfficeInfo.interest2 = l_accountInfoMstRow.getInterestFlag2() + "";
            }

            //携帯番号・勤務先情報.取引の種類（３） = 口座情報マスタ.取引の種類（３）
            if (!l_accountInfoMstRow.getInterestFlag3IsNull())
            {
                l_accInfoMobileOfficeInfo.interest3 = l_accountInfoMstRow.getInterestFlag3() + "";
            }

            //携帯番号・勤務先情報.取引の種類（４） = 口座情報マスタ.取引の種類（４）
            if (!l_accountInfoMstRow.getInterestFlag4IsNull())
            {
                l_accInfoMobileOfficeInfo.interest4 = l_accountInfoMstRow.getInterestFlag4() + "";
            }

            //携帯番号・勤務先情報.取引の種類（５） = 口座情報マスタ.取引の種類（５）
            if (!l_accountInfoMstRow.getInterestFlag5IsNull())
            {
                l_accInfoMobileOfficeInfo.interest5 = l_accountInfoMstRow.getInterestFlag5() + "";
            }

            //携帯番号・勤務先情報.取引の種類（６） = 口座情報マスタ.取引の種類（６）
            if (!l_accountInfoMstRow.getInterestFlag6IsNull())
            {
                l_accInfoMobileOfficeInfo.interest6 = l_accountInfoMstRow.getInterestFlag6() + "";
            }

            //携帯番号・勤務先情報.取引の種類（７） = 口座情報マスタ.取引の種類（７）
            if (!l_accountInfoMstRow.getInterestFlag7IsNull())
            {
                l_accInfoMobileOfficeInfo.interest7 = l_accountInfoMstRow.getInterestFlag7() + "";
            }

            //携帯番号・勤務先情報.取引の種類（８） = 口座情報マスタ.取引の種類（８）
            if (!l_accountInfoMstRow.getInterestFlag8IsNull())
            {
                l_accInfoMobileOfficeInfo.interest8 = l_accountInfoMstRow.getInterestFlag8() + "";
            }

            //携帯番号・勤務先情報.取引の種類（９） = 口座情報マスタ.取引の種類（９）
            if (!l_accountInfoMstRow.getInterestFlag9IsNull())
            {
                l_accInfoMobileOfficeInfo.interest9 = l_accountInfoMstRow.getInterestFlag9() + "";
            }

            //携帯番号・勤務先情報.取引の種類（１０） = 口座情報マスタ.取引の種類（１０）
            if (!l_accountInfoMstRow.getInterestFlag10IsNull())
            {
                l_accInfoMobileOfficeInfo.interest10 = l_accountInfoMstRow.getInterestFlag10() + "";
            }

            //携帯番号・勤務先情報.口座開設の動機@ = 口座情報マスタ.口座開設の動機@
            l_accInfoMobileOfficeInfo.appliMotivatDiv = l_accountInfoMstRow.getAppliMotivatDiv();

            //携帯番号・勤務先情報.口座開設の動機@の詳細 = 口座情報マスタ.口座開設の動機@の詳細
            l_accInfoMobileOfficeInfo.appliMotivatDetail = l_accountInfoMstRow.getAppliMotivatDivDetail();

            //携帯番号・勤務先情報.現在利用している証券会社 = 口座情報マスタ.現在利用している証券会社
            l_accInfoMobileOfficeInfo.useInstitutionDiv = l_accountInfoMstRow.getUseInstitutionDiv();

            //携帯番号・勤務先情報.インターネット取引区分 = 口座情報マスタ.インターネット取引区分
            l_accInfoMobileOfficeInfo.internetTradeDiv = l_accountInfoMstRow.getInternetTradeDiv();

            //携帯番号・勤務先情報.紹介支店 = 口座情報マスタ.紹介支店
            l_accInfoMobileOfficeInfo.introduceBranch = l_accountInfoMstRow.getIntroduceBranchCode();
        }

        //６）　@１）で取得したオブジェクト.勤務先情報 = 生成した携帯番号・勤務先情報オブジェクト
        l_changeAccount.mobileOfficeInfo = l_accInfoMobileOfficeInfo;

        //７）　@携帯番号・勤務先情報変更申込顧客オブジェクトを返却
        log.exiting(STR_METHOD_NAME);
        return l_changeAccount;
    }

    /**
     * (get変更後申込情報)<BR>
     * 携帯番号・勤務先情報変更申込オブジェクトより、変更後の携帯番号・勤務先情報変更申込<BR>
     * 顧客オブジェクトを生成し、返却する。 <BR>
     * <BR>
     * <BR>
     * １）　@this.get変更申込顧客（）で、携帯番号・勤務先情報変更申込顧客オブジェクトを取得。 <BR>
     * <BR>
     * ２）　@携帯番号・勤務先情報オブジェクトを生成する。  <BR>
     * <BR>
     * ３）　@以下の通り、プロパティをセットする。 <BR>
     * 　@携帯番号・勤務先情報.携帯番号 = this.携帯番号・勤務先情報変更申込行.連絡先電話番号（携帯） <BR>
     * 　@携帯番号・勤務先情報.勤務先名称 = this.携帯番号・勤務先情報変更申込行.勤務先名称 <BR>
     * 　@携帯番号・勤務先情報.勤務先郵便番号 = this.携帯番号・勤務先情報変更申込行.勤務先郵便番号 <BR>
     * 　@携帯番号・勤務先情報.勤務先住所 = this.携帯番号・勤務先情報変更申込行.勤務先住所 <BR>
     * 　@携帯番号・勤務先情報.勤務先電話番号 = this.携帯番号・勤務先情報変更申込行.勤務先電話番号 <BR>
     * 　@携帯番号・勤務先情報.役職名 = this.携帯番号・勤務先情報変更申込行.役職 <BR>
     * 　@携帯番号・勤務先情報.連絡先優先順位 1位 = this.携帯番号・勤務先情報変更申込行.連絡先優先順位 1位 <BR>
     * 　@携帯番号・勤務先情報.連絡先優先順位 2位 = this.携帯番号・勤務先情報変更申込行.連絡先優先順位 2位 <BR>
     * 　@携帯番号・勤務先情報.連絡先優先順位 3位 = this.携帯番号・勤務先情報変更申込行.連絡先優先順位 3位 <BR>
     * 　@携帯番号・勤務先情報.顧客正式名称１ = this.携帯番号・勤務先情報変更申込行.正式名称１   <BR>
     * 　@携帯番号・勤務先情報.顧客正式名称２ = this.携帯番号・勤務先情報変更申込行.正式名称２   <BR>
     * 　@携帯番号・勤務先情報.職業区分 = this.携帯番号・勤務先情報変更申込行.職業   <BR>
     * 　@携帯番号・勤務先情報.所属 = this.携帯番号・勤務先情報変更申込行.所属   <BR>
     * 　@携帯番号・勤務先情報.国籍 = this.携帯番号・勤務先情報変更申込行.国籍   <BR>
     * 　@携帯番号・勤務先情報.国籍その他 = this.携帯番号・勤務先情報変更申込行.国籍その他   <BR>
     * 　@携帯番号・勤務先情報.代表者名（漢字）姓 = this.携帯番号・勤務先情報変更申込行.代表者名（漢字）姓   <BR>
     * 　@携帯番号・勤務先情報.代表者名（漢字）名 = this.携帯番号・勤務先情報変更申込行.代表者名（漢字）名   <BR>
     * 　@携帯番号・勤務先情報.代表者名（カナ）姓 = this.携帯番号・勤務先情報変更申込行.代表者名（カナ）姓   <BR>
     * 　@携帯番号・勤務先情報.代表者名（カナ）名 = this.携帯番号・勤務先情報変更申込行.代表者名（カナ）名   <BR>
     * 　@携帯番号・勤務先情報.代表者権 = this.携帯番号・勤務先情報変更申込行.代表者権   <BR>
     * 　@携帯番号・勤務先情報.取引責任者名（漢字）姓 = this.携帯番号・勤務先情報変更申込行.取引先責任者名（漢字）姓   <BR>
     * 　@携帯番号・勤務先情報.取引責任者名（漢字）名 = this.携帯番号・勤務先情報変更申込行.取引先責任者名（漢字）名   <BR>
     * 　@携帯番号・勤務先情報.取引責任者名（カナ）姓 = this.携帯番号・勤務先情報変更申込行.取引先責任者名（カナ）姓   <BR>
     * 　@携帯番号・勤務先情報.取引責任者名（カナ）名 = this.携帯番号・勤務先情報変更申込行.取引先責任者名（カナ）名   <BR>
     * 　@携帯番号・勤務先情報.取引責任者　@所属部署 = this.携帯番号・勤務先情報変更申込行.取引責任者所属部署   <BR>
     * 　@携帯番号・勤務先情報.取引責任者　@役職 = this.携帯番号・勤務先情報変更申込行.取引責任者役職   <BR>
     * 　@携帯番号・勤務先情報.取引責任者郵便番号 = this.携帯番号・勤務先情報変更申込行.取引先責任者郵便番号   <BR>
     * 　@携帯番号・勤務先情報.取引責任者住所１ = this.携帯番号・勤務先情報変更申込行.取引先責任者住所１   <BR>
     * 　@携帯番号・勤務先情報.取引責任者住所２ = this.携帯番号・勤務先情報変更申込行.取引先責任者住所２   <BR>
     * 　@携帯番号・勤務先情報.取引責任者住所３ = this.携帯番号・勤務先情報変更申込行.取引先責任者住所３   <BR>
     * 　@携帯番号・勤務先情報.取引責任者生年月日　@年号 = this.携帯番号・勤務先情報変更申込行.取引先責任者生年月日年号   <BR>
     * 　@携帯番号・勤務先情報.取引責任者生年月日 = this.携帯番号・勤務先情報変更申込行.取引先責任者生年月日   <BR>
     * 　@携帯番号・勤務先情報.取引責任者会社直通番号 = this.携帯番号・勤務先情報変更申込行.取引先責任者会社直通番号   <BR>
     * 　@携帯番号・勤務先情報.その他連絡先（携帯、自宅等） = this.携帯番号・勤務先情報変更申込行.その他の連絡先（携帯、自宅等）   <BR>
     * 　@携帯番号・勤務先情報.FAX番号 = this.携帯番号・勤務先情報変更申込行.FAX番号 <BR>
     * 　@携帯番号・勤務先情報.年収 = this.携帯番号・勤務先情報変更申込行.年収 <BR>
     * 　@携帯番号・勤務先情報.金融資産額 = this.携帯番号・勤務先情報変更申込行.金融資産額 <BR>
     * 　@携帯番号・勤務先情報.運用予定額 = this.携帯番号・勤務先情報変更申込行.運用予定額 <BR>
     * 　@携帯番号・勤務先情報.投資目的 = this.携帯番号・勤務先情報変更申込行.投資目的 <BR>
     * 　@携帯番号・勤務先情報.投資予定期間 = this.携帯番号・勤務先情報変更申込行.投資予定期間 <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（１） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（１） <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（２） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（２） <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（３） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（３） <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（４） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（４） <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（５） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（５） <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（６） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（６） <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（７） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（７） <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（８） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（８） <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（９） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（９） <BR>
     * 　@携帯番号・勤務先情報.投資経験の有無（１０） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（１０） <BR>
     * 　@携帯番号・勤務先情報.投資経験（１） = this.携帯番号・勤務先情報変更申込行.投資経験（１） <BR>
     * 　@携帯番号・勤務先情報.投資経験（２） = this.携帯番号・勤務先情報変更申込行.投資経験（２） <BR>
     * 　@携帯番号・勤務先情報.投資経験（３） = this.携帯番号・勤務先情報変更申込行.投資経験（３） <BR>
     * 　@携帯番号・勤務先情報.投資経験（４） = this.携帯番号・勤務先情報変更申込行.投資経験（４） <BR>
     * 　@携帯番号・勤務先情報.投資経験（５） = this.携帯番号・勤務先情報変更申込行.投資経験（５） <BR>
     * 　@携帯番号・勤務先情報.投資経験（６） = this.携帯番号・勤務先情報変更申込行.投資経験（６） <BR>
     * 　@携帯番号・勤務先情報.投資経験（７） = this.携帯番号・勤務先情報変更申込行.投資経験（７） <BR>
     * 　@携帯番号・勤務先情報.投資経験（８） = this.携帯番号・勤務先情報変更申込行.投資経験（８） <BR>
     * 　@携帯番号・勤務先情報.投資経験（９） = this.携帯番号・勤務先情報変更申込行.投資経験（９） <BR>
     * 　@携帯番号・勤務先情報.投資経験（１０） = this.携帯番号・勤務先情報変更申込行.投資経験（１０） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（１） = this.携帯番号・勤務先情報変更申込行.取引の種類（１） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（２） = this.携帯番号・勤務先情報変更申込行.取引の種類（２） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（３） = this.携帯番号・勤務先情報変更申込行.取引の種類（３） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（４） = this.携帯番号・勤務先情報変更申込行.取引の種類（４） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（５） = this.携帯番号・勤務先情報変更申込行.取引の種類（５） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（６） = this.携帯番号・勤務先情報変更申込行.取引の種類（６） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（７） = this.携帯番号・勤務先情報変更申込行.取引の種類（７） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（８） = this.携帯番号・勤務先情報変更申込行.取引の種類（８） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（９） = this.携帯番号・勤務先情報変更申込行.取引の種類（９） <BR>
     * 　@携帯番号・勤務先情報.取引の種類（１０） = this.携帯番号・勤務先情報変更申込行.取引の種類（１０） <BR>
     * 　@携帯番号・勤務先情報.口座開設の動機@ = this.携帯番号・勤務先情報変更申込行.口座開設の動機@ <BR>
     * 　@携帯番号・勤務先情報.口座開設の動機@の詳細 = this.携帯番号・勤務先情報変更申込行.口座開設の動機@の詳細 <BR>
     * 　@携帯番号・勤務先情報.現在利用している証券会社 = this.携帯番号・勤務先情報変更申込行.現在利用している証券会社 <BR>
     * 　@携帯番号・勤務先情報.インターネット取引区分 = this.携帯番号・勤務先情報変更申込行.インターネット取引区分 <BR>
     * 　@携帯番号・勤務先情報.紹介支店 = this.携帯番号・勤務先情報変更申込行.紹介支店 <BR>
     * <BR>
     * <BR>
     * ４）　@１）で生成したオブジェクト.勤務先情報 = 生成した携帯番号・勤務先情報オブジェクト <BR>
     * <BR>
     * ５）　@携帯番号・勤務先情報変更申込顧客オブジェクトを返却。<BR>
     * @@return WEB3AccInfoMobileOfficeChangeAccount
     * @@throws WEB3BaseException
     */
    public WEB3AccInfoMobileOfficeChangeAccount getChangedChangeInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getChangedChangeInfo()";
        log.entering(STR_METHOD_NAME);

        //１）　@this.get変更申込顧客（）で、携帯番号・勤務先情報変更申込顧客オブジェクトを取得。
        WEB3AccInfoMobileOfficeChangeAccount l_changeAccount = this.getChangeRegistAccount();

        //２）　@携帯番号・勤務先情報オブジェクトを生成する。
        WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();

        //３）　@以下の通り、プロパティをセットする。
        //携帯番号・勤務先情報.携帯番号 = this.携帯番号・勤務先情報変更申込行.連絡先電話番号（携帯）
        l_accInfoMobileOfficeInfo.mobileTelephone = this.mobileOfficeInfoRegistParams.getMobile();

        //携帯番号・勤務先情報.勤務先名称 = this.携帯番号・勤務先情報変更申込行.勤務先名称
        l_accInfoMobileOfficeInfo.officeName = this.mobileOfficeInfoRegistParams.getOffice();

        //携帯番号・勤務先情報.勤務先郵便番号 = this.携帯番号・勤務先情報変更申込行.勤務先郵便番号
        l_accInfoMobileOfficeInfo.officeZipCode = this.mobileOfficeInfoRegistParams.getOfficeZipCode();

        //携帯番号・勤務先情報.勤務先住所 = this.携帯番号・勤務先情報変更申込行.勤務先住所
        l_accInfoMobileOfficeInfo.officeAdress = this.mobileOfficeInfoRegistParams.getOfficeAddress();

        //携帯番号・勤務先情報.勤務先電話番号 = this.携帯番号・勤務先情報変更申込行.勤務先電話番号
        l_accInfoMobileOfficeInfo.officeTelephone = this.mobileOfficeInfoRegistParams.getOfficeTelephone();

        //携帯番号・勤務先情報.役職名 = this.携帯番号・勤務先情報変更申込行.役職
        l_accInfoMobileOfficeInfo.position = this.mobileOfficeInfoRegistParams.getPost();

        //携帯番号・勤務先情報.連絡先優先順位 1位 = this.携帯番号・勤務先情報変更申込行.連絡先優先順位 1位
        l_accInfoMobileOfficeInfo.contactPriority1 = this.mobileOfficeInfoRegistParams.getContactPriority1();

        //携帯番号・勤務先情報.連絡先優先順位 2位 = this.携帯番号・勤務先情報変更申込行.連絡先優先順位 2位
        l_accInfoMobileOfficeInfo.contactPriority2 = this.mobileOfficeInfoRegistParams.getContactPriority2();

        //携帯番号・勤務先情報.連絡先優先順位 3位 = this.携帯番号・勤務先情報変更申込行.連絡先優先順位 3位
        l_accInfoMobileOfficeInfo.contactPriority3 = this.mobileOfficeInfoRegistParams.getContactPriority3();

        //携帯番号・勤務先情報.顧客正式名称１ = this.携帯番号・勤務先情報変更申込行.正式名称１
        l_accInfoMobileOfficeInfo.accountRealName1 = this.mobileOfficeInfoRegistParams.getRealName1();

        //携帯番号・勤務先情報.顧客正式名称２ = this.携帯番号・勤務先情報変更申込行.正式名称２
        l_accInfoMobileOfficeInfo.accountRealName2 = this.mobileOfficeInfoRegistParams.getRealName2();

        //携帯番号・勤務先情報.職業区分 = this.携帯番号・勤務先情報変更申込行.職業
        l_accInfoMobileOfficeInfo.occupationDiv = this.mobileOfficeInfoRegistParams.getOccupationDiv();

        //携帯番号・勤務先情報.所属 = this.携帯番号・勤務先情報変更申込行.所属
        l_accInfoMobileOfficeInfo.department = this.mobileOfficeInfoRegistParams.getDepartment();

        //携帯番号・勤務先情報.国籍 = this.携帯番号・勤務先情報変更申込行.国籍
        l_accInfoMobileOfficeInfo.nationality = this.mobileOfficeInfoRegistParams.getNationality();

        //携帯番号・勤務先情報.国籍その他 = this.携帯番号・勤務先情報変更申込行.国籍その他
        l_accInfoMobileOfficeInfo.nationalityOther = this.mobileOfficeInfoRegistParams.getNationalityOther();

        //携帯番号・勤務先情報.代表者名（漢字）姓 = this.携帯番号・勤務先情報変更申込行.代表者名（漢字）姓
        l_accInfoMobileOfficeInfo.representFamilyName =
            this.mobileOfficeInfoRegistParams.getRepresentFamilyName();

        //携帯番号・勤務先情報.代表者名（漢字）名 = this.携帯番号・勤務先情報変更申込行.代表者名（漢字）名
        l_accInfoMobileOfficeInfo.representName = this.mobileOfficeInfoRegistParams.getRepresentGivenName();

        //携帯番号・勤務先情報.代表者名（カナ）姓 = this.携帯番号・勤務先情報変更申込行.代表者名（カナ）姓
        l_accInfoMobileOfficeInfo.representFamilyNameKana =
            this.mobileOfficeInfoRegistParams.getRepresentFamilyNameAlt1();

        //携帯番号・勤務先情報.代表者名（カナ）名 = this.携帯番号・勤務先情報変更申込行.代表者名（カナ）名
        l_accInfoMobileOfficeInfo.representNameKana =
            this.mobileOfficeInfoRegistParams.getRepresentGivenNameAlt1();

        //携帯番号・勤務先情報.代表者権 = this.携帯番号・勤務先情報変更申込行.代表者権
        l_accInfoMobileOfficeInfo.representPower = this.mobileOfficeInfoRegistParams.getRepresentPower();

        //携帯番号・勤務先情報.取引責任者名（漢字）姓 = this.携帯番号・勤務先情報変更申込行.取引先責任者名（漢字）姓
        l_accInfoMobileOfficeInfo.directorFamilyName =
            this.mobileOfficeInfoRegistParams.getDirectorFamilyName();

        //携帯番号・勤務先情報.取引責任者名（漢字）名 = this.携帯番号・勤務先情報変更申込行.取引先責任者名（漢字）名
        l_accInfoMobileOfficeInfo.directorName = this.mobileOfficeInfoRegistParams.getDirectorGivenName();

        //携帯番号・勤務先情報.取引責任者名（カナ）姓 = this.携帯番号・勤務先情報変更申込行.取引先責任者名（カナ）姓
        l_accInfoMobileOfficeInfo.directorFamilyNameKana =
            this.mobileOfficeInfoRegistParams.getDirectorFamilyNameAlt1();

        //携帯番号・勤務先情報.取引責任者名（カナ）名 = this.携帯番号・勤務先情報変更申込行.取引先責任者名（カナ）名
        l_accInfoMobileOfficeInfo.directorNameKana =
            this.mobileOfficeInfoRegistParams.getDirectorGivenNameAlt1();

        //携帯番号・勤務先情報.取引責任者　@所属部署 = this.携帯番号・勤務先情報変更申込行.取引責任者所属部署
        l_accInfoMobileOfficeInfo.directorDepartment =
            this.mobileOfficeInfoRegistParams.getDirectorDepartment();

        //携帯番号・勤務先情報.取引責任者　@役職 = this.携帯番号・勤務先情報変更申込行.取引責任者役職
        l_accInfoMobileOfficeInfo.directorPosition = this.mobileOfficeInfoRegistParams.getDirectorPost();

        //携帯番号・勤務先情報.取引責任者郵便番号 = this.携帯番号・勤務先情報変更申込行.取引先責任者郵便番号
        l_accInfoMobileOfficeInfo.directorZipCode = this.mobileOfficeInfoRegistParams.getDirectorZipCode();

        //携帯番号・勤務先情報.取引責任者住所１ = this.携帯番号・勤務先情報変更申込行.取引先責任者住所１
        l_accInfoMobileOfficeInfo.directorAddress1 = this.mobileOfficeInfoRegistParams.getDirectorAddress1();

        //携帯番号・勤務先情報.取引責任者住所２ = this.携帯番号・勤務先情報変更申込行.取引先責任者住所２
        l_accInfoMobileOfficeInfo.directorAddress2 = this.mobileOfficeInfoRegistParams.getDirectorAddress2();

        //携帯番号・勤務先情報.取引責任者住所３ = this.携帯番号・勤務先情報変更申込行.取引先責任者住所３
        l_accInfoMobileOfficeInfo.directorAddress3 = this.mobileOfficeInfoRegistParams.getDirectorAddress3();

        //携帯番号・勤務先情報.取引責任者生年月日　@年号 = this.携帯番号・勤務先情報変更申込行.取引先責任者生年月日年号
        l_accInfoMobileOfficeInfo.directorEraBorn = this.mobileOfficeInfoRegistParams.getDirectorEraBorn();

        //携帯番号・勤務先情報.取引責任者生年月日 = this.携帯番号・勤務先情報変更申込行.取引先責任者生年月日
        l_accInfoMobileOfficeInfo.directorBornDate = this.mobileOfficeInfoRegistParams.getDirectorBornDate();

        //携帯番号・勤務先情報.取引責任者会社直通番号 = this.携帯番号・勤務先情報変更申込行.取引先責任者会社直通番号
        l_accInfoMobileOfficeInfo.directorCorpDirect =
            this.mobileOfficeInfoRegistParams.getDirectorCorpTelephone();

        //携帯番号・勤務先情報.その他連絡先（携帯、自宅等） = this.携帯番号・勤務先情報変更申込行.その他の連絡先（携帯、自宅等）
        l_accInfoMobileOfficeInfo.directorOtherContact = this.mobileOfficeInfoRegistParams.getOtherContact();

        //携帯番号・勤務先情報.FAX番号 = this.携帯番号・勤務先情報変更申込行.FAX番号
        l_accInfoMobileOfficeInfo.faxTelephone = this.mobileOfficeInfoRegistParams.getFax();

        //携帯番号・勤務先情報.年収 = this.携帯番号・勤務先情報変更申込行.年収
        l_accInfoMobileOfficeInfo.annualIncomeDiv = this.mobileOfficeInfoRegistParams.getAnnualIncomeDiv();

        //携帯番号・勤務先情報.金融資産額 = this.携帯番号・勤務先情報変更申込行.金融資産額
        l_accInfoMobileOfficeInfo.assetValueDiv = this.mobileOfficeInfoRegistParams.getAssetValueDiv();

        //携帯番号・勤務先情報.運用予定額 = this.携帯番号・勤務先情報変更申込行.運用予定額
        l_accInfoMobileOfficeInfo.fundBundgetAmountDiv =
            this.mobileOfficeInfoRegistParams.getFundBudgetAmountDiv();

        //携帯番号・勤務先情報.投資目的 = this.携帯番号・勤務先情報変更申込行.投資目的
        l_accInfoMobileOfficeInfo.investPurposeDiv = this.mobileOfficeInfoRegistParams.getInvestPurposeDiv();

        //携帯番号・勤務先情報.投資予定期間 = this.携帯番号・勤務先情報変更申込行.投資予定期間
        l_accInfoMobileOfficeInfo.investPlanPeriodDiv =
            this.mobileOfficeInfoRegistParams.getInvestPlanPeriodDiv();

        //携帯番号・勤務先情報.投資経験の有無（１） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（１）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag1IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag1 = this.mobileOfficeInfoRegistParams.getExperienceFlag1() + "";
        }

        //携帯番号・勤務先情報.投資経験の有無（２） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（２）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag2IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag2 = this.mobileOfficeInfoRegistParams.getExperienceFlag2() + "";
        }

        //携帯番号・勤務先情報.投資経験の有無（３） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（３）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag3IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag3 = this.mobileOfficeInfoRegistParams.getExperienceFlag3() + "";
        }

        //携帯番号・勤務先情報.投資経験の有無（４） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（４）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag4IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag4 = this.mobileOfficeInfoRegistParams.getExperienceFlag4() + "";
        }

        //携帯番号・勤務先情報.投資経験の有無（５） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（５）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag5IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag5 = this.mobileOfficeInfoRegistParams.getExperienceFlag5() + "";
        }

        //携帯番号・勤務先情報.投資経験の有無（６） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（６）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag6IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag6 = this.mobileOfficeInfoRegistParams.getExperienceFlag6() + "";
        }

        //携帯番号・勤務先情報.投資経験の有無（７） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（７）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag7IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag7 = this.mobileOfficeInfoRegistParams.getExperienceFlag7() + "";
        }

        //携帯番号・勤務先情報.投資経験の有無（８） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（８）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag8IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag8 = this.mobileOfficeInfoRegistParams.getExperienceFlag8() + "";
        }

        //携帯番号・勤務先情報.投資経験の有無（９） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（９）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag9IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag9 = this.mobileOfficeInfoRegistParams.getExperienceFlag9() + "";
        }

        //携帯番号・勤務先情報.投資経験の有無（１０） = this.携帯番号・勤務先情報変更申込行.投資経験の有無（１０）
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag10IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag10 =
                this.mobileOfficeInfoRegistParams.getExperienceFlag10() + "";
        }

        //携帯番号・勤務先情報.投資経験（１） = this.携帯番号・勤務先情報変更申込行.投資経験（１）
        l_accInfoMobileOfficeInfo.experienceDiv1 = this.mobileOfficeInfoRegistParams.getExperienceDiv1();

        //携帯番号・勤務先情報.投資経験（２） = this.携帯番号・勤務先情報変更申込行.投資経験（２）
        l_accInfoMobileOfficeInfo.experienceDiv2 = this.mobileOfficeInfoRegistParams.getExperienceDiv2();

        //携帯番号・勤務先情報.投資経験（３） = this.携帯番号・勤務先情報変更申込行.投資経験（３）
        l_accInfoMobileOfficeInfo.experienceDiv3 = this.mobileOfficeInfoRegistParams.getExperienceDiv3();

        //携帯番号・勤務先情報.投資経験（４） = this.携帯番号・勤務先情報変更申込行.投資経験（４）
        l_accInfoMobileOfficeInfo.experienceDiv4 = this.mobileOfficeInfoRegistParams.getExperienceDiv4();

        //携帯番号・勤務先情報.投資経験（５） = this.携帯番号・勤務先情報変更申込行.投資経験（５）
        l_accInfoMobileOfficeInfo.experienceDiv5 = this.mobileOfficeInfoRegistParams.getExperienceDiv5();

        //携帯番号・勤務先情報.投資経験（６） = this.携帯番号・勤務先情報変更申込行.投資経験（６）
        l_accInfoMobileOfficeInfo.experienceDiv6 = this.mobileOfficeInfoRegistParams.getExperienceDiv6();

        //携帯番号・勤務先情報.投資経験（７） = this.携帯番号・勤務先情報変更申込行.投資経験（７）
        l_accInfoMobileOfficeInfo.experienceDiv7 = this.mobileOfficeInfoRegistParams.getExperienceDiv7();

        //携帯番号・勤務先情報.投資経験（８） = this.携帯番号・勤務先情報変更申込行.投資経験（８）
        l_accInfoMobileOfficeInfo.experienceDiv8 = this.mobileOfficeInfoRegistParams.getExperienceDiv8();

        //携帯番号・勤務先情報.投資経験（９） = this.携帯番号・勤務先情報変更申込行.投資経験（９）
        l_accInfoMobileOfficeInfo.experienceDiv9 = this.mobileOfficeInfoRegistParams.getExperienceDiv9();

        //携帯番号・勤務先情報.投資経験（１０） = this.携帯番号・勤務先情報変更申込行.投資経験（１０）
        l_accInfoMobileOfficeInfo.experienceDiv10 = this.mobileOfficeInfoRegistParams.getExperienceDiv10();

        //携帯番号・勤務先情報.取引の種類（１） = this.携帯番号・勤務先情報変更申込行.取引の種類（１）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag1IsNull())
        {
            l_accInfoMobileOfficeInfo.interest1 = this.mobileOfficeInfoRegistParams.getInterestFlag1() + "";
        }

        //携帯番号・勤務先情報.取引の種類（２） = this.携帯番号・勤務先情報変更申込行.取引の種類（２）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag2IsNull())
        {
            l_accInfoMobileOfficeInfo.interest2 = this.mobileOfficeInfoRegistParams.getInterestFlag2() + "";
        }

        //携帯番号・勤務先情報.取引の種類（３） = this.携帯番号・勤務先情報変更申込行.取引の種類（３）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag3IsNull())
        {
            l_accInfoMobileOfficeInfo.interest3 = this.mobileOfficeInfoRegistParams.getInterestFlag3() + "";
        }

        //携帯番号・勤務先情報.取引の種類（４） = this.携帯番号・勤務先情報変更申込行.取引の種類（４）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag4IsNull())
        {
            l_accInfoMobileOfficeInfo.interest4 = this.mobileOfficeInfoRegistParams.getInterestFlag4() + "";
        }

        //携帯番号・勤務先情報.取引の種類（５） = this.携帯番号・勤務先情報変更申込行.取引の種類（５）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag5IsNull())
        {
            l_accInfoMobileOfficeInfo.interest5 = this.mobileOfficeInfoRegistParams.getInterestFlag5() + "";
        }

        //携帯番号・勤務先情報.取引の種類（６） = this.携帯番号・勤務先情報変更申込行.取引の種類（６）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag6IsNull())
        {
            l_accInfoMobileOfficeInfo.interest6 = this.mobileOfficeInfoRegistParams.getInterestFlag6() + "";
        }

        //携帯番号・勤務先情報.取引の種類（７） = this.携帯番号・勤務先情報変更申込行.取引の種類（７）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag7IsNull())
        {
            l_accInfoMobileOfficeInfo.interest7 = this.mobileOfficeInfoRegistParams.getInterestFlag7() + "";
        }

        //携帯番号・勤務先情報.取引の種類（８） = this.携帯番号・勤務先情報変更申込行.取引の種類（８）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag8IsNull())
        {
            l_accInfoMobileOfficeInfo.interest8 = this.mobileOfficeInfoRegistParams.getInterestFlag8() + "";
        }

        //携帯番号・勤務先情報.取引の種類（９） = this.携帯番号・勤務先情報変更申込行.取引の種類（９）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag9IsNull())
        {
            l_accInfoMobileOfficeInfo.interest9 = this.mobileOfficeInfoRegistParams.getInterestFlag9() + "";
        }

        //携帯番号・勤務先情報.取引の種類（１０） = this.携帯番号・勤務先情報変更申込行.取引の種類（１０）
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag10IsNull())
        {
            l_accInfoMobileOfficeInfo.interest10 = this.mobileOfficeInfoRegistParams.getInterestFlag10() + "";
        }

        //携帯番号・勤務先情報.口座開設の動機@ = this.携帯番号・勤務先情報変更申込行.口座開設の動機@
        l_accInfoMobileOfficeInfo.appliMotivatDiv = this.mobileOfficeInfoRegistParams.getAppliMotivatDiv();

        //携帯番号・勤務先情報.口座開設の動機@の詳細 = this.携帯番号・勤務先情報変更申込行.口座開設の動機@の詳細
        l_accInfoMobileOfficeInfo.appliMotivatDetail = this.mobileOfficeInfoRegistParams.getAppliMotivatDivDetail();

        //携帯番号・勤務先情報.現在利用している証券会社 = this.携帯番号・勤務先情報変更申込行.現在利用している証券会社
        l_accInfoMobileOfficeInfo.useInstitutionDiv = this.mobileOfficeInfoRegistParams.getUseInstitutionDiv();

        //携帯番号・勤務先情報.インターネット取引区分 = this.携帯番号・勤務先情報変更申込行.インターネット取引区分
        l_accInfoMobileOfficeInfo.internetTradeDiv = this.mobileOfficeInfoRegistParams.getInternetTradeDiv();

        //携帯番号・勤務先情報.紹介支店 = this.携帯番号・勤務先情報変更申込行.紹介支店
        l_accInfoMobileOfficeInfo.introduceBranch = this.mobileOfficeInfoRegistParams.getIntroduceBranchCode();

        //４）　@１）で生成したオブジェクト.勤務先情報 = 生成した携帯番号・勤務先情報オブジェクト
        l_changeAccount.mobileOfficeInfo = l_accInfoMobileOfficeInfo;

        //５）　@携帯番号・勤務先情報変更申込顧客オブジェクトを返却。
        log.exiting(STR_METHOD_NAME);
        return l_changeAccount;
    }

    /**
     * (get口座区分情報List)<BR>
     * 引数の口座区分と合致した条件のListを生成し、返却する。 <BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成 <BR>
     * <BR>
     * ２）　@携帯番号・勤務先情報変更申込Listの要素分、Loop処理を行う。 <BR>
     * <BR>
     * 　@２－１）　@携帯番号・勤務先情報変更申込オブジェクト.get顧客（）でオブジェクトを取得 <BR>
     * <BR>
     * 　@２－２）　@口座区分 = 0：個人口座の場合、 <BR>
     * 　@　@　@　@　@　@顧客オブジェクトis法@人（） = false であれば携帯番号・勤務先情報変更申込オブジェクトをadd() <BR>
     * <BR>
     * 　@２－３）　@口座区分 = 1：法@人口座の場合、 <BR>
     * 　@　@　@　@　@　@顧客オブジェクトis法@人（） = true であれば携帯番号・勤務先情報変更申込オブジェクトをadd() <BR>
     * <BR>
     * ３）　@ArrayListオブジェクトを返却する。<BR>
     * @@param l_lisAccInfoMobileOfficeInfoRegist - (携帯番号・勤務先情報変更申込List)<BR>
     * 携帯番号・勤務先情報変更申込List。
     * @@param l_strAccInfoTaxType - (口座区分)<BR>
     * 口座区分 <BR>
     * <BR>
     * 0 ： 個人口座 <BR>
     * 1 ： 法@人口座
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getAccountTypeInfoList(
        List l_lisAccInfoMobileOfficeInfoRegist,
        String l_strAccInfoTaxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getAccountTypeInfoList(List, String)";
        log.entering(STR_METHOD_NAME);

        if (l_lisAccInfoMobileOfficeInfoRegist == null || l_lisAccInfoMobileOfficeInfoRegist.isEmpty())
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME,
                "[l_lisAccInfoMobileOfficeInfoRegist] = " + l_lisAccInfoMobileOfficeInfoRegist
                );
        }

        //１）　@ArrayListオブジェクトの生成
        List l_lisAccInfoTaxTypes = new ArrayList();

        //２）　@携帯番号・勤務先情報変更申込Listの要素分、Loop処理を行う。
        int l_intIndex = l_lisAccInfoMobileOfficeInfoRegist.size();
        for (int i = 0; i < l_intIndex; i++)
        {
            WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                (WEB3AccInfoMobileOfficeInfoRegist)l_lisAccInfoMobileOfficeInfoRegist.get(i);
            //２－１）　@携帯番号・勤務先情報変更申込オブジェクト.get顧客（）でオブジェクトを取得
            WEB3GentradeMainAccount l_mainAccount =
                l_accInfoMobileOfficeInfoRegist.getMainAccount();

            //２－２）　@口座区分 = 0：個人口座の場合、
            //顧客オブジェクトis法@人（） = false であれば携帯番号・勤務先情報変更申込オブジェクトをadd()
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_strAccInfoTaxType))
            {
                if (!l_mainAccount.isCorporation())
                {
                    l_lisAccInfoTaxTypes.add(l_accInfoMobileOfficeInfoRegist);
                }
            }

            //２－３）　@口座区分 = 1：法@人口座の場合、
            //顧客オブジェクトis法@人（） = true であれば携帯番号・勤務先情報変更申込オブジェクトをadd()
            else if (WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_strAccInfoTaxType))
            {
                if (l_mainAccount.isCorporation())
                {
                    l_lisAccInfoTaxTypes.add(l_accInfoMobileOfficeInfoRegist);
                }
            }
        }

        //３）　@ArrayListオブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisAccInfoTaxTypes;
    }

    /**
     * (get変更申込顧客)<BR>
     * 携帯番号・勤務先情報変更申込顧客オブジェクトを生成し、値を返却する。 <BR>
     * <BR>
     * １）　@携帯番号・勤務先情報変更申込顧客オブジェクトを生成 <BR>
     * <BR>
     * ２）　@以下の通り、プロパティをセットする。  <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.申込日 = this.get申込日時() <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.部店コード = this.get部店().getBranchCode() <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.顧客コード =　@this.get顧客().get表示顧客コード() <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.顧客名 = this.get顧客().get顧客表示名() <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.申込者コード = this.携帯番号・勤務先情報変更申込行.申込者コード <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.判定日 = this.get判定日時() <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.判定者コード =　@this.携帯番号・勤務先情報変更申込行.判定者コード <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.申込状況区分 = this.get申込状況区分() <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.判定結果 = this.携帯番号・勤務先情報変更申込行.判定結果 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@== 0："DEFAULT"の場合、null <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@以外、携帯番号・勤務先情報変更申込.判定結果 <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.口座区分 = this.get顧客().is法@人() = true の場合、1："法@人口座" <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ = this.get顧客().is法@人() = false の場合、0："個人口座" <BR>
     * 　@　@　@携帯番号・勤務先情報変更申込顧客.受付結果 =　@this.携帯番号・勤務先情報変更申込行.受付結果<BR>
     * <BR>
     * <BR>
     * ３）　@携帯番号・勤務先情報変更申込顧客オブジェクトを返却 <BR>
     * @@return WEB3AccInfoMobileOfficeChangeAccount
     * @@throws WEB3BaseException
     */
    public WEB3AccInfoMobileOfficeChangeAccount getChangeRegistAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getChangeRegistAccount()";
        log.entering(STR_METHOD_NAME);

        //１）　@携帯番号・勤務先情報変更申込顧客オブジェクトを生成
        WEB3AccInfoMobileOfficeChangeAccount l_changeAccount =
            new WEB3AccInfoMobileOfficeChangeAccount();

        //２）　@以下の通り、プロパティをセットする。
        //携帯番号・勤務先情報変更申込顧客.申込日 = this.get申込日時()
        l_changeAccount.applyDate = this.getRegistTime();

        //携帯番号・勤務先情報変更申込顧客.部店コード = this.get部店().getBranchCode()
        l_changeAccount.branchCode = this.getBranch().getBranchCode();

        //携帯番号・勤務先情報変更申込顧客.顧客コード =　@this.get顧客().get表示顧客コード()
        l_changeAccount.accountCode = this.getMainAccount().getDisplayAccountCode();

        //携帯番号・勤務先情報変更申込顧客.顧客名 = this.get顧客().get顧客表示名()
        l_changeAccount.accountName = this.getMainAccount().getDisplayAccountName();

        //携帯番号・勤務先情報変更申込顧客.申込者コード =
        //       this.携帯番号・勤務先情報変更申込行.申込者コード
        l_changeAccount.updaterCode = this.mobileOfficeInfoRegistParams.getRegistUpdater();

        //携帯番号・勤務先情報変更申込顧客.判定日 = this.get判定日時()
        l_changeAccount.judgementDate = this.getDecisionTime();

        //携帯番号・勤務先情報変更申込顧客.判定者コード =
        //       this.携帯番号・勤務先情報変更申込行.判定者コード
        l_changeAccount.judgeCode = this.mobileOfficeInfoRegistParams.getDecisionUpdater();

        //携帯番号・勤務先情報変更申込顧客.申込状況区分 = this.get申込状況区分()
        l_changeAccount.applyStateDiv = this.getRegistStateDiv();

        //携帯番号・勤務先情報変更申込顧客.判定結果 =
        //       this.携帯番号・勤務先情報変更申込行.判定結果 == 0："DEFAULT"の場合、null
        //       以外、携帯番号・勤務先情報変更申込.判定結果
        if (WEB3JudgmentResultDivDef.DEFAULT.equals(this.mobileOfficeInfoRegistParams.getDecision() + ""))
        {
            l_changeAccount.judgmentResultDiv = null;
        }
        else
        {
            l_changeAccount.judgmentResultDiv = this.mobileOfficeInfoRegistParams.getDecision() + "";
        }

        //携帯番号・勤務先情報変更申込顧客.口座区分 =
        //       this.get顧客().is法@人() = true の場合、1："法@人口座"
        //       = this.get顧客().is法@人() = false の場合、0："個人口座"
        if (this.getMainAccount().isCorporation())
        {
            l_changeAccount.accountType = WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT;
        }
        else
        {
            l_changeAccount.accountType = WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT;
        }
        
        //携帯番号・勤務先情報変更申込顧客.受付結果 =　@this.携帯番号・勤務先情報変更申込行.受付結果
        l_changeAccount.acceptedResult = mobileOfficeInfoRegistParams.getAcceptStatus();

        //３）　@携帯番号・勤務先情報変更申込顧客オブジェクトを返却
        log.exiting(STR_METHOD_NAME);
        return l_changeAccount;
    }
}
@
