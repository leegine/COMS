head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金登録伝票作成サービスImpl(WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.056、066、067、070、071、072、074
Revision History    : 2007/06/08 徐宏偉(中訊) 修正 モデルNo.076、077
Revision History    : 2007/06/11 武波(中訊) 修正 モデルNo.078
Revision History    : 2007/06/13 徐宏偉(中訊) 修正 モデルNo.081
Revision History    : 2007/06/14 周墨洋(中訊) 修正 モデルNo.082、085
Revision History    : 2007/06/15 李木子(中訊) 修正 モデルNo.089、No.090
Revision History    : 2007/06/22 佐藤(SCS) 修正 モデルNo.094
Revision History    : 2007/06/22 佐藤(SCS) 修正 モデルNo.096
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.DirectDebitRow;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.inform.WEB3AdminInformTransferApplyFinancialInstitutionVoucher;
import webbroker3.inform.WEB3AdminInformTransferApplyPostVoucher;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.define.WEB3InformFundCodeDef;
import webbroker3.inform.define.WEB3InformProductFirstDef;
import webbroker3.inform.define.WEB3InformRegistDivDef;
import webbroker3.inform.define.WEB3InformVoucherDataSendDivDef;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInfo;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistTransferInfo;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpResponse;
import webbroker3.inform.message.WEB3InformAddInfoUnit;
import webbroker3.inform.message.WEB3InformDetailHeaderInfoUnit;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistRegistVoucherMakeService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (利金・分配金登録伝票作成サービスImpl)<BR>
 * 利金・分配金登録伝票作成サービス実装クラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistRegistVoucherMakeServiceImpl
    implements WEB3AdminInformProfDistRegistVoucherMakeService
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl.class);

    /**
     * @@roseuid 4663A9D5037A
     */
    public WEB3AdminInformProfDistRegistVoucherMakeServiceImpl()
    {

    }

    /**
     * 連絡情報検索サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * <BR>
     * get登録状況検索画面（）<BR>
     * get伝票作成入力画面（）<BR>
     * validate伝票作成確認（）<BR>
     * submit伝票作成完了（）<BR>
     * get振替口座情報参照画面（）<BR>
     * validate口座情報変更確認（）<BR>
     * submit口座情報変更完了（）<BR>
     * validate口座情報取消確認（）<BR>
     * submit口座情報取消完了（）<BR>
     * <BR>
     * 上記メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4642690A0264
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminInformProfDistStatusSearchInputRequest)
        {
            //get登録状況検索画面
            l_response = this.getRegistStatusSearchScreen((WEB3AdminInformProfDistStatusSearchInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherMakeInpRequest)
        {
            //get伝票作成入力画面
            l_response = this.getVoucherMakeInpScreen((WEB3AdminInformProfDistVoucherMakeInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherMakeCnfRequest)
        {
            //validate伝票作成確認
            l_response = this.validateVoucherMakeCnf((WEB3AdminInformProfDistVoucherMakeCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherMakeCmpRequest)
        {
            //submit伝票作成完了
            l_response = this.submitVoucherMakeCmp((WEB3AdminInformProfDistVoucherMakeCmpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherInfoRefRequest)
        {
            //get振替口座情報参照画面
            l_response = this.getTransferAccountInfoRefScreen((WEB3AdminInformProfDistVoucherInfoRefRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherChgCnfRequest)
        {
            //validate口座情報変更確認
            l_response = this.validateAccountInfoChgCnf((WEB3AdminInformProfDistVoucherChgCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherChgCmpRequest)
        {
            //submit口座情報変更完了
            l_response = this.submitAccountInfoChgCmp((WEB3AdminInformProfDistVoucherChgCmpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherCancCnfRequest)
        {
            //validate口座情報取消確認
            l_response = this.validateAccountInfoCancCnf((WEB3AdminInformProfDistVoucherCancCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistVoucherCancCmpRequest)
        {
            //submit口座情報取消完了
            l_response = this.submitAccountInfoCancCmp((WEB3AdminInformProfDistVoucherCancCmpRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get登録状況検索画面)<BR>
     * 利金・分配金登録状況問合せ画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「get登録状況検索画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistStatusSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 46426B50002A
     */
    public WEB3AdminInformProfDistStatusSearchInputResponse getRegistStatusSearchScreen(
        WEB3AdminInformProfDistStatusSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRegistStatusSearchScreen(WEB3AdminInformProfDistStatusSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        // getInstanceFromログイン情報()
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            false);

        //レスポンスデータを生成する。
        WEB3AdminInformProfDistStatusSearchInputResponse l_response =
            (WEB3AdminInformProfDistStatusSearchInputResponse)l_request.createResponse();

        //レスポンス.前営業日 = 現在日時の前営業日日付
        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();
        l_response.previousBizDate = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //レスポンス.前日 = 現在日時の前日
        l_response.previousDate = WEB3DateUtility.toDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get伝票作成入力画面)<BR>
     * 利金・分配金振替口座伝票作成入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「get伝票作成入力画面」 参照。<BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:get伝票作成入力画面<BR>
     * 　@具体位置　@　@　@:1.64　@自動振替登録マスタの値を検索し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@レコードが取得できない場合、例外をスロー<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag:　@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherMakeInpResponse
     * @@roseuid 46426D820143
     */
    public WEB3AdminInformProfDistVoucherMakeInpResponse getVoucherMakeInpScreen(
        WEB3AdminInformProfDistVoucherMakeInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getVoucherMakeInpScreen(WEB3AdminInformProfDistVoucherMakeInpRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        WEB3AdminInformProfDistVoucherMakeInpResponse l_response = null;

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //権限チェックを行う。
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            false);

        //validate注文受付可能( )
        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //リクエストデータの簡易チェック
        l_request.validate();

        //リクエストデータ.登録区分 = 1：新規　@の場合、以下の処理を実行
        if (WEB3InformRegistDivDef.REGISTRATION.equals(l_request.registDiv))
        {
            l_response = (WEB3AdminInformProfDistVoucherMakeInpResponse)l_request.createResponse();
        }
        else if (WEB3InformRegistDivDef.DELETE.equals(l_request.registDiv))
        {
            //リクエストデータ.登録区分 = 3：削除の場合、以下の処理を実行
            //証券会社コードを取得する。
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            //自動振替登録マスタの検索条件文字列を生成する。
            String l_strWhere = this.createQueryString(l_request);

            //リクエストデータから、取得条件のデータリストを生成する
            Object[] l_objWhereValue = this.createQueryDataContainer(l_strInstitutionCode, l_request);

            //自動振替登録マスタの値を検索し、取得する
            //[引数]
            //arg0 ： 自動振替登録マスタRow.TYPE
            //arg1 ： create検索条件文字列の戻り値
            //arg2 ： create検索条件データコンテナの戻り値
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                List l_lisDirectDebitRows =
                    l_queryProcessor.doFindAllQuery(DirectDebitRow.TYPE, l_strWhere, l_objWhereValue);

                if (l_lisDirectDebitRows == null || l_lisDirectDebitRows.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                        WEB3Inform.class.getName() + STR_METHOD_NAME,
                        "該当するデータが存在しません。");
                }

                //利金・分配金・売却代金振込先情報(自動振替登録マスタRow)
                WEB3AdminInformProfDistTransferInfo l_transferInfo =
                    new WEB3AdminInformProfDistTransferInfo((DirectDebitRow)l_lisDirectDebitRows.get(0));

                // 顧客名（漢字）、顧客名（カナ）をセットする処理を追加 2007.06.26 SCS佐藤--------start-----
                this.setAccountInfo(l_strInstitutionCode, l_transferInfo);
                // -----------end-----------------------------------------------------------------------
                
                //レスポンスデータの生成
                l_response = (WEB3AdminInformProfDistVoucherMakeInpResponse)l_request.createResponse();

                //以下の通りに、プロパティをセットする
                //振込先情報 ：生成した利金・分配金・売却代
                l_response.transferInfo = l_transferInfo;
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate伝票作成確認)<BR>
     * 利金・分配金振替口座伝票作成確認画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate伝票作成確認」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherMakeCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 46426F0502D9
     */
    public WEB3AdminInformProfDistVoucherMakeCnfResponse validateVoucherMakeCnf(
        WEB3AdminInformProfDistVoucherMakeCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateVoucherMakeCnf(WEB3AdminInformProfDistVoucherMakeCnfRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        //管理者インスタンスを取得する
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //権限チェックを行う。
        //[引数]
        //機@能カテゴリコード： "A0105"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            true);

        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //リクエストデータの簡易チェックを行う
        l_request.validate();

        //各種連絡インスタンスを生成する
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);

        //各種連絡情報の入力チェックを行う
        l_inform.validateInformDetailInfoUnit();

        //自動振替登録マスターレコードとの整合性チェック
        //[引数]
        //連絡情報： リクエストデータ.連絡情報
        this.validateTransferRegistInfo(l_request.informInfoUnit);

        //銘柄コードの妥当性チェックを行う
        //[引数]
        //連絡情報： リクエストデータ.連絡情報
        this.validateProductCode(l_request.informInfoUnit);

        //付加情報を取得する
        WEB3InformAddInfoUnit l_informAddInfoUnit = l_inform.getInformAddInfoUnit();

        //各種金融機@関情報( )(各種金融機@関情報::各種金融機@関情報)
        WEB3AdminInformProfDistSellTransSrcInfo l_adminInformProfDistSellTransSrcInfo =
            new WEB3AdminInformProfDistSellTransSrcInfo();

        //連絡情報.区分５ = 1：銀行振込 の場合、以下の処理を実行
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_request.informInfoUnit.div5))
        {
            //振替申込（銀行）伝票を作成する際のパラメータチェックを行う。
            //［引数］
            //連絡情報：　@リクエストデータ.連絡情報
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_adminInformTransferApplyFinancialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher();
            l_adminInformTransferApplyFinancialInstitutionVoucher.validateFinancialInstitutionInfo(
                l_request.informInfoUnit);

            //金融機@関（銀行）マスタから金融機@関情報を取得する。
            //[引数]
            //金融機@関コード： 連絡情報.コード３
            //支店コード： 連絡情報.コード４
            //金融機@関情報： 生成した各種金融機@関情報オブジェクト
            this.getFinancialInstitutionInfo(
                l_request.informInfoUnit.code3,
                l_request.informInfoUnit.code4,
                l_adminInformProfDistSellTransSrcInfo);
        }
        else if (WEB3TransferDivDef.POSTAL_TRANSFER.equals(l_request.informInfoUnit.div5))
        {
            //連絡情報.区分５ = 5：郵貯振替 の場合、以下の処理を実行

            //振替申込（郵貯）伝票を作成する際のパラメータチェックを行う。
            //［引数］
            //連絡情報：　@リクエストデータ.連絡情報
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher();
            l_adminInformTransferApplyPostVoucher.validatePostInfo(l_request.informInfoUnit);
        }

        //レスポンスオブジェクトの生成
        WEB3AdminInformProfDistVoucherMakeCnfResponse l_response =
            (WEB3AdminInformProfDistVoucherMakeCnfResponse)l_request.createResponse();

        //付加情報 = get付加情報（）の戻り値
        l_response.informAddUnit = l_informAddInfoUnit;
        //金融機@関情報 = 各種金融機@関情報オブジェクト
        l_response.financialInstitutionInfo = l_adminInformProfDistSellTransSrcInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit伝票作成完了)<BR>
     * 利金・分配金振替口座伝票作成完了画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit伝票作成完了」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherMakeCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 464270740088
     */
    public WEB3AdminInformProfDistVoucherMakeCmpResponse submitVoucherMakeCmp(
        WEB3AdminInformProfDistVoucherMakeCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitVoucherMakeCmp(WEB3AdminInformProfDistVoucherMakeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限チェックを行う。
        //[引数]
        //機@能カテゴリコード： "A0105"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            true);

        //validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかのチェックを行う。
        //[引数]
        //パスワード： リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //validate注文受付可能()
        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate()
        //リクエストデータの簡易チェック
        l_request.validate();

        //各種連絡(各種連絡情報)
        //各種連絡インスタンスを生成する。
        //[引数]
        //連絡情報： リクエストデータ.連絡情報
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);

        //validate各種連絡情報()
        //各種連絡情報の入力チェックを行う
        l_inform.validateInformDetailInfoUnit();

        //validate振込先登録情報(各種連絡情報)
        //自動振替登録マスターレコードとの整合性チェック
        //[引数]
        //連絡情報： リクエストデータ.連絡情報
        this.validateTransferRegistInfo(l_request.informInfoUnit);

        //validate銘柄コード(各種連絡情報)
        //銘柄コードの妥当性チェックを行う。
        //[引数]
        //連絡情報： リクエストデータ.連絡情報
        this.validateProductCode(l_request.informInfoUnit);

        //get新規識別コード(String, String)
        //(連絡管理識別コード採番サービスImpl::get新規識別コード)
        //新規識別コードを取得する。
        //[引数]
        //証券会社コード： リクエストデータ.連絡情報.証券会社コード
        //連絡種別： リクエストデータ.連絡情報.連絡種別
        WEB3InformHostReqOrderNumberManageService l_informHostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);
        String l_strNewOrderRequestCode = l_informHostReqOrderNumberManageService.getNewOrderRequestCode(
            l_request.informInfoUnit.institutionCode,
            l_request.informInfoUnit.informType);

        //get管理者コード( )
        //管理者コードを取得する
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //saveNew各種連絡(String, String, String)(各種連絡::saveNew各種連絡)
        //各種連絡テーブルにデータを登録する。
        //[引数]
        //更新者コード： get管理者コード（）の戻り値
        //識別コード： get新規識別コード（）の戻り値
        //作成状況：　@0:未作成
        l_inform.saveNewInform(
            l_strAdministratorCode,
            l_strNewOrderRequestCode,
            WEB3VoucherCreateStatusDef.NOT_CREATE);

        //is銀行登録伝票(String)(利金・分配金登録伝票作成サービスImpl::is銀行登録伝票)
        //データが銀行登録伝票か判別する。
        //[引数]
        //振替区分：リクエストデータ.連絡情報.区分５
        boolean l_blnFinancialInstitutionPostVoucher =
            this.isBankRegistVoucher(l_request.informInfoUnit.div5);

        //isトリガ発行
        //SONARへトリガを発行できる時間帯かを判定する。
        //[引数]
        //発注条件： 0（DEFAULT）
        boolean l_blnSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

        //get新規識別コード
        //SONAR通知キューに書き込む際に必要な識別コードを採番する。
        //[引数]
        //証券会社コード： リクエストデータ.連絡情報.証券会社コード()
        //部店コード： リクエストデータ.連絡情報.部店コード()
        //銘柄タイプ： ProductTypeEnum.その他
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_request.informInfoUnit.institutionCode,
            l_request.informInfoUnit.branchCode,
            ProductTypeEnum.OTHER);

        //MAPオブジェクトの生成
        //各種連絡テーブルを更新する為のMapオブジェクトを生成する
        Map l_map = new HashMap();
        l_map.put("order_request_number", l_strNewNumber);
        l_map.put("last_updater", l_strAdministratorCode);
        l_map.put("status", WEB3VoucherCreateStatusDef.CREATE_COMPLETE);
        l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //is銀行登録伝票（） == true の場合、以下の処理を実行
        if (l_blnFinancialInstitutionPostVoucher)
        {
            //振替申込（銀行）伝票(各種連絡Params, String, String)
            //コンストラクタ
            //[引数]
            //各種連絡行： 各種連絡オブジェクト.getDataSourceObject（）の戻り値
            //伝票識別コード： 注文識別コード採番サービス.get新規識別コード（）で取得した値
            //各種連絡識別コード： 連絡管理識別コード採番サービス.get新規識別コード（）で取得した値
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_adminInformTransferApplyFinancialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
                        (VariousInformParams)l_inform.getDataSourceObject(),
                        l_strNewNumber,
                        l_strNewOrderRequestCode);

            //save銀行登録伝票キュー
            //振替申込（銀行）伝票（G26）キューテーブルにレコードを作成する
            l_adminInformTransferApplyFinancialInstitutionVoucher.saveBankRegistVoucherHost();

            //以下の内容をMapに追加する
            //データコード：　@”GI823”（銀行）
            l_map.put("request_code", WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK);

            // isトリガ発行 == true の場合、以下の処理を実行
            if (l_blnSubmitMarketTrigger)
            {
                //トリガ発行(String, String)
                //トリガを発行する。
                //[引数]
                //証券会社コード：　@リクエストデータ.連絡情報.証券会社コード
                //データコード：　@”GI823”（銀行）
                this.submitMarketTrigger(
                    l_request.informInfoUnit.institutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK);
            }
        }
        else
        {
            //振替申込（郵貯）伝票(各種連絡Params, String, String)
            //コンストラクタ
            //[引数]
            //各種連絡行： 各種連絡オブジェクト.getDataSourceObject（）の戻り値
            //伝票識別コード： 注文識別コード採番サービス.get新規識別コード（）で取得した値
            //各種連絡識別コード： 連絡管理識別コード採番サービス.get新規識別コード（）で取得した値
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher(
                    (VariousInformParams)l_inform.getDataSourceObject(),
                    l_strNewNumber,
                    l_strNewOrderRequestCode);

            //save郵貯登録伝票キュー( )
            //振替申込（郵貯）伝票（G26）キューテーブルにレコードを作成する
            l_adminInformTransferApplyPostVoucher.savePostRegistVoucherHost();

            //Mapオブジェクトに追加
            //データコード：　@”GI828”（郵貯）
            l_map.put("request_code", WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL);

            //isトリガ発行 == true の場合、以下の処理を実行
            if (l_blnSubmitMarketTrigger)
            {
                //トリガ発行(String, String)
                //トリガを発行する。
                //[引数]
                //証券会社コード：　@リクエストデータ.連絡情報.証券会社コード
                //データコード：　@”GI828”（郵貯）
                this.submitMarketTrigger(
                    l_request.informInfoUnit.institutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL);
            }
        }

        //update各種連絡(Map)
        //各種連絡テーブルのレコードを更新する。
        //[引数]
        //生成したMapオブジェクト
        //DB更新仕様「分配金振替口座登録_各種連絡テーブル.xls」
        //分配金振替口座登録_update_DB更新仕様参照
        l_inform.updateInform(l_map);

        //レスポンスオブジェクトの生成
        WEB3AdminInformProfDistVoucherMakeCmpResponse l_response =
            (WEB3AdminInformProfDistVoucherMakeCmpResponse)l_request.createResponse();

        return l_response;
    }

    /**
     * (get振替口座情報参照画面)<BR>
     * 利金・分配金振替口座登録状況参照画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「get振替口座情報参照画面」 参照。<BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:get振替口座情報参照画面<BR>
     * 　@具体位置　@　@　@:1.5　@get各種連絡行(String, String, String, String)、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@戻り値がnullの場合、例外をスロ<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag:　@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherInfoRefResponse
     * @@throws WEB3BaseException
     * @@roseuid 4642721701B0
     */
    public WEB3AdminInformProfDistVoucherInfoRefResponse getTransferAccountInfoRefScreen(
        WEB3AdminInformProfDistVoucherInfoRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTransferAccountInfoRefScreen(WEB3AdminInformProfDistVoucherInfoRefRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //権限チェックを行う。
        //[引数]
        //機@能カテゴリコード： "A0105"
        //is更新： false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU,
            false);

        //リクエストデータの簡易チェック
        l_request.validate();

        //証券会社コードを取得する
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //主キーを基に各種連絡テーブルを検索する。
        //［引数］
        //証券会社コード： get証券会社コード（）の戻り値
        //部店コード：  リクエストデータ.部店コード
        //識別コード：  リクエストデータ.識別コード
        //連絡種別：  リクエストデータ.連絡種別
        VariousInformParams l_variousInformParams =
            WEB3Inform.getVariousInform(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber,
                l_request.informType);

        //戻り値がnullの場合、例外をスロ
        if (l_variousInformParams == null)
        {
            log.debug(" 該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        //各種連絡詳細情報インスタンスを生成する。
        //［引数］
        //各種連絡行： get各種連絡行（）の戻り値
        WEB3InformDetailHeaderInfoUnit l_informDetailHeaderInfoUnit =
            new WEB3InformDetailHeaderInfoUnit(l_variousInformParams);

        //レスポンスオブジェクトの生成
        WEB3AdminInformProfDistVoucherInfoRefResponse l_response =
            (WEB3AdminInformProfDistVoucherInfoRefResponse)l_request.createResponse();

        //連絡情報：　@各種連絡詳細情報オブジェクト
        l_response.informInfoUnit = l_informDetailHeaderInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate口座情報変更確認)<BR>
     * 利金・分配金登録状況変更確認画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate口座情報変更確認」 参照。<BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:validate口座情報変更確認<BR>
     * 　@具体位置　@　@　@:1.8　@get各種連絡行(String, String, String, String)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@戻り値がnullの場合、例外をスロー<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag:　@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherChgCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 4642757D0078
     */
    public WEB3AdminInformProfDistVoucherChgCnfResponse validateAccountInfoChgCnf(
        WEB3AdminInformProfDistVoucherChgCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateAccountInfoChgCnf(WEB3AdminInformProfDistVoucherChgCnfRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        // getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限チェックを行う。
        //[引数]
        //機@能カテゴリコード： "A0105"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, true);

        //validate注文受付可能( )
        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // validate( )
        //リクエストデータの簡易チェックを行う。
        l_request.validate();

        // 各種連絡(各種連絡情報)
        //コンストラクタ
        //［引数］
        //連絡情報：　@リクエストデータ.連絡情報
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);

        // validate各種連絡情報( )
        //各種連絡データの入力チェックを行う。
        l_inform.validateInformDetailInfoUnit();

        // get証券会社コード( )
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //  get各種連絡行(String, String, String, String)
        //主キーを基に各種連絡テーブルを検索する。
        //［引数］
        //証券会社コード： get証券会社コード（）の戻り値
        //部店コード：  リクエストデータ.部店コード
        //識別コード：  リクエストデータ.識別コード
        //連絡種別：  リクエストデータ.連絡種別
        VariousInformParams l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //戻り値がnullの場合、例外をスロー
        if (l_variousInformParams == null)
        {
            log.debug(" 該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        // isトリガ発行(発注条件 : String)
        //SONARへトリガを発行できる時間帯かを判定する。
        //
        //[引数]
        //発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        // validate伝票変更(String, boolean)
        //伝票変更が可能かチェックを行う。
        //作成状況：　@get各種連絡行（）.get作成状況（）の戻り値
        //トリガー発行区分：　@isトリガー発行（）の戻り値
        String l_strStatus = l_variousInformParams.getStatus();
        validateVoucherChg(l_strStatus, l_blnIsSubmitMarketTrigger);

        //  validate振込先登録情報(各種連絡情報)
        //自動振替登録マスターレコードとの整合性チェック
        //[引数]
        //連絡情報： リクエストデータ.連絡情報
        validateTransferRegistInfo(l_request.informInfoUnit);

        // validate銘柄コード(各種連絡情報)
        //銘柄コードの妥当性チェックを行う。
        //[引数]
        //連絡情報： リクエストデータ.連絡情報
        validateProductCode(l_request.informInfoUnit);

        // get付加情報( )
        WEB3InformAddInfoUnit l_informAddInfoUnit = l_inform.getInformAddInfoUnit();

        // 各種金融機@関情報( )
        WEB3AdminInformProfDistSellTransSrcInfo l_informProfDistSellTransSrcInfo =
            new WEB3AdminInformProfDistSellTransSrcInfo();

        // 連絡情報.区分５ = 1：銀行振込 の場合、以下の処理を実行
        String l_strDiv5 = l_request.informInfoUnit.div5;
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_strDiv5))
        {
            // validate金融機@関情報(各種連絡情報)
            //振替申込（銀行）伝票を作成する際のパラメータチェックを行う。
            //［引数］
            //連絡情報：　@リクエストデータ.連絡情報
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_informTransferApplyFinancialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher();
            l_informTransferApplyFinancialInstitutionVoucher.validateFinancialInstitutionInfo(
                l_request.informInfoUnit);

            // get金融機@関情報(String, String, 各種金融機@関情報)
            //金融機@関（銀行）マスタから金融機@関情報を取得する。
            //[引数]
            //金融機@関コード： 連絡情報.コード３
            //支店コード： 連絡情報.コード４
            //金融機@関情報： 生成した各種金融機@関情報オブジェクト
            getFinancialInstitutionInfo(
                l_request.informInfoUnit.code3,
                l_request.informInfoUnit.code4,
                l_informProfDistSellTransSrcInfo);
        }

        // 連絡情報.区分５ = 5：郵貯振替 の場合、以下の処理を実行
        if (WEB3TransferDivDef.POSTAL_TRANSFER.equals(l_strDiv5))
        {
            // validate郵貯情報(各種連絡情報)
            //振替申込（郵貯）伝票を作成する際のパラメータチェックを行う。
            //［引数］
            //連絡情報：　@リクエストデータ.連絡情報
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher();
            l_adminInformTransferApplyPostVoucher.validatePostInfo(l_request.informInfoUnit);
        }

        // createResponse( )
        //レスポンスオブジェクトの生成
        WEB3AdminInformProfDistVoucherChgCnfResponse l_adminInformProfDistVoucherChgCnfResponse =
            (WEB3AdminInformProfDistVoucherChgCnfResponse)l_request.createResponse();

        //（＊）プロパティセット
        //（＊）レスポンスオブジェクトに以下をセットする。
        //付加情報 = get付加情報（）の戻り値
        l_adminInformProfDistVoucherChgCnfResponse.informAddUnit = l_informAddInfoUnit;

        //金融機@関情報 = 各種金融機@関情報オブジェクト
        l_adminInformProfDistVoucherChgCnfResponse.financialInstitutionInfo =
            l_informProfDistSellTransSrcInfo;

        // return
        log.exiting(STR_METHOD_NAME);
        return l_adminInformProfDistVoucherChgCnfResponse;
    }

    /**
     * (submit口座情報変更完了)<BR>
     * 利金・分配金登録状況変更完了画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit口座情報変更完了」 参照。<BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:validate口座情報変更確認<BR>
     * 　@具体位置　@　@　@:1.9　@get各種連絡行(String, String, String, String)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@戻り値がnullの場合、例外をスロー<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag:　@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherChgCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 46427C490078
     */
    public WEB3AdminInformProfDistVoucherChgCmpResponse submitAccountInfoChgCmp(
        WEB3AdminInformProfDistVoucherChgCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitAccountInfoChgCmp(WEB3AdminInformProfDistVoucherChgCmpRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        // getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限チェックを行う。
        //[引数]
        //機@能カテゴリコード： "A0105"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, true);

        //validate取引パスワード(パスワード : String)
        //パスワード： リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //validate注文受付可能( )
        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //各種連絡(各種連絡情報)(各種連絡::各種連絡)
        WEB3Inform l_inform = new WEB3Inform(l_request.informInfoUnit);

        //validate各種連絡情報( )
        //各種連絡データの入力チェックを行う。
        l_inform.validateInformDetailInfoUnit();

        VariousInformParams l_afterChgVariousInformParams =
            (VariousInformParams)l_inform.getDataSourceObject();

        //get証券会社コード( )
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get各種連絡行(String, String, String, String)(各種連絡::get各種連絡行)
        //主キーを基に各種連絡テーブルを検索する。
        //［引数］
        //証券会社コード： get証券会社コード（）の戻り値
        //部店コード：  リクエストデータ.部店コード
        //識別コード：  リクエストデータ.識別コード
        //連絡種別：  リクエストデータ.連絡種別
        VariousInformParams l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //戻り値がnullの場合、
        //例外をスロー
        if (l_variousInformParams == null)
        {
            log.debug(" 該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        // isトリガ発行(発注条件 : String)
        //SONARへトリガを発行できる時間帯かを判定する。
        //[引数]
        //発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        //validate伝票変更(String, boolean)
        //伝票変更が可能かチェックを行う。
        //作成状況：　@get各種連絡行（）.get作成状況（）の戻り値
        //トリガー発行区分：　@isトリガー発行（）の戻り値
        String l_strStatus = l_variousInformParams.getStatus();
        validateVoucherChg(l_strStatus, l_blnIsSubmitMarketTrigger);

        // validate振込先登録情報(各種連絡情報)
        //自動振替登録マスターレコードとの整合性チェック
        //[引数]
        //連絡情報： リクエストデータ.連絡情報
        validateTransferRegistInfo(l_request.informInfoUnit);

        //validate銘柄コード(各種連絡情報)
        //銘柄コードの妥当性チェックを行う。
        //[引数]
        //連絡情報： リクエストデータ.連絡情報
        validateProductCode(l_request.informInfoUnit);

        // is銀行登録伝票(String)
        //データが銀行登録伝票か判別する。
        //[引数]
        //振替区分：  リクエストデータ.連絡情報.区分５
        String l_strDiv5 = l_request.informInfoUnit.div5;
        boolean l_blnIsBankRegistVoucher =
        	isBankRegistVoucher(l_strDiv5);

        //get新規識別コード(証券会社コード : String, 部店コード : String, 銘柄タイプ : ProductTypeEnum)
        //SONAR通知キューに書き込む際に必要な識別コードを採番する。
        //[引数]
        //　@証券会社コード： リクエストデータ.連絡情報.証券会社コード
        //　@部店コード： リクエストデータ.連絡情報.部店コード
        //　@銘柄タイプ： ProductTypeEnum.その他
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_request.informInfoUnit.institutionCode,
            l_request.informInfoUnit.branchCode,
            ProductTypeEnum.OTHER);

        //get各種連絡行（）.区分５
        String l_strExtDiv5 = l_variousInformParams.getExtDiv5();

        //get各種連絡行（）.区分５ == 1:銀行振込の場合
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_strExtDiv5))
        {
            //振替申込（銀行）伝票(各種連絡Params)
            //各種連絡行： get各種連絡行（）の戻り値
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_financialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
                        l_variousInformParams);

            // delete銀行登録伝票キュー( )
            //振替申込（銀行）伝票（G26）キューテーブルのレコードを削除する。
            l_financialInstitutionVoucher.deleteBankRegistVoucherHost();
        }

        //get各種連絡行（）.区分５ == 5:郵貯振替の場合
        if (WEB3TransferDivDef.POSTAL_TRANSFER.equals(l_strExtDiv5))
        {
            //振替申込（郵貯）伝票(各種連絡Params)
            //［引数］
            //各種連絡行： get各種連絡行（）の戻り値
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher(l_variousInformParams);

            // delete郵貯登録伝票キュー( )
            l_adminInformTransferApplyPostVoucher.deletePostRegistVoucherHost();
        }

        //is銀行登録伝票（） == true の場合、以下の処理を実行
        if (l_blnIsBankRegistVoucher)
        {
            //振替申込（銀行）伝票(各種連絡Params, String, String)
            //[引数]
            // 各種連絡行： 各種連絡オブジェクト.getDataSourceObject（）の戻り値
            // 伝票識別コード： 注文識別コード採番サービス.get新規識別コード（）で取得した値
            // 各種連絡識別コード： get各種連絡行（）.get識別コード（）
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_financialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
                        l_afterChgVariousInformParams,
                        l_strNewNumber,
                        l_variousInformParams.getRequestNumber());

            // save銀行登録伝票キュー()
            //振替申込（銀行）伝票（G26）キューテーブルにレコードを作成する。
            l_financialInstitutionVoucher.saveBankRegistVoucherHost();

            //isトリガ発行 == true の場合、以下の処理を実行
            if (l_blnIsSubmitMarketTrigger)
            {
                //トリガ発行(String, String)
                //トリガを発行する。
                //[引数]
                //証券会社コード：　@リクエストデータ.連絡情報.証券会社コード
                //データコード：　@”GI823”（銀行）
                submitMarketTrigger(
                    l_request.informInfoUnit.institutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK);
            }
        }
        //is銀行登録伝票（） == false の場合、以下の処理を実行
        if (!l_blnIsBankRegistVoucher)
        {
            //振替申込（郵貯）伝票(各種連絡Params, String, String)
            //[引数]
            // 各種連絡行： 各種連絡オブジェクト.getDataSourceObject（）の戻り値
            // 伝票識別コード： 注文識別コード採番サービス.get新規識別コード（）で取得した値
            // 各種連絡識別コード： get各種連絡行（）.get識別コード（）
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher(
                    l_afterChgVariousInformParams,
                    l_strNewNumber,
                    l_variousInformParams.getRequestNumber());

            // save郵貯登録伝票キュー( )
            //振替申込（郵貯）伝票（G26）キューテーブルにレコードを作成する。
            l_adminInformTransferApplyPostVoucher.savePostRegistVoucherHost();

            //isトリガ発行 == true の場合、以下の処理を実行
            if (l_blnIsSubmitMarketTrigger)
            {
                //トリガ発行(String, String)
                //トリガを発行する。
                //[引数]
                // 証券会社コード：　@リクエストデータ.連絡情報.証券会社コード
                // データコード：　@”GI828”（郵貯）
                submitMarketTrigger(
                    l_request.informInfoUnit.institutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL);
            }
        }

        // update各種連絡変更情報(各種連絡Params, 各種連絡Params, String, String)
        //各種連絡の変更情報を更新する。
        //［引数］
        //変更前各種連絡行： get各種連絡行（）の戻り値
        //変更後各種連絡行： 各種連絡オブジェクト.getDataSourceObject（）の戻り値
        //識別コード： 注文識別コード採番サービス.get新規識別コード（）で取得した値
        //データコード：　@is銀行登録伝票 == true の場合、GI823（銀行）
        //                 is銀行登録伝票 == false の場合、GI828（郵貯）
        String l_strFinancialInstitutionPostVoucher = null;
        if (l_blnIsBankRegistVoucher)
        {
            l_strFinancialInstitutionPostVoucher = WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_BANK;
        }
        else
        {
            l_strFinancialInstitutionPostVoucher = WEB3HostRequestCodeDef.ACCOPEN_TRANSFER_POSTAL;
        }
        updateVariousInformChgInfo(
            l_variousInformParams,
            l_afterChgVariousInformParams,
            l_strNewNumber,
            l_strFinancialInstitutionPostVoucher
            );

        //createResponse( )
        //レスポンスオブジェクトの生成
        WEB3AdminInformProfDistVoucherChgCmpResponse
            l_informProfDistVoucherChgCmpResponse =
                (WEB3AdminInformProfDistVoucherChgCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_informProfDistVoucherChgCmpResponse;
    }

    /**
     * (validate口座情報取消確認)<BR>
     * 利金・分配金登録状況取消確認画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate口座情報取消確認」 参照。<BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:validate口座情報変更確認<BR>
     * 　@具体位置　@　@　@:1.6　@get各種連絡行(String, String, String, String)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@戻り値がnullの場合、例外をスロー<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag:　@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherCancCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 46427D1900F5
     */
    public WEB3AdminInformProfDistVoucherCancCnfResponse validateAccountInfoCancCnf(
        WEB3AdminInformProfDistVoucherCancCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateAccountInfoCancCnf(WEB3AdminInformProfDistVoucherCancCnfRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        // getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限チェックを行う。
        //[引数]
        //機@能カテゴリコード： "A0105"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, true);

        //validate注文受付可能( )
        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // validate( )
        //リクエストデータの簡易チェックを行う。
        l_request.validate();

        // get証券会社コード( )
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get各種連絡行(String, String, String, String)
        //主キーを基に各種連絡テーブルを検索する。
        //［引数］
        //証券会社コード： get証券会社コード（）の戻り値
        //部店コード：  リクエストデータ.部店コード
        //識別コード：  リクエストデータ.識別コード
        //連絡種別：  リクエストデータ.連絡種別
        VariousInformParams l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //戻り値がnullの場合、
        //例外をスロー
        if (l_variousInformParams == null)
        {
            log.debug(" 該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        // isトリガ発行(発注条件 : String)
        //SONARへトリガを発行できる時間帯かを判定する。
        //[引数]
        //発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        //validate伝票取消(String, boolean)
        //伝票取消可能かチェックを行う。
        //[引数]
        //作成状況： get各種連絡行（）.get伝票作成状況の戻り値
        //トリガー発行区分： isトリガー発行（）の戻り値
        validateVoucherCanc(
            l_variousInformParams.getStatus(),
            l_blnIsSubmitMarketTrigger);

        //各種連絡情報(各種連絡Params)
        //コンストラクタ
        //［引数］
        //各種連絡行： get各種連絡行（）の戻り値
        WEB3InformDetailInfoUnit l_informDetailInfoUnit =
            new WEB3InformDetailInfoUnit(l_variousInformParams);

        //各種連絡(各種連絡情報)
        //コンストラクタ
        //[引数]
        //連絡情報：生成した各種連絡情報オブジェクト
        WEB3Inform l_inform = new WEB3Inform(l_informDetailInfoUnit);

        //get付加情報( )
        //付加情報を取得する。
        WEB3InformAddInfoUnit l_informAddInfoUnit = l_inform.getInformAddInfoUnit();

        //各種金融機@関情報( )
        WEB3AdminInformProfDistSellTransSrcInfo l_informProfDistSellTransSrcInfo =
            new WEB3AdminInformProfDistSellTransSrcInfo();

        WEB3AdminInformProfDistSellTransSrcInfo l_adminInformProfDistSellTransSrcInfo = null;
        //各種連絡.各種連絡行.get区分５ = 1：銀行振込 の場合、以下の処理を実行
        String l_strExtDiv5 = l_variousInformParams.getExtDiv5();
        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_strExtDiv5))
        {
            //get金融機@関情報(String, String, 各種金融機@関情報)
            //金融機@関（銀行）マスタから金融機@関情報を取得する。
            //[引数]
            //金融機@関コード： 各種連絡.各種連絡行.getコード３
            //支店コード： 各種連絡.各種連絡行.getコード４
            //金融機@関情報： 生成した各種金融機@関情報オブジェクト
            l_adminInformProfDistSellTransSrcInfo =
                getFinancialInstitutionInfo(
                    l_variousInformParams.getExtCode3(),
                    l_variousInformParams.getExtCode4(),
                    l_informProfDistSellTransSrcInfo);
        }

        // createResponse( )
        WEB3AdminInformProfDistVoucherCancCnfResponse
            l_informProfDistVoucherCancCnfResponse =
                (WEB3AdminInformProfDistVoucherCancCnfResponse)l_request.createResponse();

        //（*）プロパティセット
        //（*）レスポンスデータに以下をセットする。
        //連絡情報： 生成した各種連絡情報オブジェクト
        //付加情報： get付加情報（）の戻り値
        //金融機@関情報： get金融機@関情報（）の戻り値
        l_informProfDistVoucherCancCnfResponse.informInfoUnit = l_informDetailInfoUnit;
        l_informProfDistVoucherCancCnfResponse.informAddUnit = l_informAddInfoUnit;
        l_informProfDistVoucherCancCnfResponse.financialInstitutionInfo =
            l_adminInformProfDistSellTransSrcInfo;

        log.exiting(STR_METHOD_NAME);
        return l_informProfDistVoucherCancCnfResponse;
    }

    /**
     * (submit口座情報取消完了)<BR>
     * 利金・分配金登録状況取消完了画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit口座情報取消完了」 参照。<BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:validate口座情報変更確認<BR>
     * 　@具体位置　@　@　@:1.8　@get各種連絡行(String, String, String, String)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@戻り値がnullの場合、例外をスロー<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag:　@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistVoucherCancCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 46427D6302F9
     */
    public WEB3AdminInformProfDistVoucherCancCmpResponse submitAccountInfoCancCmp(
        WEB3AdminInformProfDistVoucherCancCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitAccountInfoCancCmp(WEB3AdminInformProfDistVoucherCancCmpRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限チェックを行う
        //[引数]
        //機@能カテゴリコード： "A0105"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, true);

        //validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかのチェックを行う。
        //[引数]
        //パスワード： リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //validate注文受付可能( )
        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // validate( )
        //リクエストデータの簡易チェック
        l_request.validate();

        //get管理者コード( )
        //管理者コードを取得する。
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //get証券会社コード( )
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get各種連絡行(String, String, String, String)
        //［引数］
        //証券会社コード： get証券会社コード（）の戻り値
        //部店コード：  リクエストデータ.部店コード
        //識別コード：  リクエストデータ.識別コード
        //連絡種別：  リクエストデータ.連絡種別
        VariousInformParams l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //戻り値がnullの場合、
        //例外をスロー
        if (l_variousInformParams == null)
        {
            log.debug(" 該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        //isトリガ発行(発注条件 : String)
        //SONARへトリガを発行できる時間帯かを判定する。
        //[引数]
        //発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        //validate伝票取消(String, boolean)
        //伝票取消可能かチェックを行う。
        //[引数]
        //作成状況： get各種連絡行（）..get伝票作成状況の戻り値
        //トリガー発行区分： isトリガー発行（）の戻り値
        validateVoucherCanc(
            l_variousInformParams.getStatus(),
            l_blnIsSubmitMarketTrigger);

        // is銀行登録伝票(String)
        //データが銀行登録伝票か判別する。
        //[引数]
        //振替区分：  get各種連絡行（）.区分５
        String l_strExtDiv5 = l_variousInformParams.getExtDiv5();
        boolean l_blnIsBankRegistVoucher =
            isBankRegistVoucher(l_strExtDiv5);

        //is銀行登録伝票（） == true の場合、以下の処理を実行
        if (l_blnIsBankRegistVoucher)
        {
            //振替申込（銀行）伝票(各種連絡Params)
            //［引数
            //各種連絡行： get各種連絡行（）の戻り値
            WEB3AdminInformTransferApplyFinancialInstitutionVoucher
                l_financialInstitutionVoucher =
                    new WEB3AdminInformTransferApplyFinancialInstitutionVoucher(
                        l_variousInformParams);

            // delete銀行登録伝票キュー( )
            //振替申込（銀行）伝票（G26）キューテーブルのレコードを削除する。
            l_financialInstitutionVoucher.deleteBankRegistVoucherHost();
        }

        //is銀行登録伝票（） == false の場合、以下の処理を実行
        if (!l_blnIsBankRegistVoucher)
        {
            //振替申込（郵貯）伝票(各種連絡Params)
            //［引数］
            //各種連絡行： get各種連絡行（）の戻り値
            WEB3AdminInformTransferApplyPostVoucher l_adminInformTransferApplyPostVoucher =
                new WEB3AdminInformTransferApplyPostVoucher(l_variousInformParams);

            // delete郵貯登録伝票キュー( )
            //振替申込（郵貯）伝票（G26）キューテーブルのレコードを削除する。
            l_adminInformTransferApplyPostVoucher.deletePostRegistVoucherHost();
        }

        //以下の内容のMapオブジェクトを生成する
        //更新者コード：　@get管理者コード（）の戻り値
        //伝票作成状況：　@0：未作成
        //更新日時：　@処理日時
        Map l_map = new HashMap();
        l_map.put("last_updater", l_strAdministratorCode);
        l_map.put("status", WEB3VoucherCreateStatusDef.NOT_CREATE);
        l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        //エラー理由コード      null
        l_map.put("error_reason_code", null);
        //伝票識別コード  null
        l_map.put("order_request_number", null);
        //データコード null
        l_map.put("request_code", null);
        //伝票送信日時 null
        l_map.put("send_timestamp", null);
        //伝票受信日時 null
        l_map.put("receipt_timestamp", null);

        //update各種連絡(Map)
        //各種連絡テーブルのレコードを更新する。
        //[引数]
        //生成したMapオブジェクト
        //DB更新仕様「分配金振替口座伝票取消_各種連絡テーブル.xls」
        //分配金振替口座伝票取消_update参照

        //コンストラクタ
        //[引数]
        //各種連絡行：get各種連絡行（）の戻り値
        WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);

        //コンストラクタ
        //[引数]
        //各種連絡行：get各種連絡行（）の戻り値
        l_inform.updateInform(l_map);

        //createResponse( )
        WEB3AdminInformProfDistVoucherCancCmpResponse
            l_adminInformProfDistVoucherCancCmpResponse =
                (WEB3AdminInformProfDistVoucherCancCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_adminInformProfDistVoucherCancCmpResponse;
    }

    /**
     * (validate振込先登録情報)<BR>
     * 対象データと自動振替登録マスターレコードの整合性チェック<BR>
     * <BR>
     * １）　@登録区分の取得<BR>
     * <BR>
     * 登録区分 = 引数:連絡情報.区分4<BR>
     * <BR>
     * ２）　@自動振替登録マスターの検索<BR>
     * <BR>
     * [検索条件]<BR>
     * 証券会社コード = 引数:連絡情報.証券会社コード<BR>
     * 部店コード = 引数:連絡情報.部店コード<BR>
     * 顧客コード like 引数:連絡情報.口座番号の頭6桁%<BR>
     * 銘柄コード = 引数:連絡情報.銘柄コード<BR>
     * ＊引数:連絡情報.銘柄コードがnull の場合、is null<BR>
     * 指定区分 = 引数:連絡情報.区分２<BR>
     * 商品 like 引数:連絡情報.区分３の頭一桁%<BR>
     * ＊引数:連絡情報.区分３がnull の場合、is null<BR>
     * 振替区分 = 引数:連絡情報.区分５<BR>
     * <BR>
     * <BR>
     * ３）　@登録区分 = 1：新規 の場合、<BR>
     * 　@２）の検索結果が存在する場合、エラー<BR>
     * 　@「顧客ダブリエラー」<BR>
     * 　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_02783<BR>
     * <BR>
     * ４）　@登録区分 = 3：削除 の場合、<BR>
     * 　@２）の検索結果が存在しない場合、エラー<BR>
     * 　@「削除該当レコードなし」<BR>
     * 　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_02784<BR>
     * <BR>
     * @@param l_informDetailInfoUnit - (連絡情報)<BR>
     * 連絡情報
     * @@throws WEB3BaseException
     * @@roseuid 4647C44D0137
     */
    protected void validateTransferRegistInfo(WEB3InformDetailInfoUnit l_informDetailInfoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateTransferRegistInfo(WEB3InformDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_informDetailInfoUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        // １）　@登録区分の取得
        // 登録区分 = 引数:連絡情報.区分4
        String l_strRegistDiv = l_informDetailInfoUnit.div4;

        List l_lisDirectDebitRow = null;
        // ２）　@自動振替登録マスターの検索
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // [検索条件]
            StringBuffer l_sbQuery = new StringBuffer();
            List l_lisValue = new ArrayList();

            // 証券会社コード = 引数:連絡情報.証券会社コード
            l_sbQuery.append(" institution_code = ? ");
            l_lisValue.add(l_informDetailInfoUnit.institutionCode);

            // 部店コード = 引数:連絡情報.部店コード
            l_sbQuery.append(" and branch_code = ? ");
            l_lisValue.add(l_informDetailInfoUnit.branchCode);

            // 顧客コード = 引数:連絡情報.口座番号
            l_sbQuery.append(" and account_code like ? || '%' ");
            l_lisValue.add(l_informDetailInfoUnit.accountNumber);

            // 銘柄コード = 引数:連絡情報.銘柄コード
            // ＊引数:連絡情報.銘柄コードがnull の場合、is null
            if (WEB3StringTypeUtility.isEmpty(l_informDetailInfoUnit.productCode))
            {
                l_sbQuery.append(" and fund_code is null");
            }
            else
            {
                l_sbQuery.append(" and fund_code = ? ");
                l_lisValue.add(l_informDetailInfoUnit.productCode);
            }

            // 指定区分 = 引数:連絡情報.区分２
            l_sbQuery.append(" and designate_div = ? ");
            l_lisValue.add(l_informDetailInfoUnit.div2);

            // 商品 like 引数:連絡情報.区分３の頭一桁%
            // ＊引数:連絡情報.区分３がnull の場合、is null
            if (WEB3StringTypeUtility.isEmpty(l_informDetailInfoUnit.div3))
            {
                l_sbQuery.append(" and comodity is null");
            }
            else
            {
                l_sbQuery.append(" and comodity like ? || '%'");
                l_lisValue.add(l_informDetailInfoUnit.div3.substring(0, 1));
            }
            //振替区分=引数:連絡情報.区分５
            l_sbQuery.append(" and transfer_div = ? ");
            l_lisValue.add(l_informDetailInfoUnit.div5);

            Object[] l_dataValues = l_lisValue.toArray();
            l_lisDirectDebitRow = l_queryProcessor.doFindAllQuery(
                DirectDebitRow.TYPE,
                l_sbQuery.toString(),
                l_dataValues);
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

        // ３）　@登録区分 = 1：新規 の場合、
        //　@ ２）の検索結果が存在する場合、エラー
        //　@ 「顧客ダブリエラー」
        if (WEB3InformRegistDivDef.REGISTRATION.equals(l_strRegistDiv))
        {
            if (!l_lisDirectDebitRow.isEmpty())
            {
                log.debug("顧客ダブリエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02783,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "顧客ダブリエラー。");
            }
        }

        // ４）　@登録区分 = 3：削除 の場合、
        //　@ ２）の検索結果が存在しない場合、エラー
        //　@「削除該当レコードなし」
        if (WEB3InformRegistDivDef.DELETE.equals(l_strRegistDiv))
        {
            if (l_lisDirectDebitRow.isEmpty())
            {
                log.debug("削除該当レコードなし。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02784,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "削除該当レコードなし。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate銘柄コード)<BR>
     * 銘柄コードの妥当性チェックを行う。<BR>
     * <BR>
     * １）引数:連絡情報.区分３ = 2:投資信託 or R: オープン株投コースの場合、<BR>
     * <BR>
     * 　@１－１）　@引数:連絡情報.コード１ のレングスが7バイト以外は例外をスロー<BR>
     * <BR>
     * 　@　@　@　@　@　@「銘柄コードエラー」<BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02785<BR>
     * <BR>
     * ２）引数:連絡情報.区分３ = 3:公社債の場合、<BR>
     * <BR>
     * 　@２－１）　@引数:連絡情報.コード２ のレングスが9バイト以外は例外をスロー<BR>
     * <BR>
     * 　@　@　@　@　@　@「銘柄コードエラー」<BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02785<BR>
     * <BR>
     * 　@２－２）　@引数:連絡情報.コード２ がALL半角０、または半角ブランクの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@「銘柄コードエラー」<BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02785<BR>
     * <BR>
     * ３）引数:連絡情報.区分３がnull または １）、２）以外の場合、<BR>
     * <BR>
     * 　@３－１）引数:連絡情報.コード１ != null　@または<BR>
     * 　@　@　@　@　@引数:連絡情報.コード２ != nullの場合、<BR>
     * 　@　@　@　@　@例外をスロー<BR>
     * <BR>
     * 　@　@　@　@　@　@「銘柄コードエラー」<BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02785<BR>
     * @@param l_informDetailInfoUnit - (連絡情報)<BR>
     * 連絡情報
     * @@throws WEB3BaseException
     * @@roseuid 46528FA1029C
     */
    protected void validateProductCode(WEB3InformDetailInfoUnit l_informDetailInfoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateProductCode(WEB3InformDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_informDetailInfoUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１）引数:連絡情報.区分３ = 2:投資信託 or R: オープン株投コースの場合、
        // １－１）　@引数:連絡情報.コード１ のレングスが7バイト以外は例外をスロー
        //「銘柄コードエラー」
        if (WEB3InformProductFirstDef.MUTUAL_FUND.equals(l_informDetailInfoUnit.div3)
            || WEB3InformProductFirstDef.OPEN_KABUTOU_COURSE.equals(l_informDetailInfoUnit.div3))
        {
            if (WEB3StringTypeUtility.getByteLength(l_informDetailInfoUnit.code1) != 7)
            {
                log.debug("銘柄コードエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "銘柄コードエラー。");
            }
        }

        //２）引数:連絡情報.区分３ = ３:公社債の場合、
        else if (WEB3InformProductFirstDef.PUBLIC_AND_CORPORATE_BOODS.equals(
            l_informDetailInfoUnit.div3))
        {
            // ２－１）　@引数:連絡情報.コード２ のレングスが9バイト以外は例外をスロー
            // 「銘柄コードエラー」
            if (WEB3StringTypeUtility.getByteLength(l_informDetailInfoUnit.code2) != 9)
            {
                log.debug("銘柄コードエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "銘柄コードエラー。");
            }

            //２－２）　@引数:連絡情報.コード２ がALL半角０、または半角ブランクの場合、例外をスロー
            //「銘柄コードエラー」
            if (WEB3InformFundCodeDef.BLANK.equals(l_informDetailInfoUnit.code2.trim())
                || (WEB3InformFundCodeDef.ALL_ZERO.equals(l_informDetailInfoUnit.code2)))
            {
                log.debug("銘柄コードエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "銘柄コードエラー。");
            }
        }
        //３）引数:連絡情報.区分３がnull または １）、２）以外の場合、
        //３－１）引数:連絡情報.コード１ != null　@または　@引数:連絡情報.コード２ != nullの場合、例外をスロー
        //「銘柄コードエラー」
        else
        {
            if (l_informDetailInfoUnit.code1 != null || l_informDetailInfoUnit.code2 != null)
            {
                log.debug("銘柄コードエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02785,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "銘柄コードエラー。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get金融機@関情報)<BR>
     * 金融機@関（銀行）マスタから金融機@関情報を取得する。<BR>
     * <BR>
     * １） 以下の検索条件で、金融機@関（銀行）マスタを検索<BR>
     * <BR>
     * [条件]<BR>
     * 銀行コード = 引数:金融機@関コード<BR>
     * 支店コード = 引数:支店コード <BR>
     * <BR>
     * ＊レコードが存在しない場合、例外をスロー<BR>
     * 「金融機@関が存在しない。」<BR>
     * 　@classes:　@WEB3BusinessLayerException<BR>
     * 　@tag:　@BUSINESS_ERROR_01314<BR>
     * <BR>
     * ２） 引数:金融機@関情報に取得した各値をセット<BR>
     * <BR>
     * ３）　@セットした金融機@関情報を返却。<BR>
     * @@param l_strFinancialInstitutionCode - (金融機@関コード)<BR>
     * 金融機@関コード
     * @@param l_strFinancialBranchCode - (支店コード)<BR>
     * 支店コード
     * @@param l_financialInstitutionInfo - (金融機@関情報)<BR>
     * 金融機@関情報
     * @@return WEB3AdminInformProfDistSellTransSrcInfo
     * @@throws WEB3BaseException
     * @@roseuid 464968D702CE
     */
    protected WEB3AdminInformProfDistSellTransSrcInfo getFinancialInstitutionInfo(
        String l_strFinancialInstitutionCode,
        String l_strFinancialBranchCode,
        WEB3AdminInformProfDistSellTransSrcInfo l_financialInstitutionInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getfinancialInstitutionInfo("
            + "String, String, WEB3AdminInformProfDistSellTransSrcInfo)";
        log.entering(STR_METHOD_NAME);

        // １） 以下の検索条件で、金融機@関（銀行）マスタを検索
        // [条件]
        // 銀行コード = 引数:金融機@関コード
        // 支店コード = 引数:支店コード
        List l_lisFinInstitutionBankRow = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // [検索条件]
            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" fin_institution_code = ? ");
            l_sbQuery.append(" and fin_branch_code = ? ");
            Object[] l_dataValues = {l_strFinancialInstitutionCode, l_strFinancialBranchCode};
            l_lisFinInstitutionBankRow = l_queryProcessor.doFindAllQuery(
                FinInstitutionBankRow.TYPE,
                l_sbQuery.toString(),
                l_dataValues);
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

        // ＊レコードが存在しない場合、例外をスロー
        // 「金融機@関が存在しない。」
        if (l_lisFinInstitutionBankRow.isEmpty())
        {
            log.debug("金融機@関が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                this.getClass().getName() + STR_METHOD_NAME,
                "金融機@関が存在しない。");
        }

        // 引数:金融機@関情報に取得した各値をセット
        if (l_lisFinInstitutionBankRow.size() == 1)
        {
            FinInstitutionBankRow l_finInstitutionBankRow =
                (FinInstitutionBankRow)l_lisFinInstitutionBankRow.get(0);

            //支店名
            l_financialInstitutionInfo.financialBranchName =
                l_finInstitutionBankRow.getFinBranchName();

            //支店名（カナ）
            l_financialInstitutionInfo.financialBranchNameKana =
                l_finInstitutionBankRow.getFinBranchNameKana();

            //金融機@関名
            l_financialInstitutionInfo.financialInstitutionName =
                l_finInstitutionBankRow.getFinInstitutionName();

            //金融機@関名（カナ）
            l_financialInstitutionInfo.financialInstitutionNameKana =
                l_finInstitutionBankRow.getFinInstitutionNameKana();
        }

        // セットした金融機@関情報を返却。
        log.exiting(STR_METHOD_NAME);
        return l_financialInstitutionInfo;
    }

    /**
     * (create検索条件文字列)<BR>
     * 自動振替登録マスタの検索条件文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     * 　@条件文字列： "institution_code=?"<BR>
     * <BR>
     * 　@上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     * 　@条件文字列： " and branch_code=?"<BR>
     * <BR>
     * 　@上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ４）顧客コード<BR>
     * <BR>
     * 　@条件文字列： " and account_code like ? %"<BR>
     * <BR>
     * 　@上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ５）指定区分<BR>
     * <BR>
     * 　@条件文字列： " and designate_div=?"<BR>
     * <BR>
     * 　@上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ６）商品<BR>
     * 　@リクエストデータ.商品 != null の場合、<BR>
     * 　@条件文字列： " and comodity like ?  % "<BR>
     * <BR>
     * 　@リクエストデータ.商品 == null の場合、　@<BR>
     * 　@条件文字列： " and comodity is null"<BR>
     * <BR>
     * ７）銘柄コード<BR>
     * <BR>
     * 　@リクエストデータ.銘柄コード != null の場合、<BR>
     * 　@条件文字列： " and fund_code=?"<BR>
     * <BR>
     * 　@リクエストデータ.銘柄コード == null の場合、　@<BR>
     * 　@条件文字列： " and fund_code is null"<BR>
     * <BR>
     * <BR>
     * ８）振替区分<BR>
     * 　@条件文字列： " and transfer_div= ?"<BR>
     * <BR>
     * 　@上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * <BR>
     * ９）生成した文字列を返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・口座伝票作成入力リクエストクラス
     * @@return String
     * @@roseuid 4643CD0C03A6
     */
    private String createQueryString(WEB3AdminInformProfDistVoucherMakeInpRequest l_request)
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminInformProfDistVoucherMakeInpRequest)";
        log.entering(STR_METHOD_NAME);

        // １）空の文字列を生成する。
        StringBuffer l_sbSearchCondition = new StringBuffer();

        // ２）証券会社コード
        //   条件文字列： "institution_code=?"
        //   上記文字列を１）の文字列の末尾に追加する。
        l_sbSearchCondition.append(" institution_code = ? ");

        // ３）部店コード
        //   条件文字列： " and branch_code=?"
        //   上記文字列を１）の文字列の末尾に追加する。
        l_sbSearchCondition.append(" and branch_code = ? ");

        // ４）顧客コード
        //   条件文字列： " and account_code like ? %"
        //   上記文字列を１）の文字列の末尾に追加する。
        l_sbSearchCondition.append(" and account_code like ? || '%' ");

        // ５）指定区分
        //   条件文字列： " and designate_div=?"
        //   上記文字列を１）の文字列の末尾に追加する。
        l_sbSearchCondition.append(" and designate_div = ? ");

        // ６）商品
        // 　@リクエストデータ.商品 != null の場合、
        //   条件文字列： " and comodity like ?  % "
        // 　@リクエストデータ.商品 == null の場合、　@
        //   条件文字列： " and comodity is null"
        if (l_request.product != null)
        {
            l_sbSearchCondition.append(" and comodity like ? || '%' ");
        }
        else
        {
            l_sbSearchCondition.append(" and comodity is null ");
        }

        // ７）銘柄コード
        // 　@リクエストデータ.銘柄コード != null の場合、
        //   条件文字列： " and fund_code=?"
        // 　@リクエストデータ.銘柄コード == null の場合、　@
        //   条件文字列： " and fund_code is null"
        if (l_request.productCode != null)
        {
            l_sbSearchCondition.append(" and fund_code = ? ");
        }
        else
        {
            l_sbSearchCondition.append(" and fund_code is null ");
        }

        // ８）振替区分
        //   条件文字列： " and transfer_div= ?"
        //   上記文字列を１）の文字列の末尾に追加する。
        l_sbSearchCondition.append(" and transfer_div = ? ");

        // ９）生成した文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCondition.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * リクエストデータから、取得条件のデータリストを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     * 　@引数.証券会社コードを１）のリストに追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     * 　@引数.リクエストデータ.部店コードを１）のリストに追加する。<BR>
     * <BR>
     * ４）顧客コード<BR>
     * <BR>
     * 　@引数.リクエストデータ.顧客コードを１）のリストに追加する。<BR>
     * <BR>
     * ５）指定区分<BR>
     * <BR>
     * 　@引数.リクエストデータ.指定区分を１）のリストに追加する。<BR>
     * <BR>
     * ６）商品<BR>
     * <BR>
     * 　@引数.リクエストデータ.商品 != null の場合、<BR>
     * 　@引数.リクエストデータ.商品の頭一桁を１）のリストに追加する。<BR>
     * <BR>
     * <BR>
     * ７）銘柄コード<BR>
     * <BR>
     * 　@引数.リクエストデータ.銘柄コード != null の場合、<BR>
     * 　@引数.リクエストデータ.銘柄コードを１）のリストに追加する。<BR>
     * <BR>
     * <BR>
     * ８）振替区分<BR>
     * <BR>
     * 　@引数.リクエストデータ.振替区分を１）のリストに追加する。<BR>
     * <BR>
     * <BR>
     * ９）リストから配列を取得し、返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return Object[]
     * @@roseuid 4643CD1D03D4
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminInformProfDistVoucherMakeInpRequest l_request)
    {
        final String STR_METHOD_NAME =
            " createQueryDataContainer(String, WEB3AdminInformProfDistVoucherMakeInpRequest)";
        log.entering(STR_METHOD_NAME);

        // １）空のArrayListを生成する。
        List l_lisSearchConditionList = new ArrayList();

        // ２）証券会社コード
        //   引数.証券会社コードを１）のリストに追加する。
        l_lisSearchConditionList.add(l_strInstitutionCode);

        // ３）部店コード
        //   引数.リクエストデータ.部店コードを１）のリストに追加する。
        l_lisSearchConditionList.add(l_request.branchCode);

        // ４）顧客コード
        //   引数.リクエストデータ.顧客コードを１）のリストに追加する。
        l_lisSearchConditionList.add(l_request.accountCode);

        // ５）指定区分
        //   引数.リクエストデータ.指定区分を１）のリストに追加する。
        l_lisSearchConditionList.add(l_request.specifyDiv);

        // ６）商品
        // 　@引数.リクエストデータ.商品 != null の場合、
        //   引数.リクエストデータ.商品の頭一桁を１）のリストに追加する。
        if (!WEB3StringTypeUtility.isEmpty(l_request.product))
        {
            l_lisSearchConditionList.add(l_request.product.substring(0, 1));
        }

        // ７）銘柄コード
        // 　@引数.リクエストデータ.銘柄コード != null の場合、
        //   引数.リクエストデータ.銘柄コードを１）のリストに追加する。
        if (!WEB3StringTypeUtility.isEmpty(l_request.productCode))
        {
            l_lisSearchConditionList.add(l_request.productCode);
        }

        // ８）振替区分
        //   引数.リクエストデータ.振替区分を１）のリストに追加する。
        l_lisSearchConditionList.add(l_request.transferDiv);

        // ９）リストから配列を取得し、返却する。
        Object[] l_strArrSearchConditions = new Object[l_lisSearchConditionList.size()];
        l_lisSearchConditionList.toArray(l_strArrSearchConditions);

        log.exiting(STR_METHOD_NAME);

        return l_strArrSearchConditions;

    }

    /**
     * (is銀行登録伝票)<BR>
     * データが銀行登録伝票か判別する。<BR>
     * <BR>
     * 引数:振替区分 == 1:銀行振込の場合、true を返却<BR>
     * <BR>
     * 1:銀行振込以外の場合、false を返却<BR>
     * @@param l_strTransferDiv - (振替区分)<BR>
     * 振替区分
     * @@return boolean
     * @@roseuid 464A94520285
     */
    private boolean isBankRegistVoucher(String l_strTransferDiv)
    {

        final String STR_METHOD_NAME = " isBankRegistVoucher(String)";
        log.entering(STR_METHOD_NAME);

        if (WEB3TransferDivDef.BANK_TRANSFER.equals(l_strTransferDiv))
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

    /**
     * (トリガ発行)<BR>
     * トリガを発行する。<BR>
     * <BR>
     * １）WEB3MQMessageSpecを生成する。 <BR>
     * <BR>
     * 　@WEB3MQMessageSpec(証券会社コード, データコード,ユーザデータ)<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@証券会社コード：　@引数.証券会社コード<BR>
     * 　@データコード：　@引数.データコード<BR>
     * 　@ユーザデータ：　@"２：伝票データ送信区分.連絡管理データ送信"<BR>
     * <BR>
     * ２）MQトリガを発行する。<BR>
     * <BR>
     * 　@WEB3MQGatewayServiceImpl.send(MQメッセージ内容)<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@MQメッセージ内容： 生成したWEB3MQMessageSpec<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strDataCode - (データコード)<BR>
     * データコード
     * @@roseuid 464AB196032A
     */
    private void submitMarketTrigger(String l_strInstitutionCode, String l_strDataCode)
    {
        final String STR_METHOD_NAME = " submitMarketTrigger(String, String)";
        log.entering(STR_METHOD_NAME);

        // １）WEB3MQMessageSpecを生成する。
        // データコードに"T"を付加するように修正 2007.06.22 SCS 佐藤
        WEB3MQMessageSpec l_mqMessageSpec =
            new WEB3MQMessageSpec(l_strInstitutionCode,
                l_strDataCode + "T",
                WEB3InformVoucherDataSendDivDef.INFORM_DATA_SEND);

        // ２）MQトリガを発行する。
        WEB3MQGatewayService l_mqGatewayService =
            (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);

        log.debug("トリガを発行する........");
        l_mqGatewayService.send(l_mqMessageSpec);
        log.debug("トリガを発行する........OK!");
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (validate伝票取消)<BR>
     * 伝票取消可能かチェックを行う。<BR>
     * <BR>
     * １）　@トリガー発行区分 == true or 作成状況 != 1:作成済の場合、<BR>
     * 　@　@　@例外をスロー<BR>
     * 　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_02798<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分
     * @@throws WEB3BaseException
     * @@roseuid 464BF90600DD
     */
    private void validateVoucherCanc(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateVoucherCanc(String, boolean)";
        log.entering(STR_METHOD_NAME);
        // トリガー発行区分 == true or 作成状況 != 1:作成済の場合、
        // 例外をスロー
        if (l_blnSubmitMarketTriggerDiv || (!WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus)))
        {
            log.debug("オンライン中または伝票未作成または伝票送信済みの場合、取消不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02798,
                this.getClass().getName() + STR_METHOD_NAME,
                "オンライン中または伝票未作成または伝票送信済みの場合、取消不可。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate伝票変更)<BR>
     * 伝票変更が可能かチェックを行う。<BR>
     * <BR>
     * １）　@引数:トリガー発行区分 == true の場合<BR>
     * 　@１－１）　@作成状況が、0：未作成、4：受付エラー のいずれでもない場合、<BR>
     * 　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@「既に伝票が作成済みのため、<BR>
     * 　@　@　@　@　@　@　@オンライン中は伝票作成が行えません。」<BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02786<BR>
     * <BR>
     * ２）　@引数:トリガー発行区分 == false の場合　@<BR>
     * 　@２－１）　@作成状況が、0：未作成、4：受付エラー、<BR>
     * 　@　@　@　@　@　@　@1：作成済み のいずれでもない場合、<BR>
     * 　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@「伝票が処理済みのため伝票作成が行えません。」<BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02787<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分
     * @@throws WEB3BaseException
     * @@roseuid 4652C4F50098
     */
    private void validateVoucherChg(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateVoucherChg(String, boolean)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数:トリガー発行区分 == true の場合
        if (l_blnSubmitMarketTriggerDiv)
        {
            // １－１）　@作成状況が、0：未作成、4：受付エラー のいずれでもない場合、
            // 例外をスロー「既に伝票が作成済みのため、オンライン中は伝票作成が行えません。」
            if (!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus)
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("既に伝票が作成済みのため、オンライン中は伝票作成が行えません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02786,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "既に伝票が作成済みのため、オンライン中は伝票作成が行えません。");
            }
        }
        // ２）　@引数:トリガー発行区分 == false の場合
        else
        {
            // ２－１）　@作成状況が、0：未作成、4：受付エラー、1：作成済み のいずれでもない場合、
            // 例外をスロー「伝票が処理済みのため伝票作成が行えません。」
            if ((!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus)))
            {
                log.debug("伝票が処理済みのため伝票作成が行えません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02787,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "伝票が処理済みのため伝票作成が行えません。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update各種連絡変更情報)<BR>
     * 各種連絡の変更情報を更新する。<BR>
     * <BR>
     * DB更新仕様「分配金振替口座伝票変更_各種連絡テーブル.xls」参照<BR>
     * <BR>
     * ［更新条件］<BR>
     * 証券会社コード = 変更前各種連絡行.get証券会社コード（）<BR>
     * 連絡種別 = 変更前各種連絡行.get連絡種別（）<BR>
     * 識別コード = 変更前各種連絡行.get識別コード（）<BR>
     * 部店コード = 変更前各種連絡行.get部店コード（）<BR>
     * @@param l_beforeChgVariousInformParams - (変更前各種連絡行)<BR>
     * 変更前各種連絡行
     * @@param l_afterChgVariousInformParams - (変更後各種連絡行)<BR>
     * 変更後各種連絡行
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード
     * @@param l_strDataCode - (データコード)<BR>
     * データコード
     * @@throws WEB3BaseException
     * @@roseuid 46539C9401E9
     */
    private void updateVariousInformChgInfo(
        VariousInformParams l_beforeChgVariousInformParams,
        VariousInformParams l_afterChgVariousInformParams,
        String l_strRequestNumber,
        String l_strDataCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " updateVariousInformChgInfo("
            + "VariousInformParams, VariousInformParams, String, String)";
        log.entering(STR_METHOD_NAME);

        // DB更新仕様「分配金振替口座伝票変更_各種連絡テーブル.xls」参照
        // ［更新条件］
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            VariousInformParams l_updateVariousInformParams = new VariousInformParams();

            l_updateVariousInformParams = l_afterChgVariousInformParams;

            // 証券会社コード = 変更前各種連絡行.get証券会社コード（）
            l_updateVariousInformParams.setInstitutionCode(
                l_beforeChgVariousInformParams.getInstitutionCode());

            // 連絡種別 = 変更前各種連絡行.get連絡種別（）
            l_updateVariousInformParams.setInformDiv(
                l_beforeChgVariousInformParams.getInformDiv());

            // 識別コード = 変更前各種連絡行.get識別コード（）
            l_updateVariousInformParams.setRequestNumber(
                l_beforeChgVariousInformParams.getRequestNumber());

            // 部店コード = 変更前各種連絡行.get部店コード（）
            l_updateVariousInformParams.setBranchCode(
                l_beforeChgVariousInformParams.getBranchCode());

            // 顧客コード
            if (WEB3StringTypeUtility.isNotEmpty(l_afterChgVariousInformParams.getAccountCode()))
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                        l_afterChgVariousInformParams.getInstitutionCode(),
                        l_afterChgVariousInformParams.getBranchCode(),
                        l_afterChgVariousInformParams.getAccountCode());
                //顧客コード
                l_updateVariousInformParams.setAccountCode(l_mainAccount.getAccountCode());
            }

            // 扱者コード
            l_updateVariousInformParams.setTraderCode(
                l_beforeChgVariousInformParams.getTraderCode());

            // 更新条件
            // 更新者コード(管理者コード)
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();

            String l_strAdministratorCode = l_administrator.getAdministratorCode();
            l_updateVariousInformParams.setLastUpdater(l_strAdministratorCode);

            // 作成日時
            l_updateVariousInformParams.setCreatedTimestamp(
                l_beforeChgVariousInformParams.getCreatedTimestamp());

            // 更新日時
            l_updateVariousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            // 伝票作成状況(1：作成済)
            l_updateVariousInformParams.setStatus(WEB3VoucherCreateStatusDef.CREATE_COMPLETE);

            // エラー理由コード
            l_updateVariousInformParams.setErrorReasonCode(null);

            // 伝票識別コード(引数:識別コード)
            l_updateVariousInformParams.setOrderRequestNumber(l_strRequestNumber);

            // データコード(引数:データコード)
            l_updateVariousInformParams.setRequestCode(l_strDataCode);

            // 伝票送信日時
            l_updateVariousInformParams.setSendTimestamp(null);

            // 伝票受信日時
            l_updateVariousInformParams.setReceiptTimestamp(null);

            l_queryProcessor.doUpdateQuery(l_updateVariousInformParams);
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
     * (set顧客情報)<BR>
     * 顧客情報を取得し、利金・分配金・売却代金振込先情報にセットする<BR>
     * <BR>
     * １）拡張アカウントマネージャ.get顧客()をコールし、<BR>
     * 　@　@　@　@顧客オブジェクトを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@証券会社コード： 引数.証券会社コード<BR>
     * 　@部店コード： 引数.振込先情報.部店コード<BR>
     * 　@口座コード： 引数.振込先情報.口座コード<BR>
     * <BR>
     * 　@※顧客オブジェクトを取得できない場合、リターンする。<BR>
     * <BR>
     * ２）顧客.getDataSourceObject()をコールし、顧客Rowを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@なし<BR>
     * <BR>
     * ３）以下のとおりに、プロパティをセットする。<BR>
     * <BR>
     * 　@引数.振込先情報.顧客名（漢字） = 顧客Row.名前（苗字）<BR>
     * 　@引数.振込先情報.顧客名（カナ） = 顧客Row.名前（苗字１）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_afterChgVariousInformParams - (変更後各種連絡行)<BR>
     * 変更後各種連絡行
     * @@param l_transferInfo - (振込先情報)<BR>
     * 振込先情報
     * @@throws WEB3BaseException
     * @@roseuid 46539C9401E9
     */
    private void setAccountInfo(String l_strInstitutionCode , WEB3AdminInformProfDistTransferInfo l_transferInfo) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " setAccountInfo("
            + "WEB3AdminInformProfDistTransferInfo)";
        log.entering(STR_METHOD_NAME);
        
        //１）拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する。
        //　@[引数]
        //　@証券会社コード： 引数.証券会社コード
        //　@部店コード： 引数.部店コード
        //　@口座コード： 引数.口座コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
            		l_strInstitutionCode,
            		l_transferInfo.branchCode,
            		l_transferInfo.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //２）顧客.getDataSourceObject()をコールし、顧客Rowを取得する。
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //３）以下のとおりに、プロパティをセットする。
        //　@引数.振込先情報.顧客名（漢字） = 顧客Row.名前（苗字）
        //　@引数.振込先情報.顧客名（カナ） = 顧客Row.名前（苗字１）
        l_transferInfo.accountName = l_mainAcountRow.getFamilyName();
        l_transferInfo.accountNameKana = l_mainAcountRow.getFamilyNameAlt1();

        
        log.exiting(STR_METHOD_NAME);
    }
}
@
