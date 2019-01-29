head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.09.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyUploadCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\��UL���~���N�G�X�g(WEB3AdminAccOpenApplyUploadCancelRequest.java)
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
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��Ҍ����J�ݐ\��UL���~���N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݐ\��UL���~���N�G�X�g<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyUploadCancelRequest extends WEB3GenRequest
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyUploadCancelRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_upload_cancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200711211748L;

    /**
     * (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     */
    public String uploadID;

    /**
     * @@roseuid 4743EF520382
     */
    public WEB3AdminAccOpenApplyUploadCancelRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyUploadCancelResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�A�b�v���[�hID�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_00973<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4729785B01B1
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

    	if (WEB3StringTypeUtility.isEmpty(this.uploadID))
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
