head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K�����������I�����N�G�X�g(WEB3MarginProductSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����V�K�����������I�����N�G�X�g�j�B<br>
 * <br>
 * �M�p����V�K�����������I�����N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginProductSelectRequest extends WEB3GenRequest 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_productSelect";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * @@roseuid 4140477E0291
     */
    public WEB3MarginProductSelectRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140477E029B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginProductSelectResponse(this);
    }
}
@
