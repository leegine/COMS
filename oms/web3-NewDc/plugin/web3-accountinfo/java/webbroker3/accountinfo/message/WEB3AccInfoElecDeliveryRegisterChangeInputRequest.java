head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoElecDeliveryRegisterChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �d�q��t�T�[�r�X�o�^�E�ύX���̓��N�G�X�g(WEB3AccInfoElecDeliveryRegisterChangeInputRequest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 �����F(���u) �V�K�쐬 �d�l�ύX���f��No.277
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoEleDeliveryFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�d�q��t�T�[�r�X�o�^�E�ύX���̓��N�G�X�g)<BR>
 * �d�q��t�T�[�r�X�o�^�E�ύX���̓��N�G�X�g�N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AccInfoElecDeliveryRegisterChangeInputRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "elec_delivery_register_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011121537L;

    /**
     * (�d�q��t�\���t���O)<BR>
     * �d�q��t�\���t���O<BR>
     * <BR>
     * 0�F�@@�\�����Ȃ� <BR>
     * 1�F�@@�\��<BR>
     */
    public String eleDeliveryFlag;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3AccInfoElecDeliveryRegisterChangeInputRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�d�q��t�\���t���O�̃`�F�b�N <BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03221<BR>
     * �@@�P�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03222<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3StringTypeUtility.isEmpty(this.eleDeliveryFlag))
        {
            log.debug("�d�q��t�\���t���O�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03221,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�\���t���O�������͂ł��B");
        }

        if (!WEB3AccInfoEleDeliveryFlagDef.NOT_APPLY.equals(this.eleDeliveryFlag)
            && !WEB3AccInfoEleDeliveryFlagDef.APPLY.equals(this.eleDeliveryFlag))
        {
            log.debug("�d�q��t�\���t���O������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03222,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�\���t���O������`�̒l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoElecDeliveryRegisterChangeInputResponse(this);
    }
}
@
