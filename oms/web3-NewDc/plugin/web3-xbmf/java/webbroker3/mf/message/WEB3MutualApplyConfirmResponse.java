head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M��W�����m�F���X�|���X�N���X(WEB3MutualApplyConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 ���� (���u) �V�K�쐬
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * ���M��W�����m�F���X�|���X�N���X <BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3MutualApplyConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_apply_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261532L;
    
    /**
     * (�T�Z��n����ʉ݃R�[�h)<BR>  
     *  �T�Z��n����ʉ݃R�[�h <BR>  
     *  A0:US$�@@A1:C$�@@A2:A$�@@A3:HK$�@@A4:S$ <BR>
     *  A5:NZ$�@@D0:���@@D1:Irish ���@@F0:Fr <BR>
     *  F1:SFr�@@I0:DM�@@J0:G�@@K0:Lit�@@L0:AS <BR>
     *  M0:DKr�@@M1:NKr�@@M2:SKr�@@N0:Pts�@@T0:�~<BR>
     *  T3:FIM�@@U1:Bath�@@Z3:ECU�@@Z4:EUR <BR> 
     */
    public String estimatedPriceCurrencyCode;
    
    /**
     * (�T�Z��n���)<BR>
     *  �T�Z��n���<BR>
     */
    public String estimatedPrice;
    
    /**
     * (�T�Z��������)<BR>
     *  �T�Z��������<BR>
     */
    public String estimatedQty;
    
    /**
     * (����ID)<BR>
     *  ����ID<BR>
     */
    public String orderId;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualApplyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
