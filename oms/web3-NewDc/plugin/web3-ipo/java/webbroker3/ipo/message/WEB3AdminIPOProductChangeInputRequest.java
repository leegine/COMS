head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����������̓��N�G�X�g(WEB3AdminIPOProductChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 �d�� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO�����������̓��N�G�X�g)<BR>
 * �Ǘ���IPO�����������̓��N�G�X�g�f�[�^�N���X
 * 
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminIPOProductChangeInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_productChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408161038L;
    
    /**
     * (�h�c)<BR>
     * IPO�����h�c
     */
    public String id;
    
    /**
     * @@roseuid 4112E37F016B
     */
    public WEB3AdminIPOProductChangeInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E37F017F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductChangeInputResponse(this);
    }
}
@
