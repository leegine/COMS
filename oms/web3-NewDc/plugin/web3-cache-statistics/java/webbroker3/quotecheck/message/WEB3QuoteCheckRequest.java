head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���N�G�X�g(WEB3QuoteCheckRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03 �� (FLJ)�V�K�쐬
 */

package webbroker3.quotecheck.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i���N�G�X�g�j�B<br>
 * <br>
 * ���N�G�X�g
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3QuoteCheckRequest
    extends WEB3GenRequest
{

    /**
     * TAGNAME<BR>
     */
    public static final String TAGNAME = "request"; 
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "quote_check";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902030000L;
    
    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3QuoteCheckRequest()
    {
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3QuoteCheckResponse();
    }
}
@
