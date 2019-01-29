head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductRegistrationCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����V�K�o�^�������N�G�X�g(WEB3AdminIPOProductRegistrationCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���o�� �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO�����V�K�o�^�������N�G�X�g)<BR>
 * IPO�����V�K�o�^�������N�G�X�g�N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIPOProductRegistrationCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productRegistrationComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161053L;
    
    /**
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * (�������)<BR>
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112DF8D023B
     */
    public WEB3AdminIPOProductRegistrationCompleteRequest() 
    {
     
    }
    
    /**
     * this.IPO�������.validate() ���R�[������B
     * @@roseuid 40C418980212
     */
    public void validate() throws WEB3BaseException
    {
        this.ipoProductInfo.validate();    
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8D0259
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductRegistrationCompleteResponse(this);
    }
}
@
