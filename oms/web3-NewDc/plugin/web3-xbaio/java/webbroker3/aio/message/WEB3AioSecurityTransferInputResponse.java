head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֓��̓��X�|���X(WEB3AioSecurityTransferInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�،��U�֓��̓��X�|���X)<BR>
 * �،��U�֓��̓��X�|���X�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412070946L;
    
    /**
     * (������)<BR>
     * ������
     */
    public String productName; 
    
    /**
     * (�U�։\����)<BR>
     * �I�����ꂽ�����̐U�։\���ʁi�ۗL���ʁj
     */
    public String changePossQuantity;
    
    /**
     * (�]���z)<BR>
     * �I�����ꂽ�����̕]���z
     */
    public String marketValue;
    
    /**
     * @@roseuid 41B031A102EE
     */
    public WEB3AioSecurityTransferInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioSecurityTransferInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
