head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�������Ɖ���(WEB3FutureExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ���Q (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (�����w���敨�������Ɖ���)<BR>
 * �����w���敨�������Ɖ���N���X
 *                                                                     
 * @@author ���Q
 * @@version 1.0
 */
public class WEB3FuturesExecuteUnit extends Message
{

    /**
     * (������)
     */
    public Date executionTimestamp;

    /**
     * (��萔��)
     */
    public String execQuantity;

    /**
     * (���P��)
     */
    public String execPrice;

    /**
     * @@roseuid 40C0754B037A
     */
    public WEB3FuturesExecuteUnit()
    {

    }
}
@
