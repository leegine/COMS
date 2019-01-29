head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOProductInfoRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�ʖ�����񃊃N�G�X�g�N���X(WEB3IPOProductInfoRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO�ʖ�����񃊃N�G�X�g�N���X
 * 
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IPOProductInfoRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_productInfo";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171833L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * @@roseuid 4112E79C0129
     */
    public WEB3IPOProductInfoRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E79C013D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOProductInfoResponse(this);
    }
}
@
