head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookPriceConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������뉿�P���o�^�m�F���X�|���X(WEB3FeqBookPriceConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/16 �đo�g(���u) �V�K�쐬 ���f��No.373
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������뉿�P���o�^�m�F���X�|���X)<BR>
 * �O�������뉿�P���o�^�m�F���X�|���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3FeqBookPriceConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "feq_book_price_confirm";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200801151256L;

    public WEB3FeqBookPriceConfirmResponse()
    {

    }

    /**
     * (�ύX��T�Z�뉿�P��)<BR>
     * �ύX��T�Z�뉿�P��<BR>
     */
    public String aftBookPrice;

    public WEB3FeqBookPriceConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
