head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfDisplayDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信・外貨MMF表示区分 定数定義インタフェイス(WEB3MFBizDateTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/05 唐性峰(中訊)　@新規作成(モデル523)
*/
package webbroker3.mf.define;

/**
 * 投信・外貨MMF表示区分 定数定義インタフェイス
 *                                                                     
 * @@author 唐性峰
 * @@version 1.0
 */
public interface WEB3MutualFrgnMmfDisplayDivDef
{
    /**
     * 0:投信のみ
     */
    public static final String MUTUAL_FUND = "0";

    /**
     * 1:外貨MMFのみ
     */
    public static final String FRGN_MMF = "1";

    /**
     * 2:両方
     */
    public static final String BOTH = "2";
}
@
