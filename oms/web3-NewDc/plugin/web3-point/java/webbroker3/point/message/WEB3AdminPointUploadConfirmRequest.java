head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�b�v���[�h�m�F���N�G�X�g(WEB3AdminPointUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�A�b�v���[�h�m�F���N�G�X�g)<BR>
 * �A�b�v���[�h�m�F���N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointUploadConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_uploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290015L;
    
    /**
     * (�A�b�v���[�h�t�@@�C��)<BR>
     * ���͂��ꂽ�A�b�v���[�h�t�@@�C��<BR>
     * �iCSV�t�@@�C���̍s�̔z��j<BR>
     */
    public String[] uploadFile;
    
    /**
     * @@roseuid 41D1254F02AF
     */
    public WEB3AdminPointUploadConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254F02BF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointUploadConfirmResponse(this);
    }
}
@
