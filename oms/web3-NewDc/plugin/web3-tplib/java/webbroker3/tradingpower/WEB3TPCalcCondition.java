head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCalcCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算条件クラス(WEB3TPCalcCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 nakazato(ACT) 新規作成
                   2006/09/12 徐宏偉 (中訊) モデルNo.018 、No.019
                   2006/09/25 車進　@ (中訊) モデルNo.050
                   2006/09/25 車進　@ (中訊) モデルNo.068
                   2006/11/13 徐大方 (中訊) モデルNo.074-076
                   2007/01/30 謝旋   (中訊) 仕様変更 計算式書（No.006 - 007）
                   2007/03/19 宮本 篤東 (SCS) モデルNo.099
Revision History : 2007/07/25 孟亜南(中訊) モデルNo.133
Revision History : 2007/07/31 孟亜南(中訊) モデルNo.143
Revision History : 2007/07/25 孟亜南(中訊) モデルNo.170
Revision History : 2007/09/19 崔遠鵬(中訊) モデルNo.173
Revision History : 2007/09/28 トウ鋒鋼（中訊）モデルNo.189
Revision History : 2007/09/28 趙林鵬（中訊）実装の問題No.003
Revision History : 2007/10/12 金傑（中訊）モデルNo.194、モデルNo.209
Revision History : 2007/10/16 孟亞南（中訊）モデルNo.204、No.210
Revision History : 2007/10/30 趙林鵬（中訊）共通ＤＢレイアウト562
Revision History : 2008/01/22 孟亞南(中訊) モデルNo.237、モデルNo.239、モデルNo.249
Revision History : 2008/02/01 崔遠鵬(中訊) モデルNo.254-256、モデルNo.260
Revision History : 2008/04/01 崔遠鵬(中訊) モデルNo.271 272
Revision History : 2008/10/20 張少傑(中訊) モデルNo.324
Revision History : 2009/09/10 孟亞南（中訊）モデルNo391
Revesion History : 2010/02/22 武波 (中訊) 仕様変更モデルNo.456
*/
package webbroker3.tradingpower;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteData;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteDataSupplierService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3SalesofficeTpcheckDivDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.common.define.WEB3TheDayTransferDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.FeqLastClosingPriceRow;
import webbroker3.gentrade.data.LastClosingPriceDao;
import webbroker3.gentrade.data.LastClosingPriceRow;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.data.QuoteClosingPriceDao;
import webbroker3.gentrade.data.QuoteClosingPriceRow;
import webbroker3.gentrade.data.SecurityCashoutRestraintDao;
import webbroker3.gentrade.data.SecurityCashoutRestraintRow;
import webbroker3.gentrade.data.SecurityShortageAccountDao;
import webbroker3.gentrade.data.SecurityShortageAccountRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.define.WEB3TPLegalMarginDepositRateDef;
import webbroker3.tradingpower.define.WEB3TPLegalMinMarginDepositDef;
import webbroker3.tradingpower.define.WEB3TPMarginOpenApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPMutualFundBuyApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPProcessManagementIdDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.define.WEB3TPTradingStopDivDef;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceCallback;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceDBQuoteCallback;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceQuoteCallback;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceStandardCallback;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （余力計算条件）
 * 
 * 余力計算条件を格納するデータセットクラス。
 */
public class WEB3TPCalcCondition
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPCalcCondition.class);

    /**
     * 「引出可能現金チェック方法@-信用新規建時」のプリファ@レンス名。
     */
    public final static String MARGINOPEN_ACTPAY_CHECK = "marginopen.actpay.check";

    
    /**
     * 「追証余力チェック方法@-現物株式時」のプリファ@レンス名。
     */
    public final static String EQUITYBUY_ADDDEPOSIT_CHECK = "equitybuy.adddeposit.check";

    /**
     * 「追証余力チェック方法@-信用新規建時」のプリファ@レンス名。
     */
    public final static String MARGINOPEN_ADDDEPOSIT_CHECK = "marginopen.adddeposit.check";

    /**
     * 「追証余力チェック方法@-信用現引時」のプリファ@レンス名。
     */
    public final static String MARGINSWAPLONG_ADDDEPOSIT_CHECK = "marginswaplong.adddeposit.check";

    /**
     * 「追証余力チェック方法@-その他商品買付時」のプリファ@レンス名。
     */
    public final static String OTHERBUY_ADDDEPOSIT_CHECK = "otherbuy.adddeposit.check";

    
    /**
     * 「信用新規建可能額適用日」のプリファ@レンス名。
     */
    public final static String MARGINOPEN_APPLY_DATE = "marginopen.apply.date";


    /**
     * 「当日建玉代金計上開始日」のプリファ@レンス名。
     */
    public final static String CONTRACTAMOUNT_APPLY_DATE = "contractamount.apply.date";

    
    /**
     * 「二階建チェック方法@-現物株式時」のプリファ@レンス名。
     */
    public final static String EQUITYBUY_DOUBLEPOSITION_CHECK = "equitybuy.doubleposition.check";

    /**
     * 「二階建チェック方法@-信用新規買建時」のプリファ@レンス名。
     */
    public final static String MARGINOPENLONG_DOUBLEPOSITION_CHECK = "marginopenlong.doubleposition.check";

    /**
     * 「二階建チェック方法@-信用現引時」のプリファ@レンス名。
     */
    public final static String MARGINSWAPLONG_DOUBLEPOSITION_CHECK = "marginswaplong.doubleposition.check";

    /**
     * 「二階建チェック方法@-その他商品買付時」のプリファ@レンス名。
     */
    public final static String OTHERBUY_DOUBLEPOSITION_CHECK = "otherbuy.doubleposition.check";

    /**
     * 「二階建チェック方法@-(保護⇒代用)証券振替時」のプリファ@レンス名。
     */
    public final static String MARGINSUBSECURITY_DOUBLEPOSITION_CHECK = "marginsubsecurity.doubleposition.check";
    
    /**
     * 「取引余力チェック方法@-現物株式買付注文」のプリファ@レンス名。
     */
    public final static String EQUITYBUY_TRADINGPOWER_CHECK = "equitybuy.tradingpower.check";

    /**
     * 「取引余力チェック方法@-信用現引注文時」のプリファ@レンス名。
     */
    public final static String MARGINSWAPLONG_TRADINGPOWER_CHECK = "marginswaplong.tradingpower.check";
    
    /**
     * 「サービス利用拘束」のプリファ@レンス名。
     */
    public final static String SERVICE_CHARGE_RESTRAINT = "service.charge.restraint";      

    /**
     * 「未約定売付注文単価設定区分」のプリファ@レンス名。
     */
    public final static String SELLORDER_PRICE_DIV = "sellorder.price.div";      
    
    /**
     * 「投資信託前受拘束」のプリファ@レンス名。
     */
    public final static String MUTUAL_FUND_ADVANCE_RESTRAINT = "mf.advance.restraint";      
    
    /**
     * 「投資信託買付可能額適用日」のプリファ@レンス名。
     */
    public final static String MFBUY_APPLY_DATE = "mfbuy.apply.date";  
    
    /**
     * 「会社部店別余力計算条件」のプリファ@レンス名。
     */
    public final static String ACTUALRECEIPT_MARGINCALLPOWER_CHECK = "actualreceipt.margincallpower.check";
    
    /**
     * 「IPO購入申込余力チェック」のプリファ@レンス名。
     */
    public final static String IPO_OFFER_TRADINGPOWER_CHECK = "ipo.offer.tradingpower.check";
    
    /**
     * 「差金決済相当外買付代金非考慮の差金決済チェック」のプリファ@レンス名。
     */
    public final static String EXCLUDE_EXCEPT_SETTLEMENT_BUY_AMOUNT_CHECK =
        "exclude.except.settlement.buy.amount.check";
    
    /**
     * 「追証解消金額項目追加区分」のプリファ@レンス名。
     */
    public final static String ADDDEPOSIT_DISSOLVE_DIV = "adddeposit.dissolve.div";
    
    /**
     * 「保証金リアル振替実施区分」のプリファ@レンス名。
     */ 
    public final static String DEPOSIT_REAL_TRANSFER_ENFORCE_DIV = "deposit.real.transfer.enforce.div";
    
    /**
     * 「建玉諸経費(現引現渡)の未受渡決済損計上区分」のプリファ@レンス名。
     */
    public final static String EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV =
        "eqtype.swap.margin.cost.undelivered.contract.loss.div";
    
    /**
     * 「第一水準追証経過日数（設定2）※法@定」のプリファ@レンス名。
     */
    public final static String FIRST_MARGIN_PASS_DAY2 = "first.margin.pass.day2";
    
    /**
     * 「第一水準追証経過日数（設定1）※社内規定」のプリファ@レンス名。
     */
    public final static String FIRST_MARGIN_PASS_DAY1 = "first.margin.pass.day1";

    /**
     * （システムタイプスタンプ）
     */
    private Timestamp systemTimeStamp;

    /**
     * （営業日[T-2..+6]）
     */
    private Date[] bizDate;
    
    /**
     * （取引停止区分）
     */
    private boolean tradingStop;

    /**
     * （信用新規建余力区分）
     */
    private boolean marginOpenPositionStop;

    /**
     * （出金余力区分）
     */
    private boolean paymentStop;

    /**
     * （その他商品買付余力区分）
     */
    private boolean otherTradingStop;

    /**
     * (追証未入金区分)
     */
    private boolean additionalDepositDiv;

    /**
     * （翌日注文受付開始区分<現物株式>）<BR>
     * <BR>
     * 翌日注文受付時間帯の場合、true。以外の場合、false。<BR>
     * <BR>
     */
    private boolean equityNextDayOrderStartDiv;

    /**
     * （出来終了区分<現物株式>）
     */
    private boolean equityExecutionDiv;

    /**
     * （出来終了区分<オプション>）
     */
    private boolean optionExecutionDiv;

    /**
     * （預り証券評価制区分）
     */
    private boolean assetEvalDiv;

    /**
     * （預り証券評価制<現物株式評価>）
     */
    private boolean equityEvalDiv;

    /**
     * （預り証券評価制<投信評価>）
     */
    private boolean fundEvalDiv;

    /**
     * （預り証券評価制<債券評価>）
     */
    private boolean bondEvalDiv;

    /**
     * （預り証券評価制<GP評価>）
     */
    private boolean gpEvalDiv;

    /**
     * （預り証券評価限度額）
     */
    private double maxAssessment;

    /**
     * （特別立替金実績）
     */
    private double specialDebitAmount;

    /**
     * （総建玉代金上限値）
     * 
     * 個人顧客に対する、会社・部店で取引できる建代金の総建玉上限値。
     */
    private double maxContPrice;

    /**
     * （保証金率）
     */
    private int marginDepositRate;

    /**
     * （現金保証金率）
     */
    private int cashMarginDepositRate;

    /**
     * （保証金維持率）
     * 建玉を維持する(追証が発生しない)為に必要な保証金の割合
     */
    private int marginMaintenanceRate;

    /**
     * （最低必要保証金）
     */
    private double minMarginDeposit;

    /**
     * （法@定保証金率）
     */
    private int legalMarginDepositRate;


    /**
     * （法@定最低必要保証金）
     */
    private double legalMinMarginDeposit;

    /**
     * （余力計算代用掛目）
     */
    private int substituteRate;

    /**
     * （余力計算基準日<株式買付/信用現引>）
     * 
     * 株式買付／信用現引の余力計算基準日
     */
    private int equityBasePoint;

    /**
     * （余力計算基準日<信用新規建>）
     * 
     * 信用新規建の余力計算基準日
     */
    private int marginBasePoint;

    /**
     * （余力計算基準日<投信>）
     * 
     * 投信の余力計算基準日
     */
    private int fundBasePoint;

    /**
     * （余力計算基準日<出金>）
     * 
     * 出金の余力計算基準日
     */
    private int paymentBasePoint;

    /**
     * （余力計算基準日<オプション新規建>）
     * 
     * オプション新規買建の余力計算基準日
     */
    private int optionBasePoint;

    /**
     * （余力計算基準日<その他買付>）
     * 
     * その他商品買付の余力計算基準日
     */
    private int otherBasePoint;

    /**
     * （余力計算基準日<ミニ株>）
     * 
     * ミニ株の余力計算基準日
     */
    private int mstkBasePoint;

    /**
     * （余力計算基準日<累投>）
     * 
     * 累投の余力計算基準日
     */
    private int ruitoBasePoint;
    
    /**
     * （余力計算基準日<IPO>）
     * 
     * IPOの余力計算基準日
     */
    private int ipoBasePoint;
    
    /**
     * （余力計算基準日<中国株式>）
     * 
     * 中国株式の余力計算基準日
     */
    private int feqBasePoint;

    /**
     * （発注日<株式／信用>）
     * 
     * 株式／信用の発注日
     */
    private int equityBizDate;

    /**
     * (権利落ち日)
     * 
     * 余力計算中保持すべき権利落ち日Map
     */
    private Map rightsOffDates;

    /**
     * (当日終値)
     * 
     * 余力計算中保持すべき当日終値Map
     */
    private Map closingPrices;

    /**
     * (時価<株式>)
     * 
     * 余力計算中保持すべき時価<株式>Map
     */
    private Map eqtypeQuotes;

    /**
     * (評価単価Callback)
     * 
     * 評価単価Callback(標準と時価でオブジェクトが異なる)
     */
    private WEB3TPUnitPriceCallback unitPriceCallback;

    /**
     * (会社部店別余力計算条件)
     * 
     * 会社部店毎の余力計算条件を格納するMap
     */
    private Map instBranCalcCondition;
    
    /**
     * (部店タイプ)
     */
    private BranchTypeEnum branchType;
    
    /**
     * (証券担保ローン区分) <BR>
     * <BR>
     * 証券担保ローン実施顧客の場合、trueをセット。以外の場合、falseをセット<BR>
     */
    private boolean securedLoanSecAccOpenDiv;
    
    /**
     * (預り金担保出金停止区分)<BR> 
     *<BR>
     * 証券担保ローン実施顧客かつ預り金担保全額出金停止の場合、<BR>
     * trueをセット。以外の場合、falseをセット<BR> 
     */
    private boolean cashDepositStopDiv;
    
    /**
     * (オリックス_担保ローン口座開設区分)<BR>
     * <BR>
     * true： 担保ローン口座開設済顧客かつ担保ローン出金拘束金テーブルにレコードが存在する。<BR>
     * false： 担保ローン口座未開設顧客または担保ローン出金拘束金テーブルにレコードが存在しない。<BR>
     */
    private boolean orixSecuredLoanAccOpenDiv;

    /**
     * (オリックス_担保ローン出金可能額)<BR>
     * <BR>
     */
    private String orixSecuredLoanPaymentTradingPower;

    /**
     * オリックス_担保ローン金額ロックフラグ<BR>
     * <BR>
     */
    private String orixSecuredLoanLockFlag;

    /**
     * (外株終値)<BR>
     * 外株終値<BR>
     */
    private Map feqLastClosingPrice;

    /**
     * (PTS出来終了区分)<BR>
     * <BR>
     * PTS出来終了の場合、true。以外の場合、false。<BR>
     */
    private boolean ptsOrderExecutionEndType;

    /**
     * (外株日計り取引採用)<BR>
     * 外株日計り取引採用<BR>
     */
    private boolean feqDayTradeAdoption;

    /**
     * （余力計算条件）
     * 
     * コンストラクタ
     * @@roseuid 40BA93580399
     */
    public WEB3TPCalcCondition()
    {
        //フィールドを初期化する。
        this.systemTimeStamp = null;
        this.bizDate = new Date[9];
        this.tradingStop = false;
        this.marginOpenPositionStop = false;
        this.paymentStop = false;
        this.otherTradingStop = false;
        this.equityExecutionDiv = false;
        this.optionExecutionDiv = false;
        this.assetEvalDiv = false;
        this.equityEvalDiv = false;
        this.fundEvalDiv = false;
        this.bondEvalDiv = false;
        this.gpEvalDiv = false;
        this.maxAssessment = 0.0;
        this.specialDebitAmount = 0.0;
        this.maxContPrice = 0.0;
        this.marginDepositRate = 0;
        this.cashMarginDepositRate = 0;
        this.marginMaintenanceRate = 0;
        this.minMarginDeposit = 0.0;
        this.legalMinMarginDeposit = 0.0;
        this.substituteRate = 0;
        this.equityBasePoint = 0;
        this.marginBasePoint = 0;
        this.fundBasePoint = 0;
        this.paymentBasePoint = 0;
        this.optionBasePoint = 0;
        this.otherBasePoint = 0;
        this.rightsOffDates = new HashMap();
        this.closingPrices = new HashMap();
        this.eqtypeQuotes = new HashMap();
        this.instBranCalcCondition = new HashMap();
        this.branchType = null;
        this.feqLastClosingPrice = new HashMap();
    }

    /**
     * （create余力計算条件<標準>）<BR>
     * (staticメソッド)
     * 
     * 余力計算条件オブジェクトを生成し、評価単価<標準>Callbackをセットする。
     * シーケンス図「(余力計算条件)create余力計算条件<標準>」参照     
     * @@param l_subAccount - (補助口座)
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     */
    public static WEB3TPCalcCondition createCalcConditionStandard(WEB3GentradeSubAccount l_subAccount)
    {
        WEB3TPCalcCondition l_calcCondition = createCalcCondition(l_subAccount);
        WEB3TPUnitPriceCallback l_unitPriceCallback = new WEB3TPUnitPriceStandardCallback(l_calcCondition);
        l_calcCondition.setUnitPriceCallback(l_unitPriceCallback);
        return l_calcCondition;
    }

    /**
     * （create余力計算条件<時価>）<BR>
     * (staticメソッド)
     * 
     * 余力計算条件オブジェクトを生成し、評価単価<時価>Callbackをセットする。
     * シーケンス図「(余力計算条件)create余力計算条件<時価>」参照     
     * @@param l_subAccount - (補助口座)
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     */
    public static WEB3TPCalcCondition createCalcConditionQuote(WEB3GentradeSubAccount l_subAccount)
    {
        WEB3TPCalcCondition l_calcCondition = createCalcCondition(l_subAccount);
        WEB3TPUnitPriceCallback l_unitPriceCallback = new WEB3TPUnitPriceQuoteCallback(l_calcCondition);
        l_calcCondition.setUnitPriceCallback(l_unitPriceCallback);
        return l_calcCondition;
    }

    /**
     * （create余力計算条件）<BR>
     * (staticメソッド)
     * 
     * 余力計算条件オブジェクトを生成し、プロパティをセットする。
     * シーケンス図「(余力計算条件)create余力計算条件」参照     
     * @@param l_subAccount - (補助口座)
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     */
    private static WEB3TPCalcCondition createCalcCondition(WEB3GentradeSubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "WEB3TPCalcCondition.createCalcCondition()";

        //1.1.余力計算条件のインスタンスを生成する
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();

        //1.2.顧客を取得
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //1.3.証券会社を取得
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        //1.4.部店を取得
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

        /*
         * 会社部店別余力計算条件をセット
         */
        try
        {
            //1.5.部店ID
            long l_lngBranchId = l_branch.getBranchId();

            //1.6.部店用プリファ@レンステーブルを検索
            List l_rowList = BranchPreferencesDao.findRowsByBranchId(l_lngBranchId);

            if(l_rowList != null)
            {
                for(Iterator iter = l_rowList.iterator(); iter.hasNext();)
                {
                    BranchPreferencesRow l_row = (BranchPreferencesRow) iter.next();

                    //1.6.1.検索結果行を余力計算条件Mapに追加
                    l_calcCondition.addInstBranCalcCondition(l_row.getName(), l_row.getValue());
                }
            }
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //1.7.システムスタンプ、営業日をセット
        l_calcCondition.calcBizDate();
        
        //1.8.余力計算基準日をセット
        l_calcCondition.calcBasePoint(l_subAccount);

        //1.9.部店タイプをセット
        //1.10.
        l_calcCondition.setBranchType(l_branch.getBranchType());
        

        /*
         * 翌日注文受付開始区分<現物株式>をセットする
         */
        try
        {
            //プロセス管理テーブルを検索
            ProcessManagementRow l_processRow = ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    WEB3TPProcessManagementIdDef.EQUITY_NEXTDAYORDER_STARTTIME,
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode());

            if(l_processRow != null)
            {
                l_calcCondition.setEquityNextDayOrderStartDiv(true);
            }
            else
            {
                l_calcCondition.setEquityNextDayOrderStartDiv(false);
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        /*
         * 出来終了区分をセット
         */
        try
        {
            //1.11.出来終了区分<現物株式>をセット       
            l_calcCondition.setEquityExecutionDiv(
                l_institution.isOrderExecEnd(ProductTypeEnum.EQUITY, WEB3FuturesOptionDivDef.DEFAULT));
            //1.12.出来終了区分<オプション>をセット       
            l_calcCondition.setOptionExecutionDiv(
                l_institution.isOrderExecEnd(ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.OPTION));
        }
        catch (WEB3SystemLayerException e)
        {
            throw new WEB3BaseRuntimeException(
                e.getErrorInfo(),
                e.getErrorMethod(),
                e.getErrorMessage(),
                e.getException());
        }

        /*
         * PTS出来終了区分をセットする
         */
        try
        {
            //プロセス管理テーブルを検索
            ProcessManagementRow l_processRow = ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    WEB3TPProcessManagementIdDef.PTS_ORDER_EXECUTION_END_TYPE,
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode());

            if(l_processRow != null)
            {
                l_calcCondition.setPtsOrderExecutionEndType(true);
            }
            else
            {
                l_calcCondition.setPtsOrderExecutionEndType(false);
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        /*
         * 1.15.余力計算条件Rowを取得する
         */
        TradingpowerCalcConditionRow l_calcConditionRow = null;
        try
        {
            l_calcConditionRow =
                (TradingpowerCalcConditionRow)TradingpowerCalcConditionDao.findRowByAccountId(
                    l_subAccount.getAccountId());
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //1.16.特別立替金実績をセット
        l_calcCondition.setSpecialDebitAmount(l_calcConditionRow.getSpecialDebitAmount());
        //1.17.取引停止区分をセット
        if (l_calcConditionRow.getTradingStopIsSet() == true
            && l_calcConditionRow.getTradingStop().equals(WEB3TPTradingStopDivDef.TRADING_STOP) == true
            || WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_STOP.equals(
                l_calcConditionRow.getAdditionalDepositStop()))
        {
            l_calcCondition.setTradingStop(true);
        }
        else
        {
            l_calcCondition.setTradingStop(false);
        }

        //1.18.信用新規建余力区分をセット
        if (l_calcConditionRow.getMarginOpenPositionStopIsSet() == true
            && l_calcConditionRow.getMarginOpenPositionStop().equals(WEB3TPTradingStopDivDef.TRADING_OK) == true)
        {
            l_calcCondition.setMarginOpenPositionStop(false);
        }
        else
        {
            l_calcCondition.setMarginOpenPositionStop(true);
        }

        //1.19.出金余力区分をセット
        if (l_calcConditionRow.getPaymentStopIsSet() == true
            && l_calcConditionRow.getPaymentStop().equals(WEB3TPTradingStopDivDef.TRADING_OK) == true)
        {
            l_calcCondition.setPaymentStop(false);
        }
        else
        {
            l_calcCondition.setPaymentStop(true);
        }

        //1.20.その他商品買付余力区分をセット
        if (l_calcConditionRow.getOtherTradingStopIsSet() == true
            && l_calcConditionRow.getOtherTradingStop().equals(WEB3TPTradingStopDivDef.TRADING_OK) == true)
        {
            l_calcCondition.setOtherTradingStop(false);
        }
        else
        {
            l_calcCondition.setOtherTradingStop(true);
        }

        //set追証未入金区分
        if (l_calcConditionRow.getAdditionalDepositStop() != null
            && WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_STOP.equals(
            l_calcConditionRow.getAdditionalDepositStop()))
        {
            l_calcCondition.setAdditionalDepositDiv(true);
        }
        else
        {
            l_calcCondition.setAdditionalDepositDiv(false);
        }

        //1.21.信用口座区分を取得する。弁済区分：指定なし
        boolean l_accoutFlg = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //1.22.
        //現物顧客の時
        if (l_accoutFlg == false)
        {
            //1.23.預り証券評価制区分を取得
            boolean l_blnAssetEvaluation;
            try
            {
                l_blnAssetEvaluation = l_mainAccount.isAssetEvaluation();
            }
            catch (WEB3SystemLayerException e)
            {
                throw new WEB3BaseRuntimeException(
                    e.getErrorInfo(),
                    e.getErrorMethod(),
                    e.getErrorMessage(),
                    e.getException());
            }
            
            //預り証券評価制区分をセット
            l_calcCondition.setAssetEvalDiv(l_blnAssetEvaluation);

            //1.22.4.預り証券評価制区分== trueの場合
            if (l_calcCondition.isAssetEvalDiv() == true)
            {
                //1.22.4.1.預り証券評価制<現物株式評価>をセット
                l_calcCondition.setEquityEvalDiv(l_institution.isInstitutionStockEvaluation());
                //1.22.4.3.預り証券評価制<投信評価>をセット
                l_calcCondition.setFundEvalDiv(l_institution.isInstitutionFundEvaluation());
                //1.22.4.5.預り証券評価制<債券評価>をセット
                l_calcCondition.setBondEvalDiv(l_institution.isInstitutionBondEvaluation());
                //1.22.4.7.預り証券評価制<GP評価>をセット
                l_calcCondition.setGpEvalDiv(l_institution.isInstitutionGpEvaluation());
            }
            //預り証券評価制区分== falseの場合
            else
            {
                //預り証券評価制<現物株式評価>をセット
                l_calcCondition.setEquityEvalDiv(false);
                //預り証券評価制<投信評価>をセット
                l_calcCondition.setFundEvalDiv(false);
                //預り証券評価制<債券評価>をセット
                l_calcCondition.setBondEvalDiv(false);
                //預り証券評価制<GP評価>をセット
                l_calcCondition.setGpEvalDiv(false);
            }

            //証券会社のデータソースを取得する。
            InstitutionRow l_institutionRow = (InstitutionRow)l_institution.getDataSourceObject();
            //1.22.4.預り証券評価限度額をセット
            l_calcCondition.setMaxAssessment(l_institutionRow.getMaximumAssessment());
            
            //1.22.5.顧客オブジェクトより、顧客マスタの行オブジェクトを取得する。
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
            //1.22.6.set証券担保ローン区分(boolean)
            //証券担保ローン区分をセットする。 
            //[引数] 
            //boolean = ".証券担保ローン区分"(*) 
            //(*) 
            //　@[a.顧客Row.証券担保ローン区分 == 1：開設] 
            //　@　@".証券担保ローン区分" = true　@ 
            //　@[a.以外の場合] 
            //　@　@".証券担保ローン区分" = false　@ 
            //　@※顧客Row = 顧客.getDataSourceObject( )の戻り値 
            boolean l_blnSecuredLoanSecAccOpen = false;
            if (WEB3SecuredLoanSecAccOpenDivDef.OPEN.equals(
                l_mainAccountRow.getSecuredLoanSecAccOpenDiv()))
            {
                l_blnSecuredLoanSecAccOpen = true;
            }

            l_calcCondition.setSecuredLoanSecAccOpenDiv(l_blnSecuredLoanSecAccOpen);

            //1.22.7.is証券担保ローン区分()
            boolean l_blnSecuredLoanSecAccOpenDiv = l_calcCondition.isSecuredLoanSecAccOpenDiv();
            
            //1.22.8.(*)分岐フロー
            //証券担保ローン実施顧客の場合
            //(is証券担保ローン区分 == true)
            if (l_blnSecuredLoanSecAccOpenDiv)
            {
                //1.22.8.1.担保不足顧客データテーブルより、当該顧客のレコードを取得する。
                //[対象テーブル]
                // 担保不足顧客データテーブル
                //[検索条件]
                // 口座ID = 引数.補助口座.getAccountId()
                SecurityShortageAccountRow l_securityShortageAccountRow = null;
                try
                {
                    l_securityShortageAccountRow = 
                        (SecurityShortageAccountRow)SecurityShortageAccountDao.findRowByAccountId(
                            l_mainAccount.getAccountId());
                    
                }
                catch (DataException de)
                {
                    log.error(de.getMessage(), de);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        STR_METHOD_NAME,
                        de.getMessage(),
                        de);
                }
                
                //1.22.8.2.(*)分岐フロー
                //担保不足顧客データテーブルの行オブジェクトを取得できた場合
                if (l_securityShortageAccountRow != null)
                {
                    //1.22.8.2.1.set預り金担保出金停止区分(boolean)
                    //預り金担保出金停止区分をセットする。
                    //[引数]
                    //boolean = "出金停止区分"(*)
                    //(*)
                    // [a.担保不足顧客Row.出金停止区分=1：全額 の場合]
                    //　@　@"出金停止区分" = true
                    // [a.以外の場合]
                    //　@　@"出金停止区分" = false
                    boolean l_blnPaymentStop = false;
                    if (WEB3PaymentStopDivDef.FULL.equals(l_securityShortageAccountRow.getPaymentStopDiv()))
                    {
                        l_blnPaymentStop = true;
                    }
                    
                    l_calcCondition.setCashDepositStopDiv(l_blnPaymentStop);
                }
            }

            try
            {
                //is証券担保ローン口座開設
                boolean l_blnIsSecuredLoanAccountOpen = l_mainAccount.isSecuredLoanAccountOpen();

                if (l_blnIsSecuredLoanAccountOpen)
                {
                    //オリックス証券担保ローン口座開設済顧客の場合
                    //担保ローン出金拘束金テーブルより、当該顧客のレコードを取得する
                    SecurityCashoutRestraintRow l_securityCashoutRestraintRow = null;
                    try
                    {
                        l_securityCashoutRestraintRow =
                            (SecurityCashoutRestraintRow)SecurityCashoutRestraintDao.findRowByAccountId(
                                l_subAccount.getAccountId());
                    }
                    catch (DataException de)
                    {
                        log.error(de.getMessage(), de);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            STR_METHOD_NAME,
                            de.getMessage(),
                            de);
                    }

                    if(l_securityCashoutRestraintRow != null)
                    {
                        //setオリックス_担保ローン口座開設区分
                        l_calcCondition.setOrixSecuredLoanAccOpenDiv(true);

                        //setオリックス_担保ローン出金可能額
                        l_calcCondition.setOrixSecuredLoanPaymentTradingPower(
                            l_securityCashoutRestraintRow.getCashoutEnablieAmount() + "");

                        //setオリックス_担保ローン金額ロックフラグ
                        l_calcCondition.setOrixSecuredLoanLockFlag(
                            l_securityCashoutRestraintRow.getAmountLockFlg());
                    }
                    else
                    {
                        //setオリックス_担保ローン口座開設区分
                        l_calcCondition.setOrixSecuredLoanAccOpenDiv(false);

                        //setオリックス_担保ローン出金可能額
                        l_calcCondition.setOrixSecuredLoanPaymentTradingPower(null);

                        //setオリックス_担保ローン金額ロックフラグ
                        l_calcCondition.setOrixSecuredLoanLockFlag(null);
                    }
                }
                else
                {
                    //オリックス証券担保ローン口座未開設顧客の場合
                    //setオリックス_担保ローン口座開設区分
                    l_calcCondition.setOrixSecuredLoanAccOpenDiv(false);

                    //setオリックス_担保ローン出金可能額
                    l_calcCondition.setOrixSecuredLoanPaymentTradingPower(null);

                    //setオリックス_担保ローン金額ロックフラグ
                    l_calcCondition.setOrixSecuredLoanLockFlag(null);
                }
            }
            catch (WEB3SystemLayerException e)
            {
                throw new WEB3BaseRuntimeException(
                    e.getErrorInfo(),
                    e.getErrorMethod(),
                    e.getErrorMessage(),
                    e.getException());
            }
        }
        //信用顧客の時
        else
        {
            //顧客のデータソースを取得する。
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            //部店のデータソースを取得する。
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();

            //口座タイプを取得
            MainAccountTypeEnum l_accountType = l_mainAccountRow.getAccountType();

            //法@人アカウントの時
            if (l_accountType == MainAccountTypeEnum.CORPORATE_ACCOUNT)
            {
                //総建代金上限値をセット
                l_calcCondition.setMaxContPrice(l_branchRow.getMaxContPriceAllCorp());
            }
            //個人／共用アカウントの時
            else if (
                l_accountType == MainAccountTypeEnum.INDIVIDUAL_ACCOUNT
                    || l_accountType == MainAccountTypeEnum.JOINT_OWNERSHIP)
            {
                //総建代金上限値をセット
                l_calcCondition.setMaxContPrice(l_branchRow.getMaxContPriceAllInd());
            }

            //保証金率をセット
            l_calcCondition.setMarginDepositRate((int)l_branchRow.getMarginDepositRate());
            //現金保証金率をセット
            l_calcCondition.setCashMarginDepositRate((int)l_branchRow.getCashMarginDepositRate());
            //保証金維持率率をセット
            l_calcCondition.setMarginMentenanceRate((int)l_branchRow.getMarginMaintenanceRate());
            //最低必要保証金をセット
            l_calcCondition.setMinMarginDeposit(l_branchRow.getMinMarginDeposit());
            //法@定保証金率をセット
            l_calcCondition.setLegalMarginDepositRate(WEB3TPLegalMarginDepositRateDef.LEGAL_MARGIN_DEPOSIT_RATE);
            //法@定最低必要保証金をセット
            l_calcCondition.setLegalMinMarginDeposit(WEB3TPLegalMinMarginDepositDef.MIN_MARGIN_DEPOSIT);
            //余力計算代用掛目をセット
            l_calcCondition.setSubstituteRate((int)l_branchRow.getCalcSubstituteRate());
        }

        //set外株日計り取引採用
        boolean l_blnIsDayTradeAdoption = false;
        try
        {
            l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                WEB3TPCalcCondition.class.getName() + STR_METHOD_NAME, 
                l_ex.getErrorMessage(),
                l_ex);
        }
        l_calcCondition.setFeqDayTradeAdoption(l_blnIsDayTradeAdoption);
        //インスタンスを返却する
        return l_calcCondition;
    }

    /**
     * （getシステムタイムスタンプ）<BR>
     * 
     * this.システムタイムスタンプを返却する。<BR>
     * @@return TimeStamp
     */
    public Timestamp getSystemTimeStamp()
    {
        return this.systemTimeStamp;
    }

    /**
     * （setシステムタイプスタンプ）<BR>
     * 
     * パラメータ.システムタイムスタンプをthis.システムタイムスタンプにセットする。<BR>
     * @@param l_dblMaxContPrice
     */
    public void setSystemTimeStamp(Timestamp l_systemTimeStamp)
    {
        this.systemTimeStamp = l_systemTimeStamp;
    }

    /**
     * (get営業日)<BR> 
     * 
     * this.営業日[T-2..+6]を返却する。<BR>
     * @@return Date[]
     */
    protected Date[] getBizDate()
    {
        return this.bizDate;
    }

    /**
     * (get営業日)<BR>
     * 
     * 引数.指定日に対応する営業日を返却する。<BR>
     * パラメータチェック(-2..+6)の後、<BR>
     * this.営業日[T-2..+6][指定日]を返却する<BR>
     * @@param l_intSpecifiedPoint
     * @@return Date
     * @@roseuid 40F4E04E0310
     */
    public Date getBizDate(int l_intSpecifiedPoint)
    {
        //パラメータチェックを実施する。
        //パラメータの範囲(-2 <= パラメータ <= 6)
        if (-2 <= l_intSpecifiedPoint && l_intSpecifiedPoint <= 6)
        {
            return this.bizDate[l_intSpecifiedPoint + 2];
        }
        else
        {
            return null;
        }
    }

    /**
     * (set営業日[T-2..+6]) <BR>
     * 
     * パラメータ.営業日[T-2..+6]をthis.営業日[T-2..+6]にセットする。<BR>
     * @@param l_datBizDate
     * @@roseuid 40F4C58B0003
     */
    public void setBizDate(Date[] l_datBizDate)
    {
        this.bizDate  = l_datBizDate;
     }

    /**
     * （is取引停止区分）<BR>
     * 
     * this.取引停止区分を返却する。<BR>
     * @@return boolean
     */
    public boolean isTradingStop()
    {
        return this.tradingStop;
    }

    /**
     * （set取引停止区分）<BR>
     * 
     * パラメータ.取引停止区分をthis.取引停止区分にセットする。
     * <BR>
     * @@param l_blnTradingStop
     */
    public void setTradingStop(boolean l_blnTradingStop)
    {
        this.tradingStop = l_blnTradingStop;
    }

    /**
     * （is新規建余力区分）<BR>
     * 
     * this.新規建余力区分を返却する。<BR>
     * @@return boolean
     */
    public boolean isMarginOpenPositionStop()
    {
        return this.marginOpenPositionStop;
    }

    /**
     * （set信用新規建余力区分）<BR>
     * 
     * パラメータ.信用新規建余力区分をthis.信用新規建余力区分にセットする。
     * <BR>
     * @@param l_blnMarginStop
     */
    public void setMarginOpenPositionStop(boolean l_blnMarginStop)
    {
        this.marginOpenPositionStop = l_blnMarginStop;
    }

    /**
     * （is出金余力区分）<BR>
     * 
     * this.出金余力区分を返却する。<BR>
     * @@return boolean
     */
    public boolean isPaymentStop()
    {
        return this.paymentStop;
    }

    /**
     * （set出金余力区分）<BR>
     * 
     * パラメータ.出金余力区分をthis.出金余力区分にセットする。
     * <BR>
     * @@param l_blnPayStop
     */
    public void setPaymentStop(boolean l_blnPayStop)
    {
        this.paymentStop = l_blnPayStop;
    }

    /**
     * （isその他商品買付余力区分）<BR>
     * 
     * this.その他商品買付余力区分を返却する。<BR>
     * @@return boolean
     */
    public boolean isOtherTradingStop()
    {
        return this.otherTradingStop;
    }

    /**
     * （setその他商品買付余力区分）<BR>
     * 
     * パラメータ.その他商品買付余力区分をthis.その他商品買付余力区分にセットする。
     * <BR>
     * @@param l_blnOtherStop
     */
    public void setOtherTradingStop(boolean l_blnOtherStop)
    {
        this.otherTradingStop = l_blnOtherStop;
    }

    /**
     * (is追証未入金区分)  <BR>
     * <BR>
     * this.追証未入金区分を返却する。<BR>
     * @@return boolean
     */
    public boolean isAdditionalDepositDiv()
    {
        return this.additionalDepositDiv;
    }

    /**
     * (set追証未入金区分) <BR>
     * <BR>
     * パラメータ.追証未入金区分をthis.追証未入金区分にセットする。<BR>
     * @@param l_blnAdditionalDepositDiv
     */
    public void setAdditionalDepositDiv(boolean l_blnAdditionalDepositDiv)
    {
        this.additionalDepositDiv = l_blnAdditionalDepositDiv;
    }

    /**
     * （is翌日注文受付開始区分<現物株式>）<BR>
     * 
     * this.翌日注文受付開始区分<現物株式>を返却する。<BR>
     * @@return boolean
     */
    public boolean isEquityNextDayOrderStartDiv()
    {
        return this.equityNextDayOrderStartDiv;
    }

    /**
     * （set翌日注文受付開始区分<現物株式>）<BR>
     * 
     * パラメータ.翌日注文受付開始区分<現物株式>をthis.翌日注文受付開始区分<現物株式>にセットする。<BR>
     * @@param l_equityNextDayOrderStartDiv
     */
    public void setEquityNextDayOrderStartDiv(boolean l_equityNextDayOrderStartDiv)
    {
        this.equityNextDayOrderStartDiv = l_equityNextDayOrderStartDiv;
    }
    
    /**
     * （is出来終了区分<現物株式>）<BR>
     * 
     * this.出来終了区分<現物株式>を返却する。<BR>
     * @@return boolean
     */
    public boolean isEquityExecutionDiv()
    {
        return this.equityExecutionDiv;
    }

    /**
     * （set出来終了区分<現物株式>）<BR>
     * 
     * パラメータ.出来終了区分<現物株式>をthis.出来終了区分<現物株式>にセットする。<BR>
     * @@param l_equityExecutionDiv
     * @@roseuid 40F4C5A501C8
     */
    public void setEquityExecutionDiv(boolean l_equityExecutionDiv)
    {
        this.equityExecutionDiv = l_equityExecutionDiv;
    }

    /**
     * （is出来終了区分<オプション>）<BR>
     * 
     * this.出来終了区分<オプション>を返却する。<BR>
     * @@return boolean
     */
    public boolean isOptionExecutionDiv()
    {
        return this.optionExecutionDiv;
    }

    /**
     * （set出来終了区分<オプション>）<BR>
     * 
     * パラメータ.出来終了区分<オプション>をthis.出来終了区分<オプション>にセットする。
     * <BR>
     * @@param l_optionExecutionDiv
     * @@roseuid 40FCE431026D
     */
    public void setOptionExecutionDiv(boolean l_optionExecutionDiv)
    {
        this.optionExecutionDiv = l_optionExecutionDiv;
    }

    /**
     * （is預り証券評価制区分）<BR>
     * 
     *this.預り証券評価制区分を返却する。<BR>
     *
     * @@return boolean
     * @@roseuid 40B6D0C70185
     */
    public boolean isAssetEvalDiv()
    {
        return this.assetEvalDiv;
    }

    /**
     * （set預り証券評価制区分）<BR>
     * 
     * パラメータ.預り証券評価制区分をthis.預り証券評価制区分にセットする。<BR>
     * @@param l_assetEvalDiv
     * @@roseuid 40B6E6D40250
     */
    public void setAssetEvalDiv(boolean l_assetEvalDiv)
    {
        this.assetEvalDiv = l_assetEvalDiv;
    }

    /**
     * （is預り証券評価制<現物株式評価>）<BR>
     * 
     * this.預り証券評価制<現物株式評価>を返却する。<BR>
     * @@return boolean
     * @@roseuid 40F4C5B40051
     */
    public boolean isEquityEvalDiv()
    {
        return this.equityEvalDiv;
    }

    /**
     * （set預り証券評価制区分）<BR>
     * 
     * パラメータ.預り証券評価制<現物株式評価>をthis.預り証券評価制<現物株式評価>にセッ
     * トする。<BR>
     * @@param l_equityEvalDiv
     * @@roseuid 40F4C5CF0012
     */
    public void setEquityEvalDiv(boolean l_equityEvalDiv)
    {
        this.equityEvalDiv = l_equityEvalDiv;
    }

    /**
     * （is預り証券評価制<投信評価>）<BR>
     * 
     * this.預り証券評価制<投信評価>を返却する。<BR>
     * @@return boolean
     * @@roseuid 40F4C5D50022
     */
    public boolean isFundEvalDiv()
    {
        return this.fundEvalDiv;
    }

    /**
     * （set預り証券評価制<投信評価>）<BR>
     * 
     * パラメータ.預り証券評価制<投信評価>をthis.預り証券評価制<投信評価>にセットする。
     * <BR>
     * @@param l_fundEvalDiv
     * @@roseuid 40F4C5DC0003
     */
    public void setFundEvalDiv(boolean l_fundEvalDiv)
    {
        this.fundEvalDiv = l_fundEvalDiv;
    }

    /**
     * （is預り証券評価制<債券評価>）<BR>
     * 
     * this.預り証券評価制<債券評価>を返却する。<BR>
     * @@return boolean
     * @@roseuid 40F4C5EA0012
     */
    public boolean isBondEvalDiv()
    {
        return this.bondEvalDiv;
    }

    /**
     * （set預り証券評価制<債券評価>）<BR>
     * 
     * パラメータ.預り証券評価制<債券評価>をthis.預り証券評価制<債券評価>にセットする。
     * <BR>
     * @@param l_bondEvalDiv
     * @@roseuid 40F4C5F1012C
     */
    public void setBondEvalDiv(boolean l_bondEvalDiv)
    {
        this.bondEvalDiv = l_bondEvalDiv;
    }

    /**
     * （is預り証券評価制<GP評価>）<BR>
     * 
     * this.預り証券評価制<GP評価>を返却する。<BR>
     * @@return boolean
     * @@roseuid 40F4C6070051
     */
    public boolean isGpEvalDiv()
    {
        return this.gpEvalDiv;
    }

    /**
     * （set預り証券評価制<GP評価>）<BR>
     * 
     * パラメータ.預り証券評価制<GP評価>をthis.預り証券評価制<GP評価>にセットする。<BR>
     * @@param l_gpEvalDiv
     * @@roseuid 40F4C60E0003
     */
    public void setGpEvalDiv(boolean l_gpEvalDiv)
    {
        this.gpEvalDiv = l_gpEvalDiv;
    }

    /**
     * （get預り証券評価限度額）<BR>
     * 
     * this.預り証券評価限度額を返却する。<BR>
     * @@return double
     * @@roseuid 40F4C6150206
     */
    public double getMaxAssessment()
    {
        return this.maxAssessment;
    }

    /**
     * （set預り証券評価限度額）<BR>
     * 
     * パラメータ.預り証券評価限度額をthis.預り証券評価限度額にセットする。<BR>
     * @@param l_dblMaxAssessment
     * @@roseuid 40F4C626009F
     */
    public void setMaxAssessment(double l_dblMaxAssessment)
    {
        this.maxAssessment = l_dblMaxAssessment;
    }

    /**
     * （get特別立替金実績）<BR>
     * 
     * this.特別立替金実績を返却する。<BR>
     * @@return double
     * @@roseuid 40FF4C5E0191
     */
    public double getSpecialDebitAmount()
    {
        return this.specialDebitAmount;
    }

    /**
     * （set特別立替金実績）<BR>
     * 
     * パラメータ.特別立替金実績をthis.特別立替金実績にセットする。<BR>
     * @@param l_dblSpecialDebitAmount
     * @@roseuid 40FF4C690366
     */
    public void setSpecialDebitAmount(double l_dblSpecialDebitAmount)
    {
        this.specialDebitAmount = l_dblSpecialDebitAmount;
    }

    /**
     * （get総建代金上限値）<BR>
     * 
     * this.総建代金上限値を返却する。<BR>
     * @@return double
     * @@roseuid 40BF12EA0264
     */
    public double getMaxContPrice()
    {
        return this.maxContPrice;
    }

    /**
     * （set総建代金上限値）<BR>
     * 
     * パラメータ.総建代金上限値をthis.総建代金上限値にセットする。<BR>
     * @@param l_dblMaxContPrice
     * @@roseuid 40BF131100EC
     */
    public void setMaxContPrice(double l_dblMaxContPrice)
    {
        this.maxContPrice = l_dblMaxContPrice;
    }

    /**
     * （get保証金率）<BR>
     * 
     * this.保証金率を返却する。<BR>
     * @@return int
     * @@roseuid 40BAD8ED032C
     */
    public int getMarginDepositRate()
    {
        return this.marginDepositRate;
    }

    /**
     * （set保証金率）<BR>
     * 
     * パラメータ.保証金率をthis.保証金率にセットする。<BR>
     * @@param l_intMarginDepositRate
     * @@roseuid 40BAD8F702DE
     */
    public void setMarginDepositRate(int l_intMarginDepositRate)
    {
        this.marginDepositRate = l_intMarginDepositRate;
    }

    /**
     * （get現金保証金率）<BR>
     * 
     * this.現金保証金率を返却する。<BR>
     * @@return int
     * @@roseuid 40C528DC01C0
     */
    public int getCashMarginDepositRate()
    {
        return this.cashMarginDepositRate;
    }

    /**
     * （set現金保証金率）<BR>
     * 
     * パラメータ.現金保証金率をthis.現金保証金率にセットする。<BR>
     * @@param l_intCashMarginDepositRate
     * @@roseuid 40C528E40385
     */
    public void setCashMarginDepositRate(int l_intCashMarginDepositRate)
    {
        this.cashMarginDepositRate = l_intCashMarginDepositRate;
    }

    /**
     * （get保証金維持率）<BR>
     * 
     * this.保証金維持率を返却する。<BR>
     * @@return int
     * @@roseuid 40C515D50394
     */
    public int getMarginMentenanceRate()
    {
        return this.marginMaintenanceRate;
    }

    /**
     * （set保証金維持率）<BR>
     * 
     * パラメータ.保証金維持率をthis.保証金維持率にセットする。<BR>
     * @@param l_intMarginMentenanceRate
     * @@roseuid 40C515DE0162
     */
    public void setMarginMentenanceRate(int l_intMarginMentenanceRate)
    {
        this.marginMaintenanceRate = l_intMarginMentenanceRate;
    }

    /**
     * （get最低必要保証金）<BR>
     * 
     * this.最低必要保証金を返却する。<BR>
     * @@return double
     * @@roseuid 40BAD4C00399
     */
    public double getMinMarginDeposit()
    {
        return this.minMarginDeposit;
    }

    /**
     * （set最低必要保証金）<BR>
     * 
     * パラメータ.最低必要保証金をthis.最低必要保証金にセットする。<BR>
     * @@param l_dblMinMarginDeposit
     * @@roseuid 40BAD4CB034B
     */
    public void setMinMarginDeposit(double l_dblMinMarginDeposit)
    {
        this.minMarginDeposit = l_dblMinMarginDeposit;
    }

    /**
     * （get法@定保証金率）<BR>
     * 
     * this.法@定保証金率を返却する。<BR>
     * @@return int
     */
    public int getLegalMarginDepositRate()
    {
        return this.legalMarginDepositRate;
    }

    /**
     * （set法@定保証金率）<BR>
     * 
     * パラメータ.法@定保証金率をthis.法@定保証金率にセットする。<BR>
     * @@param l_intLegalMarginDepositRate
     */
    public void setLegalMarginDepositRate(int l_intLegalMarginDepositRate)
    {
        this.legalMarginDepositRate = l_intLegalMarginDepositRate;
    }

    /**
     * （get法@定最低必要保証金）<BR>
     * 
     * this.法@定最低必要保証金を返却する。<BR>
     * @@return double
     * @@roseuid 40BAD97F02DE
     */
    public double getLegalMinMarginDeposit()
    {
        return this.legalMinMarginDeposit;
    }

    /**
     * （set法@定最低必要保証金）<BR>
     * 
     * パラメータ.法@定必要保証金をthis.法@定必要保証金にセットする。<BR>
     * @@param l_dblLegalMinMarginDeposit
     * @@roseuid 40C7FC640393
     */
    public void setLegalMinMarginDeposit(double l_dblLegalMinMarginDeposit)
    {
        this.legalMinMarginDeposit = l_dblLegalMinMarginDeposit;
    }

    /**
     * （get余力計算代用掛目）<BR>
     * 
     * this.余力計算代用掛目を返却する。<BR>
     * @@return int
     * @@roseuid 40BAE01C0167
     */
    public int getSubstituteRate()
    {
        return this.substituteRate;
    }

    /**
     * （set余力計算代用掛目）<BR>
     * 
     * パラメータ.余力計算代用掛目をthis.余力計算代用掛目にセットする。<BR>
     * @@param l_intSubstituteRate
     * @@roseuid 40BAE0160186
     */
    public void setSubstituteRate(int l_intSubstituteRate)
    {
        this.substituteRate = l_intSubstituteRate;
    }

    /**
     * （get余力計算基準日<株式買付／信用現引>）<BR>
     * 
     * this.余力計算基準日<株式買付/信用現引>を返却する。<BR>
     * @@return int
     * @@roseuid 40C5B6EF0398
     */
    public int getEquityBasePoint()
    {
        return this.equityBasePoint;
    }

    /**
     * （set余力計算基準日<株式買付／信用現引>）<BR>
     * 
     * パラメータ.余力計算基準日<株式買付/信用現引>をthis.余力計算基準日<株式買付/信用?
     * 引>にセットする。<BR>
     * @@param l_intEquityBasePoint
     * @@roseuid 40C5B6F60359
     */
    public void setEquityBasePoint(int l_intEquityBasePoint)
    {
        this.equityBasePoint = l_intEquityBasePoint;
    }

    /**
     * （get余力計算基準日<信用新規建>）<BR>
     * 
     * this.余力計算基準日<信用新規建>を返却する。<BR>
     * @@return int
     * @@roseuid 40DB82550312
     */
    public int getMarginBasePoint()
    {
        return this.marginBasePoint;
    }

    /**
     * （set余力計算基準日<信用新規建>）<BR>
     * 
     * パラメータ.余力計算基準日<信用新規建>をthis.余力計算基準日<信用新規建>にセットす
     * る。<BR>
     * @@param l_intMarginBasePoint
     * @@roseuid 40DB82550322
     */
    public void setMarginBasePoint(int l_intMarginBasePoint)
    {
        this.marginBasePoint = l_intMarginBasePoint;
    }

    /**
     * （get余力計算基準日<投信>）<BR>
     * 
     * this.余力計算基準日<投信>を返却する。<BR>
     * @@return int
     * @@roseuid 40DB826F0041
     */
    public int getFundBasePoint()
    {
        return this.fundBasePoint;
    }

    /**
     * （set余力計算基準日<投信>）<BR>
     * 
     * パラメータ.余力計算基準日<投信>をthis.余力計算基準日<投信>にセットする。
     * <BR>
     * @@param l_intFundBasePoint
     * @@roseuid 40DB826F0051
     */
    public void setFundBasePoint(int l_intFundBasePoint)
    {
        this.fundBasePoint = l_intFundBasePoint;
    }

    /**
     * （get余力計算基準日<出金>）<BR>
     * 
     * this.余力計算基準日<出金>を返却する。<BR>
     * @@return int
     * @@roseuid 40DB8291033C
     */
    public int getPaymentBasePoint()
    {
        return this.paymentBasePoint;
    }

    /**
     * （set余力計算基準日<出金>）<BR>
     * 
     * パラメータ.余力計算基準日<出金>をthis.余力計算基準日<出金>にセットする。<BR>
     * @@param l_intPaymentBasePoint
     * @@roseuid 40DB8291034C
     */
    public void setPaymentBasePoint(int l_intPaymentBasePoint)
    {
        this.paymentBasePoint = l_intPaymentBasePoint;
    }

    /**
     * （get余力計算基準日<オプション新規買建>）<BR>
     * 
     * this.余力計算基準日<オプション新規買建>を返却する。<BR>
     * @@return int
     * @@roseuid 40DBAD930067
     */
    public int getOptionBasePoint()
    {
        return this.optionBasePoint;
    }

    /**
     * （set余力計算基準日<オプション新規買建>）<BR>
     * 
     * パラメータ.余力計算基準日<オプション新規買建>をthis.余力計算基準日<オプション新?
     * 買建>にセットする。<BR>
     * @@param l_intOptionBasePoint
     * @@roseuid 40DBAD930077
     */
    public void setOptionBasePoint(int l_intOptionBasePoint)
    {
        this.optionBasePoint = l_intOptionBasePoint;
    }

    /**
     * （get余力計算基準日<その他買付>）<BR>
     * 
     * this.余力計算基準日<その他買付>を返却する。<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getOtherBasePoint()
    {
        return this.otherBasePoint;
    }

    /**
     * （set余力計算基準日<その他買付>）<BR>
     * 
     * パラメータ.余力計算基準日<その他買付>をthis.余力計算基準日<その他買付>にセットす
     * る。<BR>
     * @@param l_intOtherBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setOtherBasePoint(int l_intOtherBasePoint)
    {
        this.otherBasePoint = l_intOtherBasePoint;
    }

    /**
     * （get余力計算基準日<ミニ株>）<BR>
     * 
     * this.余力計算基準日<ミニ株>を返却する。<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getMstkBasePoint()
    {
        return this.mstkBasePoint;
    }

    /**
     * （set余力計算基準日<ミニ株>）<BR>
     * 
     * パラメータ.余力計算基準日<ミニ株>をthis.余力計算基準日<ミニ株>にセットす
     * る。<BR>
     * @@param l_intMstkBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setMstkBasePoint(int l_intMstkBasePoint)
    {
        this.mstkBasePoint = l_intMstkBasePoint;
    }

    /**
     * （get余力計算基準日<累投>）<BR>
     * 
     * this.余力計算基準日<累投>を返却する。<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getRuitoBasePoint()
    {
        return this.ruitoBasePoint;
    }

    /**
     * （set余力計算基準日<累投>）<BR>
     * 
     * パラメータ.余力計算基準日<累投>をthis.余力計算基準日<累投>にセットす
     * る。<BR>
     * @@param l_intRuitoBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setRuitoBasePoint(int l_intRuitoBasePoint)
    {
        this.ruitoBasePoint = l_intRuitoBasePoint;
    }

    /**
     * （get余力計算基準日<IPO>）<BR>
     * 
     * this.余力計算基準日<IPO>を返却する。<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getIpoBasePoint()
    {
        return this.ipoBasePoint;
    }

    /**
     * （set余力計算基準日<IPO>）<BR>
     * 
     * パラメータ.余力計算基準日<IPO>をthis.余力計算基準日<IPO>にセットす
     * る。<BR>
     * @@param l_intIpoBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setIpoBasePoint(int l_intIpoBasePoint)
    {
        this.ipoBasePoint = l_intIpoBasePoint;
    }

    /**
     * （get余力計算基準日<中国株式>）<BR>
     * 
     * this.余力計算基準日<中国株式>を返却する。<BR>
     * @@return int
     * @@roseuid 40DB828001E6
     */
    public int getFeqBasePoint()
    {
        return this.feqBasePoint;
    }

    /**
     * （set余力計算基準日<中国株式>）<BR>
     * 
     * パラメータ.余力計算基準日<中国株式>をthis.余力計算基準日<中国株式>にセットす
     * る。<BR>
     * @@param l_intFeqBasePoint
     * @@roseuid 40DB828001F5
     */
    public void setFeqBasePoint(int l_intFeqBasePoint)
    {
        this.feqBasePoint = l_intFeqBasePoint;
    }

    /**
     * （get発注日<株式／信用>）<BR>
     * <BR>
     * this.発注日<株式／信用>を返却する。
     * <BR>
     * @@return int
     */
    public int getEquityBizDate()
    {
        return this.equityBizDate;
    }

    /**
     * （set発注日<株式／信用>）<BR>
     * <BR>
     * パラメータ.発注日<株式／信用>をthis.発注日<株式／信用>にセットする。
     * <BR>
     * @@param l_intBizDate
     */
    public void setEquityBizDate(int l_intBizDate)
    {
        this.equityBizDate = l_intBizDate;
    }

    /**
     * （calc指定日）<BR>
     * 
     * パラメータ.営業日に対応する。指定日を返却する。<BR>
     * <BR>
     * ※パラメータ.営業日がthis.営業日[T-2..+6]の範囲外であった時例外をスローする。<BR>
     * ※営業日と指定日の対応・・・<BR>
     * パラメータ.営業日 = this.営業日[T+0] ⇒ 0 を返却<BR>
     * パラメータ.営業日 = this.営業日[T+1] ⇒ 1 を返却<BR>
     * パラメータ.営業日 = this.営業日[T+2] ⇒ 2 を返却<BR>
     * パラメータ.営業日 = this.営業日[T+3] ⇒ 3 を返却<BR>
     * パラメータ.営業日 = this.営業日[T+4] ⇒ 4 を返却<BR>
     * パラメータ.営業日 = this.営業日[T+5] ⇒ 5 を返却<BR>
     * パラメータ.営業日 = this.営業日[T+6] ⇒ 6 を返却<BR>
     * パラメータ.営業日 = this.営業日[T-1] ⇒ -1 を返却<BR>
     * パラメータ.営業日 = this.営業日[T-2] ⇒ -2 を返却<BR>
     * @@param l_datBizDate
     * @@return int
     * @@roseuid 40FCC6440377
     */
    public int calcSpecifiedPoint(Date l_datBizDate)
    {
        //this.営業日の配列の要素数を取得する。
        int l_intLength = this.bizDate.length;
        
        //項番を（0～要素数-1）の範囲でループ
        for (int index = 0; index < l_intLength; index++)
        {
            //営業日[index]とパラメータ.営業日が等しい時
            if (WEB3DateUtility.compareToDay(this.bizDate[index], l_datBizDate) == 0)
            {
                //項番を返却する。
                return index - 2;
            }
        }
        
        //エラーをスロー
        log.error("illegal Argument");
        throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
            this.getClass().getName() + ".calcSpecifiedPoint(Date)");
    }

    /**
     * （roll営業日）<BR>
     * 
     * パラメータ.営業日より、パラメータ.加算／減算日数 分スライドさせた営業日を<BR>
     * this.営業日[T-2..+6]より取得し返却する。<BR>
     * <BR>
     * ※スライドさせた営業日がthis.営業日[T-2..+6]の範囲外であった時、<BR>
     * 例外をスローする。<BR>
     * @@param l_datBizDate
     * @@param l_intRollDays
     * @@return Date
     * @@roseuid 40F4C7CC014B
     */
    public Date rollBizDate(Date l_datBizDate, int l_intRollDays)
    {
        //パラメータ.営業日の項番
        int l_intIndex = 0;
        //検索結果フラグ
        boolean l_resultFlag = false;

        //営業日の要素数を取得する。
        int l_intLength = this.bizDate.length;

        //項番を（0～要素数-1）の範囲でループ
        for (int index = 0; index < l_intLength; index++)
        {
            if (WEB3DateUtility.compareToDay(this.bizDate[index], l_datBizDate) == 0)
            {
                //パラメータ.営業日の項番を取得
                l_intIndex = index - 2;
                //検索結果フラグをTRUEにする
                l_resultFlag = true;

                //ループから抜ける
                break;
            }
        }

        //取得した項番にロールさせたい日数を加算する。
        l_intIndex = l_intIndex + l_intRollDays;

        //検索成功し、指定項番が営業日の範囲内の時
        if (l_resultFlag == true && -2 <= l_intIndex && l_intIndex <= 6)
        {
            return this.getBizDate( l_intIndex );
        }
        else
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".rollBizDate(Date, int)");
        }
    }
    
    
    /**
     * （get指定日範囲）<BR>
     * 指定日範囲(T+5)を返却する。<BR>
     * @@return int
     */
    public int getSpecifiedPointRange()
    {
        return WEB3TPSpecifiedPointDef.T_5;
    }

    /**
     * （calc余力計算基準日）<BR>
     * 
     * 取引ごとの余力計算基準日を算出し、自身のプロパティにセットする。<BR>
     * <BR>
     * シーケンス図「(余力計算条件)calc余力計算基準日」参照<BR>
     * 
     * @@throws WEB3SystemLayerException
     * @@roseuid 40D7C9B801C1
     */
    protected void calcBasePoint(WEB3GentradeSubAccount l_subAccount)
    {
        /*
         * 取引時間コンテキストををスレッドローカルから取得する。
         */
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
    
        //市場コード、受付時間区分、銘柄コードコードを退避する。
        String l_marketCode = l_clendarContext.getMarketCode();
        String l_tradingTimeType = l_clendarContext.getTradingTimeType();
        String l_productCode = l_clendarContext.getProductCode();
    
        //銘柄コードに0:DEFULTをセット
        WEB3GentradeTradingTimeManagement.resetProductCode("0");

        //証券会社を取得
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
            
        //証券会社のデータソースを取得する。 
        InstitutionRow l_institutionRow = 
            (InstitutionRow)l_institution.getDataSourceObject();
        
        //当日出金振込実施を取得
        String l_theDayTransfer =
            l_institutionRow.getTheDayTransfer();
             
        //取得した市場コード==nullの場合
        if (l_marketCode == null)
        {
            //取引時間コンテキストに、市場コード（1：東京）をセット
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.TOKYO);
        }
    
        //発注日
        Date l_orderDate = null;
        //業務日付を取得
        Date l_bizDate0 = this.getBizDate(0);
        //翌営業日を取得
        Date l_bizDate1 = this.getBizDate(1);
        //翌々営業日を取得
        Date l_bizDate2 = this.getBizDate(2);
    
        /*
         * 株式買付／信用現引／信用新規建の余力計算基準日の計算
         * 　@[get発注日()の戻り値＝getBizDate()の戻り値の場合]
         * 　@(発注日　@＝　@業務日付)
         * 　@　@余力計算基準日<株式買付/信用現引>＝3
         * 　@[get発注日()の戻り値＝翌日営業日の場合]
         * 　@(発注日　@＝　@翌営業日)
         * 　@　@余力計算基準日<株式買付/信用現引>＝4
         * 　@[以外の場合]
         * 　@　@余力計算基準日<株式買付/信用現引>＝3
         * 
         * 　@※get発注日()において、エラーがスローされた場合
         * 　@　@余力計算基準日<株式買付/信用現引>＝3         
         */
        //取引時間コンテキストに、受付時間区分（01：株式・信用）をセット
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        //発注日を取得する。
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //発注日が業務日付の時
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                this.setEquityBasePoint(3);
                this.setMarginBasePoint(1);
                this.setEquityBizDate(0);
            }
            //発注日＝翌日日付の時
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setEquityBasePoint(4);
                this.setMarginBasePoint(2);
                this.setEquityBizDate(1);
            }
            //以外の場合
            else
            {
                this.setEquityBasePoint(3);
                this.setMarginBasePoint(1);
                this.setEquityBizDate(0);
            }
        }
        //エラーがスローされた場合
        catch (WEB3SystemLayerException e)
        {
            this.setEquityBasePoint(3);
            this.setMarginBasePoint(1);
            this.setEquityBizDate(0);
        }

        //会社部店別余力計算条件.“信用新規建可能額適用日”を取得する
        String l_strMarginOpenApplyDate = this
            .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINOPEN_APPLY_DATE);

        //“信用新規建可能額適用日” == FROM_BIZ_DATE_UNTIL_T5の場合
        if(WEB3TPMarginOpenApplyDateDef.FROM_BIZ_DATE_UNTIL_T5
            .equals(l_strMarginOpenApplyDate))
        {
            /*
             * 余力計算基準日<信用新規建> = 発注日<株式／信用>
             */
            int l_intBizDate = this.getEquityBizDate();
            this.setMarginBasePoint(l_intBizDate);
        }
        //“信用新規建可能額適用日” == FROM_T2_UNTIL_T5の場合
        if (WEB3TPMarginOpenApplyDateDef.FROM_T2_UNTIL_T5.equals(l_strMarginOpenApplyDate))
        {
            try
            {
                //発注日
                l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

                //発注日＝業務日付の時
                if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
                {
                    this.setMarginBasePoint(2);
                }
                //発注日＝翌日日付の時
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
                {
                    this.setMarginBasePoint(3);
                }
                //以外の場合
                else
                {
                    this.setMarginBasePoint(2);
                }
            }
            //エラーがスローされた場合
            catch (WEB3SystemLayerException l_ex)
            {
                this.setMarginBasePoint(2);
            }
        }
        
        /*
         * オプションの余力計算基準日の計算
         * 　@[get発注日()の戻り値＝getBizDate()の戻り値の場合]
         * 　@(発注日　@＝　@業務日付)
         * 　@　@余力計算基準日<オプション新規建>＝1
         * 　@[get発注日()の戻り値＝翌日営業日の場合]
         * 　@(発注日　@＝　@翌日営業日)
         * 　@　@余力計算基準日<オプション新規建>＝2
         * 　@[以外の場合]
         * 　@　@余力計算基準日<オプション新規建>＝1
         * 
         * 　@※get発注日()において、エラーがスローされた場合
         * 　@　@余力計算基準日<オプション新規建>＝1　@　@         
         */
        //取引時間コンテキストに、受付時間区分（11：株価指数オプション）をセット
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(
            WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        //取引時間コンテキストに、市場コード（0：DEFULT）をセット
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //発注日を取得
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //発注日が業務日付の時
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                //is夕場時間帯()の戻り値＝trueの場合
                boolean l_blnIsEvening = WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();
                if (l_blnIsEvening)
                {
                    this.setOptionBasePoint(2);
                }
                else
                {
                    this.setOptionBasePoint(1);
                }
            }
            //発注日が翌日日付の時
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setOptionBasePoint(2);
            }
            //以外の場合
            else
            {
                this.setOptionBasePoint(1);
            }
        }
        //例外がスローされた場合
        catch (WEB3SystemLayerException e)
        {
            this.setOptionBasePoint(1);
        }

        /*
         * 投信の余力計算基準日の計算
         * ・投資信託買付可能額適用日＝発注日以降の場合
         * 　@　@余力計算基準日<投信>＝発注日
         * 　@※get発注日()において、エラーがスローされた場合
         * 　@　@余力計算基準日<投信>＝0         
         * 
         * ・投資信託買付可能額適用日！＝発注日以降の場合
         * 　@[get発注日()の戻り値＝(getBizDate()の戻り値)の場合]
         * 　@(発注日　@＝　@業務日付)
         * 　@　@余力計算基準日<投信>＝3
         * 　@[get発注日()の戻り値＝翌営業日の場合]
         * 　@(発注日　@＝　@翌営業日)
         * 　@　@余力計算基準日<投信>＝4
          　@[get発注日()の戻り値＝翌々営業日の場合]
         * 　@(発注日　@＝　@翌々営業日)
         * 　@　@余力計算基準日<投信>＝5
         * 　@[以外の場合]
         * 　@　@余力計算基準日<投信>＝3
         * 
         * 　@※get発注日()において、エラーがスローされた場合
         * 　@　@余力計算基準日<投信買付>＝3         
         */
        //取引時間コンテキストに、受付時間区分（06：投資信託）をセット
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
        //取引時間コンテキストに、市場コード（0：DEFULT）をセット
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //投資信託買付可能額適用日の設定値を取得
        String l_strMFBuyApplyDate = this.getInstBranCalcCondition(WEB3TPCalcCondition.MFBUY_APPLY_DATE);

        //発注日を取得
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //適用日＝発注日以降の場合
            if(WEB3TPMutualFundBuyApplyDateDef.FROM_BIZ_DATE_UNTIL_T5.equals(l_strMFBuyApplyDate))
            {
                //基準日＝当日
                this.setFundBasePoint(WEB3TPSpecifiedPointDef.T_0);
            }
            else
            {
                //発注日が業務日付の時
                if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
                {
                    this.setFundBasePoint(3);
                }
                //発注日が翌営業日の時
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
                {
                    this.setFundBasePoint(4);
                }
                //発注日が翌々営業日の時
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate2) == 0)
                {
                    this.setFundBasePoint(5);
                }
                //それ以外
                else
                {
                    this.setFundBasePoint(3);
                }
            }
        }
        //例外がスローされた場合
        catch (WEB3SystemLayerException e)
        {
            //適用日＝発注日以降の場合
            if(WEB3TPMutualFundBuyApplyDateDef.FROM_BIZ_DATE_UNTIL_T5.equals(l_strMFBuyApplyDate))
            {
                this.setFundBasePoint(0);
            }
            else
            {
                this.setFundBasePoint(3);
            }
        }

        /*
         * 出金の余力計算基準日の計算
         *  
         *  証券会社テーブル.当日出金振込み実施='1'の時 
         * 
         *  　@[get発注日()の戻り値＝getBizDate()の戻り値の場合]
         *  　@(発注日　@＝　@業務日付)
         *  　@　@余力計算基準日<出金買付>＝0
         *  　@[get発注日()の戻り値＝翌営業日の場合]
         *  　@(発注日　@＝　@翌営業日)
         *  　@　@余力計算基準日<出金買付>＝1
         *  　@[以外の場合]
         *  　@　@余力計算基準日<出金買付>＝0
         * 
         *　@それ以外のとき（証券会社テーブル.当日出金振込み実施=null も含む） 
         *   　@[get発注日()の戻り値＝getBizDate()の戻り値の場合]
         * 　@    (発注日　@＝　@業務日付)
         * 　@    　@余力計算基準日<出金買付>＝1
         *  　@[get発注日()の戻り値＝翌営業日の場合]
         *  　@(発注日　@＝　@翌営業日)
         *  　@　@余力計算基準日<出金買付>＝2
         *  　@[以外の場合]
         *  　@　@余力計算基準日<出金買付>＝1
         *
         * 
         * 　@    ※get発注日()において、エラーがスローされた場合
         * 　@　@余力計算基準日<出金>＝1
         */
        //取引時間コンテキストに、受付時間区分（16：出金）をセット
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.PAYMENT);
        //取引時間コンテキストに、市場コード（0：DEFULT）をセット
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //発注日を取得
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
 
            //証券会社テーブル.当日出金振込実施が'1'の時
            if (WEB3TheDayTransferDef.ENFORCEMENT.equals(l_theDayTransfer) == true)
            {
                //発注日が業務日付の時
                if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
                {
                    this.setPaymentBasePoint(0);
                }
                //発注日が翌営業日の時
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
                {
                    this.setPaymentBasePoint(1);
                }
                //それ以外
                else
                {
                    this.setPaymentBasePoint(0);
                }
            }
            
            //それ以外
            else
            {
                //発注日が業務日付の時
                if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
                {
                    this.setPaymentBasePoint(1);
                }
                //発注日が翌営業日の時
                else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
                {
                    this.setPaymentBasePoint(2);
                }
                //それ以外
                else
                {
                    this.setPaymentBasePoint(1);
                }
            }

        }
        //エラーがスローされた場合
        catch (WEB3SystemLayerException e)
        {
            //証券会社テーブル.当日出金振込実施が'1'の時
            if (WEB3TheDayTransferDef.ENFORCEMENT.equals(l_theDayTransfer) == true)
            {
                this.setPaymentBasePoint(0);                
            }
            //それ以外
            else
            {
                this.setPaymentBasePoint(1);
            }
        }

        /*
         * その商品の余力計算基準日の計算
         * 
         * 　@　@余力計算基準日<その他買付>＝0
         */

            this.setOtherBasePoint(0);

        /*
         * ミニ株の余力計算基準日の計算
         * 
         * 　@[get発注日()の戻り値＝(getBizDate()の戻り値)の場合]
         * 　@(発注日　@＝　@(業務日付))
         * 　@　@余力計算基準日<ミニ株>＝3
         * 　@[get発注日()の戻り値＝翌営業日の場合]
         * 　@(発注日　@＝　@翌営業日)
         * 　@　@余力計算基準日<ミニ株>＝4
         * 　@[get発注日()の戻り値＝翌々営業日の場合]
         * 　@(発注日　@＝　@翌々営業日)
         * 　@　@余力計算基準日<ミニ株>＝5
         * 　@[以外の場合]
         * 　@　@余力計算基準日<ミニ株>＝4
         * 
         * 　@※get発注日()において、エラーがスローされた場合
         * 　@　@余力計算基準日<ミニ株>＝4
         */
        //取引時間コンテキストに、受付時間区分（05：ミニ株）をセット
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MINI_STOCK);
        //取引時間コンテキストに、市場コード（0：DEFULT）をセット
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //発注日を取得
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //発注日が業務日付の時
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                this.setMstkBasePoint(3);
            }
            //発注日が翌営業日の時
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setMstkBasePoint(4);
            }
            //発注日が翌々営業日の時
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate2) == 0)
            {
                this.setMstkBasePoint(5);
            }
            //それ以外
            else
            {
                this.setMstkBasePoint(4);
            }
        }
        //エラーがスローされた場合
        catch (WEB3SystemLayerException e)
        {
            this.setMstkBasePoint(4);
        }

        /*
         * 累投の余力計算基準日の計算
         * 
         * 　@[get発注日()の戻り値＝(getBizDate()の戻り値　@または　@翌営業日)の場合]
         * 　@(発注日　@＝　@(業務日付))
         * 　@　@余力計算基準日<累投>＝1
         * 　@[get発注日()の戻り値＝翌営業日の場合]
         * 　@(発注日　@＝　@翌営業日)
         * 　@　@余力計算基準日<累投>＝2
         * 　@[以外の場合]
         * 　@　@余力計算基準日<累投>＝1
         * 
         * 　@※get発注日()において、エラーがスローされた場合
         * 　@　@余力計算基準日<累投>＝1
         */
        //取引時間コンテキストに、受付時間区分（07：中国F）をセット
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
        //取引時間コンテキストに、市場コード（0：DEFULT）をセット
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //発注日を取得
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //発注日が業務日付の時
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                this.setRuitoBasePoint(1);
            }
            //発注日が翌営業日の時
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setRuitoBasePoint(2);
            }
            //それ以外
            else
            {
                this.setRuitoBasePoint(1);
            }
        }
        //エラーがスローされた場合
        catch (WEB3SystemLayerException e)
        {
            this.setRuitoBasePoint(1);
        }


        /*
         * IPOの余力計算基準日の計算
         * 
         * 　@　@余力計算基準日<IPO>＝0
         */
 
            this.setIpoBasePoint(0);
 
        /*
         * 中国株式の余力計算基準日の計算
         * 
         * 　@[get発注日()の戻り値＝(getBizDate()の戻り値)の場合]
         * 　@(発注日　@＝　@(業務日付))
         * 　@　@余力計算基準日<中国株式>＝3
         * 　@[get発注日()の戻り値＝翌営業日の場合]
         * 　@(発注日　@＝　@翌営業日)
         * 　@　@余力計算基準日<中国株式>＝4
         * 　@[以外の場合]
         * 　@　@余力計算基準日<中国株式>＝3
         * 
         * 　@※get発注日()において、エラーがスローされた場合
         * 　@　@余力計算基準日<中国株式>＝3
         */
        //取引時間コンテキストに、受付時間区分（10：外国株式）をセット
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
        //取引時間コンテキストに、市場コード（0：DEFULT）をセット
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //発注日を取得
        try
        {
            l_orderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //発注日が業務日付の時
            if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate0) == 0)
            {
                this.setFeqBasePoint(3);
            }
            //発注日が翌営業日の時
            else if (WEB3DateUtility.compareToDay(l_orderDate, l_bizDate1) == 0)
            {
                this.setFeqBasePoint(4);
            }
            //それ以外
            else
            {
                this.setFeqBasePoint(3);
            }
        }
        //エラーがスローされた場合
        catch (WEB3SystemLayerException e)
        {
            this.setFeqBasePoint(3);
        }
        
        //取引カレンダコンテキストの市場コード、受付時間区分、銘柄コードを処理開始状態に戻す
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_marketCode);
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_tradingTimeType);
        WEB3GentradeTradingTimeManagement.resetProductCode(l_productCode);
    }

    /**
     * （calc営業日）<BR>
     * 
     * 営業日[T-2..+6]を算出し、自身のプロパティにセットする。<BR>
     * <BR>
     * シーケンス図「(余力計算条件)calc営業日」参照<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40FCCDD5027D
     */
    protected void calcBizDate()
    {
        
        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
    
        //システムタイプスタンプを取得する。
        this.systemTimeStamp = tradingSystem.getSystemTimestamp();
    
        //業務日付を取得する。
        Date l_bizDate0 = tradingSystem.getBizDate();
        
        //業務日付をプロパティにセットする。
        this.bizDate[1] = l_bizDate0;
    
        //TimeStamp型の業務日付を生成する。
        Timestamp l_bizDateStamp = new Timestamp(l_bizDate0.getTime());
    
        //営業日計算クラスを生成する。
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_bizDateStamp);
        //営業日配列の要素数を取得する。
        int l_intLength = this.bizDate.length;
    
        //n = -2～6まで繰り返し処理
        for (int index = -2; index < l_intLength - 2; index++)
        {
            Timestamp l_timeStamp;
            try
            {
                l_timeStamp = l_genBizDate.roll(index);
            }
            catch (WEB3SystemLayerException e)
            {
                throw new WEB3BaseRuntimeException(
                     e.getErrorInfo(),
                     e.getErrorMethod(),
                     e.getErrorMessage(),
                     e.getException());
             }

            //属性：営業日[T-2..+6]にセットする。
            this.bizDate[index + 2] = new Date(l_timeStamp.getTime());
        }
        
    }

    /**
     * (get権利落ち日)<BR>
     * 
     * 権利落ち日を算出し、返却する。<BR>
     * <BR>
     * シーケンス図「(余力計算条件)get権利落ち日」参照<BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@return java.util.Date
     */
    public Date getRightsOffDate(long l_lngProductId)
    {
        final String STR_METHOD_NAME = "getRightOffDate(long)";

        Long l_productId = new Long(l_lngProductId);
        boolean l_isRegistered = rightsOffDates.containsKey(l_productId);
        Date l_datRightsOffDate = null;
        
        //未登録の場合
        if(! l_isRegistered)
        {
            EqtypeProductRow l_eqProductRow = null;
            try
            {
                l_eqProductRow = EqtypeProductDao.findRowByPk(l_lngProductId);
            }
            catch (DataException de)
            {
                log.error(de.getMessage(), de);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            
            try
            {
                //決算日を基準日にする
                WEB3GentradeBizDate l_bizDateCalcUtil =
                    new WEB3GentradeBizDate(l_eqProductRow.getYearlyBooksClosingDate());

                //権利落ち日取得(基準日-2営業日)
                l_datRightsOffDate = l_bizDateCalcUtil.roll(-2);
                
                //銘柄IDと権利落ち日を関連付ける
                this.addRightsOffDate(l_productId, l_datRightsOffDate);
                
                StringBuffer l_sbLog = new StringBuffer("product_id=");
                l_sbLog.append(l_lngProductId);
                l_sbLog.append(" rights_off_date=");
                l_sbLog.append(WEB3DateUtility.formatDate(l_datRightsOffDate, "yyyy/MM/dd"));
                log.debug(l_sbLog.toString());
            }
            catch(WEB3SystemLayerException l_ex)
            {
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    l_ex.getErrorMethod(),
                    l_ex.getErrorMessage(),
                    l_ex.getException());
            }
        }
        //登録済の場合
        else
        {
            l_datRightsOffDate = (Date)rightsOffDates.get(l_productId);
        }

        return l_datRightsOffDate;
    }

    /**
     * (get終値)<BR>
     * 
     * 終値を算出し、返却する。<BR>
     * <BR>
     * シーケンス図「(余力計算条件)get終値」参照<BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_datDate - (指定日)
     * @@param l_lngMarketId - (市場ID)
     * @@param l_blnUsePrimaryMarket - (優先市場適用フラグ)
     * @@return double
     */
    public double getClosingPrice(long l_lngProductId, Date l_datDate, long l_lngMarketId, boolean l_blnUsePrimaryMarket)
    {
        final String STR_METHOD_NAME = "getClosingPrice(long, Date, long, boolean)";

        double l_dblClosingPrice = 0.0;
        LastClosingPriceRow l_lastClosingPriceRow = null;
        Timestamp l_tsDate = null;
        
        String l_strProductId_Date = String.valueOf(l_lngProductId)
                                    + WEB3DateUtility.formatDate(l_datDate, "yyyyMMdd");
        boolean l_isRegistered = closingPrices.containsKey(l_strProductId_Date);
        
        //未登録の場合
        if(! l_isRegistered)
        {
            try
            {
                //TimeStamp型の指定日を生成する。
                l_tsDate = new Timestamp(l_datDate.getTime());
                l_lastClosingPriceRow = LastClosingPriceDao.findRowByPk(l_lngProductId, l_tsDate);
            }
            catch (DataFindException l_dfe)
            {
                //no operation
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            this.addClosingPrice(l_strProductId_Date, l_lastClosingPriceRow);
            
            StringBuffer l_sbLog = new StringBuffer("product_id=");
            l_sbLog.append(l_lngProductId);
            l_sbLog.append(" base_date=");
            l_sbLog.append(l_tsDate);
            l_sbLog.append(" LastClosingPriceRow=");
            l_sbLog.append(l_lastClosingPriceRow);
            log.debug(l_sbLog.toString());
        }
        //登録済の場合
        else
        {
            l_lastClosingPriceRow = (LastClosingPriceRow)closingPrices.get(l_strProductId_Date);
        }
        
        if(l_lastClosingPriceRow != null)
        {
            //優先市場を適用する場合
            if(l_blnUsePrimaryMarket)
            {
                l_dblClosingPrice = l_lastClosingPriceRow.getPrimaryClosingPrice();
            }
            //市場を取得して判定する場合
            else
            {
                MarketRow l_marketRow = null;
                try
                {
                    l_marketRow = MarketDao.findRowByPk(l_lngMarketId);
                }
                catch (DataException de)
                {
                    log.error(de.getMessage(), de);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        de.getMessage(),
                        de);
                }
                //市場コード取得
                String l_strMarketCode = l_marketRow.getMarketCode();
                //東証
                if(WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
                {
                    l_dblClosingPrice = l_lastClosingPriceRow.getTokyoClosingPrice();
                }
                //大証
                else if(WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
                {
                    l_dblClosingPrice = l_lastClosingPriceRow.getOosakaClosingPrice();
                }
                //名証
                else if(WEB3MarketCodeDef.NAGOYA.equals(l_strMarketCode))
                {
                    l_dblClosingPrice = l_lastClosingPriceRow.getNagoyaClosingPrice();
                }
                //その他
                else
                {
                    l_dblClosingPrice = l_lastClosingPriceRow.getOtherClosingPrice();
                }
            }
        }
        
        return l_dblClosingPrice;
    }

    /**
     * (get時価<株式>)<BR>
     * 
     * 時価<株式>を算出し、返却する。<BR>
     * <BR>
     * シーケンス図「(余力計算条件)get時価<株式>」参照<BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_lngMarketId - (市場ID)
     * @@return double
     */
    public double getEqtypeQuote(long l_lngProductId, long l_lngMarketId)
    {
        final String STR_METHOD_NAME = "getEqtypeQuote(long,long)";

        double l_dblEqtypeQuotePrice = 0.0;
        
        String l_strProductIdMarketId = String.valueOf(l_lngProductId)
                                        + String.valueOf(l_lngMarketId);
        boolean l_isRegistered = eqtypeQuotes.containsKey(l_strProductIdMarketId);
        
        //未登録の場合
        if(! l_isRegistered)
        {
            //時価サービスに渡す銘柄情報を取得
            EqTypeProductManager l_eqPman = (EqTypeProductManager)GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            EqTypeTradedProduct l_eqTradedProduct = null;  
            
            try
            {
                l_eqTradedProduct = (EqTypeTradedProduct)l_eqPman.getTradedProduct(l_lngProductId, l_lngMarketId);
            }
            catch (NotFoundException l_nfe)
            {
                //取引銘柄が存在しなかったら、0を返す。
                StringBuffer l_sbLog = new StringBuffer("データが見つからないので時価=0 株式取引銘柄マスターテーブルの検索条件:product_id=");
                l_sbLog.append(l_lngProductId);
                l_sbLog.append(" market_id=");
                l_sbLog.append(l_lngMarketId);
                l_sbLog.append(" eqtype_quote_price=");
                l_sbLog.append(l_dblEqtypeQuotePrice);
                log.debug(l_sbLog.toString());

                Double l_eqtypeQuotePrice = new Double(l_dblEqtypeQuotePrice);
                this.addEqtypeQuote(l_strProductIdMarketId, l_eqtypeQuotePrice);
                return l_dblEqtypeQuotePrice;
            }
            
            try
            {
                //時価サービスを取得
                EqTypeQuoteDataSupplierService l_quoteDataSupplierService =
                    (EqTypeQuoteDataSupplierService)GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getQuoteDataSupplierService();
                
                //時価<株式>を取得
                EqTypeQuoteData l_eqQuoteData
                    = l_quoteDataSupplierService.getEqTypeQuote(l_eqTradedProduct);

                // 以下の優先順位で、取得できる（0でない）単価を時価とする。
                //１．現在値(EqTypeQuoteData.getCurrentPrice( ))
                //２．売気配値(EqTypeQuoteData.getBidPrice( ))
                //３．買気配値(EqTypeQuoteData.getAskPrice( ))

                l_dblEqtypeQuotePrice = l_eqQuoteData.getCurrentPrice();

                if (l_dblEqtypeQuotePrice == 0.0)
                {
                    l_dblEqtypeQuotePrice = l_eqQuoteData.getBidPrice();

                    if (l_dblEqtypeQuotePrice == 0.0)
                    {
                        l_dblEqtypeQuotePrice = l_eqQuoteData.getAskPrice();
                    }
                }
                Double l_eqtypeQuotePrice = new Double(l_dblEqtypeQuotePrice);
                this.addEqtypeQuote(l_strProductIdMarketId, l_eqtypeQuotePrice);
                
                StringBuffer l_sbLog = new StringBuffer("product_id=");
                l_sbLog.append(l_lngProductId);
                l_sbLog.append(" market_id=");
                l_sbLog.append(l_lngMarketId);
                l_sbLog.append(" eqtype_quote_price=");
                l_sbLog.append(l_dblEqtypeQuotePrice);
                log.debug(l_sbLog.toString());

            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(), l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }
        //登録済の場合
        else
        {
            Double l_eqtypeQuotePrice = (Double)eqtypeQuotes.get(l_strProductIdMarketId);
            l_dblEqtypeQuotePrice = l_eqtypeQuotePrice.doubleValue();
        }

        return l_dblEqtypeQuotePrice;
    }
    
    /**
     * (add権利落ち日)<BR>
     * 銘柄IDと権利落ち日を関連づける。<BR>
     * <BR>
     * HashMapに登録する。<BR>
     * －キー：引数の銘柄IDのLongオブジェクト<BR>
     * －値　@：引数の権利落ち日<BR>
     * @@param l_productId - (銘柄ID)
     * @@param l_datRightsOffDate - (権利落ち日)
     */
    protected void addRightsOffDate(Long l_productId, Date l_datRightsOffDate) 
    {
        rightsOffDates.put(l_productId, l_datRightsOffDate);
    }
    
    /**
     * (add終値)<BR>
     * 銘柄ID+指定日と終値Paramsを関連づける。<BR>
     * <BR>
     * HashMapに登録する。<BR>
     * －キー：引数の銘柄ID+指定日のStringオブジェクト<BR>
     * －値　@：引数の終値Params<BR>
     * @@param l_strProductId_Date - (銘柄ID+指定日)
     * @@param l_lastClosingPriceRow - (終値Params)
     */
    protected void addClosingPrice(String l_strProductId_Date, LastClosingPriceRow l_lastClosingPriceRow) 
    {
        closingPrices.put(l_strProductId_Date, l_lastClosingPriceRow);
    }
    
    /**
     * (add時価<株式>)<BR>
     * 銘柄ID+市場IDと時価<株式>を関連づける。<BR>
     * <BR>
     * HashMapに登録する。<BR>
     * －キー：引数の銘柄ID+市場IDのStringオブジェクト<BR>
     * －値　@：引数の時価<株式><BR>
     * @@param l_strProductIdMarketId - (銘柄ID+市場ID)
     * @@param Double - (時価<株式>)
     */
    protected void addEqtypeQuote(String l_strProductIdMarketId, Double l_EqtypeQuote) 
    {
        eqtypeQuotes.put(l_strProductIdMarketId, l_EqtypeQuote);
    }

    /**
     * （get評価単価Callback）<BR>
     * 
     * this.評価単価Callbackを返却する。<BR>
     * @@return WEB3TPUnitPriceCallback
     */
    public WEB3TPUnitPriceCallback getUnitPriceCallback()
    {
        return this.unitPriceCallback;
    }

    /**
     * （set評価単価Callback）<BR>
     * 
     * パラメータ.評価単価Callbackをthis.評価単価Callbackにセットする。<BR>
     * @@param l_unitPriceCallback
     */
    public void setUnitPriceCallback(WEB3TPUnitPriceCallback l_unitPriceCallback)
    {
        this.unitPriceCallback = l_unitPriceCallback;
    }

    /**
     * (get会社部店別余力計算条件)<BR>
     * 
     * 引数.プリファ@レンス名に対応する値をマップより検索し返却する。
     * ※レコードが存在しない場合、nullを返却する。
     * 
     * @@param l_strName - (プリファ@レンス名)
     * @@return String
     */
    public String getInstBranCalcCondition(String l_strName)
    {
        boolean l_isRegistered = instBranCalcCondition.containsKey(l_strName);

        //値
        String l_strValue = null;
        
        //登録済の場合
        if(l_isRegistered == true)
        {
            l_strValue = (String)instBranCalcCondition.get(l_strName);
        }

        return l_strValue;
    }

    /**
     * (add会社部店別余力計算条件)<BR>
     * <BR>
     * １）引数.プリファ@レンス名をキーとして引数.値を<BR>
     * Map（this.会社部店別余力計算条件）にセットする。<BR>
     * <BR>
     * 　@-this.会社部店別余力計算条件.put()をコール<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@Object：　@引数.プリファ@レンス名<BR>
     * 　@　@Object：　@引数.値<BR>
     * <BR>
     * @@param l_strName - (プリファ@レンス名)
     * @@param l_strValue - (値)
     */
    protected void addInstBranCalcCondition(String l_strName, String l_strValue) 
    {
        instBranCalcCondition.put(l_strName, l_strValue);
    }

    /**
     * (get部店タイプ)<BR>
     * <BR>
     * this.部店タイプを返却する。<BR>
     * <BR>
     * @@return BranchTypeEnum
     */
    public BranchTypeEnum getBranchType()
    {
        return branchType;
    }

    /**
     * (set部店タイプ) <BR>
     * <BR>
     * パラメータ.部店タイプをthis.部店タイプにセットする。<BR>
     * <BR>
     * @@param l_branchType - (部店タイプ)
     */
    public void setBranchType(BranchTypeEnum l_branchType)
    {
        this.branchType = l_branchType;
    }
    
    /**
     * (is証券担保ローン区分)<BR> 
     * <BR>
     * this.証券担保ローン区分を返却する。<BR>
     * @@return boolean
     */
    public boolean isSecuredLoanSecAccOpenDiv()
    {
        return this.securedLoanSecAccOpenDiv;
    }
    
    /**
     * (set証券担保ローン区分)<BR>  
     * <BR> 
     * パラメータ.証券担保ローン区分をthis.証券担保ローン区分にセットする。<BR> 
     * @@param l_blnSecuredLoanSecAccOpenDiv - (証券担保ローン区分)
     */
    public void setSecuredLoanSecAccOpenDiv(boolean l_blnSecuredLoanSecAccOpenDiv)
    {
        this.securedLoanSecAccOpenDiv = l_blnSecuredLoanSecAccOpenDiv;
    }
    
    /**
     * (is営業店取引余力チェック実施区分)<BR>
     * <BR>
     * 引数.注文種別の営業店取引余力チェック実施区分を返却する。<BR>
     * <BR>
     * [返却値 = ture の場合]<BR>
     * 　@営業店用の取引余力チェックを実施<BR>
     * <BR>
     * [返却値 = false の場合]<BR>
     * 　@通常の取引余力チェックを実施<BR>
     * <BR>
     * １）"会社部店別余力計算条件"を取得する。<BR>
     * <BR>
     * 　@[a.債券の場合]<BR>
     * 　@（引数.注文種別 IN (401：債券買い注文, 402：債券売り注文)）<BR>
     * <BR>
     * 　@　@-"会社部店別余力計算条件" = this.get会社部店別余力計算条件(:String = "bond.salesoffice.tpcheck.div")<BR>
     * <BR>
     * <BR>
     * ２）営業店取引余力チェック実施区分を返却する。<BR>
     * <BR>
     * 　@[a.営業店用の取引余力チェックを実施する場合]<BR>
     * 　@（"会社部店別余力計算条件" = 1：EXECUTE）<BR>
     * <BR>
     * 　@　@返却値：true<BR>
     * <BR>
     * 　@[a.通常の取引余力チェックを実施する場合]<BR>
     * 　@（以外の場合）<BR>
     * <BR>
     * 　@　@返却値：false<BR>
     * <BR>
     * @@param l_orderTypeEnum - (注文種別)
     * 
     * @@return boolean
     * @@roseuid 4507B8E00052
     */
    public boolean isSalesOfficeTPCheckDiv(OrderTypeEnum l_orderTypeEnum) 
    {
        
        //１）"会社部店別余力計算条件"を取得する。 
        //　@[a.債券の場合] 
        //（引数.注文種別 IN (401：債券買い注文, 402：債券売り注文)） 
        //-"会社部店別余力計算条件" = this.get会社部店別余力計算条件(:String = "bond.salesoffice.tpcheck.div")
        String l_strInstBranCalcCondition = "";
        if (OrderTypeEnum.BOND_BUY.equals(l_orderTypeEnum) || OrderTypeEnum.BOND_SELL.equals(l_orderTypeEnum))
        {
            l_strInstBranCalcCondition = getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.BOND_SALESOFFICE_TPCHECK_DIV);
        }
        
        //２）営業店取引余力チェック実施区分を返却する。 
        //[a.営業店用の取引余力チェックを実施する場合]
        //（"会社部店別余力計算条件" = 1：EXECUTE）
        //返却値：true 
        //[a.通常の取引余力チェックを実施する場合] 
        //（以外の場合） 
        //返却値：false
        if (WEB3SalesofficeTpcheckDivDef.EXECUTE.equals(
            l_strInstBranCalcCondition))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (is預り金担保出金停止区分)<BR>  
     * <BR>  
     * this.預り金担保出金停止区分を返却する。<BR>  
     * @@return boolean
     */
    public boolean isCashDepositStopDiv()
    {
        return cashDepositStopDiv;
    }
    
    /**
     * (set預り金担保出金停止区分) <BR>  
     * <BR>  
     * パラメータ.預り金担保出金停止区分をthis.預り金担保出金停止区分にセットする。<BR>  
     * @@param l_blnCashDepositStopDiv - (預り金担保出金停止区分)
     */
    public void setCashDepositStopDiv(boolean l_blnCashDepositStopDiv)
    {
        this.cashDepositStopDiv = l_blnCashDepositStopDiv;
    }

    /**
     * (isオリックス_担保ローン口座開設区分)<BR>
     * <BR>
     * this.オリックス_担保ローン口座開設区分を返却する。<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isOrixSecuredLoanAccOpenDiv()
    {
        return this.orixSecuredLoanAccOpenDiv;
    }

    /**
     * (setオリックス_担保ローン口座開設区分)<BR>
     * <BR>
     * パラメータ.口座開設をthis.オリックス_担保ローン口座開設区分にセットする。<BR>
     * <BR>
     * @@param l_blnOrixSecuredLoanAccOpenDiv - (口座開設区分)
     */
    public void setOrixSecuredLoanAccOpenDiv(boolean l_blnOrixSecuredLoanAccOpenDiv)
    {
        this.orixSecuredLoanAccOpenDiv = l_blnOrixSecuredLoanAccOpenDiv;
    }

    /**
     * (getオリックス_担保ローン出金可能額)<BR>
     * <BR>
     * this.オリックス_担保ローン出金可能額を返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getOrixSecuredLoanPaymentTradingPower()
    {
        return this.orixSecuredLoanPaymentTradingPower;
    }

    /**
     * (setオリックス_担保ローン出金可能額)<BR>
     * <BR>
     * パラメータ.出金可能額をthis.オリックス_担保ローン出金可能額にセットする。<BR>
     * <BR>
     * @@param l_strOrixSecuredLoanPaymentTradingPower - (出金可能額)
     */
    public void setOrixSecuredLoanPaymentTradingPower(String l_strOrixSecuredLoanPaymentTradingPower)
    {
        this.orixSecuredLoanPaymentTradingPower = l_strOrixSecuredLoanPaymentTradingPower;
    }

    /**
     * (getオリックス_担保ローン金額ロックフラグ)<BR>
     * <BR>
     * this.オリックス_担保ローン金額ロックフラグを返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getOrixSecuredLoanLockFlag()
    {
        return this.orixSecuredLoanLockFlag;
    }

    /**
     * (setオリックス_担保ローン金額ロックフラグ)<BR>
     * <BR>
     * パラメータ.金額ロックフラグをthis.オリックス_担保ローン金額ロックフラグにセットする。<BR>
     * <BR>
     * @@param l_strOrixSecuredLoanLockFlag - (金額ロックフラグ)
     */
    public void setOrixSecuredLoanLockFlag(String l_strOrixSecuredLoanLockFlag)
    {
        this.orixSecuredLoanLockFlag = l_strOrixSecuredLoanLockFlag;
    }

    /**
     * (get終値&lt;DB時価&gt;)<BR>
     * <BR>
     * 終値を算出し、返却する。<BR>
     * <BR>
     * シーケンス図「(余力計算条件)get終値&lt;DB時価&gt;」参照<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * @@param l_datDate - (指定日)<BR>
     * @@param l_lngMarketId - (市場ID)<BR>
     * @@param l_blnUsePrimaryMarket - (優先市場適用フラグ)<BR>
     * @@return double
     */
    public double getClosingPriceDBQuote(
        long l_lngProductId,
        Date l_datDate,
        long l_lngMarketId,
        boolean l_blnUsePrimaryMarket)
    {
        final String STR_METHOD_NAME = "getClosingPriceDBQuote(long, Date, long, boolean)";
        log.entering(STR_METHOD_NAME);

        //終値Temp = 0　@をセット
        //作業用の「終値Temp」変数を作成し、初期化する。
        double l_dblClosingPriceTemp = 0;

        QuoteClosingPriceRow l_quoteClosingPriceRow = null;

        try
        {
            //[時価終値Rowオブジェクト]を取得する。
            String l_strDate = WEB3DateUtility.formatDate(l_datDate, "yyyyMMdd");
            l_quoteClosingPriceRow = QuoteClosingPriceDao.findRowByPk(l_lngProductId, l_strDate);
        }
        catch (DataFindException l_ex)
        {
            //no operation
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //分岐フロー
        //時価終値Row == null
        if (l_quoteClosingPriceRow == null)
        {
            //終値Temp = 0　@をセット
            l_dblClosingPriceTemp = 0;
        }
        //分岐フロー
        else
        {
            //分岐フロー
            if (l_blnUsePrimaryMarket)
            {
                //終値をセット
                l_dblClosingPriceTemp = l_quoteClosingPriceRow.getPrimaryClosingPrice();
            }
            //分岐フロー
            else
            {
                MarketRow l_marketRow = null;

                try
                {
                    //[市場Rowオブジェクト]を取得する。
                    l_marketRow = MarketDao.findRowByPk(l_lngMarketId);
                }
                catch (DataException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                //市場コード取得
                String l_strMarketCode = l_marketRow.getMarketCode();
                
                //東証
                if(WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
                {
                    l_dblClosingPriceTemp = l_quoteClosingPriceRow.getTokyoClosingPrice();
                }
                //大証
                else if(WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
                {
                    l_dblClosingPriceTemp = l_quoteClosingPriceRow.getOosakaClosingPrice();
                }
                //名証
                else if(WEB3MarketCodeDef.NAGOYA.equals(l_strMarketCode))
                {
                    l_dblClosingPriceTemp = l_quoteClosingPriceRow.getNagoyaClosingPrice();
                }
                //その他
                else
                {
                    l_dblClosingPriceTemp = l_quoteClosingPriceRow.getOtherClosingPrice();
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblClosingPriceTemp;
    }
    
    /**
     * (create余力計算条件&lt;DB時価&gt;)<BR>
     * (staticメソッド)(create余力計算条件&lt;DB時価&gt;)<BR>
     * <BR>
     * 余力計算条件オブジェクトを生成し、プロパティをセットする。<BR>
     * <BR>
     * シーケンス図「(余力計算条件)create余力計算条件&lt;DB時価&gt;」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@return WEB3TPCalcCondition
     */
    public static WEB3TPCalcCondition createCalcConditionDBQuote(WEB3GentradeSubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "createCalcConditionDBQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //create余力計算条件(補助口座)
        //余力計算条件オブジェクトを取得する。
        //[引数]
        //補助口座 = 引数.補助口座
        WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcCondition(l_subAccount);

        //評価単価<DB時価>Callback(余力計算条件)
        //評価単価<DB時価>オブジェクトを生成する。
        //[引数]
        //余力計算条件 = create余力計算条件()の戻り値
        WEB3TPUnitPriceDBQuoteCallback l_unitPriceDBQuoteCallback = 
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);

        //set評価単価Callback(評価単価Callbackインターフェース)
        //余力計算条件オブジェクトに、生成した評価単価<DB時価>Callbackオブジェクトをセットする。
        //[引数]
        //評価単価Callbackインターフェース = 生成した評価単価<DB時価>Callbackオブジェクト
        l_calcCondition.setUnitPriceCallback(l_unitPriceDBQuoteCallback);

        log.exiting(STR_METHOD_NAME);
        return l_calcCondition;
    }

    /**
     * (get外株終値)<BR>
     * <BR>
     * 外株終値を取得する。<BR>
     * <BR>
     * シーケンス図「(余力計算条件)get外株終値」参照<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getFeqLastClosingPrice(long l_lngProductId)
    {
        final String STR_METHOD_NAME = "getFeqLastClosingPrice(long)";
        log.entering(STR_METHOD_NAME);

        // get外株終値Row
        FeqLastClosingPriceRow l_feqLastClosingPriceRow = this.getFeqLastClosingPriceRow(l_lngProductId);

        if (l_feqLastClosingPriceRow == null)
        {
            try
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                String l_strWhere = " product_id = ? ";
                String l_strOrderBy = " base_date desc ";
                Object l_bindVars[] = {new Long(l_lngProductId)};
                //外株終値Rowを取得する
                List l_lisFeqLastClosingPriceRows =
                    l_queryProcesser.doFindAllQuery(
                        FeqLastClosingPriceRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);

                if (l_lisFeqLastClosingPriceRows.size() == 0)
                {
                    l_feqLastClosingPriceRow = null;
                }
                else
                {
                    l_feqLastClosingPriceRow = (FeqLastClosingPriceRow)l_lisFeqLastClosingPriceRows.get(0);
                }

                //add外株終値Row
                this.addFeqLastClosingPriceRow(l_lngProductId, l_feqLastClosingPriceRow);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //外株終値Rowを取得できない場合
        if (l_feqLastClosingPriceRow == null)
        {
            //0を返却
            log.exiting(STR_METHOD_NAME);
            return 0.0;
        }
        log.exiting(STR_METHOD_NAME);
        return l_feqLastClosingPriceRow.getFeqClosingPrice();
    }

    /**
     * (get外株終値Row)<BR>
     * 外株終値(:Map)から外株終値Rowを取得する。<BR>
     * <BR>
     * Map.get()<BR>
     * 　@キー = 引数.銘柄ID<BR>
     * <BR>
     * ※引数の銘柄IDは文字列に変換する。<BR>
     * ※Map.get()の戻り値を外株終値Rowにキャストして返却する。<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID
     * @@return FeqLastClosingPriceRow
     */
    protected FeqLastClosingPriceRow getFeqLastClosingPriceRow(long l_lngProductId)
    {
        //Map.get()
        //キー = 引数.銘柄ID
        return (FeqLastClosingPriceRow)feqLastClosingPrice.get(String.valueOf(l_lngProductId));
    }

    /**
     * (add外株終値Row)<BR>
     * <BR>
     * 外株終値Rowを外株終値(:Map)に登録する。<BR>
     * <BR>
     * Map.put() <BR>
     * 　@キー = 引数.銘柄ID <BR>
     * 　@値 = 引数.外株終値Row<BR>
     * <BR>
     * ※引数の銘柄IDは文字列に変換する。<BR>
     * <BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID
     * @@param l_feqLastClosingPriceRow - (外株終値Row)<BR>
     * 外株終値Row
     */
    protected void addFeqLastClosingPriceRow(long l_lngProductId, FeqLastClosingPriceRow l_feqLastClosingPriceRow)
    {
        //Map.put()
        //キー = 引数.銘柄ID
        //値 = 引数.外株終値Row
        feqLastClosingPrice.put(String.valueOf(l_lngProductId), l_feqLastClosingPriceRow);
    }

    /**
     * (isPTS出来終了区分)<BR>
     * <BR>
     * this.PTS出来終了を返却する。<BR>
     * @@return boolean
     */
    public boolean isPtsOrderExecutionEndType()
    {
        return this.ptsOrderExecutionEndType;
    }

    /**
     * (setPTS出来終了区分)<BR>
     * <BR>
     * パラメータ.PTS出来終了区分をthis.PTS出来終了区分にセットする。<BR>
     * @@param l_blnOrderexecutionEndType - (PTS出来終了区分)<BR>
     * PTS出来終了区分
     */
    public void setPtsOrderExecutionEndType(boolean l_blnOrderexecutionEndType)
    {
        this.ptsOrderExecutionEndType = l_blnOrderexecutionEndType;
    }

    /**
     * is外株日計り取引採用<BR>
     * <BR>
     * this.外株日計り取引採用を返却する。<BR>
     * @@return boolean
     */
    public boolean isFeqDayTradeAdoption()
    {
        return feqDayTradeAdoption;
    }

    /**
     * (set外株日計り取引採用)<BR>
     * <BR>
     * パラメータ.外株日計り取引採用をthis.外株日計り取引採用にセットする。<BR>
     * @@param feqDayTradeAdoption
     */
    public void setFeqDayTradeAdoption(boolean feqDayTradeAdoption)
    {
        this.feqDayTradeAdoption = feqDayTradeAdoption;
    }

}
@
