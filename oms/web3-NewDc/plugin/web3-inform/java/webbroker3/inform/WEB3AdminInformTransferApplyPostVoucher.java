head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformTransferApplyPostVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 振替申込（郵貯）伝票(WEB3AdminInformTransferApplyPostVoucher.java)
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

import webbroker3.accountopen.data.HostPostalTransVoucherDao;
import webbroker3.accountopen.data.HostPostalTransVoucherParams;
import webbroker3.accountopen.data.HostPostalTransVoucherRow;
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
 * (振替申込（郵貯）伝票)<BR>
 * 振替申込（郵貯）伝票クラス
 */
public class WEB3AdminInformTransferApplyPostVoucher
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformTransferApplyPostVoucher.class);

    /**
     * (各種連絡行)<BR>
     * 各種連絡行オブジェクト
     */
    private VariousInformParams variousInformParams;

    /**
     * @@roseuid 4663A9D501F4
     */
    public WEB3AdminInformTransferApplyPostVoucher()
    {

    }

    /**
     * (振替申込（郵貯）伝票)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 行オブジェクトをthis.各種連絡行にセットする。<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行オブジェクト
     * @@roseuid 464D3E1A0166
     */
    public WEB3AdminInformTransferApplyPostVoucher(VariousInformParams l_variousInformParams)
    {
        // １）行オブジェクトをthis.各種連絡行にセットする。
        this.variousInformParams = l_variousInformParams;
    }

    /**
     * (振替申込（郵貯）伝票)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）行オブジェクトをthis.各種連絡行にセットする。<BR>
     * <BR>
     * ２）各種連絡識別コード != null の場合、<BR>
     * 　@this.各種連絡行に各種連絡識別コードをセットする。<BR>
     * <BR>
     * ３）伝票識別コード != null の場合、<BR>
     * 　@this.各種連絡行に伝票識別コードをセットする。<BR>
     * @@param l_variousInform - (各種連絡行)<BR>
     * 各種連絡行オブジェクト
     * @@param l_strVoucherRequestNumber - (伝票識別コード)<BR>
     * 伝票識別コード
     * @@param l_strVariousInformRequestNumber - (各種連絡識別コード)<BR>
     * 各種連絡識別コード
     * @@roseuid 464AA5EB0011
     */
    public WEB3AdminInformTransferApplyPostVoucher(
        VariousInformParams l_variousInform,
        String l_strVoucherRequestNumber,
        String l_strVariousInformRequestNumber)
    {
        // １）行オブジェクトをthis.各種連絡行にセットする。
        this.variousInformParams = l_variousInform;

        // ２）各種連絡識別コード != null の場合、
        //  this.各種連絡行に各種連絡識別コードをセットする。
        if (l_strVariousInformRequestNumber != null)
        {
            this.variousInformParams.setRequestNumber(l_strVariousInformRequestNumber);
        }

        // ３）伝票識別コード != null の場合、
        //　@this.各種連絡行に伝票識別コードをセットする。

        if (l_strVoucherRequestNumber != null)
        {
            this.variousInformParams.setOrderRequestNumber(l_strVoucherRequestNumber);
        }
    }

    /**
     * (save郵貯登録伝票キュー)<BR>
     * 振替申込（郵貯）伝票（G26）キューテーブルにレコードを作成する。<BR>
     * <BR>
     * DB更新仕様「振替申込（郵貯）伝票（G26）キューテーブル.xls」参照<BR>
     * @@throws WEB3BaseException
     * @@roseuid 464AA5EB0013
     */
    public void savePostRegistVoucherHost() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " savePostRegistVoucherHost()";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            HostPostalTransVoucherParams l_hostPostalTransVoucherParams = new HostPostalTransVoucherParams();

            // 識別コード（採番した値）
//            l_hostPostalTransVoucherParams.setOrderRequestNumber(
//                WEB3StringTypeUtility.formatNumber(HostPostalTransVoucherDao.newPkValue()));
            
            // 伝票識別コードの更新を変更 2007.06.22 SCS佐藤------------- Start
            l_hostPostalTransVoucherParams.setOrderRequestNumber(variousInformParams.getOrderRequestNumber());
            //--------------------------------------------------------- end

            // データコード（振替申込（郵貯）：”GI828”）
            l_hostPostalTransVoucherParams.setRequestCode(WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL);

            // 証券会社コード（各種連絡Params.証券会社コード）
            l_hostPostalTransVoucherParams.setInstitutionCode(this.variousInformParams.getInstitutionCode());

            // 部店コード（各種連絡Params.部店コード）
            l_hostPostalTransVoucherParams.setBranchCode(this.variousInformParams.getBranchCode());

            // 顧客コード（各種連絡Params.顧客コード）
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                    variousInformParams.getInstitutionCode(),
                    variousInformParams.getBranchCode(),
                    variousInformParams.getAccountCode());

            l_hostPostalTransVoucherParams.setAccountCode(l_mainAccount.getAccountCode());

            // 扱者コード（各種連絡Params.扱者コード（SONAR））
            l_hostPostalTransVoucherParams.setTraderCode(this.variousInformParams.getSonarTraderCode());

            // 識別コード（口座開設見込客）（各種連絡Params.識別コード）
//          l_hostPostalTransVoucherParams.setAccOpenRequestNumber(this.variousInformParams.getRequestNumber());
            
            // ----------- 識別コード（口座開設見込客）にALL9をセットするよう修正--------SCS佐藤 ----------
            l_hostPostalTransVoucherParams.setAccOpenRequestNumber("9999999999999");
            // ----------------------------- end ------------------------------------------------------            

            // 伝票通番（0）
            l_hostPostalTransVoucherParams.setSerialNo("0");

            // 振出範囲（各種連絡Params.区分２）
            l_hostPostalTransVoucherParams.setTransferRange(this.variousInformParams.getExtDiv2());

            // 指定商品コード（各種連絡Params.区分３）
            l_hostPostalTransVoucherParams.setProductTypeCodeSpec(this.variousInformParams.getExtDiv3());

            // 指定銘柄コード（各種連絡Params.銘柄コード）
            l_hostPostalTransVoucherParams.setProductCodeSpec(this.variousInformParams.getFundCode());

            // 登録区分（各種連絡Params.区分４）
            l_hostPostalTransVoucherParams.setRegistDiv(this.variousInformParams.getExtDiv4());

            // 振替区分（各種連絡Params.区分５）
            l_hostPostalTransVoucherParams.setTransferDiv(this.variousInformParams.getExtDiv5());

            // 振替手数料区分（各種連絡Params.区分６）
            l_hostPostalTransVoucherParams.setTransCommission(this.variousInformParams.getExtDiv6());

            // 通帳記号（各種連絡Params.テキスト８）
            l_hostPostalTransVoucherParams.setPostalSaveCode(this.variousInformParams.getExtText8());

            // 通帳番号（各種連絡Params.テキスト１０）
            l_hostPostalTransVoucherParams.setPostalSaveNo(this.variousInformParams.getExtText10());

            // 口座名義（各種連絡Params.テキスト１を半角カナに編集した値）
            l_hostPostalTransVoucherParams.setFinAccountName(
                WEB3StringTypeUtility.to1byteKana(this.variousInformParams.getExtText1()));

            // 処理区分（0：未処理）
            l_hostPostalTransVoucherParams.setStatus(WEB3HostStatusDef.NOT_STARTED_PROCESS);

            // 送信日時（null）
            l_hostPostalTransVoucherParams.setSendTimestamp(null);

            // 作成日時（処理日時）
            l_hostPostalTransVoucherParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            // 更新日時（処理日時）
            l_hostPostalTransVoucherParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            l_queryProcessor.doInsertQuery(l_hostPostalTransVoucherParams);
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

    /**
     * (delete郵貯登録伝票キュー)<BR>
     * 振替申込（郵貯）伝票（G26）キューテーブルのレコードを削除する。<BR>
     * <BR>
     * ［削除条件］<BR>
     * 識別コード = 各種連絡行.get伝票識別コード（） and<BR>
     * データコード = "GI828" and<BR>
     * 証券会社コード = 各種連絡行.get証券会社コード（） and<BR>
     * 部店コード = 各種連絡行.get部店コード（） and<BR>
     * 顧客コード = 各種連絡行.get顧客コード（）<BR>
     * @@throws WEB3BaseException
     * @@roseuid 464D4C4C0137
     */
    public void deletePostRegistVoucherHost() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deletePostRegistVoucherHost()";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" order_request_number = ? and ");
            l_sbQuery.append(" request_code = ? and ");
            l_sbQuery.append(" institution_code = ? and ");
            l_sbQuery.append(" branch_code = ? and ");
            l_sbQuery.append(" account_code = ? ");

            Object[] l_objValues = {
                this.variousInformParams.getOrderRequestNumber(),
                WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL,
                this.variousInformParams.getInstitutionCode(),
                this.variousInformParams.getBranchCode(),
                this.variousInformParams.getAccountCode()};

            l_queryProcessor.doDeleteAllQuery(HostPostalTransVoucherRow.TYPE,
                    l_sbQuery.toString(),
                    l_objValues);
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

    /**
     * (validate郵貯情報)<BR>
     * 振替申込（郵貯）伝票を作成する際のパラメータチェックを行う。<BR>
     * <BR>
     * <BR>
     * １）引数:連絡情報.区分２ == A～C の場合、<BR>
     * <BR>
     * 　@１－１）引数:連絡情報.区分３ != null or 引数:連絡情報.コード１ != null<BR>
     * 　@　@　@　@ or 引数:連絡情報.コード２ != null の場合、例外をスロー<BR>
     * <BR>
     * 　@　@　@　@　@『振出範囲エラー』<BR>
     * <BR>
     * ２）引数:連絡情報.区分２ != A～C、 1:全口座 の場合、例外をスロー<BR>
     * <BR>
     * 　@　@『振出範囲エラー』<BR>
     * @@param l_informInfoUnit - (連絡情報)<BR>
     * 連絡情報
     * @@throws WEB3BaseException
     * @@roseuid 4651249900B2
     */
    public void validatePostInfo(WEB3InformDetailInfoUnit l_informInfoUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePostInfo(WEB3InformDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);

        // １）引数:連絡情報.区分２ == A～C の場合、
        if (WEB3TransferRangeDef.SALE_TURNOVER.equals(l_informInfoUnit.div2)
            || WEB3TransferRangeDef.SALE_TURNOVER_2.equals(l_informInfoUnit.div2)
            || WEB3TransferRangeDef.SALE_TURNOVER_3.equals(l_informInfoUnit.div2))
        {
            //１－１）引数:連絡情報.区分３ != null or 引数:連絡情報.コード１ != null
            //　@　@ or 引数:連絡情報.コード２ != null の場合、例外をスロー
            if (l_informInfoUnit.div3 != null
                || l_informInfoUnit.code1 != null
                || l_informInfoUnit.code2 != null)
            {
                log.debug(STR_METHOD_NAME + "振出範囲エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02791,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "振出範囲エラー。");
            }
        }
        else
        {
            // ２）引数:連絡情報.区分２ != A～C、 1:全口座 の場合、例外をスロー
            if (!WEB3TransferRangeDef.ALL_ACCOUNT.equals(l_informInfoUnit.div2))
            {
                log.debug(STR_METHOD_NAME + "振出範囲エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02791,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "振出範囲エラー。");
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
