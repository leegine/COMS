head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoUploadStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード処理状態区分(WEB3PvInfoUploadStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 李弘毅(sinocom) 新規作成
*/
package webbroker3.pvinfo.define;

/**
 * アップロード処理状態区分
 *
 * @@author 李弘毅
 * @@version 1.0
 */
public interface WEB3PvInfoUploadStateDivDef
{
    /**
     * 0：   アップロード待ち
     */
    public final static String UPLOAD_STATUS_WAIT = "0";
    
    /**
     * 1：アップロード中
     */
    public final static String UPLOAD_STATUS_PROCESS   = "1";  

    /**
     * 2：アップロード済
     */
    public final static String UPLOAD_STATUS_COMPLETE  = "2" ;  
}
 @
