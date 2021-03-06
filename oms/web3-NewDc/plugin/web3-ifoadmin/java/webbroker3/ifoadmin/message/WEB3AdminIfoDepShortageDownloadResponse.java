head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・証拠金不足状況ダウンロードレスポンス(WEB3AdminIfoDepShortageDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27　@劉剣(中訊) 新規作成 モデルNo.004
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (管理者・証拠金不足状況ダウンロードレスポンス)<BR>
 * 管理者・証拠金不足状況ダウンロードレスポンスクラス<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageDownloadResponse extends WEB3AdminIfoDepShortageReferenceResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_ifo_dep_shortage_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902271349L;

   /**
    * (ダウンロードファ@イル)<BR>
    * ダウンロードファ@イル <BR>
    * <BR>
    * ※ CSVファ@イル行の配列 <BR>
    */
   public String[] downloadFile;

   /**
    * @@roseuid 49A76E5201E4
    */
   public WEB3AdminIfoDepShortageDownloadResponse()
   {

   }

   /**
    * コンストラクタ。<BR>
    * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
    * @@param l_request - リクエストオブジェクト
    */
   public WEB3AdminIfoDepShortageDownloadResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   }
}@
