head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandOfferResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�\���w���\�����X�|���X�N���X(WEB3IPODemandOfferResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���]��(���u) �V�K�쐬
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ipo.message.WEB3IPODemandOfferProductUnit;


/**
 * IPO�\���w���\�����X�|���X�N���X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3IPODemandOfferResponse extends WEB3GenResponse 
{
    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "IPO_demandOffer";

    /**
      * SerialVersionUID<BR>
      */
    public static final long serialVersionUID = 200408101044L;
    
    /**
     * �V�K���ꗗ
     */
    public WEB3IPODemandOfferProductUnit[] newListingList;
    
    /**
     * �����ꗗ
     */
    public WEB3IPODemandOfferProductUnit[] listingList;
    
    /**
     * @@roseuid 4112E44A0271
     */
    public WEB3IPODemandOfferResponse() 
    {
     
    }
    
    /**
     * ( IPO�w���\�����̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40DC03E50071
     */
    public WEB3IPODemandOfferResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
