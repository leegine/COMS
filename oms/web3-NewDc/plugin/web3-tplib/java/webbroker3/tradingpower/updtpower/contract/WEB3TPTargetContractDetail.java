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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ώی��ʏڍ�(WEB3TPTargetContractDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.contract;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;

/**
 * (�Ώی��ʏڍ�)
 */
public class WEB3TPTargetContractDetail 
{
    
    /**
     * (����ID)
     */
    private long contractId;
    
    /**
     * (����ID)
     */
    private long mainAccountId;
    
    /**
     * (�⏕����ID)
     */
    private long subAccountId;
    
    /**
     * (�s��ID)
     */
    private long marketId;
    
    /**
     * (��������)
     */
    private double originalQuantity;
    
    /**
     * (������)
     */
    private double quantity;
    
    /**
     * (���P��)
     */
    private double contractPrice;
    
    /**
     * (���敪)
     */
    private ContractTypeEnum contractType;
    
    /**
     * (����)
     */
    private Date openDate;
    
    /**
     * (����)
     */
    private Date closeDate;
    
    /**
     * (���萔��)
     */
    private double setupFee;
    
    /**
     * (���萔�������)
     */
    private double setupFeeTax;
    
    /**
     * (���`������)
     */
    private double nameTransferFee;
    
    /**
     * (���`�����������)
     */
    private double nameTransferFeeTax;
    
    /**
     * (�Ǘ���)
     */
    private double managementFee;
    
    /**
     * (�Ǘ�������)
     */
    private double managementFeeTax;
    
    /**
     * (������)
     */
    private double interestFee;
    
    /**
     * (�t����)
     */
    private double payInterestFee;
    
    /**
     * (�݊���)
     */
    private double loanEquityFee;
    
    /**
     * (�ۏ؋���)
     */
    private double marginDepositRate;
    
    /**
     * (�����ۏ؋���)
     */
    private double cashMarginDepositRate;
    
    /**
     * (����ID)
     */
    private long productId;
    
    /**
     * (�ŋ敪)
     */
    private TaxTypeEnum taxType;
    
    /**
     * (�ٍϋ敪)
     */
    private String repaymentType;
    
    /**
     * (�ٍϊ����l)
     */
    private long repaymentNum;
    
    /**
     * (�]���P��)
     */
    private double unitPrice;
    
    /**
     * (��l<�O���I�l>)
     */
    private double lastClosingPrice;
    
    /**
     * (��������)
     */
    private Date firstOpenDate;

    /**
     * @@roseuid 4104AB45030D
     */
    public WEB3TPTargetContractDetail() 
    {
     
    }
    
    /**
     * (create�Ώی��ʏڍ�)<BR>
     * �Ώی��ʏڍׂ𐶐�����B<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail
     * @@roseuid 40DBFED302DE
     */
    public static WEB3TPTargetContractDetail create() 
    {
        return new WEB3TPTargetContractDetail();
    }
    
    /**
     * (get����ID)<BR>
     * ����ID���擾����B<BR>
     * @@return long
     * @@roseuid 40E366C301C6
     */
    public long getContractId() 
    {
        return contractId;
    }
    
    /**
     * (set����ID)<BR>
     * �����̌���ID���Z�b�g����B<BR>
     * @@param l_lngContractId - (����ID)
     * @@roseuid 40E366C301E6
     */
    public void setContractId(long l_lngContractId) 
    {
        contractId = l_lngContractId;
    }
    
    /**
     * (get����ID)<BR>
     * ����ID���擾����B<BR>
     * @@return long
     * @@roseuid 40DBB80B02AF
     */
    public long getMainAccountId() 
    {
        return mainAccountId;
    }
    
    /**
     * (set����ID)<BR>
     * �����̌���ID���Z�b�g����B<BR>
     * @@param l_lngMainAccountId - (����ID)
     * @@roseuid 40DBB82400CB
     */
    public void setMainAccountId(long l_lngMainAccountId) 
    {
        mainAccountId = l_lngMainAccountId;
    }
    
    /**
     * (get�⏕����ID)<BR>
     * �⏕����ID���擾����B<BR>
     * @@return long
     * @@roseuid 40DBB81400EA
     */
    public long getSubAccountId() 
    {
        return subAccountId;
    }
    
    /**
     * (set�⏕����ID)<BR>
     * �����̕⏕����ID���Z�b�g����B<BR>
     * @@param l_lngSubAccountId - (�⏕����ID)
     * @@roseuid 40DBB829030D
     */
    public void setSubAccountId(long l_lngSubAccountId) 
    {
        subAccountId = l_lngSubAccountId;
    }
    
    /**
     * (get�s��ID)<BR>
     * �s��ID���擾����B<BR>
     * @@return long
     * @@roseuid 40DBB83100FA
     */
    public long getMarketId() 
    {
        return marketId;
    }
    
    /**
     * (set�s��ID)<BR>
     * �����̎s��ID���Z�b�g����B<BR>
     * @@param l_lngMarketId - (�s��ID)
     * @@roseuid 40DBB83502BF
     */
    public void setMarketId(long l_lngMarketId) 
    {
        marketId = l_lngMarketId;
    }
    
    /**
     * (get��������)<BR>
     * �����������擾����B<BR>
     * @@return double
     * @@roseuid 40DC0D5102D7
     */
    public double getOriginalQuantity() 
    {
        return originalQuantity;
    }
    
    /**
     * (set��������)<BR>
     * �����̌����������Z�b�g����B<BR>
     * @@param l_dblOriginalQuantity - (��������)
     * @@roseuid 40DC0D640037
     */
    public void setOriginalQuantity(double l_dblOriginalQuantity) 
    {
        originalQuantity = l_dblOriginalQuantity;
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * @@return double
     * @@roseuid 40ED12EB02D6
     */
    public double getQuantity() 
    {
        return quantity;
    }
    
    /**
     * (set������)<BR>
     * �����̌��������Z�b�g����B<BR>
     * @@param l_dblQuantity - (������)
     * @@roseuid 40ED12EB0305
     */
    public void setQuantity(double l_dblQuantity) 
    {
        quantity = l_dblQuantity;
    }
    
    /**
     * (get���P��)<BR>
     * ���P�����擾����B<BR>
     * @@return double
     * @@roseuid 40DBB852036B
     */
    public double getContractPrice() 
    {
        return contractPrice;
    }
    
    /**
     * (set���P��)<BR>
     * �����̌��P�����Z�b�g����B<BR>
     * @@param l_dblContractPrice - (���P��)
     * @@roseuid 40DBB854032C
     */
    public void setContractPrice(double l_dblContractPrice) 
    {
        contractPrice = l_dblContractPrice;
    }
    
    /**
     * (get���敪)<BR>
     * ���敪���擾����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum
     * @@roseuid 40DBB848031C
     */
    public ContractTypeEnum getContractType() 
    {
        return contractType;
    }
    
    /**
     * (set���敪)<BR>
     * �����̌��敪���Z�b�g����B<BR>
     * @@param l_contractTypeEnum - (���敪)
     * @@roseuid 40DBB84C002E
     */
    public void setContractType(ContractTypeEnum l_contractTypeEnum) 
    {
        contractType = l_contractTypeEnum;     
    }
    
    /**
     * (get����)<BR>
     * �������擾����B<BR>
     * @@return java.util.Date
     * @@roseuid 40DBB85C00EA
     */
    public Date getOpenDate() 
    {
        return openDate;
    }
    
    /**
     * (set����)<BR>
     * �����̌������Z�b�g����B<BR>
     * @@param l_datOpen - (����)
     * @@roseuid 40DBB85F01D4
     */
    public void setOpenDate(Date l_datOpen) 
    {
        openDate = l_datOpen;
    }
    
    /**
     * (get����)<BR>
     * �������擾����B<BR>
     * @@return java.util.Date
     * @@roseuid 40DBB866034B
     */
    public Date getCloseDate() 
    {
        return closeDate;
    }
    
    /**
     * (set����)<BR>
     * �����̊������Z�b�g����B<BR>
     * @@param l_datClose - (����)
     * @@roseuid 40DBB869006D
     */
    public void setCloseDate(Date l_datClose) 
    {
        closeDate = l_datClose;
    }
    
    /**
     * (get���萔��)<BR>
     * ���萔�����擾����B<BR>
     * @@return double
     * @@roseuid 40ED12E102A7
     */
    public double getSetupFee() 
    {
        return setupFee;
    }
    
    /**
     * (set���萔��)<BR>
     * �����̌��萔�����Z�b�g����B<BR>
     * @@param l_dblSetupFee - (���萔��)
     * @@roseuid 40ED12E102C6
     */
    public void setSetupFee(double l_dblSetupFee) 
    {
        setupFee = l_dblSetupFee;
    }
    
    /**
     * (get���萔�������)<BR>
     * ���萔������ł��擾����B<BR>
     * @@return double
     */
    public double getSetupFeeTax() 
    {
        return setupFeeTax;
    }
    
    /**
     * (set���萔�������)<BR>
     * �����̌��萔������ł��Z�b�g����B<BR>
     * @@param l_dblSetupFeeTax - (���萔�������)
     */
    public void setSetupFeeTax(double l_dblSetupFeeTax) 
    {
        setupFeeTax = l_dblSetupFeeTax;
    }
    
    /**
     * (get���`������)<BR>
     * ���`���������擾����B<BR>
     * @@return double
     * @@roseuid 40ED12E102E6
     */
    public double getNameTransferFee() 
    {
        return nameTransferFee;
    }
    
    /**
     * (set���`������)<BR>
     * �����̖��`���������Z�b�g����B<BR>
     * @@param l_dblNameTransferFee - (���`������)
     * @@roseuid 40ED12E10334
     */
    public void setNameTransferFee(double l_dblNameTransferFee) 
    {
        nameTransferFee = l_dblNameTransferFee;
    }
    
    /**
     * (get���`�����������)<BR>
     * ���`����������ł��擾����B<BR>
     * @@return double
     */
    public double getNameTransferFeeTax() 
    {
        return nameTransferFeeTax;
    }
    
    /**
     * (set���`�����������)<BR>
     * �����̖��`����������ł��Z�b�g����B<BR>
     * @@param l_dblNameTransferFeeTax - (���`�����������)
     */
    public void setNameTransferFeeTax(double l_dblNameTransferFeeTax) 
    {
        nameTransferFeeTax = l_dblNameTransferFeeTax;
    }
    
    /**
     * (get�Ǘ���)<BR>
     * �Ǘ�����擾����B<BR>
     * @@return double
     * @@roseuid 40ED12E10363
     */
    public double getManagementFee() 
    {
        return managementFee;
    }
    
    /**
     * (set�Ǘ���)<BR>
     * �����̊Ǘ�����Z�b�g����B<BR>
     * @@param l_dblManagementFee - (�Ǘ���)
     * @@roseuid 40ED12E10382
     */
    public void setManagementFee(double l_dblManagementFee) 
    {
        managementFee = l_dblManagementFee;
    }
    
    /**
     * (get�Ǘ�������)<BR>
     * �Ǘ������ł��擾����B<BR>
     * @@return double
     */
    public double getManagementFeeTax() 
    {
        return managementFeeTax;
    }
    
    /**
     * (set�Ǘ�������)<BR>
     * �����̊Ǘ������ł��Z�b�g����B<BR>
     * @@param l_dblManagementFeeTax - (�Ǘ�������)
     */
    public void setManagementFeeTax(double l_dblManagementFeeTax) 
    {
        managementFeeTax = l_dblManagementFeeTax;
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * @@return double
     * @@roseuid 40ED12E101DC
     */
    public double getInterestFee() 
    {
        return interestFee;
    }
    
    /**
     * (set������)<BR>
     * �����̏��������Z�b�g����B<BR>
     * @@param l_dblInterestFee - (������)
     * @@roseuid 40ED12E1020B
     */
    public void setInterestFee(double l_dblInterestFee) 
    {
        interestFee = l_dblInterestFee;
    }
    
    /**
     * (get�t����)<BR>
     * �t�������擾����B<BR>
     * @@return double
     * @@roseuid 40ED12E1023A
     */
    public double getPayInterestFee() 
    {
        return payInterestFee;
    }
    
    /**
     * (set�t����)<BR>
     * �����̋t�������Z�b�g����B<BR>
     * @@param l_dblPayInterestFee - (�t����)
     * @@roseuid 40ED12E10269
     */
    public void setPayInterestFee(double l_dblPayInterestFee) 
    {
        payInterestFee = l_dblPayInterestFee;
    }
    
    /**
     * (get�݊���)<BR>
     * �݊������擾����B<BR>
     * @@return double
     * @@roseuid 40ED12E20027
     */
    public double getLoanEquityFee() 
    {
        return loanEquityFee;
    }
    
    /**
     * (set�݊���)<BR>
     * �����̑݊������Z�b�g����B<BR>
     * @@param l_dblLoanEquityFee - (�݊���)
     * @@roseuid 40ED12E20055
     */
    public void setLoanEquityFee(double l_dblLoanEquityFee) 
    {
        loanEquityFee = l_dblLoanEquityFee;
    }
    
    /**
     * (get�ۏ؋���)<BR>
     * �ۏ؋������擾����B<BR>

     * @@return double
     * @@roseuid 40DBB8A0033C
     */
    public double getMarginDepositRate() 
    {
        return marginDepositRate;
    }
    
    /**
     * (set�ۏ؋���)<BR>
     * �����̕ۏ؋������Z�b�g����B<BR>
     * @@param l_dblMarginDepositRate - (�ۏ؋���)
     * @@roseuid 40DBB8A501F4
     */
    public void setMarginDepositRate(double l_dblMarginDepositRate) 
    {
        marginDepositRate = l_dblMarginDepositRate;
    }
    
    /**
     * (get�����ۏ؋���)<BR>
     * �����ۏ؋������擾����B<BR>
     * @@return double
     * @@roseuid 40DBB8AD0213
     */
    public double getCashMarginDepositRate() 
    {
        return cashMarginDepositRate;
    }
    
    /**
     * (set�����ۏ؋���)<BR>
     * �����̌����ۏ؋������Z�b�g����B<BR>
     * @@param l_dblCashMarginDeposit - (�����ۏ؋���)
     * @@roseuid 40DBB8B2031C
     */
    public void setCashMarginDepositRate(double l_dblCashMarginDeposit) 
    {
        cashMarginDepositRate = l_dblCashMarginDeposit;
    }
    
    /**
     * (get����ID)<BR>
     * ����ID���擾����B<BR>
     * @@return long
     * @@roseuid 40DBB83F031C
     */
    public long getProductId() 
    {
        return productId;
    }
    
    /**
     * (set����ID)<BR>
     * �����̖���ID���Z�b�g����B<BR>
     * @@param l_lngProductId - (����ID)
     * @@roseuid 40DBB84201C5
     */
    public void setProductId(long l_lngProductId) 
    {
        productId = l_lngProductId;
    }
    
    /**
     * (get�ŋ敪)<BR>
     * �ŋ敪���擾����B<BR>
     * @@return long
     * @@roseuid 40DBB87103A9
     */
    public TaxTypeEnum getTaxType() 
    {
        return taxType;
    }
    
    /**
     * (set�ŋ敪)<BR>
     * �����̐ŋ敪���Z�b�g����B<BR>
     * @@param l_taxType - (�ŋ敪)
     * @@roseuid 40DBB8740196
     */
    public void setTaxType(TaxTypeEnum l_taxType) 
    {
        taxType = l_taxType;          
    }
    
    /**
     * (get�ٍϋ敪)<BR>
     * �ٍϋ敪���擾����B<BR>
     * @@return String
     * @@roseuid 40DBB87D0138
     */
    public String getRepaymentType() 
    {
        return repaymentType;
    }
    
    /**
     * (set�ٍϋ敪)<BR>
     * �����ٍ̕ϋ敪���Z�b�g����B<BR>
     * @@param l_strRepaymentType - (�ٍϋ敪)
     * @@roseuid 40DBB88002DE
     */
    public void setRepaymentType(String l_strRepaymentType) 
    {
        repaymentType = l_strRepaymentType;
    }
    
    /**
     * (get�ٍϊ����l)<BR>
     * �ٍϊ����l���擾����B<BR>
     * @@return long
     * @@roseuid 40DBB887007D
     */
    public long getRepaymentNum() 
    {
        return repaymentNum;
    }
    
    /**
     * (set�ٍϊ����l)<BR>
     * �����ٍ̕ϊ����l���Z�b�g����B<BR>
     * @@param l_lngRepaymentNum - (�ٍϊ����l)
     * @@roseuid 40DBB88F0000
     */
    public void setRepaymentNum(long l_lngRepaymentNum) 
    {
        repaymentNum = l_lngRepaymentNum;
    }
    
    /**
     * (get�]���P��)<BR>
     * �]���P�����擾����B<BR>
     * @@return double
     */
    public double getUnitPrice() 
    {
        return unitPrice;
    }
    
    /**
     * (set�]���P��)<BR>
     * �����̕]���P�����Z�b�g����B<BR>
     * @@param l_dblUnitPrice - (�]���P��)
     */
    public void setUnitPrice(double l_dblUnitPrice) 
    {
        unitPrice = l_dblUnitPrice;
    }
    
    /**
     * (get��l<�O���I�l>)<BR>
     * ��l<�O���I�l>���擾����B<BR>
     * @@return double
     */
    public double getLastClosingPrice() 
    {
        return lastClosingPrice;
    }
    
    /**
     * (set��l<�O���I�l>)<BR>
     * �����̊�l<�O���I�l>���Z�b�g����B<BR>
     * @@param l_dblLastClosingPrice - (��l<�O���I�l>)
     */
    public void setLastClosingPrice(double l_dblLastClosingPrice) 
    {
        lastClosingPrice = l_dblLastClosingPrice;
    }

    /**
     * (get��������)<BR>
     * �����������擾����B<BR>
     * @@return java.util.Date
     */
    public Date getFirstOpenDate() 
    {
        return firstOpenDate;
    }
    
    /**
     * (set��������)<BR>
     * �����̓����������Z�b�g����B<BR>
     * @@param l_datFirstOpen - (��������)
     */
    public void setFirstOpenDate(Date l_datFirstOpen) 
    {
        firstOpenDate = l_datFirstOpen;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
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
