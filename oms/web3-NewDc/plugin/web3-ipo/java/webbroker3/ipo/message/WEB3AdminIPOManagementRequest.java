head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOManagementRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����Ǘ����N�G�X�g(WEB3AdminIPOManagementRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���o�� �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO�����Ǘ����N�G�X�g)<BR>
 * IPO�����Ǘ����N�G�X�g�N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIPOManagementRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_management";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408160950L;
    
    /**
     * @@roseuid 4112DF8C035C
     */
    public WEB3AdminIPOManagementRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8C0366
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOManagementResponse(this);
    }
}
@
