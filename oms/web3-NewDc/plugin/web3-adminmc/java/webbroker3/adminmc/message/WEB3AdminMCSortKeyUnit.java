head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.47.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メニュー制御ソートキー(WEB3AdminMCSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/22  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (メニュー制御ソートキー)<BR>
 * メニュー制御ソートキー<BR>
 * @@author 温顕法@
 * @@version 1.0
 */
 
public class WEB3AdminMCSortKeyUnit extends Message
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCSortKeyUnit.class); 

    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     * 当クラスを利用したリクエストに対してのレスポンスクラス中のシンボル名をキー項目とする。 <BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * A：昇順　@D：降順<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 4198642A009C
     */
    public WEB3AdminMCSortKeyUnit()
    {

    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@キー項目のチェック<BR>
     * 　@１－１）　@キー項目が未入力lの場合、例外をスローする。<BR>
     *               class   :  WEB3BusinessLayerException           <BR>
     *               tag     :  BUSINESS_ERROR_00085            <BR>
     * <BR>
     * ２）　@昇順／降順のチェック<BR>
     * 　@２－１）　@昇順／降順が未入力の場合、例外をスローする。<BR>
     *               class   :  WEB3BusinessLayerException           <BR>
     *               tag     :  BUSINESS_ERROR_00087              <BR>
     * <BR>
     * 　@２－２）　@昇順／降順が不正なコード値の場合、例外をスローする。<BR>
     *               class   :  WEB3BusinessLayerException           <BR>
     *               tag     :  BUSINESS_ERROR_00088               <BR>
     * <BR>
     * @@roseuid 41750D8D024A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);
        //１）　@キー項目のチェック<BR>
        //　@１－１）　@キー項目が未入力lの場合、例外をスローする。<BR>
        //              class   :  WEB3BusinessLayerException           <BR>
        //              tag     :  BUSINESS_ERROR_00085            <BR>
        if ((this.keyItem == null) || (this.keyItem == ""))
        {
            log.error(" キー項目が未入力 .");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        // ２）　@昇順／降順のチェック<BR>
           // ２－１）　@昇順／降順が未入力の場合、例外をスローする。<BR>
           //             class   :  WEB3BusinessLayerException           <BR>
           //             tag     :  BUSINESS_ERROR_00087              <BR>
           if ((this.ascDesc == null) || (this.ascDesc == ""))
           {
               log.error("昇順／降順が未入力. ");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                               this.getClass().getName() + STR_METHOD_NAME);
           }
           // 　@２－２）　@昇順／降順が不正なコード値の場合、例外をスローする。<BR>
           //               class   :  WEB3BusinessLayerException           <BR>
           //               tag     :  BUSINESS_ERROR_00088               <BR>
           if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
           {
               log.error("昇順／降順が不正なコード値. ");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                               WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                               this.getClass().getName() + STR_METHOD_NAME);
           }

           log.exiting(STR_METHOD_NAME);
     }
}
@
