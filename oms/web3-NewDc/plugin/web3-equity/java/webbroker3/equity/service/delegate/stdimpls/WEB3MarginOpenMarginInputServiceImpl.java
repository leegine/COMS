head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建入力サービスImpl(WEB3MarginOpenMarginInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 王暁傑 (Sinocom) 新規作成 
                 : 2006/11/24 唐性峰 (Sinocom)　@モデルNo.993
                 : 2007/01/11 趙林鵬 (中訊) モデル No.1083,1088,1089
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginOpenMarginInputRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginInputResponse;
import webbroker3.equity.message.WEB3MarginProductSelectRequest;
import webbroker3.equity.message.WEB3MarginProductSelectResponse;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginInputService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.define.WEB3GentradeEquityMarginDivDef;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;

/**
 * （信用取引新規建入力サービスImpl）。<BR>
 * <BR>
 * 信用取引新規建入力サービス実装クラス。
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputServiceImpl extends WEB3MarginClientRequestService
    implements WEB3MarginOpenMarginInputService
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginOpenMarginInputServiceImpl.class);

    /**
     * （コンストラクタ）。
     * @@roseuid 4140066F0147
     */
    public WEB3MarginOpenMarginInputServiceImpl()
    {

    }

    /**
     * （execute）。<BR>
     * <BR>
     * 信用取引新規建入力サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、get銘柄選択画面()または、get新規建入力画面()<BR>
     * メソッドのいずれかをコールする。
     * @@param l_request リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407BBCFA0140
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = getClass().getName() + "." + "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3MarginProductSelectRequest)
        {
            l_response = this.getProductSelectScreen((WEB3MarginProductSelectRequest) l_request);
        }
        else if (l_request instanceof WEB3MarginOpenMarginInputRequest)
        {
            l_response = this.getOpenMarginInputScreen((WEB3MarginOpenMarginInputRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        return l_response;
    }

    /**
     * (get銘柄選択画面)。<BR>
     * <BR>
     * 信用取引の銘柄選択画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引新規建入力サービス）get銘柄選択画面」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（信用取引新規建入力サービス）get銘柄選択画面」): <BR>
     *     3.validate信用注文(補助口座 : 補助口座, 弁済区分 : String)<BR>
     *     弁済区分は”DEFAULT”（指定なし）で設定する。※一般信用、<BR>
     *     制度信用どちらも実施していない部店・顧客の場合エラーとし、例外をthrowする。<BR>
     *         class: WEB3BusinessLayerException<BR>                                                                                           
     *         tag:   BUSINESS_ERROR_00644<BR>                                                                                                 
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（信用取引新規建入力サービス）get銘柄選択画面」): <BR>            
     *     7.get取扱可能市場(部店 : 部店, 弁済区分 : String, 弁済期限値 : double)<BR>   
     *     取扱可能市場が一件も存在しない場合は、「取扱可能市場なし」の例外をthrowする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00643<BR>
     * ==========================================================
     * @@param l_request リクエストデータ
     * @@return WEB3MarginProductSelectResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F64A4A031E
     */
    protected WEB3MarginProductSelectResponse getProductSelectScreen(WEB3MarginProductSelectRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductSelectScreen()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //拡張株式注文マネージャ
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        //validate信用注文
        //補助口座：　@get補助口座()の戻り値 
        //弁済区分：　@”DEFAULT”（指定なし）
        l_orderManager.validateMarginOrder(l_subAccount, WEB3GentradeRepaymentDivDef.DEFAULT);
        log.debug("validate信用注文を執行します");

        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        //該当顧客が制度信用取引口座を開設しているかを判定する。
        //弁済区分：”制度信用”
        boolean l_blnIsMarketMarginAccount =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
        log.debug("制度信用取引口座開設 = " + l_blnIsMarketMarginAccount);

        //該当顧客が一般信用取引口座を開設しているかを判定する。
        //弁済区分：”一般信用”
        boolean l_blnIsInstitutionMarginAccount =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
        log.debug("一般信用取引口座開設 = " + l_blnIsInstitutionMarginAccount);

        //信用取引区分:
        //・制度信用、一般信用とも口座開設している場合、”制度／一般信用(両方)”。 
        //    (顧客.is信用口座開設(”制度信用”)==true、かつ、顧客.is信用口座開設(”一般信用”)==true)
        //・制度信用のみ口座開設している場合、”制度信用”。 
        //    (顧客.is信用口座開設(”制度信用”)==true、かつ、顧客.is信用口座開設(”一般信用”)==false) 
        //・一般信用のみ口座開設している場合、”一般信用”。 
        //    (顧客.is信用口座開設(”制度信用”)==false、かつ、顧客.is信用口座開設(”一般信用”)==true) 
        String l_strMarginTradingDiv = null;

        if (l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
        }
        else if (l_blnIsMarketMarginAccount && !l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
        }
        else if (!l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
        }
        log.debug("信用取引区分 = " + l_strMarginTradingDiv);

        // get市場閉局警告市場
        String[] l_strTradeCloseMarkets =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_strMarginTradingDiv);

        //get取扱可能市場
        String[] l_strHandlingPossibleMarkets =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
            l_subAccount.getWeb3GenBranch(), WEB3GentradeRepaymentDivDef.DEFAULT, 0D);
        //0件時はエラー
        if (l_strHandlingPossibleMarkets == null || l_strHandlingPossibleMarkets.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00643,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 注文種別
        OrderTypeEnum l_orderTypeEnum = null;
        boolean l_flg = false;
        try
        {
            log.debug("1.7 validate顧客銘柄別取引停止（新規買建注文）");
            log.debug("取引区分が、新規買建注文として処理します。");
            l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
            l_orderManager.validateAccountProductOrderStop(l_subAccount, 0, l_orderTypeEnum);
            l_flg = true;
        }
        catch (WEB3BaseException l_be)
        {
            log.debug("1.8 validate顧客銘柄別取引停止（新規売建注文）");
            log.debug("取引区分が、新規売建注文として処理します。");
            l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
            l_orderManager.validateAccountProductOrderStop(l_subAccount, 0, l_orderTypeEnum);
        }
        catch (Exception ex)
        {
            log.debug("1.8 validate顧客銘柄別取引停止（新規売建注文）");
            log.debug("取引区分が、新規売建注文として処理します。");
            l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
            l_orderManager.validateAccountProductOrderStop(l_subAccount, 0, l_orderTypeEnum);
        }

        if (l_flg)
        {
            try
            {
                log.debug("1.8 validate顧客銘柄別取引停止（新規売建注文）");
                log.debug("取引区分が、新規売建注文として処理します。");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
                l_orderManager.validateAccountProductOrderStop(l_subAccount, 0, l_orderTypeEnum);
            }
            catch (WEB3BaseException l_be)
            {
            }
            catch (Exception ex)
            {
            }
        }

        WEB3MarginProductSelectResponse l_productSelectResponse =
            (WEB3MarginProductSelectResponse) l_request.createResponse();

        //市場コード一覧：　@(部店市場弁済別)取扱条件.get取扱可能市場( )の戻り値
        //取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市
        l_productSelectResponse.messageSuspension = l_strTradeCloseMarkets;
        l_productSelectResponse.marketList = l_strHandlingPossibleMarkets;

        log.exiting(STR_METHOD_NAME);
        return l_productSelectResponse;
    }

    /**
     * （get新規建入力画面）。<BR>
     * <BR>
     * 信用取引の新規建入力画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引新規建入力サービス）get新規建入力画面」参照。<BR>
     * <BR>
     * <BR>
     *  ========================================================<BR>                                                           
     * シーケンス図(「（信用取引新規建入力サービス）get新規建入力画面１」): <BR>                                               
     *        (12*) （get取扱可能市場( )の戻り値配列の要素数分Loopし、<BR>
     *        リクエストデータ.市場コードが含まれているかチェック）<BR>
     *        リクエストデータ.市場コードが戻り値配列に含まれていない場合：<BR>
     *       「非取扱市場エラー」の例外をthrowする。<BR>         
     *         class: WEB3BusinessLayerException<BR>                                                                           
     *         tag:   BUSINESS_ERROR_00645<BR>                                                                                 
     * ==========================================================<BR>                                                          
     * <BR>
     * ========================================================<BR>
     * シーケンス図(「（信用取引新規建入力サービス）get新規建入力画面１」): <BR>
     * (22*) get取扱可能市場(部店 : 部店, 弁済区分 : String, 弁済期限値 : double)<BR>
     * 取扱可能市場が一件も存在しない場合は、「取扱可能市場なし」の例外をthrowする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_00643<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *    シーケンス図(「（信用取引新規建入力サービス）get新規建入力画面１」): <BR>
     *　@ (28*)validateJASDAQ銘柄取扱可能(部店)<BR>
     *   (銘柄、取扱可能市場チェック)<BR>
     *   １）　@取引区分が指定されている場合(リクエストデータ.取引区分 != null)<BR>
     *   ・validate取引銘柄（信用）が例外をthrow<BR>
     *   ・validateJASDAQ銘柄取扱可能( )が例外をthrow<BR>
     *   上記のいずれかの場合、当該市場コードをレスポンス.市場コード一覧の設定対象外として除去する。<BR>
     *   <BR>
     *   ２）　@取引区分が指定されていない場合(リクエストデータ.取引区分 == null)<BR>
     *    ・validate取引銘柄（信用）(is売建(=true））とvalidate取引銘柄（信用）(is売建(=false））がどちらも例外をthrow<BR>
     *   ・validateJASDAQ銘柄取扱可能( )が例外をthrow<BR>
     *   上記のいずれかの場合、当該市場コードをレスポンス.市場コード一覧の設定対象外として除去する。<BR>
     *   <BR>
     *   ※validate取扱可能市場の戻り値の市場コード全てに対し、　@上記(銘柄、取扱可能市場チェック)を行った結果、<BR>
     *   市場コード配列のサイズが0になった場合、「非取扱銘柄」エラーの例外をthrowする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00664<BR>
     *   ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *   シーケンス図(「（信用取引新規建入力サービス）get新規建入力画面１」): <BR>
     *  (29*) get取扱可能弁済(部店 : 部店, 弁済区分 : String, 市場コード一覧 : String[])<BR>
     *  取扱可能弁済が一件も存在しない場合は、「取扱可能弁済なし」の例外をthrowする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00646<BR>
     *   ==========================================================
     * @@param l_request リクエストデータ
     * @@return WEB3MarginOpenMarginInputResponse
     * @@roseuid 40F64A4A033D
     */
    protected WEB3MarginOpenMarginInputResponse getOpenMarginInputScreen(
        WEB3MarginOpenMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOpenMarginInputScreen(WEB3MarginOpenMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //補助口座を取得
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //get市場コード(String, String, String)
        l_request.marketCode = this.getMarketCode(
            l_request.productCode,
            l_request.marketCode,
            l_subAccount.getInstitution().getInstitutionCode());

        //reset市場コード(市場コード : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        WEB3EquityProductManager l_prodcutManager =
            (WEB3EquityProductManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //validate信用注文
        //補助口座：　@get補助口座()の戻り値 
        //弁済区分：　@”DEFAULT”（指定なし）
        l_orderManager.validateMarginOrder(l_subAccount, WEB3GentradeRepaymentDivDef.DEFAULT);

        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

        //該当顧客が制度信用取引口座を開設しているかを判定する。
        //弁済区分：”制度信用”
        boolean l_blnIsMarketMarginAccount =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
        log.debug("is信用口座開設(制度信用)= " + l_blnIsMarketMarginAccount);

        //該当顧客が一般信用取引口座を開設しているかを判定する。
        //弁済区分：”一般信用”
        boolean l_blnIsInstitutionMarginAccount =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
        log.debug("is信用口座開設(一般信用) = " + l_blnIsInstitutionMarginAccount);

        //弁済区分： 
        //・制度信用、一般信用とも口座開設している場合、”DEFAULT”。 
        //    (顧客.is信用口座開設(”制度信用”)==true、かつ、顧客.is信用口座開設(”一般信用”)==true) 
        //・制度信用のみ口座開設している場合、”制度信用”。 
        //    (顧客.is信用口座開設(”制度信用”)==true、かつ、顧客.is信用口座開設(”一般信用”)==false) 
        //・一般信用のみ口座開設している場合、”一般信用”。 
        //    (顧客.is信用口座開設(”制度信用”)==false、かつ、顧客.is信用口座開設(”一般信用”)==true)
        String l_strPayTypeDiv = null;

        if (l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strPayTypeDiv = WEB3GentradeRepaymentDivDef.DEFAULT;
        }
        else if (l_blnIsMarketMarginAccount && !l_blnIsInstitutionMarginAccount)
        {
            l_strPayTypeDiv = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
        }
        else if (!l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strPayTypeDiv = WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;
        }
        log.debug("弁済区分 = " + l_strPayTypeDiv);

        WEB3EquityProduct l_product = null;
        //(*1)分岐フロー
        //銘柄が指定されている場合のみ、処理を実施する。
        //（リクエストデータ.銘柄コード != null)
        log.debug("l_request.productCode = " + l_request.productCode);

        boolean l_boolIsInsider;
        if (l_request.productCode != null)
        {
            //validate銘柄コード
            l_product = l_orderManager.validateProductCode(
                l_request.productCode, l_mainAccount.getInstitution().getInstitutionCode(), l_strPayTypeDiv);
            //1.17isインサイダー警告表示
            l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_product.getProductId());
        }
        else
        {
            l_boolIsInsider = false;
        }

        log.debug("1.7 validate顧客銘柄別取引停止を行う。");
        // 銘柄ＩＤ
        long l_lngProductId = 0;
        if (l_request.productCode != null)
        {
            l_lngProductId = l_product.getProductId();
        }
        // 注文種別
        OrderTypeEnum l_orderTypeEnum = null;
        if (l_request.tradingType == null)
        {
            log.debug("取引区分がＮＵＬＬです。");
            try
            {
                log.debug("取引区分が、新規買建注文として処理します。");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
                l_orderManager.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderTypeEnum);
            }
            catch (WEB3BaseException l_be)
            {
                log.debug("取引区分が、新規買建注文で例外が発生したので、新規売建注文として処理します。");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
                l_orderManager.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderTypeEnum);
            }
        }
        else
        {
            if (l_request.tradingType.equals("3"))
            {
                log.debug("取引区分は、新規買建注文です。");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
            }
            else
            {
                log.debug("取引区分は、新規売建注文です。");
                l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
            }
            l_orderManager.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderTypeEnum);
        }

        WEB3GentradeMarket l_market = null;
        //市場が指定されている場合のみ、処理を実施する。
        //(リクエストデータ.市場コード != null)
        log.debug("リクエストデータ.市場コード = " + l_request.marketCode);
        if (l_request.marketCode != null)
        {
            log.debug("リクエストデータ.市場コード != nullの場合");
            log.debug("弁済区分 = " + l_strPayTypeDiv);

            //get取扱可能市場
            String[] l_strHandlingPossibleMarkets =
                WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
                l_branch, l_strPayTypeDiv, 0D);

            log.debug("取扱可能市場 = " + l_strHandlingPossibleMarkets);
            if (l_strHandlingPossibleMarkets == null || l_strHandlingPossibleMarkets.length == 0)
            {
                //取扱可能市場が一件も存在しない場合は、「取扱可能市場なし」の例外をthrowする。
                log.debug("取扱可能市場が一件も存在しない場合");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00643, STR_METHOD_NAME);
            }

            //get取扱可能市場( )の戻り値配列の要素数分Loopし、
            //リクエストデータ.市場コードが含まれているかチェック
            int l_intLen = l_strHandlingPossibleMarkets.length;
            log.debug("取扱可能市場の要素数 = " + l_intLen);
            for (int i = 0; i < l_intLen; i++)
            {
                log.debug("l_request.marketCode  = " + l_request.marketCode);
                log.debug("l_strHandlingPossibleMarkets[i]  = " + l_strHandlingPossibleMarkets[i]);
                if (l_request.marketCode.equals(l_strHandlingPossibleMarkets[i]))
                {
                    break;
                }
                if (i == l_intLen - 1)
                {
                    //リクエストデータ.市場コードが戻り値配列に含まれていない場合：
                    //「非取扱市場エラー」の例外をthrowする。
                    log.debug("リクエストデータ.市場コードが戻り値配列に含まれていない場合");
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00645, STR_METHOD_NAME);
                }
            }

            //validate市場コード
            l_market = (WEB3GentradeMarket) l_orderManager.validateMarket(
                l_request.marketCode, l_subAccount.getInstitution().getInstitutionCode());
        }

        String[] l_orderPriceDivList = null;
        WEB3EquityTradedProduct l_tradedProduct = null;
        boolean l_blnIsSpecialAccountEstablished = false;
        //銘柄と市場が両方指定されている場合のみ、処理を実施する。
        //(リクエストデータ.銘柄コード != null、かつ、リクエストデータ.市場コード != null)
        if (l_request.productCode != null && l_request.marketCode != null)
        {
            log.debug("リクエストデータ.銘柄コード != null、かつ、リクエストデータ.市場コード != nullの場合");

            log.debug("l_request.tradingType = " + l_request.tradingType);
            if (l_request.tradingType == null)
            {
                //取引区分が指定されていない場合(リクエストデータ.取引区分 == null）のみ、実施する
                log.debug("取引区分が指定されていない場合");

                //validate取引銘柄(信用)については、
                //validate取引銘柄（信用）(is売建(=true））とvalidate取引銘柄（信用）(is売建(=false）)のチェック行う。
                //is売建(=true）
                try
                {
                    l_tradedProduct = l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_product, l_market, l_branch, l_strPayTypeDiv,
                        OrderCategEnum.OPEN_MARGIN, true);
                }
                catch (WEB3BaseException l_wbex)
                {
                    log.debug(STR_METHOD_NAME, l_wbex);
                    //is売建(=false）
                    l_tradedProduct = l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_product, l_market, l_branch, l_strPayTypeDiv,
                        OrderCategEnum.OPEN_MARGIN, false);
                }
            }
            else
            {

                if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_request.tradingType))
                {
                    //is売建(=true）
                    l_tradedProduct = l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_product, l_market, l_branch, l_strPayTypeDiv,
                        OrderCategEnum.OPEN_MARGIN, true);
                }
                else
                {
                    //is売建(=false）
                    l_tradedProduct = l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_product, l_market, l_branch, l_strPayTypeDiv,
                        OrderCategEnum.OPEN_MARGIN, false);
                }
            }

            //validateJASDAQ銘柄取扱可能
            l_tradedProduct.validateJASDAQProductHandling(l_branch);
            log.debug("validateJASDAQ銘柄取扱可能を執行します");

            //is特定口座開設
            l_blnIsSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(
                l_tradedProduct.getDailyDeliveryDate(), l_subAccount, WEB3GentradeEquityMarginDivDef.MARGIN);

            // get注文単価区分一覧
            l_orderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);
        }
        else
        {
            //（リクエストデータ.銘柄コード == null、または、リクエストデータ.市場コード == null)の場合
            log.debug("（リクエストデータ.銘柄コード == null、または、リクエストデータ.市場コード == null)の場合");
            //is特定口座開設
            l_blnIsSpecialAccountEstablished =
                l_mainAccount.isSpecialAccountEstablished(l_subAccount, WEB3GentradeEquityMarginDivDef.MARGIN);
        }
        log.debug("l_blnIsSpecialAccountEstablished = " + l_blnIsSpecialAccountEstablished);

        //get新規建可能額
        double l_dblOpenMarginPossiblePrice = this.getMarginTradingPower(l_subAccount);

        log.debug("信用新規建可能額 = " + l_dblOpenMarginPossiblePrice);

        //get取扱可能市場
        String[] l_strHandlingPossibleMarkets =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
            l_branch, WEB3GentradeRepaymentDivDef.DEFAULT, 0D);

        log.debug("取扱可能市場を取得 = " + l_strHandlingPossibleMarkets);
        if (l_strHandlingPossibleMarkets == null || l_strHandlingPossibleMarkets.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00643, STR_METHOD_NAME);
        }

        List l_lisHandlingPossibleMarkets = new ArrayList();

        // 市場毎の発注日一覧を作成（get出来るまで注文開始日／最終日／祝日一覧で使用）
        List l_marketOrderBizDateList = new ArrayList();
        if (l_request.marketCode == null)
        {
			int l_intLen = l_strHandlingPossibleMarkets.length;
			for (int i = 0; i < l_intLen; i++)
			{
				WEB3GentradeTradingTimeManagement.resetMarketCode(l_strHandlingPossibleMarkets[i]);
				Date l_mktBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
				l_marketOrderBizDateList.add(l_mktBizDate);
				log.debug("市場コード[" + l_strHandlingPossibleMarkets[i] + "]の発注日" + "："
				    + l_mktBizDate.toString());
			}
        }

        //銘柄が指定されている場合のみ、処理を実施する。
        //（リクエストデータ.銘柄コード != null)
        WEB3GentradeMarket l_wkMarket = null;
        if (l_request.productCode != null)
        {
            int l_intLen = l_strHandlingPossibleMarkets.length;
            for (int i = 0; i < l_intLen; i++)
            {
                log.debug("@@@@@@@@@@@@@@" + i + "@@@@@@@@@@@@@@");
                //reset市場コード
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strHandlingPossibleMarkets[i]);
                log.debug("市場コード = " + l_strHandlingPossibleMarkets[i]);

                try
                {
                    l_wkMarket = (WEB3GentradeMarket) l_finObjectManager.getMarket(
                        l_subAccount.getInstitution().getInstitutionCode(), l_strHandlingPossibleMarkets[i]);
                    //・validate取引銘柄が例外をthrow
                    //上記の場合、当該市場コードをレスポンス.市場コード一覧の設定対象外として除去する    
                    l_tradedProduct =
                        (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_product, l_wkMarket);
                }
                catch (WEB3BaseException l_webex)
                {
                    log.debug(STR_METHOD_NAME, l_webex);
                    continue;
                }
                catch (NotFoundException l_nfe)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                l_lisHandlingPossibleMarkets.add(l_strHandlingPossibleMarkets[i]);
            }
            log.debug("l_lisHandlingPossibleMarkets.size() = " + l_lisHandlingPossibleMarkets.size());
            if (l_lisHandlingPossibleMarkets.size() == 0)
            {
                //　@上記(銘柄、取扱可能市場チェック)を行った結果、市場コード配列のサイズが0になった場合、
                // 「非取扱銘柄」エラーの例外をthrowする。
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00664, STR_METHOD_NAME);
            }
        }
        else
        {
            l_lisHandlingPossibleMarkets = null;
        }

        //get取扱可能弁済
        WEB3MarginRepaymentUnit[] l_repaymentUnit =
            this.getHandlingRepayment(l_branch, l_strPayTypeDiv, l_strHandlingPossibleMarkets);

        if (l_repaymentUnit == null || l_repaymentUnit.length == 0)
        {
            //取扱可能弁済が一件も存在しない場合は、
            //「取扱可能弁済なし」の例外をthrowする。
            log.debug("取扱可能弁済が一件も存在しない場合");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00646, STR_METHOD_NAME);
        }

        //信用取引区分:
        //・制度信用、一般信用とも口座開設している場合、”制度／一般信用(両方)”。 
        //    (顧客.is信用口座開設(”制度信用”)==true、かつ、顧客.is信用口座開設(”一般信用”)==true)
        //・制度信用のみ口座開設している場合、”制度信用”。 
        //    (顧客.is信用口座開設(”制度信用”)==true、かつ、顧客.is信用口座開設(”一般信用”)==false) 
        //・一般信用のみ口座開設している場合、”一般信用”。 
        //    (顧客.is信用口座開設(”制度信用”)==false、かつ、顧客.is信用口座開設(”一般信用”)==true) 
        String l_strMarginTradingDiv = null;

        if (l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN;
        }
        else if (l_blnIsMarketMarginAccount && !l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.MARKET_MARGIN;
        }
        else if (!l_blnIsMarketMarginAccount && l_blnIsInstitutionMarginAccount)
        {
            l_strMarginTradingDiv = WEB3MarginTradingDivDef.INSTITUTION_MARGIN;
        }

        //get市場閉局警告市場
        String[] l_strTradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_strMarginTradingDiv);
        log.debug("市場閉局警告市場 = " + l_strTradeCloseMarkets);

        //取扱可能注文条件を生成
        WEB3GentradeHandlingOrderCond l_handlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_subAccount.getInstitution().getInstitutionCode(),
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                WEB3MarketCodeDef.DEFAULT);

        //信用取引新規建注文入力レスポンスに下記の通りプロパティをセットする。
        WEB3MarginOpenMarginInputResponse l_openMarginInputResponse =
            (WEB3MarginOpenMarginInputResponse) l_request.createResponse();

        //注文単価区分一覧
        if (l_request.productCode != null && l_request.marketCode != null)
        {
        }
        else
        {
            l_orderPriceDivList = new String[2];
            l_orderPriceDivList[0] = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_orderPriceDivList[1] = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        l_openMarginInputResponse.orderPriceDivList = l_orderPriceDivList;

        //値段条件区分一覧
        String[] l_strPriceCondList = l_handlingOrderCond.getHandlingPriceCond();
        l_openMarginInputResponse.priceCondList = l_strPriceCondList;

        //執行条件一覧：　@取扱可能注文条件.取扱可能執行条件取得( )の戻り値配列
        l_openMarginInputResponse.execCondList = l_handlingOrderCond.getHandlingPossibleExecCond();

        //getＷ指値用執行条件一覧(String[], String[])
        //Ｗ指値用執行条件一覧を取得する。
        //引数は以下の通り設定する。
        //執行条件一覧：　@取扱可能注文条件.取扱可能執行条件取得( )の戻り値
        //発注条件一覧：　@取扱可能注文条件.取扱可能発注条件取得( )の戻り値
        String[] l_strWLimitExecutionConditionTypeList =
            WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                l_handlingOrderCond.getHandlingPossibleExecCond(),
                l_handlingOrderCond.getHandlingPossibleOrderCond());

        //W指値用執行条件一覧：　@株式データアダプタ.getＷ指値用執行条件一覧( )の戻り値配列
        l_openMarginInputResponse.wlimitExecCondList =
            l_strWLimitExecutionConditionTypeList;

        //有効期限開始日：　@(**1)取扱可能注文条件.get出来るまで注文開始日( )の戻り値
        //有効期限最終日：　@(**1)取扱可能注文条件.get出来るまで注文最終日( )の戻り値
        //有効期限内祝日一覧：　@(**1)取扱可能注文条件.get注文期限内祝日一覧( )の戻り値配列
        //(**1)　@出来るまで注文取扱可能(取扱可能注文条件.is出来るまで注文取扱可能( ) == true)の場合のみ設定。
        //以外、nullを設定。

        if (l_handlingOrderCond.isOrderUntilDeadLinePossibleHandling())
        {
            //reset市場コード(
			WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

            //出来るまで注文from日付：            
            //リクエストデータ.市場コード == nullの場合、以下のようにセットする。
            Date l_datOrderUntilDeadLineFromDate = null;
            Timestamp l_datSysTimeStamp = l_finApp.getTradingSystem().getSystemTimestamp();
            if (l_request.marketCode == null)
            {
                for (int i = 0; i < l_marketOrderBizDateList.size(); i++)
                {
                    //受付日付と等しい発注日が存在する場合は受付日付をセット
                    if (WEB3DateUtility.compareToDay((Date) l_marketOrderBizDateList.get(i),
                        l_datSysTimeStamp) == 0)
                    {
                        l_datOrderUntilDeadLineFromDate = WEB3DateUtility.toDay(l_datSysTimeStamp);
                        log.debug("受付日付を発注日とする：[" + l_datOrderUntilDeadLineFromDate.toString() + "]");
                        break;
                    }
                }
                //全発注日が受付日付と異なる場合は受付日付の翌営業日をセット
                if (l_datOrderUntilDeadLineFromDate == null)
                {
                    WEB3GentradeBizDate l_genBizDat = new WEB3GentradeBizDate(l_datSysTimeStamp);
                    l_datOrderUntilDeadLineFromDate = WEB3DateUtility.toDay(l_genBizDat.roll(1));
                    log.debug("受付日付の翌営業日を発注日とする：["
                        + l_datOrderUntilDeadLineFromDate.toString() + "]");
                }
            }
            //以外、nullをセット。
            else
            {
                l_datOrderUntilDeadLineFromDate = null;
            }

            //有効期限開始日
            l_openMarginInputResponse.expirationStartDate =
                WEB3DateUtility.toDay(l_handlingOrderCond.getOrderUntilDeadLineStartDay(
                l_datOrderUntilDeadLineFromDate));

            //有効期限最終日
            l_openMarginInputResponse.expirationEndDate =
                WEB3DateUtility.toDay(l_handlingOrderCond.getOrderUntilDeadLineEndDay(
                l_datOrderUntilDeadLineFromDate));

            //有効期限内祝日一覧
            l_openMarginInputResponse.holidayList =
                l_handlingOrderCond.getExpirationDateHoliday(l_datOrderUntilDeadLineFromDate);
        }
        else
        {
            //有効期限開始日
            l_openMarginInputResponse.expirationStartDate = null;

            //有効期限最終日
            l_openMarginInputResponse.expirationEndDate = null;

            //有効期限内祝日一覧
            l_openMarginInputResponse.holidayList = null;
        }

        //取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場( )の戻り値配列
        l_openMarginInputResponse.messageSuspension = l_strTradeCloseMarkets;

        //isインサイダー警告表示
        l_openMarginInputResponse.insiderWarningFlag = l_boolIsInsider;

        //新規建可能額：　@calc信用新規建可能額()の戻り値
        l_openMarginInputResponse.marginTradingPower =
            WEB3StringTypeUtility.formatNumber(l_dblOpenMarginPossiblePrice);

        //銘柄名：　@株式銘柄.銘柄名　@
        //※銘柄コード指定(リクエストデータ.銘柄コード != null)の場合のみ設定。以外、nullを設定
        //※株式銘柄はvalidate銘柄コード（信用）( )の戻り値にて取得
        if (l_request.productCode == null)
        {
            l_openMarginInputResponse.productName = null;
        }
        else
        {
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
            l_openMarginInputResponse.productName = l_productRow.getStandardName();
        }

        //市場コード一覧：
        //(部店市場弁済別)取扱条件.get取扱可能市場( )の戻り値の市場コードの配列
        //※銘柄コード指定の場合は、該当銘柄が取扱可能な市場コードの配列        
        if (l_request.productCode != null)
        {
            l_strHandlingPossibleMarkets = new String[l_lisHandlingPossibleMarkets.size()];
            l_lisHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);

            l_openMarginInputResponse.marketList = l_strHandlingPossibleMarkets;
        }
        else
        {
            l_openMarginInputResponse.marketList = l_strHandlingPossibleMarkets;
        }

        //口座区分一覧：　@
        //顧客.is特定口座開設( )==trueの場合、”0：一般”と”1：特定”の２つの文字列配列をセット。
        //顧客.is特定口座開設( )==falseの場合、”0：一般”のみをセット。
        if (l_blnIsSpecialAccountEstablished)
        {
            String[] l_strTaxTypeList = { WEB3TaxTypeSpecialDef.NORMAL, WEB3TaxTypeSpecialDef.SPECIAL };
            l_openMarginInputResponse.taxTypeList = l_strTaxTypeList;
        }
        else
        {
            String[] l_strTaxTypeList = { WEB3TaxTypeSpecialDef.NORMAL };
            l_openMarginInputResponse.taxTypeList = l_strTaxTypeList;
        }

        //弁済一覧：(部店市場弁済別)取扱条件.get取扱可能弁済( )の戻り値の信用取引弁済の配列
        l_openMarginInputResponse.repaymentList = l_repaymentUnit;

        //注文期限区分一覧：　@取扱可能注文条件.取扱可能注文期限区分取得( )の戻り値配列
        l_openMarginInputResponse.expirationDateTypeList =
            l_handlingOrderCond.getHandlingPossibleExpirationDateType();

        //発注条件区分一覧：　@取扱可能注文条件.取扱可能発注条件取得( )の戻り値配列
        l_openMarginInputResponse.orderCondTypeList = l_handlingOrderCond.getHandlingPossibleOrderCond();

        //市場コード: リクエスト.市場コード
        l_openMarginInputResponse.marketCode = l_request.marketCode;

        //時価(現在値)：　@(**2)getCurrentPrice( )の戻り値
        //前日比：　@(**2)getChange( )の戻り値
        //取引時間(時価発表時間)：　@(**2)getCurrentPriceTime( )の戻り値
        //板情報項目の設定は、(**2)に該当する場合のみ。設定仕様は以下の通り。
        //現在値：　@取得した株式銘柄時価情報.get現在値()の戻り値をセット
        //現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻()の戻り値をセット
        //現在値区分：　@取得した株式銘柄時価情報.get現在値区分()の戻り値をセット
        //現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比()の戻り値をセット
        //出来高：　@取得した株式銘柄時価情報.get出来高()の戻り値をセット
        //出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻()の戻り値をセット
        //買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分()の戻り値をセット
        //買気配値：　@取得した株式銘柄時価情報.get買気配値()の戻り値をセット
        //買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻()の戻り値をセット
        //売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分()の戻り値をセット
        //売気配値：　@取得した株式銘柄時価情報.get売気配値()の戻り値をセット
        //売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻()の戻り値をセット
        //基準値段：　@取得した株式銘柄時価情報.get基準値段()の戻り値をセット
        //(**2)　@銘柄コード・市場コード指定(リクエストデータ.銘柄コード != null、
        //かつ、リクエストデータ.市場コード != null)の場合のみ設定。以外、nullを設定。
        if (l_request.marketCode != null && l_request.productCode != null)
        {
            //reset市場コード(
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

            //取引銘柄を取得
            try
            {
                l_tradedProduct = (WEB3EquityTradedProduct) l_prodcutManager.getTradedProduct(
                    l_subAccount.getInstitution(), l_request.productCode, l_request.marketCode);

                // 拡張プロダクトマネージャ取得
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                
                // get表示用時価情報
                WEB3EquityProductQuote l_productQuote =
                    l_productManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);
                    
                double l_dblCurrentPrice = l_productQuote.getQuote();
                double l_dblChange = l_productQuote.getComparedPreviousDay();

                l_openMarginInputResponse.currentPriceDiv = l_productQuote.getQuoteTypeDiv();
                l_openMarginInputResponse.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
                l_openMarginInputResponse.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
                l_openMarginInputResponse.currentPriceTime = l_productQuote.getQuoteTime();
                l_openMarginInputResponse.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();
                l_openMarginInputResponse.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();
                l_openMarginInputResponse.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();
                l_openMarginInputResponse.boardChange = l_productQuote.getBoardChange();
                l_openMarginInputResponse.volume = l_productQuote.getVolume();
                l_openMarginInputResponse.volumeTime = l_productQuote.getVolumeTime();
                l_openMarginInputResponse.askPriceTitle = l_productQuote.getAskPriceTitle();
                l_openMarginInputResponse.askPrice = l_productQuote.getAskPrice();
                l_openMarginInputResponse.askPriceTime = l_productQuote.getAskPriceTime();
                l_openMarginInputResponse.bidPriceTitle = l_productQuote.getBidPriceTitle();
                l_openMarginInputResponse.bidPrice = l_productQuote.getBidPrice();
                l_openMarginInputResponse.bidPriceTime = l_productQuote.getBidPriceTime();
                l_openMarginInputResponse.basePrice = l_productQuote.getBasePrice();
            }
            catch (NotFoundException l_nfex)
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
            }
        }
        else
        {
            l_openMarginInputResponse.currentPrice = null;
            l_openMarginInputResponse.comparedPreviousDay = null;
            l_openMarginInputResponse.currentPriceTime = null;
            l_openMarginInputResponse.boardCurrentPrice = null;
            l_openMarginInputResponse.boardCurrentPriceTime = null;
            l_openMarginInputResponse.boardCurrentPriceDiv = null;
            l_openMarginInputResponse.boardChange = null;
            l_openMarginInputResponse.volume = null;
            l_openMarginInputResponse.volumeTime = null;
            l_openMarginInputResponse.askPriceTitle = null;
            l_openMarginInputResponse.askPrice = null;
            l_openMarginInputResponse.askPriceTime = null;
            l_openMarginInputResponse.bidPriceTitle = null;
            l_openMarginInputResponse.bidPrice = null;
            l_openMarginInputResponse.bidPriceTime = null;
            l_openMarginInputResponse.basePrice = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_openMarginInputResponse;
    }
    /**
     * （get取扱可能弁済）。<BR>
     * <BR>
     * パラメータの部店に該当する（部店市場弁済別）取扱条件オブジェクトを全て取得し、<BR>
     * パラメータの弁済区分、市場コード一覧(*)に該当するオブジェクトの弁済区分と弁済期限値を<BR>
     * セットした信用取引弁済オブジェクトの配列を返却する。<BR>
     * <BR>
     * ※信用取引弁済オブジェクトへの設定は、弁済区分(昇順）、弁済期限値(昇順)の順とする。<BR>
     * <BR>
     * (*)弁済区分、市場コード一覧は指定時のみチェック内容に追加する。<BR>
     * <BR>
     * １）パラメータチェック<BR>
     * 　@パラメータ.市場コード一覧!=null、かつ、要素数が0の場合、<BR>
     * 　@「パラメータ指定エラー」の例外をthrowする。<BR>
     * <BR>
     * ２）データ取得<BR>
     * 　@（部店市場弁済別）取扱条件.get（部店市場弁済別）取扱条件(パラメータ.部店)により、<BR>
     * 　@パラメータの部店に該当する（部店市場弁済別）取扱条件オブジェクトを全て取得する。<BR>
     * <BR>
     * ３）ArrayListを生成する。<BR>
     * <BR>
     * ４）取扱可能チェック<BR>
     * 　@２）で取得したオブジェクト数分、以下のチェックを行いArrayListに追加する。<BR>
     * <BR>
     * 　@　@　@[チェック内容]<BR>
     * 　@　@　@----------------------------------------------------<BR>
     * 　@　@　@４－１）(部店市場弁済別)取扱条件.is取扱可能 == trueであること。<BR>
     * <BR>
     * 　@　@　@４－２）以下の条件によりチェックを分岐する。<BR>
     * 　@　@　@　@[パラメータ.弁済区分==”DEFAULT（指定なし）”の場合]<BR>
     * 　@　@　@　@　@　@[パラメータ.市場コード一覧!=nullの場合]<BR>
     * 　@　@　@　@　@　@　@(部店市場弁済別)取扱条件.市場コードがパラメータ.市場コード一覧のいずれにも該当しない場合、<BR>
     * 　@　@　@　@　@　@　@次のオブジェクトへ処理を移行する。(continue)<BR>
     * <BR>
     * 　@　@　@　@[パラメータ.弁済区分!=”DEFAULT（指定なし）”の場合]<BR>
     * 　@　@　@　@　@　@（部店市場弁済別）取扱条件.弁済区分 != パラメータ.弁済区分の場合、<BR>
     * 　@　@　@　@　@　@次のオブジェクトへ処理を移行する。(continue)<BR>
     * <BR>
     * 　@　@　@　@　@　@[パラメータ.市場コード一覧!=nullの場合]<BR>
     * 　@　@　@　@　@　@　@(部店市場弁済別)取扱条件.市場コードがパラメータ.市場コード一覧のいずれにも該当しない場合、<BR> 
     * 　@　@　@　@　@　@　@次のオブジェクトへ処理を移行する。(continue)<BR>
     * 　@　@　@----------------------------------------------------<BR>
     * <BR>
     * 　@　@　@４－３）上記チェックを通過したオブジェクトについて、以下の処理を実施する。<BR>
     * 　@　@　@　@①@信用取引弁済オブジェクトを生成。 <BR>
     * 　@　@　@　@②当該レコードの弁済区分、弁済期限値を生成したオブジェクトの同プロパティにセット。<BR>
     * 　@　@　@　@③信用取引弁済オブジェクトをArrayListに追加。<BR>
     * 　@　@　@　@※ArrayListに追加する信用取引弁済オブジェクトは、弁済区分と弁済期限値の組み合わせが<BR>
     * 　@　@　@　@　@重複しないオブジェクトのみをセットすること。 <BR>
     * <BR>
     * ５）作成したArrayList.toArray()の結果を返却する。
     * @@param l_branch 部店<BR>
     * 　@　@　@部店オブジェクト
     * @@param l_strRepaymentType 弁済区分<BR>
     * 　@　@　@0：DEFAULT（指定なし） <BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_marketCodeList 市場コード一覧<BR>
     * 　@　@　@取扱可能な弁済を取得する対象の市場コードの一覧。 <BR>
     * 　@　@　@指定なしの場合、null。
     * @@return WEB3MarginRepaymentUnit[]
     */
    protected WEB3MarginRepaymentUnit[] getHandlingRepayment(
        WEB3GentradeBranch l_branch, String l_strRepaymentType, String[] l_marketCodeList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingRepayment()";
        log.entering(STR_METHOD_NAME);

        //１）パラメータチェック 
        // パラメータ.市場コード一覧!=null、かつ、要素数が0の場合、 
        //「パラメータ指定エラー」の例外をthrowする。 
        if (l_marketCodeList != null && l_marketCodeList.length == 0)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }

        //２）データ取得 
        //（部店市場弁済別）取扱条件.get（部店市場弁済別）取扱条件(パラメータ.部店)により、 
        //パラメータの部店に該当する（部店市場弁済別）取扱条件オブジェクトを全て取得する
        WEB3GentradeBranchMarketRepayDealtCond[] l_branchMarketRepayDealtConds =
            WEB3GentradeBranchMarketRepayDealtCond.getBranchMarketRepayDealtCond(l_branch);

        if (l_branchMarketRepayDealtConds == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }

        //結果リスト
        List l_lisMarginRepaymentUnits = new ArrayList();

        int l_intLen = l_branchMarketRepayDealtConds.length;
        int l_intLenMarkets = 0;
        boolean l_blnHasSameMarketCode;

        for (int i = 0; i < l_intLen; i++)
        {
            l_blnHasSameMarketCode = false;

            //２）で取得したオブジェクト数分、以下のチェックを行いArrayListに追加する。 
            WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = l_branchMarketRepayDealtConds[i];
            BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow =
                (BranchMarketRepayDealtCondRow) l_branchMarketRepayDealtCond.getDataSourceObject();

            //(部店市場弁済別)取扱条件.is取扱可能 == trueであること。
            if (!l_branchMarketRepayDealtCond.isHandlingPossible())
            {
                continue;
            }
            if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
            {
                //[[パラメータ.弁済区分==”DEFAULT（指定なし）”の場合]                                                                 
            } else
            {
                //[パラメータ.弁済区分!=”DEFAULT（指定なし）”の場合] 
                //（部店市場弁済別）取扱条件.弁済区分 != パラメータ.弁済区分の場合、 
                //  次のオブジェクトへ処理を移行する。(continue) 
                if (l_branchMarketRepayDealtCondRow.getRepaymentDivIsSet() &&
                    !l_branchMarketRepayDealtCondRow.getRepaymentDiv().equals(l_strRepaymentType))
                {
                    continue;
                }

            }
            //[パラメータ.市場コード一覧!=nullの場合] 
            //    (部店市場弁済別)取扱条件.市場コードがパラメータ.市場コード一覧のいずれにも該当しない場合、 
            //    次のオブジェクトへ処理を移行する。(continue)
            if (l_marketCodeList != null)
            {
                l_intLenMarkets = l_marketCodeList.length;

                for (int j = 0; j < l_intLenMarkets; j++)
                {
                    if (l_branchMarketRepayDealtCond.getMarketCode().equals(l_marketCodeList[j]))
                    {
                        l_blnHasSameMarketCode = true;
                        break;
                    }
                }

                if (!l_blnHasSameMarketCode)
                {
                    continue;
                }
            }
            //４－３）上記チェックを通過したオブジェクトについて、以下の処理を実施する。 
            //    ①@信用取引弁済オブジェクトを生成。 
            //    ②当該レコードの弁済区分、弁済期限値を生成したオブジェクトの同プロパティにセット。 
            //    ③信用取引弁済オブジェクトをArrayListに追加。 
            //    ※ArrayListに追加する信用取引弁済オブジェクトは、弁済区分と弁済期限値の組み合わせが 
            //    重複しないオブジェクトのみをセットすること。

            WEB3MarginRepaymentUnit l_repaymentUnit = new WEB3MarginRepaymentUnit();
            l_repaymentUnit.repaymentDiv = l_branchMarketRepayDealtCondRow.getRepaymentDiv();
            l_repaymentUnit.repaymentTimeLimit = "" + l_branchMarketRepayDealtCondRow.getRepaymentNum();

            int l_intLenRepaymentUnits = l_lisMarginRepaymentUnits.size();

            if (l_intLenRepaymentUnits == 0)
            {

                l_lisMarginRepaymentUnits.add(l_repaymentUnit);
            }

            for (int k = 0; k < l_intLenRepaymentUnits; k++)
            {

                WEB3MarginRepaymentUnit l_exsitedRepaymentUnit =
                    (WEB3MarginRepaymentUnit) l_lisMarginRepaymentUnits.get(k);
                if (l_repaymentUnit.repaymentDiv.equals(l_exsitedRepaymentUnit.repaymentDiv) &&
                    l_repaymentUnit.repaymentTimeLimit.equals(l_exsitedRepaymentUnit.repaymentTimeLimit))
                {
                    break;
                }

                if (k == l_intLenRepaymentUnits - 1)
                {
                    l_lisMarginRepaymentUnits.add(l_repaymentUnit);
                }
            }

        }

        //弁済区分(昇順）、弁済期限値(昇順)にソート
        Collections.sort(l_lisMarginRepaymentUnits, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                WEB3MarginRepaymentUnit first = (WEB3MarginRepaymentUnit) o1;
                WEB3MarginRepaymentUnit second = (WEB3MarginRepaymentUnit) o2;
                int result;

                if (!first.repaymentDiv.equals(second.repaymentDiv))
                {
                    result = Integer.parseInt(first.repaymentDiv) - Integer.parseInt(second.repaymentDiv);
                }
                else
                {
                    result = Integer.parseInt(first.repaymentTimeLimit) - Integer.parseInt(second.repaymentTimeLimit);
                }

                return result;
            }
        });

        //作成したArrayList.toArray()の結果を返却する。 
        WEB3MarginRepaymentUnit[] l_repaymentUnits = new WEB3MarginRepaymentUnit[l_lisMarginRepaymentUnits.size()];
        l_lisMarginRepaymentUnits.toArray(l_repaymentUnits);

        log.exiting(STR_METHOD_NAME);
        return l_repaymentUnits;
    }
    
    /**
     * (get新規建可能額)<BR>
     * 新規建可能額を取得する。<BR>
     * <BR>
     * 取引余力サービス.get信用新規建可能額(補助口座)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblOpenMarginPossiblePrice =
            l_tradingPowerService.getMarginTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblOpenMarginPossiblePrice;
    }

    /**
     * (get市場コード )<BR>
     * 市場コードを取得する。 <BR>
     * <BR>
     * １）　@パラメータ.市場コード == nullまたは <BR>
     * 　@　@パラメータ.銘柄コード == nullの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@パラメータ.市場コード != null <BR>
     * 　@　@かつ　@パラメータ.銘柄コード != null <BR>
     * 　@　@かつ　@パラメータ.市場コード != "99：優先市場"の場合、<BR>
     * 　@　@パラメータ.市場コードを返却する。<BR>
     * <BR>
     * ３）　@上記以外の場合<BR>
     * <BR>
     * 　@３－１）　@拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。<BR>
     * 　@　@　@　@　@　@[getProduct()に設定する引数] <BR>
     * 　@　@　@　@　@　@　@証券会社コード：　@パラメータ.証券会社コード <BR>
     * 　@　@　@　@　@　@　@銘柄コード　@　@　@：　@パラメータ.銘柄コード <BR>
     * <BR>
     * 　@３－２）　@株式銘柄.get優先市場()をコールし、市場を取得する。<BR>
     * <BR>
     * 　@３－３）　@３－２）で取得した市場がnullの場合、例外をthrowする。 <BR>
     * 　@　@　@　@　@　@以外、市場.getMarketCode()の戻り値を返却する。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:    BUSINESS_ERROR_02702<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * 銘柄コード
     * @@param l_strMarketCode - 市場コード<BR>
     * 市場コード
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * 証券会社コード
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getMarketCode(
        String l_strProductCode,
        String l_strMarketCode,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarketCode(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@パラメータ.市場コード == nullまたは
        //　@パラメータ.銘柄コード == nullの場合、nullを返却する。
        if (l_strProductCode == null || l_strMarketCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@パラメータ.市場コード != null
        //　@かつ　@パラメータ.銘柄コード != null
        //　@かつ　@パラメータ.市場コード != "99：優先市場"の場合、パラメータ.市場コードを返却する。
        if (l_strMarketCode != null && l_strProductCode != null
            && !WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }

        //　@３－１）　@拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。
        //　@　@　@[getProduct()に設定する引数]
        //　@　@　@　@証券会社コード：　@パラメータ.証券会社コード
        //　@　@　@　@銘柄コード　@　@　@：　@パラメータ.銘柄コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityProductManager l_equityProductManager
            = (WEB3EquityProductManager)l_tradingModule.getProductManager();
        Institution l_institution = null;
        WEB3EquityProduct l_eqTypeProduct = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_eqTypeProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(
                    l_institution,
                    l_strProductCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //３－２）　@株式銘柄.get優先市場()をコールし、市場を取得する。
        Market l_market = l_eqTypeProduct.getPrimaryMarket();

        //３－３）　@３－２）で取得した市場がnullの場合、例外をthrowする。
        //　@　@　@以外、市場.getMarketCode()の戻り値を返却する。
        if (l_market == null)
        {
            log.debug("優先市場が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02702,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "優先市場が未指定です。");
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_market.getMarketCode();
        }
    }

}
@
