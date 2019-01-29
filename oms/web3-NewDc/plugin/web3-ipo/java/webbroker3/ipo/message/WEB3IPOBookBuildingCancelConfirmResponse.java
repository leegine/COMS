head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O����m�F���X�|���X�N���X(WEB3IPOBookBuildingCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * IPO�u�b�N�r���f�B���O����m�F���X�|���X�N���X
 * 
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IPOBookBuildingCancelConfirmResponse extends WEB3IPODemandCommonResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171845L;
    
    /**
     * �\������
     */
    public String demandQuantity;
    
    /**
     * �\�����i�敪<BR>
     * <BR>
     * 0�F�@@���s<BR>
     * 1�F�@@�w�l
     */
    public String demandPriceDiv;
    
    /**
     * �\�����i
     */
    public String demandPrice;
    
    /**
     * �\�������z
     */
    public String demandEquivalentPrice;
    
    /**
     * �m�F��������
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4112EDC4025C
     */
    public WEB3IPOBookBuildingCancelConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D26D6303DF
     */
    public WEB3IPOBookBuildingCancelConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@
