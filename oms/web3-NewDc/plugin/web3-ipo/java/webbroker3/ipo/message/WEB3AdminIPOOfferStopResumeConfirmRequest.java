head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOOfferStopResumeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO��W��~�ĊJ�m�F���N�G�X�g�N���X(WEB3AdminIPOOfferStopResumeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO��W��~�ĊJ�m�F���N�G�X�g�N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIPOOfferStopResumeConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_offerStopResumeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131155L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * @@roseuid 4112DAD302B3
     */
    public WEB3AdminIPOOfferStopResumeConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD302C7
     */
    public WEB3GenResponse createResponse() 
    {

        return new WEB3AdminIPOOfferStopResumeConfirmResponse(this);

    }
}
@
