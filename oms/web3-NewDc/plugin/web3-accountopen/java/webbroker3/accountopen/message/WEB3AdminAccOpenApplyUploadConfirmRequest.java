head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.02.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\��UL�m�F���N�G�X�g(WEB3AdminAccOpenApplyUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 ���g (���u) �V�K�쐬 ���f�� No.147
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��Ҍ����J�ݐ\��UL�m�F���N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݐ\��UL�m�F���N�G�X�g<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyUploadConfirmRequest extends WEB3GenRequest
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyUploadConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_upload_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200711211758L;

    /**
     * (�A�b�v���[�h�t�@@�C��)<BR>
     * �A�b�v���[�h�t�@@�C��<BR>
     */
    public String[] uploadFile;

    /**
     * @@roseuid 4743EF520269
     */
    public WEB3AdminAccOpenApplyUploadConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyUploadConfirmResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�A�b�v���[�h�t�@@�C���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_00976<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4729784201C4
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

    	if (this.uploadFile == null || this.uploadFile.length == 0)
    	{
    		log.debug("�A�b�v���[�h�t�@@�C�������w��ł��B");
    		log.exiting(STR_METHOD_NAME);
    		throw new WEB3BusinessLayerException(
    			WEB3ErrorCatalog.BUSINESS_ERROR_00976,
    			this.getClass().getName() + "." + STR_METHOD_NAME,
    			"�A�b�v���[�h�t�@@�C�������w��ł��B");
    	}

    	log.exiting(STR_METHOD_NAME);

    }
}
@
