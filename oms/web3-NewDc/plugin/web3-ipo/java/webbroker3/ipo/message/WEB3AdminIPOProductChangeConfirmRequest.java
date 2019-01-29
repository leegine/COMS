head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���������m�F���N�G�X�g(WEB3AdminIPOProductChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 �d�� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO���������m�F���N�G�X�g)<BR>
 * �Ǘ���IPO���������m�F���N�G�X�g�f�[�^�N���X
 * 
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminIPOProductChangeConfirmRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_productChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408161032L;
        
    /**
     * (�h�c)<BR>
     * IPO�����h�c
     */
    public String id;
    
    /**
     * (�������)<BR>
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112E37F0297
     */
    public WEB3AdminIPOProductChangeConfirmRequest() 
    {
     
    }
    
    /**
     * this.IPO�������.validate() ���R�[������B
     * @@roseuid 40C9519902BB
     */
    public void validate() throws WEB3BaseException 
    {
    //2004/11/18 U00441 this.IPO�������ɏC���@@���@@SRA  START	
		this.ipoProductInfo.validate();
//		ipoProductInfo.validate();
	//2004/11/18 U00441 this.IPO�������ɏC���@@���@@SRA  END     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E37F02BF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductChangeConfirmResponse(this);
    }
}
@
