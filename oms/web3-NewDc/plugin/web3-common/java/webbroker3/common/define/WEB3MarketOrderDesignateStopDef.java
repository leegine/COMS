head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketOrderDesignateStopDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 制度・一般信用売買停止状態　@定数定義インタフェイス(WEB3MarketOrderDesignateStopDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 SRA坂上 新規作成
*/
package webbroker3.common.define;

/**
 * 制度・一般信用売買停止状態　@定数定義インタフェイス。
 *
 * @@author SRA坂上
 * @@version 1.0
 */
public interface WEB3MarketOrderDesignateStopDef
{

    /**
     * 0:停止でない
     */
    public static final String ACTIVE = "0";

    /**
     * 1:停止中
     */
    public static final String STOP = "1";
    
}
@
