head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleRcvdqProcStatusEnum.java;


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
 * RCVD_Q'処理区分'に関するエナム変数を定義する
 */
public class SleRcvdqProcStatusEnum extends Enumerated {
    /** 処理待ち状態をあらわします */
    public static final SleRcvdqProcStatusEnum TODO = new SleRcvdqProcStatusEnum(IntValues.TODO,
          "TODO");

    /** 処理済をあらわします。 */
    public static final SleRcvdqProcStatusEnum PROCESSED = new SleRcvdqProcStatusEnum(IntValues.PROCESSED,
          "PROCESSED");
    
    /** 処理が無視されたことをあらわします。 */
    public static final SleRcvdqProcStatusEnum SKIP_PROCESSING_IGNORE = new SleRcvdqProcStatusEnum(IntValues.SKIP_PROCESSING_IGNORE,
          "SKIP_PROCESSING_IGNORE");
    
    /** エラーのため処理が省略されたことをあらわします。 */
    public static final SleRcvdqProcStatusEnum SKIP_PROCESSING_ERROR = new SleRcvdqProcStatusEnum(IntValues.SKIP_PROCESSING_ERROR,
          "SKIP_PROCESSING_ERROR");

    /** 管理画面用未定義フラグ'2' */
    public static final SleRcvdqProcStatusEnum NO_DEFINE_2 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_2,
          "NO_DEFINE_2");
    
    /** 管理画面用未定義フラグ'3' */      
    public static final SleRcvdqProcStatusEnum NO_DEFINE_3 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_3,
          "NO_DEFINE_3");                    
    
    /** 管理画面用未定義フラグ'4' */
    public static final SleRcvdqProcStatusEnum NO_DEFINE_4 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_4,
          "NO_DEFINE_4");
    
    /** 管理画面用未定義フラグ'5' */
    public static final SleRcvdqProcStatusEnum NO_DEFINE_5 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_5,
          "NO_DEFINE_5");
          
    /** 管理画面用未定義フラグ'6' */
    public static final SleRcvdqProcStatusEnum NO_DEFINE_6 = new SleRcvdqProcStatusEnum(IntValues.NO_DEFINE_6,
          "NO_DEFINE_6");
    
    /** 約定処理中フラグ'7' */      
    public static final SleRcvdqProcStatusEnum EXEC_PROCESSING = new SleRcvdqProcStatusEnum(IntValues.EXEC_PROCESSING,
          "EXEC_PROCESSING");                    
    

    
    /**
     * 上位クラスのコンストラクタをオーバライドする必須のコンストラクタです。 
     *
     * @@param i int型の値
     * @@param s 文字列表現
     */
    public SleRcvdqProcStatusEnum(int i, String s) {
        super(i, s);
    }

    /**
     * 任意のエナムの整数値定義をする内部クラスです。整数値を定義することにより、 
     * このクラスをswitch文で容易に利用することができます。
     */
    public static class IntValues {

        //~ Static fields/initializers ---------------------------------------------

        /** 処理待ち状態をあらわします。 */
        public static final int TODO = 0;

        /** 処理済をあらわします。 */
        public static final int PROCESSED = 1;
        
        /** 処理が無視されたことをあらわします。 */
        public static final int SKIP_PROCESSING_IGNORE = 8;
        
        /** エラーのため処理が省略されたことをあらわします。 */
        public static final int SKIP_PROCESSING_ERROR = 9;
        
        /** 管理画面用未定義フラグ'2' */
        public static final int NO_DEFINE_2 = 2;
        
        /** 管理画面用未定義フラグ'3' */        
        public static final int NO_DEFINE_3 = 3;
        
        /** 管理画面用未定義フラグ'4' */
        public static final int NO_DEFINE_4 = 4;
        
        /** 管理画面用未定義フラグ'5' */
        public static final int NO_DEFINE_5 = 5;

        /** 管理画面用未定義フラグ'6' */
        public static final int NO_DEFINE_6 = 6;
        
        /** 約定処理中フラグ'7' */
        public static final int EXEC_PROCESSING = 7;
        
    }
    

}
@
