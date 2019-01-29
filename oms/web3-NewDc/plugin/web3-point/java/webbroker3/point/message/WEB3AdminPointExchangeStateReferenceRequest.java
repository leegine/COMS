head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointExchangeStateReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント交換一覧リクエスト(WEB3AdminPointExchangeStateReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.point.define.WEB3AcceptDivDef;
import webbroker3.point.define.WEB3PointKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (ポイント交換一覧リクエスト)<BR>
 * ポイント交換一覧リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointExchangeStateReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPointExchangeStateReferenceRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_exchangeStateReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290091L;
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列<BR>
     */
    public String[] branchCode;
    
    /**
     * (受付区分)<BR>
     * 受付区分<BR>
     * <BR>
     * 0： 受付未済<BR>
     * 1： 受付済み<BR>
     * 2：全て表示<BR>
     */
    public String acceptDiv;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;
    
    /**
     * (ソートキー)<BR>
     * ポイント交換一覧ソートキーの配列<BR>
     */
    public WEB3PointSortKey[] sortKeys;
    
    /**
     * @@roseuid 41D1254A030D
     */
    public WEB3AdminPointExchangeStateReferenceRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     *    this.部店コード = null or<BR>
     *    this.部店コード.length() != 3<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * ２）受付区分<BR>
     * <BR>
     *    this.受付区分 != （0, 1, 2）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01960<BR>
     * ３）要求ページ番号<BR>
     * <BR>
     *    this.要求ページ番号 = null or <BR>
     *    this.要求ページ番号 <= 0 or<BR>
     *    this.要求ページ番号 != 半角数字<BR>
     * <BR>
     *    の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * ４）ページ内表示行数<BR>
     * <BR>
     *    this.ページ内表示行数 = null or <BR>
     *    this.ページ内表示行数 <= 0 or<BR>
     *    this.ページ内表示行数 != 半角数字<BR>
     * <BR>
     *    の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * ５）ソートキー <BR>
     * <BR>
     * ５－１） <BR>
     * <BR>
     *    this.ソートキー = null or<BR>
     *    this.ソートキー.length() = 0 <BR>
     * <BR>
     *    の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * ５－２）ソートキーの要素数分繰り返してチェックを行う。 <BR>
     * <BR>
     * ５－２－１） <BR>
     * <BR>
     *    ソートキー.キー項目 = null <BR>
     * <BR>
     *    の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * ５－２－２） <BR>
     * <BR>
     *    ソートキー.キー項目に以下の項目名以外の値があった場合、例外をスローする。 <BR>
     * <BR>
     *    ・部店コード<BR>
     *    ・顧客コード<BR>
     *    ・景品番号<BR>
     *    ・景品名<BR>
     *    ・申込日時<BR>
     *    ・受付区分<BR>
     *    ・更新日時<BR>
     *    ・受付ユーザ<BR>
     *    ・取消区分<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * ５－２－３） <BR>
     * <BR>
     *    ソートキー.昇順／降順 = null <BR>
     * <BR>
     *    の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00087<BR>
     * <BR>
     * ５－２－４） <BR>
     * <BR>
     *    ソートキー.昇順／降順が以下の値以外の場合、例外をスローする。 <BR>
     * <BR>
     *    ・”A：昇順” <BR>
     *    ・”D：降順” <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00088<BR>
     * @@roseuid 418F49A702DC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）部店コード
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            String l_strMessage = "部店コード = null !";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (WEB3StringTypeUtility.getByteLength(this.branchCode[i]) != 3)
            {
                String l_strMessage = "部店コードerror! " + this.branchCode;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
        }

        // ２）受付区分 
        if (!WEB3AcceptDivDef.NO_FINISHED.equals(this.acceptDiv) 
            && !WEB3AcceptDivDef.FINISHED.equals(this.acceptDiv)
            && !WEB3AcceptDivDef.ALL.equals(this.acceptDiv))
        {
            String l_strMessage = "受付区分[" + this.acceptDiv + "]エラー! 入力された受付区分が”0： 受付未済”、”1： 受付済み”、”2：全て表示”以外です。";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01960,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        // ３）要求ページ番号 
        if (this.pageIndex == null || "".equals(this.pageIndex.trim()))
        {
            String l_strMessage = "要求ページ番号error! " + this.pageIndex;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            String l_strMessage = "要求ページ番号error! " + this.pageIndex;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            String l_strMessage = "要求ページ番号error! " + this.pageIndex;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        // ４）ページ内表示行数
        if (this.pageSize == null || "".equals(this.pageSize.trim()))
        {
            String l_strMessage = "ページ内表示行数error! " + this.pageSize;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            String l_strMessage = "ページ内表示行数error! " + this.pageSize;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        if (Integer.parseInt(this.pageSize) < 0)
        {
            String l_strMessage = "ページ内表示行数error! " + this.pageSize;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        // ５）ソートキー 
        // ５－１） 
        if (this.sortKeys == null)
        {
            String l_strMessage = "ソートキーerror! " + this.sortKeys;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        if (this.sortKeys.length == 0)
        {
            String l_strMessage = "ソートキーerror! " + this.sortKeys;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        // ５－２）ソートキーの要素数分繰り返してチェックを行う。
        int l_intSortKeyCount = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeyCount; i++)
        { 
            // ５－２－１） 
            if (this.sortKeys[i].keyItem == null || "".equals(this.sortKeys[i].keyItem.trim()))
            {
                String l_strMessage = "ソートキー.キー項目error! " + this.sortKeys[i].keyItem;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }

            // ５－２－２） 
            if (!WEB3PointKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.APPLY_ACCEPT_DIV.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.APPLY_ACCEPT_USER.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.APPLY_CANCEL_DIV.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.APPLY_TIMESTAMP.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.PREMINUM_NO.equals(this.sortKeys[i].keyItem)
                && !WEB3PointKeyItemDef.PREMIUM_NAME.equals(this.sortKeys[i].keyItem))
            {
                String l_strMessage = "ソートキー.キー項目error! " + this.sortKeys[i].keyItem;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
            
            // ５－２－３） 
            if (this.sortKeys[i].ascDesc == null || "".equals(this.sortKeys[i].ascDesc.trim()))
            {
                String l_strMessage = "ソートキー.昇順／降順error! " + this.sortKeys[i].ascDesc;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }

            // ５－２－４） 
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) 
                && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                String l_strMessage = "ソートキー.昇順／降順error! " + this.sortKeys[i].ascDesc;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254A033C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointExchangeStateReferenceResponse(this);
    }
}
@
