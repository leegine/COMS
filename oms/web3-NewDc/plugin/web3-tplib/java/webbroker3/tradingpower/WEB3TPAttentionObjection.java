head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAttentionObjection.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ΤΟΣΆΎ(WEB3TPAttentionObjection.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) VKμ¬
*/

package webbroker3.tradingpower;

import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (ΤΟΣΆΎ)
 */
public class WEB3TPAttentionObjection
{

    /**
     * (ΣΆΎ\¦ζͺ)<BR>
     * <BR>
     * [ΤΟOόΰΏF or ~AΤΟγόΰΏF~]<BR>
     * @@EΣΆΎ\¦ζͺΣΆΎ\¦ζͺDef.ΣΆΎ\¦³΅<BR>
     * <BR>
     * [ΤΟOόΰΏFAΤΟγόΰΏF]<BR>
     * @@EΣΆΎ\¦ζͺΣΆΎ\¦ζͺDef.ΣΆΎ\¦Lθ(ΤΟOόΰΏLθ)<BR>
     * <BR>
     * [ΤΟOόΰΏF~AΤΟγόΰΏF]<BR>
     * @@EΣΆΎ\¦ζͺΣΆΎ\¦ζͺDef.ΣΆΎ\¦Lθ(ΤΟOόΰΏ³΅)<BR>
     */
    public String attentionObjectionType;

    /**
     * (όΰΏz)<BR>
     * <BR>
     * [ΤΟγόΰΏF~]<BR>
     * @@EόΰΏz0<BR>
     * <BR>
     * [ΤΟγόΰΏF]<BR>
     * @@EόΰΏz(ΌίΜ)ΤΟγόΰΏz<BR>
     */
    public double demandAmount;

    /**
     * @@roseuid 41E384290161
     */
    public WEB3TPAttentionObjection()
    {

    }

    /**
     * ±ΜIuWFNgΜΆρ\»πΤ·B<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("attentionObjectionType", this.attentionObjectionType)
            .append("demandAmount", this.demandAmount)
            .toString();
    }
}
@
