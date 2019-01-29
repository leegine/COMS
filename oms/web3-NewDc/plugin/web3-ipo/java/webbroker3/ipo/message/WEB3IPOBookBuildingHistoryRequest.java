head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�\���������N�G�X�g�N���X(WEB3IPOBookBuildingHistoryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���]��(���u) �V�K�쐬
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * IPO�u�b�N�r���f�B���O�\���������N�G�X�g�N���X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingHistoryRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408101037L;
    
    /**
     * (�h�c)<BR>
     * IPO�\���h�c
     */
    public String id;
    
    /**
     * @@roseuid 4112E4E10192
     */
    public WEB3IPOBookBuildingHistoryRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E4E101A6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingHistoryResponse(this);
    }
}
@
