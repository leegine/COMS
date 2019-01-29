head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3LotDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 抽選区分定数定義インタフェイス(WEB3LotDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 鄭海良(sinocom) 新規作成
*/
package webbroker3.ipo.define;

/**
 * 抽選区分 定数定義インタフェイス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3LotDivDef
{

    /**
     * 1：新規抽選　@　@　@　@ 　@　@
     */
    public final static String OPEN_LOTTERY = "1";
    
     /**
     * 2：繰上抽選　@　@　@
     */
    public final static String ADVANCED_LOTTERY = "2";
}@
