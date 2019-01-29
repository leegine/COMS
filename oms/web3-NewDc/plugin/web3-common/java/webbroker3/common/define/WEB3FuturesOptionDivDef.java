head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FuturesOptionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3FuturesOptionDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/

package webbroker3.common.define;

/**
 * 先物／オプション区分　@定数定義インタフェイス
 *
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3FuturesOptionDivDef
{
    
    /**
     * 0 : DEFAULT 
     */
    public static final String DEFAULT = "0";

    /**
     * 1 : 先物 
     */
    public static final String FUTURES = "1";

    /**
     * 2 : オプション 
     */
    public static final String OPTION = "2";
           
}
@
