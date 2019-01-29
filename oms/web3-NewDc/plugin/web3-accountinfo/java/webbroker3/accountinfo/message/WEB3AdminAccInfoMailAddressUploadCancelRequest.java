head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���~���N�G�X�g(WEB3AdminAccInfoMailAddressUploadCancelRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 ���L�Y (���u) �V�K�쐬                   
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���~���N�G�X�g)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h���~���N�G�X�g<BR>
 * 
 * @@author ���L�Y(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadCancelRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressUploadCancelRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_accInfo_mailAddressUploadCancel";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200603141626L; 
     
    /**
     * (�A�b�v���[�hID)<BR>
     */
    public String uploadID;
    
    /**
     * @@roseuid 4158E8E200C4
     */
    public WEB3AdminAccInfoMailAddressUploadCancelRequest() 
    {
     
    }    

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�A�b�v���[�hID�̃`�F�b�N<BR>  
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00973<BR>
     * <BR>
     * @@throws WEB3BaseException
     */    
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
       
        // �P�j�@@�A�b�v���[�hID�̃`�F�b�N 
        // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if(WEB3StringTypeUtility.isEmpty(this.uploadID))
        {
            log.debug("�u�A�b�v���[�hID�v = " + uploadID);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A�b�v���[�hID�������͂ł��B");
        }
          
        log.exiting(STR_METHOD_NAME);          
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAccInfoMailAddressUploadCancelResponse(this);
    }    
}
@
