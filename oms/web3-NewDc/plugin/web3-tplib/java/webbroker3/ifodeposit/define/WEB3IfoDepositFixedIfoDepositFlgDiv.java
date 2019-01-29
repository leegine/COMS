head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositFixedIfoDepositFlgDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金不足確定FLAG　@定数定義インターフェース(WEB3IfoDepositFixedIfoDepositFlgDiv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/03 nakazato(ACT) 新規作成
*/

package webbroker3.ifodeposit.define;

/**
 * 証拠金不足確定FLAG　@定数定義インターフェース
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IfoDepositFixedIfoDepositFlgDiv
{
    
    /**
     * 0：未確定 <BR>
     */
    public static final String NOT_FIXED = "0";
    
    /**
     * 1：確定<BR>
     */
    public static final String FIXED = "1";

}
@
