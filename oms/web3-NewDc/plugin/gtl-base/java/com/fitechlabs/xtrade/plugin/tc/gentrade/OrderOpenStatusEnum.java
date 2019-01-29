head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderOpenStatusEnum.java;


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
 * Define Order Open Status enum.
 * </p> 
 * @@author x-zhang
 * @@version 1.0
 */
public class OrderOpenStatusEnum extends Enumerated {

	/** Optional Inner class to define values of integers used.
    * In this way the class can be easily used in switch statements 
    */
	public static class IntValues {
		public static final int UNDEFINED = 0;		
		public static final int OPEN = 1;
		public static final int CLOSED  = 2;
	}
	
	/** Unknown order open status */
	public static final OrderOpenStatusEnum UNDEFINED  = new OrderOpenStatusEnum( IntValues.UNDEFINED, "UNDEFINED" );
	
	/** Status showing that an order is open */
	public static final OrderOpenStatusEnum OPEN  = new OrderOpenStatusEnum( IntValues.OPEN, "OPEN" );
	
	/** Status showing that an order is closed */
	public static final OrderOpenStatusEnum CLOSED = new OrderOpenStatusEnum( IntValues.CLOSED, "CLOSED" );


	/** Mandatory constructor override of superclass constructor */
	public OrderOpenStatusEnum(int i, String s) {
		super(i, s);
	}
}


@
