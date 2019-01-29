head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTUploadCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���A�b�v���[�h���~���N�G�X�g(WEB3AdminFPTUploadCancelRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 ���g (���u) �V�K�쐬 ���f�� No.013
*/

package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҋ����@@��t�{���A�b�v���[�h���~���N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t�{���A�b�v���[�h���~���N�G�X�g<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTUploadCancelRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_upload_cancel";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200712071136L;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadCancelRequest.class);

    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadId;

    /**
     * @@roseuid 4758B2790000
     */
    public WEB3AdminFPTUploadCancelRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTUploadCancelResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�A�b�v���[�hID�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@:�@@BUSINESS_ERROR_00973<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4738152603B9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�A�b�v���[�hID�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.uploadId))
        {
            log.debug("�A�b�v���[�hID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A�b�v���[�hID�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
