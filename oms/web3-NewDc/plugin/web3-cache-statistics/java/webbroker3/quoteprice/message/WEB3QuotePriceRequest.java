head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���N�G�X�g(WEB3QuotePriceRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/06 �� (FLJ)�V�K�쐬
 */

package webbroker3.quoteprice.message;

import webbroker3.common.message.*;
import webbroker3.util.*;

/**
 * �i���N�G�X�g�j�B<br>
 * <br>
 * ���N�G�X�g
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3QuotePriceRequest
    extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuotePriceRequest.class);

    /**
     * TAGNAME<BR>
     */
    public static final String TAGNAME = "request"; 
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "quote_price_save";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602060000L;
    
    /**
     * Quote Table name<BR>
     */
    public String quote_table;

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3QuotePriceRequest()
    {
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3QuotePriceResponse();
    }
}
@
