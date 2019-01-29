head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformConditionRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取報・取残電子交付・特定口座登録伝票(WEB3InformHostConditionRegVoucher.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 肖志偉 (中訊) 新規作成 モデル113 DB変更仕様016
Revision History : 2007/10/23 張騰宇 (中訊) モデル121
*/
package webbroker3.inform;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (取報・取残電子交付・特定口座登録伝票)<BR>
 * 取報・取残電子交付・特定口座登録伝票<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3InformConditionRegVoucher
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformConditionRegVoucher.class);

    /**
     * (取報・取残電子交付・特定口座登録行)<BR>
     * 取報・取残電子交付・特定口座登録行オブジェクト
     */
    private HostConditionRegVoucherParams hostConditionRegVoucherParams;

    /**
     * (各種連絡行)<BR>
     * 各種連絡行オブジェクト
     */
    private VariousInformParams variousInformParams;

    /**
     * (取報・取残電子交付・特定口座登録)<BR>
     * コンストラクタ <BR>
     * <BR>
     * １）行オブジェクトをthis.取報・取残電子交付・特定口座登録行にセットする。<BR>
     * <BR>
     * ２）伝票識別コード != null の場合、 <BR>
     * 　@this.取報・取残電子交付・特定口座登録行に伝票識別コードをセットする。<BR>
     * @@param l_hostConditionRegVoucherParams - (取報・取残電子交付・特定口座登録行)<BR>
     * 取報・取残電子交付・特定口座登録行オブジェクト<BR>
     * @@param l_strVoucherRequestNumber - (伝票識別コード)<BR>
     * 伝票識別コード
     */
    public WEB3InformConditionRegVoucher(
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams,
        String l_strVoucherRequestNumber)
    {
        //１）行オブジェクトをthis.取報・取残電子交付・特定口座登録行にセットする。
        this.hostConditionRegVoucherParams = l_hostConditionRegVoucherParams;

        //２）伝票識別コード != null の場合、
        if (l_strVoucherRequestNumber != null)
        {
            //this.取報・取残電子交付・特定口座登録行に伝票識別コードをセットする
            this.hostConditionRegVoucherParams.setOrderRequestNumber(l_strVoucherRequestNumber);
        }
    }

    /**
     * (取報・取残電子交付・特定口座登録)<BR>
     * コンストラクタ <BR>
     * <BR>
     * 行オブジェクトをthis.各種連絡行にセットする。<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行オブジェクト
     */
    public WEB3InformConditionRegVoucher(VariousInformParams l_variousInformParams)
    {
        //行オブジェクトをthis.各種連絡行にセットする。
        this.variousInformParams = l_variousInformParams;
    }

    /**
     * (save取報・取残電子交付・特定口座登録伝票キュー)<BR>
     * 取報・取残電子交付・特定口座登録（GI311）キューテーブルにレコードを作成する。 <BR>
     * <BR>
     * DB更新仕様「取報・取残電子交付・特定口座登録（GI311）キューテーブル.xls」<BR>
     * @@throws WEB3BaseException
     */
    public void saveHostConditionRegVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveHostConditionRegVoucher()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DB更新仕様「取報・取残電子交付・特定口座登録（GI311）キューテーブル.xls」
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                this.hostConditionRegVoucherParams;

            //データコード
            //取報・取残電子交付・特定口座登録：”GI843”
            l_hostConditionRegVoucherParams.setRequestCode(
                WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);

            //扱者コード
            //null
            l_hostConditionRegVoucherParams.setTraderCode(null);

            //識別コード（口座開設見込客）
            //ALL9 (9999999999999)
            l_hostConditionRegVoucherParams.setAccOpenRequestNumber("9999999999999");

            //伝票通番
            //0
            l_hostConditionRegVoucherParams.setSerialNo("0");

            //電子交付　@投信運用報告書
            //null
            l_hostConditionRegVoucherParams.setInvEReportDiv(null);

            //電子交付　@分配金・償還金
            //null
            l_hostConditionRegVoucherParams.setRefundEReportDiv(null);

            //処理区分
            //0：未処理
            l_hostConditionRegVoucherParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            //送信日時
            //null
            l_hostConditionRegVoucherParams.setSendTimestamp(null);

            //作成日時
            //処理日時
            l_hostConditionRegVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //更新日時
            //処理日時
            l_hostConditionRegVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostConditionRegVoucherParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete取報・取残電子交付・特定口座登録伝票キュー)<BR>
     * 取報・取残電子交付・特定口座登録（GI311）キューテーブルのレコードを削除する。<BR>
     * <BR>
     * ［削除条件］ <BR>
     * 識別コード = 各種連絡行.get伝票識別コード（） and <BR>
     * データコード = "GI843" and <BR>
     * 証券会社コード = 各種連絡行.get証券会社コード（） and <BR>
     * 部店コード = 各種連絡行.get部店コード（） and <BR>
     * 顧客コード = 各種連絡行.get顧客コード（） <BR>
     * @@throws WEB3BaseException
     */
    public void deleteHostConditionRegVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteHostConditionRegVoucher()";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append("order_request_number = ? ");
        l_sbWhere.append("and request_code = ? ");
        l_sbWhere.append("and institution_code = ? ");
        l_sbWhere.append("and branch_code = ? ");
        l_sbWhere.append("and account_code = ? ");

        List l_lisWhereValues = new ArrayList();

        //識別コード = 各種連絡行.get伝票識別コード（）
        l_lisWhereValues.add(this.variousInformParams.getOrderRequestNumber());

        //データコード = "GI843"
        l_lisWhereValues.add(WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);

        //証券会社コード = 各種連絡行.get証券会社コード（）
        l_lisWhereValues.add(this.variousInformParams.getInstitutionCode());

        //部店コード = 各種連絡行.get部店コード（）
        l_lisWhereValues.add(this.variousInformParams.getBranchCode());

        //顧客コード = 各種連絡行.get顧客コード（）
        l_lisWhereValues.add(this.variousInformParams.getAccountCode());

        Object[] l_objWhereValues = new Object[l_lisWhereValues.size()];
        l_lisWhereValues.toArray(l_objWhereValues);
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(
                HostConditionRegVoucherRow.TYPE,
                l_sbWhere.toString(),
                l_objWhereValues);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (is伝票作成)<BR>
     * this.取報・取残電子交付・特定口座登録行の以下項目が全てnullの場合、<BR>
     * falseを返却する。 <BR>
     * それ以外の場合はtrueを返却する。 <BR>
     * <BR>
     * １）　@this.取報・取残電子交付・特定口座登録行の以下項目が全てnullの場合、<BR>
     * falseを返却する。 <BR>
     * <BR>
     * 　@－取引残高報告書　@定期 <BR>
     * 　@－取引残高報告書　@電子交付（都度） <BR>
     * 　@－取引残高報告書　@預り証 <BR>
     * 　@－取引残高報告書　@計算書 <BR>
     * 　@－電子交付　@取引報告書 <BR>
     * 　@－電子交付　@投信運用報告書 <BR>
     * 　@－電子交付　@分配金・償還金 <BR>
     * 　@－（現物）特定口座　@今回 <BR>
     * 　@－（現物）特定口座　@次回 <BR>
     * 　@－（現物）特定口座　@開設日 <BR>
     * 　@－（信用）特定口座　@今回 <BR>
     * 　@－（信用）特定口座　@次回 <BR>
     * 　@－（信用）特定口座　@開設日 <BR>
     * 　@－特定管理口座 <BR>
     * <BR>
     * ２）　@１）に該当しない場合trueを返却する。<BR>
     * @@return boolean
     */
    public boolean isVoucherMake()
    {
        final String STR_METHOD_NAME = "isVoucherMake()";
        log.entering(STR_METHOD_NAME);

        //１）　@this.取報・取残電子交付・特定口座登録行の以下項目が全てnullの場合
        //falseを返却する。
        if (this.hostConditionRegVoucherParams.getPosReportTermDiv() == null
            && this.hostConditionRegVoucherParams.getPosReportCycleDiv() == null
            && this.hostConditionRegVoucherParams.getPosReportCertifDepoDiv() == null
            && this.hostConditionRegVoucherParams.getPosReportAccStateDiv() == null
            && this.hostConditionRegVoucherParams.getTradingEReportDiv() == null
            && this.hostConditionRegVoucherParams.getInvEReportDiv() == null
            && this.hostConditionRegVoucherParams.getRefundEReportDiv() == null
            && this.hostConditionRegVoucherParams.getEquityTaxDiv() == null
            && this.hostConditionRegVoucherParams.getEquityTaxDivNext() == null
            && this.hostConditionRegVoucherParams.getEquitySpAccOpenDat() == null
            && this.hostConditionRegVoucherParams.getMarginTaxDiv() == null
            && this.hostConditionRegVoucherParams.getMarginTaxDivNext() == null
            && this.hostConditionRegVoucherParams.getMarginSpAccOpenDat() == null
            && this.hostConditionRegVoucherParams.getSpMngAccOpenDiv() == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            //２）　@１）に該当しない場合trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
}

@
