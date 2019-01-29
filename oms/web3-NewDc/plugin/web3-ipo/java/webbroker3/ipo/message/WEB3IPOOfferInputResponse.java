head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�w���\�����̓��X�|���X(WEB3IPOOfferInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �m�a �V�K�쐬
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;

/**
* (IPO�w���\�����̓��X�|���X)<BR>
* 
* @@author �m�a
* @@version 1.0
*/
public class WEB3IPOOfferInputResponse extends WEB3IPOOfferCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408191032L;
    
    /**
     * �\������
     */
    public String demandQuantity;
    
    /**
     * �����敪�ꗗ<BR>
     * <BR>
     * �@@0�F�@@���<BR>
     * �@@1�F�@@����<BR>
     */
    public String[] taxTypeList;
    
    /**
     * �w���\�����ʕύX�\�t���O<BR>
     * <BR>
     * �@@true�F�@@�w���\�����ʓ��͉\�i�\���j<BR>
     * �@@false�F�@@�w���\�����ʂ𓖑I���ʂɌŒ�i��\���j<BR>
     */
    public boolean offerQuantityFlag;
    
    /**
     * �ژ_�����{���`�F�b�N����<BR>
     */
    public WEB3GentradeProspectusResult prospectusResult;
    
    /**
     * @@roseuid 4112E44902D4
     */
    public WEB3IPOOfferInputResponse() 
    {
     
    }
    
    /**
     * (IPO�w���\�����̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40DBBC390029
     */
    public WEB3IPOOfferInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
