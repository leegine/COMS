head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODeclineCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IIPO���ފ������N�G�X�g�N���X(WEB3IPODeclineCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �d�� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (IPO���ފ������N�G�X�g�N���X)<BR>
 * 
 * @@author �d��
 * @@version 1.0
 */
public class WEB3IPODeclineCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_declineComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408100942L;
    
    /**
     * (�h�c)<BR>
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
     * @@roseuid 4112E4E10033
     */
    public WEB3IPODeclineCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E4E1007A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPODeclineCompleteResponse(this);
    }
}
@
