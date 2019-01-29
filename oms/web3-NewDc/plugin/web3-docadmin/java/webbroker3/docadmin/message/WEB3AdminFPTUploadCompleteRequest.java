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
filename	WEB3AdminFPTUploadCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���A�b�v���[�h�������N�G�X�g(WEB3AdminFPTUploadCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 ���g (���u) �V�K�쐬 ���f�� No.013
*/

package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.define.WEB3FPTStatusDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҋ����@@��t�{���A�b�v���[�h�������N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t�{���A�b�v���[�h�������N�G�X�g<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTUploadCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_upload_complete";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200712071142L;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadCompleteRequest.class);

    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadId;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�o�^<BR>
     * 1�F�폜<BR>
     */
    public String statusDiv;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * @@roseuid 4758B278035B
     */
    public WEB3AdminFPTUploadCompleteRequest()
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
        return new WEB3AdminFPTUploadCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�A�b�v���[�hID�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A�u�A�b�v���[�hID�����w��ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_00973�j��O���X���[����B<BR>
     * �@@�@@ class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@:�@@BUSINESS_ERROR_00973<BR>
     * <BR>
     * �Q�j�@@�����敪�̃`�F�b�N<BR>
     * �@@�Q-�P�j�@@�����͂̏ꍇ�A�u�����敪�����w��ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_01249�j��O���X���[����B<BR>
     * �@@�@@ class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@:�@@BUSINESS_ERROR_01249<BR>
     * <BR>
     * �@@�Q-�Q�j�@@0 �܂���1�ȊO�̏ꍇ�A�u�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B�v<BR>
     * �@@�@@�@@�@@�@@�iBUSINESS_ERROR_01250�j��O���X���[����B<BR>
     * �@@�@@ class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@:�@@BUSINESS_ERROR_01250<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473814FD023F
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

        //�Q�j�@@�����敪�̃`�F�b�N
        //�Q-�P�j�@@�����͂̏ꍇ�A�u�����敪�����w��ł��B�v
        //�iBUSINESS_ERROR_01249�j��O���X���[����B
        if (this.statusDiv == null)
        {
            log.debug("�����敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�����w��ł��B");
        }

        //�Q-�Q�j�@@0 �܂���1�ȊO�̏ꍇ�A�u�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B�v
        //�iBUSINESS_ERROR_01250�j��O���X���[����B
        if (!(WEB3FPTStatusDivDef.LOGIN.equals(this.statusDiv)
            || WEB3FPTStatusDivDef.DELETE.equals(this.statusDiv)))
        {
            log.debug("�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
