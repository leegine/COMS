head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellSwitchingProductGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����抷�����ꗗ�s�f�[�^�N���X(WEB3MutualSellSwitchingProductGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 ���� (���u) �V�K�쐬
Revesion History : 2004/08/25 ���E (���u) ���r���[  
Revesion History : 2004/12/11 ������ (���u) �c�Ή�
Revesion History : 2005/10/20 ��O�� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/05/15 ������ (���u) �d�l�ύX�E���f��411
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��536
Revesion History : 2007/04/09 ������ (���u) �d�l�ύX�E���f��562
Revesion History : 2008/04/21 ���u�� (���u) �d�l�ύX�E���f��593
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �����M�����抷�����ꗗ�s�f�[�^�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3MutualSellSwitchingProductGroup extends Message 
{
   
    /**
     * ���M���YID<BR>
     * <BR>
     * �i��ʂł͔�\���j<BR>
     */
    public String id;
    
    /**
     * ������
     * �i��ʂł͔�\���j
     */
    public String mutualProductName;
    
	/**
	 * ����ID
	 */
	public String mutualProductId;
    
    /**
     * �����R�[�h
     */
    public String mutualProductCode;
    
    /**
     * �����敪<BR>
     * <BR>
     * 0:��ʁ@@1:����@@2:���̑�<BR>
     */
    public String taxType;
    
    /**
     * ���\����
     */
    public String sellableQty;
    
    /**
     * ��񒆊T�Z����
     */
    public String sellingEstimatedQty;
    
    /**
     * �Q�l����z�ʉ݃R�[�h<BR>
     * <BR>
     * �u�����N:�~�@@�u�����N�~�Q:�~<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     * <BR>
     */
    public String constantValueCurrencyCode;
    
    /**
     * �Q�l����z
     */
    public String constantValue;
    
    /**
     * ����z�K�p��
     */
    public Date constantValueAppDate;
    
    /**
     * �ʌ��{<BR>
     * (�擾���z)<BR>
     */
    public String indivPrincipal;
    
    /**
     * �]���z
     */
    public String marketValue;
    
    /**
     * �]�����v
     */
    public String appraisalProfitLoss;
    
    /**
     * ��񎞒P�ʌ���
     */
    public String sellUnitQty;
    
    /**
     * ��񎞍Œ����
     */
    public String sellMinQty;
    
    /**
     * ��񎞒P�ʋ��z
     */
    public String sellUnitAmt;
    
    /**
     * ��񎞍Œ���z
     */
    public String sellMinAmt;
    
    /**
     * �抷���P�ʌ���
     */
    public String switchingUnitQty;
    
    /**
     * �抷���Œ����
     */
    public String switchingMinQty;
    
    /**
     * �抷���P�ʋ��z
     */
    public String switchingUnitAmt;
    
    /**
     * �抷���Œ���z
     */
    public String switchingMinAmt;
    
    /**
     * ������t���؎���<BR>
     * <BR>
     * (MM:HH)<BR>
     */
    public String orderCloseTime;
    
    /**
     * (���\�敪)<BR>
     * ���\�t���O<BR>
     * <BR>
     * null:����\<BR>
     * 1:�S�����<BR>
     * 2:�戵�s��<BR>
     * 3:����s��<BR>
     * 4:�ً}��~��<BR>
     * 5:������ԊO������~��<BR>
     */
    public String sellPossType;
    
    /**
     * (����\�敪)<BR>
     * ����\�t���O<BR>
     * <BR>
     * null:����\<BR>
     * 1:�S�����<BR>
     * 2:�戵�s��<BR>
     * 3:����s��<BR>
     * 4:�ً}��~��<BR>
     * 5:������ԊO������~��<BR>
     */
    public String buyPossType;
    
    /**
     * (�抷�\�敪)<BR>
     * �抷�\�t���O<BR>
     * <BR>
     * null:����\<BR>
     * 1:�S�����<BR>
     * 2:�戵�s��<BR>
     * 3:����s��<BR>
     * 4:�ً}��~��<BR>
     * 5:������ԊO������~��<BR>
     */
    public String switchingPossType;
    
    /**
     * (�~�]����z)<BR>
     * �~�]����z<BR>
     * <BR>
     * ���t����z���~�]��������<BR>
     */
    public String yenConstantValue;
    
    /**
     * (�Q�l���[�g)<BR>
     * �Q�l���[�g<BR>
     * <BR>
     * �בփ��[�g�e�[�u��.TTS<BR>
     * �~�݂̏ꍇnull<BR>
     */
    public String referenceRate;

    /**
     * (�Q�l���[�g�m���)<BR>
     * �Q�l���[�g�m���<BR>
     * <BR>
     * �~�݂̏ꍇnull<BR>
     */
    public Date referenceRateFixedDay;

    /**
     * (�������z��)<BR>
     * �������z��
     */
    public String unreceivedDistribution;

    /**
     * (�O��MMF�t���O)<BR>
     * �O��MMF�t���O<BR>
     * <BR>
     * true:�������O��MMF <BR>
     * false:�������O��MMF�łȂ�<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * (��񎞊O�ݒP�ʋ��z)<BR>
     * ��񎞊O�ݒP�ʋ��z<BR>
     */
    public String sellFrgnUnitAmt;

    /**
     * (��񎞊O�ݍŒ���z)<BR>
     * ��񎞊O�ݍŒ���z<BR>
     */
    public String sellFrgnMinAmt;

    /**
     * (�w����@@�i���j)<BR>
     * �w����@@�i���j<BR>
     * <BR>
     * 0�F�I���w��<BR>
     * 3�F���z�w��<BR>
     * 4�F�����w��<BR>
     */
    public String sellSelectable;

    /**
     * (�w����@@�i�抷�j)<BR>
     * �w����@@�i�抷�j<BR>
     * <BR>
     * 0�F�I���w��<BR>
     * 3�F���z�w��<BR>
     * 4�F�����w��<BR>
     */
    public String switchingSelectable;

    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A88AA2019E
     */
    public WEB3MutualSellSwitchingProductGroup() 
    {
     
    }
}
@
