head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeCommission.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料(WEB3GentradeCommission.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 李綱 (中訊) 新規作成
Revesion History : 2006/05/11 凌建平 (中訊)【共通】仕様変更・モデルNo.187
Revesion History : 2007/10/10 栄イ (中訊)【共通】仕様変更・モデルNo.278
*/
package webbroker3.gentrade;

import java.sql.Timestamp;

/**
 * (手数料)<BR>
 * <BR>
 * 委託手数料の情報を表現する<BR>
 */
public class WEB3GentradeCommission
{

    /**
     * 証券会社コード<BR>
     */
    private String institutionCode;

    /**
     * 部店ID<BR>
     */
    private long branchId;

    /**
     * 手数料商品コード<BR>
     * <BR>
     * （会社部店商品テーブルの手数料商品コード）<BR>
     */
    private String commissionProductCode;

    /**
     * 取引コード（SONAR）<BR>
     * <BR>
     * 11：普通株式<BR>
     * 16：立会外分売<BR>
     * 51：信用建<BR>
     * 52：信用返済<BR>
     * 53：現引現渡<BR>
     * 92：場外取引(＝JASDAQ)<BR>
     */
    private String sonarTradedCode;
    
    /**
     * 市場コード（SONAR）<BR>
     * ※null可（設定時のみ、委託手数料枝番登録マスターから<BR>
     * の取得条件に使用する）<BR>
     * <BR>
     * 1：東京<BR>
     * 2：大阪<BR>
     * 3：名古屋<BR>
     * 4：札幌<BR>
     * 5：NNM<BR>
     * 6：JASDAQ<BR>
     * 9：福岡<BR>
     */
    private String sonarMarketCode;

    /**
     * 発注日<BR>
     */
    private Timestamp orderBizDate;

    /**
     * 弁済区分<BR>
     * <BR>
     * （委託手数料枝番登録マスター.弁済区分）<BR>
     * 1：制度信用　@3：一般信用　@00：その他<BR>
     */
    private String payType;

    /**
     * 注文チャネル<BR>
     */
    private String orderChannel;

    /**
     * (is指値)<BR>
     * <BR>
     * 指値注文の場合、true。成行注文の場合、false。<BR>
     */
    private boolean isLimitPrice;

    /**
     * (手数料No)<BR>
     * <BR>
     * 手数料計算に使用した手数料No。<BR>
     * calc委託手数料が返却する。<BR>
     */
    private String commissionNo;

    /**
     * (手数料No枝番)<BR>
     * <BR>
     * 手数料計算に使用した手数料No枝番。<BR>
     * calc委託手数料が返却する。<BR>
     */
    private String commissionRevNo;

    /**
     * (手数料金額)<BR>
     * <BR>
     * 委託手数料を表現する。<BR>
     * calc委託手数料が返却する。<BR>
     */
    private double commission;

    /**
     * (諸経費計算用代金)<BR>
     * <BR>
     * 諸経費計算用代金。買いであれば拘束売買代金、<BR>
     * 売りであれば売買代金、約定であれば約<BR>
     * 定代金を設定する。<BR>
     */
    private double expensesCalcAmount;

    /**
     * (原注文注文チャネル)<BR>
     */
    private String orgOrderChannel;

    /**
     * (原注文手数料No)<BR>
     * <BR>
     * 原注文の手数料を算出した際の手数料No。<BR>
     * 新規注文の場合は設定なし。<BR>
     */
    private String orgCommissionNo;

    /**
     * (原注文手数料No枝番)<BR>
     * <BR>
     * 原注文の手数料を算出した際の手数料No枝番。<BR>
     * 新規注文の場合は設定なし。<BR>
     */
    private String orgCommissionRevNo;
    
    /**
     * (手数料コースコード)<BR>
     * <BR>
     * 02：　@定率手数料（スタンダード）<BR>
     * 12：　@定率手数料（ハイパーボックス）※徴収率 = 0% <BR>
     * 03：　@約定代金合計 <BR>
     * 04：　@約定回数 <BR>
     * 05：　@一日定額制<BR>
     */
    private String commissionCourseDiv;
    
    /**
     * (最低手数料)<BR>
     * <BR>
     * 最低手数料を表現する。<BR>
     * calc委託手数料が返却する。<BR>
     */
    private double minCommission;

    /**
     * (原資産銘柄コード)<BR>
     * 原資産銘柄コード<BR>
     * <BR>
     * ※null可（設定時のみ、委託手数料枝番登録マスターからの取得条件に使用する）<BR>
     * <BR>
     * 0005：TOPIX <BR>
     * 0016：日経300 <BR>
     * 0018：日経225 <BR>
     * 0019：ミニ日経225 <BR>
     */
    private String underlyingProductCode;

    /**
     * (日計り区分)<BR>
     * 日計り区分<BR>
     * <BR>
     * ※null可（設定時のみ、委託手数料枝番登録マスターからの取得条件に使用する）<BR>
     * <BR>
     * 1：日計り以外 <BR>
     * 5：日計り <BR>
     */
    private String dayTradeType;

    /**
     * (手数料乗数)<BR>
     * 手数料乗数<BR>
     * <BR>
     * 枚数計算（※）の場合、1枚当りの手数料が設定。<BR>
     * 受渡代金による手数料計算の場合、ゼロが設定。<BR>
     * <BR>
     * ※先物である特定の枝番の場合のみ使用。<BR>
     */
    private double commitionPerUnit;

    /**
     * (数量)<BR>
     * 数量<BR>
     * <BR>
     * ※手数料乗数（1枚当りの手数料）が設定されている場合に使用。<BR>
     */
    private double quantity;

    /**
     * (消費税)<BR>
     * 消費税<BR>
     */
    private double consumptionTax;

    /**
     * コンストラクタ<BR>
     * @@return webbroker3.equity.WEB3EquityCommission
     * @@roseuid 405FD0620196
     */
    public WEB3GentradeCommission()
    {

    }

    /**
     * (set証券会社コード)<BR>
     * <BR>
     * 証券会社IDをセットする。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@roseuid 4019DCF60109
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * (get証券会社コード)<BR>
     * <BR>
     * 証券会社IDを取得する。<BR>
     * @@return long
     * @@roseuid 4019DD25038A
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * (set部店ID)<BR>
     * <BR>
     * 部店IDをセットする。<BR>
     * @@param l_lngBranchId - 部店ID
     * @@roseuid 4019E86303C9
     */
    public void setBranchId(long l_lngBranchId)
    {
        this.branchId = l_lngBranchId;
    }

    /**
     * (get部店ID)<BR>
     * <BR>
     * 部店IDを取得する。<BR>
     * @@return long
     * @@roseuid 4019E8640010
     */
    public long getBranchId()
    {
        return branchId;
    }

    /**
     * (set手数料商品コード)<BR>
     * <BR>
     * 手数料商品コードをセットする。<BR>
     * @@param l_strCommissionProductCode - 手数料商品コード
     * @@roseuid 4019E8660129
     */
    public void setCommissionProductCode(String l_strCommissionProductCode)
    {
        this.commissionProductCode = l_strCommissionProductCode;
    }

    /**
     * (get手数料商品コード)<BR>
     * <BR>
     * 手数料商品コードを取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 4019E8660139
     */
    public String getCommissionProductCode()
    {
        return commissionProductCode;
    }

    /**
     * (set取引コード（SONAR）)<BR>
     * <BR>
     * 取引コード（SONAR）をセットする。<BR>
     * @@param l_strTransactionType - 取引コード（SONAR）
     * @@roseuid 4019E8670213
     */
    public void setSonarTradedCode(String l_strSonarTradedCode)
    {
        this.sonarTradedCode = l_strSonarTradedCode;
    }

    /**
     * (get取引コード（SONAR）)<BR>
     * <BR>
     * 取引コード（SONAR）を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 4019E8670223
     */
    public String getSonarTradedCode()
    {
        return this.sonarTradedCode;
    }

    /**
     * (set発注日)<BR>
     * <BR>
     * 発注日をセットする。<BR>
     * @@param l_tsOrderBizDate - 発注日
     * @@roseuid 4019E95D01E5
     */
    public void setOrderBizDate(Timestamp l_tsOrderBizDate)
    {
        this.orderBizDate = l_tsOrderBizDate;
    }

    /**
     * (get発注日)<BR>
     * <BR>
     * 発注日を取得する。<BR>
     * @@return java.sql.Timestamp
     * @@roseuid 4019E96500DB
     */
    public Timestamp getOrderBizDate()
    {
        return orderBizDate;
    }

    /**
     * (set弁済区分)<BR>
     * <BR>
     * 弁済区分をセットする。<BR>
     * @@param l_strPayType - 弁済区分
     * @@roseuid 4019E99B0010
     */
    public void setPayType(String l_strPayType)
    {
        this.payType = l_strPayType;
    }

    /**
     * (get弁済区分)<BR>
     * <BR>
     * 弁済区分を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 4019E9BD003F
     */
    public String getPayType()
    {
        return payType;
    }

    /**
     * (set注文チャネル)<BR>
     * <BR>
     * 注文チャネルをセットする。<BR>
     * @@param l_strOrderChannel - 注文チャネル
     * @@roseuid 4019E9DA008D
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        this.orderChannel = l_strOrderChannel;
    }

    /**
     * (get注文チャネル)<BR>
     * <BR>
     * 注文チャネルを取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 4019E9FC007D
     */
    public String getOrderChannel()
    {
        return orderChannel;
    }

    /**
     * (setIs指値)<BR>
     * @@param l_blnIsLimitPrice - (is指値)
     * 
     * 指値注文の場合、true。成行注文の場合、false。
     * @@roseuid 4019EA0E038A
     */
    public void setIsLimitPrice(boolean l_blnIsLimitPrice)
    {
        this.isLimitPrice = l_blnIsLimitPrice;
    }

    /**
     * (is指値)<BR>
     * <BR>
     * 指値か、非指値（＝成行）かを取得する。<BR>
     * @@return boolean
     * @@roseuid 4019EA320262
     */
    public boolean isLimitPrice()
    {
        return isLimitPrice;
    }
    
    /**
     * (is成行)<BR>
     * <BR>
     * 成行か、非成行（＝指値）かを取得する。<BR>
     * is指値=falseならば、trueを返却する。以外、falseを返却する。<BR> 
     * @@return boolean
     * @@roseuid 4019EA320262
     */
    public boolean isMarketPrice()
    {
        if(isLimitPrice)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * (set手数料No)<BR>
     * <BR>
     * 手数料Noをセットする。<BR>
     * @@param l_strCommissionNo - 手数料No
     * @@roseuid 4019EA5E0213
     */
    public void setCommissionNo(String l_strCommissionNo)
    {
        this.commissionNo = l_strCommissionNo;
    }

    /**
     * (get手数料No)<BR>
     * <BR>
     * 手数料Noを取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 4019EA470204
     */
    public String getCommissionNo()
    {
        return commissionNo;
    }

    /**
     * (set手数料No枝番)<BR>
     * <BR>
     * 手数料No枝番をセットする。<BR>
     * @@param l_strCommissionRevNo - 手数料No枝番
     * @@roseuid 4019EA8A0281
     */
    public void setCommissionRevNo(String l_strCommissionRevNo)
    {
        this.commissionRevNo = l_strCommissionRevNo;
    }

    /**
     * (get手数料No枝番)<BR>
     * <BR>
     * 手数料No枝番を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 4019EA8A0290
     */
    public String getCommissionRevNo()
    {
        return commissionRevNo;
    }

    /**
     * (set手数料金額)<BR>
     * <BR>
     * 手数料金額をセットする。<BR>
     * @@param l_dblCommission - 手数料金額
     * @@roseuid 4019EB04002F
     */
    public void setCommission(double l_dblCommission)
    {
        this.commission = l_dblCommission;
    }

    /**
     * (get手数料金額)<BR>
     * <BR>
     * 手数料金額を取得する。<BR>
     * @@return double
     * @@roseuid 4019EB04004E
     */
    public double getCommission()
    {
        return commission;
    }

    /**
     * (set諸経費計算用代金)<BR>
     * <BR>
     * 諸経費計算用代金をセットする。<BR>
     * @@param l_dblExpensesAmount - 諸経費計算用代金
     * @@return java.lang.Void
     * @@roseuid 4019EB310187
     */
    public void setExpensesCalcAmount(double l_dblExpensesAmount)
    {
        this.expensesCalcAmount = l_dblExpensesAmount;
    }

    /**
     * (get諸経費計算用代金)<BR>
     * <BR>
     * 諸経費計算用代金を取得する。<BR>
     * @@return double
     * @@roseuid 4019EB3101A6
     */
    public double getExpensesCalcAmount()
    {
        return expensesCalcAmount;
    }

    /**
     * (set原注文注文チャネル)<BR>
     * <BR>
     * 原注文注文チャネルをセットする。<BR>
     * @@param l_strOrgOrderChannel - (原注文注文チャネル)<BR>
     * <BR>
     * 原注文注文チャネルを取得する。<BR>
     * @@roseuid 403D60FE0320
     */
    public void setOrgOrderChannel(String l_strOrgOrderChannel)
    {
        this.orgOrderChannel = l_strOrgOrderChannel;
    }

    /**
     * (get原注文注文チャネル)<BR>
     * <BR>
     * 原注文注文チャネルを取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 403D61150264
     */
    public String getOrgOrderChannel()
    {
        return orgOrderChannel;
    }

    /**
     * (set原注文手数料No)<BR>
     * <BR>
     * 原注文手数料Noをセットする。<BR>
     * @@param l_strOrgCommissionNo - 原注文手数料No
     * @@roseuid 4019EAAD01D5
     */
    public void setOrgCommissionNo(String l_strOrgCommissionNo)
    {
        this.orgCommissionNo = l_strOrgCommissionNo;
    }

    /**
     * (get原注文手数料No)<BR>
     * <BR>
     * 原注文手数料Noを取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 4019EAAD01E5
     */
    public String getOrgCommissionNo()
    {
        return orgCommissionNo;
    }

    /**
     * (set原注文手数料No枝番)<BR>
     * <BR>
     * 原注文手数料No枝番をセットする。<BR>
     * @@param l_strOrgCommissionRevNo - 原注文手数料No枝番
     * @@roseuid 4019EAD50168
     */
    public void setOrgCommissionRevNo(String l_strOrgCommissionRevNo)
    {
        this.orgCommissionRevNo = l_strOrgCommissionRevNo;
    }

    /**
     * (get原注文手数料No枝番)<BR>
     * <BR>
     * 原注文手数料No枝番を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 4019EAD50187
     */
    public String getOrgCommissionRevNo()
    {
        return orgCommissionRevNo;
    }
    
    /**
     * (set手数料コースコード )<BR>
     * <BR>
     * 手数料コースコードをセットする。<BR>
     * @@param l_strCommissionCourseDiv - 手数料コースコード
     */
    public void setCommissionCourseDiv(String l_strCommissionCourseDiv)
    {
        this.commissionCourseDiv = l_strCommissionCourseDiv;
    }
    
    /**
     * (get手数料コースコード)<BR>
     * <BR>
     * 手数料コースコードを取得する。<BR>
     * @@return java.lang.String
     */
    public String getCommissionCourseDiv()
    {
        return this.commissionCourseDiv;
    }
    
    /**
     * (set最低手数料)<BR>
     * <BR>
     * 最低手数料をセットする。<BR>
     * @@param l_dbMinCommission - 最低手数料
     */
    public void setMinCommission(double l_dbMinCommission)
    {
        this.minCommission = l_dbMinCommission;
    }
    
    /**
     * (get最低手数料)<BR>
     * <BR>
     * 最低手数料を取得する。<BR>
     * @@return double
     */
    public double getMinCommission()
    {
        return this.minCommission;
    }
    
    /**
     * (set市場コード（SONAR）)<BR>
     * <BR>
     * 市場コード（SONAR）をセットする。<BR>
     * @@param l_strSonarMarketCode - 市場コード（SONAR）
     */
    public void setSonarMarketCode(String l_strSonarMarketCode)
    {
        this.sonarMarketCode = l_strSonarMarketCode;
    }
    
    /**
     * (get市場コード（SONAR）)<BR>
     * <BR>
     * 市場コード（SONAR）を取得する。<BR>
     * @@return String
     */
    public String getSonarMarketCode()
    {
        return this.sonarMarketCode;
    }

    /**
     * (set原資産銘柄コード)<BR>
     * <BR>
     * 原資産銘柄コードをセットする。<BR>
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード
     */
    public void setUnderlyingProductCode(String l_strUnderlyingProductCode)
    {
        this.underlyingProductCode = l_strUnderlyingProductCode;
    }
    
    /**
     * (get原資産銘柄コード)<BR>
     * <BR>
     * 原資産銘柄コードを取得する。<BR>
     * @@return String
     */
    public String getUnderlyingProductCode()
    {
        return this.underlyingProductCode;
    }
    
    /**
     * (set日計り区分)<BR>
     * <BR>
     * 日計り区分をセットする。<BR>
     * @@param l_strDayTradeType - 日計り区分
     */
    public void setDayTradeType(String l_strDayTradeType)
    {
        this.dayTradeType = l_strDayTradeType;
    }
    
    /**
     * (get日計り区分)<BR>
     * <BR>
     * 日計り区分を取得する。<BR>
     * @@return String
     */
    public String getDayTradeType()
    {
        return this.dayTradeType;
    }

    /**
     * (set手数料乗数)<BR>
     * <BR>
     * 手数料乗数をセットする。<BR>
     * @@param l_dblCommitionPerUnit - 手数料乗数
     */
    public void setCommitionPerUnit(double l_dblCommitionPerUnit)
    {
        this.commitionPerUnit = l_dblCommitionPerUnit;
    }
    
    /**
     * (get手数料乗数)<BR>
     * <BR>
     * 手数料乗数を取得する。<BR>
     * @@return double
     */
    public double getCommitionPerUnit()
    {
        return this.commitionPerUnit;
    }
    
    /**
     * (set数量)<BR>
     * <BR>
     * 数量をセットする。<BR>
     * @@param l_dblQuantity - 数量
     */
    public void setQuantity(double l_dblQuantity)
    {
        this.quantity = l_dblQuantity;
    }
    
    /**
     * (get数量)<BR>
     * <BR>
     * 数量を取得する。<BR>
     * @@return double
     */
    public double getQuantity()
    {
        return this.quantity;
    }

    /**
     * (set消費税)<BR>
     * <BR>
     * 消費税をセットする。<BR>
     * @@param l_dblConsumptionTax - 消費税
     */
    public void setConsumptionTax(double l_dblConsumptionTax)
    {
        this.consumptionTax = l_dblConsumptionTax;
    }

    /**
     * (get消費税)<BR>
     * <BR>
     * 消費税を取得する。<BR>
     * @@return double
     */
    public double getConsumptionTax()
    {
        return this.consumptionTax;
    }
}
@
