head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報口座開設区分(WEB3AccInfoAccountOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * お客様情報口座開設区分
 *                                                                     
 * @@author 齊珂
 * @@version 1.0
 */
public interface WEB3AccInfoAccountOpenDivDef
{
    /**
     * 1:総合口座
     */
    public static final String MULTIPLE = "1";
    
    /**
     * 2:信用口座
     */
    public static final String MARGIN = "2";
    
    /**
     * 3:先物OP口座
     */
    public static final String IFO = "3";
      
    /**
     * 4:FX口座
     */
    public static final String FX = "4";

    /**
     * 5:中国株口座
     */
    public static final String CHINESE_EQUITY = "5"; 
}
@
