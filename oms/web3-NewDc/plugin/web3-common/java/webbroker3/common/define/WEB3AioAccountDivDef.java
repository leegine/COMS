head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioAccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioAccountDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 屈陽 (中訊) 新規作成
Revesion History : 2006/02/16 凌建平 (中訊) 入出金仕様変更管理台帳No.79(ＤＢレイアウト)
*/
package webbroker3.common.define;

/**
 * 口座区分　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */
public interface WEB3AioAccountDivDef
{
    /**
     * 1:開設済　@
     */
    public static final String OPEN_COMPLETE = "1";

    /**
     * 2：振替停止
     */
    public static final String TRANSFER_STOP = "2";
    
    /**
     * 9:抹消
     */
    public static final String DELETE = "9";
}
@
