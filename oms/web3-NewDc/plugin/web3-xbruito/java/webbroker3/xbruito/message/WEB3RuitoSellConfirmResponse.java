head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ����m�F���X�|���X�N���X(WEB3RuitoSellConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
                   2004/12/03 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �ݓ����m�F���X�|���X<BR>
 */
public class WEB3RuitoSellConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;  
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3RuitoSellConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

    /**
     * �m�F��������<BR>
     * <BR>
     * �������N�G�X�g�ő��M����l���i�[����B<BR>
     * �i��ʂł͔�\���j<BR>
     */
    public Date checkDate;

    /**
     * ����ID<BR>
     */
    public String orderId;

    
    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922BFB0157
     */
    public WEB3RuitoSellConfirmResponse()
    {

    }
}
@
