head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBalanceReferenceTotalRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����c�����v���N�G�X�g(WEB3MstkBalanceReferenceTotalRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �i�����~�j�����c���Ɖ�c�����v���N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j�����c���Ɖ�c�����v���N�G�X�g�N���X<BR>
 */
public class WEB3MstkBalanceReferenceTotalRequest extends WEB3GenRequest 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_balance_reference_total";

    /**
     * @@roseuid 4206CC990134<BR>
     */
    public WEB3MstkBalanceReferenceTotalRequest() 
    {
     
    }
    
    public WEB3GenResponse createResponse()
    {
        return new WEB3MstkBalanceReferenceTotalResponse(this);
    }
}
@
