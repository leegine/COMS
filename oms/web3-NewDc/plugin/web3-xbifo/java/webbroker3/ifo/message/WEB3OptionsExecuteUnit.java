head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ΏwIvVΆρθΖορθ(WEB3OptionsExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ηΎs (u) VKμ¬
              001: 2004/07/28 €Ε (u) Ξ ΪΧέv`FbNwE (ϊ{€) 
                   com.fitechlabs.xtrade.kernel.message.Messageπp³B
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (ΏwIvVΆρθΖορθ)<BR>
 * ΏwIvVΆρθΖορθNX<BR>                                                                    
 * @@author ηΎs
 * @@version 1.0
 */
public class WEB3OptionsExecuteUnit extends Message
{

    /**
     * ρθϊ
     */
    public Date executionTimestamp;

    /**
     * ρθΚ
     */
    public String execQuantity;

    /**
     * ρθPΏ
     */
    public String execPrice;

    /**
     * @@roseuid 40C0754B037A
     */
    public WEB3OptionsExecuteUnit()
    {

    }
}
@
