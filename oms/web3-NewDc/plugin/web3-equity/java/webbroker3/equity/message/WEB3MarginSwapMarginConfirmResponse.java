head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文確認レスポンス(WEB3MarginSwapMarginConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
Revesion History : 2004/12/13 桑原 (SRA) 修正
*/

package webbroker3.equity.message;

/**
 * （信用取引現引現渡注文確認レスポンス）。<br>
 * <br>
 * 信用取引現引現渡注文確認レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginConfirmResponse extends WEB3MarginConfirmCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (建株明細一覧)
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (注文ID)
     */
    public String orderId;
    
	/**
	 * (インサイダー警告表示フラグ)<BR>
	 * true：警告表示要　@　@　@false：警告表示不要
	 */
	public boolean insiderWarningFlag;

    /**
     * (注意文言表示区分)<BR>
     * null：注意文言非表示　@　@3：預り金不足注意文言表示<BR>
     */
    public String attentionObjectionType;
    
    /**
     * (預り金不足額)<BR>
     * 余力の不足金額<BR>
     */
    public String accountBalanceInsufficiency;

    /**
     * @@roseuid 4140425501A1
     */
    public WEB3MarginSwapMarginConfirmResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginSwapMarginConfirmResponse(WEB3MarginSwapMarginConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
