head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAmountCalcResultFactor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������z�v�Z���ʁi���j(WEB3FeqAmountCalcResultFactor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���I (���u) �V�K�쐬
                   2005/07/27 鰊](���u) ���r���[
*/

package webbroker3.feq;


/**
 * (�O���������z�v�Z���ʁi���j) <BR>
 * �O���������z�v�Z���ʁi���j<BR>
 * <BR>
 * @@ author ���I <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3FeqAmountCalcResultFactor 
{
    
    /**
     * (�O���������z�v�Z���ʁi���ׁj) <BR>
     * �O���������z�v�Z���ʁi���ׁj
     */
    private WEB3FeqAmountCalcResult[] feqAmountCalcResultDetails;
    
    /**
     * (�O���������z�v�Z���ʁi���v�j) <BR>
     * �O���������z�v�Z���ʁi���v�j
     */
    private WEB3FeqAmountCalcResult feqAmountCalcResultTotal;
    
    /**
     * @@roseuid 42CE39E700CB
     */
    public WEB3FeqAmountCalcResultFactor() 
    {
     
    }
    
    /**
     * (�O���������z�v�Z���ʁi���j) <BR>
     * <BR>
     * �����̒l���v���p�e�B�ɃZ�b�g���ăI�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_feqAmountCalcResultDetails - (�O���������z�v�Z���ʁi���ׁj) <BR>
     * �O���������z�v�Z���ʁi���ׁj
     * @@param l_feqAmountCalcResultTotal - (�O���������z�v�Z���ʁi���v�j) <BR>
     * �O���������z�v�Z���ʁi���v�j
     * @@roseuid 428AA6940214
     */
    public WEB3FeqAmountCalcResultFactor(WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails, WEB3FeqAmountCalcResult l_feqAmountCalcResultTotal) 
    {
        feqAmountCalcResultDetails = l_feqAmountCalcResultDetails;
        feqAmountCalcResultTotal = l_feqAmountCalcResultTotal;  
    }
    
    /**
     * (get�O���������z�v�Z���ʁi���v�j) <BR>
     * this.�O���������z�v�Z���ʁi���v�j��ԋp����B
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 428AA6D6006E
     */
    public WEB3FeqAmountCalcResult getFeqAmountCalcResultTotal() 
    {
        return feqAmountCalcResultTotal;
    }
    
    /**
     * (get�O���������z�v�Z���ʁi���ׁj) <BR>
     * this.�O���������z�v�Z���ʁi���ׁj[index]��ԋp����B
     * @@param l_intIndex - index�i�F�v�f�ԍ��j
     * 
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42B27B0B02D2
     */
    public WEB3FeqAmountCalcResult getFeqAmountCalcResultDetails(int l_intIndex) 
    {
        return feqAmountCalcResultDetails[l_intIndex];
    }
    
    /**
     * (set�O���������z�v�Z���ʁi���ׁj) <BR>
     * �O���������z�v�Z���ʁi���ׁj���Z�b�g����B
     * @@param l_feqAmountCalcResultDetails - (�O���������z�v�Z���ʁi���ׁj) <BR>
     * �O���������z�v�Z���ʁi���ׁj
     * @@roseuid 428AA6F503CA
     */
    public void setFeqAmountCalcResultDetails(WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails) 
    {
        this.feqAmountCalcResultDetails = l_feqAmountCalcResultDetails;
    }
    
    /**
     * (set�O���������z�v�Z���ʁi���v)) <BR>
     * �O���������z�v�Z���ʁi���v�j���Z�b�g����B
     * @@param l_feqAmountCalcResultTotal - �O���������z�v�Z���ʁi���v�j
     * @@roseuid 428AA6F503CB
     */
    public void setFeqAmountCalcResultTotal(WEB3FeqAmountCalcResult l_feqAmountCalcResultTotal) 
    {
        feqAmountCalcResultTotal = l_feqAmountCalcResultTotal; 
    }
    
    /**
     * (get���̑��R�X�g�P) <BR>
     * ���̑��R�X�g�P���擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj[index].get���̑��R�X�g�P()  <BR>
     * ��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00027
     */
    public double getForeignFeeExt1(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getForeignFeeExt1();
    }
    
    /**
     * (get���̑��R�X�g�Q) <BR>
     * ���̑��R�X�g�Q���擾����B<BR>
     * <BR>
     * this.�O���������z�v�Z���ʁi���ׁj[index].get���̑��R�X�g�Q() <BR>
     * ��ԋp����B<BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00046
     */
    public double getForeignFeeExt2(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getForeignFeeExt2();
    }
    
    /**
     * �ϑ��萔�����擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj <BR>
     * [index].get�ϑ��萔��()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00055
     */
    public double getCommisionFee(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommissionFee();
    }
    
    /**
     * (get�ϑ��萔���i�O�݁j) <BR>
     * �ϑ��萔���i�O�݁j���擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj <BR>
     * [index].get�ϑ��萔���i�O�݁j()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00065
     */
    public double getCommisionFeeFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommissionFeeFc();
    }
    
    /**
     * (get�ϑ��萔�������) <BR>
     * �ϑ��萔������ł��擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj <BR>
     * [index].get�ϑ��萔�������()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00075
     */
    public double getCommisionFeeTax(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommisionFeeTax();
    }
    
    /**
     * (get�ϑ��萔������Łi�O�݁j) <BR>
     * �ϑ��萔������Łi�O�݁j���擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj <BR>
     * [index].get�ϑ��萔������Łi�O�݁j()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00084
     */
    public double getCommisionFeeTaxFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommisionFeeTaxFc();
    }
    
    /**
     * (get���n�����) <BR>
     * ���n����ł��擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj[index].get���n�����()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00094
     */
    public double getForeignTax(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getForeignTax();
    }
    
    /**
     * (get���n�萔��) <BR>
     * ���n�萔�����擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj[index].get���n�萔��() <BR>
     * ��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC000A4
     */
    public double getForeignCommissionFee(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getForignCommissionFee();
    }
    
    /**
     * (get���n���Z���) <BR>
     * ���n���Z������擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj <BR>
     * [index].get���n���Z���()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC000B3
     */
    public double getBalanceAmountFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getBalanceAmountFc();
    }
    
    /**
     * (get���n���Z����i�~�݁j) <BR>
     * ���n���Z����i�~�݁j���擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj <BR>
     * [index].get���n���Z����i�~�݁j()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC000D2
     */
    public double getBalanceAmount(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getBalanceAmount();
    }
    
    /**
     * (get�萔��No) <BR>
     * �萔��No���擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj[index].get�萔��No.() <BR>
     * ��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return String
     * @@roseuid 42A80EC000F2
     */
    public String getCommisionNumber(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommissionNumber();
    }
    
    /**
     * (get�萔��No�}��) <BR>
     * �萔��No�}�Ԃ��擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj[index].get�ϑ�No.�}��() <BR>
     * ��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return String
     * @@roseuid 42A80EC00101
     */
    public String getCommisionBranchNumber(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getCommissionBranchNumber();
    }
    
    /**
     * (get��n���) <BR>
     * ��n������擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj[index].get��n���()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00111
     */
    public double getNetAmount(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getNetAmount();
    }
    
    /**
     * (get��n����i�O�݁j) <BR>
     * ��n����i�O�݁j���擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj <BR>
     * [index].get��n����i�O�݁j()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00121
     */
    public double getNetAmountFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getNetAmountFc();
    }
    
    /**
     * (get��������i�~�݁j) <BR>
     * ��������i�~�݁j���擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj <BR>
     * [index].get��������i�~�݁j()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00130
     */
    public double getTradePrice(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getTradePrice();
    }
    
    /**
     * (get��������i�O�݁j) <BR>
     * ��������i�O�݁j���擾����B <BR>
     *  <BR>
     * this.�O���������z�v�Z���ʁi���ׁj <BR>
     * [index].get��������i�O�݁j()��ԋp����B <BR>
     * @@param l_intIndex - (index) <BR>
     * index�i�F�v�f�ԍ��j
     * 
     * @@return double
     * @@roseuid 42A80EC00140
     */
    public double getTradePriceFc(int l_intIndex) 
    {
        return this.feqAmountCalcResultDetails[l_intIndex].getTradePriceFc();
    }
}
@
