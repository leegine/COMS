head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 株式・信用注文約定照会サービImpl(WEB3EquityMarginExecuteReferenceServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/01/08  金傑　@(中訊) 新規作成
 Revesion History : 2007/01/11  関博(中訊)   メソッドを追加
 Revesion History : 2007/01/11  吉麗ナ(中訊) メソッドを追加
 Revesion History : 2007/01/11  崔遠鵬(中訊) メソッドを追加
 Revesion History : 2007/03/01  関博(中訊) 仕様変更モデル1128
 Revesion History : 2007/03/07  玉岡(SRA) 仕様変更モデル1132
 Revesion History : 2007/04/25  武波(中訊) 仕様変更モデル1137
 Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更・モデル1164
 Revesion History : 2007/10/16 金傑(中訊) 仕様変更モデル1201、仕様変更モデル1202
 Revesion History : 2007/12/18 趙林鵬 (中訊) モデル No.1233,1234,1235,1246,1252,1268
 Revesion History : 2007/12/18 趙林鵬 (中訊) モデル No.1284,1291
 Revesion History : 2008/04/16  崔遠鵬 (中訊) モデル No.1314
 Revesion History : 2008/04/21  金傑 (中訊) モデル No.1318
 */

package webbroker3.equity.service.delegate.stdimpls;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityProductDivDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.message.WEB3EquityMarginExecuteGroup;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceResponse;
import webbroker3.equity.message.WEB3EquityMarginExecuteUnit;
import webbroker3.equity.message.WEB3EquityMarginOrderIdUnit;
import webbroker3.equity.message.WEB3EquityMarginSortKey;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3EquityMarginExecuteReferenceService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (株式・信用注文約定照会サービスImpl)<BR>
 * 株式・信用注文約定照会サービス実装クラス<BR>
 * @@author 金傑(中訊)
 * @@author 関博(中訊)
 * @@author 吉麗ナ(中訊)
 * @@author 崔遠鵬(中訊)
 * @@version 1.0 <BR>
 */
public class WEB3EquityMarginExecuteReferenceServiceImpl extends WEB3EquityClientRequestService implements
    WEB3EquityMarginExecuteReferenceService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceServiceImpl.class);

    /**
     * @@roseuid 45A3606E032C
     */
    public WEB3EquityMarginExecuteReferenceServiceImpl()
    {

    }

    /**
     * 株式・信用注文約定照会サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（株式・信用注文約定照会)01.注文約定データ取得」 参照。<BR>
     * <BR>
     * ==========================================================<BR>
     *  シーケンス図(「(株式・信用取引共通サービスモデル) /<BR>
     *  株式・信用注文約定照会」(（株式・信用注文約定照会）01.注文約定データ取得)<BR>
     * 　@　@is信用取扱実施(弁済区分 : String)<BR>
     * 　@　@戻り値 == falseの場合、<BR>
     * 　@　@『信用未実施エラー』の例外をスローする。<BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_00746<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     *  シーケンス図(「(株式・信用取引共通サービスモデル) /<BR>
     *  株式・信用注文約定照会」(（株式・信用注文約定照会）01.注文約定データ取得)<BR>
     * 　@　@is信用口座開設(弁済区分 : String)<BR>
     * 　@　@戻り値 == falseの場合、<BR>
     * 　@　@『信用口座未開設エラー』の例外をスローする。<BR>
     * 　@　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag   : BUSINESS_ERROR_00747<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     *  シーケンス図(「(株式・信用取引共通サービスモデル) /<BR>
     *  株式・信用注文約定照会」(（株式・信用注文約定照会）01.注文約定データ取得)<BR>
     * 　@1.9.2 getProduct(証券会社 : Institution, 銘柄コード : String)<BR>
     * 　@　@存在チェックの実施取得できなかった場合は、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_00301<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     *  シーケンス図(「(株式・信用取引共通サービスモデル) /<BR>
     *  株式・信用注文約定照会」(（株式・信用注文約定照会）01.注文約定データ取得)<BR>
     * 　@1.9.3.3 get取引銘柄(株式銘柄 : 株式銘柄, 市場 : 市場)<BR>
     * 　@　@存在チェックの実施取得できなかった場合は、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_00638<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエスト<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 455D1CD60076
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("パラメータがnullである");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //validate( )
        WEB3EquityMarginExecuteReferenceRequest l_equityMarginExecuteReferenceRequest =
            (WEB3EquityMarginExecuteReferenceRequest) l_request;
        l_equityMarginExecuteReferenceRequest.validate();

        //get口座( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

        //getBranch( )
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();

        //is信用口座開設(弁済区分 : String)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //(*)リクエスト.商品区分 == ”信用取引”の場合、実施
        if (WEB3EquityProductDivDef.MARGIN.equals(l_equityMarginExecuteReferenceRequest.productDiv))
        {
            //is信用取扱実施(返済区分：String)
            boolean l_isMarginTradeEnforcement =
                l_branch.isMarginTradeEnforcement(WEB3GentradeRepaymentDivDef.DEFAULT);
            if (!l_isMarginTradeEnforcement)
            {
                log.error("当該部店では、取扱が不可能です");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00746,
                    this.getClass().getName()
                    + STR_METHOD_NAME, "当該部店では、取扱が不可能です");
            }
            if (!l_blnIsMarginAccountEstablished)
            {
                log.error("信用口座未開設エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                    this.getClass().getName()
                    + STR_METHOD_NAME, "信用口座未開設エラー");
            }
        }

        //getDataSourceObject( )
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();

        //validate注文受付可能(String, String, boolean, boolean)
        boolean l_blnIsExecute = false;
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getOffFloorDiv()))
        {
            l_blnIsExecute = true;
        }
        if (WEB3EnforcementDef.NOT_ENFORCEMENT.equals(l_branchRow.getOffFloorDiv()))
        {
            l_blnIsExecute = false;
        }
        OrderStatus l_orderStatus = null;
        l_orderStatus = this.validateOrderAccept(l_equityMarginExecuteReferenceRequest.referenceType,
            l_equityMarginExecuteReferenceRequest.productDiv, l_blnIsMarginAccountEstablished, l_blnIsExecute);

        //reset市場コード(市場コード : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_equityMarginExecuteReferenceRequest.marketCode);

        //リクエスト.照会区分＝"訂正取消一覧"の場合のみ実行
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(
            l_equityMarginExecuteReferenceRequest.referenceType))
        {
            //validate取引可能顧客(顧客, String)
            //[引数]
            // 顧客： 取得した顧客オブジェクト
            //市場コード：リクエスト.市場コード
            OrderValidationResult l_orderValidationResult =
                this.validateAccountForTrading(
                    l_mainAccount,
                    l_equityMarginExecuteReferenceRequest.marketCode);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("validate取引可能顧客というメソッドのチェックが失敗");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            }
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //リクエスト.銘柄コード != null の場合
        if (WEB3StringTypeUtility.isNotEmpty(l_equityMarginExecuteReferenceRequest.productCode))
        {
            //getInstitution( )
            WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_mainAccount.getInstitution();

            //getProduct(証券会社 : Institution, 銘柄コード : String)
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            EqTypeProduct l_eqTypeProduct = null;
            try
            {
                l_eqTypeProduct = l_productManager.getProduct(l_institution,
                    l_equityMarginExecuteReferenceRequest.productCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "指定した銘柄コードに合致している銘柄が存在しません。",
                    l_ex);
            }

            //リクエスト.市場コード != null の場合
            if (WEB3StringTypeUtility.isNotEmpty(l_equityMarginExecuteReferenceRequest.marketCode))
            {
                //reset受付時間区分(受付時間区分 : String)
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

                //get市場(証券会社コード : , 市場コード : )
                WEB3GentradeFinObjectManager l_gentradeFinObjectManager = (WEB3GentradeFinObjectManager)
                    l_finApp.getFinObjectManager();
                Market l_market = null;
                try
                {
                    l_market = l_gentradeFinObjectManager.getMarket(l_institution.getInstitutionCode(),
                        l_equityMarginExecuteReferenceRequest.marketCode);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("市場オブジェクトが見つかられない", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                //get取引銘柄(株式銘柄, 市場)
                try
                {
                    l_productManager.getTradedProduct(l_eqTypeProduct, l_market);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("取引銘柄が取得できない", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00638,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "指定銘柄は指定市場での取扱不可。");
                }
            }
        }

        //createResponse()
        WEB3EquityMarginExecuteReferenceResponse l_equityMarginExecuteReferenceResponse =
            (WEB3EquityMarginExecuteReferenceResponse)l_equityMarginExecuteReferenceRequest.
                createResponse();

        // create注文約定明細(株式・信用注文約定照会リクエスト, 株式・信用注文約定照会レスポンス,
        // 注文受付状態)(株式・信用注文約定照会サービスImpl::create注文約定明細)
        if (l_orderStatus == null)
        {
            log.debug("注文受付状態オブジェクトがnullである");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        this.createExecuteGroup(l_equityMarginExecuteReferenceRequest, l_equityMarginExecuteReferenceResponse,
            l_orderStatus);

        //reset受付時間区分(受付時間区分 : String)
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

        String[] l_strPossibleMarket;
        String l_strMargineTradeDiv = null;
        String[] l_strAscPossibleMarkets = null;
        if (WEB3EquityProductDivDef.MARGIN.equals(l_equityMarginExecuteReferenceRequest.productDiv))
        {
            //リクエスト.商品区分 == ”信用取引” の場合
            String l_strRepaymentDiv = null;
            if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(
                l_mainAccount.getMainAccountRow().getMarginSysAccOpenDiv()) &&
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(
                l_mainAccount.getMainAccountRow().getMarginGenAccOpenDiv()))
            {
                //弁済区分：
                //顧客.制度信用取引口座開設区分 == ”口座開設” and
                //顧客.一般信用取引口座開設区分 == ”DEFAULT（口座なし）” の場合、”制度信用”
                l_strRepaymentDiv = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;

                //信用取引区分：
                //顧客.制度信用取引口座開設区分 == ”口座開設” and
                //顧客.一般信用取引口座開設区分 == ”DEFAULT（口座なし）” の場合、”制度信用”
                l_strMargineTradeDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
            }

            if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN.equals(l_mainAccount.getMainAccountRow().
                getMarginSysAccOpenDiv()) && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(
                l_mainAccount.getMainAccountRow().getMarginGenAccOpenDiv()))
            {
                //弁済区分：
                //顧客.制度信用取引口座開設区分 == ”DEFAULT（口座なし）” and
                //顧客.一般信用取引口座開設区分 == ”口座開設” の場合、”一般信用”
                l_strRepaymentDiv = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;

                //信用取引区分：
                //顧客.制度信用取引口座開設区分 == ”DEFAULT（口座なし）” and
                //顧客.一般信用取引口座開設区分 == ”口座開設” の場合、”一般信用”
                l_strMargineTradeDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
            }

            if (WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccount.getMainAccountRow().
                getMarginSysAccOpenDiv()) && WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(
                l_mainAccount.getMainAccountRow().getMarginGenAccOpenDiv()))
            {
                //弁済区分：
                //顧客.制度信用取引口座開設区分 == ”口座開設” and
                //顧客.一般信用取引口座開設区分 == ”口座開設” の場合、”DEFAULT”
                l_strRepaymentDiv = WEB3GentradeRepaymentDivDef.DEFAULT;

                //信用取引区分：
                //顧客.制度信用取引口座開設区分 == ”口座開設” and
                //顧客.一般信用取引口座開設区分 == ”口座開設” の場合、”制度/一般信用（両方）”
                l_strMargineTradeDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
            }
            //get取扱可能市場(部店 : 部店, 弁済区分 : String, 弁済期限値 : double)
            l_strPossibleMarket = WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(l_branch,
                l_strRepaymentDiv, 0.0d);
        }
        else
        {
            //リクエスト.商品区分 != ”信用取引” の場合
            //get取扱可能市場(部店 : 部店, 銘柄タイプ : ProductTypeEnum)
            //取扱可能市場取得メソッド①@
            l_strPossibleMarket =
                WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(l_branch, ProductTypeEnum.EQUITY);

            //get取扱可能市場(部店 : 部店, 銘柄タイプ : ProductTypeEnum)
            //取扱可能市場取得メソッド②
            String[] l_strPTSPossibleMarkets =
                WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(l_branch, ProductTypeEnum.EQUITY);

            //取扱可能市場取得メソッド①@と取扱可能市場取得メソッド②の結果を
            //市場コード昇順でマージする。
            l_strAscPossibleMarkets = this.mergeAndSort(l_strPossibleMarket, l_strPTSPossibleMarkets);

            //信用取引区分：”DEFAULT”
            l_strMargineTradeDiv = WEB3MarginTradingDivDef.DEFAULT;
        }

        //get発注日一覧(String[])
        Date[] l_datOrderBizDateLists = this.getOrderBizDateList(l_strPossibleMarket);

        //get市場閉局警告市場(部店 : 部店, 銘柄タイプ : ProductTypeEnum, 信用取引区分 : String)
        String[] l_strCloseMarkets = this.getTradeCloseMarket(
            l_branch, ProductTypeEnum.EQUITY, l_strMargineTradeDiv);

        //プロパティをセットする。
        //市場コード一覧： get取扱可能市場()の戻り値
        //商品区分!="信用取引"の場合
        //取扱可能市場取得メソッド①@と取扱可能市場取得メソッド②の結果を
        //市場コード昇順でマージしたものをセットする。
        if (WEB3EquityProductDivDef.MARGIN.equals(l_equityMarginExecuteReferenceRequest.productDiv))
        {
            l_equityMarginExecuteReferenceResponse.marketList = l_strPossibleMarket;
        }
        else
        {
            l_equityMarginExecuteReferenceResponse.marketList = l_strAscPossibleMarkets;
        }

        //発注日一覧： get発注日一覧()の戻り値
        l_equityMarginExecuteReferenceResponse.orderBizDateList = l_datOrderBizDateLists;

        //取引終了警告市場コード一覧： get市場閉局警告市場()の戻り値
        l_equityMarginExecuteReferenceResponse.messageSuspension = l_strCloseMarkets;

        //(*)レスポンス.注文情報一覧 == null or
        //   レスポンス.注文情報一覧の要素数 == 0 の場合、以下のプロパティもセットする。
        //
        //   表示ページ番号	： "0"
        //   総ページ数		： "0"
        //   総レコード数	： "0"
        if (l_equityMarginExecuteReferenceResponse.executeGroups == null ||
        	l_equityMarginExecuteReferenceResponse.executeGroups.length == 0)
        {
        	l_equityMarginExecuteReferenceResponse.pageIndex = "0";
        	l_equityMarginExecuteReferenceResponse.totalPages = "0";
        	l_equityMarginExecuteReferenceResponse.totalRecords = "0";
        }

        log.exiting(STR_METHOD_NAME);
        return l_equityMarginExecuteReferenceResponse;
    }

    /**
     * (validate注文受付可能)<BR>
     * 受付時間チェック、システム売買停止チェックを行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（株式・信用注文約定照会）02.注文受付可能チェック」 参照。<BR>
     * <BR>
     * ==========================================================<BR>
     *  シーケンス図(「(株式・信用取引共通サービスモデル) /<BR>
     *  株式・信用注文約定照会」(（株式・信用注文約定照会）02.注文受付可能チェック)<BR>
     * 　@1.2.3 コールしたすべての validate注文受付ステイタス() の結果が”受付不可”の場合<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     *           ※「緊急停止中」の場合、「緊急停止中」の例外をスロー<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_00011<BR>
     *           ※「バッチ処理中」の場合、「バッチ処理中」の例外をスロー<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_00012<BR>
     * ==========================================================<BR>
     * <BR>
     * ==========================================================<BR>
     *  シーケンス図(「(株式・信用取引共通サービスモデル) /<BR>
     *  株式・信用注文約定照会」(（株式・信用注文約定照会）02.注文受付可能チェック)<BR>
     * 　@1.3.3 コールしたすべての validate注文受付ステイタス() の結果が”受付不可”の場合<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     *           ※「緊急停止中」の場合、「緊急停止中」の例外をスロー<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_00011<BR>
     *           ※「バッチ処理中」の場合、「バッチ処理中」の例外をスロー<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_00012<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_strReferenceType - (照会区分)<BR>
     * 照会区分<BR>
     * <BR>
     * @@param l_strProductType - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * @@param l_blnIsMargin - (is信用客)<BR>
     * 信用客かどうかのフラグ<BR>
     * <BR>
     * true： 信用客<BR>
     * false： 現物客<BR>
     * <BR>
     * @@param l_blnIsOffFloorExecute - (is立会外分売実施)<BR>
     * 立会外分売を実施しているかどうかのフラグ<BR>
     * <BR>
     * true： 実施会社<BR>
     * false： 未実施会社<BR>
     * <BR>
     * @@return
     * webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServ
     * iceImpl.OrderStatus
     * @@roseuid 455D5FE50180
     */
    protected OrderStatus validateOrderAccept(
            String l_strReferenceType,
            String l_strProductType,
            boolean l_blnIsMargin,
            boolean l_blnIsOffFloorExecute) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateOrderAccept()";
        log.entering(STR_METHOD_NAME);

        OrderStatus l_orderStatus = new OrderStatus();

        // (*1) 引数.照会区分 == ”注文約定照会” の場合
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(
                l_strReferenceType))
    {
            WEB3BaseException l_web3BaseException = null;
            
            // (*1-1) 引数.商品区分 != ”信用取引” の場合
            if (!WEB3EquityProductDivDef.MARGIN.equals(l_strProductType))
            {
                try
                {
                    //validate注文受付ステイタス()
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestEquityFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("照会区分 == ”注文約定照会” And 商品区分 != ”信用取引”の場合、" +
                            "validate注文受付ステイタスが”受付不可”である", l_bex);
                    l_orderStatus.interestEquityFlag = false;
                }


                // (*1-2) 引数.is立会外分売実施 == true の場合
                if (l_blnIsOffFloorExecute)
                {
                    try
                    {
                        // reset注文受付商品(注文受付商品:String)
                        WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                                WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
                        //validate注文受付ステイタス( )
                        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                        l_orderStatus.offFloor = true;
                    }
                    catch (WEB3BaseException l_bex)
                    {
                        l_web3BaseException = l_bex;
                        log.debug("照会区分 == ”注文約定照会” And is立会外分売実施 == trueの場合、" +
                                "validate注文受付ステイタスが”受付不可”である", l_bex);
                        l_orderStatus.offFloor = false;
                    }
                }
            }

            // (*1-3) 引数.商品区分 != ”現物株式” and 引数.is信用客 == true の場合
            if (!WEB3EquityProductDivDef.EQUITY.equals(l_strProductType) &&
                (l_blnIsMargin))
            {
                try
                {

                    // reset注文受付商品(注文受付商品 : String)
                    WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                            WEB3OrderAccProductDef.MARGIN);
                    //validate注文受付ステイタス()
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestMarginFlag = true;
                    l_orderStatus.swapFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("引数.商品区分 != ”現物株式” and 引数.is信用客 == true の場合、" +
                            "validate注文受付ステイタスが”受付不可”である", l_bex);
                    l_orderStatus.interestMarginFlag = false;
                    l_orderStatus.swapFlag = false;
                }
            }
            
            //コールしたすべての validate注文受付ステイタス() の結果が”受付不可”の場合、
            //（コールしたすべての validate注文受付ステイタス() が例外をスローした場合、）
            //例外をスローする。
            //※「緊急停止中」の場合、「緊急停止中」の例外をスロー
            //※「バッチ処理中」の場合、「バッチ処理中」の例外をスロー
            if (l_orderStatus.interestEquityFlag == false &&
                l_orderStatus.offFloor == false &&
                l_orderStatus.interestMarginFlag == false &&
                l_orderStatus.swapFlag == false )
            {
              // 注文受付ステイタス＝バッチ中
              if (l_web3BaseException.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
              {
                  throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
              }
              // 注文受付ステイタス＝緊急停止中
              else if (l_web3BaseException.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
              {
                throw new WEB3BusinessLayerException(
                      WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                      WEB3GentradeTradingTimeManagement.class.getName()
                          + "." + STR_METHOD_NAME);
              }
              else
              {
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_02706,
                     this.getClass().getName()+ STR_METHOD_NAME,
                     "照会不可。");
              }
            }
        }

        // (*2) 引数.照会区分 == ”訂正取消一覧” の場合
        if (WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(
                l_strReferenceType))
        {
            WEB3BaseException l_web3BaseException = null;

            // (*2-1) 引数.商品区分 != ”信用取引” の場合
            if (!WEB3EquityProductDivDef.MARGIN.equals(l_strProductType))
            {
                try
                {
                    //validate注文受付ステイタス()
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestEquityFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("照会区分 == ”訂正取消一覧” and 商品区分 != ”信用取引” の場合、" +
                            "validate注文受付ステイタスが”受付不可”である", l_bex);
                    l_orderStatus.interestEquityFlag = false;
                }
                // reset注文受付トランザクション(注文受付トランザクション : String)
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3OrderAccTransactionDef.CANCEL);
                if (!l_orderStatus.interestEquityFlag)
                {
                    try
                    {
                        //validate注文受付ステイタス()
                        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                        l_orderStatus.interestEquityFlag = true;

                    }
                    catch (WEB3BaseException l_bex)
                    {
                        l_web3BaseException = l_bex;
                        log.debug("照会区分 == ”訂正取消一覧” and 商品区分 != ”信用取引” の場合、" +
                                "validate注文受付ステイタスが”受付不可”である", l_bex);
                        l_orderStatus.interestEquityFlag = false;
                    }                    
                }

                // (*2-2) 引数.is立会外分売実施 == true の場合
                if (l_blnIsOffFloorExecute)
                {
                    // reset注文受付商品(注文受付商品 : String)
                    WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                        WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);

                    try
                    {
                        //validate注文受付ステイタス( )
                        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                        l_orderStatus.offFloor = true;
                    }
                    catch (WEB3BaseException l_bex)
                    {
                        l_web3BaseException = l_bex;
                        log.debug("照会区分 == ”訂正取消一覧” and 引数.is立会外分売実施 == true の場合、" +
                                "validate注文受付ステイタスが”受付不可”である", l_bex);
                        l_orderStatus.offFloor = false;
                    }
                }
            }
            
            //  (*2-3) 引数.商品区分 != ”現物株式” and 引数.is信用客 == true の場合
            if (!WEB3EquityProductDivDef.EQUITY.equals(l_strProductType) &&
                    (l_blnIsMargin))
            {
                // reset注文受付商品(注文受付商品 : String)
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                        WEB3OrderAccProductDef.MARGIN);

                // reset注文受付トランザクション(注文受付トランザクション : String)
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3OrderAccTransactionDef.CHANGE);

                try
                {
                    //validate注文受付ステイタス( )
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestMarginFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("商品区分 != ”現物株式” and 引数.is信用客 == true の場合、" +
                            "validate注文受付ステイタスが”受付不可”である", l_bex);
                    l_orderStatus.interestMarginFlag = false;
                }
                
                // reset注文受付トランザクション(注文受付トランザクション : String)
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3OrderAccTransactionDef.CANCEL);

                try
                {
                    //validate注文受付ステイタス( )
                    WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
                    l_orderStatus.interestMarginFlag = true;
                    l_orderStatus.swapFlag = true;
                }
                catch (WEB3BaseException l_bex)
                {
                    l_web3BaseException = l_bex;
                    log.debug("商品区分 != ”現物株式” and 引数.is信用客 == true の場合、" +
                            "validate注文受付ステイタスが”受付不可”である", l_bex);
                    l_orderStatus.swapFlag = false;
                }
            }
            
            //コールしたすべての validate注文受付ステイタス() の結果が”受付不可”の場合、
            //（コールしたすべての validate注文受付ステイタス() が例外をスローした場合、）
            //例外をスローする。
            //※「緊急停止中」の場合、「緊急停止中」の例外をスロー
            //※「バッチ処理中」の場合、「バッチ処理中」の例外をスロー
            if (l_orderStatus.interestEquityFlag == false &&
                l_orderStatus.offFloor == false &&
                l_orderStatus.interestMarginFlag == false &&
                !l_orderStatus.swapFlag)
            {

                // 注文受付ステイタス＝バッチ中
                if (l_web3BaseException.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00011)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }
                // 注文受付ステイタス＝緊急停止中
                else if (l_web3BaseException.getErrorInfo() == WEB3ErrorCatalog.BUSINESS_ERROR_00012)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                        WEB3GentradeTradingTimeManagement.class.getName()
                            + "." + STR_METHOD_NAME);
                }
                else
                {
                	throw new WEB3BusinessLayerException(
                		WEB3ErrorCatalog.BUSINESS_ERROR_02177,
                		this.getClass().getName() + STR_METHOD_NAME,
                    	"訂正取消不可。");
                }
            }
        }
        return l_orderStatus;
    }

    /**
     * (create注文約定明細)<BR>
     * 表示対象となる注文約定明細を生成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（株式・信用注文約定照会）03.注文約定明細生成１」<BR>
     * 「（株式・信用注文約定照会）04.注文約定明細生成２」<BR>
     * 「（株式・信用注文約定照会）05.注文約定明細生成３」 参照。<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ<BR>
     * <BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンスデータ<BR>
     * <BR>
     * @@param l_orderStatus - (注文受付状態)<BR>
     * 注文受付状態オブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException<BR>
     * @@roseuid 455D820402D6
     */
    protected void createExecuteGroup(WEB3EquityMarginExecuteReferenceRequest l_request,
            WEB3EquityMarginExecuteReferenceResponse l_response, OrderStatus l_orderStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".createExecuteGroup(" + "WEB3EquityMarginExecuteReferenceRequest,"
                + "WEB3EquityMarginExecuteReferenceResponse," + "OrderStatus)";
        log.entering(STR_METHOD_NAME);
        // get補助口座()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        // 拡張金融オブジェクトマネージャを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        // 拡張トランザクションマネージャオブジェクトを取得する
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityFinTransactionManager l_objectFinTransactionManager = 
            (WEB3EquityFinTransactionManager) l_tradingModule.getFinTransactionManager();
        WEB3GentradeMarket l_market = null;
        WEB3EquityTradedProduct l_tradedProduct = null;
        Date l_datOpenDate = null;
        String l_strContractPrice = null;
        // create検索条件文字列
        String l_strSearchCond = this.createQueryString(l_request.productCode, l_request.marketCode,
                l_request.orderBizDate, l_orderStatus, l_request.orderCondType);
        // create検索条件データコンテナ
        Object[] l_searchCondContainers = this.createQueryDataContainer(l_subAccount, l_request.productCode,
                l_request.marketCode, l_request.orderBizDate, l_orderStatus, l_request.orderCondType);
        // createソート条件
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        // 拡張株式注文マネージャオブジェクトを取得する
        WEB3EquityOrderManager l_equityOrderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        // get注文単位一覧
        List l_lisRecords = l_equityOrderManager.getOrderUnits(l_subAccount, ProductTypeEnum.EQUITY, l_strSearchCond,
                l_searchCondContainers, l_strSortCond);
        if (l_lisRecords.isEmpty())
        {
            l_response.executeGroups = null;
            log.exiting(STR_METHOD_NAME);
            return;
        }

        Iterator l_itRecords = l_lisRecords.iterator();
        // 引数.リクエスト.約定状態区分 != null の場合
        if (!WEB3StringTypeUtility.isEmpty(l_request.execType))
        {
            while (l_itRecords.hasNext())
            {

                // is指定約定状態 == false
                if (!this.isDesignateExecutedStatus(l_request.execType, (EqTypeOrderUnit) l_itRecords.next()))
                {
                    l_itRecords.remove();
                }
            }
            if (l_lisRecords.isEmpty())
            {
                l_response.executeGroups = null;
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }
        EqTypeOrderUnit[] l_arrOrderUnit = (EqTypeOrderUnit[]) 
        l_lisRecords.toArray(new EqTypeOrderUnit[l_lisRecords.size()]);
        // remove繰越元注文単位
        l_arrOrderUnit = l_equityOrderManager.removeCarryOverOriginalOrderUnit(l_arrOrderUnit);
        List l_lisOrderUnit = new ArrayList();
        List l_lisOrderUnitId = new ArrayList();
        List l_lisReferenceOrderUnit = new ArrayList();
        boolean l_blnReferenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(l_request.referenceType);

        for (int i = 0; i < l_arrOrderUnit.length; i++)
        {
            // get取引店()
            WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_arrOrderUnit[i].getDataSourceObject();
            // 該当注文単位が信用取引注文の場合
            // (注文単位.注文カテゴリ == ”新規建注文” or ”返済注文” or ”現引・現渡注文” の場合)
            boolean l_blnMarginTradeEnforcement = true;
            boolean l_blnMarginAccountEstablished = true;
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnitRow.getOrderCateg())
                    || OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnitRow.getOrderCateg())
                    || OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
            {
                l_blnMarginTradeEnforcement = l_branch.isMarginTradeEnforcement(
                        l_orderUnitRow.getRepaymentType());
                // 引数.リクエスト.照会区分 == ”訂正取消一覧” and 戻り値 == false の場合
                if (!l_blnMarginTradeEnforcement && l_blnReferenceType)
                {
                    continue;
                }
                // 顧客オブジェクトを取得する
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
                // is信用口座開設
                l_blnMarginAccountEstablished = l_mainAccount.isMarginAccountEstablished(
                        l_orderUnitRow.getRepaymentType());
                // 引数.リクエスト.照会区分 == ”訂正取消一覧” and 戻り値 == false の場合
                if (!l_blnMarginAccountEstablished && l_blnReferenceType)
                {
                    continue;
                }
            }
            // is訂正取消可能時間帯
            boolean l_blnIsChangeCancelEnableTimeZone = false;
            l_blnIsChangeCancelEnableTimeZone = this.isChangeCancelEnableTimeZone(l_arrOrderUnit[i]);
            // 引数.リクエスト.照会区分 == ”訂正取消一覧” and 戻り値 == false の場合
            if (!l_blnIsChangeCancelEnableTimeZone && l_blnReferenceType)
            {
                continue;
            }
            // 銘柄オブジェクトを取得する
            WEB3EquityProduct l_product = (WEB3EquityProduct) (l_arrOrderUnit[i].getProduct());

            try
            {
                // 市場オブジェクトを取得する
                l_market = (WEB3GentradeMarket) l_objectManager.getMarket(l_orderUnitRow.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName()+ STR_METHOD_NAME, 
                        l_nfe.getMessage(), 
                        l_nfe);
            }
            boolean l_blnIsValidateTradedProduct = false;
            try
            {
                // validate取引銘柄
                l_tradedProduct = (WEB3EquityTradedProduct) l_equityOrderManager.validateTradedProduct(l_product,
                        l_market);
            }
            catch (WEB3BaseException l_web3BaseException)
            {
                l_blnIsValidateTradedProduct = true;
            }
            // 引数.リクエスト.照会区分 == ”訂正取消一覧” and 例外がスローされた場合
            if (l_blnIsValidateTradedProduct && l_blnReferenceType)
            {
                continue;
            }


            // 現物株式の注文の場合
            boolean l_blnIsValidateHandlingMarketMargin = false;
            boolean l_blnIsValidateHandlingMarket = false;
            if (OrderCategEnum.ASSET.equals(l_orderUnitRow.getOrderCateg()))
            {
                try
                {
                    // validate取扱可能市場
                    this.validateHandlingMarket(l_branch, l_tradedProduct);
                }
                catch (WEB3BaseException l_web3BaseException)
                {
                    l_blnIsValidateHandlingMarket = true;
                }
                // 引数.リクエスト.照会区分 == ”訂正取消一覧” and 例外がスローされた場合
                if (l_blnIsValidateHandlingMarket && l_blnReferenceType)
                {
                    continue;
                }
            }
            else
            {
                try
                {
                    // validate取扱可能市場（信用）
                    l_equityOrderManager.validateHandlingMarket(l_branch, l_tradedProduct, l_market.getMarketCode(),
                            l_orderUnitRow.getRepaymentType(), l_orderUnitRow.getRepaymentNum());
                }
                catch (WEB3BaseException l_web3BaseException)
                {
                    l_blnIsValidateHandlingMarketMargin = true;
                }
                // 引数.リクエスト.照会区分 == ”訂正取消一覧” and 例外がスローされた場合
                if (l_blnIsValidateHandlingMarketMargin && l_blnReferenceType)
                {
                    continue;
                }

            }
            boolean l_blnIsValidateOrderForChangeability = false;
            try
            {
                // validate注文訂正可能状態
                if (l_blnMarginTradeEnforcement 
                    && l_blnMarginAccountEstablished 
                    && l_blnIsChangeCancelEnableTimeZone
                    && !l_blnIsValidateTradedProduct 
                    && !l_blnIsValidateHandlingMarket 
                    && !l_blnIsValidateHandlingMarketMargin)
                {
                    this.validateOrderForChangeability(l_subAccount, l_product, l_market, l_branch, l_arrOrderUnit[i]);
                }
                else
                {
                    l_blnIsValidateOrderForChangeability = true;
                }
            }
            catch (WEB3BaseException l_web3BaseException)
            {
                l_blnIsValidateOrderForChangeability = true;
            }
            boolean l_blnIsValidateOrderForCancel = false;
            try
            {
                // validate注文取消可能状態
                if (l_blnMarginTradeEnforcement 
                    && l_blnMarginAccountEstablished 
                    && l_blnIsChangeCancelEnableTimeZone
                    && !l_blnIsValidateTradedProduct 
                    && !l_blnIsValidateHandlingMarket 
                    && !l_blnIsValidateHandlingMarketMargin)
                {
                    this.validateOrderForCancellation(l_arrOrderUnit[i].getOrder(), l_market);
                }
                else
                {
                    l_blnIsValidateOrderForCancel = true;
                }
            }
            catch (WEB3BaseException l_web3BaseException)
            {
                l_blnIsValidateOrderForCancel = true;
            }
          
            // 立会外分売注文の場合
            boolean l_blnIsSalesOutSideMarket = 
                WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(
                    l_orderUnitRow.getSonarTradedCode());
            boolean l_blnIsOrderRootDiv = false;
            boolean l_blnIsValidateOffFloorOrderPossible = false;
            boolean l_blnIs = false; 
            // 該当注文単位.注文経路区分 == ”HOST” の場合
            if (l_blnIsSalesOutSideMarket)
            {

                if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
                {
                    l_blnIsOrderRootDiv = true;
                    l_blnIs = true;
                }
                try
                {
                    // validate立会外分売受付可能
                    l_equityOrderManager.validateOffFloorOrderPossible(l_orderUnitRow.getProductId(), 
                            l_orderUnitRow.getMarketId(), l_subAccount);
                }
                catch (WEB3BaseException l_web3BaseException)
                {
                    l_blnIsValidateOffFloorOrderPossible = true;
                    l_blnIs = true;
                }
            }
           
            // 引数.リクエスト.照会区分 == ”訂正取消一覧” and
            // （is訂正取消可能時間帯()の戻り値 == false 
            //   or（validate注文訂正可能状態()のチェック結果がNG（例外をスロー） and validate注文取消可能状態()のチェック結果がNG（例外をスロー））
            //   or (注文単位行オブジェクト.注文経路区分 == ”HOST” and 立会外分売注文の場合)
            //   or (validate立会外分売受付可能()のチェック結果がNG（例外をスロー) and 立会外分売注文の場合)
            //   )
            if(l_blnReferenceType && 
                (!l_blnIsChangeCancelEnableTimeZone 
                   || (l_blnIsValidateOrderForChangeability && l_blnIsValidateOrderForCancel)
                   || (l_blnIsOrderRootDiv && l_blnIsSalesOutSideMarket)
                   || (l_blnIsValidateOffFloorOrderPossible && l_blnIsSalesOutSideMarket)))

            {
                continue;
            }

            WEB3EquityMarginExecuteGroup l_marginExecuteGroup = new WEB3EquityMarginExecuteGroup();
            l_marginExecuteGroup.id = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrderId());
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_product.getDataSourceObject();
            l_marginExecuteGroup.productCode = l_eqtypeProductRow.getProductCode();
            l_marginExecuteGroup.productName = l_eqtypeProductRow.getStandardName();
            l_marginExecuteGroup.marketCode = l_market.getMarketCode();

            if (l_blnIsChangeCancelEnableTimeZone && !l_blnIsValidateOrderForChangeability)
            {
                l_marginExecuteGroup.changeFlag = true;
            }
            else
            {
                l_marginExecuteGroup.changeFlag = false;
            }
            if (l_blnIsChangeCancelEnableTimeZone && !(l_blnIsValidateOrderForCancel || l_blnIs))
            {
                l_marginExecuteGroup.cancelFlag = true;
            }
            else
            {
                l_marginExecuteGroup.cancelFlag = false;
            }
            WEB3EquityMarginOrderIdUnit l_marginOrderIdUnit = new WEB3EquityMarginOrderIdUnit();
            l_marginOrderIdUnit.id = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrderId());
            l_marginOrderIdUnit.productDiv = WEB3EquityDataAdapter.getProductType(l_arrOrderUnit[i]);
            l_lisOrderUnit.add(l_arrOrderUnit[i]);
            l_lisOrderUnitId.add(l_marginOrderIdUnit);
            l_lisReferenceOrderUnit.add(l_marginExecuteGroup);
        }

        // 表示信用注文約定照会注文単位対象行の抽出
        // 要求ページ番号
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        // ページ内表示行数
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_referenceOrderUnitPageIndexInfo = new WEB3PageIndexInfo(
                (WEB3EquityMarginExecuteGroup[]) 
                l_lisReferenceOrderUnit.toArray(new WEB3EquityMarginExecuteGroup[l_lisReferenceOrderUnit.size()]), l_intPageIndex,
                l_intPageSize);

        WEB3EquityMarginExecuteGroup[] l_arrReferenceOrderUnit = (WEB3EquityMarginExecuteGroup[]) 
        l_referenceOrderUnitPageIndexInfo.getArrayReturned(WEB3EquityMarginExecuteGroup.class);

        // 表示信用注文単位対象行の抽出
        WEB3PageIndexInfo orderUnitl_pageIndexInfo = new WEB3PageIndexInfo(
                (EqTypeOrderUnit[]) 
                l_lisOrderUnit.toArray(new EqTypeOrderUnit[l_lisOrderUnit.size()]), l_intPageIndex, l_intPageSize);
        l_arrOrderUnit = (EqTypeOrderUnit[]) orderUnitl_pageIndexInfo.getArrayReturned(EqTypeOrderUnit.class);

        for (int i = 0; i < l_arrOrderUnit.length; i++)
        {
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_arrOrderUnit[i].getDataSourceObject();
            try
            {
                // 市場オブジェクトを取得する
                l_market = (WEB3GentradeMarket) l_objectManager.getMarket(l_orderUnitRow.getMarketId());

            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName()+ STR_METHOD_NAME, 
                        l_nfe.getMessage(), 
                        l_nfe);
            }
            // reset市場コード
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            // reset受付時間区分
            // 立会外分売の場合
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
            }
            // 現引現渡の場合
            else if (WEB3TransactionTypeSONARDef.SWAP_CONTRACT.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            }
            // それ以外 の場合
            else
            {
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            }
            // get口座区分
            // 税区分（現引・現渡注文）

            String l_strSwapTaxType = null;
            if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
            {
                l_strSwapTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getSwapTaxType());
            }
            // 信用注文約定照会注文単位.口座区分（現引・現渡）= get口座区分()の戻り値
            l_arrReferenceOrderUnit[i].swapTaxType = l_strSwapTaxType;
            
            // 信用注文約定照会注文単位.口座区分 = get口座区分
            l_arrReferenceOrderUnit[i].taxType = 
                WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getTaxType());
            
            // get取引区分
            String l_strTradingType = 
                WEB3EquityDataAdapter.getTradingType(l_orderUnitRow.getOrderType());
            // 信用注文約定照会注文単位.執行条件 = get執行条件（SONAR）
            l_arrReferenceOrderUnit[i].execCondType = 
                l_equityOrderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getExecutionConditionType());
            // 信用注文約定照会注文単位.処理状況 = get処理状況区分()の戻り値
            l_arrReferenceOrderUnit[i].transactionStateType = 
                WEB3EquityDataAdapter.getTransactionStatusType(l_arrOrderUnit[i]);

            // 注文単位.発注条件 == ”W指値”の場合
            String l_strExecutionConditionTypeSonar = null;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
            {
                // get執行条件（SONAR）()（W指値用）
                l_strExecutionConditionTypeSonar = l_equityOrderManager.getExecutionConditionTypeSonar(
                    l_orderUnitRow.getWLimitExecCondType());
            }

            // getW指値用有効状態区分()
            String l_strWLimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_arrOrderUnit[i]);

            //getW指値用切替前注文単価()
            String l_strWLimitBefSwitchPrice =
                WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_arrOrderUnit[i]);

            //getW指値用切替前執行条件()
            String l_strWLimitBefSwitchExecCondType =
                WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_arrOrderUnit[i]);

            //get元W指値用注文単価区分()
            String l_strOrgWLimitOrderPriceDiv =
                WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_arrOrderUnit[i]);

            // get元W指値用注文単価()
            String l_strOrgWLimitOrderPrice = null;
            // 元W指値用注文単価区分 == ”指値” の場合のみ
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
            {
                l_strOrgWLimitOrderPrice = WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_arrOrderUnit[i]);
            }

            // get元W指値用執行条件()
            String l_strOrgWLimitExecCondType =
                WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_arrOrderUnit[i]);

            //get遅延区分()
            String l_strDelayDiv =
                WEB3EquityDataAdapter.getDelayDiv(l_arrOrderUnit[i]);

            //is手動発注可能()
            boolean l_blnManualOrderPossible = l_equityOrderManager.isManualOrderPossible(l_arrOrderUnit[i]);

            // isUnexecuted()の戻り値 == false の場合
            String l_strEstimatedProfitLoss = null;
            WEB3EquityMarginExecuteUnit[] l_arrMarginExecuteUnit = null;
            double l_dblEstimateDeliveryAmountForClose = 0;
            if (!(l_arrOrderUnit[i].isUnexecuted()))
            {
                // get失効時受渡代金
                l_dblEstimateDeliveryAmountForClose = 
                    l_equityOrderManager.getEstimateDeliveryAmountForClose(l_arrOrderUnit[i]);
                // 現物売注文の場合
                if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnitRow.getOrderType()))
                {
                    // getトランザクション
                    List l_lisReturn = l_objectFinTransactionManager.getTransactions(l_arrOrderUnit[i]);
                    if (!l_lisReturn.isEmpty())
                    {
                        // get概算損益
                        l_strEstimatedProfitLoss = l_objectFinTransactionManager.getEstimatedProfitLoss(l_lisReturn);
                    }

                }

                // create分割約定一覧
                l_arrMarginExecuteUnit = this.createExecuteUnits(l_arrOrderUnit[i]);
                // 信用注文約定照会注文単位.約定株数 = 注文単位.約定数量

                double l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
                l_arrReferenceOrderUnit[i].execQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
                BigDecimal l_bdExecutedQuantity = new BigDecimal("" + l_dblExecutedQuantity);

                double l_dblExecutedAmount = l_orderUnitRow.getExecutedAmount();
                BigDecimal l_bdExecutedAmount = new BigDecimal("" + l_dblExecutedAmount);
                // 信用注文約定照会注文単位.約定単価 = 注文単位.合計約定金額 / 注文単位.約定数量
                l_arrReferenceOrderUnit[i].execPrice = WEB3StringTypeUtility.formatNumber(l_bdExecutedAmount.divide(
                        l_bdExecutedQuantity, 0, BigDecimal.ROUND_HALF_UP).doubleValue());
                // 信用注文約定照会注文単位.受渡代金 = get失効時受渡代金()の戻り値
                l_arrReferenceOrderUnit[i].deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblEstimateDeliveryAmountForClose);
            }

            WEB3MarginRepaymentUnit l_repayment = null;
            // 該当注文単位が信用取引注文の場合
            // (注文単位.注文カテゴリ == ”新規建注文” or ”返済注文” or ”現引・現渡注文” の場合)
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnitRow.getOrderCateg())
                    || OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnitRow.getOrderCateg())
                    || OrderCategEnum.SWAP_MARGIN.equals(l_orderUnitRow.getOrderCateg()))
            {
                l_repayment = new WEB3MarginRepaymentUnit();
                l_repayment.repaymentDiv = l_orderUnitRow.getRepaymentType();
                l_repayment.repaymentTimeLimit = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getRepaymentNum());
                // 信用注文約定照会注文単位.弁済 = l_repayment;
                l_arrReferenceOrderUnit[i].repayment = l_repayment;
                // get建日(注文単位)
                l_datOpenDate = this.getOpenDate(l_arrOrderUnit[i]);
                // 信用注文約定照会注文単位.建日 = get建日(注文単位);
                l_arrReferenceOrderUnit[i].openDate = l_datOpenDate;
                // get建単価(注文単位)
                l_strContractPrice = this.getContractPrice(l_arrOrderUnit[i]);
                // 信用注文約定照会注文単位.建単価 = get建単価(注文単位);
                l_arrReferenceOrderUnit[i].contractPrice = l_strContractPrice;
            }

            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                // 信用注文約定照会注文単位.取引区分 =
                // WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET;
                l_arrReferenceOrderUnit[i].tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;

            }
            else
            {
                l_arrReferenceOrderUnit[i].tradingType = l_strTradingType;
            }

            // 信用注文約定照会注文単位.値段条件 = l_orderUnitRow.getPriceConditionType();
            l_arrReferenceOrderUnit[i].priceCondType = l_orderUnitRow.getPriceConditionType();

            // 信用注文約定照会注文単位.発注条件区分 = l_orderUnitRow.getOrderConditionType();
            l_arrReferenceOrderUnit[i].orderCondType = l_orderUnitRow.getOrderConditionType();
            // 注文単位.発注条件 == ”逆指値” の場合のみ。
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
            {
                // 信用注文約定照会注文単位.逆指値用発注条件単価 =  注文単位.逆指値基準値;
                if (l_orderUnitRow.getStopOrderPriceIsNull())
                {
                    l_arrReferenceOrderUnit[i].stopOrderCondPrice = null;
                }
                else
                {
                    l_arrReferenceOrderUnit[i].stopOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_orderUnitRow.getStopOrderPrice());
                }
                    
                // 信用注文約定照会注文単位.逆指値用発注条件演算子 =
                // l_orderUnitRow.getOrderCondOperator();
                l_arrReferenceOrderUnit[i].stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
            }
            // 注文単位.発注条件 == ”W指値”の場合
            String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                // 信用注文約定照会注文単位.W指値用発注条件単価 = l_orderUnitRow.getStopOrderPrice();
                if (l_orderUnitRow.getStopOrderPriceIsNull())
                {
                    l_arrReferenceOrderUnit[i].wlimitOrderCondPrice = null;
                }
                else
                {
                    l_arrReferenceOrderUnit[i].wlimitOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                            l_orderUnitRow.getStopOrderPrice());
                }
                // 信用注文約定照会注文単位.逆指値用発注条件演算子 =
                // l_orderUnitRow.getOrderCondOperator();
                l_arrReferenceOrderUnit[i].wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
                // 注文単位.（W指値）訂正指値 == 0 の場合
                double l_dblWLimitPrice = l_orderUnitRow.getWLimitPrice();
                if (l_dblWLimitPrice == 0)
                {
                    // 信用注文約定照会注文単位.W指値用注文単価区分 = 成行
                    l_arrReferenceOrderUnit[i].wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {
                    // 信用注文約定照会注文単位.W指値用注文単価区分 = 指値
                    l_arrReferenceOrderUnit[i].wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    // 信用注文約定照会注文単位.W指値用注文単価 = 注文単位.（W指値）訂正指値
                        l_arrReferenceOrderUnit[i].wLimitPrice = WEB3StringTypeUtility.formatNumber(
                                l_orderUnitRow.getWLimitPrice());
                }
            }
            // 信用注文約定照会注文単位.W指値用執行条件 = get執行条件（SONAR）()（W指値用）の戻り値
            l_arrReferenceOrderUnit[i].wlimitExecCondType = l_strExecutionConditionTypeSonar;
            // 信用注文約定照会注文単位.W指値用有効状態区分 = getW指値用有効状態区分()
            l_arrReferenceOrderUnit[i].wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
            // 信用注文約定照会注文単位.W指値用切替前注文単価 = getW指値用切替前注文単価()の戻り値
            l_arrReferenceOrderUnit[i].wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;
            // 信用注文約定照会注文単位.W指値用切替前執行条件 = getW指値用切替前執行条件()の戻り値
            l_arrReferenceOrderUnit[i].wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;
            // 信用注文約定照会注文単位.元発注条件区分 = 注文単位.元発注条件
            l_arrReferenceOrderUnit[i].orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();
            // 信用注文約定照会注文単位.元発注条件単価 = 注文単位.元発注条件単価
            if(l_orderUnitRow.getOrgStopOrderPriceIsNull())
            {
                l_arrReferenceOrderUnit[i].orgOrderCondPrice = null;
            }
            else
            {
                l_arrReferenceOrderUnit[i].orgOrderCondPrice = WEB3StringTypeUtility.formatNumber(
                        l_orderUnitRow.getOrgStopOrderPrice());
            }
            // 信用注文約定照会注文単位.元発注条件演算子 = 注文単位.元発注条件演算子
            l_arrReferenceOrderUnit[i].orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();
            // 信用注文約定照会注文単位.元W指値用注文単価区分 = get元W指値用注文単価区分
            l_arrReferenceOrderUnit[i].orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;
            // 信用注文約定照会注文単位.元W指値用注文単価 = get元W指値用注文単価
            l_arrReferenceOrderUnit[i].orgWlimitPrice = l_strOrgWLimitOrderPrice;
            // 信用注文約定照会注文単位.元W指値用執行条件 = get元W指値用執行条件
            l_arrReferenceOrderUnit[i].orgWlimitExecCondType = l_strOrgWLimitExecCondType;
            // 信用注文約定照会注文単位.注文株数 = 注文単位.注文数量
            l_arrReferenceOrderUnit[i].orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
            // 注文単位.isMarketOrder()の戻り値 == true の場合
            boolean l_blnMarketOrder = l_arrOrderUnit[i].isMarketOrder();
            if (l_blnMarketOrder)
            {
                // 信用注文約定照会注文単位.注文単価区分 = 成行
                l_arrReferenceOrderUnit[i].orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                // 信用注文約定照会注文単位.注文単価区分 = 指値
                l_arrReferenceOrderUnit[i].orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            // 信用注文約定照会注文単位.注文単価区分 == 指値
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_arrReferenceOrderUnit[i].orderPriceDiv))
            {
                // 信用注文約定照会注文単位.注文単価 = 注文単位.指値
                if (l_orderUnitRow.getPriceIsNull())
                {
                    l_arrReferenceOrderUnit[i].limitPrice = null;
                }
                else
                {
                    l_arrReferenceOrderUnit[i].limitPrice = WEB3StringTypeUtility.formatNumber(
                            l_orderUnitRow.getLimitPrice());
                }
            }

            // 信用注文約定照会注文単位.概算受渡代金 = 注文単位.概算受渡代金
            l_arrReferenceOrderUnit[i].estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

            // 信用注文約定照会注文単位.概算損益 = get概算損益
            l_arrReferenceOrderUnit[i].estimatedProfitLoss = l_strEstimatedProfitLoss;
            // 信用注文約定照会注文単位.注文時間 = 注文単位.作成日付
            l_arrReferenceOrderUnit[i].orderDate = l_orderUnitRow.getCreatedTimestamp();
            // 信用注文約定照会注文単位.発注日 = 注文単位.発注日
            l_arrReferenceOrderUnit[i].orderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            // 出来るまで注文の場合
            boolean l_blnCarriedOrderUnit = l_equityOrderManager.isCarriedOrderUnit(l_arrOrderUnit[i]);
            if (l_blnCarriedOrderUnit)
            {
                // 信用注文約定照会注文単位.注文有効期
                l_arrReferenceOrderUnit[i].expirationDate = l_orderUnitRow.getExpirationDate();
            }

            // 信用注文約定照会注文単位.注文チャネル = 注文単位.初回注文の注文チャネル
            l_arrReferenceOrderUnit[i].orderChannel = l_orderUnitRow.getOrderChanel();
            // 信用注文約定照会注文単位.注文経路区分 = 注文単位.注文経路区分
            l_arrReferenceOrderUnit[i].orderRootDiv = l_orderUnitRow.getOrderRootDiv();
            try
            {
                WEB3GentradeTrader l_trader = (WEB3GentradeTrader) l_objectManager.getTrader(l_orderUnitRow
                        .getTraderId());
                // 信用注文約定照会注文単位.オペレータコード = 戻り値.扱者コード
                l_arrReferenceOrderUnit[i].operatorCode = l_trader.getTraderCode();
            }
            catch (NotFoundException l_ex)
            {
                // 信用注文約定照会注文単位.オペレータコード = null
                l_arrReferenceOrderUnit[i].operatorCode = null;
            }
            // 約定がある場合 : 注文単位.isUnexecuted()の戻り値 == false の場合
            if (!(l_arrOrderUnit[i].isUnexecuted()))
            {
                // 信用注文約定照会注文単位.分割約定一覧
                l_arrReferenceOrderUnit[i].executeUnits = l_arrMarginExecuteUnit;
            }
            // 信用注文約定照会注文単位.遅延区分 = get遅延区分()の戻り値
            l_arrReferenceOrderUnit[i].delayDiv = l_strDelayDiv;
            // 信用注文約定照会注文単位.手動発注可能フラグ = is手動発注可能()の戻り値
            l_arrReferenceOrderUnit[i].manualFlag = l_blnManualOrderPossible;
            // 強制決済理由
            l_arrReferenceOrderUnit[i].forcedSettleReason = l_orderUnitRow.getForcedSettleReasonType();
            // 強制失効区分
            l_arrReferenceOrderUnit[i].forcedLapseDiv = l_orderUnitRow.getForcedExpireType();
        }
        int l_intOrderUnitPageIndex = orderUnitl_pageIndexInfo.getPageIndex();
        int l_intTotalPages = orderUnitl_pageIndexInfo.getTotalPages();
        int l_intTotalRecords = orderUnitl_pageIndexInfo.getTotalRecords();

        WEB3EquityMarginOrderIdUnit[] l_arrOrderUnitId = (WEB3EquityMarginOrderIdUnit[]) 
        l_lisOrderUnitId.toArray(new WEB3EquityMarginOrderIdUnit[l_lisOrderUnitId.size()]);
        // 注文情報一覧
        l_response.executeGroups = l_arrReferenceOrderUnit;
        // ID一覧
        l_response.idList = l_arrOrderUnitId;
        // 表示ページ番号
        l_response.pageIndex = String.valueOf(l_intOrderUnitPageIndex);
        // 総ページ数
        l_response.totalPages = String.valueOf(l_intTotalPages);
        // 総レコード数
        l_response.totalRecords = String.valueOf(l_intTotalRecords);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * １）戻り値となる文字列のインスタンスを生成する。<BR>
     * <BR>
     * ２）発注日条件を１）の文字列に追加する。<BR>
     * <BR>
     * ２－１）引数.発注日 != null （発注日指定）の場合<BR>
     * <BR> " biz_date = ?"<BR>
     * <BR>
     * ２－２）引数.発注日 == null （発注日指定なし）の場合<BR>
     * <BR> " biz_date >= ?"<BR>
     * <BR>
     * ３）注文種別条件を追加する。<BR>
     * <BR>
     * ３－１）<BR>
     * n = 0 とする。<BR>
     * <BR>
     * ３－２）<BR>
     * 引数.注文受付状態.現物株式フラグ == true or<BR>
     * 引数.注文受付状態.立会外分売フラグ == true の場合<BR>
     * <BR>
     * n = n + 2 とする。<BR>
     * <BR>
     * ３－３）<BR>
     * 引数.注文受付状態.信用取引フラグ == true の場合<BR>
     * <BR>
     * n = n + 4 とする。<BR>
     * <BR>
     * ３－４）<BR>
     * 引数.注文受付状態.現引・現渡フラグ == true の場合<BR>
     * <BR>
     * n = n + 2 とする。<BR>
     * <BR>
     * ３－５）<BR>
     * 以下の文字列を１）の文字列に追加する。<BR>
     * <BR> " and order_type in (?, ?, ...)"<BR>
     * <BR>
     * ※"?"の数はｎ個になるようにする。<BR>
     * <BR>
     * ※n=0のパターンはここでは有り得ない。（validate注文受付状態()で例外になっている? ず）<BR>
     * <BR>
     * ４）取引コード（SONAR)条件を追加する。<BR>
     * <BR>
     * ４－１）<BR>
     * n = 0 とする。<BR>
     * <BR>
     * ４－２）<BR>
     * 引数.注文受付状態.現物株式フラグ == true の場合<BR>
     * <BR>
     * n = n + 1 とする。<BR>
     * <BR>
     * ４－３）<BR>
     * 引数.注文受付状態.立会外分売フラグ == true の場合<BR>
     * <BR>
     * n = n + 1 とする。<BR>
     * <BR>
     * ４－４）<BR>
     * 引数.注文受付状態.信用取引フラグ == true の場合<BR>
     * <BR>
     * n = n + 2 とする。<BR>
     * <BR>
     * ４－５）<BR>
     * 引数.注文受付状態.現引・現渡フラグ == true の場合<BR>
     * <BR>
     * n = n + 1 とする。<BR>
     * <BR>
     * ４－６）<BR>
     * 以下の文字列を１）の文字列に追加する。<BR>
     * <BR> " and sonar_traded_code in (?, ?, ...)"<BR>
     * <BR>
     * ※"?"の数はｎ個になるようにする。<BR>
     * <BR>
     * ※n=0のパターンはここでは有り得ない。（validate注文受付状態()で例外になっているはず）<BR>
     * ず）<BR>
     * <BR>
     * ５）引数.銘柄コード != null （銘柄コード指定）の場合、銘柄指定条件を１）の文字列に追加する。<BR>
     * （銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR> " and product_id = ?"<BR>
     * <BR>
     * ６）引数.市場コード != null （市場コード指定）の場合、市場指定条件を１）の文字列に追加する。<BR>
     * （市場コードに対応する市場IDで検索を行う)<BR>
     * <BR> " and market_id = ?"<BR>
     * <BR>
     * ７）引数.発注条件区分!=null（発注条件区分指定）の場合、<BR>
     * 発注条件区分指定条件を１）の文字列に追加する。<BR>
     *（株式注文単位テーブル.元発注条件に値が設定されている場合は、<BR>
     * 元発注条件をもとに検索する。<BR>
     * 元発注条件に値が設定されていない場合は、<BR>
     * 株式注文単位テーブル.発注条件をもとに検索する。) <BR>
     * <BR>
     * 　@" and nvl(org_order_condition_type,order_condition_type) = ?"<BR>
     * <BR>  
     * ８）生成した文字列を返却する。<BR>
     * <BR>
     * 
     * @@param l_strProductCode -
     *            (銘柄コード)<BR>
     *            銘柄コード<BR>
     *            <BR>
     * @@param l_strMarketCode -
     *            (市場コード)<BR>
     *            市場コード<BR>
     *            <BR>
     * @@param l_datOrderBizDate -
     *            (発注日)<BR>
     *            発注日<BR>
     *            <BR>
     * @@param l_orderStatus -
     *            (注文受付状態)<BR>
     *            注文受付状態オブジェクト<BR>
     *            <BR>
     * @@param l_strOrderConditionDiv -
     *            (発注条件区分)<BR>
     *            発注条件区分<BR>
     *            <BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 455D8AA00369
     */
    protected String createQueryString(String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        OrderStatus l_orderStatus,
        String l_strOrderConditionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".createQueryString(String, String, Date, OrderStatus, String)";
        log.entering(STR_METHOD_NAME);

        // １）戻り値となる文字列のインスタンスを生成する
        StringBuffer l_sbWhere = new StringBuffer();

        // ２）発注日条件を１）の文字列に追加する
        // ２－１）引数.発注日 != null （発注日指定）の場合
        if (l_datOrderBizDate != null)
        {
            log.debug("発注日指定の場合");
            l_sbWhere.append(" biz_date = ?");
        }
        else
        {
            // ２－２）引数.発注日 == null （発注日指定なし）の場合
            log.debug("発注日指定なしの場合");
            l_sbWhere.append(" biz_date >= ?");
        }

        // ３）注文種別条件を追加する。
        // ３－１）n = 0 とする。
        int l_intForOrderTypeCnt = 0;

        // ４）取引コード（SONAR)条件を追加する
        // ４－１）m = 0 とする
        int l_intForTradedCodeCnt = 0;

        if (l_orderStatus == null)
        {
            log.debug("注文受付状態がnullである");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_orderStatus.interestEquityFlag || l_orderStatus.offFloor)
        {
            // 引数.注文受付状態.現物株式フラグ == true or
            // 引数.注文受付状態.立会外分売フラグ == true の場合
            l_intForOrderTypeCnt += 2;
        }
        if (l_orderStatus.interestEquityFlag)
        {
            // 引数.注文受付状態.現物株式フラグ == true の場合
            l_intForTradedCodeCnt += 1;
        }
        if (l_orderStatus.offFloor)
        {
            // 引数.注文受付状態.立会外分売フラグ == true の場合
            l_intForTradedCodeCnt += 1;
        }
        if (l_orderStatus.interestMarginFlag)
        {
            // 引数.注文受付状態.信用取引フラグ == true の場合
            l_intForOrderTypeCnt += 4;
            l_intForTradedCodeCnt += 2;
        }
        if (l_orderStatus.swapFlag)
        {
            // 引数.注文受付状態.現引・現渡フラグ == true の場合
            l_intForOrderTypeCnt += 2;
            l_intForTradedCodeCnt += 1;
        }
        // 以下の文字列を１）の文字列に追加する。
        // " and order_type in (?, ?, ...)"
        // ※"?"の数はｎ個になるようにする。
        // ※n=0のパターンはここでは有り得ない。（validate注文受付状態()で例外になっているはず）
        for (int i = 0; i < l_intForOrderTypeCnt; i++)
        {
            if (i == 0)
            {
                l_sbWhere.append(" and order_type in (");
            }
            l_sbWhere.append("?");
            if ((i + 1) == l_intForOrderTypeCnt)
            {
                l_sbWhere.append(")");
            }
            else
            {
                l_sbWhere.append(", ");
            }
        }

        // 以下の文字列を１）の文字列に追加する。
        // " and sonar_traded_code in (?, ?, ...)"
        // ※"?"の数はｍ個になるようにする。
        // ※ｍ=0のパターンはここでは有り得ない。（validate注文受付状態()で例外になっているはず）
        for (int j = 0; j < l_intForTradedCodeCnt; j++)
        {
            if (j == 0)
            {
                l_sbWhere.append(" and sonar_traded_code in (");
            }
            l_sbWhere.append("?");
            if ((j + 1) == l_intForTradedCodeCnt)
            {
                l_sbWhere.append(")");
            }
            else
            {
                l_sbWhere.append(", ");
            }
        }

        if (l_strProductCode != null)
        {
            log.debug("銘柄コード != nullの場合");
            // ５）引数.銘柄コード != null （銘柄コード指定）の場合、銘柄指定条件を１）の文字列に追加する。
            l_sbWhere.append(" and product_id = ?");
        }
        if (l_strMarketCode != null)
        {
            log.debug("市場コード != nullの場合");
            // ６）引数.市場コード != null （市場コード指定）の場合、市場指定条件を１）の文字列に追加する。
            l_sbWhere.append(" and market_id = ?");
        }

        // ７）引数.発注条件区分!=null（発注条件区分指定）の場合
        if (l_strOrderConditionDiv != null)
        {
            l_sbWhere.append(" and nvl(org_order_condition_type,order_condition_type) = ?");
        }

        // ８）生成した文字列を返却する。
        String l_strQueryString = l_sbWhere.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }


    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件にセットする値の配列を生成する。<BR>
     * <BR>
     * １）リストを生成する。<BR>
     * <BR>
     * ２）発注日指定値を１）のリストに追加する。<BR>
     * <BR>
     * ２－１）引数.発注日 != null （発注日指定）の場合<BR>
     * <BR>
     * 発注日指定値 ＝ パラメータ.発注日<BR>
     * <BR>
     * ２－２）引数.発注日 == null （発注日指定なし）の場合<BR>
     * <BR>
     * 発注日指定値 ＝ 業務日付(*)<BR>
     * <BR>
     * (*)GtlUtils.getTradingSystem( ).getBizDate( ) <BR>
     * <BR>
     * ３）注文種別の設定値を追加する。<BR>
     * <BR>
     * ３－１）<BR>
     * 引数.注文受付状態.現物株式フラグ == true or<BR>
     * 引数.注文受付状態.立会外分売フラグ == true の場合<BR>
     * <BR>
     * OrderTypeEnum.現物買注文<BR>
     * OrderTypeEnum.現物売注文 を追加する。<BR>
     * <BR>
     * ３－２）<BR>
     * 引数.注文受付状態.信用取引フラグ == true の場合<BR>
     * <BR>
     * OrderTypeEnum.新規買建注文<BR>
     * OrderTypeEnum.新規売建注文<BR>
     * OrderTypeEnum.買建返済注文<BR>
     * OrderTypeEnum.売建返済注文 を追加する。<BR>
     * <BR>
     * ３－３）<BR>
     * 引数.注文受付状態.現引・現渡フラグ == true の場合<BR>
     * <BR>
     * OrderTypeEnum.現引注文<BR>
     * OrderTypeEnum.現渡注文 を追加する。<BR>
     * <BR>
     * ４）取引コード（SONAR）の設定値を追加する。<BR>
     * <BR>
     * ４－１）<BR>
     * 引数.注文受付状態.現物株式フラグ == true の場合<BR>
     * <BR>
     * WEB3TransactionTypeSONARDef.普通株式 を追加する。<BR>
     * <BR>
     * ４－２）<BR>
     * 引数.注文受付状態.立会外分売フラグ == true の場合<BR>
     * <BR>
     * WEB3TransactionTypeSONARDef.立会外分売 を追加する。<BR>
     * <BR>
     * ４－３）<BR>
     * 引数.注文受付状態.信用取引フラグ == true の場合<BR>
     * <BR>
     * WEB3TransactionTypeSONARDef.信用建<BR>
     * WEB3TransactionTypeSONARDef.信用返済 を追加する。<BR>
     * <BR>
     * ４－４）<BR>
     * 引数.注文受付状態.現引・現渡フラグ == true の場合<BR>
     * <BR>
     * WEB3TransactionTypeSONARDef.現引現渡 を追加する。<BR>
     * <BR>
     * ５）引数.銘柄コード != null （銘柄コード指定）の場合、銘柄IDをリストに追加する。<BR>
     * （銘柄コードに対応する銘柄IDで検索を行う）<BR>
     * <BR>
     * ５－１）拡張プロダクトマネージャ.get銘柄()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 証券会社： 引数.補助口座.getInstitution()の戻り値<BR>
     * 銘柄コード： 引数.銘柄コード<BR>
     * <BR>
     * ５－２）取得した銘柄の銘柄IDをリストに追加する。<BR>
     * <BR>
     * ６）引数.市場コード != null （市場コード指定）の場合、市場IDをリストに追加する。<BR>
     * （市場コードに対応する市場IDで検索を行う)<BR>
     * <BR>
     * ６－１）金融オブジェクトマネージャ.get市場()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 証券会社コード： 引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     * 市場コード： 引数.市場コード<BR>
     * <BR>
     * ６－２）取得した市場の市場IDをリストに追加する。<BR>
     * <BR>
     * ７）引数.発注条件区分 != null （発注条件区分指定）の場合、<BR>
     * 発注条件区分をリストに追加する。<BR>
     * <BR>
     * 　@発注条件区分 ＝ パラメータ.発注条件区分 <BR>
     * <BR>
     * ８）生成したリストから配列を取得して、返却する。<BR>
     * <BR>
     * 
     * @@param l_subAccount -
     *            (補助口座)<BR>
     *            補助口座オブジェクト<BR>
     *            <BR>
     * @@param l_strProductCode -
     *            (銘柄コード)<BR>
     *            銘柄コード<BR>
     *            <BR>
     * @@param l_strMarketCode -
     *            (市場コード)<BR>
     *            市場コード<BR>
     *            <BR>
     * @@param l_datOrderBizDate -
     *            (発注日)<BR>
     *            発注日<BR>
     *            <BR>
     * @@param l_orderStatus -
     *            (注文受付状態)<BR>
     *            注文受付状態オブジェクト<BR>
     *            <BR>
     * @@param l_strOrderConditionDiv -
     *            (発注条件区分)<BR>
     *            発注条件区分<BR>
     *            <BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 455D98F902DB
     */
    protected Object[] createQueryDataContainer(WEB3GentradeSubAccount l_subAccount, String l_strProductCode,
        String l_strMarketCode, Date l_datOrderBizDate, OrderStatus l_orderStatus, String l_strOrderConditionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".createQueryDataContainer("
            + "WEB3GentradeSubAccount, String, String, Date, OrderStatus, String)";
        log.entering(STR_METHOD_NAME);

        // １）リストを生成する
        ArrayList l_lisQueryContainer = new ArrayList();

        if (l_subAccount == null)
        {
            log.debug("補助口座がnullである");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_orderStatus == null)
        {
            log.debug("注文受付状態がnullである");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        WEB3GentradeFinObjectManager l_finObjManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        // ２）発注日指定値を１）のリストに追加する。
        // ２－１）引数.発注日 != null （発注日指定）の場合
        // 発注日指定値 ＝ パラメータ.発注日
        if (l_datOrderBizDate != null)
        {
            log.debug("発注日指定の場合");
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd"));
        }
        // ２－２）引数.発注日 == null （発注日指定なし）の場合
        else
        {
            log.debug("発注日指定なしの場合");
            Date l_datBusinessDate = GtlUtils.getTradingSystem().getBizDate();
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datBusinessDate, "yyyyMMdd"));
        }
        // ３）注文種別の設定値を追加する。
        // ３－１）
        // 引数.注文受付状態.現物株式フラグ == true or
        // 引数.注文受付状態.立会外分売フラグ == true の場合
        // OrderTypeEnum.現物買注文
        // OrderTypeEnum.現物売注文 を追加する。
        if (l_orderStatus.interestEquityFlag || l_orderStatus.offFloor)
        {
            log.debug("現物株式 or 立会外分売の場合");
            l_lisQueryContainer.add(OrderTypeEnum.EQUITY_BUY);
            l_lisQueryContainer.add(OrderTypeEnum.EQUITY_SELL);
        }

        // ３－２）
        // 引数.注文受付状態.信用取引フラグ == true の場合
        // OrderTypeEnum.新規買建注文
        // OrderTypeEnum.新規売建注文
        // OrderTypeEnum.買建返済注文
        // OrderTypeEnum.売建返済注文 を追加する。
        if (l_orderStatus.interestMarginFlag)
        {
            log.debug("信用取引の場合");
            l_lisQueryContainer.add(OrderTypeEnum.MARGIN_LONG);
            l_lisQueryContainer.add(OrderTypeEnum.MARGIN_SHORT);
            l_lisQueryContainer.add(OrderTypeEnum.CLOSE_MARGIN_LONG);
            l_lisQueryContainer.add(OrderTypeEnum.CLOSE_MARGIN_SHORT);
        }

        // ３－３）
        // 引数.注文受付状態.現引・現渡フラグ == true の場合
        // OrderTypeEnum.現引注文
        // OrderTypeEnum.現渡注文 を追加する。
        if (l_orderStatus.swapFlag)
        {
            log.debug("現引・現渡の場合");
            l_lisQueryContainer.add(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_lisQueryContainer.add(OrderTypeEnum.SWAP_MARGIN_SHORT);
        }

        // ４）取引コード（SONAR）の設定値を追加する。
        // ４－１）WEB3TransactionTypeSONARDef.普通株式 を追加する。
        // 引数.注文受付状態.現物株式フラグ == true の場合
        if (l_orderStatus.interestEquityFlag)
        {
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        }

        // ４－２）
        // 引数.注文受付状態.立会外分売フラグ == true の場合
        // WEB3TransactionTypeSONARDef.立会外分売 を追加する。
        if (l_orderStatus.offFloor)
        {
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
        }

        // ４－３）
        // 引数.注文受付状態.信用取引フラグ == true の場合
        // WEB3TransactionTypeSONARDef.信用建
        // WEB3TransactionTypeSONARDef.信用返済 を追加する。
        if (l_orderStatus.interestMarginFlag)
        {
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
        }

        // ４－４）
        // 引数.注文受付状態.現引・現渡フラグ == true の場合
        // WEB3TransactionTypeSONARDef.現引現渡 を追加する。
        if (l_orderStatus.swapFlag)
        {
            l_lisQueryContainer.add(WEB3TransactionTypeSONARDef.SWAP_CONTRACT);
        }

        try
        {
            // ５）引数.銘柄コード != null （銘柄コード指定）の場合、銘柄IDをリストに追加する。
            if (l_strProductCode != null)
            {
                log.debug("銘柄コード指定の場合");
                // ５－１）拡張プロダクトマネージャ.get銘柄()をコールする。
                WEB3EquityProduct l_equityProduct =
                    (WEB3EquityProduct) l_productManager.getProduct(l_subAccount.getInstitution(), l_strProductCode);
                // ５－２）取得した銘柄の銘柄IDをリストに追加する。
                l_lisQueryContainer.add(Long.toString(l_equityProduct.getProductId()));

            }
            // ６）引数.市場コード != null （市場コード指定）の場合、市場IDをリストに追加する。
            // ６－１）金融オブジェクトマネージャ.get市場()をコールする。
            if (l_strMarketCode != null)
            {
                log.debug("市場コード指定の場合");
                Market l_market = l_finObjManager.getMarket(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_strMarketCode);
                // ６－２）取得した市場の市場IDをリストに追加する。
                l_lisQueryContainer.add(Long.toString(l_market.getMarketId()));
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        // ７）引数.発注条件区分 != null （発注条件区分指定）の場合
        if (l_strOrderConditionDiv != null)
        {
            // 発注条件区分をリストに追加する
            l_lisQueryContainer.add(l_strOrderConditionDiv);
        }

        //８）生成したリストから配列を取得して、返却する。
        Object[] l_strQueryContainers = new Object[l_lisQueryContainer.size()];
        l_strQueryContainers = l_lisQueryContainer.toArray();

        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }

    /**
     * (createソート条件)<BR>
     * ソート条件文字列を作成する。<BR>
     * <BR>
     * １）引数.ソートキー.キー項目の数分、対応するテーブルの列物理名を<BR>
     *     昇順／降順指定と共に追加する。<BR>
     * <BR>
     *   ・キー項目とテーブルの列名称との対応は以下の通り<BR>
     *     ※テーブル名： 注文単位テーブル(eqtype_order_unit)<BR>
     *     ※キー項目文字列（シンボル名）は、メッセージ定義書を参照<BR>
     *     ※テーブルの列物理名は、テーブルレイアウトを参照<BR>
     * <BR>
     *   変換前          変換後<BR>
     *   ----------   -----------------------------<BR>
     *   銘柄コード     注文単位テーブル．銘柄ID<BR>
     *   口座区分      注文単位テーブル．税区分<BR>
     *   市場コード     注文単位テーブル．市場ID<BR>
     *   取引区分      注文単位テーブル．注文種別、取引コード（SONAR）(*1)<BR>
     *   値段条件      注文単位テーブル．値段条件<BR>
     *   執行条件      注文単位テーブル．執行条件<BR>
     *   発注条件      注文単位テーブル．発注条件<BR>
     *   注文時間      注文単位テーブル．作成日付<BR>
     *   発注日         注文単位テーブル．発注日<BR>
     *   注文期限      注文単位テーブル．注文失効日付<BR>
     *   弁済区分      注文単位テーブル．弁済区分<BR>
     *   弁済期限値   注文単位テーブル．弁済期限値<BR>
     * <BR>
     *   ・昇順／降順指定は、信用取引ソートキー.昇順／降順 指定に従い設定<BR>
     * <BR>
     *   (*1)昇順指定の場合： 以下の①@→③の順となる。<BR>
     *           ①@注文種別=="現物買注文"＋取引コード（SONAR）=="普通株式"<BR>
     *           ②注文種別=="現物買注文"＋取引コード（SONAR）=="立会外分売"<BR>
     * <BR>
     * ③注文種別=="現物売注文"（売注文は、取引コード（SONAR）=="普通株式"固定）<BR>
     *        降順指定の場合：　@上記の③→①@の順となる。<BR>
     * <BR>
     * ２）ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で追加する。<BR>
     * <BR>
     * ３）作成したソート条件文字列を返却する。<BR>
     * <BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキーの配列<BR>
     * <BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 455D99160077
     */
    protected String createSortCond(WEB3EquityMarginSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            ".createSortCond(WEB3EquityMarginSortKey[])";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys.length == 0)
        {
            log.debug("ソートキーの長さが0である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //ソート条件文字列を作成する。
        StringBuffer l_sbWhere = new StringBuffer();

        Map l_map = new HashMap();
        //銘柄コード ⇒ 注文単位テーブル．銘柄ID
        l_map.put(WEB3EquityKeyItemDef.PRODUCTCODE, EqtypeOrderUnitParams.PTYPE + ".product_id");
        //口座区分 ⇒ 注文単位テーブル．税区分
        l_map.put(WEB3EquityKeyItemDef.ACCOUNTTYPE, EqtypeOrderUnitParams.PTYPE + ".tax_type");
        //市場コード ⇒ 注文単位テーブル．市場ID
        l_map.put(WEB3EquityKeyItemDef.TRADEMARKET, EqtypeOrderUnitParams.PTYPE + ".market_id");
        //取引区分 ⇒ 注文単位テーブル．注文種別、取引コード（SONAR）
        String l_strSort = null;
        String l_strOperation = null;
        //値段条件 ⇒ 注文単位テーブル．値段条件
        l_map.put(WEB3EquityKeyItemDef.PRICE_COND, EqtypeOrderUnitParams.PTYPE + ".price_condition_type");
        //執行条件 ⇒ 注文単位テーブル．執行条件
        l_map.put(WEB3EquityKeyItemDef.EXECUTE_COND, EqtypeOrderUnitParams.PTYPE + ".execution_condition_type");
        //発注条件 ⇒ 注文単位テーブル．発注条件
        l_map.put(WEB3EquityKeyItemDef.SEND_COND, EqtypeOrderUnitParams.PTYPE + ".order_condition_type");
        //注文時間 ⇒ 注文単位テーブル．作成日付
        l_map.put(WEB3EquityKeyItemDef.ORDER_TIME, EqtypeOrderUnitParams.PTYPE + ".created_timestamp");
        //発注日 ⇒ 注文単位テーブル．発注日
        l_map.put(WEB3EquityKeyItemDef.SEND_DATE, EqtypeOrderUnitParams.PTYPE + ".biz_date");
        //注文期限 ⇒ 注文単位テーブル．注文失効日付
        l_map.put(WEB3EquityKeyItemDef.ORDER_TIMELIMIT, EqtypeOrderUnitParams.PTYPE + ".expiration_date");
        //弁済区分 ⇒ 注文単位テーブル．弁済区分
        l_map.put(WEB3EquityKeyItemDef.REPAYMENT_DIV, EqtypeOrderUnitParams.PTYPE + ".repayment_type");
        //弁済期限値 ⇒ 注文単位テーブル．弁済期限値
        l_map.put(WEB3EquityKeyItemDef.REPAYMENTNUM, EqtypeOrderUnitParams.PTYPE + ".repayment_num");

        for (int i = 0; i < l_sortKeys.length; i++)
        {
            l_strOperation = WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc) ? " ASC" : " DESC";

            //１）引数.ソートキー.キー項目の数分、対応するテーブルの列物理名を昇順／降順指定と共に追加する
            if (WEB3EquityKeyItemDef.TRADETYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strSort = EqtypeOrderUnitParams.PTYPE + ".order_type";
                l_strSort += l_strOperation;
                l_strSort += ", ";
                l_strSort += EqtypeOrderUnitParams.PTYPE;
                l_strSort += ".sonar_traded_code";
                l_map.put(WEB3EquityKeyItemDef.TRADETYPE, l_strSort);
            }

            if (l_map.containsKey(l_sortKeys[i].keyItem))
            {
                l_sbWhere.append(l_map.get(l_sortKeys[i].keyItem));
                l_sbWhere.append(l_strOperation);
                l_sbWhere.append(", ");
            }

        }

        //２）ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で追加する。
        l_strSort = EqtypeOrderUnitParams.PTYPE;
        l_strSort += ".last_updated_timestamp";
        l_strSort += " ASC";
        l_sbWhere.append(l_strSort);

        //３）作成したソート条件文字列を返却する。
        String l_strQueryString = l_sbWhere.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (is指定約定状態)<BR>
     * 指定された約定状態に合致しているかどうかを判定し、<BR>
     * 合致している場合はtrueを、合致していない場合はfalseを、それぞれ返す。<BR>
     * <BR>
     * １）株式データアダプタ.get約定状態区分()をコールする。<BR>
     * <BR>
     *   [引数]<BR>
     *   注文単位： 引数.注文単位<BR>
     * <BR>
     * ２）取得した約定状態区分 == 引数.約定状態区分の場合は、trueを返す。<BR>
     *     以外、falseを返す。<BR>
     * <BR>
     * @@param l_strExecutedStatus - (約定状態区分)<BR>
     * 約定状態区分<BR>
     * <BR>
     * 0： 未約定<BR>
     * 1： 一部成立<BR>
     * 2： 全部成立<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 456293230152
     */
    protected boolean isDesignateExecutedStatus(String l_strExecutedStatus, EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = ".isDesignateExecutedStatus(String, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnReturn;

        String l_strExecType = WEB3EquityDataAdapter.getExecType(l_orderUnit);
        if (l_strExecType.equals(l_strExecutedStatus))
        {
            //取得した約定状態区分＝引数.約定状態区分の場合
            log.debug("取得した約定状態区分＝引数.約定状態区分の場合");
            l_blnReturn = true;
        }
        else
        {
            //取得した約定状態区分≠引数.約定状態区分の場合
            log.debug("取得した約定状態区分≠引数.約定状態区分の場合");
            l_blnReturn = false;
        }

        log.exiting(STR_METHOD_NAME);

        return l_blnReturn;
    }

    /**
     * (is訂正取消可能時間帯)<BR>
     * 訂正取消が可能な時間帯かどうかを判定する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（株式・信用注文約定照会）06.訂正取消可能時間帯チェック」 参照。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 4562BA770118
     */
    protected boolean isChangeCancelEnableTimeZone(EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            ".isChangeCancelEnableTimeZone(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("注文単位がnullである");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)((EqTypeOrderUnit)l_orderUnit).getDataSourceObject();

        try
        {
            //2.getMarket(市場ID : long)
            Market l_market = null;
            try
            {
                l_market = l_finObjectManager.getMarket(l_eqTypeOrderUnitRow.getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                log.debug("市場がnullである");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //3.reset市場コード(String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(
                l_market.getMarketCode());

            //4.reset受付時間区分(String)
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()))
            {
                log.debug("立会外分売の場合");
                //引数.注文単位.取引コード（SONAR） == ”立会外分売” の場合、”立会外分売”
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET);
            }
            else if (WEB3TransactionTypeSONARDef.SWAP_CONTRACT.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()))
            {
                log.debug("現引現渡の場合");
                //引数.注文単位.取引コード（SONAR） == ”現引現渡” の場合、”現引・現渡”
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
            }
            else
            {
                log.debug("それ以外の場合");
                //それ以外の場合、”株式・信用”
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            }

            //5.reset注文受付商品(String)
            if (WEB3TransactionTypeSONARDef.MARKET_TRADING.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()))
            {
                log.debug("普通株式の場合");
                //引数.注文単位.取引コード（SONAR） == ”普通株式” の場合、”株式”
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
            }
            else if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(
                l_eqTypeOrderUnitRow.getSonarTradedCode()))
            {
                log.debug("立会外分売の場合");
                //引数.注文単位.取引コード（SONAR） == ”立会外分売” の場合、”立会外分売”
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.SALES_OUTSIDE_MARKET);
            }
            else
            {
                log.debug("それ以外の場合");
                //それ以外の場合、”信用取引”
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
            }
            //6.reset注文受付トランザクション(String)
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);

            //isPTS市場( )
            if (((WEB3GentradeMarket)l_market).isPTSMarket())
            {
                // validate注文受付可能( )
                WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

                //trueを返却する。
                return true;
            }

            //7.validate注文受付可能()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            //8.get発注日( )
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate(
                    l_eqTypeOrderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            //9.get発注日()の戻り値 > 引数.注文単位.発注日 の場合
            //10.validate閉局後訂正取消受付可能(ProductTypeEnum)
            if (WEB3DateUtility.compareToDay(l_datBizDate, l_datOrderBizDate) > 0)
            {
                log.debug("get発注日()の戻り値 > 引数.注文単位.発注日の場合");
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            //例外がスローされた場合（注文受付不可の場合）、false を返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //11.true を返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate注文訂正可能状態)<BR>
     * 注文が訂正可能な状態かどうかをチェックする。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（株式・信用注文約定照会）07.注文訂正可能状態チェック」 参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * <BR>
     * @@param l_product - (銘柄)<BR>
     * 銘柄オブジェクト<BR>
     * <BR>
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト<BR>
     * <BR>
     * @@param l_branch - (部店)<BR>
     * 部店オブジェクト<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * <BR>
     * @@roseuid 4562D87300A1
     */
    protected void validateOrderForChangeability(WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
        WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateOrderForChangeability("
            + "WEB3GentradeSubAccount, WEB3EquityProduct, WEB3GentradeMarket, WEB3GentradeBranch, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // 1.1 getOrder()
        // 1.2 validate注文訂正可能状態
        this.validateOrderForChangeability(l_orderUnit.getOrder(), l_market);

        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow) ((EqTypeOrderUnit) l_orderUnit).getDataSourceObject();

        WEB3EquityOrderManager l_orderMgr = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        // 1.3 (*) 現物株式の注文の場合
        // 引数.注文単位.取引コード（SONAR） == ”普通株式” or ”立会外分売”
        if (WEB3TransactionTypeSONARDef.MARKET_TRADING.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()) ||
            WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_eqTypeOrderUnitRow.getSonarTradedCode()))
        {
            log.debug("現物株式の注文の場合");
            l_orderMgr.validateTradedProduct(l_product, l_market);
        }
        else
        {
            // 1.4 (*)信用取引の注文の場合
            // validate取引銘柄（信用）(補助口座 : 補助口座,
            // 株式銘柄 : 株式銘柄,
            // 市場 : 市場,
            // 部店 : 部店,
            // 弁済区分 : String,
            // 注文カテゴリ : OrderCategEnum,
            // is売建 : boolean,
            // is売買停止チェック : boolean)
            // [引数]
            // 補助口座： 引数.補助口座
            // 株式銘柄： 引数.銘柄
            // 市場： 引数.市場
            // 部店： 引数.部店
            // 弁済区分： 引数.注文単位.弁済区分
            // 注文カテゴリ： 引数.注文単位.注文カテゴリ
            // is売建： （以下のとおり）
            // １）引数.注文単位.getSide()を取得する。
            // ２）以下のとおりにひ
            // ○引数.注文単位.注文カテゴリ == ”新規建注文” の場合
            // 引数.注文単位.getSide()の戻り値 == SideEnum.”買い” の場合、false をセットする。
            // 引数.注文単位.getSide()の戻り値 == SideEnum.”売り” の場合、true をセットする。
            // ○引数.注文単位.注文カテゴリ != ”新規建注文” の場合
            // 引数.注文単位.getSide()の戻り値 == SideEnum.”買い” の場合、true をセットする。
            // 引数.注文単位.getSide()の戻り値 == SideEnum.”売り” の場合、false をセットする。
            // is売買停止チェック： false
            log.debug("信用取引の注文の場合");
            WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = l_eqTypeOrderUnitRow.getRepaymentType();

            // is売建
            boolean l_blnIsOpenSell = true;
            if (OrderCategEnum.OPEN_MARGIN.equals(l_eqTypeOrderUnitRow.getOrderCateg()))
            {
                if (SideEnum.BUY.equals(l_orderUnit.getSide()))
                {
                    l_blnIsOpenSell = false;
                }
                else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                {
                    l_blnIsOpenSell = true;
                }
            }
            else
            {
                if (SideEnum.BUY.equals(l_orderUnit.getSide()))
                {
                    l_blnIsOpenSell = true;
                }
                else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
                {
                    l_blnIsOpenSell = false;
                }
            }

            l_orderMgr.validateTradedProductForMarginTrading(
                l_subAccount,
                l_product,
                l_market,
                l_branch,
                l_repayment.repaymentDiv,
                l_eqTypeOrderUnitRow.getOrderCateg(),
                l_blnIsOpenSell,
                false);
        }

        // 1.5 validateインサイダー
        l_orderMgr.validateInsider(l_subAccount, l_product);

        // 1.6 validate顧客銘柄別取引停止
        //補助口座： 引数.補助口座
        //銘柄ID： 引数.注文単位.銘柄ID
        //注文種別： 引数.注文単位.注文種別
        //市場： 引数.市場
        this.validateAccountProductOrderStop(
            l_subAccount,
            l_orderUnit.getProduct().getProductId(),
            l_orderUnit.getOrderType(),
            l_market);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create分割約定一覧)<BR>
     * 分割約定の配列を生成する。<BR>
     * <BR>
     * １）約定データを取得する。<BR>
     * <BR>
     *   引数.注文単位.getExecutions()をコールする。<BR>
     * <BR>
     * ２）空のリスト（ArryaList）を生成する。<BR>
     * <BR>
     * ３）取得した約定データの要素数分、以下の処理を繰り返す。<BR>
     * <BR>
     * ３－１）株式・信用分割約定のインスタンスを生成する。<BR>
     * <BR>
     * ３－２）株式・信用分割約定のプロパティをセットする。<BR>
     * <BR>
     *   約定日時： 約定データ.約定日時<BR>
     *   約定株数： 約定データ.約定数量<BR>
     *   約定単価： 約定データ.約定単価<BR>
     * <BR>
     * ３－３）リストに株式・信用分割約定インスタンスを追加する。<BR>
     * <BR>
     * ４）生成したリストから配列を取得し、返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * <BR>
     * @@return 株式・信用分割約定[]
     * @@roseuid 45641A5603A9
     */
    protected WEB3EquityMarginExecuteUnit[] createExecuteUnits(EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "createExecuteUnits(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //１）約定データを取得する。
        //引数.注文単位.getExecutions()をコールする。
        OrderExecution[] l_orderExecution = l_orderUnit.getExecutions();

        //２）空のリスト（ArryaList）を生成する。
        List l_lisArrayList = new ArrayList();

        //３）取得した約定データの要素数分、以下の処理を繰り返す。
        for (int i = 0; i < l_orderExecution.length; i++)
        {
            //３－１）株式・信用分割約定のインスタンスを生成する。
            WEB3EquityMarginExecuteUnit l_instant = new WEB3EquityMarginExecuteUnit();

            //３－２）株式・信用分割約定のプロパティをセットする。
            l_instant.executionTimestamp = l_orderExecution[i].getExecutionTimestamp();
            l_instant.execQuantity = WEB3StringTypeUtility.formatNumber(l_orderExecution[i].getExecutionQuantity());
            l_instant.execPrice = WEB3StringTypeUtility.formatNumber(l_orderExecution[i].getExecutionPrice());

            //３－３）リストに株式・信用分割約定インスタンスを追加する。
            l_lisArrayList.add(l_instant);
        }

        //４）生成したリストから配列を取得し、返却する。
        WEB3EquityMarginExecuteUnit[] l_equityMarginExecuteUnit =
            new WEB3EquityMarginExecuteUnit[l_orderExecution.length];
        l_lisArrayList.toArray(l_equityMarginExecuteUnit);

        log.exiting(STR_METHOD_NAME);
        return l_equityMarginExecuteUnit;
    }

    /**
     * (get建日)<BR>
     * 建日を取得する。<BR>
     * <BR>
     * １）引数.注文単位.注文カテゴリ == ”新規建注文” の場合<BR>
     * <BR>
     *   null を返却する。<BR>
     * <BR>
     * ２）引数.注文単位.注文カテゴリ != ”新規建注文” の場合<BR>
     * <BR>
     * ２－１）引数.注文単位.決済順序区分 == null の場合<BR>
     * <BR>
     * ２－１－１）建株IDを取得する。<BR>
     * <BR>
     * ２－１－１－１）<BR>
     *   引数.注文単位.getContractsToClose()をコールし、<BR>
     *   返済指定情報を取得する。<BR>
     * <BR>
     * ２－１－１－２）<BR>
     *   取得した返済指定情報から建株IDを取得する。<BR>
     * <BR>
     * ２－１－２）<BR>
     *   株式ポジションマネージャ.getContract()をコールする。<BR>
     * <BR>
     *   [引数]<BR>
     *   建株ID： 取得した建株ID<BR>
     * <BR>
     * ２－１－３）<BR>
     *   取得した建株.建日を返却する。<BR>
     * <BR>
     * ２－２）引数.注文単位.決済順序区分 != null の場合<BR>
     * <BR>
     *   null を返却する。<BR>
     * <BR>
     * @@param l_orderUnit - 注文単位<BR>
     * 注文単位オブジェクト<BR>
     * <BR>
     * @@return Date
     * @@roseuid 45641D2F01E4
     */
    protected Date getOpenDate(EqTypeOrderUnit l_orderUnit)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getOpenDate()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)((EqTypeOrderUnit)l_orderUnit).getDataSourceObject();
        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
        EqTypeClosingContractSpec[] l_closingContractSpecs = null;
        Date l_datContractOpenDate = null;
        // １）引数.注文単位.注文カテゴリ == ”新規建注文” の場合
        //
        // null を返却する。
        if (OrderCategEnum.OPEN_MARGIN.equals(l_eqTypeOrderUnitRow.getOrderCateg()))
        {
            return null;
        }

        // ２）引数.注文単位.注文カテゴリ != ”新規建注文” の場合
        else
        {
            // ２－１）引数.注文単位.決済順序区分 == null の場合
            if (WEB3StringTypeUtility.isEmpty(l_eqTypeOrderUnitRow.getClosingOrderType()))
            {
                // ２－１－１）建株IDを取得する。
                //
                // ２－１－１－１）
                // 引数.注文単位.getContractsToClose()をコールし、
                // 返済指定情報を取得する。
                if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
                {
                    EqTypeContractSettleOrderUnit l_closingOrderUnit =
                        (EqTypeContractSettleOrderUnit)l_orderUnit;
                    l_closingContractSpecs =
                        l_closingOrderUnit.getContractsToClose();
                }
                else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
                {
                    EqTypeContractSwapOrderUnit l_swapOrderUnit =
                        (EqTypeContractSwapOrderUnit)l_orderUnit;
                    l_closingContractSpecs =
                        l_swapOrderUnit.getContractsToClose();
                }
                else
                {
                    return null;
                }

                if (l_closingContractSpecs == null || l_closingContractSpecs.length == 0)
                {
                    return null;
                }
                
                // ２－１－１－２）
                // 取得した返済指定情報から建株IDを取得する。
                long l_lngContractId = l_closingContractSpecs[0].getContractId();
                try
                {
                    // ２－１－２） 
                    // 株式ポジションマネージャ.getContract()をコールする。 
                    WEB3EquityContract l_contract = 
                        (WEB3EquityContract) l_positionManager.getContract(l_lngContractId);
                    // ２－１－３）
                    // 取得した建株.建日を返却する。
                    l_datContractOpenDate = l_contract.getOpenDate();
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME,
                        l_nfe.getMessage(), 
                        l_nfe);
                }

            }
            // ２－２）引数.注文単位.決済順序区分 != null の場合
            //
            // null を返却する。
            else
            {
                return null;
            }
        }
        return l_datContractOpenDate;
    }

    /**
     * (get建単価)<BR>
     * 建単価を取得する。<BR>
     * <BR>
     * １）引数.注文単位.注文カテゴリ == ”新規建注文” の場合<BR>
     * <BR>
     *   null を返却する。<BR>
     * <BR>
     * ２）引数.注文単位.注文カテゴリ != ”新規建注文” の場合<BR>
     * <BR>
     * ２－１）<BR>
     *   引数.注文単位.getContractsToClose()をコールし、<BR>
     *   返済指定情報を取得する。<BR>
     * <BR>
     * ２－２）返済指定情報の要素分、以下の処理を繰り返す。<BR>
     * <BR>
     * ２－２－１）<BR>
     *   建代金を集計する。<BR>
     * <BR>
     *   建代金 = 建代金 ＋ （返済指定情報.建株IDから取得される建株の建単価 ×
     * 返済指定情報.返済注文数量）<BR>
     * <BR>
     * ２－２－２）<BR>
     *   返済注文数量を集計する。<BR>
     * <BR>
     *   返済数量 = 返済数量 ＋ 返済指定情報.返済注文数量<BR>
     * <BR>
     * ２－３）<BR>
     *   建単価を算出する。<BR>
     * <BR>
     *   建単価 = 集計した建代金 / 集計した返済数量<BR>
     * <BR>
     *   ※計算結果は円未満を四捨五入する。<BR>
     * <BR>
     * ２－４）<BR>
     *   算出した建単価を返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * <BR>
     * @@return String
     * @@roseuid 4564259D001F
     */
    protected String getContractPrice(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getContractPrice()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();

        EqTypeClosingContractSpec[] l_closingContractSpecs = null;
        double l_dblContractPrice = 0D;
        // 集計した建代金
        BigDecimal l_bdTotalContractAmount = new BigDecimal(0);
        // 返済数量
        BigDecimal l_bdContractQuantity = new BigDecimal(0);

        EqtypeOrderUnitRow l_eqTypeOrderUnitRow =
            (EqtypeOrderUnitRow)((EqTypeOrderUnit)l_orderUnit).getDataSourceObject();


        // １）引数.注文単位.注文カテゴリ == ”新規建注文” の場合
        //
        //   null を返却する。
        if (OrderCategEnum.OPEN_MARGIN.equals(l_eqTypeOrderUnitRow.getOrderCateg()))
        {
            return null;
        }
        // ２）引数.注文単位.注文カテゴリ != ”新規建注文” の場合
        else
        {
            // ２－１）
            //   引数.注文単位.getContractsToClose()をコールし、
            //   返済指定情報を取得する。
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                EqTypeContractSettleOrderUnit l_closingOrderUnit =
                    (EqTypeContractSettleOrderUnit)l_orderUnit;
                l_closingContractSpecs =
                    l_closingOrderUnit.getContractsToClose();
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                EqTypeContractSwapOrderUnit l_swapOrderUnit =
                    (EqTypeContractSwapOrderUnit)l_orderUnit;
                l_closingContractSpecs =
                    l_swapOrderUnit.getContractsToClose();
            }
            else
            {
                return null;
            }
            
            // ２－２）返済指定情報の要素分、以下の処理を繰り返す。<BR>
            //
            // ２－２－１）<BR>
            //   建代金を集計する。<BR>
            //
            //   建代金 = 建代金 ＋ （返済指定情報.建株IDから取得される建株の建単価 ×
            //   返済指定情報.返済注文数量）

            for (int j = 0;j < l_closingContractSpecs.length;j++)
            {
                long l_lngContractId = l_closingContractSpecs[j].getContractId();
                WEB3EquityContract l_contract;
                try
                {
                    l_contract = (WEB3EquityContract)
                        l_positionManager.getContract(l_lngContractId);
                }
                catch (NotFoundException l_nfe)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                        STR_METHOD_NAME,
                        l_nfe.getMessage(), l_nfe);
                }

                double l_dblQuantity = l_closingContractSpecs[j].getQuantity();
                //返済指定情報.建株IDから取得される建株の建単価
                BigDecimal l_dbContractPrice = new BigDecimal("" + l_contract.getContractPrice());
                // 返済指定情報.返済注文数量
                BigDecimal l_bdQuantity = new BigDecimal("" + l_dblQuantity);
                // 返済指定情報.建株IDから取得される建株の建単価 × 返済指定情報.返済注文数量
                BigDecimal l_bdContractAmount = l_dbContractPrice.multiply(l_bdQuantity);
                l_bdTotalContractAmount = l_bdTotalContractAmount.add(l_bdContractAmount);

                //２－２－２）
                //   返済注文数量を集計する。
                //
                //   返済数量 = 返済数量 ＋ 返済指定情報.返済注文数量
                l_bdContractQuantity = l_bdContractQuantity.add(l_bdQuantity);

            }
            
            // ２－３）<BR>
            //   建単価を算出する。<BR>
            //
            //   建単価 = 集計した建代金 / 集計した返済数量
            //  ※計算結果は円未満を四捨五入する。 
            if (l_bdContractQuantity.doubleValue() == 0)
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, STR_METHOD_NAME);
            }
            else
            {
                l_dblContractPrice = l_bdTotalContractAmount.divide(
                    l_bdContractQuantity,
                    0,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        //２－４）
        //   算出した建単価を返却する。
        return WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
    }

    /**
     * (get発注日一覧)<BR>
     * 発注日一覧を取得する。<BR>
     * <BR>
     * １）空のListを生成する。<BR>
     * <BR>
     * ２）業務日付の前営業日と業務日付を１）のListに追加する。<BR>
     * <BR>
     *    ※業務日付： GtlUtils.getTradingSystem().getBizDate()<BR>
     * <BR>
     * ３）引数.市場コード一覧の各要素について、以下の処理を行う。<BR>
     * <BR>
     * ３－１）取引カレンダコンテキストの市場コードを設定する。<BR>
     * <BR>
     *    取引時間管理.reset市場コード()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    市場コード： 引数.市場コード一覧の要素<BR>
     * <BR>
     * ３－２）発注日を取得する。<BR>
     * <BR>
     *    取引時間管理.get発注日()をコールする。<BR>
     * <BR>
     * ３－３）取得した発注日が１）のListに存在しない場合、１）のListに追加する。<BR>
     * <BR>
     * ４）１）のListから配列を取得し、返却する。<BR>
     * <BR>
     * @@param l_strMarketCodeList - (市場コード一覧)<BR>
     * 市場コード一覧<BR>
     * <BR>
     * @@return Date[]
     * @@roseuid 456A42F5024F
     */
    protected Date[] getOrderBizDateList(String[] l_strMarketCodeList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getOrderBizDateList()";
        log.entering(STR_METHOD_NAME);
        // １）空のListを生成する。
        ArrayList l_lisOrderBizDateList = new ArrayList();

        // ２）業務日付の前営業日と業務日付を１）のListに追加する。
        Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsBizDate);
        l_lisOrderBizDateList.add(l_gentradeBizDate.roll(-1));
        l_lisOrderBizDateList.add(GtlUtils.getTradingSystem().getBizDate());

        // ３）引数.市場コード一覧の各要素について、以下の処理を行う。
        // ３－１）取引カレンダコンテキストの市場コードを設定する。
        //    取引時間管理.reset市場コード()をコールする。
        //    [引数]
        //    市場コード： 引数.市場コード一覧の要素
        for (int i = 0; i < l_strMarketCodeList.length; i++)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(
                    l_strMarketCodeList[i]);

            // ３－２）発注日を取得する。<BR>
            //
            //    取引時間管理.get発注日()をコールする。
            Date l_datBizDte;
            l_datBizDte = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            // ３－３）取得した発注日が１）のListに存在しない場合、１）のListに追加する。
            boolean l_blnExist = false;
            for (int j = 0; j < l_lisOrderBizDateList.size(); j++)
            {
                if (WEB3DateUtility.compareToDay(l_datBizDte, (Date)l_lisOrderBizDateList.get(j)) == 0)
                {
                    l_blnExist = true;
                    break;
                }
            }
            if (!l_blnExist)
            {
                l_lisOrderBizDateList.add(l_datBizDte);
            }
        }
        // ４）１）のListから配列を取得し、返却する。
        log.exiting(STR_METHOD_NAME);
        return (Date[]) l_lisOrderBizDateList.toArray(new Date[l_lisOrderBizDateList.size()]);
    }

    /**
     * (get市場閉局警告市場)<BR>
     * 市場閉局警告市場を取得する。<BR>
     * <BR>
     * １）取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得する。<BR>
     * <BR>
     * 　@　@[取引時間管理.get市場閉局警告市場()にセットする引数]<BR>
     * 　@　@部店：　@引数.部店<BR>
     * 　@　@銘柄タイプ：　@"株式"<BR>
     * 　@　@信用取引区分： 引数.信用取引区分<BR>
     * <BR>
     * <BR>
     * ２）PTS取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得する<BR>
     * <BR>
     * 　@　@[PTS取引時間管理.get市場閉局警告市場()にセットする引数]<BR>
     * 　@　@部店：　@引数.部店<BR>
     * 　@　@銘柄タイプ：　@"株式"<BR>
     * 　@　@信用取引区分：　@"DEFAULT"<BR>
     * <BR>
     * ３）結果を市場コード昇順でマージする。<BR>
     * <BR>
     * ４）取得した市場コードの配列を返却する。<BR>
     * <BR>
     * @@param l_gentradeBranch - (部店)<BR>
     * 部店<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@param l_strMarginTradeDiv - (信用取引区分)<BR>
     * 信用取引区分<BR>
     * @@throws WEB3BaseException
     * @@return String[]
     */
    protected String[] getTradeCloseMarket(
        WEB3GentradeBranch l_gentradeBranch,
        ProductTypeEnum l_productType,
        String l_strMarginTradeDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseMarket(WEB3GentradeBranch, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);

        //１）取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得する。
        //[取引時間管理.get市場閉局警告市場()にセットする引数]
        //部店：　@引数.部店
        //銘柄タイプ：　@"株式"
        //信用取引区分： 引数.信用取引区分
        String[] l_strTradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_gentradeBranch, ProductTypeEnum.EQUITY, l_strMarginTradeDiv);

        //２）PTS取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得する
        //[PTS取引時間管理.get市場閉局警告市場()にセットする引数]
        //部店：　@引数.部店
        //銘柄タイプ：　@"株式"
        //信用取引区分：　@"DEFAULT"
        String[] l_strPTSTradeCloseMarkets = WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
            l_gentradeBranch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

        //３）結果を市場コード昇順でマージする。
        String[] l_strAscTradeCloseMarkets =
            this.mergeAndSort(l_strTradeCloseMarkets, l_strPTSTradeCloseMarkets);

        //４）取得した市場コードの配列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strAscTradeCloseMarkets;
    }

    /**
     * (validate注文取消可能状態)<BR>
     * 注文が取消可能な状態かどうかをチェックする。<BR>
     * 引数の市場がPTS市場であるかでコールするメソッドを呼び分ける。<BR>
     * <BR>
     * １）市場がPTS市場であるかを判定する。<BR>
     * 　@引数.市場.isPTS市場()をコールする。<BR>
     * <BR>
     * 　@※PTS市場の場合(引数.市場.isPTS市場() == true )は以下処理を行う。<BR>
     * 　@　@PTS注文マネージャ.validatePTS注文取消可能状態()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@注文： 引数.注文<BR>
     * <BR>
     * 　@※PTS市場以外の場合(引数.市場.isPTS市場() == false )は以下処理を行う。<BR>
     * 　@　@拡張株式注文マネージャ.validate注文取消可能状態()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@注文：　@引数.注文<BR>
     * <BR>
     * @@param l_order - (注文)<BR>
     * 注文<BR>
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト<BR>
     */
    protected void validateOrderForCancellation(
        Order l_order,
        WEB3GentradeMarket l_market)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation(Order, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        if (l_market == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "," + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS市場の場合(引数.市場.isPTS市場() == true )は以下処理を行う。
        if (l_market.isPTSMarket())
        {
            WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
            //PTS注文マネージャ.validatePTS注文取消可能状態()をコールする。
            //[引数]
            //注文： 引数.注文
            l_equityPTSOrderManager.validatePTSOrderForCancellation(l_order);
        }
        else
        {
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            //拡張株式注文マネージャ.validate注文取消可能状態()をコールする。
            //[引数]
            //注文：　@引数.注文
            l_equityOrderManager.validateOrderForCancellation(l_order);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate取扱可能市場)<BR>
     * 取引可能市場チェックを行う。<BR>
     * 市場がPTS市場であるかでコールするメソッドを呼び分ける。<BR>
     * <BR>
     * 1)市場を取得する。<BR>
     * 　@拡張金融オブジェクトマネージャ.get市場()をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@証券会社コード：　@引数.取引銘柄.証券会社コード<BR>
     * 　@市場コード：　@引数.取引銘柄.市場コード<BR>
     * <BR>
     * ２）市場がPTS市場であるかを判定する。<BR>
     * 　@１）で取得した市場.isPTS市場()をコールする。<BR>
     * <BR>
     * 　@※PTS市場の場合(引数.市場.isPTS市場() == true )は以下処理を行う。<BR>
     * 　@　@PTS注文マネージャ.validate取扱可能PTS市場をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@部店：　@引数.部店<BR>
     * 　@　@取引銘柄：　@引数.取引銘柄<BR>
     * <BR>
     * 　@※PTS市場以外の場合(引数.市場.isPTS市場() == false )は以下処理を行う。<BR>
     * 　@　@拡張株式注文マネージャ.validate取扱可能市場をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@部店：　@引数.部店<BR>
     * 　@　@取引銘柄：　@引数.取引銘柄<BR>
     * <BR>
     * @@param l_branch - (部店)<BR>
     * 部店オブジェクト<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    protected void validateHandlingMarket(
        WEB3GentradeBranch l_branch,
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingMarket(WEB3GentradeBranch, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "," + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張金融オブジェクトマネージャ
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //拡張金融オブジェクトマネージャ.get市場()をコールする。
        //[引数]
        //証券会社コード：　@引数.取引銘柄.証券会社コード
        //市場コード：　@引数.取引銘柄.市場コード
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_tradedProduct.getInstitutionCode(),
                l_tradedProduct.getMarketCode());
        }
        catch(NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //PTS市場の場合(引数.市場.isPTS市場() == true )は以下処理を行う。
        if (l_market.isPTSMarket())
        {
            //PTS注文マネージャ.validate取扱可能PTS市場をコールする。
            //[引数]
            //部店：　@引数.部店
            //取引銘柄：　@引数.取引銘柄
            WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();

            l_equityPTSOrderManager.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);
        }
        else
        {
            //拡張株式注文マネージャ.validate取扱可能市場をコールする。
            //[引数]
            //部店：　@引数.部店
            //取引銘柄：　@引数.取引銘柄
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            l_equityOrderManager.validateHandlingMarket(l_branch, l_tradedProduct);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate顧客銘柄別取引停止)<BR>
     * 顧客銘柄別取引停止チェックを行う。<BR>
     * 引数の市場がPTS市場であるかでコールするメソッドを呼び分ける。<BR>
     * <BR>
     * １）市場がPTS市場であるかを判定する。<BR>
     * 　@引数.市場.isPTS市場()をコールする。<BR>
     * <BR>
     * 　@※PTS市場の場合(引数.市場.isPTS市場() == true )は以下処理を行う。<BR>
     * 　@　@PTS注文マネージャ.validate顧客銘柄別取引停止(PTS)()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@補助口座：　@引数.補助口座<BR>
     * 　@　@銘柄ID：　@引数.銘柄ID<BR>
     * 　@　@注文種別：　@引数.注文種別<BR>
     * <BR>
     * 　@※PTS市場以外の場合(引数.市場.isPTS市場() == false )は以下処理を行う。<BR>
     * 　@　@拡張株式注文マネージャ.validate顧客銘柄別取引停止()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@補助口座：　@引数.補助口座<BR>
     * 　@　@銘柄ID：　@引数.銘柄ID<BR>
     * 　@　@注文種別：　@引数.注文種別<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@param l_market - (市場)<BR>
     * 市場<BR>
     * @@throws WEB3BaseException
     */
    protected void validateAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum  l_orderType,
        WEB3GentradeMarket l_market)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateAccountProductOrderStop(SubAccount, long, OrderTypeEnum, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        if (l_market == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "," + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS市場の場合(引数.市場.isPTS市場() == true )は以下処理を行う。
        if (l_market.isPTSMarket())
        {
            //PTS注文マネージャ.validate顧客銘柄別取引停止(PTS)()をコールする。
            //[引数]
            //補助口座：　@引数.補助口座
            //銘柄ID：　@引数.銘柄ID
            //注文種別：　@引数.注文種別
            WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();

            l_equityPTSOrderManager.validatePTSAccountProductOrderStop(
                l_subAccount, l_lngProductId, l_orderType);
        }
        else
        {
            //拡張株式注文マネージャ.validate顧客銘柄別取引停止()をコールする。
            //[引数]
            //補助口座：　@引数.補助口座
            //銘柄ID：　@引数.銘柄ID
            //注文種別：　@引数.注文種別
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            l_equityOrderManager.validateAccountProductOrderStop(
                l_subAccount, l_lngProductId, l_orderType);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate注文訂正可能状態)<BR>
     * 注文が訂正可能な状態かどうかをチェックする。<BR>
     * 引数の市場がPTS市場であるかでコールするメソッドを呼び分ける。<BR>
     * <BR>
     * １）市場がPTS市場であるかを判定する。<BR>
     * 　@引数.市場.isPTS市場()をコールする。<BR>
     * <BR>
     * 　@※PTS市場の場合(引数.市場.isPTS市場() == true )は以下処理を行う。<BR>
     * 　@　@PTS注文マネージャ.validatePTS注文訂正可能状態()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@注文： 引数.注文<BR>
     * <BR>
     * 　@※PTS市場以外の場合(引数.市場.isPTS市場() == false )は以下処理を行う。<BR>
     * 　@　@拡張株式注文マネージャ.validate注文訂正可能状態()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@注文：　@引数.注文<BR>
     * <BR>
     * @@param l_order - (注文)<BR>
     * 注文<BR>
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderForChangeability(
        Order l_order,
        WEB3GentradeMarket l_market)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order, WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);

        if (l_market == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "," + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //PTS市場の場合(引数.市場.isPTS市場() == true )は以下処理を行う。
        if (l_market.isPTSMarket())
        {
            //PTS注文マネージャ.validatePTS注文訂正可能状態()をコールする。
            //[引数]
            //注文： 引数.注文
            WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();

            l_equityPTSOrderManager.validatePTSOrderForChangeability(l_order);
        }
        else
        {
            //拡張株式注文マネージャ.validate注文訂正可能状態()をコールする。
            //[引数]
            //注文：　@引数.注文
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            l_equityOrderManager.validateOrderForChangeability(l_order);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate取引可能顧客)<BR>
     * 取引可能顧客かどうかチェックする。<BR>
     * PTS市場が指定されている場合は、PTS取引時間管理から<BR>
     * 発注日を取得し、発注日指定でチェックを行う。<BR>
     * <BR>
     * １）引数.市場コード≠nullの場合、<BR>
     * <BR>
     * 　@　@拡張金融オブジェクトマネージャ.getMarket()をコールし、<BR>
     * 　@　@市場オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@証券会社コード:　@引数.顧客.getInstitution().getInstitutionCode()<BR>
     * 　@　@　@市場コード:　@引数.市場コード<BR>
     * <BR>
     * ２）注文チェックオブジェクトを生成する。<BR>
     * <BR>
     * ３）以下の処理を行う。<BR>
     * <BR>
     * 　@３－１）引数.市場コード≠null　@かつ<BR>
     * 　@　@　@　@　@市場.isPTS市場()＝true　@の場合<BR>
     * <BR>
     * 　@　@　@注文チェック.validate取引可能顧客(顧客、発注日)をコールする。<BR>
     * <BR>
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@顧客：　@引数.顧客<BR>
     * 　@　@　@発注日：　@PTS取引時間管理.get発注日()<BR>
     * <BR>
     * 　@３－２）　@３－１）以外の場合<BR>
     * <BR>
     * 　@　@　@注文チェック.validate取引可能顧客(顧客)をコールする。<BR>
     * <BR>
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@顧客：　@引数.顧客<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@return OrderValidationResult
     */
    protected OrderValidationResult validateAccountForTrading(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountForTrading(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //１）引数.市場コード≠nullの場合、
        WEB3GentradeMarket l_market = null;
        boolean l_blnIsPTSMarket = false;
        if (l_strMarketCode != null)
        {
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            //拡張金融オブジェクトマネージャ.getMarket()をコールし、市場オブジェクトを生成する。
            //[引数の設定]
            //証券会社コード:　@引数.顧客.getInstitution().getInstitutionCode()
            //市場コード:　@引数.市場コード
            try
            {
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_strMarketCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            }

            try
            {
                l_blnIsPTSMarket = l_market.isPTSMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        l_ex.getErrorInfo()));
            }
        }

        //２）注文チェックオブジェクトを生成する。
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //３－１）引数.市場コード≠null　@かつ 市場.isPTS市場()＝true　@の場合
        if (l_strMarketCode != null && l_blnIsPTSMarket)
        {
            //PTS取引時間管理.get発注日()
            Date l_datOrderBizDate = null;
            try
            {
                l_datOrderBizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        l_ex.getErrorInfo()));
            }

            //注文チェック.validate取引可能顧客(顧客、発注日)をコールする。
            //[引数の設定]
            //顧客：　@引数.顧客
            //発注日：　@PTS取引時間管理.get発注日()
            OrderValidationResult l_validationPtsResult =
                l_gentradeOrderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(l_datOrderBizDate.getTime()));

            log.exiting(STR_METHOD_NAME);
            return l_validationPtsResult;
        }
        else
        {
            //以外の場合注文チェック.validate取引可能顧客(顧客)をコールする。
            //[引数の設定]
            //顧客：　@引数.顧客
            OrderValidationResult l_validationResult =
                l_gentradeOrderValidator.validateAccountForTrading(l_mainAccount);

            log.exiting(STR_METHOD_NAME);
            return l_validationResult;
        }
    }

    /**
     * (注文受付状態)<BR>
     * 注文受付状態クラス<BR>
     * <BR>
     * 各取引の注文受付状態を保持する。<BR>
     * <BR>
     */
    public class OrderStatus
    {

       /**
        * (現物株式フラグ)<BR>
        * 現物株式の注文受付状態を表すフラグ<BR>
        * <BR>
        * true： 受付可<BR>
        * false： 受付不可<BR>
        * <BR>
        */
       public boolean interestEquityFlag = false;

       /**
        * (立会外分売フラグ)<BR>
        * 立会外分売の注文受付状態を表すフラグ<BR>
        * <BR>
        * true： 受付可<BR>
        * false： 受付不可<BR>
        * <BR>
        */
       public boolean offFloor = false;

       /**
        * (信用取引フラグ)<BR>
        * 信用取引の注文受付状態を表すフラグ<BR>
        * <BR>
        * true： 受付可<BR>
        * false： 受付不可<BR>
        * <BR>
        */
       public boolean interestMarginFlag = false;

       /**
        * (現引・現渡フラグ)<BR>
        * 現引・現渡の注文受付状態を表すフラグ<BR>
        * <BR>
        * true： 受付可<BR>
        * false： 受付不可<BR>
        * <BR>
        */
       public boolean swapFlag = false;

       /**
        * (注文受付状態)<BR>
        * コンストラクタ<BR>
        * <BR>
        * @@roseuid 455D940D01A6
        */
       public OrderStatus()
       {

       }
    }

    /**
     * 二つの配列を一つの配列に合併して、昇順ソートで返却する
     * @@param l_strArraySrc
     * @@param l_strDest
     * @@return String[]
     */
    private String[] mergeAndSort(String[] l_strArraySrcs, String[] l_strDests)
    {
        final String STR_METHOD_NAME = "mergeAndSort(String[], String[])";
        log.entering(STR_METHOD_NAME);
        
         Object[] l_objMergeArrays =
             WEB3Toolkit.toUnique(WEB3Toolkit.merge(l_strArraySrcs, l_strDests));

         if (l_objMergeArrays == null)
         {
             return null;
         }

         String[] l_strResults = new String[l_objMergeArrays.length];

         for (int i = 0; i < l_objMergeArrays.length; i++)
         {
             l_strResults[i] = (String)l_objMergeArrays[i];
         }

         String l_strTemp = null;
         for (int i = 0; i < l_strResults.length; i++)
         {
             for (int j = i + 1; j < l_strResults.length; j++)
             {
                 if (Integer.parseInt(l_strResults[i]) > Integer.parseInt(l_strResults[j]))
                 {
                     l_strTemp = l_strResults[j];
                     l_strResults[j] = l_strResults[i];
                     l_strResults[i] = l_strTemp;
                 }
             }
         }

         return l_strResults;
    }
}
@
