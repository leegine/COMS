head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherMakeCnfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�����`�[�쐬�m�F���N�G�X�g(WEB3AdminInformProfDistVoucherMakeCnfRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.054
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁE�����`�[�쐬�m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE�����`�[�쐬�m�F���N�G�X�g�N���X
 */
public class WEB3AdminInformProfDistVoucherMakeCnfRequest extends WEB3AdminInformProfDistVoucherCommonRequest
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistVoucherMakeCnfRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_make_cnf";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * @@roseuid 4663A9D6033C
     */
    public WEB3AdminInformProfDistVoucherMakeCnfRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformProfDistVoucherMakeCnfResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�A����� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@BUSINESS_ERROR_01816<BR>
     * <BR>
     * �Q�j�A�����.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 461F2F6E016B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�A����� == null �̏ꍇ�A��O���X���[����B
        if (this.informInfoUnit == null)
        {
            log.debug("�A�����null�̒l�ł���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01816,
                this.getClass().getName() + STR_METHOD_NAME,
                "�A�����null�̒l�ł���B");
        }
        // �Q�j�A�����.validate()���R�[������B
        this.informInfoUnit.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
