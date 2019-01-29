head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccountOpenStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設状況区分 定数定義インタフェイス(WEB3AccountOpenStatusDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 張宝楠 (中訊) 新規作成
                 : 2006/06/08 黄建(中訊) 仕様変更・モデル050
*/
package webbroker3.accountopen.define;

/**
 * 口座開設状況区分 定数定義インタフェイス
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3AccountOpenStatusDivDef
{
    /**
     * 0：　@DEFAULT（未開設）
     */
    public static final String DEFAULT = "0";
    
    /**
     * 1：　@開設中
     */
    public static final String OPENING = "1";
    
    /**
     * 2：　@エラー発生 
     */
    public static final String ERROR = "2";
    
    /**
     * 3：　@開設済 
     */
    public static final String OPEN_COMPLETE = "3";
    
    /**
     * 4：  審査中 
     */
    public static final String JUDGEING = "4";
    
    /**
     * 5：  却下 
     */
    public static final String REJECTION = "5";
}
@
