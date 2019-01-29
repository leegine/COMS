head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\���������N�G�X�g(WEB3InformAccSwElecDeliApplyCmpRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��097
Revision History : 2007/08/30 �g�E�N�| (���u) �d�l�ύX���f��107
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����ؑցE�d�q��t�\���������N�G�X�g)<BR>
 * �����ؑցE�d�q��t�\���������N�G�X�g�N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyCmpRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformAccSwElecDeliApplyCmpRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_acc_sw_elec_deli_apply_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707191033L;

    /**
     * (�A�����)<BR>
     */
    public String informType;

    /**
     * (�Ïؔԍ�)<BR>
     */
    public String password;

    /**
     * (�ύX����)<BR>
     * �����ؑցE�d�q��t�\�����<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo;

    public WEB3InformAccSwElecDeliApplyCmpRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�A����ʂ̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01817<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�A����ʂ̃`�F�b�N
        // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.informType == null)
        {
            log.debug("�A����ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A����ʂ����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3InformAccSwElecDeliApplyCmpResponse(this);
    }
}
@