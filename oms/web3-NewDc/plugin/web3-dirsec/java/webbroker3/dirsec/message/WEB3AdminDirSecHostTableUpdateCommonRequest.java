head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キューテーブル更新共通リクエスト(WEB3AdminDirSecHostTableUpdateCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 奚翔(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (キューテーブル更新共通リクエスト)<BR>
 * キューテーブル更新共通リクエストクラス。<BR>
 * 
 * @@author 奚翔
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableUpdateCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableUpdateCommonRequest.class);

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (テーブル名)<BR>
     * テーブル名（和名）。
     */
    public String tableJpnName;
    
    /**
     * (tableName)<BR>
     * テーブル物理名。
     */
    public String tableName;
    
    /**
     * @@roseuid 442A1C8803D8
     */
    public WEB3AdminDirSecHostTableUpdateCommonRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR> 
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）テーブル名チェック <BR>
     * 　@this.テーブル名 == nullの場合、 <BR>
     * 　@　@　@　@　@「テーブル名がnull」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02424<BR>
     * <BR>
     * ２）テーブル物理名<BR>
     * 　@this.テーブル物理名 == nullの場合、 <BR>
     * 　@　@　@　@　@「テーブル物理名がnull」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02425<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4416502B0168
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）テーブル名チェック 
        //　@this.テーブル名 == nullの場合、 
        //　@　@　@　@　@「テーブル名がnull」の例外をスローする。 
        //        class: WEB3BusinessLayerException
        //        tag:   BUSINESS_ERROR_02424
        if (this.tableJpnName == null)
        {
            log.debug("テーブル名がnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02424,
                this.getClass().getName() + STR_METHOD_NAME,
                "テーブル名がnullです。");
        }
        
        //２）テーブル物理名
        //　@this.テーブル物理名 == nullの場合、 
        //　@　@　@　@　@「テーブル物理名がnull」の例外をスローする。
        //        class: WEB3BusinessLayerException
        //        tag:   BUSINESS_ERROR_02425
        if (this.tableName == null)
        {
            log.debug("テーブル物理名がnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02425,
                this.getClass().getName() + STR_METHOD_NAME,
                "テーブル物理名がnullです。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminDirSecHostTableUpdateCommonResponse(this);
    }
}
@
