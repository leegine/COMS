head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingEnterRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�Q�����N�G�X�g�N���X(WEB3IPOBookBuildingEnterRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO�u�b�N�r���f�B���O�Q�����N�G�X�g�N���X
 * 
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IPOBookBuildingEnterRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingEnter";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171848L;
    
    /**
     * @@roseuid 4112EA8500B2
     */
    public WEB3IPOBookBuildingEnterRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112EA8500C6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingEnterResponse(this);
    }
}
@
