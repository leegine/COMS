head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�N�G�X�g(WEB3SrvRegiReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�N�G�X�g)<BR>
 * �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�N�G�X�g�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151410L;
    
    /**
     * (�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F1E4A301AD
     */
    public WEB3SrvRegiReferenceRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F1E4A301CC
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3SrvRegiReferenceResponse(this);
    }
}
@
