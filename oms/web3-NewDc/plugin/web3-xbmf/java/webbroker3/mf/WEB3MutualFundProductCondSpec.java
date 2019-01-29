head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundProductCondSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�����������e�f�[�^�N���X(WEB3MutualFundProductCondSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 ������ (���u) �V�K�쐬
Revesion History : 2004/08/23 ����� (���u) ���r���[
Revesion History : 2004/12/06 ������ (���u) �c�Ή�
Revesion History : 2005/10/18 ��O�� (���u) �t�B�f���e�B�Ή�
Revesion History : 2007/04/09 �����F (���u)�@@���f��547
*/

package webbroker3.mf;

import java.util.Date;

/**
 * ���M�����������e�@@�f�[�^�N���X<BR>
 * <BR>
 * [�����M���Ǘ��ҁ@@���������o�^�T�[�r�X]�ɂāA<BR>
 * �Ǘ��҂����͂����������i�[����B<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualFundProductCondSpec
{

    /**
     * ���M�����R�[�h
     */
    private String mutualProductCode;
    
    /**
     * ���t�J�n��<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private Date buyStartDate;

    /**
     * ���t�I����<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private Date buyEndDate;

    /**
     * ���抷�J�n��<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private Date sellSwitchingStartDate;

    /**
     * ���抷�I����<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private Date sellSwitchingEndDate;

    /**
     * ���搿���J�n��<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private Date buyClaimStartDate;

    /**
     * ���搿���I����<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private Date buyClaimEndDate;
    
    /**
     * ��W�J�n��<BR>
     */
    private Date applyAbleStartDate;
    
    /**
     * ��W�I����<BR>
     */
    private Date applyAbleEndDate;

    /**
     * �w����@@�i���t�j<BR>
     * <BR>
     * 0:�I���w��@@3:���z�@@4:����<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String buySelectable;

    /**
     * �Œ�����i�V�K���t�j<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String newBuyMinQty;

    /**
     * �P�ʌ����i�V�K���t�j<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String newBuyUnitQty;

    /**
     * (�Œ���z�i�V�K���t�j)<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String newBuyMinAmt;

    /**
     * (�P�ʋ��z�i�V�K���t�j)<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String newBuyUnitAmt;

    /**
     * �Œ�����i�ǉ����t�j<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String addBuyMinQty;

    /**
     * �P�ʌ����i�ǉ����t�j<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String addBuyUnitQty;

    /**
     * (�Œ���z�i�ǉ����t�j)<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String addBuyMinAmt;

    /**
     * (�P�ʋ��z�i�ǉ����t�j)<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String addBuyUnitAmt;

    /**
     * �w����@@�i���j<BR>
     * <BR>
     * 0:�I���w��@@3:���z�@@4:����<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String sellSelectable;

    /**
     * �Œ�����i���j<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String sellMinQty;

    /**
     * �P�ʌ����i���j<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String sellUnitQty;

    /**
     * (�Œ���z�i���j)<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String sellMinAmt;

    /**
     * (�P�ʋ��z�i���j)<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String sellUnitAmt;

    /**
     * �w����@@�i�抷�j<BR>
     * <BR>
     * 0:�I���w��@@3:���z�@@4:����<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String switchingSelectable;

    /**
     * �Œ�����i�抷�j<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String switchingMinQty;

    /**
     * �P�ʌ����i�抷�j<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String switchingUnitQty;

    /**
     * (�Œ���z�i�抷�j)<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String switchingMinAmt;

    /**
     * (�P�ʋ��z�i�抷�j)<BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    private String switchingUnitAmt;
    
    /**
     * (�w����@@�i��W�j)<BR>
     * �w����@@�i��W�j <BR>
     * <BR>
     * 0:�I���w��@@3:���z�@@4:���� <BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���) <BR>
     */
    private String applySelectable;
    
    /**
     * (�Œ�����i��W�j)<BR>
     * �Œ�����i��W�j <BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���) <BR>
     */
    private String applyMinQty;
    
    /**
     * (�P�ʌ����i��W�j)<BR>
     * �P�ʌ����i��W�j <BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���) <BR>
     */
    private String applyUnitQty;
    
    /**
     * (�Œ���z�i��W�j)<BR>
     * �Œ���z�i��W�j <BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���) <BR>
     */
    private String applyMinAmt;
    
    /**
     * (�P�ʋ��z�i��W�j)<BR>
     * �P�ʋ��z�i��W�j <BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���) <BR>
     */
    private String applyUnitAmt;
    
    /**
     * (���t�\�敪�i�����������j)<BR>
     *  0�F�s�@@<BR>
     *  1�F�� <BR>
     */
    private String buyPossibleDivTheDay;
    
    /**
     * (���\�敪�i�����������j)<BR>
     *  0�F�s�@@<BR>
     *  1�F�� <BR>
     */
    private String sellPossibleDivTheDay;
    
    /**
     * (�抷�\�敪�i�����������j)<BR>
     *  0�F�s�@@<BR>
     *  1�F�� <BR>
     */
    private String swtPossibleDivTheDay;
    
    /**
     * (��W�\�敪�i�����������j)<BR>
     *  0�F�s�@@<BR>
     *  1�F�� <BR>
     */
    private String applyPossDivTheDay;
    
    /**
     * (���t�\�敪�i�����������j)<BR>
     *  0�F�s�@@<BR>
     *  1�F�� <BR>
     */
    private String buyPossibleDivTheNextDay;
    
    /**
     * (���\�敪�i�����������j)<BR>
     *  0�F�s�@@<BR>
     *  1�F�� <BR>
     */
    private String sellPossibleDivTheNextDay;
    
    /**
     * (�抷�\�敪�i�����������j)<BR>
     *  0�F�s�@@<BR>
     *  1�F�� <BR>
     */
    private String swtPossibleDivTheNextDay;
    
    /**
     * (��W�\�敪�i�����������j)<BR>
     *  0�F�s�@@<BR>
     *  1�F�� <BR>
     */
    private String applyPossDivTheNextDay;
    
    /**
     * (��t���؊J�n���ԁi�����j)<BR>
     * �����Fhhmm<BR>
     */
    private String orderCloseStartTimeFull;
    
    /**
     * (��t���؏I�����ԁi�����j)<BR>
     * �����Fhhmm<BR>
     */
    private String orderCloseEndTimeFull;
    
    /**
     * (��t���؊J�n���ԁi�����j)<BR>
     * �����Fhhmm<BR>
     */
    private String orderCloseStartTimeHalf;
    
    /**
     * (��t���؏I�����ԁi�����j)<BR>
     * �����Fhhmm<BR>
     */
    private String orderCloseEndTimeHalf;

    /**
     * (�O�ݍŒ���z�i�V�K���t�j)<BR>
     * �O�ݍŒ���z�i�V�K���t�j<BR>
     */
    private String buyFrgnMinAmtBuy;

    /**
     * (�O�ݒP�ʋ��z�i�V�K���t�j)<BR>
     * �O�ݒP�ʋ��z�i�V�K���t�j<BR>
     */
    private String buyFrgnUnitAmtBuy;

    /**
     * (�O�ݍŒ���z�i�ǉ����t�j)<BR>
     * �O�ݍŒ���z�i�ǉ����t�j<BR>
     */
    private String buyFrgnMinAmtAdd;

    /**
     * (�O�ݒP�ʋ��z�i�ǉ����t�j)<BR>
     * �O�ݒP�ʋ��z�i�ǉ����t�j<BR>
     */
    private String buyFrgnUnitAmtAdd;

    /**
     * (�O�ݍŒ���z�i���j)<BR>
     * �O�ݍŒ���z�i���j
     */
    private String sellFrgnMinAmtSell;

    /**
     * (�O�ݒP�ʋ��z�i���j)<BR>
     * �O�ݒP�ʋ��z�i���j<BR>
     */
    private String sellFrgnUnitAmtSell;

    /**
     * (���M�����������e)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40E9338C000E
     */
    public WEB3MutualFundProductCondSpec()
    {

    }

    /**
     * (get�����R�[�h)<BR>
     * this.�����R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92AC003C7
     */
    public String getMutualProductCode()
    {
        return this.mutualProductCode;
    }

    /**
     * (set�����R�[�h)<BR>
     * �����R�[�h��ݒ肷��B<BR>
     * @@param l_strProductCode - �����R�[�h
     * @@roseuid 40E92AC601F2
     */
    public void setMutualProductCode(String l_strProductCode)
    {
        this.mutualProductCode = l_strProductCode;
    }

    /**
     * (get���t�J�n��)<BR>
     * this.���t�J�n����ԋp����B<BR>
     * @@return Date
     * @@roseuid 40E92ACE008B
     */
    public Date getBuyStartDate()
    {
        return this.buyStartDate;
    }

    /**
     * (set���t�J�n��)<BR>
     * ���t�J�n����ݒ肷��B<BR>
     * @@param l_datBuyStartDate - ���t�J�n��
     * @@roseuid 40E92AD50146
     */
    public void setBuyStartDate(Date l_datBuyStartDate)
    {
        this.buyStartDate = l_datBuyStartDate;
    }

    /**
     * (get���t�I����)<BR>
     * this.���t�I������ԋp����B<BR>
     * @@return Date
     * @@roseuid 40E92AE700F8
     */
    public Date getBuyEndDate()
    {
        return this.buyEndDate;
    }

    /**
     * (set���t�I����)<BR>
     * ���t�I������ݒ肷��B<BR>
     * @@param l_acquiredEndDate - ���t�I����
     * @@roseuid 40E92AE700F9
     */
    public void setBuyEndDate(Date l_acquiredEndDate)
    {
        this.buyEndDate = l_acquiredEndDate;
    }

    /**
     * (get���抷�J�n��)<BR>
     * this.���抷�J�n����ԋp����B<BR>
     * @@return Date
     * @@roseuid 40E92AE700FA
     */
    public Date getSellSwitchingStartDate()
    {
        return this.sellSwitchingStartDate;
    }

    /**
     * (set���抷�J�n��)<BR>
     * ���抷�J�n����ݒ肷��B<BR>
     * @@param l_sellSwitchingStartDate - ���抷�J�n��
     * @@roseuid 40E92AE700FB
     */
    public void setSellSwitchingStartDate(Date l_sellSwitchingStartDate)
    {
        this.sellSwitchingStartDate = l_sellSwitchingStartDate;
    }

    /**
     * (get���抷�I����)<BR>
     * this.���抷�I������ԋp����B<BR>
     * @@return Date
     * @@roseuid 40E939830211
     */
    public Date getSellSwitchingEndDate()
    {
        return this.sellSwitchingEndDate;
    }

    /**
     * (set���抷�I����)<BR>
     * ���抷�I������ݒ肷��B<BR>
     * @@param l_sellSwitchingEndDate - ���抷�I����
     * @@roseuid 40E9398A0127
     */
    public void setSellSwitchingEndDate(Date l_sellSwitchingEndDate)
    {
        this.sellSwitchingEndDate = l_sellSwitchingEndDate;
    }

    /**
     * (get���搿���J�n��)<BR>
     * this.���搿���J�n����ԋp����B<BR>
     * @@return Date
     * @@roseuid 40E92AE802AD
     */
    public Date getBuyClaimStartDate()
    {
        return this.buyClaimStartDate;
    }

    /**
     * (set���搿���J�n��)<BR>
     * ���搿���J�n����ݒ肷��B<BR>
     * @@param l_buyRequestStartDate - ���搿���J�n��
     * @@roseuid 40E92AE802AE
     */
    public void setBuyClaimStartDate(Date l_buyRequestStartDate)
    {
        this.buyClaimStartDate = l_buyRequestStartDate;
    }

    /**
     * (get���搿���I����)<BR>
     * this.���搿���I������ԋp����B<BR>
     * @@return Date
     * @@roseuid 40E939C40202
     */
    public Date getBuyClaimEndDate()
    {
        return this.buyClaimEndDate;
    }

    /**
     * (set���搿���I����)<BR>
     * ���搿���I������ݒ肷��B<BR>
     * @@param l_datBuyClaimEndDate - ���搿���I����
     * @@roseuid 40E939C702AD
     */
    public void setBuyClaimEndDate(Date l_datBuyClaimEndDate)
    {
        this.buyClaimEndDate = l_datBuyClaimEndDate;
    }

    /**
     * (get�w����@@�i���t�j)<BR>
     * this.�w����@@�i���t�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92AE802AF
     */
    public String getBuySelectable()
    {
        return this.buySelectable;
    }

    /**
     * (set�w����@@�i���t�j)<BR>
     * �w����@@�i���t�j��ݒ肷��B<BR>
     * @@param l_strDesignateMethodAcquired - �w����@@�i���t�j
     * @@roseuid 40E92AE802B0
     */
    public void setBuySelectable(String l_strDesignateMethodAcquired)
    {
        this.buySelectable = l_strDesignateMethodAcquired;
    }

    /**
     * (get�Œ�����i�V�K���t�j)<BR>
     * this.�Œ�����i�V�K���t�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92AE902CD
     */
    public String getNewBuyMinQty()
    {
        return this.newBuyMinQty;
    }

    /**
     * (set�Œ�����i�V�K���t�j)<BR>
     * �Œ�����i�V�K���t�j��ݒ肷��B<BR>
     * @@param l_strMinQuantityOpenAcquired - �Œ�����i�V�K���t�j
     * @@roseuid 40E92AE902CE
     */
    public void setNewBuyMinQty(String l_strMinQuantityOpenAcquired)
    {
        this.newBuyMinQty = l_strMinQuantityOpenAcquired;
    }

    /**
     * (get�P�ʌ����i�V�K���t�j)<BR>
     * this.�P�ʌ����i�V�K���t�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92AE902CF
     */
    public String getNewBuyUnitQty()
    {
        return this.newBuyUnitQty;
    }

    /**
     * (set�P�ʌ����i�V�K���t�j)<BR>
     * �P�ʌ����i�V�K���t�j��ݒ肷��B<BR>
     * @@param l_strUnitQuantityOpenAcquired - �P�ʌ����i�V�K���t�j
     * @@roseuid 40E92AE902D0
     */
    public void setNewBuyUnitQty(String l_strUnitQuantityOpenAcquired)
    {
        this.newBuyUnitQty = l_strUnitQuantityOpenAcquired;
    }

    /**
     * (get�P�ʌ����i�ǉ����t�j)<BR>
     * this.�P�ʌ����i�ǉ����t�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92AEB03A7
     */
    public String getAddBuyUnitQty()
    {
        return this.addBuyUnitQty;
    }

    /**
     * (get�Œ���z�i�V�K���t�j)<BR>
     * this.�Œ���z�i�V�K���t�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FE950051
     */
    public String getNewBuyMinAmt()
    {
        return this.newBuyMinAmt;
    }

    /**
     * (set�Œ���z�i�V�K���t�j)<BR>
     * �Œ���z�i�V�K���t�j��ݒ肷��B<BR>
     * @@param l_strMinPriceOpenAcquired - �Œ���z�i�V�K���t�j
     * @@roseuid 40E9FE950060
     */
    public void setNewBuyMinAmt(String l_strMinPriceOpenAcquired)
    {
        this.newBuyMinAmt = l_strMinPriceOpenAcquired;
    }

    /**
     * (get�P�ʋ��z�i�V�K���t�j)<BR>
     * this.�P�ʋ��z�i�V�K���t�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FE950062
     */
    public String getNewBuyUnitAmt()
    {
        return this.newBuyUnitAmt;
    }

    /**
     * (set�P�ʋ��z�i�V�K���t�j)<BR>
     * �P�ʋ��z�i�V�K���t�j��ݒ肷��B<BR>
     * @@param l_strUnitPriceOpenAcquired - �P�ʋ��z�i�V�K���t�j
     * @@roseuid 40E9FE950070
     */
    public void setNewBuyUnitAmt(String l_strUnitPriceOpenAcquired)
    {
        this.newBuyUnitAmt = l_strUnitPriceOpenAcquired;
    }

    /**
     * (set�P�ʌ����i�ǉ����t�j)<BR>
     * �P�ʌ����i�ǉ����t�j��ݒ肷��B<BR>
     * @@param l_strUnitQuantityAddingAcquired - �P�ʌ����i�ǉ����t�j
     * @@roseuid 40E92AEB03B9
     */
    public void setAddBuyUnitQty(String l_strUnitQuantityAddingAcquired)
    {
        this.addBuyUnitQty = l_strUnitQuantityAddingAcquired;
    }

    /**
     * (get�Œ�����i�ǉ����t�j)<BR>
     * this.�Œ�����i�ǉ����t�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92AEB03B8
     */
    public String getAddBuyMinQty()
    {
        return this.addBuyMinQty;
    }

    /**
     * (set�Œ�����i�ǉ����t�j)<BR>
     * �Œ�����i�ǉ����t�j��ݒ肷��B<BR>
     * @@param l_strMinQuantityAddingAcquired - �Œ�����i�ǉ����t�j
     * @@roseuid 40E92AEB03B7
     */
    public void setAddBuyMinQty(String l_strMinQuantityAddingAcquired)
    {
        this.addBuyMinQty = l_strMinQuantityAddingAcquired;
    }

    /**
     * (get�Œ���z�i�ǉ����t�j)<BR>
     * this.�Œ���z�i�ǉ����t�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FEF20080
     */
    public String getAddBuyMinAmt()
    {
        return this.addBuyMinAmt;
    }

    /**
     * (set�Œ���z�i�ǉ����t�j)<BR>
     * �Œ���z�i�ǉ����t�j��ݒ肷��B<BR>
     * @@param l_strMinPriceAddingAcquired - �Œ���z�i�ǉ����t�j
     * @@roseuid 40E9FEF2008F
     */
    public void setAddBuyMinAmt(String l_strMinPriceAddingAcquired)
    {
        this.addBuyMinAmt = l_strMinPriceAddingAcquired;
    }

    /**
     * (get�P�ʋ��z�i�ǉ����t�j)<BR>
     * this.�P�ʋ��z�i�ǉ����t�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FEF20091
     */
    public String getAddBuyUnitAmt()
    {
        return this.addBuyUnitAmt;
    }

    /**
     * (set�P�ʋ��z�i�ǉ����t�j)<BR>
     * �P�ʋ��z�i�ǉ����t�j��ݒ肷��B<BR>
     * @@param l_strUnitPriceAddingAcquired - �P�ʋ��z�i�ǉ����t�j
     * @@roseuid 40E9FEF2009F
     */
    public void setAddBuyUnitAmt(String l_strUnitPriceAddingAcquired)
    {
        this.addBuyUnitAmt = l_strUnitPriceAddingAcquired;
    }

    /**
     * (get�w����@@�i���j)<BR>
     * this.�w����@@�i���j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92BA3033A
     */
    public String getSellSelectable()
    {
        return this.sellSelectable;
    }

    /**
     * (set�w����@@�i���j)<BR>
     * �w����@@�i���j��ݒ肷��B<BR>
     * @@param l_strDesignateMethodSell - �w����@@�i���j
     * @@roseuid 40E92BA3033B
     */
    public void setSellSelectable(String l_strDesignateMethodSell)
    {
        this.sellSelectable = l_strDesignateMethodSell;
    }

    /**
     * (get�Œ�����i���j)<BR>
     * this.�Œ�����i���j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92BAC02BD
     */
    public String getSellMinQty()
    {
        return this.sellMinQty;
    }

    /**
     * (set�Œ�����i���j)<BR>
     * �Œ�����i���j��ݒ肷��B<BR>
     * @@param l_strMinQuantitySell - �Œ�����i���j
     * @@roseuid 40E92BAC02CD
     */
    public void setSellMinQty(String l_strMinQuantitySell)
    {
        this.sellMinQty = l_strMinQuantitySell;
    }

    /**
     * (get�P�ʌ����i���j)<BR>
     * this.�Œ�����i���j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92BAC02CE
     */
    public String getSellUnitQty()
    {
        return this.sellUnitQty;
    }

    /**
     * (set�P�ʌ����i���j)<BR>
     * �P�ʌ����i���j��ݒ肷��B<BR>
     * @@param l_strUnitQuantitySell - �P�ʌ����i���j
     * @@roseuid 40E92BAC02CF
     */
    public void setSellUnitQty(String l_strUnitQuantitySell)
    {
        this.sellUnitQty = l_strUnitQuantitySell;
    }

    /**
     * (get�Œ���z�i���j)<BR>
     * this.�Œ���z�i���j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FF32010C
     */
    public String getSellMinAmt()
    {
        return this.sellMinAmt;
    }

    /**
     * (set�Œ���z�i���)<BR>
     * �Œ���z�i���j��ݒ肷��B<BR>
     * @@param l_strMinPriceSell - �Œ���z�i���j
     * @@roseuid 40E9FF32010D
     */
    public void setSellMinAmt(String l_strMinPriceSell)
    {
        this.sellMinAmt = l_strMinPriceSell;
    }

    /**
     * (get�P�ʋ��z�i���j)<BR>
     * this.�P�ʋ��z�i���j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FF32011C
     */
    public String getSellUnitAmt()
    {
        return this.sellUnitAmt;
    }

    /**
     * (set�P�ʋ��z�i���j)<BR>
     * �P�ʋ��z�i���j��ݒ肷��B<BR>
     * @@param l_strUnitPriceSell - �P�ʋ��z�i���j
     * @@roseuid 40E9FF32012C
     */
    public void setSellUnitAmt(String l_strUnitPriceSell)
    {
        this.sellUnitAmt = l_strUnitPriceSell;
    }

    /**
     * (get�w����@@�i�抷�j)<BR>
     * this.�w����@@�i�抷�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92BB00211
     */
    public String getSwitchingSelectable()
    {
        return this.switchingSelectable;
    }

    /**
     * (set�w����@@�i�抷�j)<BR>
     * �w����@@�i�抷�j��ݒ肷��B<BR>
     * @@param l_strDesignateMethodSwitching - �w����@@�i�抷�j
     * @@roseuid 40E92BB00212
     */
    public void setSwitchingSelectable(String l_strDesignateMethodSwitching)
    {
        this.switchingSelectable = l_strDesignateMethodSwitching;
    }

    /**
     * (get�Œ�����i�抷�j)<BR>
     * this.�Œ�����i�抷�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92BB00213
     */
    public String getSwitchingMinQty()
    {
        return this.switchingMinQty;
    }

    /**
     * (set�Œ�����i�抷�j)<BR>
     * �Œ�����i�抷�j��ݒ肷��B<BR>
     * @@param l_strMinQuantitySwitching - �Œ�����i�抷�j
     * @@roseuid 40E92BB00214
     */
    public void setSwitchingMinQty(String l_strMinQuantitySwitching)
    {
        this.switchingMinQty = l_strMinQuantitySwitching;
    }

    /**
     * (get�P�ʌ����i�抷�j)<BR>
     * this.�P�ʌ����i�抷�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92BB00215
     */
    public String getSwitchingUnitQty()
    {
        return this.switchingUnitQty;
    }

    /**
     * (set�P�ʌ����i�抷�j)<BR>
     * �P�ʌ����i�抷�j��ݒ肷��B<BR>
     * @@param l_strUnitQuantitySwitching - �P�ʌ����i�抷�j
     * @@roseuid 40E92BB00221
     */
    public void setSwitchingUnitQty(String l_strUnitQuantitySwitching)
    {
        this.switchingUnitQty = l_strUnitQuantitySwitching;
    }

    /**
     * (get�Œ���z�i�抷�j)<BR>
     * this.�Œ���z�i�抷�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB0189
     */
    public String getSwitchingMinAmt()
    {
        return this.switchingMinAmt;
    }

    /**
     * (set�Œ���z�i�抷�j)<BR>
     * �Œ���z�i�抷�j��ݒ肷��B<BR>
     * @@param l_strMinPriceSwitching - �Œ���z�i�抷�j
     * @@roseuid 40E9FFAB0199
     */
    public void setSwitchingMinAmt(String l_strMinPriceSwitching)
    {
        this.switchingMinAmt = l_strMinPriceSwitching;
    }

    /**
     * (get�P�ʋ��z�i�抷�j)<BR>
     * this.�P�ʋ��z�i�抷�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSwitchingUnitAmt()
    {
        return this.switchingUnitAmt;
    }

    /**
     * (set�P�ʋ��z�i�抷�j)<BR>
     * �P�ʋ��z�i�抷�j��ݒ肷��B<BR>
     * @@param l_strUnitPriceSwitching - �P�ʋ��z�i�抷�j
     * @@roseuid 40E9FFAB01B8
     */
    public void setSwitchingUnitAmt(String l_strUnitPriceSwitching)
    {
        this.switchingUnitAmt = l_strUnitPriceSwitching;
    }
    
    /**
     * (get���t�\�敪�i�����������j)<BR>
     * this.���t�\�敪�i�����������j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getBuyPossibleDivTheDay()
    {
        return buyPossibleDivTheDay;
    }
    
    /**
     * (set���t�\�敪�i�����������j)<BR>
     * ���t�\�敪�i�����������j��ݒ肷��B<BR>
     * @@param l_strBuyPossibleDivTheDay - ���t�\�敪�i�����������j
     * @@roseuid 40E9FFAB01B8
     */
    public void setBuyPossibleDivTheDay(String l_strBuyPossibleDivTheDay)
    {
        this.buyPossibleDivTheDay = l_strBuyPossibleDivTheDay;
    }
    
    /**
     * (get���t�\�敪�i�����������j)<BR>
     * this.���t�\�敪�i�����������j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getBuyPossibleDivTheNextDay()
    {
        return buyPossibleDivTheNextDay;
    }
    
    /**
     * (set���t�\�敪�i�����������j)<BR>
     * ���t�\�敪�i�����������j��ݒ肷��B<BR>
     * @@param l_strBuyPossibleDivTheNextDay - ���t�\�敪�i�����������j
     * @@roseuid 40E9FFAB01B8
     */
    public void setBuyPossibleDivTheNextDay(String l_strBuyPossibleDivTheNextDay)
    {
        this.buyPossibleDivTheNextDay = l_strBuyPossibleDivTheNextDay;
    }
    
    /**
     * (get�������؏I�����ԁi�����j)<BR>
     * this.�������؏I�����ԁi�����j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getOrderCloseEndTimeFull()
    {
        return orderCloseEndTimeFull;
    }
    
    /**
     * (set�������؏I�����ԁi�����j)<BR>
     * �������؏I�����ԁi�����j��ݒ肷��B<BR>
     * @@param l_strOrderCloseEndTimeFull - �������؏I�����ԁi�����j
     * @@roseuid 40E9FFAB01B8
     */
    public void setOrderCloseEndTimeFull(String l_strOrderCloseEndTimeFull)
    {
        this.orderCloseEndTimeFull = l_strOrderCloseEndTimeFull;
    }
    
    /**
     * (get�������؏I�����ԁi�����j)<BR>
     * this.�������؏I�����ԁi�����j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getOrderCloseEndTimeHalf()
    {
        return orderCloseEndTimeHalf;
    }
    
    /**
     * (set�������؏I�����ԁi�����j)<BR>
     * �������؏I�����ԁi�����j��ݒ肷��B<BR>
     * @@param l_strOrderCloseEndTimeHalf - �������؏I�����ԁi�����j
     * @@roseuid 40E9FFAB01B8
     */
    public void setOrderCloseEndTimeHalf(String l_strOrderCloseEndTimeHalf)
    {
        this.orderCloseEndTimeHalf = l_strOrderCloseEndTimeHalf;
    }
    
    /**
     * (get�������؊J�n���ԁi�����j)<BR>
     * this.�������؊J�n���ԁi�����j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getOrderCloseStartTimeFull()
    {
        return orderCloseStartTimeFull;
    }
    
    /**
     * (set�������؊J�n���ԁi�����j)<BR>
     * �������؊J�n���ԁi�����j��ݒ肷��B<BR>
     * @@param l_strOrderCloseStartTimeFull - �������؊J�n���ԁi�����j
     * @@roseuid 40E9FFAB01B8
     */
    public void setOrderCloseStartTimeFull(String l_strOrderCloseStartTimeFull)
    {
        this.orderCloseStartTimeFull = l_strOrderCloseStartTimeFull;
    }
    
    /**
     * (get�������؊J�n���ԁi�����j)<BR>
     * this.�������؊J�n���ԁi�����j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getOrderCloseStartTimeHalf()
    {
        return orderCloseStartTimeHalf;
    }
    
    /**
     * (set�������؊J�n���ԁi�����j)<BR>
     * �������؊J�n���ԁi�����j��ݒ肷��B<BR>
     * @@param l_strOrderCloseStartTimeHalf - �������؊J�n���ԁi�����j
     * @@roseuid 40E9FFAB01B8
     */
    public void setOrderCloseStartTimeHalf(String l_strOrderCloseStartTimeHalf)
    {
        this.orderCloseStartTimeHalf = l_strOrderCloseStartTimeHalf;
    }
    
    /**
     * (get���\�敪�i�����������j)<BR>
     * this.���\�敪�i�����������j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSellPossibleDivTheDay()
    {
        return sellPossibleDivTheDay;
    }
    
    /**
     * (set���\�敪�i�����������j)<BR>
     * ���\�敪�i�����������j��ݒ肷��B<BR>
     * @@param l_strSellPossibleDivTheDay - ���\�敪�i�����������j
     * @@roseuid 40E9FFAB01B8
     */
    public void setSellPossibleDivTheDay(String l_strSellPossibleDivTheDay)
    {
        this.sellPossibleDivTheDay = l_strSellPossibleDivTheDay;
    }
    
    /**
     * (get���\�敪�i�����������j)<BR>
     * this.���\�敪�i�����������j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSellPossibleDivTheNextDay()
    {
        return sellPossibleDivTheNextDay;
    }
    
    /**
     * (set���\�敪�i�����������j)<BR>
     * ���\�敪�i�����������j��ݒ肷��B<BR>
     * @@param l_strSellPossibleDivTheNextDay - ���\�敪�i�����������j
     * @@roseuid 40E9FFAB01B8
     */
    public void setSellPossibleDivTheNextDay(String l_strSellPossibleDivTheNextDay)
    {
        this.sellPossibleDivTheNextDay = l_strSellPossibleDivTheNextDay;
    }
    
    /**
     * (get�抷�\�敪�i�����������j)<BR>
     * this.�抷�\�敪�i�����������j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSwtPossibleDivTheDay()
    {
        return swtPossibleDivTheDay;
    }
    
    /**
     * (set�抷�\�敪�i�����������j)<BR>
     * �抷�\�敪�i�����������j��ݒ肷��B<BR>
     * @@param l_strSwtPossibleDivTheDay - �抷�\�敪�i�����������j
     * @@roseuid 40E9FFAB01B8
     */
    public void setSwtPossibleDivTheDay(String l_strSwtPossibleDivTheDay)
    {
        this.swtPossibleDivTheDay = l_strSwtPossibleDivTheDay;
    }
    
    /**
     * (get�抷�\�敪�i�����������j)<BR>
     * this.�抷�\�敪�i�����������j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E9FFAB01A9
     */
    public String getSwtPossibleDivTheNextDay()
    {
        return swtPossibleDivTheNextDay;
    }
    
    /**
     * (set�抷�\�敪�i�����������j)<BR>
     * �抷�\�敪�i�����������j��ݒ肷��B<BR>
     * @@param l_strSwtPossibleDivTheNextDay - �抷�\�敪�i�����������j
     * @@roseuid 40E9FFAB01B8
     */
    public void setSwtPossibleDivTheNextDay(String l_strSwtPossibleDivTheNextDay)
    {
        this.swtPossibleDivTheNextDay = l_strSwtPossibleDivTheNextDay;
    }
    
    /**
     * (get��W�J�n��)<BR>
     * this.��W�J�n����ԋp����B<BR>
     * @@return Date
     * @@roseuid 40E92ACE008B
     */
    public Date getApplyAbleStartDate()
    {
        return this.applyAbleStartDate;
    }

    /**
     * (set��W�J�n��)<BR>
     * ���t�J�n����ݒ肷��B<BR>
     * @@param l_datApplyAbleStartDate - ��W�J�n��
     * @@roseuid 40E92AD50146
     */
    public void setApplyAbleStartDate(Date l_datApplyAbleStartDate)
    {
        this.applyAbleStartDate = l_datApplyAbleStartDate;
    }
    
    /**
     * (get��W�I����)<BR>
     * this.��W�I������ԋp����B<BR>
     * @@return Date
     * @@roseuid 40E92ACE008B
     */
    public Date getApplyAbleEndDate()
    {
        return this.applyAbleEndDate;
    }

    /**
     * (set��W�I����)<BR>
     * ��W�I������ݒ肷��B<BR>
     * @@param l_datApplyAbleEndDate - ��W�I����
     * @@roseuid 40E92AD50146
     */
    public void setApplyAbleEndDate(Date l_datApplyAbleEndDate)
    {
        this.applyAbleEndDate = l_datApplyAbleEndDate;
    }
    
    /**
     * (get�w����@@�i��W�j)<BR>
     * this.�w����@@�i��W�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplySelectable()
    {
        return this.applySelectable;
    }

    /**
     * (set�w����@@�i��W�j)<BR>
     * �w����@@�i��W�j��ݒ肷��B<BR>
     * @@param l_strApplySelectable - �w����@@�i��W�j
     * @@roseuid 40E92AD50146
     */
    public void setApplySelectable(String l_strApplySelectable)
    {
        this.applySelectable = l_strApplySelectable;
    }
    
    /**
     * (get�Œ�����i��W�j)<BR>
     * this.�Œ�����i��W�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyMinQty()
    {
        return this.applyMinQty;
    }

    /**
     * (set�Œ�����i��W�j)<BR>
     * �Œ�����i��W�j��ݒ肷��B<BR>
     * @@param l_strApplyMinQty - �Œ�����i��W�j
     * @@roseuid 40E92AD50146
     */
    public void setApplyMinQty(String l_strApplyMinQty)
    {
        this.applyMinQty = l_strApplyMinQty;
    }
    
    /**
     * (get�P�ʌ����i��W�j)<BR>
     * this.�P�ʌ����i��W�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyUnitQty()
    {
        return this.applyUnitQty;
    }

    /**
     * (set�P�ʌ����i��W�j)<BR>
     * �P�ʌ����i��W�j��ݒ肷��B<BR>
     * @@param l_strApplyUnitQty - �P�ʌ����i��W�j
     * @@roseuid 40E92AD50146
     */
    public void setApplyUnitQty(String l_strApplyUnitQty)
    {
        this.applyUnitQty = l_strApplyUnitQty;
    }
    
    /**
     * (get�Œ���z�i��W�j)<BR>
     * this.�Œ���z�i��W�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyMinAmt()
    {
        return this.applyMinAmt;
    }

    /**
     * (set�Œ���z�i��W�j)<BR>
     * �Œ���z�i��W�j��ݒ肷��B<BR>
     * @@param l_strApplyMinAmt - �Œ���z�i��W�j
     * @@roseuid 40E92AD50146
     */
    public void setApplyMinAmt(String l_strApplyMinAmt)
    {
        this.applyMinAmt = l_strApplyMinAmt;
    }
    
    /**
     * (get�P�ʋ��z�i��W�j)<BR>
     * this.�P�ʋ��z�i��W�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyUnitAmt()
    {
        return this.applyUnitAmt;
    }

    /**
     * (set�P�ʋ��z�i��W�j)<BR>
     * �P�ʋ��z�i��W�j��ݒ肷��B<BR>
     * @@param l_strApplyUnitAmt - �P�ʋ��z�i��W�j
     * @@roseuid 40E92AD50146
     */
    public void setApplyUnitAmt(String l_strApplyUnitAmt)
    {
        this.applyUnitAmt = l_strApplyUnitAmt;
    }
    
    /**
     * (��W�\�敪�i�����������j)<BR>
     * this.��W�\�敪�i�����������j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyPossDivTheDay()
    {
        return this.applyPossDivTheDay;
    }

    /**
     * (set��W�\�敪�i�����������j)<BR>
     * ��W�\�敪�i�����������j��ݒ肷��B<BR>
     * @@param l_strApplyPossDivTheDay - ��W�\�敪�i�����������j
     * @@roseuid 40E92AD50146
     */
    public void setApplyPossDivTheDay(String l_strApplyPossDivTheDay)
    {
        this.applyPossDivTheDay = l_strApplyPossDivTheDay;
    }
    
    /**
     * (��W�\�敪�i�����������j)<BR>
     * this.��W�\�敪�i�����������j��ԋp����B<BR>
     * @@return String
     * @@roseuid 40E92ACE008B
     */
    public String getApplyPossDivTheNextDay()
    {
        return this.applyPossDivTheNextDay;
    }

    /**
     * (set��W�\�敪�i�����������j)<BR>
     * ��W�\�敪�i�����������j��ݒ肷��B<BR>
     * @@param l_strApplyPossDivTheNextDay - ��W�\�敪�i�����������j
     * @@roseuid 40E92AD50146
     */
    public void setApplyPossDivTheNextDay(String l_strApplyPossDivTheNextDay)
    {
        this.applyPossDivTheNextDay = l_strApplyPossDivTheNextDay;
    }

    /**
     * (get�O�ݍŒ���z�i�V�K���t�j)<BR>
     * this.�O�ݍŒ���z�i�V�K���t�j��ԋp����B<BR>
     * @@return String
     */
    public String getBuyFrgnMinAmtBuy()
    {
        return this.buyFrgnMinAmtBuy;
    }

    /**
     * (set�O�ݍŒ���z�i�V�K���t�j)<BR>
     * �O�ݍŒ���z�i�V�K���t�j��ݒ肷��B<BR>
     * @@param l_strBuyFrgnMinAmtBuy - �O�ݍŒ���z�i�V�K���t�j
     */
    public void setBuyFrgnMinAmtBuy(String l_strBuyFrgnMinAmtBuy)
    {
        this.buyFrgnMinAmtBuy = l_strBuyFrgnMinAmtBuy;
    }

    /**
     * (get�O�ݒP�ʋ��z�i�V�K���t�j)<BR>
     * this.�O�ݒP�ʋ��z�i�V�K���t�j��ԋp����B<BR>
     * @@return String
     */
    public String getBuyFrgnUnitAmtBuy()
    {
        return this.buyFrgnUnitAmtBuy;
    }

    /**
     * (set�O�ݒP�ʋ��z�i�V�K���t�j)<BR>
     * �O�ݒP�ʋ��z�i�V�K���t�j��ݒ肷��B<BR>
     * @@param l_strBuyFrgnUnitAmtBuy - �O�ݒP�ʋ��z�i�V�K���t�j
     */
    public void setBuyFrgnUnitAmtBuy(String l_strBuyFrgnUnitAmtBuy)
    {
        this.buyFrgnUnitAmtBuy = l_strBuyFrgnUnitAmtBuy;
    }

    /**
     * (get�O�ݍŒ���z�i�ǉ����t�j)<BR>
     * this.�O�ݍŒ���z�i�ǉ����t�j��ԋp����B<BR>
     * @@return String
     */
    public String getBuyFrgnMinAmtAdd()
    {
        return this.buyFrgnMinAmtAdd;
    }

    /**
     * (set�O�ݍŒ���z�i�ǉ����t�j)<BR>
     * �O�ݍŒ���z�i�ǉ����t�j��ݒ肷��B<BR>
     * @@param l_strBuyFrgnMinAmtAdd - �O�ݍŒ���z�i�ǉ����t�j
     */
    public void setBuyFrgnMinAmtAdd(String l_strBuyFrgnMinAmtAdd)
    {
        this.buyFrgnMinAmtAdd = l_strBuyFrgnMinAmtAdd;
    }

    /**
     * (get�O�ݒP�ʋ��z�i�ǉ����t�j)<BR>
     * this.�O�ݒP�ʋ��z�i�ǉ����t�j��ԋp����B<BR>
     * @@return String
     */
    public String getBuyFrgnUnitAmtAdd()
    {
        return this.buyFrgnUnitAmtAdd;
    }

    /**
     * (set�O�ݒP�ʋ��z�i�ǉ����t�j)<BR>
     * �O�ݒP�ʋ��z�i�ǉ����t�j��ݒ肷��B<BR>
     * @@param l_strBuyFrgnUnitAmtAdd - �O�ݒP�ʋ��z�i�ǉ����t�j
     */
    public void setBuyFrgnUnitAmtAdd(String l_strBuyFrgnUnitAmtAdd)
    {
        this.buyFrgnUnitAmtAdd = l_strBuyFrgnUnitAmtAdd;
    }

    /**
     * (get�O�ݍŒ���z�i���j)<BR>
     * this.�O�ݍŒ���z�i���j��ԋp����B<BR>
     * @@return String
     */
    public String getSellFrgnMinAmtSell()
    {
        return this.sellFrgnMinAmtSell;
    }

    /**
     * (set�O�ݍŒ���z�i���j)<BR>
     * �O�ݍŒ���z�i���j��ݒ肷��B<BR>
     * @@param l_strSellFrgnMinAmtSell - �O�ݍŒ���z�i���j
     */
    public void setSellFrgnMinAmtSell(String l_strSellFrgnMinAmtSell)
    {
        this.sellFrgnMinAmtSell = l_strSellFrgnMinAmtSell;
    }

    /**
     * (get�O�ݒP�ʋ��z�i���j)<BR>
     * this.�O�ݒP�ʋ��z�i���j��ԋp����B<BR>
     * @@return String
     */
    public String getSellFrgnUnitAmtSell()
    {
        return this.sellFrgnUnitAmtSell;
    }

    /**
     * (set�O�ݒP�ʋ��z�i���j)<BR>
     * �O�ݒP�ʋ��z�i���j��ݒ肷��B<BR>
     * @@param l_strSellFrgnUnitAmtSell - �O�ݒP�ʋ��z�i���j
     */
    public void setSellFrgnUnitAmtSell(String l_strSellFrgnUnitAmtSell)
    {
        this.sellFrgnUnitAmtSell = l_strSellFrgnUnitAmtSell;
    }
}
@
