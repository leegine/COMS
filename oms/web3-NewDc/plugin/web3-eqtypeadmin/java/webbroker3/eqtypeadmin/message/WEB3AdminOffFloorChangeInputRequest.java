head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������X�V���̓��N�G�X�g (WEB3AdminOffFloorChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��җ���O���������X�V���̓��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��җ���O���������X�V�T�[�r�X�i���͉�ʕ\���j�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * -----<English>--------------<BR>
 * <BR>
 * WEB3AdminOffFloorChangeInputRequest<BR>
 * <BR>
 * request data of WEB3AdminOffFloorChangeService(input screen)<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOffFloorChangeInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_change_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorChangeCommonRequest.class);

    /**
     * (����O���������L�[)<BR>
     * <BR>
     * ����O���������L�[�B<BR>
     * <BR>
     * productKey<BR>
     * <BR>
     */
    public WEB3AdminOffFloorProductKey productKey;

    /**
     * @@roseuid 421AE345015A
     */
    public WEB3AdminOffFloorChangeInputRequest()
    {

    }

    /**
     * ���N���X�̃v���p�e�B�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@����O���������L�[�`�F�b�N <BR>
     * <BR>
     * �@@�P�|�P�j�@@����O���������L�[.validate( )���R�[������B<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence of the properties in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)off floor productKey check<BR>
     * <BR>
     * �@@1-1)Call off floor productKey.validate( )<BR>
     * <BR>
     * @@roseuid 41A728FF01DD
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 Call productKey.validate()
        this.productKey.validate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminOffFloorChangeInputResponse(this);
    }
}
@
