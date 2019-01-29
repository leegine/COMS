head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConnStatusChangesDbConstants.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleConstantsクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/06 李(FLJ) 新規作成
 */

package webbroker3.slebase.utils;


/**
 * Constants for fields in ma_sle_conn_status_changes.
 * コールバック処理専用
 */
public  interface   SleConnStatusChangesDbConstants{

    /**
     * new_status field constants.
     */
    public  static  interface   NEW_STATUS{

        /** Indicates that the connection is lost. */
        public  static  final   int CONNECTION_LOST =   0;

        /** Indicates that the connection is re-established. */
        public  static  final   int RECONNECTED =   1;

        /** Indicates that the SLE Connector is successfully started and estalished the initial connection with P3/SLE. */
        public  static  final   int STARTED =   2;

        /** Indicates that the SLE Connector is stopped and there is no connection with P3/SLE. */
        public  static  final   int STOPPED =   3;
    }

    public  static  interface   PROC_STATUS{

        /** indicates that the row is not yet processed. */
        public  static  final   int TODO    =   0;

        /** Indicates that the row has been processed. */
        public  static  final   int PROCESSED   =   1;
    }
    
    public static interface EVENT_ACK_DIV{
    	
		/** indicates whether the status change has been not acknowledged the ov */
    	public static final int UN_ACKED = 0;
    	
		/** indicates whether the status change has been acknowledged the ov */
		public static final int DONE_ACKED = 1;	
    }
    
	public static interface CONN_DIV{
    	
		/** indicates that active connector server being used.*/
		public static final int ACTIVE_CONN = 0;
    	
		/** indicates that the cold standby server being used */
		public static final int STANDBY_CONN = 1;	
	}
}


@
