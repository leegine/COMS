head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.01.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\���������N�G�X�g(WEB3AccOpenApplyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����J�ݐ\���������N�G�X�g)<BR>
 * �����J�ݐ\���������N�G�X�g<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenApplyCompleteRequest extends WEB3GenRequest
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenApplyCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_applyComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081612L;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�����J�ݐ\�����)<BR>
     * �����J�ݐ\�����<BR>
     */
    public WEB3AccOpenApplyInfo accoutOpenApplyInfo;

    /**
     * @@roseuid 41B45E7C0261
     */
    public WEB3AccOpenApplyCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenApplyCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����J�ݐ\�����̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01336<BR>
     * <BR>
     * �Q�j�@@�����J�ݐ\�����.�،���ЃR�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01338<BR>
     * <BR>
     * �R�j�@@�����J�ݐ\�����.���X�R�[�h�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01339<BR>
     * @@throws WEB3BaseException
     * @@roseuid 419C947F014B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����J�ݐ\�����̃`�F�b�N<BR>
        //�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.accoutOpenApplyInfo == null)
        {
            log.debug("�����J�ݐ\����񂪖��w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01336,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�ݐ\����񂪖��w��ł��B");
        }
        
        //�Q�j�@@�����J�ݐ\�����.�،���ЃR�[�h�̃`�F�b�N
        //�@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.accoutOpenApplyInfo.institutionCode == null || "".equals(this.accoutOpenApplyInfo.institutionCode.trim()))
        {
            log.debug("�����J�ݐ\�����̏،���ЃR�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01338,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�ݐ\�����̏،���ЃR�[�h�����w��ł��B");
        }

        //�R�j�@@�����J�ݐ\�����.���X�R�[�h�̃`�F�b�N
        //�@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.accoutOpenApplyInfo.branchCode == null || "".equals(this.accoutOpenApplyInfo.branchCode.trim()))
        {
            log.debug("�����J�ݐ\�����̕��X�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01339,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�ݐ\�����̕��X�R�[�h�����w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
}
@
