head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֊������X�|���X(WEB3AioSecurityTransferCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�،��U�֊������X�|���X)<BR>
 * �،��U�֊������X�|���X�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412070946L; 
    
    /**
     * (�X�V����)<BR>
     * ������o�^��������
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (����ID)<BR>
     * �m�F���Ɏ擾��������ID
     */
    public String orderId;
    
    /**
     * @@roseuid 41B0255E030D
     */
    public WEB3AioSecurityTransferCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioSecurityTransferCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
