head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiUploadStateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理状態区分(WEB3SrvRegiUploadStateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 鄭海良(sinocom) 新規作成
*/
package webbroker3.srvregi.define;

/**
 * 処理状態区分
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3SrvRegiUploadStateDef
{

    /**
     * 0:処理中　@
     */
    public final static String UPLOADING = "0";

    /**
     * 1:アップロード済　@
     */
    public final static String UPLOAD_COMPLETE = "1";

    /**
     * 2:アップロードエラー　@
     */
    public final static String UPLOAD_ERROR = "2";
    
}
@
