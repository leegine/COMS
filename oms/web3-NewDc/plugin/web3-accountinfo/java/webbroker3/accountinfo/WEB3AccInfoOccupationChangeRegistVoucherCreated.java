head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoOccupationChangeRegistVoucherCreated.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 職業変更申込伝票作成(WEB3AccInfoOccupationChangeRegistVoucherCreated.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/18 徐宏偉 (中訊) 新規作成 モデル160 , ＤＢ更新仕様041
Revision History : 2007/01/23 徐宏偉 (中訊) ＤＢ更新仕様045
Revision History : 2007/02/10 謝旋 (中訊) モデル188
Revision History : 2007/03/05 吉麗ナ (中訊) ＤＢ更新仕様047
*/
package webbroker3.accountinfo;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountopen.data.TradeConditionVoucherDao;
import webbroker3.accountopen.data.TradeConditionVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DataClassDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3RegistDivDef;
import webbroker3.common.define.WEB3ReportDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (職業変更申込伝票作成)<BR>
 * 職業変更申込伝票作成クラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoOccupationChangeRegistVoucherCreated
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoOccupationChangeRegistVoucherCreated.class);

    /**
     * (顧客（全部店分）Row)<BR>
     * 顧客（全部店分）Rowオブジェクト<BR>
     */
    private MainAccountAllRow mainAccountAllRow;

    /**
     * (職業変更申込伝票作成)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3AccInfoOccupationChangeRegistVoucherCreated()
    {

    }

    /**
     * (職業変更申込伝票作成)<BR>
     * 顧客（全部店分）Rowを属性にセットする。<BR>
     * <BR>
     * @@param l_mainAccountAllRow - 顧客（全部店分）Rowオブジェクト
     */
    public WEB3AccInfoOccupationChangeRegistVoucherCreated(
        MainAccountAllRow l_mainAccountAllRow)
    {
        final String STR_METHOD_NAME =
            "WEB3AccInfoOccupationChangeRegistVoucherCreated(MainAccountAllRow)";
        log.entering(STR_METHOD_NAME);

        this.mainAccountAllRow = l_mainAccountAllRow;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create職業変更伝票)<BR>
     * 職業変更に伴う取残・電子交付・特定口座伝票（GI844）を作成する。<BR>
     * <BR>
     * <BR>
     * ＊伝票作成項目は「伝票作成_取残・電子交付・特定口座伝票（GI311）テーブル.xls」参照<BR>
     * <BR>
     * @@param l_lngChangeRegistID - 変更申込ID<BR>
     * @@param l_strOccupationCode - 職業コード<BR>
     * @@param l_mainAccount - 顧客オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    public void createOccupationChangeVoucher(
        long l_lngChangeRegistID, String l_strOccupationCode , MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createOccupationChangeVoucher(long, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            MainAccountRow l_mainAccountRow = ((WEB3GentradeMainAccount) l_mainAccount).getMainAccountRow();
            
            TradeConditionVoucherParams l_tradeConditionVoucherParams =
                new TradeConditionVoucherParams();
            //識別コードorder_request_number   VARCHAR29   NotNull （採番した値）
            l_tradeConditionVoucherParams.setOrderRequestNumber(
                WEB3StringTypeUtility.formatNumber(TradeConditionVoucherDao.newPkValue()));

            //データコードrequest_code  VARCHAR25   NotNull 取残・電子交付・特定口座：”GI844”
            l_tradeConditionVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_TRADE_CONDITION);

            //証券会社コードinstitution_code VARCHAR23   NotNull 顧客マスター行.証券会社コード
            l_tradeConditionVoucherParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());

            //部店コードbranch_code    VARCHAR23   NotNull 顧客マスター行.部店コード
            l_tradeConditionVoucherParams.setBranchCode(l_mainAccountRow.getBranchCode());

            //顧客コードaccount_code   VARCHAR27   NotNull 顧客マスター行.口座コード
            l_tradeConditionVoucherParams.setAccountCode(l_mainAccountRow.getAccountCode());

            //扱者コードtrader_code    VARCHAR25   Null    顧客マスター行.扱者コード（SONAR）
            l_tradeConditionVoucherParams.setTraderCode(
                l_mainAccountRow.getSonarTraderCode());

            //識別コード（口座開設見込客）acc_open_request_number   VARCHAR213  NotNull 0
            l_tradeConditionVoucherParams.setAccOpenRequestNumber("0");

            //伝票通番serial_no   VARCHAR23   NotNull 0
            l_tradeConditionVoucherParams.setSerialNo("0");

            //デ－タ種別data_class VARCHAR22   Null    02：変更コード
            l_tradeConditionVoucherParams.setDataClass(WEB3DataClassDef.CHANGE_RECORD);

            //電子交付    取引報告書   trading_e_report_div  null
            l_tradeConditionVoucherParams.setTradingEReportDiv(null);

            //    投信運用報告書 inv_e_report_div  null
            l_tradeConditionVoucherParams.setInvEReportDiv(null);

            //    分配金・償還金 refund_e_report_div VARCHAR21   Null    ０：非承諾
            l_tradeConditionVoucherParams.setRefundEReportDiv(WEB3ReportDivDef.NOT_ACCEPT);

            //    予備1 e_report_div1   VARCHAR21   Null    null
            l_tradeConditionVoucherParams.setEReportDiv1(null);

            //    予備2 e_report_div2   VARCHAR21   Null    null
            l_tradeConditionVoucherParams.setEReportDiv2(null);

            //    予備3 e_report_div3   VARCHAR21   Null    null
            l_tradeConditionVoucherParams.setEReportDiv3(null);

            //取残報告書   定期  pos_report_term_div null
            l_tradeConditionVoucherParams.setPosReportTermDiv(null);

            //    都度  pos_report_cycle_div  null
            l_tradeConditionVoucherParams.setPosReportCycleDiv(null);

            //    預り証 pos_report_certif_depo_div  null
            l_tradeConditionVoucherParams.setPosReportCertifDepoDiv(null);

            //    計算書 pos_report_acc_state_div    null
            l_tradeConditionVoucherParams.setPosReportAccStateDiv(null);

            //現物株式特定口座区分equity_tax_div     null
            l_tradeConditionVoucherParams.setEquityTaxDiv(null);

            //現物株式特定口座区分（次年）equity_tax_div_next  null
            l_tradeConditionVoucherParams.setEquityTaxDivNext(null);

            //現物株式特定口座開設日equity_sp_acc_open_dat null
            l_tradeConditionVoucherParams.setEquitySpAccOpenDat(null);

            //信用取引特定口座区分margin_tax_div   null
            l_tradeConditionVoucherParams.setMarginTaxDiv(null);

            //信用取引特定口座区分（次年）margin_tax_div_next null
            l_tradeConditionVoucherParams.setMarginTaxDivNext(null);

            //信用取引特定口座開設日margin_sp_acc_open_dat null
            l_tradeConditionVoucherParams.setMarginSpAccOpenDat(null);

            //特定管理口座開設区分sp_mng_acc_open_div    null
            l_tradeConditionVoucherParams.setSpMngAccOpenDiv(null);

            //処理区分status  VARCHAR21   NULL    0：未処理
            l_tradeConditionVoucherParams.setStatus(WEB3StatusDef.NOT_DEAL);

            //送信日時send_timestamp  DATE    NULL    null
            l_tradeConditionVoucherParams.setSendTimestamp(null);

            //作成日時created_timestamp   DATE    NotNull 処理日時
            l_tradeConditionVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //更新日時last_updated_timestamp  DATE    NotNull 処理日時
            l_tradeConditionVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //職業コードoccupation_div VARCHAR22   NULL    引数：職業コード
            l_tradeConditionVoucherParams.setOccupationDiv(l_strOccupationCode);

            //登録区分regist_div  VARCHAR21   NULL    1：変更
            l_tradeConditionVoucherParams.setRegistDiv(WEB3RegistDivDef.CHANGE);

            //携帯番号.勤務先情報変更申込ＩＤmobile_office_info_regist_id  引数：変更申込ID
            l_tradeConditionVoucherParams.setMobileOfficeInfoRegistId(l_lngChangeRegistID);

            WEB3DataAccessUtility.insertRow(l_tradeConditionVoucherParams);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * is伝票作成<BR>
     * 伝票作成の可否を判別する。<BR>
     * <BR>
     * 1） 口座情報マスタ == null　@且つ　@変更申込情報.職業区分  != null　@または <BR>
     *      口座情報マスタ != null　@且つ (口座情報マスタ行.職業 == null 且つ 変更申込情報.職業区分 != null) または  <BR>
     *      口座情報マスタ() != null　@且つ (口座情報マスタ行.職業 != null 且つ !口座情報マスタ行.職業. <BR>
     *      equals(変更申込情報.職業区分)) <BR>
     *<BR>
     *      の場合、trueを返却 <BR>
     *<BR>
     * ２）　@１）以外、falseを返却<BR>
     * <BR>
     * @@param l_mobileOfficeInfoRegistParams 携帯番号.勤務先情報変更申込params<BR>
     * @@param l_accInfoMaster 口座情報マスタ
     */
    public static boolean isVoucherCreated(
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams ,
        WEB3AccInfoMaster l_accInfoMaster)
    {
        //1） 口座情報マスタ == null　@且つ　@変更申込情報.職業区分  != null
        if (l_accInfoMaster == null && l_mobileOfficeInfoRegistParams.getOccupationDiv() != null) 
        {
            return true;
        }

        if (l_accInfoMaster != null)
        {
            String l_strOccupationDiv = ((AccountInfoMstParams)l_accInfoMaster.getDataSourceObject()).getOccupationDiv();

            //口座情報マスタ != null　@且つ (口座情報マスタ行.職業 == null 且つ 変更申込情報.職業区分 != null)
            if (l_strOccupationDiv == null && l_mobileOfficeInfoRegistParams.getOccupationDiv() != null)
            {
                return true;
            }
            
            //口座情報マスタ() != null　@且つ (口座情報マスタ行.職業 != null 
            //且つ !口座情報マスタ行.職業.equals(変更申込情報.職業区分
            if (l_strOccupationDiv != null && !l_strOccupationDiv.equals(l_mobileOfficeInfoRegistParams.getOccupationDiv())) 
            {
                return true;
            }
        }

        return false;
    }
}
@
