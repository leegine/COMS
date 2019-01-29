head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�������̓��X�|���X�N���X(WEB3MutualBuyInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 ���E (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2006/05/15 ������ (���u) �d�l�ύX�E���f��411
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��535
Revesion History : 2007/04/09 ������ (���u) �d�l�ύX�E���f��562
*/
package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;

/**
 * �����M�����t�������̓��X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_input";
    
    /**
     * ���t�\�z
     */
    public String tradingPower;
    
    /**
     * ���M�����R�[�h<BR>
     * <BR>
     * �i��ʂł͔�\���j<BR>
     */
    public String mutualProductCode;
    
    /**
     * ������
     */
    public String mutualProductName;
    
    /**
     * ���t����z�ʉ݃R�[�h<BR>
     * <BR>
     * �u�����N:�~�@@�u�����N�~�Q:�~<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     */
    public String constantValueCurrencyCode;
    
    /**
     * ���t����z
     */
    public String constantValue;
    
    /**
     * ����z�K�p��
     */
    public Date constantValueAppDate;
    
    /**
     * �����敪�ꗗ<BR>
     * <BR>
     * 0:��ʁ@@1:����  2:���̑�<BR>
     */
    public String[ ] taxTypeList;
    
    /**
     * �w����@@�ꗗ<BR>
     * <BR>
     * 3:���z�@@4:����<BR>
     */
    public String[ ] specifyDivList;
    
    /**
     * ���t��(�V�K�^�ǉ�)�P�ʌ���
     */
    public String buyUnitQty;
    
    /**
     * ���t��(�V�K�^�ǉ�)�Œ����
     */
    public String buyMinQty;
    
    /**
     * ���t��(�V�K�^�ǉ�)�P�ʋ��z
     */
    public String buyUnitAmt;
    
    /**
     * ���t��(�V�K�^�ǉ�)�Œ���z
     */
    public String buyMinAmt;
    
    /**
     * ���ϕ��@@�ꗗ<BR>
     * <BR>
     * 1:�~�݁@@2:�O��<BR>
     */
    public String[ ] settleDivList;
    
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
     * (�ژ_�����{���`�F�b�N����)<BR>
     * �ژ_�����{���`�F�b�N����
     */
    public WEB3GentradeProspectusResult prospectusResult;
 
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
     * �בփ��[�g�e�[�u��.�בփ��[�g�m���<BR> 
     */
    public Date referenceRateFixedDay;
 
    /**
     * (���t���O�ݒP�ʋ��z)<BR>
     * ���t��(�V�K�^�ǉ�)�O�ݒP�ʋ��z<BR>
     */
    public String buyFrgnUnitAmt;

    /**
     * (���t���O�ݍŒ���z)<BR>
     * ���t��(�V�K�^�ǉ�)�O�ݍŒ���z<BR>
     */
    public String buyFrgnMinAmt;

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualBuyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
    /**
     * (���M���t�������̓��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A87DAF00C3
     */
    public WEB3MutualBuyInputResponse() 
    {
     
    }
}
@
