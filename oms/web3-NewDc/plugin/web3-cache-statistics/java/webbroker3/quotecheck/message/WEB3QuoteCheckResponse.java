head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���X�|���X�N���X(WEB3QuoteCheckResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03 �� (FLJ)�V�K�쐬
 */

package webbroker3.quotecheck.message;

import webbroker3.common.message.*;

/**
 * �i���X�|���X�N���X�X�N���X�j�B<br>
 * <br>
 * ���X�|���X�N���X
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3QuoteCheckResponse
    extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "quote_check";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902030000L;
    
    /**
     * ����<BR>
     */
    public int count;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3QuoteCheckResponse()
    {
    }

}
@
