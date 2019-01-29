head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券部店別応募枠管理サービスImpl(WEB3AdminBondDomesticRecruitLimitManageServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.214,モデルNo.242
Revision History : 2007/07/23 柴双紅 (中訊) モデルNo.244,モデルNo.247
Revision History : 2007/09/10 周墨洋 (中訊) 仕様変更・モデルNo.254,モデルNo.255
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.bd.WEB3BondBranchCondition;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.data.BondBranchRecruitLimitDao;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondBranchRecruitLimitRow;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputResponse;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticRecruitLimitManageService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondBranchRecruitLimitBranchCodeDef;
import webbroker3.common.define.WEB3BranchRecruitLimitDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者国内債券部店別応募枠管理サービスImpl)<BR>
 * 管理者国内債券部店別応募枠管理サービス実装クラス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageServiceImpl
    implements WEB3AdminBondDomesticRecruitLimitManageService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticRecruitLimitManageServiceImpl.class);

    /**
     * @@roseuid 46A473FD002E
     */
    public WEB3AdminBondDomesticRecruitLimitManageServiceImpl()
    {

    }

    /**
     * 管理者国内債券部店別応募枠管理サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * input応募枠管理()、validate応募枠管理()、submit応募枠管理()<BR>
     * のいずれかのメソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 468B7F930369
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
        if (l_request instanceof WEB3AdminBondDomesticRecruitLimitManageInputRequest)
        {
            l_response =
                this.inputRecruitLimitManage(
                    (WEB3AdminBondDomesticRecruitLimitManageInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminBondDomesticRecruitLimitManageConfirmRequest)
        {
            l_response =
                this.validateRecruitLimitManage(
                    (WEB3AdminBondDomesticRecruitLimitManageConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminBondDomesticRecruitLimitManageCompleteRequest)
        {
            l_response =
                this.submitRecruitLimitManage(
                    (WEB3AdminBondDomesticRecruitLimitManageCompleteRequest)l_request);
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
     * (input応募枠管理)<BR>
     * 管理者国内債券部店別応募枠管理入力処理を行なう。<BR>
     * <BR>
     * シーケンス図：「（管理者国内債券部店別応募枠管理）input応募枠管理」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitManageInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 468B7DBC0212
     */
    protected WEB3AdminBondDomesticRecruitLimitManageInputResponse inputRecruitLimitManage(
        WEB3AdminBondDomesticRecruitLimitManageInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " inputRecruitLimitManage(WEB3AdminBondDomesticRecruitLimitManageInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        // 機@能カテゴリコード　@：機@能カテゴリコード.債券銘柄管理
        // is更新　@：false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            false);

        //is全部店許可( )
        boolean l_blnIsAllBranchsPermission = l_administrator.isAllBranchsPermission();

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get部店コード( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //get債券銘柄(long)
        //[引数]
        // 銘柄ID ： リクエスト.銘柄ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        long l_lngProductId = Long.parseLong(l_request.productId);
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(l_lngProductId);

        //get部店(証券会社コード : String, 部店コード : String)
        //[引数]
        //　@　@証券会社コード：取得した証券会社コード
        //　@　@部店コード　@　@　@：取得した部店コード
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_gentradeAccountManager.getWeb3GenBranch(
                l_strInstitutionCode,
                l_strBranchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getBranchId( )
        long l_lngBranchId = l_branch.getBranchId();

        //債券部店別条件(long)
        //[引数]
        //　@部店ID：取得した部店ID
        WEB3BondBranchCondition l_bondBranchCondition =
            new WEB3BondBranchCondition(l_lngBranchId);

        //get応募枠部店別管理区分( )
        String l_strBranchRecruitLimitDiv =
            l_bondBranchCondition.getBranchRecruitLimitDiv();

        //create国内債券部店別応募枠情報(long, String, String)
        //[引数]
        //  銘柄ID：リクエスト.銘柄ID
        //　@証券会社コード：取得した証券会社コード
        //　@部店コード　@　@：is全部店許可がtrueの場合、null。
        //           is全部店許可がfalseかつ、
        //               get応募枠部店別管理区分()が「部店別管理しない」の場合、「---」（全部店)。
        //           is全部店許可がfalseかつ、
        //               get応募枠部店別管理区分()が「部店別管理する」の場合、取得した部店コード。
        String l_strBranchRecruitCode = null;
        if (l_blnIsAllBranchsPermission)
        {
            l_strBranchRecruitCode = null;
        }
        else if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT.equals(l_strBranchRecruitLimitDiv))
        {
            l_strBranchRecruitCode = WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH;
        }
        else if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT.equals(l_strBranchRecruitLimitDiv))
        {
            l_strBranchRecruitCode = l_strBranchCode;
        }
        WEB3BondDomesticBranchRecruitLimitInfo[] l_branchRecruitLimitInfos =
            l_productManager.createAdminBondDomesticRecruitLimitInfo(
                l_lngProductId,
                l_strInstitutionCode,
                l_strBranchRecruitCode);

        //createResponse( )
        WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response =
            (WEB3AdminBondDomesticRecruitLimitManageInputResponse)l_request.createResponse();

        //プロパティ・セット
        //銘柄コード         ：債券銘柄.銘柄コード(SONAR)
        l_response.productCode = l_bondProduct.getHostProductCode();

        //回号コード         ：債券銘柄.回号コード(SONAR)
        l_response.productIssueCode = l_bondProduct.getHostProductIssueCode();

        //銘柄名           ：債券銘柄.銘柄名
        l_response.productName = l_bondProduct.getProductName();

        //種別コード         ：債券銘柄.種別コード
        l_response.bondCategCode = l_bondProduct.getBondCategCode();

        //発行年月日     ：債券銘柄.発行日
        l_response.issueDate = l_bondProduct.getIssueDate();

        //発行価格          ：債券銘柄.発行価格
        l_response.issuePrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getIssuePrice());

        //年利率           ：債券銘柄.利率
        l_response.annualRate = WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //利払月日１     ：債券銘柄.利払日１
        l_response.couponPaymentDate1 = l_bondProduct.getInterestPaymentDay1();

        //利払月日２     ：債券銘柄.利払日２
        l_response.couponPaymentDate2 = l_bondProduct.getInterestPaymentDay2();

        //償還年月日       ：債券銘柄.償還日
        l_response.maturityDate = l_bondProduct.getMaturityDate();

        //応募開始日時      ：債券銘柄.取扱開始日時
        l_response.recruitStartDate = l_bondProduct.getTradeStartDate();

        //応募終了日       ：債券銘柄.取扱終了日時
        l_response.recruitEndDate = WEB3DateUtility.toDay(l_bondProduct.getTradeEndDate());

        //国内債券部店別応募枠情報  ：create国内債券部店別応募枠情報の戻り値
        l_response.bondDomesticBranchRecruitLimitInfo = l_branchRecruitLimitInfos;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate応募枠管理)<BR>
     * 管理者国内債券部店別応募枠管理確認処理を行なう。<BR>
     * <BR>
     * シーケンス図：「（管理者国内債券部店別応募枠管理）validate応募枠管理」参照。<BR>
     * ==========================================================<BR>
     * 　@シーケンス図:（管理者国内債券部店別応募枠管理）validate応募枠管理<BR>
     * 　@具体位置    : is全部店許可( )<BR>
     * 　@　@　@　@　@　@　@　@falseの場合、管理者制限チェックエラーを返す。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag   : BUSINESS_ERROR_01380<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitManageConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 468B7E4F003D
     */
    protected WEB3AdminBondDomesticRecruitLimitManageConfirmResponse validateRecruitLimitManage(
        WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateRecruitLimitManage(WEB3AdminBondDomesticRecruitLimitManageConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード　@：機@能カテゴリコード.債券銘柄管理
        // is更新　@：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);

        //is全部店許可( )
        boolean l_blnIsAllBranchsPermission = l_administrator.isAllBranchsPermission();

        //validate応募枠変更可能(String)
        //[引数]
        //　@　@銘柄ID　@：　@リクエストデータ.銘柄ID
        this.validateRecruitLimitChangePossibility(l_request.productId);

        //falseの場合、管理者制限チェックエラーを返す。
        if (!l_blnIsAllBranchsPermission)
        {
            log.debug("管理者制限チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380,
                this.getClass().getName() + STR_METHOD_NAME,
                "管理者制限チェックエラー");
        }

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //create国内債券部店別応募枠情報(long, String, String)
        //[引数]
        // 銘柄ID：リクエスト.銘柄ID
        // 証券会社コード：取得した証券会社コード
        // 部店コード　@　@　@：null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondDomesticBranchRecruitLimitInfo[] l_branchRecruitLimitInfos =
            l_productManager.createAdminBondDomesticRecruitLimitInfo(
                Long.parseLong(l_request.productId),
                l_strInstitutionCode,
                null);

        //validate部店別応募枠(国内債券部店別応募枠情報[], 国内債券部店別応募枠情報[])
        //[引数]
        //　@　@　@　@WEB3応募枠(変更前)：create国内債券部店別応募枠情報の戻り値
        //　@　@　@　@WEB3応募枠(変更後)：リクエスト.国内債券部店別応募枠情報
        this.validateBranchRecruitLimit(
            l_branchRecruitLimitInfos,
            l_request.bondDomesticBranchRecruitLimitInfo);

        //createResponse( )
        WEB3AdminBondDomesticRecruitLimitManageConfirmResponse l_response =
            (WEB3AdminBondDomesticRecruitLimitManageConfirmResponse)l_request.createResponse();

        //プロパティ・セット
        //国内債券部店別応募枠情報 ： create国内債券部店別応募枠情報の戻り値
        l_response.bondDomesticBranchRecruitLimitInfo = l_branchRecruitLimitInfos;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit応募枠管理)<BR>
     * 管理者国内債券部店別応募枠管理完了処理を行なう。<BR>
     * <BR>
     * シーケンス図：「（管理者国内債券部店別応募枠管理）submit応募枠管理」参照。<BR>
     * ==========================================================<BR>
     * 　@シーケンス図:（管理者国内債券部店別応募枠管理）submit応募枠管理<BR>
     * 　@具体位置　@  : is全部店許可( )<BR>
     * 　@　@　@　@　@　@　@　@falseの場合、管理者制限チェックエラーを返す。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag   : BUSINESS_ERROR_01380<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitManageCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 468B7F1000AA
     */
    protected WEB3AdminBondDomesticRecruitLimitManageCompleteResponse submitRecruitLimitManage(
        WEB3AdminBondDomesticRecruitLimitManageCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitRecruitLimitManage(WEB3AdminBondDomesticRecruitLimitManageCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード　@：機@能カテゴリコード.債券銘柄管理
        //　@is更新　@：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);

        //is全部店許可( )
        boolean l_blnIsAllBranchsPermission = l_administrator.isAllBranchsPermission();

        //falseの場合、管理者制限チェックエラーを返す。
        if (!l_blnIsAllBranchsPermission)
        {
            log.debug("管理者制限チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380,
                this.getClass().getName() + STR_METHOD_NAME,
                "管理者制限チェックエラー");
        }

        //validate取引パスワード(パスワード : String)
        //[引数]
        //　@　@パスワード　@：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //validate応募枠変更可能(String)
        //[引数]
        //　@　@銘柄ID　@：　@リクエストデータ.銘柄ID
        this.validateRecruitLimitChangePossibility(l_request.productId);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //create国内債券部店別応募枠情報(long, String, String)
        //[引数]
        // 銘柄ID：リクエスト.銘柄ID
        // 証券会社コード：取得した証券会社コード
        // 部店コード　@　@　@：null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondDomesticBranchRecruitLimitInfo[] l_branchRecruitLimitInfos =
            l_productManager.createAdminBondDomesticRecruitLimitInfo(
                Long.parseLong(l_request.productId),
                l_strInstitutionCode,
                null);

        //validate部店別応募枠(国内債券部店別応募枠情報[], 国内債券部店別応募枠情報[])
        //[引数]
        //　@　@　@　@WEB3応募枠(変更前)：create国内債券部店別応募枠情報の戻り値
        //　@　@　@　@WEB3応募枠(変更後)：リクエスト.国内債券部店別応募枠情報
        this.validateBranchRecruitLimit(
            l_branchRecruitLimitInfos,
            l_request.bondDomesticBranchRecruitLimitInfo);

        //update応募枠(管理者, String, 国内債券部店別応募枠情報[])
        //[引数]
        //　@　@管理者：取得した管理者オブジェクト
        //　@　@銘柄ID：リクエスト.銘柄ID
        //　@　@国内債券部店別応募枠情報：リクエスト.国内債券部店別応募枠情報
        this.updateRecruitLimit(
            l_administrator,
            l_request.productId,
            l_request.bondDomesticBranchRecruitLimitInfo);

        //createResponse( )
        WEB3AdminBondDomesticRecruitLimitManageCompleteResponse l_response =
            (WEB3AdminBondDomesticRecruitLimitManageCompleteResponse)l_request.createResponse();

        //プロパティ・セット
        //国内債券部店別応募枠情報 ： create国内債券部店別応募枠情報の戻り値
        l_response.bondDomesticBranchRecruitLimitInfo = l_branchRecruitLimitInfos;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate部店別応募枠)<BR>
     * 応募枠入力値の整合性チェックを行う。<BR>
     * 以下のチェックを行う。<BR>
     * <BR>
     * ①@当該部店の注文金額合計（リアル）の計算結果 ≦ 変更後のWEB3応募枠（部店別）<BR>
     * ②全部店の注文金額合計（リアル）の計算結果 ≦ 変更後のWEB3応募枠（全部店）<BR>
     * ③全部店のWEB3応募枠（部店別）の集計結果 ≦ 変更後のWEB3応募枠（全部店）<BR>
     * <BR>
     * ０）前準備<BR>
     * <BR>
     * 　@０－１）<BR>
     * 　@　@引数.WEB3応募枠（変更後）のハッシュ化<BR>
     * 　@　@以下の要領で、引数.WEB3応募枠（変更後）の配列の全ての要素をハッシュ化する。<BR>
     * <BR>
     * 　@　@　@　@キー ： WEB3応募枠（変更後）.部店コード<BR>
     * 　@　@　@　@値   ： WEB3応募枠（変更後）.WEB3応募枠<BR>
     * <BR>
     * 　@　@　@※このハッシュを"変更後ハッシュ"とする。<BR>
     * <BR>
     * 　@０－２）<BR>
     * 　@　@部店別応募枠合計 = 0 とする。<BR>
     * <BR>
     * １）引数.WEB3応募枠（変更前）の要素分LOOPし、各要素について以下の処理を行う。<BR>
     * <BR>
     * 　@１－１）
     * <BR>
     * 　@　@"変更後ハッシュ"からWEB3応募枠（変更前).部店コード<BR>
     * 　@　@　@と同じ部店コードの値が取得できた場合<BR>
     * 　@　@（つまり、WEB3応募枠（変更前).部店コードについての変更後の値があった場合）、<BR>
     * 　@　@①@②の条件を満たすかどうかのチェックを行う。<BR>
     * <BR>
     * 　@　@　@WEB3応募枠（変更前）.注文金額合計 > ハッシュから取得した値<BR>
     * <BR>
     * 　@　@　@上記の条件を満たす場合、<BR>
     * 　@　@　@　@「注文金額合計未満を入力することができません」の例外をスローする。<BR>
     * 　@　@　@※例外をスローする場合、（WEB3Exception.errorMessage）<BR>
     * 　@　@　@にWEB3応募枠（変更前).部店コードをセットする。<BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_02885<BR>
     * <BR>
     * 　@※１)のLOOP処理内で部店別の部店コードと全部店の<BR>
     * 　@　@部店コード"---"が処理されるので、<BR>
     * 　@　@結果として①@②の条件のチェックを行ったことになる。<BR>
     * <BR>
     * 　@１－２）<BR>
     * <BR>
     * 　@　@③の条件を満たすかどうかのチェックを行う前準備として、<BR>
     * 　@　@　@部店別応募枠の合計を算出する。<BR>
     * 　@　@　@※WEB3応募枠（変更前).部店コード="---"の場合は、<BR>
     * 　@　@　@　@全部店の応募枠を加算する必要が無いのでこの処理をスキップする。<BR>
     * <BR>
     * 　@　@　@１－２－１）<BR>
     * <BR>
     * 　@　@　@　@"変更後ハッシュ"からWEB3応募枠（変更前).部店コード<BR>
     * 　@　@　@　@　@と同じ部店コードの値が取得できた場合<BR>
     * 　@　@　@　@（つまり、WEB3応募枠（変更前).部店コードについての変更後の値があった場合）<BR>
     * <BR>
     * 　@　@　@　@　@部店別応募枠合計 += ハッシュから取得した値<BR>
     * <BR>
     * 　@　@　@１－２－２）<BR>
     * 　@　@　@　@取得できなかった場合<BR>
     * 　@　@　@　@（つまり、<BR>
     * 　@　@　@　@　@WEB3応募枠（変更前).部店コードについての変更後の値がなかった場合）<BR>
     * <BR>
     * 　@　@　@　@　@部店別応募枠合計 += WEB3応募枠（変更前）.WEB3応募枠<BR>
     * <BR>
     * ２）③の条件を満たすかどうかのチェックを行う。<BR>
     * <BR>
     * 　@２－１）<BR>
     * 　@　@比較対象となる全部店応募枠の決定<BR>
     * <BR>
     * 　@　@２－１－１）<BR>
     * 　@　@　@"変更後ハッシュ"から部店コード = "---" の値が取得できた場合<BR>
     * 　@　@　@（つまり、全部店の変更後の値があった場合）<BR>
     * <BR>
     * 　@　@　@　@全部店応募枠 = ハッシュから取得した値<BR>
     * <BR>
     * 　@　@２－１－２）<BR>
     * 　@　@　@ 取得できなかった場合<BR>
     * 　@　@　@（つまり、全部店の変更後の値がなかった場合）<BR>
     * <BR>
     * 　@　@　@　@全部店応募枠 = WEB3応募枠（変更前）.WEB3応募枠(*）<BR>
     * 　@　@　@　@(*)全部店（部店コード = "---"）のもの<BR>
     * <BR>
     * 　@２－２）<BR>
     * <BR>
     * 　@　@以下の条件を満たす場合、<BR>
     * 　@　@「部店別の応募枠合計が全部店の応募枠を超えています。」の例外をスローする。<BR>
     * <BR>
     * 　@　@全部店応募枠 ＜ 部店別応募枠合計<BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_02886<BR>
     * @@param l_recruitLimitBefores - (WEB3応募枠(変更前))<BR>
     * WEB3応募枠(変更前)<BR>
     * @@param l_recruitLimitAfters - (WEB3応募枠(変更後))<BR>
     * WEB3応募枠(変更後)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46956E9E00C0
     */
    protected void validateBranchRecruitLimit(
        WEB3BondDomesticBranchRecruitLimitInfo[] l_recruitLimitBefores,
        WEB3BondDomesticBranchRecruitLimitInfo[] l_recruitLimitAfters)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBranchRecruitLimit("
            + "WEB3BondDomesticBranchRecruitLimitInfo[], WEB3BondDomesticBranchRecruitLimitInfo[])";
        log.entering(STR_METHOD_NAME);

        if (l_recruitLimitBefores == null || l_recruitLimitAfters == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //０）前準備
        // ０－１）
        //  引数.WEB3応募枠（変更後）のハッシュ化
        //  以下の要領で、引数.WEB3応募枠（変更後）の配列の全ての要素をハッシュ化する。
        //    キー ： WEB3応募枠（変更後）.部店コード
        //    値   ： WEB3応募枠（変更後）.WEB3応募枠
        HashMap l_hmUpdatedMap = new HashMap();
        for (int i = 0; i < l_recruitLimitAfters.length; i++)
        {
            l_hmUpdatedMap.put(
                l_recruitLimitAfters[i].branchCode,
                l_recruitLimitAfters[i].web3RecruitLimit);
        }

        //０－２）
        //部店別応募枠合計 = 0 とする。
        double l_dblBranchRecruitLimitTotal = 0;
        double l_dblRecruitLimitBefore = 0;

        //１）引数.WEB3応募枠（変更前）の要素分LOOPし、各要素について以下の処理を行う。
        for (int i = 0; i < l_recruitLimitBefores.length; i++)
        {
            WEB3BondDomesticBranchRecruitLimitInfo l_branchRecruitLimitinfo =
                l_recruitLimitBefores[i];
            //１－１）
            //"変更後ハッシュ"からWEB3応募枠（変更前).部店コードと同じ部店コードの値が取得できた場合
            double l_dblOrderAmountTotal = Double.parseDouble(l_branchRecruitLimitinfo.orderAmountTotal);
            double l_dblRecruitLimitTemp = Double.parseDouble(l_branchRecruitLimitinfo.web3RecruitLimit);
            boolean l_blnIsIncludeAllBranch =
                WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH.equals(l_branchRecruitLimitinfo.branchCode);

            if (l_hmUpdatedMap.containsKey(l_branchRecruitLimitinfo.branchCode))
            {
                //WEB3応募枠（変更前）.注文金額合計 > ハッシュから取得した値
                //上記の条件を満たす場合、「注文金額合計未満を入力することができません」の例外をスローする。
                //※例外をスローする場合、
                //（WEB3Exception.errorMessage）にWEB3応募枠（変更前).部店コードをセットする。
                double l_dblRecruitLimit =
                    Double.parseDouble((String)l_hmUpdatedMap.get(l_branchRecruitLimitinfo.branchCode));
                if (l_dblOrderAmountTotal > l_dblRecruitLimit)
                {
                    log.debug("注文金額合計未満を入力することができません");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02885,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_branchRecruitLimitinfo.branchCode);
                }

                //１－２）
                // ③の条件を満たすかどうかのチェックを行う前準備として、部店別応募枠の合計を算出する。
                //※WEB3応募枠（変更前).部店コード="---" の場合は、
                // 全部店の応募枠を加算する必要が無いのでこの処理をスキップする。
                // １－２－１）
                //  "変更後ハッシュ"からWEB3応募枠（変更前).部店コードと同じ部店コードの値が取得できた場合
                //  部店別応募枠合計 += ハッシュから取得した値
                if (l_blnIsIncludeAllBranch)
                {
                    l_dblRecruitLimitBefore = l_dblRecruitLimitTemp;
                    continue;
                }
                l_dblBranchRecruitLimitTotal += l_dblRecruitLimit;
            }
            else
            {
                // １－２－２）
                //  取得できなかった場合
                //部店別応募枠合計 += WEB3応募枠（変更前）.WEB3応募枠
                if (l_blnIsIncludeAllBranch)
                {
                    l_dblRecruitLimitBefore = l_dblRecruitLimitTemp;
                    continue;
                }
                l_dblBranchRecruitLimitTotal += l_dblRecruitLimitTemp;
            }
        }

        //２）③の条件を満たすかどうかのチェックを行う
        // ２－１）比較対象となる全部店応募枠の決定
        double l_dblAllBranchRecruitLimit = 0;

        // ２－１－１） "変更後ハッシュ"から部店コード = "---" の値が取得できた場合
        //  全部店応募枠 = ハッシュから取得した値
        if (l_hmUpdatedMap.containsKey(WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH))
        {
            l_dblAllBranchRecruitLimit =
                Double.parseDouble((String)l_hmUpdatedMap.get(WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH));
        }
        else
        {
            //  ２－１－２） 取得できなかった場合
            //   全部店応募枠 = WEB3応募枠（変更前）.WEB3応募枠(*）
            //   (*)全部店（部店コード = "---"）のもの
            l_dblAllBranchRecruitLimit = l_dblRecruitLimitBefore;
        }

        // ２－２）
        //以下の条件を満たす場合、
        //  「部店別の応募枠合計が全部店の応募枠を超えています。」の例外をスローする。
        //  全部店応募枠 ＜ 部店別応募枠合計
        if (l_dblAllBranchRecruitLimit < l_dblBranchRecruitLimitTotal)
        {
            log.debug("部店別の応募枠合計が全部店の応募枠を超えています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02886,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店別の応募枠合計が全部店の応募枠を超えています。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update応募枠)<BR>
     * 債券部店別応募枠テーブルを更新する。<BR>
     * <BR>
     * 1）引数.国内債券部店別応募枠情報の配列の要素数分以下のLOOP処理を行なう。<BR>
     * <BR>
     * 　@1-1)債券部店別応募枠Paramsオブジェクトを生成する。<BR>
     * 　@　@　@[引数]　@銘柄ID：引数.銘柄ID<BR>
     * 　@　@　@　@　@　@　@　@証券会社コード：引数.管理者.get.証券会社コード<BR>
     * 　@　@　@　@　@　@　@　@部店コード：引数.国内債券部店別応募枠情報.部店コード<BR>
     * <BR>
     * 　@1-2）債券部店別応募枠Paramsオブジェクトのクローンを生成する。<BR>
     * <BR>
     * 　@1-3）クローンにプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@　@応募枠　@　@　@　@　@　@：引数.国内債券部店別応募枠.WEB3応募枠<BR>
     * 　@　@　@　@最終更新者コード：引数.管理者.get管理者コード<BR>
     * 　@　@　@　@更新日付　@　@　@　@：<BR>
     * 　@　@　@　@　@GtlUtils.getTradingSystem(　@).getSystemTimestamp(　@)の戻り値<BR>
     * <BR>
     * 　@1-4）クローンの内容でDBを更新する。<BR>
     * <BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@param l_strProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_bondDomesticBranchRecruitLimitInfos - (国内債券部店別応募枠情報)<BR>
     * 国内債券部店別応募枠情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 469C22EE00D7
     */
    protected void updateRecruitLimit(
        WEB3Administrator l_administrator,
        String l_strProductId,
        WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateRecruitLimit(WEB3Administrator, String,"
            + " WEB3BondDomesticBranchRecruitLimitInfo[])";
        log.entering(STR_METHOD_NAME);

        if (l_bondDomesticBranchRecruitLimitInfos == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //1）引数.国内債券部店別応募枠情報の配列の要素数分以下のLOOP処理を行なう。
        for (int i = 0; i < l_bondDomesticBranchRecruitLimitInfos.length; i++)
        {
            WEB3BondDomesticBranchRecruitLimitInfo l_branchRecruitLimitInfo =
                l_bondDomesticBranchRecruitLimitInfos[i];
            //1-1)債券部店別応募枠Paramsオブジェクトを生成する。
            //引数]  銘柄ID：引数.銘柄ID
            //       証券会社コード：引数.管理者.get.証券会社コード
            //       部店コード：引数.国内債券部店別応募枠情報.部店コード
            BondBranchRecruitLimitRow l_bondBranchRecruitLimitRow = null;
            try
            {
                l_bondBranchRecruitLimitRow =
                    (BondBranchRecruitLimitRow)BondBranchRecruitLimitDao.findRowByProductIdInstitutionCodeBranchCode(
                        Long.parseLong(l_strProductId),
                        l_administrator.getInstitutionCode(),
                        l_branchRecruitLimitInfo.branchCode);
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

            if (l_bondBranchRecruitLimitRow != null)
            {
                BondBranchRecruitLimitParams l_params =
                    new BondBranchRecruitLimitParams(l_bondBranchRecruitLimitRow);

                //1-2）債券部店別応募枠Paramsオブジェクトのクローンを生成する
                BondBranchRecruitLimitParams l_updateParams =
                    new BondBranchRecruitLimitParams(l_params);

                //1-3）クローンにプロパティをセットする。
                // 応募枠　@　@　@　@　@　@：引数.国内債券部店別応募枠.WEB3応募枠
                l_updateParams.setRecruitLimit(
                    Double.parseDouble(l_branchRecruitLimitInfo.web3RecruitLimit));

                // 最終更新者コード：引数.管理者.get管理者コード
                l_updateParams.setLastUpdater(l_administrator.getAdministratorCode());

                // 更新日付          ：GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
                l_updateParams.setLastUpdatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_updateParams);
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
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate応募枠変更可能)<BR>
     * 応募枠の変更可能チェック<BR>
     * <BR>
     * １）債券プロダクトマネージャ.get債券銘柄()より債券銘柄を取得する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@　@銘柄ID　@：引数.銘柄ID<BR>
     * <BR>
     * ２）応募枠変更可能期間のチェック<BR>
     * 　@　@　@債券銘柄.応募終了日（SONAR)＜現在日時　@の場合、<BR>
     * 　@　@　@例外「応募枠変更可能期間外です。」をスローする。<BR>
     * 　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@tag　@　@:　@BUSINESS_ERROR_02901<BR>
     * <BR>
     * @@param l_strProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@throws WEB3BaseException
     */
    protected void validateRecruitLimitChangePossibility(String l_strProductId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRecruitLimitChangePossibility(String)";
        log.entering(STR_METHOD_NAME);

        // １）債券プロダクトマネージャ.get債券銘柄()より債券銘柄を取得する。
        // 　@　@[引数]
        // 　@　@　@　@銘柄ID　@：引数.銘柄ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();

        WEB3BondProduct l_bondProduct  =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(Long.parseLong(l_strProductId));

        // ２）応募枠変更可能期間のチェック
        // 　@　@　@債券銘柄.応募終了日（SONAR)＜現在日時　@の場合、
        // 　@　@　@例外「応募枠変更可能期間外です。」をスローする。
        if (WEB3DateUtility.compareToDay(l_bondProduct.getHostRecruitEndDate(),
            GtlUtils.getSystemTimestamp()) < 0)
        {
            log.debug("応募枠変更可能期間外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02901,
                this.getClass().getName() + STR_METHOD_NAME,
                "応募枠変更可能期間外です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
