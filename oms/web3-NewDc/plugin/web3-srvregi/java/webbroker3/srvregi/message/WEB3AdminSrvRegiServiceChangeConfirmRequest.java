head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���N�G�X�g(WEB3AdminSrvRegiServiceChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���N�G�X�g�N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeConfirmRequest extends WEB3AdminSrvRegiServiceChangeCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE4730013F
     */
    public WEB3AdminSrvRegiServiceChangeConfirmRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�m�F���X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE47C00093
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiServiceChangeConfirmResponse(this);
    }
}
@
