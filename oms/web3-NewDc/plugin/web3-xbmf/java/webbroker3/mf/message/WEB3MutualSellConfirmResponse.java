head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����m�F���X�|���X�N���X(WEB3MutualSellConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 ���� (���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[
                   2004/12/07 ������ (���u) �c�Ή� 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �����M�����m�F���X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3MutualSellConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408111511L;
    
    /**
     * ��񒍕����e�x���敪<BR>
     * <BR>
     * null:�G���[����<BR>
     * 1:�x���u�����z�S�����̒��߂ɂ��"�S���w��"�ύX�v<BR>
     */
    public String sellWarningType;
    
    /**
     * �T�Z��n����ʉ݃R�[�h<BR>
     * <BR>
     * �u�����N:�~�@@�u�����N�~�Q:�~<BR>
     * A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$<BR>
     * A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr<BR>
     * F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS<BR>
     * M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     * T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR<BR>
     */
    public String estimatedPriceCurrencyCode;
    
    /**
     * �T�Z��n���
     */
    public String estimatedPrice;
    
    /**
     * �T�Z��������
     */
    public String estimatedQty;
    
    /**
     * ����ID<BR>
     */
    public String orderId;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A89E130220
     */
    public WEB3MutualSellConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualSellConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
    
}
@
