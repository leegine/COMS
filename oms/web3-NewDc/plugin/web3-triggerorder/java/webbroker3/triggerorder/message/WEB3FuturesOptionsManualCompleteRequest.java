head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOptionsManualCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�蓮�����������N�G�X�g(WEB3FuturesOptionsManualCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�敨OP�蓮�����������N�G�X�g)<BR>
 * �敨OP�蓮�����������N�G�X�g�N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3FuturesOptionsManualCompleteRequest extends WEB3ManualCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_options_manual_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602162240L;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password = null;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F488920196
     */
    public WEB3FuturesOptionsManualCompleteRequest() 
    {
     
    }
    
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �敨OP�蓮�����������X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOptionsManualCompleteResponse(this);
    }
}
@
