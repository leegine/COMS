head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityStopManualConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����t�w�l�����蓮�����m�F���N�G�X�g(WEB3EquityStopManualConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@鰐V(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����t�w�l�����蓮�����m�F���N�G�X�g)<BR>
 * �����t�w�l�����蓮�����m�F���N�G�X�g<BR>
 * 
 * @@author 鰐V
 * @@version 1.0
 */
public class WEB3EquityStopManualConfirmRequest extends WEB3EquityManualConfirmRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_stop_manual_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603071345L;

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F488920196
     */
    public WEB3EquityStopManualConfirmRequest() 
    {
     
    }
    
    /**
     * (createResponse�̎���)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3EquityManualConfirmResponse();        
    }
    
}
@
