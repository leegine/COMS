head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTargetContractDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 対象建玉詳細(WEB3TPTargetContractDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 齋藤　@栄三 (FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower.contract;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;

/**
 * (対象建玉詳細)
 */
public class WEB3TPTargetContractDetail 
{
    
    /**
     * (建玉ID)
     */
    private long contractId;
    
    /**
     * (口座ID)
     */
    private long mainAccountId;
    
    /**
     * (補助口座ID)
     */
    private long subAccountId;
    
    /**
     * (市場ID)
     */
    private long marketId;
    
    /**
     * (元建株数)
     */
    private double originalQuantity;
    
    /**
     * (建株数)
     */
    private double quantity;
    
    /**
     * (建単価)
     */
    private double contractPrice;
    
    /**
     * (建区分)
     */
    private ContractTypeEnum contractType;
    
    /**
     * (建日)
     */
    private Date openDate;
    
    /**
     * (期日)
     */
    private Date closeDate;
    
    /**
     * (建手数料)
     */
    private double setupFee;
    
    /**
     * (建手数料消費税)
     */
    private double setupFeeTax;
    
    /**
     * (名義書換料)
     */
    private double nameTransferFee;
    
    /**
     * (名義書換料消費税)
     */
    private double nameTransferFeeTax;
    
    /**
     * (管理費)
     */
    private double managementFee;
    
    /**
     * (管理費消費税)
     */
    private double managementFeeTax;
    
    /**
     * (順日歩)
     */
    private double interestFee;
    
    /**
     * (逆日歩)
     */
    private double payInterestFee;
    
    /**
     * (貸株料)
     */
    private double loanEquityFee;
    
    /**
     * (保証金率)
     */
    private double marginDepositRate;
    
    /**
     * (現金保証金率)
     */
    private double cashMarginDepositRate;
    
    /**
     * (銘柄ID)
     */
    private long productId;
    
    /**
     * (税区分)
     */
    private TaxTypeEnum taxType;
    
    /**
     * (弁済区分)
     */
    private String repaymentType;
    
    /**
     * (弁済期限値)
     */
    private long repaymentNum;
    
    /**
     * (評価単価)
     */
    private double unitPrice;
    
    /**
     * (基準値<前日終値>)
     */
    private double lastClosingPrice;
    
    /**
     * (当初建日)
     */
    private Date firstOpenDate;

    /**
     * @@roseuid 4104AB45030D
     */
    public WEB3TPTargetContractDetail() 
    {
     
    }
    
    /**
     * (create対象建玉詳細)<BR>
     * 対象建玉詳細を生成する。<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail
     * @@roseuid 40DBFED302DE
     */
    public static WEB3TPTargetContractDetail create() 
    {
        return new WEB3TPTargetContractDetail();
    }
    
    /**
     * (get建玉ID)<BR>
     * 建玉IDを取得する。<BR>
     * @@return long
     * @@roseuid 40E366C301C6
     */
    public long getContractId() 
    {
        return contractId;
    }
    
    /**
     * (set建玉ID)<BR>
     * 引数の建玉IDをセットする。<BR>
     * @@param l_lngContractId - (建玉ID)
     * @@roseuid 40E366C301E6
     */
    public void setContractId(long l_lngContractId) 
    {
        contractId = l_lngContractId;
    }
    
    /**
     * (get口座ID)<BR>
     * 口座IDを取得する。<BR>
     * @@return long
     * @@roseuid 40DBB80B02AF
     */
    public long getMainAccountId() 
    {
        return mainAccountId;
    }
    
    /**
     * (set口座ID)<BR>
     * 引数の口座IDをセットする。<BR>
     * @@param l_lngMainAccountId - (口座ID)
     * @@roseuid 40DBB82400CB
     */
    public void setMainAccountId(long l_lngMainAccountId) 
    {
        mainAccountId = l_lngMainAccountId;
    }
    
    /**
     * (get補助口座ID)<BR>
     * 補助口座IDを取得する。<BR>
     * @@return long
     * @@roseuid 40DBB81400EA
     */
    public long getSubAccountId() 
    {
        return subAccountId;
    }
    
    /**
     * (set補助口座ID)<BR>
     * 引数の補助口座IDをセットする。<BR>
     * @@param l_lngSubAccountId - (補助口座ID)
     * @@roseuid 40DBB829030D
     */
    public void setSubAccountId(long l_lngSubAccountId) 
    {
        subAccountId = l_lngSubAccountId;
    }
    
    /**
     * (get市場ID)<BR>
     * 市場IDを取得する。<BR>
     * @@return long
     * @@roseuid 40DBB83100FA
     */
    public long getMarketId() 
    {
        return marketId;
    }
    
    /**
     * (set市場ID)<BR>
     * 引数の市場IDをセットする。<BR>
     * @@param l_lngMarketId - (市場ID)
     * @@roseuid 40DBB83502BF
     */
    public void setMarketId(long l_lngMarketId) 
    {
        marketId = l_lngMarketId;
    }
    
    /**
     * (get元建株数)<BR>
     * 元建株数を取得する。<BR>
     * @@return double
     * @@roseuid 40DC0D5102D7
     */
    public double getOriginalQuantity() 
    {
        return originalQuantity;
    }
    
    /**
     * (set元建株数)<BR>
     * 引数の元建株数をセットする。<BR>
     * @@param l_dblOriginalQuantity - (元建株数)
     * @@roseuid 40DC0D640037
     */
    public void setOriginalQuantity(double l_dblOriginalQuantity) 
    {
        originalQuantity = l_dblOriginalQuantity;
    }
    
    /**
     * (get建株数)<BR>
     * 建株数を取得する。<BR>
     * @@return double
     * @@roseuid 40ED12EB02D6
     */
    public double getQuantity() 
    {
        return quantity;
    }
    
    /**
     * (set建株数)<BR>
     * 引数の建株数をセットする。<BR>
     * @@param l_dblQuantity - (建株数)
     * @@roseuid 40ED12EB0305
     */
    public void setQuantity(double l_dblQuantity) 
    {
        quantity = l_dblQuantity;
    }
    
    /**
     * (get建単価)<BR>
     * 建単価を取得する。<BR>
     * @@return double
     * @@roseuid 40DBB852036B
     */
    public double getContractPrice() 
    {
        return contractPrice;
    }
    
    /**
     * (set建単価)<BR>
     * 引数の建単価をセットする。<BR>
     * @@param l_dblContractPrice - (建単価)
     * @@roseuid 40DBB854032C
     */
    public void setContractPrice(double l_dblContractPrice) 
    {
        contractPrice = l_dblContractPrice;
    }
    
    /**
     * (get建区分)<BR>
     * 建区分を取得する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum
     * @@roseuid 40DBB848031C
     */
    public ContractTypeEnum getContractType() 
    {
        return contractType;
    }
    
    /**
     * (set建区分)<BR>
     * 引数の建区分をセットする。<BR>
     * @@param l_contractTypeEnum - (建区分)
     * @@roseuid 40DBB84C002E
     */
    public void setContractType(ContractTypeEnum l_contractTypeEnum) 
    {
        contractType = l_contractTypeEnum;     
    }
    
    /**
     * (get建日)<BR>
     * 建日を取得する。<BR>
     * @@return java.util.Date
     * @@roseuid 40DBB85C00EA
     */
    public Date getOpenDate() 
    {
        return openDate;
    }
    
    /**
     * (set建日)<BR>
     * 引数の建日をセットする。<BR>
     * @@param l_datOpen - (建日)
     * @@roseuid 40DBB85F01D4
     */
    public void setOpenDate(Date l_datOpen) 
    {
        openDate = l_datOpen;
    }
    
    /**
     * (get期日)<BR>
     * 期日を取得する。<BR>
     * @@return java.util.Date
     * @@roseuid 40DBB866034B
     */
    public Date getCloseDate() 
    {
        return closeDate;
    }
    
    /**
     * (set期日)<BR>
     * 引数の期日をセットする。<BR>
     * @@param l_datClose - (期日)
     * @@roseuid 40DBB869006D
     */
    public void setCloseDate(Date l_datClose) 
    {
        closeDate = l_datClose;
    }
    
    /**
     * (get建手数料)<BR>
     * 建手数料を取得する。<BR>
     * @@return double
     * @@roseuid 40ED12E102A7
     */
    public double getSetupFee() 
    {
        return setupFee;
    }
    
    /**
     * (set建手数料)<BR>
     * 引数の建手数料をセットする。<BR>
     * @@param l_dblSetupFee - (建手数料)
     * @@roseuid 40ED12E102C6
     */
    public void setSetupFee(double l_dblSetupFee) 
    {
        setupFee = l_dblSetupFee;
    }
    
    /**
     * (get建手数料消費税)<BR>
     * 建手数料消費税を取得する。<BR>
     * @@return double
     */
    public double getSetupFeeTax() 
    {
        return setupFeeTax;
    }
    
    /**
     * (set建手数料消費税)<BR>
     * 引数の建手数料消費税をセットする。<BR>
     * @@param l_dblSetupFeeTax - (建手数料消費税)
     */
    public void setSetupFeeTax(double l_dblSetupFeeTax) 
    {
        setupFeeTax = l_dblSetupFeeTax;
    }
    
    /**
     * (get名義書換料)<BR>
     * 名義書換料を取得する。<BR>
     * @@return double
     * @@roseuid 40ED12E102E6
     */
    public double getNameTransferFee() 
    {
        return nameTransferFee;
    }
    
    /**
     * (set名義書換料)<BR>
     * 引数の名義書換料をセットする。<BR>
     * @@param l_dblNameTransferFee - (名義書換料)
     * @@roseuid 40ED12E10334
     */
    public void setNameTransferFee(double l_dblNameTransferFee) 
    {
        nameTransferFee = l_dblNameTransferFee;
    }
    
    /**
     * (get名義書換料消費税)<BR>
     * 名義書換料消費税を取得する。<BR>
     * @@return double
     */
    public double getNameTransferFeeTax() 
    {
        return nameTransferFeeTax;
    }
    
    /**
     * (set名義書換料消費税)<BR>
     * 引数の名義書換料消費税をセットする。<BR>
     * @@param l_dblNameTransferFeeTax - (名義書換料消費税)
     */
    public void setNameTransferFeeTax(double l_dblNameTransferFeeTax) 
    {
        nameTransferFeeTax = l_dblNameTransferFeeTax;
    }
    
    /**
     * (get管理費)<BR>
     * 管理費を取得する。<BR>
     * @@return double
     * @@roseuid 40ED12E10363
     */
    public double getManagementFee() 
    {
        return managementFee;
    }
    
    /**
     * (set管理費)<BR>
     * 引数の管理費をセットする。<BR>
     * @@param l_dblManagementFee - (管理費)
     * @@roseuid 40ED12E10382
     */
    public void setManagementFee(double l_dblManagementFee) 
    {
        managementFee = l_dblManagementFee;
    }
    
    /**
     * (get管理費消費税)<BR>
     * 管理費消費税を取得する。<BR>
     * @@return double
     */
    public double getManagementFeeTax() 
    {
        return managementFeeTax;
    }
    
    /**
     * (set管理費消費税)<BR>
     * 引数の管理費消費税をセットする。<BR>
     * @@param l_dblManagementFeeTax - (管理費消費税)
     */
    public void setManagementFeeTax(double l_dblManagementFeeTax) 
    {
        managementFeeTax = l_dblManagementFeeTax;
    }
    
    /**
     * (get順日歩)<BR>
     * 順日歩を取得する。<BR>
     * @@return double
     * @@roseuid 40ED12E101DC
     */
    public double getInterestFee() 
    {
        return interestFee;
    }
    
    /**
     * (set順日歩)<BR>
     * 引数の順日歩をセットする。<BR>
     * @@param l_dblInterestFee - (順日歩)
     * @@roseuid 40ED12E1020B
     */
    public void setInterestFee(double l_dblInterestFee) 
    {
        interestFee = l_dblInterestFee;
    }
    
    /**
     * (get逆日歩)<BR>
     * 逆日歩を取得する。<BR>
     * @@return double
     * @@roseuid 40ED12E1023A
     */
    public double getPayInterestFee() 
    {
        return payInterestFee;
    }
    
    /**
     * (set逆日歩)<BR>
     * 引数の逆日歩をセットする。<BR>
     * @@param l_dblPayInterestFee - (逆日歩)
     * @@roseuid 40ED12E10269
     */
    public void setPayInterestFee(double l_dblPayInterestFee) 
    {
        payInterestFee = l_dblPayInterestFee;
    }
    
    /**
     * (get貸株料)<BR>
     * 貸株料を取得する。<BR>
     * @@return double
     * @@roseuid 40ED12E20027
     */
    public double getLoanEquityFee() 
    {
        return loanEquityFee;
    }
    
    /**
     * (set貸株料)<BR>
     * 引数の貸株料をセットする。<BR>
     * @@param l_dblLoanEquityFee - (貸株料)
     * @@roseuid 40ED12E20055
     */
    public void setLoanEquityFee(double l_dblLoanEquityFee) 
    {
        loanEquityFee = l_dblLoanEquityFee;
    }
    
    /**
     * (get保証金率)<BR>
     * 保証金率を取得する。<BR>

     * @@return double
     * @@roseuid 40DBB8A0033C
     */
    public double getMarginDepositRate() 
    {
        return marginDepositRate;
    }
    
    /**
     * (set保証金率)<BR>
     * 引数の保証金率をセットする。<BR>
     * @@param l_dblMarginDepositRate - (保証金率)
     * @@roseuid 40DBB8A501F4
     */
    public void setMarginDepositRate(double l_dblMarginDepositRate) 
    {
        marginDepositRate = l_dblMarginDepositRate;
    }
    
    /**
     * (get現金保証金率)<BR>
     * 現金保証金率を取得する。<BR>
     * @@return double
     * @@roseuid 40DBB8AD0213
     */
    public double getCashMarginDepositRate() 
    {
        return cashMarginDepositRate;
    }
    
    /**
     * (set現金保証金率)<BR>
     * 引数の現金保証金率をセットする。<BR>
     * @@param l_dblCashMarginDeposit - (現金保証金率)
     * @@roseuid 40DBB8B2031C
     */
    public void setCashMarginDepositRate(double l_dblCashMarginDeposit) 
    {
        cashMarginDepositRate = l_dblCashMarginDeposit;
    }
    
    /**
     * (get銘柄ID)<BR>
     * 銘柄IDを取得する。<BR>
     * @@return long
     * @@roseuid 40DBB83F031C
     */
    public long getProductId() 
    {
        return productId;
    }
    
    /**
     * (set銘柄ID)<BR>
     * 引数の銘柄IDをセットする。<BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@roseuid 40DBB84201C5
     */
    public void setProductId(long l_lngProductId) 
    {
        productId = l_lngProductId;
    }
    
    /**
     * (get税区分)<BR>
     * 税区分を取得する。<BR>
     * @@return long
     * @@roseuid 40DBB87103A9
     */
    public TaxTypeEnum getTaxType() 
    {
        return taxType;
    }
    
    /**
     * (set税区分)<BR>
     * 引数の税区分をセットする。<BR>
     * @@param l_taxType - (税区分)
     * @@roseuid 40DBB8740196
     */
    public void setTaxType(TaxTypeEnum l_taxType) 
    {
        taxType = l_taxType;          
    }
    
    /**
     * (get弁済区分)<BR>
     * 弁済区分を取得する。<BR>
     * @@return String
     * @@roseuid 40DBB87D0138
     */
    public String getRepaymentType() 
    {
        return repaymentType;
    }
    
    /**
     * (set弁済区分)<BR>
     * 引数の弁済区分をセットする。<BR>
     * @@param l_strRepaymentType - (弁済区分)
     * @@roseuid 40DBB88002DE
     */
    public void setRepaymentType(String l_strRepaymentType) 
    {
        repaymentType = l_strRepaymentType;
    }
    
    /**
     * (get弁済期限値)<BR>
     * 弁済期限値を取得する。<BR>
     * @@return long
     * @@roseuid 40DBB887007D
     */
    public long getRepaymentNum() 
    {
        return repaymentNum;
    }
    
    /**
     * (set弁済期限値)<BR>
     * 引数の弁済期限値をセットする。<BR>
     * @@param l_lngRepaymentNum - (弁済期限値)
     * @@roseuid 40DBB88F0000
     */
    public void setRepaymentNum(long l_lngRepaymentNum) 
    {
        repaymentNum = l_lngRepaymentNum;
    }
    
    /**
     * (get評価単価)<BR>
     * 評価単価を取得する。<BR>
     * @@return double
     */
    public double getUnitPrice() 
    {
        return unitPrice;
    }
    
    /**
     * (set評価単価)<BR>
     * 引数の評価単価をセットする。<BR>
     * @@param l_dblUnitPrice - (評価単価)
     */
    public void setUnitPrice(double l_dblUnitPrice) 
    {
        unitPrice = l_dblUnitPrice;
    }
    
    /**
     * (get基準値<前日終値>)<BR>
     * 基準値<前日終値>を取得する。<BR>
     * @@return double
     */
    public double getLastClosingPrice() 
    {
        return lastClosingPrice;
    }
    
    /**
     * (set基準値<前日終値>)<BR>
     * 引数の基準値<前日終値>をセットする。<BR>
     * @@param l_dblLastClosingPrice - (基準値<前日終値>)
     */
    public void setLastClosingPrice(double l_dblLastClosingPrice) 
    {
        lastClosingPrice = l_dblLastClosingPrice;
    }

    /**
     * (get当初建日)<BR>
     * 当初建日を取得する。<BR>
     * @@return java.util.Date
     */
    public Date getFirstOpenDate() 
    {
        return firstOpenDate;
    }
    
    /**
     * (set当初建日)<BR>
     * 引数の当初建日をセットする。<BR>
     * @@param l_datFirstOpen - (当初建日)
     */
    public void setFirstOpenDate(Date l_datFirstOpen) 
    {
        firstOpenDate = l_datFirstOpen;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        String l_strYYYYMMDDFormat = "yyyy/MM/dd";
        String l_strOpenDate = WEB3DateUtility.formatDate(getOpenDate(), l_strYYYYMMDDFormat);
        String l_strCloseDate = WEB3DateUtility.formatDate(getCloseDate(), l_strYYYYMMDDFormat);
        String l_strFirstOpenDate = WEB3DateUtility.formatDate(getFirstOpenDate(), l_strYYYYMMDDFormat);
        
        return ToStringUtils
            .newToStringBuilder(this)
            .append("contractId", getContractId())
            .append("mainAccountId", getMainAccountId())
            .append("subAccountId", getSubAccountId())
            .append("marketId", getMarketId())
            .append("productId", getProductId())
            .append("contractType", getContractType())
            .append("contractPrice", getContractPrice())
            .append("originalQuantity", getOriginalQuantity())
            .append("quantity", getQuantity())
            .append("openDate", l_strOpenDate)
            .append("closeDate", l_strCloseDate)
            .append("setupFee", getSetupFee())
            .append("setupFeeTax", getSetupFeeTax())
            .append("nameTransferFee", getNameTransferFee())
            .append("nameTransferFeeTax", getNameTransferFeeTax())
            .append("managementFee", getManagementFee())
            .append("managementFeeTax", getManagementFeeTax())
            .append("interestFee", getInterestFee())
            .append("payInterestFee", getPayInterestFee())
            .append("loanEquityFee", getLoanEquityFee())
            .append("marginDepositRate", getMarginDepositRate())
            .append("cashMarginDepositRate", getCashMarginDepositRate())
            .append("taxType", getTaxType())
            .append("repaymentType", getRepaymentType())
            .append("repaymentNum", getRepaymentNum())
            .append("unitPrice", getUnitPrice())
            .append("lastClosingPrice", getLastClosingPrice())
            .append("firstOpenDate", l_strFirstOpenDate)
            .toString();
    }
}
@
