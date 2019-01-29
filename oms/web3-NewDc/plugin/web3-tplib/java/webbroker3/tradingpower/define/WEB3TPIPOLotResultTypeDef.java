head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPIPOLotResultTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPIPOLotResultTypeDef.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/24 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * IPO 当選結果　@定数定義インタフェイス
 * （まだweb3-ipoにて未定義のため余力実装用に定義)
 * @@author kazumi HORINO
 *
 */
public interface WEB3TPIPOLotResultTypeDef 
{
    /**
     * 0：DEFAULT（未抽選） 
     */
    public final static String DEFAULT = "0"; 

    /**
     * 1：当選　@
     */
    public final static String WIN = "1"; 
       
     /**
      * 2：補欠　@
      */ 
    public final static String SUBSTITUTE = "2"; 

    /**
     * 3：落選
     */                                                                       
    public final static String LOSE = "3"; 

}
@
