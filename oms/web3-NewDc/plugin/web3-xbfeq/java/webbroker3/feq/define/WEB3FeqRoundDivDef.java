head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqRoundDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算結果丸め方式定義インタフェイス(WEB3FeqRoundDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/06 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 計算結果丸め方式定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqRoundDivDef
{
    /**
     * 0：四捨五入
     */
    public static final String ROUND = "0";
    
    /**
     * 1：切捨
     */
    public static final String CUTOFF = "1";

    /**
     * 2：切上
     */
    public static final String CUT_UP = "2";
}
@
