head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����o�H�ؑ֊m�F���N�G�X�g (WEB3AdminFrontRouteChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/
package webbroker3.dirsec.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�����o�H�ؑ֊m�F���N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�����o�H�ؑփT�[�r�X�i�m�F�j�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * -------<English>----------------<BR>
 * <BR>
 * WEB3AdminFrontRouteChangeConfirmRequest<BR>
 * <BR>
 * request data of WEB3AdminFrontRouteChangeService(validate)<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontRouteChangeConfirmRequest extends WEB3AdminFrontRouteChangeCommonRequest {
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_route_change_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontRouteChangeConfirmRequest.class);

    /**
     * @@roseuid 42FFFED50041
     */
    public WEB3AdminFrontRouteChangeConfirmRequest()
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
     * @@roseuid 41A729FB01AB
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 Call super.validate()
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFrontRouteChangeConfirmResponse(this);
    }


}
@
