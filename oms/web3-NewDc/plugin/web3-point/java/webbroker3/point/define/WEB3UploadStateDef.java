head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.03.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3UploadStateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード処理状態区分(WEB3UploadStateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 鄭海良(sinocom) 新規作成
*/
package webbroker3.point.define;

/**
 * アップロード処理状態区分
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3UploadStateDef
{

    /**
     * 0:アップロード待ち　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1:アップロード中　@
     */
    public final static String UPLOADING = "1";

    /**
     * 2:アップロード済　@
     */
    public final static String UPLOAD_COMPLETE = "2";
    
}
@
