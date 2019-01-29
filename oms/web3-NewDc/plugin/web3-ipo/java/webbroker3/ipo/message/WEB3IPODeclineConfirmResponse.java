head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODeclineConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO���ފm�F���X�|���X�N���X(WEB3IPODeclineConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �d�� (���u) �V�K�쐬
*/
package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * IPO���ފm�F���X�|���X�N���X
 * 
 * @@author �d��
 * @@version 1.0
 */
public class WEB3IPODeclineConfirmResponse extends WEB3IPOOfferCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_declineConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408100942L;
    
    /**
     * ���J���i
     */
    public String publicOfferingPrice;
    
    /**
     * �w���\�����
     */
    public String offerPrice;
    
    /**
     * �w���\�����ʕύX�\�t���O<BR>
     * <BR>
     * �@@true�F�@@�w���\�����ʓ��͉\�i�\���j<BR>
     * �@@false�F�@@�w���\�����ʂ𓖑I���ʂɌŒ�i��\���j<BR>
     */
    public boolean offerQuantityFlag;
    
    /**
     * �m�F��������
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4112E44B0038
     */
    public WEB3IPODeclineConfirmResponse() 
    {
                              
    }
    
    /**
     * ( IPO���ފm�F���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11B98006E
     */
    public WEB3IPODeclineConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);             
    }
}
    @
