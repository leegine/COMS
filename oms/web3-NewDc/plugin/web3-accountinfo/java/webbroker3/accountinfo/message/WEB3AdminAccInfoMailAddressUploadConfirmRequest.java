head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���N�G�X�g(WEB3AdminAccInfoMailAddressUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 ���̖N (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���N�G�X�g)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�m�F���N�G�X�g<BR>
 * 
 * @@author ���̖N(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadConfirmRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressUploadConfirmRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603141715L;
    
    /**
     * (�A�b�v���[�h�t�@@�C��)<BR>
     * ����۰�ނ���t�@@�C����Ǎ��݁A1�s���ƂɃZ�b�g����String[]�œ]������<BR>
     */
    public String[] uploadFile;

    /**
     * @@roseuid 418F3857000F
     */
    public WEB3AdminAccInfoMailAddressUploadConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressUploadConfirmResponse(this);
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�A�b�v���[�h�t�@@�C���̃`�F�b�N<BR>  
     *   �P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00976<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4163B1E4039C
     */
    public void validate() throws WEB3BaseException
    { 
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�A�b�v���[�h�t�@@�C���̃`�F�b�N 
        //   �P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.uploadFile == null || this.uploadFile.length == 0)
        {  
            log.debug("�A�b�v���[�h�t�@@�C�������w��ł��B");
            log.exiting(STR_METHOD_NAME);            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�A�b�v���[�h�t�@@�C�������w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
