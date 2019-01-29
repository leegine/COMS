head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORProductNameSetUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文約定照会銘柄ID名称 (WEB3AdminORProductNameSetUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （管理者注文約定照会銘柄ID名称）<BR>
 * <BR>
 * 管理者注文約定照会銘柄ID名称クラス<BR>
 * <BR>
 * WEB3AdminORProductNameSetUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORProductNameSetUnit extends Message
{
    /**
     * (銘柄ID)<BR>
     * <BR>
     * 銘柄ID<BR>
     * <BR>
     * productId<BR>
     * <BR
     */
    public String productId;

    /**
     * (銘柄名)<BR>
     * <BR>
     * 銘柄名<BR>
     * <BR>
     * productName<BR>
     * <BR>
     */
    public String productName;

    /**
     * @@roseuid 4212FF490344
     */
    public WEB3AdminORProductNameSetUnit()
    {

    }
}
@
