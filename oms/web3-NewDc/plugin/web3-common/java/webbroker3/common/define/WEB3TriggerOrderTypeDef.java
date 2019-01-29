head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TriggerOrderTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TriggerOrderTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/13 凌建平(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 条件注文種別 定数定義インタフェイス
 *                                                                     
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3TriggerOrderTypeDef
{
    /**
     * 1：連続注文
     */
    public static final String SUCC = "1";
    
    /**
     * 2：OCO注文
     */
	public static final String OCO = "2";
	
	/**
	 * 3：IFD注文
	 */
	public static final String IFD = "3";
	
	/**
	 * 4：逆指値注文
	 */
	public static final String STOP = "4";

    /**
     * 5：W指値注文
     */
    public static final String W_LlIMIT = "5";
}
@
