head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ���N�G�X�g(WEB3AdminSrvRegiServiceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ���N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ���N�G�X�g�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151448L;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE2D4B01FA
     */
    public WEB3AdminSrvRegiServiceReferenceRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ���X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE2D4302B6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiServiceReferenceResponse(this);
    }
}
@
