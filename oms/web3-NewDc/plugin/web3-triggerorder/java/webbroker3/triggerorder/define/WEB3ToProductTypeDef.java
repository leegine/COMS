head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToProductTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄タイプ定義インタフェイス(WEB3ToProductTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 余新敏(中訊)　@新規作成
*/

package webbroker3.triggerorder.define;

/**
 * 銘柄タイプ定義インタフェイス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3ToProductTypeDef
{

    /**
     * 1：　@株式
     */
    public static final String EQUITY = "1";
    
    /**
     * 6：　@先物オプション
     */
    public static final String FUTURE_OPTION = "6";
}
@
