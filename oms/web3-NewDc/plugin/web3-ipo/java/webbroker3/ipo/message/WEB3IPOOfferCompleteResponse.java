head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�w���\���������X�|���X(WEB3IPOOfferResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �m�a �V�K�쐬
*/
package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
* (IPO�w���\���������X�|���X)<BR>
* 
* @@author �m�a
* @@version 1.0
*/
public class WEB3IPOOfferCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409200935L;
    
    /**
     * �X�V����
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * ���ʔԍ�
     */
    public String orderActionId;
    
    /**
     * @@roseuid 4112E449022A
     */
    public WEB3IPOOfferCompleteResponse() 
    {
     
    }
    
    /**
     * ( IPO�w���\���������X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11B8B02FE
     */
    public WEB3IPOOfferCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
