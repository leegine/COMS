head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�����������N�G�X�g(WEB3AdminPointPremiumChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�i�i�����������N�G�X�g)<BR>
 * �i�i�����������N�G�X�g�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointPremiumChangeCompleteRequest extends WEB3AdminPointPremiumChangeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumChangeComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * @@roseuid 41D1254A0186
     */
    public WEB3AdminPointPremiumChangeCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1232302FD
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointPremiumChangeCompleteResponse(this);
    }
}
@
