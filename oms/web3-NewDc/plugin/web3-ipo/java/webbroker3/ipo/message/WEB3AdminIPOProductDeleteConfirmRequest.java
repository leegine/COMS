head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����폜�m�F���N�G�X�g(WEB3AdminIPOProductDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���o�� �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO�����폜�m�F���N�G�X�g)<BR>
 * �Ǘ���IPO�����폜�m�F���N�G�X�g�f�[�^�N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIPOProductDeleteConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productDeleteConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161024L;
    
    /**
     * (�h�c)<BR>
     * IPO�����h�c
     */
    public String id;
    
    /**
     * @@roseuid 4112E3390279
     */
    public WEB3AdminIPOProductDeleteConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E339028D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductDeleteConfirmResponse(this);
    }
}
@
