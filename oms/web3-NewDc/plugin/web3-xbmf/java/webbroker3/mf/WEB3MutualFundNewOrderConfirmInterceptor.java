head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundNewOrderConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信新規注文確定インタセプタ(WEB3MutualFundNewOrderConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/08 黄建 (中訊) 新規作成
Revesion History : 2004/12/06 于美麗 (中訊) 残対応
Revesion History : 2006/10/11 張騰宇 (中訊) モデル504
Revesion History : 2007/03/09 張騰宇 (中訊) モデル549
Revesion History : 2007/03/26 趙林鵬 (中訊) モデル No.550
*/
package webbroker3.mf;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信新規注文確定インタセプタ<BR>
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundNewOrderConfirmInterceptor
    extends WEB3DefaultMutualFundOrderConfirmInterceptor
{

    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundNewOrderConfirmInterceptor.class);

    /**
     * 注文チャネル<BR>
     * <BR>
     * 0：営業店　@1：インターネット　@2：コールセンタ　@3：モバイル <BR>
     */
    protected String orderChannel;

    /**
     * 計算基準価額<BR>
     */
    protected double constantValue;

    /**
     * 計算基準価額（乗換先）<BR>
     */
    protected double switchingConstantValue;

    /**
     * 概算受渡代金<BR>
     */
    protected double estimatedPrice;

    /**
     * 概算売買口数<BR>
     */
    protected double estimatedQty;

    /**
     * 受渡方法@<BR>
     */
    protected String deliveryDiv;

    /**
     * 投信タイプ<BR>
     * <BR>
     * 0：その他　@1：国内　@2：国外　@3：外貨MMF<BR>
     */
    protected String mutualFundType;

    /**
     * 約定日<BR>
     */
    protected Timestamp executionTimestamp;

    /**
     * 決済区分<BR>
     * <BR>
     * 1：円貨　@2：外貨<BR>
     */
    protected String settlementType;

    /**
     * 無手数料区分<BR>
     * <BR>
     * 空白：無関係　@5：乗換優遇　@9：無手数料<BR>
     */
    protected String noCommissionDivision;

    /**
     * 銘柄コード（乗換先）<BR>
     */
    protected String switchingSubjectMutualProductCode;

    /**
     * 請求区分<BR>
     * <BR>
     * 0：解約　@1：買取<BR>
     */
    protected String requestDivision;

    /**
     * 注文経路区分<BR>
     * <BR>
     * 1：コールセンター　@2：ＰＣ　@3:スリングショット <BR>
     * 4：i-mode　@5：Vodafone　@6：AU　@9：HOST <BR>
     * （コールセンターの時のみ使用）<BR>
     */
    protected String orderChannelDivision;

    /**
     * 受渡日<BR>
     */
    protected Timestamp deliveryDate;

    /**
     * 投信解約区分<BR>
     */
    protected String mutualFundSellDiv;

    /**
     * 基準価額適用日<BR>
     */
    protected Timestamp constantValueAppDate;

    /**
     * 概算買付口数（乗換先）<BR>
     */
    protected double switchingEstimatedQty;

    /**
     * 税区分（乗換先）<BR>
     */
    protected TaxTypeEnum switchingSubjectTaxDivision;

    /**
     * 出金注文識別コード<BR>
     */
    protected String paymentOrderReqNumber;

    /**
     * (一括区分)<BR>
     * 一括区分<BR>
     */
    protected boolean  norealDiv;

    /**
     * (注文終了日)<BR>
     * 注文終了日<BR>
     */
    protected Date orderEndDate;

    /**
     * (投信新規注文確定インタセプタ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40AD8E0E03B4
     */
    public WEB3MutualFundNewOrderConfirmInterceptor()
    {
    }

    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、投信注文履歴データの作成・更新時に呼ばれる。<BR>
     * 引数で与えられた投信注文履歴Paramsに値を設定し、投信注文履歴Paramsを返す。 <BR>
     * <BR>
     * １）　@注文エラー理由コードの設定を行う。 <BR>
     * 　@投信注文履歴Params.set注文エラー理由コード()をコールし、注文エラー理由コードの設定を行う。 <BR>
     * 　@［set注文エラー理由コードに渡すパラメタ］ <BR>
     * 　@　@注文エラー理由コード： null <BR>
     * <BR>
     * ２）　@引数で与えられた投信注文履歴Paramsを返す。 <BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderActionParams - (投信注文履歴Params)<BR>
     * 永続化前の投信注文履歴Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AD8E93025C
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderActionParams l_mutualFundOrderActionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "MutualFundOrderActionParams l_mutualFundOrderActionParams) )";
        log.entering(STR_METHOD_NAME);

        if  (l_mutualFundOrderActionParams == null)
        {
            log.debug(" パラメータNull出来ない。With " +
                "(永続化前の投信注文履歴Params)l_mutualFundOrderActionParams" +
                    l_mutualFundOrderActionParams);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータはNullである");
        }
        // １）　@注文エラー理由コードの設定を行う。
        l_mutualFundOrderActionParams.setErrorReasonCode(null);

        //２）　@引数で与えられた投信注文履歴Paramsを返す。
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderActionParams;
    }

    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、投信注文単位データの作成・更新時に呼ばれる。<BR>
     * 引数で与えられた投信注文単位Paramsに値を設定し、投信注文単位Paramsを返す。<BR>
     * <BR>
     * １）　@補助口座オブジェクトの取得<BR>
     * 　@－拡張アカウントマネージャ.getSubAccount()をコールして、補助口座オブジェクト<BR>
     * 　@　@を取得する。<BR>
     * 　@　@［getSubAccountに渡すパラメタ］<BR>
     * 　@　@　@口座ID： 投信注文単位Params.getAccountId()の戻り値<BR>
     * 　@　@　@補助口座ID： 投信注文単位Params.getSubAccountId()の戻り値<BR>
     * <BR>
     * ２）　@受注日時の設定を行う。<BR>
     * 　@－ThreadLocalSystemAttributesRegistry.getAttribute( )をコールし、<BR>
     * 処理日時を取得する。 <BR>
     * 　@　@［getAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー： ”xblocks.gtl.attributes.systemtimestamp”<BR>
     * 　@－投信注文単位Params.set受注日時()をコールし、受注日時の設定を行う。<BR>
     * 　@　@［set受注日時に渡すパラメタ］<BR>
     * 　@　@　@受注日時： 取得した処理日時<BR>
     * <BR>
     * ３）　@扱者コード(SONAR)の設定を行う。<BR>
     * 　@－投信注文単位Params.set扱者コード（SONAR）()をコールし、<BR>
     * 扱者コード(SONAR)の設定を行う。 <BR>
     * 　@　@［set扱者コード（SONAR）に渡すパラメタ］<BR>
     * 　@　@　@扱者コード(SONAR)： <BR>
     * 取得した補助口座.getMainAccount().getDataSourceObject().get扱者コード（SONAR）()の戻り値<BR>
     * <BR>
     * ４）　@識別コードの設定を行う。<BR>
     * 　@－注文識別コード採番サービス.get新規識別コード()をコールする。 <BR>
     * 　@　@[get新規識別コードに渡すパラメタ] <BR>
     * 　@　@　@証券会社コード： 取得した補助口座.getInstitution().getInstitutionCode() の戻り値 <BR>
     * 　@　@　@部店コード： 取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値 <BR>
     * 　@　@　@銘柄タイプ： ProductTypeEnum.投資信託 <BR>
     * <BR>
     * 　@－投信注文単位Params.set識別コード()をコールし、識別コードの設定を行う。<BR>
     * 　@　@［set識別コードに渡すパラメタ］<BR>
     * 　@　@　@識別コード： 取得した識別コード<BR>
     * <BR>
     * ５）　@発注日の設定を行う。  <BR>
     *　@－投信注文単位Params.set発注日()をコールし、発注日の設定を行う。 <BR>
     *　@　@［set発注日に渡すパラメタ］ <BR>
     *　@　@　@this.一括区分がtrueなら、 <BR>
     *　@　@　@　@this.注文終了日を設定する。 <BR>
     *　@　@　@this.一括区分がfalseなら、 <BR>
     *　@　@　@　@投信取引時間管理.get投信発注日(引数無し)の戻り値を設定する。<BR>
     * <BR>
     * ６）　@計算基準価額（乗換先）の設定を行う。<BR>
     * 　@－投信注文単位Params.set計算基準価額（乗換先）()をコールし、<BR>
     * 計算基準価額（乗換先）の設定を行う。<BR>
     * 　@　@［set計算基準価額（乗換先）に渡すパラメタ］<BR>
     * 　@　@　@　@(*) Double.isNan(this.get計算基準価額（乗換先）())がtrueを返す場合は、<BR>
     * 　@　@　@　@　@　@(Double)nullを設定する。<BR>
     * 　@　@　@　@(*) Double.isNan(this.get計算基準価額（乗換先）())がfalseを返す場合は、<BR>
     * 　@　@　@　@　@　@this.get計算基準価額（乗換先）()の戻り値を設定する。<BR>
     * <BR>
     * ７）　@概算買付口数（乗換先）の設定を行う。<BR>
     * 　@－投信注文単位Params.set概算買付口数（乗換先）()をコールし、<BR>
     * 概算買付口数（乗換先）の設定を行う。<BR>
     * 　@　@［set概算買付口数（乗換先）に渡すパラメタ］<BR>
     * 　@　@　@　@(*) Double.isNan(this.get概算買付口数（乗換先）())がtrueを返す場合は、<BR>
     * 　@　@　@　@　@　@(Double)nullを設定する。<BR>
     * 　@　@　@　@(*) Double.isNan(this.get概算買付口数（乗換先）())がfalseを返す場合は、<BR>
     * 　@　@　@　@　@　@this.get概算買付口数（乗換先）()の戻り値を設定する。<BR>
     * <BR>
     * ８）　@投信注文単位Paramsに以下の設定を行う。<BR>
     * <BR>
     * 　@　@投信注文単位Params.set受渡日(this.get受渡日( ))<BR>
     * 　@　@投信注文単位Params.set初回注文の注文チャネル(this.get注文チャネル( ))<BR>
     * 　@　@投信注文単位Params.set計算基準価額(this.get計算基準価額( ))<BR>
     * 　@　@投信注文単位Params.set基準価額適用日(this.get基準価額適用日( ))<BR>
     * 　@　@投信注文単位Params.set概算受渡代金(this.get概算受渡代金( ))<BR>
     * 　@　@投信注文単位Params.set概算売買口数(this.get概算売買口数( ))<BR>
     * 　@　@投信注文単位Params.set税区分（乗換先）(this.get税区分（乗換先）( ))<BR>
     * 　@　@投信注文単位Params.set受渡方法@(this.get受渡方法@( ))<BR>
     * 　@　@投信注文単位Params.set投信タイプ(this.get投信タイプ( ))<BR>
     * 　@　@投信注文単位Params.set投信解約区分(this.get投信解約区分())<BR>
     * 　@　@投信注文単位Params.set約定日(this.get約定日( ))<BR>
     * 　@　@投信注文単位Params.set約定状態(null)<BR>
     * 　@　@投信注文単位Params.set決済区分(this.get決済区分( ))<BR>
     * 　@　@投信注文単位Params.set無手数料区分(this.get無手数料区分( ))<BR>
     * 　@　@投信注文単位Params.set銘柄コード（乗換先）(this.get銘柄コード（乗換先）( ))<BR>
     * 　@　@投信注文単位Params.set請求区分(this.get請求区分( ))<BR>
     * 　@　@投信注文単位Params.set注文経路区分(this.get注文経路区分( ))<BR>
     * 　@　@投信注文単位Params.set注文エラー理由コード(null)<BR>
     *     投信注文単位Params.set出金注文識別コード(this.get出金注文識別コード( ))
     * <BR>
     * ９）　@引数で与えられた投信注文単位Paramsを返す。<BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderUnitParams - (投信注文単位Params)<BR>
     * 永続化前の投信注文単位Params<BR>
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AD8E93026C
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderUnitParams l_mutualFundOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if(l_mutualFundOrderUnitParams == null)
        {
            log.debug(" パラメータNull出来ない。With " +
                "(永続化前の投信注文単位Params)l_mutualFundOrderUnitParams =" +
                    l_mutualFundOrderUnitParams);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータはNullである");
        }

        //１）　@補助口座オブジェクトの取得

        //補助口座
        SubAccount l_subAccount = null;

        //拡張アカウントマネージャ取得
        FinApp l_finApp =
            (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManaer =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //－拡張アカウントマネージャ.getSubAccount()をコールして、
             //補助口座オブジェクトを取得する。
            l_subAccount =
                l_gentradeAccountManaer.getSubAccount(
                    l_mutualFundOrderUnitParams.getAccountId(),
                    l_mutualFundOrderUnitParams.getSubAccountId());
            log.debug("getAccountId" + l_mutualFundOrderUnitParams.getAccountId());
            log.debug("getSubAccountId" + l_mutualFundOrderUnitParams.getSubAccountId());
            log.debug("l_subAccount" + l_subAccount);

            // ２）　@受注日時の設定を行う。

            //－ThreadLocalSystemAttributesRegistry.getAttribute( )
             //をコールし、処理日時を取得する。
            Date l_datDeliveryDate =
                (Date) ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

            //－投信注文単位Params.set受注日時()をコールし、受注日時の設定を行う。
            l_mutualFundOrderUnitParams.setReceivedDateTime(l_datDeliveryDate);

            //３）　@扱者コード(SONAR)の設定を行う。

            //－投信注文単位Params.set扱者コード（SONAR）()をコールし、
             //扱者コード(SONAR)の

            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
            String l_strSonarTraderCode =
                l_mainAccountRow.getSonarTraderCode();
            l_mutualFundOrderUnitParams.setSonarTraderCode(l_strSonarTraderCode);

            //４）　@識別コードの設定を行う。

            //注文識別コード採番サービス
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);

            //注文識別コード採番サービス.get新規識別コード()をコールする。
            String l_strOrderRequestNumber =
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    ProductTypeEnum.MUTUAL_FUND);

            //－投信注文単位Params.set識別コード()をコールし、識別コードの設定を行う。
            l_mutualFundOrderUnitParams.setOrderRequestNumber(
                    l_strOrderRequestNumber);

           // ５）　@発注日の設定を行う。
           //　@－投信注文単位Params.set発注日()をコールし、発注日の設定を行う。
           //　@　@［set発注日に渡すパラメタ］
           //　@　@　@this.一括区分がtrueなら、
           //　@　@　@　@this.注文終了日を設定する。
           //　@　@　@this.一括区分がfalseなら、
           //　@　@　@　@投信取引時間管理.get投信発注日(引数無し)の戻り値を設定する。
            String l_strBizDate = "";
            if (this.norealDiv)
            {
                l_strBizDate = WEB3DateUtility.formatDate(this.orderEndDate,"yyyyMMdd");
            }
            else
            {
                l_strBizDate =
                    WEB3DateUtility.formatDate(
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(),
                        "yyyyMMdd");
            }
            l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        }
        catch (NotFoundException l_ex)
        {
            log.error("該当補助口座オブジェクトの不存在  with " +
                "(口座ID)l_lngAccountId =" +
                    l_mutualFundOrderUnitParams.getAccountId() +
                " and (補助口座ID)l_lngSubAccountId =" +
                    l_mutualFundOrderUnitParams.getSubAccountId());
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(" テーブルに該当するデータがありません。 With " +
                "(証券会社コード)l_strInstitutionCode =" +
                    l_subAccount.getInstitution().getInstitutionCode() +
                " and (部店コード)l_strBranchCode =" +
                    l_subAccount.getMainAccount().getBranch().getBranchCode());
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //６）　@計算基準価額（乗換先）の設定を行う。
         /*  (*) Double.isNan(this.get計算基準価額（乗換先）())がtrueを返す場合は
            、(Double)nullを設定する。
             (*) Double.isNan(this.get計算基準価額（乗換先）())がfalseを返す場合は、
             this.get計算基準価額（乗換先）()の戻り値を設定する。*/
        if (Double.isNaN(this.getSwitchingConstantValue()) == true)
        {
            l_mutualFundOrderUnitParams.setSwtCalcConstantValue(null);
        }
        else
        {
            l_mutualFundOrderUnitParams.setSwtCalcConstantValue(
                this.getSwitchingConstantValue());
        }

        //７）　@概算買付口数（乗換先）の設定を行う。
         /*  (*) Double.isNan(this.get概算買付口数（乗換先）())がtrueを返す場合は
            (Double)nullを設定する
             (*) Double.isNan(this.get概算買付口数（乗換先）())がfalseを返す場合は
                  this.get概算買付口数（乗換先）()の戻り値を設定する。 */
        if (Double.isNaN(this.getSwitchingEstimatedQty()) == true)
        {
            l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(null);
        }
        else
        {
            l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(
                (long) this.getSwitchingEstimatedQty());
        }

        //８）　@投信注文単位Paramsに以下の設定を行う。

        //投信注文単位Params.set受渡日(this.get受渡日( ))
        l_mutualFundOrderUnitParams.setDeliveryDate(
            this.getDeliveryDate());

        //投信注文単位Params.set初回注文の注文チャネル(this.get注文チャネル( ))
        l_mutualFundOrderUnitParams.setOrderChanel(
            this.getOrderChannel());

        //投信注文単位Params.set計算基準価額(this.get計算基準価額( ))
        if (Double.isNaN(this.getConstantValue()) == true)
        {
            l_mutualFundOrderUnitParams.setCalcConstantValue(null);
        }
        else
        {
            l_mutualFundOrderUnitParams.setCalcConstantValue(
                this.getConstantValue());
        }

        //投信注文単位Params.set基準価額適用日(this.get基準価額適用日( ))
        l_mutualFundOrderUnitParams.setConstantValueAppDate(
            this.getConstantValueAppDate());

        //投信注文単位Params.set概算受渡代金(this.get概算受渡代金( ))
        l_mutualFundOrderUnitParams.setEstimatedPrice(
            this.getEstimatedPrice());

        //投信注文単位Params.set概算売買口数(this.get概算売買口数( ))
        l_mutualFundOrderUnitParams.setEstimateDealingQty(
            (long) this.getEstimatedQty());

        //投信注文単位Params.set税区分（乗換先）(this.get税区分（乗換先）( ))
        l_mutualFundOrderUnitParams.setSwtTaxType(
            this.getSwitchingSubjectTaxDivision());

        // 投信注文単位Params.set受渡方法@(this.get受渡方法@( ))
        l_mutualFundOrderUnitParams.setPaymentMethod(this.getDeliveryDiv());

        //投信注文単位Params.set投信タイプ(this.get投信タイプ( ))

        //** The other mutual fund besides domestic and foreign mutual fund product. */
        if (MutualFundTypeEnum.OTHER.intValue() ==
            Integer.parseInt(this.getMutualFundType()))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.OTHER);
        }

        //** Domestic mutual fund product. */
        else if (MutualFundTypeEnum.DOMESTIC.intValue() ==
            Integer.parseInt(this.getMutualFundType()))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.DOMESTIC);
        }

        //** Foreign mutual fund product. */
        else if (MutualFundTypeEnum.FOREIGN.intValue() ==
            Integer.parseInt(this.getMutualFundType()))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        }
        //FOREIGN_MMF
        else if (MutualFundTypeEnum.FOREIGN_MMF.intValue() ==
            Integer.parseInt(this.getMutualFundType()))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        }

        //投信注文単位Params.set投信解約区分(this.get投信解約区分())
        l_mutualFundOrderUnitParams.setFundSellDiv(
            this.getMutualFundSellDiv());

        //投信注文単位Params.set約定日(this.get約定日( ))
        l_mutualFundOrderUnitParams.setExecDate(
            this.getExecutionTimestamp());

        // 投信注文単位Params.set約定状態(null)
        l_mutualFundOrderUnitParams.setExecStatus(null);

        //投信注文単位Params.set決済区分(this.get決済区分( ))
        l_mutualFundOrderUnitParams.setSettlementDiv(
            this.getSettlementType());

        //投信注文単位Params.set無手数料区分(this.get無手数料区分( ))
        l_mutualFundOrderUnitParams.setNoContractCommissionDiv(
            this.getNoCommissionDivision());

        //投信注文単位Params.set銘柄コード（乗換先）(this.get銘柄コード（乗換先）( ))
        l_mutualFundOrderUnitParams.setSwtProductCode(
            this.getSwitchingSubjectMutualProductCode());

        //投信注文単位Params.set請求区分(this.get請求区分( ))
        l_mutualFundOrderUnitParams.setRequestDiv(
            this.getRequestDivision());

        //投信注文単位Params.set注文経路区分(this.get注文経路区分( ))
        l_mutualFundOrderUnitParams.setOrderRootDiv(
            this.getOrderChannelDivision());

        // 投信注文単位Params.set注文エラー理由コード(null)
        l_mutualFundOrderUnitParams.setErrorReasonCode(null);

        //投信注文単位Params.set出金注文識別コード(this.get出金注文識別コード( ))
        l_mutualFundOrderUnitParams.setPaymentOrderReqNumber(
            this.getPaymentOrderReqNumber());

        //９）　@引数で与えられた投信注文単位Paramsを返す。
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderUnitParams;
    }

    /**
     * 注文チャネルの設定を行う。<BR>
     * @@param l_strOrderChannel - 注文チャネル<BR>
     * @@roseuid 40AD92050133
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        this.orderChannel = l_strOrderChannel;
    }

    /**
     * this.注文チャネルを返す。<BR>
     * @@return String
     * @@roseuid 40AD91EC00E5
     */
    public String getOrderChannel()
    {
        return orderChannel;
    }

    /**
     * 計算基準価額の設定を行う。<BR>
     * @@param l_dblConstantValue - 基準価額<BR>
     * @@roseuid 40AD92AE0114
     */
    public void setConstantValue(double l_dblConstantValue)
    {
        this.constantValue = l_dblConstantValue;
    }

    /**
     * this.計算基準価額を返す。<BR>
     * @@return double
     * @@roseuid 40AD92B5021D
     */
    public double getConstantValue()
    {
        return constantValue;
    }

    /**
     * 計算基準価額（乗換先）の設定を行う。<BR>
     * @@param l_dblSwitchingConstantValue - 基準価額（乗換先）<BR>
     * @@roseuid 40D7D3B20221
     */
    public void setSwitchingConstantValue(double l_dblSwitchingConstantValue)
    {
        this.switchingConstantValue = l_dblSwitchingConstantValue;
    }

    /**
     * this.計算基準価額（乗換先）を返す。<BR>
     * @@return double
     * @@roseuid 40D7D3B20230
     */
    public double getSwitchingConstantValue()
    {
        return switchingConstantValue;
    }

    /**
     * 概算受渡代金の設定を行う。<BR>
     * @@param l_dblEstimatedPrice - 概算受渡代金<BR>
     * @@roseuid 40AD92B702AA
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice)
    {
        this.estimatedPrice = l_dblEstimatedPrice;
    }

    /**
     * this.概算受渡代金を返す。<BR>
     * @@return double
     * @@roseuid 40AD92C30152
     */
    public double getEstimatedPrice()
    {
        return estimatedPrice;
    }

    /**
     * 概算売買口数の設定を行う。<BR>
     * @@param l_dblEstimatedQty - 概算売買口数<BR>
     * @@roseuid 40AD92C7027B
     */
    public void setEstimatedQty(double l_dblEstimatedQty)
    {
        this.estimatedQty = l_dblEstimatedQty;
    }

    /**
     * this概算売買口数を返す。<BR>
     * @@return double
     * @@roseuid 40AD92D303D3
     */
    public double getEstimatedQty()
    {
        return estimatedQty;
    }

    /**
     * 受渡方法@の設定を行う。<BR>
     * @@param l_strDeliveryDiv - 受渡方法@<BR>
     * @@roseuid 40AD92D60366
     */
    public void setDeliveryDiv(String l_strDeliveryDiv)
    {
        this.deliveryDiv = l_strDeliveryDiv;
    }

    /**
     * this.受渡方法@を返す。<BR>
     * @@return String
     * @@roseuid 40AD92DD026C
     */
    public String getDeliveryDiv()
    {
        return deliveryDiv;
    }

    /**
     * 投信タイプの設定を行う。<BR>
     * @@param l_strMutualFundType - 投信タイプ<BR>
     * @@roseuid 40AD92E3000A
     */
    public void setMutualFundType(String l_strMutualFundType)
    {
        this.mutualFundType = l_strMutualFundType;
    }

    /**
     * this.投信タイプを返す。<BR>
     * @@return String
     * @@roseuid 40AD92ED0308
     */
    public String getMutualFundType()
    {
        return mutualFundType;
    }

    /**
     * 約定日の設定を行う。<BR>
     * @@param l_tsExecutionTimestamp - 約定日<BR>
     * @@roseuid 40AD92F70104
     */
    public void setExecutionTimestamp(Timestamp l_tsExecutionTimestamp)
    {
        this.executionTimestamp = l_tsExecutionTimestamp;
    }

    /**
     * this.約定日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40AD9301023D
     */
    public Timestamp getExecutionTimestamp()
    {
        return executionTimestamp;
    }

    /**
     * 決済区分の設定を行う。<BR>
     * @@param l_strSettlementType - 決済区分<BR>
     * @@roseuid 40AD9312028B
     */
    public void setSettlementType(String l_strSettlementType)
    {
        this.settlementType = l_strSettlementType;
    }

    /**
     * this.決済区分を返す。<BR>
     * @@return String
     * @@roseuid 40AD93190029
     */
    public String getSettlementType()
    {
        return settlementType;
    }

    /**
     * 無手数料区分の設定を行う。<BR>
     * @@param l_strNoCommissionDivision - 無手数料区分<BR>
     * @@roseuid 40AD931D0366
     */
    public void setNoCommissionDivision(String l_strNoCommissionDivision)
    {
        this.noCommissionDivision = l_strNoCommissionDivision;
    }

    /**
     * this.無手数料区分を返す。<BR>
     * @@return String
     * @@roseuid 40AD9327000A
     */
    public String getNoCommissionDivision()
    {
        return noCommissionDivision;
    }

    /**
     * 銘柄コード（乗換先）の設定を行う。<BR>
     * @@param l_strSwitchingSubjectMutualProductCode - 銘柄コード（乗換先）<BR>
     * @@roseuid 40AD936502D9
     */
    public void setSwitchingSubjectMutualProductCode(
        String l_strSwitchingSubjectMutualProductCode)
    {
        this.switchingSubjectMutualProductCode =
            l_strSwitchingSubjectMutualProductCode;
    }

    /**
     * this.銘柄コード（乗換先）を返す。<BR>
     * @@return String
     * @@roseuid 40AD93750058
     */
    public String getSwitchingSubjectMutualProductCode()
    {
        return switchingSubjectMutualProductCode;
    }

    /**
     * 請求区分の設定を行う。<BR>
     * @@param l_strRequestDivision - 請求区分<BR>
     * @@roseuid 40AD937A0087
     */
    public void setRequestDivision(String l_strRequestDivision)
    {
        this.requestDivision = l_strRequestDivision;
    }

    /**
     * this.請求区分を返す。<BR>
     * @@return String
     * @@roseuid 40AD9380028B
     */
    public String getRequestDivision()
    {
        return requestDivision;
    }

    /**
     * 注文経路区分の設定を行う。<BR>
     * @@param l_strOrderChannelDivision - 注文経路区分<BR>
     * @@roseuid 40AD93830068
     */
    public void setOrderChannelDivision(String l_strOrderChannelDivision)
    {
        this.orderChannelDivision = l_strOrderChannelDivision;
    }

    /**
     * this.注文経路区分を返す。<BR>
     * @@return String
     * @@roseuid 40AD938B00F5
     */
    public String getOrderChannelDivision()
    {
        return orderChannelDivision;
    }

    /**
     * 受渡日の設定を行う。<BR>
     * @@param l_tsDeliveryDate - 受渡日<BR>
     * @@roseuid 40B338FB03A9
     */
    public void setDeliveryDate(Timestamp l_tsDeliveryDate)
    {
        this.deliveryDate = l_tsDeliveryDate;
    }

    /**
     * this.受渡日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40B339350232
     */
    public Timestamp getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * 投信解約区分の設定を行う。<BR>
     * @@param l_strMutualFundSellDiv - 投信解約区分<BR>
     * @@roseuid 40B4558D02FD
     */
    public void setMutualFundSellDiv(String l_strMutualFundSellDiv)
    {
        this.mutualFundSellDiv = l_strMutualFundSellDiv;
    }

    /**
     * this.投信解約区分を返す。<BR>
     * @@return String
     * @@roseuid 40B4558D030D
     */
    public String getMutualFundSellDiv()
    {
        return mutualFundSellDiv;
    }

    /**
     * 基準価額適用日の設定を行う。<BR>
     * @@param l_tsConstantValueAppDate - 基準価額適用日<BR>
     * @@roseuid 40CFE5A500DA
     */
    public void setConstantValueAppDate(Timestamp l_tsConstantValueAppDate)
    {
        this.constantValueAppDate = l_tsConstantValueAppDate;
    }

    /**
     * this.基準価額適用日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40CFE5A500DC
     */
    public Timestamp getConstantValueAppDate()
    {
        return constantValueAppDate;
    }

    /**
     * 概算買付口数（乗換先）の設定を行う。<BR>
     * @@param l_dblSwitchingEstimatedQty - 乗換先概算買付口数<BR>
     * @@roseuid 40D2BB550253
     */
    public void setSwitchingEstimatedQty(double l_dblSwitchingEstimatedQty)
    {
        this.switchingEstimatedQty = l_dblSwitchingEstimatedQty;
    }

    /**
     * this.概算買付口数（乗換先）を返す。<BR>
     * @@return double
     * @@roseuid 40D2BB550255
     */
    public double getSwitchingEstimatedQty()
    {
        return switchingEstimatedQty;
    }

    /**
     * 税区分（乗換先）の設定を行う。<BR>
     * @@param l_switchingSubjectTaxDivision - 税区分（乗換先）<BR>
     * @@roseuid 40D2BBB5007E
     */
    public void setSwitchingSubjectTaxDivision(
        TaxTypeEnum l_switchingSubjectTaxDivision)
    {
        this.switchingSubjectTaxDivision = l_switchingSubjectTaxDivision;
    }

    /**
     * this.税区分（乗換先）を返す。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@roseuid 40D2BBB50080
     */
    public TaxTypeEnum getSwitchingSubjectTaxDivision()
    {
        return switchingSubjectTaxDivision;
    }

    /**
     * 出金注文識別コードの設定を行う。<BR>
     * @@param l_strPaymentOrderReqNumber - 出金注文識別コード<BR>
     * @@roseuid 40D2BBB5007E
     */
    public void setPaymentOrderReqNumber(
        String l_strPaymentOrderReqNumber)
    {
        this.paymentOrderReqNumber = l_strPaymentOrderReqNumber;
    }

    /**
     * this.出金注文識別コードを返す。<BR>
     * @@return String
     * @@roseuid 40D2BBB50080
     */
    public String getPaymentOrderReqNumber()
    {
        return paymentOrderReqNumber;
    }

    /**
     * (set一括区分)<BR>
     * 一括区分の設定を行う。<BR>
     * @@param l_blnNorealDiv - (一括区分)<BR>
     */
    public void setNorealDiv(boolean l_blnNorealDiv)
    {
        this.norealDiv = l_blnNorealDiv;
    }

    /**
     * (get一括区分)<BR>
     * this.一括区分を返す。<BR>
     * @@return boolean
     */
    public boolean getNorealDiv()
    {
        return norealDiv;
    }

    /**
     * (set注文終了日 )<BR>
     * 注文終了日の設定を行う。<BR>
     * @@param l_datOrderEndDiv - (注文終了日)<BR>
     */
    public void setOrderEndDate(Date l_datOrderEndDiv)
    {
        this.orderEndDate= l_datOrderEndDiv;
    }

    /**
     * (get注文終了日)<BR>
     * this.注文終了日を返す。<BR>
     * @@return Date
     */
    public Date getOrderEndDate()
    {
        return orderEndDate;
    }
}
@
