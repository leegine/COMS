head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMEquityDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品管理（株式）データマネージャ(WEB3AdminPMEquityDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30　@肖志偉(中訊) 仕様変更 モデル106
Revesion History : 2007/04/23　@柴双紅(中訊) 仕様変更 モデル130、135、137
Revesion History : 2007/04/28　@徐宏偉(中訊) 仕様変更 モデル143
Revesion History : 2007/06/04　@柴双紅(中訊) 仕様変更 モデル150
Revesion History : 2007/07/24　@何文敏(中訊) 仕様変更 モデル159
Revesion History : 2007/08/07　@周墨洋(中訊) 仕様変更 モデル160
Revesion History : 2007/08/09　@何文敏(中訊) 仕様変更 モデル161
Revesion History : 2007/08/27  柴双紅(中訊) 仕様変更 モデル163
Revesion History : 2007/09/11  柴双紅(中訊) 仕様変更 モデル164
Revesion History : 2007/10/10　@何文敏(中訊) 仕様変更 モデル166
Revesion History : 2008/01/18　@張騰宇(中訊) 仕様変更 モデル181、182、185
Revesion History : 2008/01/25　@トウ鋒鋼(中訊) 仕様変更 モデル184、187、189
Revesion History : 2008/02/13　@トウ鋒鋼(中訊) 仕様変更 モデル196
Revesion History : 2008/02/27　@趙林鵬(中訊) 仕様変更 モデル200
*/
package webbroker3.eqtypeadmin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceRangeIdDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionParams;
import webbroker3.eqtypeadmin.data.EqtypeProductConditionRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityDeleteFlgDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityForcedSettleReasonDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityLastUpdaterDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySmallItemDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySortKeyItemNameDef;
import webbroker3.eqtypeadmin.define.WEB3AdminPriceRangeValueDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSExecHistory;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReasonUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleSortKeyUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleTemporaryOrderUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccTradeStopInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMUpdateInfo;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.data.EquityLimitPriceRangeMstRow;
import webbroker3.equity.data.EquityTickValuesMstRow;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.data.HostEqtypeSwapRow;
import webbroker3.equity.data.HostEquityOrderExecNotifyRow;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.PtsOrderexecutionEndRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （商品管理（株式）データマネージャ）<BR>
 * <BR>
 * 商品管理（株式）データマネージャ<BR>
 * 商品管理(株式)のデータ作成などを管理するクラス<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminPMEquityDataManager<BR>
 * WEB3AdminPMEquityDataManager class.<BR>
 * <BR>
 * @@author Saravanan Krishnamoorthy
 * @@version 1.0
 */
public class WEB3AdminPMEquityDataManager
{
    /** Log Variable.<BR> */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMEquityDataManager.class);

    /**
     * @@roseuid 41FDBE3C0109
     */
    public WEB3AdminPMEquityDataManager()
    {
    }

    /**
     * （get取引銘柄登録値）<BR>
     * 引数の小項目区分に対応する取引銘柄の項目を返却する。<BR>
     * ※株式銘柄条件設定にて使用。<BR>
     * <BR>
     * １）パラメータ.小項目区分に対応する取引銘柄の項目を<BR>
     * 　@返却する。　@<BR>
     * 　@※取引銘柄の各項目は、全て文字列変換して返却すること。<BR>
     * <BR>
     * 　@パラメータ.小項目区分が、<BR>
     * 　@// 取引規制<BR>
     * 　@["買現物停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買現物停止を返却する。<BR>
     * <BR>
     * 　@["売現物停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売現物停止を返却する。<BR>
     * <BR>
     * 　@["買制度信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買制度信用停止を返却する。<BR>
     * <BR>
     * 　@["売制度信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売制度信用停止を返却する。<BR>
     * <BR>
     * 　@["買建返済制度信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買建返済(売返済)制度信用停止を返却する。<BR>
     * <BR>
     * 　@["売建返済制度信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売建返済(買返済)制度信用停止を返却する。<BR>
     * <BR>
     * 　@["現引制度信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.現引制度信用停止を返却する。<BR>
     * <BR>
     * 　@["現渡制度信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.現渡制度信用停止を返却する。<BR>
     * <BR>
     * 　@["買一般信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買一般信用停止を返却する。<BR>
     * <BR>
     * 　@["売一般信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売一般信用停止を返却する。<BR>
     * <BR>
     * 　@["買建返済一般信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買建返済(売返済)一般信用停止を返却する。<BR>
     * <BR>
     * 　@["売建返済一般信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売建返済(買返済)一般信用停止を返却する。<BR>
     * <BR>
     * 　@["現引一般信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.現引一般信用停止を返却する。<BR>
     * <BR>
     * 　@["現渡一般信用停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.現渡一般信用停止を返却する。<BR>
     * <BR>
     * 　@["買現物停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買現物成行指定停止を返却する。<BR>
     * <BR>
     * 　@["売現物停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売現物成行指定停止を返却する。<BR>
     * <BR>
     * 　@["買制度信用停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買制度信用成行指定停止を返却する。<BR>
     * <BR>
     * 　@["売制度信用停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売制度信用成行指定停止を返却する。<BR>
     * <BR>
     * 　@["買建返済制度信用停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買建返済(売返済)制度信用成行指定停止を返却する。<BR>
     * <BR>
     * 　@["売建返済制度信用停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売建返済(買返済)制度信用成行指定停止を返却する。<BR>
     * <BR>
     * 　@["買一般信用停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買一般信用成行指定停止を返却する。<BR>
     * <BR>
     * 　@["売一般信用停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売一般信用成行指定停止を返却する。<BR>
     * <BR>
     * 　@["買建返済一般信用停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買建返済(売返済)一般信用成行指定停止を返却する。<BR>
     * <BR>
     * 　@["売建返済一般信用停止(成行)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売建返済(買返済)一般信用成行指定停止を返却する。<BR>
     * <BR>
     * 　@["買ミニ株停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買ミニ株停止を返却する。<BR>
     * <BR>
     * 　@["売ミニ株停止"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売ミニ株停止を返却する。<BR>
     * <BR>
     * 　@// 基本情報<BR>
     * 　@["売買単位"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売買単位を返却する。<BR>
     * <BR>
     * 　@["強制限度単位"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.強制限度単位を返却する。<BR>
     * <BR>
     * 　@["取引方式"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.店頭公開区分を返却する。<BR>
     * <BR>
     * 　@["上場区分"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.上場区分 + <BR>
     * 　@　@パラメータ.取引銘柄.新市場区分を返却する。<BR>
     * <BR>
     * 　@["即日入金規制"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.即日入金規制を返却する。<BR>
     * <BR>
     * 　@["株式ミニ投資市場"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.市場コードを返却する。<BR>
     * <BR>
     * 　@// 信用銘柄情報<BR>
     * 　@["制度信用銘柄区分"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.制度信用銘柄区分を返却する。<BR>
     * <BR>
     * 　@["一般信用銘柄区分"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.一般信用銘柄区分を返却する。<BR>
     * <BR>
     * 　@// 委託保証金率<BR>
     * 　@["買保証金率"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買保証金率を返却する。<BR>
     * <BR>
     * 　@["売保証金率"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売保証金率を返却する。<BR>
     * <BR>
     * 　@["買現金保証金率"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.買現金保証金率を返却する。<BR>
     * <BR>
     * 　@["売現金保証金率"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.売現金保証金率を返却する。<BR>
     * <BR>
     * 　@// 値段情報<BR>
     * 　@["基準値（終値）"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.基準値（終値）を返却する。<BR>
     * 
     * 　@// 値段情報<BR>
     * 　@["基準値"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.基準値を返却する。<BR>
     * <BR>
     * 　@["値幅チェック"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.値幅チェック区分を返却する。<BR>
     * <BR>
     * 　@["強制値幅(値幅区分)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.値幅区分を返却する。<BR>
     * <BR>
     * 　@["強制値幅(下限)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.強制値幅(下限値)を返却する。<BR>
     * <BR>
     * 　@["強制値幅(上限)"の場合]<BR>
     * 　@　@パラメータ.取引銘柄.強制値幅(上限値)を返却する。<BR>
     * <BR>
     * 　@["強制値幅"の場合]<BR>
     * 　@　@[①@パラメータ.取引銘柄.値幅区分 == "指定なし"の場合]<BR>
     * 　@　@　@強制値幅 == パラメータ.取引銘柄.値幅区分<BR>
     * <BR>
     * 　@　@[②パラメータ.取引銘柄.値幅区分 == "1/XX"の場合]<BR>
     * 　@　@　@パラメータ.取引銘柄.値幅区分<BR>
     * 　@　@　@+ パラメータ.取引銘柄.強制値幅(下限)<BR>
     * 　@　@　@+ "～"<BR>
     * 　@　@　@+ パラメータ.取引銘柄.値幅区分<BR>
     * 　@　@　@+ パラメータ.取引銘柄.強制値幅(上限)を返却する。<BR>
     * <BR>
     * 　@　@[①@、②以外の場合]<BR>
     * 　@　@　@パラメータ.取引銘柄.強制値幅(下限)<BR>
     * 　@　@　@+ パラメータ.取引銘柄.値幅区分<BR>
     * 　@　@　@+ "～"<BR>
     * 　@　@　@+ パラメータ.取引銘柄.強制値幅(上限)<BR>
     * 　@　@　@+ パラメータ.取引銘柄.値幅区分を返却する。<BR>
     * <BR>
     * 　@["値幅"の場合]<BR>
     * 　@　@１）値幅上限を取得する。<BR>
     * 　@　@　@拡張株式注文マネージャ.get値幅上限値(パラメータ.取引銘柄)<BR>
     * <BR>
     * 　@　@２）値幅下限を取得する。<BR>
     * 　@　@　@拡張株式注文マネージャ.get値幅下限値(パラメータ.取引銘柄)<BR>
     * <BR>
     * 　@　@３）値幅を作成する。<BR>
     * <BR>
     * 　@　@　@値幅 = 値幅下限<BR>
     * 　@　@　@　@　@　@　@+ "円～"<BR>
     * 　@　@　@　@　@　@　@+ 値幅上限<BR>
     * 　@　@　@　@　@　@　@+ "円"<BR>
     * <BR>
     * 　@　@４）作成した値幅を返却する。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getTradedProductRegistrationValue<BR>
     * Return the value of WEB3EquityTradedProduct according to the argument
     * l_strSmallItemDiv<BR>
     * ※Use for eqtypeProductCondition<BR>
     * <BR>
     * １）Return the l_tradedProduct for each parameter.l_strSmallItemDiv. <BR>
     *  ※Each value of the l_tradedProduct is converted to string when it is
     * acquired.<BR>
     * <BR>
     * Compare Parameter.l_strSmallItemDiv<BR>
     *  // Trade Regulation<BR>
     *  [If "Def.BUY_CASH_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.buy_cash_stop.<BR>
     * <BR>
     *  [If "Def.SELL_CASH_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.sell_cash_stop.<BR>
     * <BR>
     *  [If "Def.LONG_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.LONG_CLOSE_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_close_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_CLOSE_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_close_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.LONG_SWAP_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_swap_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_SWAP_MARGIN_SYS_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_swap_margin_sys_stop.<BR>
     * <BR>
     *  [If "Def.LONG_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.LONG_CLOSE_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_close_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_CLOSE_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_close_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.LONG_SWAP_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_swap_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_SWAP_MARGIN_GEN_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_swap_margin_gen_stop.<BR>
     * <BR>
     *  [If "Def.BUY_SPOT_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.buy_spot_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SELL_SPOT_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.sell_spot_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.LONG_MS_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_ms_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_MS_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_ms_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.LONG_CMS_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_cms_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_CMS_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_cms_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.LONG_MG_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_mg_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_MG_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_mg_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.LONG_CMG_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.long_cmg_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.SHORT_CMG_MARKET_ORD_DES_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.short_cmg_market_ord_des_stop.<BR>
     * <BR>
     *  [If "Def.BUY_MINI_STOCK_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.buy_mini_stock_stop.<BR>
     * <BR>
     *  [If "Def.SELL_MINI_STOCK_STOP"]<BR>
     *   Return Parameter.l_tradedProduct.sell_mini_stock_stop.<BR>
     * <BR>
     *  // Basic Info<BR>
     *  [If "Def.LOT_SIZE"]<BR>
     *   Return Parameter.l_tradedProduct.lot_size.<BR>
     * <BR>
     *  [If "Def.COMPULSIVE_LIMITED_UNIT"]<BR>
     *   Return Parameter.l_tradedProduct.compulsive_limited_unit.<BR>
     * <BR>
     *  [If "Def.OPEN_OTC_DIV"]<BR>
     *   Return Parameter.l_tradedProduct.open_otc_div.<BR>
     * <BR>
     *  [If "Def.LIST_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.list_type + <BR>
     *   Return Parameter.l_tradedProduct.new_list_type.<BR>
     * <BR>
     *  [If "Def.TODAY_DEP_FUND_REG"]<BR>
     *   Return Parameter.l_tradedProduct.today_dep_fund_reg.<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_MARKET"]<BR>
     *   Return Parameter.l_tradedProduct.marketCode.<BR>
     * <BR>
     *  // Margin product info<BR>
     *  [If "Def.MARGIN_SYS_PRODUCT_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.margin_sys_product_type.<BR>
     * <BR>
     *  [If "Def.MARGIN_GEN_PRODUCT_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.margin_gen_product_type.<BR>
     * <BR>
     *  // Deposit rate<BR>
     *  [If "Def.LONG_MARGIN_DEPOSIT_RATE"]<BR>
     *   Return Parameter.l_tradedProduct.long_margin_deposit_rate.<BR>
     * <BR>
     *  [If "Def.SHORT_MARGIN_DEPOSIT_RATE"]<BR>
     *   Return Parameter.l_tradedProduct.short_margin_deposit_rate.<BR>
     * <BR>
     *  [If "Def.LONG_CASH_MARGIN_DEPOSIT_RATE"]<BR>
     *   Return Parameter.l_tradedProduct.long_cash_margin_deposit_rate.<BR>
     * <BR>
     *  [If "Def.SHORT_CASH_MARGIN_DEPOSIT_RATE"]<BR>
     *   Return Parameter.l_tradedProduct.short_cash_margin_deposit_rate.<BR>
     * <BR>
     *  // Price Info<BR>
     *  [If "Def.LAST_CLOSING_PRICE"]<BR>
     *   Return Parameter.l_tradedProduct.last_closing_price.<BR>
     * <BR>
     *  [If "Def.PRICE_RANGE_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.price_range_type.<BR>
     * <BR>
     *  [If "Def.PRICE_RANGE_UNIT_TYPE"]<BR>
     *   Return Parameter.l_tradedProduct.price_range_unit_type.<BR>
     * <BR>
     *  [If "Def.LOW_COMPULSIVE_PRICE_RANGE"]<BR>
     *   Return Parameter.l_tradedProduct.low_compulsive_price_range.<BR>
     * <BR>
     *  [If "Def.HIGH_COMPULSIVE_PRICE_RANGE"]<BR>
     *   Return Parameter.l_tradedProduct.high_compulsive_price_range.<BR>
     * <BR>
     *  ["Def.COMPULSIVE_PRICE_RANGE"]<BR>
     *   [①@ If Parameter.l_tradedProduct.price_range_unit_type == "Def.DEFAULT"]<BR>
     *    compulsivePriceRange == Parameter.l_tradedProduct.price_range_unit_type<BR>
     * <BR>
     *   [② If Parameter.l_tradedProduct.price_range_unit_type == "Def.FRACTION"]<BR>
     *    Parameter.l_tradedProduct.price_range_unit_type<BR>
     *    + Parameter.l_tradedProduct.low_compulsive_price_range<BR>
     *    + "～"<BR>
     *    + Parameter.l_tradedProduct.price_range_unit_type<BR>
     *    + Return Parameter.l_tradedProduct.high_compulsive_price_range.<BR>
     * <BR>
     *   [If not ①@ or ②]<BR>
     *    Parameter.l_tradedProduct.low_compulsive_price_range<BR>
     *    + Parameter.l_tradedProduct.price_range_unit_type<BR>
     *    + "～"<BR>
     *    + Parameter.l_tradedProduct.high_compulsive_price_range<BR>
     *    + Return Parameter.l_tradedProduct.price_range_unit_type.<BR>
     * <BR>
     *  [If "Def.PRICE_RANGE"]<BR>
     *   1)Acquire stopHighPrice.<BR>
     *    WEB3EquityOrderManager.getStopHighPrice(Parameter.l_tradedProduct)<BR>
     * <BR>
     *   2)Acquire stopLowPrice.<BR>
     *    WEB3EquityOrderManager.getStopLowPrice(Parameter.l_tradedProduct)<BR>
     * <BR>
     *   3)Create priceRange.<BR>
     * <BR>
     *    priceRange = stopLowPrice<BR>
     *        + "Def.YEN～"<BR>
     *        + stopHighPrice<BR>
     *        + "Def.YEN"<BR>
     * <BR>
     *   4)Return the created price range.<BR>
     * <BR>
     * @@param l_eqtypeTradedProductParams - （取引銘柄オブジェクト）<BR>
     * <BR>
     * l_tradedProduct<BR>
     * <BR>
     * @@param l_strSmallItemDiv - （小項目区分）<BR>
     * ※定義値についてはDBレイアウト<BR>
     * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
     * ----<English>--------------------<BR>
     * l_strSmallItemDiv<BR>
     * ※Refer to the DB layout for the definition value. <BR>
     * 　@"eqtype_product_condition_table.xls"<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 4199B568019D
     */
    public String getTradedProductRegistrationValue(
        EqtypeTradedProductParams l_eqtypeTradedProductParams,
        String l_strSmallItemDiv)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "getTradedProductRegistrationValue(WEB3EquityTradedProduct, String)";
        log.entering(STR_METHOD_NAME);

        Object l_registrationValue = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradedProduct l_tradedProduct = null;

        StringBuffer l_strBuffer = new StringBuffer();

        if (WEB3AdminEquitySmallItemDivDef.BUY_CASH_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.buy_cash_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.SELL_CASH_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.sell_cash_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_margin_sys_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_margin_sys_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_close_margin_sys_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_close_margin_sys_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_swap_margin_sys_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_SYS_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_swap_margin_sys_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_margin_gen_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_close_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_close_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_swap_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_GEN_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_swap_margin_gen_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.BUY_SPOT_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.buy_spot_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SELL_SPOT_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.sell_spot_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_ms_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_ms_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CMS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_cms_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CMS_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_cms_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_mg_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_mg_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CMG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_cmg_market_ord_des_stop;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CMG_MARKET_ORD_DES_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_cmg_market_ord_des_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.buy_mini_stock_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_STOP.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.sell_mini_stock_stop;
        } else if (WEB3AdminEquitySmallItemDivDef.LOT_SIZE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = new Double (l_eqtypeTradedProductParams.lot_size);
        } else if (
            WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.compulsive_limited_unit;
        } else if (WEB3AdminEquitySmallItemDivDef.OPEN_OTC_DIV.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.open_otc_div;
        } else if (WEB3AdminEquitySmallItemDivDef.LIST_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.list_type + l_eqtypeTradedProductParams.new_list_type;
        } else if (WEB3AdminEquitySmallItemDivDef.TODAY_DEP_FUND_REG.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.today_dep_fund_reg;
        } else if (WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET.equals(l_strSmallItemDiv))
        {
            long l_lngMarketId = l_eqtypeTradedProductParams.getMarketId();
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);

            String l_strMarketCode = l_market.getMarketCode();

            l_registrationValue = l_strMarketCode;
        } else if (
            WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_PRODUCT_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.margin_sys_product_type;
        } else if (
            WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_PRODUCT_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.margin_gen_product_type;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_DEPOSIT_RATE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_margin_deposit_rate;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_DEPOSIT_RATE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_margin_deposit_rate;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LONG_CASH_MARGIN_DEPOSIT_RATE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.long_cash_margin_deposit_rate;
        } else if (
            WEB3AdminEquitySmallItemDivDef.SHORT_CASH_MARGIN_DEPOSIT_RATE.equals(
                l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.short_cash_margin_deposit_rate;
        } else if (WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = new Double(l_eqtypeTradedProductParams.last_closing_price);
        } else if (WEB3AdminEquitySmallItemDivDef.BASE_PRICE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = new Double(l_eqtypeTradedProductParams.base_price);
        } else if (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.price_range_type;
        } else if (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE.equals(l_strSmallItemDiv))
        {
            l_registrationValue = l_eqtypeTradedProductParams.price_range_unit_type;
        } else if (
            WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.low_compulsive_price_range;
        } else if (
            WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE.equals(l_strSmallItemDiv))
        {
            l_registrationValue =
                l_eqtypeTradedProductParams.high_compulsive_price_range;
        } else if (WEB3AdminEquitySmallItemDivDef.COMPULSIVE_PRICE_RANGE.equals(l_strSmallItemDiv))
        {
        	if (l_eqtypeTradedProductParams.price_range_unit_type == null)
        	{
				l_registrationValue = null;
        	}else
        	{
				String l_strPriceRangeValue = new String();
	        
				if (l_eqtypeTradedProductParams.price_range_unit_type.equals(WEB3PriceRangeIdDef.YEN))
				{
					l_strPriceRangeValue = WEB3AdminPriceRangeValueDef.YEN;
				} else if (l_eqtypeTradedProductParams.price_range_unit_type.equals(WEB3PriceRangeIdDef.PERCENT))
				{
					l_strPriceRangeValue = WEB3AdminPriceRangeValueDef.PERCENT;
				} else if (l_eqtypeTradedProductParams.price_range_unit_type.equals(WEB3PriceRangeIdDef.FRACTION))
				{
					l_strPriceRangeValue = WEB3AdminPriceRangeValueDef.FRACTION;
				}
	
	            if (WEB3PriceRangeIdDef.DEFAULT.equals(l_eqtypeTradedProductParams.price_range_unit_type))
	            {
	                l_registrationValue = l_eqtypeTradedProductParams.price_range_unit_type;
	            } else if (WEB3PriceRangeIdDef.FRACTION.equals(l_eqtypeTradedProductParams.price_range_unit_type))
	            {
	                l_strBuffer.append(l_strPriceRangeValue);
	                l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_eqtypeTradedProductParams.low_compulsive_price_range.doubleValue()));
	                l_strBuffer.append("／");
	                l_strBuffer.append(l_strPriceRangeValue);
	                l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_eqtypeTradedProductParams.high_compulsive_price_range.doubleValue()));
	
	                l_registrationValue = l_strBuffer.toString();
	            } else
	            {
	                l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_eqtypeTradedProductParams.low_compulsive_price_range.doubleValue()));
	                l_strBuffer.append(l_strPriceRangeValue);
	                l_strBuffer.append("／");
	                l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_eqtypeTradedProductParams.high_compulsive_price_range.doubleValue()));
	                l_strBuffer.append(l_strPriceRangeValue);
	
	                l_registrationValue = l_strBuffer.toString();
	            }
        	}
        } else if (WEB3AdminEquitySmallItemDivDef.PRICE_RANGE.equals(l_strSmallItemDiv))
        {
            double l_dblStopHighPrice = 0;
            double l_dblStopLowPrice = 0;
            long l_lngProductId = 0;
            long l_lngMarketId = 0;
            WEB3EquityOrderManager l_equityOrderManager = null;
            WEB3EquityProductManager l_productManager = null;
            TradingModule l_tradingModule = null;

            l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

            l_equityOrderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            l_productManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

            l_lngProductId = l_eqtypeTradedProductParams.product_id;
            l_lngMarketId = l_eqtypeTradedProductParams.market_id;

            l_tradedProduct =
                (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
                    l_lngProductId,
                    l_lngMarketId);

            l_dblStopHighPrice = l_equityOrderManager.getStopHighPrice(l_tradedProduct);
            l_dblStopLowPrice = l_equityOrderManager.getStopLowPrice(l_tradedProduct);

            l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_dblStopLowPrice));
            l_strBuffer.append(WEB3AdminPriceRangeValueDef.YEN);
            l_strBuffer.append("～");
            l_strBuffer.append(WEB3StringTypeUtility.formatNumber(l_dblStopHighPrice));
            l_strBuffer.append(WEB3AdminPriceRangeValueDef.YEN);

            l_registrationValue = l_strBuffer.toString();
        }

        log.exiting(STR_METHOD_NAME);

        if (l_registrationValue == null)
        {
            return null;
        }
        else if (l_registrationValue instanceof Double)
        {
            return WEB3StringTypeUtility.formatNumber(((Double)l_registrationValue).doubleValue());
        }
        else if (l_registrationValue instanceof BooleanEnum)
        {
            return String.valueOf(((BooleanEnum)l_registrationValue).intValue());
        }
        else
        {
	        return l_registrationValue.toString();
        }
    }

    /**
     * （set取引銘柄登録値）<BR>
     * 引数の取引銘柄更新情報に変更後登録値をセットする。<BR>
     * ※株式銘柄条件設定にて使用。<BR>
     * <BR>
     * １）パラメータ.取引銘柄 == nullの場合、処理を終了する。<BR>
     * <BR>
     * ２）市場ごとの更新情報を取得する。<BR>
     * 　@パラメータ.取引銘柄更新情報.get()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[get()にセットするパラメータ](共通)<BR>
     * 　@　@　@key：　@パラメータ.市場コード<BR>
     * <BR>
     * ３）２）の戻り値 == nullの場合、更新情報を新規作成する。<BR>
     * <BR>
     * 　@３－１）更新情報インスタンスを生成する。<BR>
     * 　@３－２）生成した更新情報に以下のプロパティセットを行う。<BR>
     * <BR>
     * 　@　@　@　@　@更新情報.ID = パラメータ.取引銘柄.取引銘柄ID<BR>
     * 　@　@　@　@　@更新情報.有効日 = パラメータ.取引銘柄.有効日<BR>
     * <BR>
     * 　@３－３）パラメータ.取引銘柄更新情報.put()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@　@　@　@key：　@パラメータ.市場コード<BR>
     * 　@　@　@　@　@　@value：　@プロパティセットした更新情報インスタンス<BR>
     * <BR>
     * 　@３－４）パラメータ.取引銘柄更新情報.get()メソッドをコールする。<BR>
     * 　@　@　@　@　@上記get()メソッドの戻り値を市場ごとの更新情報とする。<BR>
     * 　@　@　@　@※更新情報を新規作成したの場合、<BR>
     * 　@　@　@　@　@以降の処理での「２）の戻り値」という記述は、<BR>
     * 　@　@　@　@　@上記get()メソッドの戻り値を指すこととする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@[get()にセットするパラメータ]<BR>
     * 　@　@　@　@　@　@　@　@key：　@パラメータ.市場コード<BR>
     * <BR>
     * ４）登録値をセットする。<BR>
     * 　@[パラメータ.小項目区分 == "上場区分"の場合]<BR>
     * 　@　@①@上場区分の更新列/値をセット<BR>
     * 　@　@　@２）の戻り値.更新列and値.put()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@　@key：　@パラメータ.更新列名<BR>
     * 　@　@　@　@value：　@パラメータ.変更後登録値の1byte目の文字<BR>
     * <BR>
     * 　@　@②新市場区分の更新列/値をセット。<BR>
     * 　@　@　@２）の戻り値.更新列and値.put()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@　@key：　@"新市場区分"(new_list_type)<BR>
     * 　@　@　@　@value：　@パラメータ.変更後登録値の2byte目の文字<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@２）の戻り値.更新列and値.put()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@　@　@key：　@パラメータ.更新列名<BR>
     * 　@　@　@　@　@value：　@パラメータ.変更後登録値<BR>
     * <BR>
     * ５）更新者コードをセットする。<BR>
     * 　@２）の戻り値.更新列and値.put()メソッドをコールする。<BR>
     * <BR>
     * 　@[put()にセットするパラメータ(更新者コード)]<BR>
     * 　@　@key：　@"更新者コード"(last_updater)<BR>
     * 　@　@value：　@パラメータ.更新者コード<BR>
     * <BR>
     * ６）更新日付をセットする。<BR>
     * 　@２）の戻り値.更新列and値.put()メソッドをコールする。<BR>
     * <BR>
     * 　@[put()にセットするパラメータ(更新日付)]<BR>
     * 　@　@key：　@"更新日付"(last_updated_timestamp)<BR>
     * 　@　@value：　@現在時刻(*1)<BR>
     * <BR>
     * (*1)現在時刻・・・<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG)<BR>
     * にて取得した現在時刻<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setTradedProductRegistrationValue<BR>
     * Set  l_productUpdateInfo into the argument l_aftBizDateRegistData<BR>
     * ※Set with eqtypeProductCondition<BR>
     * <BR>
     * 1)If Parameter.l_tradedProduct == null, end process.<BR>
     * <BR>
     * 2)Acquire the UpdateInfo for each market.<BR>
     *  Call Parameter.l_productUpdateInfo.get() method.<BR>
     * <BR>
     *   [Parameter to set in get()](common)<BR>
     *    key: Parameter.marketCode<BR>
     * <BR>
     * 3) If return value == null、Create a new updateInfo<BR>
     * <BR>
     *  3-1)Generate UpdateInfo instance.<BR>
     *  3-2)Set the property of the generated UpdateInfo as follows.<BR>
     * <BR>
     *      UpdateInfo.id = Parameter.l_tradedProduct.traded_product_id<BR>
     *      UpdateInfo.expirationDate =
     * Parameter.l_tradedProduct.valid_until_biz_date<BR>
     * <BR>
     *  3-3)Call Parameter.tradeProductUpdateInfo.put() method.<BR>
     * <BR>
     *      [Parameter to set into put()]<BR>
     *       key: Parameter.marketCode<BR>
     *       value: The UpdateInfo instance with the property set.<BR>
     * <BR>
     *  3-4)Call Parameter.tradeProductUpdateInfo.get() method.<BR>
     *      Use the return value of the get() method above to the updateInfo of each
     * market<BR>
     *     ※If a new updateInfo has been created、<BR>
     *      From here on the description [Return value of 2)] refers to the Return
     * value of the<BR>
     *      above method.<BR>
     * <BR>
     *        [Set the parameter of get()]<BR>
     *         key: Parameter.marketCode<BR>
     * <BR>
     * 4)Set registValue<BR>
     *  [If Parameter.l_strSmallItemDiv == "Def.LIST_TYPE"]<BR>
     *   ①@Set the updateRowAndValue of list_type.<BR>
     *    Call return value of2).updateRowAndValue.put().<BR>
     * <BR>
     *    [The parameter to set into put()]<BR>
     *     key: Parameter.updateRowName<BR>
     *     value: The 1st byte of Parameter.l_aftBizDateRegistData<BR>
     * <BR>
     *   ②Set the updateRowAndValue of  newMarketDiv<BR>
     *    Call Return value of 2).updateRowAndValue.put()<BR>
     * <BR>
     *    [Parameter to set into put()]<BR>
     *     key: "new_list_type"(new_list_type)<BR>
     *     value: The 2nd byte of Parameter.l_aftBizDateRegistData<BR>
     * <BR>
     *  [ELSE]<BR>
     *   Call Return value of 2)updateRowAndValue.put()<BR>
     * <BR>
     *     [Parameter to set into put()<BR>
     *      key: Parameter. updateRowName<BR>
     *      value: Parameter.aftBizDateRegistData<BR>
     * <BR>
     * ５）Set the last_updater<BR>
     *  Call Return value of 2)updateRowAndValue.put() method.<BR>
     * <BR>
     *  [Parameter to set into put()]<BR>
     *   key: "last_updater"(last_updater)<BR>
     *   value: Parameter.lastUpdater<BR>
     * <BR>
     * 6)Set updateDate.<BR>
     *  Call Return value of 2)updateRowAndValue.put() method<BR>
     * <BR>
     *  [Parameter to set into put()]<BR>
     *   key: "last_updated_timestamp"(last_updated_timestamp)<BR>
     *   value: time stamp(*1)<BR>
     * <BR>
     * (*1)timeStamp<BR>
     *  The timestamp aquired at
     * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
     * nt.TIMESTAMP_TAG)<BR>
     * <BR>
     * @@param l_productUpdateInfo - （取引銘柄更新情報）<BR>
     * <BR>
     * l_productUpdateInfo<BR>
     * @@param l_eqtypeTradedProductParams - （取引銘柄）<BR>
     * <BR>
     * l_tradedProduct<BR>
     * <BR>
     * @@param l_strSmallItemDiv - （小項目区分）<BR>
     * ※定義値についてはDBレイアウト<BR>
     * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
     * ----<English>--------------------<BR>
     * l_strSmallItemDiv<BR>
     * ※Refer to the DB layout for the definition value. <BR>
     * 　@"eqtype_product_condition_table.xls"<BR>
     * @@param l_strMarketCode - （市場コード）<BR>
     * <BR>
     * l_strMarketCode<BR>
     * <BR>
     * @@param l_updateColumnName - （更新列名）<BR>
     * <BR>
     * l_updateRowName<BR>
     * <BR>
     * @@param l_aftBizDateRegistData - （変更後登録値）<BR>
     * <BR>
     * l_aftBizDateRegistData<BR>
     * @@param l_lastUpdater - （更新者コード）<BR>
     * <BR>
     * l_lastUpdater<BR>
     * @@roseuid 4199B89300D2
     */
    public void setTradedProductRegistrationValue(
        HashMap l_productUpdateInfo,
        EqtypeTradedProductParams l_eqtypeTradedProductParams,
        String l_strSmallItemDiv,
        String l_strMarketCode,
        String l_updateColumnName,
        String l_aftBizDateRegistData,
        String l_lastUpdater)
    {
        final String STR_METHOD_NAME =
            "setTradedProductRegistrationValue(HashMap, WEB3EquityTradedProduct, "
                + "String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        Timestamp l_lastUpdatedTimestamp = null;

        // 1 Checking the l_tradedProduct object is not equal to null
        if (l_eqtypeTradedProductParams != null)
        {
            WEB3AdminPMUpdateInfo l_updateInfo = null;

            // 2
            l_updateInfo = (WEB3AdminPMUpdateInfo) l_productUpdateInfo.get(l_strMarketCode);

            // 3 Checking return value of 2 is null
            if (l_updateInfo == null)
            {
                // 3.1
                l_updateInfo = new WEB3AdminPMUpdateInfo();

                // 3.2
                l_updateInfo.id = l_eqtypeTradedProductParams.getTradedProductId();

                l_updateInfo.expirationDate = l_eqtypeTradedProductParams.valid_until_biz_date;

                // 3.3
                l_productUpdateInfo.put(l_strMarketCode, l_updateInfo);

                // 3.4
                l_updateInfo = (WEB3AdminPMUpdateInfo) l_productUpdateInfo.get(l_strMarketCode);
            }

            // 4 Checking WEB3AdminEquitySmallItemDivDef.LIST_TYPE is equals with l_strSmallItemDiv
            if (WEB3AdminEquitySmallItemDivDef.LIST_TYPE.equals(l_strSmallItemDiv))
            {
                // 4.1
                l_updateInfo.updateRowAndValue.put(
                    l_updateColumnName,
                    l_aftBizDateRegistData.substring(0, 1));

                // 4.2
                l_updateInfo.updateRowAndValue.put(
                    "new_list_type",
                    l_aftBizDateRegistData.substring(1, 2));

            } else
            {
                l_updateInfo.updateRowAndValue.put(l_updateColumnName, l_aftBizDateRegistData);
            }

            // 5
            l_updateInfo.updateRowAndValue.put(
                WEB3AdminEquityLastUpdaterDef.LAST_UPDATER,
                l_lastUpdater);

            // 6
            l_lastUpdatedTimestamp =
                (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            l_updateInfo.updateRowAndValue.put(
                WEB3AdminEquityLastUpdaterDef.LAST_UPDATED_TIMESTAMP,
                l_lastUpdatedTimestamp);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （get取引停止区分）<BR>
     * 引数の注文種別に該当する顧客銘柄別取引停止Paramsの<BR>
     * 項目を返却する。<BR>
     * ※顧客銘柄別取引停止にて使用。<BR>
     * <BR>
     * 　@パラメータ.注文種別が、<BR>
     * 　@["株式現物買注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.買現物取引停止を返却する。<BR>
     * <BR>
     * 　@["株式現物売注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.売現物取引停止を返却する。<BR>
     * <BR>
     * 　@["株式信用新規買建注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.買新規建取引停止を返却する。<BR>
     * <BR>
     * 　@["株式信用新規売建注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.売新規建取引停止を返却する。<BR>
     * <BR>
     * 　@["株式信用新規買建返済注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.買建返済(売返済)取引停止を返却する。<BR>
     * <BR>
     * 　@["株式信用新規売建返済注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.売建返済(買返済)取引停止を返却する。<BR>
     * <BR>
     * 　@["株式信用現引注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.現引取引停止を返却する。<BR>
     * <BR>
     * 　@["株式信用規現渡注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.現渡取引停止を返却する。<BR>
     * <BR>
     * 　@["株式ミニ株買注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.買ミニ株取引停止を返却する。<BR>
     * <BR>
     * 　@["株式ミニ株売注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.売ミニ株取引停止を返却する。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getTradeStopDiv<BR>
     * Retun the value item of l_accountProductOrderStopParams<BR>
     * specific to the argument l_orderType.<BR>
     * <BR>
     * ※Use at the account product order stop<BR>
     * <BR>
     * Compare the following with Parameter.l_orderType<BR>
     *  [If "Def.EQUITY_BUY"]<BR>
     *   Acquire Parameter.l_accountProductOrderStopParams.stop_trade_div_buy_cash<BR>
     * <BR>
     *  [If "Def.EQUITY_SELL"]<BR>
     *   Acquire Parameter.l_accountProductOrderStopParams.stop_trade_div_sell_cash<BR>
     * <BR>
     *  [If "Def.MARGIN_LONG"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_trade_div_long_margin<BR>
     * <BR>
     *  [If "Def.MARGIN_SHORT"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_trade_div_short_margin<BR>
     * <BR>
     *  [If "Def.CLOSE_MARGIN_LONG"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_div_long_close_margin<BR>
     * <BR>
     *  [If "Def.CLOSE_MARGIN_SHORT"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_div_short_close_margin<BR>
     * <BR>
     *  [If "Def.SWAP_MARGIN_LONG"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_div_long_swap_margin<BR>
     * <BR>
     *  [If "Def.SWAP_MARGIN_SHORT"]<BR>
     *   Acquire
     * Parameter.l_accountProductOrderStopParams.stop_div_short_swap_margin<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_BUY"]<BR>
     *   Acquire Parameter.l_accountProductOrderStopParams.stop_div_buy_mini_stock<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_SELL"]<BR>
     *   Acquire Parameter.l_accountProductOrderStopParams.stop_div_sell_mini_stock<BR>
     * <BR>
     * @@param l_orderType - （注文種別）<BR>
     * (OrderTypeEnumにて定義)<BR>
     * <BR>
     * l_orderType<BR>
     * (Define it with OrderTypeEnum. )<BR>
     * @@param l_accountProductOrderStopParams - （AccountProductOrderStopParams）<BR>
     * <BR>
     * l_accountProductOrderStopParams<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41989CAF03BB
     */
    public String getTradeStopDiv(
        OrderTypeEnum l_orderType,
        AccountProductOrderStopParams l_accountProductOrderStopParams)
    {
        final String STR_METHOD_NAME =
            "getTradeStopDiv(OrderTypeEnum, AccountProductOrderStopParams)";
        log.entering(STR_METHOD_NAME);

        String l_strTradeStopDiv = null;

        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_trade_div_buy_cash;
        } else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_trade_div_sell_cash;
        } else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_trade_div_long_margin;
        } else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_trade_div_short_margin;
        } else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_long_close_margin;
        } else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_short_close_margin;
        } else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_long_swap_margin;
        } else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_short_swap_margin;
        } else if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_buy_mini_stock;
        } else if (OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderType))
        {
            l_strTradeStopDiv = l_accountProductOrderStopParams.stop_div_sell_mini_stock;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strTradeStopDiv;
    }

    /**
     * （set取引停止区分）<BR>
     * 引数の注文種別に該当する顧客銘柄別取引停止Paramsの<BR>
     * 項目に引数の取引停止区分をセットする。<BR>
     * ※顧客銘柄別取引停止にて使用。<BR>
     * <BR>
     * パラメータ.注文種別が、<BR>
     * 　@["株式現物買注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.買現物取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * 　@["株式現物売注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.売現物取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * 　@["株式信用新規買建注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.買新規建取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * 　@["株式信用新規売建注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.売新規建取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * 　@["株式信用新規買建返済注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.買建返済(売返済)取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * 　@["株式信用新規売建返済注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.売建返済(買返済)取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * 　@["株式信用現引注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.現引取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * 　@["株式信用規現渡注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.現渡取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * 　@["株式ミニ株買注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.買ミニ株取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * 　@["株式ミニ株売注文"の場合]<BR>
     * 　@　@パラメータ.顧客銘柄別取引停止Params.売ミニ株取引停止 =<BR>
     * 　@　@　@パラメータ.取引停止区分<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * setTradeStopDiv<BR>
     * Set the specific return value of l_accountProductOrderStopParams.stop_trade_dev
     * according to l_orderType<BR>
     * ※Use at account product order stop<BR>
     * <BR>
     * Compare the following with Parameter.l_orderType<BR>
     *  [If "Def.EQUITY_BUY"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_trade_div_buy_cash =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.EQUITY_SELL"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_trade_div_sell_cash =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.MARGIN_LONG"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_trade_div_long_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.MARGIN_SHORT"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_trade_div_short_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.CLOSE_MARGIN_LONG"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_long_close_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.CLOSE_MARGIN_SHORT"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_short_close_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.SWAP_MARGIN_LONG"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_long_swap_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.SWAP_MARGIN_SHORT"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_short_swap_margin =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_BUY"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_buy_mini_stock =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     *  [If "Def.MINI_STOCK_SELL"]<BR>
     *   Parameter.l_accountProductOrderStopParams.stop_div_sell_mini_stock =<BR>
     *     Parameter.l_tradeStopDiv<BR>
     * <BR>
     * @@param l_orderType - （注文種別）<BR>
     * (OrderTypeEnumにて定義)<BR>
     * ----<English>--------------------<BR>
     * l_orderType<BR>
     * (Define it with OrderTypeEnum. ))<BR>
     * <BR>
     * @@param l_accountProductOrderStopParams - （顧客銘柄別取引停止Params）<BR>
     * ----<English>--------------------<BR>
     * l_accountProductOrderStopParams<BR>
     * <BR>
     * @@param l_tradeStopDiv - （取引停止区分）<BR>
     * ----<English>--------------------<BR>
     * l_tradeStopDiv<BR>
     * <BR>
     * @@roseuid 4198A1CC026A
     */
    public void setTradeStopDiv(
        OrderTypeEnum l_orderType,
        AccountProductOrderStopParams l_accountProductOrderStopParams,
        String l_tradeStopDiv)
    {
        final String STR_METHOD_NAME =
            "setTradeStopDiv(OrderTypeEnum, AccountProductOrderStopParams, String)";
        log.entering(STR_METHOD_NAME);

        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_trade_div_buy_cash = l_tradeStopDiv;
        } else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_trade_div_sell_cash = l_tradeStopDiv;
        } else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_trade_div_long_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_trade_div_short_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_long_close_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_short_close_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_long_swap_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_short_swap_margin = l_tradeStopDiv;
        } else if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_buy_mini_stock = l_tradeStopDiv;
        } else if (OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderType))
        {
            l_accountProductOrderStopParams.stop_div_sell_mini_stock = l_tradeStopDiv;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （create顧客銘柄別取引停止情報）<BR>
     * 引数の顧客銘柄別取引停止Paramsより顧客銘柄別取引停止情報を作成する。<BR>
     * ※顧客銘柄別取引停止にて使用。<BR>
     * <BR>
     * １）顧客銘柄別取引停止情報インスタンスを生成する。<BR>
     * <BR>
     * ２）１）にて生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@部店コード = 部店(*1).部店コード<BR>
     * 　@顧客コード = 顧客(*2).顧客コードの先頭6byte<BR>
     * 　@顧客名 = 顧客(*2).顧客名<BR>
     * 　@銘柄コード = 株式銘柄(*3).銘柄コード<BR>
     * 　@銘柄名 = 株式銘柄(*3).銘柄名<BR>
     * 　@有効期限From = パラメータ.顧客銘柄別取引停止Params.有効期限From<BR>
     * 　@有効期限To = パラメータ.顧客銘柄別取引停止Params.有効期限To<BR>
     * 　@理由 = パラメータ.顧客銘柄別取引停止Params.取引停止理由<BR>
     * <BR>
     * ３）顧客取引停止情報を格納するArrayListを生成する。<BR>
     * <BR>
     * ４）顧客取引停止情報一覧を作成する。<BR>
     * 　@処理対象の注文種別(*4)について、以下の処理を繰り返す。<BR>
     * 　@４－１）顧客取引停止情報インスタンスを生成する。<BR>
     * <BR>
     * 　@４－２）生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@注文種別 = 処理対象の注文種別<BR>
     * 　@　@　@取引停止区分 = this.get取引停止区分()の戻り値<BR>
     * <BR>
     * 　@　@　@[get取引停止区分()にセットするパラメータ]<BR>
     * 　@　@　@　@注文種別：　@処理対象の注文種別<BR>
     * 　@　@　@　@顧客銘柄別取引停止Params：　@パラメータ.顧客銘柄別取引停止Params<BR>
     * <BR>
     * 　@４－３）生成したArrayListにプロパティセットした顧客取引停止情報を<BR>
     * 　@　@　@　@　@追加する。<BR>
     * <BR>
     * ５）顧客取引停止情報一覧を顧客銘柄別取引停止情報インスタンスに<BR>
     * 　@セットする。<BR>
     * <BR>
     * 　@顧客取引停止情報一覧 = 生成したArrayList.toArray()<BR>
     * <BR>
     * ６）プロパティセットした顧客銘柄別取引停止情報を返却する。<BR>
     * <BR>
     * (*1)部店<BR>
     * 　@パラメータ.顧客銘柄別取引停止Params.部店IDに該当する部店オブジェクト<BR>
     * (*2)顧客<BR>
     * 　@パラメータ.顧客銘柄別取引停止Params.顧客IDに該当する顧客オブジェクト<BR>
     * (*3)株式銘柄<BR>
     * 　@パラメータ.銘柄銘柄別取引停止Params.銘柄IDに該当する株式銘柄オブジェクト<BR>
     * 　@※上記銘柄ID == 0の場合、銘柄コード、銘柄名にはnullをセット。<BR>
     * (*4)処理対象の注文種別<BR>
     * 　@※以下の値を上から順に処理する。<BR>
     * 　@・"株式現物買注文"<BR>
     * 　@・"株式現物売注文"<BR>
     * <BR>
     * 　@[部店.制度信用実施区分 == "実施"または<BR>
     * 　@　@部店.一般信用実施区分 == "実施"の場合]<BR>
     * 　@上記に続けて以下の注文種別も処理する。<BR>
     * 　@　@・"株式信用新規買建注文"<BR>
     * 　@　@・"株式信用新規売建注文"<BR>
     * 　@　@・"株式信用新規買建返済注文"<BR>
     * 　@　@・"株式信用新規売建返済注文"<BR>
     * 　@　@・"株式信用現引注文"<BR>
     * 　@　@・"株式信用規現渡注文"<BR>
     * <BR>
     * 　@[部店.ミニ株実施区分 == "実施"の場合]<BR>
     * 　@上記に続けて以下の注文種別も処理する。<BR>
     * 　@　@・"株式ミニ株買注文"<BR>
     * 　@　@・"株式ミニ株売注文"<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createAccProductTradeStopInfoUnit<BR>
     * Create WEB3AdminPMAccProductTradeStopInfoUnit from the argument
     * l_accountProductOrderStopParams<BR>
     * ※Use at AccountProductOrderStop.<BR>
     * <BR>
     * 1）Generate WEB3AdminPMAccProductTradeStopInfoUnit instance<BR>
     * <BR>
     * 2)Set the property of the instance that has been generated at 1)<BR>
     * 　@branchCode = branch(*1).branchCode<BR>
     * 　@accountCode = account(*2).the first 6 bytes of accountCode<BR>
     * 　@accountName = account(*2).accountName<BR>
     * 　@productCode = equityProduct(*3).productCode<BR>
     * 　@productName = equityProduct(*3).productName<BR>
     * 　@expirationStartDate =
     * Parameter.accountProductOrderStopParams.apply_start_date<BR>
     * 　@expirationEndDate = Parameter.accountProductOrderStopParams.apply_end_date<BR>
     * 　@reason = Parameter.accountProductOrderStopParams.stop_trade_reason<BR>
     * <BR>
     * 3)Generate the ArrayList to store tradeStopInfo.<BR>
     * <BR>
     * 4)Create WEB3AdminPMAccTradeStopInfoUnit list.<BR>
     * 　@Loop the following process for the specific orderType<BR>
     * 　@4-1)Generate WEB3AdminPMAccTradeStopInfoUnit instance.<BR>
     * <BR>
     * 　@4-2)Set the property of the generated instance<BR>
     * 　@　@　@orderType = the specific ordertype<BR>
     * 　@　@　@tradeStopDiv = the return value of this.getTradeStopDiv()<BR>
     * <BR>
     * 　@　@　@[Set the parameter of getTradeStopDiv()]<BR>
     * 　@　@　@　@l_orderType: the specific orderType<BR>
     * 　@　@　@　@l_accountProductOrderStopParams:
     * Parameter.accountProductOrderStopParams<BR>
     * <BR>
     * 　@4-3)Add the tradeStopInfoUnit to the generated ArrayList property<BR>
     * <BR>
     * 5)Set tradeStopInfoUnitList into the WEB3AdminPMAccProductTradeStopInfoUnit
     * instance<BR>
     * <BR>
     * 　@tradeStopInfoUnitList = Generated ArrayList.toArray()<BR>
     * <BR>
     * 6)Return the WEB3AdminPMAccProductTradeStopInfoUnit with the propertys set.<BR>
     * <BR>
     * (*1)brach<BR>
     * 　@The object that corresponds with
     * Parameter.accountProductOrderStopParams.branchID<BR>
     * (*2)account<BR>
     * 　@The object that corresponds with
     * Parameter.accountProductOrderStopParams.accountID<BR>
     * (*3)equity_product<BR>
     * 　@The object that corresponds with
     * Parameter.accountProductOrderStopParams.productID<BR>
     * 　@※If the above product_id ==0<BR>
     *        Set the following<BR>
     *          ・productCode = null<BR>
     *          ・productName = null<BR>
     * (*4)The specific orderType<BR>
     * 　@※Process in the following order<BR>
     * 　@・"Def.EQUITY_BUY"<BR>
     * 　@・"Def.EQUITY_SELL"<BR>
     * <BR>
     * 　@[If branch.margin_sys_div == "Def.ENFORCEMENT" or<BR>
     * 　@　@branch.margin_gen_div == "Def.ENFORCEMENT"]<BR>
     * 　@Perform the following process of orderType after the above process<BR>
     * 　@　@・"Def.MARGIN_LONG"<BR>
     * 　@　@・"Def.MARGIN_SHORT"<BR>
     * 　@　@・"Def.CLOSE_MARGIN_LONG"<BR>
     * 　@　@・"Def.CLOSE_MARGIN_SHORT"<BR>
     * 　@　@・"Def.SWAP_MARGIN_LONG"<BR>
     * 　@　@・"SWAP_MARGIN_SHORT"<BR>
     * <BR>
     * 　@[If branch.mstk_div == "Def.ENFORCEMENT"]<BR>
     * 　@Perform the following process of orderType after the above process<BR>
     * 　@　@・"Def.MF_BUY"<BR>
     * 　@　@・"Def.MF_SELL"<BR>
     * <BR>
     * @@param l_accountProductOrderStopParams - （顧客銘柄別取引停止Params）<BR>
     * ----<English>--------------------<BR>
     * l_accountProductOrderStopParams<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopInfoUnit
     * @@throws DataQueryException Data Query Exception
     * @@throws DataNetworkException Data Network Exception
     * @@throws DataFindException Exception in aquiring the Data
     * @@throws NotFoundException Not Found Exception
     * @@roseuid 41989F7A00C8
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit createAccProductTradeStopInfoUnit(
        AccountProductOrderStopParams l_accountProductOrderStopParams)
        throws DataFindException, DataNetworkException, DataQueryException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "createAccProductTradeStopInfoUnit(AccountProductOrderStopParams)";
        long l_lngAccountId = 0;

        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopInfoUnit l_productTradeStopInfoUnit =
            new WEB3AdminPMAccProductTradeStopInfoUnit();

        /*
         * Set All the property in l_productTradeStopInfoUnit
         * 2.1. Set BranchCode
         */
        BranchRow l_branchRow =
            BranchDao.findRowByBranchId(l_accountProductOrderStopParams.branch_id);
        l_productTradeStopInfoUnit.branchCode = l_branchRow.getBranchCode();

        // 2.2
        l_lngAccountId = l_accountProductOrderStopParams.account_id;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount) l_accountManager.getMainAccount(l_lngAccountId);
        // First 6 bytes of accountCode
        l_productTradeStopInfoUnit.accountCode = l_mainAccount.getAccountCode().substring(0, 6);
        l_productTradeStopInfoUnit.accountName = l_mainAccount.getDisplayAccountName();

        // 2.3. Set ProductCode and ProductName
        if (l_accountProductOrderStopParams.product_id != 0)
        {
            EqtypeProductRow l_eqtypeProductRow =
                EqtypeProductDao.findRowByProductId(l_accountProductOrderStopParams.product_id);
            l_productTradeStopInfoUnit.productCode = l_eqtypeProductRow.getProductCode();
            l_productTradeStopInfoUnit.productName = l_eqtypeProductRow.getStandardName();
        }

        // 2.4 Set expirationStartDate
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern("yyyyMMdd");
				
        l_productTradeStopInfoUnit.expirationStartDate =
		    formatter.format(l_accountProductOrderStopParams.apply_start_date);

        // 2.5 Set expirationEndDate
        l_productTradeStopInfoUnit.expirationEndDate =
		    formatter.format(l_accountProductOrderStopParams.apply_end_date);

        // 2.6 Set reason
        l_productTradeStopInfoUnit.reason = l_accountProductOrderStopParams.stop_trade_reason;

        // 3) Generate the ArrayList to store tradeStopInfo.
        List l_lisTradeStopInfo = new ArrayList();

        // 4-1) Generate WEB3AdminPMAccTradeStopInfoUnit instance.
        WEB3AdminPMAccTradeStopInfoUnit l_tradeStopInfoUnit = null;

        List l_lstOrderTypeEnum = new ArrayList();

        l_lstOrderTypeEnum.add(OrderTypeEnum.EQUITY_BUY);

        l_lstOrderTypeEnum.add(OrderTypeEnum.EQUITY_SELL);

        /*
         * When branch.margin_sys_div == "Def.ENFORCEMENT"
         *      or branch.margin_gen_div == "Def.ENFORCEMENT"
         */
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginSysDiv())
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginGenDiv()))
        {
            l_lstOrderTypeEnum.add(OrderTypeEnum.MARGIN_LONG);
            l_lstOrderTypeEnum.add(OrderTypeEnum.MARGIN_SHORT);
            l_lstOrderTypeEnum.add(OrderTypeEnum.CLOSE_MARGIN_LONG);
            l_lstOrderTypeEnum.add(OrderTypeEnum.CLOSE_MARGIN_SHORT);
            l_lstOrderTypeEnum.add(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_lstOrderTypeEnum.add(OrderTypeEnum.SWAP_MARGIN_SHORT);
        }

        // When branch.mstk_div == "Def.ENFORCEMENT"
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMstkDiv()))
        {
            l_lstOrderTypeEnum.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lstOrderTypeEnum.add(OrderTypeEnum.MINI_STOCK_SELL);
        }

        OrderTypeEnum[] l_enumerated =
            (OrderTypeEnum[])l_lstOrderTypeEnum.toArray(new OrderTypeEnum[0]);

        for (int i = 0; i < l_enumerated.length; i++)
        {
            l_tradeStopInfoUnit = new WEB3AdminPMAccTradeStopInfoUnit();
            l_tradeStopInfoUnit.orderType = String.valueOf(l_enumerated[i].intValue());
            l_tradeStopInfoUnit.tradeStopDiv =
                this.getTradeStopDiv(l_enumerated[i], l_accountProductOrderStopParams);
            l_lisTradeStopInfo.add(l_tradeStopInfoUnit);
        }

        // 5) Set tradeStopInfoUnitList into the WEB3AdminPMAccProductTradeStopInfoUnit
        WEB3AdminPMAccTradeStopInfoUnit[] l_arrAccTradeStopInfoUnit =
            new WEB3AdminPMAccTradeStopInfoUnit[l_lisTradeStopInfo.size()];
        l_lisTradeStopInfo.toArray(l_arrAccTradeStopInfoUnit);
        l_productTradeStopInfoUnit.accTradeStopInfoList = l_arrAccTradeStopInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_productTradeStopInfoUnit;
    }

    /**
     * （get株式銘柄条件設定Params一覧）<BR>
     * 引数の条件に該当する株式銘柄条件設定Paramsの一覧を<BR>
     * 返却する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"株式銘柄条件設定テーブル"(eqtype_product_condition)<BR>
     * 　@　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ３）２）の検索結果を返却する。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getEqtypeProductConditionParamsList<BR>
     * Return the list of eqtypeProductConditionParams() that matches the conditions of
     * the arguments<BR>
     * <BR>
     * 1) Call QueryProcessor.doFindAllQuery() method.<BR>
     * <BR>
     *  [Parameters to set into doFindAllQuery()]<BR>
     *   arg0: "eqtype_product_condition table"(eqtype_product_condition)<BR>
     *   arg1: Parameter.l_strQueryCond<BR>
     *   arg2: Parameter.l_strSortCond<BR>
     *   arg3: null<BR>
     *   arg4: Parameter.l_strQueryCondDataContainer<BR>
     * <BR>
     *  If the search result cannot be aquired return null.<BR>
     * <BR>
     * 2)Return the search result of 1).<BR>
     * <BR>
     * @@param l_strQueryCond - （検索条件文字列）<BR>
     * ----<English>--------------------<BR>
     * l_strQueryCond<BR>
     * <BR>
     * @@param l_strQueryCondDataContainer - （検索条件データコンテナ）<BR>
     * ----<English>--------------------<BR>
     * l_strQueryCondDataContainer<BR>
     * <BR>
     * @@param l_strSortCond - （ソート条件）<BR>
     * ----<English>--------------------<BR>
     * l_strSortCond<BR>
     * <BR>
     * @@return List
     * @@throws DataFindException DataFindException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 41B6E7BB018F
     */
    public List getEqtypeProductConditionParamsList(
        String l_strQueryCond,
        Object[] l_strQueryCondDataContainer,
        String l_strSortCond)
        throws DataFindException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME =
            "getEqtypeProductConditionParamsList(String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        List l_lisSearchResult = null;
        int l_intLength = 0;

        l_queryProcessor = Processors.getDefaultProcessor();

        // 1
        l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(
                EqtypeProductConditionRow.TYPE,
                l_strQueryCond,
                l_strSortCond,
                null,
                l_strQueryCondDataContainer);

        l_intLength = l_lisSearchResult.size();
        if (l_intLength > 0)
        {
            log.exiting(STR_METHOD_NAME);
            // 2
            return l_lisSearchResult;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * （get株式銘柄条件設定Params一覧）<BR>
     * (get株式銘柄条件設定Params一覧のオーバーロード)<BR>
     * 引数の条件に該当する株式銘柄条件設定Paramsの一覧を<BR>
     * 返却する。<BR>
     * <BR>
     * １）以下の条件を表す検索条件文字列と<BR>
     * 　@ArrayList(パラメータセット)を作成する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード　@かつ<BR>
     * 　@　@銘柄コード = パラメータ.銘柄コード　@かつ<BR>
     * 　@　@削除フラグ = "0：未削除"<BR>
     * <BR>
     * 　@１－１）上記条件を基に検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@　@　@検索条件文字列 = " institution_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and product_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and delete_flg = ?"<BR>
     * <BR>
     * 　@１－２）検索条件の"?"にセットする値のパラメータセットを<BR>
     * 　@　@　@　@　@作成する。<BR>
     * 　@　@　@　@　@ArrayListを生成し、以下の値を追加する。<BR>
     * 　@　@　@　@　@　@・パラメータ.証券会社コード<BR>
     * 　@　@　@　@　@　@・パラメータ.銘柄コード<BR>
     * 　@　@　@　@　@　@・"0：未削除"<BR>
     * <BR>
     * ２）this.株式銘柄条件設定Params一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@[株式銘柄条件設定Params一覧()にセットするパラメータ]<BR>
     * 　@　@検索条件文字列：　@作成した検索条件文字列<BR>
     * 　@　@検索条件データコンテナ：　@作成したArrayList.toArray()<BR>
     * 　@　@ソート条件：　@null<BR>
     * <BR>
     * ３）２）の検索結果を返却する。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getEqtypeProductConditionParamsList<BR>
     * The overload of getEqtypeProductConditionParamsList<BR>
     * Return the getEqtypeProductConditionParamsList that match the condition of the
     * arguments<BR>
     * <BR>
     * 1)The l_strQueryCond that match the following condition and<BR>
     *  ArrayList(set parameter).<BR>
     * <BR>
     *  [Condition]<BR>
     *   institutionCode = Parameter.institutionCode and<BR>
     *   productCode = Parameter.productCode and<BR>
     *   delete_flg = "0: Def.DELETE_NO"<BR>
     * <BR>
     *  1-1)Create a searchConditionString using the above condition.<BR>
     * <BR>
     *     l_strQueryCond = " institution_code = ? "<BR>
     *                + "and product_code = ? "<BR>
     *                + "and delete_flg = ?"<BR>
     * <BR>
     *  1-2)Create the the value of the parameter to set into the search condition
     * "?"<BR>
     *      Generate ArrayList and add the following values.<BR>
     *       ・Parameter.institutionCode<BR>
     *       ・Parameter.productCode<BR>
     *       ・"0: Def.DELETE_NO"<BR>
     * <BR>
     * ２）Call this.getEqtypeProductConditionParamsList() method.<BR>
     * <BR>
     *  [Set the parameter into eqtypeProductConditionParams()]<BR>
     *   l_strQueryCond: The l_strQueryCond that has been created.<BR>
     *   l_strQueryCondDataContainer: The ArrayList.toArray() that has been
     * created.<BR>
     *   l_strSortCond: null<BR>
     * <BR>
     * ３）Return the sarch result of 2).<BR>
     * <BR>
     * @@param l_strInstitutionCode - （証券会社コード）<BR>
     * ----<English>--------------------<BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_strProductCode - （銘柄コード）<BR>
     * ----<English>--------------------<BR>
     * l_strProductCode<BR>
     * <BR>
     * @@return List
     * @@throws DataQueryException Data Query Exception
     * @@throws DataNetworkException Data Network Exception
     * @@throws DataFindException Exception in aquiring the Data
     * @@roseuid 41B6E29D03E2
     */
    public List getEqtypeProductConditionParamsList(
        String l_strInstitutionCode,
        String l_strProductCode)
        throws DataFindException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "getEqtypeProductConditionParamsList(String, String)";
        List l_lisSearchResult = null;

        log.entering(STR_METHOD_NAME);

        // Build Where clause.
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and delete_flg = ? ");
        String l_strWhere = l_sbWhere.toString();

        // Add parameters in Where clause.
        Object[] l_objWhere =
            {l_strInstitutionCode, l_strProductCode, WEB3AdminEquityDeleteFlgDef.NOT_DELETE};

        // Select Records based on the where clause.
        l_lisSearchResult = this.getEqtypeProductConditionParamsList(l_strWhere, l_objWhere, null);

        log.exiting(STR_METHOD_NAME);
        return l_lisSearchResult;
    }

    /**
     * （update株式銘柄条件設定）<BR>
     * 株式銘柄条件設定テーブルを更新する。<BR>
     * <BR>
     * １）QueryProcessor.doUpdateQuery()をコールする。<BR>
     * <BR>
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@パラメータ.株式銘柄条件設定Params<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * updateEqtypeProductCondition<BR>
     * Update the eqtype_prodcut_conditon table.<BR>
     * <BR>
     * 1)Call QueryProcessor.doUpdateQuery(). <BR>
     * <BR>
     *   [The parameter to set into doUpdateQuery()]<BR>
     *     arg0:Parameter. eqtypeProductConditionParams<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParams - （株式銘柄条件設定Params）<BR>
     * 株式銘柄条件Params<BR>
     * ----<English>--------------------<BR>
     * （l_eqtypeProductConditionParams）<BR>
     * l_eqtypeProductConditionParams<BR>
     * <BR>
     * @@throws DataQueryException Data Query Exception
     * @@throws DataNetworkException Data Network Exception
     * @@roseuid 41B6E29D03E5
     */
    public void updateEqtypeProductCondition(
        EqtypeProductConditionParams l_eqtypeProductConditionParams)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "updateEqtypeProductCondition(EqtypeProductConditionParams)";
        QueryProcessor l_queryProcessor = null;
        log.entering(STR_METHOD_NAME);

        l_queryProcessor = Processors.getDefaultProcessor();

        // UpdateQuery the EqtypeProductConditionParams Object Into the DataBase.
        l_queryProcessor.doUpdateQuery(l_eqtypeProductConditionParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （insert株式銘柄条件設定）<BR>
     * 株式銘柄条件設定テーブルに一件データを登録する。<BR>
     * <BR>
     * １）QueryProcessor.doInsertQuery()をコールする。<BR>
     * <BR>
     * 　@[doInsertQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@パラメータ.株式銘柄条件設定Params<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * insertEqtypeProductCondition<BR>
     * Insert one record into eqtype_product_condition table. <BR>
     * <BR>
     * 1)Call QueryProcessor.doInsertQuery(). <BR>
     * <BR>
     *   [The parameter to set into doInsertQuery()]<BR>
     * arg0:Parameter.eqtypeProductConditionParams<BR>
     * <BR>
     * @@param l_eqtypeProductConditionParams - （株式銘柄条件設定Params）<BR>
     * 株式銘柄条件Params<BR>
     * ----<English>--------------------<BR>
     * （l_eqtypeProductConditionParams）<BR>
     * l_eqtypeProductConditionParams<BR>
     * @@throws DataQueryException Data Query Exception
     * @@throws DataNetworkException Data Network Exception
     * <BR>
     * @@roseuid 41B6E29D03E7
     */
    public void insertEqtypeProductCondition(
        EqtypeProductConditionParams l_eqtypeProductConditionParams)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "insertEqtypeProductCondition(EqtypeProductConditionParams)";
        QueryProcessor l_queryProcessor = null;
        log.entering(STR_METHOD_NAME);

        l_queryProcessor = Processors.getDefaultProcessor();

        // Insert the EqtypeProductConditionParams Object Into the DataBase.
        l_queryProcessor.doInsertQuery(l_eqtypeProductConditionParams);

        log.exiting(STR_METHOD_NAME);
    }

	/**
	 * （get部店ID一覧）<BR>
	 * 引数の証券会社コード、部店コードに該当する部店IDの一覧を返却する。<BR>
	 * 
	 * @@param l_strInstitutionCode - （証券会社コード）<BR>
	 * @@param l_strBranchCodes - （部店コード）<BR>
	 * @@throws DataQueryException Data Query Exception <BR>
	 * @@throws DataNetworkException Data Network Exception <BR>
	 * @@throws DataFindException Exception in aquiring the Data <BR>
	 * @@return String[]
	 * 
	 */
	public String[] getBranchId(
		String l_strInstitutionCode,
		String[] l_strBranchCodes)
		throws DataFindException, DataNetworkException, DataQueryException
	{
		final String STR_METHOD_NAME = "getBranchIdList(String, String[])";
		log.entering(STR_METHOD_NAME);

		List l_lisSearchResult = null;
		String[] l_strBranchId = null;

		// Build Where clause.
		StringBuffer l_sbWhere = new StringBuffer();
		StringBuffer l_strBranchCode = new StringBuffer();

		l_sbWhere.append(" institution_code = ? ");
		l_sbWhere.append(" and branch_code in (");

		// Add parameters in Where clause.
		Object[] l_objWhere = new Object[(l_strBranchCodes.length + 1)];
		l_objWhere[0] = l_strInstitutionCode;

		for (int i = 0; i < l_strBranchCodes.length; i++)
		{
			l_sbWhere.append("?,");
			l_objWhere[i + 1] = l_strBranchCodes[i];
		}

		String l_strWhere = l_sbWhere.substring(0,l_sbWhere.length() -1) + ")";
		l_lisSearchResult = Processors.getDefaultProcessor().doFindAllQuery(
			BranchRow.TYPE,
			l_strWhere,
			l_objWhere);
			int l_intCount = l_lisSearchResult.size();
			if (l_intCount > 0)
			{
				l_strBranchId = new String[l_intCount];
				for (int i = 0; i < l_lisSearchResult.size(); i++)
				{
					BranchRow l_BranchRow = (BranchRow)l_lisSearchResult.get(i);
					l_strBranchId[i] = Long.toString ((l_BranchRow.getBranchId())) ;
				}
			}
			return l_strBranchId;
	}
	
    /**
     * (get顧客一覧)<BR>
     * 引数の条件に該当する顧客の一覧を返却する。<BR> 
     * <BR>
     * １） ArrayListを生成する。 <BR>
     * <BR>
     * ２） DB検索 <BR>
     * 　@パラメータ.部店コード[]の要素数分以下の処理を繰り返す。 <BR>
     * 　@２－１） 拡張アカウントマネージャ.get顧客()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@[get顧客()にセットするパラメータ] <BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード <BR>
     * 　@　@　@部店コード：　@処理対象の部店コード <BR>
     * 　@　@　@口座コード：　@パラメータ.顧客コード <BR>
     * <BR>
     * 　@２－２） 　@２－１）の結果が取得できた場合は、 <BR>
     * 　@　@　@生成したArrayListに追加する。 <BR>
     * 　@ <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。 <BR>
     * 　@※toArray()の戻り値.length＝0の場合、 <BR>
     * 　@　@「該当データなし」の例外をスローする。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag : BUSINESS_ERROR_01037<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@return webbroker3.gentrade.WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException 
     * @@roseuid 4469593901E5
     */
    public static WEB3GentradeMainAccount[] getAccountList(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getAccountList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１） ArrayListを生成する。  
        List l_lisAccounts = new ArrayList();
        
        //２） DB検索  
        //　@パラメータ.部店コード[]の要素数分以下の処理を繰り返す。  
        //　@２－１） 拡張アカウントマネージャ.get顧客()メソッドをコールする。  
        //　@　@[get顧客()にセットするパラメータ]  
        //　@　@　@証券会社コード：　@パラメータ.証券会社コード  
        //　@　@　@部店コード：　@処理対象の部店コード  
        //　@　@　@口座コード：　@パラメータ.顧客コード  
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMananger = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        for (int i = 0; i < l_strBranchCodes.length; i++) 
        {
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = 
                    l_accountMananger.getMainAccount(l_strInstitutionCode, l_strBranchCodes[i], l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                continue;
            }
            
            //　@２－２） 　@２－１）の結果が取得できた場合は、生成したArrayListに追加する。  
            if (l_mainAccount != null) 
            {
                l_lisAccounts.add(l_mainAccount);
            }
        }
        //３）　@ArrayList.toArray()の戻り値を返却する。 
        WEB3GentradeMainAccount[] l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccounts.size()];
        l_lisAccounts.toArray(l_mainAccounts);
        
        //　@※toArray()の戻り値.length＝0の場合、「該当データなし」の例外をスローする。
        if (l_mainAccounts.length == 0)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                "WEB3AdminPMEquityDataManager."+ STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
    }
    
    /**
     * (reset取引カレンダコンテキスト)<BR>
     * 取引カレンダコンテキストの値を引数の値で再セットする。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストを取得する。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールする。<BR>
     * <BR>
     * 　@[getAttribute()にセットするパラメータ]<BR>
     * 　@　@arg0：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * 　@※取得できなかった場合は、取引カレンダコンテキストを生成し、<BR>
     * 　@　@　@以降の処理で使用すること。<BR>
     * <BR>
     * ２）　@取得した取引カレンダコンテキストに以下のプロパティをセットする。<BR>
     * 　@※対応するパラメータがnullの場合は、再セットを行わない。<BR>
     * 　@　@IDに該当するオブジェクトが取得できなかった場合、<BR>
     * 　@　@「該当データなし」のシステムエラーをスローする。<BR>
     * 　@class　@:　@WEB3SystemLayerException<BR>
     * 　@tag　@:　@SYSTEM_ERROR_80005<BR>
     * <BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@部店コード = パラメータ.部店IDに該当する部店コード<BR>
     * 　@　@市場コード = パラメータ.市場IDに該当する市場コード<BR>
     * 　@　@銘柄コード = "DEFAULT"<BR>
     * 　@　@受付時間区分 = パラメータ.受付時間区分<BR>
     * <BR>
     * ３）　@取引カレンダコンテキストを再セットする。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.setAttribute()をコールする。<BR>
     * <BR>
     * 　@[setAttribute()にセットするパラメータ]<BR>
     * 　@　@arg0：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 　@　@arg1：　@プロパティセットした取引カレンダコンテキスト<BR>
     * <BR>
     * ４）　@受付日時、日付ロールのリセットを行う。<BR>
     * 　@取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_branchId - (部店ID)<BR>
     * 部店ID
     * @@param l_marketId - (市場ID)<BR>
     * 市場ID
     * @@param l_strTradeTimeType - (受付時間区分)<BR>
     * 受付時間区分
     * @@throws WEB3BaseException 
     * @@roseuid 4469593901F6
     */
    public static void resetTradingCalContext(
        String l_strInstitutionCode, 
        Long l_branchId, 
        Long l_marketId, 
        String l_strTradeTimeType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "resetTradingCalContext(String, Long, Long, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@取引カレンダコンテキストを取得する。 
        //　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールする。 
        Object l_object = 
            ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        //　@※取得できなかった場合は、取引カレンダコンテキストを生成し、以降の処理で使用すること。 
        WEB3GentradeTradingClendarContext l_context = null;
        if (l_object != null)
        {
            l_context = (WEB3GentradeTradingClendarContext)l_object;
        }
        else
        {
            l_context = new WEB3GentradeTradingClendarContext();
        }
        
        //２）　@取得した取引カレンダコンテキストに以下のプロパティをセットする。 
        //　@※対応するパラメータがnullの場合は、再セットを行わない。
        //　@　@証券会社コード = パラメータ.証券会社コード 
        if (l_strInstitutionCode != null)
        {
            l_context.setInstitutionCode(l_strInstitutionCode);
        }
        
        //　@　@部店コード = パラメータ.部店IDに該当する部店コード 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        if (l_branchId != null)
        {
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            try
            {
                Branch l_branch = l_accountManager.getBranch(l_branchId.longValue());
                l_context.setBranchCode(l_branch.getBranchCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }
        
        //　@　@市場コード = パラメータ.市場IDに該当する市場コード 
        if (l_marketId != null)
        {
            try
            {
                WEB3GentradeFinObjectManager l_finObjManager =
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)l_finObjManager.getMarket(l_marketId.longValue());
                l_context.setMarketCode(l_market.getMarketCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }
        
        //　@　@銘柄コード = "DEFAULT" 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
        //　@　@受付時間区分 = パラメータ.受付時間区分 
        if (l_strTradeTimeType != null) 
        {
            l_context.setTradingTimeType(l_strTradeTimeType);
        }

        //３）　@取引カレンダコンテキストを再セットする。 
        //　@ThreadLocalSystemAttributesRegistry.setAttribute()をコールする。 
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //４）　@受付日時、日付ロールのリセットを行う。 
        WEB3GentradeTradingTimeManagement.setTimestamp();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get手動失効対象注文単位一覧)<BR>
     * 手動失効対象の注文単位の一覧を取得する。<BR>
     * <BR>
     * １）　@検索条件文字列（：String）、<BR>
     * 　@検索条件データコンテナ（：ArrayList）を生成する。<BR>
     * <BR>
     * ２）検索条件を作成する。<BR>
     * 　@２－１）　@以下の注文条件を検索条件に追加する。<BR>
     * 　@　@[注文条件]<BR>
     * 　@　@　@発注条件 = "DEFAULT"<BR>
     * 　@　@　@And 注文有効状態 = "オープン"<BR>
     * 　@　@　@And 注文種別 NOT IN ("株式ミニ株買注文", "株式ミニ株売注文")<BR>
     * 　@　@　@And 取引コード（SONAR） != "立会外分売"<BR>
     * 　@　@　@And 市場から確認済みの数量 = null<BR>
     * 　@　@　@And 承認状態区分 != "未承認"<BR>
     * <BR>
     * 　@　@検索条件文字列 = " and order_condition_type = ?"<BR>
     * 　@　@　@　@　@　@　@+ " and order_open_status = ?"<BR>
     * 　@　@　@　@　@　@　@+ " and order_type not in (?, ?)"<BR>
     * 　@　@　@　@　@　@　@+ " and sonar_traded_code <> ?"<BR>
     * 　@　@　@　@　@　@　@+ " and confirmed_quantity is null"<BR>
     * 　@　@　@　@　@　@　@+ " and ( approve_status_type <> ? or approve_status_type is null )"<BR>
     * <BR>
     * 　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@・"DEFAULT（条件指定なし）"<BR>
     * 　@　@　@・"オープン"<BR>
     * 　@　@　@・"株式ミニ株買注文"<BR>
     * 　@　@　@・"株式ミニ株売注文"<BR>
     * 　@　@　@・"立会外分売"<BR>
     * 　@　@　@・"未承認"（承認状態区分）<BR>
     * <BR>
     * 　@２－２）　@部店条件を検索条件に追加する。<BR>
     * 　@　@パラメータ.部店コードの要素数分"?"を追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and branch_id in (?, ?,,,) "<BR>
     * 　@　@データコンテナにパラメータ.部店コードに該当する部店.部店IDを<BR>
     * 　@　@要素数分、addする。<BR>
     * <BR>
     * 　@　@※部店を取得する際に、パラメータ.証券会社を使用する。<BR>
     * 　@　@※取得時に例外となった場合、「条件に該当するデータが存在しない。」<BR>
     * 　@　@　@の業務エラーをスローする。<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_01037<BR>
     * <BR>
     * 　@２－３）　@パラメータ.銘柄コード != nullの場合、<BR>
     * 　@　@銘柄条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and product_id = ?"<BR>
     * 　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@・パラメータ.銘柄コードに該当する株式銘柄.銘柄ID<BR>
     * <BR>
     * 　@　@※取得時に例外となった場合、「条件に該当するデータが存在しない。」<BR>
     * 　@　@の業務エラーをスローする。<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@:　@BUSINESS_ERROR_01037<BR>
     * <BR>
     * 　@２－４）　@市場条件を検索条件文字列に追加する。<BR>
     * 　@　@　@パラメータ.市場コード一覧の要素数分"?"を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列条件 += " and market_id in (?, ?,,,)"<BR>
     * 　@　@　@データコンテナにパラメータ.市場コード一覧に該当する市場.市場IDをaddする。<BR>
     * <BR>
     * 　@２－５）　@取引条件を検索条件文字列に追加する。<BR>
     * 　@　@２－５－１）　@パラメータ.取引区分一覧に、現物株式の取引が含まれる場合、<BR>
     * 　@　@　@現物株式条件を作成する。<BR>
     * 　@　@　@パラメータ.取引区分一覧の要素数分Loopし、処理対象の要素が<BR>
     * 　@　@　@以下の取引の場合は"?"を追加する。<BR>
     * 　@　@　@　@・"現物買付注文"<BR>
     * 　@　@　@　@・"現物売付注文"<BR>
     * <BR>
     * 　@　@　@　@現物株式条件 = " order_type in (?,?,,,) "<BR>
     * 　@　@　@　@データコンテナに処理対象の要素を追加する。<BR>
     * <BR>
     * 　@　@　@　@※データコンテナは同様のものを使用する。<BR>
     * <BR>
     * 　@　@　@２－５－１－１）　@現物株式条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@検索条件文字列 += " and ((" + 現物株式条件 + ")"<BR>
     * <BR>
     * 　@　@２－５－２）　@パラメータ.取引区分一覧に、信用取引の取引が含まれる場合、<BR>
     * 　@　@　@信用取引条件を作成する。<BR>
     * 　@　@　@パラメータ.取引区分一覧の要素数分Loopし、処理対象の要素が<BR>
     * 　@　@　@以下の取引の場合は"?"を追加する。<BR>
     * 　@　@　@　@・"新規買建注文"<BR>
     * 　@　@　@　@・"新規売建注文"<BR>
     * 　@　@　@　@・"買建返済注文"<BR>
     * 　@　@　@　@・"売建返済注文"<BR>
     * 　@　@　@　@・"現引注文"<BR>
     * 　@　@　@　@・"現渡注文"<BR>
     * <BR>
     * 　@　@　@　@信用取引条件 = " order_type in (?,?,,,) "<BR>
     * 　@　@　@　@データコンテナに処理対象の要素を追加する。<BR>
     * <BR>
     * 　@　@　@　@※データコンテナは同様のものを使用する。<BR>
     * <BR>
     * 　@　@　@２－５－２－１）　@弁済条件を信用取引条件に追加する。<BR>
     * 　@　@　@　@パラメータ.弁済区分一覧の要素数分"?"を追加する。<BR>
     * <BR>
     * 　@　@　@　@信用取引条件 += " and repayment_type in (?, ?,,,) "<BR>
     * 　@　@　@　@データコンテナにパラメータ.弁済区分一覧を要素数分、addする。<BR>
     * <BR>
     * 　@　@　@２－５－２－２）　@信用取引条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@[現物株式条件を作成している場合]<BR>
     * 　@　@　@　@　@検索条件文字列 += " or (" + 信用取引条件 + ")"<BR>
     * <BR>
     * 　@　@　@　@[上記以外の場合]<BR>
     * 　@　@　@　@　@検索条件文字列 += " and ((" + 信用取引条件 + ")"<BR>
     * <BR>
     * 　@　@２－５－３）　@検索条件文字列に")"（括弧）を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += ")"<BR>
     * <BR>
     * 　@２－６）　@パラメータ.顧客コード != nullの場合、<BR>
     * 　@　@顧客条件を検索条件に追加する。<BR>
     * 　@　@２－６－１）　@商品管理（株式）データマネージャ.get顧客一覧()を<BR>
     * 　@　@　@コールする。<BR>
     * <BR>
     * 　@　@　@[get顧客一覧()に指定する引数]<BR>
     * 　@　@　@　@証券会社コード：　@パラメータ.証券会社.証券会社コード<BR>
     * 　@　@　@　@部店コード：　@パラメータ.部店コード<BR>
     * 　@　@　@　@顧客コード：　@パラメータ.顧客コード<BR>
     * <BR>
     * 　@　@２－６－２）　@２－６－１）の戻り値の要素数分、検索条件に"?"を、<BR>
     * 　@　@　@データコンテナに、各要素の口座IDを追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and account_id in (?, ?,,,) "<BR>
     * 　@　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@　@・２－６－１）の戻り値の各要素.口座ID<BR>
     * <BR>
     * 　@２－７）　@パラメータ.口座IDFrom != null　@かつ<BR>
     * 　@　@パラメータ.口座IDTo != nullの場合、口座IDレンジ条件を<BR>
     * 　@　@検索条件に追加する。<BR>
     * 　@　@※非同期処理を行う場合に設定される。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and account_id >= ?"<BR>
     * 　@　@　@　@　@　@　@　@+ " and account_id <= ?"<BR>
     * 　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@・パラメータ.口座IDFrom<BR>
     * 　@　@　@・パラメータ.口座IDTo<BR>
     * <BR>
     * ３）　@DBを検索する。<BR>
     * 　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@株式注文単位Row.TYPE <BR>
     * 　@　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@　@arg2：　@作成したデータコンテナ.toArray()の戻り値<BR>
     * <BR>
     * 　@※検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ４）　@キューテーブルを検索する条件を作成する。<BR>
     * 　@４－１）　@基本の検索条件文字列、データコンテナを作成する。<BR>
     * 　@　@※データコンテナは、以前のものを使用する場合、必ず要素を全てclearすること。<BR>
     * 　@　@[基本条件]<BR>
     * 　@　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@　@処理区分 = "未処理"<BR>
     * <BR>
     * 　@　@検索条件文字列 = "institution_code = ?"<BR>
     * 　@　@　@　@　@　@　@　@+ " and status = ?"<BR>
     * 　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@・パラメータ.証券会社<BR>
     * 　@　@　@・"未処理"<BR>
     * <BR>
     * 　@４－２）　@部店条件を検索条件に追加する。<BR>
     * 　@　@パラメータ.部店コードの要素数分"?"を追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and branch_code in (?, ?,,,)"<BR>
     * 　@　@データコンテナにパラメータ.部店コードの全要素を追加する。<BR>
     * <BR>
     * 　@４－３）　@パラメータ.銘柄コード != nullの場合、<BR>
     * 　@　@銘柄条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and product_code = ?"<BR>
     * 　@　@データコンテナにパラメータ.銘柄コードを追加する。<BR>
     * <BR>
     * 　@４－４）　@パラメータ.顧客コード != nullの場合、<BR>
     * 　@　@顧客条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@４－４－１）　@商品管理（株式）データマネージャ.get顧客一覧()を<BR>
     * 　@　@　@コールする。<BR>
     * <BR>
     * 　@　@　@[get顧客一覧()に指定する引数]<BR>
     * 　@　@　@　@証券会社コード：　@パラメータ.証券会社.証券会社コード<BR>
     * 　@　@　@　@部店コード：　@パラメータ.部店コード<BR>
     * 　@　@　@　@顧客コード：　@パラメータ.顧客コード<BR>
     * <BR>
     * 　@　@４－４－２）　@４－４－１）の戻り値の要素数分、検索条件に"?"を、<BR>
     * 　@　@　@データコンテナに、各要素の口座コードを追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and account_code in (?, ?,,,) "<BR>
     * 　@　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@　@・４－４－１）の戻り値の各要素.口座コード<BR>
     * <BR>
     * ５）　@識別コードリスト（：ArrayList）を生成する。<BR>
     * <BR>
     * ６）　@パラメータ.取引区分一覧に、以下の取引のいずれかが含まれる場合、<BR>
     * 　@株式注文取引キューテーブルを検索する。<BR>
     * 　@　@・"現物買付注文"<BR>
     * 　@　@・"現物売付注文"<BR>
     * 　@　@・"新規買建注文"<BR>
     * 　@　@・"新規売建注文"<BR>
     * 　@　@・"買建返済注文"<BR>
     * 　@　@・"売建返済注文"<BR>
     * <BR>
     * 　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@株式注文取引キューRow.TYPE <BR>
     * 　@　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@　@arg2：　@作成したデータコンテナ.toArray()の戻り値<BR>
     * <BR>
     * 　@検索結果が取得できた場合、検索結果の各要素.識別コードを<BR>
     * 　@識別コードリストに追加する。<BR>
     * <BR>
     * ７）　@パラメータ.取引区分一覧に"現引注文"または"現渡注文"が<BR>
     * 　@含まれる場合、現引現渡キューテーブルを検索する。<BR>
     * 　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@現引現渡キューRow.TYPE <BR>
     * 　@　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@　@arg2：　@作成したデータコンテナ.toArray()の戻り値<BR>
     * <BR>
     * 　@検索結果が取得できた場合、検索結果の各要素.識別コードを<BR>
     * 　@識別コードリストに追加する。<BR>
     * <BR>
     * ８）　@失効対象注文リスト（：ArrayList）を生成する。<BR>
     * <BR>
     * ９）　@失効対象注文を確定する。<BR>
     * 　@３）の戻り値の要素数分、Loop処理を行い、<BR>
     * 　@処理対象の要素.識別コードが識別コードリストに含まれる場合、<BR>
     * 　@失効対象注文リストに処理対象の要素を追加する。<BR>
     * <BR>
     * 　@※識別コードリスト.size() == 0の場合、nullを返却する。<BR>
     * <BR>
     * １０）　@失効対象注文リスト.toArray()の戻り値を返却する。<BR>
     * 　@※失効対象注文リスト.size() == 0の場合、nullを返却する。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード
     * @@param l_strMarketList - (市場コード一覧)<BR>
     * 部店コードの配列
     * @@param l_strTradingTypeList - (取引区分一覧)<BR>
     * 取引区分一覧
     * @@param l_strRepaymentDivList - (弁済区分一覧)<BR>
     * 弁済区分一覧
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@param l_accountIdFrom - (口座IDFrom)<BR>
     * 口座IDFrom
     * @@param l_accountIdTo - (口座IDTo)<BR>
     * 口座IDTo
     * @@return EqtypeOrderUnitRow[]
     * @@throws WEB3BaseException 
     * @@roseuid 44695939020C
     */
    public static EqtypeOrderUnitRow[] getManualExpireOrderUnits(
        WEB3GentradeInstitution l_institution, 
        String[] l_strBranchCodes, 
        String l_strProductCode, 
        String[] l_strMarketList, 
        String[] l_strTradingTypeList, 
        String[] l_strRepaymentDivList, 
        String l_strAccountCode, 
        Long l_accountIdFrom, 
        Long l_accountIdTo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getManualExpireOrderUnits(" 
            + "WEB3GentradeInstitution, String[], String, String[], String[], String[], String, Long, Long)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）　@検索条件文字列（：String）、 
        //　@検索条件データコンテナ（：ArrayList）を生成する。 
        List l_lisQueryContainers = new ArrayList();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //２）検索条件を作成する。 
        //　@２－１）　@以下の注文条件を検索条件に追加する。 
        //　@　@[注文条件] 
        //　@　@　@発注条件 = "DEFAULT" 
        //　@　@　@And 注文有効状態 = "オープン" 
        //　@　@　@And 注文種別 NOT IN ("株式ミニ株買注文", "株式ミニ株売注文") 
        //　@　@　@And 取引コード（SONAR） != "立会外分売" 
        //　@　@　@And 市場から確認済みの数量 = null 
        //　@　@検索条件文字列 = " and order_condition_type = ?" 
        //　@　@　@　@　@　@　@+ " and order_open_status = ?" 
        //　@　@　@　@　@　@　@+ " and order_type not in (?, ?)" 
        //　@　@　@　@　@　@　@+ " and sonar_traded_code <> ?" 
        //　@　@　@　@　@　@　@+ " and confirmed_quantity is null" 
        //             + " and ( approve_status_type <> ? or approve_status_type is null )"
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("order_condition_type = ?");
        l_sbQueryString.append(" and order_open_status = ?");
        l_sbQueryString.append(" and order_type not in (?, ?)");
        l_sbQueryString.append(" and sonar_traded_code <> ?");
        l_sbQueryString.append(" and confirmed_quantity is null");
        l_sbQueryString.append(" and ( approve_status_type <> ? or approve_status_type is null )");
        
        //　@　@データコンテナ（以下の順で追加する。)
        //　@　@　@・"DEFAULT（条件指定なし）" 
        l_lisQueryContainers.add(WEB3OrderingConditionDef.DEFAULT);
        
        //　@　@　@・"オープン" 
        l_lisQueryContainers.add(OrderOpenStatusEnum.OPEN);
        
        //　@　@　@・"株式ミニ株買注文" 
        l_lisQueryContainers.add(OrderTypeEnum.MINI_STOCK_BUY);
        
        //　@　@　@・"株式ミニ株売注文" 
        l_lisQueryContainers.add(OrderTypeEnum.MINI_STOCK_SELL);
        
        //　@　@　@・"立会外分売" 
        l_lisQueryContainers.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);

        //　@　@　@・"未承認"（承認状態区分）
        l_lisQueryContainers.add(WEB3ApproveStatusType.UNAPPROVED);

        //　@２－２）　@部店条件を検索条件に追加する。 
        //　@　@パラメータ.部店コードの要素数分"?"を追加する。 
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
        {
            // 検索条件文字列 += " and branch_id in (?, ?,,,) " 
            StringBuffer l_sbBranchIdForQuery = new StringBuffer();
            l_sbBranchIdForQuery.append(" and branch_id in (");
            try 
            {
                for (int i = 0; i < l_strBranchCodes.length; i++) 
                {
                    l_sbBranchIdForQuery.append("?,");
                    
                    //　@　@データコンテナにパラメータ.部店コードに該当する部店.部店IDを要素数分、addする。
                    Branch l_branch = l_accountManager.getBranch(l_institution, l_strBranchCodes[i]);
                    l_lisQueryContainers.add(new Long(l_branch.getBranchId()));
                }
            }
            catch (NotFoundException l_ex)
            {
                //　@　@※部店を取得する際に、パラメータ.証券会社を使用する。 
                //　@　@※取得時に例外となった場合、「条件に該当するデータが存在しない。」の業務エラーをスローする。 
                log.error("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }
            
            l_sbQueryString.append(l_sbBranchIdForQuery.substring(0, l_sbBranchIdForQuery.length() - 1));
            l_sbQueryString.append(")");
        }

        //　@２－３）　@パラメータ.銘柄コード != nullの場合、 
        //　@　@銘柄条件を検索条件文字列に追加する。 
        if (l_strProductCode != null) 
        {
            try 
            {
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_equityProductManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                EqTypeProduct l_eqTypeProduct  =
                    l_equityProductManager.getProduct(l_institution, l_strProductCode);
           
                // 検索条件文字列 += " and product_id = ?"
                l_sbQueryString.append(" and product_id = ?");
                
                //　@　@データコンテナ（以下の順で追加する。） 
                //　@　@　@・パラメータ.銘柄コードに該当する株式銘柄.銘柄ID 
                l_lisQueryContainers.add(String.valueOf(l_eqTypeProduct.getProductId()));
            }
            catch (NotFoundException l_ex)
            {
                //　@　@※取得時に例外となった場合、「条件に該当するデータが存在しない。」の業務エラーをスローする。 
                log.error("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }
        }

        //　@２－４）　@市場条件を検索条件文字列に追加する。  
        if (l_strMarketList != null && l_strMarketList.length != 0)
        {
            StringBuffer l_sbMarketForQuery = new StringBuffer();
            l_sbMarketForQuery.append(" and market_id in (");
            
            try 
            {
                WEB3GentradeFinObjectManager l_finObjManager =
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
                for (int i = 0; i < l_strMarketList.length; i ++)
                {
                    //　@　@　@検索条件文字列条件 += " and market_id in (?, ?,,,)" 
                    l_sbMarketForQuery.append("?,");
                    
                    WEB3GentradeMarket l_market =
                        (WEB3GentradeMarket)l_finObjManager.getMarket(l_institution, l_strMarketList[i]);
                    
                    //　@　@　@データコンテナにパラメータ.市場コード一覧に該当する市場.市場IDをaddする。 
                    l_lisQueryContainers.add(String.valueOf(l_market.getMarketId()));
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }
            
            l_sbQueryString.append(l_sbMarketForQuery.substring(0, l_sbMarketForQuery.length() - 1));
            l_sbQueryString.append(")");
        }
        
        //　@２－５）　@取引条件を検索条件文字列に追加する。 
        //　@　@２－５－１）　@パラメータ.取引区分一覧に、現物株式の取引が含まれる場合、 
        //　@　@　@現物株式条件を作成する。 
        //　@　@　@パラメータ.取引区分一覧の要素数分Loopし、処理対象の要素が 
        //　@　@　@以下の取引の場合は"?"を追加する。 
        //　@　@　@　@・"現物買付注文" 
        //　@　@　@　@・"現物売付注文" 
        //　@　@　@　@現物株式条件 = " order_type in (?,?,,,) " 
        //　@　@　@　@データコンテナに処理対象の要素を追加する。  　@　@
        boolean l_blnEquityFlag = false;
        if (l_strTradingTypeList != null && l_strTradingTypeList.length != 0) 
        {
            StringBuffer l_sbEquityForQuery = new StringBuffer();
            l_sbEquityForQuery.append(" order_type in (");
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                if (String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbEquityForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.EQUITY_BUY);
                    l_blnEquityFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbEquityForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.EQUITY_SELL);
                    l_blnEquityFlag = true;
                }
            }
            
            //２－５－１－１）　@現物株式条件を検索条件文字列に追加する。 
            // 検索条件文字列 += " and ((" + 現物株式条件 + ")" 
            if (l_blnEquityFlag)
            {
                l_sbQueryString.append(" and ((");
                l_sbQueryString.append(l_sbEquityForQuery.substring(0, l_sbEquityForQuery.length() -1) + ")");
                l_sbQueryString.append(")");    
            }
                
            //　@　@２－５－２）　@パラメータ.取引区分一覧に、信用取引の取引が含まれる場合、 
            //　@　@　@信用取引条件を作成する。 
            //　@　@　@パラメータ.取引区分一覧の要素数分Loopし、処理対象の要素が 
            //　@　@　@以下の取引の場合は"?"を追加する。 
            //　@　@　@　@・"新規買建注文" 
            //　@　@　@　@・"新規売建注文" 
            //　@　@　@　@・"買建返済注文" 
            //　@　@　@　@・"売建返済注文" 
            //　@　@　@　@・"現引注文" 
            //　@　@　@　@・"現渡注文" 
            //　@　@　@　@信用取引条件 = " order_type in (?,?,,,) " 
            //　@　@　@　@データコンテナに処理対象の要素を追加する。  
            StringBuffer l_sbMarginForQuery = new StringBuffer();
            l_sbMarginForQuery.append(" order_type in (");
            boolean l_blnMarginFlag = false;
            for (int i = 0; i < l_strTradingTypeList.length; i++)
            {
                if (String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.MARGIN_LONG);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.MARGIN_SHORT);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.CLOSE_MARGIN_LONG);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.CLOSE_MARGIN_SHORT);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.SWAP_MARGIN_LONG);
                    l_blnMarginFlag = true;
                }
                else if (String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    l_sbMarginForQuery.append("?,");
                    l_lisQueryContainers.add(OrderTypeEnum.SWAP_MARGIN_SHORT);
                    l_blnMarginFlag = true;
                }
            }
            
            StringBuffer l_sbMarginRepayment = new StringBuffer();
            
            if (l_blnMarginFlag)
            {
                l_sbMarginRepayment.append(l_sbMarginForQuery.substring(0, l_sbMarginForQuery.length() -1));
                l_sbMarginRepayment.append(")");          
        
                //　@　@　@２－５－２－１）　@弁済条件を信用取引条件に追加する。 
                //　@　@　@　@パラメータ.弁済区分一覧の要素数分"?"を追加する。 
                //　@　@　@　@信用取引条件 += " and repayment_type in (?, ?,,,) " 
                //　@　@　@　@データコンテナにパラメータ.弁済区分一覧を要素数分、addする。 
                if (l_strRepaymentDivList != null && l_strRepaymentDivList.length != 0) 
                {
                    StringBuffer l_sbRepaymentForQuery = new StringBuffer();
                    l_sbRepaymentForQuery.append(" and repayment_type in (");
                    for (int i = 0; i < l_strRepaymentDivList.length; i++) 
                    {
                        l_sbRepaymentForQuery.append("?,");
                        l_lisQueryContainers.add(l_strRepaymentDivList[i]);
                    }
                    
                    l_sbMarginRepayment.append(l_sbRepaymentForQuery.substring(0, l_sbRepaymentForQuery.length() -1));
                    l_sbMarginRepayment.append(")");
                }
                
                //　@　@　@２－５－２－２）　@信用取引条件を検索条件文字列に追加する。 
                //　@　@　@　@[現物株式条件を作成している場合] 
                //　@　@　@　@　@検索条件文字列 += " or (" + 信用取引条件 + ")" 
                if (l_blnEquityFlag)
                {
                    l_sbQueryString.append(" or (");
                    l_sbQueryString.append(l_sbMarginRepayment);
                    l_sbQueryString.append(")");
                }
                
                //　@　@　@　@[上記以外の場合] 
                //　@　@　@　@　@検索条件文字列 += " and ((" + 信用取引条件 + ")" 
                else
                {
                    l_sbQueryString.append(" and ((");
                    l_sbQueryString.append(l_sbMarginRepayment);
                    l_sbQueryString.append(")"); 
                }
            }
            
            //　@　@２－５－３）　@検索条件文字列に")"（括弧）を追加する。 
            l_sbQueryString.append(")");
        }
        
        //　@２－６）　@パラメータ.顧客コード != nullの場合、 
        //　@　@顧客条件を検索条件に追加する。 
        if (l_strAccountCode != null)
        {
            //　@　@２－６－１）　@商品管理（株式）データマネージャ.get顧客一覧()を 
            //　@　@　@コールする。 
            //　@　@　@[get顧客一覧()に指定する引数] 
            //　@　@　@　@証券会社コード：　@パラメータ.証券会社.証券会社コード 
            //　@　@　@　@部店コード：　@パラメータ.部店コード 
            //　@　@　@　@顧客コード：　@パラメータ.顧客コード 
            WEB3GentradeMainAccount[] l_mainAccounts = 
                WEB3AdminPMEquityDataManager.getAccountList(
                    l_institution.getInstitutionCode(), 
                    l_strBranchCodes, 
                    l_strAccountCode);
            
            //　@　@２－６－２）　@２－６－１）の戻り値の要素数分、検索条件に"?"を、 
            //　@　@　@データコンテナに、各要素の口座IDを追加する。 　@　@　@
            if (l_mainAccounts != null && l_mainAccounts.length != 0) 
            {
                //　@検索条件文字列 += " and account_id in (?, ?,,,) " 
                StringBuffer l_sbAccountIdForQuery = new StringBuffer();
                l_sbAccountIdForQuery.append(" and account_id in (");
                for (int i = 0; i < l_mainAccounts.length; i++) 
                {
                    l_sbAccountIdForQuery.append("?,");
                    
                    //　@・２－６－１）の戻り値の各要素.口座ID 
                    l_lisQueryContainers.add(String.valueOf(l_mainAccounts[i].getAccountId()));
                }
                
                l_sbQueryString.append(l_sbAccountIdForQuery.substring(0, l_sbAccountIdForQuery.length() - 1));
                l_sbQueryString.append(")");
            }
        }
        
        //　@２－７）　@パラメータ.口座IDFrom != null　@かつ 
        //　@　@パラメータ.口座IDTo != nullの場合、口座IDレンジ条件を 
        //　@　@検索条件に追加する。 
        //　@　@※非同期処理を行う場合に設定される。 　@　@
        if (l_accountIdFrom != null && l_accountIdTo != null) 
        {
            //　@　@検索条件文字列 += " and account_id >= ?" + " and account_id <= ?" 
            l_sbQueryString.append(" and account_id >= ?");
            l_sbQueryString.append(" and account_id <= ?");
            
            //・パラメータ.口座IDFrom 
            l_lisQueryContainers.add(l_accountIdFrom);
            
            //・パラメータ.口座IDTo 
            l_lisQueryContainers.add(l_accountIdTo);
        }
        
        //３）　@DBを検索する。 
        //　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。 
        QueryProcessor l_queryProcessor = null;
        List l_lisRows = null;
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            
            //　@　@[doFindAllQuery()にセットするパラメータ] 
            //　@　@　@arg0：　@株式注文単位Row.TYPE  
            //　@　@　@arg1：　@作成した検索条件文字列 
            //　@　@　@arg2：　@作成したデータコンテナ.toArray()の戻り値 
            l_lisRows = l_queryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbQueryString.toString(),
                    l_queryContainers);
            
            // ※検索結果が取得できなかった場合、nullを返却する。
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        catch (DataQueryException l_dqex)
        {
            log.error("DBアクセスエラー", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DBアクセスエラー", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
         
        //４）　@キューテーブルを検索する条件を作成する。 
        //　@４－１）　@基本の検索条件文字列、データコンテナを作成する。 
        //　@　@※データコンテナは、以前のものを使用する場合、必ず要素を全てclearすること。 
        //　@　@[基本条件] 
        //　@　@　@証券会社コード = パラメータ.証券会社コード 
        //　@　@　@処理区分 = "未処理" 
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisEQTypeQueryContainers = new ArrayList();
        
        //　@　@検索条件文字列 = "institution_code = ?" + " and status = ?" 
        l_sbQuery.append("institution_code = ?");
        l_sbQuery.append(" and status = ?");
        
        //・パラメータ.証券会社 
        l_lisEQTypeQueryContainers.add(l_institution.getInstitutionCode());

        //・"未処理" 
        l_lisEQTypeQueryContainers.add(WEB3FrontOrderStatusDef.NOT_DEAL);
        
        //　@４－２）　@部店条件を検索条件に追加する。 
        //　@　@パラメータ.部店コードの要素数分"?"を追加する。 
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0) 
        {
            //　@　@検索条件文字列 += " and branch_code in (?, ?,,,)" 
            //　@　@データコンテナにパラメータ.部店コードの全要素を追加する。
            StringBuffer l_sbBranchCodeForQuery = new StringBuffer();
            l_sbBranchCodeForQuery.append(" and branch_code in (");
            for (int i = 0; i < l_strBranchCodes.length; i++) 
            {
                l_sbBranchCodeForQuery.append("?,");
                l_lisEQTypeQueryContainers.add(l_strBranchCodes[i]);
            }
            
            l_sbQuery.append(l_sbBranchCodeForQuery.substring(0, l_sbBranchCodeForQuery.length() - 1));
            l_sbQuery.append(")");
        }
        
        //　@４－３）　@パラメータ.銘柄コード != nullの場合、 
        //　@　@銘柄条件を検索条件文字列に追加する。   
        if (l_strProductCode != null)
        {
            // 検索条件文字列 += " and product_code = ?" 
            l_sbQuery.append(" and product_code = ?");
            
            // データコンテナにパラメータ.銘柄コードを追加する。
            l_lisEQTypeQueryContainers.add(l_strProductCode);
        }
        
        //　@４－４）　@パラメータ.顧客コード != nullの場合、 
        //　@　@顧客条件を検索条件文字列に追加する。 
        //　@　@４－４－１）　@商品管理（株式）データマネージャ.get顧客一覧()を 
        //　@　@　@コールする。 
        if (l_strAccountCode != null) 
        {
            WEB3GentradeMainAccount[] l_mainAccounts = 
                WEB3AdminPMEquityDataManager.getAccountList(
                    l_institution.getInstitutionCode(), 
                    l_strBranchCodes, 
                    l_strAccountCode);
            
            //　@　@４－４－２）　@４－４－１）の戻り値の要素数分、検索条件に"?"を、 
            //　@　@　@データコンテナに、各要素の口座コードを追加する。 
            if (l_mainAccounts != null && l_mainAccounts.length != 0) 
            {
                //検索条件文字列 += " and account_code in (?, ?,,,) "
                //データコンテナ（以下の順で追加する。） 
                //　@　@　@　@・４－４－１）の戻り値の各要素.口座コード 
                StringBuffer l_sbAccountsForQuery = new StringBuffer();
                l_sbAccountsForQuery.append(" and account_code in (");
                
                for (int i = 0; i < l_mainAccounts.length; i++) 
                {
                    l_sbAccountsForQuery.append("?,");
                    l_lisEQTypeQueryContainers.add(l_mainAccounts[i].getAccountCode());
                }
                
                l_sbQuery.append(l_sbAccountsForQuery.substring(0, l_sbAccountsForQuery.length() - 1));
                l_sbQuery.append(")");
            }
        }
        
        //５）　@識別コードリスト（：ArrayList）を生成する。 
        List l_lisOrderRequestNumbers = new ArrayList();
        
        //６）　@パラメータ.取引区分一覧に、以下の取引のいずれかが含まれる場合、 
        //　@株式注文取引キューテーブルを検索する。 
        //　@　@・"現物買付注文" 
        //　@　@・"現物売付注文" 
        //　@　@・"新規買建注文" 
        //　@　@・"新規売建注文" 
        //　@　@・"買建返済注文" 
        //　@　@・"売建返済注文" 
        if (l_strTradingTypeList != null && l_strTradingTypeList.length != 0)
        {
            boolean l_blnTradingTypeFlag = false;
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                if (String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i]))
                {
                    l_blnTradingTypeFlag = true;
                    break;
                }
            }
            if (l_blnTradingTypeFlag) 
            {
                //　@QueryProcessor.doFindAllQuery()メソッドをコールする。 
                QueryProcessor l_eqtypeEqueryProcessor =null;
                List l_lisEqtypeRows = null;
                Object[] l_eqTypeQueryContainers = new Object[l_lisEQTypeQueryContainers.size()];
                l_lisEQTypeQueryContainers.toArray(l_eqTypeQueryContainers);
                try
                {
                    l_eqtypeEqueryProcessor = Processors.getDefaultProcessor();
                    l_lisEqtypeRows = l_eqtypeEqueryProcessor.doFindAllQuery(
                        HostEqtypeOrderAllRow.TYPE,
                        l_sbQuery.toString(),
                        l_eqTypeQueryContainers);
                }
                catch (DataQueryException l_dqex)
                {
                    log.error("DBアクセスエラー", l_dqex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_dqex.getMessage(),
                        l_dqex);
                }
                catch (DataNetworkException l_dnex)
                {
                    log.error("DBアクセスエラー", l_dnex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_dnex.getMessage(),
                        l_dnex);
                }
                if (!l_lisEqtypeRows.isEmpty())
                {
                    for (int i = 0; i < l_lisEqtypeRows.size(); i++) 
                    {
                        //　@検索結果が取得できた場合、検索結果の各要素.識別コードを 
                        //　@識別コードリストに追加する。 
                        HostEqtypeOrderAllRow l_row = (HostEqtypeOrderAllRow)l_lisEqtypeRows.get(i);
                        l_lisOrderRequestNumbers.add(l_row.getOrderRequestNumber());
                    }
                    
                }
            }
        
            //７）　@パラメータ.取引区分一覧に"現引注文"または"現渡注文"が 
            //　@含まれる場合、現引現渡キューテーブルを検索する。 
            //　@QueryProcessor.doFindAllQuery()メソッドをコールする。 
            boolean l_blnSwapFlag = false;
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                if (String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(l_strTradingTypeList[i]))
                {
                    l_blnSwapFlag = true;
                    break;
                }
            }
            if (l_blnSwapFlag)
            {
                QueryProcessor l_swapQueryProcessor = null;
                List l_lisSwapRows = null;
                Object[] l_eqTypeQueryContainers = new Object[l_lisEQTypeQueryContainers.size()];
                l_lisEQTypeQueryContainers.toArray(l_eqTypeQueryContainers);
                try 
                {
                    l_swapQueryProcessor = Processors.getDefaultProcessor();
                    l_lisSwapRows = l_swapQueryProcessor.doFindAllQuery(
                        HostEqtypeSwapRow.TYPE,
                        l_sbQuery.toString(),
                        l_eqTypeQueryContainers);
                }
                catch (DataQueryException l_dqex)
                {
                    log.error("DBアクセスエラー", l_dqex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_dqex.getMessage(),
                        l_dqex);
                }
                catch (DataNetworkException l_dnex)
                {
                    log.error("DBアクセスエラー", l_dnex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_dnex.getMessage(),
                        l_dnex);
                }
                if (!l_lisSwapRows.isEmpty())
                {
                    for (int i = 0; i < l_lisSwapRows.size(); i++) 
                    {
                        //　@検索結果が取得できた場合、検索結果の各要素.識別コードを 
                        //　@識別コードリストに追加する。 
                        HostEqtypeSwapRow l_row = (HostEqtypeSwapRow)l_lisSwapRows.get(i);
                        l_lisOrderRequestNumbers.add(l_row.getOrderRequestNumber());
                    }
                }
            }
        
        }
        
        //８）　@失効対象注文リスト（：ArrayList）を生成する。 
        List l_lisLapseTargetOrder = new ArrayList();
        
        //※識別コードリスト.size() == 0の場合、nullを返却する。
        if (l_lisOrderRequestNumbers.size() == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //９）　@失効対象注文を確定する。 
        //　@３）の戻り値の要素数分、Loop処理を行い、 
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_lisRows.get(i);
            for (int j = 0; j < l_lisOrderRequestNumbers.size(); j++) 
            {
                //　@処理対象の要素.識別コードが識別コードリストに含まれる場合、 
                //　@失効対象注文リストに処理対象の要素を追加する。
                if (l_eqtypeOrderUnitRow.getOrderRequestNumber().equals(l_lisOrderRequestNumbers.get(j)))
                {
                    l_lisLapseTargetOrder.add(l_eqtypeOrderUnitRow);
                }
            }
        }
        
        //※失効対象注文リスト.size() == 0の場合、nullを返却する。
        if (l_lisLapseTargetOrder.size() == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //１０）　@失効対象注文リスト.toArray()の戻り値を返却する。
        EqtypeOrderUnitRow[] l_rows = new EqtypeOrderUnitRow[l_lisLapseTargetOrder.size()];
        l_lisLapseTargetOrder.toArray(l_rows);
        log.exiting(STR_METHOD_NAME);
        return l_rows;

    }

    /**
     * (get強制決済注文一覧)<BR>
     * 引数のリクエストデータにて指定された条件に該当する<BR>
     * 強制決済注文の一覧を返却する。<BR>
     * <BR>
     * １）　@検索条件文字列（：String）、検索条件データコンテナ（：ArrayList）を<BR>
     * 　@生成する。<BR>
     * <BR>
     * ２）強制決済注文を取得する検索条件を作成する。<BR>
     * 　@２－１）　@強制決済注文のみ取得する検索条件を追加する。<BR>
     * 　@　@検索条件文字列 = "forced_settle_reason_type is not null"<BR>
     * <BR>
     * 　@２－２）　@部店条件を検索条件に追加する。<BR>
     *　@ 　@パラメータ.リクエストデータ.部店コード一覧の要素数分"?"を追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and branch_id in (?, ?,,,) "<BR>
     * 　@　@データコンテナにthis.get部店ID一覧()の戻り値を<BR>
     * 　@　@要素数分、追加する。<BR>
     * <BR>
     * 　@　@[get部店ID一覧()に指定する引数]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社.証券会社コード<BR>
     * 　@　@　@部店コード一覧：　@パラメータ.リクエストデータ.部店コード一覧<BR>
     * <BR>
     * 　@　@　@※get部店ID一覧()の戻り値 == nullの場合、<BR>
     * 　@　@　@　@「条件に該当するデータが存在しない。」の業務エラーをスローする。<BR>
     * 　@　@　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01037<BR>
     * <BR>
     * 　@２－３）　@パラメータ.リクエストデータ.発注日 != nullの場合、<BR>
     * 　@　@発注日条件を検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and biz_date = ?"<BR>
     * <BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.発注日をYYYYMMDD形式で<BR>
     * 　@　@追加する。<BR>
     * <BR>
     * 　@２－４）　@パラメータ.リクエストデータ.顧客コード != nullの場合、 <BR>
     * 　@　@顧客条件を検索条件に追加する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 += " and account_code = ?" <BR>
     * <BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.顧客コードを追加する。 <BR>
     * <BR>
     * 　@２－５）　@パラメータ.リクエストデータ.銘柄コード != nullの場合、 <BR>
     * 　@　@銘柄条件を検索条件文字列に追加する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 += " and product_code = ?" <BR>
     * <BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.銘柄コードを追加する。 <BR>
     * <BR>
     * 　@２－６）　@パラメータ.リクエストデータ.承認状態 != nullの場合、<BR>
     * 　@　@承認状態条件を検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and approve_status_type = ?"<BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.承認状態を追加する。<BR>
     * <BR>
     * 　@２－７）　@パラメータ.リクエストデータ.承認者コード != nullの場合、<BR>
     * 　@　@承認者コード条件を検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and approver_code = ?"<BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.承認者コードを追加する。<BR>
     * <BR>
     * 　@２－８）　@パラメータ.リクエストデータ.作成日時From != nullの場合、<BR>
     * 　@　@作成日時Fromを検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and created_timestamp >= ?"<BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.作成日時Fromを追加する。<BR>
     * <BR>
     * 　@２－９）　@パラメータ.リクエストデータ.作成日時To != nullの場合、<BR>
     * 　@　@作成日時Toを検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and created_timestamp <= ?"<BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.作成日時Toを追加する。<BR>
     * <BR>
     * 　@２－１０）　@パラメータ.リクエストデータ.承認日時From != nullの場合、<BR>
     * 　@　@承認日時Fromを検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and approve_timestamp >= ?"<BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.承認日時Fromを追加する。<BR>
     * <BR>
     * 　@２－１１）　@パラメータ.リクエストデータ.承認日時To != nullの場合、<BR>
     * 　@　@承認日時Toを検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and approve_timestamp <= ?"<BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.承認日時Toを追加する。<BR>
     * <BR>
     * 　@２－１２）　@パラメータ.リクエストデータ.強制決済理由 != nullの場合、 <BR>
     * 　@　@強制決済理由条件を検索条件に追加する。 <BR>
     * <BR>
     * 　@　@２－１２－１）　@パラメータ.リクエストデータ.強制決済理由 == <BR>
     * 　@　@"追証(第一)期日超過"の場合 <BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and forced_settle_reason_type = in(? ,?)" <BR>
     * 　@　@　@データコンテナに"保証金維持率割れ（オンライン開始前・軽度）"、 <BR>
     * 　@　@　@　@"保証金維持率割れ（オンライン開始前・重度）" を追加する。 <BR>
     * <BR>
     * 　@　@２－１２－２）　@パラメータ.リクエストデータ.強制決済理由 == <BR>
     * 　@　@"追証(第二)期日超過"の場合 <BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and forced_settle_reason_type = in(? ,?)" <BR>
     * 　@　@　@データコンテナに"保証金維持率割れ（場間）"、 <BR>
     * 　@　@　@　@"保証金維持率割れ（オンライン開始前・法@定）" を追加する。 <BR>
     * <BR>
     * 　@　@２－１２－３）　@以外の場合 <BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and forced_settle_reason_type = ?" <BR>
     * 　@　@　@データコンテナにパラメータ.リクエストデータ.強制決済理由を追加する。 <BR>
     * <BR>
     * 　@２－１３）　@パラメータ.リクエストデータ.決済期日 != nullの場合、<BR>
     * 　@　@決済期日を検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and close_date = ?"<BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.決済期日を追加する。<BR>
     * <BR>
     * 　@２－１４）　@パラメータ.リクエストデータ.注文エラー理由コード != nullの場合、<BR>
     * 　@　@注文エラー理由コードを検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and error_reason_code = ?"<BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.注文エラー理由コードを追加する。<BR>
     * <BR>
     * 　@２－１５）　@パラメータ.リクエストデータ.承認区分 != nullの場合、<BR>
     * 　@　@承認区分条件を検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and order_open_status = ?"<BR>
     * 　@　@　@+ " and approve_status_type = ?"<BR>
     * 　@　@データコンテナに以下の値を追加する。<BR>
     * 　@　@　@・"オープン"（注文有効状態）<BR>
     * 　@　@　@・"未承認"（承認状態区分）<BR>
     * <BR>
     * 　@２－１６）　@パラメータ.リクエストデータ.口座区分 != nullの場合、<BR>
     * 　@　@口座区分条件を検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and tax_type = ?"<BR>
     * 　@　@データコンテナに株式データアダプタ.getAP口座区分()の<BR>
     * 　@　@戻り値をセットする。<BR>
     * <BR>
     * 　@　@[getAP口座区分()に指定する引数]<BR>
     * 　@　@　@税区分：　@パラメータ.リクエストデータ.口座区分<BR>
     * <BR>
     * 　@２－１７）　@パラメータ.リクエストデータ.市場コード != nullの場合、<BR>
     * 　@　@市場条件を検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列条件 += " and market_id ?"<BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.市場コードに<BR>
     * 　@　@該当する市場.市場IDを追加する。<BR>
     * <BR>
     * ３）　@ソート条件を作成する。<BR>
     * 　@this.create強制決済注文ソート条件()をコールする。<BR>
     * <BR>
     * 　@[create強制決済注文ソート条件()に指定する引数]<BR>
     * 　@　@ソートキー：　@パラメータ.リクエストデータ.ソートキー<BR>
     * <BR>
     * ４）　@DBを検索する。<BR>
     * 　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@強制決済注文Row.TYPE <BR>
     * 　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@arg2：　@create強制決済注文ソート条件()の戻り値<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@作成したデータコンテナ.toArray()の戻り値<BR>
     * <BR>
     * ※検索結果が取得できなかった場合、nullを返却する。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_referenceRequest -(リクエストデータ)<BR>
     * 管理者・強制決済仮注文照会リクエストオブジェクト<BR>
     * @@return AdminEqForcedSettleOrderRow[]
     * @@throws WEB3BaseException
     */
    public static AdminEqForcedSettleOrderRow[] getForcedSettleOrderList(
        Institution l_institution,
        WEB3AdminForcedSettleReferenceRequest l_referenceRequest)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForcedSettleOrderList(Institution, "
            + "WEB3AdminForcedSettleReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_referenceRequest == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //検索条件文字列（：String）、検索条件データコンテナ（：ArrayList）を生成する。
        StringBuffer l_sbQuery = new StringBuffer();
        ArrayList l_lisQueryDataContainers = new ArrayList();

        //強制決済注文を取得する検索条件を作成する。
        //  強制決済注文のみ取得する検索条件を追加する。
        //    検索条件文字列 = "forced_settle_reason_type is not null"
        l_sbQuery.append("forced_settle_reason_type is not null");

        //  部店条件を検索条件に追加する。
        //    パラメータ.リクエストデータ.部店コード一覧の要素数分"?"を追加する。
        //    検索条件文字列 += " and branch_id in (?, ?,,,) "
        //    データコンテナにthis.get部店ID一覧()の戻り値を要素数分、追加する。
        //　@　@[get部店ID一覧()に指定する引数]
        //　@証券会社コード：　@パラメータ.証券会社.証券会社コード
        //　@　@部店コード一覧：　@パラメータ.リクエストデータ.部店コード一覧
        //　@　@※get部店ID一覧()の戻り値 == nullの場合、
        //　@　@　@　@「条件に該当するデータが存在しない。」の業務エラーをスローする。
        WEB3AdminPMEquityDataManager l_datamanager = new WEB3AdminPMEquityDataManager();
        String[] l_strBranchIds = null;
        try
        {
            l_strBranchIds = l_datamanager.getBranchId(
                l_institution.getInstitutionCode(),
                l_referenceRequest.branchCodeList);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("DBへのアクセスに失敗しました", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_strBranchIds == null)
        {
            log.error("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        l_sbQuery.append(" and branch_id in ( ");
        for(int i = 0; i < l_strBranchIds.length; i++)
        {
            l_sbQuery.append(" ? ,");
            l_lisQueryDataContainers.add(l_strBranchIds[i]);
        }
        l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
        l_sbQuery.append(") ");

        //パラメータ.リクエストデータ.発注日 != nullの場合、発注日条件を検索条件に追加する。
        if (l_referenceRequest.orderBizDate != null)
        {
            //検索条件文字列 += " and biz_date = ?"
            //データコンテナにパラメータ.リクエストデータ.発注日をYYYYMMDD形式で追加する。
            l_sbQuery.append(" and biz_date = ?");
            String l_strBizDate = WEB3DateUtility.formatDate(l_referenceRequest.orderBizDate, "yyyyMMdd");
            l_lisQueryDataContainers.add(l_strBizDate);
        }

        //パラメータ.リクエストデータ.顧客コード != nullの場合、顧客条件を検索条件に追加する。
        if (l_referenceRequest.accountCode != null)
        {
            //検索条件文字列 += " and account_code = ?"
            //データコンテナにパラメータ.リクエストデータ.顧客コードを追加する。
            l_sbQuery.append(" and account_code = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.accountCode);
        }

        //パラメータ.リクエストデータ.銘柄コード != nullの場合、銘柄条件を検索条件文字列に追加する。
        if (l_referenceRequest.productCode != null)
        {
            //検索条件文字列 += " and product_code = ?"
            //データコンテナにパラメータ.リクエストデータ.銘柄コードを追加する。
            l_sbQuery.append(" and product_code = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.productCode);
        }

        //パラメータ.リクエストデータ.承認状態 != nullの場合、承認状態条件を検索条件に追加する。
        if (l_referenceRequest.approveState != null)
        {
            //検索条件文字列 += " and approve_status_type = ?"
            //データコンテナにパラメータ.リクエストデータ.承認状態を追加する。
            l_sbQuery.append(" and approve_status_type = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.approveState);
        }

        //パラメータ.リクエストデータ.承認者コード != nullの場合、承認者コード条件を検索条件に追加する。
        if (l_referenceRequest.checker != null)
        {
            //検索条件文字列 += " and approver_code = ?"
            //データコンテナにパラメータ.リクエストデータ.承認者コードを追加する。
            l_sbQuery.append(" and approver_code = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.checker);
        }

        //パラメータ.リクエストデータ.作成日時From != nullの場合、作成日時Fromを検索条件に追加する。
        if (l_referenceRequest.createDateFrom != null)
        {
            //検索条件文字列 += " and created_timestamp >= ?"
            //データコンテナにパラメータ.リクエストデータ.作成日時Fromを追加する。
            l_sbQuery.append(" and created_timestamp >= ?");
            l_lisQueryDataContainers.add(
                WEB3DateUtility.getDate(l_referenceRequest.createDateFrom, "yyyyMMddHHmm"));
        }

        //パラメータ.リクエストデータ.作成日時To != nullの場合、作成日時Toを検索条件に追加する。
        if (l_referenceRequest.createDateTo != null)
        {
            //検索条件文字列 += " and created_timestamp <= ?"
            //データコンテナにパラメータ.リクエストデータ.作成日時Toを追加する。
            l_sbQuery.append(" and created_timestamp <= ?");
            l_lisQueryDataContainers.add(
                WEB3DateUtility.getDate(l_referenceRequest.createDateTo, "yyyyMMddHHmm"));
        }

        //パラメータ.リクエストデータ.承認日時From != nullの場合、承認日時Fromを検索条件に追加する。
        if (l_referenceRequest.approveDateFrom != null)
        {
            //検索条件文字列 += " and approve_timestamp >= ?"
            //データコンテナにパラメータ.リクエストデータ.承認日時Fromを追加する
            l_sbQuery.append(" and approve_timestamp >= ?");
            l_lisQueryDataContainers.add(
                WEB3DateUtility.getDate(l_referenceRequest.approveDateFrom, "yyyyMMddHHmm"));
        }

        //パラメータ.リクエストデータ.承認日時To != nullの場合、承認日時Toを検索条件に追加する。
        if (l_referenceRequest.approveDateTo != null)
        {
            //検索条件文字列 += " and approve_timestamp <= ?"
            //データコンテナにパラメータ.リクエストデータ.承認日時Toを追加する。
            l_sbQuery.append(" and approve_timestamp <= ?");
            l_lisQueryDataContainers.add(
                WEB3DateUtility.getDate(l_referenceRequest.approveDateTo, "yyyyMMddHHmm"));
        }

        //パラメータ.リクエストデータ.強制決済理由 != nullの場合、
        //強制決済理由条件を検索条件に追加する。
        if (l_referenceRequest.forcedSettleReason != null)
        {
            //パラメータ.リクエストデータ.強制決済理由 == "追証(第一)期日超過"の場合
            //　@検索条件文字列 += " and forced_settle_reason_type = in(? ,?)"
            //　@データコンテナに"保証金維持率割れ（オンライン開始前・軽度）"、
            //　@　@"保証金維持率割れ（オンライン開始前・重度）" を追加する。
            if (WEB3AdminEquityForcedSettleReasonDef.ADDDEPOSIT_FIRST_DATE_EXCESS.equals(
                l_referenceRequest.forcedSettleReason))
            {
                l_sbQuery.append(" and forced_settle_reason_type in (?, ?)");
                l_lisQueryDataContainers.add(
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS);
                l_lisQueryDataContainers.add(
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS);
            }
            //パラメータ.リクエストデータ.強制決済理由 == "追証(第二)期日超過"の場合
            //　@検索条件文字列 += " and forced_settle_reason_type = in(? ,?)"
            //　@データコンテナに"保証金維持率割れ（場間）"、
            //　@　@"保証金維持率割れ（オンライン開始前・法@定）" を追加する。
            else if (WEB3AdminEquityForcedSettleReasonDef.ADDDEPOSIT_SECOND_DATE_EXCESS.equals(
                l_referenceRequest.forcedSettleReason))
            {
                l_sbQuery.append(" and forced_settle_reason_type in (?, ?)");
                l_lisQueryDataContainers.add(
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET);
                l_lisQueryDataContainers.add(
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL);
            }
            //以外の場合
            //　@検索条件文字列 += " and forced_settle_reason_type = ?"
            //　@データコンテナにパラメータ.リクエストデータ.強制決済理由を追加する。
            else
            {
                l_sbQuery.append(" and forced_settle_reason_type = ?");
                l_lisQueryDataContainers.add(l_referenceRequest.forcedSettleReason);
            }
        }

        //パラメータ.リクエストデータ.決済期日 != nullの場合、決済期日を検索条件に追加する。
        if (l_referenceRequest.closeDate != null)
        {
            //検索条件文字列 += " and close_date = ?"
            //データコンテナにパラメータ.リクエストデータ.決済期日を追加する。
            l_sbQuery.append(" and close_date = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.closeDate);
        }

        //パラメータ.リクエストデータ.注文エラー理由コード != nullの場合、注文エラー理由コードを検索条件に追加する。
        if (l_referenceRequest.errorReason != null)
        {
            //検索条件文字列 += " and error_reason_code = ?"
            //データコンテナにパラメータ.リクエストデータ.注文エラー理由コードを追加する。
            l_sbQuery.append(" and error_reason_code = ?");
            l_lisQueryDataContainers.add(l_referenceRequest.errorReason);
        }

        //パラメータ.リクエストデータ.承認区分 != nullの場合、承認区分条件を検索条件に追加する。
        if (l_referenceRequest.approveType != null)
        {
            //検索条件文字列 += " and order_open_status = ?"
            //　@　@　@+ " and approve_status_type = ?"
            //　@　@データコンテナに以下の値を追加する。
            //　@　@　@・"オープン"（注文有効状態）
            //　@　@　@・"未承認"（承認状態区分）
            l_sbQuery.append(" and order_open_status = ?");
            l_sbQuery.append(" and approve_status_type = ?");
            l_lisQueryDataContainers.add(OrderOpenStatusEnum.OPEN.intValue() + "");
            l_lisQueryDataContainers.add(WEB3ApproveStatusType.UNAPPROVED);
        }

        //パラメータ.リクエストデータ.口座区分 != nullの場合、口座区分条件を検索条件に追加する。
        if (l_referenceRequest.taxType != null)
        {
            //検索条件文字列 += " and tax_type = ?"
            //データコンテナに株式データアダプタ.getAP口座区分()の戻り値をセットする。
            //getAP口座区分()に指定する引数]税区分：　@パラメータ.リクエストデータ.口座区分
            l_sbQuery.append(" and tax_type = ?");
            String l_strTaxType = WEB3EquityDataAdapter.getApTaxType(l_referenceRequest.taxType);
            l_lisQueryDataContainers.add(l_strTaxType);
        }

        //パラメータ.リクエストデータ.市場コード != nullの場合、市場条件を検索条件に追加する。
        if (l_referenceRequest.marketCode != null)
        {
            //検索条件文字列条件 += " and market_id = ?"
            //データコンテナにパラメータ.リクエストデータ.市場コードに
            //該当する市場.市場IDを追加する。
            l_sbQuery.append(" and market_id = ?");
            Market l_market = null;
            try
            {
                l_market = l_finObjectMgr.getMarket(l_institution, l_referenceRequest.marketCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    "市場オブジェクトが取得できません。");
            }
            l_lisQueryDataContainers.add(l_market.getMarketId() + "");
        }

        //ソート条件を作成する。this.create強制決済注文ソート条件()をコールする。
        //　@[create強制決済注文ソート条件()に指定する引数]
        //　@　@ソートキー：　@パラメータ.リクエストデータ.ソートキー
        String l_strSortConditon =
            WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_referenceRequest.sortKeys);

        //DBを検索する
        //QueryProcessor.doFindAllQuery()メソッドをコールする。
        //　@[doFindAllQuery()にセットするパラメータ]
        //　@　@arg0：　@強制決済注文Row.TYPE
        //　@　@arg1：　@作成した検索条件文字列
        //　@　@arg2：　@create強制決済注文ソート条件()の戻り値
        //　@　@arg3：　@null
        //　@　@arg4：　@作成したデータコンテナ.toArray()の戻り値
        Object[] l_objValues = new Object[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_objValues);

        List l_lisResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResult = l_processor.doFindAllQuery(
                AdminEqForcedSettleOrderRow.TYPE,
                l_sbQuery.toString(),
                l_strSortConditon,
                null,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_lisResult == null || l_lisResult.size() == 0)
        {
            //※検索結果が取得できなかった場合、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        AdminEqForcedSettleOrderRow[] l_orderRows =
            new AdminEqForcedSettleOrderRow[l_lisResult.size()];
        l_lisResult.toArray(l_orderRows);

        log.exiting(STR_METHOD_NAME);
        return l_orderRows;
    }

    /**
     * (get強制決済注文一覧)<BR>
     * 引数の条件に該当する強制決済注文の一覧を返却する。<BR>
     * <BR>
     * １）　@検索条件文字列（：String）、検索条件データコンテナ（：ArrayList）を<BR>
     * 　@生成する。<BR>
     * <BR>
     * ２）強制決済注文を取得する検索条件を作成する。<BR>
     * 　@２－１）　@未承認の有効な強制決済注文のみ取得する検索条件を追加する。<BR>
     * 　@　@検索条件文字列 = "order_open_status = ?"<BR>
     * 　@　@　@+ " and forced_settle_reason_type is not null"<BR>
     * 　@　@　@+ " and approve_status_type = ?"<BR>
     * <BR>
     * 　@　@データコンテナに"オープン"（注文有効状態）、<BR>
     * 　@　@"未承認"（承認状態区分）を追加する。<BR>
     * <BR>
     * 　@２－２）　@注文IDを検索条件に追加する。<BR>
     * 　@　@パラメータ.注文ID一覧の要素数分、検索条件に"?"を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and order_id in (?, ?,,,) "<BR>
     * 　@　@　@データコンテナにパラメータ.注文ID一覧の全要素を追加する。<BR>
     * <BR>
     * 　@２－３）　@パラメータ.口座IDFrom != null　@かつ<BR>
     * 　@　@パラメータ.口座IDTo != nullの場合、口座IDレンジ条件を<BR>
     * 　@　@検索条件に追加する。<BR>
     * 　@　@※非同期処理を行う場合に設定される。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and account_id >= ?"<BR>
     * 　@　@　@　@　@　@　@　@+ " and account_id <= ?"<BR>
     * 　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@・パラメータ.口座IDFrom<BR>
     * 　@　@　@・パラメータ.口座IDTo<BR>
     * <BR>
     * ３）　@ソート条件を作成する。<BR>
     * 　@this.create強制決済注文ソート条件()をコールする。<BR>
     * <BR>
     * 　@[create強制決済注文ソート条件()に指定する引数]<BR>
     * 　@　@ソートキー：　@パラメータ.ソートキー<BR>
     * <BR>
     * ４）　@DBを検索する。<BR>
     * 　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@強制決済注文Row.TYPE <BR>
     * 　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@arg2：　@create強制決済注文ソート条件()の戻り値<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@作成したデータコンテナ.toArray()の戻り値<BR>
     * <BR>
     * ※検索結果が取得できなかった場合、nullを返却する。<BR>
     * @@param l_strOrderIdList - (注文ID一覧)<BR>
     * 注文IDの一覧<BR>
     * @@param l_sortsKeys - (ソートキー)<BR>
     * 強制決済ソートキー<BR>
     * @@param l_lngAccountIdFrom - (口座IDFrom)<BR>
     * 口座IDFrom<BR>
     * @@param l_lngAccountIdTo - (口座IDTo)<BR>
     * 口座IDTo<BR>
     * @@return AdminEqForcedSettleOrderRow[]
     * @@throws WEB3BaseException
     */
    public static AdminEqForcedSettleOrderRow[] getForcedSettleOrderList(
        String[] l_strOrderIdList,
        WEB3AdminForcedSettleSortKeyUnit[] l_sortKeys,
        Long l_lngAccountIdFrom,
        Long l_lngAccountIdTo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForcedSettleOrderList(String, "
            + "WEB3AdminForcedSettleSortKeyUnit[], Long, Long)";
        log.entering(STR_METHOD_NAME);

        if (l_strOrderIdList == null || l_strOrderIdList.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //検索条件文字列（：String）、検索条件データコンテナ（：ArrayList）を生成する。
        StringBuffer l_sbQuery = new StringBuffer();
        ArrayList l_arrayList = new ArrayList();

        //強制決済注文を取得する検索条件を作成する。
        //未承認の有効な強制決済注文のみ取得する検索条件を追加する。
        //　@　@検索条件文字列 = "order_open_status = ?"
        //　@　@　@+ " and forced_settle_reason_type is not null"
        //　@　@　@+ " and approve_status_type = ?"
        //　@　@データコンテナに"オープン"（注文有効状態）、
        //　@　@"未承認"（承認状態区分）を追加する。
        l_sbQuery.append("order_open_status = ?");
        l_sbQuery.append(" and forced_settle_reason_type is not null");
        l_sbQuery.append(" and approve_status_type = ?");
        l_arrayList.add(OrderOpenStatusEnum.OPEN.intValue() + "");
        l_arrayList.add(WEB3ApproveStatusType.UNAPPROVED);

        //注文IDを検索条件に追加する。
        //　@　@パラメータ.注文ID一覧の要素数分、検索条件に"?"を追加する。
        //　@　@　@検索条件文字列 += " and order_id in (?, ?,,,) "
        //　@　@　@データコンテナにパラメータ.注文ID一覧の全要素を追加する。
        l_sbQuery.append(" and order_id in ( ");
        for(int i = 0; i < l_strOrderIdList.length; i++)
        {
            l_sbQuery.append(" ? ,");
            l_arrayList.add(l_strOrderIdList[i]);
        }
        l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
        l_sbQuery.append(") ");

        //パラメータ.口座IDFrom != null　@かつ
        //　@　@パラメータ.口座IDTo != nullの場合、口座IDレンジ条件を
        //　@　@検索条件に追加する。
        //　@　@※非同期処理を行う場合に設定される。
        //　@　@検索条件文字列 += " and account_id >= ?"
        //　@　@　@　@　@　@　@　@+ " and account_id <= ?"
        //　@　@データコンテナ（以下の順で追加する。）
        //　@　@　@・パラメータ.口座IDFrom
        //　@　@　@・パラメータ.口座IDTo
        if (l_lngAccountIdFrom != null && l_lngAccountIdTo != null)
        {
            l_sbQuery.append(" and account_id >= ?");
            l_sbQuery.append(" and account_id <= ?");
            l_arrayList.add(l_lngAccountIdFrom);
            l_arrayList.add(l_lngAccountIdTo);
        }

        //ソート条件を作成する。
        //　@this.create強制決済注文ソート条件()をコールする。
        //　@[create強制決済注文ソート条件()に指定する引数]
        //　@　@ソートキー：　@パラメータ.ソートキー
        String l_strSort = WEB3AdminPMEquityDataManager.createForcedSettleSortCondition(l_sortKeys);

        //DBを検索する。
        //　@QueryProcessor.doFindAllQuery()メソッドをコールする。
        //　@[doFindAllQuery()にセットするパラメータ]
        //　@　@arg0：　@強制決済注文Row.TYPE
        //　@　@arg1：　@作成した検索条件文字列
        //　@　@arg2：　@create強制決済注文ソート条件()の戻り値
        //　@　@arg3：　@null
        //　@　@arg4：　@作成したデータコンテナ.toArray()の戻り値
        Object[] l_objValues = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_objValues);
        List l_lisResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResult = l_processor.doFindAllQuery(
                AdminEqForcedSettleOrderRow.TYPE,
                l_sbQuery.toString(),
                l_strSort,
                null,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //※検索結果が取得できなかった場合、nullを返却する。
        if (l_lisResult == null || l_lisResult.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        AdminEqForcedSettleOrderRow[] l_orderRows =
            new AdminEqForcedSettleOrderRow[l_lisResult.size()];
        l_lisResult.toArray(l_orderRows);

        log.exiting(STR_METHOD_NAME);
        return l_orderRows;
    }

    /**
     * (create強制決済注文ソート条件)<BR>
     * ソート条件を作成する。<BR>
     * <BR>
     * １）パラメータ.ソートキー == nullの場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * ２）ソート条件文字列(：String)を作成する。<BR>
     * <BR>
     * ３）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@３－１）ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@・「部店コード」　@→　@強制決済注文.部店ID<BR>
     * 　@　@・「顧客コード」　@→　@substr(強制決済注文.口座ID, 9, 6)<BR>
     * 　@　@・「強制決済理由」　@→　@強制決済注文.強制決済理由区分<BR>
     * 　@　@・「市場コード」　@→　@強制決済注文.市場ID<BR>
     * 　@　@・「銘柄コード」　@→　@強制決済注文.銘柄ID<BR>
     * 　@　@・「口座区分」　@→　@強制決済注文.税区分<BR>
     * 　@　@・「建区分」　@→　@強制決済注文.建区分<BR>
     * 　@　@・「弁済区分」　@→　@強制決済注文.弁済区分<BR>
     * 　@　@・「弁済期限値」　@→　@強制決済注文.弁済期限値<BR>
     * 　@　@・「建日」　@→　@強制決済注文.建日<BR>
     * 　@　@・「決済期日」　@→　@強制決済注文.期日<BR>
     * 　@　@・「作成日時」　@→　@強制決済注文.作成日時<BR>
     * 　@　@・「（非）承認日時」　@→　@強制決済注文.承認／非承認日時<BR>
     * 　@　@・「建株数」　@→　@強制決済注文.元建株数<BR>
     * 　@　@・「建単価」　@→　@強制決済注文.元建単価<BR>
     * 　@　@・「建代金」　@→　@強制決済注文.建代金<BR>
     * 　@　@・「注文株数」　@→　@強制決済注文.注文株数<BR>
     * 　@　@・「保証金預託率」　@→　@強制決済注文.保証金維持率<BR>
     * 　@　@・「承認状態」　@→　@強制決済注文.承認状態区分<BR>
     * <BR>
     * 　@３－２）ソートキー.昇順／降順に対応する取得順序<BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。<BR>
     * <BR>
     * ４）ソート条件末尾に、強制決済注文テーブル.更新日付を昇順指定で付加<BR>
     * <BR>
     * ５）作成したソート条件文字列を返却する。<BR>
     * @@param l_sortsKeys - (ソートキー)<BR>
     * 強制決済ソートキー<BR>
     * @@return String
     */
    public static String createForcedSettleSortCondition(
        WEB3AdminForcedSettleSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createForcedSettleSortCondition("
            + "WEB3AdminForcedSettleSortKeyUnit[])";
        log.entering(STR_METHOD_NAME);

        //パラメータ.ソートキー == nullの場合、nullを返却する。
        if (l_sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //ソート条件文字列(：String)を作成する。
        StringBuffer l_sbSort = new StringBuffer();

        //パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        for(int i = 0; i < l_sortKeys.length; i++)
        {
            WEB3AdminForcedSettleSortKeyUnit l_sortKeyUnit = l_sortKeys[i];
            //ソートキー.キー項目を対応する列物理名に変換し、作成したソート条件文字列に追加する。
            //・「部店コード」　@→　@強制決済注文.部店ID
            if (WEB3AdminEquitySortKeyItemNameDef.BRANCH_CODE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" branch_id ");
            }
            //・「顧客コード」　@→　@substr(強制決済注文.口座ID, 9, 6) 
            else if (WEB3AdminEquitySortKeyItemNameDef.ACCOUNT_CODE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" substr(account_id, 9, 6) ");
            }
            //・「強制決済理由」　@→　@強制決済注文.強制決済理由区分
            else if (WEB3AdminEquitySortKeyItemNameDef.FORCED_SETTLE_REASON.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" forced_settle_reason_type ");
            }
            //・「市場コード」　@→　@強制決済注文.市場ID
            else if (WEB3AdminEquitySortKeyItemNameDef.MARKET_CODE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" market_id ");
            }
            //・「銘柄コード」　@→　@強制決済注文.銘柄ID
            else if (WEB3AdminEquitySortKeyItemNameDef.PRODUCT_CODE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" product_id ");
            }
            //・「口座区分」　@→　@強制決済注文.税区分
            else if (WEB3AdminEquitySortKeyItemNameDef.TAX_TYPE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" tax_type ");
            }
            //・「建区分」　@→　@強制決済注文.建区分
            else if (WEB3AdminEquitySortKeyItemNameDef.CONTRACT_TYPE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" contract_type ");
            }
            //・「弁済区分」　@→　@強制決済注文.弁済区分
            else if (WEB3AdminEquitySortKeyItemNameDef.REPAYMENT_DIV.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" repayment_type ");
            }
            //・「弁済期限値」　@→　@強制決済注文.弁済期限値
            else if (WEB3AdminEquitySortKeyItemNameDef.REPAYMENTTIME_LIMIT.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" repayment_num ");
            }
            //・「建日」　@→　@強制決済注文.建日
            else if (WEB3AdminEquitySortKeyItemNameDef.OPEN_DATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" open_date ");
            }
            //・「決済期日」　@→　@強制決済注文.期日
            else if (WEB3AdminEquitySortKeyItemNameDef.CLOSE_DATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" close_date ");
            }
            //・「作成日時」　@→　@強制決済注文.作成日時
            else if (WEB3AdminEquitySortKeyItemNameDef.CREATE_DATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" created_timestamp ");
            }
            //・「（非）承認日時」　@→　@強制決済注文.承認／非承認日時
            else if (WEB3AdminEquitySortKeyItemNameDef.APPROVE_DATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" approve_timestamp ");
            }
            //・「建株数」　@→　@強制決済注文.元建株数
            else if (WEB3AdminEquitySortKeyItemNameDef.CONTRACT_QUANTITY.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" org_contract_quantity ");
            }
            //・「建単価」　@→　@強制決済注文.元建単価
            else if (WEB3AdminEquitySortKeyItemNameDef.CONTRACT_PRICE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" original_contract_price ");
            }
            //・「建代金」　@→　@強制決済注文.建代金
            else if (WEB3AdminEquitySortKeyItemNameDef.CONTRACT_EXEC_PRICE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" contract_amount ");
            }
            //・「注文株数」　@→　@強制決済注文.注文株数
            else if (WEB3AdminEquitySortKeyItemNameDef.ORDER_QUANTITY.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" quantity ");
            }
            //・「保証金預託率」　@→　@強制決済注文.保証金維持率
            else if (WEB3AdminEquitySortKeyItemNameDef.MARGIN_COLLATERAL_RATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" margin_maintenance_rate ");
            }
            //・「承認状態」　@→　@強制決済注文.承認状態区分
            else if (WEB3AdminEquitySortKeyItemNameDef.APPROVE_STATE.equals(l_sortKeyUnit.keyItem))
            {
                l_sbSort.append(" approve_status_type ");
            }
            else
            {
                continue;
            }
            //ソートキー.昇順／降順に対応する取得順序(asc or desc)をソート条件文字列に追加する。
            if (WEB3AscDescDef.ASC.equals(l_sortKeyUnit.ascDesc))
            {
                l_sbSort.append("ASC");
            }
            else
            {
                l_sbSort.append("DESC");
            }
            if (i < l_sortKeys.length)
            {
                l_sbSort.append(" , ");
            }
        }

        //ソート条件末尾に、強制決済注文テーブル.更新日付を昇順指定で付加
        l_sbSort.append("last_updated_timestamp ASC ");

        //作成したソート条件文字列を返却する。
        String l_strSort = l_sbSort.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strSort;
    }

    /**
     * (create強制決済注文照会情報一覧)<BR>
     * 引数の強制決済注文一覧より強制決済注文照会情報の<BR>
     * 一覧を作成する。<BR>
     * <BR>
     * １）　@戻り値を格納するArrayListを生成する。<BR>
     * <BR>
     * ２）　@部店プリファ@レンスより基準評価額を取得する。<BR>
     * <BR>
     * 　@[部店プリファ@レンステーブル検索条件]<BR>
     * 　@　@部店ID = パラメータ.強制決済注文一覧の0番目の要素.部店ID And<BR>
     * 　@　@プリファ@レンス名 = 信用取引強制決済基準評価額（強調表示判定用） And<BR>
     * 　@　@プリファ@レンス名の連番 = 1（固定）<BR>
     * <BR>
     * ３）　@パラメータ.強制決済注文一覧の要素数分、以下の処理を<BR>
     * 　@繰り返す。<BR>
     * 　@３－１）　@強制決済注文照会情報を生成する。<BR>
     * <BR>
     * 　@３－２）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@ID：　@処理対象の要素.注文ID<BR>
     * 　@　@部店コード：　@処理対象の要素.部店IDに該当する部店.部店コード<BR>
     * 　@　@顧客コード：　@処理対象の要素.口座コード <BR>
     * 　@　@顧客名：　@処理対象の要素.口座IDに該当する顧客.get表示顧客名()　@(*4) <BR>
     * 　@　@銘柄コード：　@処理対象の要素.銘柄コード <BR>
     * 　@　@銘柄名：　@処理対象の要素.銘柄IDに該当する株式銘柄.getDataSourceObject()<BR>
     * 　@　@の戻り値.銘柄名　@(*4)<BR>
     * 　@　@市場コード：　@処理対象の要素.市場IDに該当する市場.市場コード<BR>
     * 　@　@口座区分：　@株式データアダプタ.get口座区分(処理対象の要素.税区分)<BR>
     * 　@　@建区分：　@処理対象の要素.建区分<BR>
     * 　@　@建日：　@処理対象の要素.建日<BR>
     * 　@　@決済期日：　@処理対象の要素.期日<BR>
     * 　@　@建株数：　@処理対象の要素.元建株数<BR>
     * 　@　@建単価：　@処理対象の要素.元建単価<BR>
     * 　@　@建代金：　@処理対象の要素.建代金<BR>
     * 　@　@保証金預託率：　@処理対象の要素.保証金維持率<BR>
     * 　@　@追証発生日：　@処理対象の要素.追証発生日<BR>
     * 　@　@追証経過日数：　@処理対象の要素.追証経過日数<BR>
     * 　@　@弁済区分：　@処理対象の要素.弁済区分<BR>
     * 　@　@弁済期限値：　@処理対象の要素.弁済期限値<BR>
     * 　@　@注文株数：　@処理対象の要素.注文数量<BR>
     * 　@　@注文単価区分：　@処理対象の要素.指値 == 0の場合、"成行"、以外は"指値"。<BR>
     * 　@　@注文単価：　@処理対象の要素.指値<BR>
     * 　@　@発注日：　@処理対象の要素.発注日<BR>
     * 　@　@作成日時：　@処理対象の要素.作成日時<BR>
     * 　@　@（非）承認日時：　@処理対象の要素.承認／非承認日時<BR>
     * 　@　@承認状態：　@処理対象の要素.承認状態区分<BR>
     * 　@　@承認者コード：　@処理対象の要素.承認者コード<BR>
     * 　@　@注文エラー理由コード：　@処理対象の要素.注文エラー理由コード<BR>
     * 　@　@基準評価額超過フラグ：　@(*1)<BR>
     * 　@　@概算評価額：　@(*2)<BR>
     * 　@　@強制決済理由：　@(*3)<BR>
     * <BR>
     * 　@　@(*1) 「２）にて取得した基準評価額」と、「概算評価額（(*2)で取得）」とを比較し、<BR>
     * 　@　@　@　@取得した基準評価額 < 概算評価額<BR>
     * 　@　@　@であれば、true、以外はfalseをセットする。<BR>
     * <BR>
     * 　@　@　@　@※２）で該当レコードが取得できなかった場合、<BR>
     * 　@　@　@　@false（：超過なし）を固定でセットする。<BR>
     * <BR>
     * 　@　@(*2)パラメータ.承認区分≠nullの場合のみ、<BR>
     * 　@　@　@　@以下計算値をセットする。<BR>
     * <BR>
     * 　@　@　@　@　@[計算式]<BR>
     * 　@　@　@　@　@　@処理対象の要素.注文数量×基準値（＊）<BR>
     * <BR>
     * 　@　@　@　@　@　@(＊)処理対象の要素.銘柄ID、市場IDに該当する取引銘柄.基準値<BR>
     * <BR>
     * 　@　@(*3)this.create強制決済理由情報()の戻り値<BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@強制決済理由区分：　@処理対象の要素.強制決済理由区分<BR>
     * 　@　@　@　@　@　@部店：　@処理対象の要素.部店IDに該当する部店<BR>
     * <BR>
     * 　@　@　@　@※戻り値＝nullの場合、「データ不整合」の例外をthrowする。<BR>
     * <BR>
     * 　@　@(*4)取得できなかった場合は、nullをセットする。<BR>
     * <BR>
     * 　@３－３）　@プロパティセットしたインスタンスをArrayListに追加する。<BR>
     * <BR>
     * ４）　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_orderRowList - (強制決済注文一覧)<BR>
     * 仮注文一覧<BR>
     * @@param l_strApproveType - (承認区分)<BR>
     * 承認区分<BR>
     * @@return WEB3AdminForcedSettleTemporaryOrderUnit[]
     * @@throws WEB3BaseException
     */
    public static WEB3AdminForcedSettleTemporaryOrderUnit[] createForcedSettleOrderUnitList(
        AdminEqForcedSettleOrderRow[] l_orderRowList,
        String l_strApproveType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForcedSettleOrderUnitList("
            + "AdminEqForcedSettleOrderRow[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderRowList == null || l_orderRowList.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //戻り値を格納するArrayListを生成する。
        ArrayList l_arrayList = new ArrayList();

        //部店プリファ@レンスより基準評価額を取得する
        //[部店プリファ@レンステーブル検索条件]
        //　@　@部店ID = パラメータ.強制決済注文一覧の0番目の要素.部店ID And
        //　@　@プリファ@レンス名 = 信用取引強制決済基準評価額（強調表示判定用） And
        //　@　@プリファ@レンス名の連番 = 1（固定）
        BranchPreferencesRow l_preferencesRow = null;
        try
        {
            l_preferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_orderRowList[0].getBranchId(),
                    WEB3BranchPreferencesNameDef.MARGIN_FORCEDSETTLEORDER_BASEESTIMATEDASSET,
                    1);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //パラメータ.強制決済注文一覧の要素数分、以下の処理を繰り返す。
        for(int i = 0; i < l_orderRowList.length; i++)
        {
            //強制決済注文照会情報を生成する。
            WEB3AdminForcedSettleTemporaryOrderUnit l_orderUnitResult =
                new WEB3AdminForcedSettleTemporaryOrderUnit();

            //生成したインスタンスに以下のプロパティをセットする。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            //ID：　@処理対象の要素.注文ID
            if (!l_orderRowList[i].getOrderIdIsNull())
            {
                l_orderUnitResult.id = l_orderRowList[i].getOrderId() + "";
            }

            //部店コード：　@処理対象の要素.部店IDに該当する部店.部店コード
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Branch l_branch = null;
            try
            {
                l_branch =
                    l_accountManager.getBranch(l_orderRowList[i].getBranchId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            l_orderUnitResult.branchCode = l_branch.getBranchCode();

            //顧客コード：　@処理対象の要素.口座コード
            l_orderUnitResult.accountCode = l_orderRowList[i].getAccountCode();

            //顧客名：　@処理対象の要素.口座IDに該当する顧客.get表示顧客名()
            //(*4)取得できなかった場合は、nullをセットする。
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                l_mainAccount =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_orderRowList[i].getAccountId());
                l_orderUnitResult.accountName = l_mainAccount.getDisplayAccountName();
            }
            catch (NotFoundException l_nfe)
            {
                l_orderUnitResult.accountName = null;
            }

            //銘柄コード：　@処理対象の要素.銘柄コード
            l_orderUnitResult.productCode = l_orderRowList[i].getProductCode();

            //銘柄名：　@処理対象の要素.銘柄IDに該当する株式銘柄.getDataSourceObject()の戻り値.銘柄名
            //(*4)取得できなかった場合は、nullをセットする。
            EqTypeProduct l_eqtypeProduct = null;
            try
            {
                l_eqtypeProduct =
                    (EqTypeProduct)l_productManager.getProduct(l_orderRowList[i].getProductId());
                EqtypeProductRow l_productRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
                l_orderUnitResult.productName = l_productRow.getStandardName();
            }
            catch (NotFoundException l_nfe)
            {
                l_orderUnitResult.productName = null;
            }

            //市場コード：　@処理対象の要素.市場IDに該当する市場.市場コード
            WEB3GentradeFinObjectManager l_finObjectMgr =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = null;
            try
            {
                l_market = l_finObjectMgr.getMarket(l_orderRowList[i].getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            l_orderUnitResult.marketCode = l_market.getMarketCode();

            //口座区分：　@株式データアダプタ.get口座区分(処理対象の要素.税区分)
            l_orderUnitResult.taxType = WEB3EquityDataAdapter.getTaxType(l_orderRowList[i].getTaxType());

            //建区分：　@処理対象の要素.建区分
            l_orderUnitResult.contractType = l_orderRowList[i].getContractType().intValue() + "";

            //建日：　@処理対象の要素.建日
            l_orderUnitResult.openDate = l_orderRowList[i].getOpenDate();

            //決済期日：　@処理対象の要素.期日
            l_orderUnitResult.closeDate = l_orderRowList[i].getCloseDate();

            //建株数：　@処理対象の要素.元建株数
            l_orderUnitResult.contractQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderRowList[i].getOrgContractQuantity());

            //建単価：　@処理対象の要素.元建単価
            l_orderUnitResult.contractPrice =
                WEB3StringTypeUtility.formatNumber(l_orderRowList[i].getOriginalContractPrice());

            //建代金：　@処理対象の要素.建代金
            double l_dblContractAmount = l_orderRowList[i].getContractAmount();
            l_orderUnitResult.contractExecPrice =
                WEB3StringTypeUtility.formatNumber(l_dblContractAmount);

            // 保証金預託率：　@処理対象の要素.保証金維持率
            if (!l_orderRowList[i].getMarginMaintenanceRateIsNull())
            {
                double l_dbMarginMaintenanceRate = l_orderRowList[i].getMarginMaintenanceRate();
                l_orderUnitResult.marginCollateralRate =
                    WEB3StringTypeUtility.formatNumber(l_dbMarginMaintenanceRate);
            }

            //追証発生日：　@処理対象の要素.追証発生日
            l_orderUnitResult.additionalOccurredDate = l_orderRowList[i].getAdditionalMarginDate();

            // 追証経過日数：　@処理対象の要素.追証経過日数
            if (!l_orderRowList[i].getAdditionalMarginAccruedDaysIsNull())
            {
                l_orderUnitResult.additionalElapsedDays = WEB3StringTypeUtility.formatNumber(
                    l_orderRowList[i].getAdditionalMarginAccruedDays());
            }

            //弁済区分：　@処理対象の要素.弁済区分
            l_orderUnitResult.repaymentDiv = l_orderRowList[i].getRepaymentType();

            //弁済期限値：　@処理対象の要素.弁済期限値
            l_orderUnitResult.repaymentTimeLimit = l_orderRowList[i].getRepaymentNum() + "";

            //注文株数：　@処理対象の要素.注文数量
            l_orderUnitResult.orderQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderRowList[i].getQuantity());

            //注文単価区分：　@処理対象の要素.指値 == 0の場合、"成行"、以外は"指値"。
            if (l_orderRowList[i].getLimitPrice() == 0)
            {
                l_orderUnitResult.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_orderUnitResult.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }

            //注文単価：　@処理対象の要素.指値
            l_orderUnitResult.orderPrice =
                WEB3StringTypeUtility.formatNumber(l_orderRowList[i].getLimitPrice());

            //発注日：　@処理対象の要素.発注日
            l_orderUnitResult.orderBizDate =
                WEB3DateUtility.getDate(l_orderRowList[i].getBizDate(), "yyyyMMdd");

            //作成日時：　@処理対象の要素.作成日時
            l_orderUnitResult.createDate = l_orderRowList[i].getCreatedTimestamp();

            //（非）承認日時：　@処理対象の要素.承認／非承認日時
            l_orderUnitResult.approveDate = l_orderRowList[i].getApproveTimestamp();

            //承認状態：　@処理対象の要素.承認状態区分
            l_orderUnitResult.approveState = l_orderRowList[i].getApproveStatusType();

            //承認者コード：　@処理対象の要素.承認者コード
            l_orderUnitResult.checker = l_orderRowList[i].getApproverCode();

            //注文エラー理由コード：　@処理対象の要素.注文エラー理由コード
            l_orderUnitResult.errorReason = l_orderRowList[i].getErrorReasonCode();

            // (*2)パラメータ.承認区分≠nullの場合のみ、
            double l_dblEstimatedAsset = 0;
            if (l_strApproveType != null)
            {
                WEB3EquityTradedProduct l_tradedProduct = null;
                try
                {
                    // (＊)処理対象の要素.銘柄ID、市場IDに該当する取引銘柄.基準値
                    l_tradedProduct =
                        (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
                            l_orderRowList[i].getProductId(),
                            l_orderRowList[i].getMarketId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                EqtypeTradedProductRow l_eqtypeTradeProductRow =
                    (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
                double l_dbBasePrice = l_eqtypeTradeProductRow.getBasePrice();

                // 処理対象の要素.注文数量×基準値（＊）
                String l_strQuantity =
                    String.valueOf(l_orderRowList[i].getQuantity());
                String l_strBasePrice =
                    String.valueOf(l_dbBasePrice);
                BigDecimal l_bdQuantity = new BigDecimal(l_strQuantity);
                BigDecimal l_bdBasePrice = new BigDecimal(l_strBasePrice);
                BigDecimal l_bdResult = l_bdQuantity.multiply(l_bdBasePrice);

                l_dblEstimatedAsset = l_bdResult.doubleValue();
                l_orderUnitResult.estimatedAsset =
                    WEB3StringTypeUtility.formatNumber(l_dblEstimatedAsset);
            }

            //基準評価額超過フラグ：　@(*1)
            //(*1) 「２）にて取得した基準評価額」と、「概算評価額（(*2)で取得）」とを比較し、
            //取得した基準評価額 < 概算評価額
            //であれば、true、以外はfalseをセットする。
            //　@　@　@　@※２）で該当レコードが取得できなかった場合、
            //　@　@　@　@　@false（：超過なし）を固定でセットする。
            if (l_preferencesRow == null)
            {
                l_orderUnitResult.baseAssetOverFlag = false;
            }
            else
            {
                double l_dblBaseValue = Double.parseDouble(l_preferencesRow.getValue());
                if (l_dblBaseValue < l_dblEstimatedAsset)
                {
                    l_orderUnitResult.baseAssetOverFlag = true;
                }
                else
                {
                    l_orderUnitResult.baseAssetOverFlag = false;
                }
            }

            // (*3)this.create強制決済理由情報()の戻り値 
            // 強制決済理由区分：　@処理対象の要素.強制決済理由区分
            // 部店：　@処理対象の要素.部店IDに該当する部店
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                creatForcedSettleReasonUnit(
                    l_orderRowList[i].getForcedSettleReasonType(),
                    l_branch);
            // ※戻り値＝nullの場合、「データ不整合」の例外をthrowする。
            if (l_forcedSettleReasonUnit == null)
            {
                log.debug("データ不整合");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "データ不整合エラー。");
            }
            l_orderUnitResult.forcedSettleReason = l_forcedSettleReasonUnit;

            //プロパティセットしたインスタンスをArrayListに追加する。
            l_arrayList.add(l_orderUnitResult);
        }

        //生成したArrayList.toArray()の戻り値を返却する。
        WEB3AdminForcedSettleTemporaryOrderUnit[] l_orderUnit =
            new WEB3AdminForcedSettleTemporaryOrderUnit[l_arrayList.size()];
        l_arrayList.toArray(l_orderUnit);
        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (is承認／非承認処理対象注文)<BR>
     * 承認／非承認処理対象の注文かどうかチェックする。<BR>
     * ※本メソッドをコールする前に、取引カレンダコンテキストの<BR>
     * 　@セットを必ず行っておくこと。<BR>
     * <BR>
     * 以下の条件のいずれかに該当する場合はfalseを返却する。<BR>
     * 　@・パラメータ.注文単位Row.承認状態区分 != "未承認"<BR>
     * 　@・パラメータ.注文単位Row.注文有効状態 == "クローズ"<BR>
     * 　@・取引時間管理.get発注日(パラメータ.注文単位Row.発注日)が<BR>
     * 　@　@例外をスロー。<BR>
     * <BR>
     * 上記以外はtrueを返却する。<BR>
     * @@param l_orderUnitRow - (注文単位Row)<BR>
     * 注文単位Rowオブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public static boolean isApproveProcessTargetOrder(EqtypeOrderUnitRow l_orderUnitRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isApproveProcessTargetOrder(EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //以下の条件のいずれかに該当する場合はfalseを返却する。
        //　@・パラメータ.注文単位Row.承認状態区分 != "未承認"
        //  ・パラメータ.注文単位Row.注文有効状態 == "クローズ"
        //　@・取引時間管理.get発注日(パラメータ.注文単位Row.発注日)が例外をスロー。
        if (!WEB3ApproveStatusType.UNAPPROVED.equals(l_orderUnitRow.getApproveStatusType())
            || OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        try
        {
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd"));
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get強制決済用値幅上限)<BR>
     * 値幅上限値を計算し、返却する。<BR>
     * ※強制決済サービスにおいて、売建株かつマーケットメイク銘柄の場合、<BR>
     * 　@市場：”東証”、基準値：”前日終値”で計算した値幅上限で、決済を行う。<BR>
     * <BR>
     * １）　@基準値取得<BR>
     * 　@引数.取引銘柄.基準値（終値）を取得する。<BR>
     * <BR>
     * ２）　@値幅取得 <BR>
     * 　@this.calc値幅をcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@市場コード：　@”東証”（固定）<BR>
     * 　@　@　@基準値：　@１）で取得した基準値（終値）<BR>
     * <BR>
     * ３）　@刻み値取得 <BR>
     * 　@３－１）　@刻み値取得用基準値を計算<BR>
     * 　@　@以下計算式で、刻み値取得用基準値を算出する。<BR>
     * <BR>
     * 　@　@　@＜計算式＞<BR>
     * 　@　@　@　@刻み値取得用基準値＝１）で取得した基準値＋２）で取得した制限値幅<BR>
     * <BR>
     * 　@３－２）<BR>
     * 　@　@【呼値テーブル】を、以下の条件で検索し、刻み値を取得する。<BR>
     * <BR>
     * 　@　@　@　@＜検索条件＞<BR>
     *  　@　@呼値テーブル.市場コード　@＝ ”東証”<BR>
     * 　@　@　@and （（呼値テーブル.下限値 ＜ 刻み値取得用基準値<BR>
     * 　@　@　@and （刻み値取得用基準値 ≦ 呼値テーブル.上限値））<BR>
     * <BR>
     * ４）　@値幅上限値取得 <BR>
     * 　@株式発注審査個別チェック.calc値幅上限(基準値,値幅,指値単位)をコールする。 <BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@基準値：　@１）で取得した基準値（終値）<BR>
     * 　@　@　@値幅：　@２）の戻り値<BR>
     * 　@　@　@指値単位：　@３－２）の戻り値<BR>
     * <BR>
     * ５）　@４）calc値幅上限()の戻り値を返却する。 <BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄
     * @@return double
     * @@throws WEB3BaseException
     */
    public static double getForcedSettleHighPriceRange(
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForcedSettleHighPriceRange("
            + "WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //基準値取得
        //　@引数.取引銘柄.基準値（終値）を取得する。
        double l_dblLCPrice = l_tradedProduct.getLastClosingPrice();

        //値幅取得
        //　@this.calc値幅をcallする。
        //　@　@[引数]
        //　@　@　@市場コード：　@”東証”（固定）
        //　@　@　@基準値：　@１）で取得した基準値（終値）
        double l_dblRange = WEB3AdminPMEquityDataManager.calcPriceRange(
            WEB3MarketCodeDef.TOKYO,
            l_dblLCPrice);

        //刻み値取得
        //刻み値取得用基準値を計算
        //　@＜計算式＞
        //　@　@刻み値取得用基準値＝１）で取得した基準値＋２）で取得した制限値幅
        double l_dblTickValue = l_dblLCPrice + l_dblRange;

        //【呼値テーブル】を、以下の条件で検索し、刻み値を取得する。
        //　@　@　@　@＜検索条件＞
        //　@　@呼値テーブル.市場コード　@＝ ”東証”
        //　@　@　@and （（呼値テーブル.下限値 ＜ 刻み値取得用基準値
        //　@　@　@and （刻み値取得用基準値 ≦ 呼値テーブル.上限値））
        StringBuffer l_sbQuery = new StringBuffer();
        l_sbQuery.append(" market_code = ? ");
        l_sbQuery.append(" and ((low_price < ?) ");
        l_sbQuery.append(" and (? <= cap_price)) ");

        Object[] l_object = {
            WEB3MarketCodeDef.TOKYO,
            WEB3StringTypeUtility.formatNumber(l_dblTickValue),
            WEB3StringTypeUtility.formatNumber(l_dblTickValue)};

        List l_listResult = null;
        double l_dblValueUnit = 0;

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_listResult = l_processor.doFindAllQuery(
                EquityTickValuesMstRow.TYPE,
                l_sbQuery.toString(),
                l_object);

            if (l_listResult.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME);
            }

            EquityTickValuesMstRow l_mstRow =
                (EquityTickValuesMstRow)l_listResult.get(0);
            l_dblValueUnit = l_mstRow.getTickValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //値幅上限値取得
        //　@株式発注審査個別チェック.calc値幅上限(基準値,値幅,指値単位)をコールする。
        //　@[引数]
        //　@　@　@基準値：　@１）で取得した基準値（終値）
        //　@　@　@値幅：　@２）の戻り値
        //　@　@　@指値単位：　@３－２）の戻り値
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations)
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        double l_dblHighPriceRange =
            l_orderMgrResVal.calcStopHighPrice(l_dblLCPrice, l_dblRange, l_dblValueUnit);

        log.exiting(STR_METHOD_NAME);
        return l_dblHighPriceRange;
    }

    /**
     * (get強制決済用値幅下限)<BR>
     * 値幅下限値を計算し、返却する。<BR>
     * ※強制決済サービスにおいて、買建株かつマーケットメイク銘柄の場合、<BR>
     * 　@市場：”東証”、基準値：”前日終値”で計算した値幅下限で、決済を行う。<BR>
     * <BR>
     * １）　@基準値取得<BR>
     * 　@引数.取引銘柄.基準値（終値）を取得する。<BR>
     * <BR>
     * ２）　@値幅取得 <BR>
     * 　@this.calc値幅をcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@市場コード：　@”東証”（固定）<BR>
     * 　@　@　@基準値：　@１）で取得した基準値（終値）<BR>
     * <BR>
     * ３）　@値幅下限値取得 <BR>
     * 　@[（基準値－値幅）≦0の場合<BR>
     * 　@　@1を返却する。 <BR>
     * <BR>
     * 　@[以外の場合]<BR>
     * 　@　@（基準値－値幅）を返却する。 <BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public static double getForcedSettleLowPriceRange(
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForcedSettleLowPriceRange("
            + "WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //基準値取得
        //　@引数.取引銘柄.基準値（終値）を取得する。
        double l_dblLCPrice = l_tradedProduct.getLastClosingPrice();

        //値幅取得
        //　@this.calc値幅をcallする。
        //　@　@[引数]
        //　@　@　@市場コード：　@”東証”（固定）
        //　@　@　@基準値：　@１）で取得した基準値（終値）
        double l_dblRange = WEB3AdminPMEquityDataManager.calcPriceRange(
            WEB3MarketCodeDef.TOKYO,
            l_dblLCPrice);

        //値幅下限値取得
        //　@[（基準値－値幅）≦0の場合
        //　@　@1を返却する。
        BigDecimal l_bdLowPriceRange = new BigDecimal("0");
        BigDecimal l_bdLCPrice = new BigDecimal(l_dblLCPrice + "");
        BigDecimal l_bdRange = new BigDecimal(l_dblRange + "");
        l_bdLowPriceRange = l_bdLCPrice.subtract(l_bdRange);
        if(l_bdLowPriceRange.doubleValue() <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }

        //[以外の場合]
        //　@（基準値－値幅）を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_bdLowPriceRange.doubleValue();
    }

    /**
     * (calc値幅)<BR>
     * 引数の市場コード、基準値に該当する値幅を取得して、返却する。<BR>
     * ※取引銘柄の値幅区分は考慮なし。<BR>
     * <BR>
     * １）　@制限値幅を取得する<BR>
     * 　@　@【値幅テーブル】を、以下の条件で検索し、該当レコードの「値幅」を「制限値幅」とする。<BR>
     * <BR>
     * 　@　@　@　@＜検索条件＞<BR>
     *  　@　@値幅テーブル.市場コード　@＝ 引数.市場コード<BR>
     * 　@　@　@and （（値幅テーブル.下限値 ≦ 引数.基準値）<BR>
     * 　@　@　@and （引数.基準値 ＜ 値幅テーブル.上限値））<BR>
     * <BR>
     * ２）　@制限値幅を返却する<BR>
     * 　@１）で取得した値を返却する。<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_dblBaseValue - (基準値)<BR>
     * 基準値<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public static double calcPriceRange(String l_strMarketCode, double l_dblBaseValue)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcPriceRange(String, double)";
        log.entering(STR_METHOD_NAME);

        //制限値幅を取得する
        //　@【値幅テーブル】を、以下の条件で検索し、該当レコードの「値幅」を「制限値幅」とする。
        //　@　@　@　@＜検索条件＞
        //　@　@値幅テーブル.市場コード　@＝ 引数.市場コード
        //　@　@　@and （（値幅テーブル.下限値 ≦ 引数.基準値）
        // 　@　@　@and （引数.基準値 ＜ 値幅テーブル.上限値））
        StringBuffer l_sbQuery = new StringBuffer();
        l_sbQuery.append(" market_code = ? ");
        l_sbQuery.append(" and ((low_price <= ? ) ");
        l_sbQuery.append(" and (? < cap_price)) ");
        Object[] l_object = {
            l_strMarketCode,
            WEB3StringTypeUtility.formatNumber(l_dblBaseValue),
            WEB3StringTypeUtility.formatNumber(l_dblBaseValue)};

        List l_listResult = null;
        double l_dblRange = 0;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_listResult = l_processor.doFindAllQuery(
                EquityLimitPriceRangeMstRow.TYPE,
                l_sbQuery.toString(),
                l_object);

            if (l_listResult.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME);
            }

            EquityLimitPriceRangeMstRow l_mstRow =
                (EquityLimitPriceRangeMstRow)l_listResult.get(0);
            l_dblRange = l_mstRow.getRange();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //制限値幅を返却する
        log.exiting(STR_METHOD_NAME);
        return l_dblRange;
    }

    /**
     * (get決済中注文一覧)<BR>
     * 強制決済対象建株に対する決済注文を検索する。<BR>
     * 検索対象となる決済注文は以下。<BR>
     * 　@・返済、現引、現渡<BR>
     * 　@・保有建株に対する予約注文（返済、現引、現渡）<BR>
     * <BR>
     * 　@※強制決済注文も取得する。<BR>
     * <BR>
     * <BR>
     * １）　@戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２）　@引数.建株Rowの建株IDに紐付く、建株返済指定情報を取得する。<BR>
     * 　@QueryProcessor.doFindAllQuery()により、<BR>
     * 　@以下検索条件にて、建株返済指定情報テーブル（eqtype_closing_contract_spec）を検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@建株返済指定情報.建株ID ＝ 引数.建株Row.getContractId()<BR>
     * <BR>
     * ３）　@２）でレコードが取得できた場合、注文単位を取得する。<BR>
     * 　@３－１）　@注文IDの取得<BR>
     * 　@　@２）の戻り値の要素数分Loop処理し、全ての要素の注文IDを取得する。<BR>
     * <BR>
     * 　@３－２）　@注文単位の検索<BR>
     * 　@　@QueryProcessor.doFindAllQuery()により、<BR>
     * 　@　@以下検索条件にて、注文単位テーブル（eqtype_order_unit）を検索する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@注文単位.注文ID ｉｎ （ ３－１）で取得した注文ＩＤ）かつ、<BR>
     * 　@　@　@　@注文単位.注文有効状態 ＝ "オープン"<BR>
     * <BR>
     * 　@３－３）検索結果を戻り値オブジェクトに追加する。<BR>
     * <BR>
     * ４）　@連続注文を実施/未実施による処理分岐<BR>
     * 　@引数.is予約注文考慮要 ＝ falseの場合、<BR>
     * 　@取得した注文単位のLisｔを返却する。（return;）<BR>
     * <BR>
     * 　@以外の場合、以降の処理を行う。<BR>
     * 　@　@※連続注文実施会社のみ、予約注文単位の検索を行う。<BR>
     * <BR>
     * ５）　@引数.建株Rowの建株IDに紐付く、株式予約建株返済指定情報を取得する。<BR>
     * 　@※既存建に対する決済の予約注文のみ、株式予約建株返済指定情報に登録されている。<BR>
     * <BR>
     * 　@QueryProcessor.doFindAllQuery()により、<BR>
     * 　@以下検索条件にて、株式予約建株返済指定情報ﾃｰﾌﾞﾙ（rsv_eq_closing_contract_spec）を検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@株式予約建株返済指定情報.建株ID ＝ 引数.建株Row.getContractId()<BR>
     * <BR>
     * ６）　@５）でレコードが取得できた場合、注文単位を取得する。<BR>
     * 　@６－１）　@注文IDの取得<BR>
     * 　@　@５）の戻り値の要素数分Loop処理し、全ての要素の注文IDを取得する。<BR>
     * <BR>
     * 　@６－２）　@予約注文単位の検索<BR>
     * 　@　@QueryProcessor.doFindAllQuery()により、<BR>
     * 　@　@以下検索条件にて、株式予約注文単位テーブル（rsv_eq_order_unit）を検索する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@株式予約注文単位.注文ID ｉｎ （ ６－１）で取得した注文ＩＤ）かつ、<BR>
     * 　@　@　@　@株式予約注文単位.注文有効状態 ＝ "オープン"<BR>
     * <BR>
     * 　@６－３）検索結果を戻り値オブジェクトに追加する。<BR>
     * <BR>
     * ７）　@戻り値返却<BR>
     * 　@取得した注文単位のListを返却する。<BR>
     * @@param l_contractRow - (建株Row)<BR>
     * 建株Rowオブジェクト<BR>
     * @@param l_isReservationOrder - (is予約注文考慮要)<BR>
     * 予約注文も検索するかどうかのフラグ<BR>
     * <BR>
     * true：　@予約注文を検索する。<BR>
     * false：　@予約注文の検索を行わない。<BR>
     * @@return ArrayList
     */
    public static ArrayList getCloseOrderList(
        EqtypeContractRow l_contractRow,
        boolean l_isReservationOrder)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCloseOrderList(EqtypeContractRow, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_contractRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        QueryProcessor l_processor = null;
        List l_lisResult = null;
        List l_lisRsvResult = null;

        try
        {
            l_processor = Processors.getDefaultProcessor();
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //戻り値オブジェクトのインスタンスを生成する。
        ArrayList l_arrayList = new ArrayList();

        //引数.建株Rowの建株IDに紐付く、建株返済指定情報を取得する。
        // QueryProcessor.doFindAllQuery()により、
        //以下検索条件にて、建株返済指定情報テーブル（eqtype_closing_contract_spec）を検索する。
        //　@　@[条件]
        //　@　@　@建株返済指定情報.建株ID ＝ 引数.建株Row.getContractId()
        String l_strQuery = " contract_id = ? ";
        Object[] l_object = {l_contractRow.getContractId() + ""};
        try
        {
            l_lisResult = l_processor.doFindAllQuery(
                EqtypeClosingContractSpecRow.TYPE,
                l_strQuery,
                l_object);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //レコードが取得できた場合、注文単位を取得する。
        //注文IDの取得
        //戻り値の要素数分Loop処理し、全ての要素の注文IDを取得する。
        int l_intSize = 0;
        if (l_lisResult != null && l_lisResult.size() != 0)
        {
            l_intSize = l_lisResult.size();
        }

        if (l_intSize != 0)
        {
            //注文単位の検索
            //　@　@QueryProcessor.doFindAllQuery()により、
            //　@　@以下検索条件にて、注文単位テーブル（eqtype_order_unit）を検索する。
            //　@　@　@[条件]
            //　@　@　@　@注文単位.注文ID ｉｎ （ ３－１）で取得した注文ＩＤ）かつ、
            //　@　@　@　@注文単位.注文有効状態 ＝ "オープン"
            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" order_id in ( ");
            ArrayList l_orderUnit = new ArrayList();
            for(int i = 0; i < l_intSize; i++)
            {
                EqtypeClosingContractSpecRow l_specRow =
                    (EqtypeClosingContractSpecRow)l_lisResult.get(i);
                String l_strOrderId = l_specRow.getOrderId() + "";

                l_sbQuery.append(" ? ,");
                l_orderUnit.add(l_strOrderId);
            }
            l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
            l_sbQuery.append(") ");
            l_sbQuery.append(" and order_open_status = ? ");
            l_orderUnit.add(OrderOpenStatusEnum.OPEN.intValue() + "");
            Object[] l_strValues = new Object[l_orderUnit.size()];
            l_orderUnit.toArray(l_strValues);

            try
            {
                l_lisResult = l_processor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbQuery.toString(),
                    l_strValues);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //検索結果を戻り値オブジェクトに追加する。
            if (l_lisResult != null && l_lisResult.size() != 0)
            {
                for(int i = 0; i < l_lisResult.size(); i++)
                {
                    l_arrayList.add(l_lisResult.get(i));
                }
            }
        }

        //連続注文を実施/未実施による処理分岐
        //　@引数.is予約注文考慮要 ＝ falseの場合、
        //　@取得した注文単位のLisｔを返却する。（return;）
        if(!l_isReservationOrder)
        {
            log.exiting(STR_METHOD_NAME);
            return l_arrayList;
        }

        //以外の場合、以降の処理を行う。
        //※連続注文実施会社のみ、予約注文単位の検索を行う。
        //引数.建株Rowの建株IDに紐付く、株式予約建株返済指定情報を取得する。
        //　@※既存建に対する決済の予約注文のみ、株式予約建株返済指定情報に登録されている。
        //　@QueryProcessor.doFindAllQuery()により、
        //　@以下検索条件にて、株式予約建株返済指定情報ﾃｰﾌﾞﾙ（rsv_eq_closing_contract_spec）を検索する。
        //　@　@[条件]
        //　@　@　@株式予約建株返済指定情報.建株ID ＝ 引数.建株Row.getContractId()
        try
        {
            l_lisRsvResult = l_processor.doFindAllQuery(
                RsvEqClosingContractSpecRow.TYPE,
                l_strQuery,
                l_object);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intRsvResultCnt = 0;
        if (!l_lisRsvResult.isEmpty())
        {
            l_intRsvResultCnt = l_lisRsvResult.size();
        }
        // でレコードが取得できた場合、注文単位を取得する。
        if (l_intRsvResultCnt != 0)
        {
            // 注文IDの取得
            //    ５）の戻り値の要素数分Loop処理し、全ての要素の注文IDを取得する。
            //    　@予約注文単位の検索
            //     　@　@QueryProcessor.doFindAllQuery()により、
            //     　@　@以下検索条件にて、株式予約注文単位テーブル（rsv_eq_order_unit）を検索する。
            //     　@　@　@[条件]
            //     　@　@　@　@株式予約注文単位.注文IDｉｎ（６－１）で取得した注文ＩＤ）かつ、
            //     　@　@　@　@株式予約注文単位.注文有効状態＝"オープン"
            StringBuffer l_sbQuery = new StringBuffer();
            l_sbQuery.append(" order_id in ( ");
            ArrayList l_orderUnit = new ArrayList();
            for(int i = 0; i < l_intRsvResultCnt; i++)
            {
                RsvEqClosingContractSpecRow l_rsvEqClosingContractSpecRow =
                    (RsvEqClosingContractSpecRow)l_lisRsvResult.get(i);
                String l_strOrderId = l_rsvEqClosingContractSpecRow.getOrderId() + "";

                l_sbQuery.append(" ? ,");
                l_orderUnit.add(l_strOrderId);
            }
            l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
            l_sbQuery.append(") ");
            l_sbQuery.append(" and order_open_status = ? ");
            l_orderUnit.add(OrderOpenStatusEnum.OPEN.intValue() + "");
            Object[] l_objValues = new Object[l_orderUnit.size()];
            l_orderUnit.toArray(l_objValues);

            try
            {
                l_lisRsvResult = l_processor.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE,
                    l_sbQuery.toString(),
                    l_objValues);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //検索結果を戻り値オブジェクトに追加する。
            if (l_lisRsvResult != null && l_lisRsvResult.size() != 0)
            {
                for(int i = 0; i < l_lisRsvResult.size(); i++)
                {
                    l_arrayList.add(l_lisRsvResult.get(i));
                }
            }
        }

        //戻り値返却
        //取得した注文単位のListを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_arrayList;
    }

    /**
     * (create強制決済理由情報)<BR>
     * 引数の強制決済理由区分に該当する、<BR>
     * 強制決済理由情報を作成する。<BR>
     * <BR>
     * １）　@引数.強制決済理由区分＝"期日到来"の場合<BR>
     * 　@１－１）　@強制決済理由情報インスタンスを生成する。<BR>
     * <BR>
     * 　@１－２）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@強制決済理由：　@"決済期日到来"<BR>
     * 　@　@保証金維持率：　@null<BR>
     * 　@　@追証経過日数上限：　@null<BR>
     * <BR>
     * <BR>
     * ２）　@引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・軽度）"の場合<BR>
     * 　@２－１）　@保証金維持率（軽度）取得<BR>
     * 　@　@以下の条件で部店プリファ@レンステーブルを検索する。<BR>
     * 　@　@[条件]<BR>
     * 　@　@　@部店ID = 引数.部店.部店ID And<BR>
     * 　@　@　@プリファ@レンス名 = "first.deposit.rate1"<BR>
     * <BR>
     * 　@　@※レコードが取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * 　@２－２）　@追証経過日数取得<BR>
     * 　@　@以下の条件で部店プリファ@レンステーブルを検索する。<BR>
     * 　@　@[条件]<BR>
     * 　@　@　@部店ID = 引数.部店.部店ID And<BR>
     * 　@　@　@プリファ@レンス名 = "first.margin.pass.day1"<BR>
     * <BR>
     * 　@　@※レコードが取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * 　@２－３）　@強制決済理由情報インスタンスを生成する。<BR>
     * <BR>
     * 　@２－４）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@強制決済理由：　@"保証金維持率割（オンライン開始前・軽度）"<BR>
     * 　@　@保証金維持率：　@２－１）にて取得したレコード.プリファ@レンスの値<BR>
     * 　@　@追証経過日数上限：　@２－２）にて取得したレコード.プリファ@レンスの値<BR>
     * <BR>
     * <BR>
     * ３）　@引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・重度）"の場合<BR>
     * 　@３－１）　@保証金維持率（重度）取得<BR>
     * 　@　@以下の条件で部店プリファ@レンステーブルを検索する。<BR>
     * 　@　@[条件]<BR>
     * 　@　@　@部店ID = 引数.部店.部店ID And<BR>
     * 　@　@　@プリファ@レンス名 = "first.deposit.rate2"<BR>
     * <BR>
     * 　@　@※レコードが取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * 　@３－２）　@追証経過日数取得<BR>
     * 　@　@以下の条件で部店プリファ@レンステーブルを検索する。<BR>
     * 　@　@[条件]<BR>
     * 　@　@　@部店ID = パラメータ.部店.部店ID And<BR>
     * 　@　@　@プリファ@レンス名 = "first.margin.pass.day2"<BR>
     * <BR>
     * 　@　@※レコードが取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * 　@３－３）　@強制決済理由情報インスタンスを生成する。<BR>
     * <BR>
     * 　@３－４）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@強制決済理由：　@"保証金維持率割（オンライン開始前・重度）"<BR>
     * 　@　@保証金維持率：　@３－１）にて取得したレコード.プリファ@レンスの値<BR>
     * 　@　@追証経過日数上限：　@３－２）にて取得したレコード.プリファ@レンスの値<BR>
     * <BR>
     * <BR>
     * ４）　@引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・法@定）"の場合 <BR>
     * 　@４－１）　@強制決済理由情報インスタンスを生成する。 <BR>
     * <BR>
     * 　@４－２）　@生成したインスタンスに以下のプロパティをセットする。 <BR>
     * 　@　@強制決済理由：　@"保証金維持率割れ（オンライン開始前・法@定）" <BR>
     * 　@　@保証金維持率：　@null <BR>
     * 　@　@追証経過日数上限：　@null <BR>
     * <BR>
     * <BR>
     * ５）　@引数.強制決済理由区分＝"保証金維持率割れ（場間）"の場合 <BR>
     * 　@５－１）　@強制決済理由情報インスタンスを生成する。 <BR>
     * <BR>
     * 　@５－２）　@生成したインスタンスに以下のプロパティをセットする。 <BR>
     * 　@　@強制決済理由：　@"保証金維持率割（場間）" <BR>
     * 　@　@保証金維持率：　@null <BR>
     * 　@　@追証経過日数上限：　@null <BR>
     * <BR>
     * <BR>
     * ６）　@引数.強制決済理由区分＝"手動強制決済"の場合 <BR>
     * 　@６－１）　@強制決済理由情報インスタンスを生成する。 <BR>
     * <BR>
     * 　@６－２）　@生成したインスタンスに以下のプロパティをセットする。 <BR>
     * 　@　@強制決済理由：　@"手動強制決済" <BR>
     * 　@　@保証金維持率：　@null <BR>
     * 　@　@追証経過日数上限：　@null <BR>
     * <BR>
     * <BR>
     * ７）　@生成したインスタンスを返却する。 <BR>
     * <BR>
     * 　@　@※引数.強制決済理由区分が全てに当てはまらない場合は、 <BR>
     * 　@　@　@「パラメータ値不正」の例外をthrowする。 <BR>
     * <BR>
     * @@param l_strForcedSettleReasonDiv - (強制決済理由区分)<BR>
     * 強制決済理由区分
     * @@param l_branch - (部店)
     * 部店
     * @@return WEB3AdminForcedSettleReasonUnit
     * @@throws WEB3BaseException
     */
    public static WEB3AdminForcedSettleReasonUnit creatForcedSettleReasonUnit(
        String l_strForcedSettleReasonDiv,
        Branch l_branch) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " creatForcedSettleReasonUnit(String, Branch)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １）　@引数.強制決済理由区分＝"期日到来"の場合
        if (WEB3ForcedSettleReasonType.FIXED_DATE_COMING.equals(l_strForcedSettleReasonDiv))
        {
            // １－１）　@強制決済理由情報インスタンスを生成する。
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                new WEB3AdminForcedSettleReasonUnit();
            // １－２）　@生成したインスタンスに以下のプロパティをセットする。
            // 強制決済理由：　@"決済期日到来"
            // 保証金維持率：　@null
            // 追証経過日数上限：　@null
            l_forcedSettleReasonUnit.forcedSettleReason =
                WEB3ForcedSettleReasonType.FIXED_DATE_COMING;
            l_forcedSettleReasonUnit.marginMaintenanceRate = null;
            l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit = null;

            log.exiting(STR_METHOD_NAME);
            return l_forcedSettleReasonUnit;
        }

        // ２）　@引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・軽度）"の場合
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS.equals(
            l_strForcedSettleReasonDiv))
        {
           try
           {
               // ２－１）　@保証金維持率（軽度）取得
                // 以下の条件で部店プリファ@レンステーブルを検索する。
                // 部店ID = 引数.部店.部店ID And
                // プリファ@レンス名 = "first.deposit.rate1"
                StringBuffer l_sbQueryCond = new StringBuffer();
                l_sbQueryCond.append(" branch_id = ? ");
                l_sbQueryCond.append(" and name = ? ");

                Object[] l_objWhere = {new Long(l_branch.getBranchId()),
                    WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1};

                QueryProcessor l_processor = Processors.getDefaultProcessor();
                List l_lisResultList = l_processor.doFindAllQuery(
                    BranchPreferencesRow.TYPE,
                    l_sbQueryCond.toString(),
                    l_objWhere);

                // ※レコードが取得できなかった場合、nullを返却する。
                if (l_lisResultList.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                // ２－２）　@追証経過日数取得
                // 以下の条件で部店プリファ@レンステーブルを検索する。
                // 部店ID = 引数.部店.部店ID And
                // プリファ@レンス名 = "first.margin.pass.day1"
                StringBuffer l_sbQueryCondNum = new StringBuffer();
                l_sbQueryCondNum.append(" branch_id = ? ");
                l_sbQueryCondNum.append(" and name = ? ");

                Object[] l_objWhereNum = {new Long(l_branch.getBranchId()),
                    WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY1};

                List l_lisResultListNums = l_processor.doFindAllQuery(
                    BranchPreferencesRow.TYPE,
                    l_sbQueryCondNum.toString(),
                    l_objWhereNum);

                // ※レコードが取得できなかった場合、nullを返却する。 
                if (l_lisResultListNums.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                // ２－３）　@強制決済理由情報インスタンスを生成する。
                WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                    new WEB3AdminForcedSettleReasonUnit();

                // ２－４）　@生成したインスタンスに以下のプロパティをセットする。
                // 強制決済理由：　@"保証金維持率割（オンライン開始前・軽度）"
                l_forcedSettleReasonUnit.forcedSettleReason =
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS;
                // 保証金維持率：　@２－１）にて取得したレコード.プリファ@レンスの値
                BranchPreferencesRow l_branchPreferenceRow =
                    (BranchPreferencesRow)l_lisResultList.get(0);
                l_forcedSettleReasonUnit.marginMaintenanceRate =
                    l_branchPreferenceRow.getValue();
                // 追証経過日数上限：　@２－２）にて取得したレコード.プリファ@レンスの値
                BranchPreferencesRow l_branchPreferenceRow1 =
                    (BranchPreferencesRow)l_lisResultListNums.get(0);
                l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit =
                    l_branchPreferenceRow1.getValue();

                log.exiting(STR_METHOD_NAME);
                return l_forcedSettleReasonUnit;
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        // ３）　@引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・重度）"の場合
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS.equals(
            l_strForcedSettleReasonDiv))
        {
            try
            {
                // ３－１）　@保証金維持率（重度）取得
                // 以下の条件で部店プリファ@レンステーブルを検索する。
                // 部店ID = 引数.部店.部店ID And
                // プリファ@レンス名 = "first.deposit.rate2"
                StringBuffer l_sbQueryCond = new StringBuffer();
                l_sbQueryCond.append(" branch_id = ? ");
                l_sbQueryCond.append(" and name = ? ");

                Object[] l_objWhere = {new Long(l_branch.getBranchId()),
                    WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE2};

                QueryProcessor l_processor = Processors.getDefaultProcessor();
                List l_lisResultList = l_processor.doFindAllQuery(
                    BranchPreferencesRow.TYPE,
                    l_sbQueryCond.toString(),
                    l_objWhere);

                // ※レコードが取得できなかった場合、nullを返却する。
                if (l_lisResultList.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                // ３－２）　@追証経過日数取得
                // 以下の条件で部店プリファ@レンステーブルを検索する。
                // 部店ID = パラメータ.部店.部店ID And
                // プリファ@レンス名 = "first.margin.pass.day2"
                StringBuffer l_sbQueryCond2 = new StringBuffer();
                l_sbQueryCond2.append(" branch_id = ? ");
                l_sbQueryCond2.append(" and name = ? ");

                Object[] l_objWhere2 = {new Long(l_branch.getBranchId()),
                    WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY2};

                List l_lisResultList2 = l_processor.doFindAllQuery(
                    BranchPreferencesRow.TYPE,
                    l_sbQueryCond2.toString(),
                    l_objWhere2);

                // ※レコードが取得できなかった場合、nullを返却する。
                if (l_lisResultList2.isEmpty())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                // ３－３）　@強制決済理由情報インスタンスを生成する。
                WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                    new WEB3AdminForcedSettleReasonUnit();

                // ３－４）　@生成したインスタンスに以下のプロパティをセットする。
                // 強制決済理由：　@"保証金維持率割（オンライン開始前・重度）"
                l_forcedSettleReasonUnit.forcedSettleReason =
                    WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS;
                // 保証金維持率：　@３－１）にて取得したレコード.プリファ@レンスの値
                BranchPreferencesRow l_branchPreferenceRow =
                    (BranchPreferencesRow)l_lisResultList.get(0);
                l_forcedSettleReasonUnit.marginMaintenanceRate = l_branchPreferenceRow.getValue();
                // 追証経過日数上限：　@３－２）にて取得したレコード.プリファ@レンスの値
                BranchPreferencesRow l_branchPreferenceRow2 =
                    (BranchPreferencesRow)l_lisResultList2.get(0);
                l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit =
                    l_branchPreferenceRow2.getValue();

                log.exiting(STR_METHOD_NAME);
                return l_forcedSettleReasonUnit;
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //４）　@引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・法@定）"の場合
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL.equals(
            l_strForcedSettleReasonDiv))
        {
            //４－１）　@強制決済理由情報インスタンスを生成する。
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                new WEB3AdminForcedSettleReasonUnit();
            //４－２）　@生成したインスタンスに以下のプロパティをセットする。
            //　@　@強制決済理由：　@"保証金維持率割れ（オンライン開始前・法@定）"
            //　@　@保証金維持率：　@null
            //　@　@追証経過日数上限：　@null
            l_forcedSettleReasonUnit.forcedSettleReason =
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL;
            l_forcedSettleReasonUnit.marginMaintenanceRate = null;
            l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit = null;

            log.exiting(STR_METHOD_NAME);
            return l_forcedSettleReasonUnit;
        }
        //５）　@引数.強制決済理由区分＝"保証金維持率割れ（場間）"の場合
        else if (WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET.equals(
            l_strForcedSettleReasonDiv))
        {
            //　@５－１）　@強制決済理由情報インスタンスを生成する。
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                new WEB3AdminForcedSettleReasonUnit();
            //　@５－２）　@生成したインスタンスに以下のプロパティをセットする。
            //　@　@強制決済理由：　@"保証金維持率割（場間）"
            //　@　@保証金維持率：　@null
            //　@　@追証経過日数上限：　@null
            l_forcedSettleReasonUnit.forcedSettleReason =
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET;
            l_forcedSettleReasonUnit.marginMaintenanceRate = null;
            l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit = null;

            log.exiting(STR_METHOD_NAME);
            return l_forcedSettleReasonUnit;
        }

        // ６）　@引数.強制決済理由区分＝"手動強制決済"の場合
        else if (WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE.equals(l_strForcedSettleReasonDiv))
        {
            // ６－１）　@強制決済理由情報インスタンスを生成する。
            WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
                new WEB3AdminForcedSettleReasonUnit();

            // ６－２）　@生成したインスタンスに以下のプロパティをセットする。
            // 強制決済理由：　@"手動強制決済"
            l_forcedSettleReasonUnit.forcedSettleReason =
                WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE;
            // 保証金維持率：　@null
            l_forcedSettleReasonUnit.marginMaintenanceRate = null;
            // 追証経過日数上限：　@null
            l_forcedSettleReasonUnit.additionalElapsedDaysUpperLimit = null;

            log.exiting(STR_METHOD_NAME);
            return l_forcedSettleReasonUnit;
        }
        // ※引数.強制決済理由区分が全てに当てはまらない場合は、
        // 「パラメータ値不正」の例外をthrowする。
        else
        {
            log.debug("パラメータ値不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager" + STR_METHOD_NAME,
                "パラメータ値不正");
        }
    }

    /**
     * (get注文詳細)<BR>
     * 管理者・株式（PTS）注文詳細Unitにプロパティを設定する。<BR>
     * （（PTS）出来入力、（PTS）出来取消からコールされる）<BR>
     * <BR>
     * <BR>
     * １）　@株式データアダプタ.get商品区分()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@注文単位：　@引数.注文単位<BR>
     * <BR>
     * ２）　@株式データアダプタ.get取引区分()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@注文種別：　@引数.注文単位.注文種別<BR>
     * <BR>
     * ３）　@商品管理（株式）データマネージャ.get注文状態区分()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@注文単位：　@引数.注文単位<BR>
     * <BR>
     * ４）　@拡張株式注文マネージャ.get執行条件(SONAR)()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@執行条件：　@引数.注文単位.執行条件<BR>
     * <BR>
     * ５）　@株式データアダプタ.get約定状態区分()をコールする。<BR>
     * 　@　@[引数] <BR>
     * 　@　@注文単位：　@引数.注文単位<BR>
     * <BR>
     * ６）　@拡張金融オブジェクトマネージャ.get市場()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@arg0：　@引数.注文単位.市場ID<BR>
     * <BR>
     * ７）　@株式データアダプタ.get口座区分()をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@税区分：　@引数.注文単位.税区分 <BR>
     * <BR>
     * ８）　@管理者・株式（PTS）注文詳細Unitを生成し、<BR>
     * 　@　@　@プロパティをセットする。 <BR>
     * <BR>
     * 　@注文ID　@　@　@：　@引数.注文単位.注文ID<BR>
     * 　@部店コード　@：　@引数.注文単位.getBranch().getBranchCode()<BR>
     * 　@顧客コード　@：　@引数.注文単位.getMainAccount().getAccountCode()<BR>
     * 　@銘柄コード　@：　@株式銘柄Row.get銘柄コード()<BR>
     * 　@市場コード　@：　@get市場()の戻り値.get市場コード()<BR>
     * 　@口座区分　@　@：　@get口座区分()の戻り値<BR>
     * 　@商品区分　@　@：　@get商品区分()の戻り値<BR>
     * 　@取引区分　@　@：　@get取引区分()の戻り値<BR>
     * 　@弁済区分　@　@：　@引数.注文単位.弁済区分<BR>
     * 　@執行条件　@　@：　@get執行条件(SONAR)()の戻り値<BR>
     * 　@注文有効期限：　@引数.注文単位.初回注文の注文単位ID != nullの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数.注文単位.注文失効日付をセット。<BR>
     * 　@値段条件　@　@　@：　@引数.注文単位.値段条件<BR>
     * 　@発注条件区分：　@引数.注文単位.発注条件<BR>
     * 　@注文数量　@　@　@：　@引数.注文単位.注文数量<BR>
     * 　@注文単価区分：　@引数.注文単位.isMarketOrder()の戻り値がtrueの場合、"成行"をセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@falseの場合は"指値"をセット。 <BR>
     * 　@注文単価　@　@　@：　@注文単価区分が"指値"の場合、引数.注文単位.指値をセット。 <BR>
     * 　@約定数量　@　@　@：　@(*1)<BR>
     * 　@約定単価　@　@　@：　@(*1)<BR>
     * 　@注文状態区分：　@get注文状態区分()の戻り値<BR>
     * 　@約定状態区分：　@get約定状態区分()の戻り値<BR>
     * 　@訂正取消区分：　@引数.注文単位.注文訂正・取消区分<BR>
     * 　@注文時間　@　@　@：　@引数.注文単位.受注日時 <BR>
     * 　@発注日　@　@　@　@：　@引数.注文単位.発注日<BR>
     * 　@受渡日　@　@　@　@：　@引数.注文単位.受渡日<BR>
     * <BR>
     * (*1)<BR>
     * 　@約定がついている場合（引数.注文単位.isUnExecuted() == false）、以下値をセット。<BR>
     * 　@約定数量：　@引数.注文単位.約定数量<BR>
     * 　@約定単価：　@引数.注文単位.合計約定金額／引数.注文単位.約定数量（円未満は四捨五入）<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return WEB3AdminEquityPTSOrderDetailUnit
     * @@throws WEB3BaseException
     */
    public static WEB3AdminEquityPTSOrderDetailUnit getOrderUnitDetail(
        EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnitDetail(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@株式データアダプタ.get商品区分()をコールする。
        String l_strProductType = WEB3EquityDataAdapter.getProductType(l_orderUnit);

        //２）　@株式データアダプタ.get取引区分()をコールする。
        String l_strTradingType =
            WEB3EquityDataAdapter.getTradingType(l_orderUnit.getOrderType());
        //３）　@商品管理（株式）データマネージャ.get注文状態区分()をコールする。
        String l_strOrderState = getOrderState(l_orderUnit);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        //４）　@拡張株式注文マネージャ.get執行条件(SONAR)()をコールする。
        String l_strExecutionConditionTypeSonar =
            l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType());

        //５）　@株式データアダプタ.get約定状態区分()をコールする。
        String l_strExecType = WEB3EquityDataAdapter.getExecType(l_orderUnit);

        //６）　@拡張金融オブジェクトマネージャ.get市場()をコールする。
        //　@　@[引数]
        //　@　@arg0：　@引数.注文単位.市場ID
        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjectMgr.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //７）　@株式データアダプタ.get口座区分()をコールする。
        //　@　@[引数]
        //　@　@税区分：　@引数.注文単位.税区分
        String l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitRow.getTaxType());

        //８）　@管理者・株式（PTS）注文詳細Unitを生成し、
        //　@　@　@プロパティをセットする。
        WEB3AdminEquityPTSOrderDetailUnit l_orderDetailUnit = new WEB3AdminEquityPTSOrderDetailUnit();

        //　@注文ID　@　@　@：　@引数.注文単位.注文ID
        l_orderDetailUnit.orderId = String.valueOf(l_orderUnitRow.getOrderId());

        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //　@部店コード　@：　@引数.注文単位.getBranch().getBranchCode()
        l_orderDetailUnit.branchCode = l_branch.getBranchCode();

        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_orderUnit.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //　@顧客コード　@：　@引数.注文単位.getMainAccount().getAccountCode()
        l_orderDetailUnit.accountCode = l_mainAccount.getAccountCode();

        //　@銘柄コード　@：　@株式銘柄Row.get銘柄コード()
        EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_orderUnit.getProduct();
        l_orderDetailUnit.productCode = l_eqtypeProduct.getProductCode();

        //　@市場コード　@：　@get市場()の戻り値.get市場コード()
        l_orderDetailUnit.marketCode = l_market.getMarketCode();

        //　@口座区分　@　@：　@get口座区分()の戻り値
        l_orderDetailUnit.taxType = l_strTaxType;

        //　@商品区分　@　@：　@get商品区分()の戻り値
        l_orderDetailUnit.productDiv = l_strProductType;

        //　@取引区分　@　@：　@get取引区分()の戻り値
        l_orderDetailUnit.tradingType = l_strTradingType;

        //　@弁済区分　@　@：　@引数.注文単位.弁済区分
        l_orderDetailUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();

        //　@執行条件　@　@：　@get執行条件(SONAR)()の戻り値
        l_orderDetailUnit.execCondType = l_strExecutionConditionTypeSonar;

        //　@注文有効期限：　@引数.注文単位.初回注文の注文単位ID != nullの場合、引数.注文単位.注文失効日付をセット。
        if (!l_orderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_orderDetailUnit.expirationDate = l_orderUnitRow.getExpirationDate();
        }

        //　@値段条件　@　@　@：　@引数.注文単位.値段条件
        l_orderDetailUnit.priceCondType = l_orderUnitRow.getPriceConditionType();

        //　@発注条件区分：　@引数.注文単位.発注条件
        l_orderDetailUnit.orderCondType = l_orderUnitRow.getOrderConditionType();

        //　@注文数量　@　@　@：　@引数.注文単位.注文数量
        l_orderDetailUnit.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());

        //　@注文単価区分：　@引数.注文単位.isMarketOrder()の戻り値がtrueの場合、"成行"をセット。
        //　@　@　@　@　@　@　@　@　@　@falseの場合は"指値"をセット。
        if (l_orderUnit.isMarketOrder())
        {
            l_orderDetailUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            l_orderDetailUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

            //　@注文単価　@　@　@：　@注文単価区分が"指値"の場合、引数.注文単位.指値をセット。
            l_orderDetailUnit.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }

        //約定がついている場合（引数.注文単位.isUnExecuted() == false）、以下値をセット。
        if (!l_orderUnit.isUnexecuted())
        {
            //　@約定数量：　@引数.注文単位.約定数量
            l_orderDetailUnit.execQuantity =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());

            //合計約定金額
            BigDecimal l_bdExecutedAmount = new BigDecimal("" + l_orderUnit.getExecutedAmount());
            //約定数量
            BigDecimal l_bdExecutedQuantity = new BigDecimal("" + l_orderUnit.getExecutedQuantity());
            //約定単価：　@引数.注文単位.合計約定金額／引数.注文単位.約定数量（円未満は四捨五入）
            BigDecimal l_dbExecPrice =
                l_bdExecutedAmount.divide(l_bdExecutedQuantity, 0, BigDecimal.ROUND_HALF_UP);

            l_orderDetailUnit.execPrice =
                WEB3StringTypeUtility.formatNumber(l_dbExecPrice.doubleValue());
        }

        //　@注文状態区分：　@get注文状態区分()の戻り値
        l_orderDetailUnit.orderState = l_strOrderState;

        //　@約定状態区分：　@get約定状態区分()の戻り値
        l_orderDetailUnit.execType = l_strExecType;

        //　@訂正取消区分：　@引数.注文単位.注文訂正・取消区分
        l_orderDetailUnit.changeCancelDiv = l_orderUnitRow.getModifyCancelType();

        //　@注文時間　@　@　@：　@引数.注文単位.受注日時
        l_orderDetailUnit.orderDate = l_orderUnitRow.getReceivedDateTime();

        //　@発注日　@　@　@　@：　@引数.注文単位.発注日
        l_orderDetailUnit.orderBizDate =
            WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //　@受渡日　@　@　@　@：　@引数.注文単位.受渡日
        l_orderDetailUnit.deliveryDate = l_orderUnitRow.getDeliveryDate();

        log.exiting(STR_METHOD_NAME);
        return l_orderDetailUnit;
    }

    /**
     * (get約定履歴)<BR>
     * 指定された注文単位に紐づく約定の履歴を<BR>
     * 株式出来通知キューテーブルより取得する。<BR>
     * （（PTS）出来入力、（PTS）出来取消からコールされる）<BR>
     * <BR>
     * <BR>
     * １）　@株式出来通知キューテーブルから一覧を取得する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@株式出来通知キューテーブル.識別コード＝引数.注文単位.識別コード<BR>
     * <BR>
     * 　@　@[ソート順]<BR>
     * 　@　@株式出来通知キューテーブル.作成日付の昇順<BR>
     * <BR>
     * <BR>
     * 　@　@※条件に一致するデータが取得できない場合、nullを返却する。<BR>
     * <BR>
     * <BR>
     * ２）　@株式出来通知キューから取得した一覧の要素数分、以下の処理を繰り返す。<BR>
     * <BR>
     * 　@２－１）　@管理者・株式（PTS）約定履歴インスタンスを生成する。<BR>
     * 　@２－２）　@管理者・株式（PTS）約定履歴のプロパティにセットする。<BR>
     * <BR>
     * 　@　@・取消可能フラグ：　@false <BR>
     * 　@　@・約定日時：　@株式出来通知キュー.約定日時<BR>
     * 　@　@・約定株数：　@株式出来通知キュー.約定株数<BR>
     * 　@　@・約定単価：　@株式出来通知キュー.約定単価<BR>
     * 　@　@・約定・約定取消区分：　@株式出来通知キュー.出来通知区分<BR>
     * 　@　@・更新者コード：　@株式出来通知キュー.更新者コード<BR>
     * 　@　@・処理区分：　@株式出来通知キュー.処理区分<BR>
     * <BR>
     * <BR>
     * ３）　@管理者・株式（PTS）約定履歴オブジェクトの配列を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return WEB3AdminEquityPTSExecHistory[]
     * @@throws WEB3BaseException
     */
    public static WEB3AdminEquityPTSExecHistory[] getExecHistory(
        EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderUnit(String, Branch)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //株式出来通知キューテーブルから一覧を取得する。
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" order_request_number = ? ");

        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        Object[] l_objWheres = {l_orderUnitRow.getOrderRequestNumber()};

        List l_lisRecords = new ArrayList();
        String l_strSortCondition = "created_timestamp asc";
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisRecords = l_processor.doFindAllQuery(
                HostEquityOrderExecNotifyRow.TYPE,
                l_sbWhere.toString(),
                l_strSortCondition,
                null,
                l_objWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //※条件に一致するデータが取得できない場合、nullを返却する。
        if (l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3AdminEquityPTSExecHistory[] l_equityPTSExecHistorys =
            new WEB3AdminEquityPTSExecHistory[l_lisRecords.size()];

        //株式出来通知キューから取得した一覧の要素数分、以下の処理を繰り返す。
        for (int i = 0; i < l_lisRecords.size(); i++)
        {
            HostEquityOrderExecNotifyRow l_execNotifyRow =
                (HostEquityOrderExecNotifyRow)l_lisRecords.get(i);

            //管理者・株式（PTS）約定履歴インスタンスを生成する。
            WEB3AdminEquityPTSExecHistory l_equityPTSExecHistory =
                new WEB3AdminEquityPTSExecHistory();

            //管理者・株式（PTS）約定履歴のプロパティにセットする。
            //・取消可能フラグ：　@false
            l_equityPTSExecHistory.cancelFlag = false;

            //・約定日時：　@株式出来通知キュー.約定日時
            l_equityPTSExecHistory.executionTimeStamp = l_execNotifyRow.getExecTimestamp();

            //・約定株数：　@株式出来通知キュー.約定株数
            l_equityPTSExecHistory.execQuantity =
                WEB3StringTypeUtility.formatNumber(l_execNotifyRow.getExecQuantity());

            //・約定単価：　@株式出来通知キュー.約定単価
            l_equityPTSExecHistory.execPrice =
                WEB3StringTypeUtility.formatNumber(l_execNotifyRow.getExecPrice());

            //・約定・約定取消区分：　@株式出来通知キュー.出来通知区分
            l_equityPTSExecHistory.inputExecCancelExecDiv = l_execNotifyRow.getDealedType();

            //・更新者コード：　@株式出来通知キュー.更新者コード
            l_equityPTSExecHistory.updaterCode = l_execNotifyRow.getLastUpdater();

            //・処理区分：　@株式出来通知キュー.処理区分
            l_equityPTSExecHistory.inputExecCancelExecProcDiv = l_execNotifyRow.getStatus();

            l_equityPTSExecHistorys[i] = l_equityPTSExecHistory;
        }

        //管理者・株式（PTS）約定履歴オブジェクトの配列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_equityPTSExecHistorys;
    }

    /**
     * (validate出来入力出来取消可能時間帯)<BR>
     * （PTS）出来入力、（PTS）出来取消の処理が可能な時間帯かチェックする<BR>
     * <BR>
     * １）PTS市場が市場開局時間帯<BR>
     * 　@　@（PTS取引時間管理.is市場開局時間帯()==true）の場合、<BR>
     * 　@　@　@処理可能(正常終了)とする。<BR>
     * <BR>
     * <BR>
     * ２）PTS市場が閉局（PTS取引時間管理.is市場開局時間帯()==false）の場合<BR>
     * <BR>
     * ２－１）(PTS)出来終了テーブルを検索する。<BR>
     * <BR>
     * 　@　@　@［検索条件］<BR>
     * 　@　@　@証券会社コード＝取引カレンダコンテキスト(*1)の同名プロパティ<BR>
     * 　@　@　@市場コード＝ 取引カレンダコンテキスト(*1)の同名プロパティ<BR>
     * <BR>
     * <BR>
     * ２－２）出来終了レコードが存在しない　@かつ　@<BR>
     * 　@　@　@　@現在日付　@>　@業務日付の場合、<BR>
     * 　@　@　@　@処理可能(正常終了)とする。<BR>
     * <BR>
     * ２－３）　@２－２）の条件に該当しない場合、<BR>
     * 　@　@　@　@「処理不可の時間帯」の例外をスローする。<BR>
     * 　@　@　@　@class:WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:BUSINESS_ERROR_03015<BR>
     * <BR>
     * <BR>
     *  (*1)取引カレンダコンテキストの取得<BR>
     * ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。<BR>
     * 設定キー： PTS取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@throws WEB3BaseException
     */
    public static void validateInputCancelExecEnableTimeZone() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputCancelExecEnableTimeZone()";
        log.entering(STR_METHOD_NAME);

        //PTS市場が閉局（PTS取引時間管理.is市場開局時間帯()==false）の場合
        if (!WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone())
        {
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            //証券会社コード＝取引カレンダコンテキスト(*1)の同名プロパティ
            String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
            //市場コード＝ 取引カレンダコンテキスト(*1)の同名プロパティ
            String l_strMarketCode = l_clendarContext.getMarketCode();

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and market_code = ? ");

            Object[] l_objWheres = {l_strInstitutionCode, l_strMarketCode};

            List l_lisPtsOrderexecutionEnds = new ArrayList();
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_lisPtsOrderexecutionEnds = l_processor.doFindAllQuery(
                    PtsOrderexecutionEndRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWheres);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //「出来終了レコードが存在しない　@かつ現在日付　@>　@業務日付」の条件に該当しない場合、
            // 「処理不可の時間帯」の例外をスローする。
            Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            if (!(l_lisPtsOrderexecutionEnds.isEmpty()
                && WEB3DateUtility.compareToDay(l_tsSystemTime, l_datBizDate) > 0))
            {
                log.debug("処理不可の時間帯。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03015,
                    "WEB3AdminPMEquityDataManager." + STR_METHOD_NAME,
                    "処理不可の時間帯。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get注文状態区分)<BR>
     * 引数.注文単位より、注文状態区分を返却する。<BR>
     * <BR>
     * １）引数.注文単位の保持するデータにより分岐し、<BR>
     * 　@対応する注文状態区分を返却する。<BR>
     * <BR>
     * 　@１－１）手動失効の判定<BR>
     * 　@　@　@注文単位.注文有効状態 == CLOSED（クローズ） かつ<BR>
     * 　@　@　@注文単位.失効区分 == INVALIDATED_BY_MKT（マーケット拒否）かつ<BR>
     * 　@　@　@注文単位.注文エラー理由コード ==<BR>
     * 　@　@　@　@("W001:株式管理者手動失効済")の場合、<BR>
     * 　@　@　@　@"手動失効"を返却する。<BR>
     * <BR>
     * 　@１－２）上記以外の場合、<BR>
     * 　@　@　@株式データアダプタ.get注文状態区分(引数.注文単位)メソッドを<BR>
     * 　@　@　@コールし、戻り値を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@throws WEB3BaseException
     * @@return String
     */
    public static String getOrderState(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderState(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        //注文単位.注文有効状態 == CLOSED（クローズ） かつ
        //注文単位.失効区分 == INVALIDATED_BY_MKT（マーケット拒否）かつ
        //注文単位.注文エラー理由コード ==
        //("W001:株式管理者手動失効済")の場合、
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus())
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnitRow.getExpirationStatus())
            && WEB3ErrorReasonCodeDef.EQUITY_ADMIN_MANUAL_EXPIRED.equals(l_orderUnitRow.getErrorReasonCode()))
        {
            //"手動失効"を返却する。
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderStatusDef.MANUAL_EXPIRED;
        }

        //１－２）上記以外の場合、
        //株式データアダプタ.get注文状態区分(引数.注文単位)メソッドをコールし、戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3EquityDataAdapter.getOrderState(l_orderUnit);
    }
}
@
