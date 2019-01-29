head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiUploadStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード処理状態区分 定数定義インタフェイス(WEB3SrvRegiUploadStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 武波 (中訊) 新規作成 モデル No.337
*/
package webbroker3.srvregi.define;

/**
 * (アップロード処理状態区分 定数定義インタフェイス)<BR>
 * アップロード処理状態区分 定数定義インタフェイス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3SrvRegiUploadStateDivDef
{
    /**
     * 0:アップロード待ち
     */
    public static final String DEFAULT = "0";

    /**
     * 1:アップロード中
     */
    public static final String UPLOADING = "1";

    /**
     * 2:アップロード済
     */
    public static final String UPLOAD_COMPLETE = "2";
}
@
