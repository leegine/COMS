head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenStateUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設状況(WEB3AccOpenStateUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
                 : 2006/06/08 周捷(中訊) 仕様変更・モデル050
                   2006/09/19 柴雙紅 (中訊) 仕様変更 モデル098
Revesion History : 2009/08/10 張騰宇 (中訊) 仕様変更 モデル163
Revesion History : 2010/02/10 武波 (中訊) 仕様変更 モデルNo.216
*/

package webbroker3.accountopen.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (口座開設状況)<BR>
 * 口座開設状況<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenStateUnit extends Message 
{
    
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
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;
    
    /**
     * (入力区分)<BR>
     * 入力区分<BR>
     * <BR>
     * 0：　@顧客<BR>
     * 1：　@ＣＣオペレータ<BR>
     * 2：　@管理者<BR>
     */
    public String inputDiv;

    /**
     * (作成者コード)<BR>
     * 作成者コード<BR>
     * <BR>
     * ※入力区分：顧客の場合はnull<BR>
     */
    public String creatorCode;
    
    /**
     * (資料請求日)<BR>
     * 資料請求日<BR>
     */
    public Date infoClaimDate;
    
    /**
     * (口座開設日)<BR>
     * 口座開設日<BR>
     */
    public Date accountOpenDate;
    
    /**
     * (顧客姓（カナ）)<BR>
     * 顧客姓（カナ）<BR>
     */
    public String accountFamilyNameKana;
    
    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR>
     */
    public String accountNameKana;
    
    /**
     * (口座開設状況区分)<BR>
     * 口座開設状況区分<BR>
     * <BR>
     * 0：　@DEFAULT（未開設）<BR> 
     * 1：　@開設中 <BR>
     * 2：　@エラー発生 <BR>
     * 3：　@開設済 <BR>
     * 4：　@審査中 <BR>
     * 5：　@却下 <BR>
     */
    public String accountOpenStateDiv;
    
    /**
     * (取消可能フラグ)<BR>
     * 取消可能フラグ<BR>
     * <BR>
     * true：　@取消可能<BR>
     * false：　@取消不可<BR>
     */
    public boolean cancelFlag;

    /**
     * (削除フラグ)<BR>
     * 削除フラグ<BR>
     * <BR>
     *  1：TRUE/無効（削除）<BR>
     *  0：FALSE/有効（DEFAULT）<BR>
     */
    public String deleteFlag;

    /**
     * (削除日時)<BR>
     * 削除日時<BR>
     */
    public Date deleteDate;

    /**
     * (印刷フラグ)<BR>
     * 印刷フラグ<BR>
     */
    public String printFlag;

    /**
     * (受領フラグ)<BR>
     * 受領フラグ<BR>
     * <BR>
     * 1：TRUE/受領済 <BR>
     * 0：FALSE/未受領（DEFAULT）<BR>
     */
    public String receiveFlag;

    /**
     * (特定口座区分)<BR>
     * 特定口座区分<BR>
     * <BR>
     * 0：一般口座<BR>
     * 1：特定口座<BR>
     */
    public String taxTypeDiv;

    /**
     * (外国人フラグ)<BR>
     * 外国人フラグ <BR>
     * <BR>
     * 1：TRUE/日本以外 <BR>
     * 0：FALSE/日本（DEFAULT）<BR>
     */
    public String foreignerFlag;

    /**
     * (伝票作成情報)<BR>
     * 伝票作成情報<BR>
     */
    public WEB3AccOpenVoucherInfo voucherInfo;
    
    /**
     * (専用振込先口座番号)<BR>
     * 専用振込先口座番号<BR>
     */
    public String exclusiveAccountCode;

    /**
     * (内部者登録区分)<BR>
     * 内部者登録区分<BR>
     * <BR>
     * 0：登録なし<BR>
     * 1：登録あり<BR>
     */
    public String insiderDiv;

    /**
     * (口座開設状況)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 419C62E70235
     */
    public WEB3AccOpenStateUnit() 
    {
     
    }
}
@
