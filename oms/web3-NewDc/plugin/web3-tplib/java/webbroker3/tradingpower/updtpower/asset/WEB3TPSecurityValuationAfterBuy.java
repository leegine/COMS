head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityValuationAfterBuy.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 ネットトレードシステム開発部
 File Name        : 買付後証券評価額(WEB3TPSecurityValuationAfterBuy.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/04/03 nakazato(DIR-ST) 新規作成
                    2006/09/15 車進　@  (中訊)   モデルNo.43、No.44
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPExecTypeDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceCallback;
import webbroker3.tradingpower.updtpower.contract.WEB3TPClosingContractSpec;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.util.WEB3LogUtility;

/**
 * (買付後証券評価額)<BR>
 * <BR>
 * ※クラス「証券評価額」を継承<BR>
 */
public class WEB3TPSecurityValuationAfterBuy extends WEB3TPSecurityValuation
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPSecurityValuationAfterBuy.class);

    /**
     * (デフォルトコンストラクタ)
     */
    public WEB3TPSecurityValuationAfterBuy()
    {
        super();
    }

    /**
     * (staticメソッド)(create買付後証券評価)<BR>
     * ※買付後証券評価インスタンスを作成し返却する。<BR>
     * <BR>
     * 1)買付後証券評価オブジェクトを生成する。<BR>
     * 　@-デフォルトコンストラクタをコール<BR>
     * <BR>
     * 2)生成した買付後証券評価オブジェクトの属性に値をセットする。<BR>
     * 　@2-1)引数.顧客属性をセット<BR>
     * 　@　@-this.set顧客属性()をコール<BR>
     * <BR>
     * 　@2-2)引数.計算条件をセット<BR>
     * 　@　@-this.set余力計算条件()をコール<BR>
     * <BR>
     * 　@2-3)引数.現注文内容をセット<BR>
     * 　@　@-this.set現注文内容()をコール<BR>
     * <BR>
     * 　@2-4)引数.建玉情報をセット<BR>
     * 　@　@-this.set建玉情報()をコール<BR>
     * <BR>
     * 3)買付後証券評価オブジェクトを返却する。<BR>
     * <BR>
     * @@param accountInfo - (顧客属性)
     * @@param calcCondition - (計算条件)
     * @@param newOrderSpecs - (現注文内容)
     * @@param contractInfo - (建玉情報)
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationAfterBuy
     */
    public static WEB3TPSecurityValuationAfterBuy createWEB3TPSecurityValuationAfterBuy(
            WEB3TPAccountInfo accountInfo,
            WEB3TPCalcCondition calcCondition,
            WEB3TPNewOrderSpec[] newOrderSpecs,
            WEB3TPContractInfo contractInfo)
    {
        WEB3TPSecurityValuationAfterBuy l_valuation = new WEB3TPSecurityValuationAfterBuy();
        l_valuation.setAccountInfo(accountInfo);
        l_valuation.setCalcCondition(calcCondition);
        l_valuation.setNewOrderSpecs(newOrderSpecs);
        l_valuation.setContractInfo(contractInfo);
        return l_valuation;
    }

    /**
     * 
     * (do取引変動<当日以降>ロード)<BR>
     * ※Superクラスのオーバーライドメソッド<BR>
     * <BR>
     * 取引変動<当日以降>をロードする。<BR>
     * <BR>
     * 1)取引変動<当日以降>ロード(GP)をロードする。<BR>
     * 　@シーケンス図「do取引変動<当日以降>ロード(GP)」参照<BR>
     * <BR>
     * 2)do取引変動<当日以降>ロード（株式）をロードする。<BR>
     * 　@シーケンス図「(買付後証券評価額)do取引変動<当日以降>ロード(株式)」参照<BR>
     */
    protected void doTransactionChangesLoad()
    {
        //評価単価Callbackを取得
        WEB3TPUnitPriceCallback l_unitPriceCallback = getCalcCondition().getUnitPriceCallback();

        // 1.取引変動<当日以降>(GP)をロードする。
        // 1.1 預り証券データリソースアクセス管理.getGP取引レコード(資産変動)
        List l_ruitoOrderUnits = dataMgr.getRuitoOrderUnits(this);
        
        // 1.2 累投注文単位テーブルにレコードが存在する場合
        //（getGP取引レコード()　@!= nu
        if (l_ruitoOrderUnits != null)
        {
            log.debug(l_ruitoOrderUnits.size() + " ruito order unit row found.");

            //1.2.1 LOOP処理：getGP取引レコード()の戻り値の要素数回
            for (Iterator l_it = l_ruitoOrderUnits.iterator(); l_it.hasNext(); )
            {
                RuitoOrderUnitRow l_ruitoOrderUnit = (RuitoOrderUnitRow) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.2.1.1 is存在ロード済み対象銘柄(銘柄ID）
                //   引数の銘柄IDは累投注文単位テーブル.銘柄ID
                boolean isLoadedProduct = this.isLoadedProduct(l_ruitoOrderUnit.
                    getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.2.1.2 is存在ロード済み対象銘柄(銘柄ID) = falseの場合、
                if (!isLoadedProduct)
                {
                    // 1.2.1.2.1 load対象銘柄(銘柄ID)
                    //   引数の銘柄IDは累投注文単位テーブル.銘柄ID
                    //   ミニ株区分追加（預り資産対応）
                    l_product =
                        loadProduct(l_ruitoOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.2.2 銘柄ごと証券評価.create銘柄ごと証券評価(対象銘柄)
                    //   引数の対象銘柄は、「4.1.」の戻り値
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.2.1.2.3 add銘柄ごと証券評価（対象銘柄, 銘柄ごと証券評価)
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.2.1.3 is存在ロード済み対象銘柄(銘柄ID) = trueの場合、
                else
                {
                    // 1.2.1.3.1 get対象銘柄(銘柄ID)
                    //   引数の銘柄IDは累投注文単位テーブル.銘柄ID
                    //   ミニ株区分追加（預り資産対応）
                    l_product =
                        getProduct(l_ruitoOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.3.2 get銘柄ごと証券評価(対象銘柄)
                    //   引数の対象銘柄は「2.3.1」の戻り値
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.2.1.4 取引変動.create取引変動()
                WEB3TPSecurityTransactionChange l_change =
                    WEB3TPSecurityTransactionChange.create();
                
                // 1.2.1.5 取引変動.set余力計算条件(余力計算条件)
                l_change.setCalcCondition(getCalcCondition());
                
                // 1.2.1.6 取引変動. set銘柄タイプ(ProductTypeEnum)
                l_change.setProductType(l_product.getProductType());

                // 1.2.1.7取引変動.set預り区分(預り区分)
                // 引数の預り区分は、get顧客属性().get預り区分(累等注文単位テーブル.補助口座ID)
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_ruitoOrderUnit.getSubAccountId()));

                // 1.2.1.8 取引変動.set税区分 (税区分)
                // 累投注文単位テーブル.税区分
                l_change.setTaxType(l_ruitoOrderUnit.getTaxType());
                
                // 1.2.1.9  to特定口座区分(TaxTypeEnum)
                // 1.2.1.10 取引変動.set特定口座区分(特定口座区分)
                //   引数の特定口座区分は、累等注文単位テーブル.税区分
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(
                    l_ruitoOrderUnit.getTaxType()));

                // 1.2.1.11 取引変動.set注文カテゴリ(注文カテゴリ)
                //   引数の注文カテゴリは、累等注文単位テーブル.注文カテゴリ
                l_change.setOrderCateg(l_ruitoOrderUnit.getOrderCateg());

                // 1.2.1.12 取引変動.set約定区分(約定区分)
                //   引数の約定区分は、未約定
                l_change.setExecType(WEB3TPExecTypeDef.UNEXECUTED);

                // 1.2.1.13取引変動.set売買区分(売買区分)
                //   引数の売買区分は、SideEnum.get売買区分(累投注文単位テーブル.注文種別)
                l_change.setSide(SideEnum.getSide(l_ruitoOrderUnit.getOrderType()));

                // 1.2.1.14 取引変動.set変動数量(変動数量)
                //   引数の変動数量は、累投注文単位テーブル.数量
                l_change.setQuantity(l_ruitoOrderUnit.getQuantity());

                // 1.2.1.15 取引変動.set評価単価(評価単価)
                //   引数の評価単価は、対象銘柄.get評価単価()×対象銘柄.get計算単位()
                l_change.setUnitPrice(
                        l_product.getUnitPrice() * l_product.getUnitSize());                	

                // 1.2.1.16 取引変動.set評価掛目(評価掛目)
                //   引数の評価掛目は、対象銘柄.get証券評価掛目()
                l_change.setValuationRatio(
                    l_product.getValuationRatio(WEB3TPDepositTypeDef.TRUST));

                // 1.2.1.17 取引変動.calc変動反映日(受渡日)
                //   引数の受渡日は、累等注文単位テーブル.受渡日
                l_change.calcReflectDay(l_ruitoOrderUnit.getDeliveryDate());

                // 1.2.1.18 取引変動.set評価額(評価額)
                //   引数の評価額は、変動数量×評価単価×評価掛目
                l_change.setValuationPrice(
                    l_change.getQuantity()
                    * l_change.getUnitPrice());

                // 1.2.1.19 銘柄ごと証券評価額.add証券変動(証券変動)
                //   銘柄ごと証券評価額は、｢2.2.2｣または｢2.3.2｣の戻り値
                //   引数の証券変動は、｢2.4｣の戻り値
                l_valuation.addSecurityChange(l_change);

            }
        }
        else
        {
            log.debug(" ruito order unit row not found.");
        }

        // 取引変動<当日以降>(株式)をロードする。
        // 1.3 預り証券データリソースアクセス管理.get株式取引レコード(資産評価）
        List l_equityOrderUnits = dataMgr.getEqTypeOrderUnits(this);

        /*
         * 1.4 今回注文単位ID
         */
        Long l_newOrderUnitId = null;
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = this.getNewOrderSpecs();

        if(l_newOrderSpecs != null)
        {
            l_newOrderUnitId = new Long(l_newOrderSpecs[0].getOrderUnitId());
        }
        
        /*
         * 1.5 余力計算用代用掛目
         */
        double l_dblSubRate = this.getCalcCondition().getSubstituteRate();
        l_dblSubRate = l_dblSubRate / 100;
        
        // 1.6 株式注文単位テーブルにレコードが存在する場合
        //（get株式取引レコード()　@!= null）
        if (l_equityOrderUnits != null)
        {
            log.debug(l_equityOrderUnits.size() + " eqtype order unit row found.");

            // 1.6.1 LOOP処理：getGP取引レコード()の戻り値の要素数回
            for (Iterator l_it = l_equityOrderUnits.iterator(); l_it.hasNext(); )
            {
                EqtypeOrderUnitRow l_equityOrderUnit = (EqtypeOrderUnitRow) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.6.1.1 is存在ロード済み対象銘柄(銘柄ID）
                //   引数の銘柄IDは株式注文単位テーブル.銘柄ID
                //   ミニ株区分追加（預り資産対応）
                boolean isLoadedProduct = this.isLoadedProduct(l_equityOrderUnit.
                    getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.6.1.2 is存在ロード済み対象銘柄(銘柄ID) = falseの場合、
                if (!isLoadedProduct)
                {
                    // 1.6.1.2.1 load対象銘柄(銘柄ID)
                    //   引数の銘柄IDは株式注文単位テーブル.銘柄ID
                    //   ミニ株区分追加（預り資産対応）
                    l_product =
                        loadProduct(l_equityOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.6.1.2.2 銘柄ごと証券評価.create銘柄ごと証券評価(対象銘柄)
                    //   引数の対象銘柄は、「2.2.1」の戻り値
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.6.1.2.3 add銘柄ごと証券評価（対象銘柄, 銘柄ごと証券評価)
                    //   引数の対象銘柄は「2.2.1」の戻り値
                    //   引数の銘柄ごと証券評価は「2.2.2」の戻り値
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.6.1.3is存在ロード済み対象銘柄(銘柄ID) = trueの場合、
                else
                {
                    // 1.6.1.3.1 get対象銘柄(銘柄ID)
                    //   引数の銘柄IDは株式注文単位テーブル.銘柄ID
                    //   ミニ株区分追加（預り資産対応）
                    l_product =
                        getProduct(l_equityOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.6.1.3.2 get銘柄ごと証券評価(対象銘柄)
                    //   引数の対象銘柄は「2.3.1」の戻り値
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.6.1.4 取引変動.create取引変動()--未約定取引変動
                WEB3TPSecurityTransactionChange l_unExecChange =
                    WEB3TPSecurityTransactionChange.create();

                // 1.6.1.4.5 未約定取引変動.create取引変動()
                l_unExecChange.setCalcCondition(getCalcCondition());

                // 1.6.1.4.6 未約定取引変動.set銘柄タイプ()
                l_unExecChange.setProductType(l_product.getProductType());

                // 1.6.1.4.7 未約定取引変動.set預り区分(預り区分)
                // 引数の預り区分は、get顧客属性().get預り区分(株式注文単位テーブル.補助口座ID)
                l_unExecChange.setDepositType(
                    getAccountInfo().getDepositType(
                    l_equityOrderUnit.getSubAccountId()));

                // 1.6.1.4.8 未約定取引変動.set税区分(税区分)
                // 株式注文単位テーブル.税区分
                l_unExecChange.setTaxType(l_equityOrderUnit.getTaxType());
                
                // 1.6.1.9 to特定口座区分(TaxTypeEnum)
                // 1.6.1.10 未約定取引変動.set特定口座区分(特定口座区分)
                l_unExecChange.setSpecialAccountFlag(l_unExecChange.toSpecialAccountFlag(
                    l_equityOrderUnit.getTaxType()));

                // 1.6.1.11 未約定取引変動.set注文カテゴリ(注文カテゴリ)
                //   引数の注文カテゴリは、株式注文単位テーブル.注文カテゴリ
                l_unExecChange.setOrderCateg(l_equityOrderUnit.getOrderCateg());

                // 1.6.1.12 未約定取引変動.set売買区分(売買区分)
                l_unExecChange.setSide(SideEnum.getSide(l_equityOrderUnit.getOrderType()));
                
                /*
                 * 評価単価
                 */
                double l_dblValuationRatio;

                // 1.6.1.13 代用かつ、今回注文の場合 預り区分 == 代用 かつ 株式注文単位テーブル.注文単位ID == 今回注文単位ID の場合
                if(WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_unExecChange.getDepositType()) == true
                        && l_newOrderUnitId != null
                        && l_newOrderUnitId.longValue() == l_equityOrderUnit.getOrderUnitId())
                {
                    // 1.6.1.13.1 評価単価 = MIN(対象銘柄の掛目, 余力計算用代用掛目)
                    l_dblValuationRatio = Math.min(
                            l_product.getValuationRatio(l_unExecChange.getDepositType()),
                            l_dblSubRate);
                }
                //1.6.1.14 以外の場合
                else
                {
                    // 1.6.1.14.1 評価単価 = 対象銘柄の掛目
                    l_dblValuationRatio = l_product.getValuationRatio(l_unExecChange.getDepositType());
                }

                l_unExecChange.setValuationRatio(l_dblValuationRatio);
                
                // 1.6.1.15 未約定取引変動.calc変動反映日(受渡日)
                //   引数の受渡日は、株式注文単位テーブル.受渡日
                l_unExecChange.calcReflectDay(l_equityOrderUnit.getDeliveryDate());

                // 1.6.1.16 取引変動.create取引変動()--約定取引変動
                WEB3TPSecurityTransactionChange l_execChange =
                    WEB3TPSecurityTransactionChange.create();
                l_execChange.setExecType(WEB3TPExecTypeDef.EXECUTED);

                // 1.6.1.17 約定取引変動.set計算条件()
                l_execChange.setCalcCondition(getCalcCondition());

                // 1.6.1.18  約定取引変動.set銘柄タイプ
                l_execChange.setProductType(l_product.getProductType());

                // 1.6.1.19 約定取引変動.set預り区分(預り区分)
                // 引数の預り区分は、get顧客属性().get預り区分(株式注文単位テーブル.補助口座ID)
                l_execChange.setDepositType(
                    getAccountInfo().getDepositType(
                    l_equityOrderUnit.getSubAccountId()));

                // 1.6.1.20 約定取引変動.set税区分(税区分)
                // 株式注文単位テーブル.税区分
                l_execChange.setTaxType(l_equityOrderUnit.getTaxType());
                
                // 1.6.1.21  to特定口座区分(TaxTypeEnum)
                // 1.6.1.22 取引変動.set特定口座区分(特定口座区分)
                l_execChange.setSpecialAccountFlag(l_execChange.toSpecialAccountFlag(
                    l_equityOrderUnit.getTaxType()));

                // 1.6.1.23 取引変動.set注文カテゴリ(注文カテゴリ)
                //   引数の注文カテゴリは、株式注文単位テーブル.注文カテゴリ
                l_execChange.setOrderCateg(l_equityOrderUnit.getOrderCateg());

                // 1.6.1.24 未約定取引変動.set売買区分(売買区分)
                l_execChange.setSide(SideEnum.getSide(l_equityOrderUnit.getOrderType()));

                // 1.6.1.25 取引変動.set評価掛目(評価掛目)
                l_execChange.setValuationRatio(
                    l_product.getValuationRatio(l_unExecChange.getDepositType()));

                // 1.6.1.26 未約定取引変動.calc変動反映日(受渡日)
                //   引数の受渡日は、株式注文単位テーブル.受渡日
                l_execChange.calcReflectDay(l_equityOrderUnit.getDeliveryDate());

                // 1.6.1.27
                //  売けの場合：
                //      株式注文単位テーブル.注文数量 - 株式注文単位テーブル.約定数量 > 0
                //       かつ、株式注文単位テーブル.注文タイプ = 現渡の場合
                //あるいは
                //  買付の場合
                //  株式注文単位テーブル.注文数量 - 株式注文単位テーブル.約定数量 > 0
                if ( (SideEnum.SELL.equals(l_execChange.getSide())
                      && (
                    l_equityOrderUnit.getQuantity() -
                    l_equityOrderUnit.getExecutedQuantity() > 0 &&
                    OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_equityOrderUnit.getOrderType())
                    )
                      )

                    || (SideEnum.BUY.equals(l_execChange.getSide())
                        && (
                    l_equityOrderUnit.getQuantity() -
                    l_equityOrderUnit.getExecutedQuantity() > 0
                    )
                        )
                    )
                {
                    // 1.6.1.27.1.取引変動.set約定区分(約定区分)
                    //   引数の約定区分は、未約定
                    l_unExecChange.setExecType(WEB3TPExecTypeDef.UNEXECUTED);

                    // 1.6.1.27.2 現引・現渡でない場合
                    //(株式注文単位Row.注文カテゴリ != 7:現引・現渡注文)
                    if (!OrderCategEnum.SWAP_MARGIN.equals(l_equityOrderUnit.
                        getOrderCateg()))
                    {
                        // 1.6.1.27.2.2 取引変動.set変動数量(変動数量)
                        //   引数の変動数量は、株式注文単位テーブル.数量 -
                        // 株式注文単位テーブル.約定数量
                        l_unExecChange.setQuantity(l_equityOrderUnit.getQuantity() -
                            l_equityOrderUnit.getExecutedQuantity());

                        //  1.6.1.27.2.3 取引変動.set評価単価(評価単価)
                        //   引数の評価単価は、株式注文単位テーブル.注文単価 × 対象銘柄.get計算単位()
                        l_unExecChange.setUnitPrice(
                            l_unitPriceCallback.getUnitPriceNotCompare(l_equityOrderUnit.getPrice(), l_product) * 
                            l_product.getUnitSize());                                                

                        // 1.6.1.27.2.4 取引変動.set評価額(評価額)
                        //
                        // 引数の評価額は、取引変動.get変動数量()×取引変動.get評価単価()
                        //売買代金を計算するため、掛目を掛けない
                        l_unExecChange.setValuationPrice(
                            l_unExecChange.getQuantity()
                            * l_unExecChange.getUnitPrice()
                            );
                    }
                    // 1.6.1.27.3 以外（現引・現渡）の場合
                    else
                    {
                        // バグY00019：信用現引注文の未約定代金計算に使用する注文単価にnullが入る
                        // 返済情報から建単価で評価する
                        //一括返済の場合も対応する。
                        List l_closingContractSpecs = this.getContractInfo().
                            getClosingContractSpecs(l_equityOrderUnit.getOrderUnitId());
                        double l_quantity = 0.0;
                        double l_amount = 0.0;
                        for (int i = 0; i < l_closingContractSpecs.size(); i++)
                        {
                            WEB3TPClosingContractSpec l_closingContractSpec = (
                                WEB3TPClosingContractSpec)
                                l_closingContractSpecs.get(i);
                            l_quantity +=
                                (l_closingContractSpec.getQuantity() -
                                 l_closingContractSpec.getExecutedQuantity());
                            l_amount += (l_closingContractSpec.getQuantity() -
                                         l_closingContractSpec.getExecutedQuantity()) *
                                l_unitPriceCallback.getUnitPrice(l_closingContractSpec.getContractPrice(), l_product) *                                          
                                l_product.getUnitSize();
                            l_unExecChange.setQuantity(l_quantity);
                            l_unExecChange.setValuationPrice(l_amount);
                            if (l_quantity > 0)
                            {
                                l_unExecChange.setUnitPrice(l_amount / l_quantity);
                            }
                        }
                    }

                    // 1.6.1.27.3.6 銘柄ごと証券評価額.add証券変動(証券変動)
                    l_valuation.addSecurityChange(l_unExecChange);

                }

                // 1.6.1.28 株式注文単位テーブル.約定数量 > 0
                if (l_equityOrderUnit.getExecutedQuantity() > 0)
                {

                    // 1.6.1.28.1 取引変動.set約定区分(約定区分)
                    //   引数の約定区分は、約定済
                    l_execChange.setExecType(WEB3TPExecTypeDef.EXECUTED);

                    // 1.6.1.28.2 取引変動.get注文カテゴリ() != 「現引・現渡」の場合
                    if (!OrderCategEnum.SWAP_MARGIN.equals(l_unExecChange.getOrderCateg()))
                    {
                        // 1.6.1.28.2.1 預り証券データリソースアクセス管理.get株式約定レコード(注文単位ID)
                        //   引数の注文単位IDは株式注文単位テーブル.注文単位ID
                        List l_equityOrderExecutions =
                            dataMgr.
                            getEqTypeOrderExecutions(l_equityOrderUnit);

                        // 1.6.1.28.2.2 取得したレコードを集計し以下の値を算出する
                        //   変動数量 += 株式注文約定テーブル.約定数量
                        //   評価額 += 株式注文約定テーブル.約定数量×Min（株式約定テーブル.約定単価,
                        // 対象銘柄.get評価単価()× 対象銘柄.get計算単位()
                        double l_quantity = 0.0;
                        double l_amount = 0.0;
                        for (int i = 0; i < l_equityOrderExecutions.size(); i++)
                        {
                            EqtypeOrderExecutionRow l_equityOrderExecution = (
                                EqtypeOrderExecutionRow)
                                l_equityOrderExecutions.get(i);
                            //約定取消対応のため、取消された約定を対象外にする
                            if (BooleanEnum.TRUE.equals(l_equityOrderExecution.
                                getDeleteFlag()))
                            {
                                continue;
                            }
                            l_quantity += l_equityOrderExecution.getExecQuantity();
                            l_amount += l_equityOrderExecution.getExecQuantity() *
                                l_unitPriceCallback.getUnitPriceNotCompare(l_equityOrderExecution.getExecPrice(), l_product) *                                          
                                l_product.getUnitSize();
                        }
                        // 1.6.1.28.2.3 取引変動.set変動数量(変動数量)
                        //   引数の変動数量は、｢2.14.2.2｣で算出した変動数量
                        l_execChange.setQuantity(l_quantity);

                        // 1.6.1.28.2.4 取引変動.set評価額(評価額)
                        // 　@引数の評価額は、算出した評価額
                        ////売買代金を計算するため、掛目を掛けない
                        l_execChange.setValuationPrice(l_amount);
                        
                        // 　@引数の評価単価は、 2.12.2.2算出した評価額 / 取引変動.get変動数量()
                        if (l_quantity > 0)
                        {
                            l_execChange.setUnitPrice(l_amount / l_quantity);
                        }

                        //銘柄ごと証券評価額.add証券変動(証券変動)

                        l_valuation.addSecurityChange(l_execChange);
                    }
                    // 1.6.1.28.3取引変動.get注文カテゴリ() =「現引・現渡」の場合
                    else
                    {
                        if (this.getContractInfo() != null)
                        {
                            // 1.6.1.28.3.1 建玉情報.get返済指定情報(注文単位テーブル.注文単位ID)で建玉返済指定情報
                            // を取得する。
                            List l_closingContractSpecs = this.getContractInfo().
                                getClosingContractSpecs(l_equityOrderUnit.getOrderUnitId());
                            // 取得した建玉返済指定情報を集計し以下の値を算出する
                            // 　@変動数量 += 建玉返済指定情報.約定数量
                            // 　@評価額 + = 建玉返済指定情報.返済約定数量
                            // 　@　@　@　@　@　@　@× Min(建玉返済指定情報.建単価, 対象銘柄.get評価単価()) × 対象銘柄.get計算単位()
                            // 　@　@　@　@　@　@　@
                            double l_quantity = 0.0;
                            double l_amount = 0.0;
                            for (int i = 0; i < l_closingContractSpecs.size(); i++)
                            {
                                WEB3TPClosingContractSpec l_closingContractSpec = (
                                    WEB3TPClosingContractSpec)
                                    l_closingContractSpecs.get(i);
                                l_quantity += l_closingContractSpec.getExecutedQuantity();
                                l_amount += l_closingContractSpec.getExecutedQuantity() *
                                    l_unitPriceCallback.getUnitPrice(l_closingContractSpec.getContractPrice(), l_product) * 
                                    l_product.getUnitSize();
                            }

                            // 1.6.1.29 取引変動.set変動数量(変動数量)
                            l_execChange.setQuantity(l_quantity);

                            
                            // 1.6.1.31 取引変動.set評価額(評価額）
                            // 　@引数の評価額は算出した評価額
                            //  売買代金を計算するため、掛目を掛けない
                            l_execChange.setValuationPrice(l_amount);

                            //1.6.1.30 取引変動.set評価単価(評価単価)
                            // 　@引数の評価単価は、算出した評価額 / 取引変動.get変動数量()
                            if (l_quantity > 0)
                            {
                                l_execChange.setUnitPrice(l_amount / l_quantity);
                            }

                            // 1.6.1.32 銘柄ごと証券評価額.add証券変動(証券変動)
                            //   銘柄ごと証券評価額は、｢2.2.2｣または｢2.3.2｣の戻り値
                            //   引数の証券変動は、｢2.4｣の戻り値
                            l_valuation.addSecurityChange(l_execChange);
                        }

                    }
                }

            }
        }
        else
        {
            log.debug(" equity order unit row not found.");
        }

    }

}
@
