head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投注文照会リクエストクラス(WEB3RuitoOrderReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.define.WEB3RuitoReferenceTypeDef;

/**
 * 累投注文照会リクエスト<BR>
 */
public class WEB3RuitoOrderReferenceRequest extends WEB3GenRequest
{

	/**
	* ログ出力ユーティリティ
	*/
   private static WEB3LogUtility log =
	   WEB3LogUtility.getInstance(WEB3RuitoOrderReferenceRequest.class);
	   	
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_order_reference";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      
	   
    /**
     * 照会区分<BR>
     * 0：注文照会、1：取消一覧(取消可能なもののみ表示)<BR>
     */
    public String referenceType;

    /**
     * ソートキー<BR>
     * 対象項目：ファ@ンド名(銘柄名)、売買区分、注文日時<BR>
     */
    public webbroker3.xbruito.message.WEB3RuitoSortKey[] ruitoSortKeys;

    /**
     * 要求ページ番号<BR>
     * 表示させたいページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;

    /**
     * ページ内表示行数<BR>
     * 1ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922C8D005D
     */
    public WEB3RuitoOrderReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@照会区分チェック<BR>
     * 　@１－１）　@this.照会区分がnullの値である場合、例外をスローする。<BR>
     *              class    : WEB3BusinessLayerException<BR>
     *              tag      : BUSINESS_ERROR_00081<BR>
     * 　@１－２）　@this.照会区分が“0:注文照会”、<BR>
     *               “1:取消一覧(取消可能なもののみ<BR>
     * 　@　@　@　@      表示)”以外である場合、例外をスローする。<BR>
     *              class    : WEB3BusinessLayerException<BR>
     *              tag      : BUSINESS_ERROR_00082<BR>
     * ２）ソートキーチェック<BR>
     * 　@２－１）this.累投ソートキーがnullの値であれば例外をスローする。<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00231<BR>
     * 　@２－２）this.累投ソートキーの要素数が０であれば例外をスローする。<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00232<BR>
     * 　@２－３）this.累投ソートキーの要素数分繰り返してチェックを行う。<BR>
     * 　@　@２－３－１）キー項目がnullの値であれば例外をスローする。<BR>
     *                  class    : WEB3BusinessLayerException<BR>
     *                  tag      : BUSINESS_ERROR_00085<BR>
     * 　@　@２－３－２）キー項目に以下の項目名以外の値が<BR>
     * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
     * 　@　@          ・銘柄名<BR>
     * 　@　@          ・売買区分<BR>
     * 　@          　@・注文時間<BR>
     *                class    : WEB3BusinessLayerException<BR>
     *                tag      : BUSINESS_ERROR_00086<BR>
     * <BR>
     * 　@　@２－３－３）昇順／降順がnullの値であれば例外をスローする。<BR>
     *                  class    : WEB3BusinessLayerException<BR>
     *                  tag      : BUSINESS_ERROR_00087<BR>
     * 　@　@２－３－４）昇順／降順が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@           ・”A：昇順”<BR>
     * 　@　@           　@・”D：降順”<BR>
     *                  class    : WEB3BusinessLayerException<BR>
     *                  tag      : BUSINESS_ERROR_00088<BR>
     * <BR>
     * ３）　@要求ページ番号チェック<BR>
     * 　@３－１）this.要求ページ番号がnullの値であれば例外をスローする。<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00089<BR>
     * 　@３－２）this.要求ページ番号が数字以外の値であれば
     *             例外をスローする。<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00090<BR>
     * ４）　@ページ内表示行数チェック<BR>
     * 　@４－１）this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00091<BR>
     * 　@４－２）this.ページ内表示行数が数字以外の値であれば
     *             例外をスローする。<BR>
     *            class    : WEB3BusinessLayerException<BR>
     *            tag      : BUSINESS_ERROR_00092<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4073678C00A8
     */
    public void validate() throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);
		 	
        //照会区分チェック
        if (WEB3StringTypeUtility.isEmpty(this.referenceType))
        {
            log.debug("照会区分が未指定です。");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "照会区分が未指定です。");
        }
        
        if (
            !(WEB3RuitoReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(this.referenceType)
                || WEB3RuitoReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(this.referenceType)))
        {
            //照会区分が“0:注文照会”、“1:取消一覧(取消可能なもののみ表示)”以外である場合、例外をスローする。
            log.debug("照会区分が“0:注文照会”、“1:取消一覧(取消可能なもののみ表示)”以外である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "照会区分が“0:注文照会”、“1:取消一覧(取消可能なもののみ表示)”以外である。");
        }

        //ソートキーチェック
        if (this.ruitoSortKeys == null)
        {
            log.debug("ソートキーがnullの値である。");              
            //累投ソートキーがnullの値であれば例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "ソートキーがnullの値である。");
        }
        
        //累投ソートキーの要素数が０であれば例外をスローする
        if (this.ruitoSortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０である。");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "ソートキーの要素数が０である。");
        }
        
        //キー項目がnullの値であれば例外をスローする
 
        for (int i = 0; i < this.ruitoSortKeys.length; i++)
        {
            if (this.ruitoSortKeys[i].keyItem == null)
            {
                log.debug("ソートキーのキー項目が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目が未指定です。");
            }
        }

        //キー項目に以下の項目名以外の値が存在したら例外をスローする
        for (int i = 0; i < ruitoSortKeys.length; i++)
        {
            if (!(this.ruitoSortKeys[i].keyItem.equals("ruitoProductCode")
                || this.ruitoSortKeys[i].keyItem.equals("ruitoDealingType")
                || this.ruitoSortKeys[i].keyItem.equals("orderDate")))
            {
                log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
        }

        //昇順／降順がnullの値であれば例外をスローする
        for (int j = 0; j < this.ruitoSortKeys.length; j++)
        {
            if (this.ruitoSortKeys[j].ascDesc == null)
            {
                log.debug("昇順／降順が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "昇順／降順が未指定です。");
            }
        }

        //昇順／降順が以”A：昇順",”D：降順”値以外の場合例外をスローする
        for (int k = 0; k < this.ruitoSortKeys.length; k++)
        {
            if (!("A".equals(this.ruitoSortKeys[k].ascDesc)
                || "D".equals(this.ruitoSortKeys[k].ascDesc)))
            {
                log.debug("昇順／昇順／降順が”A：昇順”、”D：降順”以外の値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            }
        }

        //要求ページ番号がnullの値であれば例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "要求ページ番号が未指定です。");
        }

        //要求ページ番号が数字以外の値であれば例外をスローする
        try
        {
            double l_dblTemp = Double.parseDouble(this.pageIndex);
        }
        catch (NumberFormatException l_ex)
        {
            log.debug("要求ページ番号が数字以外の値である。");
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00090,
               this.getClass().getName() + "." + STR_METHOD_NAME, 
               "要求ページ番号が数字以外の値である。");
        }

        //ページ内表示行数がnullの値であれば例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "ページ内表示行数が未指定です。");
        }

        //ページ内表示行数が数字以外の値であれば
        try
        {
            double l_dblTemp = Double.parseDouble(this.pageSize);
        }
        catch (NumberFormatException l_ex)
        {
            log.debug("ページ内表示行数が数字以外の値です。。");
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00092,
               this.getClass().getName() + "." + STR_METHOD_NAME, 
               "ページ内表示行数が数字以外の値です。");
        }
        
		log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投注文照会レスポンスを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40922D7B03E7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoOrderReferenceResponse(this);
    }

}
@
