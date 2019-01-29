head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.13.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報一覧リクエスト(WEB3AdminMailInfoReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mailinfo.define.WEB3AdminMailInfoKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (メール情報一覧リクエスト)<BR>
 * メール情報一覧リクエストクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoReferenceRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoReferenceRequest.class);    
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (メール情報ソートキー)<BR>
     * 　@・メールID　@・識別ID<BR>
     */
    public WEB3AdminMailInfoSortKey[] sortKeys;

    /**
     * (要求ページ番号)<BR>
     * 表示させたいページ位置を指定 <BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * 1ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;    

    /**
     * @@roseuid 416DEAAB0271
     */
    public WEB3AdminMailInfoReferenceRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * メール情報一覧レスポンスオブジェクトを返却する。<BR>
     * @@return webbroker3.mailinfo.common.WEB3GenResponse
     * @@roseuid 413C0D6C02AF
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoReferenceResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) サービス利用ソートキーのチェック<BR>
     *  1-1) this.メール情報ソートキーがnullの場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00231                <BR>
     *     =============================================== <BR>
     *  1-2) this.メール情報ソートキーの要素数が0の場合、例外をスローする。<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00232                <BR>
     *     =============================================== <BR>
     *  1-3) this.メール情報ソートキーの要素数分、以下を繰り返す。<BR>
     * 　@1-3-1) this.メール情報ソートキー.キー項目がnullの場合、例外をスローする。    
     * =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00085                <BR>
     *     =============================================== <BR>
     * <BR>
     * 　@1-3-2) this.メール情報ソートキー.キー項目が以下の値以外の場合、<BR>
     * 例外をスローする。<BR>
     * 　@　@　@"プログラムID"<BR>
     * 　@　@　@"識別ID"<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00086                <BR>
     *     =============================================== <BR>
     * 　@1-3-2) this.メール情報ソートキー.昇順／降順がnullの場合、<BR>
     * 例外をスローする。<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00087                <BR>
     *     =============================================== <BR>
     * 　@1-3-3) this.メール情報ソートキー.昇順／降順が以下<BR>
     * の値以外だった場合、例<Br>外をスローする。<BR>
     * 　@　@　@"A:昇順"<BR>
     * 　@　@　@"D:降順"<BR>
     * <BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00088                <BR>
     *     =============================================== <BR>
     * 2) 要求ページ番号チェック <BR>
     *  2-1) this.要求ページ番号がnullの値であれば例外をスローする。 <BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00089                <BR>
     *     =============================================== <BR>
     *  2-2) this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     * <BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00090                <BR>
     *     =============================================== <BR>
     * 3) ページ内表示行数チェック <BR>
     *  3-1) this.ページ内表示行数がnullの値であれば例外をスローする。 <BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00091                <BR>
     *     =============================================== <BR>
     *  3-2) this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     *     =============================================== <BR>
     *         class         :  WEB3BusinessLayerException           <BR>
     *         tag            :  BUSINESS_ERROR_00092                <BR>
     *     =============================================== <BR>
     * @@throws WEB3BaseException
     * @@roseuid 413C0DF902AF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //  1-1) this.メール情報ソートキーがnullの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, this.getClass().getName() + STR_METHOD_NAME); 
        }
               
        //  1-2) this.メール情報ソートキーの要素数が0の場合、例外をスローする.
        int l_intSortKeyCnt = this.sortKeys.length;      
        if (l_intSortKeyCnt == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //  1-3)this.メール情報ソートキーの要素数分、以下を繰り返す。
        for (int i = 0; i < l_intSortKeyCnt; i++)
        {
          // 　@1-3-1) this.メール情報ソートキー.キー項目がnullの場合、例外をスローする。              
            if (sortKeys[i].keyItem == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00085, this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // 　@1-3-2) this.メール情報ソートキー.キー項目が以下の値以外の場合、例外をスローする。
            if(!WEB3AdminMailInfoKeyItemDef.SENDMAIL_DIV.equals(sortKeys[i].keyItem)
                && !WEB3AdminMailInfoKeyItemDef.DISCERN_ID.equals(sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086, this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //　@1-3-3) this.メール情報ソートキー.昇順／降順がnullの場合 例外をスローする。<BR>
            if (sortKeys[i].ascDesc == null )
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00087, this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // 　@1-3-4) this.メール情報ソートキー.昇順／降順が以下<BR>
            // の値以外だった場合、例<Br>外をスローする。<BR>
            // 　@　@　@"A:昇順"<BR>
            // 　@　@　@"D:降順"<BR>
            
            if (!WEB3AscDescDef.ASC.equals(sortKeys[i].ascDesc) && !WEB3AscDescDef.DESC.equals(sortKeys[i].ascDesc))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00088, this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        
        //  2-1) this.要求ページ番号がnullの値であれば例外をスローする。
              
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00089, this.getClass().getName() + STR_METHOD_NAME);
        }
        //  2-2) this.要求ページ番号が数字以外の値であれば例外をスローする。
                       
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex) )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, this.getClass().getName() + STR_METHOD_NAME);
        }
        //  3-1) this.ページ内表示行数がnullの値であれば例外をスローする。 
      
        
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, this.getClass().getName() + STR_METHOD_NAME); 
        }
        //  3-2) this.ページ内表示行数が数字以外の値であれば例外をスローする。
        
        
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
