head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�w���\���m�F���X�|���X(WEB3IPOOfferConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �m�a �V�K�쐬
*/
package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
* (IPO�w���\���m�F���X�|���X)<BR>
* 
* @@author �m�a
* @@version 1.0
*/
public class WEB3IPOOfferConfirmResponse extends WEB3GenResponse 
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
     * ���J���i
     */
    public String publicOfferingPrice;
    
    /**
     * �w���\�����
     */
    public String offerPrice;
    
    /**
     * �m�F��������
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4112E44A0022
     */
    public WEB3IPOOfferConfirmResponse() 
    {
     
    }
    
    /**
     * (IPO�w���\���m�F���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11B7D006E
     */
    public WEB3IPOOfferConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
