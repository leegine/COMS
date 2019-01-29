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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��(WEB3GentradeCommission.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 ���j (���u) �V�K�쐬
Revesion History : 2006/05/11 ������ (���u)�y���ʁz�d�l�ύX�E���f��No.187
Revesion History : 2007/10/10 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.278
*/
package webbroker3.gentrade;

import java.sql.Timestamp;

/**
 * (�萔��)<BR>
 * <BR>
 * �ϑ��萔���̏���\������<BR>
 */
public class WEB3GentradeCommission
{

    /**
     * �،���ЃR�[�h<BR>
     */
    private String institutionCode;

    /**
     * ���XID<BR>
     */
    private long branchId;

    /**
     * �萔�����i�R�[�h<BR>
     * <BR>
     * �i��Е��X���i�e�[�u���̎萔�����i�R�[�h�j<BR>
     */
    private String commissionProductCode;

    /**
     * ����R�[�h�iSONAR�j<BR>
     * <BR>
     * 11�F���ʊ���<BR>
     * 16�F����O����<BR>
     * 51�F�M�p��<BR>
     * 52�F�M�p�ԍ�<BR>
     * 53�F�������n<BR>
     * 92�F��O���(��JASDAQ)<BR>
     */
    private String sonarTradedCode;
    
    /**
     * �s��R�[�h�iSONAR�j<BR>
     * ��null�i�ݒ莞�̂݁A�ϑ��萔���}�ԓo�^�}�X�^�[����<BR>
     * �̎擾�����Ɏg�p����j<BR>
     * <BR>
     * 1�F����<BR>
     * 2�F���<BR>
     * 3�F���É�<BR>
     * 4�F�D�y<BR>
     * 5�FNNM<BR>
     * 6�FJASDAQ<BR>
     * 9�F����<BR>
     */
    private String sonarMarketCode;

    /**
     * ������<BR>
     */
    private Timestamp orderBizDate;

    /**
     * �ٍϋ敪<BR>
     * <BR>
     * �i�ϑ��萔���}�ԓo�^�}�X�^�[.�ٍϋ敪�j<BR>
     * 1�F���x�M�p�@@3�F��ʐM�p�@@00�F���̑�<BR>
     */
    private String payType;

    /**
     * �����`���l��<BR>
     */
    private String orderChannel;

    /**
     * (is�w�l)<BR>
     * <BR>
     * �w�l�����̏ꍇ�Atrue�B���s�����̏ꍇ�Afalse�B<BR>
     */
    private boolean isLimitPrice;

    /**
     * (�萔��No)<BR>
     * <BR>
     * �萔���v�Z�Ɏg�p�����萔��No�B<BR>
     * calc�ϑ��萔�����ԋp����B<BR>
     */
    private String commissionNo;

    /**
     * (�萔��No�}��)<BR>
     * <BR>
     * �萔���v�Z�Ɏg�p�����萔��No�}�ԁB<BR>
     * calc�ϑ��萔�����ԋp����B<BR>
     */
    private String commissionRevNo;

    /**
     * (�萔�����z)<BR>
     * <BR>
     * �ϑ��萔����\������B<BR>
     * calc�ϑ��萔�����ԋp����B<BR>
     */
    private double commission;

    /**
     * (���o��v�Z�p���)<BR>
     * <BR>
     * ���o��v�Z�p����B�����ł���΍S����������A<BR>
     * ����ł���Δ�������A���ł���Ζ�<BR>
     * ������ݒ肷��B<BR>
     */
    private double expensesCalcAmount;

    /**
     * (�����������`���l��)<BR>
     */
    private String orgOrderChannel;

    /**
     * (�������萔��No)<BR>
     * <BR>
     * �������̎萔�����Z�o�����ۂ̎萔��No�B<BR>
     * �V�K�����̏ꍇ�͐ݒ�Ȃ��B<BR>
     */
    private String orgCommissionNo;

    /**
     * (�������萔��No�}��)<BR>
     * <BR>
     * �������̎萔�����Z�o�����ۂ̎萔��No�}�ԁB<BR>
     * �V�K�����̏ꍇ�͐ݒ�Ȃ��B<BR>
     */
    private String orgCommissionRevNo;
    
    /**
     * (�萔���R�[�X�R�[�h)<BR>
     * <BR>
     * 02�F�@@�藦�萔���i�X�^���_�[�h�j<BR>
     * 12�F�@@�藦�萔���i�n�C�p�[�{�b�N�X�j�������� = 0% <BR>
     * 03�F�@@��������v <BR>
     * 04�F�@@���� <BR>
     * 05�F�@@�����z��<BR>
     */
    private String commissionCourseDiv;
    
    /**
     * (�Œ�萔��)<BR>
     * <BR>
     * �Œ�萔����\������B<BR>
     * calc�ϑ��萔�����ԋp����B<BR>
     */
    private double minCommission;

    /**
     * (�����Y�����R�[�h)<BR>
     * �����Y�����R�[�h<BR>
     * <BR>
     * ��null�i�ݒ莞�̂݁A�ϑ��萔���}�ԓo�^�}�X�^�[����̎擾�����Ɏg�p����j<BR>
     * <BR>
     * 0005�FTOPIX <BR>
     * 0016�F���o300 <BR>
     * 0018�F���o225 <BR>
     * 0019�F�~�j���o225 <BR>
     */
    private String underlyingProductCode;

    /**
     * (���v��敪)<BR>
     * ���v��敪<BR>
     * <BR>
     * ��null�i�ݒ莞�̂݁A�ϑ��萔���}�ԓo�^�}�X�^�[����̎擾�����Ɏg�p����j<BR>
     * <BR>
     * 1�F���v��ȊO <BR>
     * 5�F���v�� <BR>
     */
    private String dayTradeType;

    /**
     * (�萔���搔)<BR>
     * �萔���搔<BR>
     * <BR>
     * �����v�Z�i���j�̏ꍇ�A1������̎萔�����ݒ�B<BR>
     * ��n����ɂ��萔���v�Z�̏ꍇ�A�[�����ݒ�B<BR>
     * <BR>
     * ���敨�ł������̎}�Ԃ̏ꍇ�̂ݎg�p�B<BR>
     */
    private double commitionPerUnit;

    /**
     * (����)<BR>
     * ����<BR>
     * <BR>
     * ���萔���搔�i1������̎萔���j���ݒ肳��Ă���ꍇ�Ɏg�p�B<BR>
     */
    private double quantity;

    /**
     * (�����)<BR>
     * �����<BR>
     */
    private double consumptionTax;

    /**
     * �R���X�g���N�^<BR>
     * @@return webbroker3.equity.WEB3EquityCommission
     * @@roseuid 405FD0620196
     */
    public WEB3GentradeCommission()
    {

    }

    /**
     * (set�،���ЃR�[�h)<BR>
     * <BR>
     * �،����ID���Z�b�g����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@roseuid 4019DCF60109
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * <BR>
     * �،����ID���擾����B<BR>
     * @@return long
     * @@roseuid 4019DD25038A
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * (set���XID)<BR>
     * <BR>
     * ���XID���Z�b�g����B<BR>
     * @@param l_lngBranchId - ���XID
     * @@roseuid 4019E86303C9
     */
    public void setBranchId(long l_lngBranchId)
    {
        this.branchId = l_lngBranchId;
    }

    /**
     * (get���XID)<BR>
     * <BR>
     * ���XID���擾����B<BR>
     * @@return long
     * @@roseuid 4019E8640010
     */
    public long getBranchId()
    {
        return branchId;
    }

    /**
     * (set�萔�����i�R�[�h)<BR>
     * <BR>
     * �萔�����i�R�[�h���Z�b�g����B<BR>
     * @@param l_strCommissionProductCode - �萔�����i�R�[�h
     * @@roseuid 4019E8660129
     */
    public void setCommissionProductCode(String l_strCommissionProductCode)
    {
        this.commissionProductCode = l_strCommissionProductCode;
    }

    /**
     * (get�萔�����i�R�[�h)<BR>
     * <BR>
     * �萔�����i�R�[�h���擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 4019E8660139
     */
    public String getCommissionProductCode()
    {
        return commissionProductCode;
    }

    /**
     * (set����R�[�h�iSONAR�j)<BR>
     * <BR>
     * ����R�[�h�iSONAR�j���Z�b�g����B<BR>
     * @@param l_strTransactionType - ����R�[�h�iSONAR�j
     * @@roseuid 4019E8670213
     */
    public void setSonarTradedCode(String l_strSonarTradedCode)
    {
        this.sonarTradedCode = l_strSonarTradedCode;
    }

    /**
     * (get����R�[�h�iSONAR�j)<BR>
     * <BR>
     * ����R�[�h�iSONAR�j���擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 4019E8670223
     */
    public String getSonarTradedCode()
    {
        return this.sonarTradedCode;
    }

    /**
     * (set������)<BR>
     * <BR>
     * ���������Z�b�g����B<BR>
     * @@param l_tsOrderBizDate - ������
     * @@roseuid 4019E95D01E5
     */
    public void setOrderBizDate(Timestamp l_tsOrderBizDate)
    {
        this.orderBizDate = l_tsOrderBizDate;
    }

    /**
     * (get������)<BR>
     * <BR>
     * ���������擾����B<BR>
     * @@return java.sql.Timestamp
     * @@roseuid 4019E96500DB
     */
    public Timestamp getOrderBizDate()
    {
        return orderBizDate;
    }

    /**
     * (set�ٍϋ敪)<BR>
     * <BR>
     * �ٍϋ敪���Z�b�g����B<BR>
     * @@param l_strPayType - �ٍϋ敪
     * @@roseuid 4019E99B0010
     */
    public void setPayType(String l_strPayType)
    {
        this.payType = l_strPayType;
    }

    /**
     * (get�ٍϋ敪)<BR>
     * <BR>
     * �ٍϋ敪���擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 4019E9BD003F
     */
    public String getPayType()
    {
        return payType;
    }

    /**
     * (set�����`���l��)<BR>
     * <BR>
     * �����`���l�����Z�b�g����B<BR>
     * @@param l_strOrderChannel - �����`���l��
     * @@roseuid 4019E9DA008D
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        this.orderChannel = l_strOrderChannel;
    }

    /**
     * (get�����`���l��)<BR>
     * <BR>
     * �����`���l�����擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 4019E9FC007D
     */
    public String getOrderChannel()
    {
        return orderChannel;
    }

    /**
     * (setIs�w�l)<BR>
     * @@param l_blnIsLimitPrice - (is�w�l)
     * 
     * �w�l�����̏ꍇ�Atrue�B���s�����̏ꍇ�Afalse�B
     * @@roseuid 4019EA0E038A
     */
    public void setIsLimitPrice(boolean l_blnIsLimitPrice)
    {
        this.isLimitPrice = l_blnIsLimitPrice;
    }

    /**
     * (is�w�l)<BR>
     * <BR>
     * �w�l���A��w�l�i�����s�j�����擾����B<BR>
     * @@return boolean
     * @@roseuid 4019EA320262
     */
    public boolean isLimitPrice()
    {
        return isLimitPrice;
    }
    
    /**
     * (is���s)<BR>
     * <BR>
     * ���s���A�񐬍s�i���w�l�j�����擾����B<BR>
     * is�w�l=false�Ȃ�΁Atrue��ԋp����B�ȊO�Afalse��ԋp����B<BR> 
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
     * (set�萔��No)<BR>
     * <BR>
     * �萔��No���Z�b�g����B<BR>
     * @@param l_strCommissionNo - �萔��No
     * @@roseuid 4019EA5E0213
     */
    public void setCommissionNo(String l_strCommissionNo)
    {
        this.commissionNo = l_strCommissionNo;
    }

    /**
     * (get�萔��No)<BR>
     * <BR>
     * �萔��No���擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 4019EA470204
     */
    public String getCommissionNo()
    {
        return commissionNo;
    }

    /**
     * (set�萔��No�}��)<BR>
     * <BR>
     * �萔��No�}�Ԃ��Z�b�g����B<BR>
     * @@param l_strCommissionRevNo - �萔��No�}��
     * @@roseuid 4019EA8A0281
     */
    public void setCommissionRevNo(String l_strCommissionRevNo)
    {
        this.commissionRevNo = l_strCommissionRevNo;
    }

    /**
     * (get�萔��No�}��)<BR>
     * <BR>
     * �萔��No�}�Ԃ��擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 4019EA8A0290
     */
    public String getCommissionRevNo()
    {
        return commissionRevNo;
    }

    /**
     * (set�萔�����z)<BR>
     * <BR>
     * �萔�����z���Z�b�g����B<BR>
     * @@param l_dblCommission - �萔�����z
     * @@roseuid 4019EB04002F
     */
    public void setCommission(double l_dblCommission)
    {
        this.commission = l_dblCommission;
    }

    /**
     * (get�萔�����z)<BR>
     * <BR>
     * �萔�����z���擾����B<BR>
     * @@return double
     * @@roseuid 4019EB04004E
     */
    public double getCommission()
    {
        return commission;
    }

    /**
     * (set���o��v�Z�p���)<BR>
     * <BR>
     * ���o��v�Z�p������Z�b�g����B<BR>
     * @@param l_dblExpensesAmount - ���o��v�Z�p���
     * @@return java.lang.Void
     * @@roseuid 4019EB310187
     */
    public void setExpensesCalcAmount(double l_dblExpensesAmount)
    {
        this.expensesCalcAmount = l_dblExpensesAmount;
    }

    /**
     * (get���o��v�Z�p���)<BR>
     * <BR>
     * ���o��v�Z�p������擾����B<BR>
     * @@return double
     * @@roseuid 4019EB3101A6
     */
    public double getExpensesCalcAmount()
    {
        return expensesCalcAmount;
    }

    /**
     * (set�����������`���l��)<BR>
     * <BR>
     * �����������`���l�����Z�b�g����B<BR>
     * @@param l_strOrgOrderChannel - (�����������`���l��)<BR>
     * <BR>
     * �����������`���l�����擾����B<BR>
     * @@roseuid 403D60FE0320
     */
    public void setOrgOrderChannel(String l_strOrgOrderChannel)
    {
        this.orgOrderChannel = l_strOrgOrderChannel;
    }

    /**
     * (get�����������`���l��)<BR>
     * <BR>
     * �����������`���l�����擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 403D61150264
     */
    public String getOrgOrderChannel()
    {
        return orgOrderChannel;
    }

    /**
     * (set�������萔��No)<BR>
     * <BR>
     * �������萔��No���Z�b�g����B<BR>
     * @@param l_strOrgCommissionNo - �������萔��No
     * @@roseuid 4019EAAD01D5
     */
    public void setOrgCommissionNo(String l_strOrgCommissionNo)
    {
        this.orgCommissionNo = l_strOrgCommissionNo;
    }

    /**
     * (get�������萔��No)<BR>
     * <BR>
     * �������萔��No���擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 4019EAAD01E5
     */
    public String getOrgCommissionNo()
    {
        return orgCommissionNo;
    }

    /**
     * (set�������萔��No�}��)<BR>
     * <BR>
     * �������萔��No�}�Ԃ��Z�b�g����B<BR>
     * @@param l_strOrgCommissionRevNo - �������萔��No�}��
     * @@roseuid 4019EAD50168
     */
    public void setOrgCommissionRevNo(String l_strOrgCommissionRevNo)
    {
        this.orgCommissionRevNo = l_strOrgCommissionRevNo;
    }

    /**
     * (get�������萔��No�}��)<BR>
     * <BR>
     * �������萔��No�}�Ԃ��擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 4019EAD50187
     */
    public String getOrgCommissionRevNo()
    {
        return orgCommissionRevNo;
    }
    
    /**
     * (set�萔���R�[�X�R�[�h )<BR>
     * <BR>
     * �萔���R�[�X�R�[�h���Z�b�g����B<BR>
     * @@param l_strCommissionCourseDiv - �萔���R�[�X�R�[�h
     */
    public void setCommissionCourseDiv(String l_strCommissionCourseDiv)
    {
        this.commissionCourseDiv = l_strCommissionCourseDiv;
    }
    
    /**
     * (get�萔���R�[�X�R�[�h)<BR>
     * <BR>
     * �萔���R�[�X�R�[�h���擾����B<BR>
     * @@return java.lang.String
     */
    public String getCommissionCourseDiv()
    {
        return this.commissionCourseDiv;
    }
    
    /**
     * (set�Œ�萔��)<BR>
     * <BR>
     * �Œ�萔�����Z�b�g����B<BR>
     * @@param l_dbMinCommission - �Œ�萔��
     */
    public void setMinCommission(double l_dbMinCommission)
    {
        this.minCommission = l_dbMinCommission;
    }
    
    /**
     * (get�Œ�萔��)<BR>
     * <BR>
     * �Œ�萔�����擾����B<BR>
     * @@return double
     */
    public double getMinCommission()
    {
        return this.minCommission;
    }
    
    /**
     * (set�s��R�[�h�iSONAR�j)<BR>
     * <BR>
     * �s��R�[�h�iSONAR�j���Z�b�g����B<BR>
     * @@param l_strSonarMarketCode - �s��R�[�h�iSONAR�j
     */
    public void setSonarMarketCode(String l_strSonarMarketCode)
    {
        this.sonarMarketCode = l_strSonarMarketCode;
    }
    
    /**
     * (get�s��R�[�h�iSONAR�j)<BR>
     * <BR>
     * �s��R�[�h�iSONAR�j���擾����B<BR>
     * @@return String
     */
    public String getSonarMarketCode()
    {
        return this.sonarMarketCode;
    }

    /**
     * (set�����Y�����R�[�h)<BR>
     * <BR>
     * �����Y�����R�[�h���Z�b�g����B<BR>
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h
     */
    public void setUnderlyingProductCode(String l_strUnderlyingProductCode)
    {
        this.underlyingProductCode = l_strUnderlyingProductCode;
    }
    
    /**
     * (get�����Y�����R�[�h)<BR>
     * <BR>
     * �����Y�����R�[�h���擾����B<BR>
     * @@return String
     */
    public String getUnderlyingProductCode()
    {
        return this.underlyingProductCode;
    }
    
    /**
     * (set���v��敪)<BR>
     * <BR>
     * ���v��敪���Z�b�g����B<BR>
     * @@param l_strDayTradeType - ���v��敪
     */
    public void setDayTradeType(String l_strDayTradeType)
    {
        this.dayTradeType = l_strDayTradeType;
    }
    
    /**
     * (get���v��敪)<BR>
     * <BR>
     * ���v��敪���擾����B<BR>
     * @@return String
     */
    public String getDayTradeType()
    {
        return this.dayTradeType;
    }

    /**
     * (set�萔���搔)<BR>
     * <BR>
     * �萔���搔���Z�b�g����B<BR>
     * @@param l_dblCommitionPerUnit - �萔���搔
     */
    public void setCommitionPerUnit(double l_dblCommitionPerUnit)
    {
        this.commitionPerUnit = l_dblCommitionPerUnit;
    }
    
    /**
     * (get�萔���搔)<BR>
     * <BR>
     * �萔���搔���擾����B<BR>
     * @@return double
     */
    public double getCommitionPerUnit()
    {
        return this.commitionPerUnit;
    }
    
    /**
     * (set����)<BR>
     * <BR>
     * ���ʂ��Z�b�g����B<BR>
     * @@param l_dblQuantity - ����
     */
    public void setQuantity(double l_dblQuantity)
    {
        this.quantity = l_dblQuantity;
    }
    
    /**
     * (get����)<BR>
     * <BR>
     * ���ʂ��擾����B<BR>
     * @@return double
     */
    public double getQuantity()
    {
        return this.quantity;
    }

    /**
     * (set�����)<BR>
     * <BR>
     * ����ł��Z�b�g����B<BR>
     * @@param l_dblConsumptionTax - �����
     */
    public void setConsumptionTax(double l_dblConsumptionTax)
    {
        this.consumptionTax = l_dblConsumptionTax;
    }

    /**
     * (get�����)<BR>
     * <BR>
     * ����ł��擾����B<BR>
     * @@return double
     */
    public double getConsumptionTax()
    {
        return this.consumptionTax;
    }
}
@
