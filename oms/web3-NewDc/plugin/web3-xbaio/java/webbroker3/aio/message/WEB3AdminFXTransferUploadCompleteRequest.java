head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferUploadCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEFX�U�֒����A�b�v���[�h�������N�G�X�g(WEB3AdminFXTransferUploadCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/28 �A����(���u) �V�K�쐬
                   2006/04/10 �R��(SRA)�@@��Q�Ǘ�No.U02813  
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁEFX�U�֒����A�b�v���[�h�������N�G�X�g)<BR>
 * �Ǘ��ҁEFX�U�֒����A�b�v���[�h�������N�G�X�g�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXTransferUploadCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferUploadCompleteRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602221850L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_transfer_upload_complete";
    
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
     * @@roseuid 43FC1AE4002E
     */
    public WEB3AdminFXTransferUploadCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�A�b�v���[�hID�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag  :   BUSINESS_ERROR_00973<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43C752AE02EB
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�A�b�v���[�hID�̃`�F�b�N
        // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(uploadId))
        {
            log.debug("�A�b�v���[�hID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                getClass().getName() + "." + STR_METHOD_NAME,
                "");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferUploadCompleteResponse();
    }
}
@
