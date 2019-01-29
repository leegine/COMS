head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式RCVDキュー照会一覧リクエスト(WEB3AdminFeqRcvdQueueReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
                   2006/11/23 徐大方 (中訊) 障害管理 K00010
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.465 （実装）No.027
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum; 
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式RCVDキュー照会一覧リクエスト)<BR>
 * 管理者外国株式RCVDキュー照会一覧リクエストクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3AdminFeqRcvdQueueReferenceRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_rcvd_queue_reference";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD00AB
     */
    public WEB3AdminFeqRcvdQueueReferenceRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFeqRcvdQueueReferenceRequest.class);
    
    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode;    
    
    /**
     * (処理区分)<BR>
     * 0：処理待ち<BR>
     * 1：処理済み<BR>
     * 7：約定処理中<BR>
     * 8：処理省略<BR>
     * 9：処理エラー<BR>
     */
    public String transactionDiv;
    
    /**
     * (運用コード)<BR>
     * 運用コード<BR>
     */
    public String managementCode;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;  
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (経路区分)<BR>
     * 経路区分<BR>
     * <BR>
     * 0：出来通知<BR> 
     * 1：出来入力<BR> 
     * 2：約定結果一括入力<BR> 
     * 3：注文受付<BR>
     * 4：注文受付取消認証<BR>
     * 5：注文受付結果一括入力<BR>
     */
    public String orderRootDiv;
    
    /**
     * (受付区分)<BR>
     * 受付区分 <BR>
     * <BR>
     * 01：注文受付済<BR> 
     * 09：注文受付エラー<BR> 
     * 03：注文受付済取消<BR>
     * 11：訂正済<BR>
     * 19：訂正エラー<BR> 
     * 21：取消済<BR>
     * 29：取消エラー<BR>
     * 31：出来ず<BR>
     */
    public String acceptDiv;
    
    /**
     * (ソートキー)<BR>
     * 外国株式ソートキーオブジェクトの配列<BR>
     */
    public WEB3ForeignSortKey[] sortKeys;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号 <BR>
     * <BR>
     * 表示させたいページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR> 
     *（ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     *<BR>
     *１）処理区分チェック<BR> 
     *　@this.処理区分 != nullの場合、以下のチェックを行う。<BR> 
     *　@１－１）this.処理区分に下記の項目以外が設定されていたら、<BR> 
     *　@　@　@　@「処理区分が未定義の値」の例外をスローする。<BR> 
     *　@　@　@・0：処理待ち <BR>
     *　@　@　@・1：処理済み <BR>
     *　@　@　@・7：約定処理中 <BR>
     *　@　@　@・8：処理省略 <BR>
     *　@　@　@・9：処理エラー <BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_01250<BR>
     *<BR>
     * this.経路区分 != nullの場合、以下のチェックを行う。<BR>
     * １－２）this.経路区分に下記の項目以外が設定されていたら、<BR> 
     *　@　@　@「経路区分が未定義の値」の例外をスローする。 <BR>
     *　@　@　@・0：出来通知 <BR>
     *　@　@　@・1：出来入力 <BR>
     *　@　@　@・2：約定結果一括入力<BR> 
     *　@　@　@・3：注文受付 <BR>
     *　@　@　@・4：注文受付取消認証<BR> 
     *　@　@　@・5：注文受付結果一括入力<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_02651<BR>
     *<BR>
     * this.受付区分 != nullの場合、以下のチェックを行う。<BR>
     * １－３）this.受付区分に下記の項目以外が設定されていたら、<BR> 
     *　@　@　@「受付区分が未定義の値」の例外をスローする。<BR>
     *　@　@　@・01：注文受付済 <BR>
     *　@　@　@・09：注文受付エラー <BR>
     *　@　@　@・03：注文受付済取消 <BR>
     *　@　@　@・11：訂正済 <BR>
     *　@　@　@・19：訂正エラー <BR>
     *　@　@　@・21：取消済 <BR>
     *　@　@　@・29：取消エラー <BR>
     *　@　@　@・31：出来ず <BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00790<BR>
     *<BR>
     * １－４）this.経路区分 == null、またはthis.経路区分に下記の項目<BR> 
     *　@　@　@が設定されていて、かつthis.受付区分に項目が設定されている<BR> 
     *　@　@　@場合は、「経路区分と受付区分の組み合わせが不正」の例外をスローする。<BR> 
     *　@　@　@・0：出来通知 <BR>
     *　@　@　@・1：出来入力 <BR>
     *　@　@　@・2：約定結果一括入力<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_02652<BR>
     *<BR>
     *２）　@ソートキーチェック<BR> 
     *　@２－１）this.ソートキー == nullであった場合<BR> 
     *　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00231<BR> 
     *<BR>
     *　@２－２）this.ソートキー.length == 0だった場合<BR> 
     *　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00232<BR>
     *<BR>
     *　@２－３）this.ソートキーの全要素に対して<BR> 
     *　@　@　@　@下記のチェックを行う。<BR>
     *　@　@２－３－１）ソートキー.validate()をコールする。<BR> 
     *<BR>
     *　@　@２－３－２）ソートキー.キー項目に下記の項目以外が<BR> 
     *　@　@　@設定されていたら、<BR>
     *　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR> 
     *　@　@　@・「更新者コード」<BR>
     *　@　@　@・「運用コード」<BR> 
     *　@　@　@・「部店コード」<BR> 
     *　@　@　@・「顧客コード」<BR> 
     *　@　@　@・「経路区分」<BR> 
     *      ・「受付区分」<BR>
     *　@　@　@・「処理区分」<BR> 
     *　@　@　@・「作成日付」<BR> 
     *　@　@　@・「更新日付」<BR> 
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00086<BR>
     *<BR>
     *３）要求ページ番号チェック<BR> 
     *　@３－１）this.要求ページ番号 == nullであった場合、<BR> 
     *　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00089<BR>
     *<BR>
     *　@３－２）this.要求ページ番号が数字以外の値であった場合、<BR> 
     *　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00090<BR>
     *<BR>
     *　@３－３）this.要求ページ番号 ≦ 0であった場合、<BR> 
     *　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00616<BR>
     *<BR>
     *４）ページ内表示行数チェック<BR> 
     *　@４－１）this.ページ内表示行数 == nullであった場合、<BR> 
     *　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_02224<BR>
     *<BR>
     *　@４－２）this.ページ内表示行数が数字以外の値であった場合、<BR> 
     *　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00092<BR> 
     *<BR>
     *　@４－３）this.ページ内表示行数 ≦ 0であった場合、<BR> 
     *　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException<BR>
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
     
        //１）処理区分チェック 
        //this.処理区分 != nullの場合、以下のチェックを行う。
        if (this.transactionDiv != null)
        {
            // １－１）this.処理区分に下記の項目以外が設定されていたら、 
            //　@　@「処理区分が未定義の値」の例外をスローする。 
            //　@　@・0：処理待ち 
            //　@　@・1：処理済み
            //　@  ・7：約定処理中 
            //　@　@・8：処理省略 
            //　@　@・9：処理エラー 
            String l_strTodo = SleRcvdqProcStatusEnum.TODO.intValue() + "";
            String l_strProcessed = SleRcvdqProcStatusEnum.PROCESSED.intValue() + "";
            String l_strExecProcessing = SleRcvdqProcStatusEnum.EXEC_PROCESSING.intValue() + "";
            String l_strSkipProcessingIgnore = SleRcvdqProcStatusEnum.SKIP_PROCESSING_IGNORE.intValue() + "";
            String l_strSkipProcessedError = SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR.intValue() + "";

            if ((!l_strTodo.equals(this.transactionDiv))
                &&(!l_strProcessed.equals(this.transactionDiv))
                &&(!l_strExecProcessing.equals(this.transactionDiv))
                &&(!l_strSkipProcessingIgnore.equals(this.transactionDiv))
                &&(!l_strSkipProcessedError.equals(this.transactionDiv)))
            {  
                log.debug("処理区分の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                   this.getClass().getName() + STR_METHOD_NAME,
                    "処理区分の値が存在しないコード値です。" + this.transactionDiv);
            }
        }
        
        //１－２）this.経路区分に下記の項目以外が設定されていたら、 
        //　@　@「経路区分が未定義の値」の例外をスローする。 
        //　@　@・0：出来通知 
        //　@　@・1：出来入力 
        //　@　@・2：約定結果一括入力 
        //　@　@・3：注文受付 
        //　@　@・4：注文受付取消認証 
        //　@　@・5：注文受付結果一括入力
        if(this.orderRootDiv != null)
        {    
            if ((!WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_CANCEL_AUTHENTICATE.equals(this.orderRootDiv))
                &&(!WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD.equals(this.orderRootDiv)))
            {
                log.debug("経路区分が未定義の値。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02651,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "経路区分が未定義の値。" + this.orderRootDiv);
            }
        }
        
        //１－３）this.受付区分に下記の項目以外が設定されていたら、 
        //　@　@「受付区分が未定義の値」の例外をスローする。 
        //　@  ・01：注文受付済 
        //　@　@・09：注文受付エラー 
        //　@　@・03：注文受付済取消 
        //　@　@・11：訂正済 
        //　@　@・19：訂正エラー 
        //　@　@・21：取消済 
        //　@　@・29：取消エラー 
        //　@　@・31：出来ず 
        if(this.acceptDiv != null)
        {
            if ((!WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.CHANGED.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.CANCEL.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(this.acceptDiv))
                &&(!WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(this.acceptDiv)))
            {
                log.debug("注文受付区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00790,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "注文受付区分が存在しないコード値です。" + this.acceptDiv);
            }
        }

        //１－４）this.経路区分 == null、またはthis.経路区分に下記の項目 
        //　@　@が設定されていて、かつthis.受付区分に項目が設定されている 
        //　@　@場合は、「経路区分と受付区分の組み合わせが不正」の例外をスローする。 
        //　@　@・0：出来通知 
        //　@　@・1：出来入力 
        //　@　@・2：約定結果一括入力 
        if ((this.orderRootDiv == null || 
            WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(this.orderRootDiv) 
            ||WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(this.orderRootDiv)
            ||WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(this.orderRootDiv))
            && this.acceptDiv != null)
        {
            log.debug("経路区分と受付区分の組み合わせが不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02652,
                this.getClass().getName() + STR_METHOD_NAME,
                "経路区分と受付区分の組み合わせが不正");
        }
        
        //２）　@ソートキーチェック 
        //２－１）this.ソートキー == nullであった場合 
        //　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーがnull。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーがnull." + this.sortKeys);
        }
        
        //２－２）this.ソートキー.length == 0だった場合 
        //　@　@「ソートキー.要素数が0」の例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキー.要素数が0。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキー.要素数が0。" + this.sortKeys.length);
        }  
        
        //２－３）this.ソートキーの全要素に対して 
        //　@　@　@　@下記のチェックを行う。 
        //  　@　@２－３－１）ソートキー.validate()をコールする。 
        //
        //    　@２－３－２）ソートキー.キー項目に下記の項目以外が 
        //  　@　@設定されていたら、 
        //   　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。 
        //  　@　@　@・「更新者コード」 
        //  　@　@　@・「運用コード」 
        //  　@　@　@・「部店コード」 
        //  　@　@　@・「顧客コード」 
        //  　@　@　@・「経路区分」
        //        ・「受付区分」 
        //  　@　@　@・「処理区分」 
        //        ・「作成日付」 
        //    　@　@・「更新日付」
        int l_intSortKeysLength = 0;
 
        l_intSortKeysLength = this.sortKeys.length;
        
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            this.sortKeys[i].validate();
            if ((!WEB3FeqSortKeyItemNameDef.UPDATER_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.ORDER_ROOT_DIV.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.ACCEPT_DIV.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.TRANSACTION_DIV.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.CREATED_TIMESTAMP.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.LAST_UPDATED_TIMESTAMP.equals(this.sortKeys[i].keyItem)))
            {
                log.debug("ソートキー.キー項目が未定義の値。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキー.キー項目が未定義の値。" + this.sortKeys[i].keyItem);
            }
        }
        
        //３）要求ページ番号チェック 
        //３－１）this.要求ページ番号 == nullであった場合、 
        //　@「要求ページ番号がnull」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が未指定です。" + this.pageIndex);       
        }
        
        //３－２）this.要求ページ番号が数字以外の値であった場合、 
        //　@　@「要求ページ番号が数字以外」の例外をスローする。 
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外。" + this.pageIndex);      
        }
        
        //３－３）this.要求ページ番号 ≦ 0であった場合、 
        //　@　@「要求ページ番号が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号が0以下。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が0以下。" + this.pageIndex);      
        }
        
        //４）ページ内表示行数チェック 
        //４－１）this.ページ内表示行数 == nullであった場合、 
        //　@　@「ページ内表示行数がnull」の例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。" + this.pageSize);      
        }
        
        //４－２）this.ページ内表示行数が数字以外の値であった場合、 
        //　@　@「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外。" + this.pageSize);      
        }
        
        //４－３）this.ページ内表示行数 ≦ 0であった場合、 
        //　@　@「ページ内表示行数が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数が0以下。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が0以下。" + this.pageSize);      
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
        return new WEB3AdminFeqRcvdQueueReferenceResponse(this);
    }
    
}
@
