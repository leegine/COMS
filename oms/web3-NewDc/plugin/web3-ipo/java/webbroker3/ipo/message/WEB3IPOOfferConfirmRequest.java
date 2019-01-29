head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�w���\���m�F���N�G�X�g(WEB3IPOOfferConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �m�a �V�K�쐬
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
* (IPO�w���\���m�F���N�G�X�g)<BR>
* 
* @@author �m�a
* @@version 1.0
*/
public class WEB3IPOOfferConfirmRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408191030L;
    
    /**
     * (�h�c)<BR>
     * IPO�\���h�c
     */
    public String id;
    
    /**
     * �w���\������
     */
    public String offerQuantity;
    
    /**
     * �����敪<BR>
     * <BR>
     * �@@0�F�@@���<BR>
     * �@@1�F�@@����<BR>
     */
    public String taxType;
    
    /**
     * @@roseuid 4112E682017E
     */
    public WEB3IPOOfferConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E44A011D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOOfferConfirmResponse(this);
    }
}
@
