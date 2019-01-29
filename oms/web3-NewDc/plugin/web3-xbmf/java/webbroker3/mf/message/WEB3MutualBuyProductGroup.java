head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyProductGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�����ꗗ�s�f�[�^�N���X(WEB3MutualBuyProductGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 ���E (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2006/05/15 ������ �d�l�ύX�E���f��411
Revesion History : 2006/09/11 ���� �d�l�ύX�E���f��491
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��535
Revesion History : 2007/04/09 ������ (���u) �d�l�ύX�E���f��562
Revesion History : 2008/04/21 ���u�� (���u) �d�l�ύX�E���f��593
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �����M�����t�����ꗗ�s�f�[�^�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyProductGroup extends Message
{
    /**
     * ���M����ID<BR>
     * <BR>
     * �i��ʂł͔�\���j<BR>
     */
    public String id;
    
    /**
     * �����R�[�h
     * �i��ʂł͔�\���j <BR>
     */
    public String mutualProductCode;
    
    /**
     * ������
     */
    public String mutualProductName;
    
    /**
     * ���M�����J�e�S���[�R�[�h
     */
    public String categoryCode;
    
    /**
     * ���t����z�ʉ݃R�[�h<BR>
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
     * ���t����z
     * ����W���Ԓ��̖����̏ꍇ�A��W���i <BR>
     */
    public String constantValue;
    
    /**
     * ����z�K�p��
     * ����W���Ԓ��̖����̏ꍇ�Anull <BR>
     */
    public Date constantValueAppDate;
    
    /**
     * �V�K���t���P�ʌ���
     * ����W���Ԓ��̖����̏ꍇ�A��W���P�ʌ��� <BR>
     */
    public String newBuyUnitQty;
    
    /**
     * �V�K���t���Œ����
     * ����W���Ԓ��̖����̏ꍇ�A��W���Œ���� <BR>
     */
    public String newBuyMinQty;
    
    /**
     * �V�K���t���P�ʋ��z
     * ����W���Ԓ��̖����̏ꍇ�A��W���P�ʋ��z <BR>
     */
    public String newBuyUnitAmt;
    
    /**
     * �V�K���t���Œ���z
     * ����W���Ԓ��̖����̏ꍇ�A��W���Œ���z <BR>
     */
    public String newBuyMinAmt;
    
    /**
     * �ǉ����t���P�ʌ���
     * ����W���Ԓ��̖����̏ꍇ�Anull <BR>
     */
    public String addBuyUnitQty;
    
    /**
     * �ǉ����t���Œ����
     * ����W���Ԓ��̖����̏ꍇ�Anull <BR>
     */
    public String addBuyMinQty;
    
    /**
     * �ǉ����t���P�ʋ��z
     * ����W���Ԓ��̖����̏ꍇ�Anull <BR>
     */
    public String addBuyUnitAmt;
    
    /**
     * �ǉ����t���Œ���z
     * ����W���Ԓ��̖����̏ꍇ�Anull <BR>
     */
    public String addBuyMinAmt;
    
    /**
     * ������t���؎���<BR>
     * <BR>
     * (HH:MM)<BR>
     */
    public String orderCloseTime;
    
    /**
     * ���l�敪<BR>
     * <BR>
     * null:����<BR>
     * (1:�S�����)<BR>
     * (2:�戵�s��)<BR>
     * 3:����s��(���t��~��)<BR>
     * 4:�ً}��~��<BR>
     * 5:������ԊO������~��(��t���ԊO)<BR>
     * 6:��W���Ԓ� <BR>
     * <BR>
     */
    public String noteType;
    
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
     * (��W�J�n��)<BR>
     * ��W�J�n��<BR> 
     * <BR>
     * �،���Ђɂ���ĕ�W�J�n���Ƃ��āA��W�J�n���܂���<BR>
     * ��W�J�n���iSONAR�j�̂����ꂩ��\������B<BR>
     */
    public Date applyAbleStartDate;
    
    /**
     * (��W�I����)<BR>
     * ��W�I����<BR>
     * <BR>
     * �،���Ђɂ���ĕ�W�I�����Ƃ��āA��W�I�����܂���<BR>
     * ��W�I�����iSONAR�j�̂����ꂩ��\������B<BR>
     */
    public Date applyAbleEndDate;
    
    /**
     * (��W�J�n���iSONAR�j)<BR>
     * ��W�J�n���iSONAR�j<BR>
     * <BR>
     * �،���Ђɂ���ĕ�W�J�n���Ƃ��āA��W�J�n���܂���<BR>
     * ��W�J�n���iSONAR�j�̂����ꂩ��\������B<BR>
     */
    public Date applyAbleStartDateSonar;
    
    /**
     * (��W�I�����iSONAR�j)<BR>
     * ��W�I�����iSONAR�j<BR>
     * <BR>
     * �،���Ђɂ���ĕ�W�I�����Ƃ��āA��W�I�����܂���<BR>
     * ��W�I�����iSONAR�j�̂����ꂩ��\������B<BR>
     */
    public Date applyAbleEndDateSonar;

    /**
     * (�Q�l���[�g�m���)<BR>
     * �Q�l���[�g�m���<BR>
     */
    public Date referenceRateFixedDay;

    /**
     * (�O��MMF�t���O)<BR>
     * �O��MMF�t���O <BR>
     * <BR>
     * true:�������O��MMF <BR>
     * false:�������O��MMF�łȂ�<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * (�V�K���t���O�ݒP�ʋ��z)<BR>
     * �V�K���t���O�ݒP�ʋ��z<BR>
     */
    public String newBuyFrgnUnitAmt;

    /**
     * (�V�K���t���O�ݍŒ���z)<BR>
     * �V�K���t���O�ݍŒ���z<BR>
     */
    public String newBuyFrgnMinAmt;

    /**
     * (�ǉ����t���O�ݒP�ʋ��z)<BR>
     * �ǉ����t���O�ݒP�ʋ��z<BR>
     */
    public String addBuyFrgnUnitAmt;

    /**
     * (�ǉ����t���O�ݍŒ���z)<BR>
     * �ǉ����t���O�ݍŒ���z<BR>
     */
    public String addBuyFrgnMinAmt;

    /**
     * (�w����@@)<BR>
     * �w����@@<BR>
     * <BR>
     * 0�F�I���w��<BR>
     * 3�F���z�w��<BR>
     * 4�F�����w��<BR>
     */
    public String buySelectable;

    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A87B000027
     */
    public WEB3MutualBuyProductGroup()
    {
    }
}
@
