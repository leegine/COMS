head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingDemandConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�\���m�F���N�G�X�g�N���X(WEB3IPOBookBuildingDemandConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO�u�b�N�r���f�B���O�\���m�F���N�G�X�g�N���X
 * 
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IPOBookBuildingDemandConfirmRequest extends WEB3IPODemandCommonRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingDemandConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171842L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * @@roseuid 4112E79E030C
     */
    public WEB3IPOBookBuildingDemandConfirmRequest() 
    {
     
    }

    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingDemandConfirmResponse(this);
    }    
}
@
