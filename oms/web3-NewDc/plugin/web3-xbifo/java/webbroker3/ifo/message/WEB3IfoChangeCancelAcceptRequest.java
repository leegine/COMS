head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP���������t���N�G�X�g(WEB3IfoChangeCancelAcceptRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�敨OP���������t���N�G�X�g)<BR>
 * �敨OP���������t���N�G�X�g�N���X<BR>
 * @@author ���C�g
 * @@version 1.0 
 */
public class WEB3IfoChangeCancelAcceptRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="ifo_changeCancelAccept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141539L;
        
    /**
     * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * �X���b�hNo<BR>
     */
    public Long threadNo;

    /**
     * @@roseuid 40C0A9DD034B
     */
    public WEB3IfoChangeCancelAcceptRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3IfoChangeCancelAcceptResponse(this);
    }
}
@
