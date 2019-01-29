head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccountOpenMailFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード処理状態区分 定数定義インタフェイス(WEB3AccInfoUploadStateDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/6/1４ 艾興 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 送信フラグ 定数定義インタフェイス
 * 
 * @@author 艾興 (中訊)
 * @@version 1.0
 */
public interface WEB3AccountOpenMailFlagDef
{
    /**
     * 1：要　@
     */
    public final static String sendFlag = "1";
    
    /**
     * 0：不要
     */
    public final static String unSendFlag = "0"; 
    
    /**
     * null：指定無し
     */
    public final static String other = null; 
}@
