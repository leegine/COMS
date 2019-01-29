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
filename	WEB3BondDomesticApplyProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募銘柄一覧サービスImpl(WEB3BondDomesticApplyProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.224
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondOrderManagerReusableValidationsCheck;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDivDef;
import webbroker3.bd.define.WEB3TradingPossDivDef;
import webbroker3.bd.message.WEB3BondDomesticApplyProductInfo;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyProductListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (国内債券応募銘柄一覧サービスImpl)<BR>
 * 国内債券応募銘柄一覧サービスImpl<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductListServiceImpl
    extends WEB3BondClientRequestService
    implements WEB3BondDomesticApplyProductListService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyProductListServiceImpl.class);

    /**
     * @@roseuid 46A473FC031C
     */
    public WEB3BondDomesticApplyProductListServiceImpl()
    {

    }

    /**
     * 国内債券応募銘柄一覧サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「国内債券応募銘柄一覧」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CD702033F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        if (!(l_request instanceof WEB3BondDomesticApplyProductListRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        WEB3BondDomesticApplyProductListRequest l_listRequest = (WEB3BondDomesticApplyProductListRequest)l_request;
        WEB3BondDomesticApplyProductListResponse l_listResponse = null;

        List l_lisBondDomesticApplyProductInfos = new ArrayList();

        //validate( )
        l_listRequest.validate();

        //validate注文受付可能
        WEB3BondTradingTimeManagement.validateOrderAccept();

        //get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        //create検索条件文字列(String)
        //[create検索条件文字列()に渡す引数]
        //債券区分：　@リクエストデータ.債券区分
        String l_strQueryString = this.createQueryString(l_listRequest.bondDiv);

        //create検索条件データコンテナ(String)
        //[create検索条件データコンテナ()に渡す引数]
        //債券区分：　@リクエストデータ.債券区分
        Object[] l_queryDataContainer = this.createQueryDataContainer(l_listRequest.bondDiv);

        //createソート条件文字列(String)
        //[createソート条件文字列()に渡す引数]
        //債券区分：　@リクエストデータ.債券区分
        String l_strSortCond = this.createSortCondString(l_listRequest.bondDiv);

        //get債券銘柄リスト(String, String, Object[], String)
        //[get債券銘柄リスト()に渡す引数]
        //証券会社コード：　@取得した補助口座.getInstitution().getInstitutionCode()の戻り値
        //検索条件文字列：　@create検索条件文字列()の戻り値
        //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値
        //ソート条件文字列：　@createソート条件文字列()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        List l_lisBondProducts = l_bondProductManager.getBondProductList(
            l_strInstitutionCode,
            l_strQueryString,
            l_queryDataContainer,
            l_strSortCond);

        //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[引数]
        //l_list：　@get債券銘柄リスト()の戻り値
        //l_intRequestPageIndex：　@リクエストデータ.要求ページ番号
        //l_intRequestPageSize：　@リクエストデータ.ページ内表示行数
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisBondProducts,
            Integer.parseInt(l_listRequest.pageIndex),
            Integer.parseInt(l_listRequest.pageSize));

        //getArrayReturned(l_classType : Class)
        Object[] l_arrayReturned = l_pageIndexInfo.getArrayReturned(BondProductRow.class);

        try
        {
            //getArrayReturned()の戻り値の要素数分Loop処理
            for (int i = 0; i < l_arrayReturned.length; i++)
            {
                //税率(証券会社コード : String, 税種類 : String, 発注日 : Date)
                //　@　@　@証券会社コード：債券銘柄.証券会社コード
                //  　@　@税種類　@　@　@　@　@：国内債券源泉徴収率
                //  　@　@発注日　@　@　@　@　@：債券銘柄.get国内債券発注日
                BondProductRow l_bondProductRow = (BondProductRow)l_arrayReturned[i];
                WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductRow);
                WEB3GentradeTaxRate l_gentradeTaxRate = new WEB3GentradeTaxRate(
                    l_bondProduct.getInstitution().getInstitutionCode(),
                    WEB3DutyTypeDef.DOMESTIC_BOND_WITHHOLDING_TAX,
                    new Timestamp(l_bondProduct.getBondDomesticBizDate().getTime()));

                boolean l_blnValidationsCheckFlag = true;
                try
                {
                    //validate国内債券応募枠(long, 債券銘柄, double)
                    //[引数]
                    //　@部店ID：取得した補助口座.getMainAccountRow().getBranchId()
                    //  債券銘柄：取得した債券銘柄
                    //  注文数量：0
                    WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                        new WEB3BondOrderManagerReusableValidationsCheck();
                    l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(
                        ((SubAccountRow)l_subAccount.getDataSourceObject()).getBranchId(),
                        l_bondProduct,
                        0);
                }
                catch (WEB3BaseException l_ex)
                {
                    l_blnValidationsCheckFlag = false;
                }

                //国内債券応募銘柄情報( )
                WEB3BondDomesticApplyProductInfo l_bondDomesticApplyProductInfo =
                    new WEB3BondDomesticApplyProductInfo();

                //銘柄ID          = 債券銘柄.銘柄ID
                l_bondDomesticApplyProductInfo.productId = String.valueOf(l_bondProduct.getProductId());
                //銘柄名          = 債券銘柄.銘柄名
                l_bondDomesticApplyProductInfo.productName = l_bondProduct.getProductName();
                //応募開始日   = 債券銘柄.取扱開始日時
                l_bondDomesticApplyProductInfo.recruitStartDate =
                    l_bondProduct.getTradeStartDate();
                //応募終了日   = 債券銘柄.取扱終了日時
                l_bondDomesticApplyProductInfo.recruitEndDate =
                    l_bondProduct.getTradeEndDate();
                //利率             = 債券銘柄.利率
                l_bondDomesticApplyProductInfo.coupon = String.valueOf(l_bondProduct.getCoupon());
                //利率(課税後) = 債券銘柄.利率 × ( 1 - (税率.get税率×0.01))
                BigDecimal l_bdCoupon = new BigDecimal(String.valueOf(l_bondProduct.getCoupon()));
                BigDecimal l_dbTaxRate = new BigDecimal(String.valueOf(l_gentradeTaxRate.getTaxRate()));
                l_bondDomesticApplyProductInfo.rateAfterTax =
                    WEB3StringTypeUtility.formatNumber(
                        l_bdCoupon.multiply(
                            (new BigDecimal(String.valueOf(1)).subtract(
                                l_dbTaxRate.multiply(
                                    new BigDecimal(String.valueOf(0.01)))))).doubleValue());
                //応募単価       = 債券銘柄.買付単価
                l_bondDomesticApplyProductInfo.applyPrice =
                    WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
                //申込単位       = 債券銘柄.申込単位
                l_bondDomesticApplyProductInfo.applyUnit =
                    WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());
                //発行日          = 債券銘柄.発行日
                l_bondDomesticApplyProductInfo.issueDate = l_bondProduct.getIssueDate();
                //償還日          = 債券銘柄.償還日
                l_bondDomesticApplyProductInfo.maturityDate = l_bondProduct.getMaturityDate();
                //利払日1         = 債券銘柄.利払日1
                l_bondDomesticApplyProductInfo.couponPaymentDate1 = l_bondProduct.getInterestPaymentDay1();
                //利払日2         = 債券銘柄.利払日2
                l_bondDomesticApplyProductInfo.couponPaymentDate2 = l_bondProduct.getInterestPaymentDay2();
                //年間利払回数 = 債券銘柄.年間利払回数
                l_bondDomesticApplyProductInfo.yearlyInterestPayments =
                    String.valueOf(l_bondProduct.getYearlyInterestPayments());

                //セッションより取得した注文チャネル
                OpLoginSecurityService l_opLoginSecurityService =
                    (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                String l_strOrderChannel =
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

                //取扱開始日時 =
                //セッションより取得した注文チャネルがコールセンターor営業店の場合、債券銘柄.取扱開始日時
                //セッションより取得した注文チャネルがそれ以外の場合、債券銘柄.応募開始日
                if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChannel)
                    || WEB3ChannelDef.BRANCH.equals(l_strOrderChannel))
                {
                    l_bondDomesticApplyProductInfo.tradeStartDate = l_bondProduct.getTradeStartDate();
                }
                else
                {
                    l_bondDomesticApplyProductInfo.tradeStartDate = l_bondProduct.getRecruitStartDate();
                }

                //取扱終了日時 =
                //セッションより取得した注文チャネルがコールセンターor営業店の場合、債券銘柄.取扱終了日時
                //セッションより取得した注文チャネルがそれ以外の場合、債券銘柄.応募終了日
                if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChannel)
                    || WEB3ChannelDef.BRANCH.equals(l_strOrderChannel))
                {
                    l_bondDomesticApplyProductInfo.tradeEndDate = l_bondProduct.getTradeEndDate();
                }
                else
                {
                    l_bondDomesticApplyProductInfo.tradeEndDate = l_bondProduct.getRecruitEndDate();
                }

                //取引可能区分 =
                //債券銘柄.is国内債券応募可能()==true かつ validate国内債券応募枠が正常終了の場合、1:可能
                //債券銘柄.is国内債券応募可能()==true かつ validate国内債券応募枠がエラーの場合、2:応募枠超過
                //債券銘柄.is国内債券応募可能()==falseの場合、0:不可
                if (l_bondProduct.isBondDomesticApplyPossible())
                {
                    if (l_blnValidationsCheckFlag)
                    {
                        l_bondDomesticApplyProductInfo.tradingPossDiv = WEB3TradingPossDivDef.SELL_POSS;
                    }
                    else
                    {
                        l_bondDomesticApplyProductInfo.tradingPossDiv = WEB3TradingPossDivDef.OVER_RECRUIT_LIMIT;
                    }
                }
                else
                {
                    l_bondDomesticApplyProductInfo.tradingPossDiv = WEB3TradingPossDivDef.SELL_POSS_NO;
                }

                l_lisBondDomesticApplyProductInfos.add(l_bondDomesticApplyProductInfo);
            }

            //getPageIndex( )
            int l_intPageIndex = l_pageIndexInfo.getPageIndex();

            //getTotalPages( )
            int l_intTotalPages = l_pageIndexInfo.getTotalPages();

            //getTotalRecords( )
            int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

            //createResponse( )
            l_listResponse =
                (WEB3BondDomesticApplyProductListResponse)l_listRequest.createResponse();

            //プロパティ・セット
            //国内債券応募銘柄一覧    = 国内債券応募銘柄情報の配列
            WEB3BondDomesticApplyProductInfo[] l_bondDomesticApplyProductInfos =
                new WEB3BondDomesticApplyProductInfo[l_lisBondDomesticApplyProductInfos.size()];
            l_lisBondDomesticApplyProductInfos.toArray(l_bondDomesticApplyProductInfos);
            l_listResponse.bondDomesticApplyProductList = l_bondDomesticApplyProductInfos;

            //表示ページ番号       = getPageIndex()の戻り値
            l_listResponse.pageIndex = String.valueOf(l_intPageIndex);

            //総ページ数     = getTotalPages()の戻り値
            l_listResponse.totalPages = String.valueOf(l_intTotalPages);

            //総レコード数        = getTotalRecords()の戻り値
            l_listResponse.totalRecords = String.valueOf(l_intTotalRecords);
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
        return l_listResponse;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * (1)以下の文字列を作成する。<BR>
     * <BR>
     * 　@" 応募開始日(SONAR) <= ? and 応募終了日(SONAR) >= ?<BR>
     * 　@　@and 債券タイプ <> ?　@and 取扱区分　@= ? "<BR>
     * <BR>
     * (2)引数.債券区分 == ”個人向け国債以外”の場合、<BR>
     * 　@以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and 債券タイプ <> ? "<BR>
     * <BR>
     * (3)引数.債券区分 == ”個人向け国債のみ”の場合、<BR>
     * 　@以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and 債券タイプ = ? "<BR>
     * <BR>
     * (4)作成した文字列を返却する。<BR>
     * @@param l_strBondDiv - (債券区分)<BR>
     * 債券区分<BR>
     * @@return String
     * @@roseuid 466CD702033F
     */
    protected String createQueryString(String l_strBondDiv)
    {
        final String STR_METHOD_NAME = " createQueryString(String)";
        log.entering(STR_METHOD_NAME);

        //(1)以下の文字列を作成する。
        //" 応募開始日(SONAR) <= ? and 応募終了日(SONAR) >= ?
        //  and 債券タイプ <> ?　@and 取扱区分　@= ? "
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append(" host_recruit_start_date <= ? ");
        l_sbQueryString.append(" AND host_recruit_end_date >= ? ");
        l_sbQueryString.append(" AND bond_type <> ? ");
        l_sbQueryString.append(" AND trade_handle_div = ? ");

        //(2)引数.債券区分 == ”個人向け国債以外”の場合、以下の文字列を最後尾に追加する。
        //" and 債券タイプ <> ? "
        if (WEB3BondDivDef.EXCEPT_INDIVIDUAL_GOVERNMENT_BOND.equals(l_strBondDiv))
        {
            l_sbQueryString.append(" AND bond_type <> ? ");
        }

        //(3)引数.債券区分 == ”個人向け国債のみ”の場合、以下の文字列を最後尾に追加する。
        //" and 債券タイプ = ? "
        if (WEB3BondDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strBondDiv))
        {
            l_sbQueryString.append(" AND bond_type = ? ");
        }

        //(4)作成した文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データをObjectの配列に設定しレスポンスする。<BR>
     * <BR>
     * (1)Objectの配列を作成する。<BR>
     * <BR>
     * (2)現在日時を取得する。<BR>
     * <BR>
     * (3)(1)で作成した配列に以下をセットする。<BR>
     * <BR>
     * 　@　@①@(2)で取得した現在日時<BR>
     * 　@　@②(2)で取得した現在日時(YYYYMMDDのみ)<BR>
     * 　@　@③"外国債券"（債券タイプ）<BR>
     * 　@　@④"管理者/顧客"(取扱区分)<BR>
     * <BR>
     * (4)引数.債券区分 <> ”全部”の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@⑤"個人向け国債"（債券タイプ）<BR>
     * <BR>
     * (5)作成した配列を返却する。<BR>
     * @@param l_strBondDiv - (債券区分)<BR>
     * 債券区分<BR>
     * @@return Object[]
     * @@roseuid 466CD702033F
     */
    protected Object[] createQueryDataContainer(String l_strBondDiv)
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String)";
        log.entering(STR_METHOD_NAME);

        //(1)Objectの配列を作成する。
        Object[] l_queryDataContainer = null;
        List l_lisQueryDataContainer = new ArrayList();

        //(2)現在日時を取得する。
        Timestamp l_tsNewTime = GtlUtils.getSystemTimestamp();

        //(3)(1)で作成した配列に以下をセットする。
        //　@　@①@(2)で取得した現在日時
        //　@  ②(2)で取得した現在日時(YYYYMMDDのみ)
        //　@  ③"外国債券"（債券タイプ）
        // 　@ ④"管理者/顧客"(取扱区分)
        l_lisQueryDataContainer.add(l_tsNewTime);
        l_lisQueryDataContainer.add(WEB3DateUtility.toDay(l_tsNewTime));
        l_lisQueryDataContainer.add(BondTypeEnum.FOREIGN_BOND);
        l_lisQueryDataContainer.add(WEB3TradeHandleDivDef.MANAGER_CUSTOMER);

        //(4)引数.債券区分 <> ”全部”の場合、配列に以下を追加する。
        //⑤"個人向け国債"（債券タイプ）
        if (!WEB3BondDivDef.ALL_BOND.equals(l_strBondDiv))
        {
            l_lisQueryDataContainer.add(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
        }

        //(5)作成した配列を返却する。
        l_queryDataContainer = new Object[l_lisQueryDataContainer.size()];
        l_lisQueryDataContainer.toArray(l_queryDataContainer);

        log.exiting(STR_METHOD_NAME);
        return l_queryDataContainer;
    }

    /**
     * (createソート条件文字列)<BR>
     * ソート条件文字列を作成する。<BR>
     * <BR>
     * 以下の条件でソート条件文字列を作成し、作成した文字列を返却する。<BR>
     * <BR>
     * (1)引数.債券区分 == ”個人向け国債のみ”の場合。<BR>
     * <BR>
     * 　@　@[ソート条件]<BR>
     * 　@　@"償還期限"の昇順<BR>
     * 　@　@"回号コード(SONAR)"の昇順<BR>
     * <BR>
     * (2)引数.債券区分 <> ”個人向け国債のみ”の場合。<BR>
     * <BR>
     * 　@　@[ソート条件]<BR>
     * 　@　@"銘柄コード（SONAR）"の昇順、<BR>
     * 　@　@"回号コード(SONAR)"の昇順<BR>
     * @@param l_strBondDiv - (債券区分)<BR>
     * 債券区分<BR>
     * @@return String
     * @@roseuid 466CD702033F
     */
    protected String createSortCondString(String l_strBondDiv)
    {
        final String STR_METHOD_NAME = " createSortCondString(String)";
        log.entering(STR_METHOD_NAME);

        //(1)引数.債券区分 == ”個人向け国債のみ”の場合。
        //    [ソート条件]
        //"償還期限"の昇順
        //　@"回号コード(SONAR)"の昇順
        StringBuffer l_strSortCond = new StringBuffer();
        if (WEB3BondDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strBondDiv))
        {
            l_strSortCond.append(" redemption_term ASC, ");
            l_strSortCond.append(" host_product_issue_code ASC ");
        }
        else
        {
            //(2)引数.債券区分 <> ”個人向け国債のみ”の場合。
            //    [ソート条件]
            //"銘柄コード（SONAR）"の昇順、
            //"回号コード(SONAR)"の昇順
            l_strSortCond.append(" host_product_code ASC, ");
            l_strSortCond.append(" host_product_issue_code ASC ");
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSortCond.toString();
    }
}
@
