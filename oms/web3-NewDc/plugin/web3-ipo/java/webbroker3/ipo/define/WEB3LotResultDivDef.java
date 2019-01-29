head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3LotResultDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 抽選結果区分定数定義インタフェイス(WEB3LotResultDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/24 斉麟 新規作成
*/
package webbroker3.ipo.define;

/**
 * 抽選結果区分定数定義インタフェイス
 *
 * @@author 斉麟
 * @@version 1.0
 */
public interface WEB3LotResultDivDef
{
    /**
     * 0：DEFAULT（未抽選）　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1：当選　@ 　@　@　@ 　@　@
     */
    public final static String ELECTION = "1";
    
    /**
     * 2：補欠　@ 　@　@　@ 　@　@
     */
    public final static String SUPPLEMENT = "2";
    
    /**
     * 3：落選　@　@　@　@　@　@
     */
    public final static String DEFEAT = "3";
    
    /**
     * 21：補欠当選　@　@　@　@　@　@
     */
    public final static String SUPPLEMENT_ELECTION = "21";
    
    /**
     * 23：補欠落選　@　@　@　@　@　@
     */
    public final static String SUPPLEMENT_DEFEAT = "23";
    
    
}
@
