head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrixTpCalcResultResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力計算結果レスポンス(WEB3TPOrixTpCalcResultResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/18 Matsumoto(SRA) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;

/**
 * (余力計算結果レスポンス)<BR>
 * 余力計算結果レスポンスクラス。<BR>
 * 
 * @@author Matsumoto(SRA)
 */
public class WEB3TPOrixTpCalcResultResponse extends WEB3BackResponse 
{

    /**
     * ポリモルフィックタイプ。<BR>
     */
    public static final String PTYPE = "tradingpower_orix_tp_calc_result";

    /**
     * シリアルバージョンUID <BR>
     */
    public static final long serialVersionUId = 200503181330L;

   /**
    * メッセージ
    */
   public String message;
   
   /**
    * From口座ID
    */
   public long fromAccountID;

   /**
    * To口座ID
    */
   public long toAccountID;

   /**
    * 現物顧客出力件数
    */
   public long equityRows;

   /**
    * 信用顧客出力件数
    */
   public long marginRows;

   /**
    * (コンストラクタ)*
    * @@param l_request
    */
   protected WEB3TPOrixTpCalcResultResponse(WEB3BackRequest l_request) 
   {
       super( l_request );
   }
   
   /**
    * (コンストラクタ)
    */
   public WEB3TPOrixTpCalcResultResponse() 
   {
   }
   
}
@
