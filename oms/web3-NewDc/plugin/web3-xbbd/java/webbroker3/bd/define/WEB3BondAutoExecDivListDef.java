head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecDivListDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 自動約定枠区分リスト(WEB3BondAutoExecDivListDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.231,モデルNo.243
*/
package webbroker3.bd.define;

/**
 * (自動約定枠区分リスト)<BR>
 * 自動約定枠区分リスト<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3BondAutoExecDivListDef
{
    /**
     * 0：非自動約定
     */
    public static final String ZERO = "0";

    /**
     * 1：自動約定
     */
    public static final String ONE = "1";
}
@
