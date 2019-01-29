head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	PutAndCall.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価情報のプット＆コールを表すEnumeratedクラス(PutAndCall.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/10 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

/**
 * 時価情報のプット＆コールを表すEnumeratedクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class PutAndCall extends Enumerated
{

    /**
     * プット＆コールのEnumeratedクラスで使用する定数<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static class IntValues
    {
        /**
         * プット＆コール：未定義
         */
        public static final int UNDEFINED = 0;

        /**
         * プット＆コール：プット
         */
        public static final int PUT = 1;

        /**
         * プット＆コール：コール
         */
        public static final int CALL = 2;

    }

    /**
     * プット＆コール：未定義
     */
    public static final PutAndCall UNDEFINED =
        new PutAndCall(IntValues.UNDEFINED, "");

    /**
     * プット＆コール：プット
     */
    public static final PutAndCall PUT = new PutAndCall(IntValues.PUT, "P");

    /**
     * プット＆コール：コール
     */
    public static final PutAndCall CALL = new PutAndCall(IntValues.CALL, "C");

    /**
     * コンストラクタ
     */
    private PutAndCall(int i, String s)
    {
        super(i, s);
    }
    
    public static PutAndCall getPutAndCall(IfoDerivativeTypeEnum derivativeType)
    {
        switch (derivativeType.intValue())
        {
            case IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS :
                return PUT;
            case IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS :
                return CALL;
            default :
                return UNDEFINED;
        }
    }

}
@
