head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子交付サービス登録・変更サービスImpl(WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 張騰宇(中訊) 新規作成 仕様変更モデルNo.278 281 282 ＤＢ更新仕様064 065
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.accountinfo.WEB3AccInfoAccopenConditionRegAcceptVoucher;
import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.define.WEB3AccInfoEleDeliveryFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoEleDeliveryInfo;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceResponse;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoElecDeliveryRegisterChangeService;
import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PosReportCycleDivDef;
import webbroker3.common.define.WEB3PosReportDivDef;
import webbroker3.common.define.WEB3ReportDivDef;
import webbroker3.common.define.WEB3ReportRegDivDef;
import webbroker3.common.define.WEB3TradingReportDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.EleDeliveryManagementDao;
import webbroker3.gentrade.data.EleDeliveryManagementParams;
import webbroker3.gentrade.data.EleDeliveryManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (電子交付サービス登録・変更サービスImpl)<BR>
 * 電子交付サービス登録・変更サービス実装クラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccInfoElecDeliveryRegisterChangeServiceImpl extends WEB3AccInfoClientRequestService 
    implements WEB3AccInfoElecDeliveryRegisterChangeService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.class);

    /**
     * 電子交付サービス登録・変更処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、電子交付サービス登録・変更入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、電子交付サービス登録・変更完了リクエストの場合 <BR>
     * 　@－submit変更()をコールする。 <BR>
     * ○ 引数のリクエストデータが、電子交付情報一覧リクエストの場合 <BR>
     * 　@－get電子交付情報一覧()をコールする。 <BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (l_request instanceof WEB3AccInfoElecDeliveryRegisterChangeInputRequest)
        {
            l_response = this.getInputScreen((WEB3AccInfoElecDeliveryRegisterChangeInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)
        {
            l_response = this.submitChangeScreen((WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AccInfoElecDeliveryApyReferenceRequest)
        {
            l_response = this.getEleDeliveryInfoList((WEB3AccInfoElecDeliveryApyReferenceRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 電子交付サービス登録・変更入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（電子交付サービス登録・変更）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AccInfoElecDeliveryRegisterChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoElecDeliveryRegisterChangeInputResponse getInputScreen(
        WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="getInputScreen(WEB3AccInfoElecDeliveryRegisterChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AccInfoElecDeliveryRegisterChangeInputResponse l_response = null;

        //validate( )
        l_request.validate();

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get顧客( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();

        //get代理入力者( )
        Trader l_trader = this.getTrader();

        //lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        //[引数]
        //　@証券会社コード = get顧客().getInstitution().getInstitutionCode()の戻り値
        //　@部店コード = get顧客().getBranch().getBranchCode()の戻り値
        //　@顧客コード = get顧客().getAccountCode()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_accManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode());

        //update電子交付管理(顧客, リクエストデータ, String)
        //[update電子交付管理()に指定する引数]
        //顧客：　@get顧客()の戻り値
        //リクエストデータ：　@null
        //扱者コード：get代理入力者（）.扱者コード
        String l_strTraderCode = null;
        if (l_trader != null)
        {
            l_strTraderCode = l_trader.getTraderCode();
        }
        this.updateElecDeliveryManagement(l_mainAccount, null, l_strTraderCode);

        l_response = (WEB3AccInfoElecDeliveryRegisterChangeInputResponse)l_request.createResponse();

        //リクエスト.電子交付申込フラグ＝”0”の場合、そのままreturn
        if (WEB3AccInfoEleDeliveryFlagDef.NOT_APPLY.equals(l_request.eleDeliveryFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //create電子交付情報(顧客)
        WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = this.createEleDeliveryInfo(l_mainAccount);

        //プロパティセット
        l_response.eleDeliveryInfo = l_eleDeliveryInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更画面)<BR>
     * 電子交付サービス登録・変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（電子交付サービス登録・変更）submit変更」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse submitChangeScreen(
        WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="submitChangeScreen(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse l_response = null;

        //validate( )
        l_request.validate();

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get顧客( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();

        //lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        //[引数]
        //　@証券会社コード = get顧客().getInstitution().getInstitutionCode()の戻り値
        //　@部店コード = get顧客().getBranch().getBranchCode()の戻り値
        //　@顧客コード = get顧客().getAccountCode()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_accManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode());

        //get代理入力者( )
        Trader l_trader = this.getTrader();

        //get電子交付管理(顧客)
        EleDeliveryManagementParams l_eleDeliveryManagementParams = this.getEleDeliveryManagement(l_mainAccount);

        //validate変更項目(電子交付管理Param, 電子交付サービス登録・変更完了リクエスト, boolean)
        //電子交付管理行：　@get電子交付管理（）の戻り値
        //リクエストデータ：　@引数のリクエストデータ
        //代行入力フラグ：　@get代理入力者（）＝nullの場合、falseを返る。
        //　@　@　@　@　@　@　@　@　@　@　@以外の場合は、trueを返る。
        boolean l_blnInputFlag = false;
        if (l_trader != null)
        {
            l_blnInputFlag = true;
        }
        this.validateChangeItem(l_eleDeliveryManagementParams, l_request, l_blnInputFlag);

        // update電子交付管理(顧客, リクエストデータ, String)
        String l_strTraderCode = null;
        if (l_trader != null)
        {
            l_strTraderCode = l_trader.getTraderCode();
        }
        this.updateElecDeliveryManagement(l_mainAccount, l_request, l_strTraderCode);

        //is電子交付情報変更（SONAR）(電子交付サービス登録・変更完了リクエスト)
        boolean l_blnIsEleDeliveryInfoChangeSonar = this.isEleDeliveryInfoChangeSonar(l_request);

        //is電子交付情報変更（SONAR）()の戻り値＝trueの場合、以下処理を行なう
        if (l_blnIsEleDeliveryInfoChangeSonar)
        {
            //get新規識別コード(証券会社コード : String, 部店コード : String, 銘柄タイプ : ProductTypeEnum)
            WEB3HostReqOrderNumberManageService l_orderNumberManagerService =
                (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
            String l_strNewNumber = l_orderNumberManagerService.getNewNumber(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                ProductTypeEnum.OTHER);

            //create取報・取残電子交付・特定口座登録行(電子交付管理Param,
            //　@電子交付サービス登録・変更完了リクエスト, String)
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                this.createHostConditionRegVoucher(
                    l_eleDeliveryManagementParams,
                    l_request,
                    l_strTraderCode);

            //取報・取残電子交付・特定口座登録
            WEB3AccInfoAccopenConditionRegAcceptVoucher l_conditionRegAcceptVoucher =
                new WEB3AccInfoAccopenConditionRegAcceptVoucher(
                    l_hostConditionRegVoucherParams,
                    l_strNewNumber);

            //save取報・取残電子交付・特定口座登録（GI311）キュー( )
            l_conditionRegAcceptVoucher.saveHostConditionRegVoucherParams();
        }

        l_response = (WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (update電子交付管理)<BR>
     * 電子交付管理テーブルの更新を行なう。<BR>
     * <BR>
     * [検索条件] <BR>
     * 　@　@証券会社コード：顧客.証券会社コード <BR>
     * 　@　@部店コード：顧客.部店コード <BR>
     * 　@　@口座コード：顧客.口座コード <BR>
     * 　@　@<BR>
     *  検索結果が取得できる場合、 <BR>
     * 　@ＤＢ更新を行う。 <BR>
     * 　@入力の場合、<BR>
     * 　@「電子交付サービス登録・変更_電子交付管理テーブル.xls#電子交付管理テーブル（入力）」参照。<BR>
     *   　@　@※電子交付申込チェックフラグについて、<BR>
     * 　@　@　@検索したレコートの電子交付申込チェックフラグ="0：未済み"の場合、<BR>
     * 　@　@　@該当レコートの電子交付申込チェックフラグ「1：済み」に更新されます。<BR>
     * 　@　@　@以外の場合、更新されない。<BR>
     * 　@完了の場合、<BR>
     * 　@「電子交付サービス登録・変更_電子交付管理テーブル.xls#電子交付管理テーブル（完了）」参照。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_strTraderCode - (扱者コード)<BR>
     * 扱者コード<BR>
     * @@throws WEB3BaseException
     */
    protected void updateElecDeliveryManagement(
        WEB3GentradeMainAccount l_mainAccount,
        WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request,
        String l_strTraderCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="updateElecDeliveryManagement(" +
            "WEB3GentradeMainAccount, WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest, String)";
        log.entering(STR_METHOD_NAME);

        //電子交付管理テーブルの更新を行なう。
        //[検索条件]
        //　@　@証券会社コード：顧客.証券会社コード
        //　@　@部店コード：顧客.部店コード
        //　@　@口座コード：顧客.口座コード
        EleDeliveryManagementRow l_eleDeliveryManagementRow = null;
        try
        {
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode());
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
        if (l_eleDeliveryManagementRow == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        else
        {
            EleDeliveryManagementParams l_params = new EleDeliveryManagementParams(l_eleDeliveryManagementRow);
            //入力の場合
            //「電子交付サービス登録・変更_電子交付管理テーブル.xls#電子交付管理テーブル（入力）」参照。
            if (l_request == null)
            {
                //電子交付管理.電子交付申込チェックフラグ="0：未済み"の場合、　@「1：済み」
                //以外の場合、既存値
                if (l_params.getEleDelRegiFlag() == 0)
                {
                    l_params.setEleDelRegiFlag(1);
                }
                //電子交付申込チェック日時:処理日時
                l_params.setEleDelRegiUpdDate(GtlUtils.getSystemTimestamp());
                //更新者コード:顧客ログイン時：口座コード
                //             代行入力：扱者コード
                if (l_strTraderCode == null)
                {
                    l_params.setLastUpdater(l_mainAccount.getAccountCode());
                }
                else
                {
                    l_params.setLastUpdater(l_strTraderCode);
                }
                //更新日付:処理日時
                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }
            else
            {
                //完了の場合
                //「電子交付サービス登録・変更_電子交付管理テーブル.xls#電子交付管理テーブル（完了）」参照。

                if (l_request.tradingReportDiv != null)
                {
                    //取引報告書交付区分
                    //リクエスト.取引報告書交付区分≠nullの場合、リクエスト.取引報告書交付区分
                    //以外の場合、既存値
                    l_params.setTradingReportDiv(l_request.tradingReportDiv);
                    //取引報告書申込区分
                    //リクエスト.取引報告書交付区分≠nullの場合、0:申込中。
                    //以外の場合、既存値
                    l_params.setTradingReportRegDiv(WEB3ReportRegDivDef.APPLYING);
                    //取引報告書交付区分更新日時
                    //リクエスト.取引報告書交付区分≠nullの場合、処理日時
                    //以外の場合、既存値
                    l_params.setTradingReportDivUpdDate(GtlUtils.getSystemTimestamp());
                }

                if (l_request.positionReportDiv != null)
                {
                    //取引残高報告書交付区分
                    //リクエスト.取引残高報告書交付区分≠nullの場合、
                    //　@　@-リクエスト.取引残高報告書交付区分="0：郵便配布"、「0：郵便配布」をセットする。
                    //　@　@-リクエスト.取引残高報告書交付区分="1：電子配布"、「9：電子配布」をセットする。
                    //以外の場合、既存値
                    if (WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))
                    {
                        l_params.setPositionReportDiv(WEB3PosReportDivDef.MAIL_DISTRIBUTION);
                    }
                    else if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.positionReportDiv))
                    {
                        l_params.setPositionReportDiv(WEB3PosReportDivDef.ELECTRON_DISTRIBUTION);
                    }
                    //取引残高報告書申込区分
                    //リクエスト.取引残高報告書交付区分≠nullの場合、0:申込中。
                    //以外の場合、既存値
                    l_params.setPositionReportRegDiv(WEB3ReportRegDivDef.APPLYING);
                    //取引残高報告書交付区分更新日時
                    //リクエスト.取引残高報告書交付区分≠nullの場合、処理日時
                    //以外の場合、既存値
                    l_params.setPositionReportDivUpdDate(GtlUtils.getSystemTimestamp());
                }

                if (l_request.opeReportDiv != null)
                {
                    //運用報告書交付区分
                    //リクエスト.運用報告書交付区分≠nullの場合、リクエスト.運用報告書交付区分
                    //以外の場合、既存値
                    l_params.setOpeReportDiv(l_request.opeReportDiv);
                    //運用報告書申込区分
                    //リクエスト.運用報告書交付区分≠nullの場合、0:申込中。
                    //以外の場合、既存値
                    l_params.setOpeReportRegDiv(WEB3ReportRegDivDef.APPLYING);
                    //運用報告書交付区分更新日時
                    //リクエスト.運用報告書交付区分≠nullの場合、処理日時
                    //以外の場合、既存値
                    l_params.setOpeReportDivUpdDate(GtlUtils.getSystemTimestamp());
                }

                if (l_request.ordRulReportDiv != null)
                {
                    //約款・規定集報告書交付区分
                    //リクエスト.約款・規定集報告書交付区分≠nullの場合、リクエスト.約款・規定集報告書交付区分
                    //以外の場合、既存値
                    l_params.setOrdRulReportDiv(l_request.ordRulReportDiv);
                    //約款・規定集報告書申込区分
                    //リクエスト.約款・規定集報告書交付区分≠nullの場合、0:申込中。
                    //以外の場合、既存値
                    l_params.setOrdRulRepRegDiv(WEB3ReportRegDivDef.APPLYING);
                    //約款・規定集報告書交付区分更新日時
                    //リクエスト.約款・規定集報告書交付区分≠nullの場合、処理日時
                    //以外の場合、既存値
                    l_params.setOrdRulReportDivUpdDate(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div1 != null)
                {
                    //書面１交付区分
                    //リクエスト.書面１交付区分≠nullの場合、リクエスト.書面１交付区分
                    //以外の場合、既存値
                    l_params.setReportDiv1(l_request.report_div1);
                    //書面１申込区分
                    //リクエスト.書面１交付区分≠nullの場合、0:申込中。
                    //以外の場合、既存値
                    l_params.setReportRegDiv1(WEB3ReportRegDivDef.APPLYING);
                    //書面１交付区分更新日時
                    //リクエスト.書面１交付区分≠nullの場合、処理日時
                    //以外の場合、既存値
                    l_params.setReportDivUpdDate1(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div2 != null)
                {
                    //書面２交付区分
                    //リクエスト.書面２交付区分≠nullの場合、リクエスト.書面２交付区分
                    //以外の場合、既存値
                    l_params.setReportDiv2(l_request.report_div2);
                    //書面２申込区分
                    //リクエスト.書面２交付区分≠nullの場合、0:申込中。
                    //以外の場合、既存値
                    l_params.setReportRegDiv2(WEB3ReportRegDivDef.APPLYING);
                    //書面２交付区分更新日時
                    //リクエスト.書面２交付区分≠nullの場合、処理日時
                    //以外の場合、既存値
                    l_params.setReportDivUpdDate2(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div3 != null)
                {
                    //書面３交付区分
                    //リクエスト.書面３交付区分≠nullの場合、リクエスト.書面３交付区分
                    //以外の場合、既存値
                    l_params.setReportDiv3(l_request.report_div3);
                    //書面３申込区分
                    //リクエスト.書面３交付区分≠nullの場合、0:申込中。
                    //以外の場合、既存値
                    l_params.setReportRegDiv3(WEB3ReportRegDivDef.APPLYING);
                    //書面３交付区分更新日時
                    //リクエスト.書面３交付区分≠nullの場合、処理日時
                    //以外の場合、既存値
                    l_params.setReportDivUpdDate3(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div4 != null)
                {
                    //書面４交付区分
                    //リクエスト.書面４交付区分≠nullの場合、リクエスト.書面４交付区分
                    //以外の場合、既存値
                    l_params.setReportDiv4(l_request.report_div4);
                    //書面４申込区分
                    //リクエスト.書面４交付区分≠nullの場合、0:申込中。
                    //以外の場合、既存値
                    l_params.setReportRegDiv4(WEB3ReportRegDivDef.APPLYING);
                    //書面４交付区分更新日時
                    //リクエスト.書面４交付区分≠nullの場合、処理日時
                    //以外の場合、既存値
                    l_params.setReportDivUpdDate4(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div5 != null)
                {
                    //書面５交付区分
                    //リクエスト.書面５交付区分≠nullの場合、リクエスト.書面５交付区分
                    //以外の場合、既存値
                    l_params.setReportDiv5(l_request.report_div5);
                    //書面５申込区分
                    //リクエスト.書面５交付区分≠nullの場合、0:申込中。
                    //以外の場合、既存値
                    l_params.setReportRegDiv5(WEB3ReportRegDivDef.APPLYING);
                    //書面５交付区分更新日時
                    //リクエスト.書面５交付区分≠nullの場合、処理日時
                    //以外の場合、既存値
                    l_params.setReportDivUpdDate5(GtlUtils.getSystemTimestamp());
                }

                //更新者コード
                //顧客ログイン時：口座コード
                //代行入力：扱者コード
                if (l_strTraderCode == null)
                {
                    l_params.setLastUpdater(l_mainAccount.getAccountCode());
                }
                else
                {
                    l_params.setLastUpdater(l_strTraderCode);
                }
                //更新日付
                //処理日時
                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_params);
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
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate変更項目)<BR>
     * 登録された項目が正しいかをチェックする。<BR>
     * <BR>
     * １）　@リクエスト.取引報告書交付区分≠nullの場合で且つ<BR>
     * 　@下記条件をいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@①@get電子交付管理().取引報告書交付区分 ＝ リクエスト.取引報告書交付区分<BR>
     * 　@②get電子交付管理().取引報告書申込区分　@＝　@"0:申込中"<BR>
     * 　@③get電子交付管理().取引報告書申込区分　@＝　@"9:SONAR送信済"<BR>
     * 　@④代行入力フラグは"false"である場合は、リクエスト.取引報告書 ＝ "0：郵便配布"<BR>
     * 　@<BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03210<BR>
     * ２）　@リクエスト.取引残高報告書交付区≠nullの場合で且つ<BR>
     * 　@下記条件をいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@①@get電子交付管理().取引残高報告書交付区分=="0：郵便配布" <BR>
     * 且つ リクエスト.取引残高報告書交付区分=="0：郵便配布"<BR>
     * 　@②get電子交付管理().取引残高報告書交付区分=="1：郵便配布（受渡都度作成）" <BR>
     * 且つ リクエスト.取引残高報告書交付区分=="0：郵便配布"<BR>
     * 　@③get電子交付管理().取引残高報告書交付区分=="9：電子配布" <BR>
     * 且つ リクエスト.取引残高報告書交付区分=="1：電子配布"<BR>
     * 　@④get電子交付管理().取引残高報告書申込区分　@＝　@"0:申込中"<BR>
     * 　@⑤get電子交付管理().取引残高報告書申込区分　@＝　@"9:SONAR送信済"<BR>
     * 　@⑥代行入力フラグは"false"である場合は、リクエスト.取引残高報告書交付区分 ＝ "0：郵便配布"<BR>
     * <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03210<BR>
     * ３）　@リクエスト.運用報告書交付区分≠nullの場合で且つ<BR>
     * 　@下記条件をいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@①@get電子交付管理().運用報告書交付区分 ＝ リクエスト.運用報告書交付区分<BR>
     * 　@②get電子交付管理().運用報告書申込区分　@＝　@"0:申込中"<BR>
     * 　@③get電子交付管理().運用報告書申込区分　@＝　@"9:SONAR送信済"<BR>
     * 　@④代行入力フラグは"false"である場合は、リクエスト.運用報告書交付区分 ＝ "0：郵便配布"<BR>
     * <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03210<BR>
     * ４）　@リクエスト.約款・規定集報告書交付区分≠nullの場合で且つ<BR>
     * 　@下記条件をいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@①@get電子交付管理().約款・規定集報告書交付区分 ＝ リクエスト.約款・規定集報告書交付区分<BR>
     * 　@②get電子交付管理().約款・規定集報告書申込区分　@＝　@"0:申込中"<BR>
     * 　@③代行入力フラグは"false"である場合は、リクエスト.約款・規定集報告書交付区分 ＝ "0：郵便配布"<BR>
     * <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03210<BR>
     * ５）　@リクエスト.書面１交付区分≠nullの場合で且つ<BR>
     * 　@下記条件をいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@①@get電子交付管理().書面１交付区分 ＝ リクエスト.書面１交付区分<BR>
     * 　@②get電子交付管理().書面１申込区分　@＝　@"0:申込中"<BR>
     * 　@③代行入力フラグは"false"である場合は、リクエスト.書面１交付区分 ＝ "0：郵便配布"<BR>
     * <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03210<BR>
     * ６）　@リクエスト.書面２交付区分≠nullの場合で且つ<BR>
     * 　@下記条件をいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@①@get電子交付管理().書面２交付区分 ＝ リクエスト.書面２交付区分<BR>
     * 　@②get電子交付管理().書面２申込区分　@＝　@"0:申込中"<BR>
     * 　@③代行入力フラグは"false"である場合は、リクエスト.書面２交付区分 ＝ "0：郵便配布"<BR>
     * <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03210<BR>
     * ７）　@リクエスト.書面３交付区分≠nullの場合で且つ<BR>
     * 　@下記条件をいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@①@get電子交付管理().書面３交付区分 ＝ リクエスト.書面３交付区分<BR>
     * 　@②get電子交付管理().書面３申込区分　@＝　@"0:申込中"<BR>
     * 　@③代行入力フラグは"false"である場合は、リクエスト.書面３交付区分 ＝ "0：郵便配布"<BR>
     * <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03210<BR>
     * ８）　@リクエスト.書面４交付区分≠nullの場合で且つ<BR>
     * 　@下記条件をいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@①@get電子交付管理().書面４交付区分 ＝ リクエスト.書面４交付区分<BR>
     * 　@②get電子交付管理().書面４申込区分　@＝　@"0:申込中"<BR>
     * 　@③代行入力フラグは"false"である場合は、リクエスト.書面４交付区分 ＝ "0：郵便配布"<BR>
     * <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03210<BR>
     * ９）　@リクエスト.書面５交付区分≠nullの場合で且つ<BR>
     * 　@下記条件をいずれかに該当する場合は、例外をスローする。<BR>
     * <BR>
     * 　@①@get電子交付管理().書面５交付区分 ＝ リクエスト.書面５交付区分<BR>
     * 　@②get電子交付管理().書面５申込区分　@＝　@"0:申込中"<BR>
     * 　@③代行入力フラグは"false"である場合は、リクエスト.書面５交付区分 ＝ "0：郵便配布"<BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03210<BR>
     * @@param l_params - (電子交付管理行)<BR>
     * 電子交付管理行<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_blnInputFlag - (代行入力フラグ)<BR>
     * 代行入力フラグ<BR>
     * @@throws WEB3BaseException
     */
    protected void validateChangeItem(
        EleDeliveryManagementParams l_params,
        WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request,
        boolean l_blnInputFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validateChangeItem(" +
            "EleDeliveryManagementParams, WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest, boolean)";
        log.entering(STR_METHOD_NAME);

        //１）　@リクエスト.取引報告書交付区分≠nullの場合で且つ
        //　@下記条件をいずれかに該当する場合は、例外をスローする。
        //　@①@get電子交付管理().取引報告書交付区分 ＝ リクエスト.取引報告書交付区分
        //　@②get電子交付管理().取引報告書申込区分　@＝　@"0:申込中"
        //　@③get電子交付管理().取引報告書申込区分　@＝　@"9:SONAR送信済"
        //　@④代行入力フラグは"false"である場合は、リクエスト.取引報告書 ＝ "0：郵便配布"
        if (l_request.tradingReportDiv != null
            && (l_request.tradingReportDiv.equals(l_params.getTradingReportDiv())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getTradingReportRegDiv())
                || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_params.getTradingReportRegDiv())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.tradingReportDiv))))
        {
            log.debug("電子交付サービス申込を行うことができません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付サービス申込を行うことができません。");
        }

        //２）　@リクエスト.取引残高報告書交付区≠nullの場合で且つ
        //　@下記条件をいずれかに該当する場合は、例外をスローする。
        //　@①@get電子交付管理().取引残高報告書交付区分=="0：郵便配布"
        //且つ リクエスト.取引残高報告書交付区分=="0：郵便配布"
        //　@②get電子交付管理().取引残高報告書交付区分=="1：郵便配布（受渡都度作成）"
        //且つ リクエスト.取引残高報告書交付区分=="0：郵便配布"
        //　@③get電子交付管理().取引残高報告書交付区分=="9：電子配布"
        //且つ リクエスト.取引残高報告書交付区分=="1：電子配布"
        //　@④get電子交付管理().取引残高報告書申込区分　@＝　@"0:申込中"
        //　@⑤get電子交付管理().取引残高報告書申込区分　@＝　@"9:SONAR送信済"
        //　@⑥代行入力フラグは"false"である場合は、リクエスト.取引残高報告書交付区分 ＝ "0：郵便配布"
        if (l_request.positionReportDiv != null
            && ((WEB3PosReportDivDef.MAIL_DISTRIBUTION.equals(l_params.getPositionReportDiv())
                    && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))
                || (WEB3PosReportDivDef.MAIL_DISTRIBUTION_EACH_TIME.equals(l_params.getPositionReportDiv())
                    && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))
                || (WEB3PosReportDivDef.ELECTRON_DISTRIBUTION.equals(l_params.getPositionReportDiv())
                    && WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.positionReportDiv))
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getPositionReportRegDiv())
                || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_params.getPositionReportRegDiv())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))))
        {
            log.debug("電子交付サービス申込を行うことができません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付サービス申込を行うことができません。");
        }

        //３）　@リクエスト.運用報告書交付区分≠nullの場合で且つ
        //　@下記条件をいずれかに該当する場合は、例外をスローする。
        //　@①@get電子交付管理().運用報告書交付区分 ＝ リクエスト.運用報告書交付区分
        //　@②get電子交付管理().運用報告書申込区分　@＝　@"0:申込中"
        //　@③get電子交付管理().運用報告書申込区分　@＝　@"9:SONAR送信済"
        //　@④代行入力フラグは"false"である場合は、リクエスト.運用報告書交付区分 ＝ "0：郵便配布"
        if (l_request.opeReportDiv != null
            && (l_request.opeReportDiv.equals(l_params.getOpeReportDiv())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getOpeReportRegDiv())
                || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_params.getOpeReportRegDiv())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.opeReportDiv))))
        {
            log.debug("電子交付サービス申込を行うことができません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付サービス申込を行うことができません。");
        }

        //４）　@リクエスト.約款・規定集報告書交付区分≠nullの場合で且つ
        //　@下記条件をいずれかに該当する場合は、例外をスローする。
        //　@①@get電子交付管理().約款・規定集報告書交付区分 ＝ リクエスト.約款・規定集報告書交付区分
        //　@②get電子交付管理().約款・規定集報告書申込区分　@＝　@"0:申込中"
        //　@③代行入力フラグは"false"である場合は、リクエスト.約款・規定集報告書交付区分 ＝ "0：郵便配布"
        if (l_request.ordRulReportDiv != null
            && (l_request.ordRulReportDiv.equals(l_params.getOrdRulReportDiv())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getOrdRulRepRegDiv())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.ordRulReportDiv))))
        {
            log.debug("電子交付サービス申込を行うことができません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付サービス申込を行うことができません。");
        }

        //５）　@リクエスト.書面１交付区分≠nullの場合で且つ
        //　@下記条件をいずれかに該当する場合は、例外をスローする。
        //　@①@get電子交付管理().書面１交付区分 ＝ リクエスト.書面１交付区分
        //　@②get電子交付管理().書面１申込区分　@＝　@"0:申込中"
        //　@③代行入力フラグは"false"である場合は、リクエスト.書面１交付区分 ＝ "0：郵便配布"
        if (l_request.report_div1 != null
            && (l_request.report_div1.equals(l_params.getReportDiv1())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv1())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div1))))
        {
            log.debug("電子交付サービス申込を行うことができません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付サービス申込を行うことができません。");
        }

        //６）　@リクエスト.書面２交付区分≠nullの場合で且つ
        //　@下記条件をいずれかに該当する場合は、例外をスローする。
        //　@①@get電子交付管理().書面２交付区分 ＝ リクエスト.書面２交付区分
        //　@②get電子交付管理().書面２申込区分　@＝　@"0:申込中"
        //　@③代行入力フラグは"false"である場合は、リクエスト.書面２交付区分 ＝ "0：郵便配布"
        if (l_request.report_div2 != null
            && (l_request.report_div2.equals(l_params.getReportDiv2())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv2())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div2))))
        {
            log.debug("電子交付サービス申込を行うことができません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付サービス申込を行うことができません。");
        }

        //７）　@リクエスト.書面３交付区分≠nullの場合で且つ
        //　@下記条件をいずれかに該当する場合は、例外をスローする。
        //　@①@get電子交付管理().書面３交付区分 ＝ リクエスト.書面３交付区分
        //　@②get電子交付管理().書面３申込区分　@＝　@"0:申込中"
        //　@③代行入力フラグは"false"である場合は、リクエスト.書面３交付区分 ＝ "0：郵便配布"
        if (l_request.report_div3 != null
            && (l_request.report_div3.equals(l_params.getReportDiv3())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv3())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div3))))
        {
            log.debug("電子交付サービス申込を行うことができません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付サービス申込を行うことができません。");
        }

        //８）　@リクエスト.書面４交付区分≠nullの場合で且つ
        //　@下記条件をいずれかに該当する場合は、例外をスローする。
        //　@①@get電子交付管理().書面４交付区分 ＝ リクエスト.書面４交付区分
        //　@②get電子交付管理().書面４申込区分　@＝　@"0:申込中"
        //　@③代行入力フラグは"false"である場合は、リクエスト.書面４交付区分 ＝ "0：郵便配布"
        if (l_request.report_div4 != null
            && (l_request.report_div4.equals(l_params.getReportDiv4())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv4())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div4))))
        {
            log.debug("電子交付サービス申込を行うことができません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付サービス申込を行うことができません。");
        }

        //９）　@リクエスト.書面５交付区分≠nullの場合で且つ
        //　@下記条件をいずれかに該当する場合は、例外をスローする。
        //　@①@get電子交付管理().書面５交付区分 ＝ リクエスト.書面５交付区分
        //　@②get電子交付管理().書面５申込区分　@＝　@"0:申込中"
        //　@③代行入力フラグは"false"である場合は、リクエスト.書面５交付区分 ＝ "0：郵便配布"
        if (l_request.report_div5 != null
            && (l_request.report_div5.equals(l_params.getReportDiv5())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv5())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div5))))
        {
            log.debug("電子交付サービス申込を行うことができません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付サービス申込を行うことができません。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is電子交付情報変更（SONAR）)<BR>
     * 当該顧客が、SONARに関連の電子交付情報を変更するかどうかを判定する。 <BR>
     * <BR>
     * １）　@以下条件をいずれか満たす場合、trueを返却する。 <BR>
     * <BR>
     * 　@　@①@　@リクエスト.取引報告書交付区分≠null <BR>
     * 　@　@②　@リクエスト.取引残高報告書交付区分≠null <BR>
     * 　@　@③　@リクエスト.運用報告書交付区分≠null <BR>
     * <BR>
     * ２）　@１）以外の場合、falseを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return boolean
     */
    protected boolean isEleDeliveryInfoChangeSonar(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME="isEleDeliveryInfoChangeSonar(" +
            "WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //１）　@以下条件をいずれか満たす場合、trueを返却する。
        //　@①@　@リクエスト.取引報告書交付区分≠null
        //　@②　@リクエスト.取引残高報告書交付区分≠null
        //　@③　@リクエスト.運用報告書交付区分≠null
        if (l_request.tradingReportDiv != null
            || l_request.positionReportDiv != null
            || l_request.opeReportDiv != null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            //２）　@１）以外の場合、falseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (create取報・取残電子交付・特定口座登録行)<BR>
     * 取報・取残電子交付・特定口座登録新規行を生成する。 <BR>
     * <BR>
     * １）　@行オブジェクト生成 <BR>
     * 　@取報・取残電子交付・特定口座登録Paramsオブジェクトを生成する。 <BR>
     * <BR>
     * 　@※取報・取残電子交付・特定口座登録ParamsはDDLより自動生成する。 <BR>
     * <BR>
     * ２）　@行オブジェクトにプロパティをセットする。 <BR>
     * 　@１）で生成した取報・取残電子交付・特定口座登録Paramsオブジェクトのプロパティに、<BR>
     * 以下の通りセットを行う。<BR>
     * <BR>
     * 　@取報・取残電子交付・特定口座登録Params.証券会社コード ＝ (引数)電子交付管理行.証券会社コード <BR>
     * 　@取報・取残電子交付・特定口座登録Params.部店コード ＝ (引数)電子交付管理行.部店コード <BR>
     * 　@取報・取残電子交付・特定口座登録Params.顧客コード ＝ (引数)電子交付管理行.顧客コード <BR>
     * 　@取報・取残電子交付・特定口座登録Params.扱者コード ＝ (引数) 扱者コード <BR>
     * 　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@定期 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@預り証 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@計算書 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.電子交付　@分配金・償還金 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@今回 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@開設日 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@次回 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@今回 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@開設日 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@次回 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.特定管理口座 ＝ null <BR>
     * 　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@電子交付（都度） ＝<BR>
     * 　@－(引数)リクエスト.取引残高報告書交付区分 ＝ nullの場合、null <BR>
     * 　@－(引数)リクエスト.取引残高報告書交付区分 ＝ 0：郵便配布の場合、0：不作成<BR>
     * 　@－(引数)リクエスト.取引残高報告書交付区分 ＝ 1：電子配布の場合、9：電子交付<BR>
     * 　@取報・取残電子交付・特定口座登録Params.電子交付　@取引報告書 ＝ <BR>
     * 　@－(引数)リクエスト.取引報告書交付区分 ＝ null の場合、null <BR>
     * 　@－(引数)リクエスト.取引報告書交付区分 ＝ 0：郵便配布の場合、０：非承諾<BR>
     * 　@－(引数)リクエスト.取引報告書交付区分 ＝ 1：電子配布の場合、１：承諾 <BR>
     * 　@取報・取残電子交付・特定口座登録Params.電子交付　@投信運用報告書 ＝ <BR>
     * 　@－(引数)リクエスト.運用報告書交付区分 ＝ null の場合、null <BR>
     * 　@－(引数)リクエスト.運用報告書交付区分 ＝ 0：郵便配布の場合、０：非承諾<BR>
     * 　@－(引数)リクエスト.運用報告書交付区分 ＝ 1：電子配布の場合、１：承諾 <BR>
     * ３）　@生成した行オブジェクトを返却する。<BR>
     * @@param l_params - (電子交付管理行)<BR>
     * 電子交付管理行<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_strTraderCode - (扱者コード)<BR>
     * 扱者コード<BR>
     * @@return HostConditionRegVoucherParams
     */
    protected HostConditionRegVoucherParams createHostConditionRegVoucher(
        EleDeliveryManagementParams l_params,
        WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request,
        String l_strTraderCode)
    {
        final String STR_METHOD_NAME="createHostConditionRegVoucher(" +
            "EleDeliveryManagementParams, WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@行オブジェクト生成
        //取報・取残電子交付・特定口座登録Paramsオブジェクトを生成する。
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams = new HostConditionRegVoucherParams();

        //２）　@行オブジェクトにプロパティをセットする。
        //１）で生成した取報・取残電子交付・特定口座登録Paramsオブジェクトのプロパティに、
        //　@以下の通りセットを行う。
        //　@取報・取残電子交付・特定口座登録Params.証券会社コード ＝ (引数)電子交付管理行.証券会社コード
        l_hostConditionRegVoucherParams.setInstitutionCode(l_params.getInstitutionCode());
        //　@取報・取残電子交付・特定口座登録Params.部店コード ＝ (引数)電子交付管理行.部店コード
        l_hostConditionRegVoucherParams.setBranchCode(l_params.getBranchCode());
        //　@取報・取残電子交付・特定口座登録Params.顧客コード ＝ (引数)電子交付管理行.顧客コード
        l_hostConditionRegVoucherParams.setAccountCode(l_params.getAccountCode());
        //　@取報・取残電子交付・特定口座登録Params.扱者コード ＝ (引数) 扱者コード
        l_hostConditionRegVoucherParams.setTraderCode(l_strTraderCode);
        //　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@定期 ＝ null
        l_hostConditionRegVoucherParams.setPosReportTermDiv(null);
        //　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@預り証 ＝ null
        l_hostConditionRegVoucherParams.setPosReportCertifDepoDiv(null);
        //　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@計算書 ＝ null
        l_hostConditionRegVoucherParams.setPosReportAccStateDiv(null);
        //　@取報・取残電子交付・特定口座登録Params.電子交付　@分配金・償還金 ＝ null
        l_hostConditionRegVoucherParams.setRefundEReportDiv(null);
        //　@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@今回 ＝ null
        l_hostConditionRegVoucherParams.setEquityTaxDiv(null);
        //　@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@開設日 ＝ null
        l_hostConditionRegVoucherParams.setEquitySpAccOpenDat(null);
        //　@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@次回 ＝ null
        l_hostConditionRegVoucherParams.setEquityTaxDivNext(null);
        //　@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@今回 ＝ null
        l_hostConditionRegVoucherParams.setMarginTaxDiv(null);
        //　@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@開設日 ＝ null
        l_hostConditionRegVoucherParams.setMarginSpAccOpenDat(null);
        //　@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@次回 ＝ null
        l_hostConditionRegVoucherParams.setMarginTaxDivNext(null);
        //　@取報・取残電子交付・特定口座登録Params.特定管理口座 ＝ null
        l_hostConditionRegVoucherParams.setSpMngAccOpenDiv(null);
        //　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@電子交付（都度） ＝
        //　@－(引数)リクエスト.取引残高報告書交付区分 ＝ nullの場合、null
        //　@－(引数)リクエスト.取引残高報告書交付区分 ＝ 0：郵便配布の場合、0：不作成
        //　@－(引数)リクエスト.取引残高報告書交付区分 ＝ 1：電子配布の場合、9：電子交付
        if (l_request.positionReportDiv == null)
        {
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(null);
        }
        else if (WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))
        {
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(WEB3PosReportCycleDivDef.NOT_CREATE);
        }
        else if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.positionReportDiv))
        {
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(WEB3PosReportCycleDivDef.ELECTRONIC_DELIVER);
        }
        //　@取報・取残電子交付・特定口座登録Params.電子交付　@取引報告書 ＝
        //　@－(引数)リクエスト.取引報告書交付区分 ＝ null の場合、null
        //　@－(引数)リクエスト.取引報告書交付区分 ＝ 0：郵便配布の場合、０：非承諾
        //　@－(引数)リクエスト.取引報告書交付区分 ＝ 1：電子配布の場合、１：承諾
        if (l_request.tradingReportDiv == null)
        {
            l_hostConditionRegVoucherParams.setTradingEReportDiv(null);
        }
        else if (WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.tradingReportDiv))
        {
            l_hostConditionRegVoucherParams.setTradingEReportDiv(WEB3ReportDivDef.NOT_ACCEPT);
        }
        else if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.tradingReportDiv))
        {
            l_hostConditionRegVoucherParams.setTradingEReportDiv(WEB3ReportDivDef.ACCEPT);
        }
        //　@取報・取残電子交付・特定口座登録Params.電子交付　@投信運用報告書 ＝
        //　@－(引数)リクエスト.運用報告書交付区分 ＝ null の場合、null
        //　@－(引数)リクエスト.運用報告書交付区分 ＝ 0：郵便配布の場合、０：非承諾
        //　@－(引数)リクエスト.運用報告書交付区分 ＝ 1：電子配布の場合、１：承諾
        if (l_request.opeReportDiv == null)
        {
            l_hostConditionRegVoucherParams.setInvEReportDiv(null);
        }
        else if (WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.opeReportDiv))
        {
            l_hostConditionRegVoucherParams.setInvEReportDiv(WEB3ReportDivDef.NOT_ACCEPT);
        }
        else if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.opeReportDiv))
        {
            l_hostConditionRegVoucherParams.setInvEReportDiv(WEB3ReportDivDef.ACCEPT);
        }

        //３）　@生成した行オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_hostConditionRegVoucherParams;
    }

    /**
     * (create電子交付情報)<BR>
     * 顧客オブジェクトより、電子交付情報メッセージデータを作成する。 <BR>
     * <BR>
     * １）　@電子交付情報オブジェクトを生成する。 <BR>
     * <BR>
     * ２）　@電子交付管理行の取得 <BR>
     * 　@　@　@電子交付管理テーブルにて以下の条件で検索する。 <BR>
     * 　@　@　@※該当行がなかった場合は、nullを返却 <BR>
     * <BR>
     * 　@　@　@[条件] <BR>
     * 　@　@　@電子交付管理テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode() <BR>
     * 　@　@　@電子交付管理テーブル.部店コード = 顧客.getBranch().getBranchCode() <BR>
     * 　@　@　@電子交付管理テーブル.顧客コード = 顧客.getAccountCode() <BR>
     * <BR>
     * ３）　@以下の通り、プロパティをセットし、返却する。 <BR>
     * <BR>
     * 　@　@電子交付情報.取引報告書交付状態区分 = 電子交付管理行.取引報告書交付区分 <BR>
     * 　@　@電子交付情報.取引報告書申込状態区分= <BR>
     * 　@　@ －（電子交付管理行.取引報告書申込区分 == 0：　@申込中 　@<BR>
     * 　@　@　@　@　@　@または　@9：　@SONAR送信済）の場合、0：　@申込中 。<BR> 
     * 　@　@ －（電子交付管理行.取引報告書申込区分 == 1：　@申込完了 　@<BR>
     * 　@　@　@　@　@　@または　@2：　@取消完了）の場合、 1：　@申込完了。<BR>
     * 　@　@電子交付情報.取引報告書交付状態区分更新日時 = 電子交付管理行.取引報告書交付区分更新日時 <BR>
     * 　@　@電子交付情報.取引残高報告書交付状態区分 = <BR>
     * 　@　@ －（電子交付管理行.取引残高報告書交付区分 == 0：郵便配布）の場合、0：郵便配布。 <BR>
     * 　@　@ －（電子交付管理行.取引残高報告書交付区分 == 1：郵便配布（受渡都度作成））の場合、0：郵便配布。 <BR>
     * 　@　@ －（電子交付管理行.取引残高報告書交付区分 == 9：電子配布）の場合、1：電子配布。 <BR>
     * 　@　@電子交付情報.取引残高報告書申込状態区分 = <BR>
     * 　@　@ －（電子交付管理行.取引残高報告書申込区分 == 0：　@申込中 　@<BR>
     * 　@　@　@　@　@　@または　@9：　@SONAR送信済）の場合、0：　@申込中 。 <BR>
     * 　@　@ －（電子交付管理行.取引残高報告書申込区分 == 1：　@申込完了 　@<BR>
     * 　@　@　@　@　@　@または　@2：　@取消完了）の場合、 1：　@申込完了。<BR>
     * 　@　@電子交付情報.取引残高報告書交付状態区分更新日時 = 電子交付管理行.取引残高報告書交付区分更新日時 <BR>
     * 　@　@電子交付情報.運用報告書交付状態区分 = 電子交付管理行.運用報告書交付区分 <BR>
     * 　@　@電子交付情報.運用報告書申込状態区分 = <BR>
     * 　@　@ －（電子交付管理行.運用報告書申込区分 == 0：　@申込中 　@<BR>
     * 　@　@　@　@　@　@または　@9：　@SONAR送信済）の場合、0：　@申込中 。<BR>
     * 　@　@ －（電子交付管理行.運用報告書申込区分 == 1：　@申込完了 　@<BR>
     * 　@　@　@　@　@　@または　@2：　@取消完了）の場合、 1：　@申込完了。<BR>
     * 　@　@電子交付情報.運用報告書交付状態区分更新日時 = 電子交付管理行.運用報告書交付区分更新日時 <BR>
     * 　@　@電子交付情報.約款・規定集報告書交付状態区分 = 電子交付管理行.約款・規定集報告書交付区分 <BR>
     * 　@　@電子交付情報.約款・規定集報告書申込状態区分 = 電子交付管理行.約款・規定集報告書申込区分 <BR>
     * 　@　@電子交付情報.約款・規定集報告書交付状態区分更新日時 = 電子交付管理行.規定集報告書交付区分更新日時 <BR>
     * 　@　@電子交付情報.書面１交付区分 = 電子交付管理行.書面１交付区分 <BR>
     * 　@　@電子交付情報.書面１申込区分 = 電子交付管理行.書面１申込区分 <BR>
     * 　@　@電子交付情報.書面１交付区分更新日時 = 電子交付管理行.書面１交付区分更新日時 <BR>
     * 　@　@電子交付情報.書面２交付区分 = 電子交付管理行.書面２交付区分 <BR>
     * 　@　@電子交付情報.書面２申込区分 = 電子交付管理行.書面２申込区分 <BR>
     * 　@　@電子交付情報.書面２交付区分更新日時 = 電子交付管理行.書面２交付区分更新日時 <BR>
     * 　@　@電子交付情報.書面３交付区分 = 電子交付管理行.書面３交付区分 <BR>
     * 　@　@電子交付情報.書面３申込区分 = 電子交付管理行.書面３申込区分 <BR>
     * 　@　@電子交付情報.書面３交付区分更新日時 = 電子交付管理行.書面３交付区分更新日時 <BR>
     * 　@　@電子交付情報.書面４交付区分 = 電子交付管理行.書面４交付区分 <BR>
     * 　@　@電子交付情報.書面４申込区分 = 電子交付管理行.書面４申込区分 <BR>
     * 　@　@電子交付情報.書面４交付区分更新日時 = 電子交付管理行.書面４交付区分更新日時 <BR>
     * 　@　@電子交付情報.書面５交付区分 = 電子交付管理行.書面５交付区分 <BR>
     * 　@　@電子交付情報.書面５申込区分 = 電子交付管理行.書面５申込区分 <BR>
     * 　@　@電子交付情報.書面５交付区分更新日時 = 電子交付管理行.書面５交付区分更新日時<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@return WEB3AccInfoEleDeliveryInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoEleDeliveryInfo createEleDeliveryInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME="createEleDeliveryInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //１）　@電子交付情報オブジェクトを生成する。
        WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = new WEB3AccInfoEleDeliveryInfo();

        //２）　@電子交付管理行の取得
        //　@　@　@電子交付管理テーブルにて以下の条件で検索する。
        //　@　@　@※該当行がなかった場合は、nullを返却
        //　@　@　@[条件]
        //　@　@　@電子交付管理テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode()
        //　@　@　@電子交付管理テーブル.部店コード = 顧客.getBranch().getBranchCode()
        //　@　@　@電子交付管理テーブル.顧客コード = 顧客.getAccountCode()
        EleDeliveryManagementRow l_eleDeliveryManagementRow = null;
        try
        {
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode());
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
        //※該当行がなかった場合は、nullを返却
        if (l_eleDeliveryManagementRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //３）　@以下の通り、プロパティをセットし、返却する。 
        //電子交付情報.取引報告書交付状態区分 = 電子交付管理行.取引報告書交付区分
        l_eleDeliveryInfo.tradingReportDiv = l_eleDeliveryManagementRow.getTradingReportDiv();
        //電子交付情報.取引報告書申込状態区分=
        //－（電子交付管理行.取引報告書申込区分 == 0：　@申込中 　@または　@9：　@SONAR送信済）の場合、0：　@申込中 。
        //－（電子交付管理行.取引報告書申込区分 == 1：　@申込完了 　@または　@2：　@取消完了）の場合、 1：　@申込完了。
        if (WEB3ReportRegDivDef.APPLYING.equals(l_eleDeliveryManagementRow.getTradingReportRegDiv())
            || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_eleDeliveryManagementRow.getTradingReportRegDiv()))
        {
            l_eleDeliveryInfo.tradingReportRegDiv = WEB3ReportRegDivDef.APPLYING;
        }
        else if (WEB3ReportRegDivDef.APPLIED.equals(l_eleDeliveryManagementRow.getTradingReportRegDiv())
            || WEB3ReportRegDivDef.CANCELLED.equals(l_eleDeliveryManagementRow.getTradingReportRegDiv()))
        {
            l_eleDeliveryInfo.tradingReportRegDiv = WEB3ReportRegDivDef.APPLIED;
        }
        //電子交付情報.取引報告書交付状態区分更新日時 = 電子交付管理行.取引報告書交付区分更新日時
        l_eleDeliveryInfo.tradingReportDivUpdateDate = l_eleDeliveryManagementRow.getTradingReportDivUpdDate();
        //電子交付情報.取引残高報告書交付状態区分 =
        // －（電子交付管理行.取引残高報告書交付区分 == 0：郵便配布）の場合、0：郵便配布。
        // －（電子交付管理行.取引残高報告書交付区分 == 1：郵便配布（受渡都度作成））の場合、0：郵便配布。
        // －（電子交付管理行.取引残高報告書交付区分 == 9：電子配布）の場合、1：電子配布。
        if (WEB3PosReportDivDef.MAIL_DISTRIBUTION.equals(
            l_eleDeliveryManagementRow.getPositionReportDiv()))
        {
            l_eleDeliveryInfo.positionReportDiv = WEB3TradingReportDivDef.MAIL_DISTRIBUTION;
        }
        else if (WEB3PosReportDivDef.MAIL_DISTRIBUTION_EACH_TIME.equals(
            l_eleDeliveryManagementRow.getPositionReportDiv()))
        {
            l_eleDeliveryInfo.positionReportDiv = WEB3TradingReportDivDef.MAIL_DISTRIBUTION;
        }
        else if (WEB3PosReportDivDef.ELECTRON_DISTRIBUTION.equals(
            l_eleDeliveryManagementRow.getPositionReportDiv()))
        {
            l_eleDeliveryInfo.positionReportDiv = WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION;
        }
        //電子交付情報.取引残高報告書申込状態区分 =
        //－（電子交付管理行.取引残高報告書申込区分 == 0：　@申込中 　@または　@9：　@SONAR送信済）の場合、0：　@申込中 。
        //－（電子交付管理行.取引残高報告書申込区分 == 1：　@申込完了 　@または　@2：　@取消完了）の場合、 1：　@申込完了。
        if (WEB3ReportRegDivDef.APPLYING.equals(l_eleDeliveryManagementRow.getPositionReportRegDiv())
            || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_eleDeliveryManagementRow.getPositionReportRegDiv()))
        {
            l_eleDeliveryInfo.positionReportRegDiv = WEB3ReportRegDivDef.APPLYING;
        }
        else if (WEB3ReportRegDivDef.APPLIED.equals(l_eleDeliveryManagementRow.getPositionReportRegDiv())
            || WEB3ReportRegDivDef.CANCELLED.equals(l_eleDeliveryManagementRow.getPositionReportRegDiv()))
        {
            l_eleDeliveryInfo.positionReportRegDiv = WEB3ReportRegDivDef.APPLIED;
        }
        //電子交付情報.取引残高報告書交付状態区分更新日時 = 電子交付管理行.取引残高報告書交付区分更新日時
        l_eleDeliveryInfo.positionReportDivUpdateDate = l_eleDeliveryManagementRow.getPositionReportDivUpdDate();
        //電子交付情報.運用報告書交付状態区分 = 電子交付管理行.運用報告書交付区分
        l_eleDeliveryInfo.opeReportDiv = l_eleDeliveryManagementRow.getOpeReportDiv();
        //電子交付情報.運用報告書申込状態区分 =
        //－（電子交付管理行.運用報告書申込区分 == 0：　@申込中 　@または　@9：　@SONAR送信済）の場合、0：　@申込中 。
        //－（電子交付管理行.運用報告書申込区分 == 1：　@申込完了 　@または　@2：　@取消完了）の場合、 1：　@申込完了。
        if (WEB3ReportRegDivDef.APPLYING.equals(l_eleDeliveryManagementRow.getOpeReportRegDiv())
            || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_eleDeliveryManagementRow.getOpeReportRegDiv()))
        {
            l_eleDeliveryInfo.opeReportRegDiv = WEB3ReportRegDivDef.APPLYING;
        }
        else if (WEB3ReportRegDivDef.APPLIED.equals(l_eleDeliveryManagementRow.getOpeReportRegDiv())
            || WEB3ReportRegDivDef.CANCELLED.equals(l_eleDeliveryManagementRow.getOpeReportRegDiv()))
        {
            l_eleDeliveryInfo.opeReportRegDiv = WEB3ReportRegDivDef.APPLIED;
        }
        //電子交付情報.運用報告書交付状態区分更新日時 = 電子交付管理行.運用報告書交付区分更新日時
        l_eleDeliveryInfo.opeReportDivUpdateDate = l_eleDeliveryManagementRow.getOpeReportDivUpdDate();
        //電子交付情報.約款・規定集報告書交付状態区分 = 電子交付管理行.約款・規定集報告書交付区分
        l_eleDeliveryInfo.ordRulReportDiv = l_eleDeliveryManagementRow.getOrdRulReportDiv();
        //電子交付情報.約款・規定集報告書申込状態区分 = 電子交付管理行.約款・規定集報告書申込区分
        l_eleDeliveryInfo.ordRulRepRegDiv = l_eleDeliveryManagementRow.getOrdRulRepRegDiv();
        //電子交付情報.約款・規定集報告書交付状態区分更新日時 = 電子交付管理行.規定集報告書交付区分更新日時
        l_eleDeliveryInfo.ordRulReportDivUpdateDate = l_eleDeliveryManagementRow.getOrdRulReportDivUpdDate();
        //電子交付情報.書面１交付区分 = 電子交付管理行.書面１交付区分
        l_eleDeliveryInfo.reportDiv1 = l_eleDeliveryManagementRow.getReportDiv1();
        //電子交付情報.書面１申込区分 = 電子交付管理行.書面１申込区分
        l_eleDeliveryInfo.reportRegDiv1 = l_eleDeliveryManagementRow.getReportRegDiv1();
        //電子交付情報.書面１交付区分更新日時 = 電子交付管理行.書面１交付区分更新日時
        l_eleDeliveryInfo.reportDivUpdateDate1 = l_eleDeliveryManagementRow.getReportDivUpdDate1();
        //電子交付情報.書面２交付区分 = 電子交付管理行.書面２交付区分
        l_eleDeliveryInfo.reportDiv2 = l_eleDeliveryManagementRow.getReportDiv2();
        //電子交付情報.書面２申込区分 = 電子交付管理行.書面２申込区分
        l_eleDeliveryInfo.reportRegDiv2 = l_eleDeliveryManagementRow.getReportRegDiv2();
        //電子交付情報.書面２交付区分更新日時 = 電子交付管理行.書面２交付区分更新日時
        l_eleDeliveryInfo.reportDivUpdateDate2 = l_eleDeliveryManagementRow.getReportDivUpdDate2();
        //電子交付情報.書面３交付区分 = 電子交付管理行.書面３交付区分
        l_eleDeliveryInfo.reportDiv3 = l_eleDeliveryManagementRow.getReportDiv3();
        //電子交付情報.書面３申込区分 = 電子交付管理行.書面３申込区分
        l_eleDeliveryInfo.reportRegDiv3 = l_eleDeliveryManagementRow.getReportRegDiv3();
        //電子交付情報.書面３交付区分更新日時 = 電子交付管理行.書面３交付区分更新日時
        l_eleDeliveryInfo.reportDivUpdateDate3 = l_eleDeliveryManagementRow.getReportDivUpdDate3();
        //電子交付情報.書面４交付区分 = 電子交付管理行.書面４交付区分
        l_eleDeliveryInfo.reportDiv4 = l_eleDeliveryManagementRow.getReportDiv4();
        //電子交付情報.書面４申込区分 = 電子交付管理行.書面４申込区分
        l_eleDeliveryInfo.reportRegDiv4 = l_eleDeliveryManagementRow.getReportRegDiv4();
        //電子交付情報.書面４交付区分更新日時 = 電子交付管理行.書面４交付区分更新日時
        l_eleDeliveryInfo.reportDivUpdateDate4 = l_eleDeliveryManagementRow.getReportDivUpdDate4();
        //電子交付情報.書面５交付区分 = 電子交付管理行.書面５交付区分
        l_eleDeliveryInfo.reportDiv5 = l_eleDeliveryManagementRow.getReportDiv5();
        //電子交付情報.書面５申込区分 = 電子交付管理行.書面５申込区分
        l_eleDeliveryInfo.reportRegDiv5 = l_eleDeliveryManagementRow.getReportRegDiv5();
        //電子交付情報.書面５交付区分更新日時 = 電子交付管理行.書面５交付区分更新日時
        l_eleDeliveryInfo.reportDivUpdateDate5 = l_eleDeliveryManagementRow.getReportDivUpdDate5();

        log.exiting(STR_METHOD_NAME);
        return l_eleDeliveryInfo;
    }

    /**
     * (get電子交付情報一覧)<BR>
     * 電子交付情報一覧表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（電子交付サービス登録・変更）get電子交付情報一覧」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AccInfoElecDeliveryApyReferenceResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoElecDeliveryApyReferenceResponse getEleDeliveryInfoList(
        WEB3AccInfoElecDeliveryApyReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="getEleDeliveryInfoList(WEB3AccInfoElecDeliveryApyReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoElecDeliveryApyReferenceResponse l_response = null;

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get顧客( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();

        //create電子交付情報(顧客)
        WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = this.createEleDeliveryInfo(l_mainAccount);

        //電子交付情報一覧レスポンス
        l_response = (WEB3AccInfoElecDeliveryApyReferenceResponse)l_request.createResponse();

        //プロパティセット
        //電子交付情報:create電子交付情報の戻り値
        l_response.eleDeliveryInfo = l_eleDeliveryInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get電子交付管理)<BR>
     * 顧客に該当する電子交付管理を取得する。 <BR>
     * <BR>
     * １）　@電子交付管理テーブル検索 <BR>
     * 　@以下の条件で、電子交付管理テーブルを検索する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = 顧客.getInstitution().getInstitutionCode() And <BR>
     * 　@部店コード = 顧客.getBranch().getBranchCode() And <BR>
     * 　@口座コード = 顧客.getAccountCode() <BR>
     * <BR>
     * ２）　@電子交付管理オブジェクト返却 <BR>
     * 　@取得した各行オブジェクトについて、電子交付管理オブジェクトを生成し返却する。 <BR>
     * 　@行が取得できなかった場合、又は <BR>
     * 　@行が複数件取得できた場合は、データ不整合と判定し、例外をスローする。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@return EleDeliveryManagementParams
     * @@throws WEB3BaseException
     */
    protected EleDeliveryManagementParams getEleDeliveryManagement(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME="getEleDeliveryManagement(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        EleDeliveryManagementRow l_eleDeliveryManagementRow = null;

        //１）　@電子交付管理テーブル検索
        //　@以下の条件で、電子交付管理テーブルを検索する。
        //　@[条件]
        //　@証券会社コード = 顧客.getInstitution().getInstitutionCode() And
        //　@部店コード = 顧客.getBranch().getBranchCode() And
        //　@口座コード = 顧客.getAccountCode()
        try
        {
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode());
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

        //行が取得できなかった場合、又は
        //　@行が複数件取得できた場合は、データ不整合と判定し、例外をスローする。
        if (l_eleDeliveryManagementRow == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }

        log.exiting(STR_METHOD_NAME);
        return new EleDeliveryManagementParams(l_eleDeliveryManagementRow);
    }
}
@
