head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3UploadStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード処理状態区分(WEB3UploadStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/25 鄭海良(sinocom) 新規作成
*/
package webbroker3.ipo.define;

/**
 * アップロード処理状態区分
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3UploadStateDivDef
{
    /**
     * 0：   アップロード待ち
     */
    public final static String DEFAULT = "0";

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
