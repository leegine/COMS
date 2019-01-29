head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualRecruitOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託募集注文入力サービス実装クラス(WEB3MutualRecruitOrderInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/29 黄建 (中訊) 新規作成
Revesion History : 2006/10/12 趙林鵬 (中訊) 仕様変更・モデル503
Revesion History : 2006/11/13 松本 (SRA) 障害番号:U02944
Revesion History : 2007/04/06 唐性峰 (中訊) 実装005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualApplyInputRequest;
import webbroker3.mf.message.WEB3MutualApplyInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderInputService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託募集注文入力サービス実装クラス
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualRecruitOrderInputServiceImpl
        extends WEB3MutualClientRequestService implements WEB3MutualRecruitOrderInputService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRecruitOrderInputServiceImpl.class);

    /**
     * 投資信託募集注文入力サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(投信)募集注文入力」参照。<BR>
     * <BR>
     *==========================================================<BR>
     * シーケンス図「(投信)買付注文入力」: <BR>
     *     1.3((getProduct(long) 拡張投信銘柄が取得出来ない場合、例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00377<BR>
     *==========================================================<BR>
     * <BR>
     *==========================================================<BR>
     * シーケンス図「(投信)買付注文入力」: <BR>
     *     1.12((isシステム取扱 取扱可能銘柄チェック
     *     isシステム取扱()の戻り値がfalseの場合 （取扱不可銘柄）例外をス。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00362<BR>
     *==========================================================<BR>
     * <BR>
     *==========================================================<BR>
     * シーケンス図「(投信)買付注文入力」: <BR>
     *     1.13((iis募集可能(Date) 取引可能銘柄チェック
     *     is募集可能()の戻り値がfalseの場合、例外をスロー。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00589<BR>
     *==========================================================<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40566483009E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //投信募集注文入力リクエスト
        WEB3MutualApplyInputRequest l_mutualApplyInputRequest = null;
        if (l_request instanceof WEB3MutualApplyInputRequest)
        {
            //リクエストデータの具象データ型が「投信募集注文入力リクエスト」の場合
            l_mutualApplyInputRequest = (WEB3MutualApplyInputRequest) l_request;
        }
        else
        {
            log.debug(
                "the parameter of method isn't WEB3MutualApplyInputRequest type");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1 validate( )
        l_mutualApplyInputRequest.validate();

        //1.2 get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.3 getProduct(long)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_product = null;
        try
        {
            l_product =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getProduct(
                    Long.parseLong(l_mutualApplyInputRequest.id));
        }
        //拡張投信銘柄が取得出来ない場合、例外をスローする
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MutualFundProductRow l_mfProductRow =
            (MutualFundProductRow) l_product.getDataSourceObject();

        //1.4 to銘柄(Row)
        WEB3MutualFundProduct l_mutualFundProduct =
            (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
            l_mfProductRow);

        //1.5.二重注文チェック
        //二重注文チェック
        //１．同一銘柄注文を取得
        //    以下の条件で注文単位テーブルを検索する。
        //    [検索条件]
        StringBuffer l_strWhere = new StringBuffer();
        //    口座ID = 引数.補助口座.getAccountId() and
        l_strWhere.append("account_id = ?");
        //    補助口座ID = 引数.補助口座.getSubAccountId() and
        l_strWhere.append(" and sub_account_id = ?");
        //    銘柄ID = 引数.拡張投信銘柄.getProductId() and
        l_strWhere.append(" and product_id = ?");
        //    注文種別 = OrderTypeEnum.投資信託募集注文 and
        l_strWhere.append(" and order_type = ?");
        //    ( 注文状態 = OrderStatusEnum.受付済(新規注文)
        //      or
        //      注文状態 = OrderStatusEnum.発注済(新規注文)
        //      or
        //      注文状態 = OrderStatusEnum.発注失敗（取消注文） )
        l_strWhere.append(" and order_status in (?, ?, ?) ");

        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        long l_lngProductId = l_mutualFundProduct.getProductId();
        long l_lngOrderType = OrderTypeEnum.MF_RECRUIT.intValue();
        long l_lngOrderStatusOne = OrderStatusEnum.ACCEPTED.intValue();
        long l_lngOrderStatusTwo = OrderStatusEnum.ORDERED.intValue();
        long l_lngOrderStatusThree = OrderStatusEnum.NOT_ORDERED.intValue();
        Object[] l_objQuerys =
            new Object[]{
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngProductId),
                new Long(l_lngOrderType),
                new Long(l_lngOrderStatusOne),
                new Long(l_lngOrderStatusTwo),
                new Long(l_lngOrderStatusThree)};
        List l_lisOrderUnitRow = null;
        try
        {
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();
            l_lisOrderUnitRow =
                l_queryProcessor.doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhere.toString(),
                    null,
                    l_objQuerys);

        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２．同一銘柄注文の存在チェック
        //    １．で取得したレコード件数が0以外の場合、例外をスローする。（二重注文エラー）
        if (l_lisOrderUnitRow != null && !l_lisOrderUnitRow.isEmpty())
        {
            log.debug("二重注文エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02648,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "二重注文エラー。");
        }

        //1.6 if　@リクエストデータ.電子鳩チェックフラグ == true
        WEB3GentradeProspectusResult l_validateBataResult = null;
        if(l_mutualApplyInputRequest.batoCheckFlag)
        {
            //1.6.1 validate目論見書閲覧(String, String)
            WEB3GentradeBatoClientService l_bataService =
                (WEB3GentradeBatoClientService)Services.getService(
                    WEB3GentradeBatoClientService.class);
            l_validateBataResult =
                l_bataService.validateProspectus(
                    l_mutualApplyInputRequest.typeCode,
                    l_mutualFundProduct.getProductCode());
        }

        //1.7 getCommonOrderValidator( )
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.8 validate注文受付可能( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.9 reset銘柄コード(String)
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_mutualFundProduct.getProductCode());

        //1.10 setTimestamp( )
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        //1.11一括区分取得
        //    １）部店用プリファ@レンスから「投信募集注文一括送信区分」を取得する。
        //    [部店用プリファ@レンスの取得条件]
        //       部店ID ： 取得した補助口座.getMainAccount().getBranchId()
        //       プリファ@レンス名 ： mf.recruit.mq.send.div
        //       プリファ@レンス名の連番 ： 1
        //  ※レコード無しは「一括送信する」
        //
        //２）以下の条件と一致する場合は true を、それ以外の場合は false を一括区分へセットする。
        //    部店用プリファ@レンス.投信募集注文一括送信区分 == 「一括送信する」
        boolean l_blnMfRecruitMqSendDiv = true;
        BranchPreferencesRow l_row = null;
        try
        {
            l_row = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_subAccount.getMainAccount().getBranch().getBranchId(),
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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

        if (l_row == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_row.getValue()))
        {
            l_blnMfRecruitMqSendDiv = true;
        }
        else
        {
            l_blnMfRecruitMqSendDiv = false;
        }

        // 1.12 get投信発注日(OrderTypeEnum, boolean)
        //注文種別：　@OrderTypeEnum.投資信託募集注文
        //一括区分：　@取得した一括区分
        Date l_datOrderBizDate =
            WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(
                OrderTypeEnum.MF_RECRUIT,
                l_blnMfRecruitMqSendDiv);

        log.debug("l_datOrderBizDate = " + l_datOrderBizDate);

        //1.13 validate取引可能顧客(顧客, Timestamp)
        WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        OrderValidationResult l_validationResult =
                l_orderValidator.validateAccountForTrading(
                        l_genMainAccount,
                        new Timestamp(l_datOrderBizDate.getTime()));

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.14 isシステム取扱( )
        //取扱可能銘柄チェック  isシステム取扱()の戻り値がfalseの場合 （取扱不可銘柄）例外をス
        if (!l_mutualFundProduct.isSystemHandling())
        {
            log.debug(" __取扱不可銘柄エラー__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "__取扱不可銘柄エラー__");
        }

        //1.15 is募集可能(Date)
        //取引可能銘柄チェック is募集可能()の戻り値がfalseの場合、例外をスロー
        boolean l_blnRecruitPossible =
            l_mutualFundProduct.isRecruitPossible(l_datOrderBizDate);
        if (!l_blnRecruitPossible)
        {
            log.debug("is募集可能(Date)が false を返す場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "募集中止銘柄です。");
        }

        //1.16 validate緊急停止(拡張投信銘柄, String)
        //拡張投信銘柄： 取得した拡張投信銘柄
        //処理区分： ”5：募集”
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        try
        {
            l_validationsCheck.validateEmergencyStop(
                l_mutualFundProduct,
                WEB3ProcessDivDef.RECRUIT);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("緊急停止エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "緊急停止エラー");
        }
        //1.17 validate注文受付可能( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // is再投資銘柄()
        if (l_mutualFundProduct.isPlowbackProduct())
        {
            // is累投口座開設()
            if (!l_genMainAccount.isRuitoAccountOpen())
            {
                    log.error(" __累投口座未開設エラー__");
                    throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            " __累投口座未開設エラー__");
            }
        }
        //1.20 get投資信託買付可能額(補助口座, Date, OrderTypeEnum)
        //［引数］
        //補助口座： 取得した補助口座オブジェクト
        //受渡日：　@取得した発注日の翌営業日
        //注文種別：　@203：投資信託募集注文

        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        //受渡日：　@取得した発注日の翌営業日
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        Date l_datDeliveryDate = l_gentradeBizDate.roll(1);

        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)l_subAccount;

        double l_dblBuyPossibleAmount =
            l_tpTradingPowerService.getMutualFundBuyTradingPower(
                l_gentradeSubAccount,
                l_datDeliveryDate,
                OrderTypeEnum.MF_RECRUIT);

        //1.21 get募集終了日( )
        Date l_datRecruitEndDate = l_mutualFundProduct.getRecruitEndDate();

        //1.22 get募集終了日（SONAR）( )
        Date l_datApplyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();

        //1.23 get顧客()
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        //－拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する
        try
        {
        l_genMainAccount =
            (WEB3GentradeMainAccount) l_web3GentradeAccountManager.getMainAccount(
                l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }


        //1.24 is特定口座開設(Date, 補助口座)
        boolean l_blnIsSpecialAccountEstablished =
            l_genMainAccount.isSpecialAccountEstablished(
                l_datRecruitEndDate,
                l_subAccount);

        //1.25 if is特定口座開設() == true
        //-拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する。
        //［get顧客に渡すパラメタ］
        //口座ID： 取得した補助口座.getAccountId()の戻り値
        //-取得した顧客オブジェクト.is特定口座開設()をコールする。
        //［is特定口座開設に渡すパラメタ］
        //補助口座： 取得した補助口座オブジェクト
        //受渡日： 取得した募集終了日
        String[] l_strArrAccountDivDefs = null;
        boolean l_blnisStockType = l_mutualFundProduct.isStockType();
        if(l_blnIsSpecialAccountEstablished)
        {
            //1.25.1 is株式型( )
            if(l_blnisStockType)
            {
                //-is特定口座開設()が true かつ is株式型()がtrueを返す場合は、
                //”0：一般”と”1：特定”を設定する。
                l_strArrAccountDivDefs = new String[2];
                l_strArrAccountDivDefs[0] = WEB3AccountDivDef.NORMAL;
                l_strArrAccountDivDefs[1] = WEB3AccountDivDef.SPECIAL;
            }
            else
            {
                //-is特定口座開設()が true かつ is株式型()が falseを返す場合、あるいは
                l_strArrAccountDivDefs = new String[1];
                l_strArrAccountDivDefs[0] = WEB3AccountDivDef.NORMAL;
            }
        }
        else
        {
            //is特定口座開設()が false を返す場合は、”0：一般”を設定する。
            l_strArrAccountDivDefs = new String[1];
            l_strArrAccountDivDefs[0] = WEB3AccountDivDef.NORMAL;
        }

        //1.26 get通貨コード( )
        String l_strCurrencyCode = l_mutualFundProduct.getCurrencyCode();

        //1.27 get募集価額( )
        double l_dblRecruitConstantValue =
            l_mutualFundProduct.getRecruitConstantValue();

        //1.28 get指定方法@（募集）
        String l_strRecruitSpecityDiv = l_mutualFundProduct.getRecruitSpecityDiv();

        //1.29 get単位口数（募集）
        String l_strRecruitUnitQty = l_mutualFundProduct.getRecruitUnitQty();

        //1.30 get最低口数（募集）
        String l_strRecruitMinQty = l_mutualFundProduct.getRecruitMinQty();

        //1.31 get単位金額（募集）
        String l_strRecruitUnitAmt = l_mutualFundProduct.getRecruitUnitAmt();

        //1.32 get最低金額（募集）
        String l_strRecruitMinAmt = l_mutualFundProduct.getRecruitMinAmt();

        //1.33 get決済（募集）
        String l_strRecruitSettlementDiv =
            l_mutualFundProduct.getRecruitSettlementDiv();

        //1.34 createResponse( )
        WEB3MutualApplyInputResponse l_response =
            (WEB3MutualApplyInputResponse)l_mutualApplyInputRequest.createResponse();

        //1.35 プロパティセット
        //[投信募集注文入力レスポンスに設定する値]

        //買付可能金額： 取得した買付可能額
        l_response.tradingPower =
            WEB3StringTypeUtility.formatNumber(l_dblBuyPossibleAmount);

        //銘柄コード： 取得した拡張投信銘柄.getProductCode()の戻り値
        l_response.mutualProductCode = l_mutualFundProduct.getProductCode();

        //銘柄名： 取得した拡張投信銘柄.get銘柄名()の戻り値
        l_response.mutualProductName = l_mutualFundProduct.getMutualProductName();

        //募集価額通貨コード： 取得した拡張投信銘柄.get通貨コード()の戻り値
        l_response.constantValueCurrencyCode = l_strCurrencyCode;

        //募集価額： 取得した拡張投信銘柄.get募集価額()の戻り値
        l_response.applyConstantValue =
            WEB3StringTypeUtility.formatNumber(l_dblRecruitConstantValue);

        //口座区分一覧：
        l_response.taxTypeList = l_strArrAccountDivDefs;

        //指定方法@一覧：
        //(*) 取得した拡張投信銘柄.get指定方法@（募集）()の戻り値が
        //”0：選択指定” の場合は”3：金額”と”4：口数”を設定する。
        if (WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(l_strRecruitSpecityDiv))
        {
            l_response.specifyDivList =
                new String[] {
                    WEB3SellDivDef.COUNT_DESIGNATE,
                    WEB3SellDivDef.MONEY_DESIGNATE };
        }

        //(*) 取得した拡張投信銘柄.get指定方法@（募集）()の戻り値が
        //”3：金額指定” の場合は”3：金額”を設定する。
        else if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strRecruitSpecityDiv))
        {
            l_response.specifyDivList =
                new String[] { WEB3SellDivDef.MONEY_DESIGNATE };
        }

        //(*) 取得した拡張投信銘柄.get指定方法@（募集）()の戻り値が
        //”4：口数指定” の場合は”4：口数”を設定する。
        else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strRecruitSpecityDiv))
        {
            l_response.specifyDivList =
                new String[] { WEB3SellDivDef.COUNT_DESIGNATE };
        }

        //募集時単位口数： 取得した拡張投信銘柄.get単位口数（募集）()の 戻り値を設定する。
        l_response.applyUnitQty = l_strRecruitUnitQty;

        //募集時最低口数： 取得した拡張投信銘柄.get最低口数（募集）()の 戻り値を設定する。
        l_response.applyMinQty = l_strRecruitMinQty;

        //募集時単位金額： 取得した拡張投信銘柄.get単位金額（募集）()の 戻り値を設定する。
        l_response.applyUnitAmt = l_strRecruitUnitAmt;

        //募集時最低金額： 取得した拡張投信銘柄.get最低金額（募集）()の 戻り値を設定する。
        l_response.applyMinAmt = l_strRecruitMinAmt;

        //決済方法@一覧：
        //(*) 取得した拡張投信銘柄.get決済（募集）()の戻り値が”0：選択指定” の場合は
        //”1：円貨”と”2：外貨”を設定する。
        if (WEB3BuySellSettlementDivDef.SELECT_DESIGNATE.equals(l_strRecruitSettlementDiv))
        {
            l_response.settleDivList =
                new String[] {
                    WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY,
                    WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY };
        }

        //(*) 取得した拡張投信銘柄.get決済（募集）()の戻り値が”1：円貨” の場合は
        //”1：円貨”を設定する。
        else if (WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(l_strRecruitSettlementDiv))
        {
            l_response.settleDivList =
                new String[] { WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY };
        }

        //(*) 取得した拡張投信銘柄.get決済（募集）()の戻り値が”2：外貨” の場合は
        //”2：外貨”を設定する。
        else if (WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY.equals(l_strRecruitSettlementDiv))
        {
            l_response.settleDivList =
                new String[] { WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY };
        }

        //発注日： 取得した発注日
        l_response.orderBizDate = l_datOrderBizDate;

        //約定日： 取得した発注日
        l_response.executionTimestamp = l_datOrderBizDate;

        //受渡日：
        //取得した一括区分==trueなら取得した募集終了日(SONAR)、
        //取得した一括区分==falseなら取得した募集終了日
        if (l_blnMfRecruitMqSendDiv)
        {
            l_response.deliveryDate = l_datApplyAbleEndDateSonar;
        }
        else
        {
            l_response.deliveryDate = l_datRecruitEndDate;
        }

        //目論見書閲覧チェック結果：
        //リクエスト.電子鳩チェックフラグ==falseの場合は、nullセット
        //リクエスト.電子鳩チェックフラグ==trueの場合、validate目論見書閲覧()の結果オブジェクトをセットする。
        l_response.prospectusResult = l_validateBataResult;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
