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
filename	WEB3AdminOffFloorRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������V�K�o�^�m�F���N�G�X�g(WEB3AdminOffFloorRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��җ���O���������V�K�o�^�m�F���N�G�X�g)<BR>
 * <BR>
 * �Ǘ��җ���O���������V�K�o�^�T�[�r�X�i�m�F�j�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * ------<English>----------------<BR>
 * <BR>
 * WEB3AdminOffFloorRegistConfirmRequest<BR>
 * <BR>
 * request data of WEB3AdminOffFloorRegistService(validate)<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorRegistConfirmRequest extends WEB3AdminOffFloorRegistCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_regist_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorRegistConfirmRequest.class);

    /**
     * @@roseuid 421AE45A0070
     */
    public WEB3AdminOffFloorRegistConfirmRequest()
    {

    }

    /**
     * ���N���X�̃v���p�e�B�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@super.validate( )���R�[������B<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence of the properties in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)Call super.validate( )<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 41B7D2D4011D
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 Call super.validate( )
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminOffFloorRegistConfirmResponse(this);
    }
}
@
