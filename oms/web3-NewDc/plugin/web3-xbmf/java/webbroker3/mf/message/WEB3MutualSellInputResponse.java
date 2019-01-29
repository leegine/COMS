head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������̓��X�|���X�N���X(WEB3MutualSellInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���� (���u) �V�K�쐬
Revesion History : 2006/05/15 ������ (���u) �d�l�ύX�E���f��411
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��536
Revesion History : 2007/04/09 ������ (���u) �d�l�ύX�E���f��562
*/
package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����M�������̓��X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3MutualSellInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408120950L;
    
    /**
     * ���\����
     */
    public String sellAbleQty;
    
    /**
     * �]���z
     */
    public String marketValue;
    
    /**
     * ���M�����R�[�h<BR>
     * <BR>
     * (��ʂł͔�\��)<BR>
     */
    public String mutualProductCode;
    
    /**
     * ������
     */
    public String mutualProductName;
    
    /**
     * �����敪<BR>
     * <BR>
     * 0:��ʁ@@1:����@@2:���̑�<BR>
     */
    public String taxType;
    
    /**
     * ��񉿊z�ʉ݃R�[�h<BR>
     * <BR>
     * �u�����N:�~�@@�u�����N�~�Q:�~<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     * <BR>
     */
    public String sellValueCurrencyCode;
    
    /**
     * ��񉿊z
     */
    public String sellValue;
    
    /**
     * ����z�K�p��
     */
    public Date constantValueAppDate;
    
    /**
     * �������@@�ꗗ<BR>
     * <BR>
     * 0:��񐿋��@@1:���搿��<BR>
     */
    public String[ ] sellBuyDivList;
    
    /**
     * �w����@@�ꗗ<BR>
     * <BR>
     * 2:�S���@@3:���z�w��@@4:�����w��<BR>
     */
    public String[ ] specifyDivList;
    
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
     * ���ϕ��@@�ꗗ<BR>
     * <BR>
     * 1:�~�݁@@2:�O��<BR>
     */
    public String[ ] settleDivList;
    
    /**
     * ��n���@@�ꗗ<BR>
     * <BR>
     * 1:��s�U���݁@@2:�،���������<BR>
     */
    public String[ ] deliveryDivList;
    
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
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8907B0140
     */
    public WEB3MutualSellInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualSellInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
