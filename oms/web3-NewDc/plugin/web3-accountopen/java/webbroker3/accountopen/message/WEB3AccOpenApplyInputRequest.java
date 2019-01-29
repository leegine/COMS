head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\�����̓��N�G�X�g(WEB3AccOpenApplyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
Revesion History : 2009/08/10 �����F(���u) �d�l�ύX ���f��162
Revesion History : 2009/09/02 �����F(���u) �d�l�ύX ���f��204
*/

package webbroker3.accountopen.message;

import webbroker3.accountopen.define.WEB3AccOpenAdminDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����J�ݐ\�����̓��N�G�X�g)<BR>
 * �����J�ݐ\�����̓��N�G�X�g<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenApplyInputRequest extends WEB3GenRequest
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenApplyInputRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_applyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081617L;

    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (���[���A�h���X�o�^ID)<BR>
     * ���[���A�h���X�o�^ID<BR>
     */
    public String mailAddressID;

    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     */
    public String mailAddress;

    /**
     * (�Ǘ��ҋ敪)<BR>
     * �Ǘ��ҋ敪 <BR>
     * <BR>
     * 0�F�Ǘ��ҁA���҈ȊO <BR>
     * 1�F�Ǘ��ҁA���� <BR>
     * null�F0�Ɠ���<BR>
     */
    public String adminDiv;

    /**
     * @@roseuid 41B45E7C00BB
     */
    public WEB3AccOpenApplyInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenApplyInputResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00827<BR>
     * <BR>
     * �Q�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �R�j�@@�Ǘ��ҋ敪�̃`�F�b�N <BR>
     * �@@�R�|�P�j�@@�Ǘ��ҋ敪�@@�I���@@null�A���Ǘ��ҋ敪���u0�A1�v�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03182<BR>
     * @@throws WEB3BaseException
     * @@roseuid 419C932603BC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //* �P�j�@@�،���ЃR�[�h�̃`�F�b�N
        //* �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.institutionCode == null || "".equals(this.institutionCode.trim()))
        {
            log.debug("�،���ЃR�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + STR_METHOD_NAME,
                "�،���ЃR�[�h = null.");
        }
        
        //* �Q�j�@@���X�R�[�h�̃`�F�b�N
        //* �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCode == null || "".equals(this.branchCode.trim()))
        {
            log.debug("���X�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }

        //�R�j�@@�Ǘ��ҋ敪�̃`�F�b�N
        //       �@@�R�|�P�j�@@�Ǘ��ҋ敪�@@�I���@@null�A���Ǘ��ҋ敪���u0�A1�v�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isEmpty(this.adminDiv)
            && !WEB3AccOpenAdminDivDef.NOT_ADMIN.equals(this.adminDiv)
            && !WEB3AccOpenAdminDivDef.ADMIN.equals(this.adminDiv))
        {
            log.debug("�Ǘ��ҋ敪���݂��Ȃ��R�[�h�l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03182,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ǘ��ҋ敪���݂��Ȃ��R�[�h�l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);        
    }
}
@
