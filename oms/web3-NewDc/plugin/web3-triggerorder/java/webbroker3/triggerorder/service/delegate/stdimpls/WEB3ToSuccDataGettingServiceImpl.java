head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccDataGettingServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文データ取得サービスImpl(WEB3ToSuccDataGettingServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 孟東(中訊) 新規作成
Revesion History : 2006/08/30 柴雙紅(中訊) 仕様変更モデル165
Revesion History : 2006/11/24 徐宏偉(中訊) 仕様変更モデル185
Revesion History : 2006/11/30 徐宏偉(中訊) 仕様変更モデル198 201
Revesion History : 2007/06/05 柴双紅(中訊) 仕様変更モデル236
Revesion History : 2008/03/18 趙林鵬(中訊) 仕様変更モデル258, 288
Revesion History : 2008/04/09 金傑(中訊) 仕様変更モデル315
Revesion History : 2008/04/17 趙林鵬(中訊) 仕様変更モデル327
Revesion History : 2008/05/08 趙林鵬(中訊) 仕様変更モデル334
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccOpProductTypeDef;
import webbroker3.triggerorder.define.WEB3ToSuccTransactionStateDef;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.triggerorder.message.WEB3SuccReservationOrderUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.triggerorder.util.WEB3TriggerOrderUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (連続注文データ取得サービスImpl)<BR>
 * 連続注文データ取得サービス実装クラス<BR>
 * <BR>
 * 連続注文機@能の共通処理を実装する。<BR>
 * <BR>
 * @@author 孟東 <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccDataGettingServiceImpl 
    implements WEB3ToSuccDataGettingService 
{
    /**
     * <p>（ログ出力ユーティリティ)。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToSuccDataGettingServiceImpl.class);

    /**
     * @@roseuid 4348DB900167
     */
    public WEB3ToSuccDataGettingServiceImpl() 
    {

    }

    /**
     * (create連続注文明細)<BR>
     * 引数の連続注文明細にプロパティをセットする。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストから以下の値を<BR>
     * 　@取得し、退避させる。<BR>
     * 　@　@○市場コード<BR>
     * 　@　@○銘柄コード<BR>
     * 　@　@○受付時間区分<BR>
     * <BR>
     * ２）　@型の判別<BR>
     * 　@パラメータ.注文単位の型をinstanceofにて判別し、<BR>
     * 　@以下のいずれかにキャストする。<BR>
     * 　@・株式注文単位<BR>
     * 　@・先物OP注文単位<BR>
     * 　@・株式予約注文単位Impl<BR>
     * 　@・先物OP予約注文単位Impl<BR>
     * <BR>
     * ３）パラメータ.連続注文明細に以下のプロパティを<BR>
     * 　@セットする。<BR>
     * 　@※注文単位として、キャストしたものを使用する。<BR>
     * 　@　@プロパティセットに必要な注文単位の各値は、<BR>
     * 　@　@キャストした時点で別変数として取得し、<BR>
     * 　@　@プロパティセットのロジックは共通化するように実装すること。<BR>
     * <BR>
     * 　@注文ID = 注文単位.注文ID<BR>
     * 　@商品区分 = this.get商品区分(注文単位.注文種別)<BR>
     * 　@銘柄コード = 銘柄(*1).銘柄コード<BR>
     * 　@銘柄名 = 銘柄(*1).銘柄名<BR>
     * 　@指数種別(*10) = 銘柄.原資産銘柄コード<BR>
     * 　@限月(*10) = 銘柄.限月<BR>
     * 　@オプション商品区分(*3) =<BR>
     * 　@　@[銘柄.先物オプション商品区分 == "プットオプション"の場合]<BR>
     * 　@　@　@"プットオプション"をセット。<BR>
     * 　@　@[銘柄.先物オプション商品区分 == "コールオプション"の場合]<BR>
     * 　@　@　@"コールオプション"をセット。<BR>
     * 　@行使価格(*3) = 銘柄.行使価格<BR>
     * 　@市場コード = 注文単位.市場IDに該当する市場.市場コード<BR>
     * 　@口座区分(*7) = 株式データアダプタ.get口座区分(引数 注文単位.getTaxType)<BR>
     * 　@取引区分(*8) = 注文単位.注文種別、取引コード（SONAR）よりセット<BR>
     * 　@弁済区分(*5) = 注文単位.弁済区分<BR>
     * 　@弁済期限値(*5) = 注文単位.弁済期限値<BR>
     * 　@値段条件(*4) = 注文単位.値段条件<BR>
     * 　@執行条件(*6)=this.get執行条件（PR層）(注文単位)<BR>
     * 　@発注条件区分(*6)=注文単位.発注条件<BR>
     * <BR>
     * 　@[注文単位.発注条件=="逆指値"の場合]<BR>
     * 　@　@逆指値用プレミアム／原資産価格(*2)=注文単位.逆指値基準値タイプ<BR>
     * 　@　@逆指値用発注条件単価(*6)=注文単位.逆指値基準値<BR>
     * 　@　@逆指値用発注条件演算子(*6)=注文単位.発注条件演算子<BR>
     * 　@[注文単位.発注条件=="W指値"の場合]<BR>
     * 　@　@Ｗ指値用プレミアム／原資産価格(*2)=注文単位.逆指値基準値タイプ<BR>
     * 　@　@Ｗ指値用発注条件単価(*6)=注文単位.逆指値基準値<BR>
     * 　@　@Ｗ指値用発注条件演算子(*6)=注文単位.発注条件演算子<BR>
     * 　@　@Ｗ指値用注文単価区分(*6)=注文単位.（W指値）訂正指値==0の場合、"成行"。<BR>
     * 　@　@　@以外、"指値"をセット。<BR>
     * 　@　@Ｗ指値用注文単価(*6)=注文単位.（W指値）訂正指値!=0の場合、その値をセット。<BR>
     * <BR>
     * 　@※以下、「データアダプタ」は注文単位の型により次のように読み替える事。<BR>
     * 　@　@株式注文単位の場合、「株式データアダプタ」<BR>
     * 　@　@先物OP注文単位の場合、「先物OPデータアダプタ」<BR>
     * <BR>
     * 　@Ｗ指値用執行条件(*6) = データアダプタ.getＷ指値用執行条件(注文単位)の戻り値<BR>
     * 　@Ｗ指値用有効状態区分(*6)=データアダプタ.getＷ指値用有効状態区分(注文単位)<BR>
     * 　@　@の戻り値<BR>
     * 　@Ｗ指値用切替前注文単価(*6)=データアダプタ.getＷ指値用切替前注文単価(注文単位)<BR>
     * 　@　@の戻り値<BR>
     * 　@Ｗ指値用切替前執行条件(*6)=データアダプタ.getＷ指値用切替前執行条件(注文単位)<BR>
     * 　@　@の戻り値<BR>
     * 　@元発注条件区分(*6)=注文単位.元発注条件<BR>
     * 　@元発注条件単価(*6)=注文単位.元逆指値基準値<BR>
     * 　@元発注条件演算子(*6)=注文単位.元発注条件演算子<BR>
     * 　@元Ｗ指値用注文単価区分(*6)=データアダプタ.get元Ｗ指値用注文単価区分(注文単位)<BR>
     * 　@　@の戻り値<BR>
     * 　@元Ｗ指値用注文単価(*6)=元W指値用注文単価区分=="指値"の場合のみ、<BR>
     * 　@　@データアダプタ.get元Ｗ指値用注文単価(注文単位)の戻り値<BR>
     * 　@元Ｗ指値用執行条件(*6)=データアダプタ.get元Ｗ指値用執行条件(注文単位)<BR>
     * 　@　@の戻り値<BR>
     * 　@元プレミアム／原資産価格(*2) = 注文単位.元逆指値基準値タイプ<BR>
     * 　@注文数量=注文単位.注文数量<BR>
     * <BR>
     * 　@注文単価区分 =<BR> 
     * 　@　@[株式予約注文単位Implの場合]<BR>
     * 　@　@　@株式予約注文単位Impl.getメッセージ用注文単価区分()の戻り値<BR>
     * 　@　@[先物OP予約注文単位Implの場合]<BR>
     * 　@　@　@先物OP予約注文単位Impl.getメッセージ用注文単価区分()の戻り値<BR>
     * 　@　@[以外の場合]<BR>
     * 　@　@　@注文単位.isMarketOrder == trueの場合、"成行"。<BR>
     * 　@　@　@以外、"指値"をセット。<BR>
     * 　@注文単価 = <BR>
     * 　@　@[株式予約注文単位Implの場合]<BR>
     * 　@　@　@株式予約注文単位Impl.getメッセージ用注文単価()の戻り値<BR>
     * 　@　@[先物OP予約注文単位Impl]<BR>
     * 　@　@　@先物OP予約注文単位Impl.getメッセージ用注文単価()の戻り値<BR>
     * 　@　@[以外の場合]<BR>
     * 　@　@　@注文単位.isMarketOrder == falseの場合、その値をセット。<BR>
     * <BR>
     * 　@注文時間 = 注文単位.作成日付<BR>
     * 　@発注日 = 注文単位.発注日<BR>
     * 　@　@注文有効期限 =<BR>
     * 　@　@[株式注文単位 or 株式予約注文単位Implの場合]<BR>
     * 　@　@　@注文単位.初回注文の注文単位ID != nullの場合のみ、注文単位.注文失効日付。<BR>
     * 　@　@　@以外、nullをセット。<BR>
     * 　@　@[先物OP注文単位の場合]<BR>
     * 　@　@　@先物OPデータアダプタ.get注文期限区分（注文単位）<BR>
     * 　@　@　@が"出来るまで注文"の場合のみ、注文単位.注文失効日付。<BR>
     * 　@　@　@以外、nullをセット。<BR>
     * 　@　@[先物OP予約注文単位Implの場合]<BR>
     * 　@　@　@先物OP予約注文単位Impl.getメッセージ用注文有効期限 ()の戻り値をセット。<BR>
     * <BR>
     * 　@受渡代金 = 注文単位.概算受渡代金<BR>
     * 　@強制決済理由(*4) = 注文単位.強制決済理由<BR>
     * 　@強制失効区分(*7) = 注文単位.強制失効区分<BR>
     * <BR>
     * 　@夕場前繰越対象フラグ(*9) =<BR>
     * 　@データアダプタ.get夕場前繰越対象フラグ(PR層)(注文単位(**1))の戻り値をセット。<BR>
     * 　@立会区分(*10) = 注文単位.立会区分<BR>
     * <BR>
     * ４）　@取引カレンダコンテキストに以下の値をセットする。<BR>
     * 　@○市場コード = 原資産銘柄コードを取得している場合、"0:DEFAULT"をセット。<BR>
     * 　@　@上記以外は、取得した市場コードをセット。<BR>
     * 　@○銘柄コード = 原資産銘柄コードを取得している場合、<BR>
     * 　@　@取得した原資産銘柄コード<BR>
     * 　@○受付時間区分 =<BR>
     * 　@　@this.get受付時間区分(注文単位.注文カテゴリ, 注文単位.取引コード（SONAR）)の戻り値をセット。<BR>
     * <BR>
     * ５）　@処理状況区分をセットする。<BR>
     * 　@処理状況(*6) = this.get処理状況(注文単位)<BR>
     * <BR>
     * ６）　@パラメータ.is可能フラグ設定 == trueの場合、<BR>
     * 　@　@　@this.set可能フラグ()をコールする。<BR>
     * <BR>
     * 　@　@　@[set可能フラグ()に指定する引数]<BR>
     * 　@　@　@　@連続注文明細：　@パラメータ.連続注文明細<BR>
     * 　@　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ７）１）にて退避した値を取引カレンダコンテキストに<BR>
     * 　@再セットする。<BR>
     * <BR>
     * (*1)this.get銘柄(注文単位.銘柄ID, 注文単位.銘柄タイプ)<BR>
     * 　@により取得。<BR>
     * (*2)先物OP注文単位の場合セット。以外、null。<BR>
     * (*3)（先物OP注文単位 or 先物OP予約注文単位Impl）の場合 かつ <BR>
     * 　@先物OP注文単位.先物／オプション区分 = "オプション"の場合セット。以外、null。<BR>
     * (*4)株式注文単位の場合セット。以外、null。<BR>
     * (*5)（株式注文単位 or 株式予約注文単位Impl）の場合 かつ<BR>
     * 　@注文カテゴリ != "現物注文"の場合セット。以外、null<BR>
     * (*6)株式注文単位 or 先物OP注文単位の場合セット。以外、null<BR>
     * (*7)株式注文単位 or 株式予約注文単位Implの場合セット。以外、null<BR>
     * (*8)注文単位.注文種別≠"現物買注文"の場合は、注文単位.注文種別をそのままセット。<BR>
     * 　@　@注文単位.注文種別=="現物買注文"の場合は、取引コード（SONAR）の値により分岐。<BR>
     * 　@　@－取引コード（SONAR）=="立会外分売"の場合：　@"立会外分売"をセット。<BR>
     * 　@　@－取引コード（SONAR）≠"立会外分売"の場合：　@"現物買注文"をセット。<BR>
     * (*9)先物OP注文単位 or 先物OP予約注文単位Implの場合セット。以外、false。<BR>
     * (*10)先物OP注文単位 or 先物OP予約注文単位Implの場合セット。以外、null。<BR>
     * <BR>
     * (**1)先物OP予約注文単位Implの場合、<BR>
     * 連続注文マネージャImpl.create先物OP注文単位(注文単位)の戻り値をセット。<BR>
     *
     * @@param l_succOrderUnit - (連続注文明細)<BR>
     * 連続注文明細オブジェクト
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@param l_blnIsPossibleFlagSet - (is可能フラグ設定)<BR>
     * 訂正・取消・連続注文可能フラグを<BR>
     * 設定するかどうかのフラグ<BR>
     * <BR>
     * false：　@設定しない<BR>
     * true：　@設定する<BR>
     * @@exception WEB3BaseException
     * @@roseuid 431E4E3A0093
     */
    public void createSuccOrderUnit(
        WEB3SuccOrderUnit l_succOrderUnit, 
        OrderUnit l_orderUnit, 
        boolean l_blnIsPossibleFlagSet)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "createSuccOrderUnit(WEB3SuccOrderUnit, OrderUnit, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_succOrderUnit == null || l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //　@取引カレンダコンテキストから以下の値を 
        // 取得し、退避させる。 
        // ○市場コード 
        // ○銘柄コード 
        // ○受付時間区分 
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //市場コード
        String l_strOldMarketCode = l_clendarContext.getMarketCode();
        //銘柄コード 
        String l_strOldProductCode = l_clendarContext.getProductCode();
        //受付時間区分 
        String l_strOldTradingTimeType = l_clendarContext.getTradingTimeType();
        
        if (!(l_orderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl) &&
            !(l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl) &&
            !(l_orderUnit instanceof EqTypeOrderUnit) &&
            !(l_orderUnit instanceof IfoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //注文ID = 注文単位.注文ID
        String l_strOrderId = new Long(l_orderUnit.getOrderId()).toString(); 

        //商品区分 = this.get商品区分(注文単位.注文種別) 
        String l_strCommodityType = 
            this.getCommodityDiv(l_orderUnit.getOrderType());

        //(*1)this.get銘柄(注文単位.銘柄ID, 注文単位.銘柄タイプ)により取得。
        ProductTypeEnum l_productTypeEnum = 
            l_orderUnit.getProduct().getProductType();

        //銘柄コード = 銘柄(*1).銘柄コード
        String l_strProductCode = null;

        //銘柄名 = 銘柄(*1).銘柄名
        String l_strProductName = null;

        //指数種別(*2) = 銘柄.原資産銘柄コード
        String l_strTargetProductCode = null;
        //限月(*2) = 銘柄.限月 
        String l_strDelivaryMonth = null;
        //オプション商品区分
        String l_strOpProductType = null;
        //行使価格
        String l_strStrikePrice = null;
        //市場コード = 注文単位.市場IDに該当する市場.市場コード 
        String l_strMarketCode = null;
        //口座区分
        String l_strTaxType = null;
        //取引区分 = 注文単位.注文種別
        OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
        //弁済区分
        String l_strRepaymentDiv = null;
        //弁済期限値
        String l_strRepaymentTimeLimit = null;
        //値段条件
        String l_strPriceCondType = null;
        //執行条件
        String l_strExecCondType = null;
        //発注条件区分
        String l_strOrderConditionType = null;
        //注文数量 = 注文単位.注文数量
        double l_dblOrderQuantity = l_orderUnit.getQuantity();
        //注文時間
        Date l_datOrderDate = null;
        //発注日
        String l_strOrderBizDate = null;
        //注文有効期限
        Date l_datExpirationDate = null;
        //受渡代金
        double l_dblDeliveryPrice = 0;
        //強制決済理由
        String l_strForcedSettleReason = null;
        //強制失効区分
        String l_strForcedExpireType = null;
        //処理状況
        String l_strTransactionStateType = null;
        //取引コード（SONAR）
        String l_strSonarTradedCode = null;
        //逆指値用プレミアム/原資産価格(*2)
        String l_stopPremium_underlyingAssets = null;
        //逆指値用発注条件単価(*6)
        String l_stopOrderCondPrice = null;
        //逆指値用発注条件演算子(*6)
        String l_stopOrderCondOperator = null;
        //Ｗ指値用プレミアム/原資産価格(*2)
        String l_wlimitPremium_underlyingAssets = null;
        //Ｗ指値用発注条件単価(*6)
        String l_wlimitOrderCondPrice = null;
        //Ｗ指値用発注条件演算子(*6)
        String l_wlimitOrderCondOperator = null;
        //Ｗ指値用注文単価区分(*6)
        String l_wLimitOrderPriceDiv = null;
        //Ｗ指値用注文単価(*6)
        String l_wLimitPrice = null;
        //Ｗ指値用執行条件
        String l_strWLimitExecCondType = null;
        //Ｗ指値用有効状態区分(*6)
        String l_strWLimitEnableStatusDiv = null;
        //  　@Ｗ指値用切替前注文単価(*6) 
        String l_strWLimitBefSwitchPrice = null;
        //  　@Ｗ指値用切替前執行条件(*6)
        String l_strWLimitBefSwitchExecCondType = null;
        //  　@元発注条件区分(*6)
        String l_strOrgOrderConditionType = null;
        //  　@元発注条件単価(*6)
        String l_strOrgOrderPrice = null;
        //  　@元発注条件演算子(*6)
        String l_strOrgOrderCondOperator = null;
        //  　@元Ｗ指値用注文単価区分(*6)
        String l_strOrgWLimitOrderPriceDiv = null;
        //  　@元Ｗ指値用注文単価(*6)
        String l_strOrgWLimitOrderPrice = null;
        //  　@元Ｗ指値用執行条件(*6)
        String l_strOrgWLimitExecCondType = null;
        //  　@注文単価区分
        String l_strOrderPriceDiv = null;
        //  　@注文単価
        String l_strPrice = null;
        //元プレミアム／原資産価格
        String l_strOrgStopPriceType = null;
        //夕場前繰越対象フラグ
        boolean l_blnIsEveningSessionCarryOverFlag = false;
        //立会区分
        String l_strSessionType = null;

        try
        {
            //株式予約注文単位Implの場合
            if (l_orderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl)
            {
                RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
                    (RsvEqOrderUnitRow)((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit).getDataSourceObject();

                //(*1)this.get銘柄(注文単位.銘柄ID, 注文単位.銘柄タイプ)により取得。
                EqtypeProductRow l_eqtypeProductRow = 
                    (EqtypeProductRow)this.getProduct(
                        l_orderUnit.getProduct().getProductId(), 
                        l_productTypeEnum).getDataSourceObject();

                //銘柄コード
                l_strProductCode = l_eqtypeProductRow.getProductCode();

                //銘柄名
                l_strProductName = l_eqtypeProductRow.getStandardName();

                //市場コード = 注文単位.市場IDに該当する市場.市場コード
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_rsvEqOrderUnitRow.getMarketId()).getMarketCode();

                //口座区分(*7) = 株式データアダプタ.get口座区分(引数 注文単位.getTaxType) 
                l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());

                //株式注文単位.注文カテゴリ != "現物注文"の場合
                if (!OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
                {
                    //弁済区分(*5) = 注文単位.弁済区分
                    l_strRepaymentDiv = l_rsvEqOrderUnitRow.getRepaymentType();
                    //弁済期限値(*5) = 注文単位.弁済期限値
                    l_strRepaymentTimeLimit = 
                        new Long(l_rsvEqOrderUnitRow.getRepaymentNum()).toString();
                }

                WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnitImpl = 
                    (WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit;
                //株式予約注文単位Impl.getメッセージ用注文単価区分()の戻り値 
                l_strOrderPriceDiv = l_rsvEqOrderUnitImpl.getMsgOrderPriceDiv();

                //株式予約注文単位Impl.getメッセージ用注文単価()の戻り値
                l_strPrice = l_rsvEqOrderUnitImpl.getMsgLimitPrice();

                //注文時間 = 注文単位.作成日付
                l_datOrderDate = l_rsvEqOrderUnitRow.getCreatedTimestamp();

                //発注日 = 注文単位.発注日
                l_strOrderBizDate = l_rsvEqOrderUnitRow.getBizDate();

                //注文有効期限 = 注文単位.初回注文の注文単位ID != nullの場合のみ、 
                //注文単位.注文失効日付。以外、nullをセット。 
                if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
                {
                    l_datExpirationDate = l_rsvEqOrderUnitRow.getExpirationDate();
                }

                //受渡代金 = 注文単位.概算受渡代金
                l_dblDeliveryPrice = l_rsvEqOrderUnitRow.getEstimatedPrice();

                //強制失効区分(*7) = 注文単位.強制失効区分
                l_strForcedExpireType = l_rsvEqOrderUnitRow.getForcedExpireType();
            }
            //先物OP予約注文単位Implの場合
            else if (l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl)
            {
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = 
                    (RsvIfoOrderUnitRow)((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit).getDataSourceObject();

                //this.get銘柄(注文単位.銘柄ID, 注文単位.銘柄タイプ)により取得。
                IfoProductRow l_ifoProductRow = 
                    (IfoProductRow)this.getProduct(
                        l_orderUnit.getProduct().getProductId(), 
                        l_productTypeEnum).getDataSourceObject();

                //銘柄コード
                l_strProductCode = l_ifoProductRow.getProductCode();

                //銘柄名
                l_strProductName = l_ifoProductRow.getStandardName();
                
                //先物OP注文単位.先物／オプション区分 = "オプション"の場合
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_rsvIfoOrderUnitRow.getFutureOptionDiv()))
                {
                    if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
                    {
                        l_strOpProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
                    }
                    else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
                    {
                        l_strOpProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
                    }

                    //行使価格(*3) = 銘柄.行使価格
                    l_strStrikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());
                }

                //市場コード = 注文単位.市場IDに該当する市場.市場コード
                l_strMarketCode = new WEB3GentradeMarket(
                    l_rsvIfoOrderUnitRow.getMarketId()).getMarketCode();

                WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnitImpl = 
                    (WEB3ToSuccIfoOrderUnitImpl)l_orderUnit;
                
                //指数種別(*10) = 銘柄.原資産銘柄コード
                l_strTargetProductCode = l_ifoProductRow.getUnderlyingProductCode();

                //限月(*10) = 銘柄.限月
                l_strDelivaryMonth = l_ifoProductRow.getMonthOfDelivery();
                
                //注文単価区分
                //先物OP予約注文単位Impl.getメッセージ用注文単価区分()の戻り値 
                l_strOrderPriceDiv = l_rsvIfoOrderUnitImpl.getMsgOrderPriceDiv();

                //注文単価
                //先物OP予約注文単位Impl.getメッセージ用注文単価()の戻り値
                l_strPrice = l_rsvIfoOrderUnitImpl.getMsgLimitPrice();

                //注文時間 = 注文単位.作成日付
                l_datOrderDate = l_rsvIfoOrderUnitRow.getCreatedTimestamp();

                //発注日 = 注文単位.発注日
                l_strOrderBizDate = l_rsvIfoOrderUnitRow.getBizDate();

                //注文有効期限 = 先物OP予約注文単位Impl.getメッセージ用注文有効期限 ()の戻り値をセット。
                l_datExpirationDate = l_rsvIfoOrderUnitImpl.getMsgExpirationDate();

                //受渡代金 = 注文単位.概算受渡代金
                l_dblDeliveryPrice = l_rsvIfoOrderUnitRow.getEstimatedPrice();

                WEB3ToSuccOrderManagerImpl l_toSuceeOrderManager =
                    (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
                
                //夕場前繰越対象フラグ(*9) = データアダプタ.get夕場前繰越対象フラグ(PR層)(注文単位(**1)))の戻り値をセット。
                l_blnIsEveningSessionCarryOverFlag =
                    WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(
                        l_toSuceeOrderManager.createIfoOrderUnit(l_rsvIfoOrderUnitImpl));
                
                //立会区分(*9) = 注文単位.立会区分
                l_strSessionType = l_rsvIfoOrderUnitRow.getSessionType();
            }
            //(*2)先物OP注文単位の場合セット。以外、null
            else if (l_orderUnit instanceof IfoOrderUnit)
            {
                //(*1)this.get銘柄(注文単位.銘柄ID, 注文単位.銘柄タイプ)により取得。
                IfoProductRow l_ifoProductRow = 
                    (IfoProductRow)this.getProduct(
                        l_orderUnit.getProduct().getProductId(), 
                        l_productTypeEnum).getDataSourceObject();

                //銘柄コード
                l_strProductCode = l_ifoProductRow.getProductCode();

                //銘柄名
                l_strProductName = l_ifoProductRow.getStandardName();

                //指数種別(*10) = 銘柄.原資産銘柄コード
                l_strTargetProductCode = l_ifoProductRow.getUnderlyingProductCode();

                //限月(*10) = 銘柄.限月
                l_strDelivaryMonth = l_ifoProductRow.getMonthOfDelivery();
                IfoOrderUnitRow l_ifoOrderUnitRow = 
                    (IfoOrderUnitRow)((IfoOrderUnit)l_orderUnit).getDataSourceObject();

                //先物OP注文単位.先物／オプション区分 = "オプション"の場合
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoOrderUnitRow.getFutureOptionDiv()))
                {
                    if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
                    {
                        l_strOpProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
                    }
                    else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
                    {
                        l_strOpProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
                    }

                    //行使価格(*3) = 銘柄.行使価格
                    l_strStrikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());
                }

                //市場コード = 注文単位.市場IDに該当する市場.市場コード
                l_strMarketCode = new WEB3GentradeMarket(
                    l_ifoOrderUnitRow.getMarketId()).getMarketCode();

                //執行条件(*6) = this.get執行条件（PR層）(注文単位)
                l_strExecCondType =
                    this.getExecutionConditionTypeByPr(l_orderUnit);

                //発注条件区分(*6) = 注文単位.発注条件 
                l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

                //注文時間 = 注文単位.作成日付
                l_datOrderDate = l_ifoOrderUnitRow.getCreatedTimestamp();

                //発注日 = 注文単位.発注日
                l_strOrderBizDate = l_ifoOrderUnitRow.getBizDate();

                //注文有効期限 =
                //先物OPデータアダプタ.get注文期限区分（注文単位）が"出来るまで注文"の場合のみ、注文単位.注文失効日付。
                //以外、nullをセット。
                if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(
                    WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit)))
                {
                    l_datExpirationDate = l_ifoOrderUnitRow.getExpirationDate();
                }

                //受渡代金 = 注文単位.概算受渡代金 
                l_dblDeliveryPrice = l_ifoOrderUnitRow.getEstimatedPrice();
                
                //注文単位.注文カテゴリ
                  OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
                
                //取引コード（SONAR）
                l_strSonarTradedCode = l_ifoOrderUnitRow.getSonarTradedCode();
                
                //　@取引カレンダコンテキストに以下の値をセットする。 
                //  ○市場コード = 原資産銘柄コードを取得している場合、"0:DEFAULT"をセット。  
                //  ○銘柄コード = 原資産銘柄コードを取得している場合、
                //    取得した原資産銘柄コード 
                if (l_strTargetProductCode != null)
                {
                    l_clendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);
                    l_clendarContext.setProductCode(l_strTargetProductCode); 
                }
                else
                {
                    //上記以外は、取得した市場コードをセット。
                    l_clendarContext.setMarketCode(l_strMarketCode);
                }

                //  ○受付時間区分 = this.get受付時間区分()の戻り値をセット。
                l_clendarContext.setTradingTimeType(this.getTradingTimeType(l_orderCateg, l_strSonarTradedCode));

                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_clendarContext);
                
                //処理状況(*6) = this.get処理状況(注文単位) 
                l_strTransactionStateType = this.getTransactionState(l_orderUnit);
                
                l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
                //[注文単位.発注条件 == "逆指値"の場合]
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //逆指値用プレミアム／原資産価格(*2) = 注文単位.逆指値基準値タイプ
                    l_stopPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();
                        
                    //逆指値用発注条件単価(*6) = 注文単位.逆指値基準値
                    if (!l_ifoOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_stopOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    }

                    //逆指値用発注条件演算子(*6) = 注文単位.発注条件演算子
                    l_stopOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();
                }
                //[注文単位.発注条件 == "W指値"の場合]
                else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //Ｗ指値用プレミアム／原資産価格(*2) = 注文単位.逆指値基準値タイプ
                    l_wlimitPremium_underlyingAssets = l_ifoOrderUnitRow.getStopPriceType();

                    //Ｗ指値用発注条件単価(*6) = 注文単位.逆指値基準値
                    if (!l_ifoOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_wlimitOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getStopOrderPrice());
                    }
                    //Ｗ指値用発注条件演算子(*6) = 注文単位.発注条件演算子
                    l_wlimitOrderCondOperator = l_ifoOrderUnitRow.getOrderCondOperator();

                    if (Double.compare(l_ifoOrderUnitRow.getWLimitPrice(), 0) == 0)
                    {
                        //Ｗ指値用注文単価区分(*6) = 注文単位.（W指値）訂正指値 == 0の場合、"成行"。
                        l_wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        //Ｗ指値用注文単価区分(*6) = 注文単位.（W指値）訂正指値 != 0の場合、"指値"。
                        l_wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                        //Ｗ指値用注文単価(*6) = 注文単位.（W指値）訂正指値 != 0の場合、その値をセット。
                        l_wLimitPrice =
                            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnitRow.getWLimitPrice());
                    }
                }

                //    ※以下、「データアダプタ」は注文単位の型により次のように読み替える事。
                //  　@　@先物OP注文単位の場合、「先物OPデータアダプタ
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;

                //　@Ｗ指値用執行条件(*6) = データアダプタ.getＷ指値用執行条件(注文単位)の戻り値
                l_strWLimitExecCondType = WEB3IfoDataAdapter.getWLimitExecCondType(l_ifoOrderUnit);

                //  　@Ｗ指値用有効状態区分(*6) = データアダプタ.getＷ指値用有効状態区分(注文単位)の戻り値
                l_strWLimitEnableStatusDiv =
                    WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_ifoOrderUnit);

                //  　@Ｗ指値用切替前注文単価(*6) = データアダプタ.getＷ指値用切替前注文単価(注文単位)の戻り値
                l_strWLimitBefSwitchPrice =
                    WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_ifoOrderUnit);

                //  　@Ｗ指値用切替前執行条件(*6) = データアダプタ.getＷ指値用切替前執行条件(注文単位)の戻り値
                l_strWLimitBefSwitchExecCondType =
                    WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_ifoOrderUnit);

                //  　@元発注条件区分(*6) = 注文単位.元発注条件
                l_strOrgOrderConditionType =
                    l_ifoOrderUnitRow.getOrgOrderConditionType();

                //  　@元発注条件単価(*6) = 注文単位.元逆指値基準値
                l_strOrgOrderPrice = null;
                if (!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull())
                {
                    l_strOrgOrderPrice = WEB3StringTypeUtility.formatNumber(
                        l_ifoOrderUnitRow.getOrgStopOrderPrice());
                }

                //  　@元発注条件演算子(*6) = 注文単位.元発注条件演算子
                l_strOrgOrderCondOperator =
                    l_ifoOrderUnitRow.getOrgOrderCondOperator();

                //  　@元Ｗ指値用注文単価区分(*6) = データアダプタ.get元Ｗ指値用注文単価区分(注文単位)の戻り値
                l_strOrgWLimitOrderPriceDiv =
                    WEB3IfoDataAdapter.getOrgWLimitOrderPriceDiv(l_ifoOrderUnit);

                //  　@元Ｗ指値用注文単価(*6) = 元W指値用注文単価区分 == "指値"の場合のみ
                //  　@　@データアダプタ.get元Ｗ指値用注文単価(注文単位)の戻り値
                l_strOrgWLimitOrderPrice = null;
                if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
                {
                    l_strOrgWLimitOrderPrice =
                        WEB3IfoDataAdapter.getOrgWLimitOrderPrice(l_ifoOrderUnit);
                }

                //  　@元Ｗ指値用執行条件(*6) = データアダプタ.get元Ｗ指値用執行条件(注文単位)の戻り値
                l_strOrgWLimitExecCondType =
                    WEB3IfoDataAdapter.getOrgWLimitExecCondType(l_ifoOrderUnit);

                //   注文単価区分 = 注文単位.isMarketOrder == trueの場合、"成行"。 
                if (l_orderUnit.isMarketOrder())
                {
                    l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {   //  　@　@　@以外、"指値"をセット
                    //  　@注文単価.isMarketOrder == falseの場合、その値をセット。
                    l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_strPrice = WEB3StringTypeUtility.formatNumber(
                        l_ifoOrderUnitRow.getLimitPrice());
                }

                //元プレミアム／原資産価格(*2) = 注文単位.元逆指値基準値タイプ
                l_strOrgStopPriceType = l_ifoOrderUnitRow.getOrgStopPriceType();

                //夕場前繰越対象フラグ(*9) = データアダプタ.get夕場前繰越対象フラグ(PR層)(注文単位)の戻り値をセット。
                l_blnIsEveningSessionCarryOverFlag =
                    WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_ifoOrderUnit);

                //立会区分(*9) = 注文単位.立会区分
                l_strSessionType = l_ifoOrderUnitRow.getSessionType();
            }

            //(*4)株式注文単位の場合
            else if (l_orderUnit instanceof EqTypeOrderUnit)
            {
                EqtypeOrderUnitRow l_eqTypeOrderUnitRow = 
                    (EqtypeOrderUnitRow)(((EqTypeOrderUnit)l_orderUnit).getDataSourceObject());
                

                //(*1)this.get銘柄(注文単位.銘柄ID, 注文単位.銘柄タイプ)により取得。
                EqtypeProductRow l_eqtypeProductRow = 
                    (EqtypeProductRow)this.getProduct(
                        l_orderUnit.getProduct().getProductId(), 
                        l_productTypeEnum).getDataSourceObject();

                //銘柄コード
                l_strProductCode = l_eqtypeProductRow.getProductCode();

                //銘柄名
                l_strProductName = l_eqtypeProductRow.getStandardName();

                //市場コード = 注文単位.市場IDに該当する市場.市場コード
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_eqTypeOrderUnitRow.getMarketId()).getMarketCode();

                //注文単位.注文カテゴリ
                OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            
                //口座区分(*4) = 株式データアダプタ.get口座区分(引数 注文単位.getTaxType)
                l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());
            
                //株式注文単位.注文カテゴリ != "現物注文"の場合
                if (!OrderCategEnum.ASSET.equals(l_orderCateg))
                {
                    //弁済区分(*5) = 注文単位.弁済区分
                    l_strRepaymentDiv = l_eqTypeOrderUnitRow.getRepaymentType();
                    //弁済期限値(*5) = 注文単位.弁済期限値
                    l_strRepaymentTimeLimit = 
                        new Long(l_eqTypeOrderUnitRow.getRepaymentNum()).toString();
                }
            
                //値段条件(*4) = 注文単位.値段条件
                l_strPriceCondType = l_eqTypeOrderUnitRow.getPriceConditionType();

                //執行条件(*6) = this.get執行条件（PR層）(注文単位)
                l_strExecCondType = 
                    this.getExecutionConditionTypeByPr(l_orderUnit);

                //発注条件区分(*6) = 注文単位.発注条件
                l_strOrderConditionType = l_eqTypeOrderUnitRow.getOrderConditionType();

                //注文時間 = 注文単位.作成日付
                l_datOrderDate = l_eqTypeOrderUnitRow.getCreatedTimestamp();

                //発注日 = 注文単位.発注日
                l_strOrderBizDate = l_eqTypeOrderUnitRow.getBizDate();

                //注文有効期限 = 注文単位.初回注文の注文単位ID != nullの場合のみ、 
                //注文単位.注文失効日付。以外、nullをセット。 
                if (!l_eqTypeOrderUnitRow.getFirstOrderUnitIdIsNull())
                {
                    l_datExpirationDate = l_eqTypeOrderUnitRow.getExpirationDate();
                }

                //受渡代金 = 注文単位.概算受渡代金 
                l_dblDeliveryPrice = l_eqTypeOrderUnitRow.getEstimatedPrice();

                //強制決済理由(*4) = 注文単位.強制決済理由
                l_strForcedSettleReason = l_eqTypeOrderUnitRow.getForcedSettleReasonType();

                //強制失効区分(*7) = 注文単位.強制失効区分
                l_strForcedExpireType = l_eqTypeOrderUnitRow.getForcedExpireType();

                //取引コード（SONAR）
                l_strSonarTradedCode = l_eqTypeOrderUnitRow.getSonarTradedCode();

                //　@取引カレンダコンテキストに以下の値をセットする。 
                //  ○市場コード = 原資産銘柄コードを取得している場合、"0:DEFAULT"をセット。  
                //  ○銘柄コード = 原資産銘柄コードを取得している場合、
                //    取得した原資産銘柄コード 
                if (l_strTargetProductCode != null)
                {
                    l_clendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);
                    l_clendarContext.setProductCode(l_strTargetProductCode); 
                }
                else
                {
                    //上記以外は、取得した市場コードをセット。
                    l_clendarContext.setMarketCode(l_strMarketCode);
                }

                //  ○受付時間区分 = this.get受付時間区分()の戻り値をセット。
                l_clendarContext.setTradingTimeType(this.getTradingTimeType(l_orderCateg, l_strSonarTradedCode));

                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_clendarContext);
                
                //処理状況(*6) = this.get処理状況(注文単位) 
                l_strTransactionStateType = this.getTransactionState(l_orderUnit);
                
                //[注文単位.発注条件 == "逆指値"の場合]
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //逆指値用発注条件単価(*6) = 注文単位.逆指値基準値
                    if (!l_eqTypeOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_stopOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(l_eqTypeOrderUnitRow.getStopOrderPrice());
                    }

                    //逆指値用発注条件演算子(*6) = 注文単位.発注条件演算子
                    l_stopOrderCondOperator = l_eqTypeOrderUnitRow.getOrderCondOperator();
                }
                //[注文単位.発注条件 == "W指値"の場合]                
                else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
                {
                    //Ｗ指値用発注条件単価(*6) = 注文単位.逆指値基準値
                    if (!l_eqTypeOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_wlimitOrderCondPrice =
                            WEB3StringTypeUtility.formatNumber(
                                l_eqTypeOrderUnitRow.getStopOrderPrice());
                    }

                    //Ｗ指値用発注条件演算子(*6) = 注文単位.発注条件演算子
                    l_wlimitOrderCondOperator = l_eqTypeOrderUnitRow.getOrderCondOperator();

                    if (Double.compare(l_eqTypeOrderUnitRow.getWLimitPrice(), 0) == 0)
                    {
                        //Ｗ指値用注文単価区分(*6) = 注文単位.（W指値）訂正指値 == 0の場合、"成行"。
                        l_wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        //Ｗ指値用注文単価区分(*6) = 注文単位.（W指値）訂正指値 != 0の場合、"指値"。
                        l_wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                        //Ｗ指値用注文単価(*6) = 注文単位.（W指値）訂正指値 != 0の場合、その値をセット。
                        l_wLimitPrice =
                            WEB3StringTypeUtility.formatNumber(l_eqTypeOrderUnitRow.getWLimitPrice());
                    }
                }
                //※以下、「データアダプタ」は注文単位の型により次のように読み替える事。
                //  　@　@株式注文単位の場合、「株式データアダプタ
                EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;

                //　@Ｗ指値用執行条件(*6) = データアダプタ.getＷ指値用執行条件(注文単位)の戻り値
                l_strWLimitExecCondType = WEB3EquityDataAdapter.getWLimitExecCondType(l_eqTypeOrderUnit);
                
                //  　@Ｗ指値用有効状態区分(*6) = データアダプタ.getＷ指値用有効状態区分(注文単位)の戻り値
                l_strWLimitEnableStatusDiv =
                    WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_eqTypeOrderUnit);

                //  　@Ｗ指値用切替前注文単価(*6) = データアダプタ.getＷ指値用切替前注文単価(注文単位)の戻り値
                l_strWLimitBefSwitchPrice =
                    WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_eqTypeOrderUnit);

                //  　@Ｗ指値用切替前執行条件(*6) = データアダプタ.getＷ指値用切替前執行条件(注文単位)の戻り値
                l_strWLimitBefSwitchExecCondType =
                    WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_eqTypeOrderUnit);

                //  　@元発注条件区分(*6) = 注文単位.元発注条件
                l_strOrgOrderConditionType =
                    l_eqTypeOrderUnitRow.getOrgOrderConditionType();

                //  　@元発注条件単価(*6) = 注文単位.元逆指値基準値
                l_strOrgOrderPrice = null;
                if (!l_eqTypeOrderUnitRow.getOrgStopOrderPriceIsNull())
                {
                    l_strOrgOrderPrice = WEB3StringTypeUtility.formatNumber(
                        l_eqTypeOrderUnitRow.getOrgStopOrderPrice());
                }

                //  　@元発注条件演算子(*6) = 注文単位.元発注条件演算子
                l_strOrgOrderCondOperator =
                    l_eqTypeOrderUnitRow.getOrgOrderCondOperator();

                //  　@元Ｗ指値用注文単価区分(*6) = データアダプタ.get元Ｗ指値用注文単価区分(注文単位)の戻り値
                l_strOrgWLimitOrderPriceDiv =
                    WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_eqTypeOrderUnit);

                //  　@元Ｗ指値用注文単価(*6) = 元W指値用注文単価区分 == "指値"の場合のみ
                //  　@　@データアダプタ.get元Ｗ指値用注文単価(注文単位)の戻り値
                l_strOrgWLimitOrderPrice = null;
                if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
                {
                    l_strOrgWLimitOrderPrice =
                        WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_eqTypeOrderUnit);
                }

                //  　@元Ｗ指値用執行条件(*6) = データアダプタ.get元Ｗ指値用執行条件(注文単位)の戻り値
                l_strOrgWLimitExecCondType =
                    WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_eqTypeOrderUnit);

                //   注文単価区分 = 注文単位.isMarketOrder == trueの場合、"成行"。 
                if (l_orderUnit.isMarketOrder())
                {
                    l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
                else
                {   //  　@　@　@以外、"指値"をセット
                    //  　@注文単価.isMarketOrder == falseの場合、その値をセット。
                    l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                    l_strPrice = WEB3StringTypeUtility.formatNumber(
                        l_eqTypeOrderUnitRow.getLimitPrice());
                }

            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        //注文ID
        l_succOrderUnit.orderId = l_strOrderId; 
        //商品区分
        l_succOrderUnit.commodityType = l_strCommodityType;
        //銘柄コード
        l_succOrderUnit.productCode = l_strProductCode;
        //銘柄名
        l_succOrderUnit.productName = l_strProductName;
        //指数種別
        l_succOrderUnit.targetProductCode = l_strTargetProductCode;
        //限月
        l_succOrderUnit.delivaryMonth = l_strDelivaryMonth;
        //オプション商品区分
        l_succOrderUnit.opProductType = l_strOpProductType;
        //行使価格
        l_succOrderUnit.strikePrice = l_strStrikePrice;
        //市場コード
        l_succOrderUnit.marketCode = l_strMarketCode;
        //口座区分
        l_succOrderUnit.taxType = l_strTaxType;
        //取引区分　@※立会外分売の場合のみ、コード変換が必要
        if (!OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_succOrderUnit.tradingType = new Long(l_orderType.intValue()).toString();
        }
        else
        {
            if ((l_strSonarTradedCode != null) &&
                (l_strSonarTradedCode.equals(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET)))
            {
                l_succOrderUnit.tradingType = WEB3TradingTypeDef.PRESENCE_ORDER;
            }
            else
            {
                l_succOrderUnit.tradingType = new Long(l_orderType.intValue()).toString();
            }
        }
        //弁済区分
        l_succOrderUnit.repaymentDiv = l_strRepaymentDiv;
        //弁済期限値
        l_succOrderUnit.repaymentTimeLimit = l_strRepaymentTimeLimit;
        //値段条件
        l_succOrderUnit.priceCondType = l_strPriceCondType;
        //執行条件
        l_succOrderUnit.execCondType = l_strExecCondType;
        //発注条件区分
        l_succOrderUnit.orderCondType = l_strOrderConditionType;
        //注文数量
        l_succOrderUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity); 
        //注文時間
        l_succOrderUnit.orderDate = l_datOrderDate;
        //発注日
        l_succOrderUnit.orderBizDate = 
            WEB3DateUtility.getDate(l_strOrderBizDate, "yyyyMMdd");
        //注文有効期限
        l_succOrderUnit.expirationDate = l_datExpirationDate;
        //受渡代金
        l_succOrderUnit.deliveryPrice = WEB3StringTypeUtility.formatNumber(l_dblDeliveryPrice);

        //強制決済理由(*4) = 注文単位.強制決済理由
        l_succOrderUnit.forcedSettleReason = l_strForcedSettleReason;

        //強制失効区分(*7) = 注文単位.強制失効区分
        l_succOrderUnit.forcedLapseDiv = l_strForcedExpireType;

        //処理状況
        l_succOrderUnit.transactionStateType = l_strTransactionStateType;
        //逆指値用プレミアム/原資産価格
        l_succOrderUnit.stopPremium_underlyingAssets = l_stopPremium_underlyingAssets;
        //逆指値用発注条件単価
        l_succOrderUnit.stopOrderCondPrice = l_stopOrderCondPrice;
        //逆指値用発注条件演算子
        l_succOrderUnit.stopOrderCondOperator = l_stopOrderCondOperator;
        //Ｗ指値用プレミアム/原資産価格
        l_succOrderUnit.wlimitPremium_underlyingAssets = l_wlimitPremium_underlyingAssets;
        //Ｗ指値用発注条件単価
        l_succOrderUnit.wlimitOrderCondPrice = l_wlimitOrderCondPrice;
        //Ｗ指値用発注条件演算子
        l_succOrderUnit.wlimitOrderCondOperator = l_wlimitOrderCondOperator;
        //Ｗ指値用注文単価区分
        l_succOrderUnit.wLimitOrderPriceDiv = l_wLimitOrderPriceDiv;
        //Ｗ指値用注文単価
        l_succOrderUnit.wLimitPrice = l_wLimitPrice;

        //Ｗ指値用執行条件
        l_succOrderUnit.wlimitExecCondType = l_strWLimitExecCondType;

        //Ｗ指値用有効状態区分(*6)
        l_succOrderUnit.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

        //  　@Ｗ指値用切替前注文単価(*6)
        l_succOrderUnit.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

        //  　@Ｗ指値用切替前執行条件(*6)
        l_succOrderUnit.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

        //  　@元発注条件区分(*6)
        l_succOrderUnit.orgOrderCondType = l_strOrgOrderConditionType;

        //  　@元発注条件単価(*6)
        l_succOrderUnit.orgOrderCondPrice = l_strOrgOrderPrice;

        //  　@元発注条件演算子(*6)
        l_succOrderUnit.orgOrderCondOperator = l_strOrgOrderCondOperator;

        //  　@元Ｗ指値用注文単価区分(*6)
        l_succOrderUnit.orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

        //  　@元Ｗ指値用注文単価(*6)
        l_succOrderUnit.orgWlimitPrice = l_strOrgWLimitOrderPrice;

        //  　@元Ｗ指値用執行条件(*6)
        l_succOrderUnit.orgWlimitExecCondType = l_strOrgWLimitExecCondType;

        //  　@注文単価区分
        l_succOrderUnit.orderPriceDiv = l_strOrderPriceDiv;

        //  　@注文単価
        l_succOrderUnit.orderPrice = l_strPrice;

        //元プレミアム／原資産価格
        l_succOrderUnit.orgPremium_underlyingAssets = l_strOrgStopPriceType;

        //夕場前繰越対象フラグ
        l_succOrderUnit.eveningSessionCarryoverFlag = l_blnIsEveningSessionCarryOverFlag;

        //立会区分
        l_succOrderUnit.sessionType = l_strSessionType;

        //パラメータ.is可能フラグ設定 == trueの場合
        if (l_blnIsPossibleFlagSet)
        {
            //this.set可能フラグ()をコールする
            this.setPossibleFlag(l_succOrderUnit, l_orderUnit);
        }
        
        // 退避した値を取引カレンダコンテキストに 
        // 再セットする。
        l_clendarContext.setMarketCode(l_strOldMarketCode);
        l_clendarContext.setProductCode(l_strOldProductCode);
        l_clendarContext.setTradingTimeType(l_strOldTradingTimeType);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create予約注文明細)<BR>
     * 引数の前提注文明細に予約注文明細をセットする。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストから以下の値を取得し、退避させる。<BR>
     * 　@　@○市場コード<BR>
     * 　@　@○銘柄コード<BR>
     * 　@　@○受付時間区分<BR>
     * <BR>
     * ２）　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@パラメータ.予約注文Row一覧の要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@※処理対象の要素を株式予約注文単位Row型、<BR>
     * 　@　@　@または先物OP予約注文単位Row型にキャストする。<BR>
     * 　@３－１）　@予約注文単位インスタンスを生成する。<BR>
     * 　@　@連続注文マネージャImpl.toOrderUnit()をコールする。<BR>
     * <BR>
     * 　@　@[toOrderUnit()に指定する引数]<BR>
     * 　@　@　@注文単位Row：　@処理対象の要素<BR>
     * <BR>
     * 　@３－２）　@予約注文発注済(予約注文単位.is発注済() == true)の場合、<BR>
     * 　@　@各商品の注文単位を取得する。<BR>
     * 　@　@this.get注文単位()をコールする。<BR>
     * <BR>
     * 　@　@[get注文単位()に指定する引数]<BR>
     * 　@　@　@注文単位ID：　@処理対象の要素.注文単位ID<BR>
     * 　@　@　@銘柄タイプ：　@処理対象の要素.銘柄タイプ<BR>
     * <BR>
     * 　@３－３）　@処理対象の注文単位（=OrderUnit）を決定する。<BR>
     * 　@　@[予約注文発注済(予約注文単位.is発注済() == true)の場合]<BR>
     * 　@　@　@処理対象の注文単位 = ３－２）の戻り値<BR>
     * <BR>
     * 　@　@[上記以外]<BR>
     * 　@　@　@処理対象の注文単位 = ３－１）の戻り値<BR>
     * <BR>
     * 　@３－４）　@取引カレンダコンテキストに以下の値をセットする。<BR>
     * 　@○市場コード = 原資産銘柄コードを取得している場合、"0:DEFAULT"をセット。<BR>
     * 　@　@上記以外は、取得した市場コードをセット。<BR>
     * 　@○銘柄コード = 原資産銘柄コードを取得している場合、<BR>
     * 　@　@取得した原資産銘柄コード<BR>
     * 　@○受付時間区分 = this.get受付時間区分(予約注文単位.注文カテゴリ, null)の戻り値をセット。<BR>
     * <BR>
     * 　@３－５）　@予約注文明細インスタンスを生成する。<BR>
     * <BR>
     * 　@３－６）　@this.create連続注文明細()をコールする。<BR>
     * <BR>
     * 　@　@[create連続注文明細()に指定する引数]<BR>
     * 　@　@　@連続注文明細：　@生成した予約注文明細<BR>
     * 　@　@　@注文単位：　@処理対象の注文単位<BR>
     * 　@　@　@is可能フラグ設定：　@false(設定しない)<BR>
     * <BR>
     * 　@３－７）　@生成した予約注文明細に以下のプロパティを<BR>
     * 　@　@セットする。<BR>
     * 　@　@※予約注文単位から取得する各項目は、<BR>
     * 　@　@　@直接設定せず、一度別変数に格納するように実装すること。<BR>
     * <BR>
     * 　@　@値段条件 = "指定なし"(*3)<BR>
     * 　@　@執行条件 = "無条件"(*4)<BR>
     * 　@　@発注条件区分 = "指定なし"(*4)<BR>
     * 　@　@単価調整値 = 予約注文単位(*1).is±指値指定()==trueの場合、<BR>
     * 　@　@　@その値をセット。(*5)<BR>
     * 　@　@親注文の注文ID = 予約注文単位.親注文の注文ID<BR>
     * 　@　@親注文内連番 = 予約注文単位.親注文内連番<BR>
     * 　@　@注文エラー理由コード =<BR>
     * 　@　@　@[株式予約注文単位Rowの場合]<BR>
     * 　@　@　@　@拡張株式注文マネージャ.get注文エラー理由コード(予約注文単位.発注エラーコード)<BR>
     * 　@　@　@[先物OP予約注文単位Rowの場合]<BR>
     * 　@　@　@　@OP注文マネージャ.get注文エラー理由コード(予約注文単位.発注エラーコード)<BR>
     * <BR>
     * 　@　@　@※発注処理が実行されていない（予約注文単位.is発注実行済()==false）場合は、nullをセット。<BR>
     * 　@　@処理状況 = (*2)<BR>
     * <BR>
     * 　@　@(*1)３－１）の戻り値をinstanceofにて判別し、<BR>
     * 　@　@　@　@以下のいずれかにキャストして取得。<BR>
     * 　@　@　@　@　@・株式予約注文単位Impl<BR>
     * 　@　@　@　@　@・先物OP予約注文単位Impl<BR>
     * <BR>
     * 　@　@(*2)this.get処理状況()の戻り値をセット。<BR>
     * <BR>
     * 　@　@　@　@　@　@[get処理状況()に指定する引数]<BR>
     * 　@　@　@　@　@　@　@注文単位：　@処理対象の注文単位<BR>
     * <BR>
     * 　@　@(*3)発注済の場合(予約注文単位.is発注済()==true)は、<BR>
     * 　@　@　@　@処理対象の要素.銘柄タイプ=="株式"の場合のみ、<BR>
     * 　@　@　@　@３－２）で取得した各商品の注文単位の同項目をセットする。<BR>
     * <BR>
     * 　@　@(*4)発注済の場合(予約注文単位.is発注済()==true)は、 <BR>
     * 　@　@　@　@３－２）で取得した各商品の注文単位の同項目をセットする。 <BR>
     * 　@　@　@　@ただし、執行条件はthis.get執行条件（PR層）() <BR>
     * 　@　@　@　@でSONARのコード体系に変換してセットする。 <BR>
     * <BR>
     * 　@　@　@　@　@　@[get執行条件（PR層）()に指定する引数] <BR>
     * 　@　@　@　@　@　@注文単位：　@３－２）で取得した注文単位 <BR>
     * <BR>
     * 　@　@(*5)発注済の場合(予約注文単位.is発注済()==true)は、<BR>
     * 　@　@　@　@nullをセットする。<BR>
     * <BR>
     * 　@３－８）　@パラメータ.is可能フラグ設定 == trueの場合、<BR>
     * 　@　@以下の条件により各可能フラグを設定する。<BR>
     * <BR>
     * 　@　@　@　@[未発注の場合(予約注文単位.is発注済()==false)]<BR>
     * 　@　@　@　@－予約注文単位.注文有効状態 == "クローズ"の場合、<BR>
     * 　@　@　@　@　@以下のプロパティにfalseをセット。<BR>
     * 　@　@　@　@　@　@・予約注文明細.訂正可能フラグ<BR>
     * 　@　@　@　@　@　@・予約注文明細.取消可能フラグ<BR>
     * <BR>
     * 　@　@　@　@－取引時間管理.validate連続注文受付可能()(*6)が例外をスローした場合、<BR>
     * 　@　@　@　@　@以下のプロパティにfalseをセット。<BR>
     * 　@　@　@　@　@　@・予約注文明細.訂正可能フラグ<BR>
     * 　@　@　@　@　@　@・予約注文明細.取消可能フラグ<BR>
     * <BR>
     * 　@　@　@　@－現引現渡注文（予約注文単位.注文カテゴリ=="現引現渡"）の場合、<BR>
     * 　@　@　@　@　@以下のプロパティにfalseをセット。<BR>
     * 　@　@　@　@　@　@・予約注文明細.訂正可能フラグ<BR>
     * <BR>
     * 　@　@　@　@[発注済(上記以外)の場合]<BR>
     * 　@　@　@　@　@this.set可能フラグ()をコールする。<BR>
     * <BR>
     * 　@　@　@　@　@[set可能フラグ()に指定する引数]<BR>
     * 　@　@　@　@　@　@連続注文明細：　@プロパティセットした予約注文明細<BR>
     * 　@　@　@　@　@　@注文単位：　@処理対象の注文単位<BR>
     * <BR>
     * 　@　@　@　@(*6)　@[引数]<BR>
     * 　@　@　@　@　@証券会社：　@取引カレンダコンテキスト.証券会社コードに該当する証券会社<BR>
     * 　@　@　@　@　@銘柄タイプ：　@予約注文単位.銘柄タイプ<BR>
     * 　@　@　@　@　@先物／オプション区分：<BR>
     * 　@　@　@　@　@　@[株式予約注文単位Rowの場合]<BR>
     * 　@　@　@　@　@　@　@"DEFAULT" <BR>
     * 　@　@　@　@　@　@[先物OP予約注文単位Rowの場合]<BR>
     * 　@　@　@　@　@　@　@予約注文単位.先物／オプション区分<BR>
     * 　@　@　@　@　@出来終了区分：<BR>
     * 　@　@　@　@　@　@[株式予約注文単位Rowの場合]<BR>
     * 　@　@　@　@　@　@　@"出来終了（最終）"<BR>
     * 　@　@　@　@　@　@[先物OP予約注文単位Rowの場合]<BR>
     * 　@　@　@　@　@　@　@拡張アカウントマネージャ.getBranch(<BR>
     * 　@　@　@　@　@　@取得した部店ID).is夕場実施(予約注文単位.銘柄タイプ) == true and<BR>
     * 　@　@　@　@　@　@　@予約注文単位.立会区分 == "その他"の場合、"夕場前出来終了"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@以外、"出来終了（最終）"<BR>
     * <BR>
     * 　@３－９）　@プロパティセットした予約注文明細.連続注文可能フラグ = falseを<BR>
     * 　@　@　@　@　@　@セットする。<BR>
     * 　@　@※子注文は連続設定不可。<BR>
     * <BR>
     * 　@３－１０）　@ArrayListにプロパティセットした予約注文明細を追加する。<BR>
     * <BR>
     * ４）　@パラメータ.前提注文明細.予約注文一覧に<BR>
     * 　@ArrayList.toArray()の戻り値をセットする。<BR>
     * <BR>
     * ５）　@１）にて退避した値を取引カレンダコンテキストに再セットする。<BR>
     * @@param l_requiredOrderUnit - (前提注文明細)<BR>
     * 前提注文明細
     * @@param l_rsvOrderRowList - (予約注文Row一覧)<BR>
     * 予約注文一覧
     * @@param l_blnIsPossibleFlagSet - (is可能フラグ設定)<BR>
     * 訂正・取消・連続注文可能フラグを<BR>
     * 設定するかどうかのフラグ<BR>
     * <BR>
     * false：　@設定しない<BR>
     * true：　@設定する<BR>
     * @@exception WEB3BaseException
     * @@roseuid 431E4E4500E1
     */
    public void createRsvOrderUnit(
        WEB3SuccOrderUnit l_requiredOrderUnit, 
        Row[] l_rsvOrderRowList, 
        boolean l_blnIsPossibleFlagSet)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "createRsvOrderUnit(WEB3SuccOrderUnit, Row[], boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_requiredOrderUnit == null || l_rsvOrderRowList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //取引カレンダコンテキストの値を退避する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
            ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        String l_strOrgMarketCode = l_clendarContext.getMarketCode();
        String l_strOrgProductCode = l_clendarContext.getProductCode();
        String l_strOrgTradingTimeType = l_clendarContext.getTradingTimeType();

        //ArrayListを生成する。 
        ArrayList l_reservationOrderUnits = new ArrayList();
        //パラメータ.予約注文Row一覧の要素数分、 
        // 以下の処理を繰り返す。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityOrderManager l_eqtypeOrderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        WEB3GentradeInstitution l_institution = null;
        for (int i = 0; i < l_rsvOrderRowList.length; i++)
        {
            //予約注文単位インスタンスを生成する。
            WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            OrderUnit l_rsvOrderUnit = null;
            l_rsvOrderUnit = 
                l_orderManagerImpl.toOrderUnit(l_rsvOrderRowList[i]);
            
            boolean l_blnIsOrderd = false;
            long l_lngOrderUnitId = 0;
            ProductTypeEnum l_productTypeEnum = null;
            String l_strFutureOptionDiv = null;
            String l_strPriceAdjustmentValue = null;
            String l_strParentOrderId = null;
            String l_strSerialNoInParent = null;
            String l_strOrderErrorCode = null;
            String l_strMarketCode = null;
            OrderOpenStatusEnum l_rsvOrderOpenStatus = l_rsvOrderUnit.getOrderOpenStatus();
            OrderStatusEnum l_rsvOrderStatus = l_rsvOrderUnit.getOrderStatus();
            OrderCategEnum l_rsvOrderCategEnum = l_rsvOrderUnit.getOrderCateg();
            //原資産銘柄コード
            String l_strUnderlyingProductCode = null;
            //出来終了区分
            String l_strExecutionEndType = null;

            if (l_rsvOrderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl)
            {
	            //   ※処理対象の要素を株式予約注文単位Row型にキャストする。
	            WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqTypeOrderUnit =
                    (WEB3ToSuccEqTypeOrderUnitImpl)l_rsvOrderUnit;
	            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
	                (RsvEqOrderUnitRow)l_rsvOrderUnit.getDataSourceObject();

                if (l_institution == null)
                {
                    WEB3GentradeSubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount =
                            (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                            l_rsvEqTypeOrderUnit.getAccountId(), l_rsvEqTypeOrderUnit.getSubAccountId());
                    }
                    catch (NotFoundException l_nfe)
                    {
                        log.error("補助口座テーブルに該当するデータがありません。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "補助口座テーブルに該当するデータがありません。");
                    }
                    l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
                }
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
                try
                {
                    l_strMarketCode = l_rsvEqTypeOrderUnit.getMarket().getMarketCode();
                }
                catch (NotFoundException l_nfe)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "市場テーブルに該当するデータがありません。");
                }
	                
	            // 予約注文発注済みフラグ
	            l_blnIsOrderd = l_rsvEqTypeOrderUnit.isOrdered();
	            // 注文単位ID
                l_lngOrderUnitId = l_rsvEqOrderUnitRow.getOrderUnitId();
                // 銘柄タイプ
                l_productTypeEnum = l_rsvEqOrderUnitRow.getProductType();
                //単価調整値
                if (l_rsvEqTypeOrderUnit.isExecPriceOrder())
                {
                    l_strPriceAdjustmentValue =
                        WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getPriceAdjustValue());
                }
                //親注文の注文ID = 予約注文単位.親注文の注文ID
                l_strParentOrderId = 
                    new Long(l_rsvEqOrderUnitRow.getParentOrderId()).toString();
                //親注文内連番 = 予約注文単位.親注文内連番
                l_strSerialNoInParent =
                    new Long(l_rsvEqOrderUnitRow.getSerialNoInParent()).toString();
                //注文エラー理由コード = 拡張株式注文マネージャ.get注文エラー理由コード(予約注文単位.発注エラーコード)
                //※発注処理実行済の場合のみセット。
                if (l_rsvEqTypeOrderUnit.isOrderExecuted())
                {
                    l_strOrderErrorCode =
                        l_eqtypeOrderManager.getErrorReasonCode(l_rsvEqOrderUnitRow.getOrderErrorCode());
                }

                //出来終了区分： "出来終了（最終）"
                l_strExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
            }

            //先物OP予約注文単位Impl
            if (l_rsvOrderUnit instanceof WEB3ToSuccIfoOrderUnitImpl)
            {
                //   ※処理対象の要素を先物OP予約注文単位Row型にキャストする
                WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                    (WEB3ToSuccIfoOrderUnitImpl)l_rsvOrderUnit;
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                    (RsvIfoOrderUnitRow)l_rsvOrderUnit.getDataSourceObject();

                if (l_institution == null)
                {
                    WEB3GentradeSubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount =
                            (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                                l_rsvIfoOrderUnit.getAccountId(),
                                l_rsvIfoOrderUnit.getSubAccountId());
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error("補助口座テーブルに該当するデータがありません。", l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
                }

                //予約注文単位.先物／オプション区分 
                l_strFutureOptionDiv = l_rsvIfoOrderUnitRow.getFutureOptionDiv();

                //市場コード = 取得した市場コード
                l_strMarketCode = l_rsvIfoOrderUnit.getMarket().getMarketCode();

                // 予約注文発注済みフラグ
                l_blnIsOrderd = l_rsvIfoOrderUnit.isOrdered();

                // 注文単位ID
                l_lngOrderUnitId = l_rsvIfoOrderUnitRow.getOrderUnitId();

                // 銘柄タイプ
                l_productTypeEnum = l_rsvIfoOrderUnitRow.getProductType();

                //単価調整値
                if (l_rsvIfoOrderUnit.isExecPriceOrder())
                {
                    l_strPriceAdjustmentValue =
                        WEB3StringTypeUtility.formatNumber(l_rsvIfoOrderUnitRow.getPriceAdjustValue());
                }
                //親注文の注文ID = 予約注文単位.親注文の注文ID
                l_strParentOrderId =
                    new Long(l_rsvIfoOrderUnitRow.getParentOrderId()).toString();

                //親注文内連番 = 予約注文単位.親注文内連番
                l_strSerialNoInParent =
                    new Long(l_rsvIfoOrderUnitRow.getSerialNoInParent()).toString();

                WEB3OptionOrderManagerImpl l_optionOrderManager =
                    (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                //OP注文マネージャ.get注文エラー理由コード(予約注文単位.発注エラーコード)
                //※発注処理実行済の場合のみセット。
                if (l_rsvIfoOrderUnit.isOrderExecuted())
                {
                    l_strOrderErrorCode =
                        l_optionOrderManager.getErrorReasonCode(l_rsvIfoOrderUnitRow.getOrderErrorCode());
                }

                //注文単位.銘柄IDに該当する銘柄.原資産銘柄コード
                IfoProduct l_product =
                    (IfoProduct)this.getProduct(l_rsvIfoOrderUnitRow.getProductId(), l_productTypeEnum);
                l_strUnderlyingProductCode = l_product.getUnderlyingProductCode();

                WEB3GentradeBranch l_branch = null;
                try
                {
                    l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_rsvIfoOrderUnitRow.getBranchId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //出来終了区分：　@拡張アカウントマネージャ.getBranch(取得した部店ID).is夕場実施(予約注文単位.銘柄タイプ)
                //== true and 予約注文単位.立会区分 == "その他"の場合、"夕場前出来終了"
                if (l_branch.isEveningSessionEnforcemented(l_productTypeEnum)
                    && WEB3Toolkit.isEquals(WEB3SessionTypeDef.OTHER, l_rsvIfoOrderUnitRow.getSessionType()))
                {
                    l_strExecutionEndType =
                        WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;
                }
                //以外、"出来終了（最終）"  
                else
                {
                    l_strExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
                }
            }

            //処理対象の注文単位
            OrderUnit l_targetOrderUnit = null;
            EqtypeOrderUnitRow l_targetEqtypeOrderUnitRow = null;
            IfoOrderUnitRow l_targetIfoOrderUnitRow = null;

            //処理対象の注文単位（=OrderUnit）を決定する。 
            if (l_blnIsOrderd)
            {
	            //予約注文発注済(予約注文単位.is発注済() == true)の場合、各商品の注文単位を取得する。
                //   this.get注文単位()をコールする。
                l_targetOrderUnit = this.getOrderUnit(
                    l_lngOrderUnitId,
                    l_productTypeEnum);
                    if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
                    {
                        l_targetEqtypeOrderUnitRow =
                            (EqtypeOrderUnitRow)l_targetOrderUnit.getDataSourceObject();
                    }
                    if (ProductTypeEnum.IFO.equals(l_productTypeEnum))
                    {
                        l_targetIfoOrderUnitRow =
                            (IfoOrderUnitRow)l_targetOrderUnit.getDataSourceObject();
                    }
            }
            else
            {
                l_targetOrderUnit = l_rsvOrderUnit;
            }
            
            //取引カレンダコンテキストの値をセットする。
            //市場コード = 原資産銘柄コードを取得している場合、"0:DEFAULT"をセット。
            //銘柄コード = 原資産銘柄コードを取得している場合、
            //取得した原資産銘柄コード
            if (l_strUnderlyingProductCode != null)
            {
                l_clendarContext.setMarketCode(WEB3MarketCodeDef.DEFAULT);
                l_clendarContext.setProductCode(l_strUnderlyingProductCode);
            }
            else
            {
                //上記以外は、取得した市場コードをセット。
                l_clendarContext.setMarketCode(l_strMarketCode);
            }

            l_clendarContext.setTradingTimeType(this.getTradingTimeType(l_rsvOrderCategEnum, null));

            //予約注文明細インスタンスを生成する。
            WEB3SuccReservationOrderUnit l_succOrderUnit = 
                new WEB3SuccReservationOrderUnit();

            //this.create連続注文明細()をコールする
            this.createSuccOrderUnit(
                l_succOrderUnit,
                l_targetOrderUnit,
                false);

            //生成した予約注文明細に以下のプロパティをセットする。 
            //※予約注文単位から取得する各項目は、
            //直接設定せず、一度別変数に格納するように実装すること。 
            //値段条件
            String l_strPriceCondType = null;
            if (l_blnIsOrderd && ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
            {
                l_strPriceCondType = l_targetEqtypeOrderUnitRow.getPriceConditionType();
            }
            else
            {
                l_strPriceCondType = WEB3PriceConditionDef.DEFAULT;
            }
            l_succOrderUnit.priceCondType = l_strPriceCondType;

            //執行条件
            //発注条件区分
            String l_strExecCondType = null;
            String l_strOrderCondType = null;
            if (!l_blnIsOrderd)
            {
                l_strExecCondType = EqTypeExecutionConditionType.IntValues.NONE + "";
                l_strOrderCondType = WEB3OrderingConditionDef.DEFAULT;
            }
            else
            {
                if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
                {
                    l_strExecCondType =
                        this.getExecutionConditionTypeByPr(l_targetOrderUnit);
                    l_strOrderCondType = l_targetEqtypeOrderUnitRow.getOrderConditionType();
                }
                if (ProductTypeEnum.IFO.equals(l_productTypeEnum))
                {
                    l_strExecCondType =
                        this.getExecutionConditionTypeByPr(l_targetOrderUnit);
                    l_strOrderCondType = l_targetIfoOrderUnitRow.getOrderConditionType();
                }
            }
            l_succOrderUnit.execCondType = l_strExecCondType;
            l_succOrderUnit.orderCondType = l_strOrderCondType;

            //処理状況
            String l_strTransactionState = null;
            l_strTransactionState = this.getTransactionState(l_targetOrderUnit);

            if (l_blnIsOrderd)
            {
                l_succOrderUnit.priceAdjustmentValue = null;
            }
            else
            {
                l_succOrderUnit.priceAdjustmentValue = l_strPriceAdjustmentValue;
            }
            l_succOrderUnit.parentOrderId = l_strParentOrderId;
            l_succOrderUnit.parentOrderSequentialNo = l_strSerialNoInParent;
            l_succOrderUnit.orderErrorCode = l_strOrderErrorCode;
            l_succOrderUnit.transactionStateType = l_strTransactionState;

            //パラメータ.is可能フラグ設定 == trueの場合
            if (l_blnIsPossibleFlagSet)
            {
                //未発注の場合(予約注文単位.is発注済()==false)
                if (!l_blnIsOrderd)
                {
                    if (OrderOpenStatusEnum.CLOSED.equals(l_rsvOrderOpenStatus))
                    {
                        l_succOrderUnit.changeFlag = false;
                        l_succOrderUnit.cancelFlag = false;
                    }
                    try
                    {
                        WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
                            l_institution, l_productTypeEnum, l_strFutureOptionDiv, l_strExecutionEndType);
                    }
                    catch (WEB3BaseException l_e)
                    {
                        l_succOrderUnit.changeFlag = false;
                        l_succOrderUnit.cancelFlag = false;
                    }
                    if (OrderCategEnum.SWAP_MARGIN.equals(l_rsvOrderCategEnum))
                    {
                        l_succOrderUnit.changeFlag = false;
                    }
                }
                else
                {
                    //this.set可能フラグ()をコールする
                    this.setPossibleFlag(l_succOrderUnit, l_targetOrderUnit);
                }
            }

            //プロパティセットした予約注文明細.連続注文可能フラグ = falseをセットする。
            l_succOrderUnit.succOrderFlag = false;

            //ArrayListにプロパティセットした予約注文明細を追加する。
            l_reservationOrderUnits.add(l_succOrderUnit);
            
            //１）にて退避した値を取引カレンダコンテキストに再セットする。
            l_clendarContext.setMarketCode(l_strOrgMarketCode);
            l_clendarContext.setProductCode(l_strOrgProductCode);
            l_clendarContext.setTradingTimeType(l_strOrgTradingTimeType);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
        }

        //パラメータ.前提注文明細.予約注文一覧に
        //    ArrayList.toArray()の戻り値をセットする。 
        l_requiredOrderUnit.reservationOrderList = 
            new WEB3SuccReservationOrderUnit[l_reservationOrderUnits.size()];
        l_reservationOrderUnits.toArray(l_requiredOrderUnit.reservationOrderList);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set可能フラグ)<BR>
     * 引数の連続注文明細に訂正・取消・連続可能フラグを<BR>
     * セットする。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストから以下の値を<BR>
     * 　@取得し、退避させる。<BR>
     * 　@　@○注文受付トランザクション<BR>
     * <BR>
     * ２）　@パラメータ.注文単位の型をinstanceofにて判別し、<BR>
     * 　@以下のいずれかの型にキャストする。<BR>
     * 　@　@・株式注文単位<BR>
     * 　@　@・先物OP注文単位<BR>
     * <BR>
     * ３）　@キャストした注文単位より、以下の値を取得する。<BR>
     * 　@　@・注文単位.注文カテゴリ<BR>
     * 　@　@・注文単位.銘柄タイプ<BR>
     * 　@　@・注文単位.部店ID<BR>
     * 　@　@・注文単位.市場IDに該当する市場.市場コード<BR>
     * 　@　@・注文単位.発注日<BR>
     * 　@　@・注文単位.銘柄IDに該当する銘柄.原資産銘柄コード(*2)<BR>
     * 　@　@・注文単位.先物／オプション区分(*1)<BR>
     * 　@　@・注文単位.取引コード（SONAR）<BR>
     * 　@　@・注文単位.注文経路区分<BR>
     * 　@　@・注文単位.立会区分(*3)<BR>
     * <BR>
     * 　@　@　@(*1)株式注文単位の場合、"DEFAULT"、<BR>
     * 　@　@　@　@　@先物OP注文単位の場合、注文単位の設定値とする。<BR>
     * 　@　@　@(*2)先物OP注文単位の場合のみ取得。<BR>
     * 　@　@　@　@　@this.get銘柄(注文単位.銘柄ID, 注文単位.銘柄タイプ)を<BR>
     * 　@　@　@　@　@使用して銘柄を取得する。<BR>
     * 　@　@　@(*3)株式注文単位の場合、NULL、<BR>
     * 　@　@　@　@　@先物OP注文単位の場合、注文単位の設定値とする。<BR>
     * <BR>
     * ４）　@訂正／取消可能共通チェック<BR>
     * 　@市場閉局時間帯 かつ 当日発注済注文の場合<BR>
     * 　@(取引時間管理.is市場開局時間帯() == false かつ <BR>
     *   取得した市場から確認済みの数量 != NaN)<BR>
     * 　@取引時間管理.validate閉局後訂正取消受付可能()メソッドをコールする。<BR>
     * 　@例外をスローした場合、パラメータ.連続注文明細.訂正可能フラグ、<BR>
     * 　@取消可能フラグにfalseをセットする。<BR>
     * <BR>
     * 　@４－１）　@パラメータ.連続注文明細.商品区分 == "現物株式" or "信用取引"の場合 <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@銘柄タイプ：　@取得した銘柄タイプ<BR>
     * 　@　@　@先物／オプション区分：　@取得した先物／オプション区分<BR>
     * <BR>
     * 　@４－２）　@パラメータ.連続注文明細.商品区分 == "先物" or "オプション"の場合<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@銘柄タイプ：　@取得した銘柄タイプ<BR>
     * 　@　@　@先物／オプション区分：　@取得した先物／オプション区分<BR>
     * 　@　@　@部店：　@拡張アカウントマネージャ.getBranch(取得した部店ID)<BR>
     * 　@　@　@立会区分：　@取得した立会区分<BR>
     * 　@　@　@発注日：　@取得した発注日<BR>
     * <BR>
     * ５）　@訂正／取消可能チェック<BR>
     * 　@パラメータ.連続注文明細.訂正可能フラグ == true かつ<BR>
     * 　@パラメータ.連続注文明細.取消可能フラグ == trueの場合、<BR>
     * 　@以下の処理を行う。<BR>
     * 　@５－１）　@パラメータ.連続注文明細.商品区分により、<BR>
     * 　@　@処理を分岐しチェックを行う。<BR>
     * <BR>
     * 　@　@[パラメータ.連続注文明細.商品区分 ==<BR>
     * 　@　@　@"現物株式" or "信用取引"の場合]<BR>
     * 　@　@　@①@拡張株式注文マネージャ.validate注文訂正可能状態()メソッドを<BR>
     * 　@　@　@　@コールする。<BR>
     * 　@　@　@　@例外をスローした場合、<BR>
     * 　@　@　@　@パラメータ.連続注文明細.訂正可能フラグにfalseをセットする。<BR>
     * <BR>
     * 　@　@　@②拡張株式注文マネージャ.validate注文取消可能状態()メソッドを<BR>
     * 　@　@　@　@コールする。<BR>
     * 　@　@　@　@例外をスローした場合、<BR>
     * 　@　@　@　@パラメータ.連続注文明細.取消可能フラグにfalseをセットする。<BR>
     * <BR>
     * 　@　@　@　@[①@、②に指定する引数]<BR>
     * 　@　@　@　@　@注文：　@株式注文単位.getOrder()<BR>
     * <BR>
     * 　@　@　@③注文単位.取引コード（SONAR） == "立会外分売"の場合のみ、<BR>
     * 　@　@　@　@取引時間管理.validate注文受付可能()をコールする。<BR>
     * 　@　@　@　@例外をスローした場合、<BR>
     * 　@　@　@　@パラメータ.連続注文明細.取消可能フラグにfalseをセットする。<BR>
     * 　@　@　@　@※コールする前に、注文受付トランザクションに"取消"をセットする。<BR>
     * <BR>
     * 　@　@　@④注文単位.取引コード（SONAR） == "立会外分売"、かつ<BR>
     * 　@　@　@　@注文単位.注文経路区分 == "HOST"の場合は、<BR>
     * 　@　@　@　@パラメータ.連続注文明細.取消可能フラグにfalseをセットする。<BR>
     * <BR>
     * 　@　@[パラメータ.連続注文明細.商品区分 ==<BR>
     * 　@　@　@"先物" or "オプション"の場合]<BR>
     * 　@　@　@①@OP注文マネージャ.validate注文訂正可能状態()メソッドを<BR>
     * 　@　@　@　@コールする。<BR>
     * 　@　@　@　@例外をスローした場合、<BR>
     * 　@　@　@　@パラメータ.連続注文明細.訂正可能フラグにfalseをセットする。<BR>
     * <BR>
     * 　@　@　@②OP注文マネージャ.validate注文取消可能状態()メソッドを<BR>
     * 　@　@　@　@コールする。<BR>
     * 　@　@　@　@例外をスローした場合、<BR>
     * 　@　@　@　@パラメータ.連続注文明細.取消可能フラグにfalseをセットする。<BR>
     * <BR>
     * 　@　@　@　@[①@、②に指定する引数]<BR>
     * 　@　@　@　@　@注文：　@先物OP注文単位.getOrder()<BR>
     * <BR>
     * ６）　@連続注文可能チェック<BR>
     * 　@以下の条件のいずれかに該当する場合、<BR>
     * 　@パラメータ.連続注文明細.連続注文可能フラグにfalseをセットする。<BR>
     * 　@　@・連続注文マネージャImpl.validateトリガー注文設定To親注文()が<BR>
     * 　@　@　@例外をスロー<BR>
     * 　@　@・取引時間管理.validate連続注文受付可能()が例外をスロー<BR>
     * 　@　@・連続注文マネージャImpl.validate連続注文最大設定数()が<BR>
     * 　@　@　@例外をスロー<BR>
     * <BR>
     * 　@　@　@[validateトリガー注文設定To親注文()に指定する引数]<BR>
     * 　@　@　@　@親注文の注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * 　@　@　@[validate連続注文受付可能()に指定する引数]<BR>
     * 　@　@　@　@証券会社：　@取引カレンダコンテキスト.証券会社コードに該当する証券会社
     * 　@　@　@　@銘柄タイプ：　@取得した銘柄タイプ<BR>
     * 　@　@　@　@先物／オプション区分：　@取得した先物／オプション区分<BR>
     * 　@　@　@　@出来終了区分：<BR>
     * 　@　@　@　@　@[パラメータ.連続注文明細.商品区分 == "現物株式" or "信用取引"の場合]<BR>
     * 　@　@　@　@　@　@"出来終了（最終）"<BR>
     * 　@　@　@　@　@[パラメータ.連続注文明細.商品区分 == "先物" or "オプション"の場合]<BR>
     * 　@　@　@　@　@　@拡張アカウントマネージャ.getBranch(<BR>
     * 　@　@　@　@　@　@　@取得した部店ID).is夕場実施(取得した銘柄タイプ) == true and<BR>
     * 　@　@　@　@　@　@取得した立会区分 == "その他"の場合、"夕場前出来終了"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@以外、"出来終了（最終）"<BR>
     * <BR>
     * 　@　@　@[validate連続注文最大設定数()に指定する引数]<BR>
     * 　@　@　@　@親注文の注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ７）　@１）にて退避した値を取引カレンダコンテキストに<BR>
     * 　@再セットする。<BR>
     * @@param l_succOrderUnit - (連続注文明細)<BR>
     * 連続注文明細オブジェクト
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@exception WEB3BaseException
     * @@roseuid 43254BFB008B
     */
    public void setPossibleFlag(
        WEB3SuccOrderUnit l_succOrderUnit, 
        OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "setPossibleFlag(WEB3SuccOrderUnit, OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_succOrderUnit == null || l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!(l_orderUnit instanceof EqTypeOrderUnit) &&
            !(l_orderUnit instanceof IfoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@取引カレンダコンテキストから以下の値を 
        // 取得し、退避させる。 
        // ○注文受付トランザクション
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //注文受付トランザクション
        String l_strOldOrderAcceptTransaction = l_clendarContext.getOrderAcceptTransaction();

        //２）　@パラメータ.注文単位の型をinstanceofにて判別し、 
        //以下のいずれかの型にキャストする。 
        //・株式注文単位 
        //・先物OP注文単位

        //３）　@キャストした注文単位より、以下の値を取得する。
        //注文単位.注文カテゴリ  
        OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
        //注文単位.銘柄タイプ
        ProductTypeEnum l_productType = null;
        //注文単位.市場IDに該当する市場.市場コード
        String l_strMarketCode = null;
        //注文単位.発注日
        Date l_datOrderBizDate = null;
        //注文単位.銘柄IDに該当する銘柄.原資産銘柄コード
        //先物OP注文単位の場合のみ取得
        String l_strUnderlyingProductCode = null;
        //注文単位.先物／オプション区分
        String l_strFutureOptionDiv = null;
        //注文単位.取引コード（SONAR）
        String l_strSonarTradedCode = null;
        //注文単位.注文経路区分
        String l_strOrderRouteDiv = null;
        //注文単位.部店ID 
        long l_lngBranchId = 0;
        //注文単位.立会区分
        String l_strSessionType = null;

        try
        {
            if (l_orderUnit instanceof EqTypeOrderUnit)
            {
                EqtypeOrderUnitRow l_eqTypeOrderUnitRow = 
                    (EqtypeOrderUnitRow)((EqTypeOrderUnit)l_orderUnit).getDataSourceObject();
                //注文単位.銘柄タイプ
                l_productType = l_eqTypeOrderUnitRow.getProductType();
                //注文単位.部店ID
                l_lngBranchId = l_eqTypeOrderUnitRow.getBranchId();
                //注文単位.市場IDに該当する市場.市場コード
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_eqTypeOrderUnitRow.getMarketId()).getMarketCode();
                //先物／オプション区分は株式注文単位の場合、"DEFAULT"
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
                //注文単位.取引コード（SONAR）
                l_strSonarTradedCode = l_eqTypeOrderUnitRow.getSonarTradedCode();
                //注文単位.注文経路区分
                l_strOrderRouteDiv = l_eqTypeOrderUnitRow.getOrderRootDiv();
				//注文単位.発注日
				l_datOrderBizDate = 
					WEB3DateUtility.getDate(
						l_eqTypeOrderUnitRow.getBizDate(),
						WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                //注文単位.立会区分
                //株式注文単位の場合、NULL
                l_strSessionType = null;
            }
            else if (l_orderUnit instanceof IfoOrderUnit)
            {
                IfoOrderUnitRow l_ifoOrderUnitRow = 
                    (IfoOrderUnitRow)((IfoOrderUnit)l_orderUnit).getDataSourceObject();
                //注文単位.銘柄タイプ
                l_productType = l_ifoOrderUnitRow.getProductType();
                //注文単位.部店ID
                l_lngBranchId = l_ifoOrderUnitRow.getBranchId();
                //注文単位.市場IDに該当する市場.市場コード
                l_strMarketCode = 
                    new WEB3GentradeMarket(l_ifoOrderUnitRow.getMarketId()).getMarketCode();
                //注文単位.銘柄IDに該当する銘柄.原資産銘柄コード
                IfoProduct l_product = 
                    (IfoProduct)this.getProduct(l_ifoOrderUnitRow.getProductId(), l_productType);
                l_strUnderlyingProductCode = l_product.getUnderlyingProductCode();
                //先物／オプション区分は先物OP注文単位の場合、注文単位の設定値とする
                l_strFutureOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();
                //注文単位.取引コード（SONAR）
                l_strSonarTradedCode = l_ifoOrderUnitRow.getSonarTradedCode();
                //注文単位.注文経路区分
                l_strOrderRouteDiv = l_ifoOrderUnitRow.getOrderRootDiv();
                //注文単位.発注日
                l_datOrderBizDate = 
                    WEB3DateUtility.getDate(
                        l_ifoOrderUnitRow.getBizDate(),
                        WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                //注文単位.立会区分
                //先物OP注文単位の場合、注文単位の設定値とする
                l_strSessionType = l_ifoOrderUnitRow.getSessionType();
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        
        //４）　@訂正／取消可能共通チェック
        //注文単位の発注日が現在日時より算出した発注日より前の場合
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //拡張アカウントマネージャ.getBranch(取得した部店ID)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
        {
            try
            {
                //４－１）　@パラメータ.連続注文明細.商品区分 == "現物株式" or "信用取引"の場合
                if (WEB3CommodityDivDef.EQUITY.equals(l_succOrderUnit.commodityType)
                    || WEB3CommodityDivDef.MARGIN.equals(l_succOrderUnit.commodityType))
                {
                    //取引時間管理.validate閉局後訂正取消受付可能()メソッドをコールする
                    WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                        l_productType, l_strFutureOptionDiv);
                }
                //４－２）　@パラメータ.連続注文明細.商品区分 == "先物" or "オプション"の場合
                else if (WEB3CommodityDivDef.FUTURE.equals(l_succOrderUnit.commodityType)
                    || WEB3CommodityDivDef.OPTION.equals(l_succOrderUnit.commodityType))
                {
                    //[引数]
                    //銘柄タイプ：　@取得した銘柄タイプ
                    //先物／オプション区分：　@取得した先物／オプション区分
                    //部店：　@拡張アカウントマネージャ.getBranch(取得した部店ID)
                    //立会区分：　@取得した立会区分
                    //発注日：　@取得した発注日
                    WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                        l_productType,
                        l_strFutureOptionDiv,
                        l_branch,
                        l_strSessionType,
                        l_datOrderBizDate);
                }

            }
            catch (WEB3BaseException l_wbe)
            {
                log.debug("validate閉局後訂正取消受付可能()をコールする時、例外をスローした");
                l_succOrderUnit.changeFlag = false;
                l_succOrderUnit.cancelFlag = false;
            }
        }

        //５）　@訂正／取消可能チェック 
        if (l_succOrderUnit.changeFlag && l_succOrderUnit.cancelFlag)
        {
            //　@５－１）　@パラメータ.連続注文明細.商品区分により、 
            //  処理を分岐しチェックを行う。
            if (WEB3CommodityDivDef.EQUITY.equals(l_succOrderUnit.commodityType) ||
				WEB3CommodityDivDef.MARGIN.equals(l_succOrderUnit.commodityType))
            {
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                try
                {
                    l_orderManager.validateOrderForChangeability(l_orderUnit.getOrder());
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.debug("validate注文訂正可能状態()をコールする時、例外をスローした");
                    l_succOrderUnit.changeFlag = false;
                }
                try
                {
                    l_orderManager.validateOrderForCancellation(l_orderUnit.getOrder());
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.debug("validate注文取消可能状態()をコールする時、例外をスローした");
                    l_succOrderUnit.cancelFlag = false;
                }
                if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_strSonarTradedCode))
                {
                    l_clendarContext.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CANCEL);
                    try
                    {
                        WEB3GentradeTradingTimeManagement.validateOrderAccept();
                    }
                    catch (WEB3BaseException l_wbe)
                    {
                        log.debug("立会外分売・受付時間外");
                        l_succOrderUnit.cancelFlag = false;
                    }
                }
                if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_strSonarTradedCode) &&
                    WEB3OrderRootDivDef.HOST.equals(l_strOrderRouteDiv))
                {
                    log.debug("HOST入力の立会外分売注文");
                    l_succOrderUnit.cancelFlag = false;
                }
            }
            else if (WEB3CommodityDivDef.FUTURE.equals(l_succOrderUnit.commodityType) ||
				WEB3CommodityDivDef.OPTION.equals(l_succOrderUnit.commodityType))
            {
                WEB3OptionOrderManagerImpl l_orderManager =
                    (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                try
                {
                    l_orderManager.validateOrderChangePossibleStatus(l_orderUnit.getOrder());
                }
                catch (WEB3BaseException L_wbe)
                {
                    log.debug("validate注文訂正可能状態()をコールする時、例外をスローした");
                    l_succOrderUnit.changeFlag = false;
                }
                try
                {
                    l_orderManager.validateOrderCancelPossibleStatus(l_orderUnit.getOrder());
                }
                catch (WEB3BaseException l_wbe)
                {
                    log.debug("validate注文取消可能状態()をコールする時、例外をスローした");
                    l_succOrderUnit.cancelFlag = false;
                }
            }
        }

        //６）　@連続注文可能チェック
        try
        {
            WEB3ToSuccOrderManagerImpl l_orderManagerImpl = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            //連続注文マネージャImpl.validateトリガー注文設定To親注文()が例外をスロー 
            l_orderManagerImpl.validateTriggerOrderSettingToParentOrder(l_orderUnit);
            //取引時間管理.validate連続注文受付可能()が例外をスロー    
            String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
            WEB3GentradeInstitution l_institution =
                new WEB3GentradeInstitution(l_strInstitutionCode);

            //出来終了区分：
            String l_strExecutionEndType = null;
            //パラメータ.連続注文明細.商品区分 == "現物株式" or "信用取引"の場合
            if (WEB3CommodityDivDef.EQUITY.equals(l_succOrderUnit.commodityType)
                || WEB3CommodityDivDef.MARGIN.equals(l_succOrderUnit.commodityType))
            {
                //"出来終了（最終）"
                l_strExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
            }
            else if (WEB3CommodityDivDef.FUTURE.equals(l_succOrderUnit.commodityType)
                || WEB3CommodityDivDef.OPTION.equals(l_succOrderUnit.commodityType))
            {
                //拡張アカウントマネージャ.getBranch(取得した部店ID).is夕場実施(取得した銘柄タイプ) == true and
                //取得した立会区分 == "その他"の場合、"夕場前出来終了"
                if (l_branch.isEveningSessionEnforcemented(l_productType)
                    && WEB3Toolkit.isEquals(WEB3SessionTypeDef.OTHER, l_strSessionType))
                {
                    l_strExecutionEndType =
                        WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;
                }
                else
                {
                    //以外、"出来終了（最終）"
                    l_strExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
                }
            }

            WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
                l_institution,
                l_productType,
                l_strFutureOptionDiv,
                l_strExecutionEndType);
            //連続注文マネージャImpl.validate連続注文最大設定数()が例外をスロー
            l_orderManagerImpl.validateSuccOrderMaxQuantity(l_orderUnit);
        }
        catch (WEB3BaseException l_wbe)
        {
            l_succOrderUnit.succOrderFlag = false;
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        //７）　@１）にて退避した値を取引カレンダコンテキストに 
        //      再セットする。
        l_clendarContext.setOrderAcceptTransaction(l_strOldOrderAcceptTransaction);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get予約注文単位一覧)<BR>
     * 顧客が保持する予約注文の一覧を返却する。<BR>
     * <BR>
     * [戻り値のHashtableの構造]<BR>
     *  - key：　@親注文の注文ID + 銘柄タイプ<BR>
     *  - value：　@ArrayList。<BR>
     * 　@　@　@　@　@　@　@同一の"親注文の注文ID"を持つ予約注文単位を<BR>
     * 　@　@　@　@　@　@　@"親注文連番" 昇順でソートしたレコードを格納。<BR>
     * <BR>
     * １）　@Hashtableを生成する。<BR>
     * <BR>
     * ２）　@予約注文を取得する。<BR>
     * 　@２－１）　@引数.商品区分一覧に"現物株式" or "信用取引"が含まれる場合、<BR>
     * 　@　@　@　@　@　@以下の条件にて、株式予約注文単位テーブルを検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@口座ID = パラメータ.口座ID<BR>
     * <BR>
     * 　@　@[ソート]<BR>
     * 　@　@　@親注文の注文ID 昇順,<BR>
     * 　@　@　@親注文内連番 昇順<BR>
     * <BR>
     * 　@２－２）　@引数.商品区分一覧に"先物" or "オプション"が含まれる場合、<BR>
     * 　@　@　@　@　@　@以下の条件にて、先物OP予約注文単位テーブルを検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@口座ID = パラメータ.口座ID<BR>
     * 　@　@[ソート]<BR>
     * 　@　@　@親注文の注文ID 昇順,<BR>
     * 　@　@　@親注文内連番 昇順<BR>
     * <BR>
     * ３）　@２）の戻り値 != nullの場合、以下の処理を実施する。<BR>
     * 　@３－１）ArrayListを生成する。<BR>
     * <BR>
     * 　@３－２）２）の戻り値の要素数分、以下の処理を繰り返す。<BR>
     * 　@　@３－２－１）　@ArrayListに処理対象の要素を追加する。<BR>
     * <BR>
     * 　@　@３－２－２）　@以下の条件のいずれかに該当する場合、<BR>
     * 　@　@　@３－２－３）以降の処理を実施する。<BR>
     * 　@　@　@①@現在処理している要素が最後の要素の場合<BR>
     * 　@　@　@②処理対象の要素.親注文の注文ID + 処理対象の要素.銘柄タイプ != <BR>
     * 　@　@　@　@次の要素(index + 1).親注文の注文ID + 処理対象の要素.銘柄タイプの場合<BR>
     * <BR>
     * 　@　@３－２－３）　@Hashtable.put()メソッドをコールする。<BR>
     * 　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@key：　@処理対象の要素.親注文の注文ID +<BR>
     * 　@　@　@　@　@処理対象の要素.銘柄タイプ<BR>
     * 　@　@　@　@value：　@ArrayList<BR>
     * <BR>
     * 　@　@３－２－４）　@ArrayList.clear()メソッドをコールする。<BR>
     * <BR>
     * ４）　@Hashtableを返却する。<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID
     * @@param l_strCommodityTypeList - (商品区分一覧)<BR>
     * 商品区分一覧
     * @@return Hashtable
     * @@exception WEB3BaseException
     * @@roseuid 431F76F500DF
     */
    public Hashtable getRsvOrderUnitList(long l_lngAccountId, String[] l_strCommodityTypeList) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getRsvOrderUnitList(long, String[])";
        log.entering(STR_METHOD_NAME);

        //１）　@Hashtableを生成する。
        Hashtable l_htRsvOrderUnit = new Hashtable();
        
        //[条件] 
        //  口座ID = パラメータ.口座ID 
        //  [ソート] 
        //  親注文の注文ID 昇順, 
        //  親注文内連番 昇順
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");

        List l_lstWhere = new ArrayList();
        l_lstWhere.add(new Long(l_lngAccountId));
        Object[] l_objWhere = new Object[l_lstWhere.size()];
        l_lstWhere.toArray(l_objWhere);

        String l_strOrderBy = " parent_order_id asc, serial_no_in_parent asc ";
        List l_lstRecords = null;

        boolean l_blnIsEq = false;
        boolean l_blnIsIfo = false;
        try
        {
            int l_intCommodityTypeCnt = 0;
            if (l_strCommodityTypeList !=null)
            {
                l_intCommodityTypeCnt = l_strCommodityTypeList.length;
            }

            for (int i = 0; i < l_intCommodityTypeCnt; i++)
            {
                //２）予約注文を取得する。
                //２－１）　@引数.商品区分一覧に"現物株式" or "信用取引"が含まれる場合
                //株式予約注文単位テーブルを検索する
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityTypeList[i])
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityTypeList[i]))
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_lstRecords = l_queryProcessor.doFindAllQuery(
                        RsvEqOrderUnitRow.TYPE,
                        l_sbWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_objWhere);

                    l_blnIsEq = true;
                    break;
                }
                
                //　@２－２）　@引数.商品区分一覧に"先物" or "オプション"が含まれる場合、
                //先物OP予約注文単位テーブルを検索する。
                else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityTypeList[i])
                    || WEB3CommodityDivDef.OPTION.equals(l_strCommodityTypeList[i]))
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_lstRecords = l_queryProcessor.doFindAllQuery(
                        RsvIfoOrderUnitRow.TYPE,
                        l_sbWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_objWhere);

                    l_blnIsIfo = true;
                    break;
                }
            }
            

        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        //２－１）の戻り値 == nullの場合
        if (l_lstRecords == null || l_lstRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            //４）　@Hashtableを返却する
            return l_htRsvOrderUnit;
        }

        //３）　@２）の戻り値 != nullの場合、以下の処理を実施する
        //３－１）ArrayListを生成する。 
        ArrayList l_records = new ArrayList();
        //３－２）２）の戻り値の要素数分、以下の処理を繰り返す。
        for (int i = 0; i < l_lstRecords.size(); i ++)
        {
            //３－２－１）　@ArrayListに処理対象の要素を追加する。
            l_records.add(l_lstRecords.get(i));

            //３－２－２）　@以下の条件のいずれかに該当する場合
            //①@現在処理している要素が最後の要素の場合
            //②処理対象の要素.親注文の注文ID + 処理対象の要素.銘柄タイプ !=  
            //  次の要素(index + 1).親注文の注文ID + 処理対象の要素.銘柄タイプの場合
            String l_strKey = null;
            String l_strNextKey = null;

            if (l_blnIsEq)
            {
                l_strKey =
                    WEB3TriggerOrderUtility.createKey(
                        ((RsvEqOrderUnitRow)l_lstRecords.get(i)).getParentOrderId(),
                        ((RsvEqOrderUnitRow)l_lstRecords.get(i)).getProductType());
            }
            else if (l_blnIsIfo)
            {
                l_strKey =
                    WEB3TriggerOrderUtility.createKey(
                        ((RsvIfoOrderUnitRow)l_lstRecords.get(i)).getParentOrderId(),
                        ((RsvIfoOrderUnitRow)l_lstRecords.get(i)).getProductType());
            }

            l_strNextKey = l_strKey;
            if (i < (l_lstRecords.size() - 1))
            {
                if (l_blnIsEq)
                {
                    l_strNextKey = WEB3TriggerOrderUtility.createKey(
                        ((RsvEqOrderUnitRow)l_lstRecords.get(i + 1)).getParentOrderId(),
                        ((RsvEqOrderUnitRow)l_lstRecords.get(i + 1)).getProductType());
                }
                else if (l_blnIsIfo)
                {
                    l_strNextKey = WEB3TriggerOrderUtility.createKey(
                        ((RsvIfoOrderUnitRow)l_lstRecords.get(i + 1)).getParentOrderId(),
                        ((RsvIfoOrderUnitRow)l_lstRecords.get(i + 1)).getProductType());
                }
            }

            if ((i == l_lstRecords.size() - 1) || (!l_strKey.equals(l_strNextKey)))
            {
                //３－２－３）　@Hashtable.put()メソッドをコールする。 
                //  [put()に指定する引数] 
                //key：　@処理対象の要素.親注文の注文ID + 
                //処理対象の要素.銘柄タイプ 
                //value：　@ArrayList             }
                ArrayList l_groupRecords = new ArrayList();
                l_groupRecords.addAll(l_records);
                
                l_htRsvOrderUnit.put(l_strKey, l_groupRecords);
                
                //３－２－４）　@ArrayList.clear()メソッドをコールする。
                l_records.clear();
            }
        }

        log.exiting(STR_METHOD_NAME);
        //４）　@Hashtableを返却する
        return l_htRsvOrderUnit;
    }

    /**
     * (get商品区分)<BR>
     * 引数の注文種別より商品区分を判別し、<BR>
     * 返却する。<BR>
     * <BR>
     * パラメータ.注文種別が、<BR>
     * 　@["現物買注文" or "現物売注文"の場合]<BR>
     * 　@　@"現物株式"を返却する。<BR>
     * <BR>
     * 　@["信用新規買建注文" or<BR>
     * 　@　@"信用新規売建注文" or<BR>
     * 　@　@"信用買建返済注文（売返済）" or<BR>
     * 　@　@"信用売建返済注文（買返済）" or<BR>
     * 　@　@"信用現引注文" or<BR>
     * 　@　@"信用現渡注文"の場合]<BR>
     * 　@　@"信用取引"を返却する。<BR>
     * <BR>
     * 　@["先物新規買建注文" or<BR>
     * 　@　@"先物新規売建注文" or<BR>
     * 　@　@"先物売建買返済注文（買返済）" or<BR>
     * 　@　@"先物買建売返済注文（売返済）"の場合]<BR>
     * 　@　@"先物"を返却する。<BR>
     * <BR>
     * 　@["OP新規買建注文" or<BR>
     * 　@　@"OP新規売建注文" or<BR>
     * 　@　@"OP売建買返済注文（買返済）" or<BR>
     * 　@　@"OP買建売返済注文（売返済）"の場合]<BR>
     * 　@　@"オプション"を返却する。<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 431F90BF00DF
     */
    public String getCommodityDiv(OrderTypeEnum l_orderType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCommodityDiv(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_orderType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        String l_strCommodityDiv = null;

        //["現物買注文" or "現物売注文"の場合
        if ((OrderTypeEnum.EQUITY_BUY.equals(l_orderType)) ||
            (OrderTypeEnum.EQUITY_SELL.equals(l_orderType)))
        {
            //"現物株式"を返却する
            l_strCommodityDiv = WEB3CommodityDivDef.EQUITY;
        }
        //"信用新規買建注文" or 
        //"信用新規売建注文" or 
        //"信用買建返済注文（売返済）" or 
        //"信用売建返済注文（買返済）" or 
        //"信用現引注文" or 
        //"信用現渡注文"の場合
        else if ((OrderTypeEnum.MARGIN_LONG.equals(l_orderType)) ||
            (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType)) ||
            (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType)) ||
            (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType)) ||
            (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType)) ||
            (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType)))
        {
            //"信用取引"を返却する
            l_strCommodityDiv = WEB3CommodityDivDef.MARGIN;
        }
        //"先物新規買建注文" or 
        //"先物新規売建注文" or 
        //"先物売建買返済注文（買返済）" or 
        //"先物買建売返済注文（売返済）"の場合
        else if ((OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType)))
        {
            //"先物"を返却する
            l_strCommodityDiv = WEB3CommodityDivDef.FUTURE;
        }
        //"OP新規買建注文" or 
        //"OP新規売建注文" or 
        //"OP売建買返済注文（買返済）" or 
        //"OP買建売返済注文（売返済）"の場合
        else if ((OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType)) ||
            (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType)))
        {
            //"オプション"を返却する
            l_strCommodityDiv = WEB3CommodityDivDef.OPTION;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strCommodityDiv;
    }

	/**
	 * （get商品区分のオーバーロード） <BR>
	 * 引数の連続注文取引区分に該当する商品区分を判別し、 <BR>
	 * 返却する。 <BR>
	 * <BR>
	 * パラメータ.連続注文取引区分が、 <BR>
	 * 　@["買付（前提注文）" or "買付" <BR>
	 * 　@　@or "売付（前提注文）" or "売付（既存残）"の場合] <BR>
	 * 　@　@"現物株式"を返却する。 <BR>
	 * <BR>
	 * 　@["信用新規建（前提注文）" or <BR>
	 * 　@　@"信用新規建" or <BR>
	 * 　@　@"信用返済（前提注文）" or <BR>
	 * 　@　@"信用返済（既存残）" or <BR>
	 * 　@　@"現引現渡（前提注文）" or <BR>
	 * 　@　@"現引現渡（既存残）"の場合] <BR>
	 * 　@　@"信用取引"を返却する。 <BR>
	 * <BR>
	 * 　@["先物新規建（前提注文）" or <BR>
	 * 　@　@"先物新規建" or <BR>
	 * 　@　@"先物返済（前提注文）" or <BR>
	 * 　@　@"先物返済（既存残）"の場合] <BR>
	 * 　@　@"先物"を返却する。 <BR>
	 * <BR>
	 * 　@["OP新規建（前提注文）" or <BR>
	 * 　@　@"OP新規建" or <BR>
	 * 　@　@"OP返済（前提注文）" or <BR>
	 * 　@　@"OP返済（既存残）"の場合] <BR>
	 * 　@　@"OP"を返却する。 <BR>
	 * @@param succTradingTypeList - (連続注文取引区分一覧)<BR>
	 * 連続注文取引区分一覧
	 * @@return String
	 * @@exception WEB3BaseException
	 * @@roseuid 431F90BF00DF
	 */
	public String getCommodityDiv(String succTradingTypeList) 
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getCommodityDiv(succTradingTypeList)";
		log.entering(STR_METHOD_NAME);

		if (succTradingTypeList == null)
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}

		String l_strCommodityDiv = null;

		//"買付（前提注文）" or 
		//"買付" or 
		//"売付（前提注文）" or 
		//"売付（既存残）" の場合
		if (WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
            WEB3ReserveOrderTradingTypeDef.BUY.equals(succTradingTypeList) ||
            WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
            WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER.equals(succTradingTypeList))
		{
			//"現物株式"を返却する
			l_strCommodityDiv = WEB3CommodityDivDef.EQUITY;
		}
		//"信用新規建（前提注文）" or
		//"信用新規建" or
		//"信用返済（前提注文）" or
		//"信用返済（既存残）" or
		//"現引現渡（前提注文）" or
		//"現引現渡（既存残）"の場合
		else if (WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER.equals(succTradingTypeList))
		{
			//"信用取引"を返却する
			l_strCommodityDiv = WEB3CommodityDivDef.MARGIN;
		}
		//"先物新規建（前提注文）" or 
		//"先物新規建" or 
		//"先物返済（前提注文）" or 
		//"先物返済（既存残）"の場合
		else if (WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(succTradingTypeList))
		{
			//"先物"を返却する
			l_strCommodityDiv = WEB3CommodityDivDef.FUTURE;
		}
		
		//"OP新規建（前提注文）" or 
		//"OP新規建" or 
		//"OP返済（前提注文）" or 
		//"OP返済（既存残）"の場合
		else if (WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.OPEN_OP.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(succTradingTypeList) ||
                  WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(succTradingTypeList))
		{
			//"オプション"を返却する
			l_strCommodityDiv = WEB3CommodityDivDef.OPTION;
		}

		log.exiting(STR_METHOD_NAME);
		return l_strCommodityDiv;
	}

    /**
     * （get受付時間区分） <BR>
     * 引数の注文カテゴリ等より取引時間テーブルreadに使用する受付時間区分を判別し、<BR>
     * 返却する。 <BR>
     * <BR>
     * パラメータ.注文カテゴリ、取引コード（SONAR）の値により、以下の通り返却する。<BR>
     * 　@　@["現引・現渡注文"の場合]<BR>
     * 　@　@　@"現引・現渡"を返却。<BR>
     * 　@　@["現物注文"の場合]<BR>
     * 　@　@　@パラメータ.取引コード（SONAR）=="立会外分売"の場合は、"立会外分売"を返却。<BR>
     * 　@　@　@上記以外は、"株・信用"を返却。<BR>
     * 　@　@["新規建注文" or "返済注文"の場合]<BR>
     * 　@　@　@"株・信用"を返却。<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@"株価指数先物OP"を返却。<BR>
     * @@param l_orderCateg - (注文カテゴリ)<BR>
     * 注文カテゴリ<BR>
     * @@param l_strSonarTradedCode - (取引コード（SONAR）)<BR>
     * 取引コード（SONAR）<BR>
     * @@return String
     * @@exception WEB3SystemLayerException
     * @@roseuid 431F90BF00DF
     */
    public String getTradingTimeType(OrderCategEnum l_orderCateg, String l_strSonarTradedCode) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradingTimeType(OrderCategEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_orderCateg == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        String l_strOrderCateg = null;
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
        {
            l_strOrderCateg = WEB3TradingTimeTypeDef.SWAP;
        }
        else if (OrderCategEnum.ASSET.equals(l_orderCateg))
        {
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_strSonarTradedCode))
            {
                l_strOrderCateg = WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET;
            }
            else
            {
                l_strOrderCateg = WEB3TradingTimeTypeDef.EQUITY;
            }
        }
        else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg) ||
            OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
        {
            l_strOrderCateg = WEB3TradingTimeTypeDef.EQUITY;
        }
        else
        {
            l_strOrderCateg = WEB3TradingTimeTypeDef.INDEX_FUTURE_OP;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strOrderCateg;
    }

    /**
     * (get銘柄)<BR>
     * 引数の証券会社、銘柄コード、商品区分に<BR>
     * 該当する銘柄を取得し、返却する。<BR>
     * <BR>
     * １）　@パラメータ.商品区分により、銘柄の取得方法@を<BR>
     * 　@分岐する。<BR>
     * 　@[パラメータ.商品区分 == ("現物株式" or "信用取引")の場合]<BR>
     * 　@　@拡張プロダクトマネージャ.getProduct()にて株式銘柄を取得し、<BR>
     * 　@　@返却する。<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@先物OPプロダクトマネージャ.get銘柄()にて先物OP銘柄を<BR>
     * 　@　@取得し、返却する。<BR>
     * <BR>
     * 　@[getProduct()、get銘柄()に指定する引数]<BR>
     * 　@　@arg0：　@パラメータ.証券会社<BR>
     * 　@　@arg1：　@パラメータ.銘柄コード<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード
     * @@param l_strCommodityDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     * 
     * @@return Product
     * @@exception WEB3BaseException
     * @@roseuid 4325512901D3
     */
    public Product getProduct(
        WEB3GentradeInstitution l_institution, 
        String l_strProductCode, 
        String l_strCommodityDiv)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getProduct(WEB3GentradeInstitution, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_strProductCode == null || l_strCommodityDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Product l_product = null;

        try
        {
            //パラメータ.商品区分 == ("現物株式" or "信用取引")の場合
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) ||
                (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv)))
            {
                //拡張プロダクトマネージャ.getProduct()にて株式銘柄を取得し、 
                //返却する。 
                TradingModule l_tradingModule = 
                    GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);      
                WEB3EquityProductManager l_productMgr = 
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                l_product = l_productMgr.getProduct(l_institution, l_strProductCode);
            }
            //上記以外の場合
            else
            {
                //先物OPプロダクトマネージャ.get銘柄()にて先物OP銘柄を 
                //取得し、返却する。             
                TradingModule l_tradingModule = 
                    GtlUtils.getTradingModule(ProductTypeEnum.IFO);      
                WEB3IfoProductManagerImpl l_productMgr = 
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                l_product = l_productMgr.getIfoProduct(l_institution, l_strProductCode);
            }
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コード:[" + l_strProductCode + "]に該当する株式銘柄が取得できません。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_product;
    }

    /**
     * (get銘柄)<BR>
     * 引数の銘柄タイプ、銘柄IDに該当する銘柄を<BR>
     * 取得し、返却する。<BR>
     * <BR>
     * １）　@パラメータ.銘柄タイプにより、銘柄の取得方法@を<BR>
     * 　@分岐する。<BR>
     * 　@[パラメータ.銘柄タイプ == ProductTypeEnum.株式の場合]<BR>
     * 　@　@拡張プロダクトマネージャ.getProduct()にて株式銘柄を取得し、<BR>
     * 　@　@返却する。<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@先物OPプロダクトマネージャ.getProduct()にて先物OP銘柄を<BR>
     * 　@　@取得し、返却する。<BR>
     * <BR>
     * 　@[全てのgetProduct()に指定する引数]<BR>
     * 　@　@arg0：　@パラメータ.銘柄ID<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ
     * @@return Product
     * @@exception WEB3BaseException
     * @@roseuid 431FA2A9036F
     */
    public Product getProduct(
        long l_lngProductId, 
        ProductTypeEnum l_productType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProduct(long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        Product l_product = null;

        if (l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //パラメータ.銘柄タイプ == ProductTypeEnum.株式の場合
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                //拡張プロダクトマネージャ.getProduct()にて株式銘柄を取得し、 
                //返却する。 
                TradingModule l_tradingModule = 
                    GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);      
                WEB3EquityProductManager l_productMgr = 
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                l_product = l_productMgr.getProduct(l_lngProductId);
            }
            //上記以外の場合
            else
            {
                //先物OPプロダクトマネージャ.getProduct()にて先物OP銘柄を 
                //取得し、返却する。 
                TradingModule l_tradingModule = 
                    GtlUtils.getTradingModule(ProductTypeEnum.IFO);      
                WEB3IfoProductManagerImpl l_productMgr = 
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                l_product = l_productMgr.getProduct(l_lngProductId);
            }
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄ID:[" + l_lngProductId + "]に該当する株式銘柄が取得できません。",
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_product;
    }

    /**
     * (get執行条件（PR層）)<BR>
     * 引数の執行条件に該当する執行条件（PR層）を返却する。<BR>
     * <BR>
     * １）　@型の判別<BR>
     * 　@パラメータ.注文単位の型をinstanceofにて判別し、<BR>
     * 　@以下のいずれかにキャストする。<BR>
     * 　@・株式注文単位<BR>
     * 　@・先物OP注文単位<BR>
     * <BR>
     * ２）　@パラメータ.注文単位の型が株式注文単位の場合<BR>
     * 　@２－１）　@株式注文単位にキャストする。 <BR>
     * 　@２－２）　@拡張株式注文マネージャ.get執行条件（SONAR）()をコールし、<BR>
     * 　@　@　@　@　@　@戻り値を返却する。 <BR>
     * <BR>
     * 　@　@　@　@　@　@[get執行条件（SONAR）()に指定する引数]<BR>
     * 　@　@　@　@　@　@執行条件：　@株式注文単位.執行条件 <BR>
     * <BR>
     * ３）　@パラメータ.注文単位の型が先物OP注文単位の場合<BR>
     * 　@３－１）　@先物OP注文単位にキャストする。 <BR>
     * 　@３－２）　@先物OPデータアダプタ.get執行条件（PR層）()をコールし、<BR>
     * 　@　@　@　@　@　@戻り値を返却する。 <BR>
     * <BR>
     * 　@　@　@　@　@　@[get執行条件（PR層）()に指定する引数]<BR>
     * 　@　@　@　@　@　@執行条件：　@先物OP注文単位.執行条件<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位 
     * @@return String
     * @@exception WEB3BaseException
     */
    public String getExecutionConditionTypeByPr(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionConditionTypeByPr(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //    １）　@型の判別
        //   　@パラメータ.注文単位の型をinstanceofにて判別し
        //   　@以下のいずれかにキャストする。
        //   　@・株式注文単位
        //   　@・先物OP注文単位
        if (!(l_orderUnit instanceof EqTypeOrderUnit)
            && !(l_orderUnit instanceof IfoOrderUnit))
        {
            log.debug("パラメータ.注文単位の型不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //   ２）　@パラメータ.注文単位の型が株式注文単位の場合
        //   　@２－１）　@株式注文単位にキャストする。
        //   　@２－２）　@拡張株式注文マネージャ.get執行条件（SONAR）()をコールし
        //   　@　@　@　@　@　@戻り値を返却する。
        //   　@　@　@　@　@　@[get執行条件（SONAR）()に指定する引数]
        //   　@　@　@　@　@　@執行条件：　@株式注文単位.執行条件

        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(
                    ProductTypeEnum.EQUITY).getOrderManager();
            String l_strExecCondTypeSonar =
                l_orderManager.getExecutionConditionTypeSonar(
                    l_eqtypeOrderUnitRow.getExecutionConditionType());

            log.exiting(STR_METHOD_NAME);
            return l_strExecCondTypeSonar;
        }

        //
        //   ３）　@パラメータ.注文単位の型が先物OP注文単位の場合
        //   　@３－１）　@先物OP注文単位にキャストする。
        //   　@３－２）　@先物OPデータアダプタ.get執行条件（PR層）()をコールし
        //   　@　@　@　@　@　@戻り値を返却する
        //   　@　@　@　@　@　@[get執行条件（PR層）()に指定する引数]
        //   　@　@　@　@　@　@執行条件：　@先物OP注文単位.執行条件
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            String l_strExecutionCondByPr =
                WEB3IfoDataAdapter.getExecutionCondByPr(
                    l_ifoOrderUnitRow.getExecutionConditionType());

            log.exiting(STR_METHOD_NAME);
            return l_strExecutionCondByPr;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get処理状況)<BR>
     * 注文単位に該当する処理状況区分を返却する。<BR>
     * <BR>
     * 　@※当メソッドを呼ぶ際、以下点に注意する。<BR>
     * 　@　@　@予約注文が”発注済”の場合、株式注文単位Impl、<BR>
     * 　@　@　@または先物OP注文単位Implをパラメータに設定する。<BR>
     * <BR>
     * １）　@パラメータ.注文単位の型が予約注文単位の場合、<BR> 
     *　@　@　@　@以下の処理を実施する。<BR> 
     *　@２－１）　@以下条件に該当する戻り値を返却する。<BR> 
     *　@　@　@　@　@　@予約注文単位.注文有効状態 == "クローズ"の場合<BR> 
     *　@　@　@　@　@　@　@　@　@予約注文単位.注文状態 == "発注済（取消注文）"の場合<BR> 
     *　@　@　@　@　@　@　@　@　@　@　@"0003：全部取消完了"をセット<BR> 
     *　@　@　@　@　@　@　@　@　@上記以外<BR> 
     *　@　@　@　@　@　@　@　@　@　@　@"3020：無効"をセット<BR> 
     *　@　@　@　@　@　@上記以外<BR> 
     *　@　@　@　@　@　@　@　@"3000：予約"をセット<BR>
     * <BR>
     * ２）　@パラメータ.注文単位の型が株式注文単位の場合、<BR>
     * 　@株式注文単位にキャストし、以下の処理を実施する。<BR>
     * 　@　@２－１）　@株式データアダプタ.get処理状況区分()<BR> 
     *　@　@　@　@　@　@　@メソッドをコールし、戻り値を返却する。<BR> 
     * <BR>
     * ３）　@パラメータ.注文単位の型が先物OP注文単位の場合、<BR>
     * 　@先物OP注文単位にキャストし、以下の処理を実施する。<BR>
     * 　@　@３－１）　@先物OPデータアダプタ.get処理状況区分()<BR> 
     *　@　@　@　@　@　@　@メソッドをコールし、戻り値を返却する。<BR> 

     * <BR> 
     * 　@[全てのget処理状況区分()に指定する引数]<BR>
     * 　@　@注文単位：　@キャストした注文単位<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return String
     * @@exception WEB3BaseException
     * @@roseuid 431FAAC70321
     */
    public String getTransactionState(OrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactionState(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!(l_orderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl) &&
            !(l_orderUnit instanceof EqTypeOrderUnit) &&
            !(l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl) &&
            !(l_orderUnit instanceof IfoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strTransactionState = null;

        //１）　@パラメータ.注文単位の型が予約注文単位の場合
        if (l_orderUnit instanceof WEB3ToSuccEqTypeOrderUnitImpl
            || l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl)
        {
            //予約注文単位.注文有効状態 == "クローズ"の場合
            if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()))
            {
                //予約注文単位.注文状態 == "発注済（取消注文）"の場合
                if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
                {
                    //"0003：全部取消完了"をセット
                    l_strTransactionState = WEB3ToSuccTransactionStateDef.ALL_CANCELED;
                }
                else
                {
                    //"3020：無効"をセット 
                    l_strTransactionState = WEB3ToSuccTransactionStateDef.INEFFECTIVE;
                }
            }
            else
            {
                //"3000：予約"をセット
                l_strTransactionState = WEB3ToSuccTransactionStateDef.RESERVATION;
            }            
        }

        //２）パラメータ.注文単位の型が株式注文単位の場合
        else if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            //２－１）　@株式データアダプタ.get処理状況区分()
            //          メソッドをコールし、戻り値を返却する。
            l_strTransactionState = 
                WEB3EquityDataAdapter.getTransactionStatusType((EqTypeOrderUnit)l_orderUnit);
            
        }

        //３）パラメータ.注文単位の型が先物OP注文単位の場合
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            //３－１）　@先物OPデータアダプタ.get処理状況区分()
            //          メソッドをコールし、戻り値を返却する。
            l_strTransactionState = WEB3IfoDataAdapter.getTransactionStatusType((IfoOrderUnit)l_orderUnit);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strTransactionState;
    }

    /**
     * (get注文単位)<BR>
     * 引数の注文単位ID、銘柄タイプに該当する注文単位を<BR>
     * 取得し、返却する。<BR>
     * <BR>
     * １）　@パラメータ.銘柄タイプにより、取得先を分岐する。<BR>
     * <BR>
     * 　@[パラメータ.銘柄タイプ == "株式"の場合]<BR>
     * 　@　@拡張株式注文マネージャ.getOrderUnit()をコールする。<BR>
     * <BR>
     * 　@[パラメータ.銘柄タイプ == "先物・オプション"の場合<BR>
     * 　@　@OP注文マネージャ.getOrderUnit()をコールする。<BR>
     * <BR>
     * 　@[各getOrderUnit()に指定する引数]<BR>
     * 　@　@arg0：　@パラメータ.注文単位ID<BR>
     * <BR>
     * ２）　@取得した注文単位を返却する。<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ
     * @@return OrderUnit
     * @@exception WEB3BaseException
     * @@roseuid 4325494A02DD
     */
    public OrderUnit getOrderUnit(
        long l_lngOrderUnitId, 
        ProductTypeEnum l_productType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getOrderUnit(long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        OrderUnit l_orderUnit = null;

        try
        {
            //パラメータ.銘柄タイプ == ProductTypeEnum.株式の場合
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3EquityOrderManager l_orderManager =
                    (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            //パラメータ.銘柄タイプ == "先物・オプション"の場合 
            else if (ProductTypeEnum.IFO.equals(l_productType))
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3OptionOrderManagerImpl l_orderManager =
                    (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (get注文単位)<BR>
     * 引数の注文ID、商品区分に該当する注文単位を<BR>
     * 取得し、返却する。<BR>
     * <BR>
     * １）　@パラメータ.商品区分により、取得先を分岐する。<BR>
     * <BR>
     * 　@[パラメータ.商品区分 == ("現物株式" or "信用取引")の場合]<BR>
     * 　@　@拡張株式注文マネージャ.getOrderUnits()をコールする。<BR>
     * <BR>
     * 　@[パラメータ.商品区分 == ("先物" or "オプション")の場合<BR>
     * 　@　@OP注文マネージャ.getOrderUnits()をコールする。<BR>
     * <BR>
     * 　@[各getOrderUnit()に指定する引数]<BR>
     * 　@　@arg0：　@パラメータ.注文ID<BR>
     * <BR>
     * ２）　@１）の戻り値の0番目の要素を返却する。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID
     * @@param l_strCommodityDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     * 
     * @@return OrderUnit
     * @@exception WEB3BaseException
     * @@roseuid 4326327002F8
     */
    public OrderUnit getOrderUnit(long l_lngOrderId, String l_strCommodityDiv)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getOrderUnit(long, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strCommodityDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        OrderUnit[] l_orderUnits = null;

        //パラメータ.商品区分 == ("現物株式" or "信用取引")の場合
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) ||
            (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv)))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        }
        //パラメータ.商品区分 == ("先物" or "オプション")の場合
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv) ||
            (WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv)))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        }

        if ((l_orderUnits == null) || (l_orderUnits.length < 1))
        {
            log.error("注文単位を取得しない。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnits[0];
    }

    /**
     * (get注文単位一覧)<BR>
     * 引数の銘柄タイプに該当する注文単位の一覧を<BR>
     * 取得し、返却する。<BR>
     * <BR>
     * １）　@パラメータ.銘柄タイプにより処理を分岐する。<BR>
     * 　@[パラメータ.銘柄タイプ == ProductTypeEnum.株式の場合]<BR>
     * 　@　@拡張株式注文マネージャ.get注文単位一覧()をコールする。<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@OP注文マネージャ.get注文単位一覧()をコールする。<BR>
     * <BR>
     * 　@[各get注文単位一覧()に指定する引数]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@銘柄タイプ：　@パラメータ.銘柄タイプ<BR>
     * 　@　@検索条件文字列：　@パラメータ.検索条件文字列<BR>
     * 　@　@検索条件データコンテナ：　@パラメータ.検索条件データコンテナ<BR>
     * 　@　@ソート条件：　@パラメータ.ソート条件<BR>
     * <BR>
     * ２）１）の戻り値を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ（ProductTypeEnumオブジェクト）
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件 文字列
     * @@param l_strQueryContainers - (検索条件データコンテナ)<BR>
     * 検索条件
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件
     * @@return List
     * @@exception WEB3BaseException
     * @@roseuid 432630790078
     */
    public List getOrderUnitList(
        WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strQueryString, 
        String[] l_strQueryContainers, 
        String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getOrderUnitList(WEB3GentradeSubAccount, " +
            "ProductTypeEnum, String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || 
            l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        List l_lstOrderUnit = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //パラメータ.銘柄タイプ == ProductTypeEnum.株式の場合
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_lstOrderUnit = l_orderManager.getOrderUnits(
                l_subAccount,
                l_productType,
                l_strQueryString,
                l_strQueryContainers,
                l_strSortCond);
        }
        //上記以外の場合
        else
        {
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            l_lstOrderUnit = l_orderManager.getOrderUnits(
                l_subAccount,
                l_productType,
                l_strQueryString,
                l_strQueryContainers,
                l_strSortCond);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lstOrderUnit;
    }
    
    /**
     * (get連続注文発注状況区分)<BR>
     * 引数の銘柄タイプに該当する注文単位の一覧を<BR>
     * 取得し、返却する。<BR>
     * <BR>
     * パラメータ.注文単位より <BR>
     * PR層で使用する連続注文の発注状況区分を返却する。 <BR>
     * <BR>
     * １）　@待機@中の判定 <BR>
     * 　@　@　@注文単位．注文有効状態＝"オープン"の場合、"待機@中"を返却する。 <BR>
     * <BR>
     * ２）　@発注完了の判定 <BR>
     * 　@　@　@注文単位.注文状態＝"3：発注済（新規注文）"の場合、"発注完了"を返却する。 <BR>
     * <BR>
     * ３）　@発注審査エラーの判定 <BR>
     * 　@　@　@注文単位.注文状態＝"6：発注失敗（新規注文）"の場合、 <BR>
     * 　@　@　@"発注審査エラー"を返却する。 <BR>
     * <BR>
     * ４）　@上記以外の場合、"その他"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位
     * @@return String
     * @@exception WEB3BaseException
     */
    public String getToSuccTriggerOrderStatusType(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getToSuccTriggerOrderStatusType(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTriggerOrderStatus = null;
        
        //１）　@待機@中の判定
        if (OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }
        //２）　@発注完了の判定
        else if (OrderStatusEnum.ORDERED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_COMPLETE;
        }
        //３）　@発注審査エラーの判定
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR;
        }
        //４）　@上記以外の場合、"その他"を返却する。
        else 
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.OTHER;
        }
        
        log.debug("連続注文発注状況区分 = " + l_strTriggerOrderStatus);
        log.exiting(STR_METHOD_NAME);
        return l_strTriggerOrderStatus;        
    }

}
@
