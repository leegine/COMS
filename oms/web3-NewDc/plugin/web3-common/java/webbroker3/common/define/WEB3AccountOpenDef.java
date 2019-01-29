head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountOpenDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設(WEB3AccountOpenDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
Revesion History : 2006/03/17 凌建平(中訊) 共通仕様変更・ＤＢレイアウト355
*/

package webbroker3.common.define;

/**
 * 口座開設 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3AccountOpenDef
{

    /**
     * 0：未開設
     */
    public final static String NOT_OPEN  = "0";

    /**
     * 1：開設
     */
    public final static String OPEN  = "1";
    
    /**
     * 2：口座抹消
     */
    public final static String DELETED  = "2";

    /**
     * 3：振替停止
     */
    public final static String TRANSFER_STOP  = "3";
}@
