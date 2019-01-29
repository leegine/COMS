head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityEstimatedPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : ŠTZ‘ã‹àŒvZŒ‹‰Ê(WEB3EquityEstimatedPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/14 “‚«•ô@@V‹Kì¬ iƒ‚ƒfƒ‹jNo.1026
*/

package webbroker3.equity;

/**
 * (ŠTZ‘ã‹àŒvZŒ‹‰Ê)<BR>
 *
 * @@author “‚«•ô
 * @@version 1.0
 */
public interface WEB3EquityEstimatedPrice
{
    /**
     * (getŒvZ’P‰¿) <BR>
     * ŒvZ’P‰¿‚ğæ“¾‚·‚éB<BR>
     */
    public double getCalcUnitPrice();

    /**
     * (getŠTZó“n‘ã‹à)<BR>
     * ŠTZó“n‘ã‹à‚ğæ“¾‚·‚éB<BR>
     */
    public double getEstimateDeliveryAmount();
}
@
