head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoAccopenConditionRegAcceptVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 取報・取残電子交付・特定口座登録伝票(WEB3AccInfoAccopenConditionRegAcceptVoucher.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 張騰宇(中訊) 新規作成 仕様変更モデルNo.279 ＤＢ更新仕様064
*/
package webbroker3.accountinfo;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (取報・取残電子交付・特定口座登録伝票)<BR>
 * 取報・取残電子交付・特定口座登録伝票<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccInfoAccopenConditionRegAcceptVoucher
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoAccopenConditionRegAcceptVoucher.class);

    /**
     * (取報・取残電子交付・特定口座登録Params)<BR>
     * 取報・取残電子交付・特定口座登録行オブジェクト<BR>
     */
    private HostConditionRegVoucherParams hostConditionRegVoucherParams;
    
    /**
     * コンストラクタ <BR>
     * <BR>
     * １）行オブジェクトをthis.取報・取残電子交付・特定口座登録行にセットする。 <BR>
     * <BR>
     * ２）伝票識別コード != null の場合、 <BR>
     * 　@this.取報・取残電子交付・特定口座登録行に伝票識別コードをセットする。 <BR>
     */
    public WEB3AccInfoAccopenConditionRegAcceptVoucher(
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams,
        String l_strOrderRequestNumber)
    {
        //１）行オブジェクトをthis.取報・取残電子交付・特定口座登録行にセットする。
        this.hostConditionRegVoucherParams = l_hostConditionRegVoucherParams;

        //２）伝票識別コード != null の場合、
        //　@this.取報・取残電子交付・特定口座登録行に伝票識別コードをセットする。
        if (l_strOrderRequestNumber != null)
        {
            this.hostConditionRegVoucherParams.setOrderRequestNumber(l_strOrderRequestNumber);
        }
    }

    /**
     * (save取報・取残電子交付・特定口座登録（GI311）キュー)<BR>
     * 取報・取残電子交付・特定口座登録（GI311）キューテーブルの更新を行なう。 <BR>
     * <BR>
     * 　@「電子交付サービス登録・変更_取報・取残電子交付・特定口座登録（GI311）キューテーブル仕様.xls」参照。<BR>
     * @@throws WEB3BaseException
     */
    public void saveHostConditionRegVoucherParams() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveHostConditionRegVoucherParams()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //取報・取残電子交付・特定口座登録（GI311）キューテーブルの更新を行なう。
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams = new HostConditionRegVoucherParams();

            //識別コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setOrderRequestNumber(
                this.hostConditionRegVoucherParams.getOrderRequestNumber());

            //データコード:取報・取残電子交付・特定口座登録：”GI843”
            l_hostConditionRegVoucherParams.setRequestCode(
                WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);

            //証券会社コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setInstitutionCode(
                this.hostConditionRegVoucherParams.getInstitutionCode());

            //部店コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setBranchCode(
                this.hostConditionRegVoucherParams.getBranchCode());

            //顧客コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setAccountCode(
                this.hostConditionRegVoucherParams.getAccountCode());

            //扱者コード:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setTraderCode(
                this.hostConditionRegVoucherParams.getTraderCode());

            //識別コード（口座開設見込客）:ALL9 (9999999999999)
            l_hostConditionRegVoucherParams.setAccOpenRequestNumber("9999999999999");
            
            //伝票通番:0
            l_hostConditionRegVoucherParams.setSerialNo("0");

            //取引残高報告書　@定期:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setPosReportTermDiv(
                this.hostConditionRegVoucherParams.getPosReportTermDiv());

            //取引残高報告書　@電子交付（都度）:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(
                this.hostConditionRegVoucherParams.getPosReportCycleDiv());

            //取引残高報告書　@預り証:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setPosReportCertifDepoDiv(
                this.hostConditionRegVoucherParams.getPosReportCertifDepoDiv());

            //取引残高報告書　@計算書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setPosReportAccStateDiv(
                this.hostConditionRegVoucherParams.getPosReportAccStateDiv());

            //電子交付　@取引報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setTradingEReportDiv(
                this.hostConditionRegVoucherParams.getTradingEReportDiv());

            //電子交付　@投信運用報告書:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setInvEReportDiv(
                this.hostConditionRegVoucherParams.getInvEReportDiv());

            //電子交付　@分配金・償還金:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setRefundEReportDiv(
                this.hostConditionRegVoucherParams.getRefundEReportDiv());

            //（現物）特定口座　@今回:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setEquityTaxDiv(
                this.hostConditionRegVoucherParams.getEquityTaxDiv());

            //（現物）特定口座　@次回:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setEquityTaxDivNext(
                this.hostConditionRegVoucherParams.getEquityTaxDivNext());

            //（現物）特定口座　@開設日:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setEquitySpAccOpenDat(
                this.hostConditionRegVoucherParams.getEquitySpAccOpenDat());

            //（信用）特定口座　@今回:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setMarginTaxDiv(
                this.hostConditionRegVoucherParams.getMarginTaxDiv());

            //（信用）特定口座　@次回:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setMarginTaxDivNext(
                this.hostConditionRegVoucherParams.getMarginTaxDivNext());

            //（信用）特定口座　@開設日:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setMarginSpAccOpenDat(
                this.hostConditionRegVoucherParams.getMarginSpAccOpenDat());

            //特定管理口座:取報・取残電子交付・特定口座登録伝票オブジェクトの同名項目
            l_hostConditionRegVoucherParams.setSpMngAccOpenDiv(
                this.hostConditionRegVoucherParams.getSpMngAccOpenDiv());

            //処理区分:0：未処理
            l_hostConditionRegVoucherParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            //送信日時:null
            l_hostConditionRegVoucherParams.setSendTimestamp(null);

            //作成日時:処理日時
            l_hostConditionRegVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //更新日時:処理日時
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
        log.exiting(STR_METHOD_NAME);
    }
}
@
