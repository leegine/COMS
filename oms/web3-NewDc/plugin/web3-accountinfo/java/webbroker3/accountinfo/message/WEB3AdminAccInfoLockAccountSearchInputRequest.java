head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��N�G�X�g(WEB3AdminAccInfoLockAccountSearchInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��N�G�X�g)<BR>
 * �Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��N�G�X�g<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountSearchInputRequest extends WEB3GenRequest 
{
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_lockAccountSearchInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509191350L;
    
     /**
      * (�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��N�G�X�g)<BR>
      */
     public WEB3AdminAccInfoLockAccountSearchInputRequest()
     {

     }
     
     /**
      * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
      *<BR>
      * @@return ���X�|���X�I�u�W�F�N�g
      */
     public WEB3GenResponse createResponse()
     {
         return new WEB3AdminAccInfoLockAccountSearchInputResponse(this);
     }
}
@
