head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStopInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 停止情報メッセージ(WEB3AccInfoStopInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.accountinfo.define.WEB3LockDivDef;
import webbroker3.accountinfo.define.WEB3OrderPermitDivDef;
import webbroker3.accountinfo.define.WEB3YellowAccountDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (停止情報)<BR>
 * 停止情報メッセージ<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoStopInfo extends Message 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoStopInfo.class);
        
    /**
     * (Ｙ客区分)<BR>
     * Ｙ客区分<BR>
     * <BR>
     * 0：　@DEFAULT（Ｙ客でない）<BR>
     * 1：　@Ｙ客<BR>
     * <BR>
     */
    public String yellowAccountDiv;
    
    /**
     * (管理ロック区分)<BR>
     * 管理ロック区分<BR>
     * <BR>
     * 0：　@DEFAULT（ロックでない）<BR>
     * 1：　@ロック中<BR>
     */
    public String mngLockDiv;
    
    /**
     * (管理ロック理由フラグ（立替金）)<BR>
     * 管理ロック理由フラグ（立替金）<BR>
     * <BR>
     * true：　@理由あり<BR>
     * false：　@理由なし<BR>
     * <BR>
     */
    public boolean mngExpenseLockReasonFlag;
    
    /**
     * (管理ロック理由フラグ（保証金未入）)<BR>
     * 管理ロック理由フラグ（保証金未入）<BR>
     * <BR>
     * true：　@理由あり<BR>
     * false：　@理由なし<BR>
     */
    public boolean mngDepositLockReasonFlag;
    
    /**
     * (管理ロック理由フラグ（適格担保不足）)<BR>
     * 管理ロック理由フラグ（適格担保不足）<BR>
     * <BR>
     * true：　@理由あり<BR>
     * false：　@理由なし<BR>
     * <BR>
     */
    public boolean mngCollateralLockReasonFlag;
    
    /**
     * (管理ロック理由フラグ（預り証長期未差替）)<BR>
     * 管理ロック理由フラグ（預り証長期未差替）<BR>
     * <BR>
     * true：　@理由あり<BR>
     * false：　@理由なし<BR>
     * <BR>
     */
    public boolean mngReceiptLockReasonFlag;
    
    /**
     * (管理ロック解除開始日)<BR>
     * 管理ロック解除開始日<BR>
     */
    public Date mngLockCancelStartDate;
    
    /**
     * (管理ロック解除終了日)<BR>
     * 管理ロック解除終了日<BR>
     */
    public Date mngLockCancelEndDate;
    
    /**
     * (支店ロック区分)<BR>
     * 支店ロック区分<BR>
     * <BR>
     * 0：　@DEFAULT（ロックでない）<BR>
     * 1：　@ロック中<BR>
     */
    public String branchLockDiv;
    
    /**
     * (注文認可区分)<BR>
     * 注文認可区分<BR>
     * <BR>
     * 0：　@認可<BR>
     * 1：　@非認可<BR>
     */
    public String orderPermitDiv;
    
    /**
     * (状況区分)<BR>
     * 状況区分<BR>
     * <BR>
     * 0：　@DEFAULT（禁止中でない）<BR>
     * 1：　@禁止中<BR>
     */
    public String stateDiv;
    
    /**
     * (停止状況登録理由)<BR>
     * 停止状況登録理由<BR>
     */
    public String stopStateRegistReason;
    
    /**
     * (Y客登録解除SONAR受付状況)<BR>
     * Y客登録解除SONAR受付状況 <BR>
     * <BR>
     * 0：未送信 <BR>
     * 1：送信済 <BR>
     * 9：エラー <BR>
     * null：データなし <BR>
     */
    public String yAccountSonarState;
    
    /**
     * (ロック客登録解除SONAR受付状況)<BR>
     * ロック客登録解除SONAR受付状況 <BR>
     * <BR>
     * 0：未送信 <BR>
     * 1：送信済 <BR>
     * 9：エラー <BR>
     * null：データなし <BR>
     */
    public String lockAccountSonarState;
    
    /**
     * (登録日時)<BR>
     * 登録日時<BR>
     */
    public Date registDate;
    
    /**
     * @@roseuid 418F39F7034B
     */
    public WEB3AccInfoStopInfo() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@Ｙ客区分のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01125<BR>
     * 　@１－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01126<BR>
     * <BR>
     * ２）　@管理ロック区分のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01127<BR>
     * 　@２－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01128<BR>
     * <BR>
     * ３）　@管理ロック解除開始日／管理ロック解除終了日のチェック <BR>
     * 　@３－１）　@どちらか一方のみの入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01129<BR>
     * 　@　@　@　@　@　@※両方未入力か、両方入力されていなければならない。<BR>
     * 　@３－２）　@管理ロック解除開始日 > 管理ロック解除終了日）の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01130<BR>  
     * <BR>
     * ４）　@支店ロック区分のチェック<BR>
     * 　@４－１）　@未入力の場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01131<BR>
     * 　@４－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01132<BR>
     * <BR>
     * ５）　@注文認可区分のチェック<BR>
     * 　@５－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01133<BR>    
     * 　@５－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01134<BR>
     * 　@５－３）　@（注文認可区分 == 0：認可）の場合、<BR>
     * 　@　@（支店ロック区分 == 0：DEFAULT（ロックでない） && <BR>
     * 　@　@ 管理ロック区分 == 0：DEFAULT（ロックでない） ）であれば、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01135<BR>    
     * <BR>
     * 　@　@※注文認可をする場合は、支店ロック区分，<BR>
     * または管理ロック区分のどちらかがロック中になっていること。<BR>
     * <BR>
     * ６）　@停止状況登録理由のチェック<BR>
     * 　@※停止状況登録理由に入力がある場合のみチェックする。<BR>
     * 　@６－１）　@文字数が40byteより大きかった場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02225<BR>   
     * 　@６－２）　@全角文字以外が含まれている場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02226<BR>   
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 415CF012023F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        /*
         * １）　@Ｙ客区分のチェック<BR>
         * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01125<BR>
         */
        if(yellowAccountDiv == null || "".equals(yellowAccountDiv))
        {
            log.debug("[Ｙ客区分] = " + yellowAccountDiv);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01125, 
                this.getClass().getName() + STR_METHOD_NAME,
                "Ｙ客区分未入力");
        }
        /*
         * 　@１－２）　@不正なコード値の場合、例外をスローする。 <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01126<BR>          
         */
         if(!(WEB3YellowAccountDivDef.DEFAULT).equals(yellowAccountDiv) && 
            !(WEB3YellowAccountDivDef.YELLOW_ACCOUNT).equals(yellowAccountDiv))
         {
             log.debug("[Ｙ客区分] = " + yellowAccountDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01126, 
                this.getClass().getName() + STR_METHOD_NAME,
                "Ｙ客区分不正なコード値");
         }
         
        /* 
         * ２）　@管理ロック区分のチェック<BR>
         * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01127<BR>
         */
         if(mngLockDiv == null || "".equals(mngLockDiv))
         {
             log.debug("[管理ロック区分] = " + mngLockDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01127, 
                this.getClass().getName() + STR_METHOD_NAME,
                "管理ロック区分未入力");
         }
         
        /* 
         * 　@２－２）　@不正なコード値の場合、例外をスローする。 <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01128<BR>
         */
         if(!(WEB3LockDivDef.DEFAULT).equals(mngLockDiv) && !(WEB3LockDivDef.LOCKING).equals(mngLockDiv))
         {
             log.debug("[管理ロック区分] = " + mngLockDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01128, 
                this.getClass().getName() + STR_METHOD_NAME,
                "管理ロック区分不正なコード値");
         }
        /* 
         * ３）　@管理ロック解除開始日／管理ロック解除終了日のチェック <BR>
         * 　@３－１）　@どちらか一方のみの入力の場合、例外をスローする。<BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01129<BR>
         * 　@　@　@　@　@　@※両方未入力か、両方入力されていなければならない。<BR>
         */
         if((mngLockCancelStartDate == null && mngLockCancelEndDate != null) || 
            (mngLockCancelStartDate != null) && (mngLockCancelEndDate == null))
         {
             log.debug("[管理ロック解除開始日] = " + mngLockCancelStartDate);
             log.debug("[管理ロック解除終了日] = " + mngLockCancelEndDate);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01129, 
                this.getClass().getName() + STR_METHOD_NAME,
                "管理ロック解除開始日／管理ロック解除終了日どちらか一方のみの入力");
         }
         
        /* 
         * 　@３－２）　@管理ロック解除開始日 > 管理ロック解除終了日）の場合、<BR>
         * 例外をスローする。<BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01130<BR>  
         * <BR>
         */
         if(WEB3DateUtility.compareToDay(mngLockCancelStartDate, mngLockCancelEndDate) > 0)
         {
             log.debug("[管理ロック解除開始日] = " + mngLockCancelStartDate);
             log.debug("[管理ロック解除終了日] = " + mngLockCancelEndDate);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01130, 
                this.getClass().getName() + STR_METHOD_NAME,
				"管理ロック解除開始日 > 管理ロック解除終了日");
         }
         
        /* 
         * ４）　@支店ロック区分のチェック<BR>
         * 　@４－１）　@未入力の場合、例外をスローする。<BR> 
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01131<BR>
         */
         if(branchLockDiv == null || "".equals(branchLockDiv))         
         {
             log.debug("[支店ロック区分] = " + branchLockDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01131, 
                this.getClass().getName() + STR_METHOD_NAME,
                "支店ロック区分未入力");
         }
         
        /* 
         * 　@４－２）　@不正なコード値の場合、例外をスローする。 <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01132<BR>
         * <BR>
         */
         if(!(WEB3LockDivDef.DEFAULT).equals(branchLockDiv) && !(WEB3LockDivDef.LOCKING).equals(branchLockDiv))
         {
             log.debug("[支店ロック区分] = " + branchLockDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01132, 
                this.getClass().getName() + STR_METHOD_NAME,
                "支店ロック区分不正なコード値");
         }
         
        /* 
         * ５）　@注文認可区分のチェック<BR>
         * 　@５－１）　@未入力の場合、例外をスローする。 <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01133<BR>
         */
         if(orderPermitDiv == null || "".equals(orderPermitDiv))
         {
             log.debug("[注文認可区分] = " + orderPermitDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01133, 
                this.getClass().getName() + STR_METHOD_NAME,
                "注文認可区分未入力");
         }
         
        /*     
         * 　@５－２）　@不正なコード値の場合、例外をスローする。 <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01134<BR>
         */
         if(!(WEB3OrderPermitDivDef.AUTHORIZATION).equals(orderPermitDiv) && 
            !(WEB3OrderPermitDivDef.NOT_AUTHORIZATION).equals(orderPermitDiv))
         {
             log.debug("[注文認可区分] = " + orderPermitDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01134, 
                this.getClass().getName() + STR_METHOD_NAME,
                "注文認可区分不正なコード値");
         }
         
        /* 
         * 　@５－３）　@（注文認可区分 == 0：認可）の場合、<BR>
         * 　@　@（支店ロック区分 == 0：DEFAULT（ロックでない） && <BR>
         * 　@　@ 管理ロック区分 == 0：DEFAULT（ロックでない） ）であれば、<BR>
         * 例外をスローする。<BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01135<BR>    
         * <BR>
         * 　@　@※注文認可をする場合は、支店ロック区分，<BR>
         * または管理ロック区分のどちらかがロック中になっていること。<BR>
         */
         if((WEB3OrderPermitDivDef.AUTHORIZATION).equals(orderPermitDiv) &&
            ((WEB3LockDivDef.DEFAULT).equals(branchLockDiv) &&
                (WEB3LockDivDef.DEFAULT).equals(mngLockDiv)))
         {
             log.debug("[注文認可区分] = " + orderPermitDiv);
             log.debug("[支店ロック区分] = " + orderPermitDiv);
             log.debug("[管理ロック区分] = " + orderPermitDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01135, 
                this.getClass().getName() + STR_METHOD_NAME,
                "注文認可区分 == 0：認可）の場合（支店ロック区分 == 0：DEFAULT（ロックでない） &&管理ロック区分 == 0：DEFAULT（ロックでない） ）");
         }
         
         /*
          * ６）　@停止状況登録理由のチェック
          * 　@※停止状況登録理由に入力がある場合のみチェックする
          */
         if (!WEB3StringTypeUtility.isEmpty(stopStateRegistReason))
         {
             //６－１）　@文字数が40byteより大きかった場合、例外をスローする。
             int l_intByteLength = WEB3StringTypeUtility.getByteLength(stopStateRegistReason);
             if (l_intByteLength > 40)
             {
                 log.debug("[停止状況登録理由] = " + stopStateRegistReason);
                 throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02225, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "停止状況登録理由の文字数が40byteより大きかったです。");
             }
             //６－２）　@全角文字以外が含まれている場合、例外をスローする。
             if (!WEB3StringTypeUtility.isMulti(stopStateRegistReason))
             {
                 log.debug("[停止状況登録理由] = " + stopStateRegistReason);
                 throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02226, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "停止状況登録理由が全角文字以外が含まれているです。");
             }
         }
    }
}
@
