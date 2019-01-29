head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券銘柄(WEB3BondProduct.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  齊珂 (中訊) 新規作成
                    2006/10/08 周捷 (中訊) 仕様変更・モデル107、110、115
                    2006/10/10 柴雙紅 (中訊) 仕様変更・モデル121
                    2007/02/08 崔遠鵬 (中訊) 仕様変更・モデル158
 Revesion History : 2007/07/12 柴双紅 (中訊) 仕様変更・モデル204
 Revesion History : 2007/07/26 謝旋 (中訊) 仕様変更・モデル229
 Revesion History : 2008/08/13 馮海濤 (中訊) 仕様変更・モデル260
 Revesion History : 2009/07/24 武波 (中訊) 仕様変更・モデル262
 */

package webbroker3.bd;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondProductImpl;

import webbroker3.bd.define.WEB3BondTypeListDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderSettleDivDef;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3PaymentDateDetDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SpecialPaymentDivDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.define.WEB3YearlyInterestPaymentsDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeCurrencyCodeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券銘柄)<BR>
 * 債券銘柄クラス<BR>
 *
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3BondProduct extends BondProductImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondProduct.class);

    /**
     * (債券銘柄)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * super(債券銘柄Row)をコールする。 <BR>
     * @@param l_bondProductRow - 債券銘柄Row<BR>
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@roseuid 44C44D7A028B
     */
    public WEB3BondProduct(BondProductRow l_bondProductRow) throws DataQueryException, DataNetworkException
    {
        super(l_bondProductRow);
    }

    /**
     * (債券銘柄)<BR>
     * コンストラクタ<BR>
     * <BR>
     * super(銘柄Row)をコールする<BR>
     * @@param l_productRow - 銘柄Row<BR>
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@roseuid 44C44D7A029B
     */
    public WEB3BondProduct(ProductRow l_productRow) throws DataQueryException, DataNetworkException
    {
        super(l_productRow);
    }

    /**
     * (get銘柄コード(SONAR))<BR>
     * 銘柄コード(SONAR)を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get銘柄コード(SONAR)()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44CEEEC901C7
     */
    public String getHostProductCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strHostProductCod = l_bondProduct.getHostProductCode();
        return l_strHostProductCod;
    }

    /**
     * (get回号コード(SONAR))<BR>
     * 回号コード(SONAR)を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get回号コード(SONAR)()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44CEEEFC03CC
     */
    public String getHostProductIssueCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strProductIssueCode = l_bondProduct.getHostProductIssueCode();
        return l_strProductIssueCode;
    }

    /**
     * (get発行額)<BR>
     * 債券の発行額を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get発行額()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A03C6
     */
    public double getIssueAmount()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double  l_dblIssueAmount = l_bondProduct.getIssueAmount();
        return l_dblIssueAmount;
    }

    /**
     * (get利払日１)<BR>
     * 利払日１を返す<BR>
     * <BR>
     * this.getDataSourceObject().get利払日１()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44CEEFA8021C
     */
    public String getInterestPaymentDay1()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strPayDay = l_bondProduct.getInterestPaymentDay1st();
        return l_strPayDay;
    }

    /**
     * (get利払日２)<BR>
     * 利払日２を返す<BR>
     * <BR>
     * this.getDataSourceObject().get利払日２()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44CEEFD40327
     */
    public String getInterestPaymentDay2()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strPayDay = l_bondProduct.getInterestPaymentDay2nd();
        return l_strPayDay;
    }

    /**
     * (get応募開始日（SONAR）)<BR>
     * 応募開始日（SONAR）を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().getSONAR応募開始日()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02CC
     */
    public Date getHostRecruitStartDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datRecruitStart = l_bondProduct.getHostRecruitStartDate();
        return l_datRecruitStart;
    }

    /**
     * (get応募終了日（SONAR）)<BR>
     * 応募終了日（SONAR）を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get応募終了日（SONAR）()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02D9
     */
    public Date getHostRecruitEndDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datRecruitEnd = l_bondProduct.getHostRecruitEndDate();
        return l_datRecruitEnd;
    }

    /**
     * (get取扱区分)<BR>
     * 取扱種別を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get取扱種別()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A029F
     */
    public String getTradeHandleDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeHandleDiv = l_bondProduct.getTradeHandleDiv();
        return l_strTradeHandleDiv;
    }

    /**
     * (get取扱開始日時)<BR>
     * 取扱開始日時を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get取扱開始日時()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02AA
     */
    public Date getTradeStartDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datTradeStartDate = l_bondProduct.getTradeStartDate();
        return l_datTradeStartDate;
    }

    /**
     * (get取扱終了日時)<BR>
     * 取扱終了日時を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get取扱終了日時()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02BA
     */
    public Date getTradeEndDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datTradeEndDate = l_bondProduct.getTradeEndDate();
        return l_datTradeEndDate;
    }

    /**
     * (get応募開始日)<BR>
     * 応募開始日を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get応募開始日()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02CA
     */
    public Date getRecruitStartDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datRecruitStartDate = l_bondProduct.getRecruitStartDate();
        return l_datRecruitStartDate;
    }

    /**
     * (get応募終了日)<BR>
     * 応募終了日を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get応募終了日()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A02CB
     */
    public Date getRecruitEndDate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datRecruitEndDate = l_bondProduct.getRecruitEndDate();
        return l_datRecruitEndDate;
    }

    /**
     * (get売買区分)<BR>
     * 売買区分を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get売買区分の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A02DA
     */
    public String getTradeType()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeType = l_bondProduct.getTradeType();
        return l_strTradeType;
    }

    /**
     * (get銘柄名)<BR>
     * this.getDataSourceObject().銘柄名を返却する。<BR>
     * @@return String
     * @@roseuid 421C51300342
     */
    public String getProductName()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strProductName = l_bondProduct.getProductName();
        return l_strProductName;
    }

    /**
     * (get買付単価)<BR>
     * 買付(単価)を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get買付(単価)()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A0318
     */
    public double getBuyPrice()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblBuyPrice = l_bondProduct.getBuyPrice();
        return l_dblBuyPrice;
    }

    /**
     * (get売却単価)<BR>
     * 売却単価を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get売却単価()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A0319
     */
    public double getSellPrice()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblSellPrice = l_bondProduct.getSellPrice();
        return l_dblSellPrice;
    }

    /**
     * (get申込単位)<BR>
     * 申込単位を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get申込単位()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A031A
     */
    public double getTradeUnit()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblTradeUnit = l_bondProduct.getTradeUnit();
        return l_dblTradeUnit;
    }

    /**
     * (get最低額面)<BR>
     * 最低額面を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get最低額面()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A0327
     */
    public double getMinFaceAmount()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblMinFaceAmount = l_bondProduct.getMinFaceAmount();
        return l_dblMinFaceAmount;
    }

    /**
     * (get最高額面)<BR>
     * 最高額面を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get最高額面()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A0328
     */
    public double getMaxFaceAmount()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblMaxFaceAmount = l_bondProduct.getMaxFaceAmount();
        return l_dblMaxFaceAmount;
    }

    /**
     * (getカレンダー連携市場コード)<BR>
     * カレンダー連携市場コードを返却する<BR>
     * <BR>
     * this.getDataSourceObject().getカレンダー連携市場コードの戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0329
     */
    public String getCalLinkedMarketCode()
    {
        BondProductRow l_bondProductRow =  (BondProductRow)getDataSourceObject();
        String l_strCalLinkedMarketCode = l_bondProductRow.getCalLinkedMarketCode();
        return l_strCalLinkedMarketCode;
    }

    /**
     * (get買付受渡日移動日数)<BR>
     * 買付受渡日移動日数を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get買付受渡日移動日数()の戻り値を返す。<BR>
     * @@return int
     * @@roseuid 44C44D7A0337
     */
    public int getBuyDeliveryDateShiftDays()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        int l_intBuyDeliveryDateShiftDays = l_bondProduct.getBuyDeliveryDateShiftdays();
        return l_intBuyDeliveryDateShiftDays;
    }

    /**
     * (get売却受渡日移動日数)<BR>
     * 売却受渡日移動日数を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get売却受渡日移動日数()の戻り値を返す。<BR>
     * @@return int
     * @@roseuid 44C44D7A0338
     */
    public int getSellDeliveryDateShiftDays()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        int l_intSellDeliveryDateShiftDays = l_bondProduct.getSellDeliveryDateShiftdays();
        return l_intSellDeliveryDateShiftDays;
    }

    /**
     * (get自動約定区分)<BR>
     * 自動約定区分を返却。<BR>
     * <BR>
     * this.getDataSourceObject().get自動約定区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0348
     */
    public String getAutoExecDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strAutoExecDiv = l_bondProduct.getAutoExecDiv();
        return l_strAutoExecDiv;
    }

    /**
     * (get約定済残高)<BR>
     * 約定済残高を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get約定済残高()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A0356
     */
    public double getAutoExecAmount()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblAutoExecAmount = l_bondProduct.getAutoExecAmount();
        return l_dblAutoExecAmount;
    }

    /**
     * (get自動約定枠)<BR>
     * 自動約定枠を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get自動約定枠()の戻り値を返却する。 <BR>
     * @@return double
     * @@roseuid 44C44D7A0357
     */
    public double getAutoExecLimit()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblAutoExecLimit = l_bondProduct.getAutoExecLimit();
        return l_dblAutoExecLimit;
    }

    /**
     * (getカストディアンコード)<BR>
     * カストディアンコードを返す。 <BR>
     * <BR>
     * this.getDataSourceObject().getカストディアンコード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0359
     */
    public String getCustodianCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strCustodianCode = l_bondProduct.getCustodianCode();
        return l_strCustodianCode;
    }

    /**
     * (getHOST銘柄名1)<BR>
     * HOST銘柄名1を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().getHOST銘柄名1()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0377
     */
    public String getHostProductName1()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strHostProductName1 = l_bondProduct.getHostProductName1();
        return l_strHostProductName1;
    }

    /**
     * (getHOST銘柄名2)<BR>
     * HOST銘柄名2を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().getHOST銘柄名2()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0385
     */
    public String getHostProductName2()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strHostProductName2 = l_bondProduct.getHostProductName2();
        return l_strHostProductName2;
    }

    /**
     * (getHOST簡略銘柄名)<BR>
     * HOST簡略銘柄名を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().getHOST簡略銘柄名()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0376
     */
    public String getHostShortProductName()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strHostShortProductName = l_bondProduct.getHostShortProductName();
        return l_strHostShortProductName;
    }

    /**
     * (get種別コード)<BR>
     * 種別コードを返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get種別()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0375
     */
    public String getBondCategCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strBondCategCode = l_bondProduct.getBondCategCode();
        return l_strBondCategCode;
    }

    /**
     * (get通貨コード)<BR>
     * 通貨コードを返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get通貨コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0386
     */
    public String getCurrencyCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strCurrencyCode = l_bondProduct.getCurrencyCode();
        return l_strCurrencyCode;
    }

    /**
     * (get発行市場コード)<BR>
     * 発行市場コードを返す。 <BR>
     * this.getDataSourceObject().get発行市場コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0387
     */
    public String getIssueMarketCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strIssueMarketCode = l_bondProduct.getIssueMarketCode();
        return l_strIssueMarketCode;
    }

    /**
     * (get発行体コード)<BR>
     * 発行体コードを返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get発行体コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A0395
     */
    public String getIssueAssociationCode()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strIssueAssociationCode = l_bondProduct.getIssueAssociationCode();
        return l_strIssueAssociationCode;
    }

    /**
     * (get経過利子計算タイプ)<BR>
     * 経過利子計算タイプを返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get経過利子計算タイプ()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A03A4
     */
    public String getAccruedInterestCalcType()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strAccruedInterestCalcType = l_bondProduct.getAccruedInterestCalcType();
        return l_strAccruedInterestCalcType;
    }

    /**
     * (get経過利子起算日)<BR>
     * 経過利子起算日を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get経過利子起算日()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A03D3
     */
    public Date getAccruedInterestStartDay()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datAccruedInterestStartDay = l_bondProduct.getAccruedInterestStartDay();
        return l_datAccruedInterestStartDay;
    }

    /**
     * (get特殊利払区分)<BR>
     * 特殊利払区分を返却<BR>
     * <BR>
     * this.getDataSourceObject().get特殊利払区分()の戻り値<BR>
     * @@return String
     * @@roseuid 44C44D7A03A5
     */
    public String getSpecialPaymentDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strSpecialPaymentDiv = l_bondProduct.getSpecialPaymentDiv();
        return l_strSpecialPaymentDiv;
    }

    /**
     * (getフローティングレート・金利期間区分)<BR>
     * フローティングレートの場合、金利期間区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().getフローティングレート・金利期間区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A03B4
     */
    public String getFloatingInterestPeriodDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strFloatingInterestPeriodDiv = l_bondProduct.getFloatingInterestPeriodDiv();
        return l_strFloatingInterestPeriodDiv;
    }

    /**
     * (getフローティングレート.金利期間)<BR>
     * フローティングレートの場合、金利期間を返す。<BR>
     * <BR>
     * this.getDataSourceObject().getフローティングレート・金利期間()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C494A801DB
     */
    public String getFloatingInterestPeriod()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strFloatingInterestPeriod = l_bondProduct.getFloatingInterestPeriod();
        return l_strFloatingInterestPeriod;
    }

    /**
     * (getフローティングレート・金利種類)<BR>
     * フローティングレートの場合、金利種類を返す。<BR>
     * <BR>
     * this.getDataSourceObject().getフローティングレート・金利種類()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A03B5
     */
    public String getFloatingInterestType()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strFloatingInterestType = l_bondProduct.getFloatingInterestType();
        return l_strFloatingInterestType;
    }

    /**
     * (getフローティングレート・金利スプレッド)<BR>
     * フローティングレートの場合、金利スプレッドを返す。<BR>
     * <BR>
     * this.getDataSourceObject().getフローティングレート・金利スプレッド()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A03C4
     */
    public double getFloatingInterestSpread()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblFloatingInterestSpread = l_bondProduct.getFloatingInterestSpread();
        return l_dblFloatingInterestSpread;
    }

    /**
     * (getフローティングレート・ミニマムクーポン)<BR>
     * フローティングレート債の場合、ミニマムクーポンを返す。<BR>
     * <BR>
     * this.getDataSourceObject().getフローティングレート・ミニマムクーポン()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A03C5
     */
    public double getFloatingMinCoupon()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblFloatingMinCoupon = l_bondProduct.getFloatingMinCoupon();
        return l_dblFloatingMinCoupon;
    }

    /**
     * (get免税区分)<BR>
     * 免税区分を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get免税区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A03D4
     */
    public String getTaxFreeDiv()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTaxFreeDiv = l_bondProduct.getTaxFreeDiv();
        return l_strTaxFreeDiv;
    }

    /**
     * (getS&P)<BR>
     * S&Pを返す<BR>
     * <BR>
     * this.getDataSourceObject().getS&P()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A03D5
     */
    public String getSAndP()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strSAndP = l_bondProduct.getSAndP();
        return l_strSAndP;
    }

    /**
     * (getMoody's)<BR>
     * Moody'sを返す。<BR>
     * <BR>
     * this.getDataSourceObject().getMoodys()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A03E3
     */
    public String getMoodys()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strMoodys = l_bondProduct.getMoodys();
        return l_strMoodys;
    }

    /**
     * (getCUSIP)<BR>
     * CUSIPを返す。<BR>
     * <BR>
     * this.getDataSourceObject().getCUSIP銘柄コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7A03E4
     */
    public String getCUSIP()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strCUSIP = l_bondProduct.getCusip();
        return l_strCUSIP;
    }

    /**
     * (get仲介手数料率)<BR>
     * 仲介手数料率を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get仲介手数料率()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 44C44D7A0347
     */
    public double getMediatorCommissionRate()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        double l_dblMediatorCommissionRate = l_bondProduct.getMediatorCommissionRate();
        return l_dblMediatorCommissionRate;
    }

    /**
     * (get最終更新者コード)<BR>
     * 最終更新者コードを返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get最終更新者コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 44C44D7B000A
     */
    public String getLastUpdater()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strLastUpdater = l_bondProduct.getLastUpdater();
        return l_strLastUpdater;
    }

    /**
     * (get管理者更新日付)<BR>
     * 管理者更新日付を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get管理者更新日付()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C452B0011F
     */
    public Date getAdminLastUpdatedTimestamp()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datAdminLastUpdatedTimestamp = l_bondProduct.getAdminLastUpdatedTimestamp();
        return l_datAdminLastUpdatedTimestamp;
    }

    /**
     * (is外貨建)<BR>
     * 外貨建債券かどうか判定する。<BR>
     * <BR>
     * <BR>
     * １）債券銘柄.通貨コード==”T0”（円）orNULLの場合、falseを返却する。<BR>
     * <BR>
     * ２）１）以外の場合、trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 44C421490264
     */
    public boolean isForeignCurrency()
    {
        if ((this.getCurrencyCode() == null) ||
            WEB3GentradeCurrencyCodeDef.JPY.equals(this.getCurrencyCode()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * (is転換社債)<BR>
     * 転換社債かどうか判定する。<BR>
     * <BR>
     * <BR>
     * １）債券銘柄.債券タイプ==”9”（CB）の場合、trueを返却する。<BR>
     * <BR>
     * ２）１）以外の場合、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 44C421D00041
     */
    public boolean isExperienceDivWt()
    {
        if (BondTypeEnum.CB.equals(this.getBondType()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (get作成日付)<BR>
     * 債券銘柄の作成日付を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get作成日付()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A029D
     */
    public Date getCreatedTimestamp()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datCreatedTimestamp = l_bondProduct.getCreatedTimestamp();
        return l_datCreatedTimestamp;
    }

    /**
     * (get更新日付)<BR>
     * 債券銘柄の更新日付を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get更新日付()の戻り値を返す。<BR>
     * @@return java.util.Date
     * @@roseuid 44C44D7A029E
     */
    public Date getLastUpdatedTimestamp()
    {
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        Date l_datLastUpdatedTimestamp = l_bondProduct.getLastUpdatedTimestamp();
        return l_datLastUpdatedTimestamp;
    }

    /**
     * (is外国債券)<BR>
     * 外国債券かどうか判定する。<BR>
     * <BR>
     * １）債券銘柄.債券タイプ==”4”（外国債券）の場合、ｔｒｕｅを返却する。<BR>
     * <BR>
     * ２）１）以外の場合、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 44C499EE025C
     */
    public boolean isForeignBond()
    {
        if (BondTypeEnum.FOREIGN_BOND.equals(this.getBondType()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (get現地発注日)<BR>
     * get現地発注日 <BR>
     * <BR>
     * 1)java.util.Dateオブジェクトを生成し、返す<BR>
     * 　@new Date(引数.発注日.getTime())<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@return java.util.Date
     * @@throws WEB3BaseException
     * @@roseuid 44C4B6A30079
     */
    public Date getForeignBizDate(Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForeignBizDate(Date) ";
        log.entering(STR_METHOD_NAME);

        if (l_datBizDate == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return new Date(l_datBizDate.getTime());
    }

    /**
     * (get約定日)<BR>
     * get約定日<BR>
     * <BR>
     * 1)java.util.Dateオブジェクトを生成し、返す<BR>
     * 　@new Date(引数.発注日.getTime())<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 国内発注日<BR>
     * @@return java.util.Date
     * @@throws WEB3BaseException
     * @@roseuid 44C4B7780378
     */
    public Date getExecDate(Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecDate(Date) ";
        log.entering(STR_METHOD_NAME);

        if (l_datBizDate == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return new Date(l_datBizDate.getTime());
    }

    /**
     * (get現地約定日)<BR>
     * get現地約定日<BR>
     * <BR>
     * 1)java.util.Dateオブジェクトを生成し、返す<BR>
     * 　@new Date(引数.現地発注日.getTime())<BR>
     * @@param l_datForeignBizDate - (現地発注日)<BR>
     * 現地発注日<BR>
     * @@return java.util.Date
     * @@throws WEB3BaseException
     * @@roseuid 44C4B7AA02BD
     */
    public Date getForeignExecDate(Date l_datForeignBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForeignExecDate(Date) ";
        log.entering(STR_METHOD_NAME);
        if (l_datForeignBizDate == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return new Date(l_datForeignBizDate.getTime());

    }

    /**
     * (get受渡日)<BR>
     * get受渡日<BR>
     * <BR>
     * １）引数.債券注文種別判定is応募注文()の場合<BR>
     * 　@１－１）this.get発行日()を戻す<BR>
     * <BR>
     * ２）引数.債券注文種別判定.is買付注文()の場合<BR>
     * 　@２－１）new 営業日計算(new java.sql.Timestamp(引数.国内約定日.getTime()))<BR>
     * <BR>
     * 　@２－２)営業日計算.roll(this.get買付受渡日移動日数)を戻す<BR>
     * <BR>
     * ３）引数.債券注文種別判定is売却注文の場合<BR>
     * 　@３－１）new 営業日計算(new java.sql.Timestamp(引数.国内約定日.getTime()))<BR>
     * <BR>
     * 　@３－２)営業日計算.roll(this.get売却受渡日移動日数)を戻す<BR>
     * @@param l_datExecDate - (約定日)<BR>
     * 約定日<BR>
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@return java.util.Date
     * @@throws WEB3BaseException
     * @@roseuid 44C4B7D900F8
     */
    public Date getDeliveryDate(Date l_datExecDate,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDeliveryDate(" +
            "Date, WEB3BondOrderTypeJudge)";
        log.entering(STR_METHOD_NAME);

        if (l_datExecDate == null || l_bondOrderTypeJudge == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Date l_datTmp = null;

        //１）引数.債券注文種別判定is応募注文()の場合
        if (l_bondOrderTypeJudge.isRecruitOrder())
        {
            //１－１）this.get発行日()を戻す
            l_datTmp = this.getIssueDate();
        }

        //２）引数.債券注文種別判定.is買付注文()の場合
        if (l_bondOrderTypeJudge.isBuyOrder())
        {
            //２－１）new 営業日計算(new java.sql.Timestamp(引数.国内約定日.getTime()))
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datExecDate.getTime()));

            //２－２)営業日計算.roll(this.get買付受渡日移動日数)を戻す
            Date l_datBizDate = l_gentradeBizDate.roll(this.getBuyDeliveryDateShiftDays());
            l_datTmp = l_datBizDate;
        }

        //３）引数.債券注文種別判定is売却注文の場合
        if (l_bondOrderTypeJudge.isSellOrder())
        {
            //３－１）new 営業日計算(new java.sql.Timestamp(引数.国内約定日.getTime()))
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datExecDate.getTime()));

            //３－２)営業日計算.roll(this.get売却受渡日移動日数)を戻す
            Date l_datBizDate = l_gentradeBizDate.roll(this.getSellDeliveryDateShiftDays());
            l_datTmp = l_datBizDate;
        }
        log.exiting(STR_METHOD_NAME);
        return l_datTmp;
    }

    /**
     * (get現地受渡日)<BR>
     * get現地受渡日<BR>
     * <BR>
     * １）引数.債券注文種別判定is応募注文()の場合<BR>
     * 　@１－１）this.get発行日()を戻す<BR>
     * <BR>
     * ２）引数.債券注文種別判定.is買付注文()の場合<BR>
     * 　@２－１）new 営業日計算(new java.sql.Timestamp(引数.現地約定日.getTime()))<BR>
     * <BR>
     * 　@２－２)営業日計算.roll(this.get買付受渡日移動日数)を戻す<BR>
     * <BR>
     * ３）引数.債券注文種別判定is売却注文の場合<BR>
     * 　@３－１）new 営業日計算(new java.sql.Timestamp(引数.現地約定日.getTime()))<BR>
     * <BR>
     * 　@３－２)営業日計算.roll(this.get売却受渡日移動日数)を戻す<BR>
     * @@param l_datForeignExecDate - (現地約定日)<BR>
     * 現地約定日<BR>
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@return java.util.Date
     * @@roseuid 44C4BB3A0284
     */
    public Date getForeignDeliveryDate(Date l_datForeignExecDate,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForeignDeliveryDate(" +
        "Date, WEB3BondOrderTypeJudge)";
        log.entering(STR_METHOD_NAME);

        if (l_datForeignExecDate == null || l_bondOrderTypeJudge == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        Date l_datTmp = null;

        //１）引数.債券注文種別判定is応募注文()の場合
        if (l_bondOrderTypeJudge.isRecruitOrder())
        {
            //１－１）this.get発行日()を戻す
            l_datTmp = this.getIssueDate();
        }

        //２）引数.債券注文種別判定.is買付注文()の場合
        if (l_bondOrderTypeJudge.isBuyOrder())
        {
            //２－１）new 営業日計算(new java.sql.Timestamp(引数.現地約定日.getTime()))
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datForeignExecDate.getTime()));

            //２－２)営業日計算.roll(this.get買付受渡日移動日数)を戻す
            Date l_datBizDate = l_gentradeBizDate.roll(this.getBuyDeliveryDateShiftDays());
            l_datTmp = l_datBizDate;
        }

        //３）引数.債券注文種別判定is売却注文の場合
        if (l_bondOrderTypeJudge.isSellOrder())
        {
            //３－１）new 営業日計算(new java.sql.Timestamp(引数.現地約定日.getTime()))
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    new Timestamp(l_datForeignExecDate.getTime()));

            //３－２)営業日計算.roll(this.get売却受渡日移動日数)を戻す
            Date l_datBizDate = l_gentradeBizDate.roll(this.getSellDeliveryDateShiftDays());
            l_datTmp = l_datBizDate;
        }
        log.exiting(STR_METHOD_NAME);
        return l_datTmp;
    }

    /**
     * (is応募可能)<BR>
     * 債券銘柄が応募可能銘柄かどうかをチェックする。 <BR>
     * <BR>
     * １）売買区分を取得する。<BR>
     * 　@　@this.getDataSourceObject().get売買区分を取得する。<BR>
     * <BR>
     * ２）売買区分 ≠ ”応募” の場合、falseを返却する。<BR>
     * <BR>
     * ３）売買区分 == ”応募” の場合、以下の処理を実施する。<BR>
     * <BR>
     * ３－１）　@現在日時を取得する。 <BR>
     * 　@　@　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。 <BR>
     * <BR>
     * ３－２）　@応募開始日を取得する。 <BR>
     * 　@this.getDataSourceObject().get応募開始日()をコールして、応募開始日を取得する。 <BR>
     * <BR>
     * ３－３）　@応募終了日を取得する。 <BR>
     * 　@this.getDataSourceObject().get応募終了日()をコールして、応募終了日を取得する。 <BR>
     * <BR>
     * ３－４）　@応募開始日あるいは応募終了日が設定されていない場合は false を返す。 <BR>
     * <BR>
     * ３－５）　@以下の条件に合致する場合は true を、そうでない場合は false を返す。 <BR>
     * <BR>
     * 　@　@応募開始日 ≦ 現在日時 　@かつ　@現在日時 ≦ 応募終了日 <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException 
     * @@roseuid 44C563D6036B
     */
    public boolean isRecruitPossible() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isRecruitPossible()";
        log.entering(STR_METHOD_NAME);

        //flag
        boolean l_blnRecruitPossible = false;
        String l_strTradeType = "";

        //１）売買区分を取得する。
        //   this.getDataSourceObject().get売買区分を取得する。
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        l_strTradeType = l_bondProduct.getTradeType();

        //２）売買区分 ≠ ”応募” の場合、falseを返却する。
        if (!WEB3BondTradeTypeDef.RECRUIT.equals(l_strTradeType))
        {
            l_blnRecruitPossible = false;
        }

        // ３）売買区分 == ”応募” の場合、以下の処理を実施する。
        if (WEB3BondTradeTypeDef.RECRUIT.equals(l_strTradeType))
        {
            //３－１）　@発注日を取得する。
            //       取引時間管理.get発注日()をコールし、発注日を取得する。
            Date l_datCurrentDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(); //GtlUtils.getSystemTimestamp();

             //３－２）　@応募開始日を取得する。
             //   this.getDataSourceObject().get応募開始日()をコールして、応募開始日を取得する。
            Date l_datRecruitStartDate = l_bondProduct.getRecruitStartDate();
            log.debug("getRecruitStartDate::::::::" + l_datRecruitStartDate);

            //３－３）　@応募終了日を取得する。
            // 　@this.getDataSourceObject().get応募終了日()をコールして、応募終了日を取得する。
            Date l_datRecruitEndDate = l_bondProduct.getRecruitEndDate();
            log.debug("l_datRecruitEndDate::::::::" + l_datRecruitEndDate);

            //３－４）　@応募開始日あるいは応募終了日が設定されていない場合は false を返す。
            if ((l_datRecruitStartDate == null) || (l_datRecruitEndDate == null))
            {
                l_blnRecruitPossible = false;
                return l_blnRecruitPossible;
            }

            //３－５）　@以下の条件に合致する場合は true を、そうでない場合は false を返す。
            //           応募開始日 ≦ 発注日 　@かつ　@発注日 ≦ 応募終了日
            if ((WEB3DateUtility.compareToDay(l_datCurrentDate, l_datRecruitStartDate) >= 0) &&
                (WEB3DateUtility.compareToDay(l_datRecruitEndDate, l_datCurrentDate) >= 0))
            {
                l_blnRecruitPossible = true;
            }
            else
            {
                l_blnRecruitPossible = false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnRecruitPossible;
    }

    /**
     * (is買付可能)<BR>
     * 債券銘柄が買付可能銘柄かどうかをチェックする。 <BR>
     * <BR>
     * １）売買区分を取得する。<BR>
     * 　@　@this.getDataSourceObject().get売買区分を取得する。<BR>
     * <BR>
     * ２）売買区分 == ”買付” or ”買付/売却”の場合、trueを返却する。<BR>
     * 　@　@それ以外の場合、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 44C563D603C8
     */
    public boolean isBuyPossible()
    {
        final String STR_METHOD_NAME = "isBuyPossible()";
        log.entering(STR_METHOD_NAME);

        //１）売買区分を取得する。
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeType = l_bondProduct.getTradeType();

        //２）売買区分 == ”買付” or ”買付/売却”の場合、trueを返却する。
        if ((WEB3BondTradeTypeDef.BUY.equals(l_strTradeType)) ||
            (WEB3BondTradeTypeDef.BUY_SELL.equals(l_strTradeType)))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is管理者取扱可能)<BR>
     * is管理者取扱可能<BR>
     * <BR>
     * 当債券銘柄が管理者取扱可能かをチェックする。 <BR>
     * <BR>
     * １）取扱区分を取得する。<BR>
     * 　@　@this.get取扱区分()<BR>
     * <BR>
     * ２）取扱区分 ＝ ”不可”の場合、falseを返却する。<BR>
     * <BR>
     * ３） ２）以外 の場合、trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 44C73F59001C
     */
    public boolean isAdminTradePossible()
    {
        final String STR_METHOD_NAME = "isAdminTradePossible()";
        log.entering(STR_METHOD_NAME);

        String l_strTradeHandleDiv = this.getTradeHandleDiv();
        if (WEB3TradeHandleDivDef.DISABLED.equals(l_strTradeHandleDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

    }

    /**
     * (is顧客取扱可能)<BR>
     * 顧客が取扱可能な銘柄かどうかチェックする。<BR>
     * <BR>
     * １）取扱区分を取得する。<BR>
     * 　@　@this.getDataSourceObject().get取扱区分を取得する。<BR>
     * <BR>
     * ２）取扱区分 ≠ "管理者/顧客"の場合、falseを返却する。<BR>
     * <BR>
     * ３）取扱区分 == "管理者/顧客"の場合、以下の処理を実施する。<BR>
     * <BR>
     * ３－１）　@現在日時を取得する。 <BR>
     * 　@　@　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。 <BR>
     * <BR>
     * ３－２）　@取扱開始日を取得する。 <BR>
     * 　@this.getDataSourceObject().get取扱開始日()をコールして、取扱開始日を取得する。 <BR>
     * <BR>
     * ３－３）　@取扱終了日を取得する。 <BR>
     * 　@this.getDataSourceObject().get取扱終了日()をコールして、取扱終了日を取得する。 <BR>
     * <BR>
     * ３－４）　@取扱開始日あるいは取扱終了日が設定されていない場合は false を返す。 <BR>
     * <BR>
     * ３－５）　@以下の条件に合致する場合は true を、そうでない場合は false を返す。 <BR>
     * <BR>
     * 　@　@取扱開始日 ≦ 現在日時 　@かつ　@現在日時 ＜ 取扱終了日 <BR>
     * @@return boolean
     * @@roseuid 44C960C80213
     */
    public boolean isCustomerTradePossible()
    {
        final String STR_METHOD_NAME = "isCustomerTradePossible()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnCustomerTradePossible = false;
        //１）取扱区分を取得する。
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeHandleDiv = l_bondProduct.getTradeHandleDiv();

        //２）取扱区分 ≠ "管理者/顧客"の場合、falseを返却する。
        if (!WEB3TradeHandleDivDef.MANAGER_CUSTOMER.equals(l_strTradeHandleDiv))
        {
            l_blnCustomerTradePossible = false;
        }

        //３）取扱区分 == "管理者/顧客"の場合、以下の処理を実施する。
        if (WEB3TradeHandleDivDef.MANAGER_CUSTOMER.equals(l_strTradeHandleDiv))
        {
            //３－１）　@現在日時を取得する。
            //       ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。
            Date l_datCurrentDate = GtlUtils.getSystemTimestamp();

             //３－２）　@取扱開始日を取得する。
             //   this.getDataSourceObject().get取扱開始日()をコールして、取扱開始日を取得する。
            Date l_datTradeStartDate = l_bondProduct.getTradeStartDate();

            //３－３）　@取扱終了日を取得する。
            // 　@this.getDataSourceObject().get取扱終了日()をコールして、取扱終了日を取得する。
            Date l_datTradeEndDate = l_bondProduct.getTradeEndDate();

            //３－４）　@　@取扱開始日あるいは取扱終了日が設定されていない場合は false を返す。
            if ((l_datTradeStartDate == null) || (l_datTradeEndDate == null))
            {
                l_blnCustomerTradePossible = false;
                return l_blnCustomerTradePossible;
            }

            //３－５）　@以下の条件に合致する場合は true を、そうでない場合は false を返す。
            //          取扱開始日 ≦ 現在日時 　@かつ　@現在日時 ＜ 取扱終了日
            if ((WEB3DateUtility.compareToSecond(l_datCurrentDate, l_datTradeStartDate) >= 0) &&
                (WEB3DateUtility.compareToSecond(l_datTradeEndDate, l_datCurrentDate) > 0))
            {
                l_blnCustomerTradePossible = true;
            }
            else
            {
                l_blnCustomerTradePossible = false;
            }

        }
        log.exiting(STR_METHOD_NAME);
        return l_blnCustomerTradePossible;
    }

    /**
     * (get通貨)<BR>
     * get通貨<BR>
     * <BR>
     * （共通）通貨（証券会社コード, 通貨コード）で生成したオブジェクトを返す。<BR>
     * <BR>
     * ※）通貨コード == null or 通貨コード == "T0" の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02660<BR>
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 44CAFA9C01F5
     */
    public WEB3GentradeCurrency getCurrency() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrency()";
        log.entering(STR_METHOD_NAME);

        String l_strCurrencyCode = this.getCurrencyCode();
        if (WEB3GentradeCurrencyCodeDef.JPY.equals(l_strCurrencyCode) ||
                l_strCurrencyCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02660,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "通貨コードが未指定(null)または不正な値です。");
        }
        WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
            this.getInstitution().getInstitutionCode(),
            this.getCurrencyCode());

        log.exiting(STR_METHOD_NAME);
        return l_currency;
    }

    /**
     * (is売却可能)<BR>
     * 債券銘柄が売却可能銘柄かどうかをチェックする。 <BR>
     * <BR>
     * １）売買区分を取得する。<BR>
     * 　@　@this.getDataSourceObject().get売買区分を取得する。<BR>
     * <BR>
     * ２）売買区分 == ”売却” or ”買付/売却”の場合、trueを返却する。<BR>
     * 　@　@それ以外の場合、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 44D840500102
     */
    public boolean isSellPossible()
    {
        final String STR_METHOD_NAME = "isSellPossible()";
        log.entering(STR_METHOD_NAME);

        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        String l_strTradeType = l_bondProduct.getTradeType();

        if ((WEB3BondTradeTypeDef.SELL.equals(l_strTradeType)) ||
            (WEB3BondTradeTypeDef.BUY_SELL.equals(l_strTradeType)))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is経過利子計算警告判定)<BR>
     * 経過利子計算の警告判定を行なう。<BR>
     * <BR>
     * (1)　@以下のいずれかにあてはまる場合はfalseを返却する。<BR>
     * 　@・債券銘柄.特殊利払区分　@!=　@ ”特殊利払日無し”　@の場合 <BR>
     * 　@・債券銘柄.年間利払回数　@==　@ ”不定”(99999999)　@の場合 <BR>
     * 　@・債券銘柄.利付タイプ　@==　@”変動利付債”　@の場合 <BR>
     * <BR>
     * (2)　@上記以外はtrueを返却する。 <BR>
     * @@return boolean
     */
    public boolean isAccruedInterestCalcWarningJudge()
    {
        final String STR_METHOD_NAME = "isAccruedInterestCalcWarningJudge()";
        log.entering(STR_METHOD_NAME);

        //(1)　@以下のいずれかにあてはまる場合はfalseを返却する。
        int l_intYearlyInterestPayments = this.getYearlyInterestPayments();
        if (!WEB3SpecialPaymentDivDef.NO_SPECIAL_PAYMENT_DAY.equals(this.getSpecialPaymentDiv())
            || l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.NO_FIXED_TIME)
            || CouponTypeEnum.FLOATING_COUPON.equals(this.getCouponType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //(2)　@上記以外はtrueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;

    }
    /**
     * (get入金日)<BR>
     * get入金日 <BR>
     * <BR>
     * １）債券部店別条件を生成する。<BR>
     * 　@[引数] <BR>
     * 　@　@部店ID：引数.部店.部店ID<BR>
     * <BR>
     * ２）債券部店別条件.get入金日設定区分＝＝'約定日基準'<BR>
     * 　@　@かつ <BR>
     * 　@　@引数.債券注文種別判定.is応募＝＝true　@の場合<BR>
     * 　@２－１）引数.決済区分＝＝'円貨' <BR>
     * 　@　@　@　@　@new 営業日計算(new java.sql.Timestamp(引数.約定日.getTime()))<BR>
     * 　@　@　@　@　@入金日＝営業日計算.roll(1) <BR>
     * <BR>
     * 　@２－２)上記以外の場合<BR>
     * 　@　@　@　@　@入金日＝new Date(引数.約定日.getTime())<BR>
     * <BR>
     * 　@２－３)入金日＞this.get発行日　@の場合<BR>
     * 　@　@　@　@　@入金日＝new Date(引数.約定日.getTime())<BR>
     * <BR>
     * ３）債券部店別条件.get入金日設定区分＝＝'登録日基準'<BR>
     * 　@　@かつ<BR>
     * 　@　@引数.債券注文種別判定.is応募＝＝true　@の場合<BR>
     * 　@　@　@　@３－１）債券銘柄マスター「受渡日」がnulllでない場合、<BR>
     * 　@　@　@　@　@　@入金日＝this.get受渡日<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@なし<BR>
     * 　@　@　@　@３－２）債券銘柄マスター「受渡日」がnulllの場合、<BR>
     * <BR>
     * 　@　@　@　@　@３－２－１）引数.決済区分＝＝'円貨'<BR>
     * 　@　@　@　@　@　@　@new 営業日計算(new java.sql.Timestamp(引数.約定日.getTime()))<BR>
     * 　@　@　@　@　@　@　@入金日＝営業日計算.roll(1)<BR>
     * <BR>
     * 　@　@　@　@　@３－２－２)上記以外の場合<BR>
     * 　@　@　@　@　@　@　@入金日＝new Date(引数.約定日.getTime())<BR>
     * <BR>
     * 　@　@　@　@　@３－２－３)入金日＞this.get発行日　@の場合<BR>
     * 　@　@　@　@　@　@　@入金日＝new Date(引数.約定日.getTime())<BR>
     * <BR>
     * ４）上記以外の場合<BR>
     * 　@入金日＝this.get受渡日<BR>
     * 　@　@[引数] <BR>
     * 　@　@　@約定日：引数.約定日<BR>
     * 　@　@　@債券注文種別判定：引数.債券注文種別判定<BR>
     * <BR>
     * ５）入金日を戻す<BR>
     * @@param l_datExecDate - (約定日)<BR>
     * 約定日
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定
     * @@param l_strSettlementDiv - (決済区分)<BR>
     * 決済区分
     * @@param l_branch - (部店)<BR>
     * 部店
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getPaymentDate(
        Date l_datExecDate, WEB3BondOrderTypeJudge l_bondOrderTypeJudge,
        String l_strSettlementDiv, Branch l_branch) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentDate(Date, WEB3BondOrderTypeJudge, String, Branch)";
        log.entering(STR_METHOD_NAME);

        if (l_bondOrderTypeJudge == null || l_branch == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        Date l_datPaymentDate = null;
        //１）債券部店別条件を生成する。
        WEB3BondBranchCondition l_branchCondition =
            new WEB3BondBranchCondition(l_branch.getBranchId());

        //２）債券部店別条件.get入金日設定区分＝＝'約定日基準'
        if (WEB3PaymentDateDetDivDef.EXECUTE_DATE_BASE.equals(l_branchCondition.getPaymentDateSetDiv()) &&
            l_bondOrderTypeJudge.isRecruitOrder())
        {
            //２－１）引数.決済区分＝＝'円貨'
            //  new 営業日計算(new java.sql.Timestamp(引数.約定日.getTime()))
            //  入金日＝営業日計算.roll(1)
            if (WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_strSettlementDiv))
            {
                WEB3GentradeBizDate l_gentradeBizDate =
                    new WEB3GentradeBizDate(
                        new Timestamp(l_datExecDate.getTime()));
                l_datPaymentDate = l_gentradeBizDate.roll(1);
            }

            //２－２)上記以外の場合
            //   入金日＝new Date(引数.約定日.getTime())
            else
            {
                l_datPaymentDate = new Timestamp(l_datExecDate.getTime());
            }

            //２－３)入金日＞this.get発行日　@の場合
            //   入金日＝new Date(引数.約定日.getTime())
            if (WEB3DateUtility.compareToDay(
                l_datPaymentDate, new Timestamp(this.getIssueDate().getTime())) > 0)
            {
                l_datPaymentDate = new Timestamp(l_datExecDate.getTime());
            }
        }

        //３）債券部店別条件.get入金日設定区分＝＝'登録日基準'
        //かつ
        //引数.債券注文種別判定.is応募＝＝true　@の場合
        else if (WEB3PaymentDateDetDivDef.REGIST_DATE_BASE.equals(
            l_branchCondition.getPaymentDateSetDiv())
            && l_bondOrderTypeJudge.isRecruitOrder())
        {
            //３－１）債券銘柄マスター「受渡日」がnulllでない場合、
            //入金日＝this.get受渡日
            //[引数]
            //なし
            if (this.getDeliveryDate() != null)
            {
                l_datPaymentDate = this.getDeliveryDate();
            }
            //３－２）債券銘柄マスター「受渡日」がnulllの場合、
            else
            {
                //３－２－１）引数.決済区分＝＝'円貨'
                //new 営業日計算(new java.sql.Timestamp(引数.約定日.getTime()))
                //入金日＝営業日計算.roll(1)
                if (WEB3BondOrderSettleDivDef.YEN_CURRENCY.equals(l_strSettlementDiv))
                {
                    WEB3GentradeBizDate l_gentradeBizDate =
                        new WEB3GentradeBizDate(
                            new Timestamp(l_datExecDate.getTime()));
                    l_datPaymentDate = l_gentradeBizDate.roll(1);
                }
                //３－２－２)上記以外の場合
                //入金日＝new Date(引数.約定日.getTime())
                else
                {
                    l_datPaymentDate = new Timestamp(l_datExecDate.getTime());
                }

                //３－２－３)入金日＞this.get発行日　@の場合
                //入金日＝new Date(引数.約定日.getTime())
                if (WEB3DateUtility.compareToDay(
                    l_datPaymentDate, new Timestamp(this.getIssueDate().getTime())) > 0)
                {
                    l_datPaymentDate = new Timestamp(l_datExecDate.getTime());
                }
            }
        }
        //    ４）上記以外の場合
        //   　@入金日＝this.get受渡日
        //   　@　@[引数]
        //   　@　@　@約定日：引数.約定日
        //   　@　@　@債券注文種別判定：引数.債券注文種別判定
        else
        {
            l_datPaymentDate = this.getDeliveryDate(l_datExecDate, l_bondOrderTypeJudge);
        }

        //５）入金日を戻す
        log.exiting(STR_METHOD_NAME);
        return l_datPaymentDate;
    }

    /**
     * (get仕入時の為替レート)<BR>
     * 仕入時の為替レートを返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get仕入時の為替レート()の戻り値を返す。 <BR>
     * @@return double
     */
    public double getBuyingFxRate()
    {
        //仕入時の為替レートを返す。
        //this.getDataSourceObject().get仕入時の為替レート()の戻り値を返す。
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        return l_bondProduct.getBuyingFxRate();
    }

    /**
     * (get取引時間チェック区分)<BR>
     * 取引時間チェック区分を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get取引時間チェック区分()の戻り値を返す。<BR>
     * @@return String
     */
    public String getTradingTimeCheckDiv()
    {
        //取引時間チェック区分を返す。
        //this.getDataSourceObject().get取引時間チェック区分()の戻り値を返す。
        BondProductRow l_bondProduct =  (BondProductRow)getDataSourceObject();
        return l_bondProduct.getTradingTimeCheckDiv();
    }

    /**
     * (get受渡日)<BR>
     * 債券銘柄の受渡日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get受渡日()の戻り値を返す。<BR>
     * @@return Date
     */
    public Date getDeliveryDate()
    {
        //債券銘柄の受渡日を返す。
        //this.getDataSourceObject().get受渡日()の戻り値を返す。
        BondProductRow l_bondProduct = (BondProductRow)getDataSourceObject();
        return l_bondProduct.getDeliveryDate();
    }

    /**
     * (get最小発行券種)<BR>
     * 債券銘柄の最小発行券種を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最小発行券種()の戻り値を返す。<BR>
     * @@return String
     */
    public String getMinIssueCouponType()
    {
        //債券銘柄の最小発行券種を返す
        //this.getDataSourceObject().get最小発行券種()の戻り値を返す
        BondProductRow l_bondProduct = (BondProductRow)getDataSourceObject();
        return l_bondProduct.getMinIssueCouponType();
    }

    /**
     * (get目論見書閲覧チェック区分)<BR>
     * 債券銘柄の目論見書閲覧チェック区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get目論見書閲覧チェック区分()の戻り値を返す。<BR>
     * @@return String
     */
    public String getProspectusCheckDiv()
    {
        //債券銘柄の目論見書閲覧チェック区分を返す
        //this.getDataSourceObject().get目論見書閲覧チェック区分()の戻り値を返す
        BondProductRow l_bondProduct = (BondProductRow)getDataSourceObject();
        return l_bondProduct.getProspectusCheckDiv();
    }

    /**
     * (is個人向け国債)<BR>
     * 個人向け国債かどうか判定する。<BR>
     * <BR>
     * １）債券銘柄.債券タイプ==”11”（個人向け国債）の場合、trueを返却する。<BR>
     * <BR>
     * ２）１）以外の場合、falseを返却する<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isIndividualGovernmentBond()
    {
        final String STR_METHOD_NAME = " isIndividualGovernmentBond()";
        log.entering(STR_METHOD_NAME);

        //１）債券銘柄.債券タイプ==”11”（個人向け国債）の場合、trueを返却する。
        String l_strBondType = String.valueOf(this.getBondType().intValue());
        if (WEB3BondTypeListDef.INDIVIDUAL_GOVERNMENT_BOND.equals(l_strBondType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //２）１）以外の場合、falseを返却する
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is国内債券応募可能)<BR>
     * <BR>
     * 国内債券の応募可能な期間かどうかチェックする。<BR>
     * <BR>
     * １）売買区分を取得する。 <BR>
     * 　@　@this.get売買区分を取得する。 <BR>
     * <BR>
     * ２）売買区分 ≠ ”応募” の場合、falseを返却する。 <BR>
     * <BR>
     * ３）売買区分 == ”応募” の場合、以下の処理を実施する。 <BR>
     * <BR>
     * ４）注文チャネルを取得する。  <BR>
     * 　@　@セッションから注文チャネルを取得する。  <BR>
     * <BR>
     * ５）現在日時を取得する。  <BR>
     * 　@　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、<BR>
     * 　@　@　@現在日時を取得する。  <BR>
     * <BR>
     * ６）注文チャネル == "コールセンター"or"営業店"の場合、<BR>
     * 　@　@以下の処理を実施する。 <BR>
     * <BR>
     * 　@６-１）　@取扱開始日時を取得する。  <BR>
     * this.get取扱開始日時()をコールして、取扱開始日時を取得する。  <BR>
     * <BR>
     * 　@６-２）　@取扱終了日時を取得する。  <BR>
     * this.get取扱終了日時()をコールして、取扱終了日時を取得する。  <BR>
     * <BR>
     * 　@６-３）　@取扱開始日時あるいは取扱終了日時が設定されていない<BR>
     * 　@　@　@　@　@　@場合は false を返す。  <BR>
     * <BR>
     * 　@６-４）　@以下の条件に合致する場合は true を、そうでない場合は false を返す。  <BR>
     * <BR>
     * 取扱開始日時 ≦ 現在日時 　@かつ　@現在日時 ＜ 取扱終了日時  <BR>
     * <BR>
     * ７）それ以外の場合、以下の処理を実施する。  <BR>
     * <BR>
     * 　@７-１）　@応募開始日を取得する。  <BR>
     * this.get応募開始日()をコールして、応募開始日を取得する。  <BR>
     * <BR>
     * 　@７-２）　@応募終了日を取得する。  <BR>
     * this.get応募終了日()をコールして、応募終了日を取得する。  <BR>
     * <BR>
     * 　@７-３）　@応募開始日あるいは応募終了日が設定されていない<BR>
     * 　@　@　@　@　@場合は false を返す。  <BR>
     * <BR>
     * 　@７-４）　@以下の条件に合致する場合は true を、そうでない場合は false を返す。  <BR>
     * <BR>
     * 応募開始日 ≦ 現在日時 　@かつ　@現在日時 ＜ 応募終了日 <BR>
     * <BR>
     * @@return boolean
     */
    public boolean isBondDomesticApplyPossible()
    {
        final String STR_METHOD_NAME = " isBondDomesticApplyPossible()";
        log.entering(STR_METHOD_NAME);

        //１）売買区分を取得する。
        //this.get売買区分を取得する。
        String l_strTradeType = this.getTradeType();

        //２）売買区分 ≠ ”応募” の場合、falseを返却する。
        if (!WEB3BondTradeTypeDef.RECRUIT.equals(l_strTradeType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //３）売買区分 == ”応募” の場合、以下の処理を実施する。
        //４）注文チャネルを取得する。
        //セッションから注文チャネルを取得する。
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

        //５）現在日時を取得する。
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

        //６）注文チャネル == "コールセンター"or"営業店"の場合、以下の処理を実施する。
        if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChanel)
            || WEB3ChannelDef.BRANCH.equals(l_strOrderChanel))
        {
            //６-１）　@取扱開始日時を取得する。
            Date l_datTradeStartDate = this.getTradeStartDate();

            //６-２）　@取扱終了日時を取得する。
            Date l_datTradeEndDate = this.getTradeEndDate();

            //６-３）　@取扱開始日時あるいは取扱終了日時が設定されていない場合は false を返す。
            if (l_datTradeStartDate == null || l_datTradeEndDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            //６-４）　@以下の条件に合致する場合は true を、そうでない場合は false を返す。
            //取扱開始日時 ≦ 現在日時 　@かつ　@現在日時 ＜ 取扱終了日時
            if (WEB3DateUtility.compareToSecond(l_datTradeStartDate, l_tmsSystemTimestamp) <= 0
                && WEB3DateUtility.compareToSecond(l_tmsSystemTimestamp, l_datTradeEndDate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        else
        {
            //７）それ以外の場合、以下の処理を実施する。
            //７-１）　@応募開始日を取得する。
            Date l_datRecruitStartDate = this.getRecruitStartDate();

            //７-２）　@応募終了日を取得する。
            Date l_datRecruitEndDate = this.getRecruitEndDate();

            //７-３）　@応募開始日あるいは応募終了日が設定されていない場合は false を返す。
            if (l_datRecruitStartDate == null
                || l_datRecruitEndDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            //７-４）　@以下の条件に合致する場合は true を、そうでない場合は false を返す。
            //応募開始日 ≦ 現在日時 　@かつ　@現在日時 ＜ 応募終了日
            if (WEB3DateUtility.compareToSecond(l_datRecruitStartDate, l_tmsSystemTimestamp) <= 0
                && WEB3DateUtility.compareToSecond(l_tmsSystemTimestamp, l_datRecruitEndDate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
    }

    /**
     * (get国内債券発注日)<BR>
     * get国内債券発注日<BR>
     * <BR>
     * 取扱終了日時を返却する。  <BR>
     * this.getDataSourceObject().get取扱終了日時()をコールして、取扱終了日時を取得する。  <BR>
     * ※YYYY/MM/DDのみ <BR>
     * @@return Date
     */
    public Date getBondDomesticBizDate()
    {
        final String STR_METHOD_NAME = " getBondDomesticBizDate()";
        log.entering(STR_METHOD_NAME);

        //取扱終了日時を返却する
        //this.getDataSourceObject().get取扱終了日時()をコールして、取扱終了日時を取得する
        BondProductRow l_bondProductRow =  (BondProductRow)this.getDataSourceObject();
        Date l_datTradeEndDate = l_bondProductRow.getTradeEndDate();

        log.exiting(STR_METHOD_NAME);
        return WEB3DateUtility.toDay(l_datTradeEndDate);
    }

    /**
     * (get応募勧誘形式)<BR>
     * 債券銘柄の応募勧誘形式を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get応募勧誘形式()の戻り値を返す。<BR>
     * @@return String
     */
    public String getRecruitInvitationDiv()
    {
        final String STR_METHOD_NAME = "getRecruitInvitationDiv()";
        log.entering(STR_METHOD_NAME);

        //債券銘柄の応募勧誘形式を返す。
        //this.getDataSourceObject().get応募勧誘形式()の戻り値を返す。
        BondProductRow l_bondProductRow = (BondProductRow)this.getDataSourceObject();

        log.exiting(STR_METHOD_NAME);
        return l_bondProductRow.getRecruitInvitationDiv();
    }

    /**
     * (get応募引受け区分)<BR>
     * 債券銘柄の応募引受け区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get応募引受け区分()の戻り値を返す。<BR>
     * @@return String
     */
    public String getRecruitAcceptDiv()
    {
        final String STR_METHOD_NAME = "getRecruitAcceptDiv()";
        log.entering(STR_METHOD_NAME);

        //債券銘柄の応募引受け区分を返す。
        //this.getDataSourceObject().get応募引受け区分()の戻り値を返す。
        BondProductRow l_bondProductRow = (BondProductRow)this.getDataSourceObject();

        log.exiting(STR_METHOD_NAME);
        return l_bondProductRow.getRecruitAcceptDiv();
    }
}
@
