head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderDivTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投注文種別定数定義クラス(WEB3RuitoOrderDivTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 鈴木美由紀（SRA)新規作成
*/
package webbroker3.xbruito.define;

/**
 * 注文種別　@定数定義インタフェイス
 *
 * @@author 鈴木美由紀（SRA)
 * @@version 1.0
 */
public interface WEB3RuitoOrderDivTypeDef
{
	/**
	 * 1 : 買付
	 */
	public static final String BUY = "1";
	
    /**
     * 2 : 全部解約
     */
    public static final String ALL_SELL = "2";
    
    /**
     * 3 : 一部解約
     */
    public static final String PARTIALLY_SELL = "3";
}
@
