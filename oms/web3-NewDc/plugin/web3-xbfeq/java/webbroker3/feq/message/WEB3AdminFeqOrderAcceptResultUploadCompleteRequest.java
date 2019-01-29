head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptResultUploadCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t���ʃA�b�v���[�h�������N�G�X�g(WEB3AdminFeqOrderAcceptResultUploadCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO������������t���ʃA�b�v���[�h�������N�G�X�g)<BR>
 * �Ǘ��ҊO������������t���ʃA�b�v���[�h�������N�G�X�g�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptResultUploadCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptResultUploadCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptResultUploadComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID
     */
    public String uploadId;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 42CE39FC034B
     */
    public WEB3AdminFeqOrderAcceptResultUploadCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�A�b�v���[�h�h�c�̃`�F�b�N <BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR> 
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00973<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A0509A034A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�A�b�v���[�h�h�c�̃`�F�b�N 
        //�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.uploadId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                getClass().getName() + STR_METHOD_NAME,
                "�A�b�v���[�h�h�c�������͂ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqOrderAcceptResultUploadCompleteResponse(this);
    }
}
@
