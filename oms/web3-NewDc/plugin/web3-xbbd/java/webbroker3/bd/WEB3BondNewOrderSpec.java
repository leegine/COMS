head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張債券新規注文内容(WEB3BondNewOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
Revesion History : 2007/7/26 劉立峰 (中訊) 仕様変更・モデルNo.234
*/

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondNewOrderSpec;

/**
 * (拡張債券新規注文内容)<BR>
 * 拡張債券新規注文内容クラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3BondNewOrderSpec extends BondNewOrderSpec
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondNewOrderSpec.class);    
    
    /**
     * (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     */
    private WEB3BondOrderTypeJudge bondOrderTypeJudge;
    
    /**
     * (決済区分)<BR>
     */
    private String settlementDiv;
    
    /**
     * WEB3BondNewOrderSpec<BR>
     * <BR>
     * @@param l_trader - (オペレータ)<BR>
     * オペレータ<BR>
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@param l_blnIsSellOrder -is売却注文<BR>
     * @@param l_strIssueCode - (回号コード)<BR>
     * 回号コード<BR>
     * @@param l_strProductCode - (銘柄コード（WEB3）)<BR>
     * 銘柄コード（WEB3）<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_dblPrice - (単価)<BR>
     * 単価<BR>
     * @@param l_execType - BondExecutionConditionType
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_taxType - (税区分)<BR>
     * 税区分<BR>
     * @@return WEB3BondNewOrderSpec
     * @@roseuid 44C6F09D011E
     */
    public WEB3BondNewOrderSpec(
        Trader l_trader,
        boolean l_blnIsSellOrder, 
        String l_strIssueCode,
        String l_strProductCode,
        String l_strMarketCode, 
        double l_dblQuantity, 
        double l_dblPrice,
        BondExecutionConditionType l_execType,
        Date l_datDeliveryDate, 
        TaxTypeEnum l_taxType)
    {
        super(
            l_trader, 
            l_blnIsSellOrder, 
            l_strIssueCode,
            l_strProductCode,
            l_strMarketCode,
            l_dblQuantity, 
            l_dblPrice,
            l_execType,
            l_datDeliveryDate,
            l_taxType);
    }
    
    /**
     * (create拡張債券新規注文内容)<BR>
     * create拡張債券新規注文内容<BR>
     * <BR>
     * １）拡張債券新規注文内容を生成する<BR>
     *  コンストラクタ（引数.オペレータ,<BR>
     * 　@　@　@　@　@　@　@　@債券注文種別判定.is売却注文 ? false : true,<BR>
     * 　@　@　@　@　@　@　@　@"0",  //回号コード<BR>
     * 　@　@　@　@　@　@　@　@引数.銘柄コード（WEB3）,<BR>
     * 　@　@　@　@　@　@　@　@"0",  //市場コード<BR>
     * 　@　@　@　@　@　@　@　@引数.数量,<BR>
     * 　@　@　@　@　@　@　@　@引数.単価,<BR>
     * 　@　@　@　@　@　@　@　@BondExecutionConditionType.NONE,<BR>
     * 　@　@　@　@　@　@　@　@引数.受渡日, //失効日<BR>
     * 　@　@　@　@　@　@　@　@引数.税区分<BR>
     * 　@　@　@　@　@　@　@　@）<BR>
     * <BR>
     * ２）拡張債券新規注文内容に以下の属性をセットする<BR>
     * 　@　@・債券注文種別判定<BR>
     * 　@　@・決済区分<BR>
     * <BR>
     * ３）拡張債券新規注文内容を返す<BR>
     * <BR>
     * @@param l_trader - (オペレータ)<BR>
     * オペレータ<BR>
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@param l_strProductCode - (銘柄コード（WEB3）)<BR>
     * 銘柄コード(WEB3)<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_dblPrice - (単価)<BR>
     * 単価<BR>
     * @@param l_taxType - (税区分)<BR>
     * 税区分<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_strSettlementDiv - (決済区分)<BR>
     * 決済区分<BR>
     * @@return WEB3BondNewOrderSpec
     * @@roseuid 44C6F09D011E
     */
    public static WEB3BondNewOrderSpec createBondNewOrderSpec(
        Trader l_trader, 
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
        String l_strProductCode,
        double l_dblQuantity,
        double l_dblPrice,
        TaxTypeEnum l_taxType, 
        Date l_datDeliveryDate,
        String l_strSettlementDiv) 
    {
        final String STR_METHOD_NAME = 
            "createBondNewOrderSpec(Trader, WEB3BondOrderTypeJudge, String, double, double, TaxTypeEnum, Date, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）拡張債券新規注文内容を生成する
        WEB3BondNewOrderSpec l_bondNewOrderSpec = new WEB3BondNewOrderSpec(
            l_trader, 
            l_bondOrderTypeJudge.isSellOrder()? false : true, 
            "0", 
            l_strProductCode, 
            "0",
            l_dblQuantity,
            l_dblPrice,
            BondExecutionConditionType.NONE,
            l_datDeliveryDate,
            l_taxType);
        
        //２）拡張債券新規注文内容に以下の属性をセットする
        l_bondNewOrderSpec.bondOrderTypeJudge = l_bondOrderTypeJudge;
        l_bondNewOrderSpec.settlementDiv = l_strSettlementDiv;
        
        //３）拡張債券新規注文内容を返す
        log.exiting(STR_METHOD_NAME);
        return l_bondNewOrderSpec;
    }
    
    /**
     * (get債券注文種別判定)<BR>
     * get債券注文種別判定<BR>
     * <BR>
     * 債券注文種別判定を返す<BR>
     * @@return webbroker3.bd.WEB3BondOrderTypeJudge
     * @@roseuid 44D94EB603A9
     */
    public WEB3BondOrderTypeJudge getBondOrderTypeJudge() 
    {
        return this.bondOrderTypeJudge;
    }
    
    /**
     * (get決済区分)<BR>
     * get決済区分<BR>
     * <BR>
     * 決済区分を返す<BR>
     * @@return String
     * @@roseuid 44D94EDE005D
     */
    public String getSettlementDiv() 
    {
        return this.settlementDiv;
    }

    /**
     * (create拡張債券新規注文内容<国内債券>)<BR>
     * create拡張債券新規注文内容<国内債券><BR>
     * <BR>
     * １）拡張債券新規注文内容を生成する<BR>
     *  コンストラクタ（引数.オペレータ,<BR>
     * 　@　@　@　@　@　@　@　@true, //is買付 <BR>
     * 　@　@　@　@　@　@　@　@"0",  //回号コード<BR>
     * 　@　@　@　@　@　@　@　@引数.債券銘柄.銘柄コード（WEB3）, <BR>
     * 　@　@　@　@　@　@　@　@"0",  //市場コード<BR>
     * 　@　@　@　@　@　@　@　@引数.数量,<BR>
     * 　@　@　@　@　@　@　@　@引数.債券銘柄.買付単価, <BR>
     * 　@　@　@　@　@　@　@　@BondExecutionConditionType.NONE,<BR>
     * 　@　@　@　@　@　@　@　@引数.債券銘柄.受渡日, //失効日<BR>
     * 　@　@　@　@　@　@　@　@TaxTypeEnum.一般<BR>
     * 　@　@　@　@　@　@　@　@）<BR>
     * <BR>
     * ２）拡張債券新規注文内容に以下の内容をセットする <BR>
     * 　@　@・債券注文種別判定：引数.債券注文種別判定<BR>
     * 　@　@・決済区分：「１：円貨」<BR>
     * <BR>
     * ３）拡張債券新規注文内容を返す<BR>
     * <BR>
     * @@param l_trader - (オペレータ)<BR>
     * オペレータ<BR>
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_bdQuantity - (数量)<BR>
     * 数量<BR>
     * @@return WEB3BondNewOrderSpec
     */
    public static WEB3BondNewOrderSpec createBondNewOrderSpecDomesticBond(
        Trader l_trader,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge,
        WEB3BondProduct l_bondProduct,
        BigDecimal l_bdQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createBondNewOrderSpecDomesticBond(Trader, WEB3BondOrderTypeJudge, WEB3BondProduct, BigDecimal)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null || l_bdQuantity == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3BondNewOrderSpec.class.getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）拡張債券新規注文内容を生成する
        WEB3BondNewOrderSpec l_bondNewOrderSpec = new WEB3BondNewOrderSpec(
            l_trader,
            true,
            String.valueOf(0),
            l_bondProduct.getProductCode(),
            WEB3MarketCodeDef.DEFAULT,
            l_bdQuantity.doubleValue(),
            l_bondProduct.getBuyPrice(),
            BondExecutionConditionType.NONE,
            l_bondProduct.getDeliveryDate(),
            TaxTypeEnum.NORMAL);

        //２）拡張債券新規注文内容に以下の内容をセットする
        l_bondNewOrderSpec.bondOrderTypeJudge = l_bondOrderTypeJudge;
        l_bondNewOrderSpec.settlementDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;

        //３）拡張債券新規注文内容を返す
        log.exiting(STR_METHOD_NAME);
        return l_bondNewOrderSpec;
    }
}
@
