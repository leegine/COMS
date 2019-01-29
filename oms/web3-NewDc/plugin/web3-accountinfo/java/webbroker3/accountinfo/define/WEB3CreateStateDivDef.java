head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.27.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3CreateStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 作成状態区分 定数定義インタフェイス(WEB3CreateStateDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;


/**
 * 作成状態区分 定数定義インタフェイス
 * 取引残高報告書預り証作成状態区分、取引残高報告書計算書作成状態区分
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3CreateStateDivDef
{
    /**
     * 0：不作成
     */
    public final static String NOT_CREATE = "0";
    
    /**
     * 1：作成
     */
    public final static String CREATE = "1";

}
@
