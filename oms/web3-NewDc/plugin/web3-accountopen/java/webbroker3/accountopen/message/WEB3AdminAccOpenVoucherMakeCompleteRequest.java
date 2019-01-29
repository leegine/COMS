head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.06.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenVoucherMakeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݓ`�[�쐬�������N�G�X�g(WEB3AdminAccOpenVoucherMakeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �s�p (���u) �V�K�쐬
Revesion History : 2009/08/13 ���g (���u) �d�l�ύX���f��No.163
*/

package webbroker3.accountopen.message;

import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��Ҍ����J�ݓ`�[�쐬�������N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݓ`�[�쐬�������N�G�X�g<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */

public class WEB3AdminAccOpenVoucherMakeCompleteRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenVoucherMakeCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_voucherMakeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081602L;

    /**
     * (���l�P)<BR>
     * ���l�P<BR>
     * <BR>
     * ���i�s�����ځj���l��<BR>
     */
    public String bikou1;

    /**
     * (���l�Q)<BR>
     * ���l�Q<BR>
     * <BR>
     * ���i�s�����ځj���l��<BR>
     */
    public String bikou2;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�ڋq�敪)<BR>
     * �ڋq�敪<BR>
     */
    public String accountDiv;

    /**
     * (�����J�ݐ\�����)<BR>
     * �����J�ݐ\�����<BR>
     */
    public WEB3AccOpenApplyInfo accoutOpenApplyInfo;

    /**
     * (�s�����ڏ��ꗗ)<BR>
     * �s�����ڏ��ꗗ<BR>
     */
    public WEB3AccOpenInvalidItemInfo[] invalidItemInfo;

    /**
     * (�`�[�쐬���)<BR>
     * �`�[�쐬���<BR>
     */
    public WEB3AccOpenVoucherInfo voucherInfo;

    /**
     * @@roseuid 41B45E78038A
     */
    public WEB3AdminAccOpenVoucherMakeCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenVoucherMakeCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����J�ݐ\�����̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01336<BR>
     * <BR>
     * �Q�j�@@�`�[�쐬���<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01337<BR>
     * �R�j�@@�����J�ݐ\�����. �ڋq�R�[�h�����̔ԃt���O���P�̏ꍇ�A<BR>
     * �@@�ڋq�敪�̃`�F�b�N�����{����<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03180<BR>
     * @@throws WEB3BaseException
     * @@roseuid 418F3060003D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�����J�ݐ\�����̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.accoutOpenApplyInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01336,
                getClass().getName() + STR_METHOD_NAME,
                " �����J�ݐ\����񂪖��w��ł��B" + 
                this.accoutOpenApplyInfo); 
        }
        //�Q�j�@@�`�[�쐬���
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.voucherInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01337,
                getClass().getName() + STR_METHOD_NAME,
                " �`�[�쐬��񂪖��w��ł��B" + 
                this.voucherInfo); 
        }

        //�R�j�@@�����J�ݐ\�����. �ڋq�R�[�h�����̔ԃt���O���P�̏ꍇ�A�ڋq�敪�̃`�F�b�N�����{����
        //�R�|�P�j�@@�����͂̏ꍇ�A ��O���X���[����B
        if (WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(
            accoutOpenApplyInfo.accountCodeAutoFlag))
        {
            if (this.accountDiv == null)
            {
                log.debug("�ڋq�敪�������͂ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03180,
                    getClass().getName() + STR_METHOD_NAME,
                    "�ڋq�敪�������͂ł��B"); 
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
