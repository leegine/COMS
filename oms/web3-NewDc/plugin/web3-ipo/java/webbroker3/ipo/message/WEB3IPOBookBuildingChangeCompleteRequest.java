head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�����������N�G�X�g�N���X(WEB3IPOBookBuildingChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ���]��(���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * IPO�u�b�N�r���f�B���O�����������N�G�X�g�N���X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingChangeCompleteRequest extends WEB3IPODemandCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171401L;
    
    /**
     * IPO�\���h�c
     */
    public String id;
    
    /**
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * �m�F��������
     */
    public Date checkDate;
    
    /**
     * �m�F����l
     */
    public String checkValue;
    
    /**
     * @@roseuid 4112EDC401C5
     */
    public WEB3IPOBookBuildingChangeCompleteRequest() 
    {
        
    }
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E79E019A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingChangeCompleteResponse(this);
    }    
}
@
