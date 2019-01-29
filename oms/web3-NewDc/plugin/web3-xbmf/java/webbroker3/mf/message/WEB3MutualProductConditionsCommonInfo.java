head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductConditionsCommonInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M���������o�^���ʏ��(WEB3MutualProductConditionsCommonInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 ���� (���u) �V�K�쐬 
Revesion History : 2006/05/18 ���� (���u) �d�l�ύX�E���f��414
Revesion History : 2006/10/19 ���� (���u) �d�l�ύX�E���f��499�A505
Revesion History : 2007/04/09 �����F (���u) ���f��548
Revesion History : 2007/10/15 ���^�] (���u) �d�l�ύX�E���f��579
*/

package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (���M���������o�^���ʏ��)<BR>
 * ���M���������o�^���ʏ��f�[�^�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualProductConditionsCommonInfo extends Message 
{
    /**
     * (���M�����R�[�h)<BR>
     *  ���M�����R�[�h
     */
    public String mutualProductCode;
    
    /**
     * (WEBBROKER3�戵��)<BR>
     * <BR>
     * 0:WEBBROKER3�Ŏ�舵��Ȃ��@@<BR>
     * 1:WEBBROKER3�Ŏ�舵�� <BR>
     * 2:�X�������̂� <BR>
     * (null�̏ꍇ�A�ύX�����Ƃ���)<BR>
     */
    public String web3TreatmentFlag;
    
    /**
     * (�������i�a���j)<BR>
     *  �������i�a���j
     */
    public String jpnProductName;
    
    /**
     * (�������i�p���j)<BR>
     *  �������i�p���j
     */
    public String engProductName;
    
    /**
     * (���M�����J�e�S���[�R�[�h)<BR>
     *  ���M�����J�e�S���[�R�[�h
     */
    public String categoryCode;
    
    /**
     * (���t�J�n��)<BR>
     *  ���t�J�n��
     */
    public Date buyStartDate;
    
    /**
     * (���t�I����)<BR>
     *  ���t�I����
     */
    public Date buyEndDate;
    
    /**
     * (���抷�J�n��)<BR>
     *  ���抷�J�n��
     */
    public Date sellSwitchingStartDate;
    
    /**
     * (���抷�I����)<BR>
     *  ���抷�I����
     */
    public Date sellSwitchingEndDate;
    
    /**
     * (���搿���J�n��)<BR>
     *  ���搿���J�n��
     */
    public Date buyClaimStartDate;
    
    /**
     * (���搿���I����)<BR>
     *  ���搿���I����
     */
    public Date buyClaimEndDate;
    
    /**
     * (��W�J�n��)<BR>
     *  ��W�J�n��
     */
    public Date applyAbleStartDate;
    
    /**
     * (��W�I����)<BR>
     *  ��W�I����
     */
    public Date applyAbleEndDate;
    
    /**
     * (�w����@@�i���t�j)<BR>
     *  �w����@@�i���t�j
     */
    public String buySelectable;
    
    /**
     * (�Œ�����i�V�K���t�j)<BR>
     *  �Œ�����i�V�K���t�j
     */
    public String newBuyMinQty;
    
    /**
     * (�P�ʌ����i�V�K���t�j)<BR>
     *  �P�ʌ����i�V�K���t�j
     */
    public String newBuyUnitQty;
    
    /**
     * (�Œ���z�i�V�K���t�j)<BR>
     *  �Œ���z�i�V�K���t�j
     */
    public String newBuyMinAmt;
    
    /**
     * (�P�ʋ��z�i�V�K���t�j)<BR>
     *  �P�ʋ��z�i�V�K���t�j
     */
    public String newBuyUnitAmt;
    
    /**
     * (�Œ�����i�ǉ����t�j)<BR>
     *  �Œ�����i�ǉ����t�j
     */
    public String addBuyMinQty;
    
    /**
     * (�P�ʌ����i�ǉ����t�j)<BR>
     *  �P�ʌ����i�ǉ����t�j
     */
    public String addBuyUnitQty;
    
    /**
     * (�Œ���z�i�ǉ����t�j)<BR>
     *  �Œ���z�i�ǉ����t�j
     */
    public String addBuyMinAmt;
    
    /**
     * (�P�ʋ��z�i�ǉ����t�j)<BR>
     *  �P�ʋ��z�i�ǉ����t�j
     */
    public String addBuyUnitAmt;
    
    /**
     * (�w����@@�i���j)<BR>
     *  �w����@@�i���j
     */
    public String sellSelectable;
    
    /**
     * (�Œ�����i���j)<BR>
     *  �Œ�����i���j
     */
    public String sellMinQty;
    
    /**
     * (�P�ʌ����i���j)<BR>
     *  �P�ʌ����i���j
     */
    public String sellUnitQty;
    
    /**
     * (�Œ���z�i���j)<BR>
     *  �Œ���z�i���j
     */
    public String sellMinAmt;
    
    /**
     * (�P�ʋ��z�i���j)<BR>
     *  �P�ʋ��z�i���j
     */
    public String sellUnitAmt;
    
    /**
     * (�w����@@�i�抷�j)<BR>
     *  �w����@@�i�抷�j
     */
    public String switchingSelectable;
    
    /**
     * (�Œ�����i�抷�j)<BR>
     *  �Œ�����i�抷�j
     */
    public String switchingMinQty;
    
    /**
     * (�P�ʌ����i�抷�j)<BR>
     *  �P�ʌ����i�抷�j
     */
    public String switchingUnitQty;
    
    /**
     * (�Œ���z�i�抷�j)<BR>
     *  �Œ���z�i�抷�j
     */
    public String switchingMinAmt;
    
    /**
     * (�P�ʋ��z�i�抷�j)<BR>
     *  �P�ʋ��z�i�抷�j
     */
    public String switchingUnitAmt;
    
    /**
     * (�w����@@�i��W�j)<BR>
     *  �w����@@�i��W�j
     */
    public String applySelectable;
    
    /**
     * (�Œ�����i��W�j)<BR>
     *  �Œ�����i��W�j
     */
    public String applyMinQty;
    
    /**
     * (�P�ʌ����i��W�j)<BR>
     *  �P�ʌ����i��W�j
     */
    public String applyUnitQty;
    
    /**
     * (�Œ���z�i��W�j)<BR>
     *  �Œ���z�i��W�j
     */
    public String applyMinAmt;
    
    /**
     * (�P�ʋ��z�i��W�j)<BR>
     *  �P�ʋ��z�i��W�j
     */
    public String applyUnitAmt;
    
    /**
     * (���t�\�敪�i�����������j)<BR>
     * 0�F�s�@@1�F��
     */
    public String todayBuyPossDiv;
    
    /**
     * (���\�敪�i�����������j)<BR>
     * 0�F�s�@@1�F��
     */
    public String todaySellPossDiv;
    
    /**
     * (�抷�\�敪�i�����������j)<BR>
     * 0�F�s�@@1�F��
     */
    public String todaySwitchingPossDiv;
    
    /**
     * (��W�\�敪�i�����������j)<BR>
     * 0�F�s�@@1�F��
     */
    public String todayApplyPossDiv;
    
    /**
     * (���t�\�敪�i�����������j)<BR>
     * 0�F�s�@@1�F��
     */
    public String nextDayBuyPossDiv;
    
    /**
     * (���\�敪�i�����������j)<BR>
     * 0�F�s�@@1�F��
     */
    public String nextDaySellPossDiv;
    
    /**
     * (�抷�\�敪�i�����������j)<BR>
     * 0�F�s�@@1�F��
     */
    public String nextDaySwitchingPossDiv;
    
    /**
     * (��W�\�敪�i�����������j)<BR>
     * 0�F�s�@@1�F��
     */
    public String nextDayApplyPossDiv;
    
    /**
     * (��t���؊J�n���ԁi�����j)<BR>
     * �����Fhhmm
     */
    public String orderCloseStartTimeFull;
    
    /**
     * (��t���؏I�����ԁi�����j)<BR>
     * �����Fhhmm
     */
    public String orderCloseEndTimeFull;
    
    /**
     * (��t���؊J�n���ԁi�����j)<BR>
     * �����Fhhmm
     */
    public String orderCloseStartTimeHalf;
    
    /**
     * (��t���؏I�����ԁi�����j)<BR>
     * �����Fhhmm
     */
    public String orderCloseEndTimeHalf;
    
    /**
     * (���t�����敪)<BR>
     * 0�F���t�@@1�F�抷���t�̂݉�
     */
    public String buyRestrictionDiv;
    
    /**
     * (�I�y���[�V�������t)<BR>
     * ���͉�ʎ擾���̌��ݓ��t
     */
    public Date operationDate;
    
    /**
     * (��n���@@)<BR>
     * 0�F�I���w�� <BR>
     * 1�F��s�U�� <BR>
     * 2�F�،���������
     */
    public String deliveryVariation;
    
    /**
     * (�������������敪)<BR>
     * �������������敪 <BR>
     * <BR>
     * 0 �F�ʏ����<BR>�@@ 
     * 1 �F���t�̂� <BR>
     * 2 �F���̂� <BR>
     * 3 �F���� 
     */
    public String unitTypeProductDiv;

    /**
     * (�O�ݍŒ���z�i�V�K���t�j)<BR>
     * �O�ݍŒ���z�i�V�K���t�j<BR>
     */
    public String frgnMinAmtBuy;

    /**
     * (�O�ݒP�ʋ��z�i�V�K���t�j)<BR>
     * �O�ݒP�ʋ��z�i�V�K���t�j<BR>
     */
    public String frgnUnitAmtBuy;

    /**
     * (�O�ݍŒ���z�i�ǉ����t�j)<BR>
     * �O�ݍŒ���z�i�ǉ����t�j<BR>
     */
    public String frgnMinAmtAdd;

    /**
     * (�O�ݒP�ʋ��z�i�ǉ����t�j)<BR>
     * �O�ݒP�ʋ��z�i�ǉ����t�j<BR>
     */
    public String frgnUnitAmtAdd;

    /**
     * (�O�ݍŒ���z�i���j)<BR>
     * �O�ݍŒ���z�i���j<BR>
     */
    public String frgnMinAmtSell;

    /**
     * (�O�ݒP�ʋ��z�i���j)<BR>
     * �O�ݒP�ʋ��z�i���j<BR>
     */
    public String frgnUnitAmtSell;

    /**
     * (��W�萔���敪)<BR>
     * ��W�萔���敪<BR>
     * <BR>
     * "0�F�Ȃ�"<BR>
     * "1�F���g"<BR>
     * "2�F�O�g"<BR>
     */
    public String applyCommissionDiv;

    /**
     * (���M���������o�^���ʏ��)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 417747E00061
     */
    public WEB3MutualProductConditionsCommonInfo()
    {
    }
}
@
