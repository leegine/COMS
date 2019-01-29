head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���抷���̓��X�|���X�N���X(WEB3MutualSwitchingInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/25 ���� (���u) ���r���[
                   2004/12/07 ������ (���u) �c�Ή�
                   2005/10/18 ��O�� (���u) �t�B�f���e�B�Ή�
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;


/**
 * �����M���抷���̓��X�|���X�N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualSwitchingInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_switching_input";
    
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
    protected WEB3MutualSwitchingInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 

    /**
     * �抷�\����<BR>
     */
    public String switchingAbleQty;
    
    /**
     * �]���z<BR>
     */
    public String marketValue;
    
    /**
     * ���M�����R�[�h<BR>
     * <BR>
     * (��ʂł͔�\��)<BR>
     */
    public String mutualProductCode;
    
    /**
     * ������<BR>
     */
    public String mutualProductName;
    
    /**
     * ��񉿊z�ʉ݃R�[�h<BR>
     * <BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR
     */
    public String sellValueCurrencyCode;
    
    /**
     * ��񉿊z<BR>
     */
    public String sellValue;
    
    /**
     * ����z�K�p��<BR>
     */
    public Date constantValueAppDate;
    
    /**
     * �������敪<BR>
     * <BR>
     * 0:��ʁ@@1:����<BR>
     */
    public String taxType;
    
    /**
     * �w����@@�ꗗ<BR>
     * <BR>
     * 2:�S���@@3:���z�w��@@4:�����w��<BR>
     */
    public String[ ] specifyDivList;
    
    /**
     * �������@@�ꗗ<BR>
     * <BR>
     * 0:��񐿋��@@1:���搿��<BR>
     */
    public String[ ] sellBuyDivList;
    
    /**
     * �抷���P�ʌ���<BR>
     */
    public String switchingUnitQty;
    
    /**
     * �抷���Œ����<BR>
     */
    public String switchingMinQty;
    
    /**
     * �抷���P�ʋ��z<BR>
     */
    public String switchingUnitAmt;
    
    /**
     * �抷���Œ���z<BR>
     */
    public String switchingMinAmt;
    
    /**
     * ������<BR>
     */
    public Date orderBizDate;
    
    /**
     * ����<BR>
     */
    public Date executionTimestamp;
    
    /**
     * ��n��<BR>
     */
    public Date deliveryDate;
    
    /**
     * �����R�[�h�i�抷��j<BR>
     *  <BR>
     * (��ʂł͔�\��) <BR>
     */
    public String switchingProductCode;
    
    /**
     * �������i�抷��j<BR>
     */
    public String switchingProductName;
    
    /**
     * ���t����z�ʉ݃R�[�h <BR>
     * <BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$ <BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr <BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS <BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~ <BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR <BR>
     */
    public String buyConstantValueCurrencyCode;
    
    /**
     * ���t����z <BR>
     */
    public String buyConstantValue;
    
    /**
     * ���t����z�K�p�� <BR>
     */
    public Date buyConstantValueAppDate;
    
    /**
     * ���t�����敪�ꗗ <BR>
     * 0:��ʁ@@1:���� <BR>
     */
    public String[] taxTypeList;
    
    /**
     * �ژ_�����{���`�F�b�N���� <BR>
     */
    public WEB3GentradeProspectusResult prospectusResult;
    
    /**
     * (���M�抷���̓��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40A8A31F0072
     */
    public WEB3MutualSwitchingInputResponse() 
    {
     
    }
}
@
