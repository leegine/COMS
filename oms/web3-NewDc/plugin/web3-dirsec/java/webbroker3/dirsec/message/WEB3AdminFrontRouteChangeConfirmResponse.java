head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����o�H�ؑ֊m�F���X�|���X (WEB3AdminFrontRouteChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����o�H�ؑ֊m�F���X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE�����o�H�ؑփT�[�r�X�i�m�F�j�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * ----<English>------------------<BR>
 * <BR>
 * WEB3AdminFrontRouteChangeConfirmResponse<BR>
 * <BR>
 * response data of WEB3AdminFrontRouteChangeService(validate)<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontRouteChangeConfirmResponse extends WEB3GenResponse {

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_route_change_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * roseuid 42FFFED500DE
     */
    public WEB3AdminFrontRouteChangeConfirmResponse()
    {

    }

    /**
     * @@roseuid 
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminFrontRouteChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }


}
@
