head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLProductInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : SÛÁ¿õîñNX(WEB3SLProductInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 £«F (u) VKì¬ f760
Revision History : 2007/11/08 gEN| (u) f822
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (SÛÁ¿o^îñ)<BR>
 * SÛÁ¿o^îñ<BR>
 *
 * @@author £«F
 * @@version 1.0
 */
public class WEB3SLProductInfoUnit extends Message
{
    /**
     * (Á¿ID)<BR>
     * Á¿ID<BR>
     */
    public long productId;

    /**
     * (Á¿R[h)<BR>
     * Á¿R[h<BR>
     */
    public String productCode;

    /**
     * (Á¿^Cv)<BR>
     * Á¿^Cv<BR>
     * <BR>
     * 0F»Ì¼<BR>
     * 1F®<BR>
     * 2FÂ<BR>
     * 3FMõ<BR>
     * 4FO<BR>
     * 5F»à<BR>
     * 6Fæ¨IvV<BR>
     * 7FÝÏ<BR>
     */
    public String productType;

    /**
     * (Á¿¼)<BR>
     * Á¿¼<BR>
     */
    public String productName;

    /**
     * (Kiæª)<BR>
     * Kiæª<BR>
     * <BR>
     * 0FsKi<BR>
     * 1FKi<BR>
     */
    public String qualifiedDiv;

    /**
     * (|Ú)<BR>
     * |Ú<BR>
     */
    public String weight;

    /**
     * (KpúÔFrom)<BR>
     * KpúÔFrom<BR>
     */
    public Date targetPeriodFrom;

    /**
     * (KpúÔTo)<BR>
     * KpúÔTo<BR>
     */
    public Date targetPeriodTo;

    /**
     * (R)<BR>
     * R<BR>
     */
    public String reason;

    /**
     * (SÛÁ¿o^îñ)<BR>
     * RXgN^<BR>
     * @@roseuid 46DBBB800130
     */
    public WEB3SLProductInfoUnit()
    {

    }
}
@
