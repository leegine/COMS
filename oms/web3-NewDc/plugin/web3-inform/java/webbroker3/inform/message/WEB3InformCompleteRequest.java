head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���������N�G�X�g(WEB3InformCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
*/
package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A���������N�G�X�g)<BR>
 * �A���������N�G�X�g�N���X
 */
public class WEB3InformCompleteRequest extends WEB3GenRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200501251410L;
    /**
     * (�A�����)<BR>
     * �A�����<BR>
     */
    public WEB3InformDetailInfoUnit informInfoUnit;

    /**
     * (�Ïؔԍ�)<BR>
     * ��ʂɂē��͂��ꂽ�Ïؔԍ�<BR>
     */
    public String password;

    /**
     * @@roseuid 41EE625C00BB
     */
    public WEB3InformCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {

        return new WEB3InformCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�A����� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_01816<BR>
     * <BR>
     * �Q�j�A�����.validate()���R�[������B<BR>
     * <BR>
     * @@roseuid 418F525D0388
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�A����� == null �̏ꍇ�A��O���X���[����
        if (this.informInfoUnit == null)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01816,
                this.getClass().getName() + STR_METHOD_NAME,
                "�A�����null�̒l�ł���B");
        }

        // �Q�j�A�����.validate()���R�[������
        this.informInfoUnit.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
