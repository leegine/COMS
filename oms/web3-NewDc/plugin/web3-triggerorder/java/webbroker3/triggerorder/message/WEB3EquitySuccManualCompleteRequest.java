head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.46.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquitySuccManualCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�������蓮�����������N�G�X�g(WEB3EquitySuccManualCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@鰐V(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����A�������蓮�����������N�G�X�g)<BR>
 * �����A�������蓮�����������N�G�X�g<BR>
 * 
 * @@author 鰐V
 * @@version 1.0
 */
public class WEB3EquitySuccManualCompleteRequest extends WEB3EquityManualCompleteRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_succ_manual_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603071345L;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F488920271
     */
    public WEB3EquitySuccManualCompleteRequest() 
    {
     
    }
    
    /**
     * (createResponse�̎���)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3EquityManualCompleteResponse();        
    }
    
    
}
@
