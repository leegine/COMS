head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendqProcStatusEnum.java;


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
 Revision History : 2006/05/2 李 新規作成
 */

package webbroker3.slebase.enums;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 *  SEND_Q　@の処理区分に関するエナム定数を定義します。
 */
public class SleSendqProcStatusEnum extends Enumerated {

    /** 処理待ち状態をあらわします。 */
    public static final SleSendqProcStatusEnum TODO = new SleSendqProcStatusEnum(IntValues.TODO,
          "TODO");

    /** 処理済をあらわします。 */
    public static final SleSendqProcStatusEnum PROCESSED = new SleSendqProcStatusEnum(IntValues.PROCESSED,
          "PROCESSED");

    /** ローカル変更または取消であるため処理が省略されたことをあらわします。*/
    public static final SleSendqProcStatusEnum SKIP_PROCESSING_LOCAL = new SleSendqProcStatusEnum(IntValues.SKIP_PROCESSING_LOCAL,
          "SKIP_PROCESSING_LOCAL");

    /** エラーのため処理が省略されたことをあらわします。 */
    public static final SleSendqProcStatusEnum SKIP_PROCESSING_ERROR = new SleSendqProcStatusEnum(IntValues.SKIP_PROCESSING_ERROR,
          "SKIP_PROCESSING_ERROR");
    
    /**未送信状態をあらわします。 */
    public static final SleSendqProcStatusEnum NOT_PROCESSED = new SleSendqProcStatusEnum(IntValues.NOT_PROCESSED,
          "NOT_PROCESSED");
    
    /**送信準備状態を表します。*/
    public static final SleSendqProcStatusEnum PREPARE_PROCESSED = new SleSendqProcStatusEnum(IntValues.PREPARE_PROCESSED,
          "PREPARE_PROCESSED");
          
//    /** 管理画面用未定義フラグ'2' */
//    public static final SleSendqProcStatusEnum NO_DEFINE_2 = new SleSendqProcStatusEnum(IntValues.NO_DEFINE_2,
//            "NO_DEFINE_2");
    
    /** 管理画面用未定義フラグ'3' */      
    public static final SleSendqProcStatusEnum NO_DEFINE_3 = new SleSendqProcStatusEnum(IntValues.NO_DEFINE_3,
            "NO_DEFINE_3");                    
    
    /** 管理画面用未定義フラグ'4' */
    public static final SleSendqProcStatusEnum NO_DEFINE_4 = new SleSendqProcStatusEnum(IntValues.NO_DEFINE_4,
            "NO_DEFINE_4");
    
    /** 管理画面用未定義フラグ'5' */
    public static final SleSendqProcStatusEnum NO_DEFINE_5 = new SleSendqProcStatusEnum(IntValues.NO_DEFINE_5,
            "NO_DEFINE_5");
          
    /** バッチ処理終了フラグ'2' */
    public static final SleSendqProcStatusEnum BAT_PROCED = new SleSendqProcStatusEnum(IntValues.BAT_PROCED,
            "BAT_PROCED");    

    
    /**
     * 上位クラスのコンストラクタをオーバライドする必須のコンストラクタです。 
     *
     * @@param i int型の値
     * @@param s 文字列表現
     */
    public SleSendqProcStatusEnum(int i, String s) {
        super(i, s);
    }

    /**
     * 任意のエナムの整数値定義をする内部クラスです。整数値を定義することにより、 
     * このクラスをswitch文で容易に利用することができます。
     */
    public static class IntValues {

        //~ Static fields/initializers ---------------------------------------------

        /** 処理待ち状態をあらわします。  */
        public static final int TODO = 0;

        /** 処理済をあらわします。 */
        public static final int PROCESSED = 1;

        /** ローカル変更または取消であるため処理が省略されたことをあらわします。 */
        public static final int SKIP_PROCESSING_LOCAL    = 8;

        /** エラーのため処理が省略されたことをあらわします。 */
        public static final int SKIP_PROCESSING_ERROR = 9;
        
        /**未送信状態をあらわします。 */
        public static final int NOT_PROCESSED = 7;
        
        /**送信準備状態を表します。*/
        public static final int PREPARE_PROCESSED = 6;
        
//        /** 管理画面用未定義フラグ'2' */
//        public static final int NO_DEFINE_2 = 2;
        
        /** 管理画面用未定義フラグ'3' */        
        public static final int NO_DEFINE_3 = 3;
        
        /** 管理画面用未定義フラグ'4' */
        public static final int NO_DEFINE_4 = 4;
        
        /** 管理画面用未定義フラグ'5' */
        public static final int NO_DEFINE_5 = 5;
        
        /** バッチ処理済みの処理状態。 */
        public static final int BAT_PROCED = 2;
        
    }
}
@
