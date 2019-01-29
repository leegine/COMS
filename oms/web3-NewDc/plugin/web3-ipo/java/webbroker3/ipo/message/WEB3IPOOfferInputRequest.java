head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�w���\�����̓��N�G�X�g(WEB3IPOOfferInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �m�a �V�K�쐬
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
* (IPO�w���\�����̓��N�G�X�g)<BR>
* 
* @@author �m�a
* @@version 1.0
*/
public class WEB3IPOOfferInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408191031L;
    
    /**
     * (�h�c)<BR>
     * IPO�\���h�c
     */
    public String id;
    
    /**
     * (�d�q���`�F�b�N�t���O)<BR>
     * true�F�`�F�b�N���� <BR>
     * false�F�`�F�b�N���Ȃ�<BR>
     */
    public boolean batoCheckFlag;
    
    /**
     * (��ʃR�[�h�iIPO�ژ_�����j)<BR>
     */
    public String typeCode;
    
    /**
     * @@roseuid 4112E68200BF
     */
    public WEB3IPOOfferInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E4490388
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOOfferInputResponse(this);
    }
}
@
