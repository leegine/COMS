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
filename	WEB3AdminFPTForceLogoutReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ʖ����� �������O�A�E�g���ʏƉ�X�|���X(WEB3AdminFPTForceLogoutReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g���ʏƉ�X�|���X
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_fpt_force_logout_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200803181606L;
    
    /**
     * �Ǘ��� ���ʖ����� �������O�A�E�g���ʏƉ�Unit�ꗗ
     */
    public WEB3FPTForceLogoutInfoUnit[] forceLogoutInfoList;
    
    /**
     * @@roseuid 47DF46770288
     */
    public WEB3AdminFPTForceLogoutReferenceResponse() 
    {
     
    }
    
    /**
    *
    * @@param l_request WEB3GenRequest
    */
    public WEB3AdminFPTForceLogoutReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
