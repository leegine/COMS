head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConnectionStatusEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SleBasedMarketAdapterBasePluginクラス
Author Name      : Daiwa Institute of Research
Revision History : 2006/05/15 孫 新規作成
*/
package webbroker3.slebase.enums;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * SLEコネクタとSLE直結エンジン間の接続状態を定義する
 * 
 */
public class SleConnectionStatusEnum extends Enumerated
{
    
    /**0：SLEエンジンへ接続が失い */
    public static final SleConnectionStatusEnum CONNECTION_LOSE = new SleConnectionStatusEnum(0,
            "CONNECTION_LOSE");

    /**1：SLEエンジンへ再接続成功 */
    public static final SleConnectionStatusEnum RECONNECTION_SUCCESS =  new SleConnectionStatusEnum(1,
            "RECONNECTION_SUCCESS");
    
    /**2：SLEコネクタ正常に起動*/
    public static final SleConnectionStatusEnum START_SUCCESS   =  new SleConnectionStatusEnum(2,
            "START_SUCCESS");

    /**3：SLEコネクタ停止   */
    public static final SleConnectionStatusEnum CONNECTION_STOP =  new SleConnectionStatusEnum(3,
            "CONNECTION_STOP");
    
    public SleConnectionStatusEnum(int i, String s)
    {
        super(i, s);    
    }
    
    /**
     * 任意のエナムの整数値定義をする内部クラスです。整数値を定義することにより、 
     * このクラスをswitch文で容易に利用することができます。
     */
    public static class IntValues {

        //~ Static fields/initializers ---------------------------------------------

        /**0：SLEエンジンへ接続が失い */
        public static final int CONNECTION_LOSE = 0;

        /**1：SLEエンジンへ再接続成功 */
        public static final int RECONNECTION_SUCCESS = 1;
        
        /**2：SLEコネクタ正常に起動*/
        public static final int START_SUCCESS   = 2;

        /**3：SLEコネクタ停止   */
        public static final int CONNECTION_STOP = 3;
        
    }    
    

}
@
