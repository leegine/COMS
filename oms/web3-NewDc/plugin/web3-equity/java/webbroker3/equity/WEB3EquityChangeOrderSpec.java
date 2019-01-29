head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正内容(WEB3EquityChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 周玲玲 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/11/01 趙林鵬 (中訊) モデル No.995
Revesion History : 2007/12/18 于瀟(中訊) モデルNo.1242
Revesion History : 2007/12/27 于瀟(中訊) モデルNo.1275
*/
package webbroker3.equity;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文訂正内容）。
 * @@version 1.0
 */
public class WEB3EquityChangeOrderSpec extends EqTypeChangeOrderSpec
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderSpec.class);

    /**
     * （証券会社コード）<BR>
     */
    private String institutionCode;

    /**
     * （注文チャネル）<BR>
     */
    private String orderChannel;

    /**
     * （扱者）<BR>
     */
    private Trader trader;

    /**
     * (株式注文内容)<BR>
     */
    private WEB3EquityNewCashBasedOrderSpec newCachBasedOrderSpec;

    /**
     * (コンストラクタ。)<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * 　@スーパークラスのコンストラクタ（EqTypeChangeOrderSpec）をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@orderId：　@（引数より編集）<BR>
     * 　@株式注文訂正値詳細：　@（引数より編集）<BR>
     *    <BR>
     * ２）　@拡張プロパティをセットする<BR>
     * <BR>
     * 　@－証券会社コード：　@（引数より編集）<BR>
     * 　@－注文チャネル：　@（引数より編集）<BR>
     * 　@－扱者：　@（引数より編集）<BR>
     * @@param l_lngOrderId - (注文ＩＤ)<BR>
     * @@param l_eqTypeChangeOrderUnitEntry - (株式注文訂正内容)<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strOrderChannel - (注文チャネル)<BR>
     * @@param l_trader - (扱者)<BR>
     * @@roseuid 4043EE5E0394<BR>
     */
    public WEB3EquityChangeOrderSpec(
        long l_lngOrderId,
        EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry,
        String l_strInstitutionCode,
        String l_strOrderChannel,
        Trader l_trader)
    {
        //スーパークラスのコンストラクタをコールす
        super(l_lngOrderId, l_eqTypeChangeOrderUnitEntry);

        //拡張プロパティをセットする
        setInstitutionCode(l_strInstitutionCode);
        setOrderChannel(l_strOrderChannel);
        setTrader(l_trader);
    }

    /**
     * (create株式注文内容)<BR>
     * <BR>
     * 株式注文訂正内容より、株式注文内容オブジェクトを作成して返却する。<BR>
     * <BR>
     * １）　@既存インスタンスチェック<BR>
     * 　@－this.株式注文内容がnullでない場合、this.株式注文内容を返却し処理を終了する。<BR>
     * <BR>
     * ２）　@訂正元注文単位オブジェクトを取得する。<BR>
     * 　@－this.get訂正元注文単位( )にて、注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ３）　@インスタンス生成。<BR>
     * 　@－株式注文内容オブジェクト.create株式注文内容( )にて株式注文内容インスタンスを生成する。<BR>
     * 　@－this.株式注文内容に、生成した株式注文内容インスタンスをセットする。<BR>
     * 　@－this.株式注文内容を返却する。<BR>
     * <BR>
     * [create株式注文内容　@引数]<BR>
     * 証券会社コード：　@this.証券会社コード<BR>
     * 扱者：　@this.扱者<BR>
     * 市場コード：　@注文単位.市場IDに該当する市場コード<BR>
     * 銘柄コード：　@注文単位.銘柄IDに該当する銘柄コード<BR>
     * 株数：　@this.get訂正値詳細( ).getAfterChangeOriginalQuantity( )<BR>
     * 指値：　@this.get訂正値詳細( ).getAfterChangePrice( )<BR>
     * 執行条件：　@this.get訂正値詳細( ).get訂正後執行条件( )<BR>
     * 税区分：　@注文単位.税区分<BR>
     * 注文失効日：　@this.get訂正値詳細( ).get訂正後注文失効日( )<BR>
     * is売注文：　@注文単位.注文種別より判定する。（”現物買注文”の場合はfalse、”現物売注文”の場合はtrue）<BR>
     * 注文チャネル：　@注文単位.初回注文の注文チャネル<BR>
     * 値段条件：　@this.get訂正値詳細( ).get訂正後値段条件( )<BR>
     * 発注条件：　@this.get訂正値詳細( ).get訂正後発注条件( )<BR>
     * 発注条件演算子：　@this.get訂正値詳細( ).get訂正後発注条件演算子( )<BR>
     * 逆指値基準値：　@this.get訂正値詳細( ).get訂正後逆指値基準値( )<BR>
     * （W指値）訂正指値：　@this.get訂正値詳細( ).get訂正後（W指値）訂正指値( )<BR>
     * 初回注文の注文単位ID：　@注文単位.初回注文の注文単位ID<BR>
     * （W指値）執行条件： this.get訂正値詳細( ).get訂正後（W指値）執行条件( ) <BR>
     * <BR>
     * 以降の処理手順については、シーケンス図「（注文）訂正発注審査」中の<BR>
     * 株式注文訂正内容.create株式注文内容( )部分を参照。<BR>
     * <BR>
     * @@return webbroker3.equity.WEB3EquityNewCashBasedOrderSpec<BR>
     * @@roseuid 4043E1B30087<BR>
     */
    public WEB3EquityNewCashBasedOrderSpec createOrderSpec() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderSpec() ";
        log.entering(STR_METHOD_NAME);

        //既存インスタンスチェック
        if (this.newCachBasedOrderSpec != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.newCachBasedOrderSpec;
        }

        //訂正元注文単位オブジェクトを取得する
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)this.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //市場コードを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode =
                l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId()).getMarketCode();
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }

        //銘柄コードを取得
        EqTypeProduct l_eqTypeProduct =
            (EqTypeProduct) l_orderUnit.getProduct();
        String l_strProductCode = l_eqTypeProduct.getProductCode();

        //株数を取得
        WEB3EquityChangeOrderUnitEntry l_eqChangeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry)this.getChangeOrderUnitEntry();
        double l_dblQuantity = l_eqChangeOrderUnitEntry.getAfterChangeOriginalQuantity();

        //単価を取得
        double l_dblPrice =
            l_eqChangeOrderUnitEntry.getAfterChangePrice();

        //執行条件を取得
        EqTypeExecutionConditionType l_eqExecutionConditionType =
            l_eqChangeOrderUnitEntry.getAfterChangeExecutionConditionType();

        //税区分:注文単位.税区分
        TaxTypeEnum l_taxType = l_orderUnit.getTaxType();

        //注文失効日：　@this.get訂正値詳細( ).get訂正後注文失効日
        Timestamp l_expirationDate = new Timestamp(l_eqChangeOrderUnitEntry.getModifiedExpirationDate().getTime());

        //is売注文：　@注文単位.注文種別より判定する
        boolean l_isSellOrder = false;
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
        {
            l_isSellOrder = true;
        }

        //注文チャネル：　@注文単位.初回注文の注文チャネル
        String l_strOrderChanel = l_orderUnitRow.getOrderChanel();

        //値段条件
        String l_strPriceConditionType = l_eqChangeOrderUnitEntry.getChangeAfterPriceConditionType();
        
        //発注条件：　@this.get訂正値詳細( ).get訂正後発注条件
        String l_strOrderCondType =
            ((WEB3EquityChangeOrderUnitEntry) l_eqChangeOrderUnitEntry).getChangeAfterOrderCondType();

        //発注条件演算子：　@this.get訂正値詳細( ).get訂正後発注条件演算子
        String l_strChangeAfterOrderCondOperator =
            l_eqChangeOrderUnitEntry.getChangeAfterOrderCondOperator();

        //逆指値基準値：　@this.get訂正値詳細( ).get訂正後逆指値基準値
        double l_dblChangeAfterStopOrderCondPriceBasePrice =
            l_eqChangeOrderUnitEntry.getChangeAfterStopOrderCondPriceBasePrice();

        //（W指値）訂正指値：　@this.get訂正値詳細( ).get訂正後（W指値）訂正指値
        double l_dblChangeAfterWlimitOrderCondPrice =
            l_eqChangeOrderUnitEntry.getChangeAfterWlimitOrderCondPrice();

        //初回注文の注文単位ID：　@注文単位.初回注文の注文単位ID
        Long l_beforeCarryoverOrderUnitId = null;
        if (l_orderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_beforeCarryoverOrderUnitId = null;
        }
        else
        {
            l_beforeCarryoverOrderUnitId =
                new Long(l_orderUnitRow.getFirstOrderUnitId());
        }

        //（W指値）執行条件： this.get訂正値詳細( ).get訂正後（W指値）執行条件( )
        EqTypeExecutionConditionType l_wlimitExecCondType =
            l_eqChangeOrderUnitEntry.getModifiedWlimitExecCondType();

        this.newCachBasedOrderSpec =
            WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                this.getInstitutionCode(), //証券会社コード
                (WEB3GentradeTrader)this.getTrader(), //扱者
                l_strMarketCode, //市場コード
                l_strProductCode, //銘柄コード
                l_dblQuantity, //株数
                l_dblPrice, //単価
                l_eqExecutionConditionType, //執行条件
                l_taxType, //税区分
                l_expirationDate, //注文失効日
                l_isSellOrder, //is売注文
                l_strOrderChanel, //注文チャネル
                l_strPriceConditionType, //値段条件
                l_strOrderCondType, //発注条件
                l_strChangeAfterOrderCondOperator, //発注条件演算子
                l_dblChangeAfterStopOrderCondPriceBasePrice, //逆指値基準値
                l_dblChangeAfterWlimitOrderCondPrice, //（W指値）訂正指値
                l_beforeCarryoverOrderUnitId, //初回注文の注文単位ID
                l_wlimitExecCondType //（W指値）執行条件
            );

        //部店を取得
        AccountManager l_accountManager = l_finApp.getAccountManager();
        Branch l_branch = null;        
        try
        {
            l_branch = l_accountManager.getBranch(l_orderUnit.getBranchId());
        }
        catch (NotFoundException nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBのアクセスに失敗しました",
                nfe);
        }
        //set手数料商品コード（10：上場株式）
        newCachBasedOrderSpec.setCommissionProductCode(
            WEB3CommisionProductCodeDef.LISTING_STOCK);

        //株式注文内容::create手数料
        newCachBasedOrderSpec.createCommission(
            l_branch,
            l_orderUnitRow.getSonarTradedCode());

        //株式注文内容::get手数料 
        WEB3GentradeCommission l_equityCommission =
            newCachBasedOrderSpec.getCommission();
        
        //set当初注文手数料条件(手数料)
        this.setOriginalOrderCommCond(l_equityCommission);

        //set手数料
        newCachBasedOrderSpec.setCommission(l_equityCommission);

        log.exiting(STR_METHOD_NAME);
        return this.newCachBasedOrderSpec;
    }

    /**
     * （set証券会社コード）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@roseuid 4043195C019B<BR>
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * (get証券会社コード)<BR>
     * @@return String<BR>
     * @@roseuid 4043195D03AE<BR>
     */
    public String getInstitutionCode()
    {
        return this.institutionCode;
    }

    /**
     * (set注文チャネル)<BR>
     * @@roseuid 4043195E039E<BR>
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        this.orderChannel = l_strOrderChannel;
    }

    /**
     * (get注文チャネル)<BR>
     * @@return String<BR>
     * @@roseuid 4043195F037F<BR>
     */
    public String getOrderChannel()
    {
        return this.orderChannel;
    }

    /**
     * (set扱者)<BR>
     * @@param l_trader - (扱者)<BR>
     * @@roseuid 40431961011E<BR>
     */
    public void setTrader(Trader l_trader)
    {
        this.trader = l_trader;
    }

    /**
     * (get扱者)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Trader<BR>
     * @@roseuid 40431962015C<BR>
     */
    public Trader getTrader()
    {
        return this.trader;
    }

    /**
     * (get訂正値詳細)<BR>
     * <BR>
     * 変更注文単位エントリを取得する。<BR>
     * <BR>
     * getChangeOrderEntries( )にて訂正入力データを取得、<BR>
     * 返却値Listの0番目の要素を<BR>
     * EqTypeChangeOrderUnitEntryにキャストして返却する。<BR>
     * @@return <BR>
     * com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry<BR>
     * @@roseuid 40431B1600B0<BR>
     */
    public EqTypeChangeOrderUnitEntry getChangeOrderUnitEntry()
    {
        EqTypeChangeOrderUnitEntry[] l_eqChangeOrderUnitEntres =
            getChangeOrderUnitEntries();

        EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry =
            l_eqChangeOrderUnitEntres[0];
        return l_eqTypeChangeOrderUnitEntry;
    }

    /**
     * (get訂正元注文単位)<BR>
     * <BR>
     * 訂正元注文の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * １）　@訂正値詳細オブジェクト取得<BR>
     * this.get訂正値詳細()にて株式注文訂正値詳細オブジェクト<BR>
     * を取得する。<BR>
     * <BR>
     * ２）　@注文単位オブジェクト返却<BR>
     * 取得した訂正値詳細.get注文単位()の戻り値を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit<BR>
     * @@roseuid 40431C1E01F3<BR>
     */
    public OrderUnit getOrgChangeOrderUnit()
    {
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry) this.getChangeOrderUnitEntry();

        OrderUnit l_orderUnit = l_changeOrderUnitEntry.getOrderUnit();
        return l_orderUnit;
    }

    /**
     * (set当初注文手数料条件)<BR>
     * <BR>
     * 　@引数の手数料オブジェクトに、当初注文情報をセットする。<BR>
     * <BR>
     * 　@手数料条件を取得し、訂正時の手数料計算に<BR>
     * 使用する項目を追加でセットする。<BR>
     * <BR>
     * １）　@訂正元注文単位の取得<BR>
     * 　@get訂正元注文単位()で訂正元の<BR>
     * 　@注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@当初注文の条件項目をセットする。<BR>
     * 　@－手数料.原注文注文チャネルに、取得した<BR>
     * 　@　@注文単位オブジェクト.初回注文の注文チャネルをセットする。<BR>
     * 　@－手数料.原注文手数料No.に、取得した<BR>
     * 　@　@注文単位オブジェクト.初回注文の手数料No.をセットする。<BR>
     * 　@－手数料.原注文手数料No.枝番に、取得した<BR>
     * 　@　@注文単位オブジェクト.初回注文の手数料No.枝番をセットする。<BR>
     * <BR>
     * @@see OrderUnit#getOrderActions()<BR>
     * @@param l_commission - (手数料)<BR>
     * @@roseuid 404562EC0109<BR>
     */
    private void setOriginalOrderCommCond(WEB3GentradeCommission l_commission)
    {

        //注文単位の取得
        EqTypeOrderUnit l_orderUnit =
            (EqTypeOrderUnit) this.getOrgChangeOrderUnit();

        EqtypeOrderUnitRow l_eqOrderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //手数料.原注文注文チャネルをセットする
        l_commission.setOrgOrderChannel(l_eqOrderUnitRow.getOrderChanel());
        //手数料.原注文手数料No
        l_commission.setOrgCommissionNo(l_eqOrderUnitRow.getCommTblNo());
        //手数料.原注文手数料No.枝番をセットする
        l_commission.setOrgCommissionRevNo(
            l_eqOrderUnitRow.getCommTblSubNo());

    }

    /**
     * (get株式注文内容)<BR>
     * 株式注文内容オブジェクトを取得する。<BR>
     *<BR> 
     * @@return WEB3EquityNewCashBasedOrderSpec<BR>
     */
    public WEB3EquityNewCashBasedOrderSpec getNewCachBasedOrderSpec()
    {
        return this.newCachBasedOrderSpec;
    }
    
    /**
     * (createPTS株式注文内容)<BR>
     * <BR>
     * 株式注文訂正内容より、株式注文内容オブジェクトを作成して返却する。<BR> 
     * <BR>
     * １）　@既存インスタンスチェック <BR>
     * 　@－this.株式注文内容がnullでない場合、this.株式注文内容を返却し処理を終了する。 <BR>
     * <BR>
     * ２）　@訂正元注文単位オブジェクトを取得する。 <BR>
     * 　@－this.get訂正元注文単位( )にて、注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ３）　@インスタンス生成。 <BR>
     * 　@－株式注文内容オブジェクト.createPTS株式注文内容( )にて株式注文内容インスタンスを生成する。<BR> 
     * 　@－this.株式注文内容に、生成した株式注文内容インスタンスをセットする。 <BR>
     * 　@－this.株式注文内容を返却する。 <BR>
     * <BR>
     * [createPTS株式注文内容の引数] <BR>
     * <BR>
     * 証券会社コード：　@this.証券会社コード <BR>
     * 扱者：　@this.扱者 <BR>
     * 市場コード：　@注文単位.市場IDに該当する市場コード <BR>
     * 銘柄コード：　@注文単位.銘柄IDに該当する銘柄コード <BR>
     * 株数：　@this.get訂正値詳細( ).getAfterChangeOriginalQuantity( ) <BR>
     * 指値：　@this.get訂正値詳細( ).getAfterChangePrice( ) <BR>
     * 執行条件：　@this.get訂正値詳細( ).get訂正後執行条件( ) <BR>
     * 税区分：　@注文単位.税区分 <BR>
     * 注文失効日：　@this.get訂正値詳細( ).get訂正後注文失効日( ) <BR>
     * is売注文：　@注文単位.注文種別より判定する。（”現物買注文”の場合はfalse、”現物売注文”の場合はtrue） <BR>
     * 注文チャネル：　@注文単位.初回注文の注文チャネル <BR>
     * 値段条件：　@this.get訂正値詳細( ).get訂正後値段条件( ) <BR>
     * 発注条件：　@this.get訂正値詳細( ).get訂正後発注条件( ) <BR>
     * 発注条件演算子：　@this.get訂正値詳細( ).get訂正後発注条件演算子( ) <BR>
     * 逆指値基準値：　@this.get訂正値詳細( ).get訂正後逆指値基準値( )  <BR>
     * （W指値）訂正指値：　@this.get訂正値詳細( ).get訂正後（W指値）訂正指値( ) <BR>
     * 初回注文の注文単位ID：　@注文単位.初回注文の注文単位ID<BR>
     * <BR>
     * ４）　@手数料情報を作成する <BR>
     * <BR>
     * 　@４－１）　@this.株式注文内容.set手数料商品コード( )をコールする。<BR>
     * 　@[set手数料商品コード( )の引数] <BR>
     * 　@　@手数料商品コード：　@"上場株式"（固定）<BR>
     * <BR>
     * 　@４－２）　@this.株式注文内容.create手数料( )をコールする。<BR>
     * 　@[create手数料( )の引数] <BR>
     * 　@　@部店：　@取得した訂正元の注文単位オブジェクト.部店IDに該当する部店オブジェクト<BR>
     * 　@　@取引コード（SONAR） : 取得した訂正元の注文単位オブジェクト.取引コード（SONAR）<BR>
     * <BR>
     * 　@４－３）　@this.set当初注文手数料条件( )をコールする。<BR>
     * 　@[set当初注文手数料条件( )の引数]<BR>
     * 　@　@手数料：　@this.株式注文内容.get手数料( )の戻り値オブジェクト<BR>
     * <BR>
     * 　@４－４）　@this.株式注文内容.set手数料( )をコールする。<BR>
     * 　@[set手数料( )の引数]<BR>
     * 　@　@手数料：　@this.株式注文内容.get手数料( )の戻り値オブジェクト<BR>
     * <BR>
     * @@return WEB3EquityNewCashBasedOrderSpec
     * @@throws WEB3BaseException 
     */
    public WEB3EquityNewCashBasedOrderSpec createPTSOrderSpec() throws WEB3BaseException   
    {

        final String STR_METHOD_NAME = "createPTSOrderSpec() ";
        log.entering(STR_METHOD_NAME);

        //既存インスタンスチェック
        if (this.newCachBasedOrderSpec != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.newCachBasedOrderSpec;
        }

        //訂正元注文単位オブジェクトを取得する
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)this.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //市場コードを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        String l_strMarketCode = null;
        try
        {
            l_strMarketCode =
                l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId()).getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。", l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //銘柄コードを取得
        EqTypeProduct l_eqTypeProduct =
            (EqTypeProduct) l_orderUnit.getProduct();
        String l_strProductCode = l_eqTypeProduct.getProductCode();

        //株数を取得
        WEB3EquityChangeOrderUnitEntry l_eqChangeOrderUnitEntry =
            (WEB3EquityChangeOrderUnitEntry)this.getChangeOrderUnitEntry();
        double l_dblQuantity = l_eqChangeOrderUnitEntry.getAfterChangeOriginalQuantity();

        //指値を取得
        double l_dblPrice =
            l_eqChangeOrderUnitEntry.getAfterChangePrice();

        //執行条件を取得
        EqTypeExecutionConditionType l_eqExecutionConditionType =
            l_eqChangeOrderUnitEntry.getAfterChangeExecutionConditionType();

        //税区分:注文単位.税区分
        TaxTypeEnum l_taxType = l_orderUnit.getTaxType();

        //注文失効日：　@this.get訂正値詳細( ).get訂正後注文失効日
        Timestamp l_expirationDate = new Timestamp(l_eqChangeOrderUnitEntry.getModifiedExpirationDate().getTime());

        //is売注文：　@注文単位.注文種別より判定する
        boolean l_blnIsSellOrder = false;
        if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnit.getOrderType()))
        {
            l_blnIsSellOrder = true;
        }

        //注文チャネル：　@注文単位.初回注文の注文チャネル
        String l_strOrderChanel = l_orderUnitRow.getOrderChanel();

        //値段条件
        String l_strPriceConditionType = l_eqChangeOrderUnitEntry.getChangeAfterPriceConditionType();
        
        //発注条件：　@this.get訂正値詳細( ).get訂正後発注条件
        String l_strOrderCondType =
            ((WEB3EquityChangeOrderUnitEntry) l_eqChangeOrderUnitEntry).getChangeAfterOrderCondType();

        //発注条件演算子：　@this.get訂正値詳細( ).get訂正後発注条件演算子
        String l_strChangeAfterOrderCondOperator =
            l_eqChangeOrderUnitEntry.getChangeAfterOrderCondOperator();

        //逆指値基準値：　@this.get訂正値詳細( ).get訂正後逆指値基準値
        double l_dblChangeAfterStopOrderCondPriceBasePrice =
            l_eqChangeOrderUnitEntry.getChangeAfterStopOrderCondPriceBasePrice();

        //（W指値）訂正指値：　@this.get訂正値詳細( ).get訂正後（W指値）訂正指値
        double l_dblChangeAfterWlimitOrderCondPrice =
            l_eqChangeOrderUnitEntry.getChangeAfterWlimitOrderCondPrice();

        //初回注文の注文単位ID：　@注文単位.初回注文の注文単位ID
        Long l_beforeCarryoverOrderUnitId = null;
        if (l_orderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_beforeCarryoverOrderUnitId = null;
        }
        else
        {
            l_beforeCarryoverOrderUnitId =
                new Long(l_orderUnitRow.getFirstOrderUnitId());
        }

        this.newCachBasedOrderSpec =
            WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                this.getInstitutionCode(),
                (WEB3GentradeTrader)this.getTrader(),
                l_strMarketCode,
                l_strProductCode,
                l_dblQuantity,
                l_dblPrice,
                l_eqExecutionConditionType,
                l_taxType,
                l_expirationDate,
                l_blnIsSellOrder, 
                l_strOrderChanel,
                l_strPriceConditionType,
                l_strOrderCondType,
                l_strChangeAfterOrderCondOperator,
                l_dblChangeAfterStopOrderCondPriceBasePrice,
                l_dblChangeAfterWlimitOrderCondPrice, 
                l_beforeCarryoverOrderUnitId);

       //手数料情報を作成する 
       //this.株式注文内容.set手数料商品コード( )をコールする。 
       //[set手数料商品コード( )の引数] 
       //手数料商品コード：　@"上場株式"（固定） 
       this.newCachBasedOrderSpec.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);

       //this.株式注文内容.create手数料( )をコールする。 
       //[create手数料( )の引数] 
       //部店：　@取得した訂正元の注文単位オブジェクト.部店IDに該当する部店オブジェクト 
       //取引コード（SONAR） : 取得した訂正元の注文単位オブジェクト.取引コード（SONAR） 
       AccountManager l_accountManager = l_finApp.getAccountManager();
       Branch l_branch = null;        
       try
       {
           l_branch = l_accountManager.getBranch(l_orderUnit.getBranchId());
       }
       catch (NotFoundException l_ex)
       {
           log.error("テーブルに該当するデータがありません。", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "テーブルに該当するデータがありません。",
               l_ex);
       }
       this.newCachBasedOrderSpec.createCommission(l_branch, l_orderUnitRow.getSonarTradedCode());
       //this.set当初注文手数料条件( )をコールする。 
       //[set当初注文手数料条件( )の引数] 
       //手数料：　@this.株式注文内容.get手数料( )の戻り値オブジェクト 
       this.setOriginalOrderCommCond(this.newCachBasedOrderSpec.getCommission());
           
       //this.株式注文内容.set手数料( )をコールする。 
       //[set手数料( )の引数] 
       //手数料：　@this.株式注文内容.get手数料( )の戻り値オブジェクト
       this.newCachBasedOrderSpec.setCommission(this.newCachBasedOrderSpec.getCommission());
         
       log.exiting(STR_METHOD_NAME);
       return this.newCachBasedOrderSpec;
    }
}
@
