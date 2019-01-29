head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������Ɖ���P�ʃf�[�^�N���X(WEB3MutualOrderGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12  ���� (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��535
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �����M�������Ɖ���P�ʃf�[�^�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualOrderGroup extends Message
{   
    /**
     * ���M����ID<BR>
     * <BR>
     * (��ʂł͔�\��)<BR>
     */    
    public String id;
    
    /**
     * ������
     */    
    public String mutualProductName;
    
    /**
     * �����敪<BR>
     * <BR>
     * 0:��ʁ@@1:����@@2�F���̑�<BR>
     */    
    public String taxType;
    
    /**
     * �����敪(���M)<BR>
     * <BR>
     * 1:���t�@@2:���@@3:�抷  5:��W <BR>
     */    
    public String mutualDealingType;
    
    /**
     * �������@@<BR>
     * <BR>
     * 0:��񐿋��@@1:���搿��<BR>
     * <BR>
     * (���t�A��W�����̏ꍇ�Anull)<BR>
     */    
    public String sellBuyDiv;
    
    /**
     * �w����@@<BR>
     * <BR>
     * 2:�S���@@3:���z�@@4:����<BR>
     */    
    public String specifyDiv;
    
    /**
     * ���ϕ��@@<BR>
     * <BR>
     * 1:�~�݁@@2:�O��<BR>
     */    
    public String settleDiv;
    
    /**
     * �������ʋ敪<BR>
     * <BR>
     * 0:����<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     */    
    public String mutualOrderQuantityType;
    
    /**
     * ��������
     */    
    public String mutualOrderQuantity;
    
    /**
     * �Q�l����z�ʉ݃R�[�h<BR>
     * <BR>
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
     * �i��W�����̏ꍇ�Anull�j<BR>
     */    
    public Date constantValueAppDate;
    
    /**
     * �T�Z��������<BR>
     * <BR>
     */    
    public String estimatedQty;    
    
    /**
     * �T�Z��n����ʉ݃R�[�h<BR>
     * <BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     * <BR>
     */    
    public String estimatedPriceCurrencyCode;
    
    /**
     * �T�Z��n���<BR>
     * <BR>
     */    
    public String estimatedPrice;
    
    /**
     * ��n���@@<BR>
     * <BR>
     * 1:��s�U���݁@@2:�،���������<BR>
     */    
    public String deliveryDiv;
    
    /**
     * ��������
     */    
    public Date orderDate;
    
    /**
     * ������
     */    
    public Date orderBizDate;
    
    /**
     * ����
     */    
    public Date executionTimestamp;
    
    /**
     * ��n��
     */    
    public Date deliveryDate;
    
    /**
     * ������ԋ敪<BR>
     * <BR>
     * 1:�������@@6:������t�G���[�@@12:���������<BR>
     * 13:��������ρ@@15:����������s<BR>
     * 52:��蒆<BR>
     * 53:����<BR>
     * 54:����(��s�U��)<BR>
     * 55:�抷��<BR>
     * <BR>
     */    
    public String orderState;
    
    /**
     * ����\�t���O<BR>
     * <BR>
     * true:����\�@@false:����s��<BR>
     */    
    public boolean cancelFlag;
    
    /**
     * �������i�抷��j<BR>
     * <BR>
     * (������"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */    
    public String switchingProductName;
    
    /**
     * �v�Z����z�ʉ݃R�[�h�i�抷��j<BR>
     * <BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     * <BR>
     * (������"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */    
    public String switchingConstantValueCurrencyCode;
    
    /**
     * �v�Z����z�i�抷��j<BR>
     * <BR>
     * (������"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */    
    public String switchingConstantValue;
    
    /**
     * �����敪�i�抷��j<BR>
     * <BR>
     * 0:��ʁ@@1:����<BR>
     * <BR>
     * (������"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */    
    public String switchingTaxType;
    
    /**
     * �T�Z���t�����i�抷��j<BR>
     * <BR>
     * (������"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���)<BR>
     */    
    public String switchingEstimatedQty;
    
    /**
     * ���򒥎��S����(�抷��)<BR>
     * <BR>
     * (������"�抷"�ȊO�̏ꍇ�Anull���ݒ肳���) <BR>
     */    
    public String switchingWHRestraintPrice;
    
    /**
     * �����`���l��<BR>
     * <BR>
     * 0�F�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�@@3�F���o�C��<BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */        
    public String orderChannel;
    
    /**
     * (�����o�H�敪)<BR>
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g<BR>
     * 4�Fi-mode�@@5�FVodafone�@@6�FAU�@@9�FHOST<BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */    
    public String orderRootDiv;
    
    /**
     * (�I�y���[�^�R�[�h)<BR>
     * �戵�҃R�[�h<BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */    
    public String operatorCode;

    /**
     * (�O��MMF�t���O)<BR>
     * �O��MMF�t���O <BR>
     * <BR>
     * true:�������O��MMF <BR>
     * false:�������O��MMF�łȂ�<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A9A68F02E9
     */    
    public WEB3MutualOrderGroup()    
    {
    }
}
@
