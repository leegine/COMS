head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePasswordConvSonarTraderResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 ネットトレードシステム開発部
File Name        : 中間テーブル（扱者情報）暗証番号追加レスポンス(WEB3GentradePasswordConvSonarTraderRequest)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/22 沢田(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 中間テーブル（扱者情報）暗証番号追加レスポンス
 */
public class WEB3GentradePasswordConvSonarTraderResponse extends WEB3BackResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "gentrade_password_conv_sonar_trader";
   
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200708221700L;
   
    /**
     * コンストラクタ<br />
     * @@param WEB3BackRequest - リクエストデータ<br />
     * <br />
     * @@return WEB3GentradePasswordConvSonarTraderResponse
     */
    public WEB3GentradePasswordConvSonarTraderResponse(WEB3BackRequest l_request) 
    {
        super(l_request);
    }
}
@
