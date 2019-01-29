head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.32.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ContractTypeEnum.java;


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
 * Define Contract Type enum.
 * </p> 
 * @@author x-zhang
 * @@version 1.0
 */
public class ContractTypeEnum extends Enumerated {

	/** Optional Inner class to define values of integers used.
    * In this way the class can be easily used in switch statements 
    */
	public static class IntValues {
		public static final int UNDEFINED = 0;
		public static final int LONG = 1;
		public static final int SHORT = 2;		
	}
	
	/** Undefined contract type */
	public static final ContractTypeEnum UNDEFINED  = new ContractTypeEnum( IntValues.UNDEFINED, "UNDEFINED" );
	
	/** Long contract type */
	public static final ContractTypeEnum LONG  = new ContractTypeEnum( IntValues.LONG, "LONG" );
	
	/** Short contract type */
	public static final ContractTypeEnum SHORT  = new ContractTypeEnum( IntValues.SHORT, "SHORT" );

	/** Mandatory constructor override of superclass constructor */
	public ContractTypeEnum(int i, String s) {
		super(i, s);
	}
	
	/**
	 * Return the ContractTypeEnum enum based on an FinTransactionType
	 * @@param trans_type input FinTransactionType
	 * @@return ContractTypeEnum associated with the passed FinTransactionType
	 */
	public static ContractTypeEnum getContractType(FinTransactionType trans_type){
		switch(trans_type.intValue()){
			case FinTransactionType.IntValues.EQTYPE_MARGIN_LONG:
			case FinTransactionType.IntValues.EQTYPE_CLOSE_MARGIN_LONG:
			case FinTransactionType.IntValues.EQTYPE_SWAP_MARGIN_LONG:
			case FinTransactionType.IntValues.EQTYPE_IDX_FUTURES_BUY_TO_OPEN:
			case FinTransactionType.IntValues.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE:
			case FinTransactionType.IntValues.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN:
			case FinTransactionType.IntValues.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE:			
				return ContractTypeEnum.LONG;

			case FinTransactionType.IntValues.EQTYPE_MARGIN_SHORT:
			case FinTransactionType.IntValues.EQTYPE_CLOSE_MARGIN_SHORT:
			case FinTransactionType.IntValues.EQTYPE_SWAP_MARGIN_SHORT:
			case FinTransactionType.IntValues.EQTYPE_IDX_FUTURES_SELL_TO_OPEN:
			case FinTransactionType.IntValues.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE:
			case FinTransactionType.IntValues.EQTYPE_IDX_OPTIONS_SELL_TO_OPEN:
			case FinTransactionType.IntValues.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE:			
				return ContractTypeEnum.SHORT;

			default:
			  return ContractTypeEnum.UNDEFINED;
		}		
	}
	
}
@
