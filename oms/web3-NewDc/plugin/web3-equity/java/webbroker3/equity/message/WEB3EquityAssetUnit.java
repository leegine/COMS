head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAssetUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : »¨®ÛLYêÆïÛLY(WEB3EquityAssetUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 ACÇ(u) VKì¬
                 : 2006/08/29 £«F(u) f 972
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * i»¨®ÛLYêÆïÛLYjB<BR>
 * <BR>
 * »¨®ÛLYêÆïÛLY@@f[^NX
 * @@version 1.0
 */
public class WEB3EquityAssetUnit extends Message
{

    /**
     * ÛLYIDiñ\¦Új<BR>
     */
    public String id;

    /**
     * (Á¿R[h)
     */
    public String productCode;

    /**
     * (Á¿¼)
     */
    public String productName;

    /**
     * (ûÀæª)<BR>
     * 0FêÊ@@1FÁè@@5FXgbNIvV<BR>
     */
    public String taxType;

    /**
     * (tÂ\)
     */
    public String sellPossQuantity;

    /**
     * (¶)
     */
    public String orderedQuantity;

    /**
     * (sêR[hê)<BR>
     * Á¿Aæµsêê<BR>
     */
    public String[] marketList;

    /**
     * (tÂ\tO)<BR>
     * trueFtÂ\@@@@falseFtsÂ<BR>
     */
    public boolean sellPossFlag;

    /**
     * @@roseuid 409F5F4903C1
     */
    public WEB3EquityAssetUnit()
    {

    }
}
@
