head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPIPOHostAcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPIPOHostAcceptStatusDef.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/24 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * IPO申告注文　@受付状態　@定数定義インタフェイス
 * （まだweb3-ipoにて未定義のため余力実装用に定義)
 * @@author kazumi HORINO
 *
 */
public interface WEB3TPIPOHostAcceptStatusDef {
    
    /**
     * 0：DEFAULT（初期値）　@
     */     
    public final static String DEFAULT = "0"; 
    
    /**
     * 1：SONAR送信済　@
     */
    public final static String ACCEPTED = "1"; 
    
    
    /**
     * 2：SONAR反映済み                                                                       
     */
    public final static String PROCESSED = "2"; 
             
}
@
