head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEFX�����J�݃A�b�v���[�h���̓��N�G�X�g(WEB3AdminFXAccOpenUploadInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 �A����(���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁEFX�����J�݃A�b�v���[�h���̓��N�G�X�g)<BR>
 * �Ǘ��ҁEFX�����J�݃A�b�v���[�h���̓��N�G�X�g�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadInputRequest extends WEB3GenRequest 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602181050L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_acc_open_upload_input";
    
    /**
     * @@roseuid 43F49A6D0232
     */
    public WEB3AdminFXAccOpenUploadInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFXAccOpenUploadInputResponse();        
    }
}
@