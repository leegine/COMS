head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ʖ����� �������O�A�E�g���̓��X�|���X(WEB3AdminFPTForceLogoutInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g���̓��X�|���X
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_fpt_force_logout_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200803181606L;
    
    /**
     * ���ʋ敪�Ǘ����ꗗ
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;
    
    /**
     * @@roseuid 47DF467700A4
     */
    public WEB3AdminFPTForceLogoutInputResponse() 
    {
     
    }
    
    /**
    *
    * @@param l_request WEB3GenRequest
    */
    public WEB3AdminFPTForceLogoutInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
