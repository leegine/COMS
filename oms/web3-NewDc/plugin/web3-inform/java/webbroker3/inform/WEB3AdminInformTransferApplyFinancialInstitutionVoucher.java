head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformTransferApplyFinancialInstitutionVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 振替申込（銀行）伝票(WEB3AdminInformTransferApplyFinancialInstitutionVoucher.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.056
Revision History    : 2007/06/22 佐藤(SCS) 変更 モデルNo.093
Revision History    : 2007/07/11 佐藤(SCS) 変更 モデルNo.101
*/

package webbroker3.inform;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.HostBankTransVoucherDao;
import webbroker3.accountopen.data.HostBankTransVoucherParams;
import webbroker3.accountopen.data.HostBankTransVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3TransferRangeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (振替申込（銀行）伝票)<BR>
 * 振替申込（銀行）伝票クラス
 *
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformTransferApplyFinancialInstitutionVoucher
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher.class);

    /**
     * (各種連絡行)<BR>
     * 各種連絡行オブジェクト
     */
    private VariousInformParams variousInformParams;

    /**
     * @@roseuid 4663A9D50261
     */
    public WEB3AdminInformTransferApplyFinancialInstitutionVoucher()
    {

    }

    /**
     * (振替申込（銀行）伝票)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 行オブジェクトをthis.各種連絡行にセットする。<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行オブジェクト
     * @@roseuid 464D3DD001A4
     */
    public WEB3AdminInformTransferApplyFinancialInstitutionVoucher(VariousInformParams l_variousInformParams)
    {
        // １）行オブジェクトをthis.各種連絡行にセットする。
        this.variousInformParams = l_variousInformParams;
    }

    /**
     * (振替申込（銀行）伝票)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）行オブジェクトをthis.各種連絡行にセットする。<BR>
     * <BR>
     * ２）各種連絡識別コード != null の場合、<BR>
     * 　@this.各種連絡行に各種連絡識別コードをセットする。<BR>
     * <BR>
     * ３）伝票識別コード != null の場合、<BR>
     * 　@this.各種連絡行に伝票識別コードをセットする。<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行オブジェクト
     * @@param l_strVoucherRequestNumber - (伝票識別コード)<BR>
     * 伝票識別コード
     * @@param l_strVariousInformRequestNumber - (各種連絡識別コード)<BR>
     * 各種連絡識別コード
     * @@roseuid 464AA1B60059
     */
    public WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
        VariousInformParams l_variousInformParams,
        String l_strVoucherRequestNumber,
        String l_strVariousInformRequestNumber)
    {
        // １）行オブジェクトをthis.各種連絡行にセットする。
        this.variousInformParams = l_variousInformParams;

        // ２）各種連絡識別コード != null の場合、
        // 　@this.各種連絡行に各種連絡識別コードをセットする。
        if (l_strVariousInformRequestNumber != null)
        {
            this.variousInformParams.setRequestNumber(l_strVariousInformRequestNumber);
        }

        // ３）伝票識別コード != null の場合、
        // 　@this.各種連絡行に伝票識別コードをセットする。
        if (l_strVoucherRequestNumber != null)
        {
            this.variousInformParams.setOrderRequestNumber(l_strVoucherRequestNumber);
        }
    }

    /**
     * (save銀行登録伝票キュー)<BR>
     * 振替申込（銀行）伝票（G26）キューテーブルにレコードを作成する。<BR>
     * <BR>
     * DB更新仕様「振替申込（銀行）伝票（G26）キューテーブル.xls」<BR>
     * @@throws WEB3BaseException
     * @@roseuid 464AA4CF002F
     */
    public void saveBankRegistVoucherHost() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveBankRegistVoucherHost()";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            HostBankTransVoucherParams l_hostBankTransVoucherParams = new HostBankTransVoucherParams();

            // 識別コード（採番した値）
//            l_hostBankTransVoucherParams.setOrderRequestNumber(
//                WEB3StringTypeUtility.formatNumber(HostBankTransVoucherDao.newPkValue()));
            
            // 伝票識別コードの更新を変更 2007.06.22 SCS佐藤------------- Start
            l_hostBankTransVoucherParams.setOrderRequestNumber(variousInformParams.getOrderRequestNumber());
            //--------------------------------------------------------- end

            // データコード(振替申込（銀行）：”GI823”)
            l_hostBankTransVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK);

            // 証券会社コード(各種連絡Params.証券会社コード)
            l_hostBankTransVoucherParams.setInstitutionCode(variousInformParams.getInstitutionCode());

            // 部店コード(各種連絡Params.部店コード)
            l_hostBankTransVoucherParams.setBranchCode(variousInformParams.getBranchCode());

            // 顧客コード(各種連絡Params.顧客コード)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                    variousInformParams.getInstitutionCode(),
                    variousInformParams.getBranchCode(),
                    variousInformParams.getAccountCode());

            l_hostBankTransVoucherParams.setAccountCode(l_mainAccount.getAccountCode());

            // 扱者コード(各種連絡Params.扱者コード（SONAR）)
            l_hostBankTransVoucherParams.setTraderCode(variousInformParams.getSonarTraderCode());

            // 識別コード（口座開設見込客）(各種連絡Params.識別コード)
//            l_hostBankTransVoucherParams.setAccOpenRequestNumber(variousInformParams.getRequestNumber());

            // ----------- 識別コード（口座開設見込客）にALL9をセットするよう修正--------SCS佐藤 ----------
            l_hostBankTransVoucherParams.setAccOpenRequestNumber("9999999999999");
            // ----------------------------- end ------------------------------------------------------

            // 伝票通番(0)
            l_hostBankTransVoucherParams.setSerialNo("0");

            // 振出範囲(各種連絡Params.区分２)
            l_hostBankTransVoucherParams.setTransferRange(variousInformParams.getExtDiv2());

            // 指定商品コード(各種連絡Params.区分３)
            l_hostBankTransVoucherParams.setProductTypeCodeSpec(variousInformParams.getExtDiv3());

            // 指定銘柄コード(各種連絡Params.銘柄コード)
            l_hostBankTransVoucherParams.setProductCodeSpec(variousInformParams.getFundCode());

            // 登録区分(各種連絡Params.区分４)
            l_hostBankTransVoucherParams.setRegistDiv(variousInformParams.getExtDiv4());

            // 振替区分(各種連絡Params.区分５)
            l_hostBankTransVoucherParams.setTransferDiv(variousInformParams.getExtDiv5());

            // 振替手数料区分 (各種連絡Params.区分６)
            l_hostBankTransVoucherParams.setTransCommission(variousInformParams.getExtDiv6());

            // 取扱区分(各種連絡Params.区分７)
            l_hostBankTransVoucherParams.setTransDealDiv(variousInformParams.getExtDiv7());

            // 振込先銀行コード(各種連絡Params.コード３)
            l_hostBankTransVoucherParams.setFinInstitutionCode(variousInformParams.getExtCode3());

            // 振込先銀行支店コード(各種連絡Params.コード４)
            l_hostBankTransVoucherParams.setFinBranchCode(variousInformParams.getExtCode4());

            // 預金種類 (各種連絡Params.区分１)
            l_hostBankTransVoucherParams.setFinSaveDiv(variousInformParams.getExtDiv1());

            // 口座番号(各種連絡Params.テキスト９)
            l_hostBankTransVoucherParams.setFinAccountNo(variousInformParams.getExtText9());

            // 口座名義(各種連絡Params.テキスト１を半角カナに編集した値)
            l_hostBankTransVoucherParams.setFinAccountName(
                WEB3StringTypeUtility.to1byteKana(variousInformParams.getExtText1()));

            // 処理区分(0：未処理)
            l_hostBankTransVoucherParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            // 送信日時 (null)
            l_hostBankTransVoucherParams.setSendTimestamp(null);

            // 作成日時(処理日時)
            l_hostBankTransVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            // 更新日時(処理日時)
            l_hostBankTransVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            l_queryProcessor.doInsertQuery(l_hostBankTransVoucherParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
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
     * (delete銀行登録伝票キュー)<BR>
     * 振替申込（銀行）伝票（G26）キューテーブルのレコードを削除する。<BR>
     * <BR>
     * ［削除条件］<BR>
     * 識別コード = 各種連絡行.get伝票識別コード（） and<BR>
     * データコード = "GI823" and<BR>
     * 証券会社コード = 各種連絡行.get証券会社コード（） and<BR>
     * 部店コード = 各種連絡行.get部店コード（） and<BR>
     * 顧客コード = 各種連絡行.get顧客コード（）<BR>
     * @@throws WEB3BaseException
     * @@roseuid 464D4A9A0389
     */
    public void deleteBankRegistVoucherHost() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteBankRegistVoucherHost()";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" order_request_number = ? ");
            l_sbQuery.append(" and request_code = ? ");
            l_sbQuery.append(" and institution_code = ? ");
            l_sbQuery.append(" and branch_code = ? ");
            l_sbQuery.append(" and account_code = ? ");

            Object[] l_objValues = {
            	variousInformParams.getOrderRequestNumber(),
                WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK,
                variousInformParams.getInstitutionCode(),
                variousInformParams.getBranchCode(),
                variousInformParams.getAccountCode()};

            l_queryProcessor.doDeleteAllQuery(HostBankTransVoucherRow.TYPE,
                l_sbQuery.toString(),
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
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
     * (validate金融機@関情報)<BR>
     * 振替申込（銀行）伝票を作成する際のパラメータチェックを行う。<BR>
     * <BR>
     * １）引数:連絡情報.コード３ == null の場合、例外をスロー<BR>
     * 「金融機@関コード未指定エラー」<BR>
     * 　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_02788<BR>
     * <BR>
     * ２）引数:連絡情報.コード４ == null の場合、例外をスロー<BR>
     * 「支店コード未指定エラー」<BR>
     * 　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_02789<BR>
     * <BR>
     * ３）引数:連絡情報.区分２ = 4:積立個別口座又は個別銘柄　@かつ<BR>
     * 　@引数:連絡情報.区分３ == null の場合、例外をスロー<BR>
     * <BR>
     * 　@「商品区分エラー」<BR>
     * 　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_02790<BR>
     * <BR>
     * ４）引数:連絡情報.区分２ != 4:積立個別口座又は個別銘柄　@かつ<BR>
     * 　@引数:連絡情報.区分３ != null の場合、例外をスロー<BR>
     * <BR>
     * 　@「商品区分エラー」<BR>
     * 　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_02790<BR>
     * @@param l_informInfoUnit - (連絡情報)<BR>
     * 連絡情報
     * @@throws WEB3BaseException
     * @@roseuid 4650F613031B
     */
    public void validateFinancialInstitutionInfo(WEB3InformDetailInfoUnit l_informInfoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateFinancialInstitutionInfo(WEB3InformDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_informInfoUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １）引数:連絡情報.コード３ == null の場合、例外をスロー
        // 「金融機@関コード未指定エラー」
        if (l_informInfoUnit.code3 == null)
        {
            log.debug(STR_METHOD_NAME + "金融機@関コード未指定エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02788,
                this.getClass().getName() + STR_METHOD_NAME,
                "金融機@関コード未指定エラー。");
        }

        // ２）引数:連絡情報.コード４ == null の場合、例外をスロー
        // 「支店コード未指定エラー」
        if (l_informInfoUnit.code4 == null)
        {
            log.debug(STR_METHOD_NAME + "支店コード未指定エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02789,
                this.getClass().getName() + STR_METHOD_NAME,
                "支店コード未指定エラー。");
        }

        // ３）引数:連絡情報.区分２ = 4:積立個別口座又は個別銘柄　@かつ
        // 　@引数:連絡情報.区分３ == null の場合、例外をスロー
        // 「商品区分エラー」
        if (WEB3TransferRangeDef.RESERVE_INDIVIDUAL_ACCOUNT.equals(l_informInfoUnit.div2)
            && l_informInfoUnit.div3 == null)
        {
            log.debug(STR_METHOD_NAME + "商品区分エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02790,
                this.getClass().getName() + STR_METHOD_NAME,
                "商品区分エラー。");
        }

        // ４）引数:連絡情報.区分２ != 4:積立個別口座又は個別銘柄　@かつ
        // 　@引数:連絡情報.区分３ != null の場合、例外をスロー
        // 「商品区分エラー」
        if (!WEB3TransferRangeDef.RESERVE_INDIVIDUAL_ACCOUNT.equals(l_informInfoUnit.div2)
            && l_informInfoUnit.div3 != null)
        {
            log.debug(STR_METHOD_NAME + "商品区分エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02790,
                this.getClass().getName() + STR_METHOD_NAME,
                "商品区分エラー。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
