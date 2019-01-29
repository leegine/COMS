head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingEnterResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�Q�����X�|���X�N���X(WEB3IPOBookBuildingEnterResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO�u�b�N�r���f�B���O�Q�����X�|���X�N���X
 * 
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IPOBookBuildingEnterResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingEnter";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171847L;
    
    /**
     * �V�K���ꗗ
     */
    public WEB3IPOPublicOfferingProductUnit[] newListingList;
    
    /**
     * �����ꗗ
     */
    public WEB3IPOPublicOfferingProductUnit[] listingList;
    
    /**
     * @@roseuid 4112EA850030
     */
    public WEB3IPOBookBuildingEnterResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D165300353
     */
    public WEB3IPOBookBuildingEnterResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@