head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.44.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p��������V�K�����̓��N�G�X�g�N���X(WEB3SuccMarginOpenChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeInputRequest;

/**
 * (�i�A���j�M�p��������V�K�����̓��N�G�X�g�N���X)<BR>
 * �i�A���j�M�p��������V�K�����̓��N�G�X�g�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3SuccMarginOpenChangeInputRequest extends WEB3MarginOpenMarginChangeInputRequest 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenChangeInput";
    
    /**
     * @@roseuid 4369CBEC0177
     */
    public WEB3SuccMarginOpenChangeInputRequest() 
    {
     
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginOpenChangeInputResponse(this);
    }
}
@
