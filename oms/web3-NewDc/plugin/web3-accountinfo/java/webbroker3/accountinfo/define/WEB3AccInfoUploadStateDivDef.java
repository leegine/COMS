head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoUploadStateDivDef.java;


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
Revesion History : 2004/11/19 カク寛新 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * アップロード処理状態区分 定数定義インタフェイス
 * 
 * @@author カク寛新 (中訊)
 * @@version 1.0
 */
public interface WEB3AccInfoUploadStateDivDef
{
    /**
     * 1：アップロード中　@　@
     */
    public final static String UPOAD_CONFIRMING = "1";
    
    /**
     * 2：アップロード済
     */
    public final static String UPLOAD_COMPLETE = "2"; 
}@
