head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3informAfterPtsAccOpenDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 変更後申込区分(WEB3informAfterPtsAccOpenDiv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 柴双紅 (中訊) 新規作成
*/

package webbroker3.inform.define;

/**
 * 変更後申込区分<BR>
 * 定数定義インタフェイス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3informAfterPtsAccOpenDiv
{
    /**
     * 0：未開設
     */
    public final static String NOT_OPEN = "0";

    /**
     * 1：開設
     */
    public final static String OPEN = "1";
}
@
