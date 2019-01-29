head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���抷�m�F���X�|���X�N���X(WEB3MutualSwitchingConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/25 ���� (���u) ���r���[
                   2004/12/07 ������ (���u) �c�Ή�
                   2005/10/18 ��O�� (���u) �t�B�f���e�B�Ή�
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����M���抷�m�F���X�|���X�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualSwitchingConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_switching_confirm";
    
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
    protected WEB3MutualSwitchingConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 

    /**
     * ��񒍕����e�x���敪<BR>
     * <BR>
     * null:�G���[����<BR>
     * 1:�x���u�����z�S�����̒��߂ɂ��"�S���w��"�ύX�v<BR>
     */
    public String sellWarningType;    
    
    /**
     * �T�Z���t�����i�抷��j<BR>
     */
    public String switchingEstimatedQty;
    
    /**
     * �T�Z��n��� <BR>
     */
    public String estimatedPrice;
    
    /**
     * ����<BR>
     */
    public String mutualOrderQuantity;
    
    /**
     * ����ID<BR>
     */
    public String orderId;

    /**
     * (���M�抷�m�F���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8AABD0130
     */
    public WEB3MutualSwitchingConfirmResponse() 
    {
     
    }
}
@
