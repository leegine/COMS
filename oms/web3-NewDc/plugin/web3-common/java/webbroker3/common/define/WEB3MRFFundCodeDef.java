head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MRFFundCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MRFFundCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * ＭＲＦ設定会社　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3MRFFundCodeDef
{
    /**
     * 1 : 大和
     */
    public static final String DAIWA = "1";

    /**
     * 2 : 野村
     */
    public static final String NOMURA = "2";

    /**
     * 3 : パートナーズ
     */
    public static final String PARTNER = "3";

    /**
     * 4 : 東京三菱
     */
    public static final String TOKYO_MITSUBISHI = "4";

}
@
