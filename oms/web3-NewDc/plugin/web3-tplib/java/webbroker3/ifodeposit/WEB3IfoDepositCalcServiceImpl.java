head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金計算サービス(WEB3IfoDepositCalcServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/10/26 山田　@卓司(FLJ) 新規作成
Revesion History : 2007/06/28 hijikata(SRA) 夕場対応 モデルNo.057, No.081
Revision History : 2007/10/18 k.yamashita(SRA)  未取込要件No.021
*/
package webbroker3.ifodeposit;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3IfodepositNonCalcSqProductDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金計算サービス)<BR>
 * 証拠金計算サービスクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositCalcServiceImpl implements WEB3IfoDepositCalcService
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositCalcServiceImpl.class);

    /**
     * (証拠金計算サービス)<BR>
     * 
     * コンストラクタ。<BR>
     * @@roseuid 41136054020C
     */
    public WEB3IfoDepositCalcServiceImpl()
    {

    }

    /**
     * (get証拠金計算)<BR>
     * 
     * 証拠金計算を生成し、返却する。<BR>
     * 
     * １）　@this.create証拠金計算条件(引数.補助口座)をコールし、証拠金計算条件を生成す
     * る。<BR>
     * 
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@補助口座：　@引数.補助口座<BR>
     * 
     * ２）　@証拠金計算オブジェクト(補助口座、 証拠金計算条件)を生成する。<BR>
     * 
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@補助口座：　@引数.補助口座<BR>
     * 　@　@　@証拠金計算条件：　@１）で生成した証拠金計算条件<BR>
     * 
     * ３）　@２）で生成した証拠金計算オブジェクトを返却する。<BR>
     * @@param l_subAcount - (補助口座)<BR>
     * 
     * 補助口座オブジェクト。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalc
     * @@roseuid 410F6434019F
     */
    public WEB3IfoDepositCalc getIfoDepositCalc(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getIfoDepositCalc(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_condition = createIfoDepositCalcCondition(l_subAccount);
            WEB3IfoDepositCalc l_ifoCalc = new WEB3IfoDepositCalc(l_subAccount, l_condition);
            
            log.exiting(STR_METHOD_NAME);
            return l_ifoCalc;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get証拠金計算)<BR>
     * 
     * 今回の注文が反映された証拠金計算を生成し、返却する。<BR>
     * （新規建余力チェック時に使用）<BR>
     * 
     * １）　@this.create証拠金計算条件(補助口座)をコールし、証拠金計算条件を生成する。<
     * BR>
     * 
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@補助口座：　@引数.補助口座<BR>
     * 
     * ２）　@証拠金計算オブジェクト(補助口座、 証拠金計算条件、 
     * 先物OP現注文内容[])を生成する。<BR>
     * 
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@補助口座：　@引数.補助口座<BR>
     * 　@　@　@証拠金計算条件：　@１）で生成した証拠金計算条件<BR>
     * 　@　@　@先物OP現注文内容：　@引数.先物OP現注文内容<BR>
     * 
     * ３）　@２）で生成した証拠金計算オブジェクトを返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 
     * 補助口座オブジェクト。<BR>
     * @@param l_ifoNewOrderSpec - 先物OP現注文内容。
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalc
     * @@roseuid 4113628D00B4
     */
    public WEB3IfoDepositCalc getIfoDepositCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getIfoDepositCalc(WEB3GentradeSubAccount, WEB3IfoNewOrderSpec)";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_condition = createIfoDepositCalcCondition(l_subAccount);
            WEB3IfoDepositCalc l_ifoCalc = new WEB3IfoDepositCalc(
                    l_subAccount, l_condition, l_ifoNewOrderSpec);

            log.exiting(STR_METHOD_NAME);
            return l_ifoCalc;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (create証拠金計算条件)<BR>
     * 
     * 証拠金計算条件を作成する。<BR>
     * 
     * シーケンス図<BR>
     * 「（証拠金計算サービス）create証拠金計算条件」参照。<BR>
     * 
     * 【使用DB】<BR>
     * ・余力条件テーブル<BR>
     * ・証券会社テーブル<BR>
     * ・部店テーブル<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 
     * 補助口座オブジェクト。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalcCondition
     * @@roseuid 41122F6F0378
     */
    public WEB3IfoDepositCalcCondition createIfoDepositCalcCondition(WEB3GentradeSubAccount l_subAccount) 
     {

        WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
        l_condition.calcBizDate();
        l_condition.calcIfoDepositCalcBaseDate();
        //証拠金不足メール送信済フラグをセット
        boolean l_blnIfoDepositMail  = isIfoDepositMailFlag(l_subAccount);
        l_condition.setIfoDepositMailFlag(l_blnIfoDepositMail);
        //清算値速報受信済フラグをセット
        boolean l_blnQuickReport  = isQuickReportReceived(l_subAccount);
        l_condition.setQuickReportReceivedFlag(l_blnQuickReport);
        //夕場実施フラグをセット
        boolean l_blnEveningSession  = isEveningSession(l_subAccount);
        l_condition.setEveningSessionFlag(l_blnEveningSession);

        l_condition.createIfoDepositCalcConditionPerIndexList(l_subAccount);
        double l_dblPreBizDateIfoDepositLackCharge =
            getPreBizDateIfoDepositLackCharge(l_subAccount, l_condition.getPreBizDate());
        l_condition.setPreBizDateInfoDepositLackCharge(l_dblPreBizDateIfoDepositLackCharge);

        double l_dblCurrentBizDateIfoDepositLackCharge =
            getCurrentBizDateIfoDepositLackCharge(l_subAccount, l_condition.getCurrentBizDate());
        l_condition.setCurrentBizIfoDepositLackCharge(l_dblCurrentBizDateIfoDepositLackCharge);

        boolean l_blnNewOpenTradingPowerAvailable = isNewOpenTradingPowerAvailable(l_subAccount);
        l_condition.setNewOpenTradingPowerAvailableFlag(l_blnNewOpenTradingPowerAvailable);

        //証拠金不足額非管理フラグをセット
        boolean l_lackChargeNonManagement = isLackChargeNonManagement(l_subAccount);
        l_condition.setLackChargeNonManagementFlag(l_lackChargeNonManagement);
        
        //証拠金SQ日銘柄ポジション非計上をセット
        boolean IfodepositNonCalcSqProductFlag = isIfodepositNonCalcSqProductFlag(l_subAccount);
        l_condition.setIfodepositNonCalcSqProductFlag(IfodepositNonCalcSqProductFlag);


        InstitutionRow l_inst = (InstitutionRow)l_subAccount.getInstitution().getDataSourceObject();
        l_condition.setRealPriceIfoDepositCalcFlag(toBoolean(l_inst.getIfoRealPriceCalcDiv()));
        l_condition.setSimpleSPANCalcFlag(toBoolean(l_inst.getSimpleSpanCalcDiv()));
        l_condition.setSpanTroubleFlag(toBoolean(l_inst.getSpanTroubleDiv()));
        l_condition.setSPANFactor(l_inst.getSpanFactor());
        l_condition.setSPANFactorRed(l_inst.getSpanFactorRed());
        l_condition.setTransferPowerFactor(l_inst.getTransferPowerFactor());

        BranchRow l_branch = (BranchRow)l_subAccount.getWeb3GenBranch().getDataSourceObject();
        l_condition.setMinIfoDeposit(l_branch.getMinIfoDeposit());

        //部店別証拠金計算条件をセット        
        BranchPreferencesParams[] l_params =
            WEB3IfoDepositPersistentDataManager.getBranchPreferencesParamsList(
                l_branch.getBranchId());
        if (l_params != null)
        {
            for (int i = 0; i < l_params.length; i++)
            {
                l_condition.addCalcConditionPerBranch(l_params[i].getName(), l_params[i].getValue());
            }
        }

        log.debug("Created CalcCondition=" + l_condition.toString());

        return l_condition;

    }

    // private methods ---------------------------------------------------------    

    /**
     * (is新規建余力可能)<BR>
     * 
     * 該当顧客が新規建余力可能であるかを判定する。<BR>
     * 新規建余力が可能である場合はtrueを、不可である場合はfalseを返却する。<BR>
     * 
     * １）　@余力条件Paramsの取得<BR>
     * 　@証拠金計算データソースアクセス管理.get余力条件Params( )<BR>
     * 
     * 　@[引数の設定]<BR>
     * 　@口座ID：　@引数.補助口座.口座ID<BR>
     * 
     * ２）　@証拠金口座開設済みの場合<BR>
     * 　@(引数.補助口座.補助口座タイプ == ”株式オプション取引口座(先物証拠金)”)<BR>
     * 
     * 　@余力条件テーブルParams.取引停止区分 == ”取引可能”、かつ、<BR>
     * 　@余力条件テーブルParams..先物OP新規建余力区分 == 
     * ”余力可”ならば、trueを返却する。<BR>
     * 　@以外、falseを返却する。<BR>
     * 
     * ３）　@証拠金口座未開設の場合<BR>
     * 　@(引数.補助口座.補助口座タイプ != ”株式オプション取引口座(先物証拠金)”)<BR>
     * 
     * 　@余力条件Params.取引停止区分 == ”取引可能”、かつ、<BR>
     * 　@余力条件Params..その他商品買付余力区分 == 
     * ”余力可”ならば、trueを返却する。<BR>
     *   以外、falseを返却する。<BR>
     * @@param l_subAccount - 補助口座オブジェクト。
     * @@return boolean
     * @@roseuid 411C8BE90029
     */
    private boolean isNewOpenTradingPowerAvailable(WEB3GentradeSubAccount l_subAccount)
    {
        boolean l_blnResult = false;
        TradingpowerCalcConditionParams l_condition =
            WEB3IfoDepositPersistentDataManager.getTradingpowerCalcConditionParams(
                l_subAccount.getAccountId());
        if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            if (!toBoolean(l_condition.getTradingStop())
                && !toBoolean(l_condition.getIfoOpenPositionStop()))
            {
                l_blnResult = true;
            }
        }
        else
        {
            if (!toBoolean(l_condition.getTradingStop())
                && !toBoolean(l_condition.getOtherTradingStop()))
            {
                l_blnResult = true;
            }
        }
        return l_blnResult;
    }

    /**
     * (is証拠金不足メール送信済)<BR>
     * <BR>
     * 該当会社部店のT+0の証拠金不足メール送信済であるかを判定する。<BR>
     * 証拠金不足メール送信済である場合はtrueを、未送信である場合はfalseを返却する。<BR>
     * <BR>
     * １）　@プロセス管理Paramsの取得<BR>
     * <BR>
     * 　@証拠金計算データソースアクセス管理.getプロセス管理Params( )<BR>
     * 　@[引数の設定]<BR>
     * 　@　@プロセスID：　@”0001”(証拠金不足確定)<BR>
     * 　@　@証券会社コード：　@引数.補助口座.証券会社コード<BR>
     * 　@　@部店コード：　@引数.補助口座.get取引店.部店コード<BR>
     * <BR>
     * ２）　@証拠金不足メール送信済の場合(getプロセス管理Params( )で該当データあり)、trueを返却する。<BR>
     * <BR>
     * ３）　@証拠金不足メール未送信の場合(getプロセス管理Params( )で該当データなし(null))、falseを返却する。<BR>
     * <BR>
     * 
     * @@param l_subAccount - 補助口座オブジェクト。
     * @@return boolean
     */
    private boolean isIfoDepositMailFlag(WEB3GentradeSubAccount l_subAccount)
    {
        //プロセスID
        String l_strProcessId = "0001";
        //証券会社コード
        String l_strInstCode = l_subAccount.getInstitution().getInstitutionCode();
        //部店コード
        String l_strBranCode = l_subAccount.getWeb3GenBranch().getBranchCode();

        //プロセス管理Paramsの取得
        ProcessManagementParams l_params =
            WEB3IfoDepositPersistentDataManager.getProcessManagementParams(
                l_strProcessId,
                l_strInstCode,
                l_strBranCode);
        
        //証拠金不足メール未送信の場合(getプロセス管理Params( )で該当データなし(null))
        if(l_params == null)
        {
            //falseを返却する。
            return false;
        }
        //証拠金不足メール送信済の場合(getプロセス管理Params( )で該当データあり)
        else
        {        
            //trueを返却する。
            return true;
        }
    }

    /**
     * (get前日証拠金不足額)<BR>
     * 
     * 該当顧客の前日証拠金不足額を取得する。<BR>
     * 
     * １）　@証拠金Paramsの取得<BR>
     * 　@　@証拠金計算データソースアクセス管理.get証拠金Params( )により、<BR>
     * 　@　@営業日[T-1]に証拠金不足メールとして送信されたデータを取得する。<BR>
     * 
     * 　@　@[引数の設定]<BR>
     * 　@　@　@証券会社コード：　@引数.補助口座.証券会社コード<BR>
     * 　@　@　@部店コード：　@引数.補助口座.get取引店.部店コード<BR>
     * 　@　@　@顧客コード：　@引数.補助口座.getMainAccount( ).口座コード<BR>
     * 　@　@　@計算日：　@引数.営業日[T-1]<BR>
     * 
     * ２）　@営業日[T-1]に証拠金不足が発生していない場合(get証拠金Params( )の戻り値 == null)、
     * 　@　@　@0を返却する。<BR>
     * 
     * ３）　@営業日[T+0]の証拠金不足メール未送信の場合(上記以外）、<BR>
     * 　@　@　@証拠金Params.翌日受渡額現金の絶対値(Abs(翌日受渡額現金))を返却する。<BR>
     * 
     * 　@
     * @@param l_subAccount - (補助口座)<BR>
     * 
     * 補助口座オブジェクト。<BR>
     * @@param l_datPreBizDate - 営業日[T-1]。
     * @@return double
     * @@roseuid 4132E785027C
     */
    private double getPreBizDateIfoDepositLackCharge(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datPreBizDate)
    {
        IfoDepositParams l_ifoDeposit = getIfoDepositParams(l_subAccount, l_datPreBizDate);
        if (l_ifoDeposit != null)
        {
            return Math.abs(l_ifoDeposit.getNetAmoutCash());
        }
        else
        {
            return 0.0;
        }
    }

    /**
     * (get当日証拠金不足額)<BR>
     * 
     * 該当顧客の当日証拠金不足額を取得する。<BR>
     * 
     * １）　@証拠金Paramsの取得<BR>
     * 　@　@証拠金計算データソースアクセス管理.get証拠金Params( )により、<BR>
     * 　@　@営業日[T+0]に証拠金不足メールとして送信されたデータを取得する。<BR>
     * 
     * 　@　@[引数の設定]<BR>
     * 　@　@　@証券会社コード：　@引数.補助口座.証券会社コード<BR>
     * 　@　@　@部店コード：　@引数.補助口座.get取引店.部店コード<BR>
     * 　@　@　@顧客コード：　@引数.補助口座.getMainAccount( ).口座コード<BR>
     * 　@　@　@計算日：　@引数.営業日[T+0]<BR>
     * 
     * ２）　@営業日[T+0]に証拠金不足が発生していない(または、営業日[T+0]の証拠金不足メ?
     * ルが未送信)の場合<BR>
     * 　@　@　@(get証拠金Params( )の戻り値 == null)、<BR>
     * 　@　@　@0を返却する。<BR>
     * 
     * ３）　@上記以外の場合、<BR>
     * 　@　@　@証拠金Params.翌日受渡額現金の絶対値(Abs(翌日受渡額現金))を返却する。<BR>
     * 
     * 　@
     * @@param l_subAccount - (補助口座)<BR>
     * 
     * 補助口座オブジェクト。<BR>
     * @@param l_datCurrentDate - 営業日[T+0]。
     * @@return double
     * @@roseuid 41402B190246
     */
    private double getCurrentBizDateIfoDepositLackCharge(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datCurrentBizDate)
    {
        IfoDepositParams l_ifoDeposit = getIfoDepositParams(l_subAccount, l_datCurrentBizDate);
        if (l_ifoDeposit != null)
        {
            return Math.abs(l_ifoDeposit.getNetAmoutCash());
        }
        else
        {
            return 0.0;
        }
    }

    private boolean toBoolean(String source)
    {
        return WEB3EnforcementDef.ENFORCEMENT.equals(source) ? true : false;
    }

    private IfoDepositParams getIfoDepositParams(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datBizDate)
    {
        return WEB3IfoDepositPersistentDataManager.getIfoDepositParams(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getWeb3GenBranch().getBranchCode(),
            l_subAccount.getMainAccount().getAccountCode(),
            l_datBizDate);
    }

   /**
     * (is清算値速報受信済)<BR>
     *
     * 清算値速報受信済であるかを判定する。<BR> 
     * 受信済である場合はtrueを、受信済でない場合はfalseを返却する。<BR> 
     *
     *１）　@プロセス管理Paramsの取得 <BR>
     *    　@証拠金計算データソースアクセス管理.getプロセス管理Params( ) <BR>
     *    　@[引数の設定] <BR>
     *    　@プロセスID：　@”0008”(清算値速報受信) <BR>
     *    　@証券会社コード：　@引数.補助口座.証券会社コード <BR>
     *    　@部店コード：　@引数.補助口座.get取引店().部店コード <BR>
     *
     * ２）　@受信済の場合(getプロセス管理Params( )で該当データあり)、trueを返却する。 <BR>
     *
     * ３）　@清算値速報未受信の場合(getプロセス管理Params( )で該当データなし(null))、falseを返却する。<BR> 
     *
     * @@param l_subAccount - 補助口座オブジェクト。
     * @@return boolean 
     */
    private boolean isQuickReportReceived(WEB3GentradeSubAccount l_subAccount)
    {
        //プロセスID
        String l_strProcessId = "0008";
        //証券会社コード
        String l_strInstCode = l_subAccount.getInstitution().getInstitutionCode();
        //部店コード
         String l_strBranCode = l_subAccount.getWeb3GenBranch().getBranchCode();

         //プロセス管理Paramsの取得
         ProcessManagementParams l_params =
         WEB3IfoDepositPersistentDataManager.getProcessManagementParams(
                   l_strProcessId,
                   l_strInstCode,
                   l_strBranCode);

        //清算値速報未受信の場合(getプロセス管理Params( )で該当データなし(null))
        if(l_params == null)
        {
            //falseを返却する。
            return false;
        }
        //受信済の場合(getプロセス管理Params( )で該当データあり)
        else
        {
            //trueを返却する。
            return true;
        }
    }

   /**
     * (is夕場実施)<BR>
     * 夕場実施であるかを判定する。 
     * 夕場実施である場合はtrueを、夕場未実施の場合はfalseを返却する。 
     *
     * １）　@部店オブジェクトの取得 
       *     　@引数.補助口座.get取引店()　@ 
     *
     * ２）　@夕場実施判定 
     *     　@１）で取得した部店.is夕場実施()の戻り値を返却する。 
     *     　@ [is夕場実施()の引数設定] 
     *     　@銘柄タイプ：　@”先物オプション” 
     *
     * @@param l_subAccount - 補助口座オブジェクト。
     * @@return boolean 
     */
    private boolean isEveningSession(WEB3GentradeSubAccount l_subAccount) 
    {

        //部店オブジェクト
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

        //部店.is夕場実施()の戻り値を返却する。
        //[is夕場実施()の引数設定] 
        //銘柄タイプ：　@”先物オプション”
        boolean l_blnEveningSession = false;
        try
        {
            l_blnEveningSession = l_branch.isEveningSessionEnforcemented(ProductTypeEnum.IFO);
        }
        catch(WEB3BaseException be)
        {
            log.error(be.getMessage(), be);
            throw new WEB3BaseRuntimeException(
                be.getErrorInfo(),
                be.getErrorMethod(),
                be.getErrorMessage(),
                be.getException());
        }
        return l_blnEveningSession;
    }

   /**
     * (is証拠金不足額非管理)<BR>
     *
     * 証拠金不足額を管理するかを判定する。<BR> 
     * 証拠金不足額を管理する場合はfalseを、管理しない場合はtrueを返却する。<BR> 
     *
     * １）　@部店オブジェクトの取得 <BR>
     *     　@引数.補助口座.get取引店()<BR>
     *
     * ２）　@１）で取得した部店.is証拠金不足額非管理()の戻り値を返却する。 <BR>
     *
     * @@param l_subAccount - 補助口座オブジェクト。
     * @@return boolean 
     */
    private boolean isLackChargeNonManagement(WEB3GentradeSubAccount l_subAccount)
    {
        //部店オブジェクト
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        
        boolean l_blnManagement = false;
        try
        {
            l_blnManagement = l_branch.isIfodepositLackchargeNonManagement();
        }
        catch(WEB3BaseException be)
        {
            log.error(be.getMessage(), be);
            throw new WEB3BaseRuntimeException(
                be.getErrorInfo(),
                be.getErrorMethod(),
                be.getErrorMessage(),
                be.getException());
        }
        return l_blnManagement;
    }


    /**
      * (is証拠金SQ日銘柄ポジション非計上)<BR>
      * 
      * SQ日銘柄をポジションとして計上するかを判定する。<BR>
      * 
      * 計上しない場合はtrueを、計上する場合はfalseを返却する。<BR> 
      * ※計上する＝false、計上しない＝trueなので注意すること<BR>
      * 
      * １）　@部店オブジェクトの取得<BR>
      * 　@
      *   引数.補助口座.get取引店()　@<BR>
      * 
      * ２）　@証拠金SQ日銘柄ポジション非計上の判定<BR> 
      *   １）で取得した部店から「証拠金SQ日銘柄ポジション非計上」を検索する。<BR>
      * 
      *   ２）-１　@「計上しない」場合、trueを返却する。<BR>   
      *   ２）-２　@上記以外の場合、trueを返却する。<BR>    
      *
      * @@param l_subAccount - 補助口座オブジェクト。
      * @@return boolean 
      */
     private boolean isIfodepositNonCalcSqProductFlag(WEB3GentradeSubAccount l_subAccount)
     {
         // 部店オブジェクトの取得
         WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
         
         BranchPreferencesRow l_branchPreferencesRow = null;
         try
         {
             // 証拠金SQ日銘柄ポジション非計上を検索
             l_branchPreferencesRow =
                 BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                     l_branch.getBranchId(),
                     WEB3BranchPreferencesNameDef.IFODEPOSIT_NON_CALC_SQ_PRODUCT,
                     1);
         }
         catch (DataNetworkException l_ex)
         {
             log.error(l_ex.getMessage(), l_ex);
             throw new WEB3BaseRuntimeException
                 (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName(),
                 l_ex.getMessage(),
                 l_ex);
         }
         catch (DataQueryException l_ex)
         {
             log.error(l_ex.getMessage(), l_ex);
             throw new WEB3BaseRuntimeException
                 (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName(),
                 l_ex.getMessage(),
                 l_ex);
         }
         
         // 「計上しない」場合、trueを返却する。
         if (l_branchPreferencesRow != null && 
               WEB3IfodepositNonCalcSqProductDef.NON_CALC.equals(l_branchPreferencesRow.getValue()))
         {
             return true;
         }
         // 上記以外の場合、falseを返却する。
         else
         {
             return false;
         }
     }
}
@
