head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenUploadStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード処理状態区分(WEB3AccOpenUploadStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/23 武波 (中訊) 新規作成 モデル No.147
*/

package webbroker3.accountopen.define;

/**
 * (アップロード処理状態区分)<BR>
 * アップロード処理状態区分<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AccOpenUploadStateDivDef
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
