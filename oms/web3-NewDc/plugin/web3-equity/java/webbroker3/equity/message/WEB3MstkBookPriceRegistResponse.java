head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBookPriceRegistResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�����~�j�����뉿�P���o�^���X�|���X(WEB3MstkBookPriceRegistResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �i�����~�j�����뉿�P���o�^���X�|���X�j�B<BR>
 * <BR>
 * �����~�j�����뉿�P���o�^���X�|���X�N���X<BR>
 */
public class WEB3MstkBookPriceRegistResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502141000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_book_price_regist";
    
    /**
     * @@roseuid 4206CF450376
     */
    public WEB3MstkBookPriceRegistResponse() 
    {
     
    }
    
    public WEB3MstkBookPriceRegistResponse(WEB3MstkBookPriceRegistRequest l_request)
    {
        super(l_request);
    }
}
@
