head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.32.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderEventTypeEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 *
 * Copyright 2000-2002 Fitech Laboratories, Inc. All Rights Reserved.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. FITECH LABORATORIES AND ITS LICENSORS
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL
 * FITECH LABORATORIES OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT
 * OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT
 * OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF FITECH LABORATORIES HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 *
 */
package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * <p>
 * Define Order Event Type enum.
 * </p>
 * @@author x-zhang
 * @@version 1.0
 */
public class OrderEventTypeEnum extends Enumerated {

    /** Optional Inner class to define values of integers used.
     * In this way the class can be easily used in switch statements
     */
    public static class IntValues {
        public static final int UNDEFINED = 0;
        public static final int NEW = 1;
        public static final int CHANGE  = 2;
        public static final int CANCEL  = 3;
        public  static final int SEND_TO_MKT  = 5;
        public  static final int CONFIRMED_BY_MKT  = 6;
        public  static final int REJECTED_BY_MKT  = 7;

        public  static final int MARKER_MESSAGE_COMPLETE_CHANGE_ORDER = 8;
        public  static final int MARKER_ACCEPTED_COMPLETE_CHANGE_ORDER = 9;
        public  static final int MARKER_REFUSAL_CHANGE_ORDER = 10;
        public  static final int MARKER_MESSAGE_COMPLETE_CANCEL_ORDER = 11;
        public  static final int MARKER_ACCEPTED_COMPLETE_CANCEL_ORDER = 12;
        public  static final int MARKER_REFUSAL_CANCEL_ORDER = 13;
        public  static final int MARKER_REFUSAL = 14;
        public  static final int APPROVED = 15;

        public static final int ORDER_DELAY = 21;
        public static final int SWITCH_DELAY = 22;

        public static final int EXECUTE  = 91;
        public static final int EXPIRE  = 92;

        public static final int  UNDO_INVALIDATION_BY_MKT = 93;
        public static final int  UNDO_EXECUTION           = 94;
        public static final int  ASSET_TRANSFER_DONE      = 95;

    }

    /** Unknown order event type */
    public static final OrderEventTypeEnum UNDEFINED  = new OrderEventTypeEnum( IntValues.UNDEFINED, "UNDEFINED" );

    /** A new order is placed and passed to market adapter */
    public static final OrderEventTypeEnum NEW  = new OrderEventTypeEnum( IntValues.NEW, "NEW" );

    /** A change order is placed and passed to market adapter */
    public static final OrderEventTypeEnum CHANGE = new OrderEventTypeEnum( IntValues.CHANGE, "CHANGE" );

    /** A cancel order is placed and passed to market adapter */
    public static final OrderEventTypeEnum CANCEL = new OrderEventTypeEnum( IntValues.CANCEL, "CANCEL" );

    /** Market adapter notifies order manager that the new order has been sent to market */
    public static final OrderEventTypeEnum SEND_TO_MKT = new OrderEventTypeEnum( IntValues.SEND_TO_MKT, "SEND_TO_MKT" );

    /** Market adapter notifies order manager that the new order has been confirmed by the market */
    public static final OrderEventTypeEnum CONFIRMED_BY_MKT = new OrderEventTypeEnum( IntValues.CONFIRMED_BY_MKT, "CONFIRMED_BY_MKT" );

    /** Market adapter notifies order manager that the new order has been rejected by the market */
    public static final OrderEventTypeEnum REJECTED_BY_MKT = new OrderEventTypeEnum( IntValues.REJECTED_BY_MKT, "REJECTED_BY_MKT" );

    /** マーケット送信済（変更注文） */
    public static final OrderEventTypeEnum MARKER_MESSAGE_COMPLETE_CHANGE_ORDER = new OrderEventTypeEnum( IntValues.MARKER_MESSAGE_COMPLETE_CHANGE_ORDER, "MARKER_MESSAGE_COMPLETE_CHANGE_ORDER" );

    /** マーケット受付済（変更注文） */
    public static final OrderEventTypeEnum MARKER_ACCEPTED_COMPLETE_CHANGE_ORDER = new OrderEventTypeEnum( IntValues.MARKER_ACCEPTED_COMPLETE_CHANGE_ORDER, "MARKER_ACCEPTED_COMPLETE_CHANGE_ORDER" );

    /** マーケット拒否（変更注文） */
    public static final OrderEventTypeEnum MARKER_REFUSAL_CHANGE_ORDER = new OrderEventTypeEnum( IntValues.MARKER_REFUSAL_CHANGE_ORDER, "MARKER_REFUSAL_CHANGE_ORDER" );

    /** マーケット送信済（取消注文） */
    public static final OrderEventTypeEnum MARKER_MESSAGE_COMPLETE_CANCEL_ORDER = new OrderEventTypeEnum( IntValues.MARKER_MESSAGE_COMPLETE_CANCEL_ORDER, "MARKER_MESSAGE_COMPLETE_CANCEL_ORDER" );

    /** マーケット受付済（取消注文） */
    public static final OrderEventTypeEnum MARKER_ACCEPTED_COMPLETE_CANCEL_ORDER = new OrderEventTypeEnum( IntValues.MARKER_ACCEPTED_COMPLETE_CANCEL_ORDER, "MARKER_ACCEPTED_COMPLETE_CANCEL_ORDER" );

    /** マーケット拒否（取消注文） */
    public static final OrderEventTypeEnum MARKER_REFUSAL_CANCEL_ORDER = new OrderEventTypeEnum( IntValues.MARKER_REFUSAL_CANCEL_ORDER, "MARKER_REFUSAL_CANCEL_ORDER" );

    /** マーケット送信拒否 */
    public static final OrderEventTypeEnum MARKER_REFUSAL = new OrderEventTypeEnum( IntValues.MARKER_REFUSAL, "MARKER_REFUSAL" );

    /** 承認済 */
    public static final OrderEventTypeEnum APPROVED = new OrderEventTypeEnum( IntValues.APPROVED, "APPROVED" );

    /** 発注遅延 */
    public static final OrderEventTypeEnum ORDER_DELAY = new OrderEventTypeEnum( IntValues.ORDER_DELAY, "ORDER_DELAY" );

    /** 切替遅延 */
    public static final OrderEventTypeEnum SWITCH_DELAY = new OrderEventTypeEnum( IntValues.SWITCH_DELAY, "SWITCH_DELAY" );
    
    /** Market adapter notifies order manager that there is execution on the order */
    public static final OrderEventTypeEnum EXECUTE = new OrderEventTypeEnum( IntValues.EXECUTE, "EXECUTE" );

    /** An order is expired */
    public static final OrderEventTypeEnum EXPIRE = new OrderEventTypeEnum( IntValues.EXPIRE, "EXPIRE" );

    /** Event indicating undo of an order invalidation message sent by market.  */
    public static final OrderEventTypeEnum UNDO_INVALIDATION_BY_MKT =
            new OrderEventTypeEnum( IntValues.UNDO_INVALIDATION_BY_MKT, "UNDO_INVALIDATION_BY_MKT" );

    /** Event indicating undo of an order execution.  */
    public static final OrderEventTypeEnum UNDO_EXECUTION =
            new OrderEventTypeEnum( IntValues.UNDO_EXECUTION, "UNDO_EXECUTION" );

    /** Event indicating asset transfer done .  */
    public static final OrderEventTypeEnum ASSET_TRANSFER_DONE  =
            new OrderEventTypeEnum( IntValues.ASSET_TRANSFER_DONE, "ASSET_TRANSFER_DONE" );

    /** Mandatory constructor override of superclass constructor */
    public OrderEventTypeEnum(int i, String s) {
        super(i, s);
    }
}@
