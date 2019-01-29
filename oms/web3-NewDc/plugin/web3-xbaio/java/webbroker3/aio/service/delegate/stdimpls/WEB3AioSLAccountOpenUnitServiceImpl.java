head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLAccountOpenUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SL口座開設UnitServiceImpl(WEB3AioSLAccountOpenUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 謝旋 (中訊) 新規作成 モデルNo.763
Revision History : 2007/09/19 謝旋 (中訊) 仕様変更 モデルNo.778 , モデルNo.781 , モデルNo.783 , モデルNo.786 , モデルNo.788
Revision History : 2007/11/09 謝旋 (中訊) 仕様変更 モデルNo.823
Revision History : 2007/12/12 何文敏 (中訊) 仕様変更 モデルNo.826
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import webbroker3.aio.define.WEB3SystemPreferenceDef;
import webbroker3.aio.message.WEB3SLAccountBaseInfoUnit;
import webbroker3.aio.message.WEB3SLAccountOpenApplyRequest;
import webbroker3.aio.message.WEB3SLAccountOpenApplyResponse;
import webbroker3.aio.message.WEB3SLAccountOpenRequest;
import webbroker3.aio.message.WEB3SLAccountOpenResponse;
import webbroker3.aio.service.delegate.WEB3AioSLAccountOpenUnitService;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanDataControlService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CommSerialNumbersPK;
import webbroker3.gentrade.data.CommSerialNumbersRow;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * (SL口座開設UnitServiceImpl)<BR>
 * SL口座開設UnitService実装クラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AioSLAccountOpenUnitServiceImpl extends WEB3ClientRequestService
    implements WEB3AioSLAccountOpenUnitService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLAccountOpenUnitServiceImpl.class);

    /**
     * @@roseuid 46F0D5800186
     */
    public WEB3AioSLAccountOpenUnitServiceImpl()
    {

    }

    /**
     * SL口座開設サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のいずれかのメソッドをコールする。<BR>
     * 　@・validateSL口座開設()<BR>
     * 　@・submitSL口座開設()<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE06120347
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        // validateSL口座開設
        if (l_request instanceof WEB3SLAccountOpenApplyRequest)
        {
            l_response =
                this.validateSLAccountOpen(
                    (WEB3SLAccountOpenApplyRequest)l_request);
        }

        // submitSL口座開設
        else if (l_request instanceof WEB3SLAccountOpenRequest)
        {
            l_response =
                this.submitSLAccountOpen(
                    (WEB3SLAccountOpenRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateSL口座開設)<BR>
     * SL口座開設の依頼処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validateSL口座開設」参照。<BR>
     * <BR>
     * ==================================================<BR>
     * is法@人( )<BR>
     * 法@人客であるか判別を行う。<BR>
     * is法@人() == true の場合<BR>
     * 例外をスローする。<BR>
     * 【（法@人）口座開設不可エラー】<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_02934<BR>
     * ==================================================<BR>
     * is預り証券評価制( )<BR>
     * 預り証券評価制を実施しているか判別する。<BR>
     * is預り証券評価制() == true の場合<BR>
     * 例外をスローする。<BR>
     * 【（預り証券評価制）口座開設不可エラー】<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_02935<BR>
     * ==================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * <BR>
     * @@return WEB3SLAccountOpenApplyResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE3362007D
     */
    protected WEB3SLAccountOpenApplyResponse validateSLAccountOpen(
        WEB3SLAccountOpenApplyRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " validateSLAccountOpen(WEB3SLAccountOpenApplyRequest)";
        log.entering(STR_METHOD_NAME);

        // validate注文受付可能
        // 受付時間チェック/システム売買停止チェックを行う。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get補助口座(補助口座タイプ : SubAccountTypeEnum)
        // [引数]
        // 口座ID：　@返済必要額データParams.口座ID
        // 補助口座タイプ： 1（株式取引口座（預り金））
        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        // [引数]
        // 証券会社コード：補助口座.証券会社コード
        // 部店コード：補助口座.get取引店().getBranchCode()
        // 口座コード：補助口座.getMainAccount().getAccountCode()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode());

        //validate取引可能顧客
        //[引数]
        //顧客：get顧客()で取得した顧客オブジェクト
        //発注日：現在日時
        //処理フラグ："1"（固定）
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                l_gentradeMainAccount,
                GtlUtils.getSystemTimestamp(),
                1 + "");

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("チェックエラーの場合は例外をスローする"
                + l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

		// get顧客行( )
		// 顧客Paramsを取得する。
		MainAccountRow l_mainAccountRow =
			l_gentradeMainAccount.getMainAccountRow();

		//１）パラメータ.補助口座から取得した顧客オブジェクト.居住／非居住区分
		//が「1：特別非居住者」 or 「2：非居住者」の場合は、例外をスローする。
		if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()) ||
			WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02979,
				this.getClass().getName() + STR_METHOD_NAME,
				"非居住者は申込むことができません。");
		}

        // is法@人( )
        // 法@人客であるか判別を行う。
        // is法@人() == true の場合
        if (l_gentradeMainAccount.isCorporation())
        {
            // 例外をスローする。
            // 【（法@人）口座開設不可エラー】
            log.debug("（法@人）口座開設不可エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02934,
                this.getClass().getName() + STR_METHOD_NAME,
                "（法@人）口座開設不可エラー。");
        }

        // is預り証券評価制( )
        // 預り証券評価制を実施しているか判別する。
        // is預り証券評価制() == true の場合
        if (l_gentradeMainAccount.isAssetEvaluation())
        {
            // 例外をスローする。
            // 【（預り証券評価制）口座開設不可エラー】
            log.debug("（預り証券評価制）口座開設不可エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02935,
                this.getClass().getName() + STR_METHOD_NAME,
                "（預り証券評価制）口座開設不可エラー。");
        }

        // is信用口座開設(弁済区分 : String)
        // 信用口座開設区分を取得する。
        // [引数]
        // 弁済区分：”指定なし”
        boolean l_blnIsMarginAccountOpen =
            l_gentradeMainAccount.isMarginAccountEstablished(
                WEB3GentradeRepaymentDivDef.DEFAULT);

        // is先物OP口座開設(先物／オプション区分 : String)
        // 先物口座開設区分を取得する。
        // [引数]
        // 先物/オプション区分：”1”(先物)
        // [戻り値]
        // 先物口座開設区分
        boolean l_blnIsFuturesAccountOpen =
            l_gentradeMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES);

        // is先物OP口座開設(先物／オプション区分 : String)
        // オプション口座開設区分を取得する。
        // [引数]
        // 先物/オプション区分：”2”(オプション)
        // [戻り値]
        // オプション口座開設区分
        boolean l_blnIsOptionAccountOpen =
            l_gentradeMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);

        // validate口座開設可能(boolean, boolean, boolean)
        //［引数］
        // 信用口座開設区分：is信用口座開設()の戻り値
        // 先物口座開設区分：is先物OP口座開設(先物)の戻り値
        // オプション口座開設区分：is先物OP口座開設(オプション)の戻り値
        this.validateAccOpenPossible(
            l_blnIsMarginAccountOpen,
            l_blnIsFuturesAccountOpen,
            l_blnIsOptionAccountOpen);

        //validate年齢下限値(SubAccount, String, long)
        //[引数]
        //補助口座：get補助口座の戻り値
        //プリファ@レンス名：”sl_lowlimit.age.check”
        //プリファ@レンスの連番： 1
        WEB3GentradeBranch l_gentradeBranch =
            (WEB3GentradeBranch)l_gentradeSubAccount.getMainAccount().getBranch();
        l_gentradeBranch.validateAgeLowLimit(
            l_gentradeSubAccount,
            WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK,
            1);

        //validate年齢上限値(SubAccount, String, long)
        //[引数]
        //補助口座：get補助口座の戻り値
        //プリファ@レンス名：”sl_highlimit.age.check”
        //プリファ@レンスの連番： 1
        l_gentradeBranch.validateAgeHighLimit(
            l_gentradeSubAccount,
            WEB3BranchPreferencesNameDef.SL_HIGHLIMIT_AGE_CHECK,
            1);

        // createResponse( )
        WEB3SLAccountOpenApplyResponse l_response =
            (WEB3SLAccountOpenApplyResponse)l_request.createResponse();

        // プロパティセット
        // 以下の通り、プロパティを設定する。
        WEB3SLAccountBaseInfoUnit l_slAccountBaseInfoUnit =
            new WEB3SLAccountBaseInfoUnit();

        // 顧客名(カナ) = get顧客行.顧客名(カナ)
        l_slAccountBaseInfoUnit.accountNameKana =
            l_mainAccountRow.getFamilyNameAlt1();

        // 顧客名(漢字) = get顧客行.顧客名(漢字)
        l_slAccountBaseInfoUnit.accountName = l_mainAccountRow.getFamilyName();

        // 生年月日年号 = get顧客行.生年月日年号
        l_slAccountBaseInfoUnit.eraBorn = l_mainAccountRow.getEraBorn();

        // 生年月日 = get顧客行.生年月日
        l_slAccountBaseInfoUnit.bornDate = l_mainAccountRow.getBornDate();

        // 性別 = get顧客行.性別
        l_slAccountBaseInfoUnit.sex = l_mainAccountRow.getSex();

        // 郵便番号 = get顧客行.郵便番号
        l_slAccountBaseInfoUnit.zipCode = l_mainAccountRow.getZipCode();

        // 住所１(漢字) = get顧客行.住所１(漢字)
        l_slAccountBaseInfoUnit.address1 = l_mainAccountRow.getAddressLine1();

        // 住所２(漢字) = get顧客行.住所２(漢字)
        l_slAccountBaseInfoUnit.address2 = l_mainAccountRow.getAddressLine2();

        // 住所３(漢字) = get顧客行.住所３(漢字)
        l_slAccountBaseInfoUnit.address3 = l_mainAccountRow.getAddressLine3();

        // emailアドレス = get顧客行.emailアドレス
        l_slAccountBaseInfoUnit.mailAddress = l_mainAccountRow.getEmailAddress();

        // (*)レスポンスセット
        // 以下をレスポンスにセットする。
        // 上記でプロパティセットを行った顧客基本情報
        l_response.accountBaseInfo = l_slAccountBaseInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitSL口座開設)<BR>
     * SL口座開設を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submitSL口座開設」参照。<BR>
     * <BR>
     * ==================================================<BR>
     * is法@人( )<BR>
     * 法@人客であるか判別を行う。<BR>
     * is法@人() == true の場合<BR>
     * 例外をスローする。<BR>
     * 【（法@人）口座開設不可エラー】<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_02934<BR>
     * ==================================================<BR>
     * is預り証券評価制( )<BR>
     * 預り証券評価制を実施しているか判別する。<BR>
     * is預り証券評価制() == true の場合<BR>
     * 例外をスローする。<BR>
     * 【（預り証券評価制）口座開設不可エラー】<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_02935<BR>
     * ==================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * <BR>
     * @@return WEB3SLAccountOpenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE3362007B
     */
    protected WEB3SLAccountOpenResponse submitSLAccountOpen(
        WEB3SLAccountOpenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " submitSLAccountOpen(WEB3SLAccountOpenRequest)";
        log.entering(STR_METHOD_NAME);

        // 1 validate注文受付可能( )
        // 受付時間チェック/システム売買停止チェックを行う。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get補助口座(補助口座タイプ : SubAccountTypeEnum)
        // 補助口座オブジェクトを取得する。
        // [引数]
        // 口座ID：　@返済必要額データParams.口座ID
        // 補助口座タイプ： 1（株式取引口座（預り金））
        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        // [引数]
        // 証券会社コード：補助口座.証券会社コード
        // 部店コード：補助口座.get取引店().getBranchCode()
        // 口座コード：補助口座.getMainAccount().getAccountCode()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        // 証券会社コード
        String l_strInstitutionCode =
            l_gentradeSubAccount.getInstitution().getInstitutionCode();

        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode());

        //validate取引可能顧客(顧客, Timestamp, String)
        //[引数]
        //顧客：get顧客()で取得した顧客オブジェクト
        //発注日：現在日時
        //処理フラグ："1"（固定）
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                l_gentradeMainAccount,
                GtlUtils.getSystemTimestamp(),
                1 + "");

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("チェックエラーの場合はを例外をスローする"
                + l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

		// get顧客行( )
		// 顧客Paramsを取得する。
		MainAccountRow l_mainAccountRow =
			l_gentradeMainAccount.getMainAccountRow();
		MainAccountParams l_mainAccountParams =
			new MainAccountParams(l_mainAccountRow);

		//１）パラメータ.補助口座から取得した顧客オブジェクト.居住／非居住区分
		//が「1：特別非居住者」 or 「2：非居住者」の場合は、例外をスローする。
		if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()) ||
			WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02979,
				this.getClass().getName() + STR_METHOD_NAME,
				"非居住者は申込むことができません。");
		}

        //  is法@人( )
        // 法@人客であるか判別を行う。
        // is法@人() == true の場合
        if (l_gentradeMainAccount.isCorporation())
        {
            // 例外をスローする。
            // 【（法@人）口座開設不可エラー】
            log.debug("（法@人）口座開設不可エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02934,
                this.getClass().getName() + STR_METHOD_NAME,
                "（法@人）口座開設不可エラー。");
        }

        // is預り証券評価制( )
        // 預り証券評価制を実施しているか判別する。
        // is預り証券評価制() == true の場合
        if (l_gentradeMainAccount.isAssetEvaluation())
        {
            // 例外をスローする。
            // 【（預り証券評価制）口座開設不可エラー】
            log.debug("（預り証券評価制）口座開設不可エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02935,
                this.getClass().getName() + STR_METHOD_NAME,
                "（預り証券評価制）口座開設不可エラー。");
        }

        // is信用口座開設(弁済区分 : String)
        // 信用口座開設区分を取得する。
        // [引数]
        // 弁済区分：”指定なし”
        boolean l_blnIsMarginAccountOpen =
            l_gentradeMainAccount.isMarginAccountEstablished(
                WEB3GentradeRepaymentDivDef.DEFAULT);

        // is先物OP口座開設(先物／オプション区分 : String)
        // 先物口座開設区分を取得する。
        // [引数]
        // 先物/オプション区分：”1”(先物)
        // [戻り値]
        // 先物口座開設区分
        boolean l_blnIsFuturesAccountOpen =
            l_gentradeMainAccount.isIfoAccountOpen(
                WEB3FuturesOptionDivDef.FUTURES);

        // is先物OP口座開設(先物／オプション区分 : String)
        // オプション口座開設区分を取得する。
        // [引数]
        // 先物/オプション区分：”2”(オプション)
        // [戻り値]
        // オプション口座開設区分
        boolean l_blnIsOptionAccountOpen =
            l_gentradeMainAccount.isIfoAccountOpen(
                WEB3FuturesOptionDivDef.OPTION);

        // validate口座開設可能(boolean, boolean, boolean)
        //［引数］
        // 信用口座開設区分：is信用口座開設()の戻り値
        // 先物口座開設区分：is先物OP口座開設(先物)の戻り値
        // オプション口座開設区分：is先物OP口座開設(オプション)の戻り値
        this.validateAccOpenPossible(
            l_blnIsMarginAccountOpen,
            l_blnIsFuturesAccountOpen,
            l_blnIsOptionAccountOpen);

        //validate年齢下限値(SubAccount, String, long)
        //[引数]
        //補助口座：get補助口座の戻り値
        //プリファ@レンス名：”sl_lowlimit.age.check”
        //プリファ@レンスの連番： 1
        WEB3GentradeBranch l_gentradeBranch =
            (WEB3GentradeBranch)l_gentradeSubAccount.getMainAccount().getBranch();
        l_gentradeBranch.validateAgeLowLimit(
            l_gentradeSubAccount,
            WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK,
            1);

        //validate年齢上限値(SubAccount, String, long)
        //[引数]
        //補助口座：get補助口座の戻り値
        //プリファ@レンス名：”sl_highlimit.age.check”
        //プリファ@レンスの連番： 1
        l_gentradeBranch.validateAgeHighLimit(
            l_gentradeSubAccount,
            WEB3BranchPreferencesNameDef.SL_HIGHLIMIT_AGE_CHECK,
            1);

        // get株券担保ローン顧客情報(口座ID : long, 日付 : Timestamp)
        // [引数]
        // 口座ID：get顧客行().口座ID
        // 日付：現在日時(YYYYMMDD)
        WEB3AioSecuredLoanDataControlService l_aioSecuredLoanDataControlService =
            (WEB3AioSecuredLoanDataControlService)Services.getService(
                WEB3AioSecuredLoanDataControlService.class);

        Date l_datCurrentDay = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        Timestamp l_tsCurrentDay = new Timestamp(l_datCurrentDay.getTime());

        List l_lisStockSecuredLoanAccInfos =
            l_aioSecuredLoanDataControlService.getStockSecuredLoanAccInfo(
                l_mainAccountRow.getAccountId(),
                l_tsCurrentDay);

        // ストックローン口座番号
        String l_strStockLoanAccount = null;

        // get株券担保ローン顧客情報() == null の場合
        if (l_lisStockSecuredLoanAccInfos == null)
        {
            // getシリアルナンバー(String, String)
            // 引数で指定された設定名称の設定値をシリアルナンバーテーブルから取得する。
            // [引数]
            // 証券会社コード：補助口座.証券会社コード
            // 設定名称：　@"stock_secured_loan"
            String l_strSerialNumber =
                this.getSerialNumber(
                    l_strInstitutionCode,
                    StockSecuredLoanRow.TYPE.getTableName());

            // createストックローン口座番号(String)
            // ストックローン口座番号を算出する。
            //［引数］
            // シリアルナンバー：getシリアルナンバー()の戻り値
            l_strStockLoanAccount =
                this.createStockLoanAccountNo(l_strSerialNumber);

            // insert株券担保ローン(ストックローン口座番号 : String, 顧客Params : 顧客Params)
            // [引数]
            // ストックローン口座番号：createストックローン口座番号()の戻り値
            // 顧客Params：get顧客行()の戻り値
            l_aioSecuredLoanDataControlService.insertStockSecuredLoan(
                l_strStockLoanAccount,
                l_mainAccountParams);

            // update採番テーブル(証券会社コード : String, 採番項目名 : String,
            // 　@　@　@　@　@　@　@　@　@　@シリアルナンバー : String)
            // ［引数］
            // 証券会社コード：補助口座.証券会社コード
            // 採番項目名：”stock_secured_loan”
            // シリアルナンバー：createストックローン口座番号()の戻り値の上9桁
            // 例）ストックローン口座番号 = 0712180056 の場合
            // 引数で渡すのは 071218005
            l_aioSecuredLoanDataControlService.updateCommSerialNumbers(
                l_strInstitutionCode,
                StockSecuredLoanRow.TYPE.getTableName(),
                l_strStockLoanAccount.substring(0, 9));
        }

        // getプリファ@レンス(String)
        // 引数で指定された設定名称の設定値をシステムプリファ@レンステーブルから取得する。
        // [引数]
        // 設定名称：　@"46_CREDIT_URL"
        String l_strPreference = this.getPreference(WEB3SystemPreferenceDef.CREDIT_URL);

        // createResponse( )
        WEB3SLAccountOpenResponse l_response =
            (WEB3SLAccountOpenResponse)l_request.createResponse();

        // (*)プロパティセット
        // 以下の通り、プロパティを設定する。
        WEB3SLAccountBaseInfoUnit l_slAccountBaseInfoUnit =
            new WEB3SLAccountBaseInfoUnit();

        // 顧客名(カナ) = get顧客行.顧客名(カナ)
        l_slAccountBaseInfoUnit.accountNameKana =
            l_mainAccountRow.getFamilyNameAlt1();

        // 顧客名(漢字) = get顧客行.顧客名(漢字)
        l_slAccountBaseInfoUnit.accountName = l_mainAccountRow.getFamilyName();

        // 生年月日年号 = get顧客行.生年月日年号
        l_slAccountBaseInfoUnit.eraBorn = l_mainAccountRow.getEraBorn();

        // 生年月日 = get顧客行.生年月日
        l_slAccountBaseInfoUnit.bornDate = l_mainAccountRow.getBornDate();

        // 性別 = get顧客行.性別
        l_slAccountBaseInfoUnit.sex = l_mainAccountRow.getSex();

        // 郵便番号 = get顧客行.郵便番号
        l_slAccountBaseInfoUnit.zipCode = l_mainAccountRow.getZipCode();

        // 住所１(漢字) = get顧客行.住所１(漢字)
        l_slAccountBaseInfoUnit.address1 = l_mainAccountRow.getAddressLine1();

        // 住所２(漢字) = get顧客行.住所２(漢字)
        l_slAccountBaseInfoUnit.address2 = l_mainAccountRow.getAddressLine2();

        // 住所３(漢字) = get顧客行.住所３(漢字)
        l_slAccountBaseInfoUnit.address3 = l_mainAccountRow.getAddressLine3();

        // emailアドレス = get顧客行.emailアドレス
        l_slAccountBaseInfoUnit.mailAddress = l_mainAccountRow.getEmailAddress();

        // (*)レスポンスセット
        // 以下の通り、プロパティを設定する。

        // ストックローン口座番号
        // （get株券担保ローン顧客情報() == null の場合）
        if (l_lisStockSecuredLoanAccInfos == null)
        {
            // ストックローン口座番号 = createストックローン口座番号()の戻り値
            l_response.stockLoanAccount = l_strStockLoanAccount;
        }
        else
        {
            // （get株券担保ローン顧客情報() != null の場合）
            // ストックローン口座番号 = get株券担保ローン顧客情報().ストックローン口座番号
            l_response.stockLoanAccount =
                ((StockSecuredLoanRow)l_lisStockSecuredLoanAccInfos.get(0)).getStockLoanAccountCode();
        }

        // 外部接続URL = getプリファ@レンス()の戻り値
        l_response.url = l_strPreference;

        // 顧客基本情報 = 上記でプロパティセットを行った顧客基本情報
        l_response.accountBaseInfo = l_slAccountBaseInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate口座開設可能)<BR>
     * 証券担保ローンの口座開設が可能かチェックを行う。<BR>
     * 判定する要素は<BR>
     * is信用口座開設()<BR>
     * is先物OP口座開設(先物)<BR>
     * is先物OP口座開設(OP)<BR>
     * の3つのメソッドである。<BR>
     * <BR>
     * <BR>
     * 1) （is信用口座開設() == true &&<BR>
     * 　@　@is先物OP口座開設(先物)==false && is先物OP口座開設(OP)==false） の場合<BR>
     * 　@　@【（信用）口座開設不可エラー】<BR>
     * <BR>
     * 2) （is信用口座開設() == false &&<BR>
     * 　@　@（is先物OP口座開設(先物)==true || is先物OP口座開設(OP)==true）） の場合<BR>
     * 　@　@【（先OP）口座開設不可エラー】<BR>
     * <BR>
     * 3) （is信用口座開設() == true &&<BR>
     * 　@　@（is先物OP口座開設(先物)==true || is先物OP口座開設(OP)==true）） の場合<BR>
     * 　@　@【（信用、先OP）口座開設不可エラー】<BR>
     * <BR>
     * @@param l_blnIsMarginAccountOpenDiv - (信用口座開設区分)<BR>
     * 信用口座開設区分<BR>
     * @@param l_blnIsFuturesAccountOpenDiv - (先物口座開設区分)<BR>
     * 先物口座開設区分<BR>
     * @@param l_blnIsOptionAccountOpenDiv - (オプション口座開設区分)<BR>
     * オプション口座開設区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D26A8C01D7
     */
    private void validateAccOpenPossible(
        boolean l_blnIsMarginAccountOpenDiv,
        boolean l_blnIsFuturesAccountOpenDiv,
        boolean l_blnIsOptionAccountOpenDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConAccOpenPossible(boolean, boolean, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_blnIsMarginAccountOpenDiv)
        {
            //1) （is信用口座開設() == true &&
            // 　@　@is先物OP口座開設(先物)==false && is先物OP口座開設(OP)==false） の場合
            //【（信用）口座開設不可エラー】
            if (!(l_blnIsFuturesAccountOpenDiv || l_blnIsOptionAccountOpenDiv))
            {
                log.debug("（信用）口座開設不可エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02936,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "（信用）口座開設不可エラー");
            }

            //（is信用口座開設() == true &&
            // 　@　@（is先物OP口座開設(先物)==true || is先物OP口座開設(OP)==true）） の場合
            // 　@　@【（信用、先OP）口座開設不可エラー】
            if (l_blnIsFuturesAccountOpenDiv || l_blnIsOptionAccountOpenDiv)
            {
                log.debug("（信用、先OP）口座開設不可エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02938,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "（信用、先OP）口座開設不可エラー");
            }
        }
        else
        {
            //2) （is信用口座開設() == false &&
            // 　@　@（is先物OP口座開設(先物)==true || is先物OP口座開設(OP)==true）） の場合
            // 　@　@【（先OP）口座開設不可エラー】
            if (l_blnIsFuturesAccountOpenDiv || l_blnIsOptionAccountOpenDiv)
            {
                log.debug("（先OP）口座開設不可エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02937,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "（先OP）口座開設不可エラー");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getプリファ@レンス)<BR>
     * 引数で指定された設定名称の設定値をシステムプリファ@レンステーブルから取得する。<BR>
     * <BR>
     * １）システムプリファ@レンステーブルから以下の条件でレコードを取得する。 <BR>
     * <BR>
     * 　@[取得条件] <BR>
     * 　@名称（環境変数名） = （引数）設定名称 <BR>
     * <BR>
     * ２）取得したシステムプリファ@レンステーブルのレコードの設定値を返却する。 <BR>
     * <BR>
     * @@param l_strName - (設定名称)<BR>
     * 設定名称<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46D3E69D03E6
     */
    private String getPreference(String l_strName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPreference(String)";
        log.entering(STR_METHOD_NAME);

        //１）システムプリファ@レンステーブルから以下の条件でレコードを取得する。
        //[取得条件]
        //名称（環境変数名） = （引数）設定名称
        SystemPreferencesRow l_systemPreferencerow;
        String l_strValue = null;
        try
        {
            l_systemPreferencerow = (SystemPreferencesRow)SystemPreferencesDao.findRowByName(l_strName);

            if (l_systemPreferencerow != null)
            {
                l_strValue = l_systemPreferencerow.getValue();
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）取得したシステムプリファ@レンステーブルのレコードの設定値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }

    /**
     * (getシリアルナンバー)<BR>
     * 引数で指定された採番項目名の採番コードを採番テーブルから取得する。<BR>
     * <BR>
     * １）採番テーブルから以下の条件でレコードを取得する。<BR>
     * <BR>
     * 　@[取得条件]<BR>
     * 　@証券会社コード=（引数）証券会社コード<BR>
     * 　@採番項目名=（引数）採番項目名<BR>
     * <BR>
     * ２）取得した採番テーブルのレコードの設定値を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strSerialNumberName - (採番項目名)<BR>
     * 設定名称<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46D3EF580278
     */
    private String getSerialNumber(
        String l_strInstitutionCode,
        String l_strSerialNumberName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSerialNumber(String, String)";
        log.entering(STR_METHOD_NAME);

        //　@[取得条件]
        //証券会社コード = （引数）証券会社コード
        //採番項目名 = （引数）採番項目名
        CommSerialNumbersPK l_commSerialNumbersPK =
            new CommSerialNumbersPK(l_strInstitutionCode, l_strSerialNumberName);

        CommSerialNumbersRow l_commSerialNumbersRow = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_commSerialNumbersRow =
                (CommSerialNumbersRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_commSerialNumbersPK);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DBサーバとの通信に失敗した]__" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）取得した採番テーブルのレコードの設定値を返却する。
        String l_strSerialNumber = null;
        if (l_commSerialNumbersRow != null)
        {
            l_strSerialNumber = l_commSerialNumbersRow.getSerialNumber();
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSerialNumber;
    }

    /**
     * (createストックローン口座番号)<BR>
     * ストックローン口座番号を生成する。<BR>
     * <BR>
     * 詳細は<BR>
     * シーケンス図「createストックローン口座番号」を参照<BR>
     * <BR>
     * @@param l_strSerialNumber - (シリアルナンバー)<BR>
     * シリアルナンバー<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46D3F51D0199
     */
    private String createStockLoanAccountNo(String l_strSerialNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createStockLoanAccount(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strSerialNumber == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //現在日付(YYMMDD)をint型に変換する。(YYは西暦下二桁）
        String l_strNowTime = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyMMdd");
        int l_intNowTime = Integer.parseInt(l_strNowTime);

        //引数.シリアルナンバーをint型に変換する。
        int l_intSerialNumber = Integer.parseInt(l_strSerialNumber);
        int l_intSerialNumberTemp = Integer.parseInt(l_strSerialNumber.substring(0, 6));

        //newシリアルナンバー
        String l_strNewSerialNumber;
        int l_intSerialNumberLength = l_strSerialNumber.length();
        //int型の現在日付(YYMMDD) <= int型の引数.シリアルナンバーの上6桁 の場合
        if (l_intNowTime <= l_intSerialNumberTemp)
        {
            //newシリアルナンバー = 引数.シリアルナンバー + 1
            l_strNewSerialNumber = String.valueOf(l_intSerialNumber + 1);

            int l_intNewSerialNumberLength = l_strNewSerialNumber.length();
            int l_intLengthTemp = l_intSerialNumberLength - l_intNewSerialNumberLength;
            String l_strZero = "";
            for (int i = 0; i < l_intLengthTemp; i++)
            {
                l_strZero = l_strZero + "0";
            }

            l_strNewSerialNumber = l_strZero + l_strNewSerialNumber;
        }
        else
        {
            //上記以外の場合
            //newシリアルナンバー =  YYMMDD001
            //（YYMMDDは現在日付（YYは西暦下二桁）
            //（001は数値）
            l_strNewSerialNumber = l_strNowTime + "001";
        }

        //ストックローン口座番号生成
        //10桁のストックローン口座番号を生成する。
        char[] l_serialNumbers = l_strNewSerialNumber.toCharArray();
        int l_intCount = 0;
        //①@ newシリアルナンバー9桁の番号を全て加算する。
        // y+y+m+m+d+d+x+x+x
        for (int i = 0; i < l_serialNumbers.length; i++)
        {
            l_intCount = l_intCount + Integer.parseInt(String.valueOf(l_serialNumbers[i]));
        }

        //② ①@で計算した数値の下1桁を取得する。
        String l_strCount = String.valueOf(l_intCount);
        int l_intCluntLength = l_strCount.length();
        String l_strCountRight = l_strCount.substring(l_intCluntLength - 1, l_intCluntLength);

        //③ チェックデジット = 10 - ②で取得した1桁の数値
        int l_intFlag = 10 - Integer.parseInt(l_strCountRight);
        //（②が0 の場合、チェックデジットは 0 とする。）
        if (l_intFlag == 10)
        {
            l_intFlag = 0;
        }

        //yymmddxxx = 上記で取得したnewシリアルナンバー
        //c = チェックデジット
        String l_strStockLoanAccount = l_strNewSerialNumber + String.valueOf(l_intFlag);

        //ストックローン口座番号を返却
        log.exiting(STR_METHOD_NAME);
        return l_strStockLoanAccount;
    }
}
@
