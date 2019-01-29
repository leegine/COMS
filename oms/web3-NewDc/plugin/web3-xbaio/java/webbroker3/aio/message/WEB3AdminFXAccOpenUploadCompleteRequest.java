head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEFX�����J�݃A�b�v���[�h�������N�G�X�g(WEB3AdminFXAccOpenUploadCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 �A����(���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁEFX�����J�݃A�b�v���[�h�������N�G�X�g)<BR>
 * �Ǘ��ҁEFX�����J�݃A�b�v���[�h�������N�G�X�g�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadCompleteRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenUploadCompleteRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602181050L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_acc_open_upload_complete";
    
    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadId;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * @@roseuid 43F49A6D030D
     */
    public WEB3AdminFXAccOpenUploadCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�A�b�v���[�hID�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :  BUSINESS_ERROR_00973<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43DDE1070228
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�A�b�v���[�hID�̃`�F�b�N
        // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (uploadId == null)
        {
            log.debug("�A�b�v���[�hID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A�b�v���[�hID�����w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFXAccOpenUploadCompleteResponse();        
    }
}
@
